package com.cheroliv.plugin

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert.assertNotNull
import org.junit.Test

class GreetingPluginTest {
    @Test
    fun pluginRegistersATask() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("com.cheroliv.plugin.greeting")

        // Verify the result
        assertNotNull(project.tasks.findByName("greet"))
    }
}
