plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "material-management"

include(":material")
include(":inventory")
include(":gateway")
include(":authorization")