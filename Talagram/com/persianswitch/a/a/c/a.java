package com.persianswitch.a.a.c;

import java.io.File;
import java.io.IOException;

public interface a {
    final class com.persianswitch.a.a.c.a$1 implements a {
        com.persianswitch.a.a.c.a$1() {
            super();
        }

        public void a(File arg4) {
            if(!arg4.delete()) {
                if(!arg4.exists()) {
                }
                else {
                    StringBuilder v1 = new StringBuilder();
                    v1.append("failed to delete ");
                    v1.append(arg4);
                    throw new IOException(v1.toString());
                }
            }
        }

        public void a(File arg4, File arg5) {
            this.a(arg5);
            if(arg4.renameTo(arg5)) {
                return;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("failed to rename ");
            v1.append(arg4);
            v1.append(" to ");
            v1.append(arg5);
            throw new IOException(v1.toString());
        }

        public boolean b(File arg1) {
            return arg1.exists();
        }

        public long c(File arg3) {
            return arg3.length();
        }
    }

    public static final a a;

    static {
        a.a = new com.persianswitch.a.a.c.a$1();
    }

    void a(File arg1);

    void a(File arg1, File arg2);

    boolean b(File arg1);

    long c(File arg1);
}

