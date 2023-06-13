package pl.demo.hexagonal.infrastucture.adapters.in.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.demo.hexagonal.application.ports.in.GetUserUseCase;
import pl.demo.hexagonal.domain.model.Details;
import pl.demo.hexagonal.domain.model.User;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.mapper.UserMapper;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.model.ErrorRsp;
import pl.demo.hexagonal.infrastucture.adapters.in.rest.model.UserRsp;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final GetUserUseCase getUserUseCase;
    private final UserMapper userMapper;

    @GetMapping(
            path = ""
            , produces = "application/vnd.0i99.v1+json"
    )
    public ResponseEntity<UserRsp> getDetailsForUser(@RequestParam(name = "user-id") String userId) {
        User userDetails = getUserUseCase.getUser(userId);
        Details additionalDetails = Details.builder().info("some info").build();
        return ResponseEntity.ok(userMapper.maptoUserRsp(userDetails, additionalDetails));//Details.builder().info("info").build()
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    protected ResponseEntity<ErrorRsp> handle() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ErrorRsp.builder()
                        .code("code")
                        .message("message")
                        .details("details")
                        .build()
        );
    }

}
