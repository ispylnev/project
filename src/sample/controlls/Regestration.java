package sample.controlls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.data.User;
import sample.database.sql.connection.DataBaseConnection;

public class Regestration  {

    @FXML
    private TextField regFirstName;

    @FXML
    private PasswordField regPasswordField;

    @FXML
    private Button signUp;

    @FXML
    private TextField regLoginField;

    @FXML
    private TextField regLastName;

    @FXML
    private TextField regDepartment;

    @FXML
    void dbRegestration(){
        signUp.setOnAction(event -> {
            signUpUser();
            Controller controller = new Controller();
            controller.showNewScene("/sample/view/sample.fxml",signUp);
        });
    }

    private void signUpUser() {
         String firstName = regFirstName.getText();
         String lastName = regLastName.getText();
         String userName = regLoginField.getText();
         String password = regPasswordField.getText();
         String department = regDepartment.getText();
         User user = new User(firstName,lastName,userName,password,department);
         DataBaseConnection dataBaseConnection = new DataBaseConnection();
         dataBaseConnection.signUpUser(user);
    }
}
