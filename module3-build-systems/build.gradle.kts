plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Depends on jackson-databind 2.14.0
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    // Depends on jackson-databind 2.13.2
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.7")

    implementation("com.google.guava:guava:33.5.0-jre")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}