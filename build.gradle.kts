
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.8"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

}

// ./gradlew runWorkaround
task<JavaExec>("runWorkaround") {
    group = "application"
    mainClass = "com.example.WorkaroundApplicationKt"
    classpath = sourceSets["main"].runtimeClasspath
}


// ./gradlew runWorkaround2
task<JavaExec>("runWorkaround2") {
    group = "application"
    mainClass = "com.example.WorkaroundApplication2Kt"
    classpath = sourceSets["main"].runtimeClasspath
}
