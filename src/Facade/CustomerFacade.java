package Facade;

import Beans.Category;
import Beans.Coupon;
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
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class CustomerFacade extends ClientFacade{
    private int customerId;
    CustomerFacade(){
        couponsDBDAO = new CouponsDBDAO();
        customersDBDAO = new CustomersDBDAO();
        companiesDBDAO = new CompaniesDBDAO();
    }
    @Override
    public boolean logIn(String email, String password) {
        LogInManager logInManager = LogInManager.getInstance();
        ClientFacade clientFacade = logInManager.logIn(email, password, ClientType.Customer);
        if (clientFacade == null) {
            return false;
        }
        else {
            return true;
        }
    }
    public void purchaseCoupon(Coupon coupon){
        coupon = couponsDBDAO.getOneCoupon(coupon.getId());
        if (coupon == null){
            System.out.println("coupon is null");
            return;
        }
        if(coupon.getAmount() > 0) {
            coupon.setAmount(coupon.getAmount() - 1);
            couponsDBDAO.updateCoupon(coupon);
        }else {
            System.out.println("No coupons");
            return;
        }
        ArrayList<Coupon> purchasedCoupons = new ArrayList<>();
        purchasedCoupons = couponsDBDAO.getAllCouponsPurchasesByCustomerId(customerId);
        for (Coupon purchesedCoupon : purchasedCoupons){
            if (purchesedCoupon.getId() == coupon.getId()){
                System.out.println("coupon already purchased");
                return;
            }
        }
        couponsDBDAO.addCouponPurchase(customerId, coupon.getId());
    }
    public ArrayList<Coupon> getCustomerCoupons(){
        return couponsDBDAO.getAllCouponsPurchasesByCustomerId(customerId);
    }
    public ArrayList<Coupon> getCustomerCouponsByCategory(Category category){
        List<Coupon> coupons = getCustomerCoupons()
                .stream()
                .filter(coupon -> {
                    if (coupon == null)
                        return false;
                    return coupon.getCategory().toString().equals(category.toString());
                })
                .collect(Collectors.toList());
        return (ArrayList<Coupon>) coupons;
    }
    public ArrayList<Coupon> getCustomerCouponsByMaxPrice(double mapPrice){
        List<Coupon> coupons = getCustomerCoupons();
        coupons = coupons
                .stream()
                .filter(coupon -> coupon.getPrice() <= mapPrice)
                .collect(Collectors.toList());
        return (ArrayList<Coupon>) coupons;
    }
    public Customer getCustomerDetails(){
        return customersDBDAO.getOneCustomer(customerId);
    }
}
