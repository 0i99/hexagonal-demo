package pl.demo.hexagonal.infrastucture.adapters.out.csv.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Long id;
    private String title;
    private Date date;
    private String pathToDetails;
    private Details details;
}
