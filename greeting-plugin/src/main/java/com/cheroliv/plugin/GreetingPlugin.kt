package com.cheroliv.plugin

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register(
            "greet",
            Action { task: Task? ->
                task!!.doLast(Action { s: Task? -> println("Hello from plugin 'com.cheroliv.plugin.greeting'") })
            }
        )
    }
}
