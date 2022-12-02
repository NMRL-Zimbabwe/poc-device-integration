package zw.org.nmrl.poc.device.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.org.nmrl.poc.device.domain.SamplePatient;

/**
 * Service Interface for managing {@link SamplePatient}.
 */
public interface SamplePatientService {
    /**
     * Save a samplePatient.
     *
     * @param samplePatient the entity to save.
     * @return the persisted entity.
     */
    SamplePatient save(SamplePatient samplePatient);

    /**
     * Updates a samplePatient.
     *
     * @param samplePatient the entity to update.
     * @return the persisted entity.
     */
    SamplePatient update(SamplePatient samplePatient);

    /**
     * Partially updates a samplePatient.
     *
     * @param samplePatient the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SamplePatient> partialUpdate(SamplePatient samplePatient);

    /**
     * Get all the samplePatients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SamplePatient> findAll(Pageable pageable);

    /**
     * Get the "id" samplePatient.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SamplePatient> findOne(String id);

    /**
     * Delete the "id" samplePatient.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
