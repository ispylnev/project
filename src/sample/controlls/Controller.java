package sample.controlls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.animation.Shake;
import sample.data.User;
import sample.database.sql.connection.DataBaseConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private AnchorPane Status;

    @FXML
    private TextField LoginField;

    @FXML
    private Button EntrButton;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button RegButton;

    @FXML
    void initializer(){
        EntrButton.setOnAction((event -> {
            String loginText = LoginField.getText().trim();
            String passwordText = PasswordField.getText().trim();
            if(!loginText.equals("")&& !passwordText.equals("")){
                try {
                    loginUser(loginText, passwordText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                Label label = new Label();
                label.setText("Error-login and password empty ");
                Status.setAccessibleText("Error-login and password empty ");
                Status.getChildren().addAll(label);
            }
        }));
    }
    @FXML
    void regestration(){
        RegButton.setOnAction(event -> {
            //прячем текущее окно
            showNewScene("/sample/view/Regestration.fxml",EntrButton);
        });
    }
//
    private void loginUser(String loginText, String passwordField) throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(passwordField);
        ResultSet resultSet = dataBaseConnection.getUser(user);
        int check = 0;
        while (resultSet.next()){
            check++;
        }
        if (check >= 1) {
            showNewScene("/sample/view/test.fxml",RegButton);
            System.out.println("Success");
        }else {
            Shake userLoginShake = new Shake(LoginField);
            Shake userPasShake = new Shake(PasswordField);
            userLoginShake.playAnim();
            userPasShake.playAnim();

        }
    }
    public void showNewScene(String pathWindow,Button button) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pathWindow));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }
}

