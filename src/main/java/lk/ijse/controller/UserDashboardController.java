package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UserDashboardController {
    @FXML
    private Text lblName;

    @FXML
    private Rectangle childrenRect;

    @FXML
    private Rectangle fantacyRect;

    @FXML
    private Rectangle fictionRect;

    @FXML
    private Rectangle horrorRect;

    @FXML
    private Rectangle popularRect;

    @FXML
    private Circle profile;

    @FXML
    private Rectangle recentRect;

    @FXML
    private Label lblDateTime;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);

    public void initialize(){
        //lblName.setText(LoginController.name + " !");
        getName();
        generateTime();
    }

    public void getName(){
       String fullName = LoginController.name;
       lblName.setText(fullName);

//       String name = fullName.substring(0, fullName.indexOf(' '));
//       lblName.setText(name + " !");
    }

//    public void generateTime(){
//        String month = new SimpleDateFormat("MMMM").format(new Date());
//        String dayOfWeek = new SimpleDateFormat("EEEE").format(new Date());
//        String currentDate = new SimpleDateFormat("dd, yyyy").format(new Date());
//        String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
//
//        lblDateTime.setText(month+" "+currentDate+" | "+dayOfWeek+", "+currentTime);
//    }

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
    void childrenRectOnAction(MouseEvent event) {

    }

    @FXML
    void fantacyRectOnAction(MouseEvent event) {

    }

    @FXML
    void fictionRectOnAction(MouseEvent event) {

    }

    @FXML
    void genreOnAction(ActionEvent event) {

    }

    @FXML
    void horrorRectOnAction(MouseEvent event) {

    }

    @FXML
    void popularRectOnAction(MouseEvent event) {

    }

    @FXML
    void recentRectOnAction(MouseEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }
}
