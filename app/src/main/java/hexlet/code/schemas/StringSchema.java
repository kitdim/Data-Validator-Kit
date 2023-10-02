package hexlet.code.schemas;

public class StringSchema {
    public boolean isValid(String value) {
        if (value == null || value.isEmpty() || value.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public StringSchema required() {
        return this;
    }

    public StringSchema contains(String word) {
        return this;
    }

    public StringSchema minLength(int size) {
        return this;
    }
}
