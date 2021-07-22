package main.java.com.insurance.compensation;

import java.util.Scanner;


public class InsuranceCompensation {
	private String customerID;
	private int insuranceID;
	private String dateDemand; //청구 날짜
	private int damageAmount; //청구(피해) 금액
	private String datePayment; //지급 날짜
	private int compensationAmount; //지급 금액
	private String bankName;
	private int bankAccount;
	private String accountName;
	private String refuseReason;
	private Boolean state;

	public InsuranceCompensation() {
		customerID = null;
		insuranceID = 0;
		dateDemand = null;
		damageAmount = 0;
		datePayment = null;
		compensationAmount = 0;
		bankName = null;
		bankAccount = 0;
		accountName = null;
		refuseReason = null;
		state = null;
	}

	public String getCustomerID() {
		return customerID;
	}

	public int getInsuranceID() {
		return insuranceID;
	}

	public String getDateDemand() {
		return dateDemand;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public String getDatePayment() {
		return datePayment;
	}

	public int getCompensationAmount() {
		return compensationAmount;
	}

	public String getBankName() {
		return bankName;
	}

	public int getBankAccount() {
		return bankAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public Boolean getState() {
		return state;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

	Scanner scanner = new Scanner(System.in);

	public void setDateDemand() {
		System.out.println("청구 날짜를 입력하세요(예: 2020-01-01)");
		String temp = scanner.next();
		this.dateDemand = temp.trim();
	}

	public void setDamageAmount() {
		System.out.println("피해 금액을 숫자로만 입력하세요");
		this.damageAmount = scanner.nextInt();
	}

	public void setDatePayment() {
		System.out.println("지급 날짜를 입력하세요(예: 2020-01-01)");
		String temp = scanner.next();
		this.datePayment = temp.trim();
	}

	public void setCompensationAmount() {
		System.out.println("지급 금액을 입력하세요");
		this.compensationAmount = scanner.nextInt();
	}

	public void setBankName() {
		System.out.println("은행명을 입력해주세요");
		String temp = scanner.next();
		this.bankName = temp.trim();
	}

	public void setBankAccount() {
		System.out.println("계좌 번호를 숫자로만 입력하세요");
		this.bankAccount = scanner.nextInt();
	}

	public void setAccountName() {
		System.out.println("계좌주를 입력하세요");
		String temp = scanner.next();
		this.accountName = temp.trim();
	}

	public void setRefuseReason() {
		System.out.println("보상 거부 사유를 입력하세요 ");
		String temp = scanner.next();
		this.refuseReason = temp.trim();
	}

	public void setState(Boolean state) {
		this.state = state;
	}


}
