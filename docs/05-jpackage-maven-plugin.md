# The jpackage Maven Plugin
Whilst the [JavaFX Maven Plugin](04-javafx-maven-plugin.md) creates a self-contained Java runtime image of the application,
this still requires end-users to download the image directory, extract it, and go several directories deep to find the 
launcher for the application. This is, to be blunt, a poor end-user experience. As such, the jpackage Maven Plugin utilises
[jpackage](https://docs.oracle.com/en/java/javase/26/jpackage/packaging-overview.html) to package the Java runtime image of
the application to be packaged into a platform-specific distributable, tailored to the Operating System you are currently
targeting.

As explained when discussing the [pom.xml properties](03-pom-xml-properties.md), this is determined automatically, dependent
on which Operating System you build the JavaFX Application on, via Maven Profiles. 

## Distributable Types
The 3 types of distributable that are made
are as follows:

- Windows: `.exe` (Windows Executable)
- Linux: `AppImage` (Linux Application Image)
- macOS: `.dmg` (macOS disk image)

The rationale behind Linux specifically using AppImage is due to the only other options (as far as I am aware of at the time
of writing) for jpackage to use for Linux are `.deb` and `.rpm`, which only cater to debian and red hat distros specifically, 
whereas AppImage caters to every Distro (more or less). Whilst not ideal, this is the price we pay for having an Operating System
with 101 different packaging solutions. 

It is important to clarify **The project will only build a distributable for the specific operating system the Application 
is being built on**. I.e. if you run the build on a Windows PC locally, it will only produce the `.exe` distributable. As
such, it is advised to set up some sort of CI/CD pipeline if you wish a "one click and done" solution for packaging applications
for each of the OS' covered, though this is beyond the scope of this guide. 

## The jpackage Maven Plugin Options: 

- `name`: The name of the final application
- `type`: The type of distributable to build (determined at build-time via the [Maven Plugin properties](03-pom-xml-properties.md))
- `app version`: The version number of the application. This is not automatically updated, but probably could be (but is beyond
the scope of this guide). 
- `destination`: The destination of the packaged distributable (in this case, simply its own dedicated jpackage directory within target).
- `module`: This, counter-intuitively, can point towards the main class of the application itself, rather than specifically
the module-info.java instance.
- `runtimeImage`: The location of the runtime image to package into the distributable. This is why the [JavaFX Maven Plugin](04-javafx-maven-plugin.md)
is required to run first. As if it does not, there would be no runtime image to package. 