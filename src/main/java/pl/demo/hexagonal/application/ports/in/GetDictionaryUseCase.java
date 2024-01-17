package pl.demo.hexagonal.application.ports.in;

import pl.demo.hexagonal.domain.model.Dictionary;

public interface GetDictionaryUseCase {

    Dictionary getComments();

    Dictionary getComment(Long id);

    Dictionary getCommentDetails(Long id);
}
