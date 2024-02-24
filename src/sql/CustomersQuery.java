package sql;

public class CustomersQuery {
    public static final String isCustomerExistQuery(){
        return "SELECT * FROM `" + DBmanager.SQL_DB + "`.`customers` " +
                "WHERE email = ? AND password = ?";
    }
    public static final String addCustomerQuery(){
        return "INSERT INTO `" + DBmanager.SQL_DB + "`.`Customers` " +
                "(first_name, last_name, email, password) " +
                "VALUES (?, ?, ?, ?)";
    }
    public static final String updateCustomerQuery(){
        return "UPDATE `" + DBmanager.SQL_DB + "`.`customers` " +
                "SET first_name = ?, last_name = ?, email = ?, password = ?" +
                "WHERE id = ?";
    }
    public static final String deleteCustomerQuery(){
        return "DELETE FROM`" + DBmanager.SQL_DB + "`.`customers` " +
                "WHERE id = ?";
    }
    public static final String getAllCustomersQuery(){
        return "SELECT * FROM `" + DBmanager.SQL_DB + "`.`customers` ";
    }
    public static final String getOneCustomerQuery(){
        return "SELECT * FROM `" + DBmanager.SQL_DB + "`.`customers` " +
                "WHERE id = ?";
    }
}
