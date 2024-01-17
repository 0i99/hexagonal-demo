package pl.demo.hexagonal.application.ports.out;

import java.util.List;

public interface GetDictionaryPort<T> {

    List<T> getDictionary();

}
