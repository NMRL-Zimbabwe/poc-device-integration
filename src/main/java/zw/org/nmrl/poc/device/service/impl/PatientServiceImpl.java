package zw.org.nmrl.poc.device.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zw.org.nmrl.poc.device.domain.Analysis;
import zw.org.nmrl.poc.device.domain.Patient;
import zw.org.nmrl.poc.device.domain.AnalysisRequest;
import zw.org.nmrl.poc.device.domain.id.AnalysisId;
import zw.org.nmrl.poc.device.repository.AnalysisRepository;
import zw.org.nmrl.poc.device.repository.PatientRepository;
import zw.org.nmrl.poc.device.repository.AnalysisRequestRepository;
import zw.org.nmrl.poc.device.service.PatientService;
import zw.org.nmrl.poc.device.service.dto.AnalysisDTO;
import zw.org.nmrl.poc.device.service.dto.AnalysisRequestAnalysisProfileDTO;
import zw.org.nmrl.poc.device.service.dto.PatientDTO;
import zw.org.nmrl.poc.device.service.dto.UnifiedPatientSampleCaseAnalysisDTO;

/**
 * Service Implementation for managing {@link Patient}.
 */
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	private final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

	private final PatientRepository patientRepository;

	private final AnalysisRequestRepository sampleRepository;

	private final AnalysisRepository analysisRepository;

	public PatientServiceImpl(PatientRepository patientRepository, AnalysisRequestRepository sampleRepository,
			AnalysisRepository analysisRepository) {
		this.patientRepository = patientRepository;
		this.sampleRepository = sampleRepository;
		this.analysisRepository = analysisRepository;
	}

	@Override
	public Patient save(Patient patient) {
		log.debug("Request to save Patient : {}", patient);
		return patientRepository.save(patient);
	}

	@Override
	public Patient update(Patient patient) {
		log.debug("Request to update Patient : {}", patient);
		return patientRepository.save(patient);
	}

	@Override
	public Optional<Patient> partialUpdate(Patient patient) {
		log.debug("Request to partially update Patient : {}", patient);

		return patientRepository.findByPatientId(patient.getPatientId()).map(existingPatient -> {
			if (patient.getFirstName() != null) {
				existingPatient.setFirstName(patient.getFirstName());
			}
			if (patient.getLastName() != null) {
				existingPatient.setLastName(patient.getLastName());
			}
			if (patient.getBirthDate() != null) {
				existingPatient.setBirthDate(patient.getBirthDate());
			}

			if (patient.getGender() != null) {
				existingPatient.setGender(patient.getGender());
			}
			if (patient.getPrimaryReferrer() != null) {
				existingPatient.setPrimaryReferrer(patient.getPrimaryReferrer());
			}
			if (patient.getClientPatientId() != null) {
				existingPatient.setClientPatientId(patient.getClientPatientId());
			}

			return existingPatient;
		}).map(patientRepository::save);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Patient> findAll(Pageable pageable) {
		log.debug("Request to get all Patients");
		return patientRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Patient> findOne(Long id) {
		log.debug("Request to get Patient : {}", id);
		return null; // patientRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete Patient : {}", id);
		// patientRepository.deleteById(id);
	}

	@Override
	public Patient saveBatch(UnifiedPatientSampleCaseAnalysisDTO batch) {
		Patient savedPatient = null;
		if (batch.getPatient() != null) {

			PatientDTO ptDTO = batch.getPatient();

			Patient pt = new Patient();
			pt.setPatientId(UUID.randomUUID().toString());
			pt.setPatientUid(ptDTO.getPatientUid());

			pt.setClientPatientId(ptDTO.getClientPatientId());
			pt.setFirstName(ptDTO.getFirstName());
			pt.setLastName(ptDTO.getLastName());
			pt.setBirthDate(ptDTO.getBirthDate());
			pt.setGender(ptDTO.getGender());
			pt.setPrimaryReferrer(ptDTO.getPrimaryReferrer());

			savedPatient = patientRepository.save(pt);

			if (savedPatient != null && batch.getAnalysisRequest() != null) {

				AnalysisRequestAnalysisProfileDTO anDTO = batch.getAnalysisRequest();

				AnalysisRequest sample = new AnalysisRequest();

				sample.setAnalysisRequestId(UUID.randomUUID().toString());
				sample.setAnalysisRequestUid(anDTO.getAnalysisRequestUid());

				sample.setPatientId(savedPatient.getPatientId());

				sample.setPatientUid(anDTO.getPatientId());
				sample.setClientSampleId(anDTO.getClientSampleId());
				// sample.setClientContact(anDTO.getClientContactName());
				// sample.setSampleTypeName(anDTO.getSampleTypeName());
				sample.setDateCollected(anDTO.getDateCollected());
				sample.setDateRegistered(anDTO.getDateRegistered());
				// sample.setDateTested(anDTO.getDateTested());
				sample.setDatePublished(anDTO.getDatePublished());
				// sample.setState(anDTO.getReviewState());
				sample.setSource(anDTO.getSource());

				AnalysisRequest savedSam = sampleRepository.save(sample);

				if (savedSam != null && batch.getAnalysisRequest().getAnalyses() != null) {

					for (AnalysisDTO analysisDTO : batch.getAnalysisRequest().getAnalyses()) {

						Analysis an = new Analysis();
						an.setAnalysisId(UUID.randomUUID().toString());
						
						an.setAnalysisUid(analysisDTO.getAnalysisUid());

						an.setAnalysisRequestId(savedSam.getAnalysisRequestId());
						an.setAnalysisRequestUid(analysisDTO.getAnalysisRequestUid());
						
						an.setLabId(anDTO.getLabId());
						an.setResult(analysisDTO.getResult());
						an.setStatus(analysisDTO.getStatus());
						an.setReviewState(analysisDTO.getReviewState());
						an.setAnalyst(analysisDTO.getAnalyst());
						an.setReviewer(analysisDTO.getReviewer());
						an.setRemarks(analysisDTO.getRemarks());
						an.setMethod(analysisDTO.getMethod());
						an.setSubmitter(analysisDTO.getSubmitter());
						an.setHidden(analysisDTO.getHidden());
						an.setRetest(analysisDTO.getRetest());
						an.setRetestOf(analysisDTO.getRetestOf());
						an.setUnit(analysisDTO.getUnit());
						an.setInterpretedResult(analysisDTO.getInterpretedResult());

						analysisRepository.save(an);
					}

				}

			}
		}
		return savedPatient;
	}
}
