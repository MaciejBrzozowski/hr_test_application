package pl.brzozowski.maciej.clis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.brzozowski.maciej.clis.services.RequestOut;
import pl.brzozowski.maciej.clis.services.UnitConverter;
import pl.brzozowski.maciej.clis.utilities.FileSearch;
import pl.brzozowski.maciej.clis.utilities.ReadDataFile;
import pl.brzozowski.maciej.clis.utilities.UnitConversionObject;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

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
}
