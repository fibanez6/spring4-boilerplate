package com.fibanez.spring4annotation.dao;

import com.fibanez.spring4annotation.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile>implements UserProfileDao{

    public UserProfile findById(int id) {
        return getByKey(id);
    }

    public UserProfile findByType(String type) {
        System.out.println("type: "+type);
        try{
            UserProfile userProfile = (UserProfile) getEntityManager()
                    .createQuery("SELECT p FROM UserProfile p WHERE p.type LIKE :type")
                    .setParameter("type", type)
                    .getSingleResult();
            return userProfile;
        }catch(NoResultException ex){
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll(){
        List<UserProfile> userProfiles = getEntityManager()
                .createQuery("SELECT p FROM UserProfile p ORDER BY p.type ASC")
                .getResultList();
        return userProfiles;
    }

}

