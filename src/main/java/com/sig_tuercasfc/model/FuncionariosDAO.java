package com.sig_tuercasfc.model;

import com.sig_tuercasfc.domain.Funcionarios;
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

public class FuncionariosDAO {
    public static final String SELECT = "SELECT * FROM tuercas_fc.funcionarios";
    public static final String INSERT = "INSERT INTO tuercas_fc.funcionarios(cedula, cargo, nombres, apellidos, telefono, correo, direccion, sueldo) VALUES(?,?,?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE tuercas_fc.funcionarios SET cedula = ?, cargo = ?, nombres = ?, apellidos = ?, telefono = ?, correo = ?, direccion = ?, sueldo = ? WHERE cedula = ?";
    public static final String DELETE = "DELETE FROM tuercas_fc.funcionarios WHERE cedula = ?";

    public void llenarTablaFuncionarios(TableView<Map> tablaFuncionarios, TableColumn<?, ?> cedulaFuncionariosColumna, TableColumn<?, ?> cargoFuncionariosColumna,
                                        TableColumn<?,?> nombresFuncionariosColumna, TableColumn<?,?> apellidosFuncionariosColumna, TableColumn<?,?> telefonoFuncionariosColumna,
                                        TableColumn<?,?> correoFuncionariosColumna, TableColumn<?,?> direccionFuncionariosColumna, TableColumn<?,?> sueldoFuncionariosColumna){

        ObservableList<Map> funcionariosList = null;
        try {
            funcionariosList = select();
        } catch (ConnectionDBException e) {
            throw new RuntimeException(e);
        }

        cedulaFuncionariosColumna.setCellValueFactory(new MapValueFactory("cedula"));
        cargoFuncionariosColumna.setCellValueFactory(new MapValueFactory("cargo"));
        nombresFuncionariosColumna.setCellValueFactory(new MapValueFactory("nombres"));
        apellidosFuncionariosColumna.setCellValueFactory(new MapValueFactory("apellidos"));
        telefonoFuncionariosColumna.setCellValueFactory(new MapValueFactory("telefono"));
        correoFuncionariosColumna.setCellValueFactory(new MapValueFactory("correo"));
        direccionFuncionariosColumna.setCellValueFactory(new MapValueFactory("direccion"));
        sueldoFuncionariosColumna.setCellValueFactory(new MapValueFactory("sueldo"));

        tablaFuncionarios.setItems(funcionariosList);
    }
    public boolean hasEmptyFields(TextField cedulaField, TextField cargoField, TextField nombresField, TextField apellidosField,
                                  TextField telefonoField, TextField correoField, TextField direccionField, TextField sueldoField) {

        if (cedulaField.getText().isEmpty() || cargoField.getText().isEmpty() || nombresField.getText().isEmpty() || apellidosField.getText().isEmpty() ||
                telefonoField.getText().isEmpty() || correoField.getText().isEmpty() || direccionField.getText().isEmpty() ||
                sueldoField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensaje Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos.");
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
        ObservableList<Map> funcionariosList = FXCollections.observableArrayList();

        try {
            connection = connectionToDB.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Funcionarios funcionario = new Funcionarios();

                Map<String, Object> coleccionMap = new HashMap<>();

                funcionario.setCedula(Integer.parseInt(resultSet.getString(1)));
                funcionario.setCargo(resultSet.getString(2));
                funcionario.setNombres(resultSet.getString(3));
                funcionario.setApellidos(resultSet.getString(4));
                funcionario.setTelefono(Integer.parseInt(resultSet.getString(5)));
                funcionario.setCorreo(resultSet.getString(6));
                funcionario.setDireccion(resultSet.getString(7));
                funcionario.setSueldo(Double.parseDouble(resultSet.getString(8)));

                coleccionMap.put("cedula", funcionario.getCedula());
                coleccionMap.put("cargo", funcionario.getCargo());
                coleccionMap.put("nombres", funcionario.getNombres());
                coleccionMap.put("apellidos", funcionario.getApellidos());
                coleccionMap.put("telefono", funcionario.getTelefono());
                coleccionMap.put("correo", funcionario.getCorreo());
                coleccionMap.put("direccion", funcionario.getDireccion());
                coleccionMap.put("sueldo", funcionario.getSueldo());

                funcionariosList.add(coleccionMap);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connectionToDB.close(resultSet);
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
        return funcionariosList;
    }

    public void insert(TextField cedulaField, TextField cargoField, TextField nombresField, TextField apellidosField, TextField telefonoField,
                       TextField correoField, TextField direccionField, TextField sueldoField) throws ConnectionDBException {

        IConnectionToDB connectionToDB = new MySQLConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Alert alert;
            connection = connectionToDB.getConnection();

            if(hasEmptyFields(cedulaField, cargoField, nombresField, apellidosField, telefonoField,
                    correoField, direccionField, sueldoField)){

            }else {
                String checkCedula = "SELECT cedula FROM tuercas_fc.funcionarios WHERE cedula = " + cedulaField.getText();
                preparedStatement = connection.prepareStatement(checkCedula);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("El numero de cedula ya existe.");
                    alert.showAndWait();
                }else {
                    preparedStatement = connection.prepareStatement(INSERT);
                    preparedStatement.setInt(1, Integer.parseInt(cedulaField.getText()));
                    preparedStatement.setString(2, cargoField.getText());
                    preparedStatement.setString(3, nombresField.getText());
                    preparedStatement.setString(4, apellidosField.getText());
                    preparedStatement.setInt(5, Integer.parseInt(telefonoField.getText()));
                    preparedStatement.setString(6, correoField.getText());
                    preparedStatement.setString(7, direccionField.getText());
                    preparedStatement.setDouble(8, Double.parseDouble(sueldoField.getText()));


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


    public void update(TextField cedulaField, TextField cargoField, TextField nombresField, TextField apellidosField, TextField telefonoField,
                       TextField correoField, TextField direccionField, TextField sueldoField) throws ConnectionDBException{

        IConnectionToDB connectionToDB = new MySQLConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Alert alert;

            if(!hasEmptyFields(cedulaField, cargoField, nombresField, apellidosField,
                               telefonoField, correoField, direccionField, sueldoField)){

                connection = connectionToDB.getConnection();
                preparedStatement = connection.prepareStatement(UPDATE);
                preparedStatement.setInt(1, Integer.parseInt(cedulaField.getText()));
                preparedStatement.setString(2, cargoField.getText());
                preparedStatement.setString(3, nombresField.getText());
                preparedStatement.setString(4, apellidosField.getText());
                preparedStatement.setInt(5, Integer.parseInt(telefonoField.getText()));
                preparedStatement.setString(6, correoField.getText());
                preparedStatement.setString(7, direccionField.getText());
                preparedStatement.setDouble(8, Double.parseDouble(sueldoField.getText()));
                preparedStatement.setInt(9, Integer.parseInt(cedulaField.getText()));


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

    public void funcionariosColumnClicked(TableView<?> tablaFuncionarios, TextField cedulaField, TextField cargoField, TextField nombresField, TextField apellidosField,
                                          TextField telefonoField, TextField correoField, TextField direccionField, TextField sueldoField) {
        tablaFuncionarios.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Map<String, Object> funcionariosMap = (Map<String, Object>) tablaFuncionarios.getSelectionModel().getSelectedItem();
                cedulaField.setText(String.valueOf(funcionariosMap.get("cedula")));
                cargoField.setText(String.valueOf(funcionariosMap.get("cargo")));
                nombresField.setText(String.valueOf(funcionariosMap.get("nombres")));
                apellidosField.setText(String.valueOf(funcionariosMap.get("apellidos")));
                telefonoField.setText(String.valueOf(funcionariosMap.get("telefono")));
                correoField.setText(String.valueOf(funcionariosMap.get("correo")));
                direccionField.setText(String.valueOf(funcionariosMap.get("direccion")));
                sueldoField.setText(String.valueOf(funcionariosMap.get("sueldo")));
            }
        });

    }



}
