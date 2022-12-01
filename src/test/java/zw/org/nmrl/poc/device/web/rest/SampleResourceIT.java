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
import zw.org.nmrl.poc.device.domain.Sample;
import zw.org.nmrl.poc.device.repository.SampleRepository;
import zw.org.nmrl.poc.device.service.criteria.SampleCriteria;

/**
 * Integration tests for the {@link SampleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SampleResourceIT {

    private static final String DEFAULT_PATIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/samples";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSampleMockMvc;

    private Sample sample;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sample createEntity(EntityManager em) {
        Sample sample = new Sample()
            .patientId(DEFAULT_PATIENT_ID)
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
        return sample;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sample createUpdatedEntity(EntityManager em) {
        Sample sample = new Sample()
            .patientId(UPDATED_PATIENT_ID)
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
        return sample;
    }

    @BeforeEach
    public void initTest() {
        sample = createEntity(em);
    }

    @Test
    @Transactional
    void createSample() throws Exception {
        int databaseSizeBeforeCreate = sampleRepository.findAll().size();
        // Create the Sample
        restSampleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sample)))
            .andExpect(status().isCreated());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeCreate + 1);
        Sample testSample = sampleList.get(sampleList.size() - 1);
        assertThat(testSample.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testSample.getClientSampleId()).isEqualTo(DEFAULT_CLIENT_SAMPLE_ID);
        assertThat(testSample.getClientContact()).isEqualTo(DEFAULT_CLIENT_CONTACT);
        assertThat(testSample.getSampleTypeId()).isEqualTo(DEFAULT_SAMPLE_TYPE_ID);
        assertThat(testSample.getSampleTypeName()).isEqualTo(DEFAULT_SAMPLE_TYPE_NAME);
        assertThat(testSample.getAnalysisServiceId()).isEqualTo(DEFAULT_ANALYSIS_SERVICE_ID);
        assertThat(testSample.getAnalysisServiceName()).isEqualTo(DEFAULT_ANALYSIS_SERVICE_NAME);
        assertThat(testSample.getDateCollected()).isEqualTo(DEFAULT_DATE_COLLECTED);
        assertThat(testSample.getDateRegistered()).isEqualTo(DEFAULT_DATE_REGISTERED);
        assertThat(testSample.getDateTested()).isEqualTo(DEFAULT_DATE_TESTED);
        assertThat(testSample.getResult()).isEqualTo(DEFAULT_RESULT);
        assertThat(testSample.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testSample.getDatePublished()).isEqualTo(DEFAULT_DATE_PUBLISHED);
        assertThat(testSample.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    void createSampleWithExistingId() throws Exception {
        // Create the Sample with an existing ID
        sample.setId(1L);

        int databaseSizeBeforeCreate = sampleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSampleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sample)))
            .andExpect(status().isBadRequest());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSamples() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList
        restSampleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sample.getId().intValue())))
            .andExpect(jsonPath("$.[*].patientId").value(hasItem(DEFAULT_PATIENT_ID)))
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
    void getSample() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get the sample
        restSampleMockMvc
            .perform(get(ENTITY_API_URL_ID, sample.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sample.getId().intValue()))
            .andExpect(jsonPath("$.patientId").value(DEFAULT_PATIENT_ID))
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
    void getSamplesByIdFiltering() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        Long id = sample.getId();

        defaultSampleShouldBeFound("id.equals=" + id);
        defaultSampleShouldNotBeFound("id.notEquals=" + id);

        defaultSampleShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSampleShouldNotBeFound("id.greaterThan=" + id);

        defaultSampleShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSampleShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllSamplesByPatientIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where patientId equals to DEFAULT_PATIENT_ID
        defaultSampleShouldBeFound("patientId.equals=" + DEFAULT_PATIENT_ID);

        // Get all the sampleList where patientId equals to UPDATED_PATIENT_ID
        defaultSampleShouldNotBeFound("patientId.equals=" + UPDATED_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByPatientIdIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where patientId in DEFAULT_PATIENT_ID or UPDATED_PATIENT_ID
        defaultSampleShouldBeFound("patientId.in=" + DEFAULT_PATIENT_ID + "," + UPDATED_PATIENT_ID);

        // Get all the sampleList where patientId equals to UPDATED_PATIENT_ID
        defaultSampleShouldNotBeFound("patientId.in=" + UPDATED_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByPatientIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where patientId is not null
        defaultSampleShouldBeFound("patientId.specified=true");

        // Get all the sampleList where patientId is null
        defaultSampleShouldNotBeFound("patientId.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByPatientIdContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where patientId contains DEFAULT_PATIENT_ID
        defaultSampleShouldBeFound("patientId.contains=" + DEFAULT_PATIENT_ID);

        // Get all the sampleList where patientId contains UPDATED_PATIENT_ID
        defaultSampleShouldNotBeFound("patientId.contains=" + UPDATED_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByPatientIdNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where patientId does not contain DEFAULT_PATIENT_ID
        defaultSampleShouldNotBeFound("patientId.doesNotContain=" + DEFAULT_PATIENT_ID);

        // Get all the sampleList where patientId does not contain UPDATED_PATIENT_ID
        defaultSampleShouldBeFound("patientId.doesNotContain=" + UPDATED_PATIENT_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByClientSampleIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientSampleId equals to DEFAULT_CLIENT_SAMPLE_ID
        defaultSampleShouldBeFound("clientSampleId.equals=" + DEFAULT_CLIENT_SAMPLE_ID);

        // Get all the sampleList where clientSampleId equals to UPDATED_CLIENT_SAMPLE_ID
        defaultSampleShouldNotBeFound("clientSampleId.equals=" + UPDATED_CLIENT_SAMPLE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByClientSampleIdIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientSampleId in DEFAULT_CLIENT_SAMPLE_ID or UPDATED_CLIENT_SAMPLE_ID
        defaultSampleShouldBeFound("clientSampleId.in=" + DEFAULT_CLIENT_SAMPLE_ID + "," + UPDATED_CLIENT_SAMPLE_ID);

        // Get all the sampleList where clientSampleId equals to UPDATED_CLIENT_SAMPLE_ID
        defaultSampleShouldNotBeFound("clientSampleId.in=" + UPDATED_CLIENT_SAMPLE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByClientSampleIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientSampleId is not null
        defaultSampleShouldBeFound("clientSampleId.specified=true");

        // Get all the sampleList where clientSampleId is null
        defaultSampleShouldNotBeFound("clientSampleId.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByClientSampleIdContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientSampleId contains DEFAULT_CLIENT_SAMPLE_ID
        defaultSampleShouldBeFound("clientSampleId.contains=" + DEFAULT_CLIENT_SAMPLE_ID);

        // Get all the sampleList where clientSampleId contains UPDATED_CLIENT_SAMPLE_ID
        defaultSampleShouldNotBeFound("clientSampleId.contains=" + UPDATED_CLIENT_SAMPLE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByClientSampleIdNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientSampleId does not contain DEFAULT_CLIENT_SAMPLE_ID
        defaultSampleShouldNotBeFound("clientSampleId.doesNotContain=" + DEFAULT_CLIENT_SAMPLE_ID);

        // Get all the sampleList where clientSampleId does not contain UPDATED_CLIENT_SAMPLE_ID
        defaultSampleShouldBeFound("clientSampleId.doesNotContain=" + UPDATED_CLIENT_SAMPLE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByClientContactIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientContact equals to DEFAULT_CLIENT_CONTACT
        defaultSampleShouldBeFound("clientContact.equals=" + DEFAULT_CLIENT_CONTACT);

        // Get all the sampleList where clientContact equals to UPDATED_CLIENT_CONTACT
        defaultSampleShouldNotBeFound("clientContact.equals=" + UPDATED_CLIENT_CONTACT);
    }

    @Test
    @Transactional
    void getAllSamplesByClientContactIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientContact in DEFAULT_CLIENT_CONTACT or UPDATED_CLIENT_CONTACT
        defaultSampleShouldBeFound("clientContact.in=" + DEFAULT_CLIENT_CONTACT + "," + UPDATED_CLIENT_CONTACT);

        // Get all the sampleList where clientContact equals to UPDATED_CLIENT_CONTACT
        defaultSampleShouldNotBeFound("clientContact.in=" + UPDATED_CLIENT_CONTACT);
    }

    @Test
    @Transactional
    void getAllSamplesByClientContactIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientContact is not null
        defaultSampleShouldBeFound("clientContact.specified=true");

        // Get all the sampleList where clientContact is null
        defaultSampleShouldNotBeFound("clientContact.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByClientContactContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientContact contains DEFAULT_CLIENT_CONTACT
        defaultSampleShouldBeFound("clientContact.contains=" + DEFAULT_CLIENT_CONTACT);

        // Get all the sampleList where clientContact contains UPDATED_CLIENT_CONTACT
        defaultSampleShouldNotBeFound("clientContact.contains=" + UPDATED_CLIENT_CONTACT);
    }

    @Test
    @Transactional
    void getAllSamplesByClientContactNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where clientContact does not contain DEFAULT_CLIENT_CONTACT
        defaultSampleShouldNotBeFound("clientContact.doesNotContain=" + DEFAULT_CLIENT_CONTACT);

        // Get all the sampleList where clientContact does not contain UPDATED_CLIENT_CONTACT
        defaultSampleShouldBeFound("clientContact.doesNotContain=" + UPDATED_CLIENT_CONTACT);
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeId equals to DEFAULT_SAMPLE_TYPE_ID
        defaultSampleShouldBeFound("sampleTypeId.equals=" + DEFAULT_SAMPLE_TYPE_ID);

        // Get all the sampleList where sampleTypeId equals to UPDATED_SAMPLE_TYPE_ID
        defaultSampleShouldNotBeFound("sampleTypeId.equals=" + UPDATED_SAMPLE_TYPE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeIdIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeId in DEFAULT_SAMPLE_TYPE_ID or UPDATED_SAMPLE_TYPE_ID
        defaultSampleShouldBeFound("sampleTypeId.in=" + DEFAULT_SAMPLE_TYPE_ID + "," + UPDATED_SAMPLE_TYPE_ID);

        // Get all the sampleList where sampleTypeId equals to UPDATED_SAMPLE_TYPE_ID
        defaultSampleShouldNotBeFound("sampleTypeId.in=" + UPDATED_SAMPLE_TYPE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeId is not null
        defaultSampleShouldBeFound("sampleTypeId.specified=true");

        // Get all the sampleList where sampleTypeId is null
        defaultSampleShouldNotBeFound("sampleTypeId.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeIdContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeId contains DEFAULT_SAMPLE_TYPE_ID
        defaultSampleShouldBeFound("sampleTypeId.contains=" + DEFAULT_SAMPLE_TYPE_ID);

        // Get all the sampleList where sampleTypeId contains UPDATED_SAMPLE_TYPE_ID
        defaultSampleShouldNotBeFound("sampleTypeId.contains=" + UPDATED_SAMPLE_TYPE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeIdNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeId does not contain DEFAULT_SAMPLE_TYPE_ID
        defaultSampleShouldNotBeFound("sampleTypeId.doesNotContain=" + DEFAULT_SAMPLE_TYPE_ID);

        // Get all the sampleList where sampleTypeId does not contain UPDATED_SAMPLE_TYPE_ID
        defaultSampleShouldBeFound("sampleTypeId.doesNotContain=" + UPDATED_SAMPLE_TYPE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeName equals to DEFAULT_SAMPLE_TYPE_NAME
        defaultSampleShouldBeFound("sampleTypeName.equals=" + DEFAULT_SAMPLE_TYPE_NAME);

        // Get all the sampleList where sampleTypeName equals to UPDATED_SAMPLE_TYPE_NAME
        defaultSampleShouldNotBeFound("sampleTypeName.equals=" + UPDATED_SAMPLE_TYPE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeName in DEFAULT_SAMPLE_TYPE_NAME or UPDATED_SAMPLE_TYPE_NAME
        defaultSampleShouldBeFound("sampleTypeName.in=" + DEFAULT_SAMPLE_TYPE_NAME + "," + UPDATED_SAMPLE_TYPE_NAME);

        // Get all the sampleList where sampleTypeName equals to UPDATED_SAMPLE_TYPE_NAME
        defaultSampleShouldNotBeFound("sampleTypeName.in=" + UPDATED_SAMPLE_TYPE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeName is not null
        defaultSampleShouldBeFound("sampleTypeName.specified=true");

        // Get all the sampleList where sampleTypeName is null
        defaultSampleShouldNotBeFound("sampleTypeName.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeNameContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeName contains DEFAULT_SAMPLE_TYPE_NAME
        defaultSampleShouldBeFound("sampleTypeName.contains=" + DEFAULT_SAMPLE_TYPE_NAME);

        // Get all the sampleList where sampleTypeName contains UPDATED_SAMPLE_TYPE_NAME
        defaultSampleShouldNotBeFound("sampleTypeName.contains=" + UPDATED_SAMPLE_TYPE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplesBySampleTypeNameNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where sampleTypeName does not contain DEFAULT_SAMPLE_TYPE_NAME
        defaultSampleShouldNotBeFound("sampleTypeName.doesNotContain=" + DEFAULT_SAMPLE_TYPE_NAME);

        // Get all the sampleList where sampleTypeName does not contain UPDATED_SAMPLE_TYPE_NAME
        defaultSampleShouldBeFound("sampleTypeName.doesNotContain=" + UPDATED_SAMPLE_TYPE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceId equals to DEFAULT_ANALYSIS_SERVICE_ID
        defaultSampleShouldBeFound("analysisServiceId.equals=" + DEFAULT_ANALYSIS_SERVICE_ID);

        // Get all the sampleList where analysisServiceId equals to UPDATED_ANALYSIS_SERVICE_ID
        defaultSampleShouldNotBeFound("analysisServiceId.equals=" + UPDATED_ANALYSIS_SERVICE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceIdIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceId in DEFAULT_ANALYSIS_SERVICE_ID or UPDATED_ANALYSIS_SERVICE_ID
        defaultSampleShouldBeFound("analysisServiceId.in=" + DEFAULT_ANALYSIS_SERVICE_ID + "," + UPDATED_ANALYSIS_SERVICE_ID);

        // Get all the sampleList where analysisServiceId equals to UPDATED_ANALYSIS_SERVICE_ID
        defaultSampleShouldNotBeFound("analysisServiceId.in=" + UPDATED_ANALYSIS_SERVICE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceId is not null
        defaultSampleShouldBeFound("analysisServiceId.specified=true");

        // Get all the sampleList where analysisServiceId is null
        defaultSampleShouldNotBeFound("analysisServiceId.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceIdContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceId contains DEFAULT_ANALYSIS_SERVICE_ID
        defaultSampleShouldBeFound("analysisServiceId.contains=" + DEFAULT_ANALYSIS_SERVICE_ID);

        // Get all the sampleList where analysisServiceId contains UPDATED_ANALYSIS_SERVICE_ID
        defaultSampleShouldNotBeFound("analysisServiceId.contains=" + UPDATED_ANALYSIS_SERVICE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceIdNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceId does not contain DEFAULT_ANALYSIS_SERVICE_ID
        defaultSampleShouldNotBeFound("analysisServiceId.doesNotContain=" + DEFAULT_ANALYSIS_SERVICE_ID);

        // Get all the sampleList where analysisServiceId does not contain UPDATED_ANALYSIS_SERVICE_ID
        defaultSampleShouldBeFound("analysisServiceId.doesNotContain=" + UPDATED_ANALYSIS_SERVICE_ID);
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceName equals to DEFAULT_ANALYSIS_SERVICE_NAME
        defaultSampleShouldBeFound("analysisServiceName.equals=" + DEFAULT_ANALYSIS_SERVICE_NAME);

        // Get all the sampleList where analysisServiceName equals to UPDATED_ANALYSIS_SERVICE_NAME
        defaultSampleShouldNotBeFound("analysisServiceName.equals=" + UPDATED_ANALYSIS_SERVICE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceNameIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceName in DEFAULT_ANALYSIS_SERVICE_NAME or UPDATED_ANALYSIS_SERVICE_NAME
        defaultSampleShouldBeFound("analysisServiceName.in=" + DEFAULT_ANALYSIS_SERVICE_NAME + "," + UPDATED_ANALYSIS_SERVICE_NAME);

        // Get all the sampleList where analysisServiceName equals to UPDATED_ANALYSIS_SERVICE_NAME
        defaultSampleShouldNotBeFound("analysisServiceName.in=" + UPDATED_ANALYSIS_SERVICE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceName is not null
        defaultSampleShouldBeFound("analysisServiceName.specified=true");

        // Get all the sampleList where analysisServiceName is null
        defaultSampleShouldNotBeFound("analysisServiceName.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceNameContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceName contains DEFAULT_ANALYSIS_SERVICE_NAME
        defaultSampleShouldBeFound("analysisServiceName.contains=" + DEFAULT_ANALYSIS_SERVICE_NAME);

        // Get all the sampleList where analysisServiceName contains UPDATED_ANALYSIS_SERVICE_NAME
        defaultSampleShouldNotBeFound("analysisServiceName.contains=" + UPDATED_ANALYSIS_SERVICE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplesByAnalysisServiceNameNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where analysisServiceName does not contain DEFAULT_ANALYSIS_SERVICE_NAME
        defaultSampleShouldNotBeFound("analysisServiceName.doesNotContain=" + DEFAULT_ANALYSIS_SERVICE_NAME);

        // Get all the sampleList where analysisServiceName does not contain UPDATED_ANALYSIS_SERVICE_NAME
        defaultSampleShouldBeFound("analysisServiceName.doesNotContain=" + UPDATED_ANALYSIS_SERVICE_NAME);
    }

    @Test
    @Transactional
    void getAllSamplesByDateCollectedIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateCollected equals to DEFAULT_DATE_COLLECTED
        defaultSampleShouldBeFound("dateCollected.equals=" + DEFAULT_DATE_COLLECTED);

        // Get all the sampleList where dateCollected equals to UPDATED_DATE_COLLECTED
        defaultSampleShouldNotBeFound("dateCollected.equals=" + UPDATED_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateCollectedIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateCollected in DEFAULT_DATE_COLLECTED or UPDATED_DATE_COLLECTED
        defaultSampleShouldBeFound("dateCollected.in=" + DEFAULT_DATE_COLLECTED + "," + UPDATED_DATE_COLLECTED);

        // Get all the sampleList where dateCollected equals to UPDATED_DATE_COLLECTED
        defaultSampleShouldNotBeFound("dateCollected.in=" + UPDATED_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateCollectedIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateCollected is not null
        defaultSampleShouldBeFound("dateCollected.specified=true");

        // Get all the sampleList where dateCollected is null
        defaultSampleShouldNotBeFound("dateCollected.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByDateCollectedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateCollected is greater than or equal to DEFAULT_DATE_COLLECTED
        defaultSampleShouldBeFound("dateCollected.greaterThanOrEqual=" + DEFAULT_DATE_COLLECTED);

        // Get all the sampleList where dateCollected is greater than or equal to UPDATED_DATE_COLLECTED
        defaultSampleShouldNotBeFound("dateCollected.greaterThanOrEqual=" + UPDATED_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateCollectedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateCollected is less than or equal to DEFAULT_DATE_COLLECTED
        defaultSampleShouldBeFound("dateCollected.lessThanOrEqual=" + DEFAULT_DATE_COLLECTED);

        // Get all the sampleList where dateCollected is less than or equal to SMALLER_DATE_COLLECTED
        defaultSampleShouldNotBeFound("dateCollected.lessThanOrEqual=" + SMALLER_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateCollectedIsLessThanSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateCollected is less than DEFAULT_DATE_COLLECTED
        defaultSampleShouldNotBeFound("dateCollected.lessThan=" + DEFAULT_DATE_COLLECTED);

        // Get all the sampleList where dateCollected is less than UPDATED_DATE_COLLECTED
        defaultSampleShouldBeFound("dateCollected.lessThan=" + UPDATED_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateCollectedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateCollected is greater than DEFAULT_DATE_COLLECTED
        defaultSampleShouldNotBeFound("dateCollected.greaterThan=" + DEFAULT_DATE_COLLECTED);

        // Get all the sampleList where dateCollected is greater than SMALLER_DATE_COLLECTED
        defaultSampleShouldBeFound("dateCollected.greaterThan=" + SMALLER_DATE_COLLECTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateRegisteredIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateRegistered equals to DEFAULT_DATE_REGISTERED
        defaultSampleShouldBeFound("dateRegistered.equals=" + DEFAULT_DATE_REGISTERED);

        // Get all the sampleList where dateRegistered equals to UPDATED_DATE_REGISTERED
        defaultSampleShouldNotBeFound("dateRegistered.equals=" + UPDATED_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateRegisteredIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateRegistered in DEFAULT_DATE_REGISTERED or UPDATED_DATE_REGISTERED
        defaultSampleShouldBeFound("dateRegistered.in=" + DEFAULT_DATE_REGISTERED + "," + UPDATED_DATE_REGISTERED);

        // Get all the sampleList where dateRegistered equals to UPDATED_DATE_REGISTERED
        defaultSampleShouldNotBeFound("dateRegistered.in=" + UPDATED_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateRegisteredIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateRegistered is not null
        defaultSampleShouldBeFound("dateRegistered.specified=true");

        // Get all the sampleList where dateRegistered is null
        defaultSampleShouldNotBeFound("dateRegistered.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByDateRegisteredIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateRegistered is greater than or equal to DEFAULT_DATE_REGISTERED
        defaultSampleShouldBeFound("dateRegistered.greaterThanOrEqual=" + DEFAULT_DATE_REGISTERED);

        // Get all the sampleList where dateRegistered is greater than or equal to UPDATED_DATE_REGISTERED
        defaultSampleShouldNotBeFound("dateRegistered.greaterThanOrEqual=" + UPDATED_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateRegisteredIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateRegistered is less than or equal to DEFAULT_DATE_REGISTERED
        defaultSampleShouldBeFound("dateRegistered.lessThanOrEqual=" + DEFAULT_DATE_REGISTERED);

        // Get all the sampleList where dateRegistered is less than or equal to SMALLER_DATE_REGISTERED
        defaultSampleShouldNotBeFound("dateRegistered.lessThanOrEqual=" + SMALLER_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateRegisteredIsLessThanSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateRegistered is less than DEFAULT_DATE_REGISTERED
        defaultSampleShouldNotBeFound("dateRegistered.lessThan=" + DEFAULT_DATE_REGISTERED);

        // Get all the sampleList where dateRegistered is less than UPDATED_DATE_REGISTERED
        defaultSampleShouldBeFound("dateRegistered.lessThan=" + UPDATED_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateRegisteredIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateRegistered is greater than DEFAULT_DATE_REGISTERED
        defaultSampleShouldNotBeFound("dateRegistered.greaterThan=" + DEFAULT_DATE_REGISTERED);

        // Get all the sampleList where dateRegistered is greater than SMALLER_DATE_REGISTERED
        defaultSampleShouldBeFound("dateRegistered.greaterThan=" + SMALLER_DATE_REGISTERED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateTestedIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateTested equals to DEFAULT_DATE_TESTED
        defaultSampleShouldBeFound("dateTested.equals=" + DEFAULT_DATE_TESTED);

        // Get all the sampleList where dateTested equals to UPDATED_DATE_TESTED
        defaultSampleShouldNotBeFound("dateTested.equals=" + UPDATED_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateTestedIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateTested in DEFAULT_DATE_TESTED or UPDATED_DATE_TESTED
        defaultSampleShouldBeFound("dateTested.in=" + DEFAULT_DATE_TESTED + "," + UPDATED_DATE_TESTED);

        // Get all the sampleList where dateTested equals to UPDATED_DATE_TESTED
        defaultSampleShouldNotBeFound("dateTested.in=" + UPDATED_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateTestedIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateTested is not null
        defaultSampleShouldBeFound("dateTested.specified=true");

        // Get all the sampleList where dateTested is null
        defaultSampleShouldNotBeFound("dateTested.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByDateTestedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateTested is greater than or equal to DEFAULT_DATE_TESTED
        defaultSampleShouldBeFound("dateTested.greaterThanOrEqual=" + DEFAULT_DATE_TESTED);

        // Get all the sampleList where dateTested is greater than or equal to UPDATED_DATE_TESTED
        defaultSampleShouldNotBeFound("dateTested.greaterThanOrEqual=" + UPDATED_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateTestedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateTested is less than or equal to DEFAULT_DATE_TESTED
        defaultSampleShouldBeFound("dateTested.lessThanOrEqual=" + DEFAULT_DATE_TESTED);

        // Get all the sampleList where dateTested is less than or equal to SMALLER_DATE_TESTED
        defaultSampleShouldNotBeFound("dateTested.lessThanOrEqual=" + SMALLER_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateTestedIsLessThanSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateTested is less than DEFAULT_DATE_TESTED
        defaultSampleShouldNotBeFound("dateTested.lessThan=" + DEFAULT_DATE_TESTED);

        // Get all the sampleList where dateTested is less than UPDATED_DATE_TESTED
        defaultSampleShouldBeFound("dateTested.lessThan=" + UPDATED_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplesByDateTestedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where dateTested is greater than DEFAULT_DATE_TESTED
        defaultSampleShouldNotBeFound("dateTested.greaterThan=" + DEFAULT_DATE_TESTED);

        // Get all the sampleList where dateTested is greater than SMALLER_DATE_TESTED
        defaultSampleShouldBeFound("dateTested.greaterThan=" + SMALLER_DATE_TESTED);
    }

    @Test
    @Transactional
    void getAllSamplesByResultIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where result equals to DEFAULT_RESULT
        defaultSampleShouldBeFound("result.equals=" + DEFAULT_RESULT);

        // Get all the sampleList where result equals to UPDATED_RESULT
        defaultSampleShouldNotBeFound("result.equals=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    void getAllSamplesByResultIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where result in DEFAULT_RESULT or UPDATED_RESULT
        defaultSampleShouldBeFound("result.in=" + DEFAULT_RESULT + "," + UPDATED_RESULT);

        // Get all the sampleList where result equals to UPDATED_RESULT
        defaultSampleShouldNotBeFound("result.in=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    void getAllSamplesByResultIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where result is not null
        defaultSampleShouldBeFound("result.specified=true");

        // Get all the sampleList where result is null
        defaultSampleShouldNotBeFound("result.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByResultContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where result contains DEFAULT_RESULT
        defaultSampleShouldBeFound("result.contains=" + DEFAULT_RESULT);

        // Get all the sampleList where result contains UPDATED_RESULT
        defaultSampleShouldNotBeFound("result.contains=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    void getAllSamplesByResultNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where result does not contain DEFAULT_RESULT
        defaultSampleShouldNotBeFound("result.doesNotContain=" + DEFAULT_RESULT);

        // Get all the sampleList where result does not contain UPDATED_RESULT
        defaultSampleShouldBeFound("result.doesNotContain=" + UPDATED_RESULT);
    }

    @Test
    @Transactional
    void getAllSamplesByUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where unit equals to DEFAULT_UNIT
        defaultSampleShouldBeFound("unit.equals=" + DEFAULT_UNIT);

        // Get all the sampleList where unit equals to UPDATED_UNIT
        defaultSampleShouldNotBeFound("unit.equals=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllSamplesByUnitIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where unit in DEFAULT_UNIT or UPDATED_UNIT
        defaultSampleShouldBeFound("unit.in=" + DEFAULT_UNIT + "," + UPDATED_UNIT);

        // Get all the sampleList where unit equals to UPDATED_UNIT
        defaultSampleShouldNotBeFound("unit.in=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllSamplesByUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where unit is not null
        defaultSampleShouldBeFound("unit.specified=true");

        // Get all the sampleList where unit is null
        defaultSampleShouldNotBeFound("unit.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByUnitContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where unit contains DEFAULT_UNIT
        defaultSampleShouldBeFound("unit.contains=" + DEFAULT_UNIT);

        // Get all the sampleList where unit contains UPDATED_UNIT
        defaultSampleShouldNotBeFound("unit.contains=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllSamplesByUnitNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where unit does not contain DEFAULT_UNIT
        defaultSampleShouldNotBeFound("unit.doesNotContain=" + DEFAULT_UNIT);

        // Get all the sampleList where unit does not contain UPDATED_UNIT
        defaultSampleShouldBeFound("unit.doesNotContain=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllSamplesByDatePublishedIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where datePublished equals to DEFAULT_DATE_PUBLISHED
        defaultSampleShouldBeFound("datePublished.equals=" + DEFAULT_DATE_PUBLISHED);

        // Get all the sampleList where datePublished equals to UPDATED_DATE_PUBLISHED
        defaultSampleShouldNotBeFound("datePublished.equals=" + UPDATED_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplesByDatePublishedIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where datePublished in DEFAULT_DATE_PUBLISHED or UPDATED_DATE_PUBLISHED
        defaultSampleShouldBeFound("datePublished.in=" + DEFAULT_DATE_PUBLISHED + "," + UPDATED_DATE_PUBLISHED);

        // Get all the sampleList where datePublished equals to UPDATED_DATE_PUBLISHED
        defaultSampleShouldNotBeFound("datePublished.in=" + UPDATED_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplesByDatePublishedIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where datePublished is not null
        defaultSampleShouldBeFound("datePublished.specified=true");

        // Get all the sampleList where datePublished is null
        defaultSampleShouldNotBeFound("datePublished.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByDatePublishedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where datePublished is greater than or equal to DEFAULT_DATE_PUBLISHED
        defaultSampleShouldBeFound("datePublished.greaterThanOrEqual=" + DEFAULT_DATE_PUBLISHED);

        // Get all the sampleList where datePublished is greater than or equal to UPDATED_DATE_PUBLISHED
        defaultSampleShouldNotBeFound("datePublished.greaterThanOrEqual=" + UPDATED_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplesByDatePublishedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where datePublished is less than or equal to DEFAULT_DATE_PUBLISHED
        defaultSampleShouldBeFound("datePublished.lessThanOrEqual=" + DEFAULT_DATE_PUBLISHED);

        // Get all the sampleList where datePublished is less than or equal to SMALLER_DATE_PUBLISHED
        defaultSampleShouldNotBeFound("datePublished.lessThanOrEqual=" + SMALLER_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplesByDatePublishedIsLessThanSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where datePublished is less than DEFAULT_DATE_PUBLISHED
        defaultSampleShouldNotBeFound("datePublished.lessThan=" + DEFAULT_DATE_PUBLISHED);

        // Get all the sampleList where datePublished is less than UPDATED_DATE_PUBLISHED
        defaultSampleShouldBeFound("datePublished.lessThan=" + UPDATED_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplesByDatePublishedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where datePublished is greater than DEFAULT_DATE_PUBLISHED
        defaultSampleShouldNotBeFound("datePublished.greaterThan=" + DEFAULT_DATE_PUBLISHED);

        // Get all the sampleList where datePublished is greater than SMALLER_DATE_PUBLISHED
        defaultSampleShouldBeFound("datePublished.greaterThan=" + SMALLER_DATE_PUBLISHED);
    }

    @Test
    @Transactional
    void getAllSamplesByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where state equals to DEFAULT_STATE
        defaultSampleShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the sampleList where state equals to UPDATED_STATE
        defaultSampleShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllSamplesByStateIsInShouldWork() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where state in DEFAULT_STATE or UPDATED_STATE
        defaultSampleShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the sampleList where state equals to UPDATED_STATE
        defaultSampleShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllSamplesByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where state is not null
        defaultSampleShouldBeFound("state.specified=true");

        // Get all the sampleList where state is null
        defaultSampleShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllSamplesByStateContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where state contains DEFAULT_STATE
        defaultSampleShouldBeFound("state.contains=" + DEFAULT_STATE);

        // Get all the sampleList where state contains UPDATED_STATE
        defaultSampleShouldNotBeFound("state.contains=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllSamplesByStateNotContainsSomething() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        // Get all the sampleList where state does not contain DEFAULT_STATE
        defaultSampleShouldNotBeFound("state.doesNotContain=" + DEFAULT_STATE);

        // Get all the sampleList where state does not contain UPDATED_STATE
        defaultSampleShouldBeFound("state.doesNotContain=" + UPDATED_STATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSampleShouldBeFound(String filter) throws Exception {
        restSampleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sample.getId().intValue())))
            .andExpect(jsonPath("$.[*].patientId").value(hasItem(DEFAULT_PATIENT_ID)))
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
        restSampleMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSampleShouldNotBeFound(String filter) throws Exception {
        restSampleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSampleMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingSample() throws Exception {
        // Get the sample
        restSampleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSample() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();

        // Update the sample
        Sample updatedSample = sampleRepository.findById(sample.getId()).get();
        // Disconnect from session so that the updates on updatedSample are not directly saved in db
        em.detach(updatedSample);
        updatedSample
            .patientId(UPDATED_PATIENT_ID)
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

        restSampleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSample.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSample))
            )
            .andExpect(status().isOk());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
        Sample testSample = sampleList.get(sampleList.size() - 1);
        assertThat(testSample.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSample.getClientSampleId()).isEqualTo(UPDATED_CLIENT_SAMPLE_ID);
        assertThat(testSample.getClientContact()).isEqualTo(UPDATED_CLIENT_CONTACT);
        assertThat(testSample.getSampleTypeId()).isEqualTo(UPDATED_SAMPLE_TYPE_ID);
        assertThat(testSample.getSampleTypeName()).isEqualTo(UPDATED_SAMPLE_TYPE_NAME);
        assertThat(testSample.getAnalysisServiceId()).isEqualTo(UPDATED_ANALYSIS_SERVICE_ID);
        assertThat(testSample.getAnalysisServiceName()).isEqualTo(UPDATED_ANALYSIS_SERVICE_NAME);
        assertThat(testSample.getDateCollected()).isEqualTo(UPDATED_DATE_COLLECTED);
        assertThat(testSample.getDateRegistered()).isEqualTo(UPDATED_DATE_REGISTERED);
        assertThat(testSample.getDateTested()).isEqualTo(UPDATED_DATE_TESTED);
        assertThat(testSample.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testSample.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testSample.getDatePublished()).isEqualTo(UPDATED_DATE_PUBLISHED);
        assertThat(testSample.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void putNonExistingSample() throws Exception {
        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();
        sample.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSampleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sample.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sample))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSample() throws Exception {
        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();
        sample.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSampleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sample))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSample() throws Exception {
        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();
        sample.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSampleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sample)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSampleWithPatch() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();

        // Update the sample using partial update
        Sample partialUpdatedSample = new Sample();
        partialUpdatedSample.setId(sample.getId());

        partialUpdatedSample
            .clientContact(UPDATED_CLIENT_CONTACT)
            .sampleTypeId(UPDATED_SAMPLE_TYPE_ID)
            .sampleTypeName(UPDATED_SAMPLE_TYPE_NAME)
            .analysisServiceId(UPDATED_ANALYSIS_SERVICE_ID)
            .datePublished(UPDATED_DATE_PUBLISHED)
            .state(UPDATED_STATE);

        restSampleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSample.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSample))
            )
            .andExpect(status().isOk());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
        Sample testSample = sampleList.get(sampleList.size() - 1);
        assertThat(testSample.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testSample.getClientSampleId()).isEqualTo(DEFAULT_CLIENT_SAMPLE_ID);
        assertThat(testSample.getClientContact()).isEqualTo(UPDATED_CLIENT_CONTACT);
        assertThat(testSample.getSampleTypeId()).isEqualTo(UPDATED_SAMPLE_TYPE_ID);
        assertThat(testSample.getSampleTypeName()).isEqualTo(UPDATED_SAMPLE_TYPE_NAME);
        assertThat(testSample.getAnalysisServiceId()).isEqualTo(UPDATED_ANALYSIS_SERVICE_ID);
        assertThat(testSample.getAnalysisServiceName()).isEqualTo(DEFAULT_ANALYSIS_SERVICE_NAME);
        assertThat(testSample.getDateCollected()).isEqualTo(DEFAULT_DATE_COLLECTED);
        assertThat(testSample.getDateRegistered()).isEqualTo(DEFAULT_DATE_REGISTERED);
        assertThat(testSample.getDateTested()).isEqualTo(DEFAULT_DATE_TESTED);
        assertThat(testSample.getResult()).isEqualTo(DEFAULT_RESULT);
        assertThat(testSample.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testSample.getDatePublished()).isEqualTo(UPDATED_DATE_PUBLISHED);
        assertThat(testSample.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void fullUpdateSampleWithPatch() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();

        // Update the sample using partial update
        Sample partialUpdatedSample = new Sample();
        partialUpdatedSample.setId(sample.getId());

        partialUpdatedSample
            .patientId(UPDATED_PATIENT_ID)
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

        restSampleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSample.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSample))
            )
            .andExpect(status().isOk());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
        Sample testSample = sampleList.get(sampleList.size() - 1);
        assertThat(testSample.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSample.getClientSampleId()).isEqualTo(UPDATED_CLIENT_SAMPLE_ID);
        assertThat(testSample.getClientContact()).isEqualTo(UPDATED_CLIENT_CONTACT);
        assertThat(testSample.getSampleTypeId()).isEqualTo(UPDATED_SAMPLE_TYPE_ID);
        assertThat(testSample.getSampleTypeName()).isEqualTo(UPDATED_SAMPLE_TYPE_NAME);
        assertThat(testSample.getAnalysisServiceId()).isEqualTo(UPDATED_ANALYSIS_SERVICE_ID);
        assertThat(testSample.getAnalysisServiceName()).isEqualTo(UPDATED_ANALYSIS_SERVICE_NAME);
        assertThat(testSample.getDateCollected()).isEqualTo(UPDATED_DATE_COLLECTED);
        assertThat(testSample.getDateRegistered()).isEqualTo(UPDATED_DATE_REGISTERED);
        assertThat(testSample.getDateTested()).isEqualTo(UPDATED_DATE_TESTED);
        assertThat(testSample.getResult()).isEqualTo(UPDATED_RESULT);
        assertThat(testSample.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testSample.getDatePublished()).isEqualTo(UPDATED_DATE_PUBLISHED);
        assertThat(testSample.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void patchNonExistingSample() throws Exception {
        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();
        sample.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSampleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sample.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sample))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSample() throws Exception {
        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();
        sample.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSampleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sample))
            )
            .andExpect(status().isBadRequest());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSample() throws Exception {
        int databaseSizeBeforeUpdate = sampleRepository.findAll().size();
        sample.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSampleMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sample)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Sample in the database
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSample() throws Exception {
        // Initialize the database
        sampleRepository.saveAndFlush(sample);

        int databaseSizeBeforeDelete = sampleRepository.findAll().size();

        // Delete the sample
        restSampleMockMvc
            .perform(delete(ENTITY_API_URL_ID, sample.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
