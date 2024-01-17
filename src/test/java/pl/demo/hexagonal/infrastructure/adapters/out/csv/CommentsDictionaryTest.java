package pl.demo.hexagonal.infrastructure.adapters.out.csv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.CommentsCommentsDictionary;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Comment;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Details;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.CsvDictionaryLoader;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.DictionaryTemplateLoader;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.JsonDictionaryTemplateReader;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.XmlDictionaryTemplateReader;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CommentsDictionaryTest {

    @Spy
    CsvDictionaryLoader csvDictionaryLoader = new CsvDictionaryLoader();

    @Spy
    DictionaryTemplateLoader dictionaryTemplateLoader = new DictionaryTemplateLoader(
            new JsonDictionaryTemplateReader(new ObjectMapper()),
            new XmlDictionaryTemplateReader(new XmlMapper())
    );

    @InjectMocks
    CommentsCommentsDictionary commentsDictionary;

    @DisplayName("check positive case for getting dictionary")
    @Test
    void checkPositiveCase() {
        SoftAssertions assertions = new SoftAssertions();

        List<Comment> comments = commentsDictionary.getDictionary();

        assertions.assertThat(comments).isNotEmpty();

        Comment json = comments.get(0);

        assertions.assertThat(json).isNotNull();
        assertions.assertThat(json.getId()).isEqualTo(1L);
        assertions.assertThat(json.getTitle()).isEqualTo("title1");
        assertions.assertThat(json.getPathToDetails()).isEqualTo("dict/details/1.json");

        assertions.assertThat(json.getDate()).isNotNull();

        assertions.assertThat(json.getDetails()).isEqualTo(
                Details.builder().something("sth").data("lorem ipsum").build()
        );

        assertions.assertAll();

    }

}