package pl.demo.hexagonal.infrastructure.adapters.in.rest.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.demo.hexagonal.domain.model.City;
import pl.demo.hexagonal.domain.model.Details;
import pl.demo.hexagonal.domain.model.User;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.mapper.CityMapper;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.mapper.UserMapper;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.model.CityRsp;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.model.UserRsp;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Spy
    CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    @Spy
    @InjectMocks
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @DisplayName("check mappings from User to UserRsp")
    @Test
    void checkMappingToUserRsp() {
        User source = User.builder().name("jan").surname("nowak").build();
        Details source2 = Details.builder().info("qwerty").build();
        UserRsp target = mapper.maptoUserRsp(source, source2);//

        Assertions.assertEquals("jan", target.getName());
        Assertions.assertEquals("NOWAK", target.getOthersurname());
        Assertions.assertEquals("qwerty", target.getInformation());

    }
}
