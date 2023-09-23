package pl.demo.hexagonal.infrastucture.adapters.out.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.demo.hexagonal.application.ports.out.GetUserPort;
import pl.demo.hexagonal.domain.model.User;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.UserEntity;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.UserEntity_;
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
        var query = criteriaBuilder.createQuery(UserEntity.class);

        var user = query.from(UserEntity.class);
        var userIdPath = user.get(UserEntity_.id);
        user.fetch(UserEntity_.details, JoinType.LEFT);

        List<Predicate> predicates = new LinkedList<>();
        predicates.add(
                criteriaBuilder.equal(userIdPath.get(UserId_.email), userId)
        );

        query.select(
                user
        ).where(predicates.toArray(Predicate[]::new));

        try {
            var result = entityManager.createQuery(query).getSingleResult();
            return User.builder().name(result.getDetails().getName())
                    .surname(result.getDetails().getSurname()).build();
        } catch (NoResultException e) {
            log.warn("No user found for {}", userId, e);
        }
        return null;
    }
}
