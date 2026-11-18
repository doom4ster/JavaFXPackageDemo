## JavaFX 打包示範
這是一個小型專案，目的是示範如何使用 Maven 建置工具，將 JavaFX 應用程式成功打包成獨立、可直接發佈的安裝檔。

以下文件提供深入說明，解釋這套打包方式如何運作，以及為什麼這樣設計。

- [事前準備](docs/01-prerequisites.md)：依作業系統區分的建置需求（撰寫中）
- [打包架構](docs/02-packaging-architecture.md)：打包系統運作方式的整體概覽
- [Maven pom.xml 屬性](docs/03-pom-xml-properties.md)：`pom.xml` 中各個通用屬性代表的意義，以及為什麼要設成屬性
- [JavaFX Maven 外掛](docs/04-javafx-maven-plugin.md)：說明用來建立應用程式自訂 Java 執行環境映像檔的外掛
- [jpackage Maven 外掛](docs/05-jpackage-maven-plugin.md)：說明將應用程式最終映像檔打包成各作業系統專用安裝檔的外掛
