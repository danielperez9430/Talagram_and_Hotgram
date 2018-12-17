package okhttp3.internal.b;

import java.io.IOException;
import java.lang.reflect.Method;

public final class e extends RuntimeException {
    private static final Method a;
    private IOException b;

    static {
        Method v0;
        try {
            v0 = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        }
        catch(Exception ) {
            v0 = null;
        }

        e.a = v0;
    }

    public e(IOException arg1) {
        super(((Throwable)arg1));
        this.b = arg1;
    }

    public void a(IOException arg2) {
        this.a(arg2, this.b);
        this.b = arg2;
    }

    private void a(IOException arg4, IOException arg5) {
        if(e.a != null) {
            try {
                e.a.invoke(arg4, arg5);
                return;
            }
            catch(IllegalAccessException ) {
                return;
            }
        }
    }

    public IOException a() {
        return this.b;
    }
}

