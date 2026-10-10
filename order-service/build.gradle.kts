plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.google.protobuf")
}

group = "com.democompany"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("io.temporal:temporal-sdk:1.33.0")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.grpc:spring-grpc-test")
}


// Helper tasks to run the worker and the starter
tasks.register<JavaExec>("runWorker") {
    group = "demogroup"
    description = "Run the Temporal worker"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = "com.demo_company.order_service.workflow.OrderWorkflow"
}

tasks.register<JavaExec>("runStarter") {
    group = "demogroup"
    description = "Run the workflow starter"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = "com.demo_company.order_service.Starter"
}