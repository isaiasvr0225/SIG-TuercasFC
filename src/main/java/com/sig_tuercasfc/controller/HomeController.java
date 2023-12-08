package com.sig_tuercasfc.controller;

import com.sig_tuercasfc.domain.Jugadores;
import com.sig_tuercasfc.model.JugadoresDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static com.sig_tuercasfc.model.SetConnection.close;

public class ServerController2 implements Initializable {
    @FXML
    private Pane admCuerpoTecnicoBtn;

    @FXML
    private AnchorPane admCuerpoTecnicoPage;

    @FXML
    private Pane admFuncionariosBtn;

    @FXML
    private AnchorPane admFuncionariosPage;

    @FXML
    private Pane admJugadoresBtn;

    @FXML
    private AnchorPane admJugadoresPage;

    @FXML
    private Pane helloPage;

    @FXML
    private TableView<Map> tableJugadores;
    @FXML
    private TableColumn<?,?> cedulaJugadoresColumna;
    @FXML
    private TableColumn<?,?> nombresJugadoresCoolumna;
    @FXML
    private TableColumn<?,?> apellidosJugadoresColumna;
    @FXML
    private TableColumn<?,?> telefonoJugadoresColumna;
    @FXML
    private TableColumn<?,?> correoJugadoresColumna;
    @FXML
    private TableColumn<?,?> direccionJugadoresColumna;
    @FXML
    private TableColumn<?,?> sueldoJugadoresColumna;


    ObservableList<Jugadores> listaJugadores;
    JugadoresDAO jugadoresDAO = new JugadoresDAO();
    //ObservableList<users> dataList;

    private void llenarTablaJugadores(){
        ObservableList<Map> jugadoresList = jugadoresDAO.select();

        this.cedulaJugadoresColumna.setCellValueFactory(new MapValueFactory("cedula"));
        this.nombresJugadoresCoolumna.setCellValueFactory(new MapValueFactory("nombres"));
        this.apellidosJugadoresColumna.setCellValueFactory(new MapValueFactory("apellidos"));
        this.telefonoJugadoresColumna.setCellValueFactory(new MapValueFactory("telefono"));
        this.correoJugadoresColumna.setCellValueFactory(new MapValueFactory("correo"));
        this.direccionJugadoresColumna.setCellValueFactory(new MapValueFactory("direccion"));
        this.sueldoJugadoresColumna.setCellValueFactory(new MapValueFactory("sueldo"));

        this.tableJugadores.setItems(jugadoresList);
    }

    @FXML
    void onCloseButtonClicked(MouseEvent event) {

    }

    @FXML
    void onAdmButtonClicked(MouseEvent event){
        String whoPage = ((Pane) event.getSource()).getId();
        switch (whoPage){
            case "admJugadoresBtn" -> {
                admJugadoresPage.setVisible(true);
                admCuerpoTecnicoPage.setVisible(false);
                admFuncionariosPage.setVisible(false);
                helloPage.setVisible(false);
            }
            case "admCuerpoTecnicoBtn" -> {
                admCuerpoTecnicoPage.setVisible(true);
                admJugadoresPage.setVisible(false);
                admFuncionariosPage.setVisible(false);
                helloPage.setVisible(false);
            }
            case "admFuncionariosBtn" -> {
                admFuncionariosPage.setVisible(true);
                admJugadoresPage.setVisible(false);
                admCuerpoTecnicoPage.setVisible(false);
                helloPage.setVisible(false);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llenarTablaJugadores();
    }
}
