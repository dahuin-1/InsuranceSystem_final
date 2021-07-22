package main.java.com.insurance.dao;

import main.java.com.insurance.compensation.InsuranceCompensation;
import main.java.com.insurance.compensation.InsuranceCompensationList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompensationDaoImpl extends Dao implements CompensationDao {
    public CompensationDaoImpl() {
        try {
            super.coneect();
        } catch (Exception e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void insert(InsuranceCompensation insCompensation) throws SQLException {
        String query;

        try {
            query = "insert into insuranceCompensation (customer_id, insurance_id, state, dateDemand, damageAmount) values (" +
                    insCompensation.getCustomerID() + ", " +
                    insCompensation.getInsuranceID() + ", " +
                    insCompensation.getState() + ", " +
                    "'" + insCompensation.getDateDemand() + "', " +
                    insCompensation.getDamageAmount() + ");";
            super.insert(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 보험, 가입 join -> 가입한 보험 상품 정보 출력
    @Override
    public void selectIns(String searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select insuranceJoin.insurance_id, insurance.type, insurance.name " +
                    "from insuranceJoin, insurance where insuranceJoin.insurance_id=insurance.id " +
                    "AND insuranceJoin.customer_id = '" + searchID + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("보험 ID: " + resultSet.getInt("insurance_id"));
                System.out.println("보험 종류: " + resultSet.getString("insurance.type"));
                System.out.println("가입 상품명: " + resultSet.getString("insurance.name"));
            }

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 해당 고객의 가입된 보험 있는지 확인. id 있으면 true, 없으면 false (보상 청구)
    @Override
    public boolean checkInsID(String custID, int insID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insuranceJoin where customer_id='" + custID + "' AND insurance_id=" + insID + ";";
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

    // 보험 ID 검색

    @Override
    public int searchInsID(int searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select insurance_id from insuranceJoin where insurance_id = " + searchID + ";";
            resultSet = super.select(query);
            while (resultSet.next()) {
                return resultSet.getInt("insurance_id");
            }
            resultSet.close();
            return 0;
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 보상 청구 유무 확인. 있으면 true, 없으면 false
    @Override
    public boolean checkExistence() throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insuranceCompensation;";
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

    // state null인 상품 유무 확인. 있으면 true, 없으면 false
    @Override
    public boolean checkNullExistence() throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insuranceCompensation where state is null;";
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

    // state null인 가입 정보 출력
    @Override
    public void selectStateNull() throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select customer_id, insurance_id from insuranceCompensation where state is null;";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("고객 ID: " + resultSet.getString("customer_id")
                        + " 보험 ID: " + resultSet.getInt("insurance_id"));
            }
           resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 고객 id 입력 오류
    @Override
    public boolean chooseID(String searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insuranceCompensation where customer_id = '" + searchID + "';";
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

    // 청구 신청 정보 출력
    @Override
    public void selectCom(String searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select customer_id, dateDemand, damageAmount " +
                    "from insuranceCompensation where customer_id = '" + searchID + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("고객 ID: " + resultSet.getString("customer_id"));
                System.out.println("청구 날짜: " + resultSet.getDate("dateDemand"));
                System.out.println("청구 금액: " + resultSet.getInt("damageAmount"));
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateState(boolean state, String searchID) throws SQLException {
        String query;
        try {
            query = "update insuranceCompensation set state=" + state
                    + " where customer_id = '" + searchID + "';";
            super.update(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateApproval(InsuranceCompensation insCompensation) throws SQLException {
        String query;
        try {
            query = "update insuranceCompensation set datePayment='" + insCompensation.getDatePayment() + "', " +
                    "compensationAmount=" + insCompensation.getCompensationAmount() + ", " +
                    "bankName='" + insCompensation.getBankName() + "', " +
                    "bankAccount=" + insCompensation.getBankAccount() + ", " +
                    "accountName='" + insCompensation.getAccountName() + "' " +
                    "where customer_id='" + insCompensation.getCustomerID() +
                    "' AND insurance_id=" + insCompensation.getInsuranceID() + ";";
            super.update(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateDeny(InsuranceCompensation insCompensation) throws SQLException {
        String query;
        try {
            query = "update insuranceCompensation set refuseReason='" + insCompensation.getRefuseReason() + "' " +
                    "where customer_id='" + insCompensation.getCustomerID() +
                    "' AND insurance_id=" + insCompensation.getInsuranceID() + ";";
            System.out.println(query);
            super.update(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
