package com.persianswitch.sdk.base.log;

public final class SDKLog {
    private static ILogger a;
    private static int b;

    static {
        SDKLog.a = new MemoryLogCatLogger();
        SDKLog.b = 6;
    }

    public SDKLog() {
        super();
    }

    private static void a(int arg7, String arg8, String arg9, Throwable arg10, Object[] arg11) {
        if(SDKLog.a(arg7)) {
            try {
                SDKLog.a.a(arg7, arg8, arg9, arg10, arg11);
            }
            catch(Exception v7) {
                v7.printStackTrace();
            }
        }
    }

    private static boolean a(int arg1) {
        boolean v1 = arg1 <= SDKLog.b ? true : false;
        return v1;
    }

    public static void a(String arg1, String arg2, Throwable arg3, Object[] arg4) {
        SDKLog.a(3, arg1, arg2, arg3, arg4);
    }

    public static void a(String arg2, String arg3, Object[] arg4) {
        SDKLog.a(4, arg2, arg3, null, arg4);
    }

    public static void b(String arg1, String arg2, Throwable arg3, Object[] arg4) {
        SDKLog.a(6, arg1, arg2, arg3, arg4);
    }

    public static void b(String arg2, String arg3, Object[] arg4) {
        SDKLog.a(3, arg2, arg3, null, arg4);
    }

    public static void c(String arg2, String arg3, Object[] arg4) {
        SDKLog.a(6, arg2, arg3, null, arg4);
    }
}

