package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public interface o {
    final class okhttp3.o$1 implements o {
        okhttp3.o$1() {
            super();
        }

        public List a(String arg5) {
            if(arg5 != null) {
                try {
                    return Arrays.asList(InetAddress.getAllByName(arg5));
                }
                catch(NullPointerException v0) {
                    StringBuilder v2 = new StringBuilder();
                    v2.append("Broken system behaviour for dns lookup of ");
                    v2.append(arg5);
                    UnknownHostException v1 = new UnknownHostException(v2.toString());
                    v1.initCause(((Throwable)v0));
                    throw v1;
                }
            }

            throw new UnknownHostException("hostname == null");
        }
    }

    public static final o a;

    static {
        o.a = new okhttp3.o$1();
    }

    List a(String arg1);
}

