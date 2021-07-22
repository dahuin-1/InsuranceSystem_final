package main.java.com.insurance.menu;

import main.java.com.insurance.dao.InsuranceDao;
import main.java.com.insurance.dao.InsuranceDaoImpl;
import main.java.com.insurance.insurance.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DevInsurance {
    private InsuranceDao insuranceDao = new InsuranceDaoImpl();
    public InsuranceService insuranceList = new InsuranceServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void showDvMenu() throws SQLException {
        // 1. 상품 개발하기
        System.out.println("**********상품 개발하기**********");
        System.out.println("1. 상품 설계하기");
        System.out.println("2. 상품 확정 관리하기");
        System.out.println("3. 돌아가기");
        //       try {
        int dvMenu = scanner.nextInt();
        switch (dvMenu) {
            case 1:
                //1. 상품 설계하기
                designIns();
                break;
            case 2:
                // 2. 상품 확정 관리하기
                confirmIns();
                break;
            case 3:
                break;
            default:
                System.out.println("ㅤㅤㅤ%% 경고 %%");
                System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                System.out.println("ㅤ");
                showDvMenu();
        }
//        } catch (InputMismatchException e) {
//            scanner = new Scanner(System.in);
//            System.out.println("원하시는 메뉴를 문자가 아닌 숫자로 입력해주세요");
//        }
    }

    public void designIns() throws SQLException {
        System.out.println("1. 실비보험");
        System.out.println("2. 운전자보험");
        System.out.println("3. 돌아가기");
        int insType = scanner.nextInt();
        switch (insType) {
            case 1:
                // 1. 실비보험
                Insurance actualExpense = new ActualExpense();
                actualExpense.setInsuranceID();
                // ID 중복 체크
                if (insuranceDao.checkInsID(actualExpense)) {
                    System.out.println("해당 ID가 존재합니다.");
                    designIns();
                }
                actualExpense.setName();
                System.out.println("ID : " + actualExpense.getInsuranceID() + "\n" + "이름 : " + actualExpense.getName());
                System.out.println("상품 계약 정보를 입력하세요.");
                ContractConditions acConditions = new ContractConditions();
                acConditions.setJoiningAge();
                acConditions.setBasicPremium();
                acConditions.setPayPeriod();
                acConditions.setInsurancePeriod();
                actualExpense.setContractConditions(acConditions);
                // contractCondition 정보 출력
                insuranceDao.insertCC(actualExpense);
                insuranceDao.selectCC(actualExpense);

                System.out.println("\n" + "상품 약관을 입력하세요.");

                CompensationInfo acCompensationInfo;
                ArrayList<CompensationInfo> acCompensationInfoList = new ArrayList<>();
                int count = 0;
                while (true) {
                    System.out.println("약관 입력을 종료하려면 0을 입력하세요.");
                    System.out.println("계속 입력하려면 0을 제외한 아무 숫자를 입력해주세요.");
                    int i = scanner.nextInt();
                    if (i == 0) {
                        break;
                    }
                    acCompensationInfo = new CompensationInfo();
                    acCompensationInfo.setCompensationName();
                    acCompensationInfo.setCompensationAmount();
                    acCompensationInfo.setCompensationReason();
                    acCompensationInfoList.add(acCompensationInfo);

                    actualExpense.setCompensationInfoList(acCompensationInfoList);

                    System.out.println("보장 내용 : " + actualExpense.getCompensationInfoList().get(count).getCompensationName());
                    System.out.println("지급 금액 : " + actualExpense.getCompensationInfoList().get(count).getCompensationAmount());
                    System.out.println("지급 사유: " + actualExpense.getCompensationInfoList().get(count).getCompensationReason());
                    count++;
                }
                actualExpense.setType("실비 보험");
                insuranceList.add(actualExpense);
                System.out.println("실비 보험 생성 완료");
                insuranceDao.insert(actualExpense);
                insuranceDao.insertCompensationInfo(actualExpense);

                break;

            case 2:
                // 2. 운전자보험
                Insurance driver = new ActualExpense();
                driver.setInsuranceID();
                // ID 중복 체크
                if (insuranceDao.checkInsID(driver)) {
                    System.out.println("해당 ID가 존재합니다.");
                    designIns();
                }
                driver.setName();
                System.out.println("ID : " + driver.getInsuranceID() + "\n" + "이름 : " + driver.getName());

                System.out.println("상품 계약 정보를 입력하세요.");
                ContractConditions drConditions = new ContractConditions();
                drConditions.setJoiningAge();
                drConditions.setBasicPremium();
                drConditions.setPayPeriod();
                drConditions.setInsurancePeriod();
                driver.setContractConditions(drConditions);
                // contractCondition 정보 출력
                insuranceDao.insertCC(driver);
                insuranceDao.selectCC(driver);

                System.out.println("\n" + "상품 약관을 입력하세요.");

                CompensationInfo drCompensationInfo;
                ArrayList<CompensationInfo> drCompensationInfoList = new ArrayList<CompensationInfo>();
                count = 0;
                while (true) {
                    System.out.println("약관 입력을 종료하려면 0을 입력하세요.");
                    System.out.println("계속 입력하려면 0을 제외한 아무 숫자를 입력해주세요.");
                    int i = scanner.nextInt();

                    if (i == 0) {
                        break;
                    }

                    drCompensationInfo = new CompensationInfo();
                    drCompensationInfo.setCompensationName();
                    drCompensationInfo.setCompensationAmount();
                    drCompensationInfo.setCompensationReason();
                    drCompensationInfoList.add(drCompensationInfo);

                    driver.setCompensationInfoList(drCompensationInfoList);

                    System.out.println("보장 내용 : " + driver.getCompensationInfoList().get(count).getCompensationName());
                    System.out.println("지급 금액 : " + driver.getCompensationInfoList().get(count).getCompensationAmount());
                    System.out.println("지급 사유: " + driver.getCompensationInfoList().get(count).getCompensationReason());
                    count++;
                }

                driver.setType("운전자 보험");
                insuranceList.add(driver);
                System.out.println("운전자 보험 생성 완료");

                insuranceDao.insert(driver);
                insuranceDao.insertCompensationInfo(driver);

                break;

            case 3:
                // 3.돌아가기
                showDvMenu();
                break;
            default:
                System.out.println("ㅤㅤㅤ%% 경고 %%");
                System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                System.out.println("ㅤ");
                designIns();
        }
    }

    public void confirmIns() throws SQLException {
        System.out.println("**********상품 확정 관리하기**********");
        System.out.println("1. 상품 확정하기");
        System.out.println("2. 상품 확정 여부 확인하기");
        System.out.println("3. 돌아가기");
        int conMenu = scanner.nextInt();
        switch (conMenu) {
            case 1:
                // 1. 상품 확정하기
                if (!insuranceDao.checkExistence()) {
                    System.out.println("보험 상품이 존재하지 않습니다.");
                }
                if (!insuranceDao.checkNullExistence()) {
                    System.out.println("조회할 상품이 존재하지 않습니다.");
                } else {
                    System.out.println("조회 하고자 하는 상품의 ID를 입력하세요.");
                    insuranceDao.selectStateNull();
                    System.out.println("999. 돌아가기");
                    int conId = scanner.nextInt();
                    if (conId == 999) {
                        break;
                    } else {
                        // 입력 오류

                        // 보험 정보 출력
                        insuranceDao.searchInsurance(conId);
                        System.out.println("1. 확정");
                        System.out.println("2. 보류");
                        int d = scanner.nextInt();
                        if (d == 1) {
                            insuranceDao.updateState(true, conId);
                            System.out.println("해당 상품이 확정 되었습니다.");
                        } else if (d == 2) {
                            insuranceDao.updateState(false, conId);
                            System.out.println("해당 상품이 보류 되었습니다.");
                        } else {
                            System.out.println("잘못 입력하셨습니다.");
                        }
                    }
                }
                break;
            case 2:
                // 2. 상품 확정 여부 확인하기
                System.out.println("1. 확정 목록 확인");
                System.out.println("2. 보류 목록 확인");
                System.out.println("3. 돌아가기 ");

                int checkConMenu = scanner.nextInt();
                switch (checkConMenu) {
                    case 1:
                        if (!insuranceDao.checkExistenceState(true)) {
                            System.out.println("조회할 상품이 존재하지 않습니다.");
                        } else {
                            insuranceDao.selectState(true);
                        }
                        break;
                    case 2:
                        if (!insuranceDao.checkExistenceState(false)) {
                            System.out.println("조회할 상품이 존재하지 않습니다.");
                        } else {
                            insuranceDao.selectState(false);
                        }
                        break;
                    case 3:
                        confirmIns();
                    default:
                        System.out.println("ㅤㅤㅤ%% 경고 %%");
                        System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                        System.out.println("ㅤ");
                        confirmIns();
                }
                break;
            case 3:
                showDvMenu();
                break;
            default:
                System.out.println("ㅤㅤㅤ%% 경고 %%");
                System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                System.out.println("ㅤ");
                confirmIns();
        }
    }
}
