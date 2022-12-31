package zw.org.nmrl.poc.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zw.org.nmrl.poc.device.domain.IdServer;

/**
 * Spring Data repository for the IdServer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdServerRepository extends JpaRepository<IdServer, Long> {
    IdServer findByPrefixIgnoreCase(String prefix);
    //    @Query("INSERT INTO idserver (prefix, number) VALUES(:prefix, 1) ON CONFLICT (prefix) DO UPDATE SET number = idserver.number + 1 RETURNING idserver.number")
    //    int getNextIdSequence(@Param("prefix") String prefix);
}
