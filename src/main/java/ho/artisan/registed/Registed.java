package ho.artisan.registed;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Registed implements ModInitializer {
	public static final String NAME = "Registed", ID = "registed";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
	}

	public static Identifier id(String... paths) {
		return new Identifier(ID, String.join("/", paths));
	}
}
