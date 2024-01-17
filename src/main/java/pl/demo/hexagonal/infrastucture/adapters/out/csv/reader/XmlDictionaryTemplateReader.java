package pl.demo.hexagonal.infrastucture.adapters.out.csv.reader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class XmlDictionaryTemplateReader implements DictionaryTemplateReader {

    private final XmlMapper mapper;

    @Override
    public <T> Optional<T> read(Class<T> type, String fileName) {
        try {
            File file = new ClassPathResource(fileName).getFile();
            if (file.exists()) {
                return Optional.of(mapper.readValue(file, type));
            }
        } catch (Exception e) {
            log.error("Error occurred while loading object with type {} from file {}", type.getSimpleName(), fileName, e);
        }
        return Optional.empty();
    }
}
