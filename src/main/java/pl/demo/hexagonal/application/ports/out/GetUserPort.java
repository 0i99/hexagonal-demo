package pl.demo.hexagonal.application.ports.out;

import pl.demo.hexagonal.domain.model.User;

public interface GetUserPort {

    User getUser(String userId);
}
