package lk.ijse.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXCheckBox chkPw;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtpassword;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        String pw = txtpassword.getText();

        String password = userBO.getPw(userName);

        System.out.println(password);

        if (password.equals(pw)){
            new Alert(Alert.AlertType.CONFIRMATION, "Login successfully").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION, "Failed").show();
        }
    }

    @FXML
    void createAccOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/signup.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();

        stage.setTitle("Signup Page");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

}
