import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    application
}

group = "com.ai.labs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-testng"))
    implementation("com.fasterxml.jackson.core:jackson-core:2.6.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.6.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.6.3")
}

tasks.test {
    useTestNG()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}