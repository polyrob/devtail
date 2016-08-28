package com.wellsfargo.devtail.io;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Rob on 8/28/2016.
 */
public class SettingsLoaderTest {

    @Test
    public void loadTest() {
        Settings settings = SettingsLoader.load("settings.xml");
        assertNotNull(settings);
    }
}
