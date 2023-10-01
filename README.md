### <p align=right>[`→` Modrinth](https://modrinth.com/mod/registed)</p>

# Registed

## Gradle

## Gradle

<details>

<summary>Groovy</summary>

<h6 align=right>build.gradle</h6>

```groovy
repositories {
	maven { url "https://api.modrinth.com/maven" }
}

dependencies {
	modApi "maven.modrinth:registed:$project.registed_version"
}
```

<h6 align=right>gradle.properties</h6>

```
registed_version=?
```

> [!NOTE]
> Replace `?` with the latest [`tag name`](https://github.com/HO-Artisan/Registed/tags) of **Registed.**

</details>

<details>

<summary>Kotlin</summary>

<h6 align=right>build.gradle.kts</h6>

```kotlin
repositories {
	maven("https://api.modrinth.com/maven")
}

dependencies {
	modApi("maven.modrinth:registed:${property("registedVersion")}")
}
```

<h6 align=right>gradle.properties</h6>

```
registedVersion=?
```

> [!NOTE]
> Replace `?` with the latest [`tag name`](https://github.com/HO-Artisan/Registed/tags) of **Registed.**

</details>

## License

**Registed** is licensed under the **[GNU General Public License v3.](LICENSE)**
