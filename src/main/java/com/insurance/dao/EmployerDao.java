package main.java.com.insurance.dao;

import java.sql.SQLException;

public interface EmployerDao {
    public String selectPW(String id) throws SQLException;

    public int checkID(String id) throws SQLException;
}
