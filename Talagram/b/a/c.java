package b.a;

public final class c {
    public static Object a(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static Object a(Object arg0, String arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(arg1);
    }
}

