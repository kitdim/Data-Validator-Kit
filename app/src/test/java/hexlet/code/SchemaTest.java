package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchemaTest {
    private Validator validator;
    @BeforeEach
    void initValidator() {
        validator = new Validator();
    }

    @Test
    void testStringValidator() {
        StringSchema stringSchema = validator.string();
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(5));
        assertTrue(stringSchema.contains("ов").isValid("Чехов"));

        stringSchema.required();
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.minLength(5).isValid("dio"));
        assertFalse(stringSchema.minLength(5).contains("Булгаков").isValid("Чехов"));
        assertTrue(stringSchema.contains("ов").minLength(5).isValid("Булгаков"));
    }

    @Test
    void testNumberValidator() {
        NumberSchema numberSchema = validator.number();
        assertTrue(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid("dio"));
        assertTrue(numberSchema.positive().isValid(null));

        numberSchema.required();
        assertFalse(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(8));
        assertFalse(numberSchema.positive().isValid(null));
        assertFalse(numberSchema.isValid(-8));

        numberSchema.range(1, 5);
        assertFalse(numberSchema.isValid(8));
        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(1));
    }

    @Test
    void testMapValidator() {
        MapSchema mapSchema = validator.map();
        Map<Integer, String> temp = new HashMap<>();
        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(temp));

        mapSchema.required();
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(temp));
        temp.put(1, "Булгаков");
        assertTrue(mapSchema.isValid(temp));

        mapSchema.sizeof(3);
        assertFalse(mapSchema.isValid(temp));
        temp.put(2, "Чехов");
        temp.put(3, "Достоевский");
        assertTrue(mapSchema.isValid(temp));
    }

    @Test
    void testMapValidatorShape() {
        MapSchema mapSchema = validator.map();
        Map<String, BaseSchema> schemas1 = new HashMap<>();
        Map<String, BaseSchema> schemas2 = new HashMap<>();

        schemas1.put("name", validator.string().required());
        schemas1.put("age", validator.number().positive());
        schemas2.put("name", validator.string().required().contains("io").minLength(5));
        schemas2.put("age", validator.number().positive().range(2, 40));

        mapSchema.shape(schemas1);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(mapSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "");
        human2.put("age", null);
        assertFalse(mapSchema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "Valya");
        human3.put("age", -5);
        mapSchema.isValid(human3);

        mapSchema.shape(schemas2);
        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "dio");
        human4.put("age", 40);
        assertFalse(mapSchema.isValid(human4));

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "Fabricio");
        human5.put("age", 40);
        assertTrue(mapSchema.isValid(human5));
    }
}
