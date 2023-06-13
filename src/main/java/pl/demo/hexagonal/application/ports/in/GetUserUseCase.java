package pl.demo.hexagonal.application.ports.in;

import pl.demo.hexagonal.domain.model.User;

public interface GetUserUseCase {
    User getUser(String userId);
}
