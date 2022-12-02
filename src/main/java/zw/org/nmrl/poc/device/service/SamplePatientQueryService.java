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
import zw.org.nmrl.poc.device.domain.SamplePatient;
import zw.org.nmrl.poc.device.repository.SamplePatientRepository;
import zw.org.nmrl.poc.device.service.criteria.SamplePatientCriteria;

/**
 * Service for executing complex queries for {@link SamplePatient} entities in the database.
 * The main input is a {@link SamplePatientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SamplePatient} or a {@link Page} of {@link SamplePatient} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SamplePatientQueryService extends QueryService<SamplePatient> {

    private final Logger log = LoggerFactory.getLogger(SamplePatientQueryService.class);

    private final SamplePatientRepository samplePatientRepository;

    public SamplePatientQueryService(SamplePatientRepository samplePatientRepository) {
        this.samplePatientRepository = samplePatientRepository;
    }

    /**
     * Return a {@link List} of {@link SamplePatient} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SamplePatient> findByCriteria(SamplePatientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SamplePatient> specification = createSpecification(criteria);
        return samplePatientRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link SamplePatient} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SamplePatient> findByCriteria(SamplePatientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SamplePatient> specification = createSpecification(criteria);
        return samplePatientRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SamplePatientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SamplePatient> specification = createSpecification(criteria);
        return samplePatientRepository.count(specification);
    }

    /**
     * Function to convert {@link SamplePatientCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SamplePatient> createSpecification(SamplePatientCriteria criteria) {
        Specification<SamplePatient> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            //            if (criteria.getId() != null) {
            //                specification = specification.and(buildRangeSpecification(criteria.getId().toString(), SamplePatient_.id.toString()));
            //            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), SamplePatient_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), SamplePatient_.lastName));
            }
            if (criteria.getDob() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDob(), SamplePatient_.dob));
            }
            if (criteria.getAge() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAge(), SamplePatient_.age));
            }
            if (criteria.getGender() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGender(), SamplePatient_.gender));
            }
            if (criteria.getPrimaryReferrer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrimaryReferrer(), SamplePatient_.primaryReferrer));
            }
            if (criteria.getClientPatientId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientPatientId(), SamplePatient_.clientPatientId));
            }
            if (criteria.getClientSampleId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientSampleId(), SamplePatient_.clientSampleId));
            }
            if (criteria.getClientContact() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientContact(), SamplePatient_.clientContact));
            }
            if (criteria.getSampleTypeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSampleTypeId(), SamplePatient_.sampleTypeId));
            }
            if (criteria.getSampleTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSampleTypeName(), SamplePatient_.sampleTypeName));
            }
            if (criteria.getAnalysisServiceId() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getAnalysisServiceId(), SamplePatient_.analysisServiceId));
            }
            if (criteria.getAnalysisServiceName() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getAnalysisServiceName(), SamplePatient_.analysisServiceName));
            }
            if (criteria.getDateCollected() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCollected(), SamplePatient_.dateCollected));
            }
            if (criteria.getDateRegistered() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateRegistered(), SamplePatient_.dateRegistered));
            }
            if (criteria.getDateTested() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTested(), SamplePatient_.dateTested));
            }
            if (criteria.getResult() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResult(), SamplePatient_.result));
            }
            if (criteria.getUnit() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUnit(), SamplePatient_.unit));
            }
            if (criteria.getDatePublished() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatePublished(), SamplePatient_.datePublished));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildStringSpecification(criteria.getState(), SamplePatient_.state));
            }
        }
        return specification;
    }
}
