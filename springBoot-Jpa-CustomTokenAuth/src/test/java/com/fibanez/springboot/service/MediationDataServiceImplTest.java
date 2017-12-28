package com.fibanez.springboot.service;

import com.fibanez.springboot.dao.AccountDao;
import com.fibanez.springboot.domain.dto.*;
import com.fibanez.springboot.web.rest.dto.response.NetworkAResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MediationDataServiceImplTest {

    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private MediationDataServiceImpl mediationDataService;

    @Test
    public void when_accountNofound_expect_empty() throws Exception {
        when(accountDao.findById(anyInt())).thenReturn(Optional.empty());

        List<NetworkAResponse> result = mediationDataService.getNetworkA(-1,-1, new Date());

        assertThat(result.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_accountHasNotApps_throws_illegalArgumentException() throws Exception {
        long accountId = 1;
        long appId = -1;

        Optional<Account> mockAccount = Optional.of(new Account(accountId, "test", Collections.emptyList(), Collections.emptySet()));

        when(accountDao.findById(anyInt())).thenReturn(mockAccount);

        mediationDataService.getNetworkA(accountId,appId, new Date());
    }


    @Test(expected = IllegalArgumentException.class)
    public void when_appNotBelongAccount_throws_illegalArgumentException() throws Exception {
        long accountId = 1;
        long appId = -1;

        Application mockApp = new Application(1L,"testApp", Collections.emptySet());
        Set<Application> applications = new HashSet<Application>() {{
            add(mockApp);
        }};

        Optional<Account> mockAccount = Optional.of(new Account(accountId,"test", Collections.emptyList(), applications));

        when(accountDao.findById(anyInt())).thenReturn(mockAccount);

        mediationDataService.getNetworkA(accountId,appId, new Date());
    }

    @Test
    public void when_appHasNotMediation_expect_size0() throws Exception {
        long accountId = 1;
        long appId = 1;

        Application mockApp = new Application(appId, "testApp", Collections.emptySet());
        Set<Application> applications = new HashSet<Application>() {{
            add(mockApp);
        }};

        Optional<Account> mockAccount = Optional.of(new Account(accountId, "test", Collections.emptyList(), applications));

        when(accountDao.findById(anyInt())).thenReturn(mockAccount);

        List<NetworkAResponse> result = mediationDataService.getNetworkA(accountId,appId, new Date());

        assertThat(result.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_mediationHasNotAdConfig_throws_illegalArgumentException() throws Exception {
        long accountId = 1;
        long appId = 1;

        final Mediation mockMediation = new Mediation(1L,null, Collections.emptySet());
        Set<Mediation> mockMediations = new HashSet<Mediation>() {{
            add(mockMediation);
        }};

        Application mockApp = new Application(appId, "testApp", mockMediations);
        Set<Application> mockApps = new HashSet<Application>() {{
            add(mockApp);
        }};

        Optional<Account> mockAccount = Optional.of(new Account(accountId, "test", Collections.emptyList(), mockApps));

        when(accountDao.findById(anyInt())).thenReturn(mockAccount);

        List<NetworkAResponse> result = mediationDataService.getNetworkA(accountId,appId, new Date());

        assertThat(result.size(), is(0));
    }

    @Test
    public void when_mediationHasAdConfig_expect_oneNetWorkAResponse() throws Exception {
        long accountId = 1;
        long appId = 1;

        final Country mockCountry = new Country(1L, "test United Kingdom", "UK");
        final CountryCode mockCountryCode = new CountryCode(1L, mockCountry, "gb");
        Set<CountryCode> mockCountryCodes = new HashSet<CountryCode>() {{
            add(mockCountryCode);
        }};

        final Network mockNetwork = new Network(1L, "testNetwork", mockCountryCodes);
        final AdFormat mockAdFormat = new AdFormat(1L, "testFormat");

        final AdConfig mockAdConfig = new AdConfig(1L, mockAdFormat, Boolean.TRUE);
        Set<AdConfig> mockAdConfigs = new HashSet<AdConfig>() {{
            add(mockAdConfig);
        }};

        final Mediation mockMediation = new Mediation(1L,mockNetwork, mockAdConfigs);
        Set<Mediation> mockMediations = new HashSet<Mediation>() {{
            add(mockMediation);
        }};

        final Application mockApp = new Application(appId, "testApp", mockMediations);
        Set<Application> mockApps = new HashSet<Application>() {{
            add(mockApp);
        }};

        Optional<Account> mockAccount = Optional.of(new Account(accountId, "test", Collections.emptyList(), mockApps));

        when(accountDao.findById(anyInt())).thenReturn(mockAccount);

        List<NetworkAResponse> result = mediationDataService.getNetworkA(accountId,appId, new Date());

        assertThat(result.get(0).getAccountId(), is(accountId));
        assertThat(result.get(0).getAppId(), is(appId));
        assertThat(result.get(0).getAdFormat(), is(mockAdFormat.getFormat()));
        assertThat(result.get(0).getCountry(), is(mockCountry.getCountry()));
    }

}