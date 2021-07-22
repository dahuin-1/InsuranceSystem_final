package main.java.com.insurance.customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer add(Customer customer, ArrayList<Customer> customerArrayList) {
        customerArrayList.add(customer);
        return customer;
    }

    @Override
    public boolean delete(String customerID, ArrayList<Customer> customerArrayList) {
        for (Customer customer : customerArrayList) {
            if (customer.getCustomerID().equals(customerID)) {
                customerArrayList.remove(customer);
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer search(String customerID, List<Customer> customerArrayList) {
        for (Customer customer : customerArrayList) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }
}
