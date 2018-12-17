package net.hockeyapp.android.e;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class j {
    private static final char[] a;
    private boolean b;
    private boolean c;
    private File d;
    private OutputStream e;
    private String f;

    static {
        j.a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    public j(File arg5) {
        super();
        int v0 = 0;
        this.c = false;
        this.b = false;
        this.d = arg5;
        try {
            this.e = new FileOutputStream(this.d);
        }
        catch(IOException v5) {
            e.b("Failed to open temp file", ((Throwable)v5));
        }

        StringBuilder v5_1 = new StringBuilder();
        Random v1 = new Random();
        while(v0 < 30) {
            v5_1.append(j.a[v1.nextInt(j.a.length)]);
            ++v0;
        }

        this.f = v5_1.toString();
    }

    public void a(OutputStream arg5) {
        this.c();
        FileInputStream v0 = new FileInputStream(this.d);
        BufferedOutputStream v1 = new BufferedOutputStream(arg5);
        byte[] v5 = new byte[4096];
        while(true) {
            int v2 = v0.read(v5);
            if(v2 == -1) {
                break;
            }

            v1.write(v5, 0, v2);
        }

        v0.close();
        v1.flush();
        v1.close();
        this.d.delete();
        this.d = null;
    }

    public void a(String arg4, String arg5) {
        this.b();
        OutputStream v0 = this.e;
        StringBuilder v1 = new StringBuilder();
        v1.append("Content-Disposition: form-data; name=\"");
        v1.append(arg4);
        v1.append("\"\r\n");
        v0.write(v1.toString().getBytes());
        this.e.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes());
        this.e.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes());
        this.e.write(arg5.getBytes());
        OutputStream v4 = this.e;
        StringBuilder v5 = new StringBuilder();
        v5.append("\r\n--");
        v5.append(this.f);
        v5.append("\r\n");
        v4.write(v5.toString().getBytes());
    }

    public void a(String arg7, String arg8, InputStream arg9, boolean arg10) {
        this.a(arg7, arg8, arg9, "application/octet-stream", arg10);
    }

    public String a() {
        return this.f;
    }

    public void a(String arg4, String arg5, InputStream arg6, String arg7, boolean arg8) {
        this.b();
        try {
            arg7 = "Content-Type: " + arg7 + "\r\n";
            OutputStream v0_1 = this.e;
            StringBuilder v1 = new StringBuilder();
            v1.append("Content-Disposition: form-data; name=\"");
            v1.append(arg4);
            v1.append("\"; filename=\"");
            v1.append(arg5);
            v1.append("\"\r\n");
            v0_1.write(v1.toString().getBytes());
            this.e.write(arg7.getBytes());
            this.e.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] v4_1 = new byte[4096];
            while(true) {
                int v5 = arg6.read(v4_1);
                if(v5 == -1) {
                    break;
                }

                this.e.write(v4_1, 0, v5);
            }

            this.e.flush();
            if(arg8) {
                this.c();
            }
            else {
                goto label_44;
            }
        }
        catch(Throwable v4) {
            goto label_59;
        }

        try {
        label_56:
            arg6.close();
        }
        catch(IOException ) {
        }

        return;
        try {
        label_44:
            OutputStream v4_2 = this.e;
            StringBuilder v5_1 = new StringBuilder();
            v5_1.append("\r\n--");
            v5_1.append(this.f);
            v5_1.append("\r\n");
            v4_2.write(v5_1.toString().getBytes());
            goto label_56;
        }
        catch(Throwable v4) {
            goto label_59;
        }

        return;
        try {
        label_59:
            arg6.close();
            goto label_60;
        }
        catch(IOException ) {
        label_60:
            throw v4;
        }
    }

    public void b() {
        if(!this.c) {
            OutputStream v0 = this.e;
            StringBuilder v1 = new StringBuilder();
            v1.append("--");
            v1.append(this.f);
            v1.append("\r\n");
            v0.write(v1.toString().getBytes());
        }

        this.c = true;
    }

    public void c() {
        if(this.b) {
            return;
        }

        try {
            OutputStream v0_1 = this.e;
            StringBuilder v1 = new StringBuilder();
            v1.append("\r\n--");
            v1.append(this.f);
            v1.append("--\r\n");
            v0_1.write(v1.toString().getBytes());
            this.e.flush();
            this.e.close();
            this.e = null;
        }
        catch(IOException v0) {
            e.b("Failed to close temp file", ((Throwable)v0));
        }

        this.b = true;
    }

    public long d() {
        this.c();
        return this.d.length();
    }
}

