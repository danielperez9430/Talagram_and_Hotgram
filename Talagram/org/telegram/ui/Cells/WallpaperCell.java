package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_photoCachedSize;
import org.telegram.tgnet.TLRPC$TL_wallPaperSolid;
import org.telegram.tgnet.TLRPC$WallPaper;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class WallpaperCell extends FrameLayout {
    private BackupImageView imageView;
    private ImageView imageView2;
    private View selectionView;

    public WallpaperCell(Context arg5) {
        super(arg5);
        this.imageView = new BackupImageView(arg5);
        this.addView(this.imageView, LayoutHelper.createFrame(100, 100, 83));
        this.imageView2 = new ImageView(arg5);
        this.imageView2.setImageResource(2131231171);
        this.imageView2.setScaleType(ImageView$ScaleType.CENTER);
        this.addView(this.imageView2, LayoutHelper.createFrame(100, 100, 83));
        this.selectionView = new View(arg5);
        this.selectionView.setBackgroundResource(2131231713);
        this.addView(this.selectionView, LayoutHelper.createFrame(100, 102f));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100f), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(102f), 1073741824));
    }

    public void setWallpaper(WallPaper arg7, int arg8, Drawable arg9, boolean arg10) {
        View v7;
        int v0 = 1514625126;
        int v1 = 4;
        int v2 = 0;
        if(arg7 == null) {
            this.imageView.setVisibility(v1);
            this.imageView2.setVisibility(0);
            if(arg10) {
                v7 = this.selectionView;
                if(arg8 == -2) {
                    v1 = 0;
                }

                v7.setVisibility(v1);
                this.imageView2.setImageDrawable(arg9);
                this.imageView2.setScaleType(ImageView$ScaleType.CENTER_CROP);
            }
            else {
                v7 = this.selectionView;
                int v9 = -1;
                if(arg8 == v9) {
                    v1 = 0;
                }

                v7.setVisibility(v1);
                ImageView v7_1 = this.imageView2;
                if(arg8 != v9) {
                    if(arg8 == 1000001) {
                    }
                    else {
                        v0 = 1509949440;
                    }
                }

                v7_1.setBackgroundColor(v0);
                this.imageView2.setScaleType(ImageView$ScaleType.CENTER);
                this.imageView2.setImageResource(2131231171);
            }
        }
        else {
            this.imageView.setVisibility(0);
            this.imageView2.setVisibility(v1);
            View v9_1 = this.selectionView;
            if(arg8 == arg7.id) {
                v1 = 0;
            }

            v9_1.setVisibility(v1);
            Bitmap v9_2 = null;
            if((arg7 instanceof TL_wallPaperSolid)) {
                this.imageView.setImageBitmap(v9_2);
                this.imageView.setBackgroundColor(arg7.bg_color | -16777216);
                return;
            }

            arg8 = AndroidUtilities.dp(100f);
            Object v10 = v9_2;
            while(v2 < arg7.sizes.size()) {
                Object v1_1 = arg7.sizes.get(v2);
                if(v1_1 == null) {
                }
                else {
                    int v3 = ((PhotoSize)v1_1).w >= ((PhotoSize)v1_1).h ? ((PhotoSize)v1_1).w : ((PhotoSize)v1_1).h;
                    if(v10 != null && (arg8 <= 100 || ((PhotoSize)v10).location == null || ((PhotoSize)v10).location.dc_id != -2147483648) && !(v1_1 instanceof TL_photoCachedSize) && v3 > arg8) {
                        goto label_88;
                    }

                    v10 = v1_1;
                }

            label_88:
                ++v2;
            }

            if(v10 != null && ((PhotoSize)v10).location != null) {
                this.imageView.setImage(((PhotoSize)v10).location, "100_100", ((Drawable)v9_2));
            }

            this.imageView.setBackgroundColor(v0);
        }
    }
}

