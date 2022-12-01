package zw.org.nmrl.poc.device.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import zw.org.nmrl.poc.device.domain.SampleType;

/**
 * Spring Data JPA repository for the SampleType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SampleTypeRepository extends JpaRepository<SampleType, Long>, JpaSpecificationExecutor<SampleType> {}
