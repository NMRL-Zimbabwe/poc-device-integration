package zw.org.nmrl.poc.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.org.nmrl.poc.device.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
