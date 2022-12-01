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
import zw.org.nmrl.poc.device.domain.SamplePatient;
import zw.org.nmrl.poc.device.repository.SamplePatientRepository;
import zw.org.nmrl.poc.device.service.criteria.SamplePatientCriteria;

/**
 * Integration tests for the {@link SamplePatientResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SamplePatientResourceIT {

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

    private static final String DEFAULT_CLIENT_SAMPLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_SAMPLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_SAMPLE_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SAMPLE_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SAMPLE_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SAMPLE_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ANALYSIS_SERVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ANALYSIS_SERVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ANALYSIS_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ANALYSIS_SERVICE_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_COLLECTED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_COLLECTED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_COLLECTED = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATE_REGISTERED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REGISTERED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_REGISTERED = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATE_TESTED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TESTED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_TESTED = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_PUBLISHED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PUBLISHED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_PUBLISHED = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sample-patients";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SamplePatientRepository samplePatientRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSamplePatientMockMvc;

    private SamplePatient samplePatient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SamplePatient createEntity(EntityManager em) {
        SamplePatient samplePatient = new SamplePatient()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .dob(DEFAULT_DOB)
            .age(DEFAULT_AGE)
            .gender(DEFAULT_GENDER)
            .primaryReferrer(DEFAULT_PRIMARY_REFERRER)
            .clientPatientId(DEFAULT_CLIENT_PATIENT_ID)
            .clientSampleId(DEFAULT_CLIENT_SAMPLE_ID)
            .clientContact(DEFAULT_CLIENT_CONTACT)
            .sampleTypeId(DEFAULT_SAMPLE_TYPE_ID)
            .sampleTypeName(DEFAULT_SAMPLE_TYPE_NAME)
            .analysisServiceId(DEFAULT_ANALYSIS_SERVICE_ID)
            .analysisServiceName(DEFAULT_ANALYSIS_SERVICE_NAME)
            .dateCollected(DEFAULT_DATE_COLLECTED)
            .dateRegistered(DEFAULT_DATE_REGISTERED)
            .dateTested(DEFAULT_DATE_TESTED)
            .result(DEFAULT_RESULT)
            .unit(DEFAULT_UNIT)
            .datePublished(DEFAULT_DATE_PUBLISHED)
            .state(DEFAULT_STATE);
        return samplePatient;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SamplePatient createUpdatedEntity(EntityManager em) {
        SamplePatient samplePatient = new SamplePatient()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .primaryReferrer(UPDATED_PRIMARY_REFERRER)
            .clientPatientId(UPDATED_CLIENT_PATIENT_ID)
            .clientSampleId(UPDATED_CLIENT_SAMPLE_ID)
            .clientContact(UPDATED_CLIENT_CONTACT)
            .sampleTypeId(UPDATED_SAMPLE_TYPE_ID)
            .sampleTypeName(UPDATED_SAMPLE_TYPE_NAME)
            .analysisServiceId(UPDATED_ANALYSIS_SERVICE_ID)
            .analysisServiceName(UPDATED_ANALYSIS_SERVICE_NAME)
            .dateCollected(UPDATED_DATE_COLLECTED)
            .dateRegistered(UPDATED_DATE_REGISTERED)
            .dateTested(UPDATED_DATE_TESTED)
            .result(UPDATED_RESULT)
            .unit(UPDATED_UNIT)
            .datePublished(UPDATED_DATE_PUBLISHED)
            .state(UPDATED_STATE);
        return samplePatient;
    }

    @BeforeEach
    public void initTest() {
        samplePatient = createEntity(em);
    }

    @Test
    @Transactional
    void createSamplePatient() throws Exception {
        int databaseSizeBeforeCreate = samplePatientRepository.findAll().size();
        // Create the SamplePatient
        restSamplePatientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(samplePatient)))
            .andExpect(status().isCreated());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeCreate + 1);
        SamplePatient testSamplePatient = samplePatientList.get(samplePatientList.size() - 1);
        assertThat(testSamplePatient.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testSamplePatient.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSamplePatient.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testSamplePatient.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testSamplePatient.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testSamplePatient.getPrimaryReferrer()).isEqualTo(DEFAULT_PRIMARY_REFERRER);
        assertThat(testSamplePatient.getClientPatientId()).isEqualTo(DEFAULT_CLIENT_PATIENT_ID);
        assertThat(testSamplePatient.getClientSampleId()).isEqualTo(DEFAULT_CLIENT_SAMPLE_ID);
        assertThat(testSamplePatient.getClientContact()).isEqualTo(DEFAULT_CLIENT_CONTACT);
        assertThat(testSamplePatient.getSampleTypeId()).isEqualTo(DEFAULT_SAMPLE_TYPE_ID);
        assertThat(testSamplePatient.getSampleTypeName()).isEqualTo(DEFAULT_SAMPLE_TYPE_NAME);
        assertThat(testSamplePatient.getAnalysisServiceId()).isEqualTo(DEFAULT_ANALYSIS_SERVICE_ID);
        assertThat(testSamplePatient.getAnalysisServiceName()).isEqualTo(DEFAULT_ANALYSIS_SERVICE_NAME);
        assertThat(testSamplePatient.getDateCollected()).isEqualTo(DEFAULT_DATE_COLLECTED);
        assertThat(testSamplePatient.getDateRegistered()).isEqualTo(DEFAULT_DATE_REGISTERED);
        assertThat(testSamplePatient.getDateTested()).isEqualTo(DEFAULT_DATE_TESTED);
        assertThat(testSamplePatient.getResult()).isEqualTo(DEFAULT_RESULT);
        assertThat(testSamplePatient.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testSamplePatient.getDatePublished()).isEqualTo(DEFAULT_DATE_PUBLISHED);
        assertThat(testSamplePatient.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    void createSamplePatientWithExistingId() throws Exception {
        // Create the SamplePatient with an existing ID
        samplePatient.setId(1L);

        int databaseSizeBeforeCreate = samplePatientRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSamplePatientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(samplePatient)))
            .andExpect(status().isBadRequest());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSamplePatients() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList
        restSamplePatientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(samplePatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].primaryReferrer").value(hasItem(DEFAULT_PRIMARY_REFERRER)))
            .andExpect(jsonPath("$.[*].clientPatientId").value(hasItem(DEFAULT_CLIENT_PATIENT_ID)))
            .andExpect(jsonPath("$.[*].clientSampleId").value(hasItem(DEFAULT_CLIENT_SAMPLE_ID)))
            .andExpect(jsonPath("$.[*].clientContact").value(hasItem(DEFAULT_CLIENT_CONTACT)))
            .andExpect(jsonPath("$.[*].sampleTypeId").value(hasItem(DEFAULT_SAMPLE_TYPE_ID)))
            .andExpect(jsonPath("$.[*].sampleTypeName").value(hasItem(DEFAULT_SAMPLE_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].analysisServiceId").value(hasItem(DEFAULT_ANALYSIS_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].analysisServiceName").value(hasItem(DEFAULT_ANALYSIS_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].dateCollected").value(hasItem(DEFAULT_DATE_COLLECTED.toString())))
            .andExpect(jsonPath("$.[*].dateRegistered").value(hasItem(DEFAULT_DATE_REGISTERED.toString())))
            .andExpect(jsonPath("$.[*].dateTested").value(hasItem(DEFAULT_DATE_TESTED.toString())))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].datePublished").value(hasItem(DEFAULT_DATE_PUBLISHED.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)));
    }

    @Test
    @Transactional
    void getSamplePatient() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get the samplePatient
        restSamplePatientMockMvc
            .perform(get(ENTITY_API_URL_ID, samplePatient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(samplePatient.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.primaryReferrer").value(DEFAULT_PRIMARY_REFERRER))
            .andExpect(jsonPath("$.clientPatientId").value(DEFAULT_CLIENT_PATIENT_ID))
            .andExpect(jsonPath("$.clientSampleId").value(DEFAULT_CLIENT_SAMPLE_ID))
            .andExpect(jsonPath("$.clientContact").value(DEFAULT_CLIENT_CONTACT))
            .andExpect(jsonPath("$.sampleTypeId").value(DEFAULT_SAMPLE_TYPE_ID))
            .andExpect(jsonPath("$.sampleTypeName").value(DEFAULT_SAMPLE_TYPE_NAME))
            .andExpect(jsonPath("$.analysisServiceId").value(DEFAULT_ANALYSIS_SERVICE_ID))
            .andExpect(jsonPath("$.analysisServiceName").value(DEFAULT_ANALYSIS_SERVICE_NAME))
            .andExpect(jsonPath("$.dateCollected").value(DEFAULT_DATE_COLLECTED.toString()))
            .andExpect(jsonPath("$.dateRegistered").value(DEFAULT_DATE_REGISTERED.toString()))
            .andExpect(jsonPath("$.dateTested").value(DEFAULT_DATE_TESTED.toString()))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.datePublished").value(DEFAULT_DATE_PUBLISHED.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE));
    }

    @Test
    @Transactional
    void getSamplePatientsByIdFiltering() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        Long id = samplePatient.getId();

        defaultSamplePatientShouldBeFound("id.equals=" + id);
        defaultSamplePatientShouldNotBeFound("id.notEquals=" + id);

        defaultSamplePatientShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSamplePatientShouldNotBeFound("id.greaterThan=" + id);

        defaultSamplePatientShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSamplePatientShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where firstName equals to DEFAULT_FIRST_NAME
        defaultSamplePatientShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the samplePatientList where firstName equals to UPDATED_FIRST_NAME
        defaultSamplePatientShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultSamplePatientShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the samplePatientList where firstName equals to UPDATED_FIRST_NAME
        defaultSamplePatientShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where firstName is not null
        defaultSamplePatientShouldBeFound("firstName.specified=true");

        // Get all the samplePatientList where firstName is null
        defaultSamplePatientShouldNotBeFound("firstName.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where firstName contains DEFAULT_FIRST_NAME
        defaultSamplePatientShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the samplePatientList where firstName contains UPDATED_FIRST_NAME
        defaultSamplePatientShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where firstName does not contain DEFAULT_FIRST_NAME
        defaultSamplePatientShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the samplePatientList where firstName does not contain UPDATED_FIRST_NAME
        defaultSamplePatientShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where lastName equals to DEFAULT_LAST_NAME
        defaultSamplePatientShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the samplePatientList where lastName equals to UPDATED_LAST_NAME
        defaultSamplePatientShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultSamplePatientShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the samplePatientList where lastName equals to UPDATED_LAST_NAME
        defaultSamplePatientShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where lastName is not null
        defaultSamplePatientShouldBeFound("lastName.specified=true");

        // Get all the samplePatientList where lastName is null
        defaultSamplePatientShouldNotBeFound("lastName.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByLastNameContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where lastName contains DEFAULT_LAST_NAME
        defaultSamplePatientShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the samplePatientList where lastName contains UPDATED_LAST_NAME
        defaultSamplePatientShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where lastName does not contain DEFAULT_LAST_NAME
        defaultSamplePatientShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the samplePatientList where lastName does not contain UPDATED_LAST_NAME
        defaultSamplePatientShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDobIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dob equals to DEFAULT_DOB
        defaultSamplePatientShouldBeFound("dob.equals=" + DEFAULT_DOB);

        // Get all the samplePatientList where dob equals to UPDATED_DOB
        defaultSamplePatientShouldNotBeFound("dob.equals=" + UPDATED_DOB);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDobIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dob in DEFAULT_DOB or UPDATED_DOB
        defaultSamplePatientShouldBeFound("dob.in=" + DEFAULT_DOB + "," + UPDATED_DOB);

        // Get all the samplePatientList where dob equals to UPDATED_DOB
        defaultSamplePatientShouldNotBeFound("dob.in=" + UPDATED_DOB);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDobIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dob is not null
        defaultSamplePatientShouldBeFound("dob.specified=true");

        // Get all the samplePatientList where dob is null
        defaultSamplePatientShouldNotBeFound("dob.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDobIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dob is greater than or equal to DEFAULT_DOB
        defaultSamplePatientShouldBeFound("dob.greaterThanOrEqual=" + DEFAULT_DOB);

        // Get all the samplePatientList where dob is greater than or equal to UPDATED_DOB
        defaultSamplePatientShouldNotBeFound("dob.greaterThanOrEqual=" + UPDATED_DOB);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDobIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dob is less than or equal to DEFAULT_DOB
        defaultSamplePatientShouldBeFound("dob.lessThanOrEqual=" + DEFAULT_DOB);

        // Get all the samplePatientList where dob is less than or equal to SMALLER_DOB
        defaultSamplePatientShouldNotBeFound("dob.lessThanOrEqual=" + SMALLER_DOB);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDobIsLessThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dob is less than DEFAULT_DOB
        defaultSamplePatientShouldNotBeFound("dob.lessThan=" + DEFAULT_DOB);

        // Get all the samplePatientList where dob is less than UPDATED_DOB
        defaultSamplePatientShouldBeFound("dob.lessThan=" + UPDATED_DOB);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDobIsGreaterThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dob is greater than DEFAULT_DOB
        defaultSamplePatientShouldNotBeFound("dob.greaterThan=" + DEFAULT_DOB);

        // Get all the samplePatientList where dob is greater than SMALLER_DOB
        defaultSamplePatientShouldBeFound("dob.greaterThan=" + SMALLER_DOB);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAgeIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where age equals to DEFAULT_AGE
        defaultSamplePatientShouldBeFound("age.equals=" + DEFAULT_AGE);

        // Get all the samplePatientList where age equals to UPDATED_AGE
        defaultSamplePatientShouldNotBeFound("age.equals=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAgeIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where age in DEFAULT_AGE or UPDATED_AGE
        defaultSamplePatientShouldBeFound("age.in=" + DEFAULT_AGE + "," + UPDATED_AGE);

        // Get all the samplePatientList where age equals to UPDATED_AGE
        defaultSamplePatientShouldNotBeFound("age.in=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAgeIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where age is not null
        defaultSamplePatientShouldBeFound("age.specified=true");

        // Get all the samplePatientList where age is null
        defaultSamplePatientShouldNotBeFound("age.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAgeContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where age contains DEFAULT_AGE
        defaultSamplePatientShouldBeFound("age.contains=" + DEFAULT_AGE);

        // Get all the samplePatientList where age contains UPDATED_AGE
        defaultSamplePatientShouldNotBeFound("age.contains=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAgeNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where age does not contain DEFAULT_AGE
        defaultSamplePatientShouldNotBeFound("age.doesNotContain=" + DEFAULT_AGE);

        // Get all the samplePatientList where age does not contain UPDATED_AGE
        defaultSamplePatientShouldBeFound("age.doesNotContain=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByGenderIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where gender equals to DEFAULT_GENDER
        defaultSamplePatientShouldBeFound("gender.equals=" + DEFAULT_GENDER);

        // Get all the samplePatientList where gender equals to UPDATED_GENDER
        defaultSamplePatientShouldNotBeFound("gender.equals=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByGenderIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where gender in DEFAULT_GENDER or UPDATED_GENDER
        defaultSamplePatientShouldBeFound("gender.in=" + DEFAULT_GENDER + "," + UPDATED_GENDER);

        // Get all the samplePatientList where gender equals to UPDATED_GENDER
        defaultSamplePatientShouldNotBeFound("gender.in=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByGenderIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where gender is not null
        defaultSamplePatientShouldBeFound("gender.specified=true");

        // Get all the samplePatientList where gender is null
        defaultSamplePatientShouldNotBeFound("gender.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByGenderContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where gender contains DEFAULT_GENDER
        defaultSamplePatientShouldBeFound("gender.contains=" + DEFAULT_GENDER);

        // Get all the samplePatientList where gender contains UPDATED_GENDER
        defaultSamplePatientShouldNotBeFound("gender.contains=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByGenderNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where gender does not contain DEFAULT_GENDER
        defaultSamplePatientShouldNotBeFound("gender.doesNotContain=" + DEFAULT_GENDER);

        // Get all the samplePatientList where gender does not contain UPDATED_GENDER
        defaultSamplePatientShouldBeFound("gender.doesNotContain=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByPrimaryReferrerIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where primaryReferrer equals to DEFAULT_PRIMARY_REFERRER
        defaultSamplePatientShouldBeFound("primaryReferrer.equals=" + DEFAULT_PRIMARY_REFERRER);

        // Get all the samplePatientList where primaryReferrer equals to UPDATED_PRIMARY_REFERRER
        defaultSamplePatientShouldNotBeFound("primaryReferrer.equals=" + UPDATED_PRIMARY_REFERRER);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByPrimaryReferrerIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where primaryReferrer in DEFAULT_PRIMARY_REFERRER or UPDATED_PRIMARY_REFERRER
        defaultSamplePatientShouldBeFound("primaryReferrer.in=" + DEFAULT_PRIMARY_REFERRER + "," + UPDATED_PRIMARY_REFERRER);

        // Get all the samplePatientList where primaryReferrer equals to UPDATED_PRIMARY_REFERRER
        defaultSamplePatientShouldNotBeFound("primaryReferrer.in=" + UPDATED_PRIMARY_REFERRER);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByPrimaryReferrerIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where primaryReferrer is not null
        defaultSamplePatientShouldBeFound("primaryReferrer.specified=true");

        // Get all the samplePatientList where primaryReferrer is null
        defaultSamplePatientShouldNotBeFound("primaryReferrer.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByPrimaryReferrerContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where primaryReferrer contains DEFAULT_PRIMARY_REFERRER
        defaultSamplePatientShouldBeFound("primaryReferrer.contains=" + DEFAULT_PRIMARY_REFERRER);

        // Get all the samplePatientList where primaryReferrer contains UPDATED_PRIMARY_REFERRER
        defaultSamplePatientShouldNotBeFound("primaryReferrer.contains=" + UPDATED_PRIMARY_REFERRER);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByPrimaryReferrerNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where primaryReferrer does not contain DEFAULT_PRIMARY_REFERRER
        defaultSamplePatientShouldNotBeFound("primaryReferrer.doesNotContain=" + DEFAULT_PRIMARY_REFERRER);

        // Get all the samplePatientList where primaryReferrer does not contain UPDATED_PRIMARY_REFERRER
        defaultSamplePatientShouldBeFound("primaryReferrer.doesNotContain=" + UPDATED_PRIMARY_REFERRER);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientPatientIdIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientPatientId equals to DEFAULT_CLIENT_PATIENT_ID
        defaultSamplePatientShouldBeFound("clientPatientId.equals=" + DEFAULT_CLIENT_PATIENT_ID);

        // Get all the samplePatientList where clientPatientId equals to UPDATED_CLIENT_PATIENT_ID
        defaultSamplePatientShouldNotBeFound("clientPatientId.equals=" + UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientPatientIdIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientPatientId in DEFAULT_CLIENT_PATIENT_ID or UPDATED_CLIENT_PATIENT_ID
        defaultSamplePatientShouldBeFound("clientPatientId.in=" + DEFAULT_CLIENT_PATIENT_ID + "," + UPDATED_CLIENT_PATIENT_ID);

        // Get all the samplePatientList where clientPatientId equals to UPDATED_CLIENT_PATIENT_ID
        defaultSamplePatientShouldNotBeFound("clientPatientId.in=" + UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientPatientIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientPatientId is not null
        defaultSamplePatientShouldBeFound("clientPatientId.specified=true");

        // Get all the samplePatientList where clientPatientId is null
        defaultSamplePatientShouldNotBeFound("clientPatientId.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientPatientIdContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientPatientId contains DEFAULT_CLIENT_PATIENT_ID
        defaultSamplePatientShouldBeFound("clientPatientId.contains=" + DEFAULT_CLIENT_PATIENT_ID);

        // Get all the samplePatientList where clientPatientId contains UPDATED_CLIENT_PATIENT_ID
        defaultSamplePatientShouldNotBeFound("clientPatientId.contains=" + UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientPatientIdNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientPatientId does not contain DEFAULT_CLIENT_PATIENT_ID
        defaultSamplePatientShouldNotBeFound("clientPatientId.doesNotContain=" + DEFAULT_CLIENT_PATIENT_ID);

        // Get all the samplePatientList where clientPatientId does not contain UPDATED_CLIENT_PATIENT_ID
        defaultSamplePatientShouldBeFound("clientPatientId.doesNotContain=" + UPDATED_CLIENT_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientSampleIdIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientSampleId equals to DEFAULT_CLIENT_SAMPLE_ID
        defaultSamplePatientShouldBeFound("clientSampleId.equals=" + DEFAULT_CLIENT_SAMPLE_ID);

        // Get all the samplePatientList where clientSampleId equals to UPDATED_CLIENT_SAMPLE_ID
        defaultSamplePatientShouldNotBeFound("clientSampleId.equals=" + UPDATED_CLIENT_SAMPLE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientSampleIdIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientSampleId in DEFAULT_CLIENT_SAMPLE_ID or UPDATED_CLIENT_SAMPLE_ID
        defaultSamplePatientShouldBeFound("clientSampleId.in=" + DEFAULT_CLIENT_SAMPLE_ID + "," + UPDATED_CLIENT_SAMPLE_ID);

        // Get all the samplePatientList where clientSampleId equals to UPDATED_CLIENT_SAMPLE_ID
        defaultSamplePatientShouldNotBeFound("clientSampleId.in=" + UPDATED_CLIENT_SAMPLE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientSampleIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientSampleId is not null
        defaultSamplePatientShouldBeFound("clientSampleId.specified=true");

        // Get all the samplePatientList where clientSampleId is null
        defaultSamplePatientShouldNotBeFound("clientSampleId.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientSampleIdContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientSampleId contains DEFAULT_CLIENT_SAMPLE_ID
        defaultSamplePatientShouldBeFound("clientSampleId.contains=" + DEFAULT_CLIENT_SAMPLE_ID);

        // Get all the samplePatientList where clientSampleId contains UPDATED_CLIENT_SAMPLE_ID
        defaultSamplePatientShouldNotBeFound("clientSampleId.contains=" + UPDATED_CLIENT_SAMPLE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientSampleIdNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientSampleId does not contain DEFAULT_CLIENT_SAMPLE_ID
        defaultSamplePatientShouldNotBeFound("clientSampleId.doesNotContain=" + DEFAULT_CLIENT_SAMPLE_ID);

        // Get all the samplePatientList where clientSampleId does not contain UPDATED_CLIENT_SAMPLE_ID
        defaultSamplePatientShouldBeFound("clientSampleId.doesNotContain=" + UPDATED_CLIENT_SAMPLE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientContactIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientContact equals to DEFAULT_CLIENT_CONTACT
        defaultSamplePatientShouldBeFound("clientContact.equals=" + DEFAULT_CLIENT_CONTACT);

        // Get all the samplePatientList where clientContact equals to UPDATED_CLIENT_CONTACT
        defaultSamplePatientShouldNotBeFound("clientContact.equals=" + UPDATED_CLIENT_CONTACT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientContactIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientContact in DEFAULT_CLIENT_CONTACT or UPDATED_CLIENT_CONTACT
        defaultSamplePatientShouldBeFound("clientContact.in=" + DEFAULT_CLIENT_CONTACT + "," + UPDATED_CLIENT_CONTACT);

        // Get all the samplePatientList where clientContact equals to UPDATED_CLIENT_CONTACT
        defaultSamplePatientShouldNotBeFound("clientContact.in=" + UPDATED_CLIENT_CONTACT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientContactIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientContact is not null
        defaultSamplePatientShouldBeFound("clientContact.specified=true");

        // Get all the samplePatientList where clientContact is null
        defaultSamplePatientShouldNotBeFound("clientContact.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientContactContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientContact contains DEFAULT_CLIENT_CONTACT
        defaultSamplePatientShouldBeFound("clientContact.contains=" + DEFAULT_CLIENT_CONTACT);

        // Get all the samplePatientList where clientContact contains UPDATED_CLIENT_CONTACT
        defaultSamplePatientShouldNotBeFound("clientContact.contains=" + UPDATED_CLIENT_CONTACT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByClientContactNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where clientContact does not contain DEFAULT_CLIENT_CONTACT
        defaultSamplePatientShouldNotBeFound("clientContact.doesNotContain=" + DEFAULT_CLIENT_CONTACT);

        // Get all the samplePatientList where clientContact does not contain UPDATED_CLIENT_CONTACT
        defaultSamplePatientShouldBeFound("clientContact.doesNotContain=" + UPDATED_CLIENT_CONTACT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeId equals to DEFAULT_SAMPLE_TYPE_ID
        defaultSamplePatientShouldBeFound("sampleTypeId.equals=" + DEFAULT_SAMPLE_TYPE_ID);

        // Get all the samplePatientList where sampleTypeId equals to UPDATED_SAMPLE_TYPE_ID
        defaultSamplePatientShouldNotBeFound("sampleTypeId.equals=" + UPDATED_SAMPLE_TYPE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeIdIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeId in DEFAULT_SAMPLE_TYPE_ID or UPDATED_SAMPLE_TYPE_ID
        defaultSamplePatientShouldBeFound("sampleTypeId.in=" + DEFAULT_SAMPLE_TYPE_ID + "," + UPDATED_SAMPLE_TYPE_ID);

        // Get all the samplePatientList where sampleTypeId equals to UPDATED_SAMPLE_TYPE_ID
        defaultSamplePatientShouldNotBeFound("sampleTypeId.in=" + UPDATED_SAMPLE_TYPE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeId is not null
        defaultSamplePatientShouldBeFound("sampleTypeId.specified=true");

        // Get all the samplePatientList where sampleTypeId is null
        defaultSamplePatientShouldNotBeFound("sampleTypeId.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeIdContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeId contains DEFAULT_SAMPLE_TYPE_ID
        defaultSamplePatientShouldBeFound("sampleTypeId.contains=" + DEFAULT_SAMPLE_TYPE_ID);

        // Get all the samplePatientList where sampleTypeId contains UPDATED_SAMPLE_TYPE_ID
        defaultSamplePatientShouldNotBeFound("sampleTypeId.contains=" + UPDATED_SAMPLE_TYPE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeIdNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeId does not contain DEFAULT_SAMPLE_TYPE_ID
        defaultSamplePatientShouldNotBeFound("sampleTypeId.doesNotContain=" + DEFAULT_SAMPLE_TYPE_ID);

        // Get all the samplePatientList where sampleTypeId does not contain UPDATED_SAMPLE_TYPE_ID
        defaultSamplePatientShouldBeFound("sampleTypeId.doesNotContain=" + UPDATED_SAMPLE_TYPE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeName equals to DEFAULT_SAMPLE_TYPE_NAME
        defaultSamplePatientShouldBeFound("sampleTypeName.equals=" + DEFAULT_SAMPLE_TYPE_NAME);

        // Get all the samplePatientList where sampleTypeName equals to UPDATED_SAMPLE_TYPE_NAME
        defaultSamplePatientShouldNotBeFound("sampleTypeName.equals=" + UPDATED_SAMPLE_TYPE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeName in DEFAULT_SAMPLE_TYPE_NAME or UPDATED_SAMPLE_TYPE_NAME
        defaultSamplePatientShouldBeFound("sampleTypeName.in=" + DEFAULT_SAMPLE_TYPE_NAME + "," + UPDATED_SAMPLE_TYPE_NAME);

        // Get all the samplePatientList where sampleTypeName equals to UPDATED_SAMPLE_TYPE_NAME
        defaultSamplePatientShouldNotBeFound("sampleTypeName.in=" + UPDATED_SAMPLE_TYPE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeName is not null
        defaultSamplePatientShouldBeFound("sampleTypeName.specified=true");

        // Get all the samplePatientList where sampleTypeName is null
        defaultSamplePatientShouldNotBeFound("sampleTypeName.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeNameContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeName contains DEFAULT_SAMPLE_TYPE_NAME
        defaultSamplePatientShouldBeFound("sampleTypeName.contains=" + DEFAULT_SAMPLE_TYPE_NAME);

        // Get all the samplePatientList where sampleTypeName contains UPDATED_SAMPLE_TYPE_NAME
        defaultSamplePatientShouldNotBeFound("sampleTypeName.contains=" + UPDATED_SAMPLE_TYPE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsBySampleTypeNameNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where sampleTypeName does not contain DEFAULT_SAMPLE_TYPE_NAME
        defaultSamplePatientShouldNotBeFound("sampleTypeName.doesNotContain=" + DEFAULT_SAMPLE_TYPE_NAME);

        // Get all the samplePatientList where sampleTypeName does not contain UPDATED_SAMPLE_TYPE_NAME
        defaultSamplePatientShouldBeFound("sampleTypeName.doesNotContain=" + UPDATED_SAMPLE_TYPE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceIdIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceId equals to DEFAULT_ANALYSIS_SERVICE_ID
        defaultSamplePatientShouldBeFound("analysisServiceId.equals=" + DEFAULT_ANALYSIS_SERVICE_ID);

        // Get all the samplePatientList where analysisServiceId equals to UPDATED_ANALYSIS_SERVICE_ID
        defaultSamplePatientShouldNotBeFound("analysisServiceId.equals=" + UPDATED_ANALYSIS_SERVICE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceIdIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceId in DEFAULT_ANALYSIS_SERVICE_ID or UPDATED_ANALYSIS_SERVICE_ID
        defaultSamplePatientShouldBeFound("analysisServiceId.in=" + DEFAULT_ANALYSIS_SERVICE_ID + "," + UPDATED_ANALYSIS_SERVICE_ID);

        // Get all the samplePatientList where analysisServiceId equals to UPDATED_ANALYSIS_SERVICE_ID
        defaultSamplePatientShouldNotBeFound("analysisServiceId.in=" + UPDATED_ANALYSIS_SERVICE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceId is not null
        defaultSamplePatientShouldBeFound("analysisServiceId.specified=true");

        // Get all the samplePatientList where analysisServiceId is null
        defaultSamplePatientShouldNotBeFound("analysisServiceId.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceIdContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceId contains DEFAULT_ANALYSIS_SERVICE_ID
        defaultSamplePatientShouldBeFound("analysisServiceId.contains=" + DEFAULT_ANALYSIS_SERVICE_ID);

        // Get all the samplePatientList where analysisServiceId contains UPDATED_ANALYSIS_SERVICE_ID
        defaultSamplePatientShouldNotBeFound("analysisServiceId.contains=" + UPDATED_ANALYSIS_SERVICE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceIdNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceId does not contain DEFAULT_ANALYSIS_SERVICE_ID
        defaultSamplePatientShouldNotBeFound("analysisServiceId.doesNotContain=" + DEFAULT_ANALYSIS_SERVICE_ID);

        // Get all the samplePatientList where analysisServiceId does not contain UPDATED_ANALYSIS_SERVICE_ID
        defaultSamplePatientShouldBeFound("analysisServiceId.doesNotContain=" + UPDATED_ANALYSIS_SERVICE_ID);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceNameIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceName equals to DEFAULT_ANALYSIS_SERVICE_NAME
        defaultSamplePatientShouldBeFound("analysisServiceName.equals=" + DEFAULT_ANALYSIS_SERVICE_NAME);

        // Get all the samplePatientList where analysisServiceName equals to UPDATED_ANALYSIS_SERVICE_NAME
        defaultSamplePatientShouldNotBeFound("analysisServiceName.equals=" + UPDATED_ANALYSIS_SERVICE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceNameIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceName in DEFAULT_ANALYSIS_SERVICE_NAME or UPDATED_ANALYSIS_SERVICE_NAME
        defaultSamplePatientShouldBeFound("analysisServiceName.in=" + DEFAULT_ANALYSIS_SERVICE_NAME + "," + UPDATED_ANALYSIS_SERVICE_NAME);

        // Get all the samplePatientList where analysisServiceName equals to UPDATED_ANALYSIS_SERVICE_NAME
        defaultSamplePatientShouldNotBeFound("analysisServiceName.in=" + UPDATED_ANALYSIS_SERVICE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceName is not null
        defaultSamplePatientShouldBeFound("analysisServiceName.specified=true");

        // Get all the samplePatientList where analysisServiceName is null
        defaultSamplePatientShouldNotBeFound("analysisServiceName.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceNameContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceName contains DEFAULT_ANALYSIS_SERVICE_NAME
        defaultSamplePatientShouldBeFound("analysisServiceName.contains=" + DEFAULT_ANALYSIS_SERVICE_NAME);

        // Get all the samplePatientList where analysisServiceName contains UPDATED_ANALYSIS_SERVICE_NAME
        defaultSamplePatientShouldNotBeFound("analysisServiceName.contains=" + UPDATED_ANALYSIS_SERVICE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByAnalysisServiceNameNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where analysisServiceName does not contain DEFAULT_ANALYSIS_SERVICE_NAME
        defaultSamplePatientShouldNotBeFound("analysisServiceName.doesNotContain=" + DEFAULT_ANALYSIS_SERVICE_NAME);

        // Get all the samplePatientList where analysisServiceName does not contain UPDATED_ANALYSIS_SERVICE_NAME
        defaultSamplePatientShouldBeFound("analysisServiceName.doesNotContain=" + UPDATED_ANALYSIS_SERVICE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateCollectedIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateCollected equals to DEFAULT_DATE_COLLECTED
        defaultSamplePatientShouldBeFound("dateCollected.equals=" + DEFAULT_DATE_COLLECTED);

        // Get all the samplePatientList where dateCollected equals to UPDATED_DATE_COLLECTED
        defaultSamplePatientShouldNotBeFound("dateCollected.equals=" + UPDATED_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateCollectedIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateCollected in DEFAULT_DATE_COLLECTED or UPDATED_DATE_COLLECTED
        defaultSamplePatientShouldBeFound("dateCollected.in=" + DEFAULT_DATE_COLLECTED + "," + UPDATED_DATE_COLLECTED);

        // Get all the samplePatientList where dateCollected equals to UPDATED_DATE_COLLECTED
        defaultSamplePatientShouldNotBeFound("dateCollected.in=" + UPDATED_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateCollectedIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateCollected is not null
        defaultSamplePatientShouldBeFound("dateCollected.specified=true");

        // Get all the samplePatientList where dateCollected is null
        defaultSamplePatientShouldNotBeFound("dateCollected.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateCollectedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateCollected is greater than or equal to DEFAULT_DATE_COLLECTED
        defaultSamplePatientShouldBeFound("dateCollected.greaterThanOrEqual=" + DEFAULT_DATE_COLLECTED);

        // Get all the samplePatientList where dateCollected is greater than or equal to UPDATED_DATE_COLLECTED
        defaultSamplePatientShouldNotBeFound("dateCollected.greaterThanOrEqual=" + UPDATED_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateCollectedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateCollected is less than or equal to DEFAULT_DATE_COLLECTED
        defaultSamplePatientShouldBeFound("dateCollected.lessThanOrEqual=" + DEFAULT_DATE_COLLECTED);

        // Get all the samplePatientList where dateCollected is less than or equal to SMALLER_DATE_COLLECTED
        defaultSamplePatientShouldNotBeFound("dateCollected.lessThanOrEqual=" + SMALLER_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateCollectedIsLessThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateCollected is less than DEFAULT_DATE_COLLECTED
        defaultSamplePatientShouldNotBeFound("dateCollected.lessThan=" + DEFAULT_DATE_COLLECTED);

        // Get all the samplePatientList where dateCollected is less than UPDATED_DATE_COLLECTED
        defaultSamplePatientShouldBeFound("dateCollected.lessThan=" + UPDATED_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateCollectedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateCollected is greater than DEFAULT_DATE_COLLECTED
        defaultSamplePatientShouldNotBeFound("dateCollected.greaterThan=" + DEFAULT_DATE_COLLECTED);

        // Get all the samplePatientList where dateCollected is greater than SMALLER_DATE_COLLECTED
        defaultSamplePatientShouldBeFound("dateCollected.greaterThan=" + SMALLER_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateRegisteredIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateRegistered equals to DEFAULT_DATE_REGISTERED
        defaultSamplePatientShouldBeFound("dateRegistered.equals=" + DEFAULT_DATE_REGISTERED);

        // Get all the samplePatientList where dateRegistered equals to UPDATED_DATE_REGISTERED
        defaultSamplePatientShouldNotBeFound("dateRegistered.equals=" + UPDATED_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateRegisteredIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateRegistered in DEFAULT_DATE_REGISTERED or UPDATED_DATE_REGISTERED
        defaultSamplePatientShouldBeFound("dateRegistered.in=" + DEFAULT_DATE_REGISTERED + "," + UPDATED_DATE_REGISTERED);

        // Get all the samplePatientList where dateRegistered equals to UPDATED_DATE_REGISTERED
        defaultSamplePatientShouldNotBeFound("dateRegistered.in=" + UPDATED_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateRegisteredIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateRegistered is not null
        defaultSamplePatientShouldBeFound("dateRegistered.specified=true");

        // Get all the samplePatientList where dateRegistered is null
        defaultSamplePatientShouldNotBeFound("dateRegistered.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateRegisteredIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateRegistered is greater than or equal to DEFAULT_DATE_REGISTERED
        defaultSamplePatientShouldBeFound("dateRegistered.greaterThanOrEqual=" + DEFAULT_DATE_REGISTERED);

        // Get all the samplePatientList where dateRegistered is greater than or equal to UPDATED_DATE_REGISTERED
        defaultSamplePatientShouldNotBeFound("dateRegistered.greaterThanOrEqual=" + UPDATED_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateRegisteredIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateRegistered is less than or equal to DEFAULT_DATE_REGISTERED
        defaultSamplePatientShouldBeFound("dateRegistered.lessThanOrEqual=" + DEFAULT_DATE_REGISTERED);

        // Get all the samplePatientList where dateRegistered is less than or equal to SMALLER_DATE_REGISTERED
        defaultSamplePatientShouldNotBeFound("dateRegistered.lessThanOrEqual=" + SMALLER_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateRegisteredIsLessThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateRegistered is less than DEFAULT_DATE_REGISTERED
        defaultSamplePatientShouldNotBeFound("dateRegistered.lessThan=" + DEFAULT_DATE_REGISTERED);

        // Get all the samplePatientList where dateRegistered is less than UPDATED_DATE_REGISTERED
        defaultSamplePatientShouldBeFound("dateRegistered.lessThan=" + UPDATED_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateRegisteredIsGreaterThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateRegistered is greater than DEFAULT_DATE_REGISTERED
        defaultSamplePatientShouldNotBeFound("dateRegistered.greaterThan=" + DEFAULT_DATE_REGISTERED);

        // Get all the samplePatientList where dateRegistered is greater than SMALLER_DATE_REGISTERED
        defaultSamplePatientShouldBeFound("dateRegistered.greaterThan=" + SMALLER_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateTestedIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateTested equals to DEFAULT_DATE_TESTED
        defaultSamplePatientShouldBeFound("dateTested.equals=" + DEFAULT_DATE_TESTED);

        // Get all the samplePatientList where dateTested equals to UPDATED_DATE_TESTED
        defaultSamplePatientShouldNotBeFound("dateTested.equals=" + UPDATED_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateTestedIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateTested in DEFAULT_DATE_TESTED or UPDATED_DATE_TESTED
        defaultSamplePatientShouldBeFound("dateTested.in=" + DEFAULT_DATE_TESTED + "," + UPDATED_DATE_TESTED);

        // Get all the samplePatientList where dateTested equals to UPDATED_DATE_TESTED
        defaultSamplePatientShouldNotBeFound("dateTested.in=" + UPDATED_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateTestedIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateTested is not null
        defaultSamplePatientShouldBeFound("dateTested.specified=true");

        // Get all the samplePatientList where dateTested is null
        defaultSamplePatientShouldNotBeFound("dateTested.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateTestedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateTested is greater than or equal to DEFAULT_DATE_TESTED
        defaultSamplePatientShouldBeFound("dateTested.greaterThanOrEqual=" + DEFAULT_DATE_TESTED);

        // Get all the samplePatientList where dateTested is greater than or equal to UPDATED_DATE_TESTED
        defaultSamplePatientShouldNotBeFound("dateTested.greaterThanOrEqual=" + UPDATED_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateTestedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateTested is less than or equal to DEFAULT_DATE_TESTED
        defaultSamplePatientShouldBeFound("dateTested.lessThanOrEqual=" + DEFAULT_DATE_TESTED);

        // Get all the samplePatientList where dateTested is less than or equal to SMALLER_DATE_TESTED
        defaultSamplePatientShouldNotBeFound("dateTested.lessThanOrEqual=" + SMALLER_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateTestedIsLessThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateTested is less than DEFAULT_DATE_TESTED
        defaultSamplePatientShouldNotBeFound("dateTested.lessThan=" + DEFAULT_DATE_TESTED);

        // Get all the samplePatientList where dateTested is less than UPDATED_DATE_TESTED
        defaultSamplePatientShouldBeFound("dateTested.lessThan=" + UPDATED_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDateTestedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where dateTested is greater than DEFAULT_DATE_TESTED
        defaultSamplePatientShouldNotBeFound("dateTested.greaterThan=" + DEFAULT_DATE_TESTED);

        // Get all the samplePatientList where dateTested is greater than SMALLER_DATE_TESTED
        defaultSamplePatientShouldBeFound("dateTested.greaterThan=" + SMALLER_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByResultIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where result equals to DEFAULT_RESULT
        defaultSamplePatientShouldBeFound("result.equals=" + DEFAULT_RESULT);

        // Get all the samplePatientList where result equals to UPDATED_RESULT
        defaultSamplePatientShouldNotBeFound("result.equals=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByResultIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where result in DEFAULT_RESULT or UPDATED_RESULT
        defaultSamplePatientShouldBeFound("result.in=" + DEFAULT_RESULT + "," + UPDATED_RESULT);

        // Get all the samplePatientList where result equals to UPDATED_RESULT
        defaultSamplePatientShouldNotBeFound("result.in=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByResultIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where result is not null
        defaultSamplePatientShouldBeFound("result.specified=true");

        // Get all the samplePatientList where result is null
        defaultSamplePatientShouldNotBeFound("result.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByResultContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where result contains DEFAULT_RESULT
        defaultSamplePatientShouldBeFound("result.contains=" + DEFAULT_RESULT);

        // Get all the samplePatientList where result contains UPDATED_RESULT
        defaultSamplePatientShouldNotBeFound("result.contains=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByResultNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where result does not contain DEFAULT_RESULT
        defaultSamplePatientShouldNotBeFound("result.doesNotContain=" + DEFAULT_RESULT);

        // Get all the samplePatientList where result does not contain UPDATED_RESULT
        defaultSamplePatientShouldBeFound("result.doesNotContain=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where unit equals to DEFAULT_UNIT
        defaultSamplePatientShouldBeFound("unit.equals=" + DEFAULT_UNIT);

        // Get all the samplePatientList where unit equals to UPDATED_UNIT
        defaultSamplePatientShouldNotBeFound("unit.equals=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByUnitIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where unit in DEFAULT_UNIT or UPDATED_UNIT
        defaultSamplePatientShouldBeFound("unit.in=" + DEFAULT_UNIT + "," + UPDATED_UNIT);

        // Get all the samplePatientList where unit equals to UPDATED_UNIT
        defaultSamplePatientShouldNotBeFound("unit.in=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where unit is not null
        defaultSamplePatientShouldBeFound("unit.specified=true");

        // Get all the samplePatientList where unit is null
        defaultSamplePatientShouldNotBeFound("unit.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByUnitContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where unit contains DEFAULT_UNIT
        defaultSamplePatientShouldBeFound("unit.contains=" + DEFAULT_UNIT);

        // Get all the samplePatientList where unit contains UPDATED_UNIT
        defaultSamplePatientShouldNotBeFound("unit.contains=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByUnitNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where unit does not contain DEFAULT_UNIT
        defaultSamplePatientShouldNotBeFound("unit.doesNotContain=" + DEFAULT_UNIT);

        // Get all the samplePatientList where unit does not contain UPDATED_UNIT
        defaultSamplePatientShouldBeFound("unit.doesNotContain=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDatePublishedIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where datePublished equals to DEFAULT_DATE_PUBLISHED
        defaultSamplePatientShouldBeFound("datePublished.equals=" + DEFAULT_DATE_PUBLISHED);

        // Get all the samplePatientList where datePublished equals to UPDATED_DATE_PUBLISHED
        defaultSamplePatientShouldNotBeFound("datePublished.equals=" + UPDATED_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDatePublishedIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where datePublished in DEFAULT_DATE_PUBLISHED or UPDATED_DATE_PUBLISHED
        defaultSamplePatientShouldBeFound("datePublished.in=" + DEFAULT_DATE_PUBLISHED + "," + UPDATED_DATE_PUBLISHED);

        // Get all the samplePatientList where datePublished equals to UPDATED_DATE_PUBLISHED
        defaultSamplePatientShouldNotBeFound("datePublished.in=" + UPDATED_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDatePublishedIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where datePublished is not null
        defaultSamplePatientShouldBeFound("datePublished.specified=true");

        // Get all the samplePatientList where datePublished is null
        defaultSamplePatientShouldNotBeFound("datePublished.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDatePublishedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where datePublished is greater than or equal to DEFAULT_DATE_PUBLISHED
        defaultSamplePatientShouldBeFound("datePublished.greaterThanOrEqual=" + DEFAULT_DATE_PUBLISHED);

        // Get all the samplePatientList where datePublished is greater than or equal to UPDATED_DATE_PUBLISHED
        defaultSamplePatientShouldNotBeFound("datePublished.greaterThanOrEqual=" + UPDATED_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDatePublishedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where datePublished is less than or equal to DEFAULT_DATE_PUBLISHED
        defaultSamplePatientShouldBeFound("datePublished.lessThanOrEqual=" + DEFAULT_DATE_PUBLISHED);

        // Get all the samplePatientList where datePublished is less than or equal to SMALLER_DATE_PUBLISHED
        defaultSamplePatientShouldNotBeFound("datePublished.lessThanOrEqual=" + SMALLER_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDatePublishedIsLessThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where datePublished is less than DEFAULT_DATE_PUBLISHED
        defaultSamplePatientShouldNotBeFound("datePublished.lessThan=" + DEFAULT_DATE_PUBLISHED);

        // Get all the samplePatientList where datePublished is less than UPDATED_DATE_PUBLISHED
        defaultSamplePatientShouldBeFound("datePublished.lessThan=" + UPDATED_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByDatePublishedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where datePublished is greater than DEFAULT_DATE_PUBLISHED
        defaultSamplePatientShouldNotBeFound("datePublished.greaterThan=" + DEFAULT_DATE_PUBLISHED);

        // Get all the samplePatientList where datePublished is greater than SMALLER_DATE_PUBLISHED
        defaultSamplePatientShouldBeFound("datePublished.greaterThan=" + SMALLER_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where state equals to DEFAULT_STATE
        defaultSamplePatientShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the samplePatientList where state equals to UPDATED_STATE
        defaultSamplePatientShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByStateIsInShouldWork() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where state in DEFAULT_STATE or UPDATED_STATE
        defaultSamplePatientShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the samplePatientList where state equals to UPDATED_STATE
        defaultSamplePatientShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where state is not null
        defaultSamplePatientShouldBeFound("state.specified=true");

        // Get all the samplePatientList where state is null
        defaultSamplePatientShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplePatientsByStateContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where state contains DEFAULT_STATE
        defaultSamplePatientShouldBeFound("state.contains=" + DEFAULT_STATE);

        // Get all the samplePatientList where state contains UPDATED_STATE
        defaultSamplePatientShouldNotBeFound("state.contains=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllSamplePatientsByStateNotContainsSomething() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        // Get all the samplePatientList where state does not contain DEFAULT_STATE
        defaultSamplePatientShouldNotBeFound("state.doesNotContain=" + DEFAULT_STATE);

        // Get all the samplePatientList where state does not contain UPDATED_STATE
        defaultSamplePatientShouldBeFound("state.doesNotContain=" + UPDATED_STATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSamplePatientShouldBeFound(String filter) throws Exception {
        restSamplePatientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(samplePatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].primaryReferrer").value(hasItem(DEFAULT_PRIMARY_REFERRER)))
            .andExpect(jsonPath("$.[*].clientPatientId").value(hasItem(DEFAULT_CLIENT_PATIENT_ID)))
            .andExpect(jsonPath("$.[*].clientSampleId").value(hasItem(DEFAULT_CLIENT_SAMPLE_ID)))
            .andExpect(jsonPath("$.[*].clientContact").value(hasItem(DEFAULT_CLIENT_CONTACT)))
            .andExpect(jsonPath("$.[*].sampleTypeId").value(hasItem(DEFAULT_SAMPLE_TYPE_ID)))
            .andExpect(jsonPath("$.[*].sampleTypeName").value(hasItem(DEFAULT_SAMPLE_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].analysisServiceId").value(hasItem(DEFAULT_ANALYSIS_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].analysisServiceName").value(hasItem(DEFAULT_ANALYSIS_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].dateCollected").value(hasItem(DEFAULT_DATE_COLLECTED.toString())))
            .andExpect(jsonPath("$.[*].dateRegistered").value(hasItem(DEFAULT_DATE_REGISTERED.toString())))
            .andExpect(jsonPath("$.[*].dateTested").value(hasItem(DEFAULT_DATE_TESTED.toString())))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].datePublished").value(hasItem(DEFAULT_DATE_PUBLISHED.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)));

        // Check, that the count call also returns 1
        restSamplePatientMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSamplePatientShouldNotBeFound(String filter) throws Exception {
        restSamplePatientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSamplePatientMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingSamplePatient() throws Exception {
        // Get the samplePatient
        restSamplePatientMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSamplePatient() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();

        // Update the samplePatient
        SamplePatient updatedSamplePatient = samplePatientRepository.findById(samplePatient.getId()).get();
        // Disconnect from session so that the updates on updatedSamplePatient are not directly saved in db
        em.detach(updatedSamplePatient);
        updatedSamplePatient
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .primaryReferrer(UPDATED_PRIMARY_REFERRER)
            .clientPatientId(UPDATED_CLIENT_PATIENT_ID)
            .clientSampleId(UPDATED_CLIENT_SAMPLE_ID)
            .clientContact(UPDATED_CLIENT_CONTACT)
            .sampleTypeId(UPDATED_SAMPLE_TYPE_ID)
            .sampleTypeName(UPDATED_SAMPLE_TYPE_NAME)
            .analysisServiceId(UPDATED_ANALYSIS_SERVICE_ID)
            .analysisServiceName(UPDATED_ANALYSIS_SERVICE_NAME)
            .dateCollected(UPDATED_DATE_COLLECTED)
            .dateRegistered(UPDATED_DATE_REGISTERED)
            .dateTested(UPDATED_DATE_TESTED)
            .result(UPDATED_RESULT)
            .unit(UPDATED_UNIT)
            .datePublished(UPDATED_DATE_PUBLISHED)
            .state(UPDATED_STATE);

        restSamplePatientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSamplePatient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSamplePatient))
            )
            .andExpect(status().isOk());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
        SamplePatient testSamplePatient = samplePatientList.get(samplePatientList.size() - 1);
        assertThat(testSamplePatient.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSamplePatient.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSamplePatient.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testSamplePatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testSamplePatient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testSamplePatient.getPrimaryReferrer()).isEqualTo(UPDATED_PRIMARY_REFERRER);
        assertThat(testSamplePatient.getClientPatientId()).isEqualTo(UPDATED_CLIENT_PATIENT_ID);
        assertThat(testSamplePatient.getClientSampleId()).isEqualTo(UPDATED_CLIENT_SAMPLE_ID);
        assertThat(testSamplePatient.getClientContact()).isEqualTo(UPDATED_CLIENT_CONTACT);
        assertThat(testSamplePatient.getSampleTypeId()).isEqualTo(UPDATED_SAMPLE_TYPE_ID);
        assertThat(testSamplePatient.getSampleTypeName()).isEqualTo(UPDATED_SAMPLE_TYPE_NAME);
        assertThat(testSamplePatient.getAnalysisServiceId()).isEqualTo(UPDATED_ANALYSIS_SERVICE_ID);
        assertThat(testSamplePatient.getAnalysisServiceName()).isEqualTo(UPDATED_ANALYSIS_SERVICE_NAME);
        assertThat(testSamplePatient.getDateCollected()).isEqualTo(UPDATED_DATE_COLLECTED);
        assertThat(testSamplePatient.getDateRegistered()).isEqualTo(UPDATED_DATE_REGISTERED);
        assertThat(testSamplePatient.getDateTested()).isEqualTo(UPDATED_DATE_TESTED);
        assertThat(testSamplePatient.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testSamplePatient.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testSamplePatient.getDatePublished()).isEqualTo(UPDATED_DATE_PUBLISHED);
        assertThat(testSamplePatient.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void putNonExistingSamplePatient() throws Exception {
        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();
        samplePatient.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSamplePatientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, samplePatient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(samplePatient))
            )
            .andExpect(status().isBadRequest());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSamplePatient() throws Exception {
        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();
        samplePatient.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSamplePatientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(samplePatient))
            )
            .andExpect(status().isBadRequest());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSamplePatient() throws Exception {
        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();
        samplePatient.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSamplePatientMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(samplePatient)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSamplePatientWithPatch() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();

        // Update the samplePatient using partial update
        SamplePatient partialUpdatedSamplePatient = new SamplePatient();
        partialUpdatedSamplePatient.setId(samplePatient.getId());

        partialUpdatedSamplePatient
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .clientPatientId(UPDATED_CLIENT_PATIENT_ID)
            .clientContact(UPDATED_CLIENT_CONTACT)
            .analysisServiceId(UPDATED_ANALYSIS_SERVICE_ID)
            .analysisServiceName(UPDATED_ANALYSIS_SERVICE_NAME)
            .dateCollected(UPDATED_DATE_COLLECTED)
            .result(UPDATED_RESULT)
            .datePublished(UPDATED_DATE_PUBLISHED)
            .state(UPDATED_STATE);

        restSamplePatientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSamplePatient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSamplePatient))
            )
            .andExpect(status().isOk());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
        SamplePatient testSamplePatient = samplePatientList.get(samplePatientList.size() - 1);
        assertThat(testSamplePatient.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testSamplePatient.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSamplePatient.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testSamplePatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testSamplePatient.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testSamplePatient.getPrimaryReferrer()).isEqualTo(DEFAULT_PRIMARY_REFERRER);
        assertThat(testSamplePatient.getClientPatientId()).isEqualTo(UPDATED_CLIENT_PATIENT_ID);
        assertThat(testSamplePatient.getClientSampleId()).isEqualTo(DEFAULT_CLIENT_SAMPLE_ID);
        assertThat(testSamplePatient.getClientContact()).isEqualTo(UPDATED_CLIENT_CONTACT);
        assertThat(testSamplePatient.getSampleTypeId()).isEqualTo(DEFAULT_SAMPLE_TYPE_ID);
        assertThat(testSamplePatient.getSampleTypeName()).isEqualTo(DEFAULT_SAMPLE_TYPE_NAME);
        assertThat(testSamplePatient.getAnalysisServiceId()).isEqualTo(UPDATED_ANALYSIS_SERVICE_ID);
        assertThat(testSamplePatient.getAnalysisServiceName()).isEqualTo(UPDATED_ANALYSIS_SERVICE_NAME);
        assertThat(testSamplePatient.getDateCollected()).isEqualTo(UPDATED_DATE_COLLECTED);
        assertThat(testSamplePatient.getDateRegistered()).isEqualTo(DEFAULT_DATE_REGISTERED);
        assertThat(testSamplePatient.getDateTested()).isEqualTo(DEFAULT_DATE_TESTED);
        assertThat(testSamplePatient.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testSamplePatient.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testSamplePatient.getDatePublished()).isEqualTo(UPDATED_DATE_PUBLISHED);
        assertThat(testSamplePatient.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void fullUpdateSamplePatientWithPatch() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();

        // Update the samplePatient using partial update
        SamplePatient partialUpdatedSamplePatient = new SamplePatient();
        partialUpdatedSamplePatient.setId(samplePatient.getId());

        partialUpdatedSamplePatient
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dob(UPDATED_DOB)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .primaryReferrer(UPDATED_PRIMARY_REFERRER)
            .clientPatientId(UPDATED_CLIENT_PATIENT_ID)
            .clientSampleId(UPDATED_CLIENT_SAMPLE_ID)
            .clientContact(UPDATED_CLIENT_CONTACT)
            .sampleTypeId(UPDATED_SAMPLE_TYPE_ID)
            .sampleTypeName(UPDATED_SAMPLE_TYPE_NAME)
            .analysisServiceId(UPDATED_ANALYSIS_SERVICE_ID)
            .analysisServiceName(UPDATED_ANALYSIS_SERVICE_NAME)
            .dateCollected(UPDATED_DATE_COLLECTED)
            .dateRegistered(UPDATED_DATE_REGISTERED)
            .dateTested(UPDATED_DATE_TESTED)
            .result(UPDATED_RESULT)
            .unit(UPDATED_UNIT)
            .datePublished(UPDATED_DATE_PUBLISHED)
            .state(UPDATED_STATE);

        restSamplePatientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSamplePatient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSamplePatient))
            )
            .andExpect(status().isOk());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
        SamplePatient testSamplePatient = samplePatientList.get(samplePatientList.size() - 1);
        assertThat(testSamplePatient.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSamplePatient.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSamplePatient.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testSamplePatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testSamplePatient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testSamplePatient.getPrimaryReferrer()).isEqualTo(UPDATED_PRIMARY_REFERRER);
        assertThat(testSamplePatient.getClientPatientId()).isEqualTo(UPDATED_CLIENT_PATIENT_ID);
        assertThat(testSamplePatient.getClientSampleId()).isEqualTo(UPDATED_CLIENT_SAMPLE_ID);
        assertThat(testSamplePatient.getClientContact()).isEqualTo(UPDATED_CLIENT_CONTACT);
        assertThat(testSamplePatient.getSampleTypeId()).isEqualTo(UPDATED_SAMPLE_TYPE_ID);
        assertThat(testSamplePatient.getSampleTypeName()).isEqualTo(UPDATED_SAMPLE_TYPE_NAME);
        assertThat(testSamplePatient.getAnalysisServiceId()).isEqualTo(UPDATED_ANALYSIS_SERVICE_ID);
        assertThat(testSamplePatient.getAnalysisServiceName()).isEqualTo(UPDATED_ANALYSIS_SERVICE_NAME);
        assertThat(testSamplePatient.getDateCollected()).isEqualTo(UPDATED_DATE_COLLECTED);
        assertThat(testSamplePatient.getDateRegistered()).isEqualTo(UPDATED_DATE_REGISTERED);
        assertThat(testSamplePatient.getDateTested()).isEqualTo(UPDATED_DATE_TESTED);
        assertThat(testSamplePatient.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testSamplePatient.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testSamplePatient.getDatePublished()).isEqualTo(UPDATED_DATE_PUBLISHED);
        assertThat(testSamplePatient.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void patchNonExistingSamplePatient() throws Exception {
        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();
        samplePatient.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSamplePatientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, samplePatient.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(samplePatient))
            )
            .andExpect(status().isBadRequest());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSamplePatient() throws Exception {
        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();
        samplePatient.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSamplePatientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(samplePatient))
            )
            .andExpect(status().isBadRequest());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSamplePatient() throws Exception {
        int databaseSizeBeforeUpdate = samplePatientRepository.findAll().size();
        samplePatient.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSamplePatientMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(samplePatient))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SamplePatient in the database
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSamplePatient() throws Exception {
        // Initialize the database
        samplePatientRepository.saveAndFlush(samplePatient);

        int databaseSizeBeforeDelete = samplePatientRepository.findAll().size();

        // Delete the samplePatient
        restSamplePatientMockMvc
            .perform(delete(ENTITY_API_URL_ID, samplePatient.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SamplePatient> samplePatientList = samplePatientRepository.findAll();
        assertThat(samplePatientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
