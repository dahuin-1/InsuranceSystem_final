package main.java.com.insurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployerDaoImpl extends Dao implements EmployerDao {
    public EmployerDaoImpl() {
        try {
            super.coneect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String selectPW(String id) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select password from employee where id='" + id + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                return resultSet.getString("password");
            }
            resultSet.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int checkID(String id) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from employee where id='" + id + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                return resultSet.getInt("count(*)");
            }
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
