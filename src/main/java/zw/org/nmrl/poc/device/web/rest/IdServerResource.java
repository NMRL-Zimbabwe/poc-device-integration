package zw.org.nmrl.poc.device.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import zw.org.nmrl.poc.device.domain.IdServer;
import zw.org.nmrl.poc.device.repository.CustomIdServerRepository;
import zw.org.nmrl.poc.device.service.IdServerService;
import zw.org.nmrl.poc.device.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link zw.org.downtime.domain.IdServer}.
 */
@RestController
@RequestMapping("/api")
public class IdServerResource {
    private final Logger log = LoggerFactory.getLogger(IdServerResource.class);

    private static final String ENTITY_NAME = "idServer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IdServerService idServerService;

    private final CustomIdServerRepository customIdServerRepository;

    public IdServerResource(IdServerService idServerService, CustomIdServerRepository customIdServerRepository) {
        this.idServerService = idServerService;
        this.customIdServerRepository = customIdServerRepository;
    }

    /**
     * {@code POST  /id-servers} : Create a new idServer.
     *
     * @param idServer the idServer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new idServer, or with status {@code 400 (Bad Request)} if the idServer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/id-servers")
    public ResponseEntity<IdServer> createIdServer(@RequestBody IdServer idServer) throws URISyntaxException {
        log.debug("REST request to save IdServer : {}", idServer);
        if (idServer.getId() != null) {
            throw new BadRequestAlertException("A new idServer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdServer result = idServerService.save(idServer);
        return ResponseEntity
            .created(new URI("/api/id-servers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /id-servers} : Updates an existing idServer.
     *
     * @param idServer the idServer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated idServer,
     * or with status {@code 400 (Bad Request)} if the idServer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the idServer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/id-servers")
    public ResponseEntity<IdServer> updateIdServer(@RequestBody IdServer idServer) throws URISyntaxException {
        log.debug("REST request to update IdServer : {}", idServer);
        if (idServer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IdServer result = idServerService.save(idServer);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, idServer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /id-servers} : get all the idServers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of idServers in body.
     */
    @GetMapping("/id-servers")
    public ResponseEntity<List<IdServer>> getAllIdServers(Pageable pageable) {
        log.debug("REST request to get a page of IdServers");
        Page<IdServer> page = idServerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /id-servers/:id} : get the "id" idServer.
     *
     * @param id the id of the idServer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the idServer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/id-servers/{id}")
    public ResponseEntity<IdServer> getIdServer(@PathVariable Long id) {
        log.debug("REST request to get IdServer : {}", id);
        Optional<IdServer> idServer = idServerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(idServer);
    }

    /**
     * {@code DELETE  /id-servers/:id} : delete the "id" idServer.
     *
     * @param id the id of the idServer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/id-servers/{id}")
    public ResponseEntity<Void> deleteIdServer(@PathVariable Long id) {
        log.debug("REST request to delete IdServer : {}", id);
        idServerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /id-servers/generate/:prefix} : generate new id for prefix.
     *
     * @param prefix the prefix of the id sequence.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of idServers in body.
     */
    @GetMapping("/id-servers/generate/{prefix}")
    public ResponseEntity<List> getIdServersNextSequence(@PathVariable String prefix) {
        log.debug("REST request to get next IdServers id sequence");
        List nextSequence = customIdServerRepository.findNextIdSequence(prefix);
        return ResponseEntity.ok().body(nextSequence);
    }
}
