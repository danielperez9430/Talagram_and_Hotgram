package android.support.f;

import android.os.IBinder;

class aj implements al {
    private final IBinder a;

    aj(IBinder arg1) {
        super();
        this.a = arg1;
    }

    public boolean equals(Object arg2) {
        boolean v2 = !(arg2 instanceof aj) || !((aj)arg2).a.equals(this.a) ? false : true;
        return v2;
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}

