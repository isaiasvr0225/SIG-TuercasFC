module com.sig_tuercasfc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.sig_tuercasfc to javafx.fxml;
    exports com.sig_tuercasfc;
}