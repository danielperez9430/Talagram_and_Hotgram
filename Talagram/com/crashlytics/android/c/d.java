package com.crashlytics.android.c;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

class d extends FileOutputStream {
    final class com.crashlytics.android.c.d$1 implements FilenameFilter {
        com.crashlytics.android.c.d$1() {
            super();
        }

        public boolean accept(File arg1, String arg2) {
            return arg2.endsWith(".cls_temp");
        }
    }

    public static final FilenameFilter a;
    private final String b;
    private File c;
    private File d;
    private boolean e;

    static {
        d.a = new com.crashlytics.android.c.d$1();
    }

    public d(File arg4, String arg5) {
        StringBuilder v1 = new StringBuilder();
        v1.append(arg5);
        v1.append(".cls_temp");
        super(new File(arg4, v1.toString()));
        this.e = false;
        this.b = arg4 + File.separator + arg5;
        StringBuilder v5 = new StringBuilder();
        v5.append(this.b);
        v5.append(".cls_temp");
        this.c = new File(v5.toString());
    }

    public void a() {
        if(this.e) {
            return;
        }

        this.e = true;
        super.flush();
        super.close();
    }

    public void close() {
        File v0_1;
        __monitor_enter(this);
        try {
            if(!this.e) {
                goto label_6;
            }
        }
        catch(Throwable v0) {
            goto label_50;
        }

        __monitor_exit(this);
        return;
        try {
        label_6:
            this.e = true;
            super.flush();
            super.close();
            StringBuilder v1 = new StringBuilder();
            v1.append(this.b);
            v1.append(".cls");
            v0_1 = new File(v1.toString());
            if(!this.c.renameTo(v0_1)) {
                goto label_26;
            }

            this.c = null;
            this.d = v0_1;
        }
        catch(Throwable v0) {
            goto label_50;
        }

        __monitor_exit(this);
        return;
        try {
        label_26:
            String v1_1 = "";
            if(v0_1.exists()) {
                v1_1 = " (target already exists)";
            }
            else if(!this.c.exists()) {
                v1_1 = " (source does not exist)";
            }

            StringBuilder v3 = new StringBuilder();
            v3.append("Could not rename temp file: ");
            v3.append(this.c);
            v3.append(" -> ");
            v3.append(v0_1);
            v3.append(v1_1);
            throw new IOException(v3.toString());
        }
        catch(Throwable v0) {
        label_50:
            __monitor_exit(this);
            throw v0;
        }
    }
}

