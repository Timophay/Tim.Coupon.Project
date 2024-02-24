package DBDAO;
import Beans.Company;
import Beans.Coupon;
import DAO.CompaniesDAW;
import sql.CompaniesQuery;
import sql.ConnectionPool;
import sql.DBmanager;
import sql.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAW{
    //@Override
    public boolean isCompanyExist(String email, String password) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        ResultSet resultSet = DButils.runQueryWithParamsAndWithResults(CompaniesQuery.isCompanyExistQuery(), params, "companiesDBDAO: isCompanyExist");
        try {
            if(resultSet.next())
                return true;
            else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCompany(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        if(DButils.runQueryWithParamsAndWithoutResults(CompaniesQuery.addCompanyQuery(), params, "companiesDBDAO: addCompany")){
            System.out.println("a new company has been added");
        }
    }

    public void updateCompany(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        params.put(4, company.getId());
        if(DButils.runQueryWithParamsAndWithoutResults(CompaniesQuery.updateCompanyQuery(), params, "companiesDBDAO: updateCompany")){
            System.out.println("a company has been updated");
        }
    }

    public void deleteCompany(int companyID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        if(DButils.runQueryWithParamsAndWithoutResults(CompaniesQuery.deleteCompanyQuery(), params, "companiesDBDAO: deleteCompany")){
            System.out.println("a company has been deleted");
        }
    }

    public ArrayList<Company> getAllCompanies() {
        ArrayList<Company> companiesList = new ArrayList<>();
        try {
            ResultSet rs = DButils.runQueryWithoutParamsAndWithResults(CompaniesQuery.getAllCompaniesQuery(), "companiesDBDAO: getAllCompanies");
            while (rs.next()){
                Company company = new Company(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                company.setId(rs.getInt(1));
                companiesList.add(company);
            }
            return companiesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Company getOneCompany(int companyID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        ResultSet resultSet = DButils.runQueryWithParamsAndWithResults(CompaniesQuery.getOneCompanyQuery(), params, "companiesDBDAO: getOneCompany");
        try {
            if (resultSet.next()){
                Company company = new Company();
                company.setId(resultSet.getInt(1));
                company.setName(resultSet.getString(2));
                company.setEmail(resultSet.getString(3));
                company.setPassword(resultSet.getString(4));
                return company;
            }
            else {
                System.out.println("company not found");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
