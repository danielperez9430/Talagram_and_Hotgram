package android.support.v4.f;

public class l {
    public static Object a(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static int a(int arg0) {
        if(arg0 >= 0) {
            return arg0;
        }

        throw new IllegalArgumentException();
    }

    public static Object a(Object arg0, Object arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(String.valueOf(arg1));
    }
}

