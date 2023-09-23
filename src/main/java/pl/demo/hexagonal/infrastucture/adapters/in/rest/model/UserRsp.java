package pl.demo.hexagonal.infrastucture.adapters.in.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRsp {

    @JsonProperty("qwerty_name")
    private String name;

    @JsonProperty("qwerty_surname")
    private String othersurname;

    private String information; //from another source
}
