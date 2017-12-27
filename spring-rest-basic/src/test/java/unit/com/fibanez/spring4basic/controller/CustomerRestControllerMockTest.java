package unit.com.fibanez.spring4basic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fibanez.spring4basic.model.Customer;
import utils.TestUtil;
import com.fibanez.spring4basic.web.controller.CustomerRestController;
import com.fibanez.spring4basic.web.service.CustomerService;
import com.fibanez.spring4basic.web.validator.CustomerValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRestControllerMockTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerRestController restController;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restController)
                .setValidator(new CustomerValidator())
                .build();
    }

    @After
    public void tearDown() {
        mockMvc = null;
    }

    @Test
    public void when_list_expected_ok() throws Exception{
        when(customerService.list()).thenReturn(null);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_anyMediaType_expected_ok() throws Exception{
        when(customerService.list()).thenReturn(null);

        mockMvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk());
    }

    @Test
    public void when_list_expected_jsonMediaType() throws Exception{
        when(customerService.list()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                ;
    }

    @Test
    public void when_list_expected_jsonDataSize1() throws Exception{
        List customers = Arrays.asList(new Customer(101l, "John", "Doe", "djohn@gmail.com", "121-232-3435", null));

        when(customerService.list()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
//                .andDo(print())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                ;
    }

    @Test
    public void when_listCustomer_expected_jssonCustomer() throws Exception{
        Customer mockCustomer = new Customer(101l, "John", "Doe", "djohn@gmail.com", "121-232-3435", null);

        List customers = Arrays.asList(mockCustomer);

        when(customerService.list()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(mockCustomer.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(mockCustomer.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(mockCustomer.getLastName())))
                .andExpect(jsonPath("$[0].email", is(mockCustomer.getEmail())))
                .andExpect(jsonPath("$[0].mobile", is(mockCustomer.getMobile())))
                .andExpect(jsonPath("$[0].dateOfBirth", is(notNullValue()) ))
                .andExpect(jsonPath("$[0].address", is(nullValue()) ))
                ;

        verify(customerService, times(1)).list();
        verifyNoMoreInteractions(customerService);

    }

    @Test
    public void when_createNull_throws_badRequest() throws Exception{
        mockMvc.perform(post("/customers")).andExpect(status().isBadRequest());
    }

    @Test
    public void when_createWrongMediaType_throws_unsupportedMediaType() throws Exception{
        mockMvc.perform(
                post("/customers")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void when_createWrongJson_throws_badRequest() throws Exception{

        ResultActions result =  mockMvc.perform(
                post("/customers")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                ;
    }

    @Test
    public void when_createCustomerWithoutFirstname_throws_badRequest() throws Exception{
        Customer mockCustomer = new Customer(101l, "", "Doe", "djohn@gmail.com", "121-232-3435", null);
        String jsonCustomer = mapper.writeValueAsString(mockCustomer);

        ResultActions result =  mockMvc.perform(
                post("/customers")
                        .content(jsonCustomer)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                ;
    }

    @Test
    public void when_createCustomer_throws_badRequest() throws Exception{
        Customer mockCustomer = new Customer(null, "John", "Doe", "djohn@gmail.com", "121-232-3435", null);
        String jsonCustomer = mapper.writeValueAsString(mockCustomer);


        mockCustomer.setId(123l);
        when(customerService.create(any())).thenReturn(mockCustomer);


        ResultActions result =  mockMvc.perform(
                post("/customers")
                        .content(jsonCustomer)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(mockCustomer.getId().intValue()) ))
                .andExpect(jsonPath("$.firstName", is(mockCustomer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(mockCustomer.getLastName())))
                .andExpect(jsonPath("$.email", is(mockCustomer.getEmail())))
                .andExpect(jsonPath("$.mobile", is(mockCustomer.getMobile())))
                .andExpect(jsonPath("$.dateOfBirth", is(notNullValue()) ))
                .andExpect(jsonPath("$.address", is(nullValue()) ))
                ;

        verify(customerService, times(1)).create(any());
        verifyNoMoreInteractions(customerService);
    }


    // TODO add MORE

}