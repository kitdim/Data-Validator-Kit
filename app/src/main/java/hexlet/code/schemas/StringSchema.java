package hexlet.code.schemas;

import hexlet.code.Validator;

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
        if (value == null || value.isEmpty()) {
            return true;
        } else if (!isResize && !isRequired && !isContains) {
            return true;
        } else if (value.contains(word) && value.length() == size) {
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
        this.word = word;
        return this;
    }

    public StringSchema minLength(int size) {
        isResize = true;
        this.size = size;
        return this;
    }
}
