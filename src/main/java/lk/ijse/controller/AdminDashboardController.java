package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.BorrowedBookBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.BookDto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminDashboardController {

    @FXML
    private ImageView imgB1;

    @FXML
    private ImageView imgB2;

    @FXML
    private ImageView imgB3;

    @FXML
    private ImageView imgB4;

    @FXML
    private Label lblBorrowedBooks;

    @FXML
    private Label lblOverdueBooks;

    @FXML
    private Label lblUsers;

    private List<String> bookIds = new ArrayList<>();

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);
    BorrowedBookBO borrowedBookBO = (BorrowedBookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BORROWEDDETAILS);

    public void initialize(){
        setBooks();

        setBookImage();

        lblUsers.setText(String.valueOf(userBO.getReaderCount()));
        lblBorrowedBooks.setText(String.valueOf(borrowedBookBO.getBorrowedCount()));
        lblOverdueBooks.setText(String.valueOf(borrowedBookBO.getOverDueCount()));

    }

    private void setBookImage() {
        byte[] bytes1 = bookBO.getBookImg(bookIds.get(0));
        ByteArrayInputStream bis1 = new ByteArrayInputStream(bytes1);
        imgB1.setImage(new Image(bis1));

        byte[] bytes2 = bookBO.getBookImg(bookIds.get(1));
        ByteArrayInputStream bis2 = new ByteArrayInputStream(bytes2);
        imgB2.setImage(new Image(bis2));

        byte[] bytes3 = bookBO.getBookImg(bookIds.get(2));
        ByteArrayInputStream bis3 = new ByteArrayInputStream(bytes3);
        imgB3.setImage(new Image(bis3));

        byte[] bytes4 = bookBO.getBookImg(bookIds.get(3));
        ByteArrayInputStream bis4 = new ByteArrayInputStream(bytes4);
        imgB4.setImage(new Image(bis4));
    }

    @FXML
    void borrowedOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/borrowedBookTable.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void overDueOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/over_due_books.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("OverDue Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void setBooks() {

        List<BookDto> dtoList = bookBO.getRecentlyAddedBooks(4);

        for (BookDto dto : dtoList) {
            bookIds.add(dto.getId());
        }
    }

}
