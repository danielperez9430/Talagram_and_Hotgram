package androidx.work.impl.b;

import android.arch.b.a.f;
import android.arch.b.b.b;
import android.arch.b.b.e;
import android.arch.b.b.h;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class o implements n {
    private final e a;
    private final b b;

    public o(e arg2) {
        super();
        this.a = arg2;
        this.b = new b(arg2) {
            public String a() {
                return "INSERT OR IGNORE INTO `WorkTag`(`tag`,`work_spec_id`) VALUES (?,?)";
            }

            public void a(f arg3, m arg4) {
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
                this.a(arg1, ((m)arg2));
            }
        };
    }

    public List a(String arg4) {
        ArrayList v1_1;
        h v0 = h.a("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?", 1);
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

    public void a(m arg2) {
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
}

