plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

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

tasks.test {
    useJUnitPlatform()
}