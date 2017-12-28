package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.NetworkTokenAuthDao;
import com.fibanez.springboot.domain.dto.NetworkTokenAuth;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository("networkTokenAuthDao")
public class NetworkTokenAuthDaoImpl extends AbstractDao<Long, NetworkTokenAuth> implements NetworkTokenAuthDao {

    @Override
    public Optional<NetworkTokenAuth> findById(long id) {
        NetworkTokenAuth NetworkTokenAuth = getByKey(id);
        return Optional.ofNullable(NetworkTokenAuth);
    }

    @Override
    public Optional<NetworkTokenAuth> findByToken(String token) {
        try{
            NetworkTokenAuth NetworkTokenAuth = (NetworkTokenAuth) getEntityManager()
                    .createQuery("SELECT n FROM NetworkTokenAuth n WHERE n.token = :token")
                    .setParameter("token", token)
                    .getSingleResult();
            return Optional.of(NetworkTokenAuth);
        } catch(NoResultException ex){
            return Optional.empty();
        }
    }
}
