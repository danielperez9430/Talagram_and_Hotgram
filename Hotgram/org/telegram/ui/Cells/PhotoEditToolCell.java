package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PhotoEditorSeekBar$PhotoEditorSeekBarDelegate;
import org.telegram.ui.Components.PhotoEditorSeekBar;

public class PhotoEditToolCell extends FrameLayout {
    class org.telegram.ui.Cells.PhotoEditToolCell$1 implements Runnable {
        org.telegram.ui.Cells.PhotoEditToolCell$1(PhotoEditToolCell arg1) {
            PhotoEditToolCell.this = arg1;
            super();
        }

        public void run() {
            PhotoEditToolCell.this.valueTextView.setTag(null);
            PhotoEditToolCell.this.valueAnimation = new AnimatorSet();
            PhotoEditToolCell.this.valueAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(PhotoEditToolCell.this.valueTextView, "alpha", new float[]{0f}), ObjectAnimator.ofFloat(PhotoEditToolCell.this.nameTextView, "alpha", new float[]{1f})});
            PhotoEditToolCell.this.valueAnimation.setDuration(180);
            PhotoEditToolCell.this.valueAnimation.setInterpolator(new DecelerateInterpolator());
            PhotoEditToolCell.this.valueAnimation.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    if(arg2.equals(this.this$1.this$0.valueAnimation)) {
                        this.this$1.this$0.valueAnimation = null;
                    }
                }
            });
            PhotoEditToolCell.this.valueAnimation.start();
        }
    }

    private Runnable hideValueRunnable;
    private TextView nameTextView;
    private PhotoEditorSeekBar seekBar;
    private AnimatorSet valueAnimation;
    private TextView valueTextView;

    public PhotoEditToolCell(Context arg12) {
        super(arg12);
        this.hideValueRunnable = new org.telegram.ui.Cells.PhotoEditToolCell$1(this);
        this.nameTextView = new TextView(arg12);
        this.nameTextView.setGravity(5);
        this.nameTextView.setTextColor(-1);
        this.nameTextView.setTextSize(1, 12f);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.addView(this.nameTextView, LayoutHelper.createFrame(80, -2f, 19, 0f, 0f, 0f, 0f));
        this.valueTextView = new TextView(arg12);
        this.valueTextView.setTextColor(-9649153);
        this.valueTextView.setTextSize(1, 12f);
        this.valueTextView.setGravity(5);
        this.valueTextView.setSingleLine(true);
        this.addView(this.valueTextView, LayoutHelper.createFrame(80, -2f, 19, 0f, 0f, 0f, 0f));
        this.seekBar = new PhotoEditorSeekBar(arg12);
        this.addView(this.seekBar, LayoutHelper.createFrame(-1, 40f, 19, 96f, 0f, 24f, 0f));
    }

    static TextView access$000(PhotoEditToolCell arg0) {
        return arg0.valueTextView;
    }

    static AnimatorSet access$100(PhotoEditToolCell arg0) {
        return arg0.valueAnimation;
    }

    static AnimatorSet access$102(PhotoEditToolCell arg0, AnimatorSet arg1) {
        arg0.valueAnimation = arg1;
        return arg1;
    }

    static TextView access$200(PhotoEditToolCell arg0) {
        return arg0.nameTextView;
    }

    static Runnable access$300(PhotoEditToolCell arg0) {
        return arg0.hideValueRunnable;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40f), 1073741824));
    }

    public void setIconAndTextAndValue(String arg6, float arg7, int arg8, int arg9) {
        String v2;
        AnimatorSet v1 = null;
        if(this.valueAnimation != null) {
            this.valueAnimation.cancel();
            this.valueAnimation = v1;
        }

        AndroidUtilities.cancelRunOnUIThread(this.hideValueRunnable);
        this.valueTextView.setTag(v1);
        TextView v0 = this.nameTextView;
        v0.setText(arg6.substring(0, 1).toUpperCase() + arg6.substring(1).toLowerCase());
        if(arg7 > 0f) {
            v0 = this.valueTextView;
            v1_1 = new StringBuilder();
            v2 = "+";
        }
        else {
            v0 = this.valueTextView;
            v1_1 = new StringBuilder();
            v2 = "";
        }

        v1_1.append(v2);
        v1_1.append(((int)arg7));
        v0.setText(v1_1.toString());
        this.valueTextView.setAlpha(0f);
        this.nameTextView.setAlpha(1f);
        this.seekBar.setMinMax(arg8, arg9);
        this.seekBar.setProgress(((int)arg7), false);
    }

    public void setSeekBarDelegate(PhotoEditorSeekBarDelegate arg3) {
        this.seekBar.setDelegate(new PhotoEditorSeekBarDelegate(arg3) {
            public void onProgressChanged(int arg7, int arg8) {
                String v1;
                StringBuilder v0;
                TextView v7;
                this.val$photoEditorSeekBarDelegate.onProgressChanged(arg7, arg8);
                if(arg8 > 0) {
                    v7 = PhotoEditToolCell.this.valueTextView;
                    v0 = new StringBuilder();
                    v1 = "+";
                }
                else {
                    v7 = PhotoEditToolCell.this.valueTextView;
                    v0 = new StringBuilder();
                    v1 = "";
                }

                v0.append(v1);
                v0.append(arg8);
                v7.setText(v0.toString());
                if(PhotoEditToolCell.this.valueTextView.getTag() == null) {
                    if(PhotoEditToolCell.this.valueAnimation != null) {
                        PhotoEditToolCell.this.valueAnimation.cancel();
                    }

                    PhotoEditToolCell.this.valueTextView.setTag(Integer.valueOf(1));
                    PhotoEditToolCell.this.valueAnimation = new AnimatorSet();
                    PhotoEditToolCell.this.valueAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(PhotoEditToolCell.this.valueTextView, "alpha", new float[]{1f}), ObjectAnimator.ofFloat(PhotoEditToolCell.this.nameTextView, "alpha", new float[]{0f})});
                    PhotoEditToolCell.this.valueAnimation.setDuration(180);
                    PhotoEditToolCell.this.valueAnimation.setInterpolator(new DecelerateInterpolator());
                    PhotoEditToolCell.this.valueAnimation.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator arg3) {
                            AndroidUtilities.runOnUIThread(this.this$1.this$0.hideValueRunnable, 1000);
                        }
                    });
                    PhotoEditToolCell.this.valueAnimation.start();
                }
                else {
                    AndroidUtilities.cancelRunOnUIThread(PhotoEditToolCell.this.hideValueRunnable);
                    AndroidUtilities.runOnUIThread(PhotoEditToolCell.this.hideValueRunnable, 1000);
                }
            }
        });
    }

    public void setTag(Object arg2) {
        super.setTag(arg2);
        this.seekBar.setTag(arg2);
    }
}

