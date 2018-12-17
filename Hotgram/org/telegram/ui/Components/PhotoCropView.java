package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Build$VERSION;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.Crop.CropRotationWheel$RotationWheelListener;
import org.telegram.ui.Components.Crop.CropRotationWheel;
import org.telegram.ui.Components.Crop.CropView$CropViewListener;
import org.telegram.ui.Components.Crop.CropView;

public class PhotoCropView extends FrameLayout {
    public interface PhotoCropViewDelegate {
        Bitmap getBitmap();

        void needMoveImageTo(float arg1, float arg2, float arg3, boolean arg4);

        void onChange(boolean arg1);
    }

    private RectF animationEndValues;
    private Runnable animationRunnable;
    private RectF animationStartValues;
    private float bitmapGlobalScale;
    private float bitmapGlobalX;
    private float bitmapGlobalY;
    private int bitmapHeight;
    private Bitmap bitmapToEdit;
    private int bitmapWidth;
    private int bitmapX;
    private int bitmapY;
    private CropView cropView;
    private PhotoCropViewDelegate delegate;
    private int draggingState;
    private boolean freeformCrop;
    private float oldX;
    private float oldY;
    private int orientation;
    private float rectSizeX;
    private float rectSizeY;
    private float rectX;
    private float rectY;
    private boolean showOnSetBitmap;
    private CropRotationWheel wheelView;

    public PhotoCropView(Context arg2) {
        super(arg2);
        this.freeformCrop = true;
        this.rectSizeX = 600f;
        this.rectSizeY = 600f;
        this.draggingState = 0;
        this.oldX = 0f;
        this.oldY = 0f;
        this.bitmapWidth = 1;
        this.bitmapHeight = 1;
        this.rectX = -1f;
        this.rectY = -1f;
        this.bitmapGlobalScale = 1f;
        this.bitmapGlobalX = 0f;
        this.bitmapGlobalY = 0f;
    }

    static PhotoCropViewDelegate access$000(PhotoCropView arg0) {
        return arg0.delegate;
    }

    static CropRotationWheel access$100(PhotoCropView arg0) {
        return arg0.wheelView;
    }

    static CropView access$200(PhotoCropView arg0) {
        return arg0.cropView;
    }

    static Runnable access$300(PhotoCropView arg0) {
        return arg0.animationRunnable;
    }

    static Runnable access$302(PhotoCropView arg0, Runnable arg1) {
        arg0.animationRunnable = arg1;
        return arg1;
    }

    public void cancelAnimationRunnable() {
        if(this.animationRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.animationRunnable);
            this.animationRunnable = null;
            this.animationStartValues = null;
            this.animationEndValues = null;
        }
    }

    public Bitmap getBitmap() {
        if(this.cropView != null) {
            return this.cropView.getResult();
        }

        return null;
    }

    public float getBitmapX() {
        return ((float)(this.bitmapX - AndroidUtilities.dp(14f)));
    }

    public float getBitmapY() {
        int v0 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
        return (((float)(this.bitmapY - AndroidUtilities.dp(14f)))) - (((float)v0));
    }

    public float getLimitHeight() {
        int v0 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
        float v0_1 = ((float)v0);
        return (((float)(this.getHeight() - AndroidUtilities.dp(14f)))) - v0_1 - this.rectY - (((float)(((int)Math.max(0, Math.ceil(((double)(((((float)(this.getHeight() - AndroidUtilities.dp(28f)))) - (((float)this.bitmapHeight)) * this.bitmapGlobalScale - v0_1) / 2f)))))))) - this.rectSizeY;
    }

    public float getLimitWidth() {
        return (((float)(this.getWidth() - AndroidUtilities.dp(14f)))) - this.rectX - (((float)(((int)Math.max(0, Math.ceil(((double)(((((float)(this.getWidth() - AndroidUtilities.dp(28f)))) - (((float)this.bitmapWidth)) * this.bitmapGlobalScale) / 2f)))))))) - this.rectSizeX;
    }

    public float getLimitX() {
        return this.rectX - Math.max(0f, ((float)Math.ceil(((double)(((((float)this.getWidth())) - (((float)this.bitmapWidth)) * this.bitmapGlobalScale) / 2f)))));
    }

    public float getLimitY() {
        int v0 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
        return this.rectY - Math.max(0f, ((float)Math.ceil(((double)(((((float)this.getHeight())) - (((float)this.bitmapHeight)) * this.bitmapGlobalScale + (((float)v0))) / 2f)))));
    }

    public float getRectSizeX() {
        return this.cropView.getCropWidth();
    }

    public float getRectSizeY() {
        return this.cropView.getCropHeight();
    }

    public float getRectX() {
        return this.cropView.getCropLeft() - (((float)AndroidUtilities.dp(14f)));
    }

    public float getRectY() {
        float v0 = this.cropView.getCropTop() - (((float)AndroidUtilities.dp(14f)));
        int v1 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
        return v0 - (((float)v1));
    }

    public boolean isReady() {
        return this.cropView.isReady();
    }

    public void moveToFill(boolean arg14) {
        float v3;
        float v0 = (((float)this.bitmapWidth)) / this.rectSizeX;
        float v1 = (((float)this.bitmapHeight)) / this.rectSizeY;
        if(v0 > v1) {
            v0 = v1;
        }

        v1 = 1f;
        if(v0 > v1) {
            v3 = 3f;
            if(this.bitmapGlobalScale * v0 > v3) {
                v0 = v3 / this.bitmapGlobalScale;
            }
            else {
                goto label_19;
            }
        }
        else {
        label_19:
            if(v0 < v1 && this.bitmapGlobalScale * v0 < v1) {
                v0 = v1 / this.bitmapGlobalScale;
            }
        }

        float v2 = this.rectSizeX * v0;
        v3 = this.rectSizeY * v0;
        int v4 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
        float v4_1 = ((float)v4);
        float v5 = ((((float)this.getWidth())) - v2) / 2f;
        float v7 = ((((float)this.getHeight())) - v3 + v4_1) / 2f;
        this.animationStartValues = new RectF(this.rectX, this.rectY, this.rectSizeX, this.rectSizeY);
        this.animationEndValues = new RectF(v5, v7, v2, v3);
        v1 = v0 - v1;
        this.delegate.needMoveImageTo(v5 + (((float)(this.getWidth() / 2))) * v1 + (this.bitmapGlobalX - this.rectX) * v0, v7 + ((((float)this.getHeight())) + v4_1) / 2f * v1 + (this.bitmapGlobalY - this.rectY) * v0, this.bitmapGlobalScale * v0, arg14);
    }

    public void onAppear() {
        if(this.cropView != null) {
            this.cropView.willShow();
        }
    }

    public void onAppeared() {
        if(this.cropView != null) {
            this.cropView.show();
        }
        else {
            this.showOnSetBitmap = true;
        }
    }

    public void onDisappear() {
        this.cropView.hide();
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        Bitmap v1 = this.delegate.getBitmap();
        if(v1 != null) {
            this.bitmapToEdit = v1;
        }

        if(this.cropView != null) {
            this.cropView.updateLayout();
        }
    }

    public void reset() {
        this.wheelView.reset();
        this.cropView.reset();
    }

    public void setAnimationProgress(float arg4) {
        if(this.animationStartValues != null) {
            if(arg4 == 1f) {
                this.rectX = this.animationEndValues.left;
                this.rectY = this.animationEndValues.top;
                this.rectSizeX = this.animationEndValues.right;
                this.rectSizeY = this.animationEndValues.bottom;
                this.animationStartValues = null;
                this.animationEndValues = null;
            }
            else {
                this.rectX = this.animationStartValues.left + (this.animationEndValues.left - this.animationStartValues.left) * arg4;
                this.rectY = this.animationStartValues.top + (this.animationEndValues.top - this.animationStartValues.top) * arg4;
                this.rectSizeX = this.animationStartValues.right + (this.animationEndValues.right - this.animationStartValues.right) * arg4;
                this.rectSizeY = this.animationStartValues.bottom + (this.animationEndValues.bottom - this.animationStartValues.bottom) * arg4;
            }

            this.invalidate();
        }
    }

    public void setBitmap(Bitmap arg10, int arg11, boolean arg12) {
        this.bitmapToEdit = arg10;
        this.rectSizeX = 600f;
        this.rectSizeY = 600f;
        this.draggingState = 0;
        this.oldX = 0f;
        this.oldY = 0f;
        this.bitmapWidth = 1;
        this.bitmapHeight = 1;
        this.rectX = -1f;
        this.rectY = -1f;
        this.freeformCrop = arg12;
        this.orientation = arg11;
        this.requestLayout();
        if(this.cropView == null) {
            this.cropView = new CropView(this.getContext());
            this.cropView.setListener(new CropViewListener() {
                public void onAspectLock(boolean arg2) {
                    PhotoCropView.this.wheelView.setAspectLock(arg2);
                }

                public void onChange(boolean arg2) {
                    if(PhotoCropView.this.delegate != null) {
                        PhotoCropView.this.delegate.onChange(arg2);
                    }
                }
            });
            this.cropView.setBottomPadding(((float)AndroidUtilities.dp(64f)));
            this.addView(this.cropView);
            this.wheelView = new CropRotationWheel(this.getContext());
            this.wheelView.setListener(new RotationWheelListener() {
                public void aspectRatioPressed() {
                    PhotoCropView.this.cropView.showAspectRatioDialog();
                }

                public void onChange(float arg2) {
                    PhotoCropView.this.cropView.setRotation(arg2);
                    if(PhotoCropView.this.delegate != null) {
                        PhotoCropView.this.delegate.onChange(false);
                    }
                }

                public void onEnd(float arg1) {
                    PhotoCropView.this.cropView.onRotationEnded();
                }

                public void onStart() {
                    PhotoCropView.this.cropView.onRotationBegan();
                }

                public void rotate90Pressed() {
                    PhotoCropView.this.wheelView.reset();
                    PhotoCropView.this.cropView.rotate90Degrees();
                }
            });
            this.addView(this.wheelView, LayoutHelper.createFrame(-1, -2f, 81, 0f, 0f, 0f, 0f));
        }

        this.cropView.setVisibility(0);
        this.cropView.setBitmap(arg10, arg11, arg12);
        if(this.showOnSetBitmap) {
            this.showOnSetBitmap = false;
            this.cropView.show();
        }

        this.wheelView.setFreeform(arg12);
        this.wheelView.reset();
    }

    public void setBitmapParams(float arg1, float arg2, float arg3) {
        this.bitmapGlobalScale = arg1;
        this.bitmapGlobalX = arg2;
        this.bitmapGlobalY = arg3;
    }

    public void setDelegate(PhotoCropViewDelegate arg1) {
        this.delegate = arg1;
    }

    public void setOrientation(int arg4) {
        this.orientation = arg4;
        this.rectX = -1f;
        this.rectY = -1f;
        this.rectSizeX = 600f;
        this.rectSizeY = 600f;
        this.delegate.needMoveImageTo(0f, 0f, 1f, false);
        this.requestLayout();
    }

    public void startAnimationRunnable() {
        if(this.animationRunnable != null) {
            return;
        }

        this.animationRunnable = new Runnable() {
            public void run() {
                if(PhotoCropView.this.animationRunnable == this) {
                    PhotoCropView.this.animationRunnable = null;
                    PhotoCropView.this.moveToFill(true);
                }
            }
        };
        AndroidUtilities.runOnUIThread(this.animationRunnable, 1500);
    }
}

