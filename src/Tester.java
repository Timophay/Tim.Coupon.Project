import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Facade.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester implements Runnable{

    @Override
    public void run() {
        AdminFacade adminFacade = (AdminFacade) LogInManager.getInstance().logIn("admin@admin.com", "admin", ClientType.Administrator);

        Company cocaCola = new Company("coca cola", "cocacola@gmail.com", "123456789");
        cocaCola.setId(1);
        Company diadora = new Company("diadora", "diadora@gmail.com", "123456789");
        diadora.setId(2);
        Company BMW = new Company("BMW", "BMW@gmail.com", "123456789");
        BMW.setId(3);
        Company samsung = new Company("samsung", "samsung@gmail.com", "123456789");
        samsung.setId(4);

        Customer yosi = new Customer(1, "yosi", "choen", "yosi@gmail.com", "123456789");
        Customer ilan = new Customer(2, "ilan", "levi", "ilan@gmail.com", "123456789");
        Customer tim = new Customer(3, "tim", "gourevitch", "tim@gmail.com", "123456789");
        Customer pavel = new Customer(4, "pavel", "kuznizov", "pavel@gmail.com", "123456789");

        System.out.println("\nADDING DATA");
        System.out.println("===========");
        adminFacade.addCompany(cocaCola);
        adminFacade.addCompany(diadora);
        adminFacade.addCompany(BMW);
        adminFacade.addCompany(samsung);

        adminFacade.addCustomer(yosi);
        adminFacade.addCustomer(ilan);
        adminFacade.addCustomer(tim);
        adminFacade.addCustomer(pavel);

        CompanyFacade cocaColaFacade = (CompanyFacade) LogInManager.getInstance().logIn("cocacola@gmail.com", "123456789", ClientType.Company);
        CompanyFacade diadoraFacade = (CompanyFacade) LogInManager.getInstance().logIn("diadora@gmail.com", "123456789", ClientType.Company);
        CompanyFacade BMWFacade = (CompanyFacade) LogInManager.getInstance().logIn("BMW@gmail.com", "123456789", ClientType.Company);
        CompanyFacade samsungFacade = (CompanyFacade) LogInManager.getInstance().logIn("samsung@gmail.com", "123456789", ClientType.Company);

        CustomerFacade yosiFacade = (CustomerFacade) LogInManager.getInstance().logIn("yosi@gmail.com", "123456789", ClientType.Customer);
        yosiFacade.setCustomerId(1);
        CustomerFacade ilanFacade = (CustomerFacade) LogInManager.getInstance().logIn("ilan@gmail.com", "123456789", ClientType.Customer);
        ilanFacade.setCustomerId(2);
        CustomerFacade timFacade = (CustomerFacade) LogInManager.getInstance().logIn("tim@gmail.com", "123456789", ClientType.Customer);
        timFacade.setCustomerId(3);
        CustomerFacade pavelFacade = (CustomerFacade) LogInManager.getInstance().logIn("pavel@gmail.com", "123456789", ClientType.Customer);
        pavelFacade.setCustomerId(4);

        Coupon cocaColaCoupon1 = new Coupon(1, 1, Category.Food, "fanta exotic", "drink different", LocalDate.now(), LocalDate.now().plusWeeks(5), 4, 44.12, "photo");
        Coupon cocaColaCoupon2 = new Coupon(2, 1, Category.Food, "fanta shokata", "drink any time", LocalDate.now(), LocalDate.now().plusWeeks(3), 2, 44.12, "photo");
        Coupon cocaColaCoupon3 = new Coupon(3, 1, Category.Food, "sprit", "don't drink", LocalDate.now(), LocalDate.now().plusWeeks(1), 40, 44.12, "photo");
        Coupon cocaColaCoupon4 = new Coupon(4, 1, Category.Food, "free alcohol beer", "alcohol is bad", LocalDate.now(), LocalDate.now().plusWeeks(5), 1, 44.12, "photo");

        Coupon diadoraCoupon1 = new Coupon(5, 2, Category.Electricity, "electric shoes", "walk like a robot", LocalDate.now(), LocalDate.now().plusWeeks(5), 11, 5.5, "photo");
        Coupon diadoraCoupon2 = new Coupon(6, 2, Category.Vacation, "walking shoes", "take a track", LocalDate.now(), LocalDate.now().plusWeeks(5), 2, 6.4, "photo");
        Coupon diadoraCoupon3 = new Coupon(7, 2, Category.Restaurant, "don't eat shoes", "shoes cake", LocalDate.now(), LocalDate.now().plusWeeks(5), 6, 0.1, "photo");
        Coupon diadoraCoupon4 = new Coupon(8, 2, Category.Food, "shoes to eat", "shoes cookies", LocalDate.now(), LocalDate.now().plusWeeks(5), 3, 100.22, "photo");

        Coupon BMWCoupon1 = new Coupon(9, 3, Category.Vacation, "jeep car", "x6", LocalDate.now(), LocalDate.now().plusWeeks(1), 1, 1000000, "photo");
        Coupon BMWCoupon2 = new Coupon(10, 3, Category.Electricity, "electric car", "i8", LocalDate.now(), LocalDate.now().plusWeeks(2), 1, 30000, "photo");
        Coupon BMWCoupon3 = new Coupon(11, 3, Category.Electricity, "electric M7", "M7", LocalDate.now(), LocalDate.now().plusDays(5), 3, 200000, "photo");
        Coupon BMWCoupon4 = new Coupon(12, 3, Category.Food, "car cake", "cake car", LocalDate.now(), LocalDate.now().plusWeeks(8), 90, 10.0, "photo");

        Coupon samsungCoupon1 = new Coupon(13, 4, Category.Electricity, "S23", "new phone", LocalDate.now(), LocalDate.now().plusWeeks(8), 90, 10.0, "photo");
        Coupon samsungCoupon2 = new Coupon(14, 4, Category.Electricity, "", "cake car", LocalDate.now(), LocalDate.now().plusWeeks(8), 90, 10.0, "photo");
        Coupon samsungCoupon3 = new Coupon(15, 4, Category.Electricity, "car cake", "cake car", LocalDate.now(), LocalDate.now().plusWeeks(8), 90, 10.0, "photo");
        Coupon samsungCoupon4 = new Coupon(16, 4, Category.Electricity, "car cake", "cake car", LocalDate.now(), LocalDate.now().plusWeeks(8), 90, 10.0, "photo");


        cocaColaFacade.addCoupon(cocaColaCoupon1);
        cocaColaFacade.addCoupon(cocaColaCoupon2);
        cocaColaFacade.addCoupon(cocaColaCoupon3);
        cocaColaFacade.addCoupon(cocaColaCoupon4);
        diadoraFacade.addCoupon(diadoraCoupon1);
        diadoraFacade.addCoupon(diadoraCoupon2);
        diadoraFacade.addCoupon(diadoraCoupon3);
        diadoraFacade.addCoupon(diadoraCoupon4);
        BMWFacade.addCoupon(BMWCoupon1);
        BMWFacade.addCoupon(BMWCoupon2);
        BMWFacade.addCoupon(BMWCoupon3);
        BMWFacade.addCoupon(BMWCoupon4);
        samsungFacade.addCoupon(samsungCoupon1);
        samsungFacade.addCoupon(samsungCoupon2);
        samsungFacade.addCoupon(samsungCoupon3);
        samsungFacade.addCoupon(samsungCoupon4);

        System.out.println("\nCUSTOMERS PURCHASING COUPONS");
        System.out.println("=============================");
        pavelFacade.purchaseCoupon(cocaColaCoupon4);
        pavelFacade.purchaseCoupon(cocaColaCoupon4);
        timFacade.purchaseCoupon(samsungCoupon1);
        yosiFacade.purchaseCoupon(BMWCoupon1);
        yosiFacade.purchaseCoupon(BMWCoupon2);
        yosiFacade.purchaseCoupon(BMWCoupon3);
        yosiFacade.purchaseCoupon(BMWCoupon4);
        System.out.println();
        System.out.println("\nCOMPANIES UPDATING COUPON");
        System.out.println("=========================");

        ArrayList<Coupon> cocaColaCoupons = cocaColaFacade.getCompanyCouponsByCategory(cocaCola.getId(), Category.Food);
        cocaColaCoupons.forEach(cocaColaCoupon -> cocaColaCoupon.setPrice(cocaColaCoupon.getPrice() * 2));
        cocaColaCoupons.forEach(cocaColaFacade::updateCoupun);
        System.out.println();
        System.out.println("\nGET CUSTOMER COUPONS BY CATEGORY COUPON");
        System.out.println("=======================================");
        System.out.println(pavelFacade.getCustomerCouponsByCategory(Category.Electricity));
        System.out.println(pavelFacade.getCustomerCouponsByCategory(Category.Food));
        System.out.println("\nADMIN DELETING COMPANY");
        System.out.println("=========================");
        adminFacade.deleteCompany(cocaCola.getId());
        System.out.println("\nADMIN UPDATING CUSTOMER");
        System.out.println("=========================");
        Customer customerToUpdate = adminFacade.getOneCustomer(ilan.getId());
        customerToUpdate.setLastName("Mask");
        adminFacade.updateCustomer(customerToUpdate);
        System.out.println("\nADMIN DELETING CUSTOMER");
        System.out.println("=========================");
        adminFacade.deleteCustomer(timFacade.getCustomerId());
        System.out.println("\nADMIN UPDATING COMPANY");
        System.out.println("========================");
        Company companyToUpdate = adminFacade.getOneCompany(BMW.getId());
        companyToUpdate.setName("BMW motors");
        adminFacade.updateCompany(BMW);
        System.out.println("\nADMIN DELETING COMPANY");
        System.out.println("========================");
        Company companyToDelete = adminFacade.getOneCompany(BMW.getId());
        adminFacade.deleteCompany(BMW.getId());

    }
}
