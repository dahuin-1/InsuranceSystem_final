package main.java.com.insurance.insurance;


import main.java.com.insurance.customer.Customer;

public class Driver extends Insurance {
    private float riskRate;
    public Driver() {
    }

    public float calculateRiskRate(Customer targetCustomer) {
        riskRate = (float) 1.0;
        if (targetCustomer.isMen) {
            riskRate *= 0.9;
        } else {
            riskRate *= 1.1;
        }


// "1.DRIVING_JOB, 2.DANGEROUS_JOB, 3.OFFICE_JOB,4.TECHNICAL_JOB, 5.NONE"
        switch (targetCustomer.getJob()) {
            case NONE:
                riskRate *= 0.9;
                break;
            case OFFICE_JOB:
                riskRate *= 1.0;
                break;
            case TECHNICAL_JOB:
                riskRate *= 1.2;
                break;
            case DANGEROUS_JOB:
                riskRate *= 1.1;
                break;
            case DRIVING_JOB:
                riskRate *= 1.2;
            default:
                riskRate *= 1.0;
                break;

        }
       // 0.NOTHING 1.CANCER, 2.HIGH_BLOOD_PRESSURE, 3.DIABETES"
        switch (targetCustomer.getDiseaseHistory()) {
            case NOTHING:
                riskRate *= 1.0;
                break;
            case CANCER:
                riskRate *= 1.1;
                break;
            case HIGH_BLOOD_PRESSURE:
                riskRate *= 1.2;
                break;
            case DIABETES:
                riskRate *= 1.2;
                break;
            default:
                riskRate *= 1.0;
                break;
        }
        switch (targetCustomer.getFamilyDiseaseHistory()) {
            case NOTHING:
                riskRate *= 1.0;
                break;
            case DIABETES:
                riskRate *= 1.1;
                break;
            case CANCER:
                riskRate *= 1.1;
                break;
            case HIGH_BLOOD_PRESSURE:
                riskRate *= 1.1;
                break;
            default:
                riskRate *= 1.0;
                break;

        }
        return riskRate;
    }
    public float getRiskRate() {
        return riskRate;
    }
    public void setRiskRate() {
        this.riskRate = riskRate;
    }
}
