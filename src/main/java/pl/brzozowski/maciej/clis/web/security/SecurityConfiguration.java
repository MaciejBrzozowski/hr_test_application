package pl.brzozowski.maciej.clis.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN", "USER", "ACTUATOR").and()
                .withUser("user").password("user").roles("USER");
    }
}
