package com.wellsfargo.devtail.io;

import com.wellsfargo.devtail.controllers.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Rob on 8/28/2016.
 */
public class SettingsLoader {
    private static final Logger logger = LogManager.getLogger(SettingsLoader.class);

    public static Settings load(String path) {
        logger.info("Loading settings...");
        Settings settings = null;
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            settings = (Settings) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            logger.error("Error loading settings, ", e);
        }
        return settings;
    }
}
