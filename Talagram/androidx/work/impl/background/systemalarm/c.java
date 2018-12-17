package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.impl.a.d;
import androidx.work.impl.b.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class c {
    private final Context a;
    private final int b;
    private final e c;
    private final d d;

    c(Context arg1, int arg2, e arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = new d(this.a, null);
    }

    void a() {
        List v0 = this.c.d().d().m().a(this.c.d().e().f());
        ConstraintProxy.a(this.a, v0);
        this.d.a(v0);
        ArrayList v1 = new ArrayList(v0.size());
        long v2 = System.currentTimeMillis();
        Iterator v0_1 = v0.iterator();
        while(v0_1.hasNext()) {
            Object v4 = v0_1.next();
            String v5 = ((j)v4).a;
            if(v2 < ((j)v4).c()) {
                continue;
            }

            if((((j)v4).d()) && !this.d.a(v5)) {
                continue;
            }

            ((List)v1).add(v4);
        }

        v0_1 = ((List)v1).iterator();
        while(v0_1.hasNext()) {
            String v1_1 = v0_1.next().a;
            Intent v2_1 = b.b(this.a, v1_1);
            androidx.work.j.b("ConstraintsCmdHandler", String.format("Creating a delay_met command for workSpec with id (%s)", v1_1), new Throwable[0]);
            this.c.a(new a(this.c, v2_1, this.b));
        }

        this.d.a();
    }
}

