package pl.demo.hexagonal.infrastucture.adapters.in.rest.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.demo.hexagonal.domain.model.City;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.model.CityRsp;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityRsp maptoRsp(City source);

    @AfterMapping
    default void afterRspMapping(City source, @MappingTarget CityRsp target) {
        target.setName(target.getName().toUpperCase());
    }
}
