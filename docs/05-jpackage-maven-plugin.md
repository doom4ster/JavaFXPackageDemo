# jpackage Maven 外掛
雖然 [JavaFX Maven 外掛](04-javafx-maven-plugin.md) 會為應用程式建立獨立的 Java 執行環境映像檔，
但使用者仍然得下載映像檔資料夾、解壓縮，再往下鑽好幾層資料夾才能找到應用程式的啟動程式。
直白地說，這樣的使用者體驗很差。因此，jpackage Maven 外掛使用
[jpackage](https://docs.oracle.com/en/java/javase/26/jpackage/packaging-overview.html)，
把應用程式的 Java 執行環境映像檔打包成平台專用的安裝檔，針對你目前的目標作業系統量身打造。

如同在 [pom.xml 屬性](03-pom-xml-properties.md) 中說明的，這會透過 Maven profile，
依據你在哪個作業系統上建置 JavaFX 應用程式而自動決定。

## 安裝檔類型
會產生的 3 種安裝檔如下：

- Windows：`.exe`（Windows 執行檔）
- Linux：`AppImage`（Linux 應用程式映像檔）
- macOS：`.dmg`（macOS 磁碟映像檔）

Linux 特別選用 AppImage 的原因是，就我撰寫本文時所知，jpackage 在 Linux 上唯一的其他選項是 `.deb` 和 `.rpm`，
它們分別只適用於 Debian 和 Red Hat 系的發行版；而 AppImage（大致上）適用於所有發行版。
雖然不理想，但這就是使用一個有 101 種打包方案的作業系統所要付出的代價。

要特別強調的是，**本專案只會為正在建置應用程式的那個作業系統產生安裝檔**。
例如，如果你在 Windows 電腦上進行本機建置，就只會產生 `.exe` 安裝檔。
因此，如果你想要一鍵為上述每個作業系統打包應用程式，建議設定某種 CI/CD 流程，不過這超出了本指南的範圍。

## jpackage Maven 外掛選項：

- `name`：最終應用程式的名稱
- `type`：要建置的安裝檔類型（在建置時透過 [Maven 外掛屬性](03-pom-xml-properties.md) 決定）
- `app version`：應用程式的版本號。它不會自動更新，雖然大概可以做到（但這超出了本指南的範圍）。
- `destination`：打包好的安裝檔存放位置（在本專案中，就是 target 底下專屬的 jpackage 資料夾）。
- `module`：與直覺相反，這裡可以直接指向應用程式的主類別，而不一定要指向 module-info.java。
- `runtimeImage`：要打包進安裝檔的執行環境映像檔位置。這就是為什麼必須先執行 [JavaFX Maven 外掛](04-javafx-maven-plugin.md)：
如果沒有先執行，就沒有執行環境映像檔可以打包。
