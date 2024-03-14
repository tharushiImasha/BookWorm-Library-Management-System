package lk.ijse.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;

import java.io.IOException;
import java.util.regex.Pattern;

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

    private boolean validateUser() {
        String userName = txtUserName.getText();
        boolean isValid = Pattern.matches("[a-zA-Z]{3,}", userName);

        if (!isValid){

            if (txtUserName.getText().isEmpty()){
                flashBorder(txtUserName);
                return false;
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid UserName").show();
                return false;
            }
        }

        String name = txtfullName.getText();
        boolean isValidName = Pattern.matches("([a-zA-Z\\s]+)", name);

        if (!isValidName){

            if (txtfullName.getText().isEmpty()){
                flashBorder(txtfullName);
                return false;
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Invalid Name").show();
                return false;
            }
        }

        String email = txtEmail.getText();
        boolean isValidJob = Pattern.matches("(^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$)", email);

        if (!isValidJob){

            if (txtEmail.getText().isEmpty()){
                flashBorder(txtEmail);
                return false;
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
                return false;
            }
        }

        String pw = "";

        if (chkPw.isSelected()){
             pw = txtpassword.getText();
        }else {
             pw = txtHidePw.getText();
        }

        boolean isValidPw = Pattern.matches("(^[a-zA-Z]\\w{3,14}$)", pw);

        if (!isValidPw){

            if (txtpassword.getText().isEmpty()){
                flashBorder(txtpassword);
                return false;
            } else if (txtHidePw.getText().isEmpty()) {
                flashBorder(txtHidePw);
                return false;
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
                return false;
            }
        }

        return true;
    }

    private void flashBorder(TextField textField) {
        textField.setStyle("-fx-border-color: #000000;-fx-background-color: rgba(255,0,0,0.13)");
        setBorderResetAnimation(textField);
    }

    private void setBorderResetAnimation(TextField textField) {

        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(textField.styleProperty(), "-fx-background-color:rgba(255,0,0,0.13);-fx-border-color: rgba(128,128,128,0.38);-fx-background-radius:10;-fx-border-radius:10")),
                new KeyFrame(Duration.seconds(0.1), new KeyValue(textField.styleProperty(), "-fx-background-color: white;-fx-border-color: rgba(128,128,128,0.38);-fx-background-radius:10;-fx-border-radius:10")),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(textField.styleProperty(), "-fx-background-color:rgba(255,0,0,0.13);-fx-border-color: rgba(128,128,128,0.38);-fx-background-radius:10;-fx-border-radius:10")),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(textField.styleProperty(), "-fx-background-color: white;-fx-border-color: rgba(128,128,128,0.38);-fx-background-radius:10;-fx-border-radius:10")),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(textField.styleProperty(), "-fx-background-color:rgba(255,0,0,0.13);-fx-border-color: rgba(128,128,128,0.38);-fx-background-radius:10;-fx-border-radius:10")),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(textField.styleProperty(), "-fx-background-color: white;-fx-border-color: rgba(128,128,128,0.38);-fx-background-radius:10;-fx-border-radius:10"))
        );
        timeline1.play();
    }

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

            if (validateUser()) {

                boolean isSaved = userBO.saveUser(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "User not saved!").show();
                }
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
