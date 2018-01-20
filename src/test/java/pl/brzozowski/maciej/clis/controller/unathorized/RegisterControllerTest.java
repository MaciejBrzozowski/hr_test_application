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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.brzozowski.maciej.clis.services.RegisterService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.REGISTER;
import static pl.brzozowski.maciej.clis.controller.unathorized.RegisterController.INFO;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RegisterControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @MockBean
    private RegisterService registerService;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void registerController() throws Exception {
        this.mockMvc.perform(get(REGISTER)
                .with(httpBasic("user", "password")))
                .andDo(print()).andExpect(MockMvcResultMatchers.status()
                .is2xxSuccessful()).
                andExpect(MockMvcResultMatchers.content().string(INFO));

    }

    @Test
    public void postRegisterController() throws Exception {
        this.mockMvc.perform(post(REGISTER)
                .with(httpBasic("user", "password")))
                .andDo(print()).andExpect(MockMvcResultMatchers.status()
                .is2xxSuccessful()).
                andExpect(MockMvcResultMatchers.content().string(INFO));
    }

    @Test
    public void putRegisterController() throws Exception {
        this.mockMvc.perform(put(REGISTER)
                .with(httpBasic("user", "password")).contentType("application/json").content("{\"email\":\"321@321.pl\",\"password\":\"1\" }"))
                .andDo(print()).andExpect(MockMvcResultMatchers.status()
                .is2xxSuccessful()).
                andExpect(MockMvcResultMatchers.content().string(""));
    }

}