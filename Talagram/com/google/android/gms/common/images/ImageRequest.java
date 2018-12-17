package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.images.internal.CrossFadingDrawable;
import com.google.android.gms.common.images.internal.ImageUtils;
import com.google.android.gms.common.images.internal.LoadingImageView;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache$PostProcessedResource;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.PlatformVersion;
import java.lang.ref.WeakReference;

public abstract class ImageRequest {
    public final class ImageViewImageRequest extends ImageRequest {
        private WeakReference zzpo;

        public ImageViewImageRequest(ImageView arg2, int arg3) {
            super(null, arg3);
            Asserts.checkNotNull(arg2);
            this.zzpo = new WeakReference(arg2);
        }

        public ImageViewImageRequest(ImageView arg2, Uri arg3) {
            super(arg3, 0);
            Asserts.checkNotNull(arg2);
            this.zzpo = new WeakReference(arg2);
        }

        public final boolean equals(Object arg4) {
            if(!(arg4 instanceof ImageViewImageRequest)) {
                return 0;
            }

            if(this == (((ImageViewImageRequest)arg4))) {
                return 1;
            }

            Object v2 = this.zzpo.get();
            arg4 = ((ImageViewImageRequest)arg4).zzpo.get();
            if(arg4 != null && v2 != null && (Objects.equal(arg4, v2))) {
                return 1;
            }

            return 0;
        }

        public final int hashCode() {
            return 0;
        }

        protected final void loadImage(Drawable arg6, boolean arg7, boolean arg8, boolean arg9) {
            CrossFadingDrawable v6;
            Object v0 = this.zzpo.get();
            if(v0 != null) {
                int v1 = 0;
                int v2 = (arg8) || (arg9) ? 0 : 1;
                if(v2 != 0 && ((v0 instanceof LoadingImageView))) {
                    int v3 = v0.getLoadedNoDataPlaceholderResId();
                    if(this.mNoDataPlaceholderResId != 0) {
                        if(v3 != this.mNoDataPlaceholderResId) {
                            goto label_18;
                        }

                        return;
                    }
                }

            label_18:
                arg7 = ((ImageRequest)this).shouldCrossFade(arg7, arg8);
                if((this.mUseNewDrawable) && arg6 != null) {
                    arg6 = arg6.getConstantState().newDrawable();
                }

                if(arg7) {
                    v6 = ((ImageRequest)this).createTransitionDrawable(((ImageView)v0).getDrawable(), arg6);
                }

                ((ImageView)v0).setImageDrawable(((Drawable)v6));
                if((v0 instanceof LoadingImageView)) {
                    Uri v8 = arg9 ? this.zzpk.uri : null;
                    ((LoadingImageView)v0).setLoadedUri(v8);
                    if(v2 != 0) {
                        v1 = this.mNoDataPlaceholderResId;
                    }

                    ((LoadingImageView)v0).setLoadedNoDataPlaceholderResId(v1);
                }

                if(!arg7) {
                    return;
                }

                v6.startTransition(250);
            }
        }
    }

    public final class ListenerImageRequest extends ImageRequest {
        private WeakReference zzpp;

        public ListenerImageRequest(OnImageLoadedListener arg2, Uri arg3) {
            super(arg3, 0);
            Asserts.checkNotNull(arg2);
            this.zzpp = new WeakReference(arg2);
        }

        public final boolean equals(Object arg5) {
            if(!(arg5 instanceof ListenerImageRequest)) {
                return 0;
            }

            if(this == (((ListenerImageRequest)arg5))) {
                return 1;
            }

            Object v2 = this.zzpp.get();
            Object v3 = ((ListenerImageRequest)arg5).zzpp.get();
            if(v3 != null && v2 != null && (Objects.equal(v3, v2)) && (Objects.equal(((ListenerImageRequest)arg5).zzpk, this.zzpk))) {
                return 1;
            }

            return 0;
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{this.zzpk});
        }

        protected final void loadImage(Drawable arg1, boolean arg2, boolean arg3, boolean arg4) {
            if(!arg3) {
                Object v2 = this.zzpp.get();
                if(v2 != null) {
                    ((OnImageLoadedListener)v2).onImageLoaded(this.zzpk.uri, arg1, arg4);
                }
            }
        }
    }

    public final class PostProcessingFlags {
        public static final int CIRCLE_CROP = 1;

        public PostProcessingFlags() {
            super();
        }
    }

    public final class TextViewImageRequest extends ImageRequest {
        public static final int POSITION_BOTTOM = 3;
        public static final int POSITION_END = 2;
        public static final int POSITION_START = 0;
        public static final int POSITION_TOP = 1;
        private WeakReference zzpq;
        private int zzpr;

        public TextViewImageRequest(TextView arg3, int arg4, int arg5) {
            super(null, arg5);
            this.zzpr = -1;
            Asserts.checkNotNull(arg3);
            boolean v5 = true;
            if(arg4 == 0 || arg4 == 1 || arg4 == 2 || arg4 == 3) {
                v5 = false;
            }
            else {
            }

            StringBuilder v1 = new StringBuilder(29);
            v1.append("Invalid position: ");
            v1.append(arg4);
            Asserts.checkState(v5, v1.toString());
            this.zzpq = new WeakReference(arg3);
            this.zzpr = arg4;
        }

        public TextViewImageRequest(TextView arg3, int arg4, Uri arg5) {
            super(arg5, 0);
            this.zzpr = -1;
            Asserts.checkNotNull(arg3);
            boolean v5 = true;
            if(arg4 == 0 || arg4 == 1 || arg4 == 2 || arg4 == 3) {
                v5 = false;
            }
            else {
            }

            StringBuilder v1 = new StringBuilder(29);
            v1.append("Invalid position: ");
            v1.append(arg4);
            Asserts.checkState(v5, v1.toString());
            this.zzpq = new WeakReference(arg3);
            this.zzpr = arg4;
        }

        public final boolean equals(Object arg5) {
            if(!(arg5 instanceof TextViewImageRequest)) {
                return 0;
            }

            if(this == (((TextViewImageRequest)arg5))) {
                return 1;
            }

            Object v2 = this.zzpq.get();
            Object v3 = ((TextViewImageRequest)arg5).zzpq.get();
            if(v3 != null && v2 != null && (Objects.equal(v3, v2)) && (Objects.equal(Integer.valueOf(((TextViewImageRequest)arg5).zzpr), Integer.valueOf(this.zzpr)))) {
                return 1;
            }

            return 0;
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{Integer.valueOf(this.zzpr)});
        }

        protected final void loadImage(Drawable arg6, boolean arg7, boolean arg8, boolean arg9) {
            CrossFadingDrawable v6;
            Object v9 = this.zzpq.get();
            if(v9 != null) {
                int v0 = this.zzpr;
                arg7 = ((ImageRequest)this).shouldCrossFade(arg7, arg8);
                Drawable[] v8 = PlatformVersion.isAtLeastJellyBeanMR1() ? ((TextView)v9).getCompoundDrawablesRelative() : ((TextView)v9).getCompoundDrawables();
                Drawable v1 = v8[v0];
                if(arg7) {
                    v6 = ((ImageRequest)this).createTransitionDrawable(v1, arg6);
                }

                v1 = v0 == 0 ? ((Drawable)v6) : v8[0];
                Drawable v2 = v0 == 1 ? ((Drawable)v6) : v8[1];
                int v3 = 2;
                Drawable v3_1 = v0 == v3 ? ((Drawable)v6) : v8[v3];
                int v4 = 3;
                Drawable v8_1 = v0 == v4 ? ((Drawable)v6) : v8[v4];
                if(PlatformVersion.isAtLeastJellyBeanMR1()) {
                    ((TextView)v9).setCompoundDrawablesRelativeWithIntrinsicBounds(v1, v2, v3_1, v8_1);
                }
                else {
                    ((TextView)v9).setCompoundDrawablesWithIntrinsicBounds(v1, v2, v3_1, v8_1);
                }

                if(!arg7) {
                    return;
                }

                v6.startTransition(250);
            }
        }
    }

    final class zza {
        public final Uri uri;

        public zza(Uri arg1) {
            super();
            this.uri = arg1;
        }

        public final boolean equals(Object arg2) {
            if(!(arg2 instanceof zza)) {
                return 0;
            }

            if(this == (((zza)arg2))) {
                return 1;
            }

            return Objects.equal(((zza)arg2).uri, this.uri);
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{this.uri});
        }
    }

    protected int mLoadingPlaceholderResId;
    protected int mNoDataPlaceholderResId;
    protected OnImageLoadedListener mOnImageLoadedListener;
    protected int mPostProcessingFlags;
    protected boolean mUseNewDrawable;
    final zza zzpk;
    private boolean zzpl;
    private boolean zzpm;
    private boolean zzpn;

    public ImageRequest(Uri arg3, int arg4) {
        super();
        this.mLoadingPlaceholderResId = 0;
        this.mNoDataPlaceholderResId = 0;
        this.mUseNewDrawable = false;
        this.zzpl = true;
        this.zzpm = false;
        this.zzpn = true;
        this.zzpk = new zza(arg3);
        this.mNoDataPlaceholderResId = arg4;
    }

    protected CrossFadingDrawable createTransitionDrawable(Drawable arg2, Drawable arg3) {
        if(arg2 == null) {
            arg2 = null;
        }
        else if((arg2 instanceof CrossFadingDrawable)) {
            arg2 = ((CrossFadingDrawable)arg2).getEndDrawable();
        }

        return new CrossFadingDrawable(arg2, arg3);
    }

    protected Drawable frameDrawableInCircle(Resources arg1, Drawable arg2) {
        return ImageUtils.frameDrawableInCircle(arg1, arg2);
    }

    protected abstract void loadImage(Drawable arg1, boolean arg2, boolean arg3, boolean arg4);

    public void setCrossFadeAlwaysEnabled(boolean arg1) {
        this.zzpm = arg1;
        if(arg1) {
            this.setCrossFadeEnabled(true);
        }
    }

    public void setCrossFadeEnabled(boolean arg1) {
        this.zzpl = arg1;
    }

    public void setLoadingPlaceholder(int arg1) {
        this.mLoadingPlaceholderResId = arg1;
    }

    public void setLoadingPlaceholderEnabled(boolean arg1) {
        this.zzpn = arg1;
    }

    public void setNoDataPlaceholder(int arg1) {
        this.mNoDataPlaceholderResId = arg1;
    }

    public void setOnImageLoadedListener(OnImageLoadedListener arg1) {
        this.mOnImageLoadedListener = arg1;
    }

    public void setPostProcessingFlags(int arg1) {
        this.mPostProcessingFlags = arg1;
    }

    public void setUseNewDrawable(boolean arg1) {
        this.mUseNewDrawable = arg1;
    }

    protected boolean shouldCrossFade(boolean arg2, boolean arg3) {
        if((this.zzpl) && !arg3 && (!arg2 || (this.zzpm))) {
            return 1;
        }

        return 0;
    }

    final void zza(Context arg3, PostProcessedResourceCache arg4, boolean arg5) {
        Drawable v3 = this.mNoDataPlaceholderResId != 0 ? this.zza(arg3, arg4, this.mNoDataPlaceholderResId) : null;
        if(this.mOnImageLoadedListener != null) {
            this.mOnImageLoadedListener.onImageLoaded(this.zzpk.uri, v3, false);
        }

        this.loadImage(v3, arg5, false, false);
    }

    final void zza(Context arg3, Bitmap arg4, boolean arg5) {
        Asserts.checkNotNull(arg4);
        if((this.mPostProcessingFlags & 1) != 0) {
            arg4 = ImageUtils.frameBitmapInCircle(arg4);
        }

        BitmapDrawable v0 = new BitmapDrawable(arg3.getResources(), arg4);
        if(this.mOnImageLoadedListener != null) {
            this.mOnImageLoadedListener.onImageLoaded(this.zzpk.uri, ((Drawable)v0), true);
        }

        this.loadImage(((Drawable)v0), arg5, false, true);
    }

    final void zza(Context arg3, PostProcessedResourceCache arg4) {
        if(this.zzpn) {
            Drawable v0 = null;
            if(this.mLoadingPlaceholderResId != 0) {
                v0 = this.zza(arg3, arg4, this.mLoadingPlaceholderResId);
            }

            this.loadImage(v0, false, true, false);
        }
    }

    private final Drawable zza(Context arg3, PostProcessedResourceCache arg4, int arg5) {
        Resources v3 = arg3.getResources();
        if(this.mPostProcessingFlags > 0) {
            PostProcessedResource v0 = new PostProcessedResource(arg5, this.mPostProcessingFlags);
            Object v1 = arg4.get(v0);
            if(v1 == null) {
                Drawable v5 = v3.getDrawable(arg5);
                Drawable v1_1 = (this.mPostProcessingFlags & 1) != 0 ? this.frameDrawableInCircle(v3, v5) : v5;
                arg4.put(v0, v1_1);
            }

            return ((Drawable)v1);
        }

        return v3.getDrawable(arg5);
    }
}

