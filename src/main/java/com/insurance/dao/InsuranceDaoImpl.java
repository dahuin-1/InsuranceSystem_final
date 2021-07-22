package main.java.com.insurance.dao;

import main.java.com.insurance.insurance.Insurance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceDaoImpl extends Dao implements InsuranceDao {

    public InsuranceDaoImpl() {
        try {
            super.coneect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertCC(Insurance insurance) throws SQLException {
        String query;
        try {
            query = "insert into contractCondition (joiningAge, basicPremium, payPeriod, insurancePeriod) values (" +
                    insurance.getContractConditions().getJoiningAge() + ", " +
                    insurance.getContractConditions().getBasicPremium() + ", " +
                    "'" + insurance.getContractConditions().getPayPeriod() + "', " +
                    "'" + insurance.getContractConditions().getInsurancePeriod() + "');";
            super.insert(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void insert(Insurance insurance) throws SQLException {
        String query;
        try {
            query = "insert into insurance (ID, name, state, type, contractCondition_id) values (" +
                    insurance.getInsuranceID() + ", " +
                    "'" + insurance.getName() + "', " +
                    insurance.getState() + ", " +
                    "'" + insurance.getType() + "', LAST_INSERT_ID()); ";
            super.insert(query);
        } catch (SQLException e) {
            System.out.println("insert error");
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void insertCompensationInfo(Insurance insurance) throws SQLException {
        String query;
        try {
            for (int i = 0; i < insurance.getCompensationInfoList().size(); i++) {
                query = "insert into compensationInfo (compensationName, compensationAmount, compensationReason, insurance_id) values (" +
                        "'" + insurance.getCompensationInfoList().get(i).getCompensationName() + "', " +
                        insurance.getCompensationInfoList().get(i).getCompensationAmount() + ", " +
                        "'" + insurance.getCompensationInfoList().get(i).getCompensationReason() + "', " +
                        insurance.getInsuranceID() + ");";
                super.insert(query);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 계약 정보 확인
    @Override
    public void selectCC(Insurance insurance) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select joiningAge, basicPremium, payPeriod, insurancePeriod from ContractCondition where id=LAST_INSERT_ID();";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("계약 정보 확인");
                System.out.println("가입 나이: " + resultSet.getInt("joiningAge"));
                System.out.println("기본 보험료: " + resultSet.getInt("basicPremium"));
                System.out.println("납입 기간: " + resultSet.getString("payPeriod"));
                System.out.println("보험 기간: " + resultSet.getString("insurancePeriod"));
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }


    //약관 입력 확인
    @Override
    public void selectComInfo(Insurance insurance) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select compensationName, compensationAmount, compensationReason from compensationInfo where insurance_id="
                    + insurance.getInsuranceID() + ";";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("상품 약관");
                System.out.println("보장 내용: " + resultSet.getString("compensationName"));
                System.out.println("지급 금액: " + resultSet.getInt("compensationAmount"));
                System.out.println("지급 사유: " + resultSet.getString("compensationReason"));
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // id 중복 체크
    @Override
    public boolean checkInsID(Insurance insurance) throws SQLException { //id 있으면 true, 없으면 false
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insurance where id=" + insurance.getInsuranceID() + ";";
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

    // state 변경
    @Override
    public void updateState(boolean state, int searchID) throws SQLException {
        String query;
        try {
            query = "update insurance set state = " + state + " where id = " + searchID + ";";
            super.update(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 보험 상품 유무 확인. 있으면 true, 없으면 false
    @Override
    public boolean checkExistence() throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insurance;";
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
            query = "select count(*) from insurance where state is null;";
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

    // state null인 보험 상품 출력
    @Override
    public void selectStateNull() throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select id, name from insurance where state is null;";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("보험 ID: " + resultSet.getInt("id")
                        + " 상품명: " + resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // state에 따른 상품 유무 확인. 있으면 true, 없으면 false
    @Override
    public boolean checkExistenceState(boolean state) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insurance where state = " + state + ";";
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

    // state에 따른 보험 정보 출력
    @Override
    public void selectState(boolean state) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select id, name from insurance where state=" + state + ";";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("보험 ID: " + resultSet.getInt("id")
                        + " 상품명: " + resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 보험, 계약 정보 join -> 보험 상품 확정
    @Override
    public void searchInsurance(int searchID) throws SQLException {
        String query;
		ResultSet resultSet;
        try {
            query = "select joiningAge, basicPremium, payPeriod, insurancePeriod " +
                    "from insurance, contractCondition where insurance.contractCondition_id = contractCondition.id AND insurance.id = "
                    + searchID + ";";
			resultSet = super.select(query);
			while (resultSet.next()) {
				System.out.println("가입 나이: " + resultSet.getInt("joiningAge"));
				System.out.println("기본 보험료: " + resultSet.getInt("basicPremium"));
				System.out.println("납입 기간: " + resultSet.getString("payPeriod"));
				System.out.println("보험 기간: " + resultSet.getString("insurancePeriod"));
			}
           resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 보험 id 입력 오류 (보험 가입 시)
    @Override
    public boolean chooseInsID(int searchID) throws SQLException {
        String query;
		ResultSet resultSet;
        try {
            query = "select count(*) from insurance where id = " + searchID + ";";
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

    // 보험, 계약 정보 join -> 가입 - 납입/보험 기간 출력
    @Override
    public void selectPeriod(int searchID) throws SQLException {
        String query;
		ResultSet resultSet;
        try {
            query = "select payPeriod, insurancePeriod from insurance, contractCondition " +
                    "where insurance.contractCondition_id = contractCondition.id AND insurance.id = "
                    + searchID + ";";
			resultSet = super.select(query);
			while (resultSet.next()) {
				System.out.println("납입 기간: " + resultSet.getString("payPeriod"));
				System.out.println("보험 기간: " + resultSet.getString("insurancePeriod"));
			}
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}