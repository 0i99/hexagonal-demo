package pl.demo.hexagonal.infrastucture.adapters.out.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.demo.hexagonal.application.ports.out.GetCountryPort;
import pl.demo.hexagonal.domain.model.City;
import pl.demo.hexagonal.domain.model.Country;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.CityEntity;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.CountryEntity;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.CountryEntity_;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.id.CountryId;
import pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.id.CountryId_;

import java.util.LinkedList;
import java.util.List;

/**
 * for joining tests
 */
@RequiredArgsConstructor
@Slf4j
@Repository
public class CountryRepository implements GetCountryPort {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Country getCountry(Long countryId) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(CountryEntity.class);

        var country = query.from(CountryEntity.class);
//        Path<CountryId> countryIdPath = country.get(CountryEntity_.id);

        var city = country.join(CountryEntity_.cities, JoinType.LEFT);

        List<Predicate> predicates = new LinkedList<>();
        predicates.add(
                criteriaBuilder.equal(country.get(CountryEntity_.id), countryId)
        );

        query.select(country)
                .where(predicates.toArray(Predicate[]::new));

        var result = entityManager.createQuery(query).getSingleResult();
        return Country.builder()
                .name(result.getName())
                .cities(result.getCities().stream().map(it -> City.builder().name(it.getName()).build()).toList())
                .build();
    }

}
