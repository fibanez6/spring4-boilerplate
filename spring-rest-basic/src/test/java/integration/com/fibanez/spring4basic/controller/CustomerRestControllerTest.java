package integration.com.fibanez.spring4basic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fibanez.spring4basic.model.Customer;
import com.fibanez.spring4basic.web.service.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import integration.com.fibanez.spring4basic.utils.TestContext;
import utils.TestUtil;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class CustomerRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        reset(customerServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() {
        mockMvc = null;
    }

    @Test
    public void when_list_expected_ok() throws Exception{
        when(customerServiceMock.list()).thenReturn(null);

        mockMvc.perform(get("/customers"))
//                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void when_anyMediaType_expected_ok() throws Exception{
        when(customerServiceMock.list()).thenReturn(null);

        mockMvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk());
    }

    @Test
    public void when_list_expected_jsonMediaType() throws Exception{
        when(customerServiceMock.list()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                ;
    }

    @Test
    public void when_list_expected_jsonDataSize1() throws Exception{
        List customers = Arrays.asList(new Customer(101l, "John", "Doe", "djohn@gmail.com", "121-232-3435", null));

        when(customerServiceMock.list()).thenReturn(customers);

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

        when(customerServiceMock.list()).thenReturn(customers);

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

        verify(customerServiceMock, times(1)).list();
        verifyNoMoreInteractions(customerServiceMock);

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
//                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void when_createWrongJson_throws_badRequest() throws Exception{

        ResultActions result =  mockMvc.perform(
                post("/customers")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isBadRequest())
                ;
    }

    /**
     * This checks the @valid on method
     * @throws Exception
     */
    @Test
    public void when_createCustomerNullFirstname_throws_badRequest() throws Exception{
        Customer mockCustomer = new Customer(101l, "", "Doe", "djohn@gmail.com", "121-232-3435", null);
        String jsonCustomer = mapper.writeValueAsString(mockCustomer);

        ResultActions result =  mockMvc.perform(
                post("/customers")
                        .content(jsonCustomer)
                        .contentType(MediaType.APPLICATION_JSON)
                )
//                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.objectName", is("customer")) )
                .andExpect(jsonPath("$.field", is("firstName")) )
                .andExpect(jsonPath("$.defaultMessage", is("size must be between 3 and 30")) )
                ;
    }

    @Test
    public void when_createCustomer_throws_badRequest() throws Exception{
        Customer mockCustomer = new Customer(null, "John", "Doe", "djohn@gmail.com", "121-232-3435", null);
        String jsonCustomer = mapper.writeValueAsString(mockCustomer);


        mockCustomer.setId(123l);
        when(customerServiceMock.create(any())).thenReturn(mockCustomer);


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

        verify(customerServiceMock, times(1)).create(any());
        verifyNoMoreInteractions(customerServiceMock);
    }


    // TODO add MORE

}