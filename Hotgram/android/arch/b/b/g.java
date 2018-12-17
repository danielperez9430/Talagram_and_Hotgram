package android.arch.b.b;

import android.arch.b.a.b;
import android.arch.b.a.c$a;
import android.database.Cursor;
import java.util.Iterator;
import java.util.List;

public class g extends a {
    public abstract class android.arch.b.b.g$a {
        public final int a;

        public android.arch.b.b.g$a(int arg1) {
            super();
            this.a = arg1;
        }

        protected abstract void a(b arg1);

        protected abstract void b(b arg1);

        protected abstract void c(b arg1);

        protected abstract void d(b arg1);

        protected abstract void e(b arg1);
    }

    private android.arch.b.b.a b;
    private final android.arch.b.b.g$a c;
    private final String d;
    private final String e;

    public g(android.arch.b.b.a arg2, android.arch.b.b.g$a arg3, String arg4, String arg5) {
        super(arg3.a);
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
    }

    public void a(b arg1) {
        super.a(arg1);
    }

    public void a(b arg3, int arg4, int arg5) {
        int v0_2;
        if(this.b != null) {
            List v0 = this.b.d.a(arg4, arg5);
            if(v0 != null) {
                Iterator v0_1 = v0.iterator();
                while(v0_1.hasNext()) {
                    v0_1.next().a(arg3);
                }

                this.c.e(arg3);
                this.f(arg3);
                v0_2 = 1;
            }
            else {
                goto label_17;
            }
        }
        else {
        label_17:
            v0_2 = 0;
        }

        if(v0_2 == 0) {
            if(this.b != null && !this.b.a(arg4)) {
                this.c.a(arg3);
                this.c.b(arg3);
                return;
            }

            StringBuilder v0_3 = new StringBuilder();
            v0_3.append("A migration from ");
            v0_3.append(arg4);
            v0_3.append(" to ");
            v0_3.append(arg5);
            v0_3.append(" was required but not found. Please provide the ");
            v0_3.append("necessary Migration path via ");
            v0_3.append("RoomDatabase.Builder.addMigration(Migration ...) or allow for ");
            v0_3.append("destructive migrations via one of the ");
            v0_3.append("RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
            throw new IllegalStateException(v0_3.toString());
        }
    }

    public void b(b arg2) {
        this.f(arg2);
        this.c.b(arg2);
        this.c.d(arg2);
    }

    public void b(b arg1, int arg2, int arg3) {
        this.a(arg1, arg2, arg3);
    }

    public void c(b arg2) {
        super.c(arg2);
        this.e(arg2);
        this.c.c(arg2);
        this.b = null;
    }

    private void e(b arg4) {
        Object v1 = null;
        if(g.h(arg4)) {
            Cursor v4 = arg4.a(new android.arch.b.a.a("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
            try {
                if(v4.moveToFirst()) {
                    String v1_1 = v4.getString(0);
                }
            }
            catch(Throwable v0) {
                v4.close();
                throw v0;
            }

            v4.close();
        }

        if(!this.d.equals(v1)) {
            if(this.e.equals(v1)) {
            }
            else {
                throw new IllegalStateException("Room cannot verify the data integrity. Looks like you\'ve changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
            }
        }
    }

    private void f(b arg2) {
        this.g(arg2);
        arg2.c(f.a(this.d));
    }

    private void g(b arg2) {
        arg2.c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    private static boolean h(b arg2) {
        boolean v1;
        Cursor v2 = arg2.b("SELECT 1 FROM sqlite_master WHERE type = \'table\' AND name=\'room_master_table\'");
        try {
            v1 = false;
            if(v2.moveToFirst()) {
                if(v2.getInt(0) == 0) {
                    goto label_8;
                }

                goto label_7;
            }

            goto label_8;
        }
        catch(Throwable v0) {
            v2.close();
            throw v0;
        }

    label_7:
        v1 = true;
    label_8:
        v2.close();
        return v1;
    }
}

