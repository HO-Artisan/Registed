package ho.artisan.registed;

import ho.artisan.registed.annotation.RegistryID;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class Registed implements ModInitializer {
	public static final String NAME = "Registed", ID = "registed";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		// Process entrypoints
		FabricLoader.getInstance().getEntrypoints(ID, Object.class).forEach(object -> {
			Class<?> clazz = object.getClass();

			if (clazz.isAnnotationPresent(RegistryID.class)) {
				String registryId = clazz.getAnnotation(RegistryID.class).value();
				if (registryId.isEmpty()) {
					LOGGER.error("Entrypoint class '" + object.getClass().getName() + "' has an empty registry id!");
					return;
				}

				RegistryHandler registryHandler = new RegistryHandler(registryId, object);
				Arrays.stream(clazz.getDeclaredFields()).forEach(registryHandler::register);

			} else {
				LOGGER.error("Entrypoint class '" + object.getClass().getName() + "' is not annotated by '" + RegistryID.class.getName() + "'!");
			}
		});
	}

	static void checkAndReportField(Field field) {
		if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()))
			return;

		ArrayList<String> warnings = new ArrayList<>();

		if (!Modifier.isStatic(field.getModifiers()))
			warnings.add("non static");

		if (!Modifier.isFinal(field.getModifiers()))
			warnings.add("non final");

		LOGGER.warn(
				"Found "
						+ String.join(" ", warnings)
						+ " field '"
						+ field.getName()
						+ "'!"
		);
	}
}
