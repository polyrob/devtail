package com.wellsfargo.devtail.controllers;

import com.wellsfargo.devtail.digest.LineProcessor;
import com.wellsfargo.devtail.digest.TableProcessor;
import com.wellsfargo.devtail.io.Settings;
import com.wellsfargo.devtail.io.SettingsLoader;
import com.wellsfargo.devtail.io.TailManager;
import com.wellsfargo.devtail.model.LogRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by Rob on 8/28/2016.
 */
public class MainController {
    private static final Logger logger = LogManager.getLogger(MainController.class);

    private TailManager tailManager;

    private final ObservableList<LogRow> data = FXCollections.observableArrayList();

    @FXML private TableView<LogRow> logtable;
    @FXML public Button btnTail;
    @FXML public Button btnStop;

    @FXML
    public void initialize() {
        logger.info("Initializing MainController...");

        try {
            Settings settings = SettingsLoader.load("settings.xml");
            LineProcessor processor = new TableProcessor(data);
            logtable.setItems(data);
            tailManager = new TailManager(processor, settings.getFiles());

            // bind buttons
            btnTail.disableProperty().bind(tailManager.tailingProperty());
            btnStop.disableProperty().bind(tailManager.tailingProperty().not());

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

    @FXML
    protected void handleClearButtonAction(ActionEvent event) {
        logger.info("Clear Button clicked.");
        logtable.getSelectionModel().clearSelection();
        logtable.getItems().clear();
    }
}
