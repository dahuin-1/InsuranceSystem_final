package main.java.com.insurance.menu;


import main.java.com.insurance.customer.CustomerService;
import main.java.com.insurance.customer.CustomerServiceImpl;
import main.java.com.insurance.dao.CustomerDao;
import main.java.com.insurance.dao.CustomerDaoImpl;
import main.java.com.insurance.dao.EmployerDao;
import main.java.com.insurance.dao.EmployerDaoImpl;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class InsSysMenu {
    private EmployerDao employerDao = new EmployerDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private DevInsurance devIns = new DevInsurance();
    private SaleInsurance saleIns = new SaleInsurance(devIns.insuranceList);
    private SupInsurance supIns = new SupInsurance(saleIns.insuranceJoinList, devIns.insuranceList);
    private ComInsurance comIns = new ComInsurance(devIns.insuranceList, saleIns.insuranceJoinList);
    private CustomerService customerService = new CustomerServiceImpl();



    // 테스트용 직원 계정 만들기(mysql에서 실행)
    // insert into employee (id, password) values ('E1', '1111');

    public void initialMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. 로그인");
        System.out.println("2. 보험 가입하기");
        try {
            int initial = sc.nextInt();
            switch (initial) {
                case 1:
                    logIn();
                    break;
                case 2:
                    saleIns.joincustomer();
                    break;
                default:
                    System.out.println("ㅤㅤㅤ%% 경고 %%");
                    System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                    System.out.println("ㅤ");
                    initialMenu();
                    break;
            }
        }
      catch (InputMismatchException e) {
            sc = new Scanner(System.in);
            System.out.println("원하시는 메뉴를 문자가 아닌 숫자로 입력해주세요");
        }


    }

    static String userType;
    String id, password;
    public void logIn() throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.print("사용자 ID 입력 : ");
        id = sc.next();
        if (customerDao.checkID(id) == 1) {
            userType = "customer";
        } else if (employerDao.checkID(id) == 1) {
            userType = "employer";
        } else {
            System.out.println("존재하지 않는 ID 입니다.");
            logIn();
        }

        System.out.print("사용자 Password 입력 : ");
        password = sc.next();
        if (userType.equals("employer") && employerDao.selectPW(id).equals(password)) {
            System.out.println("직원 로그인 성공!");
            showMainMenu();
        } else if (userType.equals("customer") && customerDao.selectPW(id).equals(password)) {
            System.out.println("고객 로그인 성공!");
            showCustomerMenu();
        } else {
            System.out.println("비밀번호가 틀렸습니다.");
            logIn();
        }

    }

    private void showCustomerMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
            System.out.println("**********보험사 고객 메뉴**********");
            System.out.println("1. 보상 청구하기");
            System.out.println("2. 종료하기");


            int menu = sc.nextInt();
                switch (menu) {
                    case 1:
                        comIns.demandCom();
                        break;
                    case 2:
                        System.out.println("종료되었습니다.");
                        return;
                    default:
                        System.out.println("ㅤㅤㅤ%% 경고 %%");
                        System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                        System.out.println("ㅤ");
                        break;
                }

            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("원하시는 메뉴를 문자가 아닌 숫자로 입력해주세요");
            }
      }

    }


    public void showMainMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    System.out.println("**********보험사 메인 메뉴**********");
                    System.out.println("1. 상품 개발하기");
                    System.out.println("2. 상품 관리하기");
                    System.out.println("3. 상품 영업하기");
                    System.out.println("4. 보상 처리하기");
                    System.out.println("5. 종료하기");

                    int menu = sc.nextInt();

                    switch (menu) {
                        case 1:
                            // 1. 상품 개발하기
                            devIns.showDvMenu();
                            break;
                        case 2:
                            // 2. 상품 관리하기
                            supIns.showSupMenu();
                            break;
                        case 3:
                            // 3. 상품 영업하기
                            saleIns.showSaleMenu();
                            break;
                        case 4:
                            // 4. 보상 처리하기
                            comIns.showComMenu();
                            break;
                        case 5:
                            System.out.println("종료되었습니다.");
                            return;
                        default:
                            System.out.println("ㅤㅤㅤ%% 경고 %%");
                            System.out.println("ㅤ올바른 값을 입력하세요ㅤ");
                            System.out.println("ㅤ");
                            break;
                    }
                } catch (InputMismatchException e) {
                    sc = new Scanner(System.in);
                    System.out.println("원하시는 메뉴를 문자가 아닌 숫자로 입력해주세요");
                }
            }
        }
    }

