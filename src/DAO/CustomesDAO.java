package DAO;

import Beans.Customer;

import java.util.ArrayList;

public interface CustomesDAO {
    public boolean isCustomerExist(String email, String password);

    public void addCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void deleteCustomer(int customerID);

    public ArrayList<Customer> getAllCustomers();

    public Customer getOneCustomer(int customerID);
}
