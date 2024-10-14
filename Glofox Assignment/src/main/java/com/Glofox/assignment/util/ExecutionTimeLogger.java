package com.Glofox.assignment.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class ExecutionTimeLogger {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeLogger.class);
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    
    private long startTime;

    // Log the start of the method execution
    public void logMethodStart(String className, String methodName) {
        this.startTime = System.nanoTime(); // Capture start time
        logger.info("Entering method: {}.{}", className, methodName);
    }

    // Log the end of the method execution
    public void logMethodEnd(String className, String methodName) {
        long executionTime = System.nanoTime() - startTime;
        double executionTimeInSeconds = executionTime / 1_000_000_000.0; // Convert nanoseconds to seconds

        logger.info("Exiting method: {}.{} (Execution time: {} seconds)", 
                     className, methodName, decimalFormat.format(executionTimeInSeconds));
    }
}
