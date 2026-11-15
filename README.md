## JavaFX Packaging Demo
This is a small project created with the purpose of demonstrating how to successfully create a self-contained, packaged
distributable of JavaFX applications, utilising the Maven build tool.

The following documentation has been added to provide in-depth guidance on how this packaging solution works, and why. 

- [Prerequisites](docs/01-prerequisites.md): General, per Operating System, requirements to build the application (WIP)
- [Packaging Architecture](docs/02-packaging-architecture.md): Top-level overview of how the packaging system functions
- [The Maven pom.xml Properties](docs/03-pom-xml-properties.md): What each of the general properties of the `pom.xml` represent, and why they are properties.
- [The JavaFX Maven Plugin](docs/04-javafx-maven-plugin.md): Explanation of the plugin used for creating the custom Java runtime image of the application.
- [The jpackage Maven Plugin](docs/05-jpackage-maven-plugin.md): Explanation of the plugin that packages the final image of the application into an OS-specific Distributable.