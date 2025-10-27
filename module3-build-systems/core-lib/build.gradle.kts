plugins {
    id("java")
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"]) // публикуем Java-компонент
            groupId = project.group.toString()
            artifactId = "core-lib"
            version = project.version.toString()
        }
    }
    repositories {
        maven {
            name = "localMaven"
            url = uri(System.getProperty("user.home") + "/.m2/repository")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}