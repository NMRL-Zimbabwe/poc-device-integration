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
import zw.org.nmrl.poc.device.domain.Sample;
import zw.org.nmrl.poc.device.domain.id.AnalysisId;
import zw.org.nmrl.poc.device.repository.AnalysisRepository;
import zw.org.nmrl.poc.device.repository.PatientRepository;
import zw.org.nmrl.poc.device.repository.SampleRepository;
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

	private final SampleRepository sampleRepository;

	private final AnalysisRepository analysisRepository;

	public PatientServiceImpl(PatientRepository patientRepository, SampleRepository sampleRepository,
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

		return patientRepository.findById(patient.getId()).map(existingPatient -> {
			if (patient.getFirstName() != null) {
				existingPatient.setFirstName(patient.getFirstName());
			}
			if (patient.getLastName() != null) {
				existingPatient.setLastName(patient.getLastName());
			}
			if (patient.getDob() != null) {
				existingPatient.setDob(patient.getDob());
			}
			if (patient.getAge() != null) {
				existingPatient.setAge(patient.getAge());
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
		return patientRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete Patient : {}", id);
		patientRepository.deleteById(id);
	}

	@Override
	public Patient saveBatch(UnifiedPatientSampleCaseAnalysisDTO batch) {
		if (batch.getPatient() != null) {
			PatientDTO ptDTO = batch.getPatient();
			Patient pt = new Patient();
			pt.setFirstName(ptDTO.getFirstName());
			pt.setLastName(ptDTO.getLastName());
			pt.setDob(ptDTO.getBirthDate());
			pt.setGender(ptDTO.getGender().toString());
			pt.setPrimaryReferrer(ptDTO.getPrimaryReferrer());

			Patient savedPatient = patientRepository.save(pt);

			if (savedPatient != null && batch.getAnalysisRequest() != null) {

				AnalysisRequestAnalysisProfileDTO anDTO = batch.getAnalysisRequest();

				Sample sample = new Sample();

				sample.setPatientId(anDTO.getPatientId());
				sample.setClientSampleId(anDTO.getClientSampleId());
				sample.setClientContact(anDTO.getClientContactName());
				sample.setSampleTypeName(anDTO.getSampleTypeName());
				sample.setDateCollected(anDTO.getDateCollected());
				sample.setDateRegistered(anDTO.getDateRegistered());
				sample.setDateTested(anDTO.getDateTested());
				sample.setDatePublished(anDTO.getDatePublished());
				sample.setState(anDTO.getReviewState());
				sample.setSource(anDTO.getSource());

				Sample savedSam = sampleRepository.save(sample);

				if (savedSam != null && batch.getAnalysisRequest().getAnalyses() != null) {

					for (AnalysisDTO analysisDTO : batch.getAnalysisRequest().getAnalyses()) {

						Analysis an = new Analysis();
						an.setAnalysisId(new AnalysisId(analysisDTO.getAnalysisId(), UUID.randomUUID().toString()));
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
		return null;
	}
}
