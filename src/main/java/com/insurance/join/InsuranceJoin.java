package main.java.com.insurance.join;

public class InsuranceJoin {
	private String customerID;
	private int insuranceID;
	private Boolean state;
	private float riskRate;
	private float expectedPremium;

	public InsuranceJoin() {
		customerID = null;
		insuranceID = 0;
		state = null;
		riskRate = 0;
		expectedPremium = 0;
	}

	public String getCustomerID() {
		return customerID;
	}

	public int getInsuranceID() {
		return insuranceID;
	}

	public Boolean getState() {
		return state;
	}

	public float getRiskRate() {
		return riskRate;
	}

	public float getExpectedPremium() {
		return expectedPremium;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public void setRiskRate(float riskRate) {
		this.riskRate = riskRate;
	}

	public void setExpectedPremium(float expectedPremium) {
		this.expectedPremium = expectedPremium;
	}

}

