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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DeleteUserController implements Initializable {

    @FXML
    private Button deleteBtn;
    @FXML
    private TextField usernameTf;
    @FXML
    private PasswordField passwordPf;


    Database db;
    Connection conn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is DeleteUserController");
    }


    // Delete the only user in the system
    public void deleteUser() throws SQLException, IOException {

        String username = usernameTf.getText();
        String password = passwordPf.getText();
        Alerts alert = new Alerts();

        if (!(username.equals("")) && !(password.equals(""))) {

            if (validUser()) {

                db = new Database();
                conn = db.getConnection();

                // Deleting user
                String query = "DELETE FROM users WHERE username = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {

                    String deleteContactsQuery = "DELETE FROM contacts where username=?";
                    PreparedStatement preparedStatement = conn.prepareStatement(deleteContactsQuery);
                    preparedStatement.setString(1,username);
                    preparedStatement.executeUpdate();

                    System.out.println("DeleteUserController: User " + username + "successfully deleted from system.");
                    preparedStatement.close();
                    back();
                }

                ps.close();
                conn.close();

            } else {
                alert.showInvalidInformationMessage();
            }
        } else {
            alert.showInsufficientInformationMessage();
        }
        clearUserNamePasswordFields();
    }


    // check whether it is a valid user
    public boolean validUser() throws SQLException {

        String enteredUsername = usernameTf.getText();
        String enteredPassword = passwordPf.getText();

        db = new Database();
        conn = db.getConnection();

        String sqlQuery = "SELECT username, password from users WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, enteredUsername);
        ps.setString(2, enteredPassword);

        ResultSet rs = ps.executeQuery();

        boolean isValidUser = rs.next();

        ps.close();
        conn.close();

        return isValidUser;
    }

    // Go back to Log In screen
    public void back() throws IOException {
        ChangeView cv = new ChangeView(deleteBtn);
        cv.changeView("LogIn");
    }

    // Clear the Username and Password fields
    private void clearUserNamePasswordFields() {
        usernameTf.clear();
        passwordPf.clear();
    }
}
