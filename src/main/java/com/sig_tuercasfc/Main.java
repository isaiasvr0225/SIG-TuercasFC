package com.sig_tuercasfc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

// Clase principal, se encarga de cargar la interfaz de inicio de sesión (LoginInterfaz.fxml) con el metodo start()
// e invocar el metodo launch() el cual inicia la aplicación.
//Los metodos start() y lunch() son metodos heredados de la clase Application de JavaFX, son parte de la API de JavaFX.
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginInterfaz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}