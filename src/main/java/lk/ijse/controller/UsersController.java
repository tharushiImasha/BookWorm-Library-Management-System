package lk.ijse.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.tm.UserTm;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UsersController {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<UserTm> tblUsers;

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

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        setListener();
    }

    private void setCellValueFactory() {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadAllCustomers() {

        try {
            List<UserDto> dtoList = userBO.getAllUser();

            ObservableList<UserTm> obList = FXCollections.observableArrayList();

            for(UserDto dto : dtoList){
                Button btn = new Button("Remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no) == yes){
                        int index = tblUsers.getSelectionModel().getFocusedIndex();
                        String id = (String) colUserName.getCellData(index);

                        deleteUsers(id);

                        obList.remove(index);
                        tblUsers.refresh();
                    }

                });

                var tm = new UserTm(dto.getUserName(), dto.getEmail(), dto.getFullName(), dto.getPassword(),dto.getUserRole(), btn);

                obList.add(tm);

            }

            tblUsers.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setListener() {
        tblUsers.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    var dto = new UserDto(
                            newValue.getUserName(),
                            newValue.getEmail(),
                            newValue.getFullName(),
                            newValue.getPassword(),
                            newValue.getUserRole()
                    );
                    setFields(dto);
                });
    }

    private void setFields(UserDto dto) {
        txtEmail.setText(dto.getEmail());
        txtName.setText(dto.getFullName());
        txtPw.setText(dto.getPassword());
        txtUserName.setText(dto.getUserName());
        if (dto.getUserRole().equals("Admin")){
            radAdmin.setSelected(true);
        } else if (dto.getUserRole().equals("Reader")) {
            radReader.setSelected(true);
        }
    }

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

        String name = txtName.getText();
        boolean isValidName = Pattern.matches("([a-zA-Z\\s]+)", name);

        if (!isValidName){

            if (txtName.getText().isEmpty()){
                flashBorder(txtName);
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

        String pw = txtPw.getText();

        boolean isValidPw = Pattern.matches("(^[a-zA-Z]\\w{3,14}$)", pw);

        if (!isValidPw){

            if (txtPw.getText().isEmpty()){
                flashBorder(txtPw);
                return false;
            } else if (txtPw.getText().isEmpty()) {
                flashBorder(txtPw);
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

    private void deleteUsers(String id) {
        String email = txtEmail.getText();
        String name = txtName.getText();
        String pw = txtPw.getText();
        String role = "";

        if (radAdmin.isSelected()){
            role = "Admin";
        } else if (radReader.isSelected()) {
            role = "Reader";
        }

        var dto = new UserDto(id, email, name, pw, role);

        try {

            boolean isDelete = userBO.deleteUser(dto);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User not deleted!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    private void clear() {
        txtUserName.clear();
        txtPw.clear();
        txtName.clear();
        txtEmail.clear();
        radReader.setSelected(false);
        radAdmin.setSelected(false);
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
            radReader.setSelected(false);
        } else if (radReader.isSelected()) {
            role = "Reader";
            radAdmin.setSelected(false);
        }

        var dto = new UserDto(userName, email, name, pw, role);

        try {

            if (validateUser()) {

                boolean isSaved = userBO.saveUser(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
                    initialize();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "User not saved!").show();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
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

            boolean isUpdate = userBO.updateUser(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated!").show();
                initialize();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User not Updated!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
