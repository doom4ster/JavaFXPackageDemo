# Packaging Architecture
My personal gripe with a lot of the "guidance" about JavaFX packaging out there is, frankly, they fail to give a
general overview of how the packaging actually works. As useful as a complete pom.xml file is, I find it is quite a 
frustrating exercise to have to figure out what such "complete" solutions are actually doing to achieve the end-result.

As such, the following will be a top-level-view of how the packaging of a JavaFX app works, and what each individual part 
of the packaging process is responsible for. 

## Workflow Overview
The workflow of the packaging process is actually quite simple. The workflow is designed to solve 3 separate problems:

- How do you specify which Operating System you wish to package a JavaFX application for?
- How do you build a JavaFX application that only has what I need in it?
- How to package the built JavaFX application as a standalone application? 

These 3 problems are solved by the pom.xml properties, javafx-maven-plugin, and jpackage-maven-plugin respectfully.
![img.png](images/package-workflow-diagram.png)![workflow.png](images/package-workflow-diagram.png)


### The Maven pom.xml Properties
The `pom.xml` file will utilise properties heavily, for the sake of simplifying the build process. Specific to JavaFX,
the main class path, application name and version, along with that package type, as determined by the various OS
profiles. The appropriate profile will be used, based on the Operating System the package is being built from.

This is better than hard coding the various information throughout the `pom.xml` for three reasons:

1. Having all the variable properties in one location, near the top of the pom.xml, makes it easier to both find and
edit them. When key information is scattered throughout the `pom.xml`, it is not uncommon for one of the properties to
be missed when doing updates.
2. Properties that are in several of the plugins utilised (such as the main class path for the JavaFX application) do
not need changing in several places, as there is a single source of truth. 
3. Due to the OS profiles, a developer can package the JavaFX application utilising the same `pom.xml`, regardless of
the Operating System the application is being packaged on.

### The JavaFX Maven Plugin
The JavaFX Maven Plugin has a relatively simple purpose: creating a runtime image of the JavaFX application. In this context,
the runtime image is a self-contained package that contains the Java runtime and all the modules required to run the 
JavaFX application. This runtime image is typically set up with only the components required by the JavaFX application. 
This makes it possible to package the JavaFX application with its own, custom runtime. This allows the application to
run without the end-user having to install Java separately. 

On the surface, this seems to be a problem, as packaging the entirety of the Java runtime for the sake of a singular 
application would result in an unnecessarily large distribution. The solution is to utilise an in-built feature of the 
JavaFX maven plugin, options for utilising jlink.  

Jlink is a tool that allows the generation of a Java runtime image containing only the modules required for the 
JavaFX application to run. This results in a significantly smaller Java runtime that is tailored to the requirements of
the application. 
### The JPackage Maven Plugin
Whilst you now have a custom Java runtime image, distributing your JavaFX application in this form will not lead to a
very convenient experience for end-users attempting to use your application. Depending on the operating system, the 
end-user would have to download and extract the zipped runtime image themselves, locate the executable within the extracted
directory, and create their own QoL features, such as desktop shortcuts.

This is where jpackage (utilised via the jpackage Maven Plugin) comes in. The plugin allows for the JavaFX application, 
along with its custom runtime image, to be packaged into a platform-specific distributable, tailored to the targeted OS.
