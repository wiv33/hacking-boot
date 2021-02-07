plugins {
    java
    id("org.springframework.boot") version "2.3.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.palantir.docker") version "0.22.1"
}

group = "org.psawesome"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_15
//    targetCompatibility = org.gradle.api.JavaVersion.VERSION_15
}

allprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testImplementation("io.projectreactor:reactor-test")
    }
    tasks.withType(Test::useJUnitPlatform)
}
