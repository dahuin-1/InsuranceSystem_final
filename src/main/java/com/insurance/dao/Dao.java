package main.java.com.insurance.dao;

import java.sql.*;

public class Dao {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    public void coneect() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //DB접속 url
            String url = "jdbc:mysql://localhost/insurance?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false";
            connect = DriverManager.getConnection(url, "root", "2264");
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(String query) throws SQLException {
        try {
            statement = connect.createStatement();
            if (!statement.execute(query))
                System.out.println("insert OK!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void update(String query) throws SQLException {
        try {
            statement = connect.createStatement();
            if (!statement.execute(query))
                System.out.println("update OK!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(String query) throws SQLException {
        try {
            statement = connect.createStatement();
            if (!statement.execute(query))
                System.out.println("delete OK!");
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public ResultSet select(String query) throws SQLException {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}

