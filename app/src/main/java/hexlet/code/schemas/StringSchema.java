package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        super();
        addCheck("instanceof",
                value -> {
                    if (value != null) {
                        return value instanceof String;
                    }
                    return true;
                });
    }

    public StringSchema required() {
        addCheck("required",
                value -> (value != null && !value.equals("")));
        return this;
    }

    public StringSchema contains(String word) {
        addCheck("contains",
                value -> value != null && ((String) value).contains(word));
        return this;
    }

    public StringSchema minLength(int size) {
        addCheck("minLength",
                value -> value != null && ((String) value).length() >= size);
        return this;
    }
}
