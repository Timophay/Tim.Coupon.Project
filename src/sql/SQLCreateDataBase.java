package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class SQLCreateDataBase {
    private static final String DBName = DBmanager.SQL_DB;
    public static void createDataBase() throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = DriverManager.getConnection(
                DBmanager.URL,
                DBmanager.SQL_USER,
                DBmanager.SQL_PASSWORD
        );
        preparedStatement = connection.prepareStatement(createNewDatabaseQuery());
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(createCouponTableQuery());
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(createCUSTOMERS_VS_COUPONSTableQuery());
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(createCompaniesTableQuery());
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(createCustomersTableQuery());
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement(createCATEGORIESTableQuery());
        preparedStatement.execute();
        connection.close();

    }
    private static final String createNewDatabaseQuery(){
        return "CREATE SCHEMA IF NOT EXISTS `" + SQLCreateDataBase.DBName + "`";
    }
    private static final String createCouponTableQuery(){
        return "CREATE TABLE IF NOT EXISTS `" + SQLCreateDataBase.DBName + "`.`coupons` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `company_id` INT NOT NULL," +
                "  `category_id` INT NOT NULL," +
                "  `title` VARCHAR(45) NOT NULL," +
                "  `description` VARCHAR(45) NOT NULL," +
                "  `start_date` DATE NOT NULL," +
                "  `end_date` DATE NOT NULL," +
                "  `amount` INT NOT NULL," +
                "  `price` DOUBLE NOT NULL," +
                "  `image` VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY (`id`));";
    }
    private static final String createCUSTOMERS_VS_COUPONSTableQuery(){
        return "CREATE TABLE IF NOT EXISTS `" + SQLCreateDataBase.DBName + "`.`customers_vs_coupons` (" +
                "  `customer_id` INT NOT NULL," +
                "  `coupon_id` INT NOT NULL," +
                "  PRIMARY KEY (`customer_id`, `coupon_id`));";
    }
    private static final String createCompaniesTableQuery(){
        return "CREATE TABLE IF NOT EXISTS `" + SQLCreateDataBase.DBName + "`.`companies` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NOT NULL," +
                "  `email` VARCHAR(45) NOT NULL," +
                "  `password` VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY (`ID`)," +
                "  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE," +
                "  UNIQUE INDEX `EMAIL_UNIQUE` (`email` ASC) VISIBLE);";
    }
    private static final String createCustomersTableQuery(){
        return "CREATE TABLE IF NOT EXISTS `" + SQLCreateDataBase.DBName + "`.`customers` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `first_name` VARCHAR(45) NOT NULL," +
                "  `last_name` VARCHAR(45) NOT NULL," +
                "  `email` VARCHAR(45) NOT NULL," +
                "  `password` VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY (`id`));";
    }
    private static final String createCATEGORIESTableQuery(){
        return "CREATE TABLE IF NOT EXISTS `" + SQLCreateDataBase.DBName + "`.`categories` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY (`id`)," +
                "  UNIQUE INDEX `NAME_UNIQUE` (`name` ASC) VISIBLE);";
    }
}
