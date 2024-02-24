package sql;

import java.sql.*;
import java.util.Map;
import java.util.Queue;

public class DButils {
    /*public static boolean runQueryWithoutParamsAndWithoutResults(String SqlQuery){
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery);
            preparedStatement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }*/
    public static ResultSet runQueryWithoutParamsAndWithResults(String SqlQuery, String calledBy){
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery);
            return preparedStatement.executeQuery();

        } catch (InterruptedException | SQLException e) {
            System.out.println("ERROR: " + calledBy + " " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
    public static boolean runQueryWithParamsAndWithoutResults(String SqlQuery, Map<Integer, Object> params, String calledBy){
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery);
            params.forEach((key, value) -> {
                try {
                    if (value instanceof Integer){
                        preparedStatement.setInt(key,(Integer)value);
                    } else if (value instanceof String){
                        preparedStatement.setString(key,String.valueOf(value));
                    } else if (value instanceof Date){
                        preparedStatement.setDate(key,(Date)value);
                    } else if (value instanceof Double){
                        preparedStatement.setDouble(key, (Double)value);
                    } else if (value instanceof Boolean){
                        preparedStatement.setBoolean(key, (Boolean)value);
                    } else if (value instanceof Float){
                        preparedStatement.setFloat(key, (Float)value);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + calledBy + " " + e.getMessage());
                    throw new RuntimeException(e);
                }
            });
            preparedStatement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            System.out.println("ERROR: " + calledBy + " " + e.getMessage());
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
    public static ResultSet runQueryWithParamsAndWithResults(String SqlQuery, Map<Integer, Object> params, String calledBy){
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery);
            params.forEach((key, value) -> {
                try {
                    if (value instanceof Integer){
                        preparedStatement.setInt(key,(Integer)value);
                    } else if (value instanceof String){
                        preparedStatement.setString(key,String.valueOf(value));
                    } else if (value instanceof Date){
                        preparedStatement.setDate(key,(Date)value);
                    } else if (value instanceof Double){
                        preparedStatement.setDouble(key, (Double)value);
                    } else if (value instanceof Boolean){
                        preparedStatement.setBoolean(key, (Boolean)value);
                    } else if (value instanceof Float){
                        preparedStatement.setFloat(key, (Float)value);
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR: " + calledBy + " " + e.getMessage());
                    throw new RuntimeException(e);
                }
            });
            return preparedStatement.executeQuery();
        } catch (InterruptedException | SQLException e) {
            System.out.println("ERROR: " + calledBy + " " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
}
