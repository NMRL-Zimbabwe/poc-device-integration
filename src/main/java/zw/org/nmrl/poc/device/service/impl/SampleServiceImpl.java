package zw.org.nmrl.poc.device.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.org.nmrl.poc.device.domain.Sample;
import zw.org.nmrl.poc.device.repository.SampleRepository;
import zw.org.nmrl.poc.device.service.SampleService;

/**
 * Service Implementation for managing {@link Sample}.
 */
@Service
@Transactional
public class SampleServiceImpl implements SampleService {

    private final Logger log = LoggerFactory.getLogger(SampleServiceImpl.class);

    private final SampleRepository sampleRepository;

    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Sample save(Sample sample) {
        log.debug("Request to save Sample : {}", sample);
        return sampleRepository.save(sample);
    }

    @Override
    public Sample update(Sample sample) {
        log.debug("Request to update Sample : {}", sample);
        return sampleRepository.save(sample);
    }

    @Override
    public Optional<Sample> partialUpdate(Sample sample) {
        log.debug("Request to partially update Sample : {}", sample);

        return sampleRepository
            .findById(sample.getId())
            .map(existingSample -> {
                if (sample.getPatientId() != null) {
                    existingSample.setPatientId(sample.getPatientId());
                }
                if (sample.getClientSampleId() != null) {
                    existingSample.setClientSampleId(sample.getClientSampleId());
                }
                if (sample.getClientContact() != null) {
                    existingSample.setClientContact(sample.getClientContact());
                }
                if (sample.getSampleTypeId() != null) {
                    existingSample.setSampleTypeId(sample.getSampleTypeId());
                }
                if (sample.getSampleTypeName() != null) {
                    existingSample.setSampleTypeName(sample.getSampleTypeName());
                }
                if (sample.getAnalysisServiceId() != null) {
                    existingSample.setAnalysisServiceId(sample.getAnalysisServiceId());
                }
                if (sample.getAnalysisServiceName() != null) {
                    existingSample.setAnalysisServiceName(sample.getAnalysisServiceName());
                }
                if (sample.getDateCollected() != null) {
                    existingSample.setDateCollected(sample.getDateCollected());
                }
                if (sample.getDateRegistered() != null) {
                    existingSample.setDateRegistered(sample.getDateRegistered());
                }
                if (sample.getDateTested() != null) {
                    existingSample.setDateTested(sample.getDateTested());
                }
                if (sample.getResult() != null) {
                    existingSample.setResult(sample.getResult());
                }
                if (sample.getUnit() != null) {
                    existingSample.setUnit(sample.getUnit());
                }
                if (sample.getDatePublished() != null) {
                    existingSample.setDatePublished(sample.getDatePublished());
                }
                if (sample.getState() != null) {
                    existingSample.setState(sample.getState());
                }

                return existingSample;
            })
            .map(sampleRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sample> findAll(Pageable pageable) {
        log.debug("Request to get all Samples");
        return sampleRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sample> findOne(Long id) {
        log.debug("Request to get Sample : {}", id);
        return sampleRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sample : {}", id);
        sampleRepository.deleteById(id);
    }
}
