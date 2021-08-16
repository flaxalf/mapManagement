package it.uniroma2.ispw.c3s.maps.dao;

/*
 * Classe Java responsabile della creazione e
 * gestione della connessione con un database
 * tramite il metodo getConnection()
 */

import java.sql.*;

public class DBConnectionSingleton {
    private final static String dbURI = "jdbc:postgresql://localhost:5432/map";
    private final static String user = "postgres";
    private final static String password = "postgres";
    private static DBConnectionSingleton instance;
    private Connection con;

    private DBConnectionSingleton() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.con = DriverManager.getConnection(dbURI, user, password);
    }

    public Connection getConnection() {
        return this.con;
    }

    public static DBConnectionSingleton getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnectionSingleton();
        }

        return instance;
    }
}