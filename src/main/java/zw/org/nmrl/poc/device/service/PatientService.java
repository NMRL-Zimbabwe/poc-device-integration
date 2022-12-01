package zw.org.nmrl.poc.device.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.org.nmrl.poc.device.domain.Patient;

/**
 * Service Interface for managing {@link Patient}.
 */
public interface PatientService {
    /**
     * Save a patient.
     *
     * @param patient the entity to save.
     * @return the persisted entity.
     */
    Patient save(Patient patient);

    /**
     * Updates a patient.
     *
     * @param patient the entity to update.
     * @return the persisted entity.
     */
    Patient update(Patient patient);

    /**
     * Partially updates a patient.
     *
     * @param patient the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Patient> partialUpdate(Patient patient);

    /**
     * Get all the patients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Patient> findAll(Pageable pageable);

    /**
     * Get the "id" patient.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Patient> findOne(Long id);

    /**
     * Delete the "id" patient.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}