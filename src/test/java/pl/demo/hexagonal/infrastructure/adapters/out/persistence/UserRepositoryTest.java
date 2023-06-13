package pl.demo.hexagonal.infrastructure.adapters.out.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import pl.demo.hexagonal.domain.model.User;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.PersistenceAdapterConfig;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.UserRepository;

@DataJpaTest
@Tag("integration")
@Sql({
        "/sql/data.sql"
})
@Import(PersistenceAdapterConfig.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @DisplayName("positive path for getting user ")
    @Test
    void getPositivePathForExistingUser() {
        User user = userRepository.getUser("user1@pm.me");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("nowak",user.getSurname());
    }
}
