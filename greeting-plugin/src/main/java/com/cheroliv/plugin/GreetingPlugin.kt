package com.cheroliv.plugin

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register(
            "greet",
            Action { task ->
                task.doLast(
                    Action { s ->
                        "Hello from plugin 'com.cheroliv.plugin.greeting'"
                            .run(::println)
                    })
            }
        )
    }
}
