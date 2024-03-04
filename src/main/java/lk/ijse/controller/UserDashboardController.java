package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;

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

    LoginController loginController = new LoginController();
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.USER);

    public void initialize(){
        getName();
    }

    private void getName(){
        String name = userBO.getName(loginController.getUserName());
        lblName.setText(name);
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
