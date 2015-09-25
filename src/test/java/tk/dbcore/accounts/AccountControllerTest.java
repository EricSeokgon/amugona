package tk.dbcore.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import tk.dbcore.Application;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project: amugona
 * FileName: AccountControllerTest
 * Date: 2015-09-14
 * Time: 오후 3:15
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class AccountControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AccountService service;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void createAccount() throws Exception {
        //MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        AccountDto.Create createDto = accountCreateDto();


        ResultActions result = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)));

        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.username", is("hadeslee")));


        result = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)));

        result.andDo(print());
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.code", is("duplicated.username.exception")));
    }

    @Test
    public void createAccount_BadRequest() throws Exception {
        AccountDto.Create createDto = new AccountDto.Create();
        createDto.setUsername(" ");
        createDto.setPassword("1234");

        ResultActions result = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)));

        result.andDo(print());
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.code", is("bad.request")));
    }

    //TODO getAccounts()

    @Test
    public void getAccounts() throws Exception {
        AccountDto.Create createDto = accountCreateDto();
        service.createAccount(createDto);

        ResultActions result = mockMvc.perform(get("/accounts"));

        // {"content":
        // [{"id":1,"username":"hadeslee","fullname":null,"joined":1442379729278,"updated":1442379729278}],"last":true,"totalElements":1,
        // "totalPages":1,
        // "size":20,
        // "number":0,
        // "sort":null,
        // "first":true,
        // "numberOfElements":1}
        result.andDo(print());
        result.andExpect(status().isOk());
    }

    private AccountDto.Create accountCreateDto() {
        AccountDto.Create createDto = new AccountDto.Create();
        createDto.setUsername("hadeslee");
        createDto.setPassword("password");
        return createDto;
    }

    @Test
    public void getAccount() throws Exception {
        AccountDto.Create createDto = new AccountDto.Create();
        Account account = service.createAccount(createDto);

        ResultActions result = mockMvc.perform(get("/accounts/" + account.getId()));

        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void updateAccount() throws Exception {
        AccountDto.Create createDto = accountCreateDto();
        Account account = service.createAccount(createDto);

        AccountDto.Update updateDto = new AccountDto.Update();
        updateDto.setFullName("seokgon lee");
        updateDto.setPassword("pass");

        ResultActions result = mockMvc.perform(put("/accounts/" + account.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)));

        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.fullname", is("seokgon lee")));

    }

    @Test
    public void deleteAccount() throws Exception {
        ResultActions result = mockMvc.perform(delete("/accounts/1"));
        result.andDo(print());
        result.andExpect(status().isBadRequest());

        AccountDto.Create createDto = accountCreateDto();
        Account account = service.createAccount(createDto);

        result = mockMvc.perform(delete("/accounts/" + account.getId()));
        result.andDo(print());
        result.andExpect(status().isNoContent());

    }

}