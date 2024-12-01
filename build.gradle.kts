plugins {
    kotlin("jvm") version "2.0.21" // Kotlin version to use
}

val kotestVersion = "5.9.1"

group = "me.aoc24"
version = "1.0-SNAPSHOT"

repositories { // Sources of dependencies. See 1️⃣
    mavenCentral() // Maven Central Repository. See 2️⃣
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}