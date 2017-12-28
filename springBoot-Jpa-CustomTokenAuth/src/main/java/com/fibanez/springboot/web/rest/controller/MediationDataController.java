package com.fibanez.springboot.web.rest.controller;

import com.fibanez.springboot.service.MediationDataService;
import com.fibanez.springboot.web.rest.dto.response.NetworkAResponse;
import com.fibanez.springboot.web.rest.dto.response.NetworkBResponse;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reporting")
public class MediationDataController {

    private static final Logger logger = LoggerFactory.getLogger(MediationDataController.class);

    @Autowired
    private MediationDataService mediationDataService;

    @ApiOperation(value = "Reporting API Network A",
            notes = "This can only be done by a valid token.",
            response = NetworkAResponse.class,
            responseContainer="List",
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date",
                    value = "Date un format YYYYMMdd",
                    example = "20171212",
                    required = true,
                    dataType = "date-time",
                    paramType = "path"),
            @ApiImplicitParam(name = "Authorization",
                value = "token",
                required = true,
                dataType = "string",
                defaultValue = "Bearer aaa",
                paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(
                    code = 200,
                    message = "Json data from the Network A",
                    response = NetworkAResponse.class,
                    responseContainer="List"
            )
    })
    @RequestMapping(value = "/network_a/{account_id}/{app_id}/{date}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public HttpEntity<List<NetworkAResponse>> networkA(
            Authentication authentication
            , @PathVariable("account_id") Long accountId
            , @PathVariable("app_id") Long appId
            , @PathVariable("date") @DateTimeFormat(pattern="yyyyMMdd") Date date) {

        Long authAccountId = (Long) authentication.getPrincipal();

        if (authAccountId != accountId) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        try {
            List<NetworkAResponse> aResponse = mediationDataService.getNetworkA(authAccountId,appId, date);

            return new ResponseEntity<>(aResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "Reporting API Network B",
            response = NetworkBResponse.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date",
                    value = "Date un format yyyy-MM-dd",
                    example = "2017-12-12",
                    required = true,
                    dataType = "date-time",
                    paramType = "path"),
            @ApiImplicitParam(name = "Authorization",
                    value = "token",
                    required = true,
                    dataType = "string",
                    defaultValue = "Bearer aaa",
                    paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(
                    code = 200,
                    message = "Json data from the Network B",
                    response = NetworkBResponse.class
            )
    })
    @RequestMapping(value = "network_b/{date}/{account_id}/{app_id}",
            produces = MediaType.TEXT_PLAIN_VALUE,
            method = RequestMethod.POST)
    public HttpEntity<String> networkB(
            @RequestHeader(value="Authorization") String Authorization
            , @PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date
            , @PathVariable("account_id") Integer accountId
            , @PathVariable("app_id") Integer appId) {

        String networkBResponse = null;

        try {





            return new ResponseEntity<>(networkBResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(networkBResponse, HttpStatus.NOT_FOUND);
        }
    }


}
