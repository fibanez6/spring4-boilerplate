package com.fibanez.spring4annotation.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import com.fibanez.spring4annotation.dao.AbstractDao;
import com.fibanez.spring4annotation.dao.UserDao;
import com.fibanez.spring4annotation.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findById(int id) {
        User user = getByKey(id);
        if(user!=null){
            initializeCollection(user.getUserProfiles());
        }
        return user;
    }

    public User findBySSO(String sso) {
        System.out.println("SSO : "+sso);
        try{
            User user = (User) getEntityManager()
                    .createQuery("SELECT u FROM User u WHERE u.ssoId LIKE :ssoId")
                    .setParameter("ssoId", sso)
                    .getSingleResult();

            if(user!=null){
                initializeCollection(user.getUserProfiles());
            }
            return user;
        }catch(NoResultException ex){
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        List<User> users = getEntityManager()
                .createQuery("SELECT u FROM User u ORDER BY u.firstName ASC")
                .getResultList();
        return users;
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteBySSO(String sso) {
        User user = (User) getEntityManager()
                .createQuery("SELECT u FROM User u WHERE u.ssoId LIKE :ssoId")
                .setParameter("ssoId", sso)
                .getSingleResult();
        delete(user);
    }

    public void deleteAllUsers() {
        getEntityManager()
                .createQuery("DELETE FROM User")
                .executeUpdate();
    }

    //An alternative to Hibernate.initialize()
    protected void initializeCollection(Collection<?> collection) {
        if(collection == null) {
            return;
        }
        collection.iterator().hasNext();
    }

}