plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
}

repositories.mavenCentral()

dependencies.testImplementation("junit:junit:4.13")


gradlePlugin {
    // Define the plugin
    val greeting by plugins.creating {
        id = "com.cheroliv.plugin.greeting"
        implementationClass = "com.cheroliv.plugin.GreetingPlugin"
    }
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
