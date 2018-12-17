package android.support.f;

import android.view.View;
import android.view.WindowId;

class ak implements al {
    private final WindowId a;

    ak(View arg1) {
        super();
        this.a = arg1.getWindowId();
    }

    public boolean equals(Object arg2) {
        boolean v2 = !(arg2 instanceof ak) || !((ak)arg2).a.equals(this.a) ? false : true;
        return v2;
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}

