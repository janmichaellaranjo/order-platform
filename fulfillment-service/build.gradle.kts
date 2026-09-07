plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("grpc-service")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.protobuf")
}

grpcService {
    protoFiles = setOf("fulfillment.proto")
}

group = "com.democompany"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.grpc:spring-grpc-test")
}