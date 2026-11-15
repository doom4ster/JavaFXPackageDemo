# The JavaFX Maven Plugin
When running a JavaFX application, it would typically be required for the end user to have a JDK/JRE distribution that 
includes JavaFX. This is a problem for several reasons, but first and foremost is that JavaFX is no longer packaged 
within typical JDKs. The JavaFX Maven Plugin provides a solution to this problem. The plugin creates a custom Java 
runtime image of the application. This allows the JavaFX application to run independently of the end-user's local JDK/JRE
setup.

Whilst the documentation for the plugin can be found [here](https://github.com/openjfx/javafx-maven-plugin), the following
section will go into details of the main aspects of the plugin, and how it works, as the documentation for the plugin
assumes a base knowledge of concepts such as jlink. This Plugin executes the build during the "package" phase. This is to 
ensure the Java runtime image is completed before [jpackage maven plugin](05-jpackage-maven-plugin.md) is called to package
it.

## JavaFX Run Options
The plugin defines various run options within the `pom.xml`. In this setup, only one of them is a non-jlink related option.

The `mainClass`is the path of the main class of the application. To clarify this is the class that acts as the entry point 
of the application. So, for this project, it is the `HelloApplication.java` class, not the `Launcher.class`.

## The jlink Tool & Options
The majority of the options found within the JavaFX Maven Plugin configuration are related to jlink. The jlink tool is what
specifically allows the plugin to create a custom Java runtime image. This image will only contain the dependencies required
to run the application, allowing it to be a much smaller size. The issue I personally have found with jlink is, with the 
greatest of respect to Oracle, their [official documentation for jlink](https://docs.oracle.com/en/java/javase/21/docs/specs/man/jlink.html)
is not exactly clear and concise. As such, this guide will focus on the ability to utilise jlink via the JavaFX Maven Plugin.

The following JavaFX Maven Plugin options are related to jlink directly: 
- `launcher`: This will define a script that launches the application, directing to the custom Java Runtime and the 
main class of the application (i.e. `HelloApplication.java` in this case).
- `jlinkImageName`: Sets the name of the directory of the image, which will be useful as it will be called directly by
the [jpackage maven plugin](05-jpackage-maven-plugin.md) later.
- `jlinkZipName`: Sets the name of the zipped directory version of the image. _(Note, at the moment the name of the zip
and image names are hardcoded. Have debated if it would be better as a general pom.xml property, but ultimately refrained
, as I am not sure if there could be use cases where you want different names for the zip and image.)_
- `noManPages`: Simply removes the directory from the Java runtime image that contain the documentation about command-line
tools for the image. As this image will eventually package up into a standalone app, there is no need for this documentation
to be present, hence they are removed to reduce image size.
- `stripDebug`: This strips the debug information (such as metadata like source file info, line number tables etc.) from
the image. This reduces the image size, but means it is more difficult to directly debug on the image directly (as opposed
to say a developer build).
- `noHeaderFiles`: Removes the native header files from the Image. Any JavaFX application that is not intending to use native
functionality (via something such as Java Native Interface) these header files are not typically needed, hence can be removed
to reduce image size. 




