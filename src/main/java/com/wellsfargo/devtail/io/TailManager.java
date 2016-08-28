package com.wellsfargo.devtail.io;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Rob on 8/28/2016.
 */
public class TailManager {
    private static final Logger logger = LogManager.getLogger(TailManager.class);
    public static final int DELAY_MILLIS = 1000;

    private LogFiles logFiles;
    private List<Tailer> tailers;
    private volatile boolean stopRequested = false;

    public TailManager(LogFiles logFiles) {
        this.logFiles = logFiles;
    }


//    public void start() {
//        List<BufferedReader> readers = new ArrayList<>();
//
//        for (LogFile logFile : logFiles.getLogFiles()) {
//            try {
//                FileReader fileReader = new FileReader(logFile.getPath());
//                BufferedReader reader = new BufferedReader(fileReader);
//                readers.add(reader);
//                fileReader.
//
//            } catch (FileNotFoundException e) {
//                logger.error("Could not create BufferedReader for file: " + logFile.getPath(), e);
//            }
//            logger.info("BufferedReaders created for " + readers.size() + " files.");
//        }
//
//        // Skip to heads of files
//
//        try {
//            while (!stopRequested) {
//                for (BufferedReader reader : readers) {
//                    String line = reader.readLine();
//                    if (line != null) {
//                        // process that line
//                        logger.info(line);
//                    }
//                }
//                Thread.sleep(1000); // sleep only once in polling loop
//            }
//        } catch (Exception e) {
//            logger.error("Exception polling file", e);
//        }
//    }

    /**
     *  Apache Tailer implementation
     */
    public void start() {
        // create Tailer Listener
        LogTailerListener listener = new LogTailerListener();

        // create tailers for each file
        tailers = new ArrayList<>();
        for (LogFile logFile : logFiles.getLogFiles()) {
            File file = new File(logFile.getPath());
            Tailer tailer = new Tailer(file, listener, DELAY_MILLIS, true);
            tailers.add(tailer);
        }

        Executor executor = new Executor() {
            public void execute(Runnable command) {
                command.run();
            }
        };

        for (Tailer tailer : tailers) {
            logger.info("Executing tailer", tailer);
            executor.execute(tailer);
        }
    }


    public void stop() {
        for (Tailer tailer : tailers) {
            logger.info("Stopping tailer", tailer);
            tailer.stop();
        }
    }

    public boolean isStopRequested() {
        return stopRequested;
    }

    public void setStopRequested(boolean stopRequested) {
        this.stopRequested = stopRequested;
    }


    private class LogTailerListener extends TailerListenerAdapter {
        @Override
        public void handle(String line) {
            super.handle(line);
            logger.info(line);
        }
    }
}
