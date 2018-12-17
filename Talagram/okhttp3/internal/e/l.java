package okhttp3.internal.e;

import e.e;
import java.util.List;

public interface l {
    final class okhttp3.internal.e.l$1 implements l {
        okhttp3.internal.e.l$1() {
            super();
        }

        public void a(int arg1, b arg2) {
        }

        public boolean a(int arg1, e arg2, int arg3, boolean arg4) {
            arg2.h(((long)arg3));
            return 1;
        }

        public boolean a(int arg1, List arg2) {
            return 1;
        }

        public boolean a(int arg1, List arg2, boolean arg3) {
            return 1;
        }
    }

    public static final l a;

    static {
        l.a = new okhttp3.internal.e.l$1();
    }

    boolean a(int arg1, e arg2, int arg3, boolean arg4);

    boolean a(int arg1, List arg2);

    boolean a(int arg1, List arg2, boolean arg3);

    void a(int arg1, b arg2);
}

