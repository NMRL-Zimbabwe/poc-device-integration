package zw.org.nmrl.poc.device.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import zw.org.nmrl.poc.device.domain.AnalysisRequest;
import zw.org.nmrl.poc.device.domain.id.AnalysisRequestId;

/**
 * Spring Data JPA repository for the Sample entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnalysisRequestRepository extends JpaRepository<AnalysisRequest, String>, JpaSpecificationExecutor<AnalysisRequest> {

	List<AnalysisRequest> findBySyncIsNullAndRetryLessThan(int retry);

	Optional<AnalysisRequest> findByAnalysisRequestId(String analysisRequestId);
}
