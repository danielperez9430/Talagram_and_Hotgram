package c.a.a.a.a.d;

import android.content.Context;

public class i implements Runnable {
    private final Context a;
    private final e b;

    public i(Context arg1, e arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public void run() {
        try {
            c.a.a.a.a.b.i.a(this.a, "Performing time based file roll over.");
            if(this.b.c()) {
                return;
            }

            this.b.d();
        }
        catch(Exception v0) {
            c.a.a.a.a.b.i.a(this.a, "Failed to roll over file", ((Throwable)v0));
        }
    }
}

