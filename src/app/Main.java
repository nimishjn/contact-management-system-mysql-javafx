package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Create the main scene that contains the application
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/HomePage.fxml")));
        primaryStage.setTitle("Contact Management System");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
