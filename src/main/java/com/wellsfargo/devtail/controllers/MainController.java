package com.wellsfargo.devtail.controllers;

import com.wellsfargo.devtail.io.Settings;
import com.wellsfargo.devtail.io.SettingsLoader;
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

    private Settings settings;

    @FXML private Button btnTail;

    @FXML
    public void initialize() {
        logger.info("Initializing MainController...");

        try {
            settings = SettingsLoader.load("settings.xml");
        }catch (Exception e) {
            logger.error("Error loading settings, ", e);
        }
    }

    @FXML
    protected void handleTailButtonAction(ActionEvent event) {
        logger.info("Tail Button clicked.");
    }
}
