package org.telegram.ui.Cells;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.a.b;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class DrawerProfileCell extends FrameLayout {
    private boolean accountsShowed;
    private ImageView arrowView;
    private BackupImageView avatarImageView;
    private Integer currentColor;
    private Rect destRect;
    private TextView nameTextView;
    private Paint paint;
    private TextView phoneTextView;
    private ImageView shadowView;
    private Rect srcRect;

    public DrawerProfileCell(Context arg22) {
        DrawerProfileCell v0 = this;
        Context v1 = arg22;
        super(arg22);
        v0.srcRect = new Rect();
        v0.destRect = new Rect();
        v0.paint = new Paint();
        v0.shadowView = new ImageView(v1);
        v0.shadowView.setVisibility(4);
        v0.shadowView.setScaleType(ImageView$ScaleType.FIT_XY);
        v0.shadowView.setImageResource(2131230955);
        ImageView v2 = v0.shadowView;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        v0.addView(((View)v2), LayoutHelper.createFrame(-1, 70, v3 | 80));
        v0.avatarImageView = new BackupImageView(v1);
        v0.avatarImageView.getImageReceiver().setRoundRadius(AndroidUtilities.dp(32f));
        BackupImageView v2_1 = v0.avatarImageView;
        int v6 = 64;
        float v7 = 64f;
        v3 = LocaleController.isRTL ? 5 : 3;
        int v8 = v3 | 80;
        float v3_1 = LocaleController.isRTL ? 0f : 16f;
        float v11 = LocaleController.isRTL ? 16f : 0f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v6, v7, v8, v3_1, 0f, v11, 67f));
        v0.nameTextView = new TextView(v1);
        v0.nameTextView.setTextSize(1, 15f);
        v0.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0.nameTextView.setLines(1);
        v0.nameTextView.setMaxLines(1);
        v0.nameTextView.setSingleLine(true);
        TextView v2_2 = v0.nameTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_2.setGravity(v3);
        v0.nameTextView.setTypeface(AndroidUtilities.getTypeface(""));
        v0.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        v2_2 = v0.nameTextView;
        int v14 = -1;
        float v15 = -2f;
        v3 = LocaleController.isRTL ? 5 : 3;
        int v16 = v3 | 80;
        float v17 = LocaleController.isRTL ? 76f : 16f;
        float v19 = LocaleController.isRTL ? 16f : 76f;
        v0.addView(((View)v2_2), LayoutHelper.createFrame(v14, v15, v16, v17, 0f, v19, 28f));
        v0.phoneTextView = new TextView(v1);
        v0.phoneTextView.setTextSize(1, 13f);
        v0.phoneTextView.setLines(1);
        v0.phoneTextView.setMaxLines(1);
        v0.phoneTextView.setSingleLine(true);
        v2_2 = v0.phoneTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_2.setGravity(v3);
        v0.phoneTextView.setTypeface(AndroidUtilities.getTypeface(""));
        v2_2 = v0.phoneTextView;
        v14 = -1;
        v15 = -2f;
        v3 = LocaleController.isRTL ? 5 : 3;
        v16 = v3 | 80;
        v17 = LocaleController.isRTL ? 76f : 16f;
        v19 = LocaleController.isRTL ? 16f : 76f;
        v0.addView(((View)v2_2), LayoutHelper.createFrame(v14, v15, v16, v17, 0f, v19, 9f));
        v0.arrowView = new ImageView(v1);
        v0.arrowView.setScaleType(ImageView$ScaleType.CENTER);
        ImageView v1_1 = v0.arrowView;
        if(LocaleController.isRTL) {
        }
        else {
            v4 = 5;
        }

        v0.addView(((View)v1_1), LayoutHelper.createFrame(59, 59, v4 | 80));
    }

    static boolean access$000(DrawerProfileCell arg0) {
        return arg0.accountsShowed;
    }

    static boolean access$002(DrawerProfileCell arg0, boolean arg1) {
        arg0.accountsShowed = arg1;
        return arg1;
    }

    static ImageView access$100(DrawerProfileCell arg0) {
        return arg0.arrowView;
    }

    public boolean isAccountsShowed() {
        return this.accountsShowed;
    }

    protected void onDraw(Canvas arg8) {
        Drawable v0 = Theme.getCachedWallpaper();
        int v1 = Theme.hasThemeKey("chats_menuTopShadow") ? Theme.getColor("chats_menuTopShadow") : Theme.getServiceMessageColor() | -16777216;
        if(this.currentColor == null || this.currentColor.intValue() != v1) {
            this.currentColor = Integer.valueOf(v1);
            this.shadowView.getDrawable().setColorFilter(new PorterDuffColorFilter(v1, PorterDuff$Mode.MULTIPLY));
        }

        this.nameTextView.setTextColor(Theme.getColor("chats_menuName"));
        if((Theme.isCustomTheme()) && v0 != null) {
            this.phoneTextView.setTextColor(Theme.getColor("chats_menuPhone"));
            this.shadowView.setVisibility(0);
            if((v0 instanceof ColorDrawable)) {
                v0.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
                v0.draw(arg8);
            }
            else if((v0 instanceof BitmapDrawable)) {
                Bitmap v0_1 = ((BitmapDrawable)v0).getBitmap();
                float v1_1 = (((float)this.getMeasuredWidth())) / (((float)v0_1.getWidth()));
                float v3 = (((float)this.getMeasuredHeight())) / (((float)v0_1.getHeight()));
                if(v1_1 < v3) {
                    v1_1 = v3;
                }

                int v3_1 = ((int)((((float)this.getMeasuredWidth())) / v1_1));
                v1 = ((int)((((float)this.getMeasuredHeight())) / v1_1));
                int v4 = (v0_1.getWidth() - v3_1) / 2;
                int v5 = (v0_1.getHeight() - v1) / 2;
                this.srcRect.set(v4, v5, v3_1 + v4, v1 + v5);
                this.destRect.set(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
                try {
                    arg8.drawBitmap(v0_1, this.srcRect, this.destRect, this.paint);
                }
                catch(Throwable v8) {
                    FileLog.e(v8);
                }
            }
            else {
            }
        }
        else {
            this.shadowView.setVisibility(4);
            this.phoneTextView.setTextColor(Theme.getColor("chats_menuPhoneCats"));
            super.onDraw(arg8);
        }
    }

    protected void onMeasure(int arg4, int arg5) {
        float v0 = 148f;
        int v1 = 1073741824;
        if(Build$VERSION.SDK_INT >= 21) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg4), v1), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(v0) + AndroidUtilities.statusBarHeight, v1));
            goto label_24;
        }

        try {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg4), v1), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(v0), v1));
        }
        catch(Exception v5) {
            this.setMeasuredDimension(View$MeasureSpec.getSize(arg4), AndroidUtilities.dp(v0));
            FileLog.e(((Throwable)v5));
        }

    label_24:
        SharedPreferences v4 = ApplicationLoader.applicationContext.getSharedPreferences("plusconfig", 0);
        if(!v4.getBoolean("hideMobile", false) || (v4.getBoolean("showUsername", false))) {
            this.phoneTextView.setVisibility(0);
        }
        else {
            this.phoneTextView.setVisibility(8);
        }
    }

    public void setAccountsShowed(boolean arg2) {
        if(this.accountsShowed == arg2) {
            return;
        }

        this.accountsShowed = arg2;
        ImageView v2 = this.arrowView;
        int v0 = this.accountsShowed ? 2131231002 : 2131231001;
        v2.setImageResource(v0);
    }

    public void setOnArrowClickListener(View$OnClickListener arg3) {
        this.arrowView.setOnClickListener(new View$OnClickListener(arg3) {
            public void onClick(View arg2) {
                DrawerProfileCell.this.accountsShowed ^= 1;
                ImageView v2 = DrawerProfileCell.this.arrowView;
                int v0 = DrawerProfileCell.this.accountsShowed ? 2131231002 : 2131231001;
                v2.setImageResource(v0);
                this.val$onClickListener.onClick(DrawerProfileCell.this);
            }
        });
    }

    public void setUser(User arg4, boolean arg5) {
        String v5_1;
        FileLocation v0_1;
        if(arg4 == null) {
            return;
        }

        TLObject v0 = null;
        if(arg4.photo != null) {
            v0_1 = arg4.photo.photo_small;
        }

        this.accountsShowed = arg5;
        if(ApplicationLoader.applicationContext.getSharedPreferences("plusconfig", 0).getBoolean("showUsername", false)) {
            if(arg4.username != null && arg4.username.length() != 0) {
                v5_1 = "@" + arg4.username;
                goto label_41;
            }

            v5_1 = LocaleController.getString("UsernameEmpty", 2131626338);
        }
        else {
            b v5_2 = b.a();
            StringBuilder v1 = new StringBuilder();
            v1.append("+");
            v1.append(arg4.phone);
            v5_1 = v5_2.e(v1.toString());
        }

    label_41:
        ImageView v1_1 = this.arrowView;
        int v2 = this.accountsShowed ? 2131231002 : 2131231001;
        v1_1.setImageResource(v2);
        this.nameTextView.setText(UserObject.getUserName(arg4));
        this.phoneTextView.setText(((CharSequence)v5_1));
        AvatarDrawable v5_3 = new AvatarDrawable(arg4);
        v5_3.setColor(Theme.getColor("avatar_backgroundInProfileBlue"));
        this.avatarImageView.setImage(((TLObject)v0_1), "50_50", ((Drawable)v5_3));
    }
}

