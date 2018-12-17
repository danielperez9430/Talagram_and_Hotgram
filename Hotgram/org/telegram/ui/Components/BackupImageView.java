package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.SecureDocument;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;

public class BackupImageView extends View {
    private int height;
    private ImageReceiver imageReceiver;
    private int width;

    public BackupImageView(Context arg1) {
        super(arg1);
        this.width = -1;
        this.height = -1;
        this.init();
    }

    public BackupImageView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.width = -1;
        this.height = -1;
        this.init();
    }

    public BackupImageView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.width = -1;
        this.height = -1;
        this.init();
    }

    public ImageReceiver getImageReceiver() {
        return this.imageReceiver;
    }

    public int getRoundRadius() {
        return this.imageReceiver.getRoundRadius();
    }

    private void init() {
        this.imageReceiver = new ImageReceiver(((View)this));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageReceiver.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageReceiver.onDetachedFromWindow();
    }

    protected void onDraw(Canvas arg6) {
        int v1 = -1;
        if(this.width == v1 || this.height == v1) {
            this.imageReceiver.setImageCoords(0, 0, this.getWidth(), this.getHeight());
        }
        else {
            this.imageReceiver.setImageCoords((this.getWidth() - this.width) / 2, (this.getHeight() - this.height) / 2, this.width, this.height);
        }

        this.imageReceiver.draw(arg6);
    }

    public void setAspectFit(boolean arg2) {
        this.imageReceiver.setAspectFit(arg2);
    }

    public void setImage(TLObject arg11, String arg12, Drawable arg13) {
        this.setImage(arg11, null, arg12, arg13, null, null, null, null, 0);
    }

    public void setImage(TLObject arg11, String arg12, String arg13, Drawable arg14) {
        this.setImage(arg11, null, arg12, arg14, null, null, null, arg13, 0);
    }

    public void setImage(String arg11, String arg12, Drawable arg13) {
        this.setImage(null, arg11, arg12, arg13, null, null, null, null, 0);
    }

    public void setImage(TLObject arg12, String arg13, String arg14, Drawable arg15, Bitmap arg16, FileLocation arg17, String arg18, String arg19, int arg20) {
        BitmapDrawable v5;
        BackupImageView v0_1;
        Bitmap v0 = arg16;
        if(v0 != null) {
            BitmapDrawable v1 = new BitmapDrawable(null, v0);
            v0_1 = this;
            v5 = v1;
        }
        else {
            v0_1 = this;
            Drawable v5_1 = arg15;
        }

        v0_1.imageReceiver.setImage(arg12, arg13, arg14, ((Drawable)v5), arg17, arg18, arg20, arg19, 0);
    }

    public void setImage(SecureDocument arg11, String arg12) {
        this.setImage(arg11, null, arg12, null, null, null, null, null, 0);
    }

    public void setImage(TLObject arg11, String arg12, Bitmap arg13) {
        this.setImage(arg11, null, arg12, null, arg13, null, null, null, 0);
    }

    public void setImage(TLObject arg11, String arg12, Bitmap arg13, int arg14) {
        this.setImage(arg11, null, arg12, null, arg13, null, null, null, arg14);
    }

    public void setImage(TLObject arg11, String arg12, Drawable arg13, int arg14) {
        this.setImage(arg11, null, arg12, arg13, null, null, null, null, arg14);
    }

    public void setImage(TLObject arg11, String arg12, FileLocation arg13, int arg14) {
        this.setImage(arg11, null, arg12, null, null, arg13, null, null, arg14);
    }

    public void setImageBitmap(Bitmap arg2) {
        this.imageReceiver.setImageBitmap(arg2);
    }

    public void setImageDrawable(Drawable arg2) {
        this.imageReceiver.setImageBitmap(arg2);
    }

    public void setImageResource(int arg2) {
        this.imageReceiver.setImageBitmap(this.getResources().getDrawable(arg2));
        this.invalidate();
    }

    public void setImageResource(int arg3, int arg4) {
        Drawable v3 = this.getResources().getDrawable(arg3);
        if(v3 != null) {
            v3.setColorFilter(new PorterDuffColorFilter(arg4, PorterDuff$Mode.MULTIPLY));
        }

        this.imageReceiver.setImageBitmap(v3);
        this.invalidate();
    }

    public void setOrientation(int arg2, boolean arg3) {
        this.imageReceiver.setOrientation(arg2, arg3);
    }

    public void setRoundRadius(int arg2) {
        this.imageReceiver.setRoundRadius(arg2);
        this.invalidate();
    }

    public void setSize(int arg1, int arg2) {
        this.width = arg1;
        this.height = arg2;
    }
}

