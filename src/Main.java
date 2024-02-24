import Beans.*;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import sql.DBmanager;
import sql.SQLCreateDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        try {
            SQLCreateDataBase.createDataBase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Thread tseterThread = new Thread(new Tester());
        tseterThread.start();
        Thread couponExpirationDailyJobThread = new Thread(new CouponExpirationDailyJob());
        couponExpirationDailyJobThread.start();
    }
}