# The Maven pom.xml Properties
Within the `pom.xml`, a number of properties have been set up to make the setup / maintaining of the application / its
packaging easier long term. This includes both general application properties, along with Operating System specific
profiles. This ensures that there is a single source of truth for all the critical properties of the application, 
reducing the chance of properties being forgotten about / missed when updating the application. 

## Operating System Profiles
As the type of packaged distributable is Operating-System dependant, this needs to be specified within the packaging 
workflow. This poses a problem, as this would require 3 separate `pom.xml` instances (one for each Operating System). 
Luckily, in maven, conditional profiles can be set up to make this easier. 

In this case: 

```xml
<profiles>

        <profile>
            <id>linux</id>
            <activation>
                <os>
                    <name>Linux</name>
                </os>
            </activation>
            <properties>
                <app.package.type>APP_IMAGE</app.package.type>
            </properties>
        </profile>

        <profile>
            <id>windows</id>
            <activation>
                <os>
                    <family>windows</family>
                </os>
            </activation>
            <properties>
                <app.package.type>EXE</app.package.type>
            </properties>
        </profile>

        <profile>
            <id>macos</id>
            <activation>
                <os>
                    <family>mac</family>
                </os>
            </activation>
            <properties>
                <app.package.type>DMG</app.package.type>
            </properties>
        </profile>

    </profiles>
```

Each profile is provided with an appropriate id (technically, the id for these profiles can be anything you want, but it
makes sense to name after their respective OS). To ensure the correct profile is used on the correct OS, the `<activation>`
element is utilised. This is conditional logic that determines if the profile will be utilised during the build. In this
case, the type of operating system is utilised. _(Note: The Linux profile uses `name` rather than `family` as, counter 
intuitively, whilst macOS has its own dedicated `mac` family, it also falls under the `unix` family (which is also utilised
for Linux, as it does not have its own dedicated family)_.

Once a profile has been determined, the build will use that profile's version of `app.package.type` for any configuration
of the build that requires it. 

## Application Properties:
Below are explanations for where each of the properties are utilised within the packaging workflow:

- `project.build.sourceEncoding`: This is a standard property that informs Maven to utilise UTF-8 character encoding 
when reading / compiling the project's files. 
- `maven.compiler.release`: Utilised to set the version of Java to utilise when compiling. Technically, this is only used
in one place, but is set as a property so there is a single source of truth for the key properties.
- `javafx.version`: The version of JavaFX to use for both the `javafx-controls` and `javafx-fxml` dependencies.
- `junit.version` : The version of JUnit to utilise during the build. Like the `maven.compiler.release`, it is only a 
property to ensure a single source of truth for key properties. 
- `app.name` : The name of the final packaged application.
- `app.version` : The version number of the packaged application.
- `app.mainClass` : The path to the main class of the application. This is handy as both the [JavaFX Maven Plugin](04-javafx-maven-plugin.md) and 
[jpackage Maven Plugin](05-jpackage-maven-plugin.md) utilise the class path, ensuring that any updates to that path are
captured by both plugins via a single change. 