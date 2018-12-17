package c.a.a.a.a.d;

import android.content.Context;
import c.a.a.a.a.b.i;
import c.a.a.a.a.b.r;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class h implements c {
    private final Context a;
    private final File b;
    private final String c;
    private final File d;
    private r e;
    private File f;

    public h(Context arg1, File arg2, String arg3, String arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg4;
        this.d = new File(this.b, arg3);
        this.e = new r(this.d);
        this.e();
    }

    private void a(File arg5, File arg6) {
        int v0_1;
        OutputStream v6_1;
        FileInputStream v1;
        Closeable v0 = null;
        try {
            v1 = new FileInputStream(arg5);
        }
        catch(Throwable v6) {
            Closeable v1_1 = v0;
            goto label_22;
        }

        try {
            v6_1 = this.a(arg6);
            v0_1 = 1024;
        }
        catch(Throwable v6) {
            goto label_22;
        }

        try {
            i.a(((InputStream)v1), v6_1, new byte[v0_1]);
            goto label_7;
        }
        catch(Throwable v0_2) {
            Throwable v3 = v0_2;
            OutputStream v0_3 = v6_1;
            v6 = v3;
        }

    label_22:
        i.a(((Closeable)v1), "Failed to close file input stream");
        i.a(v0, "Failed to close output stream");
        arg5.delete();
        throw v6;
    label_7:
        i.a(((Closeable)v1), "Failed to close file input stream");
        i.a(((Closeable)v6_1), "Failed to close output stream");
        arg5.delete();
    }

    public OutputStream a(File arg2) {
        return new FileOutputStream(arg2);
    }

    public int a() {
        return this.e.a();
    }

    public List a(int arg6) {
        ArrayList v0 = new ArrayList();
        File[] v1 = this.f.listFiles();
        int v2 = v1.length;
        int v3 = 0;
        while(v3 < v2) {
            ((List)v0).add(v1[v3]);
            if(((List)v0).size() >= arg6) {
            }
            else {
                ++v3;
                continue;
            }

            break;
        }

        return ((List)v0);
    }

    public void a(String arg4) {
        this.e.close();
        this.a(this.d, new File(this.f, arg4));
        this.e = new r(this.d);
    }

    public void a(List arg7) {
        Iterator v7 = arg7.iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            i.a(this.a, String.format("deleting sent analytics file %s", ((File)v0).getName()));
            ((File)v0).delete();
        }
    }

    public void a(byte[] arg2) {
        this.e.a(arg2);
    }

    public boolean a(int arg2, int arg3) {
        return this.e.a(arg2, arg3);
    }

    public boolean b() {
        return this.e.b();
    }

    public List c() {
        return Arrays.asList(this.f.listFiles());
    }

    public void d() {
        try {
            this.e.close();
            goto label_2;
        }
        catch(IOException ) {
        label_2:
            this.d.delete();
            return;
        }
    }

    private void e() {
        this.f = new File(this.b, this.c);
        if(!this.f.exists()) {
            this.f.mkdirs();
        }
    }
}

