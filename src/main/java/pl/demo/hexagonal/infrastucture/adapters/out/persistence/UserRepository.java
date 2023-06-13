package pl.demo.hexagonal.infrastucture.adapters.out.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.demo.hexagonal.application.ports.out.GetUserPort;
import pl.demo.hexagonal.domain.model.User;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.UserEntity;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.UserEntity_;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.id.UserId;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.id.UserId_;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Repository
public class UserRepository implements GetUserPort {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public User getUser(String userId) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createTupleQuery();

        var user = query.from(UserEntity.class);
        Path<UserId> userIdPath = user.get(UserEntity_.id);

        List<Predicate> predicates = new LinkedList<>();
        predicates.add(
                criteriaBuilder.equal(userIdPath.get(UserId_.email), userId)
        );

        query.multiselect(
                user
        ).where(predicates.toArray(Predicate[]::new));

        var tuple = entityManager.createQuery(query).getSingleResult();
        UserEntity userEntity = tuple.get(0, UserEntity.class);

        return User.builder().name(userEntity.getDetails().getName())
                .surname(userEntity.getDetails().getSurname()).build();
    }
}
