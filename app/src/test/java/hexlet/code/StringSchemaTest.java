package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private static Validator validator;
    private static StringSchema schema;

    @BeforeAll
    public static void init() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    void stringTest() {
        var actual = validator.string();
        assertThat(actual != null).isTrue();
    }
    @Test
    void requiredTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("dio"));
        assertFalse(schema.required().isValid(null));
        assertFalse(schema.contains("dio").minLength(3).isValid("nono"));
    }

}
