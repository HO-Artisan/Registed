package ho.artisan.registed.example;

import ho.artisan.registed.annotation.RegistryID;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.item.Item;

/**
 * This class shows example registrations using Registed.
 * In this case, this class is not referenced in the mod's <code>fabric.mod.json</code> as this is just an example.
 * But in production, remember to reference your registration classes as entrypoints.
 */
@RegistryID("registed")
public class ExampleRegistrations {
    @ho.artisan.registed.annotation.registries.Item
    public static final Item ITEM = new Item(new Item.Settings());

    @ho.artisan.registed.annotation.registries.Block
    public static final Block BLOCK = new GrassBlock(FabricBlockSettings.create());
}
