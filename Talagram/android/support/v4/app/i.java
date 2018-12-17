package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class i {
    public i() {
        super();
    }

    public Fragment a(Context arg1, String arg2, Bundle arg3) {
        return Fragment.instantiate(arg1, arg2, arg3);
    }

    public abstract View a(int arg1);

    public abstract boolean a();
}

