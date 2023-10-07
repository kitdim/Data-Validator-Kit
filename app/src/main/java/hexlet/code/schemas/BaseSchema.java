package hexlet.code.schemas;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final HashMap<String, Predicate<Object>> checks;
    protected BaseSchema() {
        checks = new LinkedHashMap<>();
    }

    protected final void addCheck(String name, Predicate<Object> validate) {
        checks.put(name, validate);
    }

    public final boolean isValid(Object value) {
        return checks
                .values()
                .stream()
                .allMatch(check -> check.test(value));
    }
}
