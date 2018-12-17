package android.support.v4.view;

import android.os.Build$VERSION;
import android.support.a.a$c;
import android.view.View;
import android.view.ViewGroup;

public final class v {
    public static boolean a(ViewGroup arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.isTransitionGroup();
        }

        Object v0 = arg2.getTag(c.tag_transition_group);
        boolean v2 = v0 != null && (((Boolean)v0).booleanValue()) || (arg2.getBackground() != null || t.q(((View)arg2)) != null) ? true : false;
        return v2;
    }
}

