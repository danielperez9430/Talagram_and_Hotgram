package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public interface bu {
    public final class a implements bu {
        private final Map a;

        public a() {
            super();
            this.a = new HashMap();
        }

        public void a(UUID arg2, b arg3) {
            this.a.put(arg2, arg3);
        }

        public boolean equals(Object arg6) {
            if(arg6 != null) {
                if(this.getClass() != arg6.getClass()) {
                }
                else if(this.a.size() != ((a)arg6).a.size()) {
                    return 0;
                }
                else {
                    Iterator v1 = this.a.keySet().iterator();
                    do {
                        if(v1.hasNext()) {
                            Object v2 = v1.next();
                            if(ft.a(this.a.get(v2), ((a)arg6).a.get(v2))) {
                                continue;
                            }

                            return 0;
                        }
                        else {
                            return 1;
                        }
                    }
                    while(true);

                    return 0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.a.hashCode();
        }
    }

    public final class b {
        public final String a;
        public final byte[] b;

        public b(String arg1, byte[] arg2) {
            super();
            this.a = fe.a(arg1);
            this.b = fe.a(arg2);
        }

        public boolean equals(Object arg5) {
            if(!(arg5 instanceof b)) {
                return 0;
            }

            boolean v0 = true;
            if((((b)arg5)) == this) {
                return 1;
            }

            if(!this.a.equals(((b)arg5).a) || !Arrays.equals(this.b, ((b)arg5).b)) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        public int hashCode() {
            return this.a.hashCode() + Arrays.hashCode(this.b) * 31;
        }
    }

    public final class c implements bu {
        private b a;

        public c(b arg1) {
            super();
            this.a = arg1;
        }

        public boolean equals(Object arg3) {
            if(arg3 != null) {
                if(this.getClass() != arg3.getClass()) {
                }
                else {
                    return ft.a(this.a, ((c)arg3).a);
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.a.hashCode();
        }
    }

}

