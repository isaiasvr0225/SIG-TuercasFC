package com.sig_tuercasfc.model;

import java.sql.*;

public class SetConnection {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/tuercas_fc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String JDBC_USER="root";
    private static final String JDBC_PASS="admin";

    public static Connection setConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(ResultSet resultSet){
        try {
            if(resultSet != null){
                resultSet.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close(PreparedStatement preparedStatement){
        try {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close(Connection connection){
        try {
           if(connection != null){
               connection.close();
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
