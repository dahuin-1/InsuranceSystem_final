package main.java.com.insurance.dao;

import main.java.com.insurance.customer.Customer;
import main.java.com.insurance.join.InsuranceJoin;

import java.sql.SQLException;

public interface JoinDao {
    public void insert(InsuranceJoin insuranceJoin) throws SQLException;

    public void selectActualID() throws SQLException;
    public void selectDriverID() throws SQLException;

    public void selectCustInfo(Customer customer) throws SQLException;
    public void selectInsInfo(int searchID) throws SQLException;
    public int selectBasicPremium(int searchID) throws SQLException;
    public int searchInsID(int searchID) throws SQLException;

    public boolean checkExistence() throws SQLException;
    public boolean checkNullExistence() throws SQLException;
    public void selectStateNull() throws SQLException;
    public void updateState(boolean state, String searchID) throws SQLException;

    public boolean chooseCustID(String searchID) throws SQLException;

    public void searchJoinInfo(String searchID) throws SQLException;

    public void searchCust(String searchID) throws SQLException;
    public boolean checkCustID(String searchID) throws SQLException;
    public boolean checkInsID(int searchID) throws SQLException;
    public void searchIns(int searchID) throws SQLException;
}
