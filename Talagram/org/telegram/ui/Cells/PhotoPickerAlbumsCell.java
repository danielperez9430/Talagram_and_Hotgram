package org.telegram.ui.Cells;

import android.content.Context;
import android.os.Build$VERSION;
import android.text.TextUtils$TruncateAt;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaController$AlbumEntry;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class PhotoPickerAlbumsCell extends FrameLayout {
    class AlbumView extends FrameLayout {
        private TextView countTextView;
        private BackupImageView imageView;
        private TextView nameTextView;
        private View selector;

        public AlbumView(PhotoPickerAlbumsCell arg18, Context arg19) {
            PhotoPickerAlbumsCell.this = arg18;
            super(arg19);
            this.imageView = new BackupImageView(arg19);
            this.addView(this.imageView, LayoutHelper.createFrame(-1, -1f));
            LinearLayout v1 = new LinearLayout(arg19);
            v1.setOrientation(0);
            v1.setBackgroundColor(2130706432);
            this.addView(((View)v1), LayoutHelper.createFrame(-1, 28, 83));
            this.nameTextView = new TextView(arg19);
            this.nameTextView.setTextSize(1, 13f);
            this.nameTextView.setTextColor(-1);
            this.nameTextView.setSingleLine(true);
            this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
            this.nameTextView.setMaxLines(1);
            this.nameTextView.setGravity(16);
            v1.addView(this.nameTextView, LayoutHelper.createLinear(0, -1, 1f, 8, 0, 0, 0));
            this.countTextView = new TextView(arg19);
            this.countTextView.setTextSize(1, 13f);
            this.countTextView.setTextColor(-5592406);
            this.countTextView.setSingleLine(true);
            this.countTextView.setEllipsize(TextUtils$TruncateAt.END);
            this.countTextView.setMaxLines(1);
            this.countTextView.setGravity(16);
            v1.addView(this.countTextView, LayoutHelper.createLinear(-2, -1, 4f, 0f, 4f, 0f));
            this.selector = new View(arg19);
            this.selector.setBackgroundDrawable(Theme.getSelectorDrawable(false));
            this.addView(this.selector, LayoutHelper.createFrame(-1, -1f));
        }

        static BackupImageView access$200(AlbumView arg0) {
            return arg0.imageView;
        }

        static TextView access$300(AlbumView arg0) {
            return arg0.nameTextView;
        }

        static TextView access$400(AlbumView arg0) {
            return arg0.countTextView;
        }

        public boolean onTouchEvent(MotionEvent arg4) {
            if(Build$VERSION.SDK_INT >= 21) {
                this.selector.drawableHotspotChanged(arg4.getX(), arg4.getY());
            }

            return super.onTouchEvent(arg4);
        }
    }

    public interface PhotoPickerAlbumsCellDelegate {
        void didSelectAlbum(AlbumEntry arg1);
    }

    private AlbumEntry[] albumEntries;
    private AlbumView[] albumViews;
    private int albumsCount;
    private PhotoPickerAlbumsCellDelegate delegate;

    public PhotoPickerAlbumsCell(Context arg5) {
        super(arg5);
        int v0 = 4;
        this.albumEntries = new AlbumEntry[v0];
        this.albumViews = new AlbumView[v0];
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.albumViews[v1] = new AlbumView(this, arg5);
            this.addView(this.albumViews[v1]);
            this.albumViews[v1].setVisibility(v0);
            this.albumViews[v1].setTag(Integer.valueOf(v1));
            this.albumViews[v1].setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg3) {
                    if(PhotoPickerAlbumsCell.access$000(PhotoPickerAlbumsCell.this) != null) {
                        PhotoPickerAlbumsCell.access$000(PhotoPickerAlbumsCell.this).didSelectAlbum(PhotoPickerAlbumsCell.access$100(PhotoPickerAlbumsCell.this)[arg3.getTag().intValue()]);
                    }
                }
            });
        }
    }

    static PhotoPickerAlbumsCellDelegate access$000(PhotoPickerAlbumsCell arg0) {
        return arg0.delegate;
    }

    static AlbumEntry[] access$100(PhotoPickerAlbumsCell arg0) {
        return arg0.albumEntries;
    }

    protected void onMeasure(int arg5, int arg6) {
        float v0 = 4f;
        arg6 = AndroidUtilities.isTablet() ? AndroidUtilities.dp(490f) : AndroidUtilities.displaySize.x;
        arg6 = (arg6 - (this.albumsCount + 1) * AndroidUtilities.dp(v0)) / this.albumsCount;
        int v1;
        for(v1 = 0; v1 < this.albumsCount; ++v1) {
            ViewGroup$LayoutParams v2 = this.albumViews[v1].getLayoutParams();
            ((FrameLayout$LayoutParams)v2).topMargin = AndroidUtilities.dp(v0);
            ((FrameLayout$LayoutParams)v2).leftMargin = (AndroidUtilities.dp(v0) + arg6) * v1;
            ((FrameLayout$LayoutParams)v2).width = arg6;
            ((FrameLayout$LayoutParams)v2).height = arg6;
            ((FrameLayout$LayoutParams)v2).gravity = 51;
            this.albumViews[v1].setLayoutParams(v2);
        }

        super.onMeasure(arg5, View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(v0) + arg6, 1073741824));
    }

    public void setAlbum(int arg8, AlbumEntry arg9) {
        String v6;
        StringBuilder v5;
        BackupImageView v0;
        this.albumEntries[arg8] = arg9;
        if(arg9 != null) {
            AlbumView v8 = this.albumViews[arg8];
            AlbumView.access$200(v8).setOrientation(0, true);
            int v3 = 2131231414;
            if(arg9.coverPhoto == null || arg9.coverPhoto.path == null) {
                AlbumView.access$200(v8).setImageResource(v3);
            }
            else {
                AlbumView.access$200(v8).setOrientation(arg9.coverPhoto.orientation, true);
                String v4 = null;
                if(arg9.coverPhoto.isVideo) {
                    v0 = AlbumView.access$200(v8);
                    v5 = new StringBuilder();
                    v6 = "vthumb://";
                }
                else {
                    v0 = AlbumView.access$200(v8);
                    v5 = new StringBuilder();
                    v6 = "thumb://";
                }

                v5.append(v6);
                v5.append(arg9.coverPhoto.imageId);
                v5.append(":");
                v5.append(arg9.coverPhoto.path);
                v0.setImage(v5.toString(), v4, this.getContext().getResources().getDrawable(v3));
            }

            AlbumView.access$300(v8).setText(arg9.bucketName);
            AlbumView.access$400(v8).setText(String.format("%d", Integer.valueOf(arg9.photos.size())));
        }
        else {
            this.albumViews[arg8].setVisibility(4);
        }
    }

    public void setAlbumsCount(int arg5) {
        int v1;
        for(v1 = 0; v1 < this.albumViews.length; ++v1) {
            AlbumView v2 = this.albumViews[v1];
            int v3 = v1 < arg5 ? 0 : 4;
            v2.setVisibility(v3);
        }

        this.albumsCount = arg5;
    }

    public void setDelegate(PhotoPickerAlbumsCellDelegate arg1) {
        this.delegate = arg1;
    }
}

