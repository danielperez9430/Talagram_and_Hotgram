package androidx.work.impl.b;

import android.arch.b.a.f;
import android.arch.b.b.e;
import android.arch.b.b.h;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class c implements b {
    private final e a;
    private final android.arch.b.b.b b;

    public c(e arg2) {
        super();
        this.a = arg2;
        this.b = new android.arch.b.b.b(arg2) {
            public String a() {
                return "INSERT OR IGNORE INTO `Dependency`(`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
            }

            public void a(f arg3, a arg4) {
                if(arg4.a == null) {
                    arg3.a(1);
                }
                else {
                    arg3.a(1, arg4.a);
                }

                int v1 = 2;
                if(arg4.b == null) {
                    arg3.a(v1);
                }
                else {
                    arg3.a(v1, arg4.b);
                }
            }

            public void a(f arg1, Object arg2) {
                this.a(arg1, ((a)arg2));
            }
        };
    }

    public void a(a arg2) {
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

    public boolean a(String arg5) {
        boolean v3;
        h v0 = h.a("SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)", 1);
        if(arg5 == null) {
            v0.a(1);
        }
        else {
            v0.a(1, arg5);
        }

        Cursor v5 = this.a.a(((android.arch.b.a.e)v0));
        try {
            v3 = false;
            if(v5.moveToFirst()) {
                if(v5.getInt(0) == 0) {
                    goto label_15;
                }

                goto label_14;
            }

            goto label_15;
        }
        catch(Throwable v1) {
            v5.close();
            v0.b();
            throw v1;
        }

    label_14:
        v3 = true;
    label_15:
        v5.close();
        v0.b();
        return v3;
    }

    public List b(String arg4) {
        ArrayList v1_1;
        h v0 = h.a("SELECT work_spec_id FROM dependency WHERE prerequisite_id=?", 1);
        if(arg4 == null) {
            v0.a(1);
        }
        else {
            v0.a(1, arg4);
        }

        Cursor v4 = this.a.a(((android.arch.b.a.e)v0));
        try {
            v1_1 = new ArrayList(v4.getCount());
            while(v4.moveToNext()) {
                ((List)v1_1).add(v4.getString(0));
            }
        }
        catch(Throwable v1) {
            goto label_22;
        }

        v4.close();
        v0.b();
        return ((List)v1_1);
    label_22:
        v4.close();
        v0.b();
        throw v1;
    }

    public boolean c(String arg5) {
        boolean v3;
        h v0 = h.a("SELECT COUNT(*)>0 FROM dependency WHERE prerequisite_id=?", 1);
        if(arg5 == null) {
            v0.a(1);
        }
        else {
            v0.a(1, arg5);
        }

        Cursor v5 = this.a.a(((android.arch.b.a.e)v0));
        try {
            v3 = false;
            if(v5.moveToFirst()) {
                if(v5.getInt(0) == 0) {
                    goto label_15;
                }

                goto label_14;
            }

            goto label_15;
        }
        catch(Throwable v1) {
            v5.close();
            v0.b();
            throw v1;
        }

    label_14:
        v3 = true;
    label_15:
        v5.close();
        v0.b();
        return v3;
    }
}

