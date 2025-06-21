package com.cheroliv.plugin

import org.gradle.testkit.runner.GradleRunner.create
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files.createDirectories

class GreetingPluginFunctionalTest {
    @Test
    @Throws(IOException::class)
    fun canRunTask() {
        // Setup the test build
        val projectDir = File("build/functionalTest")
        createDirectories(projectDir.toPath())
        writeString(File(projectDir, "settings.gradle"), "")
        writeString(
            File(projectDir, "build.gradle"),
            "plugins {" + "  id('com.cheroliv.plugin.greeting')" + "}"
        )

        // Run the build
        val result = create()
            .forwardOutput()
            .withPluginClasspath()
            .withArguments("greet")
            .withProjectDir(projectDir)
            .build()

        // Verify the result
        assertTrue(result.output.contains("Hello from plugin 'com.cheroliv.plugin.greeting'"))
    }

    @Throws(IOException::class)
    private fun writeString(file: File, string: String) {
        FileWriter(file).use { writer -> writer.write(string) }
    }
}
