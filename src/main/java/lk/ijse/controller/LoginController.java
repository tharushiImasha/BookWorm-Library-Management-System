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

    @FXML
    private PasswordField txtHidePw;

    public static String name;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String pw = txtpassword.getText();
        String hidePw = txtHidePw.getText();

        String password = userBO.getPw(userName);

        name = userBO.getName(userName);

        String role = userBO.getRole(userName);

        if (password.equals(pw)||password.equals(hidePw)){

            if (role.equals("Admin")){
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/admin_dashboard_pane.fxml"));

                Scene scene = new Scene(rootNode);
                Stage stage = (Stage) this.rootNode.getScene().getWindow();

                stage.setTitle("Admin Dashboard");
                stage.setScene(scene);
                stage.centerOnScreen();
            }else {
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_dashboard.fxml"));

                Scene scene = new Scene(rootNode);
                Stage stage = (Stage) this.rootNode.getScene().getWindow();

                stage.setTitle("User Dashboard");
                stage.setScene(scene);
                stage.centerOnScreen();
            }

        }else {
            new Alert(Alert.AlertType.CONFIRMATION, "Username or Password invalid").show();
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

    String passwordNew = "";

    @FXML
    void showPwOnAction(ActionEvent event) {
        if (chkPw.isSelected()){
            passwordNew = txtHidePw.getText();
            txtpassword.setText(passwordNew);

            txtHidePw.setVisible(false);
            txtpassword.setVisible(true);
        } else {
            passwordNew = txtpassword.getText();
            txtHidePw.setText(passwordNew);

            txtpassword.setVisible(false);
            txtHidePw.setVisible(true);
        }
    }

    @FXML
    void pwOnAction(ActionEvent event) throws IOException {
        btnLoginOnAction(event);
    }

}
