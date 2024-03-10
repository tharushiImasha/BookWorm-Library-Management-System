package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.tm.BookTm;
import lk.ijse.entity.Book;

import java.util.List;
import java.util.Optional;

public class BooksController {

    @FXML
    private ComboBox<?> cmbBookType;

    @FXML
    private ComboBox<String> cmbBranch;

    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private ImageView imgBook;

    @FXML
    private TableView<BookTm> tblAllBooks;

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

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);

    public void initialize() {
        setCellValueFactory();
        loadAllBooks();
        setListener();
        loadGenre();
    }

    private void loadGenre() {
        cmbGenre.getItems().add("Horror");
        cmbGenre.getItems().add("Fantasy");
        cmbGenre.getItems().add("Children's");
        cmbGenre.getItems().add("Fiction");
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadAllBooks() {
        try {
            List<BookDto> dtoList = bookBO.getAllBook();

            ObservableList<BookTm> obList = FXCollections.observableArrayList();

            for(BookDto dto : dtoList){
                Button btn = new Button("Remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no) == yes){
                        int index = tblAllBooks.getSelectionModel().getFocusedIndex();
                        String id = (String) colBookId.getCellData(index);

                        deleteBooks(id);

                        obList.remove(index);
                        tblAllBooks.refresh();
                    }

                });

                var tm = new BookTm(dto.getId(), dto.getTitle(), dto.getAuthor(),dto.getDesc(), dto.getGenre(), btn);

                obList.add(tm);

            }

            tblAllBooks.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setListener() {
        tblAllBooks.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    var dto = new BookDto(
                            newValue.getId(),
                            newValue.getTitle(),
                            newValue.getAuthor(),
                            newValue.getDesc(),
                            newValue.getGenre()
                    );
                    setFields(dto);
                });
    }

    private void setFields(BookDto dto) {
        txtDesc.setText(dto.getDesc());
        txtTitle.setText(dto.getTitle());
        txtAuthor.setText(dto.getAuthor());
        cmbGenre.setValue(dto.getGenre());
    }

    private void deleteBooks(String id) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String desc = txtDesc.getText();
        String genre = cmbGenre.getValue();

        BookDto bookDto = new BookDto();

        var dto = new BookDto(bookDto.getId(), title, author, desc, genre);

        try {

            boolean isDelete = bookBO.deleteBook(dto);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Book Deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Book not deleted!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
        String genre = cmbBranch.getValue();

        BookDto bookDto = new BookDto();

        var dto = new BookDto(bookDto.getId(), title, author, desc, genre);

        try {

            boolean isSaved = bookBO.saveBook(dto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Book saved!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Book not saved!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String author = txtAuthor.getText();
        String title = txtTitle.getText();
        String desc = txtDesc.getText();
        String genre = cmbBranch.getValue();

        Book book = new Book();

        var dto = new BookDto(book.getId(), title, author, desc, genre);

        try {

            boolean isSaved = bookBO.updateBook(dto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Book updated!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Book not updated!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
