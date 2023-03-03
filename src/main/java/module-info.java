module com.hit {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;
    requires java.sql;

    opens com.hit.view to javafx.fxml;
    exports com.hit.view;
    exports com.hit.controller;
    opens com.hit.controller;
    opens com.hit.dm;
}