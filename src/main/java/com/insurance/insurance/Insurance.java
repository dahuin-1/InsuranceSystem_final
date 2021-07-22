package main.java.com.insurance.insurance;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Insurance {
    private String name;
    private int insuranceID;
    private Boolean state;
    private String type;
    private ArrayList<CompensationInfo> compensationInfoList;
    private ContractConditions contractConditions;
    // private static Scanner scanner = new Scanner(System.in);

    public Insurance() {
        name = null;
        insuranceID = 0;
        state = null;
        compensationInfoList = new ArrayList<CompensationInfo>();
        contractConditions = new ContractConditions();
    }

    public String getName() {
        return name;
    }

    public void setName() {
        Scanner sc =new Scanner(System.in);
        while (true) {
            System.out.println("상품 이름을 입력하세요.");
            try {
                //sc.nextLine();
                this.name = sc.nextLine();
                break;
            } catch (Exception e) {
                sc = new Scanner(System.in);
                System.out.println("다시입력해라");
            }
        }
    }

    public int getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID() {
        Scanner sc =new Scanner(System.in);
        while (true) {
            System.out.println("상품 ID를 입력하세요.");
            try {
                this.insuranceID = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("다시입력해라");
            }
        }
    }



    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<CompensationInfo> getCompensationInfoList() {
        return compensationInfoList;
    }

    public void setCompensationInfoList(ArrayList<CompensationInfo> compensationInfoList) {
        this.compensationInfoList = compensationInfoList;
    }

    public ContractConditions getContractConditions() {
        return contractConditions;
    }

    public void setContractConditions(ContractConditions contractConditions) {
        this.contractConditions = contractConditions;
    }
}
