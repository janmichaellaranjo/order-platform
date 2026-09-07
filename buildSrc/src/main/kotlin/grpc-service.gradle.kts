import com.google.protobuf.gradle.id

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.protobuf")
}

// --- extension consuming subprojects configure ---
abstract class GrpcServiceExtension {
    /** Proto filenames (relative to common's proto dir) this service needs, e.g. "order.proto". */
    var protoFiles: Set<String> = emptySet()
}

val grpcServiceExtension = extensions.create("grpcService", GrpcServiceExtension::class.java)

val protobufVersion = "3.25.5"
val grpcVersion = "1.68.1"
val grpcKotlinVersion = "1.4.1"

dependencies {
    implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")

    implementation("com.google.protobuf:protobuf-java:$protobufVersion")
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    compileOnly("org.apache.tomcat:annotations-api:6.0.53") // needed for @Generated on JDK 9+
}

val commonProtoDir = project(":common").file("src/main/proto")

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("kotlin")
            }
            task.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

afterEvaluate {
    val selected = grpcServiceExtension.protoFiles
    check(selected.isNotEmpty()) {
        "No proto files declared. Configure grpcService { protoFiles.add(\"your.proto\") } in this subproject's build.gradle.kts."
    }

    sourceSets {
        main {
            proto {
                srcDir(commonProtoDir)
                include(selected)
            }
        }
    }
}