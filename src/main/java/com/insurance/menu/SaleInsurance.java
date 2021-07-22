package main.java.com.insurance.menu;

import main.java.com.insurance.customer.Customer;
import main.java.com.insurance.customer.CustomerService;
import main.java.com.insurance.customer.CustomerServiceImpl;
import main.java.com.insurance.dao.*;
import main.java.com.insurance.insurance.ActualExpense;
import main.java.com.insurance.insurance.Driver;
import main.java.com.insurance.insurance.InsuranceService;
import main.java.com.insurance.join.InsuranceJoin;
import main.java.com.insurance.join.InsuranceJoinList;
import main.java.com.insurance.join.InsuranceJoinListImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SaleInsurance {
	public SaleInsurance(InsuranceService insuranceList) {
		this.insuranceList = insuranceList;
	}
	private CustomerDao customerDao = new CustomerDaoImpl();
	private InsuranceDao insuranceDao = new InsuranceDaoImpl();
	private JoinDao joinDao = new JoinDaoImpl();
	List<Customer> customerList = new ArrayList<>();
	public CustomerService customerService = new CustomerServiceImpl();
	public InsuranceService insuranceList;
	public InsuranceJoin insuranceJoin;
	public InsuranceJoinList insuranceJoinList = new InsuranceJoinListImpl();
	private Scanner scanner = new Scanner(System.in);

	public void showSaleMenu() throws SQLException {
		Scanner sc = new Scanner(System.in);
//		while (true) {
			System.out.println("**********상품 영업하기**********");
			System.out.println("1. 상품 판매하기");
			System.out.println("2. 판매 내역 조회하기");
			System.out.println("3. 돌아가기");
//			try{
				int saleMenu = sc.nextInt();
				switch (saleMenu) {
					case 1:
						// 1. 상품판매하기
						joincustomer();
						break;
					case 2:
						// 2. 판매 내역 조회하기
						salesCheck();
						break;
					case 3:
						break;
					default:
						System.out.println("ㅤㅤㅤ%% 경고 %%");
						System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
						System.out.println("ㅤ");
						showSaleMenu();
				}
//			} catch (InputMismatchException e) {
//				sc = new Scanner(System.in);
//				System.out.println("원하시는 메뉴를 문자가 아닌 숫자로 입력해주세요");
//			}
//		}
	}

	public void joincustomer() throws SQLException {
		System.out.println("1. 실비보험");
		System.out.println("2. 운전자보험");
		System.out.println("3. 돌아가기");

		int selNum = scanner.nextInt();
		switch (selNum) {
			case 1:
				// 1. 실비보험
				if (!insuranceDao.checkExistence()) {
					System.out.println("가입 가능한 상품이 존재하지 않습니다.");
				}
				else {
					System.out.println("고객 정보를 입력하세요");
					Customer actualCust = new Customer();
					actualCust.setCustomerID();
					// ID 중복 체크
					if (customerDao.checkCustID(actualCust)) {
						System.out.println("해당 ID가 존재합니다.");
						joincustomer();
						break;
					}
					actualCust.setPassword(); // pw 추가
					actualCust.setName();
					actualCust.setAge();
					actualCust.setBirth();
					actualCust.setGender();
					actualCust.setPhoneNumber();
					actualCust.setJob();
					actualCust.setDiseaseHistory();
					actualCust.setFamilyDiseaseHistory();

					customerList.add(actualCust);
					customerDao.insert(actualCust);
					System.out.println("가입을 원하는 보험 상품 id를 입력하세요");

					joinDao.selectActualID();
					int aInsId = scanner.nextInt();
					// 보험 id 입력 오류
					if (!insuranceDao.chooseInsID(aInsId)) {
						System.out.println("잘못 입력하셨습니다.");
						joincustomer();
					}
					// 상품 설계 단계에서 받은 납입/만기 기간을 원하지 않으면 가입 취소
					insuranceDao.selectPeriod(aInsId);

					System.out.println("1. 동의 2. 취소");
					int num = scanner.nextInt();
					if (num == 1) {
						joinDao.selectCustInfo(actualCust);
						joinDao.selectInsInfo(aInsId);
						System.out.println("*****예상 보험료 안내*****");
						ActualExpense actualExpense = new ActualExpense();
						actualExpense.calculateRiskRate(actualCust);
						actualExpense.setRiskRate();
						float expectedPremium = joinDao.selectBasicPremium(aInsId) * actualExpense.getRiskRate();

						System.out.println("위험률 : " + actualExpense.getRiskRate());
						System.out.println("예상 월 보험료 : " + expectedPremium);
						System.out.println();
						System.out.println("가입하시겠습니까?");
						System.out.println("1. 예 2. 아니오");
						int jNum = scanner.nextInt();
						if (jNum == 1) {
							// add된 customer와 이미 생성되어있는 insurance 연결
							insuranceJoin = new InsuranceJoin();
							insuranceJoin.setCustomerID(customerList.get(customerList.size() - 1).getCustomerID());
							insuranceJoin.setInsuranceID(joinDao.searchInsID(aInsId));
							insuranceJoin.setState(null);
							insuranceJoin.setRiskRate(actualExpense.getRiskRate());
							insuranceJoin.setExpectedPremium(expectedPremium);

							insuranceJoinList.add(insuranceJoin);
							joinDao.insert(insuranceJoin);
							System.out.println();
							System.out.println("가입 완료되었습니다.");
						} else {
							System.out.println("가입 취소되었습니다");
							showSaleMenu();
						}
					} else {
						System.out.println("가입 취소되었습니다");
					}
				}
				break;

			case 2:
				// 2. 운전자보험
				if (!insuranceDao.checkExistence()) {
					System.out.println("가입 가능한 상품이 존재하지 않습니다.");
				}
				else {
					System.out.println("고객 정보를 입력해주세요");
					Customer driverCust = new Customer();
					driverCust.setCustomerID();
					// ID 중복 체크
					if (customerDao.checkCustID(driverCust)) {
						System.out.println("해당 ID가 존재합니다.");
						joincustomer();
					}
					driverCust.setPassword();
					driverCust.setName();
					driverCust.setAge();
					driverCust.setBirth();
					driverCust.setGender();
					driverCust.setPhoneNumber();
					driverCust.setJob();
					driverCust.setDiseaseHistory();
					driverCust.setFamilyDiseaseHistory();
					driverCust.setDrivingExperience();
					driverCust.setAccidentIn3m();

					customerList.add(driverCust);
					customerDao.insert(driverCust);
					customerDao.updateDriver(driverCust);

					System.out.println("가입을 원하는 보험 상품 id를 입력하세요");
					joinDao.selectDriverID();
					int dInsId = scanner.nextInt();
					// 보험 id 입력 오류
					if (!insuranceDao.chooseInsID(dInsId)) {
						System.out.println("잘못 입력하셨습니다.");
						joincustomer();
					}
					// 상품 설계 단계에서 받은 납입/만기 기간을 원하지 않으면 가입 취소
					System.out.println("보험 상품의 납입 기간과 만기기간을 확인해주세요");
					insuranceDao.selectPeriod(dInsId);

					System.out.println("1. 동의 2. 취소");
					int num = scanner.nextInt();
					if (num == 1) {
						// 고객 정보 + 보험 이름 출력
						joinDao.selectCustInfo(driverCust);
						joinDao.selectInsInfo(dInsId);
						System.out.println("*****예상 보험료 안내*****");
						Driver driver = new Driver();
						driver.calculateRiskRate(driverCust);
						driver.setRiskRate();
						float expectedPremium = joinDao.selectBasicPremium(dInsId) * driver.getRiskRate();

						System.out.println("위험률 : " + driver.getRiskRate());
						System.out.println("예상 월 보험료 : " + expectedPremium);
						System.out.println();
						System.out.println("가입하시겠습니까?");
						System.out.println("1. 예 2. 아니오");
						int jNum = scanner.nextInt();
						if (jNum == 1) {
							// add된 customer와 이미 생성되어있는 insurance 연결
							insuranceJoin = new InsuranceJoin();
							insuranceJoin.setCustomerID(customerList.get(customerList.size() - 1).getCustomerID());
							insuranceJoin.setInsuranceID(joinDao.searchInsID(dInsId));
							insuranceJoin.setState(null);
							insuranceJoin.setRiskRate(driver.getRiskRate());
							insuranceJoin.setExpectedPremium(expectedPremium);

							insuranceJoinList.add(insuranceJoin);
							joinDao.insert(insuranceJoin);
							System.out.println();
							System.out.println("가입 완료되었습니다.");
						} else {
							System.out.println("가입 취소되었습니다");
							showSaleMenu();
						}
					} else {
						System.out.println("가입 취소되었습니다");
					}
				}
				break;
			case 3:
				break;
			default:
				System.out.println("ㅤㅤㅤ%% 경고 %%");
				System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
				System.out.println("ㅤ");
				joincustomer();
		}
	}


	private void salesCheck() throws SQLException {
		System.out.println("판매 내역 조회하기 메뉴입니다");
		System.out.println("1. 고객별 가입 상품 조회하기");
		System.out.println("2. 상품별 가입자수 조회하기");
		System.out.println("3. 돌아가기");

		int salesNum = scanner.nextInt();
		switch (salesNum) {
			case 1:
				if (!joinDao.checkExistence()) {
					System.out.println("조회 가능한 고객이 존재하지 않습니다.");
				}
				System.out.println("조회하고 싶은 고객 ID를 입력하세요.");
				String searchCID = scanner.next();
				// 가입한 고객 id 없을 때
				if (!joinDao.checkCustID(searchCID)) {
					System.out.println("해당 고객 ID가 존재하지 않습니다.");
					salesCheck();
				} else {
					joinDao.searchCust(searchCID);
				}
				break;

			case 2:
				System.out.println("조회하고 싶은 보험 ID를 입력하세요.");
				int searchIID = scanner.nextInt();
				// 가입한 보험 id 없을 때
				if (!joinDao.checkInsID(searchIID)) {
					System.out.println("해당 보험 ID가 존재하지 않습니다.");
					salesCheck();
				} else {
					joinDao.searchIns(searchIID);
				}
				break;

			case 3:
				break;
			default:
				System.out.println("ㅤㅤㅤ%% 경고 %%");
				System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
				System.out.println("ㅤ");
				salesCheck();
		}

	}

}