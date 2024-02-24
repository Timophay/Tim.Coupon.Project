package sql;

public class CouponsQuery {
    public static final String addCouponQuery(){
        return "INSERT INTO `" + DBmanager.SQL_DB + "`.`Coupons` " +
                "(company_ID, category_ID, title, description, start_date, end_date, amount, price, image)" +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
    public static final String updateCouponQuery(){
        return "UPDATE `" + DBmanager.SQL_DB + "`.`Coupons` " +
                "SET " +
                "company_ID = ?, " +
                "category_ID = ?, " +
                "title = ?, " +
                "description = ?, " +
                "start_date = ?, " +
                "end_date = ?, " +
                "amount = ?, " +
                "price = ?, " +
                "image = ?" +
            "WHERE id = ?";
    }
    public static final String deleteCouponQuery(){
        return "delete FROM `" + DBmanager.SQL_DB + "`.`Coupons` " +
                "WHERE id = ?;";
    }
    public static final String deleteCouponsPurchasesByCouponIdQuery(){
        return "DELETE FROM `" + DBmanager.SQL_DB + "`.`customers_vs_coupons` " +
                "WHERE coupon_id = ?";
    }
    public static final String getAllCouponsQuery(){
        return "SELECT * FROM `" + DBmanager.SQL_DB + "`.`Coupons` ";
    }
    public static final String getAllCouponsIdsByCompanyIdQuery(){
        return "SELECT id FROM `" + DBmanager.SQL_DB + "`.`Coupons` "
                +"WHERE company_id = ?";
    }
    public static final String getCouponByIdQuery(){
        return "SELECT * FROM `" + DBmanager.SQL_DB + "`.`Coupons` " +
                "WHERE id = ?";
    }
    public static final String addCouponPurchaseQuery(){
        return "INSERT INTO `" + DBmanager.SQL_DB + "`.`Customers_vs_Coupons` " +
                "(customer_ID, coupon_ID) " +
                "VALUES (?, ?)";
    }
    public static final String deleteCouponPurchaseQuery(){
        return "DELETE FROM `" + DBmanager.SQL_DB + "`.`customers_vs_coupons` " +
                "WHERE customer_id = ? AND coupon_id = ?";
    }

    //extra
    public static final String getAllCouponsPurchasesByCustomerIdQuery(){
        return "SELECT * FROM`" + DBmanager.SQL_DB + "`.`customers_vs_coupons` " +
                "WHERE customer_id = ?";
    }
    public static final String deleteCouponsByCompanyIdQuery(){
        return "DELETE FROM `" + DBmanager.SQL_DB + "`.`Coupons` " +
                "WHERE company_id = ?";
    }
    public static final String deleteCouponPurchasesByCouponIdQuery(){
        return "DELETE FROM `" + DBmanager.SQL_DB + "`.`customers_vs_coupons` " +
                "WHERE company_id = ?";
    }
    public static final String getAllExpiredCouponsIdQuery(){
        return "SELECT id FROM `" + DBmanager.SQL_DB + "`.`coupons` " +
                "WHERE end_date = CURDATE()";
    }
    public static final String deleteAllExpiredCouponsQuery(){
        return "DELETE FROM `" + DBmanager.SQL_DB + "`.`coupons` " +
                "WHERE end_date < CURDATE()";
    }
}
