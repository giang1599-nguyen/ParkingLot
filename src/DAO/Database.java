package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class Database {
	static final Connection connection = GetConnectDatabase.getConnectionSql();
	

	public Database() {
		
	}

	public static void storeCarParkinglot(String state, String name) {
        // Nothing yet

    }

    public static boolean checkUser(String email, String password) throws SQLException {
        ResultSet set = null;
        try {
            String sql = "SELECT * FROM user WHERE user.email=? AND user.password=? AND user.active=1";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            set = st.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return set.next();
    }

    public static User getUser(String email) {
        ResultSet set = null;
        try {
            String sql = "SELECT * FROM user WHERE user.email=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            set = st.executeQuery();
            if (set.next()) {
                return new User(set.getString("fullname"), set.getString("email"), set.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //add user thanh cong tra ve 1, khong thanh cong tra ve 0
    public static int addUser(String email, String name) {
        String sql = "Insert into user(fullname,email,active) values(?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            pre.setString(1, name);
            pre.setString(2, email);
            pre.setInt(3, 1);
            return pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int changeActive(String email, int active) {
        String sql = "Update user set active=? where email=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, active);
            pre.setString(2, email);
            return pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static int addUser(User user) {
        if (getUser(user.getEmail()) == null) {
            String sql = "Insert into user(email,fullname,password,address,phone,active) values(?,?,?,?,?,?)";
            try {
                PreparedStatement pre = connection.prepareStatement(sql);

                pre.setString(1, user.getEmail());
                pre.setString(2, user.getFullname());
                pre.setString(3, user.getPassword());
                pre.setString(4, user.getAddress());
                pre.setString(5, user.getPhone());
                pre.setInt(6, 0);
                return pre.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }
public static void createToken(String email, String token) {
        String sql = "insert into  resetpass(email,token) values(?,?)";
        Connection conn;
        GetConnectDatabase get = new GetConnectDatabase();
        try {
            conn = get.getConnectionSql();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, MD5Hashing.getMD5(token));


            pre.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
