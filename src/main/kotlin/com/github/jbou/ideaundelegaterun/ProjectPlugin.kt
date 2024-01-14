package com.github.jbou.ideaundelegaterun

import com.intellij.execution.configurations.RunProfile
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.module.Module
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

@State(name = "undelegate-run", reloadable = true, storages = [Storage("undelegate-run.xml")])
class ProjectPlugin : PersistentStateComponent<ProjectPlugin.State> {
    private val stateLock = Any()
    private var state: State? = null

    init {
        synchronized(stateLock) {
            state = State()
        }
    }

    override fun getState(): State {
        synchronized(stateLock) {
            return State(state)
        }
    }

    override fun loadState(state: State) {
        synchronized(stateLock) {
            this.state = State(state)
        }
    }

    class State {
        var isEnabled: Boolean = true
        private var excludedModules = ""
        private var excludedRunConfigurations = ""

        constructor()

        constructor(that: State?) {
            this.isEnabled = that!!.isEnabled
            this.excludedModules = that.excludedModules
            this.excludedRunConfigurations = that.excludedRunConfigurations
        }

        fun getExcludedModules(): String {
            return excludedModules
        }

        fun setExcludedModules(excludedModules: String?) {
            this.excludedModules = excludedModules ?: ""
        }

        fun getExcludedRunConfigurations(): String {
            return excludedRunConfigurations
        }

        fun setExcludedRunConfigurations(excludedRunConfigurations: String?) {
            this.excludedRunConfigurations = excludedRunConfigurations ?: ""
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false
            val that = other as State
            if (isEnabled != that.isEnabled) return false
            if (excludedModules != that.excludedModules) return false
            return excludedRunConfigurations == that.excludedRunConfigurations
        }

        override fun hashCode(): Int {
            var result = (if (isEnabled) 1 else 0)
            result = 31 * result + excludedModules.hashCode()
            result = 31 * result + excludedRunConfigurations.hashCode()
            return result
        }
    }

    companion object {
        private val LOG = Logger.getInstance(
            ProjectPlugin::class.java
        )

        fun isEnabledFor(runProfile: RunProfile, module: Module?): Boolean {
            if (module == null) {
                return false
            }
            val state = module.project.getComponent(
                ProjectPlugin::class.java
            ).getState()
            if (!state.isEnabled) {
                return false
            }
            if (isExcluded(state.getExcludedModules(), module.name)) {
                return false
            }
            if (isExcluded(state.getExcludedRunConfigurations(), runProfile.name)) {
                return false
            }
            return true
        }

        private fun isExcluded(pattern: String, string: String): Boolean {
            if (pattern.isBlank()) {
                return false
            }
            try {
                return Pattern.compile(pattern).matcher(string).find()
            } catch (e: PatternSyntaxException) {
                LOG.error("Illegal pattern: $pattern", e)
                return false
            }
        }
    }
}
