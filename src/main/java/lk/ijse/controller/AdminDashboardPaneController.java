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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminDashboardPaneController {

    @FXML
    private ImageView booksOff;

    @FXML
    private ImageView booksOn;

    @FXML
    private ImageView branchesOff;

    @FXML
    private ImageView branchesOn;

    @FXML
    private AnchorPane childNode;

    @FXML
    private ImageView dashboardOff;

    @FXML
    private ImageView dashboardOn;

    @FXML
    private Label lblDateTime;

    @FXML
    private Text lblName;

    @FXML
    private TextField txtSearch;

    @FXML
    private Rectangle rectBooks;

    @FXML
    private Rectangle rectBranches;

    @FXML
    private Rectangle rectDashboard;

    @FXML
    private Rectangle rectLogout;

    @FXML
    private Rectangle rectUsers;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private ImageView usersOff;

    @FXML
    private ImageView usersOn;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.BOOK);

    public void initialize() throws IOException {
        lblName.setText(LoginController.name + " !");
        generateTime();

        booksOn.setVisible(false);
        usersOn.setVisible(false);
        branchesOn.setVisible(false);
        dashboardOn.setVisible(true);
        dashboardOff.setVisible(true);

        childNode.getChildren().clear();
        childNode.getChildren().add(FXMLLoader.load(childNode.getClass().getResource("/view/admin_dashboard.fxml")));
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
    void ReturnBooksOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/borrowedBookTable.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();

        stage.setTitle("Book");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void rectBooksOnAction(MouseEvent event) throws IOException {
        booksOn.setVisible(true);
        usersOn.setVisible(false);
        branchesOn.setVisible(false);
        dashboardOn.setVisible(false);
        booksOff.setVisible(true);

        childNode.getChildren().clear();
        childNode.getChildren().add(FXMLLoader.load(childNode.getClass().getResource("/view/books.fxml")));
    }

    @FXML
    void rectBranchesOnAction(MouseEvent event) throws IOException {
        booksOn.setVisible(false);
        usersOn.setVisible(false);
        branchesOn.setVisible(true);
        dashboardOn.setVisible(false);
        branchesOff.setVisible(true);

        childNode.getChildren().clear();
        childNode.getChildren().add(FXMLLoader.load(childNode.getClass().getResource("/view/branch.fxml")));
    }

    @FXML
    void rectLogoutOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void rectUsersOnAction(MouseEvent event) throws IOException {
        booksOn.setVisible(false);
        usersOn.setVisible(true);
        branchesOn.setVisible(false);
        dashboardOn.setVisible(false);
        usersOff.setVisible(true);

        childNode.getChildren().clear();
        childNode.getChildren().add(FXMLLoader.load(childNode.getClass().getResource("/view/users.fxml")));
    }

    @FXML
    void rectdashboardOnAction(MouseEvent event) throws IOException {
        booksOn.setVisible(false);
        usersOn.setVisible(false);
        branchesOn.setVisible(false);
        dashboardOn.setVisible(true);
        dashboardOff.setVisible(true);

        childNode.getChildren().clear();
        childNode.getChildren().add(FXMLLoader.load(childNode.getClass().getResource("/view/admin_dashboard.fxml")));
    }

    @FXML
    void searchOnAction(ActionEvent event) throws IOException {
        String id = bookBO.getId(txtSearch.getText());

        UserDashboardController.id = id;

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

}
