package pl.demo.hexagonal.infrastructure.adapters.out.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.PersistenceAdapterConfig;

@Configuration
@Import(PersistenceAdapterConfig.class)
class TestRepositoryConfig {

}
