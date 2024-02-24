package DBDAO;

import Beans.Customer;
import DAO.CustomesDAO;
import sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersDBDAO implements CustomesDAO {

    @Override
    public boolean isCustomerExist(String email, String password) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        ResultSet resultSet = DButils.runQueryWithParamsAndWithResults(CustomersQuery.isCustomerExistQuery(), params, "customersDBDAO: isCustomerExist");
        try {
            if(resultSet.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addCustomer(Customer customer) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        if(DButils.runQueryWithParamsAndWithoutResults(CustomersQuery.addCustomerQuery(), params, "customersDBDAO: addCustomer")){
            System.out.println("customer " + customer.getFirstName() + " " + customer.getLastName()  + " added successfully");
        }
    }

    public void updateCustomer(Customer customer) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        params.put(5, customer.getId());
        if(DButils.runQueryWithParamsAndWithoutResults(CustomersQuery.updateCustomerQuery(), params, "customersDBDAO: updateCustomer")){
            System.out.println("customer " + customer.getFirstName() + " " + customer.getLastName()  + " updated successfully");
        }
    }

    public void deleteCustomer(int customerID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        if(DButils.runQueryWithParamsAndWithoutResults(CustomersQuery.deleteCustomerQuery(), params, "customersDBDAO: deleteCustomer")){
            System.out.println("customer has been removed");
        }
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet = DButils.runQueryWithoutParamsAndWithResults(CustomersQuery.getAllCustomersQuery(), "customersDBDAO: getAllCustomers");
        try{
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setEmail(resultSet.getString(4));
                customer.setPassword(resultSet.getString(5));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getOneCustomer(int customerID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        ResultSet resultSet = DButils.runQueryWithParamsAndWithResults(CustomersQuery.getOneCustomerQuery(), params, "customersDBDAO: getOneCustomer");
        Customer customer = new Customer();
        try {
            if (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setEmail(resultSet.getString(4));
                customer.setPassword(resultSet.getString(5));
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
