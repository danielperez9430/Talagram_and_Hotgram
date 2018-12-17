package okhttp3.internal.c;

import java.net.ProtocolException;
import okhttp3.y;

public final class k {
    public final y a;
    public final int b;
    public final String c;

    public k(y arg1, int arg2, String arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public static k a(String arg8) {
        int v4;
        StringBuilder v1_1;
        y v0_1;
        int v1 = 4;
        int v2 = 32;
        int v3 = 9;
        if(arg8.startsWith("HTTP/1.")) {
            if(arg8.length() >= v3 && arg8.charAt(8) == v2) {
                int v0 = arg8.charAt(7) - 48;
                if(v0 == 0) {
                    v0_1 = y.a;
                    goto label_44;
                }
                else if(v0 == 1) {
                    v0_1 = y.b;
                    goto label_44;
                }
                else {
                    v1_1 = new StringBuilder();
                    v1_1.append("Unexpected status line: ");
                    v1_1.append(arg8);
                    throw new ProtocolException(v1_1.toString());
                }
            }

            v1_1 = new StringBuilder();
            v1_1.append("Unexpected status line: ");
            v1_1.append(arg8);
            throw new ProtocolException(v1_1.toString());
        }
        else {
            if(!arg8.startsWith("ICY ")) {
                goto label_87;
            }

            v0_1 = y.a;
            v3 = 4;
        }

    label_44:
        int v5 = v3 + 3;
        if(arg8.length() >= v5) {
            try {
                v4 = Integer.parseInt(arg8.substring(v3, v5));
            }
            catch(NumberFormatException ) {
                v1_1 = new StringBuilder();
                v1_1.append("Unexpected status line: ");
                v1_1.append(arg8);
                throw new ProtocolException(v1_1.toString());
            }

            String v6 = "";
            if(arg8.length() > v5) {
                if(arg8.charAt(v5) == v2) {
                    v6 = arg8.substring(v3 + v1);
                }
                else {
                    v1_1 = new StringBuilder();
                    v1_1.append("Unexpected status line: ");
                    v1_1.append(arg8);
                    throw new ProtocolException(v1_1.toString());
                }
            }

            return new k(v0_1, v4, v6);
        }

        v1_1 = new StringBuilder();
        v1_1.append("Unexpected status line: ");
        v1_1.append(arg8);
        throw new ProtocolException(v1_1.toString());
    label_87:
        v1_1 = new StringBuilder();
        v1_1.append("Unexpected status line: ");
        v1_1.append(arg8);
        throw new ProtocolException(v1_1.toString());
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        String v1 = this.a == y.a ? "HTTP/1.0" : "HTTP/1.1";
        v0.append(v1);
        char v1_1 = ' ';
        v0.append(v1_1);
        v0.append(this.b);
        if(this.c != null) {
            v0.append(v1_1);
            v0.append(this.c);
        }

        return v0.toString();
    }
}

