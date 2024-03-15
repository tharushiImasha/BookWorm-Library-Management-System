package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.tm.BookTm;

import java.io.IOException;
import java.util.List;

public class BookFromGenreController {

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<BookTm> tblAllBooks;

    String genre;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);

    public void initialize() {
        genre = UserDashboardController.genreForBook;

        setCellValueFactory();
        loadAllBooks();
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

    private void loadAllBooks() {
        try {
            List<BookDto> dtoList = bookBO.getBooksFromType(genre);

            ObservableList<BookTm> obList = FXCollections.observableArrayList();

            for(BookDto dto : dtoList){
                String status = bookBO.getStatus(dto.getId());

                var tm = new BookTm(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getDesc(), dto.getGenre(), dto.getBranchId(), status, dto.getImage());

                obList.add(tm);
            }

            tblAllBooks.setItems(obList);

            // Add event handler for row selection
            tblAllBooks.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    String selectedBookId = newSelection.getId();

                    try {
                        loadPopupBook(selectedBookId);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPopupBook(String id) throws IOException {
        UserDashboardController.id = id;

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_details.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

}
