package pl.brzozowski.maciej.clis.controller.unathorized;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.brzozowski.maciej.clis.configuration.ApplicationConfiguration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static pl.brzozowski.maciej.clis.controller.unathorized.WebSimpleController.BASE_TEXT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@WebAppConfiguration
public class WebSimpleControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void shouldReturnTextWhenBasicEndpointCall() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/register").with(user("user").password("password").roles("USER"))).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
                andExpect(MockMvcResultMatchers.content().string(BASE_TEXT));
    }

}