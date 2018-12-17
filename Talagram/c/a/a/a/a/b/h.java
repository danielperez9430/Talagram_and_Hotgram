package c.a.a.a.a.b;

import android.os.Process;

public abstract class h implements Runnable {
    public h() {
        super();
    }

    protected abstract void a();

    public final void run() {
        Process.setThreadPriority(10);
        this.a();
    }
}

