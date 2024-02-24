package DBDAO;
import Beans.Category;
import sql.ConnectionPool;
import sql.CouponsQuery;
import sql.DBmanager;

import Beans.Coupon;
import DAO.CouponsDAO;
import sql.DButils;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class CouponsDBDAO implements CouponsDAO {

    public void addCoupon(Coupon coupon) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyId());
        params.put(2, Category.valueOf(coupon.getCategory().toString()).ordinal());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, Date.valueOf(coupon.getStartDate()));
        params.put(6, Date.valueOf(coupon.getEndDate()));
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());
        if(DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.addCouponQuery(), params, "couponsDBDAO: addCoupon"))
            System.out.println(coupon.getTitle() + " coupon added.");
    }

    public void updateCoupon(Coupon coupon) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyId());
        params.put(2, Category.valueOf(coupon.getCategory().toString()).ordinal());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, Date.valueOf(coupon.getStartDate()));
        params.put(6, Date.valueOf(coupon.getEndDate()));
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());
        params.put(10, coupon.getId());
        if(DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.updateCouponQuery(), params, "couponsDBDAO: updateCoupon"))
            System.out.println(coupon.getTitle() + " coupon updated.");
    }

    public void deleteCoupon(int couponID) {
        deleteCouponsPurchasesByCouponId(couponID);
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        if(DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.deleteCouponQuery(), params, "couponsDBDAO: deleteCoupon"))
            System.out.println("");
    }
    //extra
    private void deleteCouponsPurchasesByCouponId(int couponId){
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponId);
        DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.deleteCouponsPurchasesByCouponIdQuery(), params, "couponsDBDAO: deleteCouponsPurchasesByCouponId");
    }

    public ArrayList<Coupon> getAllCoupons() {
        ArrayList<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet = DButils.runQueryWithoutParamsAndWithResults(CouponsQuery.getAllCouponsQuery(), "couponsDBDAO: getAllCoupons");
        try {
            while (resultSet.next()){
                Coupon coupon = new Coupon(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        Category.values()[resultSet.getInt(3)],
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getDate(7).toLocalDate(),
                        resultSet.getInt(8),
                        resultSet.getDouble(9),
                        resultSet.getString(10)
                );
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coupons;
    }

    public Coupon getOneCoupon(int couponID) {
        try {
            Map<Integer, Object> params = new HashMap<>();
            params.put(1, couponID);
            ResultSet resultSet = DButils.runQueryWithParamsAndWithResults(CouponsQuery.getCouponByIdQuery(),params, "couponsDBDAO: getOneCoupon");
            if (resultSet.next()){
                Coupon coupon = new Coupon(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        Category.values()[resultSet.getInt(3)],
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getDate(7).toLocalDate(),
                        resultSet.getInt(8),
                        resultSet.getDouble(9),
                        resultSet.getString(10)
                );
                return coupon;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void addCouponPurchase(int customerID, int couponID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponID);
        if(DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.addCouponPurchaseQuery(), params, "couponsDBDAO: addCouponPurchase"))
            System.out.println("coupon purchased");
    }
    public void deleteCouponPurchase(int customerID, int couponID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        params.put(2, customerID);
        if(DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.deleteCouponPurchaseQuery(), params, "couponsDBDAO: deleteCouponPurchase")){
            System.out.println("coupon deleted successfully");
        }
    }
    //extra
    public ArrayList<Coupon> getAllCouponsPurchasesByCustomerId(int customerId){
        ArrayList<Coupon>  coupons = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        ResultSet resultSet = DButils.runQueryWithParamsAndWithResults(CouponsQuery.getAllCouponsPurchasesByCustomerIdQuery(), params, "couponsDBDAO: getAllCouponsPurchasesByCustomerId");
        try {
            while (resultSet.next()) {
                if(resultSet.getInt(1) == customerId){
                    coupons.add(this.getOneCoupon(resultSet.getInt(2)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coupons;
    }
    public void deleteCouponsByCompanyId(int companyId){
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.deleteCouponsByCompanyIdQuery(), params, "couponsDBDAO: deleteCouponsByCompanyId");
    }
    public void deleteCouponPurchasesByCompanyId(int companyId){
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        ResultSet resultSet = DButils.runQueryWithParamsAndWithResults(CouponsQuery.getAllCouponsIdsByCompanyIdQuery(), params, "couponsDBDAO: deleteCouponPurchasesByCompanyId");
        params.clear();
        try {
            while (resultSet.next()){
                params.put(1, resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: CouponsDBDAO deleteCouponPurchasesByCompanyId " + e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            while (resultSet.next()){
                DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.deleteCouponPurchasesByCouponIdQuery(), params, "couponsDBDAO: deleteCouponPurchasesByCompanyId");
            }
        }
        catch (SQLException e){
            System.out.println("ERROR: CouponsDBDAO deleteCouponPurchasesByCompanyId " + e.getMessage());
        }
    }
    public void deleteAllExpiredCoupons(){
        ResultSet resultSet = DButils.runQueryWithoutParamsAndWithResults(CouponsQuery.getAllExpiredCouponsIdQuery(), "couponsDBDAO: deleteAllExpiredCoupons");
        Map<Integer, Object> params = new HashMap<>();
        try {
            while (resultSet.next()){
                params.put(1, resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        params.forEach((key, value) -> {
            this.deleteCouponsPurchasesByCouponId((Integer) value);
        });
        DButils.runQueryWithParamsAndWithoutResults(CouponsQuery.deleteAllExpiredCouponsQuery(), params, "couponsDBDAO: deleteAllExpiredCoupons");
    }
}
