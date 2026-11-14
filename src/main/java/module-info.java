module io.senftresearch.javafxpackagedemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.senftresearch.javafxpackagedemo to javafx.fxml;
    exports io.senftresearch.javafxpackagedemo;
}