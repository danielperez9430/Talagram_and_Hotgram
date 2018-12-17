package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaController$PhotoEntry;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.PhotoViewer;

public class PhotoAttachPhotoCell extends FrameLayout {
    public interface PhotoAttachPhotoCellDelegate {
        void onCheckClick(PhotoAttachPhotoCell arg1);
    }

    private AnimatorSet animatorSet;
    private CheckBox checkBox;
    private FrameLayout checkFrame;
    private PhotoAttachPhotoCellDelegate delegate;
    private BackupImageView imageView;
    private boolean isLast;
    private boolean isVertical;
    private boolean needCheckShow;
    private PhotoEntry photoEntry;
    private boolean pressed;
    private static Rect rect;
    private FrameLayout videoInfoContainer;
    private TextView videoTextView;

    static {
        PhotoAttachPhotoCell.rect = new Rect();
    }

    public PhotoAttachPhotoCell(Context arg13) {
        super(arg13);
        this.imageView = new BackupImageView(arg13);
        this.addView(this.imageView, LayoutHelper.createFrame(80, 80f));
        this.checkFrame = new FrameLayout(arg13);
        this.addView(this.checkFrame, LayoutHelper.createFrame(42, 42f, 51, 38f, 0f, 0f, 0f));
        this.videoInfoContainer = new FrameLayout(arg13);
        this.videoInfoContainer.setBackgroundResource(2131231476);
        this.videoInfoContainer.setPadding(AndroidUtilities.dp(3f), 0, AndroidUtilities.dp(3f), 0);
        this.addView(this.videoInfoContainer, LayoutHelper.createFrame(80, 16, 83));
        ImageView v0 = new ImageView(arg13);
        v0.setImageResource(2131231262);
        this.videoInfoContainer.addView(((View)v0), LayoutHelper.createFrame(-2, -2, 19));
        this.videoTextView = new TextView(arg13);
        this.videoTextView.setTextColor(-1);
        this.videoTextView.setTextSize(1, 12f);
        this.videoInfoContainer.addView(this.videoTextView, LayoutHelper.createFrame(-2, -2f, 19, 18f, -0.7f, 0f, 0f));
        this.checkBox = new CheckBox(arg13, 2131230987);
        this.checkBox.setSize(30);
        this.checkBox.setCheckOffset(AndroidUtilities.dp(1f));
        this.checkBox.setDrawBackground(true);
        this.checkBox.setColor(-12793105, -1);
        this.addView(this.checkBox, LayoutHelper.createFrame(30, 30f, 51, 46f, 4f, 0f, 0f));
        this.checkBox.setVisibility(0);
    }

    static AnimatorSet access$000(PhotoAttachPhotoCell arg0) {
        return arg0.animatorSet;
    }

    static AnimatorSet access$002(PhotoAttachPhotoCell arg0, AnimatorSet arg1) {
        arg0.animatorSet = arg1;
        return arg1;
    }

    public void callDelegate() {
        this.delegate.onCheckClick(this);
    }

    public CheckBox getCheckBox() {
        return this.checkBox;
    }

    public FrameLayout getCheckFrame() {
        return this.checkFrame;
    }

    public BackupImageView getImageView() {
        return this.imageView;
    }

    public PhotoEntry getPhotoEntry() {
        return this.photoEntry;
    }

    public View getVideoInfoContainer() {
        return this.videoInfoContainer;
    }

    protected void onMeasure(int arg4, int arg5) {
        arg5 = 6;
        float v1 = 80f;
        int v2 = 1073741824;
        if(this.isVertical) {
            arg4 = View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(v1), v2);
            if(this.isLast) {
                arg5 = 0;
            }

            arg5 = AndroidUtilities.dp(((float)(arg5 + 80)));
        }
        else {
            if(this.isLast) {
                arg5 = 0;
            }

            arg4 = View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(((float)(arg5 + 80))), v2);
            arg5 = AndroidUtilities.dp(v1);
        }

        super.onMeasure(arg4, View$MeasureSpec.makeMeasureSpec(arg5, v2));
    }

    public boolean onTouchEvent(MotionEvent arg6) {
        this.checkFrame.getHitRect(PhotoAttachPhotoCell.rect);
        boolean v1 = true;
        if(arg6.getAction() == 0) {
            if(PhotoAttachPhotoCell.rect.contains(((int)arg6.getX()), ((int)arg6.getY()))) {
                this.pressed = true;
                this.invalidate();
            }
            else {
                goto label_45;
            }
        }
        else if(this.pressed) {
            if(arg6.getAction() == 1) {
                this.getParent().requestDisallowInterceptTouchEvent(true);
                this.pressed = false;
                this.playSoundEffect(0);
                this.delegate.onCheckClick(this);
            }
            else {
                if(arg6.getAction() != 3) {
                    if(arg6.getAction() != 2) {
                        goto label_45;
                    }
                    else if(!PhotoAttachPhotoCell.rect.contains(((int)arg6.getX()), ((int)arg6.getY()))) {
                    }
                    else {
                        goto label_45;
                    }
                }

                this.pressed = false;
            }

            this.invalidate();
            goto label_45;
        }
        else {
        label_45:
            v1 = false;
        }

        if(!v1) {
            v1 = super.onTouchEvent(arg6);
        }

        return v1;
    }

    public void setChecked(int arg2, boolean arg3, boolean arg4) {
        this.checkBox.setChecked(arg2, arg3, arg4);
    }

    public void setDelegate(PhotoAttachPhotoCellDelegate arg1) {
        this.delegate = arg1;
    }

    public void setIsVertical(boolean arg1) {
        this.isVertical = arg1;
    }

    public void setNum(int arg2) {
        this.checkBox.setNum(arg2);
    }

    public void setOnCheckClickLisnener(View$OnClickListener arg2) {
        this.checkFrame.setOnClickListener(arg2);
    }

    public void setPhotoEntry(PhotoEntry arg6, boolean arg7, boolean arg8) {
        String v4;
        StringBuilder v3_1;
        String v3;
        BackupImageView v6_1;
        int v0 = 0;
        this.pressed = false;
        this.photoEntry = arg6;
        this.isLast = arg8;
        if(this.photoEntry.isVideo) {
            this.imageView.setOrientation(0, true);
            this.videoInfoContainer.setVisibility(0);
            int v6 = this.photoEntry.duration / 60;
            this.videoTextView.setText(String.format("%d:%02d", Integer.valueOf(v6), Integer.valueOf(this.photoEntry.duration - v6 * 60)));
        }
        else {
            this.videoInfoContainer.setVisibility(4);
        }

        String v1 = null;
        int v2 = 2131231414;
        if(this.photoEntry.thumbPath != null) {
            v6_1 = this.imageView;
            v3 = this.photoEntry.thumbPath;
            goto label_41;
        }
        else if(this.photoEntry.path != null) {
            if(this.photoEntry.isVideo) {
                v6_1 = this.imageView;
                v3_1 = new StringBuilder();
                v4 = "vthumb://";
            }
            else {
                this.imageView.setOrientation(this.photoEntry.orientation, true);
                v6_1 = this.imageView;
                v3_1 = new StringBuilder();
                v4 = "thumb://";
            }

            v3_1.append(v4);
            v3_1.append(this.photoEntry.imageId);
            v3_1.append(":");
            v3_1.append(this.photoEntry.path);
            v3 = v3_1.toString();
        label_41:
            v6_1.setImage(v3, v1, this.getResources().getDrawable(v2));
        }
        else {
            this.imageView.setImageResource(v2);
        }

        if((arg7) && (PhotoViewer.isShowingImage(this.photoEntry.path))) {
            v0 = 1;
        }

        this.imageView.getImageReceiver().setVisible(v0 ^ 1, true);
        CheckBox v6_2 = this.checkBox;
        float v7 = 1f;
        float v1_1 = v0 != 0 ? 0f : 1f;
        v6_2.setAlpha(v1_1);
        FrameLayout v6_3 = this.videoInfoContainer;
        if(v0 != 0) {
            v7 = 0f;
        }

        v6_3.setAlpha(v7);
        this.requestLayout();
    }

    public void showCheck(boolean arg11) {
        float v0 = 1f;
        if((arg11) && this.checkBox.getAlpha() == v0 || !arg11 && this.checkBox.getAlpha() == 0f) {
            return;
        }

        if(this.animatorSet != null) {
            this.animatorSet.cancel();
            this.animatorSet = null;
        }

        this.animatorSet = new AnimatorSet();
        this.animatorSet.setInterpolator(new DecelerateInterpolator());
        this.animatorSet.setDuration(180);
        AnimatorSet v2 = this.animatorSet;
        Animator[] v3 = new Animator[2];
        FrameLayout v4 = this.videoInfoContainer;
        String v5 = "alpha";
        float[] v7 = new float[1];
        float v8 = arg11 ? 1f : 0f;
        v7[0] = v8;
        v3[0] = ObjectAnimator.ofFloat(v4, v5, v7);
        CheckBox v4_1 = this.checkBox;
        v5 = "alpha";
        v7 = new float[1];
        if(arg11) {
        }
        else {
            v0 = 0f;
        }

        v7[0] = v0;
        v3[1] = ObjectAnimator.ofFloat(v4_1, v5, v7);
        v2.playTogether(v3);
        this.animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                if(arg2.equals(PhotoAttachPhotoCell.this.animatorSet)) {
                    PhotoAttachPhotoCell.this.animatorSet = null;
                }
            }
        });
        this.animatorSet.start();
    }

    public void showImage() {
        this.imageView.getImageReceiver().setVisible(true, true);
    }
}

