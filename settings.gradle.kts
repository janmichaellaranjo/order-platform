pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "order-platform"
include("common", "order-service", "inventory-service", "payment-service", "fulfillment-service")