package androidx.work.impl.b;

import android.arch.b.a.f;
import android.arch.b.b.b.a;
import android.arch.b.b.b;
import android.arch.b.b.e;
import android.arch.b.b.h;
import android.arch.b.b.i;
import android.database.Cursor;
import androidx.work.c;
import androidx.work.o;
import java.util.ArrayList;
import java.util.List;

public class l implements k {
    private final e a;
    private final b b;
    private final i c;
    private final i d;
    private final i e;
    private final i f;
    private final i g;
    private final i h;
    private final i i;
    private final i j;

    public l(e arg2) {
        super();
        this.a = arg2;
        this.b = new b(arg2) {
            public String a() {
                return "INSERT OR IGNORE INTO `WorkSpec`(`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void a(f arg9, j arg10) {
                if(arg10.a == null) {
                    arg9.a(1);
                }
                else {
                    arg9.a(1, arg10.a);
                }

                arg9.a(2, ((long)p.a(arg10.b)));
                int v1 = 3;
                if(arg10.c == null) {
                    arg9.a(v1);
                }
                else {
                    arg9.a(v1, arg10.c);
                }

                v1 = 4;
                if(arg10.d == null) {
                    arg9.a(v1);
                }
                else {
                    arg9.a(v1, arg10.d);
                }

                byte[] v0 = androidx.work.e.a(arg10.e);
                v1 = 5;
                if(v0 == null) {
                    arg9.a(v1);
                }
                else {
                    arg9.a(v1, v0);
                }

                v0 = androidx.work.e.a(arg10.f);
                v1 = 6;
                if(v0 == null) {
                    arg9.a(v1);
                }
                else {
                    arg9.a(v1, v0);
                }

                arg9.a(7, arg10.g);
                arg9.a(8, arg10.h);
                arg9.a(9, arg10.i);
                arg9.a(10, ((long)arg10.k));
                arg9.a(11, ((long)p.a(arg10.l)));
                arg9.a(12, arg10.m);
                arg9.a(13, arg10.n);
                arg9.a(14, arg10.o);
                arg9.a(15, arg10.p);
                c v10 = arg10.j;
                int v0_1 = 20;
                v1 = 19;
                int v2 = 18;
                int v3 = 17;
                int v4 = 16;
                int v5 = 21;
                if(v10 != null) {
                    arg9.a(v4, ((long)p.a(v10.a())));
                    arg9.a(v3, ((long)v10.b()));
                    arg9.a(v2, ((long)v10.c()));
                    arg9.a(v1, ((long)v10.d()));
                    arg9.a(v0_1, ((long)v10.e()));
                    byte[] v10_1 = p.a(v10.f());
                    if(v10_1 == null) {
                        goto label_105;
                    }
                    else {
                        arg9.a(v5, v10_1);
                    }
                }
                else {
                    arg9.a(v4);
                    arg9.a(v3);
                    arg9.a(v2);
                    arg9.a(v1);
                    arg9.a(v0_1);
                label_105:
                    arg9.a(v5);
                }
            }

            public void a(f arg1, Object arg2) {
                this.a(arg1, ((j)arg2));
            }
        };
        this.c = new i(arg2) {
            public String a() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.d = new i(arg2) {
            public String a() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.e = new i(arg2) {
            public String a() {
                return "UPDATE workspec SET period_start_time=? WHERE id=?";
            }
        };
        this.f = new i(arg2) {
            public String a() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.g = new i(arg2) {
            public String a() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.h = new i(arg2) {
            public String a() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.i = new i(arg2) {
            public String a() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.j = new i(arg2) {
            public String a() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
    }

    public int a(o arg5, String[] arg6) {
        StringBuilder v0 = a.a();
        v0.append("UPDATE workspec SET state=");
        v0.append("?");
        v0.append(" WHERE id IN (");
        a.a(v0, arg6.length);
        v0.append(")");
        f v0_1 = this.a.a(v0.toString());
        v0_1.a(1, ((long)p.a(arg5)));
        int v5 = arg6.length;
        int v1 = 2;
        int v2;
        for(v2 = 0; v2 < v5; ++v2) {
            String v3 = arg6[v2];
            if(v3 == null) {
                v0_1.a(v1);
            }
            else {
                v0_1.a(v1, v3);
            }

            ++v1;
        }

        this.a.f();
        try {
            v5 = v0_1.a();
            this.a.h();
        }
        catch(Throwable v5_1) {
            this.a.g();
            throw v5_1;
        }

        this.a.g();
        return v5;
    }

    public List a() {
        ArrayList v3;
        h v0 = h.a("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
        Cursor v2 = this.a.a(((android.arch.b.a.e)v0));
        try {
            v3 = new ArrayList(v2.getCount());
            while(v2.moveToNext()) {
                ((List)v3).add(v2.getString(0));
            }
        }
        catch(Throwable v1) {
            goto label_17;
        }

        v2.close();
        v0.b();
        return ((List)v3);
    label_17:
        v2.close();
        v0.b();
        throw v1;
    }

    public List a(int arg39) {
        ArrayList v12_1;
        h v16;
        int v3;
        int v1;
        int v15;
        int v14;
        int v13;
        int v12;
        int v11;
        int v10;
        int v9;
        int v8;
        int v7;
        int v6;
        int v5;
        int v0_1;
        h v2 = h.a("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 1);
        v2.a(1, ((long)arg39));
        Cursor v4 = this.a.a(((android.arch.b.a.e)v2));
        try {
            v0_1 = v4.getColumnIndexOrThrow("id");
            v5 = v4.getColumnIndexOrThrow("state");
            v6 = v4.getColumnIndexOrThrow("worker_class_name");
            v7 = v4.getColumnIndexOrThrow("input_merger_class_name");
            v8 = v4.getColumnIndexOrThrow("input");
            v9 = v4.getColumnIndexOrThrow("output");
            v10 = v4.getColumnIndexOrThrow("initial_delay");
            v11 = v4.getColumnIndexOrThrow("interval_duration");
            v12 = v4.getColumnIndexOrThrow("flex_duration");
            v13 = v4.getColumnIndexOrThrow("run_attempt_count");
            v14 = v4.getColumnIndexOrThrow("backoff_policy");
            v15 = v4.getColumnIndexOrThrow("backoff_delay_duration");
            v1 = v4.getColumnIndexOrThrow("period_start_time");
            v3 = v4.getColumnIndexOrThrow("minimum_retention_duration");
            v16 = v2;
        }
        catch(Throwable v0) {
            v16 = v2;
            goto label_176;
        }

        try {
            int v17 = v4.getColumnIndexOrThrow("schedule_requested_at");
            int v2_1 = v4.getColumnIndexOrThrow("required_network_type");
            int v18 = v3;
            v3 = v4.getColumnIndexOrThrow("requires_charging");
            int v19 = v1;
            v1 = v4.getColumnIndexOrThrow("requires_device_idle");
            int v20 = v15;
            v15 = v4.getColumnIndexOrThrow("requires_battery_not_low");
            int v21 = v14;
            v14 = v4.getColumnIndexOrThrow("requires_storage_not_low");
            int v22 = v13;
            v13 = v4.getColumnIndexOrThrow("content_uri_triggers");
            int v23 = v12;
            int v24 = v11;
            v12_1 = new ArrayList(v4.getCount());
            while(v4.moveToNext()) {
                String v11_1 = v4.getString(v0_1);
                int v25 = v0_1;
                String v0_2 = v4.getString(v6);
                int v26 = v6;
                c v6_1 = new c();
                int v28 = v2_1;
                v6_1.a(p.c(v4.getInt(v2_1)));
                boolean v2_2 = v4.getInt(v3) != 0 ? true : false;
                v6_1.a(v2_2);
                v2_2 = v4.getInt(v1) != 0 ? true : false;
                v6_1.b(v2_2);
                v2_2 = v4.getInt(v15) != 0 ? true : false;
                v6_1.c(v2_2);
                v2_2 = v4.getInt(v14) != 0 ? true : false;
                v6_1.d(v2_2);
                v6_1.a(p.a(v4.getBlob(v13)));
                j v2_3 = new j(v11_1, v0_2);
                v2_3.b = p.a(v4.getInt(v5));
                v2_3.d = v4.getString(v7);
                v2_3.e = androidx.work.e.a(v4.getBlob(v8));
                v2_3.f = androidx.work.e.a(v4.getBlob(v9));
                v2_3.g = v4.getLong(v10);
                v2_3.h = v4.getLong(v24);
                v2_3.i = v4.getLong(v23);
                v2_3.k = v4.getInt(v22);
                v2_3.l = p.b(v4.getInt(v21));
                v2_3.m = v4.getLong(v20);
                v2_3.n = v4.getLong(v19);
                v2_3.o = v4.getLong(v18);
                v2_3.p = v4.getLong(v17);
                v2_3.j = v6_1;
                ((List)v12_1).add(v2_3);
                v17 = v17;
                v20 = v20;
                v0_1 = v25;
                v6 = v26;
                v2_1 = v28;
                v1 = v1;
                v7 = v7;
                v8 = v8;
                v24 = v24;
                v23 = v23;
                v22 = v22;
                v21 = v21;
                v19 = v19;
                v18 = v18;
            }
        }
        catch(Throwable v0) {
            goto label_176;
        }

        v4.close();
        v16.b();
        return ((List)v12_1);
    label_176:
        v4.close();
        v16.b();
        throw v0;
    }

    public void a(j arg2) {
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

    public void a(String arg3) {
        f v0 = this.c.c();
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

    public void a(String arg3, long arg4) {
        f v0 = this.e.c();
        this.a.f();
        try {
            v0.a(1, arg4);
            int v4 = 2;
            if(arg3 == null) {
                v0.a(v4);
            }
            else {
                v0.a(v4, arg3);
            }

            v0.a();
            this.a.h();
        }
        catch(Throwable v3) {
            this.a.g();
            this.e.a(v0);
            throw v3;
        }

        this.a.g();
        this.e.a(v0);
    }

    public void a(String arg3, androidx.work.e arg4) {
        f v0 = this.d.c();
        this.a.f();
        try {
            byte[] v4 = androidx.work.e.a(arg4);
            if(v4 == null) {
                v0.a(1);
            }
            else {
                v0.a(1, v4);
            }

            int v4_1 = 2;
            if(arg3 == null) {
                v0.a(v4_1);
            }
            else {
                v0.a(v4_1, arg3);
            }

            v0.a();
            this.a.h();
        }
        catch(Throwable v3) {
            this.a.g();
            this.d.a(v0);
            throw v3;
        }

        this.a.g();
        this.d.a(v0);
    }

    public int b() {
        int v1_1;
        f v0 = this.i.c();
        this.a.f();
        try {
            v1_1 = v0.a();
            this.a.h();
        }
        catch(Throwable v1) {
            this.a.g();
            this.i.a(v0);
            throw v1;
        }

        this.a.g();
        this.i.a(v0);
        return v1_1;
    }

    public int b(String arg3, long arg4) {
        int v3_1;
        f v0 = this.h.c();
        this.a.f();
        try {
            v0.a(1, arg4);
            int v4 = 2;
            if(arg3 == null) {
                v0.a(v4);
            }
            else {
                v0.a(v4, arg3);
            }

            v3_1 = v0.a();
            this.a.h();
        }
        catch(Throwable v3) {
            this.a.g();
            this.h.a(v0);
            throw v3;
        }

        this.a.g();
        this.h.a(v0);
        return v3_1;
    }

    public j b(String arg26) {
        j v1_3;
        h v16;
        int v3_1;
        int v2;
        int v15;
        int v14;
        int v13;
        int v12;
        int v11;
        int v10;
        int v9;
        int v8;
        int v7;
        int v6;
        int v5;
        int v0_2;
        String v0 = arg26;
        h v1 = h.a("SELECT * FROM workspec WHERE id=?", 1);
        if(v0 == null) {
            v1.a(1);
        }
        else {
            v1.a(1, v0);
        }

        l v3 = this;
        Cursor v4 = v3.a.a(((android.arch.b.a.e)v1));
        try {
            v0_2 = v4.getColumnIndexOrThrow("id");
            v5 = v4.getColumnIndexOrThrow("state");
            v6 = v4.getColumnIndexOrThrow("worker_class_name");
            v7 = v4.getColumnIndexOrThrow("input_merger_class_name");
            v8 = v4.getColumnIndexOrThrow("input");
            v9 = v4.getColumnIndexOrThrow("output");
            v10 = v4.getColumnIndexOrThrow("initial_delay");
            v11 = v4.getColumnIndexOrThrow("interval_duration");
            v12 = v4.getColumnIndexOrThrow("flex_duration");
            v13 = v4.getColumnIndexOrThrow("run_attempt_count");
            v14 = v4.getColumnIndexOrThrow("backoff_policy");
            v15 = v4.getColumnIndexOrThrow("backoff_delay_duration");
            v2 = v4.getColumnIndexOrThrow("period_start_time");
            v3_1 = v4.getColumnIndexOrThrow("minimum_retention_duration");
            v16 = v1;
        }
        catch(Throwable v0_1) {
            v16 = v1;
            goto label_146;
        }

        try {
            int v17 = v4.getColumnIndexOrThrow("schedule_requested_at");
            int v1_1 = v4.getColumnIndexOrThrow("required_network_type");
            int v18 = v3_1;
            v3_1 = v4.getColumnIndexOrThrow("requires_charging");
            int v19 = v2;
            v2 = v4.getColumnIndexOrThrow("requires_device_idle");
            int v20 = v15;
            v15 = v4.getColumnIndexOrThrow("requires_battery_not_low");
            int v21 = v14;
            v14 = v4.getColumnIndexOrThrow("requires_storage_not_low");
            int v22 = v13;
            v13 = v4.getColumnIndexOrThrow("content_uri_triggers");
            if(v4.moveToFirst()) {
                v0 = v4.getString(v0_2);
                String v6_1 = v4.getString(v6);
                int v24 = v12;
                c v12_1 = new c();
                v12_1.a(p.c(v4.getInt(v1_1)));
                v1_1 = v4.getInt(v3_1);
                boolean v3_2 = false;
                boolean v1_2 = v1_1 != 0 ? true : false;
                v12_1.a(v1_2);
                v1_2 = v4.getInt(v2) != 0 ? true : false;
                v12_1.b(v1_2);
                v1_2 = v4.getInt(v15) != 0 ? true : false;
                v12_1.c(v1_2);
                if(v4.getInt(v14) != 0) {
                    v3_2 = true;
                }

                v12_1.d(v3_2);
                v12_1.a(p.a(v4.getBlob(v13)));
                v1_3 = new j(v0, v6_1);
                v1_3.b = p.a(v4.getInt(v5));
                v1_3.d = v4.getString(v7);
                v1_3.e = androidx.work.e.a(v4.getBlob(v8));
                v1_3.f = androidx.work.e.a(v4.getBlob(v9));
                v1_3.g = v4.getLong(v10);
                v1_3.h = v4.getLong(v11);
                v1_3.i = v4.getLong(v24);
                v1_3.k = v4.getInt(v22);
                v1_3.l = p.b(v4.getInt(v21));
                v1_3.m = v4.getLong(v20);
                v1_3.n = v4.getLong(v19);
                v1_3.o = v4.getLong(v18);
                v1_3.p = v4.getLong(v17);
                v1_3.j = v12_1;
            }
            else {
                goto label_138;
            }

            goto label_139;
        }
        catch(Throwable v0_1) {
        }

    label_146:
        v4.close();
        v16.b();
        throw v0_1;
    label_138:
        v1_3 = null;
    label_139:
        v4.close();
        v16.b();
        return v1_3;
    }

    public List c(String arg7) {
        ArrayList v3;
        h v0 = h.a("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if(arg7 == null) {
            v0.a(1);
        }
        else {
            v0.a(1, arg7);
        }

        Cursor v7 = this.a.a(((android.arch.b.a.e)v0));
        try {
            int v1_1 = v7.getColumnIndexOrThrow("id");
            int v2 = v7.getColumnIndexOrThrow("state");
            v3 = new ArrayList(v7.getCount());
            while(v7.moveToNext()) {
                androidx.work.impl.b.j$a v4 = new androidx.work.impl.b.j$a();
                v4.a = v7.getString(v1_1);
                v4.b = p.a(v7.getInt(v2));
                ((List)v3).add(v4);
            }
        }
        catch(Throwable v1) {
            goto label_31;
        }

        v7.close();
        v0.b();
        return ((List)v3);
    label_31:
        v7.close();
        v0.b();
        throw v1;
    }

    public int d(String arg3) {
        int v3_1;
        f v0 = this.f.c();
        this.a.f();
        if(arg3 == null) {
            try {
                v0.a(1);
                goto label_11;
            label_10:
                v0.a(1, arg3);
            label_11:
                v3_1 = v0.a();
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
        this.f.a(v0);
        throw v3;
    label_14:
        this.a.g();
        this.f.a(v0);
        return v3_1;
    }

    public int e(String arg3) {
        int v3_1;
        f v0 = this.g.c();
        this.a.f();
        if(arg3 == null) {
            try {
                v0.a(1);
                goto label_11;
            label_10:
                v0.a(1, arg3);
            label_11:
                v3_1 = v0.a();
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
        this.g.a(v0);
        throw v3;
    label_14:
        this.a.g();
        this.g.a(v0);
        return v3_1;
    }

    public o f(String arg3) {
        o v1_1;
        h v0 = h.a("SELECT state FROM workspec WHERE id=?", 1);
        if(arg3 == null) {
            v0.a(1);
        }
        else {
            v0.a(1, arg3);
        }

        Cursor v3 = this.a.a(((android.arch.b.a.e)v0));
        try {
            if(v3.moveToFirst()) {
                v1_1 = p.a(v3.getInt(0));
            }
            else {
                goto label_15;
            }

            goto label_16;
        }
        catch(Throwable v1) {
            v3.close();
            v0.b();
            throw v1;
        }

    label_15:
        v1_1 = null;
    label_16:
        v3.close();
        v0.b();
        return v1_1;
    }

    public List g(String arg4) {
        ArrayList v1_1;
        h v0 = h.a("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
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
                ((List)v1_1).add(androidx.work.e.a(v4.getBlob(0)));
            }
        }
        catch(Throwable v1) {
            goto label_23;
        }

        v4.close();
        v0.b();
        return ((List)v1_1);
    label_23:
        v4.close();
        v0.b();
        throw v1;
    }

    public List h(String arg4) {
        ArrayList v1_1;
        h v0 = h.a("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
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

    public List i(String arg4) {
        ArrayList v1_1;
        h v0 = h.a("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
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
}

