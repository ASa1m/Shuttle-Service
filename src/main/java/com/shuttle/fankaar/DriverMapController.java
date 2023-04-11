package com.shuttle.fankaar;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverMapController {

    public StackPane sPane;
    public ImageView sLocation1;
    Connection conn;
    int rs;
    PreparedStatement pst;
    double ID;
    ResultSet details;
    @FXML
    private Button bReload;
    @FXML
    private ImageView mapImage;
    @FXML
    private ImageView sLocation;
    private List<ImageView> shuttles = new ArrayList<>();
    private List<ImageView> users = new ArrayList<>();

    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            Runnable updater = () -> {
                sPane.getChildren().clear();
                sPane.getChildren().add(mapImage);
                showShuttles();
                showUsers();
            };

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

                // UI update is run on the Application thread
                Platform.runLater(updater);
            }
        }

    });

    public DriverMapController() {
        conn = DbConnection.ConnectDb();
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
    }

    static void spawnShuttles(ImageView newShuttle, ImageView sLocation, ResultSet details, StackPane sPane, List<ImageView> shuttles) throws SQLException {
        newShuttle.setFitHeight(sLocation.getFitHeight());
        newShuttle.setFitWidth(sLocation.getFitWidth());
        newShuttle.setPickOnBounds(true);
        newShuttle.setPreserveRatio(true);
        newShuttle.setTranslateX(details.getDouble("X"));
        newShuttle.setTranslateY(details.getDouble("Y"));
        sPane.getChildren().add(newShuttle);
        shuttles.add(newShuttle);
    }

    public double getID() {
        return ID;
    }
    public void setID(double ID) {
        this.ID = ID;
    }

    @FXML
    void updateLocation(MouseEvent event) throws SQLException {
        sLocation.setTranslateX(event.getX()-329);
        sLocation.setTranslateY(event.getY()-218);

        try{
            String sql = "update ShuttleLocations set X = ? , Y = ? where ID = ?";
            pst=conn.prepareStatement(sql);
            pst.setDouble(1,sLocation.getTranslateX());
            System.out.println(sLocation.getTranslateX());
            pst.setDouble(2,sLocation.getTranslateY());
            pst.setDouble(3,ID);
            rs=pst.executeUpdate();
            if(rs!=1)
                JOptionPane.showMessageDialog(null, "Could not update Location");
            pst.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            conn.close();

        }
    }

    public void showUsers() {
        try{
            String sql="select * from UserLocations";
            pst=conn.prepareStatement(sql);
            details = pst.executeQuery();
            users.clear();
            UserMapController.spawnPins(details, sLocation1, sPane, shuttles, pst);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
            try {
                conn.close();
            } catch (SQLException exc) {
                throw new RuntimeException(exc);
            }

        }
    }

    public void showShuttles() {
        try{
            String sql="select * from ShuttleLocations where status = 1";
            pst=conn.prepareStatement(sql);
            details = pst.executeQuery();
            shuttles.clear();
            sPane.getChildren().add(sLocation);
            while (details.next()) {
                ImageView newShuttle = new ImageView();
                newShuttle.setImage(sLocation.getImage());
                newShuttle.setX(sLocation.getX());
                newShuttle.setY(sLocation.getY());
                spawnShuttles(newShuttle, sLocation, details, sPane, shuttles);

            }
            pst.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
            try {
                conn.close();
            } catch (SQLException exc) {
                throw new RuntimeException(exc);
            }

        }
    }

}
