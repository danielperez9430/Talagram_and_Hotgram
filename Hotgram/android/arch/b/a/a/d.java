package android.arch.b.a.a;

import android.database.sqlite.SQLiteProgram;

class d implements android.arch.b.a.d {
    private final SQLiteProgram a;

    d(SQLiteProgram arg1) {
        super();
        this.a = arg1;
    }

    public void a(int arg2) {
        this.a.bindNull(arg2);
    }

    public void a(int arg2, double arg3) {
        this.a.bindDouble(arg2, arg3);
    }

    public void a(int arg2, long arg3) {
        this.a.bindLong(arg2, arg3);
    }

    public void a(int arg2, String arg3) {
        this.a.bindString(arg2, arg3);
    }

    public void a(int arg2, byte[] arg3) {
        this.a.bindBlob(arg2, arg3);
    }

    public void close() {
        this.a.close();
    }
}

