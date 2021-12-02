package controllers;

import app.Alerts;
import app.ChangeView;
import app.Database;
import app.UserSession;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button signUpBtn, logInBtn, delUserBtn, loadHomeButton;
    @FXML
    public TextField usernameTf;
    @FXML
    private PasswordField passwordPf;

    private Database db;
    private Connection conn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Login Controller initialized.");
        try {
            setDelUserBtn();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error occurred in LoginController.");
            e.printStackTrace();
        }
    }

    // Change view to signUp view
    public void switchToSignUpView() throws IOException {
        ChangeView switchToView = new ChangeView(signUpBtn);
        switchToView.changeView("SignUp");
    }

    // Change view to Contacts view
    public void switchToContactsView() throws IOException {
        ChangeView switchToView = new ChangeView(logInBtn);
        switchToView.changeView("Contacts");
    }

    // Change view to HomePage view
    public void switchToHomeView() throws IOException {
        ChangeView switchToView = new ChangeView(loadHomeButton);
        switchToView.changeView("HomePage");
    }

    private void clearUserNamePasswordFields() {
        usernameTf.clear();
        passwordPf.clear();
    }

    public void logIn() throws SQLException, IOException {

        Alerts alert = new Alerts();

        String username = usernameTf.getText();
        String password = passwordPf.getText();

        if (username.equals("") || password.equals("")) {
            alert.showInsufficientInformationMessage();
        }
        else { // If the user have entered both username and password

            // Creating DB connection
            db = new Database();
            conn = db.getConnection();

            // Creating mySQL Query
            String sqlQuery = "SELECT username, password from users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            // If SQL query is valid i.e. the user is valid, then Switch ot Contact View
            if (rs.next()) {
                System.out.println("Login successful for " + username + ".");
                UserSession.setUserName(username);
                switchToContactsView();
            }
            else {
                alert.showLoginUnsuccessfulMessage();
            }

            rs.close();
            ps.close();
            conn.close();
        }
        clearUserNamePasswordFields();
    }

    // Render DeleteUser page
    public void moveToDeleteUserView() throws IOException {

      ChangeView cv = new ChangeView(delUserBtn);
      cv.changeView("DeleteUser");
    }

    // When there are no users in the database, disable 'Delete User' button
    public void setDelUserBtn() throws SQLException {

        db = new Database();
        conn = db.getConnection();

        String query = "SELECT count(username) FROM users";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();

        int noOfUsers = Integer.parseInt(rs.getString(1));

        if (noOfUsers == 0) {
            delUserBtn.setDisable(true);
            System.out.println("Delete user button is disabled");
        }
    }
}
