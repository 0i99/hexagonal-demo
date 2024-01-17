package pl.demo.hexagonal.infrastucture.adapters.out.csv;

import pl.demo.hexagonal.application.ports.out.GetCommentsDictionaryPort;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Comment;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Details;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.CsvDictionaryLoader;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.DictionaryTemplateLoader;

import java.util.List;

public class CommentsCommentsDictionary extends DictionaryLoader<Comment> implements GetCommentsDictionaryPort {

    private final DictionaryTemplateLoader dictionaryTemplateLoader;

    public CommentsCommentsDictionary(CsvDictionaryLoader csvDictionaryLoader, DictionaryTemplateLoader dictionaryTemplateLoader) {
        super(csvDictionaryLoader);
        this.dictionaryTemplateLoader = dictionaryTemplateLoader;
    }

    @Override
    public String getDictionaryFileName() {
        return "dict/dict1.csv";
    }

    @Override
    public List<Comment> getDictionary() {
        List<Comment> comments = getData();
        comments.forEach(this::fetchDetails);
        return comments;
    }

    @Override
    public Comment getComment(Long id) {
        return getDictionary().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Details getDetailsForComment(Long id) {
        return getDictionary().stream().filter(p -> p.getId().equals(id)).findFirst().map(Comment::getDetails).orElse(null);
    }

    private void fetchDetails(Comment comment) {
        if (comment.getPathToDetails() != null) {
            dictionaryTemplateLoader.loadTemplate(Details.class, comment.getPathToDetails())
                    .ifPresent(comment::setDetails);
        }
    }
}
