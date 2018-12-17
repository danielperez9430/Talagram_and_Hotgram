package com.persianswitch.a;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public interface o {
    final class com.persianswitch.a.o$1 implements o {
        com.persianswitch.a.o$1() {
            super();
        }

        public List a(String arg2) {
            if(arg2 != null) {
                return Arrays.asList(InetAddress.getAllByName(arg2));
            }

            throw new UnknownHostException("hostname == null");
        }
    }

    public static final o a;

    static {
        o.a = new com.persianswitch.a.o$1();
    }

    List a(String arg1);
}

