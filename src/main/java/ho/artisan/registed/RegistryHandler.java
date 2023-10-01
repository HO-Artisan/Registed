package ho.artisan.registed;

import ho.artisan.registed.annotation.RegistryID;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public record RegistryHandler(String namespace, Object classObject) {
    private static boolean isValidAnnotation(Annotation rawAnnotation) {
        return rawAnnotation.annotationType().getPackageName().equals(RegistryID.class.getPackageName() + ".registries");
    }

    private static boolean isValidField(Field field) {
        Annotation[] validAnnotations = Arrays.stream(field.getDeclaredAnnotations())
                .filter(RegistryHandler::isValidAnnotation)
                .toArray(Annotation[]::new);

        return validAnnotations.length == 1;
    }

    private static Registry<?> getRegistry(Annotation validAnnotation) {
        String annotationName = validAnnotation.annotationType().getSimpleName();
        String fieldName = camelToSnake(annotationName).toUpperCase();
        Field[] registries = Registries.class.getDeclaredFields();

        Field registry = Arrays.stream(registries)
                .filter(r -> r.getName().equals(fieldName))
                .findFirst()
                .orElseThrow();

        try {
            // Get the static registry field
            return (Registry<?>) registry.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static String camelToSnake(String camelCase) {
        return camelCase
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2");
    }

    public  <T> void register(Field field) {
        // Check field validity
        if (!isValidField(field)) return;
        Registed.checkAndReportField(field);

        Annotation annotation = Arrays.stream(field.getDeclaredAnnotations())
                .filter(RegistryHandler::isValidAnnotation)
                .findFirst()
                .orElseThrow();

        // Get identifier
        String path = field.isAnnotationPresent(RegistryID.class)
                ? field.getAnnotation(RegistryID.class).value()
                : field.getName().toLowerCase();

        Identifier id = new Identifier(namespace(), path);

        // Get registry instance
        Registry<T> registry = (Registry<T>) getRegistry(annotation);
        try {
            Registry.register(registry, id, (T) field.get(classObject()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
