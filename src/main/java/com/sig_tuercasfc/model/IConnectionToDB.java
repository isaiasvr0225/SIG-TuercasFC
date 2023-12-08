package com.sig_tuercasfc.model;

import com.sig_tuercasfc.exceptions.ConnectionDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
    Aquí se establece el requerimiento Polimorfismo de Interfaces.

    Esta interfaz tiene como objetivo establecer los métodos que se van a utilizar
    para la conexión a la base de datos y cerrar las conexiones.

    Esta interfaz se crea pensando en la escalabilidad, ya que se podrá cambiar el tipo de base de datos.
 */
public interface IConnectionToDB {

    Connection getConnection() throws ConnectionDBException;

    void close(Connection connection) throws ConnectionDBException;

    void close(ResultSet resultSet) throws ConnectionDBException;

    void close(PreparedStatement preparedStatement) throws ConnectionDBException;
}
