package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        super();
        addCheck("instanceof", value -> {
            if (value != null) {
                return value instanceof Integer;
            }
            return true;
        });
    }

    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> {
            if (value instanceof Integer) {
                return (Integer) value > 0;
            }
            return true;
        });
        return this;
    }

    public NumberSchema range(int start, int end) {
        addCheck("range", value -> {
            if (value instanceof Integer) {
                return (Integer) value >= start && (Integer) value <= end;
            }
            return true;
        });
        return this;
    }
}
