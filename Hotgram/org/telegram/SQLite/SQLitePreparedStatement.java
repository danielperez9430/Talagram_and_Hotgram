package org.telegram.SQLite;

import java.nio.ByteBuffer;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.NativeByteBuffer;

public class SQLitePreparedStatement {
    private boolean a;
    private long b;
    private boolean c;

    public SQLitePreparedStatement(SQLiteDatabase arg3, String arg4, boolean arg5) {
        super();
        this.a = false;
        this.c = arg5;
        this.b = this.prepare(arg3.a(), arg4);
    }

    public void a(int arg7, long arg8) {
        this.bindLong(this.b, arg7, arg8);
    }

    public void a(int arg3, int arg4) {
        this.bindInt(this.b, arg3, arg4);
    }

    public void a(int arg7, NativeByteBuffer arg8) {
        this.bindByteBuffer(this.b, arg7, arg8.buffer, arg8.limit());
    }

    public void a(int arg3, String arg4) {
        this.bindString(this.b, arg3, arg4);
    }

    public void a(int arg3) {
        this.bindNull(this.b, arg3);
    }

    public long a() {
        return this.b;
    }

    public SQLiteCursor a(Object[] arg9) {
        if(arg9 == null) {
            goto label_41;
        }

        this.f();
        this.reset(this.b);
        int v0 = 0;
        int v7 = 1;
        while(true) {
            if(v0 >= arg9.length) {
                goto label_38;
            }

            Object v1 = arg9[v0];
            if(v1 == null) {
                this.bindNull(this.b, v7);
            }
            else if((v1 instanceof Integer)) {
                this.bindInt(this.b, v7, ((Integer)v1).intValue());
            }
            else if((v1 instanceof Double)) {
                this.bindDouble(this.b, v7, ((Double)v1).doubleValue());
            }
            else if((v1 instanceof String)) {
                this.bindString(this.b, v7, ((String)v1));
            }
            else {
                break;
            }

            ++v7;
            ++v0;
        }

        throw new IllegalArgumentException();
    label_38:
        return new SQLiteCursor(this);
    label_41:
        throw new IllegalArgumentException();
    }

    public void a(int arg7, double arg8) {
        this.bindDouble(this.b, arg7, arg8);
    }

    public int b() {
        return this.step(this.b);
    }

    native void bindByteBuffer(long arg1, int arg2, ByteBuffer arg3, int arg4) {
    }

    native void bindDouble(long arg1, int arg2, double arg3) {
    }

    native void bindInt(long arg1, int arg2, int arg3) {
    }

    native void bindLong(long arg1, int arg2, long arg3) {
    }

    native void bindNull(long arg1, int arg2) {
    }

    native void bindString(long arg1, int arg2, String arg3) {
    }

    public SQLitePreparedStatement c() {
        this.step(this.b);
        return this;
    }

    public void d() {
        this.f();
        this.reset(this.b);
    }

    public void e() {
        if(this.c) {
            this.g();
        }
    }

    void f() {
        if(!this.a) {
            return;
        }

        throw new a("Prepared query finalized");
    }

    native void finalize(long arg1) {
    }

    public void g() {
        if(this.a) {
            return;
        }

        try {
            this.a = true;
            this.finalize(this.b);
        }
        catch(a v0) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e(v0.getMessage(), ((Throwable)v0));
        }
    }

    native long prepare(long arg1, String arg2) {
    }

    native void reset(long arg1) {
    }

    native int step(long arg1) {
    }
}

