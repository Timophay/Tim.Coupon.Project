import Beans.Coupon;
import DBDAO.CouponsDBDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class CouponExpirationDailyJob implements Runnable{
    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    private List<Coupon> coupons = new ArrayList<>();
    private LocalDate today;
    private boolean quit = false;
    @Override
    public void run() {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (!quit) {
            System.out.println("DELETING ALL EXPIRED COUPONS");
            today = LocalDate.now();
            couponsDBDAO.deleteAllExpiredCoupons();

            try {
                Thread.sleep(24 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                System.out.println("ERROR: CouponExpirationDailyJob " + e.getMessage());
            }
        }
        }
    }