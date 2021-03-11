dependencies {
    print("hello data access")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.mongodb:mongodb-driver-sync")
    implementation("io.projectreactor.tools:blockhound:1.0.3.RELEASE")
    testImplementation("io.projectreactor.tools:blockhound-junit-platform:1.0.3.RELEASE")
}