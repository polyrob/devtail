package com.robbomb.devtail.io;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Rob on 8/28/2016.
 */
public class SettingsLoaderTest {

    @Test
    public void loadTest() {
        Settings settings = SettingsLoader.load("settings.xml");
        assertNotNull(settings);
        assertNotNull(settings.getFiles());
        assertEquals(settings.getFiles().size(), 2);

    }
}
