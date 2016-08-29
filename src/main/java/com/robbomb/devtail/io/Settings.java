package com.robbomb.devtail.io;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Rob on 8/28/2016.
 */
@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Settings {

    @XmlElement(name = "files")
    private LogFiles files = null;




    public List<LogFile> getFiles() {
        return files.getLogFiles();
    }

    public void setFiles(LogFiles files) {
        this.files = files;
    }
}
