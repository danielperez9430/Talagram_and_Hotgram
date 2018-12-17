package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLRPC$EncryptedChat;

public class IdenticonDrawable extends Drawable {
    private int[] colors;
    private byte[] data;
    private Paint paint;

    public IdenticonDrawable() {
        super();
        this.paint = new Paint();
        this.colors = new int[]{-1, -2758925, -13805707, -13657655};
    }

    public void draw(Canvas arg17) {
        float v9_1;
        float v11;
        int v8;
        int v7;
        int v4;
        int v3_1;
        float v6;
        float v2;
        float v1;
        IdenticonDrawable v0 = this;
        if(v0.data == null) {
            return;
        }

        float v3 = 2f;
        if(v0.data.length == 16) {
            v1 = ((float)Math.floor(((double)((((float)Math.min(this.getBounds().width(), this.getBounds().height()))) / 8f))));
            v2 = 8f * v1;
            v6 = Math.max(0f, ((((float)this.getBounds().width())) - v2) / v3);
            v2 = Math.max(0f, ((((float)this.getBounds().height())) - v2) / v3);
            v3_1 = 0;
            v4 = 0;
            while(true) {
                v7 = 8;
                if(v3_1 < v7) {
                    v8 = v4;
                    for(v4 = 0; v4 < v7; ++v4) {
                        int v9 = v0.getBits(v8);
                        v8 += 2;
                        v0.paint.setColor(v0.colors[Math.abs(v9) % 4]);
                        v11 = v6 + (((float)v4)) * v1;
                        v9_1 = (((float)v3_1)) * v1;
                        arg17.drawRect(v11, v9_1 + v2, v11 + v1, v9_1 + v1 + v2, v0.paint);
                    }

                    ++v3_1;
                    v4 = v8;
                    continue;
                }

                return;
            }
        }
        else {
            v1 = ((float)Math.floor(((double)((((float)Math.min(this.getBounds().width(), this.getBounds().height()))) / 12f))));
            v2 = 12f * v1;
            v6 = Math.max(0f, ((((float)this.getBounds().width())) - v2) / v3);
            v2 = Math.max(0f, ((((float)this.getBounds().height())) - v2) / v3);
            v3_1 = 0;
            v4 = 0;
            while(true) {
                v7 = 12;
                if(v3_1 < v7) {
                    v8 = v4;
                    for(v4 = 0; v4 < v7; ++v4) {
                        v0.paint.setColor(v0.colors[Math.abs(v0.getBits(v8)) % 4]);
                        v11 = v6 + (((float)v4)) * v1;
                        v9_1 = (((float)v3_1)) * v1;
                        arg17.drawRect(v11, v9_1 + v2, v11 + v1, v9_1 + v1 + v2, v0.paint);
                        v8 += 2;
                    }

                    ++v3_1;
                    v4 = v8;
                    continue;
                }

                return;
            }
        }
    }

    private int getBits(int arg3) {
        return this.data[arg3 / 8] >> arg3 % 8 & 3;
    }

    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(32f);
    }

    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(32f);
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int arg1) {
    }

    public void setColorFilter(ColorFilter arg1) {
    }

    public void setColors(int[] arg3) {
        if(this.colors.length == 4) {
            this.colors = arg3;
            this.invalidateSelf();
            return;
        }

        throw new IllegalArgumentException("colors must have length of 4");
    }

    public void setEncryptedChat(EncryptedChat arg2) {
        this.data = arg2.key_hash;
        if(this.data == null) {
            byte[] v0 = AndroidUtilities.calcAuthKeyHash(arg2.auth_key);
            this.data = v0;
            arg2.key_hash = v0;
        }

        this.invalidateSelf();
    }
}

