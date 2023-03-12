package com.dominik.typer.controller;

import DataForTests.ProvideUser;
import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.json.UserJson;
import com.dominik.typer.service.DbErrorService;
import com.dominik.typer.service.userPersistence.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = { UserJsonController.class, UserMapper.class} )
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserJsonControllerTest implements ProvideUser{

    @MockBean
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DbErrorService dbErrorService;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @PostConstruct
    public void init() {
        String uri = "http://localhost:" + port;
    }


    @Test
    void givenUser_whenGetAllUsers_returnUsers() throws Exception {
        User u = new User(1, "A", 10, "a@wp.pl", BigDecimal.ZERO, UserRole.USER);
        User u2 = new User(2, "B", 20, "b@wp.pl", BigDecimal.ZERO, UserRole.USER);
        when(userService.getUsers()).thenReturn(List.of(u, u2));
        mockMvc.perform(get("/user"))
                .andExpect(status().is(200))
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].points", Matchers.equalTo(10)))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(2)))
                .andExpect(jsonPath("$[1].points", Matchers.equalTo(20)));
    }

    @Test
    void givenUser_whenGetUser_returnUser() throws Exception {
        User u = new User(1, "A", 10, "b@wp.pl", BigDecimal.ZERO, UserRole.USER);
        when(userService.getUser(1)).thenReturn(u);
        mockMvc.perform(get("/user/1"))
                .andExpect(status().is(200))
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.points", Matchers.equalTo(10)));
    }

    @Test
    void givenUserJson_whenRegisterUser_returnStatus201() throws Exception{
        //given
        UserJson u = UserJson.builder().id(1).username("A").email("domwp.pl").points(10).build();
        String userjson = objectMapper.writeValueAsString(u);
        //when and then
        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(userjson))
                .andExpect(status().isCreated());
    }
    @Test
    void givenMissingBody_thenReturns404() throws Exception{
       mockMvc.perform(post("/user")
                .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void givenHeaderAndBody_whenRegisterUserWithAdmin_thenSave() throws Exception{
        //given
        UserJson u = UserJson.builder().id(1).username("A").email("domwp.pl").points(10).build();
        String userJson = objectMapper.writeValueAsString(u);
        //when and then
        mockMvc.perform(post("/user/admin")
                .contentType("application/json")
                .header("login", "Admin")
                .content(userJson))
                .andExpect(status().isCreated());
    }

}