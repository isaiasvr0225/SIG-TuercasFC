package com.sig_tuercasfc.controller;

import com.sig_tuercasfc.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController {
    @FXML
    private AnchorPane loginPage;
    @FXML
    private ImageView closeButton;
    @FXML
    private Button logingButton;
    @FXML
    private ImageView minimizeButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField userField;


    //Metodo para iniciar sesión y abrir la interfaz grafica Home (HomeInterfaz.fxml)
    @FXML
    void onLoginButtonAction(ActionEvent event) throws IOException {
        Alert alert;
        String user = userField.getText();//Obtiene el texto ingresado en el campo o field usuario
        String password = passwordField.getText();//Obtiene el texto ingresado en el campo o field contraseña

        if(user.equals("admin") && password.equals("1234")){//Verifica si el usuario = admin y contraseña = 1234 son correctos. Si es asi, abre la interfaz grafica Home (HomeInterfaz.fxml)
                Stage stage = (Stage) logingButton.getScene().getWindow();
                stage.close();//Cierra la ventana de inicio de sesión (LoginInterfaz.fxml) automaticamente

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomeInterfaz.fxml"));//Carga la interfaz grafica Home (HomeInterfaz.fxml)
                Scene scene = new Scene(fxmlLoader.load(), 1366, 725);
                Stage stage2 = new Stage(StageStyle.UNDECORATED);
                stage2.setScene(scene);
                stage2.show();

                //Mensaje de inicio de sesión exitoso
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensaje de información");
                alert.setHeaderText(null);
                alert.setContentText("Inicio de sesión exitoso!");
                alert.showAndWait();
            }else {//Si el usuario o contraseña son incorrectos, muestra un mensaje de error
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mensaje de error");
                alert.setHeaderText(null);
                alert.setContentText("Usuario o contraseña incorrectos!");
                alert.showAndWait();
            }



    }

    //Metodo para cerrar la ventana de inicio de sesión (LoginInterfaz.fxml)
    @FXML
    void onCloseButtonLoginClicked(MouseEvent event) {
        javafx.application.Platform.exit();
    }


    //Metodo para minimizar la ventana de inicio de sesión (LoginInterfaz.fxml)
    @FXML
    void onMinimizeButtonLoginClicked(MouseEvent event) {
        Stage stage = (Stage) logingButton.getScene().getWindow();
        stage.setIconified(true);
    }

}