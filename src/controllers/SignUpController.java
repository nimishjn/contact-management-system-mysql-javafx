package controllers;

import app.Alerts;
import app.ChangeView;
import app.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private TextField usernameTf;
    @FXML
    private PasswordField passwordPf, reTypePasswordPf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("SignUpController initialized.");
    }

    // Render Login View
    public void backToLoginView() throws IOException {
        ChangeView switchToView = new ChangeView(backBtn);
        switchToView.changeView("LogIn");
    }

    // Clear Username, Password and Re-type password
    public void clearAllFields() {
        usernameTf.clear();
        passwordPf.clear();
        reTypePasswordPf.clear();
    }

    // Check if Username, Password and Re-type password are not empty
    public boolean completedFields() {
        if (usernameTf.getText().equals("") || passwordPf.getText().equals("") || reTypePasswordPf.getText().equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    // check whether passwords match
    public boolean passwordMatches() {
        if(passwordPf.getText().equals(reTypePasswordPf.getText()) && passwordPf.getText() != null && reTypePasswordPf.getText()!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    // sign up a new user
    public void signUp() throws SQLException, IOException {

        Alerts alert = new Alerts();

        if ((completedFields()) && (passwordMatches())) {

            addUserToDatabase();
            alert.showSignUpSuccessfulMessage();
            System.out.println("Sign Up successful for " + usernameTf.getText() + ".");
            backToLoginView();
        }
        else if (!completedFields()) {
            alert.showInsufficientInformationMessage();
        }
        else {
            alert.showPasswordsDoesNotMatch();
        }
        clearAllFields();
    }

    // Adding user to the database
    public void addUserToDatabase() throws SQLException {

        Database db = new Database();
        Connection conn = db.getConnection();

        String username = usernameTf.getText();
        String password = passwordPf.getText();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (?, ?)");
        ps.setString(1, username);
        ps.setString(2, password);

        int rowsAffected = ps.executeUpdate();

        System.out.println("MySQL: Rows affected = " + rowsAffected);

        ps.close();
        conn.close();
    }
}
