package com.sig_tuercasfc.model;

import com.sig_tuercasfc.domain.CuerpoTecnico;
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

public class CuerpoTecnicoDAO {
    //Aqui se cumple otro requisito, el cual es utilizar las operaciones SELECT, INSERT, UPDATE y DELETE de SQL
    public static final String SELECT = "SELECT * FROM tuercas_fc.cuerpo_tecnico";
    public static final String INSERT = "INSERT INTO tuercas_fc.cuerpo_tecnico(cedula, cargo, nombres, apellidos, telefono, correo, direccion, sueldo) VALUES(?,?,?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE tuercas_fc.cuerpo_tecnico SET cedula = ?, cargo = ?, nombres = ?, apellidos = ?, telefono = ?, correo = ?, direccion = ?, sueldo = ? WHERE cedula = ?";
    public static final String DELETE = "DELETE FROM tuercas_fc.cuerpo_tecnico WHERE cedula = ?";

    /*Este método se encarga de llenar la tabla en la interfaz grafica mas exactamente en la vista  Administrar Datos Cuerpo Técnico,
      recibe como parámetros la tabla y cada una de las columnas de la tabla.

        La tabla es de tipo TableView, la cual es una clase de la API de JavaFX, esta clase representa una tabla en la interfaz grafica.
        La clase TableColumn es una clase de la API de JavaFX, esta clase representa una columna de la tabla.
     */
    public void llenarTablaCuerpoTecnico(TableView<Map> tablaCuerpoTecnico, TableColumn<?, ?> cedulaCuerpoTecnicoColumna, TableColumn<?, ?> cargoCuerpoTecnicoColumna,
                                         TableColumn<?,?> nombresCuerpoTecnicoColumna, TableColumn<?,?> apellidosCuerpoTecnicoColumna, TableColumn<?,?> telefonoCuerpoTecnicoColumna,
                                         TableColumn<?,?> correoCuerpoTecnicoColumna, TableColumn<?,?> direccionCuerpoTecnicoColumna, TableColumn<?,?> sueldoCuerpoTecnicoColumna){

        /*Aqui se crea una lista observable de tipo Map. La interfaz ObservableList es una interfaz que extiende la interfaz List del paquete java.util.
          La clase Map es una interfaz que representa una colección de pares clave-valor.

        Esta lista se llena con el método select() que se encuentra más abajo en esta clase. Este método select() retorna una lista de objetos de tipo Map
        con los datos de la tabla cuerpo_tecnico de la base de datos tuercas_fc.
         */
        ObservableList<Map> cuerpoTecnicoList = null;
        try {
            cuerpoTecnicoList = select(); //Se rodea con try-catch porque el método select() lanza una excepción de tipo ConnectionDBException, la cual es la excepción que se creó personalizada.
        } catch (ConnectionDBException e) {
            throw new RuntimeException(e);
        }

        /*
            A continuación se les asigna el valor a cada columna con el metodo setCellValueFactory de la API de JavaFX,
            este metodo recibe como parametro un objeto de tipo MapValueFactory, el cual a su vez recibe como parametro el nombre de la columna guardad en la
            lista cuerpoTecnicoList, recordar que es de tipo map, clave-valor.
         */
        cedulaCuerpoTecnicoColumna.setCellValueFactory(new MapValueFactory("cedula"));
        cargoCuerpoTecnicoColumna.setCellValueFactory(new MapValueFactory("cargo"));
        nombresCuerpoTecnicoColumna.setCellValueFactory(new MapValueFactory("nombres"));
        apellidosCuerpoTecnicoColumna.setCellValueFactory(new MapValueFactory("apellidos"));
        telefonoCuerpoTecnicoColumna.setCellValueFactory(new MapValueFactory("telefono"));
        correoCuerpoTecnicoColumna.setCellValueFactory(new MapValueFactory("correo"));
        direccionCuerpoTecnicoColumna.setCellValueFactory(new MapValueFactory("direccion"));
        sueldoCuerpoTecnicoColumna.setCellValueFactory(new MapValueFactory("sueldo"));

        //Aqui se llena la tabla con la lista de objetos de tipo Map
        tablaCuerpoTecnico.setItems(cuerpoTecnicoList);

        //Cuando se llame a este metodo entonces lo que hará es llenar la tabla en la interfaz con los datos de la base de datos.
    }

    /*
    Este método se encarga de verificar si los campos de la interfaz grafica están vacíos, recibe como parámetros cada uno de los campos de la interfaz.
    Los campos de la interfaz estan al lado de la tabla, cada dato como cedula, cargo, nombres, apellidos, etc. tiene un campo o field.

    La clase TextField es una clase de la API de JavaFX, esta clase representa un campo de texto o field en la interfaz grafica.
     */
    public boolean hasEmptyFields(TextField cedulaField, TextField cargoField, TextField nombresField, TextField apellidosField,
                                  TextField telefonoField, TextField correoField, TextField direccionField, TextField sueldoField) {

        if (cedulaField.getText().isEmpty() || cargoField.getText().isEmpty() || nombresField.getText().isEmpty() || apellidosField.getText().isEmpty() ||
                telefonoField.getText().isEmpty() || correoField.getText().isEmpty() || direccionField.getText().isEmpty() ||
                sueldoField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llene todos los campos.");
            alert.showAndWait();
            return true; //Si hay campos vacíos entonces retorna true, osea que si hay campos vacíos. Y manda un mensaje de error.
        }
        return false; //Si no hay campos vacíos entonces retorna false, osea que no hay campos vacíos.
    }


    /*
    Este método se encarga de obtener los datos de la base de datos y llenar la lista de objetos cuerpoTecnicoList ObservableList de tipo Map, retorna una lista de objetos de tipo Map.
    La cual será usada para llenar la tabla de la interfaz con el metodo llenarTablaCuerpoTecnico() que se encuentra más arriba en esta clase.
     */
    public ObservableList<Map> select() throws ConnectionDBException {
        IConnectionToDB connectionToDB = new MySQLConnection(); //Aqui vemos con exactitud donde se aplica el polimorfismo de la interfaz IConnectionToDB
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ObservableList<Map> cuerpoTecnicoList = FXCollections.observableArrayList();

        try {
            connection = connectionToDB.getConnection();//Aqui se obtiene la conexión a la base de datos
            preparedStatement = connection.prepareStatement(SELECT);//Se prepara la consulta SELECT
            resultSet = preparedStatement.executeQuery();//Aqui se ejecuta la consulta SELECT

            //Se recorre el resultSet para obtener los datos de la base de datos
            while (resultSet.next()){
                CuerpoTecnico cuerpoTecnico = new CuerpoTecnico();

                //Aqui se cumple otro requisito, el cual es utilizar colecciones de la biblioteca java.util
                //En este caso se utiliza la implementación de la interfaz Map llamada HashMap
                Map<String, Object> coleccionMap = new HashMap<>();

                //Se guarda cada uno de los datos de la base de datos en cada uno de los atributos del objeto cuerpoTecnico de la clase CuerpoTecnico
                cuerpoTecnico.setCedula(Integer.parseInt(resultSet.getString(1)));//Por ejemplo, cuando el puntero este en el indice 1 se obtiene el dato de la columna cedula de la tabla cuerpo_tecnico y se guarda en el atributo cedula del objeto cuerpoTecnico
                cuerpoTecnico.setCargo(resultSet.getString(2));
                cuerpoTecnico.setNombres(resultSet.getString(3));
                cuerpoTecnico.setApellidos(resultSet.getString(4));
                cuerpoTecnico.setTelefono(Integer.parseInt(resultSet.getString(5)));
                cuerpoTecnico.setCorreo(resultSet.getString(6));
                cuerpoTecnico.setDireccion(resultSet.getString(7));
                cuerpoTecnico.setSueldo(Double.parseDouble(resultSet.getString(8)));


                //Se guardan los datos de cada uno de los atributos del objeto cuerpoTecnico en la colección de tipo Map
                //Se asigna como clave el nombre de la columna de la tabla de la base de datos y como valor el dato que se obtuvo de la base de datos
                //Esto para luuego poder ser obtenido por el metodo setCellValueFactory en el metodo llenarTablaCuerpoTecnico()
                coleccionMap.put("cedula", cuerpoTecnico.getCedula());
                coleccionMap.put("cargo", cuerpoTecnico.getCargo());
                coleccionMap.put("nombres", cuerpoTecnico.getNombres());
                coleccionMap.put("apellidos", cuerpoTecnico.getApellidos());
                coleccionMap.put("telefono", cuerpoTecnico.getTelefono());
                coleccionMap.put("correo", cuerpoTecnico.getCorreo());
                coleccionMap.put("direccion", cuerpoTecnico.getDireccion());
                coleccionMap.put("sueldo", cuerpoTecnico.getSueldo());

                //Se agrega la colección de tipo Map a la lista de objetos de tipo ObservableList
                cuerpoTecnicoList.add(coleccionMap);
            }
        } catch (SQLException e) {//Se atrapa la excepción SQLException
            throw new ConnectionDBException("Error al obtener los datos de la base de datos"); //Se lanza la excepción ConnectionDBException
        }
        finally {
            //Se cierran las conexiones
            connectionToDB.close(resultSet);
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
        return cuerpoTecnicoList; //Se retorna la lista de objetos de tipo ObservableList
    }


    /*
    Este método se encarga de insertar los datos obtenidos de los fields o campos de la interfaz grafica en la base de datos.
    Recibe como parámetros cada uno de los campos de la interfaz grafica.
    Los campos de la interfaz estan al lado de la tabla, cada dato como cedula, cargo, nombres, apellidos, etc. tiene un campo o field.
     */
    public void insert(TextField cedulaField, TextField cargoField, TextField nombresField, TextField apellidosField, TextField telefonoField,
                       TextField correoField, TextField direccionField, TextField sueldoField) throws ConnectionDBException {

        IConnectionToDB connectionToDB = new MySQLConnection();//Polimorfismo de la interfaz IConnectionToDB
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Alert alert;
            connection = connectionToDB.getConnection();//Aqui se obtiene la conexión a la base de datos

            //Se verifica si hay campos vacíos usando el metodo hasEmptyFields()
            if(hasEmptyFields(cedulaField, cargoField, nombresField, apellidosField, telefonoField,
                    correoField, direccionField, sueldoField)){//Si hay campos vacíos entonces se retorna y se manda el mensaje de error.

            }else { //Si no hay campos vacíos entonces se procede a insertar los datos en la base de datos

                //Pero antes, se verifica si el numero de cedula ya existe en la base de datos
                String checkCedula = "SELECT cedula FROM tuercas_fc.cuerpo_tecnico WHERE cedula = " + cedulaField.getText();
                preparedStatement = connection.prepareStatement(checkCedula);
                resultSet = preparedStatement.executeQuery(); //Se ejecuta la consulta SELECT checkCedula

                if (resultSet.next()) {//Si el resulSet obtiene un resultado entonces el numero de cedula ya existe entonces y se manda el mensaje de error
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("El numero de cedula ya existe.");
                    alert.showAndWait();
                }else {//Si el resulSet no obtiene un resultado entonces el numero de cedula no existe por lo que se procede a insertar los datos en la base de datos con los datos de cada unos de los campos o fields de la interfaz grafica
                    preparedStatement = connection.prepareStatement(INSERT);
                    preparedStatement.setInt(1, Integer.parseInt(cedulaField.getText()));//Por ejemplo, cuando el puntero este en el indice 1 se obtiene el dato del field cedulaField y se guarda en la columna cedula de la tabla cuerpo_tecnico de la base de datos
                    preparedStatement.setString(2, cargoField.getText());//Y asi sucesivamente con cada uno de los fields
                    preparedStatement.setString(3, nombresField.getText());
                    preparedStatement.setString(4, apellidosField.getText());
                    preparedStatement.setInt(5, Integer.parseInt(telefonoField.getText()));
                    preparedStatement.setString(6, correoField.getText());
                    preparedStatement.setString(7, direccionField.getText());
                    preparedStatement.setDouble(8, Double.parseDouble(sueldoField.getText()));

                    //Se muestra un mensaje de confirmación de que los datos se han insertado con éxito
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mensaje de información");
                    alert.setHeaderText(null);
                    alert.setContentText("Agregado con éxito!");
                    alert.showAndWait();

                    preparedStatement.executeUpdate();//Se ejecuta la sentencia SQL INSERT

                }
            }

        } catch (SQLException e) {//Se atrapa la excepción SQLException
            throw new ConnectionDBException("Error al insertar los datos en la base de datos");//Se lanza la excepción ConnectionDBException
        } finally {
            //Se cierran las conexiones
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
    }


    /*
        Este método se encarga de actualizar los datos obtenidos de los fields o campos de la interfaz grafica en la base de datos.
    Recibe como parámetros cada uno de los campos de la interfaz grafica.
    Los campos de la interfaz estan al lado de la tabla, cada dato como cedula, cargo, nombres, apellidos, etc. tiene un campo o field.
     */
    public void update(TextField cedulaField, TextField cargoField, TextField nombresField, TextField apellidosField, TextField telefonoField,
                       TextField correoField, TextField direccionField, TextField sueldoField) throws ConnectionDBException {

        IConnectionToDB connectionToDB = new MySQLConnection();//Polimorfismo de la interfaz IConnectionToDB
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Alert alert;

            if(!hasEmptyFields(cedulaField, cargoField, nombresField, apellidosField, telefonoField,
                    correoField, direccionField, sueldoField)){//Se invierte el valor booleano con ! y Si el resultado es false entonces no hay campos vacíos entonces y se procede a actualizar los datos en la base de datos

                connection = connectionToDB.getConnection();//Aqui se obtiene la conexión a la base de datos
                preparedStatement = connection.prepareStatement(UPDATE);//Se prepara la sentencia SQL UPDATE
                preparedStatement.setInt(1, Integer.parseInt(cedulaField.getText()));//Por ejemplo, cuando el puntero este en el indice 1 se obtiene el dato del field cedulaField y se guarda en la columna cedula de la tabla cuerpo_tecnico de la base de datos
                preparedStatement.setString(2, cargoField.getText());
                preparedStatement.setString(3, nombresField.getText());
                preparedStatement.setString(4, apellidosField.getText());
                preparedStatement.setInt(5, Integer.parseInt(telefonoField.getText()));
                preparedStatement.setString(6, correoField.getText());
                preparedStatement.setString(7, direccionField.getText());
                preparedStatement.setDouble(8, Double.parseDouble(sueldoField.getText()));
                preparedStatement.setInt(9, Integer.parseInt(cedulaField.getText()));

                //Se muestra un mensaje de confirmación de que los datos se han actualizado con éxito
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensaje de información");
                alert.setHeaderText(null);
                alert.setContentText("Actualizado con éxito!");
                alert.showAndWait();

                preparedStatement.executeUpdate();//Se ejecuta la sentencia SQL UPDATE

            }

        } catch (SQLException e) {//Se atrapa la excepción SQLException
            throw new ConnectionDBException("Error al actualizar los datos en la base de datos");//Se lanza la excepción ConnectionDBException
        } finally {
            //Se cierran las conexiones
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
    }

    /*
        Este método se encarga de eliminar los datos de la base de datos.
        Recibe como parámetro el campo cedulaField que es el campo donde se ingresa el numero de cedula del cuerpo técnico que se desea eliminar.
     */
    public void delete(TextField cedulaField) throws ConnectionDBException {

        IConnectionToDB connectionToDB = new MySQLConnection();//Polimorfismo de la interfaz IConnectionToDB
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Alert alert;
            connection = connectionToDB.getConnection();//Aqui se obtiene la conexión a la base de datos
            preparedStatement = connection.prepareStatement(DELETE);//Se prepara la sentencia SQL DELETE

            //Se manda como parametro el numero de cedula que se desea eliminar, el cual se obtiene del campo cedulaField que viene como parámetro
            preparedStatement.setInt(1, Integer.parseInt(cedulaField.getText()));
            preparedStatement.executeUpdate();//Se ejecuta la sentencia SQL DELETE

            //Se muestra un mensaje de confirmación de que los datos se han eliminado con éxito
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de información");
            alert.setHeaderText(null);
            alert.setContentText("Eliminado con éxito!");
            alert.showAndWait();
        } catch (SQLException e) {//Se atrapa la excepción SQLException
            throw new ConnectionDBException("Error al eliminar los datos en la base de datos"); //Se lanza la excepción ConnectionDBException
        }
        finally {
            connectionToDB.close(preparedStatement);
            connectionToDB.close(connection);
        }
    }

    /*
        Este metodo se encarga de llenar los campos o fields de la interfaz grafica
        Al hacer click en una fila de la tabla, los datos de la fila se llenan en los fields de la interfaz grafica
        Recibe como parámetros la tabla donde se hará el evento click y cada uno de los fields de la interfaz grafica los cuales serán llenados con los datos de la fila seleccionada
     */
    public void cuerpoTecnicoColumnClicked(TableView<?> tablaCuerpoTecnico, TextField cedulaField, TextField cargoField, TextField nombresField,
                                           TextField apellidosField, TextField telefonoField, TextField correoField,
                                           TextField direccionField, TextField sueldoField) {
        //Para lograr eso, se usa una expresion lambda para usar el metodo setOnMouseClicked
        // se hace un cast de la tabla a Map<String, Object> y se obtiene el item seleccionado
        tablaCuerpoTecnico.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                Map<String, Object> cuerpoTecnico = (Map<String, Object>) tablaCuerpoTecnico.getSelectionModel().getSelectedItem();
                cedulaField.setText(String.valueOf(cuerpoTecnico.get("cedula"))); //Se obtiene el valor de la columna cedula, se convierte a String y se llena en su respectivo field
                cargoField.setText(String.valueOf(cuerpoTecnico.get("cargo"))); //Y asi sucesivamente con el resto
                nombresField.setText(String.valueOf(cuerpoTecnico.get("nombres")));
                apellidosField.setText(String.valueOf(cuerpoTecnico.get("apellidos")));
                telefonoField.setText(String.valueOf(cuerpoTecnico.get("telefono")));
                correoField.setText(String.valueOf(cuerpoTecnico.get("correo")));
                direccionField.setText(String.valueOf(cuerpoTecnico.get("direccion")));
                sueldoField.setText(String.valueOf(cuerpoTecnico.get("sueldo")));
            }
        });

    }
}
