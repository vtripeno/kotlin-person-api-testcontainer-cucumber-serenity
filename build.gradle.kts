import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

defaultTasks("clean", "test", "aggregate")

plugins{
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.61"
    id("org.springframework.boot") version "2.3.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    jacoco
}

buildscript {
    repositories {
        mavenLocal()
        jcenter()
    }
    dependencies {
        classpath("net.serenity-bdd:serenity-gradle-plugin:2.2.9")
    }
}

apply(
        plugin = "net.serenity-bdd.aggregator"
)

group = "org.example"
version = "1.0-SNAPSHOT"

description = ""

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    jcenter()
    google()
}

@Suppress("MagicNumber")
val minTestCoverage = 0.5.toBigDecimal()

val springVersion = "2.3.1.RELEASE"
val jacksonKotlinVersion = "2.11.0"
val kotlinVersion = "1.3.61"
val serenityCucumberVersion = "1.9.51"
val serenityVersion = "2.0.91"
val mockMvcVersion = "3.3.0"
val archUniteVersion = "0.14.1"
val kluentVersion = "1.61"
val testContainerVersion = "1.14.3"
val springMockKVersion = "1.1.3"
val mockKVersion = "1.9.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-mustache:${springVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web:${springVersion}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jacksonKotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:${springVersion}")
    implementation("org.springframework.boot:spring-boot-test:${springVersion}")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:${springVersion}")
    // TESTS
    testImplementation("net.serenity-bdd:serenity-cucumber:${serenityCucumberVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${springVersion}")
    testImplementation("io.rest-assured:spring-mock-mvc:${mockMvcVersion}")
    testImplementation("com.tngtech.archunit:archunit-junit4:${archUniteVersion}")
    testImplementation("org.amshove.kluent:kluent:${kluentVersion}")
    testImplementation("net.serenity-bdd:serenity-core:${serenityVersion}") {
        exclude("module:cucumber-core")
    }
    testImplementation("net.serenity-bdd:serenity-spring:${serenityVersion}")
    testImplementation("net.serenity-bdd:serenity-junit:${serenityVersion}")
    testImplementation("net.serenity-bdd:serenity-screenplay:${serenityVersion}") {
        exclude("module:cucumber-core")
    }
    testImplementation("org.testcontainers:testcontainers:${testContainerVersion}")
    testImplementation("com.ninja-squad:springmockk:${springMockKVersion}")
    testImplementation("io.mockk:mockk:${mockKVersion}")
}

fun ignorePackagesInJacoco(classDirectories: ConfigurableFileCollection) {
    classDirectories.setFrom(
            files(classDirectories.files.map {
                fileTree(it) {
                    excludes.add("com/victor/api/Application.class")
                    excludes.add("com/victor/api/Application.kt")
                    excludes.add("com/victor/api/dataprovider/repository/PersonRepository.class")
                }
            })
    )
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<JacocoCoverageVerification> {
    violationRules {
        isFailOnViolation = true

        rule {
            enabled = true

            limit {
                minimum = minTestCoverage
            }
        }
    }

    afterEvaluate {
        ignorePackagesInJacoco(classDirectories)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

