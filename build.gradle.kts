plugins {
    kotlin("jvm") version "2.0.21" // Kotlin version to use
}

val kotestVersion = "5.9.1"
val immutablesVersion = "0.3.8"

group = "me.aoc24"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:$immutablesVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}