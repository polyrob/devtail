package com.robbomb.devtail.digest;

import com.robbomb.devtail.model.LogRow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 * Created by Rob on 8/28/2016.
 */
public class TableProcessor implements LineProcessor {

    ObservableList<LogRow> data;

    public TableProcessor(ObservableList<LogRow> data) {
        this.data = data;
    }

    @Override
    public void process(String filename, String line) {
        String[] split = line.split("\\|");
        LogRow row = new LogRow(new SimpleStringProperty(filename), new SimpleStringProperty(split[0]), new SimpleStringProperty(split[split.length-1]));
        data.add(row);
    }
}
