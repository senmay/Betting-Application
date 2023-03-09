package com.dominik.typer.controller;

import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.service.DbErrorService;
import com.dominik.typer.service.userPersistence.UserService;
import jakarta.annotation.PostConstruct;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = { UserJsonController.class, UserMapper.class} )
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserJsonControllerTest {

    @MockBean
    UserService userService;

//    @MockBean
//    UserMapper userMapper;

    @MockBean
    DbErrorService dbErrorService;

    @LocalServerPort
    private int port;

    private String uri;

    @Autowired
    private MockMvc mockMvc;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }


    @Test
    public void a() throws Exception {
        User u = new User(1, "A", 10, "a@wp.pl", BigDecimal.ZERO, UserRole.USER);
        BDDMockito.when(userService.getUsers()).thenReturn(List.of(u));
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().is(200))
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].points", Matchers.equalTo(10)));
    }
}