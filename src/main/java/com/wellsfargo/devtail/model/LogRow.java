package com.wellsfargo.devtail.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Rob on 8/28/2016.
 */
public class LogRow {

    private final SimpleStringProperty file;
    private final SimpleStringProperty datetime;
    private final SimpleStringProperty guts;

    public LogRow(SimpleStringProperty file, SimpleStringProperty datetime, SimpleStringProperty guts) {
        this.file = file;
        this.datetime = datetime;
        this.guts = guts;
    }

    public String getFile() {
        return file.get();
    }

    public SimpleStringProperty fileProperty() {
        return file;
    }

    public void setFile(String file) {
        this.file.set(file);
    }

    public String getDatetime() {
        return datetime.get();
    }

    public SimpleStringProperty datetimeProperty() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime.set(datetime);
    }

    public String getGuts() {
        return guts.get();
    }

    public SimpleStringProperty gutsProperty() {
        return guts;
    }

    public void setGuts(String guts) {
        this.guts.set(guts);
    }
}
