package dao;


import model.User;

import java.sql.*;

public class Database {

    GetConnectDatabase g;

    public Database() {
        // mỗi lần chạy dữ liệu đều ktra token
        Database.deleteToken();
    }

    public static void storeCarParkinglot(String state, String name) {

    }

    public static boolean checkUser(String email, String password) throws SQLException {
        ResultSet set = null;
        try {
            Connection connection = GetConnectDatabase.getConnectionSql();
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
            Connection connection = GetConnectDatabase.getConnectionSql();
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
        Connection conn = null;
        try {
            conn = GetConnectDatabase.getConnectionSql();
            PreparedStatement pre = conn.prepareStatement(sql);

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
        Connection conn = null;
        try {
            conn = GetConnectDatabase.getConnectionSql();
            PreparedStatement pre = conn.prepareStatement(sql);
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
            Connection conn = null;
            try {
                conn = GetConnectDatabase.getConnectionSql();
                PreparedStatement pre = conn.prepareStatement(sql);

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

//    public static void main(String[] args) {
//        if (Database.getUser("thnhlngtrung@gmail.com") != null)
////			Database.changeActive(email,1);
//
//            System.out.println(Database.changeActive("thnhlngtrung@gmail.com", 1));
//    }

    public static boolean checkToken(String email, String token) {
        String sql = "select * from resetpass where email=?";
        Connection conn;
        GetConnectDatabase get = new GetConnectDatabase();
        try {
            conn = get.getConnectionSql();
            PreparedStatement pre = conn.prepareStatement(sql);
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

    public static void createToken(String email, String token) {
        if (checkEmail(email)) {
            updateToken(email, token);
        } else {
            insertToken(email, token);
        }
    }

    private static boolean checkEmail(String email) {

        Connection conn;
        GetConnectDatabase get = new GetConnectDatabase();
        PreparedStatement pre = null;
        String sqlCheck = "select resetpass.email from resetpass ";
        boolean flag = false;
        try {
            conn = get.getConnectionSql();
            // check email trong reset đã tồn tại chưa
            PreparedStatement check = conn.prepareStatement(sqlCheck);
            ResultSet result = check.executeQuery();
            while (result.next()) {
                if (result.getString("email").equals(email))
                    flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    private static void insertToken(String email, String token) {
        Connection conn;
        GetConnectDatabase get = new GetConnectDatabase();
        PreparedStatement pre = null;
        String sqlInsert = "insert into  resetpass (email,token) values(?,?)";
        try {
            conn = get.getConnectionSql();
            System.out.println(" chưa có mail");
            // nếu mail chưa có trong bảng reset thì thêm vào
            pre = conn.prepareStatement(sqlInsert);
            pre.setString(1, email);
            pre.setString(2, MD5Hashing.getMD5(token));
            pre.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void updateToken(String email, String token) {
        Connection conn;
        GetConnectDatabase get = new GetConnectDatabase();
        PreparedStatement pre = null;
        String sqlUpdate;
        if (token == null) {
            sqlUpdate = "UPDATE resetpass set resetpass.token='' ";
            try {
                conn = get.getConnectionSql();
                pre = conn.prepareStatement(sqlUpdate);
                pre.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            //
            sqlUpdate = "UPDATE resetpass set resetpass.token=? WHERE email=?";
            try {
                conn = get.getConnectionSql();
                // đã có mail thì update token
                System.out.println(" Đã có mail");
                pre = conn.prepareStatement(sqlUpdate);

                pre.setString(1, MD5Hashing.getMD5(token));
                pre.setString(2, email);
                pre.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void changePass(String email, String pass) {
        String sql = "update user set password=? where email=?";
        Connection conn;
        GetConnectDatabase get = new GetConnectDatabase();
        try {
            conn = get.getConnectionSql();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pass);
            pre.setString(2, email);
            pre.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteToken(String email) {
        // thay đổi mật khẩu thành công thì xóa luôn token đã gửi về mail

        updateToken(email, null);
    }

    // kiểm tra thời gian token đó tồn tại trong vòng 30 phút mà chưa click thì xóa luôn token
    public static void deleteToken() {
        String sql = "select expirydate from resetpass";
        Connection conn;
        GetConnectDatabase get = new GetConnectDatabase();
        Timestamp time_token;
        try {
            conn = get.getConnectionSql();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                String time = result.getString("expirydate");
                time_token = Timestamp.valueOf(time);
                Timestamp window_time = new Timestamp(System.currentTimeMillis());
                long t1 = window_time.getTime();
                long t2 = time_token.getTime();
                System.out.println(t1 - t2 + " dfd " + t2);
                if (t1 - t2 >= 1764)// >=30 phút
                {
                    // xóa token
                    String sqlUpdate = "UPDATE resetpass set resetpass.token=? ";
                    try {
                        conn = get.getConnectionSql();
                        pre = conn.prepareStatement(sqlUpdate);
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


    public static void main(String[] args) {
//        Database.createToken("17130047@st.hcmuaf.edu.vn", "ssss");
//        Database.createToken("giangil.com", "xxd");
//        Database.deleteToken();
//        System.out.println(Database.convertStringToTimestamp("2020-08-01 23:48:34.581"));
//        Database.deleteToken("17130047@st.hcmuaf.edu.vn");
//        Database.updateToken("17130047@st.hcmuaf.edu.vn", null);
    }


}
