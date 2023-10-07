package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        super();
        addCheck("instanceof", value -> {
            if (value != null) {
                return value instanceof Map<?, ?>;
            }
            return true;
        });
    }

    public MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> ((Map<?, ?>) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("shape", value ->
                schemas.entrySet().stream().allMatch(item -> {
                    Object object = ((Map<?, ?>) value).get(item.getKey());
                    return item.getValue().isValid(object);
                })
        );
        return this;
    }
}
