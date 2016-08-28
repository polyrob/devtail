package com.wellsfargo.devtail.io;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Rob on 8/28/2016.
 */
@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Settings {

    @XmlElement(name = "files")
    private LogFiles logFiles = null;




    public LogFiles getLogFiles() {
        return logFiles;
    }

    public void setLogFiles(LogFiles logFiles) {
        this.logFiles = logFiles;
    }
}
