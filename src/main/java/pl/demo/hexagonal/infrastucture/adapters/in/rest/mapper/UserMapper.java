package pl.demo.hexagonal.infrastucture.adapters.in.rest.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.demo.hexagonal.domain.model.Details;
import pl.demo.hexagonal.domain.model.User;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.model.UserRsp;

@Mapper(componentModel = "spring", uses = {CityMapper.class}, config = NonBuilderMapperConfig.class)
public abstract class UserMapper {

    @Mapping(source = "user.surname", target = "othersurname")
    @Mapping(source = "details.info", target = "information")
    public abstract UserRsp maptoUserRsp(User user, Details details);

    @AfterMapping
    protected void afterMappings(@MappingTarget UserRsp userRsp) {
        if (userRsp != null && userRsp.getOthersurname() != null) {
            userRsp.setOthersurname(userRsp.getOthersurname().toUpperCase());
        }
    }
}
