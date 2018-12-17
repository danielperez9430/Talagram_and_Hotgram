package com.google.android.gms.common.images.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.gms.base.R$styleable;
import com.google.android.gms.common.images.ImageManager$OnImageLoadedListener;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.images.ImageRequest$ImageViewImageRequest;
import com.google.android.gms.common.images.ImageRequest;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.util.PlatformVersion;

public final class LoadingImageView extends ImageView {
    public interface ClipPathProvider {
        Path getClipPath(int arg1, int arg2);
    }

    public static final int ASPECT_RATIO_ADJUST_HEIGHT = 2;
    public static final int ASPECT_RATIO_ADJUST_NONE = 0;
    public static final int ASPECT_RATIO_ADJUST_WIDTH = 1;
    private OnImageLoadedListener mOnImageLoadedListener;
    private int mPostProcessingFlags;
    private boolean zzpl;
    private boolean zzpm;
    private static ImageManager zzqm;
    private Uri zzqn;
    private int zzqo;
    private boolean zzqp;
    private int zzqq;
    private ClipPathProvider zzqr;
    private int zzqs;
    private float zzqt;

    public LoadingImageView(Context arg3) {
        this(arg3, null, 0);
    }

    public LoadingImageView(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4, arg5);
        this.zzqo = 0;
        this.zzpl = true;
        this.zzpm = false;
        this.zzqp = false;
        this.zzqq = 0;
        this.mPostProcessingFlags = 0;
        this.zzqs = 0;
        this.zzqt = 1f;
        TypedArray v3 = arg3.obtainStyledAttributes(arg4, styleable.LoadingImageView);
        this.zzqs = v3.getInt(styleable.LoadingImageView_imageAspectRatioAdjust, 0);
        this.zzqt = v3.getFloat(styleable.LoadingImageView_imageAspectRatio, 1f);
        this.setCircleCropEnabled(v3.getBoolean(styleable.LoadingImageView_circleCrop, false));
        v3.recycle();
    }

    public LoadingImageView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public final void clearAspectRatioAdjust() {
        if(this.zzqs != 0) {
            this.zzqs = 0;
            this.requestLayout();
        }
    }

    public final void clearImage() {
        this.loadUri(null);
        this.zzqp = true;
    }

    public final int getLoadedNoDataPlaceholderResId() {
        return this.zzqo;
    }

    public final Uri getLoadedUri() {
        return this.zzqn;
    }

    public final void loadUri(Uri arg3) {
        this.loadUri(arg3, 0, true, false);
    }

    public final void loadUri(Uri arg5, int arg6, boolean arg7, boolean arg8) {
        boolean v2;
        boolean v0 = true;
        if(arg5 != null) {
            v2 = arg5.equals(this.zzqn);
        }
        else if(this.zzqn == null) {
            v2 = true;
        }
        else {
            v2 = false;
        }

        if(v2) {
            if(this.zzqn != null) {
                this.zzc(true);
                return;
            }
            else if(this.zzqo == arg6) {
                this.zzc(false);
                return;
            }
        }

        if(LoadingImageView.zzqm == null) {
            LoadingImageView.zzqm = ImageManager.create(this.getContext(), this.getContext().getApplicationContext().getPackageName().equals("com.google.android.play.games"));
        }

        if(!this.zzpm) {
            if(this.zzqp) {
            }
            else {
                v0 = false;
            }
        }

        this.zzqp = false;
        ImageViewImageRequest v1 = new ImageViewImageRequest(((ImageView)this), arg5);
        ((ImageRequest)v1).setNoDataPlaceholder(arg6);
        ((ImageRequest)v1).setCrossFadeEnabled(this.zzpl);
        ((ImageRequest)v1).setCrossFadeAlwaysEnabled(v0);
        ((ImageRequest)v1).setLoadingPlaceholderEnabled(arg7);
        ((ImageRequest)v1).setPostProcessingFlags(this.mPostProcessingFlags);
        ((ImageRequest)v1).setOnImageLoadedListener(this.mOnImageLoadedListener);
        ((ImageRequest)v1).setUseNewDrawable(arg8);
        LoadingImageView.zzqm.loadImage(((ImageRequest)v1));
    }

    public final void loadUri(Uri arg3, int arg4) {
        this.loadUri(arg3, arg4, true, false);
    }

    public final void loadUri(Uri arg2, int arg3, boolean arg4) {
        this.loadUri(arg2, arg3, arg4, false);
    }

    protected final void onDraw(Canvas arg4) {
        if(this.zzqr != null) {
            arg4.clipPath(this.zzqr.getClipPath(this.getWidth(), this.getHeight()));
        }

        super.onDraw(arg4);
        if(this.zzqq != 0) {
            arg4.drawColor(this.zzqq);
        }
    }

    protected final void onMeasure(int arg2, int arg3) {
        super.onMeasure(arg2, arg3);
        switch(this.zzqs) {
            case 1: {
                goto label_10;
            }
            case 2: {
                goto label_4;
            }
        }

        return;
    label_4:
        arg2 = this.getMeasuredWidth();
        arg3 = ((int)((((float)arg2)) / this.zzqt));
        goto label_15;
    label_10:
        arg3 = this.getMeasuredHeight();
        arg2 = ((int)((((float)arg3)) * this.zzqt));
    label_15:
        this.setMeasuredDimension(arg2, arg3);
    }

    public final void setCircleCropEnabled(boolean arg1) {
        int v1 = arg1 ? this.mPostProcessingFlags | 1 : this.mPostProcessingFlags & -2;
        this.mPostProcessingFlags = v1;
    }

    public final void setClipPathProvider(ClipPathProvider arg2) {
        this.zzqr = arg2;
        if(!PlatformVersion.isAtLeastJellyBean()) {
            this.setLayerType(1, null);
        }
    }

    public final void setCrossFadeAlwaysEnabled(boolean arg1) {
        this.zzpm = arg1;
    }

    public final void setCrossFadeEnabled(boolean arg1) {
        this.zzpl = arg1;
    }

    public final void setImageAspectRatioAdjust(int arg4, float arg5) {
        boolean v0 = false;
        boolean v2 = arg4 == 0 || arg4 == 1 || arg4 == 2 ? true : false;
        Asserts.checkState(v2);
        if(arg5 > 0f) {
            v0 = true;
        }

        Asserts.checkState(v0);
        this.zzqs = arg4;
        this.zzqt = arg5;
        this.requestLayout();
    }

    public final void setLoadedNoDataPlaceholderResId(int arg1) {
        this.zzqo = arg1;
    }

    public final void setLoadedUri(Uri arg1) {
        this.zzqn = arg1;
    }

    public final void setOnImageLoadedListener(OnImageLoadedListener arg1) {
        this.mOnImageLoadedListener = arg1;
    }

    public final void setTintColor(int arg1) {
        this.zzqq = arg1;
        ColorFilter v1 = this.zzqq != 0 ? ColorFilters.COLOR_FILTER_BW : null;
        this.setColorFilter(v1);
        this.invalidate();
    }

    public final void setTintColorId(int arg2) {
        if(arg2 > 0) {
            Resources v0 = this.getResources();
            if(v0 != null) {
                arg2 = v0.getColor(arg2);
            }
            else {
                goto label_5;
            }
        }
        else {
        label_5:
            arg2 = 0;
        }

        this.setTintColor(arg2);
    }

    private final void zzc(boolean arg4) {
        if(this.mOnImageLoadedListener != null) {
            this.mOnImageLoadedListener.onImageLoaded(this.zzqn, null, arg4);
        }
    }
}

