package main.java.com.insurance.insurance;

import java.util.ArrayList;

public class InsuranceServiceImpl implements InsuranceService {
    private ArrayList<Insurance> insuranceList;

    public InsuranceServiceImpl() {
        insuranceList = new ArrayList<Insurance>();
    }


    public ArrayList<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(ArrayList<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    @Override
    public boolean add(Insurance insurance) {
        this.insuranceList.add(insurance);
        return true;
    }

    @Override
    public boolean confirm(Insurance insurance) {
        insurance.setState(true);
        return true;
    }

    @Override
    public boolean delete(Insurance insurance) {
        this.insuranceList.remove(insurance);
        return true;
    }

    @Override
    public Insurance search(int insuranceID) {
        for (Insurance insurance : this.insuranceList) {
            if (insurance.getInsuranceID() == insuranceID) {
                return insurance;
            }
        }
        return null;
    }

    public boolean showByState(Boolean state){
        for (Insurance insurance : this.insuranceList){
            if(insurance.getState() == state) {
                System.out.println(insurance.getInsuranceID() + " " + insurance.getName());
            }
        }
        return true;
    }

}

