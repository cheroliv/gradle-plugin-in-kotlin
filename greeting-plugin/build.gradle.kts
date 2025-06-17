plugins {
    `java-gradle-plugin`
    this.id("com.gradle.plugin-publish") version "1.2.1"
    signing
}

group = "com.cheroliv"
version = "0.0.1"
repositories.mavenCentral()

dependencies.testImplementation("junit:junit:4.13")


gradlePlugin {
    // Define the plugin
    val greeting by plugins.creating {
        id = "com.cheroliv.plugin.greeting"
        implementationClass = "com.cheroliv.plugin.GreetingPlugin"
        displayName = "com.cheroliv.plugin.GreetingPlugin"
        description = "Mon premier plugin gradle en Kotlin."
        tags = listOf("greetings")
    }
    website = "https://cheroliv.com"
    vcsUrl = "https://github.com/cheroliv/gradle-plugin-in-kotlin.git"
}


// Add a source set and a task for a functional test suite
val functionalTest by sourceSets.creating
gradlePlugin.testSourceSets(functionalTest)

configurations[functionalTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())

val functionalTestTask = tasks.register<Test>("functionalTest") {
    testClassesDirs = functionalTest.output.classesDirs
    classpath = configurations[functionalTest.runtimeClasspathConfigurationName] + functionalTest.output
}

tasks.check { dependsOn(functionalTestTask) }
