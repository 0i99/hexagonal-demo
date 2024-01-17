package pl.demo.hexagonal.infrastucture.adapters.in.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.demo.hexagonal.application.ports.in.GetDictionaryUseCase;
import pl.demo.hexagonal.domain.model.Dictionary;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/dictionary")
public class DictionaryController {
    private final GetDictionaryUseCase getDictionaryUseCase;

    @GetMapping(
            path = ""
            , produces = "application/vnd.0i99.v1+json"
    )
    public ResponseEntity<Dictionary> getDictionary(@RequestParam(name = "id", required = false) Long id) {
        return ResponseEntity.ok(
                id != null ? getDictionaryUseCase.getComment(id) : getDictionaryUseCase.getComments()
        );
    }

}
