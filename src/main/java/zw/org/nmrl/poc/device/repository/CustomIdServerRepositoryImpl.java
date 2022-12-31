package zw.org.nmrl.poc.device.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the LaboratoryRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public class CustomIdServerRepositoryImpl implements CustomIdServerRepository {
    private final Logger log = LoggerFactory.getLogger(CustomIdServerRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List findNextIdSequence(String prefixValue) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        String upsertStatement =
            "INSERT INTO idserver (prefix, number) " +
            "VALUES (:prefix, 1) " +
            "ON CONFLICT (prefix) " +
            "DO UPDATE SET number = idserver.number + 1 " +
            "RETURNING idserver.number";

        List results = entityManager.createNativeQuery(upsertStatement).setParameter("prefix", prefixValue).getResultList();

        log.debug(
            String.format(
                "getFirstResult is {}",
                entityManager.createNativeQuery(upsertStatement).setParameter("prefix", prefixValue).getFirstResult()
            )
        );
        log.debug(
            String.format(
                "getResultList is {}",
                entityManager.createNativeQuery(upsertStatement).setParameter("prefix", prefixValue).getResultList()
            )
        );

        return new ArrayList();
    }
}
