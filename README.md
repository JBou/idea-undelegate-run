# Un-Delegate Run Actions IDEA Plugin

![Build](https://github.com/JBou/idea-undelegate-run/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/com.github.jbou.ideaundelegaterun.svg)](https://plugins.jetbrains.com/plugin/24216-un-delegate-run-actions)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/com.github.jbou.ideaundelegaterun.svg)](https://plugins.jetbrains.com/plugin/24216-un-delegate-run-actions)

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
Execution, Deployment / Build Tools / Un-Delegate Run Actions`.
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