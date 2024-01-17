package pl.demo.hexagonal.infrastructure.adapters.out.csv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.CommentsCommentsDictionary;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.CsvDictionaryLoader;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Comment;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.model.Details;

import java.io.File;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CommentsDictionaryTest {

    @Spy
    CsvDictionaryLoader csvDictionaryLoader = new CsvDictionaryLoader();

    @InjectMocks
    CommentsCommentsDictionary commentsDictionary;

    @DisplayName("check positive case for getting dictionary")
    @Test
    void checkPositiveCase() {
        SoftAssertions assertions = new SoftAssertions();

        List<Comment> comments = commentsDictionary.getDictionary();

        assertions.assertThat(comments).isNotEmpty();

        Comment json = comments.get(0);
        Comment xml = comments.get(1);

        assertions.assertThat(json).isNotNull();
        assertions.assertThat(json.getId()).isEqualTo(1L);
        assertions.assertThat(json.getTitle()).isEqualTo("title1");
        assertions.assertThat(json.getPathToDetails()).isEqualTo("1.json");

        assertions.assertThat(json.getDate()).isNotNull();

        Details details = Details.builder().something("sth").data("lorem ipsum").build();
        ObjectMapper objectMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(details));
            System.out.println(xmlMapper.writeValueAsString(details));
            File file = new ClassPathResource(xml.getPathToDetails()).getFile();
            Details details1 = xmlMapper.readValue(file, Details.class);
            System.out.println(details1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//
//        File file = new ClassPathResource(json.getDetails()).getFile();
//        ObjectMapper objectMapper = new ObjectMapper();
//        MappingIterator<T> readValues = mapper.reader(type).with(bootstrapSchema).readValues(file);

        assertions.assertAll();

    }

}