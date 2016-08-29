package com.wellsfargo.devtail.digest;

/**
 * Created by Rob on 8/28/2016.
 */
public interface LineProcessor {

    void process(String filename, String line);

}
