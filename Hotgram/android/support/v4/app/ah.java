package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public final class ah implements Iterable {
    public interface a {
        Intent a();
    }

    private final ArrayList a;
    private final Context b;

    private ah(Context arg2) {
        super();
        this.a = new ArrayList();
        this.b = arg2;
    }

    public static ah a(Context arg1) {
        return new ah(arg1);
    }

    public ah a(Activity arg2) {
        Intent v0 = (arg2 instanceof a) ? arg2.a() : null;
        if(v0 == null) {
            v0 = u.a(arg2);
        }

        if(v0 != null) {
            ComponentName v2 = v0.getComponent();
            if(v2 == null) {
                v2 = v0.resolveActivity(this.b.getPackageManager());
            }

            this.a(v2);
            this.a(v0);
        }

        return this;
    }

    public ah a(ComponentName arg3) {
        int v0 = this.a.size();
        try {
            Context v1 = this.b;
            while(true) {
                Intent v3_1 = u.a(v1, ((ComponentName)v3_1));
                if(v3_1 == null) {
                    return this;
                }

                this.a.add(v0, v3_1);
                v1 = this.b;
                arg3 = v3_1.getComponent();
            }
        }
        catch(PackageManager$NameNotFoundException v3) {
            goto label_14;
        }

        return this;
    label_14:
        Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
        throw new IllegalArgumentException(((Throwable)v3));
    }

    public ah a(Intent arg2) {
        this.a.add(arg2);
        return this;
    }

    public void a() {
        this.a(null);
    }

    public void a(Bundle arg5) {
        if(!this.a.isEmpty()) {
            Object[] v0 = this.a.toArray(new Intent[this.a.size()]);
            v0[0] = new Intent(v0[0]).addFlags(268484608);
            if(!android.support.v4.content.a.a(this.b, ((Intent[])v0), arg5)) {
                Intent v5 = new Intent(v0[v0.length - 1]);
                v5.addFlags(268435456);
                this.b.startActivity(v5);
            }

            return;
        }

        throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }

    @Deprecated public Iterator iterator() {
        return this.a.iterator();
    }
}

