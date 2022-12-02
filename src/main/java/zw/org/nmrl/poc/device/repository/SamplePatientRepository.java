package zw.org.nmrl.poc.device.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import zw.org.nmrl.poc.device.domain.SamplePatient;

/**
 * Spring Data JPA repository for the SamplePatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SamplePatientRepository extends JpaRepository<SamplePatient, String>, JpaSpecificationExecutor<SamplePatient> {
    SamplePatient findByExternalUniqueIdentifer(String externalUniqueIdentifer);
}
