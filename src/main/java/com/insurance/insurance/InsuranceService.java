package main.java.com.insurance.insurance;

import java.util.ArrayList;

public interface InsuranceService {
    ArrayList<Insurance> getInsuranceList();

    void setInsuranceList(ArrayList<Insurance> insuranceList);


    boolean add(Insurance insurance);

    boolean confirm(Insurance insurance);

    boolean delete(Insurance insurance);

    Insurance search(int insuranceID);

    boolean showByState(Boolean state);

}
