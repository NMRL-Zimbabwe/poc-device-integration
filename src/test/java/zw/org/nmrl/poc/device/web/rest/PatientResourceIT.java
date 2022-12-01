package zw.org.nmrl.poc.device.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import zw.org.nmrl.poc.device.IntegrationTest;
import zw.org.nmrl.poc.device.domain.Patient;
import zw.org.nmrl.poc.device.repository.PatientRepository;
import zw.org.nmrl.poc.device.service.criteria.PatientCriteria;

/**
 * Integration tests for the {@link PatientResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PatientResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DOB = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_AGE = "AAAAAAAAAA";
    private static final String UPDATED_AGE = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_REFERRER = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_REFERRER = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_PATIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_PATIENT_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/patients";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientMockMvc;

    private Patient patient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createEntity(EntityManager em) {
        Patient patient = new Patient()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .dob(DEFAULT_DOB)
            .age(DEFAULT_AGE)
            .gender(DEFAULT_GENDER)
            .primaryReferrer(DEFAULT_PRIMARY_REFERRER)
            .clientPatientId(DEFAULT_CLIENT_PATIENT_ID);
        return patient;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createUpdatedEntity(EntityManager em) {
        Patient patient = new Patient()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .primaryReferrer(UPDATED_PRIMARY_REFERRER)
            .clientPatientId(UPDATED_CLIENT_PATIENT_ID);
        return patient;
    }

    @BeforeEach
    public void initTest() {
        patient = createEntity(em);
    }

    @Test
    @Transactional
    void createPatient() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();
        // Create the Patient
        restPatientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isCreated());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate + 1);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPatient.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPatient.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testPatient.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testPatient.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPatient.getPrimaryReferrer()).isEqualTo(DEFAULT_PRIMARY_REFERRER);
        assertThat(testPatient.getClientPatientId()).isEqualTo(DEFAULT_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void createPatientWithExistingId() throws Exception {
        // Create the Patient with an existing ID
        patient.setId(1L);

        int databaseSizeBeforeCreate = patientRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPatients() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList
        restPatientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patient.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].primaryReferrer").value(hasItem(DEFAULT_PRIMARY_REFERRER)))
            .andExpect(jsonPath("$.[*].clientPatientId").value(hasItem(DEFAULT_CLIENT_PATIENT_ID)));
    }

    @Test
    @Transactional
    void getPatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get the patient
        restPatientMockMvc
            .perform(get(ENTITY_API_URL_ID, patient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patient.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.primaryReferrer").value(DEFAULT_PRIMARY_REFERRER))
            .andExpect(jsonPath("$.clientPatientId").value(DEFAULT_CLIENT_PATIENT_ID));
    }

    @Test
    @Transactional
    void getPatientsByIdFiltering() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        Long id = patient.getId();

        defaultPatientShouldBeFound("id.equals=" + id);
        defaultPatientShouldNotBeFound("id.notEquals=" + id);

        defaultPatientShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPatientShouldNotBeFound("id.greaterThan=" + id);

        defaultPatientShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPatientShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllPatientsByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName equals to DEFAULT_FIRST_NAME
        defaultPatientShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the patientList where firstName equals to UPDATED_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllPatientsByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultPatientShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the patientList where firstName equals to UPDATED_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllPatientsByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName is not null
        defaultPatientShouldBeFound("firstName.specified=true");

        // Get all the patientList where firstName is null
        defaultPatientShouldNotBeFound("firstName.specified=false");
    }

    @Test
    @Transactional
    void getAllPatientsByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName contains DEFAULT_FIRST_NAME
        defaultPatientShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the patientList where firstName contains UPDATED_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllPatientsByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where firstName does not contain DEFAULT_FIRST_NAME
        defaultPatientShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the patientList where firstName does not contain UPDATED_FIRST_NAME
        defaultPatientShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllPatientsByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName equals to DEFAULT_LAST_NAME
        defaultPatientShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the patientList where lastName equals to UPDATED_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllPatientsByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultPatientShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the patientList where lastName equals to UPDATED_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllPatientsByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName is not null
        defaultPatientShouldBeFound("lastName.specified=true");

        // Get all the patientList where lastName is null
        defaultPatientShouldNotBeFound("lastName.specified=false");
    }

    @Test
    @Transactional
    void getAllPatientsByLastNameContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName contains DEFAULT_LAST_NAME
        defaultPatientShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the patientList where lastName contains UPDATED_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllPatientsByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where lastName does not contain DEFAULT_LAST_NAME
        defaultPatientShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the patientList where lastName does not contain UPDATED_LAST_NAME
        defaultPatientShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllPatientsByDobIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dob equals to DEFAULT_DOB
        defaultPatientShouldBeFound("dob.equals=" + DEFAULT_DOB);

        // Get all the patientList where dob equals to UPDATED_DOB
        defaultPatientShouldNotBeFound("dob.equals=" + UPDATED_DOB);
    }

    @Test
    @Transactional
    void getAllPatientsByDobIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dob in DEFAULT_DOB or UPDATED_DOB
        defaultPatientShouldBeFound("dob.in=" + DEFAULT_DOB + "," + UPDATED_DOB);

        // Get all the patientList where dob equals to UPDATED_DOB
        defaultPatientShouldNotBeFound("dob.in=" + UPDATED_DOB);
    }

    @Test
    @Transactional
    void getAllPatientsByDobIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dob is not null
        defaultPatientShouldBeFound("dob.specified=true");

        // Get all the patientList where dob is null
        defaultPatientShouldNotBeFound("dob.specified=false");
    }

    @Test
    @Transactional
    void getAllPatientsByDobIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dob is greater than or equal to DEFAULT_DOB
        defaultPatientShouldBeFound("dob.greaterThanOrEqual=" + DEFAULT_DOB);

        // Get all the patientList where dob is greater than or equal to UPDATED_DOB
        defaultPatientShouldNotBeFound("dob.greaterThanOrEqual=" + UPDATED_DOB);
    }

    @Test
    @Transactional
    void getAllPatientsByDobIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dob is less than or equal to DEFAULT_DOB
        defaultPatientShouldBeFound("dob.lessThanOrEqual=" + DEFAULT_DOB);

        // Get all the patientList where dob is less than or equal to SMALLER_DOB
        defaultPatientShouldNotBeFound("dob.lessThanOrEqual=" + SMALLER_DOB);
    }

    @Test
    @Transactional
    void getAllPatientsByDobIsLessThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dob is less than DEFAULT_DOB
        defaultPatientShouldNotBeFound("dob.lessThan=" + DEFAULT_DOB);

        // Get all the patientList where dob is less than UPDATED_DOB
        defaultPatientShouldBeFound("dob.lessThan=" + UPDATED_DOB);
    }

    @Test
    @Transactional
    void getAllPatientsByDobIsGreaterThanSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where dob is greater than DEFAULT_DOB
        defaultPatientShouldNotBeFound("dob.greaterThan=" + DEFAULT_DOB);

        // Get all the patientList where dob is greater than SMALLER_DOB
        defaultPatientShouldBeFound("dob.greaterThan=" + SMALLER_DOB);
    }

    @Test
    @Transactional
    void getAllPatientsByAgeIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age equals to DEFAULT_AGE
        defaultPatientShouldBeFound("age.equals=" + DEFAULT_AGE);

        // Get all the patientList where age equals to UPDATED_AGE
        defaultPatientShouldNotBeFound("age.equals=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllPatientsByAgeIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age in DEFAULT_AGE or UPDATED_AGE
        defaultPatientShouldBeFound("age.in=" + DEFAULT_AGE + "," + UPDATED_AGE);

        // Get all the patientList where age equals to UPDATED_AGE
        defaultPatientShouldNotBeFound("age.in=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllPatientsByAgeIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age is not null
        defaultPatientShouldBeFound("age.specified=true");

        // Get all the patientList where age is null
        defaultPatientShouldNotBeFound("age.specified=false");
    }

    @Test
    @Transactional
    void getAllPatientsByAgeContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age contains DEFAULT_AGE
        defaultPatientShouldBeFound("age.contains=" + DEFAULT_AGE);

        // Get all the patientList where age contains UPDATED_AGE
        defaultPatientShouldNotBeFound("age.contains=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllPatientsByAgeNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where age does not contain DEFAULT_AGE
        defaultPatientShouldNotBeFound("age.doesNotContain=" + DEFAULT_AGE);

        // Get all the patientList where age does not contain UPDATED_AGE
        defaultPatientShouldBeFound("age.doesNotContain=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllPatientsByGenderIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender equals to DEFAULT_GENDER
        defaultPatientShouldBeFound("gender.equals=" + DEFAULT_GENDER);

        // Get all the patientList where gender equals to UPDATED_GENDER
        defaultPatientShouldNotBeFound("gender.equals=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllPatientsByGenderIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender in DEFAULT_GENDER or UPDATED_GENDER
        defaultPatientShouldBeFound("gender.in=" + DEFAULT_GENDER + "," + UPDATED_GENDER);

        // Get all the patientList where gender equals to UPDATED_GENDER
        defaultPatientShouldNotBeFound("gender.in=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllPatientsByGenderIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender is not null
        defaultPatientShouldBeFound("gender.specified=true");

        // Get all the patientList where gender is null
        defaultPatientShouldNotBeFound("gender.specified=false");
    }

    @Test
    @Transactional
    void getAllPatientsByGenderContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender contains DEFAULT_GENDER
        defaultPatientShouldBeFound("gender.contains=" + DEFAULT_GENDER);

        // Get all the patientList where gender contains UPDATED_GENDER
        defaultPatientShouldNotBeFound("gender.contains=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllPatientsByGenderNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where gender does not contain DEFAULT_GENDER
        defaultPatientShouldNotBeFound("gender.doesNotContain=" + DEFAULT_GENDER);

        // Get all the patientList where gender does not contain UPDATED_GENDER
        defaultPatientShouldBeFound("gender.doesNotContain=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllPatientsByPrimaryReferrerIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where primaryReferrer equals to DEFAULT_PRIMARY_REFERRER
        defaultPatientShouldBeFound("primaryReferrer.equals=" + DEFAULT_PRIMARY_REFERRER);

        // Get all the patientList where primaryReferrer equals to UPDATED_PRIMARY_REFERRER
        defaultPatientShouldNotBeFound("primaryReferrer.equals=" + UPDATED_PRIMARY_REFERRER);
    }

    @Test
    @Transactional
    void getAllPatientsByPrimaryReferrerIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where primaryReferrer in DEFAULT_PRIMARY_REFERRER or UPDATED_PRIMARY_REFERRER
        defaultPatientShouldBeFound("primaryReferrer.in=" + DEFAULT_PRIMARY_REFERRER + "," + UPDATED_PRIMARY_REFERRER);

        // Get all the patientList where primaryReferrer equals to UPDATED_PRIMARY_REFERRER
        defaultPatientShouldNotBeFound("primaryReferrer.in=" + UPDATED_PRIMARY_REFERRER);
    }

    @Test
    @Transactional
    void getAllPatientsByPrimaryReferrerIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where primaryReferrer is not null
        defaultPatientShouldBeFound("primaryReferrer.specified=true");

        // Get all the patientList where primaryReferrer is null
        defaultPatientShouldNotBeFound("primaryReferrer.specified=false");
    }

    @Test
    @Transactional
    void getAllPatientsByPrimaryReferrerContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where primaryReferrer contains DEFAULT_PRIMARY_REFERRER
        defaultPatientShouldBeFound("primaryReferrer.contains=" + DEFAULT_PRIMARY_REFERRER);

        // Get all the patientList where primaryReferrer contains UPDATED_PRIMARY_REFERRER
        defaultPatientShouldNotBeFound("primaryReferrer.contains=" + UPDATED_PRIMARY_REFERRER);
    }

    @Test
    @Transactional
    void getAllPatientsByPrimaryReferrerNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where primaryReferrer does not contain DEFAULT_PRIMARY_REFERRER
        defaultPatientShouldNotBeFound("primaryReferrer.doesNotContain=" + DEFAULT_PRIMARY_REFERRER);

        // Get all the patientList where primaryReferrer does not contain UPDATED_PRIMARY_REFERRER
        defaultPatientShouldBeFound("primaryReferrer.doesNotContain=" + UPDATED_PRIMARY_REFERRER);
    }

    @Test
    @Transactional
    void getAllPatientsByClientPatientIdIsEqualToSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where clientPatientId equals to DEFAULT_CLIENT_PATIENT_ID
        defaultPatientShouldBeFound("clientPatientId.equals=" + DEFAULT_CLIENT_PATIENT_ID);

        // Get all the patientList where clientPatientId equals to UPDATED_CLIENT_PATIENT_ID
        defaultPatientShouldNotBeFound("clientPatientId.equals=" + UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllPatientsByClientPatientIdIsInShouldWork() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where clientPatientId in DEFAULT_CLIENT_PATIENT_ID or UPDATED_CLIENT_PATIENT_ID
        defaultPatientShouldBeFound("clientPatientId.in=" + DEFAULT_CLIENT_PATIENT_ID + "," + UPDATED_CLIENT_PATIENT_ID);

        // Get all the patientList where clientPatientId equals to UPDATED_CLIENT_PATIENT_ID
        defaultPatientShouldNotBeFound("clientPatientId.in=" + UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllPatientsByClientPatientIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where clientPatientId is not null
        defaultPatientShouldBeFound("clientPatientId.specified=true");

        // Get all the patientList where clientPatientId is null
        defaultPatientShouldNotBeFound("clientPatientId.specified=false");
    }

    @Test
    @Transactional
    void getAllPatientsByClientPatientIdContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where clientPatientId contains DEFAULT_CLIENT_PATIENT_ID
        defaultPatientShouldBeFound("clientPatientId.contains=" + DEFAULT_CLIENT_PATIENT_ID);

        // Get all the patientList where clientPatientId contains UPDATED_CLIENT_PATIENT_ID
        defaultPatientShouldNotBeFound("clientPatientId.contains=" + UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllPatientsByClientPatientIdNotContainsSomething() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList where clientPatientId does not contain DEFAULT_CLIENT_PATIENT_ID
        defaultPatientShouldNotBeFound("clientPatientId.doesNotContain=" + DEFAULT_CLIENT_PATIENT_ID);

        // Get all the patientList where clientPatientId does not contain UPDATED_CLIENT_PATIENT_ID
        defaultPatientShouldBeFound("clientPatientId.doesNotContain=" + UPDATED_CLIENT_PATIENT_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPatientShouldBeFound(String filter) throws Exception {
        restPatientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patient.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].primaryReferrer").value(hasItem(DEFAULT_PRIMARY_REFERRER)))
            .andExpect(jsonPath("$.[*].clientPatientId").value(hasItem(DEFAULT_CLIENT_PATIENT_ID)));

        // Check, that the count call also returns 1
        restPatientMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPatientShouldNotBeFound(String filter) throws Exception {
        restPatientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPatientMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingPatient() throws Exception {
        // Get the patient
        restPatientMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Update the patient
        Patient updatedPatient = patientRepository.findById(patient.getId()).get();
        // Disconnect from session so that the updates on updatedPatient are not directly saved in db
        em.detach(updatedPatient);
        updatedPatient
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .primaryReferrer(UPDATED_PRIMARY_REFERRER)
            .clientPatientId(UPDATED_CLIENT_PATIENT_ID);

        restPatientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPatient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPatient))
            )
            .andExpect(status().isOk());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPatient.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPatient.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testPatient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPatient.getPrimaryReferrer()).isEqualTo(UPDATED_PRIMARY_REFERRER);
        assertThat(testPatient.getClientPatientId()).isEqualTo(UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void putNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();
        patient.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, patient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(patient))
            )
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();
        patient.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPatientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(patient))
            )
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();
        patient.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPatientMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePatientWithPatch() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Update the patient using partial update
        Patient partialUpdatedPatient = new Patient();
        partialUpdatedPatient.setId(patient.getId());

        partialUpdatedPatient
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .primaryReferrer(UPDATED_PRIMARY_REFERRER);

        restPatientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPatient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPatient))
            )
            .andExpect(status().isOk());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPatient.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPatient.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testPatient.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPatient.getPrimaryReferrer()).isEqualTo(UPDATED_PRIMARY_REFERRER);
        assertThat(testPatient.getClientPatientId()).isEqualTo(DEFAULT_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void fullUpdatePatientWithPatch() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Update the patient using partial update
        Patient partialUpdatedPatient = new Patient();
        partialUpdatedPatient.setId(patient.getId());

        partialUpdatedPatient
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .primaryReferrer(UPDATED_PRIMARY_REFERRER)
            .clientPatientId(UPDATED_CLIENT_PATIENT_ID);

        restPatientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPatient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPatient))
            )
            .andExpect(status().isOk());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPatient.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPatient.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testPatient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPatient.getPrimaryReferrer()).isEqualTo(UPDATED_PRIMARY_REFERRER);
        assertThat(testPatient.getClientPatientId()).isEqualTo(UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void patchNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();
        patient.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, patient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(patient))
            )
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();
        patient.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPatientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(patient))
            )
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();
        patient.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPatientMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeDelete = patientRepository.findAll().size();

        // Delete the patient
        restPatientMockMvc
            .perform(delete(ENTITY_API_URL_ID, patient.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
