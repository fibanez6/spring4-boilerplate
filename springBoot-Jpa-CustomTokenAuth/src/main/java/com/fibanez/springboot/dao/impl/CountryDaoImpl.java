package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.CountryDao;
import com.fibanez.springboot.domain.dto.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository("countryDao")
public class CountryDaoImpl extends AbstractDao<Long, Country> implements CountryDao {

    @Override
    public Optional<Country> findById(long id) {
        Country country = getByKey(id);
        return Optional.ofNullable(country);
    }

    @Override
    public Optional<Country> findByCode(String code) {
        try{
            Country country = (Country) getEntityManager()
                    .createQuery("SELECT c FROM Country c WHERE c.code = :code")
                    .setParameter("code", code)
                    .getSingleResult();
            return Optional.of(country);
        } catch(NoResultException ex){
            return Optional.empty();
        }
    }

}
