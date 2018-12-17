package androidx.work;

import android.net.Uri;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class d implements Iterable {
    public final class a {
        private final Uri a;
        private final boolean b;

        a(Uri arg1, boolean arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public Uri a() {
            return this.a;
        }

        public boolean b() {
            return this.b;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((a)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.b != ((a)arg5).b || !this.a.equals(((a)arg5).a)) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.a.hashCode() * 31 + this.b;
        }
    }

    private final Set a;

    public d() {
        super();
        this.a = new HashSet();
    }

    public int a() {
        return this.a.size();
    }

    public void a(Uri arg2, boolean arg3) {
        this.a.add(new a(arg2, arg3));
    }

    public boolean equals(Object arg3) {
        if(this == (((d)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return this.a.equals(((d)arg3).a);
            }
        }

        return 0;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public Iterator iterator() {
        return this.a.iterator();
    }
}

