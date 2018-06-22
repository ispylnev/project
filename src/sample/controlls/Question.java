package sample.controlls;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Question {

    @FXML
    private TextField LoginField;

    @FXML
    private Button EntrButton;

    @FXML
    void initializer() {
        EntrButton.setOnAction(event -> {
        Controller controller = new Controller();
        controller.showNewScene("/sample/view/sample.fxml",EntrButton);
    });
    }
    }


