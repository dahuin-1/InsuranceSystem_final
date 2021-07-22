package main.java.com.insurance.compensation;

import java.util.ArrayList;
import main.java.com.insurance.compensation.InsuranceCompensation;


public class InsuranceCompensationList {	
	private ArrayList<InsuranceCompensation> insuranceCompensationList;
	public InsuranceCompensationList() {
		insuranceCompensationList = new ArrayList<InsuranceCompensation>();
	}

	public ArrayList<InsuranceCompensation> getInsuranceCompensationList() {
		return insuranceCompensationList;
	}

	public void setInsuranceCompensationList(ArrayList<InsuranceCompensation> insuranceCompensationList) {
		this.insuranceCompensationList = insuranceCompensationList;
	}
	
	public InsuranceCompensation add(InsuranceCompensation insuranceCompensation) {
		insuranceCompensationList.add(insuranceCompensation);
		return insuranceCompensation;
	}

	public boolean delete(String customerID) {
		for (InsuranceCompensation insuranceCompensation : insuranceCompensationList) {
			if (insuranceCompensation.getCustomerID().equals(customerID)) {
				insuranceCompensationList.remove(insuranceCompensation);
				return true;
			}
		}
		return false;
	}

	public InsuranceCompensation search(String customerID) {
		for (InsuranceCompensation insuranceCompensation : insuranceCompensationList) {
			if (insuranceCompensation.getCustomerID().equals(customerID)) {
				return insuranceCompensation;
			}
		}
		return null;
	}


	public boolean compensate(Boolean state) {
		for (InsuranceCompensation insuranceCompensation : this.insuranceCompensationList){
			if(insuranceCompensation.getState() == state) {
				// 고객 ID, 보험 ID
				System.out.println("고객 ID: " + insuranceCompensation.getCustomerID()
						+ "  보험 ID: " + insuranceCompensation.getInsuranceID());
			}
		}
		return true;
	}

}
