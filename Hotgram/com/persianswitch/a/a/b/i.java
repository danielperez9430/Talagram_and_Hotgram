package com.persianswitch.a.a.b;

public final class i {
    public static boolean a(String arg1) {
        boolean v1 = (arg1.equals("POST")) || (arg1.equals("PATCH")) || (arg1.equals("PUT")) || (arg1.equals("DELETE")) || (arg1.equals("MOVE")) ? true : false;
        return v1;
    }

    public static boolean b(String arg1) {
        boolean v1 = (arg1.equals("POST")) || (arg1.equals("PUT")) || (arg1.equals("PATCH")) || (arg1.equals("PROPPATCH")) || (arg1.equals("REPORT")) ? true : false;
        return v1;
    }

    public static boolean c(String arg1) {
        boolean v1 = (i.b(arg1)) || (arg1.equals("OPTIONS")) || (arg1.equals("DELETE")) || (arg1.equals("PROPFIND")) || (arg1.equals("MKCOL")) || (arg1.equals("LOCK")) ? true : false;
        return v1;
    }

    public static boolean d(String arg1) {
        return arg1.equals("PROPFIND") ^ 1;
    }
}

