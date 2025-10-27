plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

configurations.all {
    resolutionStrategy {
        failOnVersionConflict()
    }
}

dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.3")

    implementation("org.springframework.boot:spring-boot-starter-web:2.6.7") {
        exclude(group = "com.fasterxml.jackson.core", module = "jackson-databind")
        exclude(group = "com.fasterxml.jackson.core", module = "jackson-core")
        exclude(group = "com.fasterxml.jackson.core", module = "jackson-annotations")
        exclude(group = "com.fasterxml.jackson.dataformat", module = "jackson-dataformat-xml")
        exclude(group = "com.fasterxml.jackson.datatype", module = "jackson-datatype-jdk8")
        exclude(group = "com.fasterxml.jackson.datatype", module = "jackson-datatype-jsr310")
        exclude(group = "com.fasterxml.jackson.module", module = "jackson-module-parameter-names")
        exclude(group = "org.slf4j", module = "slf4j-api")
    }

    implementation("com.google.guava:guava:33.5.0-jre")
}

tasks.test {
    useJUnitPlatform()
}