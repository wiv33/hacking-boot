// Import task types

plugins {
    java
    id("org.springframework.boot") version "2.3.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.bmuschko.docker-remote-api") version "6.7.0"
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
    apply(plugin = "com.bmuschko.docker-remote-api")

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

    docker {
        url.set("https://192.168.59.103:2376")
        certPath.set(File(System.getProperty("user.home"), ".boot2docker/certs/boot2docker-vm"))

        registryCredentials {
            url.set("https://index.docker.io/v1/")
            username.set("bmuschko")
            password.set("pwd")
            email.set("benjamin.muschko@gmail.com")
        }
    }

}