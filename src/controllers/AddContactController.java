package controllers;

import app.Alerts;
import app.ChangeView;
import app.Database;
import app.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddContactController implements Initializable {

    @FXML
    private TextField contactNameTf, contactNumberTf;
    @FXML
    private Button okBtn;
    @FXML
    private ComboBox<String> contactExtensionTf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AddContactController Initialized.");

        ObservableList<String> codes = FXCollections.observableArrayList("+91","+1","+44","+20","+971","+46");
        contactExtensionTf.getItems().addAll(codes);
        contactExtensionTf.setValue("+91");
    }

    // clear name and number fields
    public void clearFields() {
        contactNameTf.clear();
        contactNumberTf.clear();
    }

    public void addContact() throws SQLException {

        String contactName = contactNameTf.getText();
        String contactNumber = contactNumberTf.getText();
        String countryCode = contactExtensionTf.getValue();
        String username = UserSession.getUserName();

        if (!(contactName.equals("") || contactNumber.equals(""))) {

            // stop execution if number is not valid
            if (!(validateNumber(contactNumber))) {

                Alerts alert = new Alerts();
                alert.showNumberNotValid();
                System.out.println("AddContactController: Number not valid.");
                return;
            }

            Database db = new Database();
            Connection conn = db.getConnection();

            String query = "INSERT INTO contacts VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, contactName);
            ps.setString(2, countryCode + " " + contactNumber);
            ps.setString(3, username);

            int rowsAffected = ps.executeUpdate();

            ps.close();
            conn.close();

            if (rowsAffected == 1) {
                System.out.println("AddContactController: " + contactName + " added to database for " + username);
            }
        }
        clearFields();
    }

    /*
     * The below method checks if all the characters in number
     * are digits, and number of digits are 10 following the
     * Indian standards.
     */
    public boolean validateNumber(String number) {

        if (number.length() == 10) {
            String regex = "[0-9]+";
            Pattern onlyDigits = Pattern.compile(regex);
            Matcher matcher = onlyDigits.matcher(number);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public void backToContactList() throws IOException {
        ChangeView switchView = new ChangeView(okBtn);
        switchView.changeView("Contacts");
    }
}
