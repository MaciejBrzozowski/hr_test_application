package pl.brzozowski.maciej.clis.controller.unathorized;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.brzozowski.maciej.clis.entity.UserIn;
import pl.brzozowski.maciej.clis.entity.UserOut;
import pl.brzozowski.maciej.clis.services.LoginService;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.LOGIN;
import static pl.brzozowski.maciej.clis.controller.unathorized.RegisterController.INFO;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @MockBean
    private LoginService loginService;
    private UserIn userIn;
    private UserOut userOut;
    private String email = "test@test.pl";
    private String password = "password";
    private String token = "token";

    @Before
    public void setUp() throws Exception {
        userIn = new UserIn();
        userOut = new UserOut();
        userIn.setEmail(email);
        userOut.setEmail(email);
        userIn.setPassword(password);
        userOut.setToken(token);
        when(loginService.loginUser(userIn)).thenReturn(userOut);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void shouldLoginUser() throws Exception {
        this.mockMvc.perform(post(LOGIN).with(httpBasic("user", "password"))
                .contentType("application/json")
                .content("{\"email\":\"321@321.pl\",\"password\":\"1\" }"))
                .andDo(print()).andExpect(status()
                .is2xxSuccessful()).
                andExpect(content().string(INFO));
    }

}