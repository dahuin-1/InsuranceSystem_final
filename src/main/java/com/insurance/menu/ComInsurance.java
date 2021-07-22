package main.java.com.insurance.menu;

import main.java.com.insurance.compensation.InsuranceCompensation;
import main.java.com.insurance.compensation.InsuranceCompensationList;
import main.java.com.insurance.dao.CompensationDao;
import main.java.com.insurance.dao.CompensationDaoImpl;
import main.java.com.insurance.dao.JoinDao;
import main.java.com.insurance.dao.JoinDaoImpl;
import main.java.com.insurance.insurance.InsuranceService;
import main.java.com.insurance.join.InsuranceJoinList;

import java.sql.SQLException;
import java.util.Scanner;

public class ComInsurance {
	public ComInsurance(InsuranceService insuranceList, InsuranceJoinList insuranceJoinList) {
		this.insuranceList = insuranceList;
		this.insuranceJoinList = insuranceJoinList;
	}

	private Scanner scanner = new Scanner(System.in);
	public InsuranceService insuranceList;
	public InsuranceJoinList insuranceJoinList;
	private InsuranceCompensation insCompensation = new InsuranceCompensation();
	private InsuranceCompensationList compensationList = new InsuranceCompensationList();
	private CompensationDao compensationDao = new CompensationDaoImpl();
	private JoinDao joinDao = new JoinDaoImpl();

	public void showComMenu() throws SQLException {
		Scanner sc = new Scanner(System.in);
//		while(true) {
			System.out.println("**********보상 처리하기**********");
			System.out.println("1. 보상 청구하기");
			System.out.println("2. 보험금 지급하기");
			System.out.println("3. 돌아가기");
//			try{
				int comMenu = sc.nextInt();

				switch (comMenu) {
					case 1:
						//1. 보상 청구하기
						demandCom();
						break;
					case 2:
						//2. 보험금 지급하기
						paymentIns();
						break;
					case 3:
						break;
					default:
						System.out.println("ㅤㅤㅤ%% 경고 %%");
						System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
						System.out.println("ㅤ");
						showComMenu();
				}
//			} catch (InputMismatchException e) {
//				sc = new Scanner(System.in);
//				System.out.println("원하시는 메뉴를 문자가 아닌 숫자로 입력해주세요");
//			}
//		}
	}

	public void demandCom() throws SQLException {
		System.out.println("고객의 ID를 입력해주세요");
		String custID = scanner.next();
		if (!joinDao.checkCustID(custID)) {
			System.out.println("보험을 가입한 고객이 없습니다.");
			demandCom();
		}
		else {
			// 해당 고객이 가입한 보험 상품 정보 출력 (id, 보험 종류, 보험명)
			compensationDao.selectIns(custID);
			// 상품 선택
			System.out.println("해당하는 보험 ID를 입력하세요.");
			System.out.println("999: 돌아가기");
			int sID = scanner.nextInt();
			if (sID == 999) {
				demandCom();
			} else {
				// 입력 오류
				if (!compensationDao.checkInsID(custID, sID)) {
					System.out.println("잘못 입력하셨습니다");
					demandCom();
				} else {
					System.out.println("보상 청구 내역을 입력해주세요");
					//청구 날짜, (피해)청구 금액
					insCompensation.setDateDemand();
					insCompensation.setDamageAmount();
					System.out.println("청구 내역을 저장하시겠습니까?");
					System.out.println("1.예 2.아니오");
					int a = scanner.nextInt();
					switch (a) {
						case 1:
							insCompensation.setCustomerID(custID);
							insCompensation.setInsuranceID(compensationDao.searchInsID(sID));
							insCompensation.setState(null);
							compensationList.add(insCompensation);
							compensationDao.insert(insCompensation);
							System.out.println("보상 청구가 완료되었습니다.");
							break;
						case 2:
							System.out.println("보상 청구가 취소되었습니다.");
							break;
						default:
							System.out.println("ㅤㅤㅤ%% 경고 %%");
							System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
							System.out.println("ㅤ");
							demandCom();
					}
				}
			}

		}
	}

	public void paymentIns() throws SQLException {
		if (!compensationDao.checkExistence()) {
			System.out.println("보험 청구 내역이 존재하지 않습니다.");
		}
		if (!compensationDao.checkNullExistence()) {
			System.out.println("조회할 청구 내역이 존재하지 않습니다.");
		} else {
			System.out.println("******** 승인 대기 목록 ********");
			compensationDao.selectStateNull();
			System.out.println("**********************************");
			System.out.println("조회 하고자 하는 보상 청구 내역의 고객 ID를 입력하세요");
			System.out.println("999. 돌아가기");
			String payID = scanner.next();
			if (payID.equals("999")) {
				System.out.println("취소되었습니다.");
			} else {
				// 고객 id 입력 오류
				if (!compensationDao.chooseID(payID)) {
					System.out.println("잘못 입력하셨습니다.");
					paymentIns();
				}
				// 해당 고객의 보상 청구 신청 정보 출력
				compensationDao.selectCom(payID);
				System.out.println("승인하시겠습니까?");
				System.out.println("1. 승인");
				System.out.println("2. 거부");
				int d = scanner.nextInt();
				switch (d) {
					case 1:
					//지급 승인
					compensationDao.updateState(true, payID);
					System.out.println("보상 지급 정보를 입력하세요");
					//지급 날짜, 지급액, 계좌번호, 은행명, 계좌주
					insCompensation.setDatePayment();
					insCompensation.setCompensationAmount();
					insCompensation.setBankName();
					insCompensation.setBankAccount();
					insCompensation.setAccountName();
					compensationList.add(insCompensation);

					compensationDao.updateApproval(insCompensation);
					System.out.println("보험금 지급이 완료되었습니다.");
					break;
				case 2:
					//지급 거부
					compensationDao.updateState(false, payID);
					insCompensation.setRefuseReason();
					compensationList.add(insCompensation);

					compensationDao.updateDeny(insCompensation);
					System.out.println("보험금 지급이 거부되었습니다.");
					break;
				default:
				System.out.println("ㅤㅤㅤ%% 경고 %%");
				System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
				System.out.println("ㅤ");
				paymentIns();
				}
			}
		}
	}
}