package pl.brzozowski.maciej.clis.controller.unathorized;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static pl.brzozowski.maciej.clis.controller.unathorized.WebSimpleController.BASE_TEXT;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
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
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")
                                                   .with(httpBasic("user", "password"))
                                                   .contentType("aplication/json"))
                    .andDo(print())
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful()).
                andExpect(MockMvcResultMatchers.content().string(BASE_TEXT));
    }

}