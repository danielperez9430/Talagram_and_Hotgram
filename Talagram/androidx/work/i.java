package androidx.work;

import java.util.List;

public abstract class i {
    public i() {
        super();
    }

    public static i a(String arg4) {
        try {
            return Class.forName(arg4).newInstance();
        }
        catch(Exception v0) {
            j.e("InputMerger", "Trouble instantiating + " + arg4, new Throwable[]{v0});
            return null;
        }
    }

    public abstract e a(List arg1);
}

