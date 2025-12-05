plugins {
    java
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation("pascal.qihe:platform:1.0.0-SNAPSHOT")

    // JUnit test.
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Use slf4j for logging.
    implementation("org.slf4j:slf4j-api:2.0.9")
    // Use log4j as logging implementation.
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.20.0")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.20.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    applicationName = "qihe"
    mainClass.set("com.example.Main")
}
