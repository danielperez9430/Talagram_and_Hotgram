package android.support.v4.b;

public class a {
    public static int a(int arg0, int arg1, int arg2) {
        if(arg0 < arg1) {
            return arg1;
        }

        if(arg0 > arg2) {
            return arg2;
        }

        return arg0;
    }

    public static float a(float arg1, float arg2, float arg3) {
        if(arg1 < arg2) {
            return arg2;
        }

        if(arg1 > arg3) {
            return arg3;
        }

        return arg1;
    }
}

