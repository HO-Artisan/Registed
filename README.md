### <p align=right>[`→` Modrinth](https://modrinth.com/mod/registed)</p>

# Registed

## Implementation

First, implement **Registed** through gradle:

<details>

<summary>Using <b>Groovy</b></summary>

<h6 align="right">build.gradle</h6>

```groovy
repositories {
	maven { url "https://api.modrinth.com/maven" }
}

dependencies {
	modApi "maven.modrinth:registed:$project.registed_version"
}
```

<h6 align="right">gradle.properties</h6>

```
registed_version=?
```

> [!NOTE]
> Replace `?` with the latest [`tag name`](https://github.com/HO-Artisan/Registed/tags) of **Registed.**

</details>

<details>

<summary>Using <b>Kotlin</b></summary>

<h6 align="right">build.gradle.kts</h6>

```kotlin
repositories {
	maven("https://api.modrinth.com/maven")
}

dependencies {
	modApi("maven.modrinth:registed:${property("registedVersion")}")
}
```

<h6 align="right">gradle.properties</h6>

```
registedVersion=?
```

> [!NOTE]
> Replace `?` with the latest [`tag name`](https://github.com/HO-Artisan/Registed/tags) of **Registed.**

</details>

Next, reference **Registed** into your `fabric.mod.json` as a dependency:

<h6 align="right">fabric.mod.json</h6>

```json5
{
  // Other contents

  "depends": {
    "registed": "*"
  },

  // Other contents
}
```


> [!IMPORTANT]
> **Registed** **should not** be nested in your project. You should always treat **Registed** as a standalone mod.

## Usage

To register an object, do:

<h6 align="right">full.path.to.ClassName.java</h6>

```java
@RegistryId("modid")
public class ClassName {
	@ho.artisan.registed.annotation.registries.Item
	public static final Item ITEM = ...;
}
```

You must annotate your registration class with [`@RegistryID ↗`](src/main/java/ho/artisan/registed/annotation/RegistryID.java)[^@RegistryID_classpath] to tell **Registed** which namespace to use.

[^@RegistryID_Classpath]: `ho.artisan.registed.annotation.RegistryID`

After that, remember to reference this class in your `fabric.mod.json`:

<h6 align="right">fabric.mod.json</h6>

```json5
{
  // Other contents

  "entrypoints": {
    "registed": [
      "full.path.to.ClassName"
    ]
  },

  // Other contents
}
```

**Registed** supports all `Registry<?>`[^Registry_classpath] types inside class `Registries`[^Registries_classpath]. You should convert the target registry type name from **UPPER_SNAKE_CASE** to **CamelCase** to find the desired annotation, for example, `Registries.LOOT_NBT_PROVIDER_TYPE` has a representing annotation [`@LootNBTProviderType`](src/main/java/ho/artisan/registed/annotation/registries/LootNBTProviderType.java).

[^Registry_classpath]: `net.minecraft.registry.Registry`
[^Registries_classpath]: `net.minecraft.registry.Registries`

> [!NOTE]
> All available annotations for registered objects are under package `ho.artisan.registed.annotation.registries`.

> [!IMPORTANT]
> These annotations can only be applied on fields, and you should always make sure your registered objects are `static` and `final` fields.

By default, the registered objects will use their field names in **snake_case** as their in-game identifier paths, for example, field `MY_EXAMPLE_ITEM` will have the identifier path of `my_example_item`. We suggest using **UPPER_SNAKE_CASE** to name your fields, as they should be `static` and `final` and only in snake cases will **Registed** preserve the underscores.

To customize a registered object's in-game identifier path, you can apply annotation [`@RegistryID ↗`](src/main/java/ho/artisan/registed/annotation/RegistryID.java)[^@RegistryID_classpath] to the target field:

```java
@RegistryID("my_item")
@ho.artisan.registed.annotation.registries.Item
public static final Item ITEM = ...;
```

> [!NOTE]
> The identifier path provided by [`@RegistryID ↗@@rightup`](src/main/java/ho/artisan/registed/annotation/RegistryID.java)[@^RegistryID_classpath] will be consumed as raw without any further processing.

> [!NOTE]
> Examples can be found at [`ExampleRegistrations ↗`](src/main/java/ho/artisan/registed/example/ExampleRegistrations.java).

## License

**Registed** is licensed under the **[GNU General Public License v3.](LICENSE)**
