package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.tm.BookTm;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

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
    private TableColumn<?, ?> colBranchId;

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
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branchId"));
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
                            //newValue.getImage(),
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

    private boolean validateBooks() {
        String id = txtId.getText();
        boolean isValid = Pattern.matches("[B][0-9]{1,}", id);

        if (!isValid){

            if (txtId.getText().isEmpty()){
                flashBorder(txtId);
                return false;
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
                return false;
            }
        }

        String title = txtTitle.getText();
        boolean isValidName = Pattern.matches("([a-zA-Z\\s]+)", title);

        if (!isValidName){

            if (txtTitle.getText().isEmpty()){
                flashBorder(txtTitle);
                return false;
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Invalid Title").show();
                return false;
            }
        }

        String author = txtAuthor.getText();
        boolean isValidAuthor = Pattern.matches("([a-zA-Z\\s]+)", author);

        if (!isValidAuthor){

            if (txtAuthor.getText().isEmpty()){
                flashBorder(txtAuthor);
                return false;
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Invalid Author Name").show();
                return false;
            }
        }

        return true;
    }

    private void flashBorder(TextField textField) {
        textField.setStyle("-fx-border-color: #000000;-fx-background-color: rgba(224,68,68,0.13)");
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
        txtTitle.clear();
        txtDesc.clear();
        txtId.clear();
        txtPhoto.clear();
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

            if (validateBooks()) {

                boolean isSaved = bookBO.saveBook(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Book saved!").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Book not saved!").show();
                }
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

    @FXML
    void borrowedBooksOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/borrowedBookTable.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void overDueBooksOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/over_due_books.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    void imgOnAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
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
