package zw.org.nmrl.poc.device.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import zw.org.nmrl.poc.device.domain.AnalysisService;
import zw.org.nmrl.poc.device.repository.AnalysisServiceRepository;
import zw.org.nmrl.poc.device.service.AnalysisServiceQueryService;
import zw.org.nmrl.poc.device.service.AnalysisServiceService;
import zw.org.nmrl.poc.device.service.criteria.AnalysisServiceCriteria;
import zw.org.nmrl.poc.device.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link zw.org.nmrl.poc.device.domain.AnalysisService}.
 */
@RestController
@RequestMapping("/api")
public class AnalysisServiceResource {

    private final Logger log = LoggerFactory.getLogger(AnalysisServiceResource.class);

    private static final String ENTITY_NAME = "analysisService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnalysisServiceService analysisServiceService;

    private final AnalysisServiceRepository analysisServiceRepository;

    private final AnalysisServiceQueryService analysisServiceQueryService;

    public AnalysisServiceResource(
        AnalysisServiceService analysisServiceService,
        AnalysisServiceRepository analysisServiceRepository,
        AnalysisServiceQueryService analysisServiceQueryService
    ) {
        this.analysisServiceService = analysisServiceService;
        this.analysisServiceRepository = analysisServiceRepository;
        this.analysisServiceQueryService = analysisServiceQueryService;
    }

    /**
     * {@code POST  /analysis-services} : Create a new analysisService.
     *
     * @param analysisService the analysisService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new analysisService, or with status {@code 400 (Bad Request)} if the analysisService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/analysis-services")
    public ResponseEntity<AnalysisService> createAnalysisService(@RequestBody AnalysisService analysisService) throws URISyntaxException {
        log.debug("REST request to save AnalysisService : {}", analysisService);
        if (analysisService.getId() != null) {
            throw new BadRequestAlertException("A new analysisService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnalysisService result = analysisServiceService.save(analysisService);
        return ResponseEntity
            .created(new URI("/api/analysis-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /analysis-services/:id} : Updates an existing analysisService.
     *
     * @param id the id of the analysisService to save.
     * @param analysisService the analysisService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated analysisService,
     * or with status {@code 400 (Bad Request)} if the analysisService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the analysisService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/analysis-services/{id}")
    public ResponseEntity<AnalysisService> updateAnalysisService(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnalysisService analysisService
    ) throws URISyntaxException {
        log.debug("REST request to update AnalysisService : {}, {}", id, analysisService);
        if (analysisService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, analysisService.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!analysisServiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnalysisService result = analysisServiceService.update(analysisService);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, analysisService.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /analysis-services/:id} : Partial updates given fields of an existing analysisService, field will ignore if it is null
     *
     * @param id the id of the analysisService to save.
     * @param analysisService the analysisService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated analysisService,
     * or with status {@code 400 (Bad Request)} if the analysisService is not valid,
     * or with status {@code 404 (Not Found)} if the analysisService is not found,
     * or with status {@code 500 (Internal Server Error)} if the analysisService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/analysis-services/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnalysisService> partialUpdateAnalysisService(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnalysisService analysisService
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnalysisService partially : {}, {}", id, analysisService);
        if (analysisService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, analysisService.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!analysisServiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnalysisService> result = analysisServiceService.partialUpdate(analysisService);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, analysisService.getId().toString())
        );
    }

    /**
     * {@code GET  /analysis-services} : get all the analysisServices.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of analysisServices in body.
     */
    @GetMapping("/analysis-services")
    public ResponseEntity<List<AnalysisService>> getAllAnalysisServices(
        AnalysisServiceCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get AnalysisServices by criteria: {}", criteria);
        Page<AnalysisService> page = analysisServiceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /analysis-services/count} : count all the analysisServices.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/analysis-services/count")
    public ResponseEntity<Long> countAnalysisServices(AnalysisServiceCriteria criteria) {
        log.debug("REST request to count AnalysisServices by criteria: {}", criteria);
        return ResponseEntity.ok().body(analysisServiceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /analysis-services/:id} : get the "id" analysisService.
     *
     * @param id the id of the analysisService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the analysisService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/analysis-services/{id}")
    public ResponseEntity<AnalysisService> getAnalysisService(@PathVariable Long id) {
        log.debug("REST request to get AnalysisService : {}", id);
        Optional<AnalysisService> analysisService = analysisServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(analysisService);
    }

    /**
     * {@code DELETE  /analysis-services/:id} : delete the "id" analysisService.
     *
     * @param id the id of the analysisService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/analysis-services/{id}")
    public ResponseEntity<Void> deleteAnalysisService(@PathVariable Long id) {
        log.debug("REST request to delete AnalysisService : {}", id);
        analysisServiceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
