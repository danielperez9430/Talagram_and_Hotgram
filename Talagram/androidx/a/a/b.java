package androidx.a.a;

import java.util.concurrent.Executor;

enum b implements Executor {
    public static final enum b a;

    static {
        b.a = new b("INSTANCE", 0);
        b.b = new b[]{b.a};
    }

    private b(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public void execute(Runnable arg1) {
        arg1.run();
    }

    public String toString() {
        return "DirectExecutor";
    }

    public static b valueOf(String arg1) {
        return Enum.valueOf(b.class, arg1);
    }

    public static b[] values() {
        // Method was not decompiled
    }
}

