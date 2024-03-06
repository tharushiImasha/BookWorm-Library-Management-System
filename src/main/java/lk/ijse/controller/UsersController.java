package lk.ijse.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;

public class UsersController {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<?> tblUsers;

    @FXML
    private JFXRadioButton radAdmin;

    @FXML
    private JFXRadioButton radReader;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPw;

    @FXML
    private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String pw = txtPw.getText();
        String role = "";

        if (radAdmin.isSelected()){
            role = "Admin";
        } else if (radReader.isSelected()) {
            role = "Reader";
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
    void btnUpdateOnAction(ActionEvent event) {

    }

}
