package com.sig_tuercasfc.controller;

import com.sig_tuercasfc.Main;
import com.sig_tuercasfc.exceptions.ConnectionDBException;
import com.sig_tuercasfc.model.CuerpoTecnicoDAO;
import com.sig_tuercasfc.model.FuncionariosDAO;
import com.sig_tuercasfc.model.JugadoresDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
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
    private TextField cedulaJugadorField;
    @FXML
    private TextField nombresJugadorField;
    @FXML
    private TextField apellidosJugadorField;
    @FXML
    private TextField telefonoJugadorField;
    @FXML
    private TextField correoJugadorField;
    @FXML
    private TextField direccionJugadorField;
    @FXML
    private TextField sueldoJugadorField;


    @FXML
    private TableView<Map> tablaJugadores;
    @FXML
    private TableColumn<?, ?> cedulaJugadoresColumna;
    @FXML
    private TableColumn<?, ?> nombresJugadoresCoolumna;
    @FXML
    private TableColumn<?, ?> apellidosJugadoresColumna;
    @FXML
    private TableColumn<?, ?> telefonoJugadoresColumna;
    @FXML
    private TableColumn<?, ?> correoJugadoresColumna;
    @FXML
    private TableColumn<?, ?> direccionJugadoresColumna;
    @FXML
    private TableColumn<?, ?> sueldoJugadoresColumna;


    @FXML
    private TextField cedulaCuerpoTecnicoField;
    @FXML
    private TextField cargoCuerpoTecnicoField;

    @FXML
    private TextField nombresCuerpoTecnicoField;
    @FXML
    private TextField apellidosCuerpoTecnicoField;
    @FXML
    private TextField telefonoCuerpoTecnicoField;
    @FXML
    private TextField correoCuerpoTecnicoField;
    @FXML
    private TextField direccionCuerpoTecnicoField;
    @FXML
    private TextField sueldoCuerpoTecnicoField;


    @FXML
    private TableView<Map> tablaCuerpoTecnico;
    @FXML
    private TableColumn<?, ?> cedulaCuerpoTecnicoColumna;
    @FXML
    private TableColumn<?, ?> cargoCuerpoTecnicoColumna;
    @FXML
    private TableColumn<?, ?> nombresCuerpoTecnicoColumna;
    @FXML
    private TableColumn<?, ?> apellidosCuerpoTecnicoColumna;
    @FXML
    private TableColumn<?, ?> telefonoCuerpoTecnicoColumna;
    @FXML
    private TableColumn<?, ?> correoCuerpoTecnicoColumna;
    @FXML
    private TableColumn<?, ?> direccionCuerpoTecnicoColumna;
    @FXML
    private TableColumn<?, ?> sueldoCuerpoTecnicoColumna;


    @FXML
    private TextField cedulaFuncionarioField;
    @FXML
    private TextField cargoFuncionarioField;
    @FXML
    private TextField nombresFuncionarioField;
    @FXML
    private TextField apellidosFuncionarioField;
    @FXML
    private TextField telefonoFuncionarioField;
    @FXML
    private TextField correoFuncionarioField;
    @FXML
    private TextField direccionFuncionarioField;
    @FXML
    private TextField sueldoFuncionarioField;


    @FXML
    private TableView<Map> tablaFuncionarios;
    @FXML
    private TableColumn<?, ?> cedulaFuncionarioColumna;
    @FXML
    private TableColumn<?, ?> cargoFuncionarioColumna;
    @FXML
    private TableColumn<?, ?> nombresFuncionarioColumna;
    @FXML
    private TableColumn<?, ?> apellidosFuncionarioColumna;
    @FXML
    private TableColumn<?, ?> telefonoFuncionarioColumna;
    @FXML
    private TableColumn<?, ?> correoFuncionarioColumna;
    @FXML
    private TableColumn<?, ?> direccionFuncionarioColumna;
    @FXML
    private TableColumn<?, ?> sueldoFuncionarioColumna;


    //Se crean los objetos de las clases DAO para poder acceder a los metodos de cada una de ellas y asi poder realizar las operaciones de base de datos SQL
    JugadoresDAO jugadoresDAO = new JugadoresDAO();
    CuerpoTecnicoDAO cuerpoTecnicoDAO = new CuerpoTecnicoDAO();
    FuncionariosDAO funcionariosDAO = new FuncionariosDAO();

    //Este metodo se ejecuta al iniciar la interfaz grafica HomeInterfaz.fxml
    //Se encarga de llenar las tablas de la interfaz grafica con los datos de cada una de tablas de la base de datos
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Se llama al metodo llenarTablaJugadores de la clase JugadoresDAO para llenar la tabla de jugadores
        //Y se le pasa como parametros la tabla, las columnas de la tabla y los campos de texto de la interfaz grafica
        jugadoresDAO.llenarTablaJugadores(tablaJugadores, cedulaJugadoresColumna, nombresJugadoresCoolumna, apellidosJugadoresColumna,
                                          telefonoJugadoresColumna, correoJugadoresColumna, direccionJugadoresColumna, sueldoJugadoresColumna);

        //De igual manera con el resto, solo cambia el nombre del metodo, de la tabla y los campos o fields
        cuerpoTecnicoDAO.llenarTablaCuerpoTecnico(tablaCuerpoTecnico, cedulaCuerpoTecnicoColumna, cargoCuerpoTecnicoColumna, nombresCuerpoTecnicoColumna, apellidosCuerpoTecnicoColumna,
                                                  telefonoCuerpoTecnicoColumna, correoCuerpoTecnicoColumna, direccionCuerpoTecnicoColumna, sueldoCuerpoTecnicoColumna);

        funcionariosDAO.llenarTablaFuncionarios(tablaFuncionarios, cedulaFuncionarioColumna, cargoFuncionarioColumna, nombresFuncionarioColumna, apellidosFuncionarioColumna,
                                                telefonoFuncionarioColumna, correoFuncionarioColumna, direccionFuncionarioColumna, sueldoFuncionarioColumna);
    }



    //Este metodo se ejecuta al presionar el boton de agregar en alguna de las vistas de la interfaz grafica
    //Hay 3 vistas: Administrar Datos Jugadores, Administrar Datos Cuerpo Tecnico y Administrar Datos Funcionarios, por ende existen 3 botones de agregar, uno para cada vista
    @FXML
    void onAddButtonAction(ActionEvent event) throws ConnectionDBException {
        String whoAddButton = ((Button) event.getSource()).getId();//Se obtiene el id del boton agregar que se presiono

        switch (whoAddButton) {//Dependiendo del id del boton agregar que se presiono se ejecuta el codigo correspondiente
            case "addButtonJugadores" -> {//Por ejemplo, Si el id del boton agregar que se presiono es addButtonJugadores se ejecuta el metodo insert de la clase JugadoresDAO
                jugadoresDAO.insert(cedulaJugadorField, nombresJugadorField, apellidosJugadorField,
                        telefonoJugadorField, correoJugadorField, direccionJugadorField, sueldoJugadorField);

                //Se llama al metodo llenarTablaJugadores de la clase JugadoresDAO para llenar la tabla de jugadores
                //Esto hará que se actualice la tabla con los datos que se acaban de agregar
                jugadoresDAO.llenarTablaJugadores(tablaJugadores, cedulaJugadoresColumna, nombresJugadoresCoolumna, apellidosJugadoresColumna,
                        telefonoJugadoresColumna, correoJugadoresColumna, direccionJugadoresColumna, sueldoJugadoresColumna);
                cleanFields(event);//Se llama al metodo cleanFields para limpiar los campos o fields una vez se agregan los datos
            }

            //De igual manera con el resto de casos

            case "addButtonCuerpoTecnico" -> {
                cuerpoTecnicoDAO.insert(cedulaCuerpoTecnicoField, cargoCuerpoTecnicoField, nombresCuerpoTecnicoField, apellidosCuerpoTecnicoField,
                        telefonoCuerpoTecnicoField, correoCuerpoTecnicoField, direccionCuerpoTecnicoField, sueldoCuerpoTecnicoField);

                cuerpoTecnicoDAO.llenarTablaCuerpoTecnico(tablaCuerpoTecnico, cedulaCuerpoTecnicoColumna, cargoCuerpoTecnicoColumna, nombresCuerpoTecnicoColumna,
                        apellidosCuerpoTecnicoColumna, telefonoCuerpoTecnicoColumna, correoCuerpoTecnicoColumna, direccionCuerpoTecnicoColumna, sueldoCuerpoTecnicoColumna);
                cleanFields(event);
            }

            case "addButtonFuncionarios" -> {
                funcionariosDAO.insert(cedulaFuncionarioField, cargoFuncionarioField, nombresFuncionarioField, apellidosFuncionarioField,
                        telefonoFuncionarioField, correoFuncionarioField, direccionFuncionarioField, sueldoFuncionarioField);

                funcionariosDAO.llenarTablaFuncionarios(tablaFuncionarios, cedulaFuncionarioColumna, cargoFuncionarioColumna, nombresFuncionarioColumna, apellidosFuncionarioColumna,
                        telefonoFuncionarioColumna, correoFuncionarioColumna, direccionFuncionarioColumna, sueldoFuncionarioColumna);
                cleanFields(event);
            }
        }
    }


    /*Este metodo funciona casi igual que el metodo onAddButtonAction, solo que en vez de agregar datos a la base de datos,
     este metodo actualiza los datos de la base de datos */
    @FXML
    public void onUpdateButtonAction(ActionEvent event) throws ConnectionDBException {
        String whoUpdateButton = ((Button) event.getSource()).getId();//Se obtiene el id del boton actualizar que se presiono

        switch (whoUpdateButton) {//Dependiendo del id del boton actualizar que se presiono se ejecuta el codigo correspondiente
            case "updateButtonJugadores" -> {//Por ejemplo, si el id del boton actualizar que se presiono es updateButtonJugadores se ejecuta el metodo update de la clase JugadoresDAO
                jugadoresDAO.update(cedulaJugadorField, nombresJugadorField, apellidosJugadorField, telefonoJugadorField,
                                    correoJugadorField, direccionJugadorField, sueldoJugadorField);

                //Se llama al metodo llenarTablaJugadores de la clase JugadoresDAO para llenar la tabla de jugadores
                //Esto hará que se actualice la tabla con los datos que se acaban de actualizar
                jugadoresDAO.llenarTablaJugadores(tablaJugadores, cedulaJugadoresColumna, nombresJugadoresCoolumna, apellidosJugadoresColumna,
                                                  telefonoJugadoresColumna, correoJugadoresColumna, direccionJugadoresColumna, sueldoJugadoresColumna);

                cleanFields(event);//Se llama al metodo cleanFields para limpiar los campos o fields una vez se actualizan los datos
            }

            //De igual manera con el resto de casos

            case "updateButtonCuerpoTecnico" -> {
                cuerpoTecnicoDAO.update(cedulaCuerpoTecnicoField, cargoCuerpoTecnicoField, nombresCuerpoTecnicoField, apellidosCuerpoTecnicoField,
                                        telefonoCuerpoTecnicoField, correoCuerpoTecnicoField, direccionCuerpoTecnicoField, sueldoCuerpoTecnicoField);

                cuerpoTecnicoDAO.llenarTablaCuerpoTecnico(tablaCuerpoTecnico, cedulaCuerpoTecnicoColumna, cargoCuerpoTecnicoColumna, nombresCuerpoTecnicoColumna, apellidosCuerpoTecnicoColumna,
                                                          telefonoCuerpoTecnicoColumna, correoCuerpoTecnicoColumna, direccionCuerpoTecnicoColumna, sueldoCuerpoTecnicoColumna);
                cleanFields(event);
            }

            case "updateButtonFuncionarios" -> {
                funcionariosDAO.update(cedulaFuncionarioField, cargoFuncionarioField, nombresFuncionarioField, apellidosFuncionarioField,
                                       telefonoFuncionarioField, correoFuncionarioField, direccionFuncionarioField, sueldoFuncionarioField);

                funcionariosDAO.llenarTablaFuncionarios(tablaFuncionarios, cedulaFuncionarioColumna, cargoFuncionarioColumna, nombresFuncionarioColumna, apellidosFuncionarioColumna,
                                                        telefonoFuncionarioColumna, correoFuncionarioColumna, direccionFuncionarioColumna, sueldoFuncionarioColumna);
                cleanFields(event);
            }
        }
    }

    /*Este metodo funciona casi igual que el metodo onAddButtonAction y onUpdateButtonAction, solo que en vez de agregar o actualizar datos a la base de datos,
     este metodo elimina los datos de la base de datos */
    @FXML
    public void onDeleteButtonAction (ActionEvent event) throws ConnectionDBException{
        String whoDeleteButton = ((Button) event.getSource()).getId();//Se obtiene el id del boton eliminar que se presiono

        switch (whoDeleteButton) {//Dependiendo del id del boton eliminar que se presiono se ejecuta el codigo correspondiente
            case "deleteButtonJugadores" -> {//Por ejemplo, si el id del boton eliminar que se presiono es deleteButtonJugadores se ejecuta el metodo delete de la clase JugadoresDAO
                jugadoresDAO.delete(cedulaJugadorField);

                //Se llama al metodo llenarTablaJugadores de la clase JugadoresDAO para llenar la tabla de jugadores
                //Esto hará que se actualice la tabla con los datos que se acaban de eliminar
                jugadoresDAO.llenarTablaJugadores(tablaJugadores, cedulaJugadoresColumna, nombresJugadoresCoolumna, apellidosJugadoresColumna,
                        telefonoJugadoresColumna, correoJugadoresColumna, direccionJugadoresColumna, sueldoJugadoresColumna);
                cleanFields(event);//Se llama al metodo cleanFields para limpiar los campos o fields una vez se eliminan los datos
            }

            //De igual manera con el resto de casos

            case "deleteButtonCuerpoTecnico" -> {
                cuerpoTecnicoDAO.delete(cedulaCuerpoTecnicoField);
                cuerpoTecnicoDAO.llenarTablaCuerpoTecnico(tablaCuerpoTecnico, cedulaCuerpoTecnicoColumna, cargoCuerpoTecnicoColumna, nombresCuerpoTecnicoColumna, apellidosCuerpoTecnicoColumna,
                        telefonoCuerpoTecnicoColumna, correoCuerpoTecnicoColumna, direccionCuerpoTecnicoColumna, sueldoCuerpoTecnicoColumna);
                cleanFields(event);
            }
            case "deleteButtonFuncionarios" -> {
                funcionariosDAO.delete(cedulaFuncionarioField);
                funcionariosDAO.llenarTablaFuncionarios(tablaFuncionarios, cedulaFuncionarioColumna, cargoFuncionarioColumna, nombresFuncionarioColumna, apellidosFuncionarioColumna,
                        telefonoFuncionarioColumna, correoFuncionarioColumna, direccionFuncionarioColumna, sueldoFuncionarioColumna);
                cleanFields(event);
            }
        }
    }

    //Este metodo limpia los campos o fields de la interfaz grafica

    @FXML
    public void onCleanButtonAction(ActionEvent event) {
        String whoCleanButton = ((Button) event.getSource()).getId();//Se obtiene el id del boton limpiar que se presiono

        switch (whoCleanButton) {//Dependiendo del id del boton clean que se presiono se ejecuta el codigo correspondiente
            case "cleanButtonJugadores" -> {//Por ejemplo, si el id del boton clean que se presiono es cleanButtonJugadores se limpian los campos o fields de la interfaz grafica
                cedulaJugadorField.setText("");
                nombresJugadorField.setText("");
                apellidosJugadorField.setText("");
                telefonoJugadorField.setText("");
                correoJugadorField.setText("");
                direccionJugadorField.setText("");
                sueldoJugadorField.setText("");
            }

            //De igual manera con el resto de casos

            case "cleanButtonCuerpoTecnico" -> {
                cedulaCuerpoTecnicoField.setText("");
                cargoCuerpoTecnicoField.setText("");
                nombresCuerpoTecnicoField.setText("");
                apellidosCuerpoTecnicoField.setText("");
                telefonoCuerpoTecnicoField.setText("");
                correoCuerpoTecnicoField.setText("");
                direccionCuerpoTecnicoField.setText("");
                sueldoCuerpoTecnicoField.setText("");
            }
            case "cleanButtonFuncionarios" -> {
                cedulaFuncionarioField.setText("");
                cargoFuncionarioField.setText("");
                nombresFuncionarioField.setText("");
                apellidosFuncionarioField.setText("");
                telefonoFuncionarioField.setText("");
                correoFuncionarioField.setText("");
                direccionFuncionarioField.setText("");
                sueldoFuncionarioField.setText("");
            }
        }
    }



        //Este metodo funciona para cambar de vistas
        @FXML
        void onAdmButtonClicked (MouseEvent event){
            String whoPage = ((Pane) event.getSource()).getId();//Se obtiene el id del boton administrar que se presiono
            switch (whoPage) {//Dependiendo del id del boton administrar que se presiono se ejecuta el codigo correspondiente
                case "admJugadoresBtn" -> {//Por ejemplo, si el id del boton administrar que se presiono es admJugadoresBtn se hace visible la vista de Administrar Datos Jugadores y se ocultan las demas vistas
                    admJugadoresPage.setVisible(true);
                    admCuerpoTecnicoPage.setVisible(false);
                    admFuncionariosPage.setVisible(false);
                    helloPage.setVisible(false);
                }

                //De igual manera con el resto de casos

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

        //Este es el metodo que sirve para llenar los campos o fields de la interfaz grafica con los datos de la tabla jugadores
        //Al hacer click en una fila de la tabla se llenan los campos o fields con los datos de la fila seleccionada
        //Recibe como parametros la tabla donde se hará el evento click y los campos o fields de la interfaz grafica que serán llenados
        @FXML
        public void onJugadorColumnClicked () {
            jugadoresDAO.jugadorColumnClicked(tablaJugadores, cedulaJugadorField, nombresJugadorField, apellidosJugadorField,
                                              telefonoJugadorField, correoJugadorField, direccionJugadorField, sueldoJugadorField);
        }

        //Este es el metodo que sirve para llenar los campos o fields de la interfaz grafica con los datos de la tabla cuerpo tecnico
        //Al hacer click en una fila de la tabla se llenan los campos o fields con los datos de la fila seleccionada
        //Recibe como parametros la tabla donde se hará el evento click y los campos o fields de la interfaz grafica que serán llenados
        @FXML
        public void onCuerpoTecnicoColumClicked() {
            cuerpoTecnicoDAO.cuerpoTecnicoColumnClicked(tablaCuerpoTecnico, cedulaCuerpoTecnicoField, cargoCuerpoTecnicoField, nombresCuerpoTecnicoField, apellidosCuerpoTecnicoField,
                                                        telefonoCuerpoTecnicoField, correoCuerpoTecnicoField, direccionCuerpoTecnicoField, sueldoCuerpoTecnicoField);
        }


        //Este es el metodo que sirve para llenar los campos o fields de la interfaz grafica con los datos de la tabla funcionarios
        //Al hacer click en una fila de la tabla se llenan los campos o fields con los datos de la fila seleccionada
        //Recibe como parametros la tabla donde se hará el evento click y los campos o fields de la interfaz grafica que serán llenados
        @FXML
        public void onFuncionariosColumnClicked() {
            funcionariosDAO.funcionariosColumnClicked(tablaFuncionarios, cedulaFuncionarioField, cargoFuncionarioField, nombresFuncionarioField, apellidosFuncionarioField,
                                                      telefonoFuncionarioField, correoFuncionarioField, direccionFuncionarioField, sueldoFuncionarioField);
        }


    //Este metodo sirve para limpiar los campos o fields de la interfaz grafica automaticamente, dependiendo de la operacion que se realizó
    //Por ejemplo, si se presiono el boton agregar, se limpian los campos o fields de la interfaz grafica
    //Si se presiono el boton actualizar, se limpian los campos o fields de la interfaz grafica
    public void cleanFields(ActionEvent event) {
        String whoFieldsType = ((Button) event.getSource()).getId();//Se obtiene el id del tipo boton que se presiono

        switch (whoFieldsType) {//Dependiendo del id del tipo boton que se presiono se ejecuta el codigo correspondiente
            case "addButtonJugadores" -> {//Si se realizó la operacion agregar, se limpian los campos o fields de la interfaz grafica automaticamente
                cedulaJugadorField.setText("");
                nombresJugadorField.setText("");
                apellidosJugadorField.setText("");
                telefonoJugadorField.setText("");
                correoJugadorField.setText("");
                direccionJugadorField.setText("");
                sueldoJugadorField.setText("");
            }
            case "updateButtonJugadores" -> {//Si se realizó la operacion actualizar, se limpian los campos o fields de la interfaz grafica automaticamente
                cedulaJugadorField.setText("");
                nombresJugadorField.setText("");
                apellidosJugadorField.setText("");
                telefonoJugadorField.setText("");
                correoJugadorField.setText("");
                direccionJugadorField.setText("");
                sueldoJugadorField.setText("");
            }

            case "deleteButtonJugadores" -> {//Si se realizó la operacion eliminar, se limpian los campos o fields de la interfaz grafica automaticamente
                cedulaJugadorField.setText("");
                nombresJugadorField.setText("");
                apellidosJugadorField.setText("");
                telefonoJugadorField.setText("");
                correoJugadorField.setText("");
                direccionJugadorField.setText("");
                sueldoJugadorField.setText("");
            }

            //De igual manera con el resto de casos

            case "addButtonCuerpoTecnico" -> {
                cedulaCuerpoTecnicoField.setText("");
                cargoCuerpoTecnicoField.setText("");
                nombresCuerpoTecnicoField.setText("");
                apellidosCuerpoTecnicoField.setText("");
                telefonoCuerpoTecnicoField.setText("");
                correoCuerpoTecnicoField.setText("");
                direccionCuerpoTecnicoField.setText("");
                sueldoCuerpoTecnicoField.setText("");
            }
            case "updateButtonCuerpoTecnico" -> {
                cedulaCuerpoTecnicoField.setText("");
                cargoCuerpoTecnicoField.setText("");
                nombresCuerpoTecnicoField.setText("");
                apellidosCuerpoTecnicoField.setText("");
                telefonoCuerpoTecnicoField.setText("");
                correoCuerpoTecnicoField.setText("");
                direccionCuerpoTecnicoField.setText("");
                sueldoCuerpoTecnicoField.setText("");
            }

            case "deleteButtonCuerpoTecnico" -> {
                cedulaCuerpoTecnicoField.setText("");
                cargoCuerpoTecnicoField.setText("");
                nombresCuerpoTecnicoField.setText("");
                apellidosCuerpoTecnicoField.setText("");
                telefonoCuerpoTecnicoField.setText("");
                correoCuerpoTecnicoField.setText("");
                direccionCuerpoTecnicoField.setText("");
                sueldoCuerpoTecnicoField.setText("");
            }


            case "addButtonFuncionarios" -> {
                cedulaFuncionarioField.setText("");
                cargoFuncionarioField.setText("");
                nombresFuncionarioField.setText("");
                apellidosFuncionarioField.setText("");
                telefonoFuncionarioField.setText("");
                correoFuncionarioField.setText("");
                direccionFuncionarioField.setText("");
                sueldoFuncionarioField.setText("");
            }
            case "updateButtonFuncionarios" -> {
                cedulaFuncionarioField.setText("");
                cargoFuncionarioField.setText("");
                nombresFuncionarioField.setText("");
                apellidosFuncionarioField.setText("");
                telefonoFuncionarioField.setText("");
                correoFuncionarioField.setText("");
                direccionFuncionarioField.setText("");
                sueldoFuncionarioField.setText("");
            }

            case "deleteButtonFuncionarios" -> {
                cedulaFuncionarioField.setText("");
                cargoFuncionarioField.setText("");
                nombresFuncionarioField.setText("");
                apellidosFuncionarioField.setText("");
                telefonoFuncionarioField.setText("");
                correoFuncionarioField.setText("");
                direccionFuncionarioField.setText("");
                sueldoFuncionarioField.setText("");
            }


        }
    }


    //Este metodo sirve para cerrar la sesion
    // Cierra la ventana home (HomeInterfaz.fxml) y abre la ventana de login (LoginInterfaz.fxml)
    @FXML
    void onLogoutButtonClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();//Cierra la ventana home (HomeInterfaz.fxml) automaticamente

        //Abre la ventana de login (LoginInterfaz.fxml)
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginInterfaz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        Stage stage2 = new Stage(StageStyle.UNDECORATED);
        stage2.setScene(scene);
        stage2.show();
    }

    //Este metodo sirve para cerrar la ventana home (HomeInterfaz.fxml)
    //Al momento de presionar el boton cerrar, se cierra la ventana home (HomeInterfaz.fxml) automaticamente
    @FXML
    void onCloseButtonHomeClicked(MouseEvent event) {
        javafx.application.Platform.exit();
    }

    //Este metodo sirve para minimizar la ventana home (HomeInterfaz.fxml)
    //Al momento de presionar el boton minimizar, se minimiza la ventana home (HomeInterfaz.fxml) automaticamente
    @FXML
    void onMinimizeButtonHomeClicked(MouseEvent event) {
        Stage stage = (Stage) helloPage.getScene().getWindow();
        stage.setIconified(true);
    }

}

