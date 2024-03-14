package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDashboardController {

    @FXML
    private Rectangle childrenRect;

    @FXML
    private Rectangle fantacyRect;

    @FXML
    private Rectangle fictionRect;

    @FXML
    private Rectangle horrorRect;

    @FXML
    private ImageView imgB1;

    @FXML
    private ImageView imgB2;

    @FXML
    private ImageView imgB3;

    @FXML
    private ImageView imgB4;

    @FXML
    private Label lblAuthor1;

    @FXML
    private Label lblAuthor2;

    @FXML
    private Label lblAuthor3;

    @FXML
    private Label lblAuthor4;

    @FXML
    private Label lblB1;

    @FXML
    private Label lblB2;

    @FXML
    private Label lblB3;

    @FXML
    private Label lblB4;

    @FXML
    private Label lblDateTime;

    @FXML
    private Text lblName;

    @FXML
    private TextField txtSearch;

    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private Rectangle popularRect;

    @FXML
    private Circle profile;

    @FXML
    private Rectangle recentRect;

    static String id;

    public static String genreForBook;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);

    public void initialize(){
        lblName.setText(LoginController.name + " !");
        generateTime();
        setBook();
        setTitle();
        setAuthor();
        loadGenre();
    }

    private void loadGenre() {
        cmbGenre.getItems().add("Horror");
        cmbGenre.getItems().add("Fantasy");
        cmbGenre.getItems().add("Children's");
        cmbGenre.getItems().add("Fiction");
    }

    private void setAuthor() {
        lblAuthor1.setText(bookBO.getAuthor("B1"));
        lblAuthor2.setText(bookBO.getAuthor("B2"));
        lblAuthor3.setText(bookBO.getAuthor("B3"));
        lblAuthor4.setText(bookBO.getAuthor("B4"));
    }

    private void setTitle() {
        lblB1.setText(bookBO.getTitle("B1"));
        lblB2.setText(bookBO.getTitle("B2"));
        lblB3.setText(bookBO.getTitle("B3"));
        lblB4.setText(bookBO.getTitle("B4"));
    }

    private void setBook() {
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

    public void generateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | EEEE, HH:mm");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime currentTime = LocalDateTime.now();
                    String formattedTime = currentTime.format(formatter);
                    Platform.runLater(() -> lblDateTime.setText(formattedTime));
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void ProfileOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_profile.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void childrenRectOnAction(MouseEvent event) throws IOException {
        genreForBook = "Children's";

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_from_genre.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void fantacyRectOnAction(MouseEvent event) throws IOException {
        genreForBook = "Fantasy";

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_from_genre.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void fictionRectOnAction(MouseEvent event) throws IOException {
        genreForBook = "Fiction";

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_from_genre.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void horrorRectOnAction(MouseEvent event) throws IOException {
        genreForBook = "Horror";

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_from_genre.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void genreOnAction(ActionEvent event) throws IOException {
        genreForBook = cmbGenre.getValue();

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_from_genre.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void imgB1inAction(MouseEvent event) throws IOException {
        id = "B1";

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_details.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void imgB2inAction(MouseEvent event) throws IOException {
        id = "B2";

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_details.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void imgB3inAction(MouseEvent event) throws IOException {
        id = "B3";

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_details.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void imgB4inAction(MouseEvent event) throws IOException {
        id = "B4";

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_details.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void popularRectOnAction(MouseEvent event) {

    }

    @FXML
    void recentRectOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/recentBooks.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void searchOnAction(ActionEvent event) throws IOException {
        id = bookBO.getId(txtSearch.getText());

        if(id != null){

            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/book_details.fxml"));

            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();

            stage.setTitle("Book");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.centerOnScreen();
            stage.show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION, "This book is not in the library").show();
        }

    }

    @FXML
    void moreBookOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/moreBooks.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
