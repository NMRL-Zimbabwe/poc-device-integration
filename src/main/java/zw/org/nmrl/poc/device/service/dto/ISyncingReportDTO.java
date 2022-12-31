package zw.org.nmrl.poc.device.service.dto;

public interface ISyncingReportDTO {
	
	Long getTotalReceived();
	
	Long getTotalSynced();

	Long getMonth();

	Long getError();
	
	Long getSubscriber();

}
