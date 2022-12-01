package zw.org.nmrl.poc.device.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import zw.org.nmrl.poc.device.domain.AnalysisService;
import zw.org.nmrl.poc.device.repository.AnalysisServiceRepository;
import zw.org.nmrl.poc.device.service.criteria.AnalysisServiceCriteria;

/**
 * Integration tests for the {@link AnalysisServiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnalysisServiceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/analysis-services";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnalysisServiceRepository analysisServiceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnalysisServiceMockMvc;

    private AnalysisService analysisService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnalysisService createEntity(EntityManager em) {
        AnalysisService analysisService = new AnalysisService().name(DEFAULT_NAME).unit(DEFAULT_UNIT);
        return analysisService;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnalysisService createUpdatedEntity(EntityManager em) {
        AnalysisService analysisService = new AnalysisService().name(UPDATED_NAME).unit(UPDATED_UNIT);
        return analysisService;
    }

    @BeforeEach
    public void initTest() {
        analysisService = createEntity(em);
    }

    @Test
    @Transactional
    void createAnalysisService() throws Exception {
        int databaseSizeBeforeCreate = analysisServiceRepository.findAll().size();
        // Create the AnalysisService
        restAnalysisServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(analysisService))
            )
            .andExpect(status().isCreated());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeCreate + 1);
        AnalysisService testAnalysisService = analysisServiceList.get(analysisServiceList.size() - 1);
        assertThat(testAnalysisService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAnalysisService.getUnit()).isEqualTo(DEFAULT_UNIT);
    }

    @Test
    @Transactional
    void createAnalysisServiceWithExistingId() throws Exception {
        // Create the AnalysisService with an existing ID
        analysisService.setId(1L);

        int databaseSizeBeforeCreate = analysisServiceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnalysisServiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(analysisService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAnalysisServices() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList
        restAnalysisServiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(analysisService.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)));
    }

    @Test
    @Transactional
    void getAnalysisService() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get the analysisService
        restAnalysisServiceMockMvc
            .perform(get(ENTITY_API_URL_ID, analysisService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(analysisService.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT));
    }

    @Test
    @Transactional
    void getAnalysisServicesByIdFiltering() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        Long id = analysisService.getId();

        defaultAnalysisServiceShouldBeFound("id.equals=" + id);
        defaultAnalysisServiceShouldNotBeFound("id.notEquals=" + id);

        defaultAnalysisServiceShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAnalysisServiceShouldNotBeFound("id.greaterThan=" + id);

        defaultAnalysisServiceShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAnalysisServiceShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where name equals to DEFAULT_NAME
        defaultAnalysisServiceShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the analysisServiceList where name equals to UPDATED_NAME
        defaultAnalysisServiceShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where name in DEFAULT_NAME or UPDATED_NAME
        defaultAnalysisServiceShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the analysisServiceList where name equals to UPDATED_NAME
        defaultAnalysisServiceShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where name is not null
        defaultAnalysisServiceShouldBeFound("name.specified=true");

        // Get all the analysisServiceList where name is null
        defaultAnalysisServiceShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByNameContainsSomething() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where name contains DEFAULT_NAME
        defaultAnalysisServiceShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the analysisServiceList where name contains UPDATED_NAME
        defaultAnalysisServiceShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where name does not contain DEFAULT_NAME
        defaultAnalysisServiceShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the analysisServiceList where name does not contain UPDATED_NAME
        defaultAnalysisServiceShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where unit equals to DEFAULT_UNIT
        defaultAnalysisServiceShouldBeFound("unit.equals=" + DEFAULT_UNIT);

        // Get all the analysisServiceList where unit equals to UPDATED_UNIT
        defaultAnalysisServiceShouldNotBeFound("unit.equals=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByUnitIsInShouldWork() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where unit in DEFAULT_UNIT or UPDATED_UNIT
        defaultAnalysisServiceShouldBeFound("unit.in=" + DEFAULT_UNIT + "," + UPDATED_UNIT);

        // Get all the analysisServiceList where unit equals to UPDATED_UNIT
        defaultAnalysisServiceShouldNotBeFound("unit.in=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where unit is not null
        defaultAnalysisServiceShouldBeFound("unit.specified=true");

        // Get all the analysisServiceList where unit is null
        defaultAnalysisServiceShouldNotBeFound("unit.specified=false");
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByUnitContainsSomething() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where unit contains DEFAULT_UNIT
        defaultAnalysisServiceShouldBeFound("unit.contains=" + DEFAULT_UNIT);

        // Get all the analysisServiceList where unit contains UPDATED_UNIT
        defaultAnalysisServiceShouldNotBeFound("unit.contains=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllAnalysisServicesByUnitNotContainsSomething() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        // Get all the analysisServiceList where unit does not contain DEFAULT_UNIT
        defaultAnalysisServiceShouldNotBeFound("unit.doesNotContain=" + DEFAULT_UNIT);

        // Get all the analysisServiceList where unit does not contain UPDATED_UNIT
        defaultAnalysisServiceShouldBeFound("unit.doesNotContain=" + UPDATED_UNIT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAnalysisServiceShouldBeFound(String filter) throws Exception {
        restAnalysisServiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(analysisService.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)));

        // Check, that the count call also returns 1
        restAnalysisServiceMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAnalysisServiceShouldNotBeFound(String filter) throws Exception {
        restAnalysisServiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAnalysisServiceMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingAnalysisService() throws Exception {
        // Get the analysisService
        restAnalysisServiceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAnalysisService() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();

        // Update the analysisService
        AnalysisService updatedAnalysisService = analysisServiceRepository.findById(analysisService.getId()).get();
        // Disconnect from session so that the updates on updatedAnalysisService are not directly saved in db
        em.detach(updatedAnalysisService);
        updatedAnalysisService.name(UPDATED_NAME).unit(UPDATED_UNIT);

        restAnalysisServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAnalysisService.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAnalysisService))
            )
            .andExpect(status().isOk());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
        AnalysisService testAnalysisService = analysisServiceList.get(analysisServiceList.size() - 1);
        assertThat(testAnalysisService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAnalysisService.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    void putNonExistingAnalysisService() throws Exception {
        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();
        analysisService.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnalysisServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, analysisService.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(analysisService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnalysisService() throws Exception {
        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();
        analysisService.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnalysisServiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(analysisService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnalysisService() throws Exception {
        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();
        analysisService.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnalysisServiceMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(analysisService))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnalysisServiceWithPatch() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();

        // Update the analysisService using partial update
        AnalysisService partialUpdatedAnalysisService = new AnalysisService();
        partialUpdatedAnalysisService.setId(analysisService.getId());

        partialUpdatedAnalysisService.name(UPDATED_NAME).unit(UPDATED_UNIT);

        restAnalysisServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnalysisService.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnalysisService))
            )
            .andExpect(status().isOk());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
        AnalysisService testAnalysisService = analysisServiceList.get(analysisServiceList.size() - 1);
        assertThat(testAnalysisService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAnalysisService.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    void fullUpdateAnalysisServiceWithPatch() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();

        // Update the analysisService using partial update
        AnalysisService partialUpdatedAnalysisService = new AnalysisService();
        partialUpdatedAnalysisService.setId(analysisService.getId());

        partialUpdatedAnalysisService.name(UPDATED_NAME).unit(UPDATED_UNIT);

        restAnalysisServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnalysisService.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnalysisService))
            )
            .andExpect(status().isOk());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
        AnalysisService testAnalysisService = analysisServiceList.get(analysisServiceList.size() - 1);
        assertThat(testAnalysisService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAnalysisService.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    void patchNonExistingAnalysisService() throws Exception {
        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();
        analysisService.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnalysisServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, analysisService.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(analysisService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnalysisService() throws Exception {
        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();
        analysisService.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnalysisServiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(analysisService))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnalysisService() throws Exception {
        int databaseSizeBeforeUpdate = analysisServiceRepository.findAll().size();
        analysisService.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnalysisServiceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(analysisService))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnalysisService in the database
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnalysisService() throws Exception {
        // Initialize the database
        analysisServiceRepository.saveAndFlush(analysisService);

        int databaseSizeBeforeDelete = analysisServiceRepository.findAll().size();

        // Delete the analysisService
        restAnalysisServiceMockMvc
            .perform(delete(ENTITY_API_URL_ID, analysisService.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnalysisService> analysisServiceList = analysisServiceRepository.findAll();
        assertThat(analysisServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
