package pl.demo.hexagonal.infrastucture.adapters.out.csv.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    private String something;
    private String data;
}
