package zw.org.nmrl.poc.device.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zw.org.nmrl.poc.device.domain.IdServer;
import zw.org.nmrl.poc.device.repository.IdServerRepository;
import zw.org.nmrl.poc.device.service.IdServerService;

/**
 * Service Implementation for managing {@link IdServer}.
 */
@Service
@Transactional
public class IdServerServiceImpl implements IdServerService {
    private final Logger log = LoggerFactory.getLogger(IdServerServiceImpl.class);

    private final IdServerRepository idServerRepository;

    public IdServerServiceImpl(IdServerRepository idServerRepository) {
        this.idServerRepository = idServerRepository;
    }

    @Override
    public IdServer save(IdServer idServer) {
        log.debug("Request to save IdServer : {}", idServer);
        return idServerRepository.save(idServer);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IdServer> findAll(Pageable pageable) {
        log.debug("Request to get all IdServers");
        return idServerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IdServer> findOne(Long id) {
        log.debug("Request to get IdServer : {}", id);
        return idServerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete IdServer : {}", id);
        idServerRepository.deleteById(id);
    }
}
