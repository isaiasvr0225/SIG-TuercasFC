package com.sig_tuercasfc.model;

import com.sig_tuercasfc.exceptions.ConnectionDBException;

import java.sql.*;
/*
    Esta clase implementa la interfaz IConnectionToDB, por lo que se debe implementar todos los métodos de la interfaz.
    Esta clase se encarga de establecer la conexión a la base de datos MySQL.
    Implementando el patrón Singleton, se asegura que solo exista una instancia de la clase.

    Si se desea establecer la conexión a otra base de datos, se debe crear una clase que implemente la interfaz IConnectionToDB.
 */
public class MySQLConnection implements IConnectionToDB{
    //Se crea una constante que almacena la url de la base de datos.
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/tuercas_fc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //Se crea una constante que almacena el usuario de la base de datos.
    private static final String JDBC_USER="root";

    //Se crea una constante que almacena la contraseña de la base de datos.
    private static final String JDBC_PASS="admin";

    //Se crea un metodo que retorna una conexion a la base de datos.
    @Override
    public Connection getConnection() throws ConnectionDBException {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (SQLException e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }

    //Se crean los metodos que cierra la conexion a la base de datos.
    @Override
    public void close(ResultSet resultSet)throws ConnectionDBException{
        try {
            if(resultSet != null){
                resultSet.close();
            }

        } catch (SQLException e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }

    @Override
    public void close(PreparedStatement preparedStatement)throws ConnectionDBException{
        try {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }

    @Override
    public void close(Connection connection)throws ConnectionDBException{
        try {
           if(connection != null){
               connection.close();
           }
        } catch (SQLException e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }
}
