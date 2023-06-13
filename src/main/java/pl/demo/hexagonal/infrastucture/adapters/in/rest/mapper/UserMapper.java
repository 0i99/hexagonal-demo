package pl.demo.hexagonal.infrastucture.adapters.in.rest.mapper;

import org.mapstruct.*;
import pl.demo.hexagonal.domain.model.Details;
import pl.demo.hexagonal.domain.model.User;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.model.UserRsp;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public abstract class UserMapper {

    @Mapping(source = "user.surname", target = "othersurname")
    @Mapping(source = "user.cities", target = "cityRspList")
    @Mapping(source = "details.info", target = "information")
    public abstract UserRsp maptoUserRsp(User user, Details details);

    @AfterMapping
    protected void afterMappings(@MappingTarget UserRsp userRsp){
        System.out.println("after from UserMapper");
        userRsp.setOthersurname(userRsp.getOthersurname().toUpperCase());
    }
}
