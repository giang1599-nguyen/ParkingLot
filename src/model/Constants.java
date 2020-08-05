package model;

public class Constants {
    public static String GOOGLE_CLIENT_ID = "1097903455009-nsp0udljlpi2773rpgn3cdhf4rs65qf4.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "lP6kw9LZ5gETW3oScoib_tsq";
//link để sau khi chọn tài khoản google thì nó sẽ gửi tham số qua
    public static String GOOGLE_REDIRECT_URI = "https://parkinglotnlu.azurewebsites.net/ParkingLot/LoginGGController";
// đường dẫn để lấy token
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
//đường dẫn để lấy thông tin người dùng dựa trên access_token
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
