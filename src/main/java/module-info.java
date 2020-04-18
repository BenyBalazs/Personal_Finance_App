module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires org.jdbi.v3.core;
    requires mysql.connector.java;
    requires java.sql;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.orm.core;
    requires java.persistence;

    opens org.openjfx to javafx.fxml;
    opens Modells to javafx.base;
    opens Database to javafx.base;
    exports org.openjfx;
}