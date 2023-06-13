package pl.demo.hexagonal.infrastucture.adapters.in.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

//@Builder //problems with @AfterMapping
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRsp {

    @JsonProperty("qwerty_name")
    private String name;

    @JsonProperty("qwerty_surname")
    private String othersurname;

    private List<CityRsp> cityRspList;

    private String information; //from another source
}
