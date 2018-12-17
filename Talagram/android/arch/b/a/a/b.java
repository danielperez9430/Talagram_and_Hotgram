package android.arch.b.a.a;

import android.arch.b.a.c;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class b implements c {
    class a extends SQLiteOpenHelper {
        final android.arch.b.a.a.a[] a;
        final android.arch.b.a.c$a b;
        private boolean c;

        a(Context arg7, String arg8, android.arch.b.a.a.a[] arg9, android.arch.b.a.c$a arg10) {
            super(arg7, arg8, null, arg10.a, new DatabaseErrorHandler(arg10) {
                public void onCorruption(SQLiteDatabase arg2) {
                    android.arch.b.a.a.a v2 = this.a[0];
                    if(v2 != null) {
                        this.b.d(((android.arch.b.a.b)v2));
                    }
                }
            });
            this.b = arg10;
            this.a = arg9;
        }

        android.arch.b.a.b a() {
            android.arch.b.a.a.a v0_3;
            android.arch.b.a.b v0_2;
            SQLiteDatabase v0_1;
            __monitor_enter(this);
            try {
                this.c = false;
                v0_1 = super.getWritableDatabase();
                if(!this.c) {
                    goto label_10;
                }

                this.close();
                v0_2 = this.a();
            }
            catch(Throwable v0) {
                goto label_14;
            }

            __monitor_exit(this);
            return v0_2;
            try {
            label_10:
                v0_3 = this.a(v0_1);
            }
            catch(Throwable v0) {
            label_14:
                __monitor_exit(this);
                throw v0;
            }

            __monitor_exit(this);
            return ((android.arch.b.a.b)v0_3);
        }

        android.arch.b.a.a.a a(SQLiteDatabase arg3) {
            if(this.a[0] == null) {
                this.a[0] = new android.arch.b.a.a.a(arg3);
            }

            return this.a[0];
        }

        public void close() {
            __monitor_enter(this);
            try {
                super.close();
                this.a[0] = null;
            }
            catch(Throwable v0) {
                __monitor_exit(this);
                throw v0;
            }

            __monitor_exit(this);
        }

        public void onConfigure(SQLiteDatabase arg2) {
            this.b.a(this.a(arg2));
        }

        public void onCreate(SQLiteDatabase arg2) {
            this.b.b(this.a(arg2));
        }

        public void onDowngrade(SQLiteDatabase arg2, int arg3, int arg4) {
            this.c = true;
            this.b.b(this.a(arg2), arg3, arg4);
        }

        public void onOpen(SQLiteDatabase arg2) {
            if(!this.c) {
                this.b.c(this.a(arg2));
            }
        }

        public void onUpgrade(SQLiteDatabase arg2, int arg3, int arg4) {
            this.c = true;
            this.b.a(this.a(arg2), arg3, arg4);
        }
    }

    private final a a;

    b(Context arg1, String arg2, android.arch.b.a.c$a arg3) {
        super();
        this.a = this.a(arg1, arg2, arg3);
    }

    private a a(Context arg3, String arg4, android.arch.b.a.c$a arg5) {
        return new a(arg3, arg4, new android.arch.b.a.a.a[1], arg5);
    }

    public android.arch.b.a.b a() {
        return this.a.a();
    }

    public void a(boolean arg2) {
        this.a.setWriteAheadLoggingEnabled(arg2);
    }
}

