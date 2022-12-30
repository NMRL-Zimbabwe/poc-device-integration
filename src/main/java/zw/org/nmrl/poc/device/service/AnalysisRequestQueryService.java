package zw.org.nmrl.poc.device.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.jhipster.service.QueryService;
// for static metamodels
import zw.org.nmrl.poc.device.domain.AnalysisRequest;
import zw.org.nmrl.poc.device.repository.AnalysisRequestRepository;
import zw.org.nmrl.poc.device.service.criteria.AnalysisRequestCriteria;
import zw.org.nmrl.poc.device.service.criteria.AnalysisRequestCriteria;

/**
 * Service for executing complex queries for {@link AnalysisRequest} entities in the database.
 * The main input is a {@link AnalysisRequestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnalysisRequest} or a {@link Page} of {@link AnalysisRequest} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnalysisRequestQueryService extends QueryService<AnalysisRequest> {

    private final Logger log = LoggerFactory.getLogger(AnalysisRequestQueryService.class);

    private final AnalysisRequestRepository sampleRepository;

    public AnalysisRequestQueryService(AnalysisRequestRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    /**
     * Return a {@link List} of {@link AnalysisRequest} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AnalysisRequest> findByCriteria(AnalysisRequestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AnalysisRequest> specification = createSpecification(criteria);
        return sampleRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link AnalysisRequest} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AnalysisRequest> findByCriteria(AnalysisRequestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AnalysisRequest> specification = createSpecification(criteria);
        return sampleRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AnalysisRequestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AnalysisRequest> specification = createSpecification(criteria);
        return sampleRepository.count(specification);
    }

    /**
     * Function to convert {@link AnalysisRequestCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AnalysisRequest> createSpecification(AnalysisRequestCriteria criteria) {
        Specification<AnalysisRequest> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            /**
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AnalysisRequest_.id));
            }
            if (criteria.getPatientId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPatientId(), AnalysisRequest_.patientId));
            }
            if (criteria.getClientAnalysisRequestId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientAnalysisRequestId(), AnalysisRequest_.clientAnalysisRequestId));
            }
            if (criteria.getClientContact() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientContact(), AnalysisRequest_.clientContact));
            }
            if (criteria.getAnalysisRequestTypeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAnalysisRequestTypeId(), AnalysisRequest_.sampleTypeId));
            }
            if (criteria.getAnalysisRequestTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAnalysisRequestTypeName(), AnalysisRequest_.sampleTypeName));
            }
            if (criteria.getAnalysisServiceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAnalysisServiceId(), AnalysisRequest_.analysisServiceId));
            }
            if (criteria.getAnalysisServiceName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAnalysisServiceName(), AnalysisRequest_.analysisServiceName));
            }
            if (criteria.getDateCollected() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCollected(), AnalysisRequest_.dateCollected));
            }
            if (criteria.getDateRegistered() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateRegistered(), AnalysisRequest_.dateRegistered));
            }
            if (criteria.getDateTested() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTested(), AnalysisRequest_.dateTested));
            }
            if (criteria.getResult() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResult(), AnalysisRequest_.result));
            }
            if (criteria.getUnit() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUnit(), AnalysisRequest_.unit));
            }
            if (criteria.getDatePublished() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatePublished(), AnalysisRequest_.datePublished));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildStringSpecification(criteria.getState(), AnalysisRequest_.state));
            }
            **/
        }
        return specification;
    }
}
