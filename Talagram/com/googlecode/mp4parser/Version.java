package com.googlecode.mp4parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.logging.Logger;

public class Version {
    private static final Logger LOG;
    public static final String VERSION;

    static {
        String v0_2;
        Version.LOG = Logger.getLogger(Version.class.getName());
        LineNumberReader v0 = new LineNumberReader(new InputStreamReader(Version.class.getResourceAsStream("/version.txt")));
        try {
            v0_2 = v0.readLine();
        }
        catch(IOException v0_1) {
            Version.LOG.warning(v0_1.getMessage());
            v0_2 = "unknown";
        }

        Version.VERSION = v0_2;
    }

    public Version() {
        super();
    }
}

