package com.robbomb.devtail.io;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Rob on 8/28/2016.
 */
@XmlRootElement(name = "files")
@XmlAccessorType(XmlAccessType.FIELD)
public class LogFiles {

    @XmlElement(name = "logFile")
    private List<LogFile> logFiles = null;



    public List<LogFile> getLogFiles() {
        return logFiles;
    }

    public void setLogFiles(List<LogFile> logFiles) {
        this.logFiles = logFiles;
    }
}
