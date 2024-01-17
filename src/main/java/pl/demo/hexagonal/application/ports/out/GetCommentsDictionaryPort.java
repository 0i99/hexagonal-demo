package pl.demo.hexagonal.application.ports.out;

import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Comment;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Details;

public interface GetCommentsDictionaryPort extends GetDictionaryPort<Comment> {

    Comment getComment(Long id);

    Details getDetailsForComment(Long id);

}
