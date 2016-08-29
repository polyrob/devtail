package com.wellsfargo.devtail;/**
 * Created by rob on 8/27/16.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main extends Application {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting FXML application...");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            Scene scene = new Scene(root, 1280, 720);
            primaryStage.setTitle("Devtail");
            primaryStage.setScene(scene);

            primaryStage.setOnCloseRequest(event -> {
                logger.info("Stage is closing");
                primaryStage.close();
                System.exit(0);
            });


            primaryStage.show();
        } catch (IOException e) {
           logger.error("Error loading devtail, ", e);
        }
    }

}
