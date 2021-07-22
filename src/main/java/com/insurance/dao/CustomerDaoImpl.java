package main.java.com.insurance.dao;

import main.java.com.insurance.customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl extends Dao implements CustomerDao {
    public CustomerDaoImpl() {
        try {
            super.coneect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // customer insert
    @Override
    public void insert(Customer customer) throws SQLException {
        String query;
        try {
            query = "insert into customer (id, pw, name, age, birth, gender, phoneNumber, job, DiseaseHistory, FamilyDiseaseHistory) " +
                    "values ('" + customer.getCustomerID() + "', "
                    + "'" + customer.getPassword() + "', "
                    + "'" + customer.getName() + "', "
                    + customer.getAge() + ", "
                    + customer.getBirth() + ", "
                    + customer.getGender() + ", "
                    + customer.getPhoneNumber() + ", "
                    + "'" + customer.getJob() + "', "
                    + "'" + customer.getDiseaseHistory() + "', "
                    + "'" + customer.getFamilyDiseaseHistory() + "');";
            super.insert(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // driver 정보 추가
    @Override
    public void updateDriver(Customer customer) throws SQLException {
        String query;
        try {
            query = "update customer set DrivingExperience = '" + customer.getDrivingExperience() +
                    "', AccidentIn3m = " + customer.getAccidentIn3m() + " where id = '" + customer.getCustomerID() + "';";
            super.update(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 고객 id 중복 확인. id 있으면 true, 없으면 false
    @Override
    public boolean checkCustID(Customer customer) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from customer where id='" + customer.getCustomerID() + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                if (resultSet.getInt("count(*)") != 0) {
                    return true;
                }
            }
            resultSet.close();
            return false;
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public String selectPW(String id) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select pw from customer where id='" + id + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                return resultSet.getString("pw");
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
            query = "select count(*) from customer where id='" + id + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                return resultSet.getInt("count(*)");
            }
            resultSet.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
