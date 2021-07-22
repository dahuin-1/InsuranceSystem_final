package main.java.com.insurance.dao;

import main.java.com.insurance.compensation.InsuranceCompensation;
import main.java.com.insurance.compensation.InsuranceCompensationList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CompensationDao {
    public void insert(InsuranceCompensation insCompensation) throws SQLException;

    public void selectIns(String searchID) throws SQLException;
    public boolean checkInsID(String custID, int insID) throws SQLException;
    public int searchInsID(int searchID) throws SQLException;

    public boolean checkExistence() throws SQLException;
    public boolean checkNullExistence() throws SQLException;
    public void selectStateNull() throws SQLException;

    public boolean chooseID(String searchID) throws SQLException;
    public void selectCom(String searchID) throws SQLException;

    public void updateState(boolean state, String searchID) throws SQLException;
    public void updateApproval(InsuranceCompensation insCompensation) throws SQLException;
    public void updateDeny(InsuranceCompensation insCompensation) throws SQLException;



}
