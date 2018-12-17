package androidx.work.impl.b;

import android.arch.b.b.b;
import android.arch.b.b.h;
import android.arch.b.b.i;
import android.database.Cursor;

public class f implements e {
    private final android.arch.b.b.e a;
    private final b b;
    private final i c;

    public f(android.arch.b.b.e arg2) {
        super();
        this.a = arg2;
        this.b = new b(arg2) {
            public String a() {
                return "INSERT OR REPLACE INTO `SystemIdInfo`(`work_spec_id`,`system_id`) VALUES (?,?)";
            }

            public void a(android.arch.b.a.f arg4, d arg5) {
                if(arg5.a == null) {
                    arg4.a(1);
                }
                else {
                    arg4.a(1, arg5.a);
                }

                arg4.a(2, ((long)arg5.b));
            }

            public void a(android.arch.b.a.f arg1, Object arg2) {
                this.a(arg1, ((d)arg2));
            }
        };
        this.c = new i(arg2) {
            public String a() {
                return "DELETE FROM SystemIdInfo where work_spec_id=?";
            }
        };
    }

    public d a(String arg5) {
        d v3;
        h v0 = h.a("SELECT * FROM SystemIdInfo WHERE work_spec_id=?", 1);
        if(arg5 == null) {
            v0.a(1);
        }
        else {
            v0.a(1, arg5);
        }

        Cursor v5 = this.a.a(((android.arch.b.a.e)v0));
        try {
            int v1_1 = v5.getColumnIndexOrThrow("work_spec_id");
            int v2 = v5.getColumnIndexOrThrow("system_id");
            if(v5.moveToFirst()) {
                v3 = new d(v5.getString(v1_1), v5.getInt(v2));
            }
            else {
                goto label_20;
            }

            goto label_21;
        }
        catch(Throwable v1) {
            v5.close();
            v0.b();
            throw v1;
        }

    label_20:
        v3 = null;
    label_21:
        v5.close();
        v0.b();
        return v3;
    }

    public void a(d arg2) {
        this.a.f();
        try {
            this.b.a(arg2);
            this.a.h();
        }
        catch(Throwable v2) {
            this.a.g();
            throw v2;
        }

        this.a.g();
    }

    public void b(String arg3) {
        android.arch.b.a.f v0 = this.c.c();
        this.a.f();
        if(arg3 == null) {
            try {
                v0.a(1);
                goto label_11;
            label_10:
                v0.a(1, arg3);
            label_11:
                v0.a();
                this.a.h();
                goto label_14;
            label_9:
                goto label_19;
            }
            catch(Throwable v3) {
                goto label_9;
            }
        }
        else {
            goto label_10;
        }

        goto label_11;
    label_19:
        this.a.g();
        this.c.a(v0);
        throw v3;
    label_14:
        this.a.g();
        this.c.a(v0);
    }
}

