# Un-Delegate Run Actions IDEA Plugin

![Build](https://github.com/JBou/idea-undelegate-run/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Template ToDo list
- [ ] Adjust the [pluginGroup](./gradle.properties), [plugin ID](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [ ] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the `PLUGIN_ID` in the above README badges.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

Description
------------

<!-- Plugin description -->
This is a small plugin for IDEA that allows to only delegate build actions to Gradle, but not run actions.
It's a proof of concept, I'm hoping to get this functionality integrated into IDEA.

This Plugin is based on https://github.com/Abnaxos/idea-undelegate-run.
All credits go to that project.
It was just updated to support newer IntelliJ versions.

Allows to delegate build actions to Gradle, but keep run actions in
IDEA.
This way, you get the best of both worlds: Gradle builds, IDEA
runs.
Enable and configure the un-delegation in `Settings / Build,
Execution, Deployment / Un-Delegate Run Actions`.
Tested with Gradle only, but it may also work with Maven
delegation.

There is already an issue to add this functionality:
https://youtrack.jetbrains.com/issue/IDEA-176987/Delegate-only-build-to-Gradle
<!-- Plugin description end -->


Known Issues
------------

- Class reloading doesn't work. Class reloading actually never works when
  delegating build/run actions to Gradle, so this isn't a new issue. But
  the plugin unfortunately doesn't fix it.

Build
-----

```
./gradlew build
```

Usage
-----

The plugin adds a settings panel in *Build, Exection, Deployment / Build Tools / Un-Delegate Run Actions*.

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "idea-undelegate-run"</kbd> >
  <kbd>Install</kbd>
  
- Manually:

  Download the [latest release](https://github.com/JBou/idea-undelegate-run/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation


License
-------

[WTFPL](http://www.wtfpl.net/)