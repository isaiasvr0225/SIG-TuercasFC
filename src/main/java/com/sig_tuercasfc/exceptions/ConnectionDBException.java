package com.sig_tuercasfc.exceptions;

import java.sql.SQLException;

/*
  Aqui se cumple otro requisito, que es el de crear una clase de excepcion propia

    Esta clase extiende de SQLException, que es la clase de excepcion que se lanza cuando existe
    un error en la conexion con la base de datos. Esta clase será utilizada para lanzar una excepcion,
    y se utilizará multiples veces en el proyecto.
 */
public class ConnectionDBException extends SQLException {
        //Se crea un constructor vacio, que simplemente llama al constructor de la clase padre
        public ConnectionDBException() {
            super("Error al conectar con la base de datos");
        }

        //Se crea un constructor que recibe un mensaje, que se le pasa al constructor de la clase padre
        public ConnectionDBException(String message) {
            super(message);
        }


}
