package lk.ijse.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;

import java.io.IOException;

public class SignupController {
    @FXML
    private JFXCheckBox chkPw;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtHidePw;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtfullName;

    @FXML
    private TextField txtpassword;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);

    @FXML
    void btnCreateAccOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String name = txtfullName.getText();
        String userName = txtUserName.getText();
        String pw = "";
        String role = "Reader";

        if (chkPw.isSelected()){
            pw = txtpassword.getText();
        }else {
            pw = txtHidePw.getText();
        }


        var dto = new UserDto(userName, email, name, pw, role);

        try {

            boolean isSaved = userBO.saveUser(dto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User not saved!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void loginOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();

        stage.setTitle("Signup Page");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    String password = "";

    @FXML
    void showPwOnAction(ActionEvent event) {
        if (chkPw.isSelected()){
            password = txtHidePw.getText();
            txtpassword.setText(password);

            txtHidePw.setVisible(false);
            txtpassword.setVisible(true);
        } else {
            password = txtpassword.getText();
            txtHidePw.setText(password);

            txtpassword.setVisible(false);
            txtHidePw.setVisible(true);
        }
    }
}
