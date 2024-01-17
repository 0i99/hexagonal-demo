package pl.demo.hexagonal.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.demo.hexagonal.application.ports.in.GetDictionaryUseCase;
import pl.demo.hexagonal.application.ports.out.GetCommentsDictionaryPort;
import pl.demo.hexagonal.domain.model.Dictionary;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Comment;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Details;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class DictionaryService implements GetDictionaryUseCase {

    private final GetCommentsDictionaryPort commentsDictionaryPort;

    @Override
    public Dictionary getComments() {
        var comments = commentsDictionaryPort.getDictionary();
        return Dictionary.builder()
                .something(comments.stream().map(m -> m.getId() + " , " + m.getTitle()).collect(Collectors.joining(" | ")))
                .build();
    }

    @Override
    public Dictionary getComment(Long id) {
        Comment comment = commentsDictionaryPort.getComment(id);
        return Dictionary.builder()
                .something(comment.toString())
                .build();
    }

    @Override
    public Dictionary getCommentDetails(Long id) {
        Details details = commentsDictionaryPort.getDetailsForComment(id);
        return Dictionary.builder()
                .something(details.toString())
                .build();
    }
}
