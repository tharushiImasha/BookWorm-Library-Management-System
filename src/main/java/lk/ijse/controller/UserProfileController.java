package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.BorrowedBookBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.tm.BorrowedDetailsTm;
import lk.ijse.entity.User;

import java.io.IOException;
import java.util.List;

public class UserProfileController {

    @FXML
    private TableView<BorrowedDetailsTm> tblBorrowedDetails;

    @FXML
    private TableColumn<?, ?> colBorrowedDate;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colReturn;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtEmail;

    @FXML
    private AnchorPane rootNode;

    String userName;

    String returned;

    String bookId;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);
    BorrowedBookBO borrowedBookBO = (BorrowedBookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BORROWEDDETAILS);

    public void initialize() {
        userName = userBO.getUserName(LoginController.name);

        loadAllBooks();
        setCellValueFactory();
        getUserDetails();
    }

    private void getUserDetails() {
        User user = userBO.searchUser(userName);

        txtName.setText(user.getFullName());
        txtPassword.setText(user.getPassword());
        txtEmail.setText(user.getEmail());
        txtUserName.setText(user.getUserName());
    }

    private void setCellValueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colReturn.setCellValueFactory(new PropertyValueFactory<>("returned"));
    }

    private void loadAllBooks() {

        try {
            List<BorrowedDetailsDto> dtoList = borrowedBookBO.getAllBorrowedFromUser(userName);

            ObservableList<BorrowedDetailsTm> obList = FXCollections.observableArrayList();

            for(BorrowedDetailsDto dto : dtoList){

                bookId = dto.getBorrowedDetailPK().getId();

                boolean isNull = borrowedBookBO.checkReturnDate(bookId);
                returned = isNull ? "Not" : "Returned";

                String title = bookBO.getTitle(dto.getBorrowedDetailPK().getId());

                var tm = new BorrowedDetailsTm(dto.getBorrowedDetailPK(), dto.getBorrowedDate(), dto.getDueDate(), title, returned);

                obList.add(tm);

            }

            tblBorrowedDetails.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String pw = txtPassword.getText();

        String role = "Reader";

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
