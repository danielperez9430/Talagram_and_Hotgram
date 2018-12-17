package okhttp3.internal.c;

public final class f {
    public static boolean a(String arg1) {
        boolean v1 = (arg1.equals("POST")) || (arg1.equals("PATCH")) || (arg1.equals("PUT")) || (arg1.equals("DELETE")) || (arg1.equals("MOVE")) ? true : false;
        return v1;
    }

    public static boolean b(String arg1) {
        boolean v1 = (arg1.equals("POST")) || (arg1.equals("PUT")) || (arg1.equals("PATCH")) || (arg1.equals("PROPPATCH")) || (arg1.equals("REPORT")) ? true : false;
        return v1;
    }

    public static boolean c(String arg1) {
        boolean v1 = (arg1.equals("GET")) || (arg1.equals("HEAD")) ? false : true;
        return v1;
    }

    public static boolean d(String arg1) {
        return arg1.equals("PROPFIND");
    }

    public static boolean e(String arg1) {
        return arg1.equals("PROPFIND") ^ 1;
    }
}

