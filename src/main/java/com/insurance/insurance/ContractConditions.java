package main.java.com.insurance.insurance;

import main.java.com.insurance.global.Constant;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ContractConditions {
    private int basicPremium;
    private int joiningAge;
    private Constant.InsurancePeriod insurancePeriod;
    private Constant.PayPeriod payPeriod;



    public int getBasicPremium() {
        return basicPremium;
    }

    public void setBasicPremium() {
        Scanner sc =new Scanner(System.in);
        while (true) {
            System.out.println("기본 보험료를 입력하세요");
            try {
                this.basicPremium = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("다시입력해라");
            }
        }
    }

    public int getJoiningAge() {
        return joiningAge;
    }

    public void setJoiningAge() {
        Scanner sc =new Scanner(System.in);
        while (true) {
            System.out.println("가입 나이를 입력하세요");
            try {
                this.joiningAge = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("다시입력해라");
            }
        }
    }


    public Constant.InsurancePeriod getInsurancePeriod() {
        return insurancePeriod;
    }

    public void setInsurancePeriod() {
        Scanner sc =new Scanner(System.in);
        System.out.println("보험 기간을 선택하세요.");
        System.out.println("1. 80세");
        System.out.println("2. 90세");
        System.out.println("3. 100세");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                this.insurancePeriod = Constant.InsurancePeriod.AGE_80;
                break;
            case 2:
                this.insurancePeriod = Constant.InsurancePeriod.AGE_90;
                break;
            case 3:
                this.insurancePeriod = Constant.InsurancePeriod.AGE_100;
                break;
            default:
                System.out.println("ㅤㅤㅤ%% 경고 %%");
                System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                System.out.println("ㅤ");
                setInsurancePeriod();
        }

        this.insurancePeriod = insurancePeriod;
    }

    public Constant.PayPeriod getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod() {
        Scanner sc =new Scanner(System.in);
        System.out.println("납입 기간을 선택하세요.");
        System.out.println("1. 10");
        System.out.println("2. 20");
        System.out.println("3. 30");
        System.out.println("4. 전기납");
        int acPayPer = sc.nextInt();
        switch (acPayPer) {
            case 1:
                this.payPeriod = Constant.PayPeriod.TEN_YEARS;
                break;
            case 2:
                this.payPeriod = Constant.PayPeriod.TWENTY_YEARS;
                break;
            case 3:
                this.payPeriod = Constant.PayPeriod.THIRTY_YEARS;
                break;
            case 4:
                this.payPeriod = Constant.PayPeriod.WHOLE_TIME;
                break;
            default:
                System.out.println("ㅤㅤㅤ%% 경고 %%");
                System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                System.out.println("ㅤ");
                setPayPeriod();
                // break;
        }
        this.payPeriod = payPeriod;

    }
}

