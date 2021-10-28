plugins {
    kotlin("multiplatform") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    application
}

group = "com.mikiloz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
        withJava()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.ajalt.clikt:clikt:3.3.0")
                implementation("com.squareup.okio:okio:3.0.0-alpha.11")
                implementation("io.ktor:ktor-client-core:1.6.4")
                implementation("io.ktor:ktor-client-serialization:1.6.4")
                implementation("io.ktor:ktor-client-logging:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:1.6.4")
                //implementation("org.slf4j:slf4j-api:1.7.32")
                implementation("ch.qos.logback:logback-classic:1.2.6")
            }
        }
        val jvmTest by getting
    }
}

// WA for https://youtrack.jetbrains.com/issue/KT-37964
distributions {
    main {
        contents {
            from("$buildDir/libs") {
                exclude(project.name)
                rename("${project.name}-jvm", project.name)
                into("lib")
            }
        }
    }
}

application {
    mainClass.set("com.mikiloz.kanjitomo2anki.MainKt")
}
