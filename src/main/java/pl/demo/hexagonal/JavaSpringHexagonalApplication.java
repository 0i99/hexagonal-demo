package pl.demo.hexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import pl.demo.hexagonal.domain.DomainConfig;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.RestAdapterConfig;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.PersistenceAdapterConfig;

@Import(value = {
        PersistenceAdapterConfig.class
        , RestAdapterConfig.class
        , DomainConfig.class
})
@ComponentScan(basePackages = {"pl.demo.hexagonal"}, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
})
@SpringBootApplication
public class JavaSpringHexagonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringHexagonalApplication.class, args);
    }

}
