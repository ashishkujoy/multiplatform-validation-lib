plugins{
    id("maven-publish")
    java
    kotlin("multiplatform") version "1.4.31"
    id("java-library")
}

group = "org.example"
version = "1.0.6"

repositories {
    mavenCentral()
}

kotlin {
    targets {
        js(org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR) {
            binaries.library()
            nodejs {
                compilations.all {
                    packageJson {
                        private = false
                    }
                }
            }
        }

        jvm {
            compilations.all {
                kotlinOptions.jvmTarget = "1.8"
            }
            testRuns["test"].executionTask.configure {
                useJUnit()
            }
        }
    }

    sourceSets {
        val kotestVersion = "4.4.3"

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        val jvmMain by getting {
            dependsOn(commonMain)
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.kotest:kotest-assertions-core:$kotestVersion")
            }

        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

    }
}

tasks.named<org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile>("compileKotlinJs").configure {
    kotlinOptions.moduleKind = "commonjs"
}

tasks.named<Test>("jvmTest") {
    useJUnit()
    filter {
        isFailOnNoMatchingTests = false
    }
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
