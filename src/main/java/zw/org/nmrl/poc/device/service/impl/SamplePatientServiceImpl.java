package zw.org.nmrl.poc.device.service.impl;

import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.org.nmrl.poc.device.domain.SamplePatient;
import zw.org.nmrl.poc.device.repository.SamplePatientRepository;
import zw.org.nmrl.poc.device.service.SamplePatientService;

/**
 * Service Implementation for managing {@link SamplePatient}.
 */
@Service
@Transactional
public class SamplePatientServiceImpl implements SamplePatientService {

    private final Logger log = LoggerFactory.getLogger(SamplePatientServiceImpl.class);

    private final SamplePatientRepository samplePatientRepository;

    public SamplePatientServiceImpl(SamplePatientRepository samplePatientRepository) {
        this.samplePatientRepository = samplePatientRepository;
    }

    @Override
    public SamplePatient save(SamplePatient samplePatient) {
        log.debug("Request to save SamplePatient : {}", samplePatient);

        if (samplePatient.getId() == null) {
            samplePatient.setId(UUID.randomUUID().toString());
        }

        SamplePatient exist = samplePatientRepository.findByExternalUniqueIdentifier(samplePatient.getExternalUniqueIdentifier());

        if (exist != null) {
            samplePatient.setId(exist.getId());
        }

        return samplePatientRepository.save(samplePatient);
    }

    @Override
    public SamplePatient update(SamplePatient samplePatient) {
        log.debug("Request to update SamplePatient : {}", samplePatient);
        return samplePatientRepository.save(samplePatient);
    }

    @Override
    public Optional<SamplePatient> partialUpdate(SamplePatient samplePatient) {
        log.debug("Request to partially update SamplePatient : {}", samplePatient);

        return samplePatientRepository
            .findById(samplePatient.getId().toString())
            .map(existingSamplePatient -> {
                if (samplePatient.getFirstName() != null) {
                    existingSamplePatient.setFirstName(samplePatient.getFirstName());
                }
                if (samplePatient.getLastName() != null) {
                    existingSamplePatient.setLastName(samplePatient.getLastName());
                }
                if (samplePatient.getDob() != null) {
                    existingSamplePatient.setDob(samplePatient.getDob());
                }
                if (samplePatient.getAge() != null) {
                    existingSamplePatient.setAge(samplePatient.getAge());
                }
                if (samplePatient.getGender() != null) {
                    existingSamplePatient.setGender(samplePatient.getGender());
                }
                if (samplePatient.getPrimaryReferrer() != null) {
                    existingSamplePatient.setPrimaryReferrer(samplePatient.getPrimaryReferrer());
                }
                if (samplePatient.getClientPatientId() != null) {
                    existingSamplePatient.setClientPatientId(samplePatient.getClientPatientId());
                }
                if (samplePatient.getClientSampleId() != null) {
                    existingSamplePatient.setClientSampleId(samplePatient.getClientSampleId());
                }
                if (samplePatient.getClientContact() != null) {
                    existingSamplePatient.setClientContact(samplePatient.getClientContact());
                }
                if (samplePatient.getSampleTypeId() != null) {
                    existingSamplePatient.setSampleTypeId(samplePatient.getSampleTypeId());
                }
                if (samplePatient.getSampleTypeName() != null) {
                    existingSamplePatient.setSampleTypeName(samplePatient.getSampleTypeName());
                }
                if (samplePatient.getAnalysisServiceId() != null) {
                    existingSamplePatient.setAnalysisServiceId(samplePatient.getAnalysisServiceId());
                }
                if (samplePatient.getAnalysisServiceName() != null) {
                    existingSamplePatient.setAnalysisServiceName(samplePatient.getAnalysisServiceName());
                }
                if (samplePatient.getDateCollected() != null) {
                    existingSamplePatient.setDateCollected(samplePatient.getDateCollected());
                }
                if (samplePatient.getDateRegistered() != null) {
                    existingSamplePatient.setDateRegistered(samplePatient.getDateRegistered());
                }
                if (samplePatient.getDateTested() != null) {
                    existingSamplePatient.setDateTested(samplePatient.getDateTested());
                }
                if (samplePatient.getResult() != null) {
                    existingSamplePatient.setResult(samplePatient.getResult());
                }
                if (samplePatient.getUnit() != null) {
                    existingSamplePatient.setUnit(samplePatient.getUnit());
                }
                if (samplePatient.getDatePublished() != null) {
                    existingSamplePatient.setDatePublished(samplePatient.getDatePublished());
                }
                if (samplePatient.getState() != null) {
                    existingSamplePatient.setState(samplePatient.getState());
                }

                return existingSamplePatient;
            })
            .map(samplePatientRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SamplePatient> findAll(Pageable pageable) {
        log.debug("Request to get all SamplePatients");
        return samplePatientRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SamplePatient> findOne(String id) {
        log.debug("Request to get SamplePatient : {}", id);
        return samplePatientRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SamplePatient : {}", id);
        samplePatientRepository.deleteById(id);
    }
}
