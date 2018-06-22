package sample.database.sql.connection;
import java.sql.*;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import sample.data.User;

public class DataBaseConnection extends Config{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException , SQLException{
        String connection = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?autoReconnect=true&useSSL=false";
//        String connection = String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true&useSSL=false&user=%s&password=%s", dbHost, dbPort, dbName, dbUser, dbPass);

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connection, dbUser,dbPass);
//         dbConnection = DriverManager.getConnection(connection);

        System.out.println("Connection OK");
        return dbConnection;
    }
    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_FIRSTNAME  + "," + Const.USER_LASTNAME + "," + Const.USER_NAME +
               "," + Const.USER_PASSWORD + "," + Const.USER_DEPARTMET + ")" + "VALUE(?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getLastName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getDepartment());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resultSet= null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_NAME + "=?" + " AND " + Const.USER_PASSWORD
                + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());
           resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
