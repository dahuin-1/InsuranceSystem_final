package main.java.com.insurance.menu;


import main.java.com.insurance.dao.JoinDao;
import main.java.com.insurance.dao.JoinDaoImpl;
import main.java.com.insurance.insurance.InsuranceService;
import main.java.com.insurance.join.InsuranceJoinList;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class SupInsurance {
	public SupInsurance(InsuranceJoinList insuranceJoinList, InsuranceService insuranceList) {
		this.insuranceList = insuranceList;
		this.insuranceJoinList = insuranceJoinList;
	}

	private JoinDao joinDao = new JoinDaoImpl();
	private Scanner scanner = new Scanner(System.in);
	public InsuranceService insuranceList;
	public InsuranceJoinList insuranceJoinList;

	public void showSupMenu() throws SQLException {
		Scanner sc = new Scanner(System.in);
//		while (true) {
			System.out.println("**********상품 관리하기**********");
			System.out.println("1. 인수 심사하기");
			System.out.println("2. 돌아가기");
//			try {
				int supMenu = sc.nextInt();

				switch (supMenu) {
					case 1:
						// 1. 인수 심사하기
						uwJoin();
						break;
					case 2:
						break;
					default:
						System.out.println("ㅤㅤㅤ%% 경고 %%");
						System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
						System.out.println("ㅤ");
						showSupMenu();
				}
//			} catch (InputMismatchException e) {
//				sc = new Scanner(System.in);
//				System.out.println("원하시는 메뉴를 문자가 아닌 숫자로 입력해주세요");
//			}
//		}
	}

	private void uwJoin() throws SQLException {

		if (!joinDao.checkExistence()) {
			System.out.println("보험 상품이 존재하지 않습니다.");
		}
		if (!joinDao.checkNullExistence()) {
			System.out.println("조회할 상품이 존재하지 않습니다.");
		} else {
			System.out.println("******** 인수 심사 대기 목록 ********");
			joinDao.selectStateNull();
			System.out.println("**********************************");
			System.out.println("가입 승인하고자 하는 목록의 고객 ID를 입력하세요.");
			System.out.println("999: 돌아가기");
			String uwID = scanner.next();
			if (uwID.equals("999")) {
				System.out.println("취소되었습니다.");
			} else {
				// 가입한 고객 id 입력 오류
				if (!joinDao.chooseCustID(uwID)) {
					System.out.println("잘못 입력하셨습니다.");
					uwJoin();
				}
				// 고객 id, 보험 종류, 상품명, 위험률 출력
				joinDao.searchJoinInfo(uwID);
				System.out.println("1. 승인");
				System.out.println("2. 거절");
				int d = scanner.nextInt();
				if (d == 1) {
					joinDao.updateState(true, uwID);
					System.out.println("가입 승인되었습니다.");
				} else if (d == 2) {
					joinDao.updateState(false, uwID);
					System.out.println("가입 거절되었습니다.");
				} else {
					System.out.println("잘못 입력하셨습니다");
				}
			}
		}
	}
}