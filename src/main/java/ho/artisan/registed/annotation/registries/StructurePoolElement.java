package ho.artisan.registed.annotation.registries;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see net.minecraft.registry.Registries#STRUCTURE_POOL_ELEMENT
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StructurePoolElement {
}
