package Facade;

import Beans.Company;
import DBDAO.CompaniesDBDAO;
import DBDAO.CustomersDBDAO;
import lombok.Builder;

@Builder
public class LogInManager {
    private static LogInManager instance = null;

    private LogInManager(){

    }
    public static LogInManager getInstance(){
        if(instance == null){
            synchronized(LogInManager.class){
                if (instance == null){
                    instance = new LogInManager();
                }
            }
        }
        return instance;
    }
    public ClientFacade logIn(String email, String password, ClientType clientType){
        ClientFacade clientFacade;
        if (clientType.equals(ClientType.Administrator)){
            if(email.equals("admin@admin.com") && password.equals("admin"))
                return new AdminFacade();
            else
                return null;
        }
        else if(clientType.equals(ClientType.Company)){
            CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
            if(companiesDBDAO.isCompanyExist(email, password)) {
                return new CompanyFacade();
            }
            else {
                return null;
            }
        }
        else if(clientType.equals(ClientType.Customer)){
            CustomersDBDAO customersDBDAO = new CustomersDBDAO();
            if (customersDBDAO.isCustomerExist(email, password)){
                return new CustomerFacade();
            }
            else {
                return null;
            }

        }
        return null;
    }
}
