package jjs.lang.references;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(staticName = "apply")
public class TypeReference<T> {

    Class<?> type;

    List<Class<?>> genericTypes;

    public static <T> TypeReference<T> apply(Class<?> type) {
        return new TypeReference<>(type, List.of());
    }

    public static <T> TypeReference<T> apply(Class<?> type, Class<?>... genericTypes) {
        return new TypeReference<>(type, List.of(genericTypes));
    }

}
