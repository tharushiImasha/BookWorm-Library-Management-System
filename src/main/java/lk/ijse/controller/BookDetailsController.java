package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;

import java.io.ByteArrayInputStream;

public class BookDetailsController {

    @FXML
    private ImageView available;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private ImageView imgBook;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblTitle;

    @FXML
    private ImageView notAvailble;

    private String id;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);

    public void initialize(){
        id = UserDashboardController.id;

        setImage();
        setTitle();
        setAuthor();
        setDesc();
    }

    private void setDesc() {
        lblDesc.setText(bookBO.getDescd(id));
    }

    private void setAuthor() {
        lblAuthor.setText(bookBO.getAuthor(id));
    }

    private void setTitle() {
        lblTitle.setText(bookBO.getTitle(id));
    }

    private void setImage() {
        byte[] bytes1 = bookBO.getBookImg(id);
        ByteArrayInputStream bis1 = new ByteArrayInputStream(bytes1);
        imgBook.setImage(new Image(bis1));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = (Stage) this.rootNode.getScene().getWindow();

        stage.close();
    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) {

    }

}
