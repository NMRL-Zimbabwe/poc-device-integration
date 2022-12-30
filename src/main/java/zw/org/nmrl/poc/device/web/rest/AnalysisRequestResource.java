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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import zw.org.nmrl.poc.device.domain.AnalysisRequest;
import zw.org.nmrl.poc.device.repository.AnalysisRequestRepository;
import zw.org.nmrl.poc.device.service.AnalysisRequestQueryService;
import zw.org.nmrl.poc.device.service.SampleService;
import zw.org.nmrl.poc.device.service.criteria.AnalysisRequestCriteria;
import zw.org.nmrl.poc.device.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link zw.org.nmrl.poc.device.domain.AnalysisRequest}.
 */
@RestController
@RequestMapping("/api")
public class AnalysisRequestResource {

    private final Logger log = LoggerFactory.getLogger(AnalysisRequestResource.class);

    private static final String ENTITY_NAME = "sample";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SampleService sampleService;

    private final AnalysisRequestRepository sampleRepository;

    private final AnalysisRequestQueryService sampleQueryService;

    public AnalysisRequestResource(SampleService sampleService, AnalysisRequestRepository sampleRepository, AnalysisRequestQueryService sampleQueryService) {
        this.sampleService = sampleService;
        this.sampleRepository = sampleRepository;
        this.sampleQueryService = sampleQueryService;
    }

    /**
     * {@code POST  /samples} : Create a new sample.
     *
     * @param sample the sample to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sample, or with status {@code 400 (Bad Request)} if the sample has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/samples")
    public ResponseEntity<AnalysisRequest> createSample(@RequestBody AnalysisRequest sample) throws URISyntaxException {
        log.debug("REST request to save Sample : {}", sample);
        if (sample.getAnalysisRequestId() != null) {
            throw new BadRequestAlertException("A new sample cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnalysisRequest result = sampleService.save(sample);
        return ResponseEntity
            .created(new URI("/api/samples/" + result.getAnalysisRequestId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getAnalysisRequestId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /samples/:id} : Updates an existing sample.
     *
     * @param id the id of the sample to save.
     * @param sample the sample to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sample,
     * or with status {@code 400 (Bad Request)} if the sample is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sample couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/samples/{id}")
    public ResponseEntity<AnalysisRequest> updateSample(@PathVariable(value = "id", required = false) final Long id, @RequestBody AnalysisRequest sample)
        throws URISyntaxException {
        log.debug("REST request to update Sample : {}, {}", id, sample);
        if (sample.getAnalysisRequestId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sample.getAnalysisRequestId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
		/*
		 * if (!sampleRepository.existsById(id)) { throw new
		 * BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"); }
		 */

        AnalysisRequest result = sampleService.update(sample);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sample.getAnalysisRequestId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /samples/:id} : Partial updates given fields of an existing sample, field will ignore if it is null
     *
     * @param id the id of the sample to save.
     * @param sample the sample to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sample,
     * or with status {@code 400 (Bad Request)} if the sample is not valid,
     * or with status {@code 404 (Not Found)} if the sample is not found,
     * or with status {@code 500 (Internal Server Error)} if the sample couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/samples/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnalysisRequest> partialUpdateSample(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnalysisRequest sample
    ) throws URISyntaxException {
        log.debug("REST request to partial update Sample partially : {}, {}", id, sample);
        if (sample.getAnalysisRequestId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sample.getAnalysisRequestId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

		/*
		 * if (!sampleRepository.existsById(id)) { throw new
		 * BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"); }
		 */

        Optional<AnalysisRequest> result = sampleService.partialUpdate(sample);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sample.getAnalysisRequestId().toString())
        );
    }

    /**
     * {@code GET  /samples} : get all the samples.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of samples in body.
     */
    @GetMapping("/samples")
    public ResponseEntity<List<AnalysisRequest>> getAllSamples(
        AnalysisRequestCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Samples by criteria: {}", criteria);
        Page<AnalysisRequest> page = sampleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /samples/count} : count all the samples.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/samples/count")
    public ResponseEntity<Long> countSamples(AnalysisRequestCriteria criteria) {
        log.debug("REST request to count Samples by criteria: {}", criteria);
        return ResponseEntity.ok().body(sampleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /samples/:id} : get the "id" sample.
     *
     * @param id the id of the sample to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sample, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/samples/{id}")
    public ResponseEntity<AnalysisRequest> getSample(@PathVariable Long id) {
        log.debug("REST request to get Sample : {}", id);
        Optional<AnalysisRequest> sample = sampleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sample);
    }

    /**
     * {@code DELETE  /samples/:id} : delete the "id" sample.
     *
     * @param id the id of the sample to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/samples/{id}")
    public ResponseEntity<Void> deleteSample(@PathVariable Long id) {
        log.debug("REST request to delete Sample : {}", id);
        sampleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
