package zw.org.nmrl.poc.device.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zw.org.nmrl.poc.device.repository.IdServerRepository;
import zw.org.nmrl.poc.device.service.DashboardService;
import zw.org.nmrl.poc.device.service.dto.ISyncingReportDTO;

@Service
public class DashboardServiceImpl implements DashboardService {

	private final Logger log = LoggerFactory.getLogger(DashboardServiceImpl.class);

	private final IdServerRepository idServerRepository;

	public DashboardServiceImpl(IdServerRepository idServerRepository) {
		this.idServerRepository = idServerRepository;

	}

	@Override
	public List<ISyncingReportDTO> getMonlthyStatistics() {

		log.debug("request to get monthly statistics::::");

		return idServerRepository.getSyncingStatsByMonth();
	}

}
