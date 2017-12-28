package com.fibanez.springboot.service;

import com.fibanez.springboot.dao.AccountDao;
import com.fibanez.springboot.domain.dto.*;
import com.fibanez.springboot.web.rest.dto.response.NetworkAResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MediationDataServiceImpl implements MediationDataService {

    @Autowired @Qualifier("accountDao")
    private AccountDao accountDao;

    public List<NetworkAResponse> getNetworkA(long accountId, long appId, Date date) {
        Optional<Account> optAccount = accountDao.findById(accountId);
        if (!optAccount.isPresent()) {
            return Collections.emptyList();
        }

        Application application = getApplication(optAccount.get(), appId);

        return application.getMediations()
                .stream()
                .map( m -> buildNetworkAResponse(optAccount.get(),application,date, m))
                .collect(Collectors.toList());
    }

    private Application getApplication(Account account, long appId) {
        return account.getApplications()
                .stream()
                .filter(app -> app.getId().equals(appId))
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(String.format("App %d does not belong to Account", appId)));
    }

    private NetworkAResponse buildNetworkAResponse(Account account, Application application, Date date, Mediation mediation) {
        return NetworkAResponse.builder()
                .accountId(account.getId())
                .appId(application.getId())
                .date(date)
                .adFormat(getFirstAdFormat(mediation).orElse(new AdFormat()).getFormat())
                .country(getFirstCountry(mediation).orElse(new Country()).getCountry())
                .clicks(0)
                .impressions(0)
                .revenue(0L)
                .build();
    }

    private Optional<AdFormat> getFirstAdFormat(Mediation mediation) {
        if (mediation == null || mediation.getAdConfigs().isEmpty()) {
           throw new IllegalArgumentException(String.format("Mediation %d has not Ad configs", mediation.getId()));
        }
        AdConfig adConfig =  mediation.getAdConfigs()
                .stream()
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(String.format("Mediation %d has not Ad configs", mediation.getId())));
        return Optional.ofNullable(adConfig.getFormat());
    }

    private Optional<Country> getFirstCountry(Mediation mediation) {
        if (mediation == null || mediation.getNetwork() == null) {
            throw new IllegalArgumentException(String.format("Mediation %d has not Country Code", mediation.getId()));
        }
        CountryCode countryCode = mediation.getNetwork().getCountryCodes()
                .stream()
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(String.format("Mediation %d has not Country Code", mediation.getId())));
        return Optional.ofNullable(countryCode.getCountry());
    }


}
