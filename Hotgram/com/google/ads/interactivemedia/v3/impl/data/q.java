package com.google.ads.interactivemedia.v3.impl.data;

import android.support.v4.view.t;
import android.view.View;
import com.google.ads.interactivemedia.v3.internal.ki;
import com.google.ads.interactivemedia.v3.internal.la;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@ki(a=i.class) public abstract class q {
    public abstract class a {
        public a() {
            super();
        }

        public abstract q build();

        public abstract a obstructions(List arg1);

        public a views(Collection arg4) {
            ArrayList v0 = new ArrayList();
            Iterator v4 = arg4.iterator();
            while(v4.hasNext()) {
                v0.add(b.builder().view(v4.next()).build());
            }

            return this.obstructions(((List)v0));
        }
    }

    @ki(a=j.class) public abstract class b {
        public abstract class com.google.ads.interactivemedia.v3.impl.data.q$b$a {
            public com.google.ads.interactivemedia.v3.impl.data.q$b$a() {
                super();
            }

            public abstract com.google.ads.interactivemedia.v3.impl.data.q$b$a attached(boolean arg1);

            public abstract com.google.ads.interactivemedia.v3.impl.data.q$b$a bounds(m arg1);

            public abstract b build();

            public abstract com.google.ads.interactivemedia.v3.impl.data.q$b$a hidden(boolean arg1);

            public abstract com.google.ads.interactivemedia.v3.impl.data.q$b$a type(String arg1);

            com.google.ads.interactivemedia.v3.impl.data.q$b$a view(View arg3) {
                return this.attached(t.D(arg3)).bounds(m.builder().locationOnScreenOfView(arg3).build()).hidden(arg3.isShown() ^ 1).type(arg3.getClass().getCanonicalName());
            }
        }

        public b() {
            super();
        }

        abstract boolean attached();

        abstract m bounds();

        public static com.google.ads.interactivemedia.v3.impl.data.q$b$a builder() {
            return new com.google.ads.interactivemedia.v3.impl.data.j$a();
        }

        abstract boolean hidden();

        abstract String type();
    }

    public q() {
        super();
    }

    public static a builder() {
        return new com.google.ads.interactivemedia.v3.impl.data.i$a();
    }

    abstract la obstructions();
}

