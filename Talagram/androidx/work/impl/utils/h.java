package androidx.work.impl.utils;

import java.util.concurrent.Executor;

public class h implements Executor {
    public h() {
        super();
    }

    public void execute(Runnable arg1) {
        arg1.run();
    }
}

