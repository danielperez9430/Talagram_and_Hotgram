package com.e.a;

import java.io.PrintWriter;

public class s {
    public final int a;
    public final int b;
    public final long c;
    public final long d;
    public final long e;
    public final long f;
    public final long g;
    public final long h;
    public final long i;
    public final long j;
    public final int k;
    public final int l;
    public final int m;
    public final long n;

    public s(int arg4, int arg5, long arg6, long arg8, long arg10, long arg12, long arg14, long arg16, long arg18, long arg20, int arg22, int arg23, int arg24, long arg25) {
        super();
        this.a = arg4;
        this.b = arg5;
        this.c = arg6;
        this.d = arg8;
        this.e = arg10;
        this.f = arg12;
        this.g = arg14;
        this.h = arg16;
        this.i = arg18;
        this.j = arg20;
        this.k = arg22;
        this.l = arg23;
        this.m = arg24;
        this.n = arg25;
    }

    public void a(PrintWriter arg3) {
        arg3.println("===============BEGIN PICASSO STATS ===============");
        arg3.println("Memory Cache Stats");
        arg3.print("  Max Cache Size: ");
        arg3.println(this.a);
        arg3.print("  Cache Size: ");
        arg3.println(this.b);
        arg3.print("  Cache % Full: ");
        arg3.println(((int)Math.ceil(((double)((((float)this.b)) / (((float)this.a)) * 100f)))));
        arg3.print("  Cache Hits: ");
        arg3.println(this.c);
        arg3.print("  Cache Misses: ");
        arg3.println(this.d);
        arg3.println("Network Stats");
        arg3.print("  Download Count: ");
        arg3.println(this.k);
        arg3.print("  Total Download Size: ");
        arg3.println(this.e);
        arg3.print("  Average Download Size: ");
        arg3.println(this.h);
        arg3.println("Bitmap Stats");
        arg3.print("  Total Bitmaps Decoded: ");
        arg3.println(this.l);
        arg3.print("  Total Bitmap Size: ");
        arg3.println(this.f);
        arg3.print("  Total Transformed Bitmaps: ");
        arg3.println(this.m);
        arg3.print("  Total Transformed Bitmap Size: ");
        arg3.println(this.g);
        arg3.print("  Average Bitmap Size: ");
        arg3.println(this.i);
        arg3.print("  Average Transformed Bitmap Size: ");
        arg3.println(this.j);
        arg3.println("===============END PICASSO STATS ===============");
        arg3.flush();
    }

    public String toString() {
        return "StatsSnapshot{maxSize=" + this.a + ", size=" + this.b + ", cacheHits=" + this.c + ", cacheMisses=" + this.d + ", downloadCount=" + this.k + ", totalDownloadSize=" + this.e + ", averageDownloadSize=" + this.h + ", totalOriginalBitmapSize=" + this.f + ", totalTransformedBitmapSize=" + this.g + ", averageOriginalBitmapSize=" + this.i + ", averageTransformedBitmapSize=" + this.j + ", originalBitmapCount=" + this.l + ", transformedBitmapCount=" + this.m + ", timeStamp=" + this.n + '}';
    }
}

