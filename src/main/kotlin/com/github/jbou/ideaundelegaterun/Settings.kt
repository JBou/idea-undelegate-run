package com.github.jbou.ideaundelegaterun

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.VerticalFlowLayout
import com.intellij.ui.DocumentAdapter
import org.jetbrains.annotations.Nls
import java.awt.BorderLayout
import java.awt.Cursor
import javax.swing.*
import javax.swing.event.DocumentEvent

class Settings(project: Project) : Configurable {
    private val plugin: ProjectPlugin? = project.getComponent(ProjectPlugin::class.java)

    private var state: ProjectPlugin.State? = null

    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "Un-Delegate Run Actions"
    }

    override fun createComponent(): JComponent {
        // nullability for searchable index builder
        state = plugin().state
        val container = JPanel(BorderLayout())
        container.border = BorderFactory.createEmptyBorder(5, 5, 5, 5)
        val settings = JPanel(VerticalFlowLayout(true, false))
        container.add(settings, BorderLayout.NORTH)
        // enabled check box
        var line = JPanel(BorderLayout())
        val enabled = JCheckBox("Un-Delegate Run Actions")
        enabled.isSelected = state!!.isEnabled
        enabled.addChangeListener { state!!.isEnabled = enabled.isSelected }
        line.add(enabled, BorderLayout.WEST)
        settings.add(line)
        // exclude modules regex
        line = JPanel(BorderLayout(5, 5))
        line.add(JLabel("Excluded Modules (Regex contains)"), BorderLayout.WEST)
        val excludedModules = JTextField()
        excludedModules.cursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)
        excludedModules.text = state!!.getExcludedModules()
        excludedModules.document.addDocumentListener(object : DocumentAdapter() {
            override fun textChanged(e: DocumentEvent) {
                state!!.setExcludedModules(excludedModules.text)
            }
        })
        line.add(excludedModules, BorderLayout.CENTER)
        settings.add(line)
        // exclude run configurations regex
        line = JPanel(BorderLayout(5, 5))
        line.add(JLabel("Excluded Run Configurations (Regex contains)"), BorderLayout.WEST)
        val excludedRunConfs = JTextField()
        excludedRunConfs.cursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)
        excludedRunConfs.text = state!!.getExcludedRunConfigurations()
        excludedRunConfs.document.addDocumentListener(object : DocumentAdapter() {
            override fun textChanged(e: DocumentEvent) {
                state!!.setExcludedRunConfigurations(excludedRunConfs.text)
            }
        })
        line.add(excludedRunConfs, BorderLayout.CENTER)
        settings.add(line)
        return container
    }

    override fun isModified(): Boolean {
        return plugin().state != state
    }

    override fun apply() {
        plugin().loadState(state!!)
    }

    private fun plugin(): ProjectPlugin {
        if (plugin == null) {
            LOG.error("Plugin is null; that should only happen during indexing")
            return ProjectPlugin()
        } else {
            return plugin
        }
    }

    companion object {
        private val LOG = Logger.getInstance(Settings::class.java)
    }
}
