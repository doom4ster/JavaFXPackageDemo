# Maven pom.xml 屬性
`pom.xml` 中設定了一些屬性，讓應用程式及其打包的設定與長期維護更容易。這包括一般的應用程式屬性，
以及各作業系統專用的 profile。這樣可以確保應用程式所有關鍵屬性都只有單一來源，
降低更新應用程式時忘記或漏改屬性的機會。

## 作業系統 Profile
由於打包出來的安裝檔類型取決於作業系統，這必須在打包流程中指定。這會造成一個問題：
這樣就需要 3 份不同的 `pom.xml`（每個作業系統一份）。幸好 Maven 可以設定條件式 profile，讓這件事變簡單。

在本專案中：

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

每個 profile 都有一個適當的 id（技術上這些 profile 的 id 可以隨便取，但以對應的作業系統命名比較合理）。
為了確保在正確的作業系統上使用正確的 profile，這裡使用了 `<activation>` 元素。
這是一段條件邏輯，決定建置時是否套用該 profile。在這裡，判斷依據是作業系統的類型。
_（注意：Linux profile 使用 `name` 而不是 `family`，因為與直覺相反，macOS 雖然有自己專屬的 `mac` family，
但它同時也屬於 `unix` family（Linux 也使用這個 family，因為 Linux 沒有自己專屬的 family））_。

確定使用哪個 profile 之後，建置過程中任何需要 `app.package.type` 的設定，都會使用該 profile 中的值。

## 應用程式屬性：
以下說明各個屬性在打包流程中用在哪裡：

- `project.build.sourceEncoding`：標準屬性，告訴 Maven 在讀取和編譯專案檔案時使用 UTF-8 字元編碼。
- `maven.compiler.release`：設定編譯時使用的 Java 版本。技術上它只在一個地方被使用，
但設成屬性是為了讓關鍵屬性只有單一來源。
- `javafx.version`：`javafx-controls` 和 `javafx-fxml` 兩個相依套件使用的 JavaFX 版本。
- `junit.version`：建置時使用的 JUnit 版本。和 `maven.compiler.release` 一樣，設成屬性只是為了讓關鍵屬性只有單一來源。
- `app.name`：最終打包出的應用程式名稱。
- `app.version`：打包出的應用程式版本號。
- `app.mainClass`：應用程式主類別的路徑。這很方便，因為 [JavaFX Maven 外掛](04-javafx-maven-plugin.md) 和
[jpackage Maven 外掛](05-jpackage-maven-plugin.md) 都會用到這個類別路徑，只要改一處，兩個外掛都會套用更新。
