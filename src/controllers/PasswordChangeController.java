package controllers;

import app.Alerts;
import app.ChangeView;
import app.Database;
import app.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PasswordChangeController implements Initializable {


    @FXML
    private Button okBtn;
    @FXML
    private PasswordField oldPass, newPass;
    @FXML
    private TextField username;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("PasswordChangeController Initialized");
    }

    // Check with the database and update password
    public void changePassword() throws SQLException {
        Alerts alert;

        if (!(oldPass.getText().equals("") || newPass.getText().equals("") || username.getText().equals(""))) {

            if (oldPass.getText().equals(newPass.getText())) {
                System.out.println("PasswordChangeController: No change detected between passwords");

                // Custom Alert box
                alert = new Alerts();
                alert.auxAlert(Alert.AlertType.ERROR,
                        "Change password Failed",
                        "Change password Failed",
                        "The old password and the new password is the same."
                );
                return;
            }

            String sessionUsername = UserSession.getUserName();

            String inputUsername = username.getText(); // Username entered by user
            String oldPassword = oldPass.getText(); // Old password
            String newPassword = newPass.getText(); // New Password

            Database db = new Database();
            Connection conn = db.getConnection();

            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, inputUsername);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();

            String RealOldPassword = rs.getString(1);

            if (oldPassword.equals(RealOldPassword) && sessionUsername.equals(inputUsername)) {
                System.out.println("PasswordChangeController: Real old password and Old password matches");

                String q = "UPDATE users SET password = ? WHERE username = ?";
                PreparedStatement ps2 = conn.prepareStatement(q);
                ps2.setString(1, newPassword);
                ps2.setString(2, inputUsername);

                int rowsAffectedHere = ps2.executeUpdate();

                if (rowsAffectedHere == 1) {

                    // Custom Alert Box
                    alert = new Alerts();
                    alert.auxAlert(
                            Alert.AlertType.INFORMATION,
                            "Password Change",
                            "Password Change",
                            "Password successfully changed for " + inputUsername
                    );

                    System.out.println("PasswordChangeController: Password is changed");
                    goBack();
                }

                ps2.close();
            }
            else if(sessionUsername.equals(inputUsername)) {
                System.out.println("PasswordChangeController: Username does not match");
                Alerts.auxAlertStatic(
                        Alert.AlertType.ERROR,
                        "Password Changer",
                        "Password Changer",
                        "The entered username do not match your username."
                );
            }
            else {
                System.out.println("PasswordChangeController: Real old password and Old password does not match");
                Alerts.auxAlertStatic(
                        Alert.AlertType.ERROR,
                        "Password Changer",
                        "Password Changer",
                        "Real old password and Old password does not match"
                );
            }

            ps.close();
            rs.close();
            conn.close();
            clearFields();

        } else {
            System.out.println("PasswordChangeController: Nothing entered in one or more TextFields");
            Alerts.auxAlertStatic(
                    Alert.AlertType.ERROR,
                    "Password Changer",
                    "Password Changer",
                    "Please fill all the text fields to continue."
            );

        }
    }

    public void clearFields() {
        oldPass.clear();
        newPass.clear();
    }

    // Render 'Contacts' page
    public void goBack() {

        try {
            ChangeView cv = new ChangeView(okBtn);
            cv.changeView("Contacts");
        } catch (IOException e) {
            System.err.println("Error in PasswordChangeController:");
            e.printStackTrace();
        }
    }
}
