package csku.expense;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginPageController implements Initializable {
    @FXML private TextField idField;
    @FXML private PasswordField pinField;
    @FXML private Label showErrorLogin;
    @FXML private JFXButton loginButton;
    @FXML private JFXButton cancelButton;

    Users users = new Users("1", "6499", "Thikamporn", 200.0);

    @FXML
    void handleCancelButton(ActionEvent event) {
        idField.clear();
        pinField.clear();
        showErrorLogin.setText("");

    }

    @FXML
    void handleLoginButton(ActionEvent event) throws IOException {
        if (users.validateUser(idField.getText(), pinField.getText()))
        {
            FXMLLoader loader = new FXMLLoader();
            loginButton.getScene().getWindow().hide();
            Stage homeWindow = new Stage();
            Parent root = loader.load(getClass().getResource("/mainPage.fxml"));
            Scene scene = new Scene(root);
            homeWindow.setScene(scene);
            homeWindow.show();
            homeWindow.setResizable(false);

        }
        else if (idField.getText().equalsIgnoreCase("") || pinField.getText().equalsIgnoreCase("")){
            showErrorLogin.setText("Your ID or PIN incorrect");
        }
        else if (idField.getText().equalsIgnoreCase("") && pinField.getText().equalsIgnoreCase("")){
            showErrorLogin.setText("Your ID or PIN incorrect");
        }
        else showErrorLogin.setText("Your ID or PIN incorrect");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idField.clear();
        pinField.clear();

    }
}
