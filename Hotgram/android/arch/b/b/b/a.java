package android.arch.b.b.b;

public class a {
    public static final String[] a;

    static {
        a.a = new String[0];
    }

    public static StringBuilder a() {
        return new StringBuilder();
    }

    public static void a(StringBuilder arg2, int arg3) {
        int v0;
        for(v0 = 0; v0 < arg3; ++v0) {
            arg2.append("?");
            if(v0 < arg3 - 1) {
                arg2.append(",");
            }
        }
    }
}

