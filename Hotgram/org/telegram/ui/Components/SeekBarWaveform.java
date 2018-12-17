package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;

public class SeekBarWaveform {
    private SeekBarDelegate delegate;
    private int height;
    private int innerColor;
    private MessageObject messageObject;
    private int outerColor;
    private static Paint paintInner;
    private static Paint paintOuter;
    private View parentView;
    private boolean pressed;
    private boolean selected;
    private int selectedColor;
    private boolean startDraging;
    private float startX;
    private int thumbDX;
    private int thumbX;
    private byte[] waveformBytes;
    private int width;

    public SeekBarWaveform(Context arg1) {
        super();
        this.thumbX = 0;
        this.thumbDX = 0;
        this.startDraging = false;
        this.pressed = false;
        if(SeekBarWaveform.paintInner == null) {
            SeekBarWaveform.paintInner = new Paint();
            SeekBarWaveform.paintOuter = new Paint();
        }
    }

    public void draw(Canvas arg33) {
        int v31;
        int v7;
        SeekBarWaveform v0 = this;
        if(v0.waveformBytes != null) {
            if(v0.width == 0) {
            }
            else {
                float v2 = 3f;
                float v1 = ((float)(v0.width / AndroidUtilities.dp(v2)));
                if(v1 <= 0.1f) {
                    return;
                }
                else {
                    int v4 = 5;
                    int v3 = v0.waveformBytes.length * 8 / v4;
                    float v5 = (((float)v3)) / v1;
                    Paint v6 = SeekBarWaveform.paintInner;
                    if(v0.messageObject != null && !v0.messageObject.isOutOwner() && (v0.messageObject.isContentUnread())) {
                        v7 = v0.outerColor;
                    }
                    else if(v0.selected) {
                        v7 = v0.selectedColor;
                    }
                    else {
                        v7 = v0.innerColor;
                    }

                    v6.setColor(v7);
                    SeekBarWaveform.paintOuter.setColor(v0.outerColor);
                    int v8 = 2;
                    int v6_1 = (v0.height - AndroidUtilities.dp(14f)) / v8;
                    int v1_1 = 0;
                    int v10 = 0;
                    float v11 = 0f;
                    int v12 = 0;
                    while(v1_1 < v3) {
                        if(v1_1 != v10) {
                            v31 = v3;
                        }
                        else {
                            float v13 = v11;
                            int v14 = 0;
                            int v11_1 = v10;
                            while(v10 == v11_1) {
                                v13 += v5;
                                v11_1 = ((int)v13);
                                ++v14;
                            }

                            v10 = v1_1 * 5;
                            int v15 = v10 / 8;
                            v10 -= v15 * 8;
                            int v9 = 8 - v10;
                            int v16 = 5 - v9;
                            byte v7_1 = ((byte)(v0.waveformBytes[v15] >> v10 & (v8 << Math.min(v4, v9) - 1) - 1));
                            if(v16 > 0) {
                                ++v15;
                                if(v15 < v0.waveformBytes.length) {
                                    v7_1 = ((byte)((((byte)(v7_1 << v16))) | v0.waveformBytes[v15] & (v8 << v16 - 1) - 1));
                                }
                            }

                            v9 = 0;
                            while(v9 < v14) {
                                v10 = AndroidUtilities.dp(v2) * v12;
                                float v16_1 = 31f;
                                v2 = 1f;
                                float v18 = 2f;
                                if(v10 >= v0.thumbX || AndroidUtilities.dp(v18) + v10 >= v0.thumbX) {
                                    float v4_1 = ((float)v10);
                                    float v15_1 = (((float)v7_1)) * 14f / v16_1;
                                    v31 = v3;
                                    arg33.drawRect(v4_1, ((float)(v6_1 + AndroidUtilities.dp(14f - Math.max(v2, v15_1)))), ((float)(v10 + AndroidUtilities.dp(v18))), ((float)(v6_1 + AndroidUtilities.dp(14f))), SeekBarWaveform.paintInner);
                                    if(v10 < v0.thumbX) {
                                        arg33.drawRect(v4_1, ((float)(AndroidUtilities.dp(14f - Math.max(1f, v15_1)) + v6_1)), ((float)v0.thumbX), ((float)(AndroidUtilities.dp(14f) + v6_1)), SeekBarWaveform.paintOuter);
                                    }
                                }
                                else {
                                    arg33.drawRect(((float)v10), ((float)(AndroidUtilities.dp(14f - Math.max(v2, (((float)v7_1)) * 14f / v16_1)) + v6_1)), ((float)(v10 + AndroidUtilities.dp(v18))), ((float)(AndroidUtilities.dp(14f) + v6_1)), SeekBarWaveform.paintOuter);
                                    v31 = v3;
                                }

                                ++v12;
                                ++v9;
                                v3 = v31;
                                v2 = 3f;
                            }

                            v31 = v3;
                            v10 = v11_1;
                            v11 = v13;
                        }

                        ++v1_1;
                        v3 = v31;
                        v2 = 3f;
                        v4 = 5;
                        v8 = 2;
                    }
                }
            }
        }
    }

    public boolean isDragging() {
        return this.pressed;
    }

    public boolean isStartDraging() {
        return this.startDraging;
    }

    public boolean onTouch(int arg4, float arg5, float arg6) {
        if(arg4 != 0) {
            if(arg4 != 1) {
                if(arg4 == 3) {
                }
                else if(arg4 != 2) {
                    return 0;
                }
                else if(this.pressed) {
                    if(this.startDraging) {
                        this.thumbX = ((int)(arg5 - (((float)this.thumbDX))));
                        if(this.thumbX < 0) {
                            this.thumbX = 0;
                        }
                        else if(this.thumbX > this.width) {
                            this.thumbX = this.width;
                        }
                    }

                    arg6 = -1f;
                    if(this.startX != arg6 && Math.abs(arg5 - this.startX) > AndroidUtilities.getPixelsInCM(0.2f, true)) {
                        if(this.parentView != null && this.parentView.getParent() != null) {
                            this.parentView.getParent().requestDisallowInterceptTouchEvent(true);
                        }

                        this.startDraging = true;
                        this.startX = arg6;
                    }

                    return 1;
                }
                else {
                    return 0;
                }
            }

            if(!this.pressed) {
                return 0;
            }

            if(arg4 == 1 && this.delegate != null) {
                this.delegate.onSeekBarDrag((((float)this.thumbX)) / (((float)this.width)));
            }

            this.pressed = false;
            return 1;
        }
        else if(0f <= arg5 && arg5 <= (((float)this.width)) && arg6 >= 0f && arg6 <= (((float)this.height))) {
            this.startX = arg5;
            this.pressed = true;
            this.thumbDX = ((int)(arg5 - (((float)this.thumbX))));
            this.startDraging = false;
            return 1;
        }

        return 0;
    }

    public void setColors(int arg1, int arg2, int arg3) {
        this.innerColor = arg1;
        this.outerColor = arg2;
        this.selectedColor = arg3;
    }

    public void setDelegate(SeekBarDelegate arg1) {
        this.delegate = arg1;
    }

    public void setMessageObject(MessageObject arg1) {
        this.messageObject = arg1;
    }

    public void setParentView(View arg1) {
        this.parentView = arg1;
    }

    public void setProgress(float arg3) {
        int v3;
        this.thumbX = ((int)Math.ceil(((double)((((float)this.width)) * arg3))));
        if(this.thumbX < 0) {
            v3 = 0;
            goto label_10;
        }
        else if(this.thumbX > this.width) {
            v3 = this.width;
        label_10:
            this.thumbX = v3;
        }
    }

    public void setSelected(boolean arg1) {
        this.selected = arg1;
    }

    public void setSize(int arg1, int arg2) {
        this.width = arg1;
        this.height = arg2;
    }

    public void setWaveform(byte[] arg1) {
        this.waveformBytes = arg1;
    }
}

