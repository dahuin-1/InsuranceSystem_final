package main.java.com.insurance.insurance;

import java.util.Scanner;

public class CompensationInfo {
    private int compensationAmount;
    private String compensationReason;
    private String compensationName;
    private static Scanner scanner = new Scanner(System.in);

    public int getCompensationAmount() {
        return compensationAmount;
    }

    public void setCompensationAmount() {
        System.out.println("지급 금액을 입력하세요");
        this.compensationAmount = scanner.nextInt();
    }

    public String getCompensationReason() {
        return compensationReason;
    }

    public void setCompensationReason() {
        System.out.println("지급 사유를 입력하세요");
        scanner.nextLine();
        this.compensationReason  = scanner.nextLine();
    }

    public String getCompensationName() {
        return compensationName;
    }

    public void setCompensationName() {
        System.out.println("보장 내용을 입력하세요.");
        this.compensationName = scanner.nextLine();
    }
}
