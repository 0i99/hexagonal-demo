package pl.demo.hexagonal.infrastucture.adapters.out.csv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.demo.hexagonal.application.ports.out.GetCommentsDictionaryPort;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.CsvDictionaryLoader;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.DictionaryTemplateLoader;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.JsonDictionaryTemplateReader;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.XmlDictionaryTemplateReader;

@Configuration
@ComponentScan
public class CsvAdapterConfig {

    @Bean
    public CsvDictionaryLoader csvDictionaryLoader() {
        return new CsvDictionaryLoader();
    }

    @Bean
    public JsonDictionaryTemplateReader jsonDictionaryTemplateReader() {
        return new JsonDictionaryTemplateReader(new ObjectMapper());
    }

    @Bean
    public XmlDictionaryTemplateReader xmlDictionaryTemplateReader() {
        return new XmlDictionaryTemplateReader(new XmlMapper());
    }

    @Bean
    public DictionaryTemplateLoader dictionaryTemplateLoader(JsonDictionaryTemplateReader jsonDictionaryTemplateReader, XmlDictionaryTemplateReader xmlDictionaryTemplateReader) {
        return new DictionaryTemplateLoader(jsonDictionaryTemplateReader, xmlDictionaryTemplateReader);
    }

    @Bean
    public GetCommentsDictionaryPort getCommentsDictionaryPort(CsvDictionaryLoader csvDictionaryLoader, DictionaryTemplateLoader dictionaryTemplateLoader) {
        return new CommentsCommentsDictionary(csvDictionaryLoader, dictionaryTemplateLoader);
    }

}
