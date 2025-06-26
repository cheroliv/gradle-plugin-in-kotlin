pluginManagement.plugins.kotlin("jvm") version "2.1.21"

plugins { this.id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }

rootProject.name = "sample_gradle_plugin-kotlin-dsl"
include("greeting-plugin")
