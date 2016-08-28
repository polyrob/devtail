package com.wellsfargo.devtail.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Rob on 8/28/2016.
 */
public class SettingsLoader {

    public static Settings load(String path) {
        Settings settings = null;
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            settings = (Settings) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return settings;
    }
}
