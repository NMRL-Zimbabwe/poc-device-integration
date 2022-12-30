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
import zw.org.nmrl.poc.device.domain.SampleType;
import zw.org.nmrl.poc.device.repository.SampleTypeRepository;
import zw.org.nmrl.poc.device.service.criteria.SampleTypeCriteria;

/**
 * Service for executing complex queries for {@link SampleType} entities in the database.
 * The main input is a {@link SampleTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SampleType} or a {@link Page} of {@link SampleType} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SampleTypeQueryService extends QueryService<SampleType> {

    private final Logger log = LoggerFactory.getLogger(SampleTypeQueryService.class);

    private final SampleTypeRepository sampleTypeRepository;

    public SampleTypeQueryService(SampleTypeRepository sampleTypeRepository) {
        this.sampleTypeRepository = sampleTypeRepository;
    }

    /**
     * Return a {@link List} of {@link SampleType} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SampleType> findByCriteria(SampleTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SampleType> specification = createSpecification(criteria);
        return sampleTypeRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link SampleType} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SampleType> findByCriteria(SampleTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SampleType> specification = createSpecification(criteria);
        return sampleTypeRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SampleTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SampleType> specification = createSpecification(criteria);
        return sampleTypeRepository.count(specification);
    }

    /**
     * Function to convert {@link SampleTypeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SampleType> createSpecification(SampleTypeCriteria criteria) {
        Specification<SampleType> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
			/*
			 * if (criteria.getId() != null) { specification =
			 * specification.and(buildRangeSpecification(criteria.getId(), SampleType_.id));
			 * } if (criteria.getName() != null) { specification =
			 * specification.and(buildStringSpecification(criteria.getName(),
			 * SampleType_.name)); }
			 */
        }
        return specification;
    }
}
