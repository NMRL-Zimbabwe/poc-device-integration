package zw.org.nmrl.poc.device.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import zw.org.nmrl.poc.device.domain.Patient;
import zw.org.nmrl.poc.device.domain.id.PatientId;

/**
 * Spring Data JPA repository for the Patient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientRepository extends JpaRepository<Patient, PatientId>, JpaSpecificationExecutor<Patient> {

	Optional<Patient> findByPatientIdAndPatientUidAndRetryLessThan(String patientId, String patientUid, int retry);

	Optional<Patient> findByPatientId(String patientId);
}
