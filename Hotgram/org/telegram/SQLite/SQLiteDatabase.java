package org.telegram.SQLite;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class SQLiteDatabase {
    private final long a;
    private boolean b;
    private boolean c;

    public SQLiteDatabase(String arg3) {
        super();
        this.a = this.opendb(arg3, ApplicationLoader.getFilesDirFixed().getPath());
        this.b = true;
    }

    public SQLitePreparedStatement a(String arg3) {
        return new SQLitePreparedStatement(this, arg3, true);
    }

    public Integer a(String arg1, Object[] arg2) {
        Integer v2_1;
        this.c();
        SQLiteCursor v1 = this.b(arg1, arg2);
        try {
            if(v1.a()) {
                goto label_8;
            }
        }
        catch(Throwable v2) {
            goto label_13;
        }

        v1.b();
        return null;
        try {
        label_8:
            v2_1 = Integer.valueOf(v1.b(0));
        }
        catch(Throwable v2) {
        label_13:
            v1.b();
            throw v2;
        }

        v1.b();
        return v2_1;
    }

    public long a() {
        return this.a;
    }

    public void b() {
        if(this.b) {
            try {
                this.e();
                this.closedb(this.a);
            }
            catch(a v0) {
                if(!BuildVars.LOGS_ENABLED) {
                    goto label_11;
                }

                FileLog.e(v0.getMessage(), ((Throwable)v0));
            }

        label_11:
            this.b = false;
        }
    }

    public SQLiteCursor b(String arg3, Object[] arg4) {
        this.c();
        return new SQLitePreparedStatement(this, arg3, true).a(arg4);
    }

    native void beginTransaction(long arg1) {
    }

    void c() {
        if(this.b) {
            return;
        }

        throw new a("Database closed");
    }

    native void closedb(long arg1) {
    }

    native void commitTransaction(long arg1) {
    }

    public void d() {
        if(!this.c) {
            this.c = true;
            this.beginTransaction(this.a);
            return;
        }

        throw new a("database already in transaction");
    }

    public void e() {
        if(!this.c) {
            return;
        }

        this.c = false;
        this.commitTransaction(this.a);
    }

    public void finalize() {
        super.finalize();
        this.b();
    }

    native long opendb(String arg1, String arg2) {
    }
}

