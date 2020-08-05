package dao;

import java.sql.*;

public class GetConnectDatabase {
    //Thong tin account truy cap db.
    private String username;
    private String password;
    public static Connection conn = null;

    public GetConnectDatabase() {
    }

    public static Connection getConnectionSql(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");

            if (conn == null) {
                String hostName = "localhost";
                String dbName = "parkinglot";
                String userName = "root";
                String password = "";
                String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
                return conn = DriverManager.getConnection(connectionURL, userName, password);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
		}

        return conn;
    }

    public static void main(String[] args) throws SQLException {
        GetConnectDatabase c = new GetConnectDatabase();
        Connection connection = c.getConnectionSql();
        System.out.println(connection);
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM user WHERE email=" + "'" + "giang1599.ng@gmail.com" + "'";
        ResultSet set = statement.executeQuery(sql);
        System.out.println(set.next());
    }
}
