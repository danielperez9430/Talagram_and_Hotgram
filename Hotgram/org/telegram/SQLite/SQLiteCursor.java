package org.telegram.SQLite;

import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.NativeByteBuffer;

public class SQLiteCursor {
    private SQLitePreparedStatement a;
    private boolean b;

    public SQLiteCursor(SQLitePreparedStatement arg2) {
        super();
        this.b = false;
        this.a = arg2;
    }

    public boolean a() {
        int v0 = this.a.step(this.a.a());
        int v1 = -1;
        if(v0 == v1) {
            int v2 = 6;
            while(true) {
                int v3 = v2 - 1;
                if(v2 != 0) {
                    try {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("sqlite busy, waiting...");
                        }

                        Thread.sleep(500);
                        v2 = this.a.b();
                        if(v2 != 0) {
                            goto label_20;
                        }
                    }
                    catch(Exception v2_1) {
                        FileLog.e(((Throwable)v2_1));
                        goto label_24;
                    }

                    v0 = v2;
                    break;
                label_20:
                    v0 = v2;
                label_24:
                    v2 = v3;
                    continue;
                }

                break;
            }

            if(v0 != v1) {
            }
            else {
                throw new a("sqlite busy");
            }
        }

        boolean v0_1 = v0 == 0 ? true : false;
        this.b = v0_1;
        return this.b;
    }

    public boolean a(int arg3) {
        this.c();
        boolean v0 = true;
        if(this.columnIsNull(this.a.a(), arg3) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public int b(int arg3) {
        this.c();
        return this.columnIntValue(this.a.a(), arg3);
    }

    public void b() {
        this.a.e();
    }

    void c() {
        if(this.b) {
            return;
        }

        throw new a("You must call next before");
    }

    public double c(int arg3) {
        this.c();
        return this.columnDoubleValue(this.a.a(), arg3);
    }

    native byte[] columnByteArrayValue(long arg1, int arg2) {
    }

    native long columnByteBufferValue(long arg1, int arg2) {
    }

    native double columnDoubleValue(long arg1, int arg2) {
    }

    native int columnIntValue(long arg1, int arg2) {
    }

    native int columnIsNull(long arg1, int arg2) {
    }

    native long columnLongValue(long arg1, int arg2) {
    }

    native String columnStringValue(long arg1, int arg2) {
    }

    native int columnType(long arg1, int arg2) {
    }

    public long d(int arg3) {
        this.c();
        return this.columnLongValue(this.a.a(), arg3);
    }

    public String e(int arg3) {
        this.c();
        return this.columnStringValue(this.a.a(), arg3);
    }

    public byte[] f(int arg3) {
        this.c();
        return this.columnByteArrayValue(this.a.a(), arg3);
    }

    public NativeByteBuffer g(int arg5) {
        this.c();
        long v0 = this.columnByteBufferValue(this.a.a(), arg5);
        if(v0 != 0) {
            return NativeByteBuffer.wrap(v0);
        }

        return null;
    }
}

