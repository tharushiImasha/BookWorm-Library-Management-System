package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.Branch;

import static lk.ijse.bo.custom.impl.UserBOImpl.userBO;

public class BooksController {

    @FXML
    private ComboBox<?> cmbBookType;

    @FXML
    private ComboBox<String> cmbBranch;

    @FXML
    private ComboBox<?> cmbGenre;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private ImageView imgBook;

    @FXML
    private TableView<?> tblAllBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtCount;

    @FXML
    private TextField txtPhoto;

    @FXML
    private TextField txtTitle;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtAuthor.clear();
        txtCount.clear();
        txtTitle.clear();
        txtDesc.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String author = txtAuthor.getText();
        String title = txtTitle.getText();
        String desc = txtDesc.getText();
        int count = Integer.parseInt(txtCount.getText());
        String branch = cmbBranch.getValue();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
