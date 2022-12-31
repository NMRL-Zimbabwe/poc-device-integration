package zw.org.nmrl.poc.device.service.impl;

/**
 * RabbitMQ worker responsible for sending laboratory request to LIMS
 *
 *
 * @author Lawrence Chirowodza
 * @date 2022
 */
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import zw.org.nmrl.poc.device.domain.AnalysisRequest;
import zw.org.nmrl.poc.device.domain.IdServer;
import zw.org.nmrl.poc.device.domain.Patient;
import zw.org.nmrl.poc.device.management.enumeration.LaboratoryRequestStatus;
import zw.org.nmrl.poc.device.repository.AnalysisRequestRepository;
import zw.org.nmrl.poc.device.repository.ClientRepository;
import zw.org.nmrl.poc.device.repository.IdServerRepository;
import zw.org.nmrl.poc.device.repository.PatientRepository;
import zw.org.nmrl.poc.device.service.dto.AnalysisRequestAnalysisProfileDTO;
import zw.org.nmrl.poc.device.service.dto.PatientDTO;
import zw.org.nmrl.poc.device.service.dto.UnifiedPatientSampleCaseAnalysisDTO;

@Service
public class SendToCentralRepoService {
	private static final Logger log = LoggerFactory.getLogger(SendToCentralRepoService.class);

	@Autowired
	AnalysisRequestRepository sampleRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	IdServerRepository idServerRepository;

	@Autowired
	@Qualifier(value = "centralRepository")
	private AmqpTemplate amqpTemplate;

	public void sendMessageToLims(UnifiedPatientSampleCaseAnalysisDTO message, String routingKey)
			throws JsonProcessingException {
		System.out.println("[******] Waiting for messages.");

		ObjectMapper mapper = new ObjectMapper();
		Object jsonMessage = mapper.writeValueAsString(message);
		amqpTemplate.convertAndSend("amq.direct", "pocWeb", jsonMessage.toString());
		// amqpTemplate.convertAndSend(jsonMessage);

	}

	@Scheduled(fixedRate = 5000)
	public void requestBuilder() throws Exception {
		
		/*
		 * IdServer id = idServerRepository.findByPrefixIgnoreCase("Scheduled");
		 * 
		 * if (id == null) { log.debug("Scheduled task has not been setup");
		 * 
		 * return; }
		 * 
		 * if (id != null && id.getNumber() != 1) {
		 * log.debug("Syncing of Samples  Scheduled task is turned off"); return; }
		 */
		/**
		 * Search for records that have not been sent to Central Repository
		 */

		UnifiedPatientSampleCaseAnalysisDTO unifiedLimsRequest = new UnifiedPatientSampleCaseAnalysisDTO();
		int retry = 4;
		List<AnalysisRequest> sendToLims = sampleRepository.findBySyncIsNullAndRetryLessThan(retry);
		for (AnalysisRequest request : sendToLims) {
			log.info("AnalysisRequest found ******* :{}", request );
			
			Optional<Patient> isPatient = patientRepository.findByPatientIdAndPatientUidAndRetryLessThan(
					request.getPatientId(), request.getPatientUid(), retry);

			/**
			 * Ignore records with empty PENDING_RESOLVE ART
			 */
			if (isPatient.isPresent()) {
				// continue executing scripts below
			} else {
				return;
			}
			Patient patient = isPatient.get();
			/**
			 * Construct patient details
			 */
			/**
			 * Optional<Laboratory> laboratory =
			 * laboratoryRepository.findByCode(request.getLabId()); String destination =
			 * null; if (laboratory.isPresent()) {
			 * unifiedLimsRequest.setLabId(laboratory.get().getId());
			 * unifiedLimsRequest.setLabName(laboratory.get().getName()); destination =
			 * laboratory.get().getType(); } else { if (request.getLabId() != null) {
			 * log.error("UNKNOWN Laboratory :{}", request.getLabId()); }
			 * flushOurErrorsFromQueue(request, "UNKNOWN Laboratory"); return; }
			 **/
			
			log.info("Patient found ******* :{}", request );
			
			PatientDTO pt = new PatientDTO();

			pt.setFirstName(patient.getFirstName());
			pt.setLastName(patient.getLastName());
			pt.setClientPatientId(patient.getClientPatientId());
			//pt.setBirthDate(patient.getBirthDate());
			pt.setBirthDateEstimated(patient.getBirthDateEstimated());
			pt.setGender(patient.getGender());
			pt.setPrimaryReferrer(patient.getPrimaryReferrer());

			unifiedLimsRequest.setPatient(pt);

			/**
			 * Construct case/batch details
			 */

			/**
			 * Construct laboratory request details
			 */

			AnalysisRequestAnalysisProfileDTO analysisRequest = new AnalysisRequestAnalysisProfileDTO();
			
			analysisRequest.setAnalysisRequestId(UUID.randomUUID().toString());
			analysisRequest.setAnalysisRequestUid(request.getAnalysisRequestUid());

			//analysisRequest.setDateCollected(request.getDateCollected());
			
			analysisRequest.setPatientId(request.getPatientId());
			analysisRequest.setPatientUid(request.getPatientUid());
			analysisRequest.setClientId(request.getClientId());
			analysisRequest.setSampleTypeName(request.getSampleTypeIdAlias());

			String profiles[] = new String[] { "Viral Load Plasma" };

			unifiedLimsRequest.setAnalysisRequest(analysisRequest);

			// The code below will be substituted with the bundled request

			String routingKey = "pocWeb";

			if (unifiedLimsRequest.getPatient().getClientPatientId() != null) {
				log.info("Sending to Central repo found ******* :{}", request );
				sendMessageToLims(unifiedLimsRequest, routingKey); // Flag sent request as sent to Central Repo
				request.setSync(LaboratoryRequestStatus.SENT_TO_CENTRAL_REPOSITORY.toString());

				// TODO move the persistence process to a service
				sampleRepository.save(request);

				log.info("Sending UNIFIED messege to LIMS for ******* :{} ",
						unifiedLimsRequest.getPatient().getFirstName());
			} else {
				
				log.info("ClientPatientID  is null :{} ", request);
				request.setStatus(LaboratoryRequestStatus.REJECTED.toString());
				request.setSentToLims(LaboratoryRequestStatus.DECLINED.toString());
				request.setErrorReason("Rejected No Client Patient ID found");
				sampleRepository.save(request);
			}
		}
	}

	private void flushOurErrorsFromQueue(AnalysisRequest request, String error_reason) {
		request.setRetry(request.getRetry() + 1);
		request.setErrorReason(error_reason);
		sampleRepository.save(request);
	}
}
