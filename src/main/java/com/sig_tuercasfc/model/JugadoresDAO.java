package com.sig_tuercasfc.model;

import com.sig_tuercasfc.domain.Jugadores;
import com.sig_tuercasfc.exceptions.ConnectionDBException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JugadoresDAO {
    public static final String SELECT = "SELECT * FROM tuercas_fc.jugadores";
    public static final String INSERT = "INSERT INTO tuercas_fc.jugadores(cedula, nombres, apellidos, telefono, correo, direccion, sueldo) VALUES(?,?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE tuercas_fc.jugadores SET cedula = ?, nombres = ?, apellidos = ?, telefono = ?, correo = ?, direccion = ?, sueldo = ? WHERE cedula = ?";
    public static final String DELETE = "DELETE FROM tuercas_fc.jugadores WHERE cedula = ?";


    public void llenarTablaJugadores(TableView<Map> tablaJugadores, TableColumn<?, ?> cedulaJugadoresColumna, TableColumn<?,?> nombresJugadoresCoolumna,
                                      TableColumn<?,?> apellidosJugadoresColumna, TableColumn<?,?> telefonoJugadoresColumna, TableColumn<?,?> correoJugadoresColumna,
                                      TableColumn<?,?> direccionJugadoresColumna, TableColumn<?,?> sueldoJugadoresColumna){
        ObservableList<Map> jugadoresList = null;
        try {
            jugadoresList = select();
        } catch (ConnectionDBException e) {
            throw new RuntimeException(e);
        }

        cedulaJugadoresColumna.setCellValueFactory(new MapValueFactory("cedula"));
        nombresJugadoresCoolumna.setCellValueFactory(new MapValueFactory("nombres"));
        apellidosJugadoresColumna.setCellValueFactory(new MapValueFactory("apellidos"));
        telefonoJugadoresColumna.setCellValueFactory(new MapValueFactory("telefono"));
        correoJugadoresColumna.setCellValueFactory(new MapValueFactory("correo"));
        direccionJugadoresColumna.setCellValueFactory(new MapValueFactory("direccion"));
        sueldoJugadoresColumna.setCellValueFactory(new MapValueFactory("sueldo"));

        tablaJugadores.setItems(jugadoresList);
    }
    public boolean hasEmptyFields(TextField cedulaField, TextField nombresField, TextField apellidosField, TextField telefonoField,
                                  TextField correoField, TextField direccionField, TextField sueldoField) {

        if (cedulaField.getText().isEmpty() || nombresField.getText().isEmpty() || apellidosField.getText().isEmpty() ||
                telefonoField.getText().isEmpty() || correoField.getText().isEmpty() || direccionField.getText().isEmpty() ||
                sueldoField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensaje Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos");
            alert.showAndWait();
            return true;
        }
        return false;
    }
    public ObservableList<Map> select() throws ConnectionDBException {
        IConnectionToDB connectionToDB = new MySQLConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ObservableList<Map> jugadoresObservableList = FXCollections.observableArrayList();

        try {
            connection = connectionToDB.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Jugadores jugador = new Jugadores();

                Map<String, Object> coleccionMap = new HashMap<>();

                jugador.setCedula(Integer.parseInt(resultSet.getString(1)));
                jugador.setNombres(resultSet.getString(2));
                jugador.setApellidos(resultSet.getString(3));
                jugador.setTelefono(Integer.parseInt(resultSet.getString(4)));
                jugador.setCorreo(resultSet.getString(5));
                jugador.setDireccion(resultSet.getString(6));
                jugador.setSueldo(Double.parseDouble(resultSet.getString(7)));

                coleccionMap.put("cedula", jugador.getCedula());
                coleccionMap.put("nombres", jugador.getNombres());
                coleccionMap.put("apellidos", jugador.getApellidos());
                coleccionMap.put("telefono", jugador.getTelefono());
                coleccionMap.put("correo", jugador.getCorreo());
                coleccionMap.put("direccion", jugador.getDireccion());
                coleccionMap.put("sueldo", jugador.getSueldo());

                jugadoresObservableList.add(coleccionMap);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connectionToDB.close(resultSet);
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
        return jugadoresObservableList;
    }

    public void insert(TextField cedulaField, TextField nombresField, TextField apellidosField, TextField telefonoField,
                       TextField correoField, TextField direccionField, TextField sueldoField) throws ConnectionDBException {
        IConnectionToDB connectionToDB = new MySQLConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Alert alert;
            connection = connectionToDB.getConnection();

            if(hasEmptyFields(cedulaField, nombresField, apellidosField, telefonoField,
                            correoField, direccionField, sueldoField)){

            }else {
                String checkCedula = "SELECT cedula FROM tuercas_fc.jugadores WHERE cedula = " + cedulaField.getText();
                preparedStatement = connection.prepareStatement(checkCedula);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Mensaje Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El número de cédula ya existe");
                    alert.showAndWait();
                }else {
                    preparedStatement = connection.prepareStatement(INSERT);
                    preparedStatement.setInt(1, Integer.parseInt(cedulaField.getText()));
                    preparedStatement.setString(2, nombresField.getText());
                    preparedStatement.setString(3, apellidosField.getText());
                    preparedStatement.setInt(4, Integer.parseInt(telefonoField.getText()));
                    preparedStatement.setString(5, correoField.getText());
                    preparedStatement.setString(6, direccionField.getText());
                    preparedStatement.setDouble(7, Double.parseDouble(sueldoField.getText()));


                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mensaje de información");
                    alert.setHeaderText(null);
                    alert.setContentText("Agregado con éxito!");
                    alert.showAndWait();

                    preparedStatement.executeUpdate();

                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
    }


    public void update(TextField cedulaField, TextField nombresField, TextField apellidosField, TextField telefonoField,
                       TextField correoField, TextField direccionField, TextField sueldoField) throws ConnectionDBException {

        IConnectionToDB connectionToDB = new MySQLConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Alert alert;

            if(!hasEmptyFields(cedulaField, nombresField, apellidosField, telefonoField,
                        correoField, direccionField, sueldoField)){
                connection = connectionToDB.getConnection();
                preparedStatement = connection.prepareStatement(UPDATE);
                preparedStatement.setInt(1, Integer.parseInt(cedulaField.getText()));
                preparedStatement.setString(2, nombresField.getText());
                preparedStatement.setString(3, apellidosField.getText());
                preparedStatement.setInt(4, Integer.parseInt(telefonoField.getText()));
                preparedStatement.setString(5, correoField.getText());
                preparedStatement.setString(6, direccionField.getText());
                preparedStatement.setDouble(7, Double.parseDouble(sueldoField.getText()));
                preparedStatement.setInt(8, Integer.parseInt(cedulaField.getText()));


                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensaje de información");
                alert.setHeaderText(null);
                alert.setContentText("Actualizado con éxito!");
                alert.showAndWait();

                preparedStatement.executeUpdate();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
    }

    public void delete(TextField cedulaField) throws ConnectionDBException {

        IConnectionToDB connectionToDB = new MySQLConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Alert alert;
            connection = connectionToDB.getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, Integer.parseInt(cedulaField.getText()));
            preparedStatement.executeUpdate();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de información");
            alert.setHeaderText(null);
            alert.setContentText("Eliminado con éxito!");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
    }

    public void jugadorColumnClicked(TableView<?> tablaJugadores, TextField cedulaField, TextField nombresField, TextField apellidosField,
                                     TextField telefonoField, TextField correoField, TextField direccionField, TextField sueldoField) {
        tablaJugadores.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Map<String, Object> jugador = (Map<String, Object>) tablaJugadores.getSelectionModel().getSelectedItem();
                cedulaField.setText(String.valueOf(jugador.get("cedula")));
                nombresField.setText(String.valueOf(jugador.get("nombres")));
                apellidosField.setText(String.valueOf(jugador.get("apellidos")));
                telefonoField.setText(String.valueOf(jugador.get("telefono")));
                correoField.setText(String.valueOf(jugador.get("correo")));
                direccionField.setText(String.valueOf(jugador.get("direccion")));
                sueldoField.setText(String.valueOf(jugador.get("sueldo")));
            }
        });

    }
}
