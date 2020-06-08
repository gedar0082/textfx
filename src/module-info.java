module testfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens sample;
}