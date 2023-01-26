package com.bezkoder.spring.security.postgresql.requestTest;

import com.bezkoder.spring.security.postgresql.security.WebSecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithUserDetails("mod")
public class LogInTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Test
    public void logInTest() throws Exception {
        this.mockMvc.perform(get("/api/auth/signup"))
                .andDo(print());
        // .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        // .andExpect(redirectedUrl("http://localhost//api/auth/signup"));
    }

    @Test
    public void correctLogInTest() throws Exception {
        this.mockMvc.perform(formLogin().user("mod").password("12345678"))
                .andDo(print());
    }

    @Test
    public void BadDataUsers() throws Exception {
        this.mockMvc.perform(post("/api/auth/signup").param("user", "111"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void authenticateTest() throws Exception {
        this.mockMvc.perform(get("/api/test/user"))
                .andDo(print()).
                andExpect(authenticated());
    }
}
