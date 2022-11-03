package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static Map<String, List<String>> advancedValidate(Object o) {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            List<String> errors = getErrors(field, o);
            if (!errors.isEmpty()) {
                result.put(field.getName(), errors);
            }
        }
        return result;
    }
    private static List<String> getErrors(Field field, Object obj) {
        List<String> errors = new ArrayList<String>();
        Annotation[] annotations = field.getAnnotations();
        if (annotations.length != 0) {
            for (Annotation a : annotations) {
                if (a instanceof NotNull) {
                    try {
                        field.setAccessible(true);
                        Object fieldValue = field.get(obj);
                        if (fieldValue == null) {
                            errors.add("can not be null");
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (a instanceof MinLength) {
                    try {
                        field.setAccessible(true);
                        String fieldValue = (String) field.get(obj);
                        if (fieldValue.length() < ((MinLength) a).minLength()) {
                            errors.add("Length less than " + ((MinLength) a).minLength());
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }

        return errors;


    }

    public static List<Object> validate(Object obj) {
        List<Object> result = new ArrayList<Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(NotNull.class) != null) {
                try {
                    Object fieldValue = field.get(obj);
                    if (fieldValue == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return result;
    }
}
// END
