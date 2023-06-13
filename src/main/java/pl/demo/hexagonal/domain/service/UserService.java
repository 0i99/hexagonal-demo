package pl.demo.hexagonal.domain.service;

import lombok.RequiredArgsConstructor;
import pl.demo.hexagonal.application.ports.in.GetUserUseCase;
import pl.demo.hexagonal.application.ports.out.GetUserPort;
import pl.demo.hexagonal.domain.model.User;

@RequiredArgsConstructor
public class UserService implements GetUserUseCase {

    private final GetUserPort getUserPort;

    @Override
    public User getUser(String userId) {
        return null;
    }
}
