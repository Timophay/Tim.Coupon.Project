package DAO;

import Beans.Coupon;

import java.util.ArrayList;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon);

    public void updateCoupon(Coupon coupon);

    public void deleteCoupon(int couponID);

    public ArrayList<Coupon> getAllCoupons();

    public Coupon getOneCoupon(int couponID);

    public void addCouponPurchase(int customerID, int couponID);

    public void deleteCouponPurchase(int coustomerID, int couponID);
}
