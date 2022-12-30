package zw.org.nmrl.poc.device.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.org.nmrl.poc.device.domain.AnalysisRequest;

/**
 * Service Interface for managing {@link AnalysisRequest}.
 */
public interface SampleService {
    /**
     * Save a sample.
     *
     * @param sample the entity to save.
     * @return the persisted entity.
     */
    AnalysisRequest save(AnalysisRequest sample);

    /**
     * Updates a sample.
     *
     * @param sample the entity to update.
     * @return the persisted entity.
     */
    AnalysisRequest update(AnalysisRequest sample);

    /**
     * Partially updates a sample.
     *
     * @param sample the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnalysisRequest> partialUpdate(AnalysisRequest sample);

    /**
     * Get all the samples.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnalysisRequest> findAll(Pageable pageable);

    /**
     * Get the "id" sample.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnalysisRequest> findOne(Long id);

    /**
     * Delete the "id" sample.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
