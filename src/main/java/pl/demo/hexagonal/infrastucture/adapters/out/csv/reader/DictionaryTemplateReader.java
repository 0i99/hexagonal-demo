package pl.demo.hexagonal.infrastucture.adapters.out.csv.reader;

import java.util.Optional;

public interface DictionaryTemplateReader {

    <T> Optional<T> read(Class<T> type, String fileName);
}
