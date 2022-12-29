package zw.org.nmrl.poc.device.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zw.org.nmrl.poc.device.domain.Analysis;
import zw.org.nmrl.poc.device.domain.id.AnalysisId;

/**
 * Spring Data repository for the Analysis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, AnalysisId> {
    List<Analysis> findTop8ByWorksheetNumberIsNull();

    Page<Analysis> findAllByAnalysisIdAnalysisUid(Pageable pageable, String laboratoryRequestId);

    Page<Analysis> findAllByWorksheetNumber(Pageable pageable, String worksheetNumber);

    List<Analysis> findByAnalysisRequestIdAndAnalysisRequestUid(String analysisRequestId, String analysisRequestUid);

    Page<Analysis> findAllByAnalysisRequestIdAndAnalysisRequestUid(Pageable pageable, String analysisRequestId, String analysisRequestUid);
}
