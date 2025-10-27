plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.example.Main")
}

val env: String = if (project.hasProperty("env")) project.property("env") as String else "dev"

tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to "MyApp",
            "Implementation-Version" to version,
            "Profile" to env,
            "Main-Class" to "org.example.Main"
        )
    }

    from("src/main/resources") {
        include("application-$env.properties")
        rename("application-$env.properties", "application.properties")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.5.0-jre")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set("")
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

tasks.test {
    useJUnitPlatform()
}