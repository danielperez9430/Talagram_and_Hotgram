package org.linphone.tools;

import org.linphone.core.LpConfig;
import org.linphone.mediastream.Log;

public class Lpc2Xml {
    class org.linphone.tools.Lpc2Xml$1 {
        static {
            org.linphone.tools.Lpc2Xml$1.$SwitchMap$org$linphone$tools$Lpc2Xml$LogLevel = new int[LogLevel.values().length];
            try {
                org.linphone.tools.Lpc2Xml$1.$SwitchMap$org$linphone$tools$Lpc2Xml$LogLevel[LogLevel.DEBUG.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.linphone.tools.Lpc2Xml$1.$SwitchMap$org$linphone$tools$Lpc2Xml$LogLevel[LogLevel.MESSAGE.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.linphone.tools.Lpc2Xml$1.$SwitchMap$org$linphone$tools$Lpc2Xml$LogLevel[LogLevel.WARNING.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.linphone.tools.Lpc2Xml$1.$SwitchMap$org$linphone$tools$Lpc2Xml$LogLevel[LogLevel.ERROR.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    enum LogLevel {
        public static final enum LogLevel DEBUG;
        public static final enum LogLevel ERROR;
        public static final enum LogLevel MESSAGE;
        public static final enum LogLevel WARNING;

        static {
            LogLevel.DEBUG = new LogLevel("DEBUG", 0);
            LogLevel.MESSAGE = new LogLevel("MESSAGE", 1);
            LogLevel.WARNING = new LogLevel("WARNING", 2);
            LogLevel.ERROR = new LogLevel("ERROR", 3);
            LogLevel.$VALUES = new LogLevel[]{LogLevel.DEBUG, LogLevel.MESSAGE, LogLevel.WARNING, LogLevel.ERROR};
        }

        private LogLevel(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static LogLevel valueOf(String arg1) {
            return Enum.valueOf(LogLevel.class, arg1);
        }

        public static LogLevel[] values() {
            // Method was not decompiled
        }
    }

    private long internalPtr;
    private static boolean mAvailable;

    static {
        try {
            System.loadLibrary("xml2");
            Lpc2Xml.mAvailable = true;
        }
        catch(Throwable ) {
            Lpc2Xml.mAvailable = false;
        }
    }

    public Lpc2Xml() {
        super();
        this.internalPtr = 0;
        this.init();
    }

    public native int convertFile(String arg1) {
    }

    public native int convertString(StringBuffer arg1) {
    }

    private native void destroy() {
    }

    public void finalize() {
        this.destroy();
    }

    private native void init() {
    }

    static boolean isAvailable() {
        return Lpc2Xml.mAvailable;
    }

    public void printLog(int arg3, String arg4) {
        if(arg3 > 0 && arg3 < LogLevel.values().length) {
            switch(org.linphone.tools.Lpc2Xml$1.$SwitchMap$org$linphone$tools$Lpc2Xml$LogLevel[LogLevel.values()[arg3].ordinal()]) {
                case 1: {
                    goto label_25;
                }
                case 2: {
                    goto label_21;
                }
                case 3: {
                    goto label_17;
                }
                case 4: {
                    goto label_13;
                }
            }

            return;
        label_17:
            Log.w(new Object[]{arg4});
            return;
        label_21:
            Log.i(new Object[]{arg4});
            return;
        label_25:
            Log.d(new Object[]{arg4});
            return;
        label_13:
            Log.e(new Object[]{arg4});
        }
    }

    public native int setLpc(LpConfig arg1) {
    }
}

