package dao;


import model.User;

import java.sql.*;

public class Database {
	
	static Connection connection = GetConnectDatabase.getConnectionSql();
    public Database()  {
        // moi lan chay database la kem tra token da het han chua
        deleteToken();
    }

    public static void storeCarParkinglot(String state, String name) {

    }

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

//    //add user thanh cong tra ve 1, khong thanh cong tra ve 0
//    public static int addUser(String email, String name) {
//        String sql = "Insert into user(fullname,email,active) values(?,?,?)";
//        try {
//            PreparedStatement pre = connection.prepareStatement(sql);
//
//            pre.setString(1, name);
//            pre.setString(2, email);
//            pre.setInt(3, 1);
//            return pre.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
    
    //7.2Đổi active thành 1 nếu người dùng click vào link xác nhận
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
    	//Kiểm tra xem người dùng với email đã nhập đã tồn tại chưa
        if (getUser(user.getEmail()) == null) {
            String sql = "Insert into user(email,fullname,password,address,phone,active) values(?,?,?,?,?,?)";
            try {
                PreparedStatement pre = connection.prepareStatement(sql);

                pre.setString(1, user.getEmail());
                pre.setString(2, user.getFullname());
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
        String sql = "select * from resetpass where email=?";
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
// to token voi email nguoi dung nhap
    public void createToken(String email, String token) {
    // kiem tra mail nguoi dung nhap da ton tai trong database
        if (checkEmail(email)) {
        //6.1a: chi cap nhat lai token
            updateToken(email, token);
        } else {
        //6.1b: chen email va token xuong database
            insertToken(email, token);
        }
    }

    private boolean checkEmail(String email) {

        String sqlCheck = "select resetpass.email from resetpass ";
        boolean flag = false;
        try {
            // check email trong reset Ă„â€˜Ä‚Â£ tĂ¡Â»â€œn tĂ¡ÂºÂ¡i chĂ†Â°a
            PreparedStatement check = connection.prepareStatement(sqlCheck);
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

    private void insertToken(String email, String token) {
        PreparedStatement pre = null;
        String sqlInsert = "insert into  resetpass (email,token) values(?,?)";
        try {
            System.out.println(" chĂ†Â°a cÄ‚Â³ mail");
            // nĂ¡ÂºÂ¿u mail chĂ†Â°a cÄ‚Â³ trong bĂ¡ÂºÂ£ng reset thÄ‚Â¬ thÄ‚Âªm vÄ‚Â o
            pre = connection.prepareStatement(sqlInsert);
            pre.setString(1, email);
            pre.setString(2, MD5Hashing.getMD5(token));
            pre.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateToken(String email, String token) {
        PreparedStatement pre = null;
        String sqlUpdate;
        if (token == null) {
            sqlUpdate = "UPDATE resetpass set resetpass.token='' ";
            try {
                pre = connection.prepareStatement(sqlUpdate);
                pre.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            //
            sqlUpdate = "UPDATE resetpass set resetpass.token=? WHERE email=?";
            try {
                // Ă„â€˜Ä‚Â£ cÄ‚Â³ mail thÄ‚Â¬ update token
                System.out.println(" Ă„ï¿½Ä‚Â£ cÄ‚Â³ mail");
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
    public void changePass(String email, String pass) {
        String sql = "update user set password=? where email=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1,MD5Hashing.getMD5(pass) );
            pre.setString(2, email);
            pre.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
// xoa token
    public void deleteToken(String email) {
        // 11.4 cap nhat lai token null trong bang resetpass
        updateToken(email, null);
    }

    // kiĂ¡Â»Æ’m tra thĂ¡Â»ï¿½i gian token Ă„â€˜Ä‚Â³ tĂ¡Â»â€œn tĂ¡ÂºÂ¡i trong vÄ‚Â²ng 30 phÄ‚Âºt mÄ‚Â  chĂ†Â°a click thÄ‚Â¬ xÄ‚Â³a luÄ‚Â´n token
    public void deleteToken() {
        String sql = "select expirydate from resetpass";
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
                if (t1 - t2 >= 1764)// >=30 phÄ‚Âºt
                {
                    // xÄ‚Â³a token
                    String sqlUpdate = "UPDATE resetpass set resetpass.token=? ";
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


    public static void main(String[] args) {
//        Database.createToken("17130047@st.hcmuaf.edu.vn", "ssss");
//        Database.createToken("giangil.com", "xxd");
//        Database.deleteToken();
//        System.out.println(Database.convertStringToTimestamp("2020-08-01 23:48:34.581"));
//        Database.deleteToken("17130047@st.hcmuaf.edu.vn");
//        Database.updateToken("17130047@st.hcmuaf.edu.vn", null);
    }


}
