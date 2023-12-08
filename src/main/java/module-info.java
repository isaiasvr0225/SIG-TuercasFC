module com.sig_tuercasfc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.sig_tuercasfc to javafx.fxml;
    exports com.sig_tuercasfc;
    exports com.sig_tuercasfc.controller;
    exports com.sig_tuercasfc.exceptions;
    opens com.sig_tuercasfc.controller to javafx.fxml;
    opens com.sig_tuercasfc.exceptions to javafx.fxml;
}