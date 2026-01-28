plugins {
    id("java")
    id("maven-publish")
}

group = "fr.smeltblock"
version = "1.0.8"

repositories {
    mavenCentral()

    // API Bukkit / Spigot
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    // API Paper
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    // Adventure / Kyori
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    // Bukkit / Paper API
    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")

    // Adventure
    compileOnly("net.kyori:adventure-api:4.14.0")
    compileOnly("net.kyori:adventure-text-serializer-legacy:4.14.0")

    // JDBC Drivers
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("com.mysql:mysql-connector-j:8.3.0")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"]) // Publier le composant Java

            groupId = "fr.smeltblock.plugincommons"
            artifactId = "PluginCommons"
            version = project.version.toString()

            pom {
                name.set("PluginCommons") // Corrigé
                description.set("Plugin Minecraft PluginCommons")
                url.set("https://maven.humbrain.com/")
            }
        }
    }

    repositories {
        maven {
            name = "smeltblock"
            url = uri("https://maven.humbrain.com/repository/smeltblock/")
            credentials {
                username = project.findProperty("nexusUsername") as String? ?: "admin"
                password = project.findProperty("nexusPassword") as String? ?: "ton_mot_de_passe"
            }
        }
    }
}