package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.BorrowedBookBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.dto.tm.BorrowedDetailsTm;

import java.util.List;

public class MyBooksController {

    @FXML
    private TableColumn<?, ?> colBorrowedDate;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colReturn;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<BorrowedDetailsTm> tblBorrowedDetails;

    BorrowedBookBO borrowedBookBO = (BorrowedBookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BORROWEDDETAILS);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);

    String userName;

    String returned;

    String bookId;

    public void initialize() {
        userName = userBO.getUserName(LoginController.name);

        loadAllBooks();
        setCellValueFactory();
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

}
