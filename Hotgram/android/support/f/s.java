package android.support.f;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class s {
    public final Map a;
    public View b;
    final ArrayList c;

    public s() {
        super();
        this.a = new HashMap();
        this.c = new ArrayList();
    }

    public boolean equals(Object arg3) {
        if(((arg3 instanceof s)) && this.b == ((s)arg3).b && (this.a.equals(((s)arg3).a))) {
            return 1;
        }

        return 0;
    }

    public int hashCode() {
        return this.b.hashCode() * 31 + this.a.hashCode();
    }

    public String toString() {
        String v0_1 = "TransitionValues@" + Integer.toHexString(this.hashCode()) + ":\n";
        v0_1 = v0_1 + "    view = " + this.b + "\n";
        v0_1 = v0_1 + "    values:";
        Iterator v1_1 = this.a.keySet().iterator();
        while(v1_1.hasNext()) {
            Object v2 = v1_1.next();
            v0_1 = v0_1 + "    " + (((String)v2)) + ": " + this.a.get(v2) + "\n";
        }

        return v0_1;
    }
}

