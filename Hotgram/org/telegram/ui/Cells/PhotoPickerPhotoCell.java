package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaController$SearchImage;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.LayoutHelper;

public class PhotoPickerPhotoCell extends FrameLayout {
    private AnimatorSet animator;
    private AnimatorSet animatorSet;
    public CheckBox checkBox;
    public FrameLayout checkFrame;
    public int itemWidth;
    public BackupImageView photoImage;
    public FrameLayout videoInfoContainer;
    public TextView videoTextView;
    private boolean zoomOnSelect;

    public PhotoPickerPhotoCell(Context arg12, boolean arg13) {
        super(arg12);
        this.zoomOnSelect = arg13;
        this.photoImage = new BackupImageView(arg12);
        int v1 = -1;
        this.addView(this.photoImage, LayoutHelper.createFrame(v1, -1f));
        this.checkFrame = new FrameLayout(arg12);
        this.addView(this.checkFrame, LayoutHelper.createFrame(42, 42, 53));
        this.videoInfoContainer = new FrameLayout(arg12);
        this.videoInfoContainer.setBackgroundResource(2131231476);
        this.videoInfoContainer.setPadding(AndroidUtilities.dp(3f), 0, AndroidUtilities.dp(3f), 0);
        this.addView(this.videoInfoContainer, LayoutHelper.createFrame(v1, 16, 83));
        ImageView v0 = new ImageView(arg12);
        v0.setImageResource(2131231262);
        this.videoInfoContainer.addView(((View)v0), LayoutHelper.createFrame(-2, -2, 19));
        this.videoTextView = new TextView(arg12);
        this.videoTextView.setTextColor(v1);
        this.videoTextView.setTextSize(1, 12f);
        this.videoInfoContainer.addView(this.videoTextView, LayoutHelper.createFrame(-2, -2f, 19, 18f, -0.7f, 0f, 0f));
        this.checkBox = new CheckBox(arg12, 2131230987);
        CheckBox v12 = this.checkBox;
        int v4 = arg13 ? 30 : 26;
        v12.setSize(v4);
        this.checkBox.setCheckOffset(AndroidUtilities.dp(1f));
        this.checkBox.setDrawBackground(true);
        this.checkBox.setColor(-10043398, v1);
        v12 = this.checkBox;
        v4 = arg13 ? 30 : 26;
        float v5 = arg13 ? 30f : 26f;
        this.addView(((View)v12), LayoutHelper.createFrame(v4, v5, 53, 0f, 4f, 4f, 0f));
    }

    static AnimatorSet access$000(PhotoPickerPhotoCell arg0) {
        return arg0.animatorSet;
    }

    static AnimatorSet access$002(PhotoPickerPhotoCell arg0, AnimatorSet arg1) {
        arg0.animatorSet = arg1;
        return arg1;
    }

    static AnimatorSet access$100(PhotoPickerPhotoCell arg0) {
        return arg0.animator;
    }

    static AnimatorSet access$102(PhotoPickerPhotoCell arg0, AnimatorSet arg1) {
        arg0.animator = arg1;
        return arg1;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(this.itemWidth, 1073741824), View$MeasureSpec.makeMeasureSpec(this.itemWidth, 1073741824));
    }

    public void setChecked(int arg9, boolean arg10, boolean arg11) {
        this.checkBox.setChecked(arg9, arg10, arg11);
        if(this.animator != null) {
            this.animator.cancel();
            this.animator = null;
        }

        if(this.zoomOnSelect) {
            arg9 = -16119286;
            float v0 = 1f;
            if(arg11) {
                if(arg10) {
                    this.setBackgroundColor(arg9);
                }

                this.animator = new AnimatorSet();
                AnimatorSet v9 = this.animator;
                Animator[] v11 = new Animator[2];
                BackupImageView v3 = this.photoImage;
                String v4 = "scaleX";
                float[] v6 = new float[1];
                float v7 = arg10 ? 0.85f : 1f;
                v6[0] = v7;
                v11[0] = ObjectAnimator.ofFloat(v3, v4, v6);
                v3 = this.photoImage;
                v4 = "scaleY";
                v6 = new float[1];
                if(arg10) {
                    v0 = 0.85f;
                }

                v6[0] = v0;
                v11[1] = ObjectAnimator.ofFloat(v3, v4, v6);
                v9.playTogether(v11);
                this.animator.setDuration(200);
                this.animator.addListener(new AnimatorListenerAdapter(arg10) {
                    public void onAnimationCancel(Animator arg2) {
                        if(PhotoPickerPhotoCell.this.animator != null && (PhotoPickerPhotoCell.this.animator.equals(arg2))) {
                            PhotoPickerPhotoCell.this.animator = null;
                        }
                    }

                    public void onAnimationEnd(Animator arg2) {
                        if(PhotoPickerPhotoCell.this.animator != null && (PhotoPickerPhotoCell.this.animator.equals(arg2))) {
                            PhotoPickerPhotoCell.this.animator = null;
                            if(!this.val$checked) {
                                PhotoPickerPhotoCell.this.setBackgroundColor(0);
                            }
                        }
                    }
                });
                this.animator.start();
            }
            else {
                if(arg10) {
                }
                else {
                    arg9 = 0;
                }

                this.setBackgroundColor(arg9);
                BackupImageView v9_1 = this.photoImage;
                float v11_1 = arg10 ? 0.85f : 1f;
                v9_1.setScaleX(v11_1);
                v9_1 = this.photoImage;
                if(arg10) {
                    v0 = 0.85f;
                }

                v9_1.setScaleY(v0);
            }
        }
    }

    public void setImage(SearchImage arg4) {
        String v4_2;
        FileLocation v4_1;
        PhotoSize v4;
        BackupImageView v1;
        Drawable v0 = this.getResources().getDrawable(2131231414);
        String v2 = null;
        if(arg4.thumbPhotoSize != null) {
            v1 = this.photoImage;
            v4 = arg4.thumbPhotoSize;
            goto label_8;
        }
        else if(arg4.photoSize != null) {
            v1 = this.photoImage;
            v4_1 = arg4.photoSize.location;
            v2 = "80_80";
            goto label_9;
        }
        else {
            if(arg4.thumbPath != null) {
                v1 = this.photoImage;
                v4_2 = arg4.thumbPath;
            }
            else {
                if(arg4.thumbUrl != null && arg4.thumbUrl.length() > 0) {
                    v1 = this.photoImage;
                    v4_2 = arg4.thumbUrl;
                    goto label_22;
                }

                goto label_32;
            }

        label_22:
            v1.setImage(v4_2, v2, v0);
            return;
        label_32:
            if(arg4.document != null && arg4.document.thumb != null) {
                v1 = this.photoImage;
                v4 = arg4.document.thumb;
            label_8:
                v4_1 = v4.location;
            label_9:
                v1.setImage(((TLObject)v4_1), v2, v0);
                return;
            }

            this.photoImage.setImageDrawable(v0);
        }
    }

    public void setNum(int arg2) {
        this.checkBox.setNum(arg2);
    }

    public void showCheck(boolean arg11) {
        if(this.animatorSet != null) {
            this.animatorSet.cancel();
            this.animatorSet = null;
        }

        this.animatorSet = new AnimatorSet();
        this.animatorSet.setInterpolator(new DecelerateInterpolator());
        this.animatorSet.setDuration(180);
        AnimatorSet v0 = this.animatorSet;
        Animator[] v1 = new Animator[2];
        FrameLayout v2 = this.videoInfoContainer;
        String v3 = "alpha";
        float[] v5 = new float[1];
        float v6 = 0f;
        float v8 = arg11 ? 1f : 0f;
        v5[0] = v8;
        v1[0] = ObjectAnimator.ofFloat(v2, v3, v5);
        CheckBox v2_1 = this.checkBox;
        v3 = "alpha";
        v5 = new float[1];
        if(arg11) {
            v6 = 1f;
        }

        v5[0] = v6;
        v1[1] = ObjectAnimator.ofFloat(v2_1, v3, v5);
        v0.playTogether(v1);
        this.animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                if(arg2.equals(PhotoPickerPhotoCell.this.animatorSet)) {
                    PhotoPickerPhotoCell.this.animatorSet = null;
                }
            }
        });
        this.animatorSet.start();
    }
}

