package com.example.app.ConexionPSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionPsql {

    public static Connection conectar (){

        Connection conect = null;

        try {

            Class.forName("org.postgresql.Driver");

            conect = DriverManager.getConnection("jdbc:postgresql://192.168.0.22:5432/evelyn","evelyn","berezin");
            //conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","admin@123");
            //conect = DriverManager.getConnection("jdbc:postgresql://192.168.0.22:5432/evelyn","evelyn","berezin");
            //conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","admin@123");

            System.out.println("ENTRAMOS AL TRY");

        }catch (SQLException e) {

            System.out.println("ERROR UNO AL CONECTARO CON BD");

        }catch (ClassNotFoundException e) {

            System.out.println("error dos ");
        }

        return conect;
    }
}
