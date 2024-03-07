package lk.ijse.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.tm.UserTm;

import java.util.List;
import java.util.Optional;

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
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User not Updated!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
