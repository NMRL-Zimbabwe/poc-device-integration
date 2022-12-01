package zw.org.nmrl.poc.device.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import zw.org.nmrl.poc.device.domain.*; // for static metamodels
import zw.org.nmrl.poc.device.domain.Sample;
import zw.org.nmrl.poc.device.repository.SampleRepository;
import zw.org.nmrl.poc.device.service.criteria.SampleCriteria;

/**
 * Service for executing complex queries for {@link Sample} entities in the database.
 * The main input is a {@link SampleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Sample} or a {@link Page} of {@link Sample} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SampleQueryService extends QueryService<Sample> {

    private final Logger log = LoggerFactory.getLogger(SampleQueryService.class);

    private final SampleRepository sampleRepository;

    public SampleQueryService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    /**
     * Return a {@link List} of {@link Sample} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Sample> findByCriteria(SampleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Sample> specification = createSpecification(criteria);
        return sampleRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Sample} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Sample> findByCriteria(SampleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Sample> specification = createSpecification(criteria);
        return sampleRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SampleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Sample> specification = createSpecification(criteria);
        return sampleRepository.count(specification);
    }

    /**
     * Function to convert {@link SampleCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Sample> createSpecification(SampleCriteria criteria) {
        Specification<Sample> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Sample_.id));
            }
            if (criteria.getPatientId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPatientId(), Sample_.patientId));
            }
            if (criteria.getClientSampleId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientSampleId(), Sample_.clientSampleId));
            }
            if (criteria.getClientContact() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientContact(), Sample_.clientContact));
            }
            if (criteria.getSampleTypeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSampleTypeId(), Sample_.sampleTypeId));
            }
            if (criteria.getSampleTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSampleTypeName(), Sample_.sampleTypeName));
            }
            if (criteria.getAnalysisServiceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAnalysisServiceId(), Sample_.analysisServiceId));
            }
            if (criteria.getAnalysisServiceName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAnalysisServiceName(), Sample_.analysisServiceName));
            }
            if (criteria.getDateCollected() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCollected(), Sample_.dateCollected));
            }
            if (criteria.getDateRegistered() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateRegistered(), Sample_.dateRegistered));
            }
            if (criteria.getDateTested() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTested(), Sample_.dateTested));
            }
            if (criteria.getResult() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResult(), Sample_.result));
            }
            if (criteria.getUnit() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUnit(), Sample_.unit));
            }
            if (criteria.getDatePublished() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatePublished(), Sample_.datePublished));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildStringSpecification(criteria.getState(), Sample_.state));
            }
        }
        return specification;
    }
}
