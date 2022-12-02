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
import zw.org.nmrl.poc.device.domain.SamplePatient;
import zw.org.nmrl.poc.device.repository.SamplePatientRepository;
import zw.org.nmrl.poc.device.service.SamplePatientQueryService;
import zw.org.nmrl.poc.device.service.SamplePatientService;
import zw.org.nmrl.poc.device.service.criteria.SamplePatientCriteria;
import zw.org.nmrl.poc.device.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link zw.org.nmrl.poc.device.domain.SamplePatient}.
 */
@RestController
@RequestMapping("/api")
public class SamplePatientResource {

    private final Logger log = LoggerFactory.getLogger(SamplePatientResource.class);

    private static final String ENTITY_NAME = "samplePatient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SamplePatientService samplePatientService;

    private final SamplePatientRepository samplePatientRepository;

    private final SamplePatientQueryService samplePatientQueryService;

    public SamplePatientResource(
        SamplePatientService samplePatientService,
        SamplePatientRepository samplePatientRepository,
        SamplePatientQueryService samplePatientQueryService
    ) {
        this.samplePatientService = samplePatientService;
        this.samplePatientRepository = samplePatientRepository;
        this.samplePatientQueryService = samplePatientQueryService;
    }

    /**
     * {@code POST  /sample-patients} : Create a new samplePatient.
     *
     * @param samplePatient the samplePatient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new samplePatient, or with status {@code 400 (Bad Request)} if the samplePatient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sample-patients")
    public ResponseEntity<SamplePatient> createSamplePatient(@RequestBody SamplePatient samplePatient) throws URISyntaxException {
        log.debug("REST request to save SamplePatient : {}", samplePatient);
        if (samplePatient.getId() != null) {
            throw new BadRequestAlertException("A new samplePatient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SamplePatient result = samplePatientService.save(samplePatient);
        return ResponseEntity
            .created(new URI("/api/sample-patients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sample-patients/:id} : Updates an existing samplePatient.
     *
     * @param id the id of the samplePatient to save.
     * @param samplePatient the samplePatient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated samplePatient,
     * or with status {@code 400 (Bad Request)} if the samplePatient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the samplePatient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sample-patients/{id}")
    public ResponseEntity<SamplePatient> updateSamplePatient(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SamplePatient samplePatient
    ) throws URISyntaxException {
        log.debug("REST request to update SamplePatient : {}, {}", id, samplePatient);
        if (samplePatient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, samplePatient.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!samplePatientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SamplePatient result = samplePatientService.update(samplePatient);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, samplePatient.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sample-patients/:id} : Partial updates given fields of an existing samplePatient, field will ignore if it is null
     *
     * @param id the id of the samplePatient to save.
     * @param samplePatient the samplePatient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated samplePatient,
     * or with status {@code 400 (Bad Request)} if the samplePatient is not valid,
     * or with status {@code 404 (Not Found)} if the samplePatient is not found,
     * or with status {@code 500 (Internal Server Error)} if the samplePatient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sample-patients/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SamplePatient> partialUpdateSamplePatient(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody SamplePatient samplePatient
    ) throws URISyntaxException {
        log.debug("REST request to partial update SamplePatient partially : {}, {}", id, samplePatient);
        if (samplePatient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, samplePatient.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!samplePatientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SamplePatient> result = samplePatientService.partialUpdate(samplePatient);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, samplePatient.getId().toString())
        );
    }

    /**
     * {@code GET  /sample-patients} : get all the samplePatients.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of samplePatients in body.
     */
    @GetMapping("/sample-patients")
    public ResponseEntity<List<SamplePatient>> getAllSamplePatients(
        SamplePatientCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get SamplePatients by criteria: {}", criteria);
        Page<SamplePatient> page = samplePatientQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sample-patients/count} : count all the samplePatients.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/sample-patients/count")
    public ResponseEntity<Long> countSamplePatients(SamplePatientCriteria criteria) {
        log.debug("REST request to count SamplePatients by criteria: {}", criteria);
        return ResponseEntity.ok().body(samplePatientQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sample-patients/:id} : get the "id" samplePatient.
     *
     * @param id the id of the samplePatient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the samplePatient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sample-patients/{id}")
    public ResponseEntity<SamplePatient> getSamplePatient(@PathVariable String id) {
        log.debug("REST request to get SamplePatient : {}", id);
        Optional<SamplePatient> samplePatient = samplePatientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(samplePatient);
    }

    /**
     * {@code DELETE  /sample-patients/:id} : delete the "id" samplePatient.
     *
     * @param id the id of the samplePatient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sample-patients/{id}")
    public ResponseEntity<Void> deleteSamplePatient(@PathVariable String id) {
        log.debug("REST request to delete SamplePatient : {}", id);
        samplePatientService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
