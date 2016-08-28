package com.wellsfargo.devtail.controllers;

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

    @FXML private Button btnTail;


    @FXML
    protected void handleTailButtonAction(ActionEvent event) {
        logger.info("Tail Button clicked.");
    }
}
