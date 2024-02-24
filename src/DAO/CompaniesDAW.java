package DAO;

import Beans.Company;

import java.util.ArrayList;

public interface CompaniesDAW {
    public static boolean isCompanyExist(String email, String password) {
        return false;
    }

    public void addCompany(Company company);

    public void updateCompany(Company company);

    public void deleteCompany(int companyID);

    public ArrayList<Company> getAllCompanies();

    public Company getOneCompany(int companyID);

}
