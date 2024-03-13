package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.UserDto;
import lk.ijse.dto.tm.BookTm;
import lk.ijse.entity.Book;

import java.io.*;
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
    private TableColumn<?, ?> colStatus;

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
    private TextField txtId;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtCount;

    @FXML
    private TextField txtPhoto;

    @FXML
    private TextField txtTitle;

    private byte [] imageBytes;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BRANCH);

    public void initialize() {
        setCellValueFactory();
        loadAllBooks();
        setListener();
        loadGenre();
        loadBranch();
    }

    private void loadGenre() {
        cmbGenre.getItems().add("Horror");
        cmbGenre.getItems().add("Fantasy");
        cmbGenre.getItems().add("Children's");
        cmbGenre.getItems().add("Fiction");
    }

    private void loadBranch() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<BranchDto> list = branchBO.getAllBranch();

            for (BranchDto dto : list) {
                obList.add(dto.getId());
            }

            cmbBranch.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
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

                var tm = new BookTm(dto.getId(), dto.getTitle(), dto.getAuthor(),dto.getDesc(), dto.getGenre(), dto.getBranchId(), dto.getStatus(), dto.getImage(), btn);

                System.out.println(dto.getId());
                System.out.println(dto.getGenre());

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
                            newValue.getGenre(),
                            newValue.getBranchId(),
                            newValue.getImage(),
                            newValue.getStatus()
                    );
                    setFields(dto);
                });
    }

    private void setFields(BookDto dto) {
        txtId.setText(dto.getId());
        txtDesc.setText(dto.getDesc());
        txtTitle.setText(dto.getTitle());
        txtAuthor.setText(dto.getAuthor());
        cmbGenre.setValue(dto.getGenre());
        cmbBranch.setValue(dto.getBranchId());

        if (dto.getImage() != null) {
            Image image = new Image(new ByteArrayInputStream(dto.getImage()));
            imgBook.setImage(image);
        } else {
            imgBook.setImage(null);
        }
    }

    private void deleteBooks(String id) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String desc = txtDesc.getText();
        String genre = cmbGenre.getValue();
        String bookId = txtId.getText();
        String branchId = cmbBranch.getValue();

        String status = "Available";

        var dto = new BookDto(bookId, title, author, desc, genre, branchId, status);

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
        String genre = cmbGenre.getValue();
        String bookId = txtId.getText();
        String branchId = cmbBranch.getValue();

        String status = "Available";

        var dto = new BookDto(bookId, title, author, desc, genre, branchId, imageBytes, status);

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
        String bookId = txtId.getText();
        String branchId = cmbBranch.getValue();

        String status = "Available";

        var dto = new BookDto(bookId, title, author, desc, genre, branchId, imageBytes, status);

        try {

            boolean isUpdated = bookBO.updateBook(dto);

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Book updated!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Book not updated!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @FXML
//    void btnSelectedOnAction(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
//        List<File> files = fileChooser.showOpenMultipleDialog(null);
//
//        for (File file : files){
//            try {
//                byte[] bytes = convertFileToBytes(file);
//
//                imageBytes = bytes;
//
//                Image image = convertBytesToImage(bytes);
//
//                imgBook.setImage(image);
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    @FXML
    void borrowedBooksOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/borrowedBookTable.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void imgOnAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        List<File> files = fileChooser.showOpenMultipleDialog(null);

        for (File file : files){
            try {
                byte[] bytes = convertFileToBytes(file);

                imageBytes = bytes;

                Image image = convertBytesToImage(bytes);

                imgBook.setImage(image);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private byte[] convertFileToBytes(File file) throws IOException {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            return bos.toByteArray();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

    private Image convertBytesToImage(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new Image(bis);
    }

}
