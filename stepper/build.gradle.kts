@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
    id("maven-publish")
}

val libGroup = "io.github.rajdeepvaghela"
val libVersion = "2.0.0"

kotlin {

    android {
        namespace = "com.rdapps.stepper"
        compileSdk = 36
        minSdk = 23
    }

    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js { browser() }
    wasmJs { browser() }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.animation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.androidx.material.icons.extended)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(libGroup, "stepper", libVersion)

    pom {
        name = "Vertical Stepper"
        description = "A customizable vertical stepper with ability to animate and use custom layouts for the content "
        inceptionYear = "2024"
        url = "https://github.com/rajdeepvaghela/VerticalStepper"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "rajdeepvaghela"
                name = "Rajdeep Vaghela"
                url = "https://github.com/rajdeepvaghela"
            }
        }
        scm {
            url = "https://github.com/rajdeepvaghela/VerticalStepper"
            connection = "scm:git:git://github.com/rajdeepvaghela/VerticalStepper.git"
            developerConnection = "scm:git:git://github.com/rajdeepvaghela/VerticalStepper.git"
        }
    }
}