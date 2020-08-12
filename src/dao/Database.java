
package dao;


import model.User;
import org.apache.http.client.fluent.Request;

import java.sql.*;

public class Database {

    Connection connection;

    public Database() throws SQLException {

        // 4.1.1: ket noi vao db
        //5.2.1 ket noi vao db
//        9.1.1 connect db
        this.connection = getConnectionSql();
    }

    //ket noi database
    public Connection getConnectionSql() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");
            String hostName = "localhost";
            String dbName = "parkinglot";
            String userName = "root";
            String password = "";
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
            return conn = DriverManager.getConnection(connectionURL, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return conn;
    }
//    public static void storeCarParkinglot(String state, String name) {
//
//    }
// kiểm tra với email thì có ứng với password  k
    public boolean checkUser(String email, String password) throws SQLException {
        ResultSet set = null;
        try {
            String sql = "SELECT * FROM user WHERE user.email=? AND user.password=? AND user.active=1";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, MD5Hashing.getMD5(password));
            set = st.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return set.next();
    }

    //  Kiem tra voi mot mail nguoi dung nhap vao thi user do co ton tai trong database chua
    public User getUser(String email) {
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
    public int addUser(String email, String name) {
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

    //7.2 Đoi active thanh 1 neu ng dung nhan vao link xac nhan trong email
    public int changeActive(String email, int active) {
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

    public int addUser(User user) {
        //5.4 Kiem tra ng dung co ton tai k, neu k thi se them vao neu khong se tra ve 0
        if (getUser(user.getEmail()) == null) {
            String sql = "Insert into user(email,fullname,password,address,phone,active) values(?,?,?,?,?,?)";
            try {
                PreparedStatement pre = connection.prepareStatement(sql);

                pre.setString(1, user.getEmail());
                pre.setString(2, user.getFullname());
                //5.4.1 Ma hoa mat khau
                pre.setString(3, MD5Hashing.getMD5(user.getPassword()));
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

    // kiem tra input email va token co trung trong database khong
    public boolean checkToken(String email, String token) {
        String sql = "select * from user where email=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                if (rs.getString("token").equals(MD5Hashing.getMD5(token))) return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

// cập nhật lại token cho 1 mail đầu vào
    public void updateToken(String email, String token) {
        PreparedStatement pre = null;
        String sqlUpdate;
        if (token == null) {
            sqlUpdate = "UPDATE user set user.token='' ";
            try {
                pre = connection.prepareStatement(sqlUpdate);
                pre.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            //
            sqlUpdate = "UPDATE user set user.token=? WHERE email=?";
            try {

                pre = connection.prepareStatement(sqlUpdate);

                pre.setString(1, MD5Hashing.getMD5(token));
                pre.setString(2, email);
                pre.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // cap nhat password trong database
    //11.2 changePass(email, pass): đổi mật khẩu cho email đó
    public void changePass(String email, String pass) {
        String sql = "update user set password=? where email=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, MD5Hashing.getMD5(pass));
            pre.setString(2, email);
            pre.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // xoa token
    public void deleteToken(String email) {
        // 11.4 cap nhat lai token null trong bang user
        updateToken(email, null);
    }

    // kiÄ‚Â¡Ă‚Â»Ă†â€™m tra thÄ‚Â¡Ă‚Â»Ă¯Â¿Â½i gian token Ä‚â€�Ă¢â‚¬ËœĂ„â€�Ă‚Â³ tÄ‚Â¡Ă‚Â»Ă¢â‚¬Å“n tÄ‚Â¡Ă‚ÂºĂ‚Â¡i trong vĂ„â€�Ă‚Â²ng 30 phĂ„â€�Ă‚Âºt mĂ„â€�Ă‚Â  chÄ‚â€ Ă‚Â°a click thĂ„â€�Ă‚Â¬ xĂ„â€�Ă‚Â³a luĂ„â€�Ă‚Â´n token
    public void deleteToken() {
        String sql = "select expirydate from user";
        Timestamp time_token;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                String time = result.getString("expirydate");
                time_token = Timestamp.valueOf(time);
                Timestamp window_time = new Timestamp(System.currentTimeMillis());
                long t1 = window_time.getTime();
                long t2 = time_token.getTime();
                System.out.println(t1 - t2 + " dfd " + t2);
                if (t1 - t2 >= 1764)// >=30 phĂ„â€�Ă‚Âºt
                {
                    // xĂ„â€�Ă‚Â³a token
                    String sqlUpdate = "UPDATE user set user.token=? ";
                    try {
                        pre = connection.prepareStatement(sqlUpdate);
                        pre.setString(1, "");
                        pre.executeUpdate();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException {
//        Database.createToken("17130047@st.hcmuaf.edu.vn", "ssss");
//        Database.createToken("giangil.com", "xxd");
        Database d= new Database();
        d.deleteToken();
//        System.out.println(Database.convertStringToTimestamp("2020-08-01 23:48:34.581"));
//        Database.deleteToken("17130047@st.hcmuaf.edu.vn");
//        Database.updateToken("17130047@st.hcmuaf.edu.vn", null);
    }


}
