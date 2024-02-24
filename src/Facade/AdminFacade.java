package Facade;


import Beans.Company;
import Beans.Customer;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
//@AllArgsConstructor
@Builder
public class AdminFacade extends ClientFacade{
    AdminFacade(){
        couponsDBDAO = new CouponsDBDAO();
        customersDBDAO = new CustomersDBDAO();
        companiesDBDAO = new CompaniesDBDAO();
    }
    @Override
    public boolean logIn(String email, String password) {
        LogInManager logInManager = LogInManager.getInstance();
        ClientFacade clientFacade = logInManager.logIn(email, password, ClientType.Administrator);
        if(clientFacade == null)
            return false;
        else
            return true;
    }
    public void addCompany(Company company){
        companiesDBDAO.addCompany(company);
    }
    public void updateCompany(Company company){
        companiesDBDAO.updateCompany(company);
    }
    public void deleteCompany(int companyId){
        couponsDBDAO.deleteCouponPurchasesByCompanyId(companyId);
        couponsDBDAO.deleteCouponsByCompanyId(companyId);
        companiesDBDAO.deleteCompany(companyId);
    }
    public List<Company> getAllCompanies(){
        List<Company> companies = companiesDBDAO.getAllCompanies();
        return companies;
    }
    public Company getOneCompany(int companyID){
        return companiesDBDAO.getOneCompany(companyID);
    }
    public void addCustomer(Customer customer){
        if(customersDBDAO.isCustomerExist(customer.getEmail(), customer.getPassword())){
            System.out.println("this customer exist");
            return;
        }
        customersDBDAO.addCustomer(customer);
    }
    public void updateCustomer(Customer customer){
        customersDBDAO.updateCustomer(customer);
    }
    public void deleteCustomer(int customerId){
        customersDBDAO.deleteCustomer(customerId);
    }
    public List<Customer> getAllCustomers(){
        return customersDBDAO.getAllCustomers();
    }
    public Customer getOneCustomer(int customerID){
        return customersDBDAO.getOneCustomer(customerID);
    }
}
