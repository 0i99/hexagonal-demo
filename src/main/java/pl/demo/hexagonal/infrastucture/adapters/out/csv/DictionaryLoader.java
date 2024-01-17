package pl.demo.hexagonal.infrastucture.adapters.out.csv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.demo.hexagonal.infrastucture.adapters.out.csv.reader.CsvDictionaryLoader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
public abstract class DictionaryLoader<T> {

    private final CsvDictionaryLoader csvDictionaryLoader;
    private Class<T> tClass;

    /**
     * Provide relative path (to resources) for CSV file
     *
     * @return
     */
    public abstract String getDictionaryFileName();

    /**
     * todo: should be in cache to reload after some period of time, so instead of singleton spring cache may be used
     *
     * @return
     */
    protected List<T> getData() {
        try {
            return csvDictionaryLoader.read(getType(), getDictionaryFileName());
        } catch (ClassNotFoundException e) {
            log.error("Cannot load data from {}", getDictionaryFileName(), e);
        }
        return Collections.emptyList();
    }

    protected Class<T> getType() throws ClassNotFoundException {
        if (tClass == null) {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
            tClass = (Class<T>) Class.forName(actualTypeArgument.getTypeName());
        }
        return tClass;
    }
}
