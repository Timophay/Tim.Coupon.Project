package Facade;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
//@AllArgsConstructor
@Builder
public class CompanyFacade extends ClientFacade{
    CompanyFacade(){
        couponsDBDAO = new CouponsDBDAO();
        customersDBDAO = new CustomersDBDAO();
        companiesDBDAO = new CompaniesDBDAO();
    }
    @Override
    public boolean logIn(String email, String password) {
        LogInManager logInManager = LogInManager.getInstance();
        ClientFacade clientFacade = logInManager.logIn(email, password, ClientType.Company);
        if (clientFacade == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public void addCoupon(Coupon coupon){
        couponsDBDAO.addCoupon(coupon);
    }

    public void updateCoupun(Coupon coupon){
        couponsDBDAO.updateCoupon(coupon);
    }
    public void deleteCoupon(int couponId){
        couponsDBDAO.deleteCoupon(couponId);
    }
    public ArrayList<Coupon> getCompanyCoupons(int companyID){
        return (ArrayList<Coupon>) couponsDBDAO.getAllCoupons()
                .stream()
                .filter(coupon -> coupon.getCompanyId() == companyID)
                .collect(Collectors.toList());
    }
    public ArrayList<Coupon> getCompanyCouponsByCategory(int companyID, Category category){
        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons = (ArrayList<Coupon>) this.getCompanyCoupons(companyID)
                .stream()
                .filter(coupon -> coupon.getCategory().toString().equals(category.toString()))
                .collect(Collectors.toList());
        return coupons;
    }
    public ArrayList<Coupon> getCompanyCouponsByMaxPrice(int companyID, double maxPrice){
        ArrayList<Coupon> coupons = new ArrayList<>();
        coupons = (ArrayList<Coupon>) this.getCompanyCoupons(companyID)
                .stream()
                .filter(coupon -> coupon.getPrice() <= maxPrice)
                .collect(Collectors.toList());
        return coupons;
    }
    public Company getCompanyByID(int companyID){
        return companiesDBDAO.getOneCompany(companyID);
    }
}
