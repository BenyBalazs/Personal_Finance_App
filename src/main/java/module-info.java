module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}