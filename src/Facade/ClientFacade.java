package Facade;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;

public abstract class ClientFacade {
    protected CompaniesDBDAO companiesDBDAO;
    protected CustomersDBDAO customersDBDAO;
    protected CouponsDBDAO couponsDBDAO;
    public abstract boolean logIn(String email, String password);
}
