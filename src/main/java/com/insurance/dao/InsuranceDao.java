package main.java.com.insurance.dao;

import main.java.com.insurance.insurance.Insurance;

import java.sql.SQLException;

public interface InsuranceDao {
	
	public void insert(Insurance insurance) throws SQLException;
	public void insertCC(Insurance insurance) throws SQLException;
	public void insertCompensationInfo(Insurance insurance) throws SQLException;

    public void updateState(boolean state, int searchID) throws SQLException;

    public void selectCC(Insurance insurance) throws SQLException;
    public void selectComInfo(Insurance insurance) throws SQLException;
    public boolean checkInsID(Insurance insurance) throws SQLException;

    public boolean checkExistence() throws SQLException;
    public boolean checkNullExistence() throws SQLException;

    public void selectStateNull() throws SQLException;
    public boolean checkExistenceState(boolean state) throws SQLException;

    public void selectState(boolean state) throws SQLException;

    public boolean chooseInsID(int searchID) throws SQLException;

    public void searchInsurance(int searchID) throws SQLException;
    public void selectPeriod(int searchID) throws SQLException;
}
