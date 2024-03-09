package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.tm.BranchTm;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BranchController {

    @FXML
    private ComboBox<String> cmbUserName;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<BranchTm> tblBranches;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLocation;

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BRANCH);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        setListener();
        loadUserName();
    }

    private void loadUserName() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<UserDto> list = userBO.getUsersAdmin();

            for (UserDto dto : list) {
                obList.add(dto.getUserName());
            }

            cmbUserName.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadAllCustomers() {

        try {
            List<BranchDto> dtoList = branchBO.getAllBranch();

            ObservableList<BranchTm> obList = FXCollections.observableArrayList();

            for(BranchDto dto : dtoList){
                Button btn = new Button("Remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no) == yes){
                        int index = tblBranches.getSelectionModel().getFocusedIndex();
                        String id = (String) colId.getCellData(index);

                        deleteBranches(id);

                        obList.remove(index);
                        tblBranches.refresh();
                    }

                });

                var tm = new BranchTm(dto.getId(), dto.getLocation(), dto.getUserName(), btn);

                obList.add(tm);

            }

            tblBranches.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteBranches(String id) {
        String branchId = txtId.getText();
        String location = txtLocation.getText();
        String userName = cmbUserName.getValue();

        var dto = new BranchDto(branchId, location, userName);

        try {
            boolean isDelete = branchBO.deleteBranch(dto);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Branch Deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Branch not deleted!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setListener() {
        tblBranches.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    var dto = new BranchDto(
                            newValue.getId(),
                            newValue.getLocation(),
                            newValue.getUserName()
                    );
                    setFields(dto);
                });
    }

    private void setFields(BranchDto dto) {
        txtLocation.setText(dto.getLocation());
        txtId.setText(dto.getId());
        cmbUserName.setValue(dto.getUserName());
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtLocation.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String branchId = txtId.getText();
        String location = txtLocation.getText();
        String userName = cmbUserName.getValue();

        var dto = new BranchDto(branchId, location, userName);

        try {

            boolean isSaved = branchBO.saveBranch(dto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Branch saved!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Branch not saved!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String branchId = txtId.getText();
        String location = txtLocation.getText();
        String userName = cmbUserName.getValue();

        var dto = new BranchDto(branchId, location, userName);

        try {

            boolean isSaved = branchBO.updateBranch(dto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Branch updated!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Branch not updated!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
