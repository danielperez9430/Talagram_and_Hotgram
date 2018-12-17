package android.support.design.internal;

import android.content.Context;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class NavigationMenuView extends RecyclerView implements p {
    public NavigationMenuView(Context arg2) {
        this(arg2, null);
    }

    public NavigationMenuView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public NavigationMenuView(Context arg2, AttributeSet arg3, int arg4) {
        super(arg2, arg3, arg4);
        this.setLayoutManager(new LinearLayoutManager(arg2, 1, false));
    }

    public void a(h arg1) {
    }

    public int getWindowAnimations() {
        return 0;
    }
}

