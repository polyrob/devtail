package com.robbomb.devtail.io;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Rob on 8/28/2016.
 */
@XmlRootElement(name = "logFile")
@XmlAccessorType(XmlAccessType.FIELD)
public class LogFile {
    private String name;
    private String path;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
