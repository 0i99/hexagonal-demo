package pl.demo.hexagonal.application.ports.out;

import pl.demo.hexagonal.domain.model.Country;

public interface GetCountryPort {

    Country getCountry(Long countryId);
}
