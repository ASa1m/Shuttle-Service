package com.shuttle.fankaar;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapController {
    @FXML
    public StackPane sPane;
    @FXML
    public ImageView uLocation;
    @FXML
    public Rectangle borderline;
    @FXML
    public Button bCancel;
    @FXML
    public ProgressIndicator rProgress;
    Connection conn;
    int rs;

    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    double ID;
    PreparedStatement pst;
    String sql;
    ResultSet details;
    @FXML
    private Button bRequest;
    @FXML
    private ImageView mapImage;
    @FXML
    private ImageView sLocation;
    private List<ImageView> shuttles = new ArrayList<>();

    // Using Multithreading to Keep updating locations in background
    Thread thread = new Thread(() -> {
        Runnable updater = this::showShuttles;

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }

            // UI update is run on the Application thread
            Platform.runLater(updater);
        }
    });

    //Constructor
    public UserMapController() {
        conn = DbConnection.ConnectDb();
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
    }

    public void showShuttles() {
        try {
            if (conn == null) {
                return;
            }
            String sql = "select * from ShuttleLocations where status = 1";
            pst = conn.prepareStatement(sql);
            details = pst.executeQuery();
            shuttles.clear();
            sPane.getChildren().clear();
            sPane.getChildren().addAll(mapImage,sLocation,uLocation,borderline);
            spawnPins(details, sLocation, sPane, shuttles, pst);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    static void spawnPins(ResultSet details, ImageView sLocation, StackPane sPane, List<ImageView> shuttles, PreparedStatement pst) throws SQLException {
        while (details.next()) {
            ImageView newShuttle = new ImageView();
            newShuttle.setImage(sLocation.getImage());
            DriverMapController.spawnShuttles(newShuttle, sLocation, details, sPane, shuttles);

        }
        pst.close();
    }

    public void requestShuttle(ActionEvent actionEvent) {
        if (conn == null) {
            return;
        }
        rProgress.setVisible(true);
        bCancel.setDisable(false);
        try {
            sql = "update UserLocations set X = ? , Y = ? where ID = ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, uLocation.getTranslateX());
            pst.setDouble(2, uLocation.getTranslateY());
            pst.setDouble(3, ID);
            rs = pst.executeUpdate();
            if (rs != 1) {
                sql = "insert into UserLocations values (?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setDouble(2, uLocation.getTranslateX());
                pst.setDouble(3, uLocation.getTranslateY());
                pst.setDouble(1, ID);
                rs = pst.executeUpdate();
                if (rs != 1) {
                    JOptionPane.showMessageDialog(null, "Could not update your location");
                    rProgress.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Shuttle Requested!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Request location updated!");
            }
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }

    public void updateLocation(javafx.scene.input.MouseEvent event) {
        uLocation.setTranslateX(event.getSceneX() - 350);
        uLocation.setTranslateY(event.getSceneY() - 250);
    }

    public void cancelRequest(ActionEvent actionEvent) throws SQLException {
        if (conn == null) {
            return;
        }
        if (JOptionPane.showConfirmDialog(null, "Ae you sure you want to cancel request?", "Confirmation", JOptionPane.YES_NO_OPTION) == 1)
            return;
        try {
            String sql = "delete from UserLocations where ID = ?";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, ID);
            rs = pst.executeUpdate();
            if (rs != 1)
                JOptionPane.showMessageDialog(null, "Request already canceled");
            pst.close();
            rProgress.setVisible(false);
            bCancel.setDisable(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            conn.close();

        }
    }
}
