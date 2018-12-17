package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View$OnLongClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_documentAttributeVideo;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.PhotoViewer;

public class SharedPhotoVideoCell extends FrameLayout {
    class PhotoVideoView extends FrameLayout {
        private AnimatorSet animator;
        private CheckBox checkBox;
        private FrameLayout container;
        private BackupImageView imageView;
        private View selector;
        private FrameLayout videoInfoContainer;
        private TextView videoTextView;

        public PhotoVideoView(SharedPhotoVideoCell arg14, Context arg15) {
            SharedPhotoVideoCell.this = arg14;
            super(arg15);
            this.container = new FrameLayout(arg15);
            this.addView(this.container, LayoutHelper.createFrame(-1, -1f));
            this.imageView = new BackupImageView(arg15);
            this.imageView.getImageReceiver().setNeedsQualityThumb(true);
            this.imageView.getImageReceiver().setShouldGenerateQualityThumb(true);
            this.container.addView(this.imageView, LayoutHelper.createFrame(-1, -1f));
            this.videoInfoContainer = new FrameLayout(arg15);
            this.videoInfoContainer.setBackgroundResource(2131231476);
            this.videoInfoContainer.setPadding(AndroidUtilities.dp(3f), 0, AndroidUtilities.dp(3f), 0);
            this.container.addView(this.videoInfoContainer, LayoutHelper.createFrame(-1, 16, 83));
            ImageView v14 = new ImageView(arg15);
            v14.setImageResource(2131231262);
            this.videoInfoContainer.addView(((View)v14), LayoutHelper.createFrame(-2, -2, 19));
            this.videoTextView = new TextView(arg15);
            this.videoTextView.setTextColor(-1);
            this.videoTextView.setTextSize(1, 12f);
            this.videoInfoContainer.addView(this.videoTextView, LayoutHelper.createFrame(-2, -2f, 19, 18f, -0.7f, 0f, 0f));
            this.selector = new View(arg15);
            this.selector.setBackgroundDrawable(Theme.getSelectorDrawable(false));
            this.addView(this.selector, LayoutHelper.createFrame(-1, -1f));
            this.checkBox = new CheckBox(arg15, 2131231523);
            this.checkBox.setVisibility(4);
            this.checkBox.setColor(Theme.getColor("checkbox"), Theme.getColor("checkboxCheck"));
            this.addView(this.checkBox, LayoutHelper.createFrame(22, 22f, 53, 0f, 2f, 2f, 0f));
        }

        static AnimatorSet access$000(PhotoVideoView arg0) {
            return arg0.animator;
        }

        static AnimatorSet access$002(PhotoVideoView arg0, AnimatorSet arg1) {
            arg0.animator = arg1;
            return arg1;
        }

        static CheckBox access$400(PhotoVideoView arg0) {
            return arg0.checkBox;
        }

        static BackupImageView access$500(PhotoVideoView arg0) {
            return arg0.imageView;
        }

        static FrameLayout access$600(PhotoVideoView arg0) {
            return arg0.videoInfoContainer;
        }

        static TextView access$700(PhotoVideoView arg0) {
            return arg0.videoTextView;
        }

        public void clearAnimation() {
            super.clearAnimation();
            if(this.animator != null) {
                this.animator.cancel();
                this.animator = null;
            }
        }

        public boolean onTouchEvent(MotionEvent arg4) {
            if(Build$VERSION.SDK_INT >= 21) {
                this.selector.drawableHotspotChanged(arg4.getX(), arg4.getY());
            }

            return super.onTouchEvent(arg4);
        }

        public void setChecked(boolean arg10, boolean arg11) {
            if(this.checkBox.getVisibility() != 0) {
                this.checkBox.setVisibility(0);
            }

            this.checkBox.setChecked(arg10, arg11);
            if(this.animator != null) {
                this.animator.cancel();
                this.animator = null;
            }

            int v0 = -657931;
            float v2 = 1f;
            if(arg11) {
                if(arg10) {
                    this.setBackgroundColor(v0);
                }

                this.animator = new AnimatorSet();
                AnimatorSet v11 = this.animator;
                Animator[] v0_1 = new Animator[2];
                FrameLayout v4 = this.container;
                String v5 = "scaleX";
                float[] v7 = new float[1];
                float v8 = arg10 ? 0.85f : 1f;
                v7[0] = v8;
                v0_1[0] = ObjectAnimator.ofFloat(v4, v5, v7);
                v4 = this.container;
                v5 = "scaleY";
                v7 = new float[1];
                if(arg10) {
                    v2 = 0.85f;
                }

                v7[0] = v2;
                v0_1[1] = ObjectAnimator.ofFloat(v4, v5, v7);
                v11.playTogether(v0_1);
                this.animator.setDuration(200);
                this.animator.addListener(new AnimatorListenerAdapter(arg10) {
                    public void onAnimationCancel(Animator arg2) {
                        if(this.this$1.animator != null && (this.this$1.animator.equals(arg2))) {
                            this.this$1.animator = null;
                        }
                    }

                    public void onAnimationEnd(Animator arg2) {
                        if(this.this$1.animator != null && (this.this$1.animator.equals(arg2))) {
                            this.this$1.animator = null;
                            if(!this.val$checked) {
                                this.this$1.setBackgroundColor(0);
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
                    v0 = 0;
                }

                this.setBackgroundColor(v0);
                FrameLayout v11_1 = this.container;
                float v0_2 = arg10 ? 0.85f : 1f;
                v11_1.setScaleX(v0_2);
                v11_1 = this.container;
                if(arg10) {
                    v2 = 0.85f;
                }

                v11_1.setScaleY(v2);
            }
        }
    }

    public interface SharedPhotoVideoCellDelegate {
        void didClickItem(SharedPhotoVideoCell arg1, int arg2, MessageObject arg3, int arg4);

        boolean didLongClickItem(SharedPhotoVideoCell arg1, int arg2, MessageObject arg3, int arg4);
    }

    private SharedPhotoVideoCellDelegate delegate;
    private int[] indeces;
    private boolean isFirst;
    private int itemsCount;
    private MessageObject[] messageObjects;
    private PhotoVideoView[] photoVideoViews;

    public SharedPhotoVideoCell(Context arg5) {
        super(arg5);
        int v0 = 6;
        this.messageObjects = new MessageObject[v0];
        this.photoVideoViews = new PhotoVideoView[v0];
        this.indeces = new int[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.photoVideoViews[v1] = new PhotoVideoView(this, arg5);
            this.addView(this.photoVideoViews[v1]);
            this.photoVideoViews[v1].setVisibility(4);
            this.photoVideoViews[v1].setTag(Integer.valueOf(v1));
            this.photoVideoViews[v1].setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg5) {
                    if(SharedPhotoVideoCell.access$100(SharedPhotoVideoCell.this) != null) {
                        int v5 = arg5.getTag().intValue();
                        SharedPhotoVideoCell.access$100(SharedPhotoVideoCell.this).didClickItem(SharedPhotoVideoCell.this, SharedPhotoVideoCell.access$200(SharedPhotoVideoCell.this)[v5], SharedPhotoVideoCell.access$300(SharedPhotoVideoCell.this)[v5], v5);
                    }
                }
            });
            this.photoVideoViews[v1].setOnLongClickListener(new View$OnLongClickListener() {
                public boolean onLongClick(View arg5) {
                    if(SharedPhotoVideoCell.access$100(SharedPhotoVideoCell.this) != null) {
                        int v5 = arg5.getTag().intValue();
                        return SharedPhotoVideoCell.access$100(SharedPhotoVideoCell.this).didLongClickItem(SharedPhotoVideoCell.this, SharedPhotoVideoCell.access$200(SharedPhotoVideoCell.this)[v5], SharedPhotoVideoCell.access$300(SharedPhotoVideoCell.this)[v5], v5);
                    }

                    return 0;
                }
            });
        }
    }

    static SharedPhotoVideoCellDelegate access$100(SharedPhotoVideoCell arg0) {
        return arg0.delegate;
    }

    static int[] access$200(SharedPhotoVideoCell arg0) {
        return arg0.indeces;
    }

    static MessageObject[] access$300(SharedPhotoVideoCell arg0) {
        return arg0.messageObjects;
    }

    public BackupImageView getImageView(int arg2) {
        if(arg2 >= this.itemsCount) {
            return null;
        }

        return PhotoVideoView.access$500(this.photoVideoViews[arg2]);
    }

    public MessageObject getMessageObject(int arg2) {
        if(arg2 >= this.itemsCount) {
            return null;
        }

        return this.messageObjects[arg2];
    }

    protected void onMeasure(int arg7, int arg8) {
        float v0 = 4f;
        arg8 = AndroidUtilities.isTablet() ? AndroidUtilities.dp(490f) : AndroidUtilities.displaySize.x;
        arg8 = (arg8 - (this.itemsCount + 1) * AndroidUtilities.dp(v0)) / this.itemsCount;
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < this.itemsCount; ++v2) {
            ViewGroup$LayoutParams v3 = this.photoVideoViews[v2].getLayoutParams();
            int v4 = this.isFirst ? 0 : AndroidUtilities.dp(v0);
            ((FrameLayout$LayoutParams)v3).topMargin = v4;
            ((FrameLayout$LayoutParams)v3).leftMargin = (AndroidUtilities.dp(v0) + arg8) * v2 + AndroidUtilities.dp(v0);
            ((FrameLayout$LayoutParams)v3).width = arg8;
            ((FrameLayout$LayoutParams)v3).height = arg8;
            ((FrameLayout$LayoutParams)v3).gravity = 51;
            this.photoVideoViews[v2].setLayoutParams(v3);
        }

        if(this.isFirst) {
        }
        else {
            v1 = AndroidUtilities.dp(v0);
        }

        super.onMeasure(arg7, View$MeasureSpec.makeMeasureSpec(v1 + arg8, 1073741824));
    }

    public void setChecked(int arg2, boolean arg3, boolean arg4) {
        this.photoVideoViews[arg2].setChecked(arg3, arg4);
    }

    public void setDelegate(SharedPhotoVideoCellDelegate arg1) {
        this.delegate = arg1;
    }

    public void setIsFirst(boolean arg1) {
        this.isFirst = arg1;
    }

    public void setItem(int arg18, int arg19, MessageObject arg20) {
        Bitmap v12;
        Drawable v11;
        String v10;
        String v9;
        TLObject v8;
        BackupImageView v7;
        FileLocation v13;
        SharedPhotoVideoCell v0 = this;
        MessageObject v2 = arg20;
        v0.messageObjects[arg18] = v2;
        v0.indeces[arg18] = arg19;
        int v3 = 4;
        if(v2 != null) {
            v0.photoVideoViews[arg18].setVisibility(0);
            PhotoVideoView v1 = v0.photoVideoViews[arg18];
            PhotoVideoView.access$500(v1).getImageReceiver().setParentMessageObject(v2);
            PhotoVideoView.access$500(v1).getImageReceiver().setVisible(PhotoViewer.isShowingImage(arg20) ^ 1, false);
            int v6 = 2131231465;
            if(arg20.isVideo()) {
                PhotoVideoView.access$600(v1).setVisibility(0);
                v3 = 0;
                while(true) {
                    if(v3 < arg20.getDocument().attributes.size()) {
                        Object v4 = arg20.getDocument().attributes.get(v3);
                        if((v4 instanceof TL_documentAttributeVideo)) {
                            v3 = ((DocumentAttribute)v4).duration;
                        }
                        else {
                            ++v3;
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    goto label_43;
                }

                v3 = 0;
            label_43:
                int v4_1 = v3 / 60;
                PhotoVideoView.access$700(v1).setText(String.format("%d:%02d", Integer.valueOf(v4_1), Integer.valueOf(v3 - v4_1 * 60)));
                if(arg20.getDocument().thumb == null) {
                    goto label_75;
                }

                v13 = arg20.getDocument().thumb.location;
                v7 = PhotoVideoView.access$500(v1);
                v8 = null;
                v9 = null;
                v10 = null;
                v11 = ApplicationLoader.applicationContext.getResources().getDrawable(v6);
                v12 = null;
                goto label_70;
            }
            else {
                if(((v2.messageOwner.media instanceof TL_messageMediaPhoto)) && v2.messageOwner.media.photo != null && !v2.photoThumbs.isEmpty()) {
                    PhotoVideoView.access$600(v1).setVisibility(v3);
                    PhotoSize v2_1 = FileLoader.getClosestPhotoSizeWithSize(v2.photoThumbs, 80);
                    v7 = PhotoVideoView.access$500(v1);
                    v8 = null;
                    v9 = null;
                    v10 = null;
                    v11 = ApplicationLoader.applicationContext.getResources().getDrawable(v6);
                    v12 = null;
                    v13 = v2_1.location;
                label_70:
                    v7.setImage(v8, v9, v10, v11, v12, v13, "b", null, 0);
                    return;
                }

                PhotoVideoView.access$600(v1).setVisibility(v3);
            }

        label_75:
            PhotoVideoView.access$500(v1).setImageResource(v6);
        }
        else {
            v0.photoVideoViews[arg18].clearAnimation();
            v0.photoVideoViews[arg18].setVisibility(v3);
            v0.messageObjects[arg18] = null;
        }
    }

    public void setItemsCount(int arg5) {
        int v1;
        for(v1 = 0; v1 < this.photoVideoViews.length; ++v1) {
            this.photoVideoViews[v1].clearAnimation();
            PhotoVideoView v2 = this.photoVideoViews[v1];
            int v3 = v1 < arg5 ? 0 : 4;
            v2.setVisibility(v3);
        }

        this.itemsCount = arg5;
    }

    public void updateCheckboxColor() {
        int v0;
        for(v0 = 0; v0 < 6; ++v0) {
            PhotoVideoView.access$400(this.photoVideoViews[v0]).setColor(Theme.getColor("checkbox"), Theme.getColor("checkboxCheck"));
        }
    }
}

