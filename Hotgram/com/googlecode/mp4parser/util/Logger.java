package com.googlecode.mp4parser.util;

public abstract class Logger {
    public Logger() {
        super();
    }

    public static Logger getLogger(Class arg2) {
        if(System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik")) {
            return new AndroidLogger(arg2.getSimpleName());
        }

        return new JuliLogger(arg2.getSimpleName());
    }

    public abstract void logDebug(String arg1);

    public abstract void logError(String arg1);

    public abstract void logWarn(String arg1);
}

