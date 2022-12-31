package zw.org.nmrl.poc.device.service;

import java.util.List;

import zw.org.nmrl.poc.device.service.dto.ISyncingReportDTO;

public interface DashboardService {

	List<ISyncingReportDTO> getMonlthyStatistics();

}
