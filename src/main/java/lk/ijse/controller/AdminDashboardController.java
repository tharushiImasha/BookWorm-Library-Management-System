package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;

import java.io.ByteArrayInputStream;

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

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);

    public void initialize(){
        byte[] bytes1 = bookBO.getBookImg("B1");
        ByteArrayInputStream bis1 = new ByteArrayInputStream(bytes1);
        imgB1.setImage(new Image(bis1));

        byte[] bytes2 = bookBO.getBookImg("B2");
        ByteArrayInputStream bis2 = new ByteArrayInputStream(bytes2);
        imgB2.setImage(new Image(bis2));

        byte[] bytes3 = bookBO.getBookImg("B3");
        ByteArrayInputStream bis3 = new ByteArrayInputStream(bytes3);
        imgB3.setImage(new Image(bis3));

        byte[] bytes4 = bookBO.getBookImg("B4");
        ByteArrayInputStream bis4 = new ByteArrayInputStream(bytes4);
        imgB4.setImage(new Image(bis4));

    }

}
