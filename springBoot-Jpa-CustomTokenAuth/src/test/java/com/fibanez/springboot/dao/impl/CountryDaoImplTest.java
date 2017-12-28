package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.CountryDao;
import com.fibanez.springboot.domain.dto.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.fibanez.springboot.dao.impl"})
@ActiveProfiles("integration-tests")
public class CountryDaoImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    @Qualifier("countryDao")
    private CountryDao countryDao;

    @Test
    public void when_id1_expect_noEmpty() throws Exception {
        long id = 1L;

        Optional<Country> country = countryDao.findById(id);
        assertTrue(country.isPresent());
    }

}