package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;
import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a=e.class) public abstract class m {
    public abstract class a {
        public a() {
            super();
        }

        public abstract m build();

        public abstract a height(int arg1);

        public abstract a left(int arg1);

        public a locationOnScreenOfView(View arg4) {
            int[] v0 = new int[2];
            arg4.getLocationOnScreen(v0);
            return this.left(v0[0]).top(v0[1]).height(arg4.getHeight()).width(arg4.getWidth());
        }

        public abstract a top(int arg1);

        public abstract a width(int arg1);
    }

    public m() {
        super();
    }

    public static a builder() {
        return new com.google.ads.interactivemedia.v3.impl.data.e$a();
    }

    public abstract int height();

    public abstract int left();

    public abstract int top();

    public abstract int width();
}

