package app;

import javafx.scene.control.Alert;

public class Alerts {

    Alert alert;

    // Common Alter Dialog Box Function
    public void auxAlert(Alert.AlertType alertType, String setTitle, String setHeaderText, String setContentText) {
        alert = new Alert(alertType);
        alert.setTitle(setTitle);
        alert.setHeaderText(setHeaderText);
        alert.setContentText(setContentText);
        alert.showAndWait();
    }

    // Used to make auxAlert() function static
    public static void auxAlertStatic(Alert.AlertType alertType, String setTitle, String setHeaderText, String setContentText){
        Alert alert = new Alert(alertType);
        alert.setTitle(setTitle);
        alert.setHeaderText(setHeaderText);
        alert.setContentText(setContentText);
        alert.show();
    }

    public void showInvalidInformationMessage() {
        auxAlert(Alert.AlertType.WARNING,
                "Invalid",
                "Invalid Information",
                "");
    }

    public void showLoginUnsuccessfulMessage() {
        auxAlert(Alert.AlertType.ERROR,
                "Login Failure",
                "Login Failure",
                "Please check your username and password and try again."
        );
    }

    public void showNumberNotValid() {
        auxAlert(Alert.AlertType.ERROR,
                "Invalid Number",
                "Invalid Number",
                "Please enter a valid 10 digit number to continue."
        );
    }

    public void showUrlNotValid() {
        auxAlert(Alert.AlertType.ERROR,
                "Invalid Url",
                "Invalid Url",
                "Please enter a valid contact image URL. Allowed extensions are '.jpg', '.jpeg' and '.png'."
        );
    }

    public void showInsufficientInformationMessage() {
        auxAlert(Alert.AlertType.ERROR,
                "Insufficient Information",
                "Insufficient Information",
                "Please fill in all required fields."
        );
    }

    public void showPasswordsDoesNotMatch() {
        auxAlert(Alert.AlertType.ERROR,
                "Password Mismatch",
                "Password Mismatch",
                "The passwords do not watch. Retype passwords to avoid issues."
        );
    }

    public void showSignUpSuccessfulMessage() {
        auxAlert(Alert.AlertType.INFORMATION,
                "Sign Up Successful",
                "Sign Up Successful",
                "Thank you for signing up with us."
        );
    }

    public void showAllContactsDeletedMessage() {
        auxAlert(Alert.AlertType.INFORMATION,
                "Contacts deleted",
                "Contacts deleted",
                "All contact has been cleared from the database."
        );
    }

    public void showEditionSuccessful() {
        auxAlert(Alert.AlertType.INFORMATION,
                "Contact Edited",
                "Contact Edited",
                "The contact has been successfully edited."
        );
    }
}
