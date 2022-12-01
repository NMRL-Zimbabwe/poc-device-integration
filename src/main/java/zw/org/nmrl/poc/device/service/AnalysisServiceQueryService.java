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
import zw.org.nmrl.poc.device.domain.AnalysisService;
import zw.org.nmrl.poc.device.repository.AnalysisServiceRepository;
import zw.org.nmrl.poc.device.service.criteria.AnalysisServiceCriteria;

/**
 * Service for executing complex queries for {@link AnalysisService} entities in the database.
 * The main input is a {@link AnalysisServiceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnalysisService} or a {@link Page} of {@link AnalysisService} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnalysisServiceQueryService extends QueryService<AnalysisService> {

    private final Logger log = LoggerFactory.getLogger(AnalysisServiceQueryService.class);

    private final AnalysisServiceRepository analysisServiceRepository;

    public AnalysisServiceQueryService(AnalysisServiceRepository analysisServiceRepository) {
        this.analysisServiceRepository = analysisServiceRepository;
    }

    /**
     * Return a {@link List} of {@link AnalysisService} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AnalysisService> findByCriteria(AnalysisServiceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AnalysisService> specification = createSpecification(criteria);
        return analysisServiceRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link AnalysisService} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AnalysisService> findByCriteria(AnalysisServiceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AnalysisService> specification = createSpecification(criteria);
        return analysisServiceRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AnalysisServiceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AnalysisService> specification = createSpecification(criteria);
        return analysisServiceRepository.count(specification);
    }

    /**
     * Function to convert {@link AnalysisServiceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AnalysisService> createSpecification(AnalysisServiceCriteria criteria) {
        Specification<AnalysisService> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AnalysisService_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), AnalysisService_.name));
            }
            if (criteria.getUnit() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUnit(), AnalysisService_.unit));
            }
        }
        return specification;
    }
}
