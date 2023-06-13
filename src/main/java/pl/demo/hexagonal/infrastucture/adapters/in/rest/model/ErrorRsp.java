package pl.demo.hexagonal.infrastucture.adapters.in.rest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorRsp {
    private String code;
    private String message;
    private String details;
}
