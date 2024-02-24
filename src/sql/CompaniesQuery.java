package sql;

public class CompaniesQuery {
    public static final String isCompanyExistQuery(){
        return "SELECT * FROM `" + DBmanager.SQL_DB + "`.`Companies` " +
                "WHERE email = ? AND password = ?";
    }
    public static final String addCompanyQuery(){
        return "INSERT INTO `" + DBmanager.SQL_DB + "`.`Companies` " +
                "( name, email, password) " +
                "VALUES " +
                "(?, ?, ?) ";
    }
    public static final String updateCompanyQuery(){
        return "UPDATE `" + DBmanager.SQL_DB + "`.`Companies` " +
                "SET name = ?, email = ?, password = ? " +
                "WHERE id = ?";
    }
    public static final String deleteCompanyQuery(){
        return /*"DELETE FROM `" + DBmanager.SQL_DB + "`.`customers_vs_coupons` " +
                "WHERE company_id = ?;" +*/
                "DELETE FROM `" + DBmanager.SQL_DB + "`.`Companies` " +
                "WHERE id = ?";
    }
    public static final String getAllCompaniesQuery(){
        return "SELECT * FROM `" + DBmanager.SQL_DB + "`.`Companies` " ;
    }
    public static final String getOneCompanyQuery(){
        return "SELECT * FROM `" + DBmanager.SQL_DB + "`.`Companies` " +
                "WHERE id = ?";
    }
}
