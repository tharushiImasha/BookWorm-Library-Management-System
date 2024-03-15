package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.BorrowedBookBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.dto.tm.BookTm;
import lk.ijse.dto.tm.BorrowedDetailsTm;
import lk.ijse.embedded.BorrowedDetailPK;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowedDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OverDueBooksController {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colBorrowingDate;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<BorrowedDetailsTm> tblBorrowedBooks;

    String userName;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);
    BorrowedBookBO borrowedBookBO = (BorrowedBookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BORROWEDDETAILS);

    public void initialize() {
        setCellValueFactory();
        loadAllBooks();
    }

    private void setCellValueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBorrowingDate.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadAllBooks() {
        try {
            List<BorrowedDetailsDto> dtoList = borrowedBookBO.getOverdueBorrowedDetails();

            ObservableList<BorrowedDetailsTm> obList = FXCollections.observableArrayList();

            for(BorrowedDetailsDto dto : dtoList){

                Button btn = new Button("Return");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to return?", yes, no).showAndWait();

                    if (type.orElse(no) == yes){
                        int index = tblBorrowedBooks.getSelectionModel().getFocusedIndex();

                        String title = (String) colTitle.getCellData(index);

                        String bookId = bookBO.getId(title);

                        userName = borrowedBookBO.getUserNameFromBorrowed(dto.getBorrowedDetailPK().getId());

                        returnBook(bookId);

                        obList.remove(index);
                        tblBorrowedBooks.refresh();
                    }

                });

                String title = bookBO.getTitle(dto.getBorrowedDetailPK().getId());

                userName = borrowedBookBO.getUserNameFromBorrowed(dto.getBorrowedDetailPK().getId());

                String name = userBO.getName(userName);

                var tm = new BorrowedDetailsTm(dto.getBorrowedDetailPK(), dto.getBorrowedDate(), dto.getDueDate(), title, name, btn);

                obList.add(tm);

            }

            tblBorrowedBooks.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void returnBook(String id) {

        Timestamp returnDate = new Timestamp(System.currentTimeMillis());

        Book book = bookBO.searchBook(id);

        BorrowedDetails borrowedDetails = borrowedBookBO.searchBorrowedDetails(id, userName);

        boolean isUpdated = borrowedBookBO.updateBorrowedDetail(new BorrowedDetailsDto(borrowedDetails.getBorrowedDetailPK(), borrowedDetails.getBorrowedDateTime(), borrowedDetails.getDueDate(), returnDate));

        if (isUpdated){
            boolean available = bookBO.updateBook(new BookDto(id, book.getTitle(), book.getAuthor(), book.getDesc(), book.getGenre(), book.getBranch().getId(), book.getImage(), "Available"));

            if (available){
                new Alert(Alert.AlertType.CONFIRMATION, "Book Returned!").show();
            }
        }

    }

}
