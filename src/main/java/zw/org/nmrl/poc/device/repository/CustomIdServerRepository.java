package zw.org.nmrl.poc.device.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the LaboratoryRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomIdServerRepository {
    List findNextIdSequence(String prefix);
}
