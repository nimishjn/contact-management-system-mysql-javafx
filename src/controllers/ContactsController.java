package controllers;

import app.Alerts;
import app.ChangeView;
import app.Database;
import app.UserSession;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {

    @FXML
    private Button signOutBtn, addBtn, ContactSearchBtn;
    @FXML
    private ListView<String> contactList;
    @FXML
    private Label contactNameLabel, contactNumberLabel, usernameLabel;
    @FXML
    private TextField ContactSearchText;

    private Database db;
    private Connection conn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("ContactsController initialized.");

        try {
            readFromDatabase("");
            updateScreenUsername();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        contactList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                reflectDataOfContactSelected();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    // Search Button Click
    public void contactSearchButtonClick() {
        String searchValue = ContactSearchText.getText();

        try {
            readFromDatabase(searchValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Render AddContact Page
    public void changeViewToAddContact() throws IOException {
        ChangeView changeView = new ChangeView(addBtn);
        changeView.changeView("AddContact");
    }

    // Update username on top-left
    public void updateScreenUsername() throws IOException {
        String username = UserSession.getUserName();
        usernameLabel.setText("Hello, " + username);
    }

    // Loads all the contacts from the database
    public void readFromDatabase(String filter) throws SQLException {

        // Clear the list of contacts
        contactList.getItems().clear();

        String name;
        String username = UserSession.getUserName();

        db = new Database();
        conn = db.getConnection();

        String query = "SELECT name FROM contacts WHERE username = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            name = rs.getString(1);

            // Applies the filter when a person wants to search for a contact
            if(name.toLowerCase().contains(filter.toLowerCase()))
                contactList.getItems().add(name);
        }

        rs.close();
        ps.close();
        conn.close();
    }

    // Load Name and Phone number of the contact
    public void reflectDataOfContactSelected() throws SQLException {

        if (contactList.getItems().isEmpty()) {
            contactNameLabel.setText("N/A");
            contactNumberLabel.setText("N/A");
            return;
        }

        String name, number;
        String username = UserSession.getUserName();

        name = contactList.getSelectionModel().getSelectedItem();

        db = new Database();
        conn = db.getConnection();

        String query = "SELECT number from contacts WHERE name = ? and username = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, username);

        ResultSet rs = ps.executeQuery();

        rs.next();

        number = rs.getString(1); // Stores number

        contactNameLabel.setText(name);
        contactNumberLabel.setText(number);
    }

    // Clear all contacts
    public void clearAllContacts() throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setTitle("Clear all");
        alert.setHeaderText("Clear all");
        alert.setContentText("Are you sure you want to clear all contacts? If yes, click 'Ok'.");

        Optional<ButtonType> result = alert.showAndWait();

        if(!result.isPresent() || result.get() != ButtonType.OK) {
            System.out.println("ContactsController: Clear all cancelled.");
        } else {
            db = new Database();
            conn = db.getConnection();

            String query = "DELETE FROM contacts";
            Statement st = conn.createStatement();
            int rowsAffected = st.executeUpdate(query);

            if (rowsAffected > 0) {

                contactList.getItems().clear();
                System.out.println("ContactsController: Contact list cleared.");
                Alerts alert2 = new Alerts();
                alert2.showAllContactsDeletedMessage();
            } else {
                System.out.println("ContactsController: Failed to clear all contacts or maybe user has no contacts.");
            }

            st.close();
            conn.close();
        }
    }

    // Delete contact from the ListView 'contactList'
    public void deleteContact() throws SQLException {

        // ONLY if a contact is selected do the below
        if (contactList.getSelectionModel().getSelectedItem() != null) {

            String contactName = contactList.getSelectionModel().getSelectedItem();
            String username = UserSession.getUserName();

            db = new Database();
            conn = db.getConnection();

            String query = "DELETE FROM contacts WHERE name = ? and username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, contactName);
            ps.setString(2, username);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected != 0) {
                contactList.getItems().remove(contactName);
                System.out.println("ContactsController: " + contactName + " deleted.");
            } else {
                System.out.println("ContactsController: Could not delete contact.");
            }
        } else {
            System.out.println("ContactsController: No contact selected.");
        }
    }

    // When editBtn is pressed, send information of the contact selected for editing to the contact editing scene
    public void sendInfoToEditController() throws IOException {

        // terminate execution of function if no contact is selected
        if (contactList.getSelectionModel().getSelectedItem() == null) {
            System.out.println("ContactsController: No contact selected for editing.");
            return;
        }

        String contactName = contactNameLabel.getText();
        String contactNumber = contactNumberLabel.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/EditContact.fxml"));
        Parent root = loader.load();

        // Return the controller of 'EditContacts'
        EditContactController editingController = loader.getController();
        editingController.setTextBoxes(contactName, contactNumber);

        Stage window = (Stage) addBtn.getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    // Render ChangePassword page
    public void moveToChangePasswordView() throws IOException {
        ChangeView cv = new ChangeView(signOutBtn);
        cv.changeView("PasswordChange");
    }

    // Signout and clear User Session
    public void signOut() throws IOException {
        ChangeView switchView = new ChangeView(signOutBtn);
        UserSession.cleanUserSession();
        System.out.println("User signed out");
        switchView.changeView("LogIn");
    }
}
