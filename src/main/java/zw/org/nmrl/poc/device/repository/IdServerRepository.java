package zw.org.nmrl.poc.device.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import zw.org.nmrl.poc.device.domain.IdServer;
import zw.org.nmrl.poc.device.service.dto.ISyncingReportDTO;

/**
 * Spring Data repository for the IdServer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdServerRepository extends JpaRepository<IdServer, Long> {
	IdServer findByPrefixIgnoreCase(String prefix);

	/*
	 * @Query("INSERT INTO idserver (prefix, number) VALUES(:prefix, 1) ON CONFLICT (prefix) DO UPDATE SET number = idserver.number + 1 RETURNING idserver.number"
	 * ) int getNextIdSequence(@Param("prefix") String prefix);
	 */

	/*
	 * SELECT count(*) as total_received, date_part('month',created_date) as month,
	 * count(sync) as total_synced, count(case when error_reason is not null then
	 * error_reason end) as error, count(DISTINCT case when source is not null then
	 * source end) as subscriber FROM public.analysis_request group by month,
	 * error_reason, source
	 */
	
	// Global target A: Substantially reduce global disaster mortality by 2030, aiming to lower average per 100,000 global mortality between 2020-2030 compared with 2005-2015.
	@Query(value = "SELECT count(*) as totalReceived, date_part('month',created_date) as month, count(sync) as totalSynced,\n"
			+ "count(case when error_reason is not null then error_reason\n"
			+ "end) as error,\n"
			+ "count(DISTINCT case when source is not null then source\n"
			+ "end) as subscriber\n"
			+ "FROM public.analysis_request\n"
			+ "group by month, error_reason, source", nativeQuery = true)
	List<ISyncingReportDTO> getSyncingStatsByMonth();
}
