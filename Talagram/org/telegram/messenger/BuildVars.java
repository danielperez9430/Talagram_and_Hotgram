package org.telegram.messenger;

public class BuildVars {
    public static String APP_HASH = "3a041b61993d401a1671d21e5a760a5e";
    public static int APP_ID = 157342;
    public static int BUILD_VERSION = 1358;
    public static String BUILD_VERSION_STRING = "4.9.1";
    public static boolean CHECK_UPDATES = false;
    public static boolean DEBUG_PRIVATE_VERSION = false;
    public static boolean DEBUG_VERSION = false;
    public static boolean LOGS_ENABLED = false;
    public static String PLAYSTORE_APP_URL = "";

    static {
        if(ApplicationLoader.applicationContext != null) {
            BuildVars.LOGS_ENABLED = ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", 0).getBoolean("logsEnabled", BuildVars.DEBUG_VERSION);
        }
    }

    public BuildVars() {
        super();
    }
}

