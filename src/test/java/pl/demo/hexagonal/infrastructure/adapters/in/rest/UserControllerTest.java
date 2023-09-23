package pl.demo.hexagonal.infrastructure.adapters.in.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.demo.hexagonal.application.ports.in.GetUserUseCase;
import pl.demo.hexagonal.domain.model.User;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.RestAdapterConfig;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.UserController;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.mapper.UserMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@ContextConfiguration(classes = RestAdapterConfig.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @SpyBean
    UserMapper userMapper;

    @MockBean
    GetUserUseCase getUserUseCase;

    @DisplayName("when user by id api called but requests unsupported version then http 406 returned")
    @Test
    void invalidVersionRequired() throws Exception {
        mockMvc.perform(
                        get("/user")
                                .accept("application/vnd.0i99.v5+json") //unsupported version
                                .param("user-id", "1")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotAcceptable())
                .andReturn();
    }

    @DisplayName("when user by id api called in positive path then ok returned")
    @Test
    void positivePath() throws Exception {
        Mockito.when(getUserUseCase.getUser(Mockito.any())).thenReturn(
                User.builder().name("sth").surname("else").build()
        );
        MvcResult result = mockMvc.perform(
                        get("/user")
                                .accept("application/vnd.0i99.v1+json")
                                .param("user-id", "1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("""
                             {"information":"some info","qwerty_name":"sth","qwerty_surname":"ELSE"}
                        """))
                .andReturn();

        Assertions.assertFalse(result.getResponse().getHeaderNames().isEmpty());
    }

    @DisplayName("when user by id api called and exception thrown then properly handled")
    @Test
    void handleException() throws Exception {
        Mockito.when(getUserUseCase.getUser(Mockito.any())).thenThrow(UnsupportedOperationException.class);

        mockMvc.perform(
                        get("/user")
                                .accept("application/vnd.0i99.v1+json")
                                .param("user-id", "1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("""
                        {"code":"code","message":"message","details":"details"}
                        """));
    }

}
