package c.a.a.a.a.e;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy$Type;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;

public class d {
    public abstract class a extends c.a.a.a.a.e.d$d {
        private final Closeable a;
        private final boolean b;

        protected a(Closeable arg1, boolean arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        protected void c() {
            if((this.a instanceof Flushable)) {
                this.a.flush();
            }

            if(this.b) {
                try {
                    this.a.close();
                }
                catch(IOException ) {
                }
            }
            else {
                this.a.close();
            }
        }
    }

    public interface b {
        final class c.a.a.a.a.e.d$b$1 implements b {
            c.a.a.a.a.e.d$b$1() {
                super();
            }

            public HttpURLConnection a(URL arg1) {
                return arg1.openConnection();
            }

            public HttpURLConnection a(URL arg1, Proxy arg2) {
                return arg1.openConnection(arg2);
            }
        }

        public static final b a;

        static {
            b.a = new c.a.a.a.a.e.d$b$1();
        }

        HttpURLConnection a(URL arg1, Proxy arg2);

        HttpURLConnection a(URL arg1);
    }

    public class c extends RuntimeException {
        protected c(IOException arg1) {
            super(((Throwable)arg1));
        }

        public IOException a() {
            return super.getCause();
        }

        public Throwable getCause() {
            return this.a();
        }
    }

    public abstract class c.a.a.a.a.e.d$d implements Callable {
        protected c.a.a.a.a.e.d$d() {
            super();
        }

        protected abstract Object b();

        protected abstract void c();

        public Object call() {
            Object v1_2;
            int v0 = 1;
            try {
                v1_2 = this.b();
                goto label_2;
            }
            catch(Throwable v0_1) {
                v1_3 = v0_1;
                v0 = 0;
            }
            catch(IOException v1) {
                try {
                    throw new c(v1);
                }
                catch(Throwable v1_3) {
                }
            }
            catch(c v1_1) {
                try {
                    throw v1_1;
                }
                catch(Throwable v1_3) {
                }
            }

            try {
                this.c();
            }
            catch(IOException v2) {
                if(v0 != 0) {
                    goto label_27;
                }

                throw new c(v2);
            }

        label_27:
            throw v1_3;
            try {
            label_2:
                this.c();
                return v1_2;
            }
            catch(IOException v0_2) {
                throw new c(v0_2);
            }
        }
    }

    public class e extends BufferedOutputStream {
        private final CharsetEncoder a;

        public e(OutputStream arg1, String arg2, int arg3) {
            super(arg1, arg3);
            this.a = Charset.forName(d.e(arg2)).newEncoder();
        }

        public e a(String arg3) {
            ByteBuffer v3 = this.a.encode(CharBuffer.wrap(((CharSequence)arg3)));
            super.write(v3.array(), 0, v3.limit());
            return this;
        }
    }

    public final URL a;
    private static final String[] b;
    private static b c;
    private HttpURLConnection d;
    private final String e;
    private e f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private String k;
    private int l;

    static {
        d.b = new String[0];
        d.c = b.a;
    }

    public d(CharSequence arg2, String arg3) {
        super();
        this.d = null;
        this.h = true;
        this.i = false;
        this.j = 8192;
        try {
            this.a = new URL(arg2.toString());
        }
        catch(MalformedURLException v2) {
            throw new c(((IOException)v2));
        }

        this.e = arg3;
    }

    public d a(int arg2) {
        this.a().setConnectTimeout(arg2);
        return this;
    }

    public d a(boolean arg2) {
        this.a().setUseCaches(arg2);
        return this;
    }

    public d a(String arg2, String arg3) {
        this.a().setRequestProperty(arg2, arg3);
        return this;
    }

    static int a(d arg0) {
        return arg0.j;
    }

    public static d a(CharSequence arg0, Map arg1, boolean arg2) {
        String v0 = d.a(arg0, arg1);
        if(arg2) {
            v0 = d.a(((CharSequence)v0));
        }

        return d.b(((CharSequence)v0));
    }

    public static String a(CharSequence arg3, Map arg4) {
        String v3 = arg3.toString();
        if(arg4 != null) {
            if(arg4.isEmpty()) {
            }
            else {
                StringBuilder v0 = new StringBuilder(v3);
                d.a(v3, v0);
                d.b(v3, v0);
                Iterator v3_1 = arg4.entrySet().iterator();
                Object v4 = v3_1.next();
                v0.append(((Map$Entry)v4).getKey().toString());
                char v1 = '=';
                v0.append(v1);
                v4 = ((Map$Entry)v4).getValue();
                if(v4 != null) {
                label_19:
                    v0.append(v4);
                }

                do {
                    if(!v3_1.hasNext()) {
                        goto label_32;
                    }

                    v0.append('&');
                    v4 = v3_1.next();
                    v0.append(((Map$Entry)v4).getKey().toString());
                    v0.append(v1);
                    v4 = ((Map$Entry)v4).getValue();
                }
                while(v4 == null);

                goto label_19;
            label_32:
                v3 = v0.toString();
            }
        }

        return v3;
    }

    public static String a(CharSequence arg7) {
        URL v0;
        try {
            v0 = new URL(arg7.toString());
        }
        catch(IOException v7) {
            throw new c(v7);
        }

        String v7_1 = v0.getHost();
        int v1 = v0.getPort();
        if(v1 != -1) {
            v7_1 = v7_1 + ':' + Integer.toString(v1);
        }

        String v3 = v7_1;
        try {
            v7_1 = new URI(v0.getProtocol(), v3, v0.getPath(), v0.getQuery(), null).toASCIIString();
            int v0_1 = v7_1.indexOf(63);
            if(v0_1 > 0) {
                ++v0_1;
                if(v0_1 < v7_1.length()) {
                    v7_1 = v7_1.substring(0, v0_1) + v7_1.substring(v0_1).replace("+", "%2B");
                }
            }
        }
        catch(URISyntaxException v7_2) {
            IOException v0_2 = new IOException("Parsing URI failed");
            v0_2.initCause(((Throwable)v7_2));
            throw new c(v0_2);
        }

        return v7_1;
    }

    private static StringBuilder a(String arg2, StringBuilder arg3) {
        char v1 = '/';
        if(arg2.indexOf(58) + 2 == arg2.lastIndexOf(v1)) {
            arg3.append(v1);
        }

        return arg3;
    }

    public int a(String arg2, int arg3) {
        this.l();
        return this.a().getHeaderFieldInt(arg2, arg3);
    }

    public HttpURLConnection a() {
        if(this.d == null) {
            this.d = this.r();
        }

        return this.d;
    }

    protected d a(InputStream arg8, OutputStream arg9) {
        return new a(arg8, this.h, arg8, arg9) {
            public d a() {
                byte[] v0 = new byte[d.a(this.c)];
                while(true) {
                    int v1 = this.a.read(v0);
                    if(v1 == -1) {
                        break;
                    }

                    this.b.write(v0, 0, v1);
                }

                return this.c;
            }

            public Object b() {
                return this.a();
            }
        }.call();
    }

    public d a(String arg2, Number arg3) {
        return this.a(arg2, null, arg3);
    }

    public d a(String arg1, String arg2, Number arg3) {
        String v3 = arg3 != null ? arg3.toString() : null;
        return this.b(arg1, arg2, v3);
    }

    protected d a(String arg3, String arg4, String arg5) {
        StringBuilder v0 = new StringBuilder();
        v0.append("form-data; name=\"");
        v0.append(arg3);
        if(arg4 != null) {
            v0.append("\"; filename=\"");
            v0.append(arg4);
        }

        v0.append('\"');
        this.f("Content-Disposition", v0.toString());
        if(arg5 != null) {
            this.f("Content-Type", arg5);
        }

        return this.f("\r\n");
    }

    public d a(String arg4, String arg5, String arg6, File arg7) {
        d v4_2;
        BufferedInputStream v1;
        BufferedInputStream v0 = null;
        try {
            v1 = new BufferedInputStream(new FileInputStream(arg7));
            goto label_5;
        }
        catch(Throwable v4) {
        }
        catch(IOException v4_1) {
            goto label_17;
            try {
            label_5:
                v4_2 = this.a(arg4, arg5, arg6, ((InputStream)v1));
                goto label_6;
            }
            catch(Throwable v4) {
                v0 = v1;
            }
            catch(IOException v4_1) {
                v0 = v1;
                try {
                label_17:
                    throw new c(v4_1);
                }
                catch(Throwable v4) {
                }
            }
        }

        if(v0 != null) {
            try {
                ((InputStream)v0).close();
                goto label_22;
            }
            catch(IOException ) {
            label_22:
                throw v4;
            }
        }

        goto label_22;
        try {
        label_6:
            ((InputStream)v1).close();
            return v4_2;
        }
        catch(IOException ) {
            return v4_2;
        }
    }

    public d a(String arg1, String arg2, String arg3, InputStream arg4) {
        try {
            this.n();
            this.a(arg1, arg2, arg3);
            this.a(arg4, this.f);
            return this;
        }
        catch(IOException v1) {
            throw new c(v1);
        }
    }

    public d a(String arg1, String arg2, String arg3, String arg4) {
        try {
            this.n();
            this.a(arg1, arg2, arg3);
            this.f.a(arg4);
            return this;
        }
        catch(IOException v1) {
            throw new c(v1);
        }
    }

    public d a(Map$Entry arg2) {
        return this.a(arg2.getKey(), arg2.getValue());
    }

    public String a(String arg3) {
        ByteArrayOutputStream v0 = this.d();
        try {
            this.a(this.f(), ((OutputStream)v0));
            return v0.toString(d.f(arg3));
        }
        catch(IOException v3) {
            throw new c(v3);
        }
    }

    public static d b(CharSequence arg2) {
        return new d(arg2, "GET");
    }

    private static StringBuilder b(String arg4, StringBuilder arg5) {
        char v0 = '?';
        int v1 = arg4.indexOf(v0);
        int v2 = arg5.length() - 1;
        if(v1 == -1) {
        label_6:
            arg5.append(v0);
        }
        else if(v1 < v2) {
            v0 = '&';
            if(arg4.charAt(v2) != v0) {
                goto label_6;
            }
        }

        return arg5;
    }

    public static d b(CharSequence arg0, Map arg1, boolean arg2) {
        String v0 = d.a(arg0, arg1);
        if(arg2) {
            v0 = d.a(((CharSequence)v0));
        }

        return d.c(((CharSequence)v0));
    }

    public d b(String arg2, String arg3, String arg4) {
        return this.a(arg2, arg3, null, arg4);
    }

    public int b() {
        try {
            this.k();
            return this.a().getResponseCode();
        }
        catch(IOException v0) {
            throw new c(v0);
        }
    }

    public String b(String arg2) {
        this.l();
        return this.a().getHeaderField(arg2);
    }

    public String b(String arg1, String arg2) {
        return this.c(this.b(arg1), arg2);
    }

    public static d c(CharSequence arg2) {
        return new d(arg2, "POST");
    }

    protected String c(String arg9, String arg10) {
        String v0 = null;
        if(arg9 != null) {
            if(arg9.length() == 0) {
            }
            else {
                int v1 = arg9.length();
                int v2 = 59;
                int v3 = arg9.indexOf(v2) + 1;
                if(v3 != 0) {
                    if(v3 == v1) {
                    }
                    else {
                        int v5 = arg9.indexOf(v2, v3);
                        int v6 = -1;
                        if(v5 == v6) {
                        label_16:
                            v5 = v1;
                        }

                        do {
                            if(v3 >= v5) {
                                return v0;
                            }

                            int v7 = arg9.indexOf(61, v3);
                            if(v7 != v6 && v7 < v5 && (arg10.equals(arg9.substring(v3, v7).trim()))) {
                                String v3_1 = arg9.substring(v7 + 1, v5).trim();
                                v7 = v3_1.length();
                                if(v7 != 0) {
                                    if(v7 > 2) {
                                        int v10 = 34;
                                        if(v10 == v3_1.charAt(0)) {
                                            --v7;
                                            if(v10 == v3_1.charAt(v7)) {
                                                return v3_1.substring(1, v7);
                                            }
                                        }
                                    }

                                    return v3_1;
                                }
                            }

                            v3 = v5 + 1;
                            v5 = arg9.indexOf(v2, v3);
                        }
                        while(v5 != v6);

                        goto label_16;
                    }
                }
            }
        }

        return v0;
    }

    public int c(String arg2) {
        return this.a(arg2, -1);
    }

    public boolean c() {
        boolean v0 = 200 == this.b() ? true : false;
        return v0;
    }

    public static d d(CharSequence arg2) {
        return new d(arg2, "PUT");
    }

    protected ByteArrayOutputStream d() {
        int v0 = this.j();
        if(v0 > 0) {
            return new ByteArrayOutputStream(v0);
        }

        return new ByteArrayOutputStream();
    }

    public d d(String arg2) {
        return this.d(arg2, null);
    }

    public d d(String arg3, String arg4) {
        if(arg4 != null && arg4.length() > 0) {
            StringBuilder v1 = new StringBuilder();
            v1.append(arg3);
            v1.append("; charset=");
            v1.append(arg4);
            return this.a("Content-Type", v1.toString());
        }

        return this.a("Content-Type", arg3);
    }

    public static d e(CharSequence arg2) {
        return new d(arg2, "DELETE");
    }

    static String e(String arg0) {
        return d.f(arg0);
    }

    public d e(String arg2, String arg3) {
        return this.b(arg2, null, arg3);
    }

    public String e() {
        return this.a(this.h());
    }

    private static String f(String arg1) {
        if(arg1 != null && arg1.length() > 0) {
            return arg1;
        }

        return "UTF-8";
    }

    public d f(String arg2, String arg3) {
        return this.f(((CharSequence)arg2)).f(": ").f(((CharSequence)arg3)).f("\r\n");
    }

    public d f(CharSequence arg2) {
        try {
            this.m();
            this.f.a(arg2.toString());
            return this;
        }
        catch(IOException v2) {
            throw new c(v2);
        }
    }

    public BufferedInputStream f() {
        return new BufferedInputStream(this.g(), this.j);
    }

    public InputStream g() {
        InputStream v0_1;
        if(this.b() < 400) {
            try {
                v0_1 = this.a().getInputStream();
            }
            catch(IOException v0) {
                throw new c(v0);
            }
        }
        else {
            v0_1 = this.a().getErrorStream();
            if(v0_1 == null) {
                try {
                    v0_1 = this.a().getInputStream();
                }
                catch(IOException v0) {
                    throw new c(v0);
                }
            }
        }

        if(this.i) {
            if(!"gzip".equals(this.i())) {
            }
            else {
                try {
                    return new GZIPInputStream(v0_1);
                }
                catch(IOException v0) {
                    throw new c(v0);
                }
            }
        }

        return v0_1;
    }

    public String h() {
        return this.b("Content-Type", "charset");
    }

    public String i() {
        return this.b("Content-Encoding");
    }

    public int j() {
        return this.c("Content-Length");
    }

    protected d k() {
        if(this.f == null) {
            return this;
        }

        if(this.g) {
            this.f.a("\r\n--00content0boundary00--\r\n");
        }

        if(this.h) {
            try {
                this.f.close();
            }
            catch(IOException ) {
            }
        }
        else {
            this.f.close();
        }

        this.f = null;
        return this;
    }

    protected d l() {
        try {
            return this.k();
        }
        catch(IOException v0) {
            throw new c(v0);
        }
    }

    protected d m() {
        if(this.f != null) {
            return this;
        }

        this.a().setDoOutput(true);
        this.f = new e(this.a().getOutputStream(), this.c(this.a().getRequestProperty("Content-Type"), "charset"), this.j);
        return this;
    }

    protected d n() {
        String v1;
        e v0;
        if(!this.g) {
            this.g = true;
            this.d("multipart/form-data; boundary=00content0boundary00").m();
            v0 = this.f;
            v1 = "--00content0boundary00\r\n";
        }
        else {
            v0 = this.f;
            v1 = "\r\n--00content0boundary00\r\n";
        }

        v0.a(v1);
        return this;
    }

    public URL o() {
        return this.a().getURL();
    }

    public String p() {
        return this.a().getRequestMethod();
    }

    private Proxy q() {
        return new Proxy(Proxy$Type.HTTP, new InetSocketAddress(this.k, this.l));
    }

    private HttpURLConnection r() {
        try {
            HttpURLConnection v0_1 = this.k != null ? d.c.a(this.a, this.q()) : d.c.a(this.a);
            v0_1.setRequestMethod(this.e);
            return v0_1;
        }
        catch(IOException v0) {
            throw new c(v0);
        }
    }

    public String toString() {
        return this.p() + ' ' + this.o();
    }
}

