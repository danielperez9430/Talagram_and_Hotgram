package android.support.v4.d;

import android.support.v4.f.l;
import android.util.Base64;
import java.util.List;

public final class a {
    private final String a;
    private final String b;
    private final String c;
    private final List d;
    private final int e;
    private final String f;

    public a(String arg1, String arg2, String arg3, List arg4) {
        super();
        this.a = l.a(arg1);
        this.b = l.a(arg2);
        this.c = l.a(arg3);
        this.d = l.a(arg4);
        this.e = 0;
        StringBuilder v1 = new StringBuilder(this.a);
        v1.append("-");
        v1.append(this.b);
        v1.append("-");
        v1.append(this.c);
        this.f = v1.toString();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public List d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("FontRequest {mProviderAuthority: " + this.a + ", mProviderPackage: " + this.b + ", mQuery: " + this.c + ", mCertificates:");
        int v2;
        for(v2 = 0; v2 < this.d.size(); ++v2) {
            v0.append(" [");
            Object v3 = this.d.get(v2);
            int v4;
            for(v4 = 0; v4 < ((List)v3).size(); ++v4) {
                v0.append(" \"");
                v0.append(Base64.encodeToString(((List)v3).get(v4), 0));
                v0.append("\"");
            }

            v0.append(" ]");
        }

        v0.append("}");
        v0.append("mCertificatesArray: " + this.e);
        return v0.toString();
    }
}

