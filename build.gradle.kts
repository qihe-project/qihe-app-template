plugins {
    java
    application
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

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
    implementation("pascal.qihe:platform:1.1.0-SNAPSHOT")

    // JUnit test.
    testImplementation(platform("org.junit:junit-bom:6.0.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Use slf4j for logging.
    implementation("org.slf4j:slf4j-api:2.0.17")
    // Use log4j as logging implementation.
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl:2.25.3")
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.25.3")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.25.3")
}

tasks.test {
    useJUnitPlatform()
}

application {
    applicationName = "qihe"
    mainClass.set("com.example.Main")
}
