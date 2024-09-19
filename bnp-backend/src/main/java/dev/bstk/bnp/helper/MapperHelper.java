package dev.bstk.bnp.helper;

import java.util.List;
import org.modelmapper.ModelMapper;

public class MapperHelper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private MapperHelper() { }

    public static <T> T to(final Object source, final Class<T> clazz) {
        return MODEL_MAPPER.map(source, clazz);
    }

    public static <S, T> List<T> to(final List<S> source, final Class<T> clazz) {
        return source
            .stream()
            .map(element -> MODEL_MAPPER.map(element, clazz))
            .toList();
    }
}
