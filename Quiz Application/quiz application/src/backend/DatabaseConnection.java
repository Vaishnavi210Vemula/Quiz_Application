package backend;
import java.sql.*; 
class DatabaseConnection { 
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quiz1"; 
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "sqlVaishu@210"; 
    public static Connection getConnection() throws SQLException { 
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); 
    } 
}