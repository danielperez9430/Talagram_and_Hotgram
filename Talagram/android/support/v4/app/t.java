package android.support.v4.app;

import android.arch.lifecycle.g;
import android.support.v4.content.b;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class t {
    public interface a {
        void a(b arg1);

        void a(b arg1, Object arg2);
    }

    public t() {
        super();
    }

    public static t a(g arg2) {
        return new LoaderManagerImpl(arg2, arg2.getViewModelStore());
    }

    public abstract void a();

    @Deprecated public abstract void a(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4);
}

