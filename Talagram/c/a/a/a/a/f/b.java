package c.a.a.a.a.f;

import android.content.Context;
import c.a.a.a.c;
import c.a.a.a.i;
import java.io.File;

public class b implements a {
    private final Context a;
    private final String b;
    private final String c;

    public b(i arg2) {
        super();
        if(arg2.q() != null) {
            this.a = arg2.q();
            this.b = arg2.s();
            this.c = "Android/" + this.a.getPackageName();
            return;
        }

        throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
    }

    public File a() {
        return this.a(this.a.getFilesDir());
    }

    File a(File arg3) {
        if(arg3 != null) {
            if(!arg3.exists()) {
                if(arg3.mkdirs()) {
                }
                else {
                    c.h().d("Fabric", "Couldn\'t create file");
                    return null;
                }
            }

            return arg3;
        }
        else {
            c.h().a("Fabric", "Null File");
        }

        return null;
    }
}

