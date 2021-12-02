package controllers;

import app.ChangeView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    private Button homeHogInBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login Controller initialized.");
    }

    public void loadLogIn() throws IOException {
        ChangeView cv = new ChangeView(homeHogInBtn);
        cv.changeView("LogIn");
    }
}