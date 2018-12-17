package org.linphone.mediastream;

public final class Log {
    private static Log logger;

    private Log() {
        super();
    }

    @Deprecated public Log(String arg1, boolean arg2) {
        super();
    }

    public static void d(Object[] arg1) {
        Log.instance().d(Log.toString(arg1));
    }

    private native void d(String arg1) {
    }

    public static void e(Object[] arg1) {
        Log.instance().e(Log.toString(arg1));
    }

    private native void e(String arg1) {
    }

    public static void i(Object[] arg1) {
        Log.instance().i(Log.toString(arg1));
    }

    private native void i(String arg1) {
    }

    private static Log instance() {
        if(Log.logger == null) {
            Log.logger = new Log();
        }

        return Log.logger;
    }

    private static String toString(Object[] arg4) {
        StringBuilder v0 = new StringBuilder();
        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.append(arg4[v2]);
        }

        return v0.toString();
    }

    public static void w(Object[] arg1) {
        Log.instance().w(Log.toString(arg1));
    }

    private native void w(String arg1) {
    }
}

