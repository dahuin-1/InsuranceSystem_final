package main.java.com.insurance.dao;

import main.java.com.insurance.customer.Customer;
import main.java.com.insurance.join.InsuranceJoin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinDaoImpl extends Dao implements JoinDao {
    public JoinDaoImpl() {
        try {
            super.coneect();
        } catch (Exception e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // join insert
    @Override
    public void insert(InsuranceJoin insuranceJoin) throws SQLException {
        String query;
        try {
            query = "insert into insuranceJoin (customer_id, insurance_id, state, riskRate, expectedPremium) values ('" +
                    insuranceJoin.getCustomerID() + "', " +
                    insuranceJoin.getInsuranceID() + ", " +
                    insuranceJoin.getState() + ", " +
                    insuranceJoin.getRiskRate() + ", " +
                    insuranceJoin.getExpectedPremium() + ");";
            super.insert(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 가입 가능한 실비 보험 출력
    @Override
    public void selectActualID() throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select id, name from insurance where state=true AND type = '실비 보험';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.print("보험 ID: " + resultSet.getInt("id"));
                System.out.println(" 보험 이름: " + resultSet.getString("name"));

            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 가입 가능한 운전자 보험 출력
    @Override
    public void selectDriverID() throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select id, name from insurance where state=true AND type = '운전자 보험';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.print("보험 ID: " + resultSet.getInt("id"));
                System.out.println(" 보험 이름: " + resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 가입할 고객 정보 출력
    @Override
    public void selectCustInfo(Customer customer) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select name, birth, age from customer where id = '" + customer.getCustomerID() + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("고객 이름: " + resultSet.getString("name"));
                System.out.println("고객 생년월일: " + resultSet.getInt("birth"));
                System.out.println("고객 나이: " + resultSet.getInt("age"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 고객이 가입할 보험명 출력
    @Override
    public void selectInsInfo(int searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select name from insurance where id = " + searchID + ";";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("보험명: " + resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 보험, 계약정보 join -> 기본 보험료 출력
    @Override
    public int selectBasicPremium(int searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select basicPremium from contractCondition, insurance " +
                    "where insurance.contractCondition_id = contractCondition.id AND insurance.id = "
                    + searchID + ";";
            resultSet = super.select(query);
            while (resultSet.next()) {
                return resultSet.getInt("basicPremium");
            }
            resultSet.close();
            return 0;
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 가입할 보험 ID 검색
    @Override
    public int searchInsID(int searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select id from insurance where id = " + searchID + ";";
            resultSet = super.select(query);
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
            resultSet.close();
            return 0;
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
            query = "select count(*) from insuranceJoin;";
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
            query = "select count(*) from insuranceJoin where state is null;";
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
        String query1, query2;
        ResultSet resultSet1, resultSet2;
        String cusID = null, insName = null;
        int insID = 0;
        try {
            query1 = "select customer_id, insurance_id from insuranceJoin where state is null;";
            resultSet1 = super.select(query1);
            while (resultSet1.next()) {
                cusID = resultSet1.getString("customer_id");
                insID = resultSet1.getInt("insurance_id");
            }

            query2 = "select name from insurance where id =" + insID + ";";
            resultSet2 = super.select(query2);
            while (resultSet2.next()) {
                insName = resultSet2.getString("name");
            }

            System.out.println("고객 ID: " + cusID
                    + " 보험 ID: " + insID
                    + "보험 이름: " + insName);
            resultSet1.close();

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 가입 state 변경
    @Override
    public void updateState(boolean state, String searchID) throws SQLException {
        String query;
        try {
            query = "update insuranceJoin set state = " + state + " where customer_id = '" + searchID + "';";
            super.update(query);
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 가입한 고객 id 입력 오류 (인수 심사)
    @Override
    public boolean chooseCustID(String searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insuranceJoin where customer_id = '" + searchID + "';";
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

    // 보험, 가입 join -> 정보 출력 (인수 심사)
    @Override
    public void searchJoinInfo(String searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select insuranceJoin.customer_id, insurance.type, insurance.name, insuranceJoin.riskRate " +
                    "from insuranceJoin, insurance where insurance.id = insuranceJoin.insurance_id AND insuranceJoin.customer_id = "
                    + searchID + ";";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("고객 ID: " + resultSet.getInt("customer_id"));
                System.out.println("보험 종류: " + resultSet.getString("type"));
                System.out.println("상품명: " + resultSet.getString("name"));
                System.out.println("위험률: " + resultSet.getFloat("riskRate"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 가입한 고객 있는지 확인. id 있으면 true, 없으면 false (고객별 상품 가입 조회 / 보상 청구)
    @Override
    public boolean checkCustID(String searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insuranceJoin where customer_id='" + searchID + "';";
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

    // 고객, 보험, 가입 join (고객별 상품 가입 조회)
    @Override
    public void searchCust(String searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select insuranceJoin.customer_id, customer.name, customer.birth, customer.phoneNumber, insurance.name " +
                    "from insuranceJoin, customer, insurance " +
                    "where insuranceJoin.customer_id=customer.id AND insuranceJoin.insurance_id=insurance.id " +
                    "AND insuranceJoin.state = true AND insuranceJoin.customer_id = '" + searchID + "';";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("고객 ID: " + resultSet.getString("customer_id"));
                System.out.println("고객 이름: " + resultSet.getString("customer.name"));
                System.out.println("생년월일: " + resultSet.getInt("birth"));
                System.out.println("연락처: " + resultSet.getInt("phoneNumber"));
                System.out.println("가입상품명: " + resultSet.getString("insurance.name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 가입된 보험 있는지 확인. id 있으면 true, 없으면 false (상품별 가입자수 조회)
    @Override
    public boolean checkInsID(int searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select count(*) from insuranceJoin where insurance_id=" + searchID + ";";
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

    // 가입, 보험 join (상품별 가입자수 조회)
    @Override
    public void searchIns(int searchID) throws SQLException {
        String query;
        ResultSet resultSet;
        try {
            query = "select insuranceJoin.insurance_id, insurance.name, insurance.type, count(insuranceJoin.customer_id) as count " +
                    "from insuranceJoin, insurance " +
                    "where insuranceJoin.insurance_id=insurance.id " +
                    "AND insuranceJoin.state = true AND insuranceJoin.insurance_id = " + searchID + ";";
            resultSet = super.select(query);
            while (resultSet.next()) {
                System.out.println("보험 ID: " + resultSet.getInt("insurance_id"));
                System.out.println("상품명: " + resultSet.getString("insurance.name"));
                System.out.println("상품 종류: " + resultSet.getString("type"));
                System.out.println("가입자 수: " + resultSet.getInt("count") + "명");
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
