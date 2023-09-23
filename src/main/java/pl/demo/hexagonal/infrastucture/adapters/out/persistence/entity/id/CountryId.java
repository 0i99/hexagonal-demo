package pl.demo.hexagonal.infrastucture.adapters.out.persistence.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


/**
 * complex key
 */
@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryId implements Serializable {

    @Column(name = "s_id")
    private Long id;

    @Column(name = "s_status")
    private Integer status;


}
