package main.java.com.insurance.customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    Customer add(Customer customer, ArrayList<Customer> customerArrayList);
    boolean delete(String customerID, ArrayList<Customer> customerArrayList);
    Customer search(String customerID, List<Customer> customerArrayList);
}
