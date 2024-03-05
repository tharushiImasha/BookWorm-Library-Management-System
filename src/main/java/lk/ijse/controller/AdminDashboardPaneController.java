package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    private Circle profile;

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

    public void initialize(){
        lblName.setText(LoginController.name + " !");
        generateTime();
    }

    public void generateTime() {
        // Create a DateTimeFormatter for formatting the time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy | EEEE, HH:mm");

        // Create a Timeline to update the time every second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    // Get current time
                    LocalDateTime currentTime = LocalDateTime.now();
                    // Format the time
                    String formattedTime = currentTime.format(formatter);
                    // Update the UI on the JavaFX Application Thread
                    Platform.runLater(() -> lblDateTime.setText(formattedTime));
                })
        );
        // Set the cycle count to indefinite so the timeline keeps running
        timeline.setCycleCount(Animation.INDEFINITE);
        // Start the timeline
        timeline.play();
    }

    @FXML
    void ProfileOnAction(MouseEvent event) {

    }

    @FXML
    void rectBooksOnAction(MouseEvent event) {

    }

    @FXML
    void rectBranchesOnAction(MouseEvent event) {

    }

    @FXML
    void rectLogoutOnAction(MouseEvent event) {

    }

    @FXML
    void rectUsersOnAction(MouseEvent event) {

    }

    @FXML
    void rectdashboardOnAction(MouseEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

}
