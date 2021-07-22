package main.java.com.insurance.join;

import java.util.ArrayList;


public class InsuranceJoinListImpl implements InsuranceJoinList {
    private ArrayList<InsuranceJoin> insuranceJoinList;

    public InsuranceJoinListImpl() {
        insuranceJoinList = new ArrayList<InsuranceJoin>();
    }


    public ArrayList<InsuranceJoin> getInsuranceJoinList() {
        return insuranceJoinList;
    }


    public void setInsuranceJoinList(ArrayList<InsuranceJoin> insuranceJoinList) {
        this.insuranceJoinList = insuranceJoinList;
    }


    @Override
    public boolean add(InsuranceJoin insuranceJoin) {
        this.insuranceJoinList.add(insuranceJoin);
        return true;
    }

    @Override
    public InsuranceJoin searchByCustomer(String customerID) {
        for (InsuranceJoin insuranceJoin : this.insuranceJoinList) {
            if (insuranceJoin.getCustomerID().equals(customerID)) {
                return insuranceJoin;
            }
        }
        return null;
    }

    @Override
    public InsuranceJoin searchByInsurance(int insuranceID) {
        for (InsuranceJoin insuranceJoin : this.insuranceJoinList) {
            if (insuranceJoin.getInsuranceID() == insuranceID) {
                return insuranceJoin;
            }
        }
        return null;
    }

    @Override
    public boolean underWriting(Boolean state){
        for (InsuranceJoin insuranceJoin : this.insuranceJoinList){
            if (insuranceJoin.getState() == state) {
                System.out.println("고객 id: " + insuranceJoin.getCustomerID() + " 보험 id: " + insuranceJoin.getInsuranceID());
            }
        }
        return true;
    }

}
