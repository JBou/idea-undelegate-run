package com.github.jbou.ideaundelegaterun

import com.intellij.execution.Executor
import com.intellij.execution.application.ApplicationConfiguration
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project
import com.intellij.task.ExecuteRunConfigurationTask
import org.jetbrains.plugins.gradle.execution.build.GradleExecutionEnvironmentProvider

class UndelegateExecutionEnvironmentProvider : GradleExecutionEnvironmentProvider {
    override fun isApplicable(task: ExecuteRunConfigurationTask): Boolean {
        if (task.runProfile !is ApplicationConfiguration) {
            return false
        }
        val runProfile = task.runProfile as ApplicationConfiguration
        return ProjectPlugin.isEnabledFor(runProfile, runProfile.configurationModule.module)
    }

    override fun createExecutionEnvironment(
        project: Project,
        task: ExecuteRunConfigurationTask,
        executor: Executor
    ): ExecutionEnvironment? {
        return null
    }
}
