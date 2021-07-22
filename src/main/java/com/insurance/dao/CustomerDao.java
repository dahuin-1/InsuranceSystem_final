package main.java.com.insurance.dao;

import main.java.com.insurance.customer.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao {
    public void insert(Customer customer) throws SQLException;
    public void updateDriver(Customer customer) throws SQLException;
    public boolean checkCustID(Customer customer) throws SQLException;
    public String selectPW(String id) throws SQLException;
    public int checkID(String id) throws SQLException;
}
