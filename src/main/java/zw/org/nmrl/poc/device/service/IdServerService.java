package zw.org.nmrl.poc.device.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import zw.org.nmrl.poc.device.domain.IdServer;

/**
 * Service Interface for managing {@link IdServer}.
 */
public interface IdServerService {
    /**
     * Save a idServer.
     *
     * @param idServer the entity to save.
     * @return the persisted entity.
     */
    IdServer save(IdServer idServer);

    /**
     * Get all the idServers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IdServer> findAll(Pageable pageable);

    /**
     * Get the "id" idServer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IdServer> findOne(Long id);

    /**
     * Delete the "id" idServer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
