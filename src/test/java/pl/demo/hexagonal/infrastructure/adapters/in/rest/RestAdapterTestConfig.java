package pl.demo.hexagonal.infrastructure.adapters.in.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.GlobalExceptionHandler;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.UserController;

@Configuration
@Import({
        UserController.class,
        GlobalExceptionHandler.class
})
@ComponentScan({
        "pl.demo.hexagonal.infrastucture.adapters.in.rest.mapper"
})
public class RestAdapterTestConfig {
}
