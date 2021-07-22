package main.java.com.insurance.customer;

import main.java.com.insurance.global.Constant;

import java.util.Scanner;


public class Customer {
    private boolean accidentIn3m;
    private int age;
    private int birth;
    private String customerID;
    private Constant.FamilyDiseaseHistory familyDiseaseHistory;
    public boolean isMen = true;
    private Constant.DiseaseHistory diseaseHistory;
    private Constant.Job job;
    private String name;
    private int phoneNumber;
    private Constant.DrivingExperience drivingExperience;
    private String password;


    public Constant.DiseaseHistory getDiseaseHistory() { return diseaseHistory; }

    public Constant.FamilyDiseaseHistory getFamilyDiseaseHistory() { return familyDiseaseHistory; }

    public int getAge() {
        return age;
    }

    public int getBirth() {
        return birth;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getPassword() { return password; }

    public int getPhoneNumber() { return phoneNumber; }

    public boolean getAccidentIn3m() { return accidentIn3m; } // 0611 추가

    public boolean getGender() {return isMen; } // 0611 추가

    public Constant.Job getJob() {
        return job;
    }

    public Constant.DrivingExperience getDrivingExperience() { return drivingExperience; }

    public String getName() {
        return name;
    }

    Scanner sc = new Scanner(System.in);


    public void setName() {
        System.out.println("고객 이름을 입력하세요");
        String temp = sc.next();
        this.name = temp.trim();
    }

    public void setPhoneNumber() {
        System.out.println("고객 휴대폰 번호를 입력하세요");
        this.phoneNumber =  sc.nextInt();
    }

    public void setAge() {
        System.out.println("고객 나이를 입력하세요");
        this.age = sc.nextInt();
    }

    public void setBirth() {
        System.out.println("고객 생년월일 6자리를 입력하세요");
        this.birth = sc.nextInt();
    }

    public void setCustomerID() {
        System.out.println("고객 id를 입력하세요");
        String temp = sc.next();
        this.customerID = temp.trim();
    }

    public void setPassword() {
        System.out.println("고객 비밀번호를 입력하세요");
        String temp = sc.next();
        this.password = temp.trim();
    }

    public void setDiseaseHistory() {
        System.out.println("병력에 해당하는 번호를 입력해주세요");
        System.out.println("1.CANCER, 2.HIGH_BLOOD_PRESSURE, 3.DIABETES, 4.NOTHING");
        System.out.println("입력이 끝났으면 0를 입력해주세요");

        while (true) {
            int temp = sc.nextInt();
            if (temp == 1) {
                this.diseaseHistory = Constant.DiseaseHistory.CANCER;
            } else if (temp == 2) {
                this.diseaseHistory = Constant.DiseaseHistory.HIGH_BLOOD_PRESSURE;
            } else if (temp == 3) {
                this.diseaseHistory = Constant.DiseaseHistory.DIABETES;
            } else if (temp == 4) {
                this.diseaseHistory = Constant.DiseaseHistory.NOTHING;
            } else if (temp == 0) {
                break;
            } else {
                System.out.println("잘못 입력하셨습니다");
                setDiseaseHistory();
            }
        }
    }


    public void setFamilyDiseaseHistory() {
        System.out.println("가족력에 해당하는 번호를 입력해주세요");
        System.out.println("1.CANCER, 2.HIGH_BLOOD_PRESSURE, 3.DIABETES, 4.NOTHING");
        System.out.println("입력을 마치셨거나 해당사항이 없으시면 0를 입력해주세요");

        while (true) {
            int temp = sc.nextInt();
            if (temp == 1) {
                this.familyDiseaseHistory = Constant.FamilyDiseaseHistory.CANCER;
            } else if (temp == 2) {
                this.familyDiseaseHistory = Constant.FamilyDiseaseHistory.HIGH_BLOOD_PRESSURE;
            } else if (temp == 3) {
                this.familyDiseaseHistory = Constant.FamilyDiseaseHistory.DIABETES;
            } else if (temp == 4) {
                this.familyDiseaseHistory = Constant.FamilyDiseaseHistory.NOTHING;
            } else if (temp == 0) {
                break;
            }else {
                System.out.println("잘못 입력하셨습니다");
                setFamilyDiseaseHistory();
            }
        }
        this.familyDiseaseHistory = familyDiseaseHistory;
    }

    public void setGender() {
        System.out.println("성별에 해당하는 번호 입력해주세요");
        System.out.println("0.남 1.여");
        while (true) {
            int temp = sc.nextInt();
            if (temp == 0) {
                this.isMen = true;
                break;
            } else if (temp == 1) {
                this.isMen = false;
                break;
            } else {
                System.out.println("잘못 입력하셨습니다");
                setGender();
            }
            break;

        }
        this.isMen = isMen;
    }

    public void setJob() {
        // OFFICE_JOB,TECHNICAL_JOB,DANGEROUS_JOB,DRIVING_JOB, NONE
        System.out.println("직업에 해당하는 번호를 입력해주세요");
        System.out.println("1.DRIVING_JOB, 2.DANGEROUS_JOB, 3.OFFICE_JOB,4.TECHNICAL_JOB, 5.NONE");
        System.out.println("입력이 끝났으면 0를 입력해주세요");

        while (true) {
            int temp = sc.nextInt();
            if (temp == 1) {
                this.job = Constant.Job.DRIVING_JOB;
                //return Job.DRIVING_JOB;
            } else if (temp == 2) {
                this.job = Constant.Job.DANGEROUS_JOB;
                // return Job.DANGEROUS_JOB;
            } else if (temp == 3) {
                this.job = Constant.Job.OFFICE_JOB;
                //  return Job.OFFICE_JOB;
            } else if (temp == 4) {
                this.job = Constant.Job.TECHNICAL_JOB;
                // return Job.TECHNICAL_JOB;
            } else if (temp == 5) {
                this.job = Constant.Job.NONE;
                //  return Job.NONE;
            } else if (temp == 0) {
                break;
            } else {
                System.out.println("잘못 입력하셨습니다");
                setJob();
            }
        }
        this.job = job;
    }


    public void setDrivingExperience () {
        System.out.println("운전경력에 해당하는 번호를 입력해주세요");
        System.out.println("1.LESS_1YEAR, 2.GREATER_5YEARS, 3.ONE_TO_FIVE_YEARS");
        System.out.println("입력이 끝났으면 0를 입력해주세요");
        while(true) {
            int temp = sc.nextInt();
            if (temp == 1) {
                this.drivingExperience = Constant.DrivingExperience.LESS_1YEAR;
                //setAccidentIn3m();
                //return DrivingExperience.LESS_1YEAR;
            } else if (temp == 2) {
                this.drivingExperience = Constant.DrivingExperience.GREATER_5YEARS;
               // setAccidentIn3m();
            } else if (temp == 3) {
                this.drivingExperience = Constant.DrivingExperience.ONE_TO_FIVE_YEARS;
               // setAccidentIn3m();
            }  else if (temp == 0) {
                break;
            } else {
                System.out.println("잘못 입력하셨습니다");
                setDrivingExperience();
            }
        }
        this.drivingExperience = drivingExperience;
    }

    public void setAccidentIn3m() {
        System.out.println("3개월 이내 사고를 낸 경험이 있습니까?");
        System.out.println("0.예, 1.아니오");
        while (true) {
            int temp = sc.nextInt();
            if (temp == 0) {
                this.accidentIn3m = true;
                break;
            } else if (temp == 1) {
                this.accidentIn3m = false;
                break;
            } else {
                System.out.println("다시 입력해주세요");
                setAccidentIn3m();
            }
        }
        this.accidentIn3m = accidentIn3m;
    }

}

