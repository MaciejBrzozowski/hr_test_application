package pl.brzozowski.maciej.clis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.brzozowski.maciej.clis.configuration.security.AuthenticationEntryPoint;
import pl.brzozowski.maciej.clis.configuration.security.SecurityConfiguration;
import pl.brzozowski.maciej.clis.configuration.security.TokenHandlerInterceptor;
import pl.brzozowski.maciej.clis.repository.UserRepository;
import pl.brzozowski.maciej.clis.utilities.*;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    public TokenHandlerInterceptor tokenHandlerInterceptor;

    @Autowired
    public SecurityConfiguration securityConfiguration;

    @Bean
    public UnitConversionObject unitConversionObject() {
        return new UnitConversionObject();
    }

    @Bean
    public FileSearch fileSearch() {
        return new FileSearch();
    }

    @Bean
    public ReadDataFile readDataFile() {
        return new ReadDataFile();
    }

    @Bean
    public Logger logger() {
        return getLogger("Application logger");
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public TokenGenerator tokenGenerator() {
        return new TokenGenerator();
    }

    @Bean
    public TokenDetails tokenDetails() {
        return new TokenDetails();
    }

    @Bean
    public TokenValidator tokenValidator() {
        return new TokenValidator();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint();
    }

    @Bean
    public ResetTokenGenerator resetTokenGenerator() {
        return new ResetTokenGenerator();
    }

}
