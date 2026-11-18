# JavaFX Maven 外掛
執行 JavaFX 應用程式時，通常需要使用者擁有包含 JavaFX 的 JDK/JRE。這會造成好幾個問題，
其中最主要的是，一般的 JDK 已經不再內附 JavaFX。JavaFX Maven 外掛解決了這個問題：
它會為應用程式建立自訂的 Java 執行環境映像檔，讓 JavaFX 應用程式不依賴使用者電腦上的 JDK/JRE 設定也能執行。

這個外掛的官方文件在[這裡](https://github.com/openjfx/javafx-maven-plugin)，不過官方文件預設讀者已經具備 jlink 等概念的基礎知識，
所以以下會詳細說明外掛的主要部分及其運作方式。這個外掛會在「package」階段執行建置，
確保在呼叫 [jpackage Maven 外掛](05-jpackage-maven-plugin.md) 打包之前，Java 執行環境映像檔已經建立完成。

## JavaFX 執行選項
這個外掛在 `pom.xml` 中定義了多個執行選項。在本專案的設定中，只有一個選項與 jlink 無關。

`mainClass` 是應用程式主類別的路徑。說得更明確一點，這是作為應用程式進入點的類別。
所以在本專案中，它是 `HelloApplication.java` 類別，而不是 `Launcher.class`。

## jlink 工具與選項
JavaFX Maven 外掛設定中的大部分選項都與 jlink 有關。jlink 工具正是讓外掛能建立自訂 Java 執行環境映像檔的關鍵。
這個映像檔只會包含執行應用程式所需的相依套件，因此體積可以小很多。我個人覺得 jlink 的問題在於，
恕我直言，Oracle 的 [jlink 官方文件](https://docs.oracle.com/en/java/javase/21/docs/specs/man/jlink.html)並不怎麼清楚簡潔。
因此，本文會著重在如何透過 JavaFX Maven 外掛使用 jlink。

以下是與 jlink 直接相關的 JavaFX Maven 外掛選項：
- `launcher`：定義一個啟動應用程式的腳本，指向自訂的 Java 執行環境以及應用程式的主類別（在本專案中即 `HelloApplication.java`）。
- `jlinkImageName`：設定映像檔資料夾的名稱。這個名稱很重要，因為之後 [jpackage Maven 外掛](05-jpackage-maven-plugin.md) 會直接使用它。
- `jlinkZipName`：設定映像檔壓縮版本的名稱。_（注意：目前壓縮檔和映像檔的名稱都是寫死的。我考慮過是否改成一般的 pom.xml 屬性會比較好，
但最後沒有這麼做，因為我不確定是否會有需要讓壓縮檔和映像檔使用不同名稱的情況。）_
- `noManPages`：從 Java 執行環境映像檔中移除命令列工具說明文件（man pages）的資料夾。由於這個映像檔最終會被打包成獨立的應用程式，
不需要這些文件，因此移除它們以縮小映像檔。
- `stripDebug`：移除映像檔中的除錯資訊（例如原始檔資訊、行號表等中繼資料）。這可以縮小映像檔，
但相較於開發用的建置版本，直接在這個映像檔上除錯會比較困難。
- `noHeaderFiles`：從映像檔中移除原生標頭檔（header files）。如果 JavaFX 應用程式不打算使用原生功能（例如透過 Java Native Interface），
通常不需要這些標頭檔，因此可以移除以縮小映像檔。
