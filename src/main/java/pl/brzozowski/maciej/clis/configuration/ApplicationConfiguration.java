package pl.brzozowski.maciej.clis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.brzozowski.maciej.clis.services.RequestOut;
import pl.brzozowski.maciej.clis.services.UnitConverter;
import pl.brzozowski.maciej.clis.utilities.FindIContent;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RequestOut requestOut() {
        return new RequestOut();
    }

    @Bean
    public UnitConverter unitConverter() {
        return new UnitConverter();
    }

    @Bean
    public FindIContent findIContent() {
        return new FindIContent();
    }

}
