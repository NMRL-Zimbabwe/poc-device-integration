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
import zw.org.nmrl.poc.device.domain.Patient;
import zw.org.nmrl.poc.device.repository.PatientRepository;
import zw.org.nmrl.poc.device.service.PatientQueryService;
import zw.org.nmrl.poc.device.service.PatientService;
import zw.org.nmrl.poc.device.service.criteria.PatientCriteria;
import zw.org.nmrl.poc.device.service.dto.UnifiedPatientSampleCaseAnalysisDTO;
import zw.org.nmrl.poc.device.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link zw.org.nmrl.poc.device.domain.Patient}.
 */
@RestController
@RequestMapping("/api")
public class UnifiedPatientSampleResource {

    private final Logger log = LoggerFactory.getLogger(UnifiedPatientSampleResource.class);

    private static final String ENTITY_NAME = "patient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientService patientService;

    private final PatientRepository patientRepository;

    private final PatientQueryService patientQueryService;

    public UnifiedPatientSampleResource(PatientService patientService, PatientRepository patientRepository, PatientQueryService patientQueryService) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
        this.patientQueryService = patientQueryService;
    }

    /**
     * {@code POST  /patients} : Create a new patient.
     *
     * @param patient the patient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patient, or with status {@code 400 (Bad Request)} if the patient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unified-patient-sample")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) throws URISyntaxException {
        log.debug("REST request to save Patient : {}", patient);
        if (patient.getPatientId() != null) {
            throw new BadRequestAlertException("A new patient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Patient result = patientService.save(patient);
        return ResponseEntity
            .created(new URI("/api/patients/" + result.getPatientId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getPatientId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /patients/:id} : Updates an existing patient.
     *
     * @param id the id of the patient to save.
     * @param patient the patient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patient,
     * or with status {@code 400 (Bad Request)} if the patient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unified-patient-sample/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id", required = false) final Long id, @RequestBody Patient patient)
        throws URISyntaxException {
        log.debug("REST request to update Patient : {}, {}", id, patient);
        if (patient.getPatientId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patient.getPatientId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

		/*
		 * if (!patientRepository.existsById(id)) { throw new
		 * BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"); }
		 */

        Patient result = patientService.update(patient);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, patient.getPatientId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /patients/:id} : Partial updates given fields of an existing patient, field will ignore if it is null
     *
     * @param id the id of the patient to save.
     * @param patient the patient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patient,
     * or with status {@code 400 (Bad Request)} if the patient is not valid,
     * or with status {@code 404 (Not Found)} if the patient is not found,
     * or with status {@code 500 (Internal Server Error)} if the patient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/unified-patient-sample/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Patient> partialUpdatePatient(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Patient patient
    ) throws URISyntaxException {
        log.debug("REST request to partial update Patient partially : {}, {}", id, patient);
        if (patient.getPatientId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patient.getPatientId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

		/*
		 * if (!patientRepository.existsById(id)) { throw new
		 * BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"); }
		 */

        Optional<Patient> result = patientService.partialUpdate(patient);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, patient.getPatientId().toString())
        );
    }

    /**
     * {@code GET  /patients} : get all the patients.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patients in body.
     */
    @GetMapping("/unified-patient-sample")
    public ResponseEntity<List<Patient>> getAllPatients(
        PatientCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Patients by criteria: {}", criteria);
        Page<Patient> page = patientQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /patients/count} : count all the patients.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/unified-patient-sample/count")
    public ResponseEntity<Long> countPatients(PatientCriteria criteria) {
        log.debug("REST request to count Patients by criteria: {}", criteria);
        return ResponseEntity.ok().body(patientQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /patients/:id} : get the "id" patient.
     *
     * @param id the id of the patient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unified-patient-sample/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        log.debug("REST request to get Patient : {}", id);
        Optional<Patient> patient = patientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patient);
    }

    /**
     * {@code DELETE  /patients/:id} : delete the "id" patient.
     *
     * @param id the id of the patient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unified-patient-sample/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        log.debug("REST request to delete Patient : {}", id);
        patientService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
    
    /**
     * {@code POST  /patients} : Create a new patient.
     *
     * @param patient the patient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patient, or with status {@code 400 (Bad Request)} if the patient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unified-patient-sample/subscription")
    public ResponseEntity<Patient> createPatientSample(@RequestBody UnifiedPatientSampleCaseAnalysisDTO batch) throws URISyntaxException {
        log.debug("REST request to save Patient Sample Analysis : {}", batch);
        if (batch.getPatient().getPatientId() != null) {
            throw new BadRequestAlertException("A new patient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Patient result = patientService.saveBatch(batch);
        return ResponseEntity
            .created(new URI("/api/patients/"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getPatientId().toString()))
            .body(result);
    }
}
