package main.java.com.insurance.join;

import java.util.ArrayList;

public interface InsuranceJoinList {
	ArrayList<InsuranceJoin> getInsuranceJoinList();

	void setInsuranceJoinList(ArrayList<InsuranceJoin> insuranceJoinList);

	boolean add(InsuranceJoin insuranceJoin);
	InsuranceJoin searchByCustomer(String customerID);
	InsuranceJoin searchByInsurance(int insuranceID);
	boolean underWriting(Boolean state);
}
