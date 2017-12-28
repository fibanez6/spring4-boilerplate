package com.fibanez.springboot.rest.controller;

import com.fibanez.springboot.Application;
import com.fibanez.springboot.service.MediationDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class MediationDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MediationDataService mockMediationDataService;

    /**
     * Success: false
     *
     * Inputs: /reporting/path
     *         no Authorizarion header
     *
     * Expected: HttpCode:401 Unauthorized
     */
    @Test
    public void when_missingAuthHeader_throws_unauthorized() throws Exception {
        this.mockMvc.perform(post("/reporting/path")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized());
    }


    /**
     * Success: false
     *
     * Inputs: /reporting/path
     * Header: "Authorization": "Bearer aaa"
     *
     * Expected: HttpCode:404 Not found
     */
    @Test
    public void when_wrongPath_throws_notFound() throws Exception {
        this.mockMvc.perform(post("/reporting/noExist")
                .header("Authorization", "Bearer aaa")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    /**
     * Success: false
     *
     * Inputs: /reporting/network_a/1/1/12122017
     * Header: "Authorization": "Bearer aaa"
     *
     * Expected: HttpCode:400 Bad request
     */
    @Test
    public void when_wrongDate_throws_badRequest() throws Exception {
        this.mockMvc.perform(post("/reporting/network_a/1/1/12122017")
                .header("Authorization", "Bearer aaa")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    /**
     * Success: false
     *
     * Inputs: /reporting/network_a/1/1/12122017
     * Header: "Authorization": "Bearer noexist"
     *
     * Expected: HttpCode:401 Unauthorized
     */
    @Test
    public void when_wrongToken_throws_unauthorized() throws Exception {
        this.mockMvc.perform(post("/reporting/network_a/1/1/20171212")
                .header("Authorization", "Bearer noexist")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized());
    }

    /**
     * Success: true
     *
     * Inputs: /reporting/network_a/1/1/20171212
     * Header: "Authorization": "Bearer aaa"
     *
     * Expected: HttpCode:200 Ok
     */
    @Test
    public void when_wrongToken_expect_ok() throws Exception {
        this.mockMvc.perform(post("/reporting/network_a/1/1/20171212")
                .header("Authorization", "Bearer aaa")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
