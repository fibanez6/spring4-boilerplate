package com.fibanez.springboot.service;

import com.fibanez.springboot.web.rest.dto.response.NetworkAResponse;

import java.util.Date;
import java.util.List;

public interface MediationDataService {

    List<NetworkAResponse> getNetworkA(long id, long appId, Date date);
}
