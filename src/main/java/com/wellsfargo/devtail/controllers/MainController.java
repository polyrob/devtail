package com.wellsfargo.devtail.controllers;

import com.wellsfargo.devtail.io.Settings;
import com.wellsfargo.devtail.io.SettingsLoader;
import com.wellsfargo.devtail.io.TailManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by Rob on 8/28/2016.
 */
public class MainController {
    private static final Logger logger = LogManager.getLogger(MainController.class);

    private TailManager tailManager;
    private Settings settings;


    @FXML
    public void initialize() {
        logger.info("Initializing MainController...");

        try {
            settings = SettingsLoader.load("settings.xml");
            tailManager = new TailManager(settings.getLogFiles());
        }catch (Exception e) {
            logger.error("Error loading settings, ", e);
        }
    }

    @FXML
    protected void handleTailButtonAction(ActionEvent event) {
        logger.info("Tail Button clicked.");
        tailManager.start();
    }

    @FXML
    protected void handleStopButtonAction(ActionEvent event) {
        logger.info("Stop Button clicked.");
        tailManager.stop();
    }
}
