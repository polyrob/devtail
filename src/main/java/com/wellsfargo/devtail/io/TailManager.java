package com.wellsfargo.devtail.io;

import com.wellsfargo.devtail.digest.LineProcessor;
import javafx.beans.property.SimpleBooleanProperty;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rob on 8/28/2016.
 */
public class TailManager {
    private static final Logger logger = LogManager.getLogger(TailManager.class);
    private static final int DELAY_MILLIS = 1000;

    private LineProcessor processor;

    private List<LogFile> logFiles;
    private List<Tailer> tailers = new ArrayList<>();
    private final Object lock = new Object();

    private SimpleBooleanProperty tailing = new SimpleBooleanProperty(false);


    public TailManager(LineProcessor processor, List<LogFile> logFiles) {
        this.processor = processor;
        this.logFiles = logFiles;
    }


    /**
     * Apache Tailer implementation
     */
    public void start() {
        synchronized (lock) {
            // check tailers running to be safe and prevent memory/thread leak
            if (!tailers.isEmpty()) {
                logger.error("Tailers may still be running. Stopping first");
                return;
            }

            tailing.set(true);

            // create tailers for each file
            for (LogFile logFile : logFiles) {
                File file = new File(logFile.getPath());
                LogTailerListener listener = new LogTailerListener(logFile.getName());
                Tailer tailer = new Tailer(file, listener, DELAY_MILLIS, true);
                tailers.add(tailer);
            }
            logger.info("Tailers created for " + tailers.size() + " files.");


            for (Tailer tailer : tailers) {
                logger.debug("Starting tailer " + tailer.getFile());
                Thread thread = new Thread(tailer);
                thread.start();
            }

            logger.info("Tail startup completed.");
        }
    }


    public void stop() {
        synchronized (lock) {
            Iterator<Tailer> itr = tailers.iterator();
            while (itr.hasNext()) {
                Tailer tailer = itr.next();
                logger.debug("Stopping tailer " + tailer.getFile());
                tailer.stop();
                itr.remove();
            }
        }
        tailing.set(false);
    }


    private class LogTailerListener extends TailerListenerAdapter {
        private final String filename;

        public LogTailerListener(String filename) {
            this.filename = filename;
        }

        @Override
        public void handle(String line) {
            super.handle(line);
            logger.info(line);
            processor.process(filename, line);
        }

        @Override
        public void handle(Exception e) {
            super.handle(e);
            logger.error("Exception occurred in LogTailerListener", e);
        }
    }


    public SimpleBooleanProperty tailingProperty() {
        return tailing;
    }
}
