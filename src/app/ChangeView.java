package app;

/*
 * A class which contains a method called changeView to easily switch views/scenes whenever need
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChangeView {

    private final Node node;

    public ChangeView(Node nodeInCurrentView) {
        this.node = nodeInCurrentView;
    }

    public void changeView(String toView) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/" + toView + ".fxml")));
            Stage window = (Stage) this.node.getScene().getWindow();
            Scene scene = new Scene(root, 900, 700);
            window.setScene(scene);
            window.show();
            window.setResizable(false);
        } catch (Exception e) {
            System.err.println("Error occurred in ChangeView()");
            e.printStackTrace();
        }
    }
}
