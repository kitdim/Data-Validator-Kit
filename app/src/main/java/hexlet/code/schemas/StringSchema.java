package hexlet.code.schemas;

public class StringSchema {
    private boolean isResize;
    private boolean isRequired;
    private boolean isContains;
    private int size;
    private String word;

    public StringSchema() {
        isResize = false;
        isRequired = false;
        isContains = false;
    }


    public boolean isValid(String value) {
        if (value == null || value.isEmpty() && !isResize && !isRequired && !isContains) {
            return true;
        } else {
            return false;
        }

    }

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema contains(String word) {
        isContains = true;
        return this;
    }

    public StringSchema minLength(int size) {
        isResize = true;
        return this;
    }
}
