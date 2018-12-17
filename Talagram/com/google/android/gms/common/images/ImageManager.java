package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.f.g;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache;
import com.google.android.gms.common.internal.Asserts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    @KeepName final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        private final ArrayList zzpf;

        ImageReceiver(ImageManager arg2, Uri arg3) {
            this.zzpg = arg2;
            super(new Handler(Looper.getMainLooper()));
            this.mUri = arg3;
            this.zzpf = new ArrayList();
        }

        public final void onReceiveResult(int arg4, Bundle arg5) {
            ImageManager.zzf(this.zzpg).execute(new zzb(this.zzpg, this.mUri, arg5.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        static ArrayList zza(ImageReceiver arg0) {
            return arg0.zzpf;
        }

        public final void zza(ImageRequest arg2) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zzpf.add(arg2);
        }

        public final void zzb(ImageRequest arg2) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zzpf.remove(arg2);
        }

        public final void zzco() {
            Intent v0 = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            v0.putExtra("com.google.android.gms.extras.uri", this.mUri);
            v0.putExtra("com.google.android.gms.extras.resultReceiver", ((Parcelable)this));
            v0.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.zzb(this.zzpg).sendBroadcast(v0);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri arg1, Drawable arg2, boolean arg3);
    }

    final class zza extends g {
        public zza(Context arg3) {
            Object v0 = arg3.getSystemService("activity");
            int v1 = 1048576;
            int v3 = (arg3.getApplicationInfo().flags & v1) != 0 ? 1 : 0;
            v3 = v3 != 0 ? ((ActivityManager)v0).getLargeMemoryClass() : ((ActivityManager)v0).getMemoryClass();
            super(((int)((((float)(v3 * v1))) * 0.33f)));
        }

        protected final void entryRemoved(boolean arg1, Object arg2, Object arg3, Object arg4) {
            super.entryRemoved(arg1, arg2, arg3, arg4);
        }

        protected final int sizeOf(Object arg1, Object arg2) {
            return ((Bitmap)arg2).getHeight() * ((Bitmap)arg2).getRowBytes();
        }
    }

    final class zzb implements Runnable {
        private final Uri mUri;
        private final ParcelFileDescriptor zzph;

        public zzb(ImageManager arg1, Uri arg2, ParcelFileDescriptor arg3) {
            this.zzpg = arg1;
            super();
            this.mUri = arg2;
            this.zzph = arg3;
        }

        public final void run() {
            Bitmap v9;
            boolean v10;
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can\'t be executed in the main thread");
            boolean v2 = false;
            Bitmap v3 = null;
            if(this.zzph != null) {
                try {
                    v3 = BitmapFactory.decodeFileDescriptor(this.zzph.getFileDescriptor());
                }
                catch(OutOfMemoryError v0) {
                    String v4 = String.valueOf(this.mUri);
                    StringBuilder v6 = new StringBuilder(String.valueOf(v4).length() + 34);
                    v6.append("OOM while loading bitmap for uri: ");
                    v6.append(v4);
                    Log.e("ImageManager", v6.toString(), ((Throwable)v0));
                    v2 = true;
                }

                try {
                    this.zzph.close();
                }
                catch(IOException v0_1) {
                    Log.e("ImageManager", "closed failed", ((Throwable)v0_1));
                }

                v10 = v2;
                v9 = v3;
            }
            else {
                v9 = v3;
                v10 = false;
            }

            CountDownLatch v0_2 = new CountDownLatch(1);
            ImageManager.zzg(this.zzpg).post(new zze(this.zzpg, this.mUri, v9, v10, v0_2));
            try {
                v0_2.await();
                return;
            }
            catch(InterruptedException ) {
                String v1 = String.valueOf(this.mUri);
                StringBuilder v3_1 = new StringBuilder(String.valueOf(v1).length() + 32);
                v3_1.append("Latch interrupted while posting ");
                v3_1.append(v1);
                Log.w("ImageManager", v3_1.toString());
                return;
            }
        }
    }

    final class zzc implements Runnable {
        private final ImageRequest zzpi;

        public zzc(ImageManager arg1, ImageRequest arg2) {
            this.zzpg = arg1;
            super();
            this.zzpi = arg2;
        }

        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            Object v0 = ImageManager.zza(this.zzpg).get(this.zzpi);
            if(v0 != null) {
                ImageManager.zza(this.zzpg).remove(this.zzpi);
                ((ImageReceiver)v0).zzb(this.zzpi);
            }

            com.google.android.gms.common.images.ImageRequest$zza v0_1 = this.zzpi.zzpk;
            if(v0_1.uri == null) {
                this.zzpi.zza(ImageManager.zzb(this.zzpg), ImageManager.zzc(this.zzpg), true);
                return;
            }

            Bitmap v1 = ImageManager.zza(this.zzpg, v0_1);
            if(v1 != null) {
                this.zzpi.zza(ImageManager.zzb(this.zzpg), v1, true);
                return;
            }

            Object v1_1 = ImageManager.zzd(this.zzpg).get(v0_1.uri);
            if(v1_1 != null) {
                if(SystemClock.elapsedRealtime() - ((Long)v1_1).longValue() < 3600000) {
                    this.zzpi.zza(ImageManager.zzb(this.zzpg), ImageManager.zzc(this.zzpg), true);
                    return;
                }
                else {
                    ImageManager.zzd(this.zzpg).remove(v0_1.uri);
                }
            }

            this.zzpi.zza(ImageManager.zzb(this.zzpg), ImageManager.zzc(this.zzpg));
            v1_1 = ImageManager.zze(this.zzpg).get(v0_1.uri);
            if(v1_1 == null) {
                ImageReceiver v1_2 = new ImageReceiver(this.zzpg, v0_1.uri);
                ImageManager.zze(this.zzpg).put(v0_1.uri, v1_2);
            }

            ((ImageReceiver)v1_1).zza(this.zzpi);
            if(!(this.zzpi instanceof ListenerImageRequest)) {
                ImageManager.zza(this.zzpg).put(this.zzpi, v1_1);
            }

            Object v2 = ImageManager.zzcm();
            __monitor_enter(v2);
            try {
                if(!ImageManager.zzcn().contains(v0_1.uri)) {
                    ImageManager.zzcn().add(v0_1.uri);
                    ((ImageReceiver)v1_1).zzco();
                }

                __monitor_exit(v2);
                return;
            label_95:
                __monitor_exit(v2);
            }
            catch(Throwable v0_2) {
                goto label_95;
            }

            throw v0_2;
        }
    }

    final class zzd implements ComponentCallbacks2 {
        private final zza zzpa;

        public zzd(zza arg1) {
            super();
            this.zzpa = arg1;
        }

        public final void onConfigurationChanged(Configuration arg1) {
        }

        public final void onLowMemory() {
            this.zzpa.evictAll();
        }

        public final void onTrimMemory(int arg2) {
            if(arg2 >= 60) {
                this.zzpa.evictAll();
                return;
            }

            if(arg2 >= 20) {
                this.zzpa.trimToSize(this.zzpa.size() / 2);
            }
        }
    }

    final class zze implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zzfd;
        private boolean zzpj;

        public zze(ImageManager arg1, Uri arg2, Bitmap arg3, boolean arg4, CountDownLatch arg5) {
            this.zzpg = arg1;
            super();
            this.mUri = arg2;
            this.mBitmap = arg3;
            this.zzpj = arg4;
            this.zzfd = arg5;
        }

        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            int v0 = this.mBitmap != null ? 1 : 0;
            if(ImageManager.zzh(this.zzpg) != null) {
                if(this.zzpj) {
                    ImageManager.zzh(this.zzpg).evictAll();
                    System.gc();
                    this.zzpj = false;
                    ImageManager.zzg(this.zzpg).post(((Runnable)this));
                    return;
                }
                else if(v0 != 0) {
                    ImageManager.zzh(this.zzpg).put(new com.google.android.gms.common.images.ImageRequest$zza(this.mUri), this.mBitmap);
                }
            }

            Object v2 = ImageManager.zze(this.zzpg).remove(this.mUri);
            if(v2 != null) {
                ArrayList v2_1 = ImageReceiver.zza(((ImageReceiver)v2));
                int v3 = v2_1.size();
                int v4;
                for(v4 = 0; v4 < v3; ++v4) {
                    Object v5 = v2_1.get(v4);
                    if(v0 != 0) {
                        ((ImageRequest)v5).zza(ImageManager.zzb(this.zzpg), this.mBitmap, false);
                    }
                    else {
                        ImageManager.zzd(this.zzpg).put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                        ((ImageRequest)v5).zza(ImageManager.zzb(this.zzpg), ImageManager.zzc(this.zzpg), false);
                    }

                    if(!(v5 instanceof ListenerImageRequest)) {
                        ImageManager.zza(this.zzpg).remove(v5);
                    }
                }
            }

            this.zzfd.countDown();
            Object v0_1 = ImageManager.zzcm();
            __monitor_enter(v0_1);
            try {
                ImageManager.zzcn().remove(this.mUri);
                __monitor_exit(v0_1);
                return;
            label_74:
                __monitor_exit(v0_1);
            }
            catch(Throwable v1) {
                goto label_74;
            }

            throw v1;
        }
    }

    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_MEDIUM = 2;
    private final Context mContext;
    private final Handler mHandler;
    private static final Object zzov;
    private static HashSet zzow;
    private static ImageManager zzox;
    private static ImageManager zzoy;
    private final ExecutorService zzoz;
    private final zza zzpa;
    private final PostProcessedResourceCache zzpb;
    private final Map zzpc;
    private final Map zzpd;
    private final Map zzpe;

    static {
        ImageManager.zzov = new Object();
        ImageManager.zzow = new HashSet();
    }

    private ImageManager(Context arg2, boolean arg3) {
        super();
        this.mContext = arg2.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.zzoz = Executors.newFixedThreadPool(4);
        if(arg3) {
            this.zzpa = new zza(this.mContext);
            this.mContext.registerComponentCallbacks(new zzd(this.zzpa));
        }
        else {
            this.zzpa = null;
        }

        this.zzpb = new PostProcessedResourceCache();
        this.zzpc = new HashMap();
        this.zzpd = new HashMap();
        this.zzpe = new HashMap();
    }

    public static ImageManager create(Context arg1) {
        return ImageManager.create(arg1, false);
    }

    public static ImageManager create(Context arg1, boolean arg2) {
        if(arg2) {
            if(ImageManager.zzoy == null) {
                ImageManager.zzoy = new ImageManager(arg1, true);
            }

            return ImageManager.zzoy;
        }

        if(ImageManager.zzox == null) {
            ImageManager.zzox = new ImageManager(arg1, false);
        }

        return ImageManager.zzox;
    }

    public final void loadImage(ImageView arg2, int arg3) {
        this.loadImage(new ImageViewImageRequest(arg2, arg3));
    }

    public final void loadImage(ImageRequest arg2) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zzc(this, arg2).run();
    }

    public final void loadImage(ImageView arg2, Uri arg3) {
        this.loadImage(new ImageViewImageRequest(arg2, arg3));
    }

    public final void loadImage(ImageView arg2, Uri arg3, int arg4) {
        ImageViewImageRequest v0 = new ImageViewImageRequest(arg2, arg3);
        ((ImageRequest)v0).setNoDataPlaceholder(arg4);
        this.loadImage(((ImageRequest)v0));
    }

    public final void loadImage(OnImageLoadedListener arg2, Uri arg3) {
        this.loadImage(new ListenerImageRequest(arg2, arg3));
    }

    public final void loadImage(OnImageLoadedListener arg2, Uri arg3, int arg4) {
        ListenerImageRequest v0 = new ListenerImageRequest(arg2, arg3);
        ((ImageRequest)v0).setNoDataPlaceholder(arg4);
        this.loadImage(((ImageRequest)v0));
    }

    static Bitmap zza(ImageManager arg0, com.google.android.gms.common.images.ImageRequest$zza arg1) {
        return arg0.zza(arg1);
    }

    private final Bitmap zza(com.google.android.gms.common.images.ImageRequest$zza arg2) {
        if(this.zzpa == null) {
            return null;
        }

        return this.zzpa.get(arg2);
    }

    static Map zza(ImageManager arg0) {
        return arg0.zzpc;
    }

    static Context zzb(ImageManager arg0) {
        return arg0.mContext;
    }

    static PostProcessedResourceCache zzc(ImageManager arg0) {
        return arg0.zzpb;
    }

    static Object zzcm() {
        return ImageManager.zzov;
    }

    static HashSet zzcn() {
        return ImageManager.zzow;
    }

    static Map zzd(ImageManager arg0) {
        return arg0.zzpe;
    }

    static Map zze(ImageManager arg0) {
        return arg0.zzpd;
    }

    static ExecutorService zzf(ImageManager arg0) {
        return arg0.zzoz;
    }

    static Handler zzg(ImageManager arg0) {
        return arg0.mHandler;
    }

    static zza zzh(ImageManager arg0) {
        return arg0.zzpa;
    }
}

