package c.a.a.a.a.f;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Build$VERSION;
import c.a.a.a.i;

public class d implements c {
    private final SharedPreferences a;
    private final String b;
    private final Context c;

    public d(Context arg2, String arg3) {
        super();
        if(arg2 != null) {
            this.c = arg2;
            this.b = arg3;
            this.a = this.c.getSharedPreferences(this.b, 0);
            return;
        }

        throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
    }

    @Deprecated public d(i arg2) {
        this(arg2.q(), arg2.getClass().getName());
    }

    public SharedPreferences a() {
        return this.a;
    }

    @TargetApi(value=9) public boolean a(SharedPreferences$Editor arg3) {
        if(Build$VERSION.SDK_INT >= 9) {
            arg3.apply();
            return 1;
        }

        return arg3.commit();
    }

    public SharedPreferences$Editor b() {
        return this.a.edit();
    }
}

