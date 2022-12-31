package zw.org.nmrl.poc.device.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.jhipster.web.util.PaginationUtil;
import zw.org.nmrl.poc.device.domain.IdServer;
import zw.org.nmrl.poc.device.repository.CustomIdServerRepository;
import zw.org.nmrl.poc.device.service.DashboardService;
import zw.org.nmrl.poc.device.service.IdServerService;
import zw.org.nmrl.poc.device.service.dto.ISyncingReportDTO;

/**
 * REST controller for managing {@link zw.org.downtime.domain.IdServer}.
 */
@RestController
@RequestMapping("/api")
public class DashboardStatistic {
    private final Logger log = LoggerFactory.getLogger(DashboardStatistic.class);

    private static final String ENTITY_NAME = "idServer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IdServerService idServerService;

    private final CustomIdServerRepository customIdServerRepository;
    
    private final DashboardService dashboardService;

    public DashboardStatistic(IdServerService idServerService, CustomIdServerRepository customIdServerRepository, DashboardService dashboardService) {
        this.idServerService = idServerService;
        this.customIdServerRepository = customIdServerRepository;
        this.dashboardService = dashboardService;
    }

  

    /**
     * {@code GET  /id-servers} : get all the idServers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of idServers in body.
     */
    @GetMapping("/dashboard-statistics")
    public List<ISyncingReportDTO> getAllIdServers() {
       
        return dashboardService.getMonlthyStatistics();
    }

   
}
