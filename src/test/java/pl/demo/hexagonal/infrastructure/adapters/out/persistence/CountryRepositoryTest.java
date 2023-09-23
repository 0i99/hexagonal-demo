package pl.demo.hexagonal.infrastructure.adapters.out.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import pl.demo.hexagonal.domain.model.Country;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.CountryRepository;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.PersistenceAdapterConfig;

@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=none"
})
@Tag("integration")
@Sql({
        "/sql/ddl.sql",
        "/sql/data.sql"
})
@Import(PersistenceAdapterConfig.class)
class CountryRepositoryTest {

    @Autowired
    CountryRepository repository;

    @DisplayName("positive path for getting country ")
    @Test
    void getPositivePath() {
        Country country = repository.getCountry(1L);
        Assertions.assertNotNull(country);
        Assertions.assertNotNull(country.getName());

        Assertions.assertNotNull(country.getCities());
        Assertions.assertTrue(!country.getCities().isEmpty());
    }
}
