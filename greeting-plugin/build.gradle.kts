plugins {
    signing
    `java-gradle-plugin`
    this.alias(libs.plugins.publish)
    this.alias(libs.plugins.kotlin.jvm)
}

group = "com.cheroliv"
version = "0.0.1"

repositories.mavenCentral()

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit:junit:4.13")
}

kotlin.jvmToolchain(21)

val functionalTest: SourceSet by sourceSets.creating
val functionalTestTask = tasks.register<Test>("functionalTest") {
    testClassesDirs = functionalTest.output.classesDirs
    classpath = configurations[functionalTest.runtimeClasspathConfigurationName] + functionalTest.output
}


configurations[functionalTest.implementationConfigurationName]
    .extendsFrom(configurations.testImplementation.get())

gradlePlugin {
    plugins {
        create("greeting") {
            id = "com.cheroliv.plugin.greeting"
            implementationClass = "com.cheroliv.plugin.GreetingPlugin"
            displayName = "Greeting Plugin"
            description = "Mon premier plugin Gradle en Kotlin."
            tags = listOf("gradle", "kotlin", "greeting")
        }
    }
    website = "https://cheroliv.com"
    vcsUrl = "https://github.com/cheroliv/gradle-plugin-in-kotlin.git"
    testSourceSets(functionalTest)
}


publishing {
    publications {

        withType<MavenPublication> {
            // Nous allons nous fier à la signature automatique,
            // donc pas d'action spécifique ici liée à la signature.
        }
    }
}

signing {
    // Nous nous lions aux publications connues automatiquement par le plugin signing
    // et nous assurons que la commande gpg est utilisée.
    useGpgCmd()

}

tasks.check { dependsOn(functionalTestTask) }

