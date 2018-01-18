package pl.brzozowski.maciej.clis.controller.authorized;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UnitConverterControlerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void checkingProperSetupOfTests() {
        ServletContext servletContext = context.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(context.getBean("unitConverter"));
    }

    @Test
    public void shouldReturn404WhenNoBasicAuthInHeader() throws Exception {
        this.mockMvc.perform(get("/auth/convert/meters/to/badUnit")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(UNAUTHORIZED.value()));
    }

    @Test
    public void shouldReturn200WhenBasicAuthIsAddedInHeaderAndTokenIsValid() throws Exception {
        this.mockMvc.perform(get("/auth/convert/meters/to/badUnit").with(httpBasic("user", "password")))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status()
                            .is(OK.value()));
    }

}