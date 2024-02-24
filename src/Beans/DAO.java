package Beans;

import sql.DBmanager;

import java.sql.*;

public class DAO {
    private final static String DBName = "timcoupondatabase";
    public static void addCompany(Company company) throws SQLException {
        Connection connection = DriverManager.getConnection(DBmanager.URL, DBmanager.SQL_USER, DBmanager.SQL_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + DBName + ".`companies` " +
                "(name, email, password)" +
                "VALUES " +
                "(?, ?, ?)");
        preparedStatement.setString(1, company.getName());
        preparedStatement.setString(2, company.getEmail());
        preparedStatement.setString(3, company.getPassword());
        preparedStatement.execute();
        connection.close();
    }
    public static void addCoupon(Coupon coupon) throws SQLException {
        Connection connection = DriverManager.getConnection(DBmanager.URL, DBmanager.SQL_USER, DBmanager.SQL_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `" + DBName + "`.`coupon` " +
                "(company_id, category_id, title, description, start_date, end_date, amount, price, image) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, coupon.getCompanyId());
        preparedStatement.setInt(2, Category.valueOf(coupon.getCategory().toString()).ordinal());
        preparedStatement.setString(3, coupon.getTitle());
        preparedStatement.setString(4, coupon.getDescription());
        preparedStatement.setDate(5, Date.valueOf(coupon.getStartDate()));
        preparedStatement.setDate(6, Date.valueOf(coupon.getEndDate()));
        preparedStatement.setInt(7, coupon.getAmount());
        preparedStatement.setDouble(8, coupon.getPrice());
        preparedStatement.setString(9, coupon.getImage());
        preparedStatement.execute();
        connection.close();
    }
    public static void addCoustomer(Customer customer) throws SQLException {
        Connection connection = DriverManager.getConnection(DBmanager.URL, DBmanager.SQL_USER, DBmanager.SQL_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `" + DBName + "`.`customers` " +
                "(first_name, last_name, email, password)" +
                "VALUES " +
                "(?, ?, ?, ?)");
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getPassword());
        preparedStatement.execute();
        connection.close();
    }
}
