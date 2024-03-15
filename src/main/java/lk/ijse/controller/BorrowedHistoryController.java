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
import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.dto.tm.BorrowedDetailsTm;

import java.util.List;
import java.util.Optional;

public class BorrowedHistoryController {

    @FXML
    private TableColumn<?, ?> colBorrowingDate;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colReturn;

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
        colReturn.setCellValueFactory(new PropertyValueFactory<>("returned"));
    }

    private void loadAllBooks() {
        try {
            List<BorrowedDetailsDto> dtoList = borrowedBookBO.getAllBorrowed();

            ObservableList<BorrowedDetailsTm> obList = FXCollections.observableArrayList();

            for(BorrowedDetailsDto dto : dtoList){

                String bookId = dto.getBorrowedDetailPK().getId();

                boolean isNull = borrowedBookBO.checkReturnDate(bookId);
                String returned = isNull ? "Not" : "Returned";

                String title = bookBO.getTitle(dto.getBorrowedDetailPK().getId());

                userName = borrowedBookBO.getUserNameFromBorrowed(dto.getBorrowedDetailPK().getId());

                String name = userBO.getName(userName);

                var tm = new BorrowedDetailsTm(dto.getBorrowedDetailPK(), dto.getBorrowedDate(), dto.getDueDate(), title, returned, name);

                obList.add(tm);

            }

            tblBorrowedBooks.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
