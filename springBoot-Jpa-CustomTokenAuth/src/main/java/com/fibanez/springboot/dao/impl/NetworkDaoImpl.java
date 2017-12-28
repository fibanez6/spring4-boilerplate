package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.NetworkDao;
import com.fibanez.springboot.domain.dto.Network;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("networkDao")
public class NetworkDaoImpl extends AbstractDao<Long, Network> implements NetworkDao {

    @Override
    public Optional<Network> findById(long id) {
        Network network = getByKey(id);
        return Optional.ofNullable(network);
    }

}
