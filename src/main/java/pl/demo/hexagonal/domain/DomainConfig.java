package pl.demo.hexagonal.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.demo.hexagonal.application.ports.in.GetDictionaryUseCase;
import pl.demo.hexagonal.application.ports.in.GetUserUseCase;
import pl.demo.hexagonal.application.ports.out.GetCommentsDictionaryPort;
import pl.demo.hexagonal.application.ports.out.GetUserPort;
import pl.demo.hexagonal.domain.service.DictionaryService;
import pl.demo.hexagonal.domain.service.UserService;

@Configuration
public class DomainConfig {

    @Bean
    public GetUserUseCase getUserUseCase(GetUserPort userPort) {
        return new UserService(userPort);
    }

    @Bean
    public GetDictionaryUseCase getDictionaryUseCase(GetCommentsDictionaryPort commentsDictionaryPort) {
        return new DictionaryService(commentsDictionaryPort);
    }
}
