import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    print("hello data access")
    apply(plugin = "com.bmuschko.docker-remote-api")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.mongodb:mongodb-driver-sync")
    implementation("io.projectreactor.tools:blockhound:1.0.3.RELEASE")
    testImplementation("io.projectreactor.tools:blockhound-junit-platform:1.0.3.RELEASE")

    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true

}

// Use task types
tasks.create("buildMyAppImage", com.bmuschko.gradle.docker.tasks.image.DockerBuildImage::class) {
    inputDir.set(file("docker/myapp"))
    images.add("test/myapp:latest")
}