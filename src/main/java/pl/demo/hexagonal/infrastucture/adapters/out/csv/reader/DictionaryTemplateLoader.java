package pl.demo.hexagonal.infrastucture.adapters.out.csv.reader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class DictionaryTemplateLoader {

    private final JsonDictionaryTemplateReader jsonReader;
    private final XmlDictionaryTemplateReader xmlReader;

    public <T> Optional<T> loadTemplate(Class<T> type, String fileName) {
        if(fileName == null){
            log.warn("No template file specified");
            return Optional.empty();
        }
        try {
            File file = new ClassPathResource(fileName).getFile();
            if (file.exists()) {
                return getReaderByContent(file).read(type, fileName);
            } else {
                log.error("File {} does not exist", fileName);
            }
        } catch (Exception e) {
            log.error("Error occurred while loading object with type {} from file {}", type.getSimpleName(), fileName, e);
        }
        return Optional.empty();
    }

    private DictionaryTemplateReader getReaderByContent(File file) throws IOException {
        String content = Files.readString(file.toPath());
        return isJSON(content) ? jsonReader : xmlReader;
    }

    private boolean isJSON(String content) {
        String jsonRegex = "^\\s*\\{[\\s\\S]*\\}\\s*$";
        return content.matches(jsonRegex);
    }

    private boolean isXML(String content) {
        String xmlRegex = "^\\s*<.*>[\\s\\S]*</.*>\\s*$";
        return content.matches(xmlRegex);
    }
}
