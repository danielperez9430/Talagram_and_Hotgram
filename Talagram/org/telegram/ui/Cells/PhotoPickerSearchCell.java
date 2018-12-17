package org.telegram.ui.Cells;

import android.content.Context;
import android.os.Build$VERSION;
import android.text.TextUtils$TruncateAt;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class PhotoPickerSearchCell extends LinearLayout {
    public interface PhotoPickerSearchCellDelegate {
        void didPressedSearchButton(int arg1);
    }

    class SearchButton extends FrameLayout {
        private ImageView imageView;
        private View selector;
        private TextView textView1;
        private TextView textView2;

        public SearchButton(PhotoPickerSearchCell arg11, Context arg12) {
            PhotoPickerSearchCell.this = arg11;
            super(arg12);
            this.setBackgroundColor(-15066598);
            this.selector = new View(arg12);
            this.selector.setBackgroundDrawable(Theme.getSelectorDrawable(false));
            this.addView(this.selector, LayoutHelper.createFrame(-1, -1f));
            this.imageView = new ImageView(arg12);
            this.imageView.setScaleType(ImageView$ScaleType.CENTER);
            this.addView(this.imageView, LayoutHelper.createFrame(48, 48, 51));
            this.textView1 = new TextView(arg12);
            this.textView1.setGravity(16);
            this.textView1.setTextSize(1, 14f);
            this.textView1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.textView1.setTextColor(-1);
            this.textView1.setSingleLine(true);
            this.textView1.setEllipsize(TextUtils$TruncateAt.END);
            this.addView(this.textView1, LayoutHelper.createFrame(-1, -2f, 51, 51f, 8f, 4f, 0f));
            this.textView2 = new TextView(arg12);
            this.textView2.setGravity(16);
            this.textView2.setTextSize(1, 10f);
            this.textView2.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            this.textView2.setTextColor(-10066330);
            this.textView2.setSingleLine(true);
            this.textView2.setEllipsize(TextUtils$TruncateAt.END);
            this.addView(this.textView2, LayoutHelper.createFrame(-1, -2f, 51, 51f, 26f, 4f, 0f));
        }

        static TextView access$000(SearchButton arg0) {
            return arg0.textView1;
        }

        static TextView access$100(SearchButton arg0) {
            return arg0.textView2;
        }

        static ImageView access$200(SearchButton arg0) {
            return arg0.imageView;
        }

        public boolean onTouchEvent(MotionEvent arg4) {
            if(Build$VERSION.SDK_INT >= 21) {
                this.selector.drawableHotspotChanged(arg4.getX(), arg4.getY());
            }

            return super.onTouchEvent(arg4);
        }
    }

    private PhotoPickerSearchCellDelegate delegate;

    public PhotoPickerSearchCell(Context arg8, boolean arg9) {
        super(arg8);
        this.setOrientation(0);
        SearchButton v1 = new SearchButton(this, arg8);
        SearchButton.access$000(v1).setText(LocaleController.getString("SearchImages", 2131625962));
        SearchButton.access$100(v1).setText(LocaleController.getString("SearchImagesInfo", 2131625963));
        SearchButton.access$200(v1).setImageResource(2131231544);
        this.addView(((View)v1));
        ViewGroup$LayoutParams v2 = v1.getLayoutParams();
        float v3 = 0.5f;
        ((LinearLayout$LayoutParams)v2).weight = v3;
        ((LinearLayout$LayoutParams)v2).topMargin = AndroidUtilities.dp(4f);
        ((LinearLayout$LayoutParams)v2).height = AndroidUtilities.dp(48f);
        ((LinearLayout$LayoutParams)v2).width = 0;
        v1.setLayoutParams(v2);
        v1.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg2) {
                if(PhotoPickerSearchCell.access$300(PhotoPickerSearchCell.this) != null) {
                    PhotoPickerSearchCell.access$300(PhotoPickerSearchCell.this).didPressedSearchButton(0);
                }
            }
        });
        FrameLayout v1_1 = new FrameLayout(arg8);
        v1_1.setBackgroundColor(0);
        this.addView(((View)v1_1));
        v2 = v1_1.getLayoutParams();
        ((LinearLayout$LayoutParams)v2).topMargin = AndroidUtilities.dp(4f);
        ((LinearLayout$LayoutParams)v2).height = AndroidUtilities.dp(48f);
        ((LinearLayout$LayoutParams)v2).width = AndroidUtilities.dp(4f);
        v1_1.setLayoutParams(v2);
        v1 = new SearchButton(this, arg8);
        SearchButton.access$000(v1).setText(LocaleController.getString("SearchGifs", 2131625960));
        SearchButton.access$100(v1).setText("GIPHY");
        SearchButton.access$200(v1).setImageResource(2131231542);
        this.addView(((View)v1));
        ViewGroup$LayoutParams v8 = v1.getLayoutParams();
        ((LinearLayout$LayoutParams)v8).weight = v3;
        ((LinearLayout$LayoutParams)v8).topMargin = AndroidUtilities.dp(4f);
        ((LinearLayout$LayoutParams)v8).height = AndroidUtilities.dp(48f);
        ((LinearLayout$LayoutParams)v8).width = 0;
        v1.setLayoutParams(v8);
        if(arg9) {
            v1.setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg2) {
                    if(PhotoPickerSearchCell.access$300(PhotoPickerSearchCell.this) != null) {
                        PhotoPickerSearchCell.access$300(PhotoPickerSearchCell.this).didPressedSearchButton(1);
                    }
                }
            });
        }
        else {
            v1.setAlpha(v3);
        }
    }

    static PhotoPickerSearchCellDelegate access$300(PhotoPickerSearchCell arg0) {
        return arg0.delegate;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52f), 1073741824));
    }

    public void setDelegate(PhotoPickerSearchCellDelegate arg1) {
        this.delegate = arg1;
    }
}

