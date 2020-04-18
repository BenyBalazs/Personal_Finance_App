module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;

    opens org.openjfx to javafx.fxml;
    opens Modells to javafx.base;
    opens Database to javafx.base;
    exports org.openjfx;
}