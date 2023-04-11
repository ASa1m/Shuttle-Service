package com.shuttle.fankaar;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    public TabPane maintab;
    public TextField uTextField;
    public PasswordField uPasswordField;
    public Button uLogin;
    public Button signup1;
    public Button adminlogin1;
    public Button bloginDriver;
    Connection conn;
    public TextField textField;
    public PasswordField passwordField;
    public Button signup;
    ResultSet resultSet = null;
    PreparedStatement pst = null;

    public void dLogin(ActionEvent actionEvent) {

        String username = textField.getText();
        String pass = passwordField.getText();
        if (username.equals("") && pass.equals("")) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Invalid username or password");
            a.show();
        } else {
            try {
                conn = DbConnection.ConnectDb();
                assert conn != null;
                pst = conn.prepareStatement("select * from Drivers where Username=? AND Pass=?");
                pst.setString(1, username);
                pst.setString(2, pass);
                resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DriverMap.fxml"));
                    Parent root = loader.load();
                    DriverMapController c2 = loader.getController();
                    c2.setID(resultSet.getDouble("ID"));
                    Scene scene = new Scene(root);
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    window.setMaximized(false);

                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid username or password");
                    alert.show();
                }

            } catch (SQLException | IOException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

    }
    public void uLogin(ActionEvent actionEvent) {

        String username = uTextField.getText();
        String pass = uPasswordField.getText();
        if (username.equals("") && pass.equals("")) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Invalid username or password");
            a.show();
        } else {
            try {
                conn = DbConnection.ConnectDb();
                assert conn != null;
                pst = conn.prepareStatement("select * from Account where UserName=? AND Pass=?");
                pst.setString(1, username);
                pst.setString(2, pass);
                resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMap.fxml"));
                    Parent root = loader.load();
                    UserMapController c2 = loader.getController();
                    c2.setID(resultSet.getDouble("ID"));
                    Scene scene = new Scene(root);
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    window.setMaximized(false);

                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid username or password");
                    alert.show();
                }

            } catch (SQLException | IOException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

    }

    public void forget(ActionEvent actionEvent) {
    }

    public void signup(ActionEvent actionEvent) {
    }

}
