package service;

import java.util.ArrayList;
import model.Customer;
import java.util.Collection;

public class CustomerService {
    private static final ArrayList<Customer> allCustomers = new ArrayList<>();

    public void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        //System.out.println("Added new customer:" + firstName + " " + lastName + ", " + email);
        allCustomers.add(customer);
    }

    public static Customer getCustomer(String customerEmail){
        for (Customer customer : allCustomers) {
            if (customer.getEmail().equals(customerEmail)){
                System.out.println(customer);
                return customer;
            }//System.out.println("There is no customer with email " + customerEmail);
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        if(allCustomers.isEmpty()){
            return null;
        } else {
            return allCustomers;
        }
    }
}
