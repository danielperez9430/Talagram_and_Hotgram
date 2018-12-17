package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.text.TextUtils;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.LatLng;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController$SharingLocationInfo;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.LocationActivity$LiveLocation;

public class SharingLiveLocationCell extends FrameLayout {
    class org.telegram.ui.Cells.SharingLiveLocationCell$1 implements Runnable {
        org.telegram.ui.Cells.SharingLiveLocationCell$1(SharingLiveLocationCell arg1) {
            SharingLiveLocationCell.this = arg1;
            super();
        }

        public void run() {
            SharingLiveLocationCell.this.invalidate((((int)SharingLiveLocationCell.this.rect.left)) - 5, (((int)SharingLiveLocationCell.this.rect.top)) - 5, (((int)SharingLiveLocationCell.this.rect.right)) + 5, (((int)SharingLiveLocationCell.this.rect.bottom)) + 5);
            AndroidUtilities.runOnUIThread(SharingLiveLocationCell.this.invalidateRunnable, 1000);
        }
    }

    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private int currentAccount;
    private SharingLocationInfo currentInfo;
    private SimpleTextView distanceTextView;
    private Runnable invalidateRunnable;
    private LiveLocation liveLocation;
    private Location location;
    private SimpleTextView nameTextView;
    private RectF rect;

    public SharingLiveLocationCell(Context arg13, boolean arg14) {
        int v14_2;
        SimpleTextView v13;
        float v10;
        float v9;
        float v8;
        int v7;
        float v6;
        int v5;
        super(arg13);
        this.rect = new RectF();
        this.location = new Location("network");
        this.invalidateRunnable = new org.telegram.ui.Cells.SharingLiveLocationCell$1(this);
        this.avatarImageView = new BackupImageView(arg13);
        this.avatarImageView.setRoundRadius(AndroidUtilities.dp(20f));
        this.avatarDrawable = new AvatarDrawable();
        this.nameTextView = new SimpleTextView(arg13);
        this.nameTextView.setTextSize(16);
        this.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        SimpleTextView v0 = this.nameTextView;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v1);
        if(arg14) {
            BackupImageView v14 = this.avatarImageView;
            v5 = 40;
            v6 = 40f;
            v7 = LocaleController.isRTL ? 5 : 3;
            v7 |= 48;
            v8 = LocaleController.isRTL ? 0f : 17f;
            v9 = 13f;
            v10 = LocaleController.isRTL ? 17f : 0f;
            this.addView(((View)v14), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
            SimpleTextView v14_1 = this.nameTextView;
            v5 = -1;
            v6 = 20f;
            int v0_1 = LocaleController.isRTL ? 5 : 3;
            v7 = v0_1 | 48;
            v8 = LocaleController.isRTL ? 54f : 73f;
            v9 = 12f;
            v10 = LocaleController.isRTL ? 73f : 54f;
            this.addView(((View)v14_1), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
            this.distanceTextView = new SimpleTextView(arg13);
            this.distanceTextView.setTextSize(14);
            this.distanceTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
            v13 = this.distanceTextView;
            v14_2 = LocaleController.isRTL ? 5 : 3;
            v13.setGravity(v14_2);
            v13 = this.distanceTextView;
            v5 = -1;
            v6 = 20f;
            if(LocaleController.isRTL) {
                v2 = 5;
            }

            v7 = v2 | 48;
            v8 = LocaleController.isRTL ? 54f : 73f;
            v9 = 37f;
            if(!LocaleController.isRTL) {
                goto label_170;
            }

            v10 = 73f;
        }
        else {
            BackupImageView v13_1 = this.avatarImageView;
            v5 = 40;
            v6 = 40f;
            v14_2 = LocaleController.isRTL ? 5 : 3;
            v7 = v14_2 | 48;
            v8 = LocaleController.isRTL ? 0f : 17f;
            v9 = 7f;
            v10 = LocaleController.isRTL ? 17f : 0f;
            this.addView(((View)v13_1), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
            v13 = this.nameTextView;
            v5 = -2;
            v6 = -2f;
            if(LocaleController.isRTL) {
                v2 = 5;
            }

            v7 = v2 | 48;
            v8 = LocaleController.isRTL ? 54f : 74f;
            v9 = 17f;
            if(LocaleController.isRTL) {
                v10 = 74f;
                goto label_171;
            }

        label_170:
            v10 = 54f;
        }

    label_171:
        this.addView(((View)v13), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.setWillNotDraw(false);
    }

    static RectF access$000(SharingLiveLocationCell arg0) {
        return arg0.rect;
    }

    static Runnable access$100(SharingLiveLocationCell arg0) {
        return arg0.invalidateRunnable;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        AndroidUtilities.runOnUIThread(this.invalidateRunnable);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AndroidUtilities.cancelRunOnUIThread(this.invalidateRunnable);
    }

    protected void onDraw(Canvas arg11) {
        float v6;
        RectF v1_1;
        int v1;
        int v0;
        if(this.currentInfo == null && this.liveLocation == null) {
            return;
        }

        if(this.currentInfo != null) {
            v0 = this.currentInfo.stopTime;
            v1 = this.currentInfo.period;
        }
        else {
            v0 = this.liveLocation.object.date + this.liveLocation.object.media.period;
            v1 = this.liveLocation.object.media.period;
        }

        int v2 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if(v0 < v2) {
            return;
        }

        v0 -= v2;
        float v2_1 = (((float)Math.abs(v0))) / (((float)v1));
        float v3 = 42f;
        float v5 = 12f;
        float v7 = 43f;
        float v8 = 13f;
        if(LocaleController.isRTL) {
            v1_1 = this.rect;
            v8 = ((float)AndroidUtilities.dp(v8));
            if(this.distanceTextView != null) {
                v5 = 18f;
            }

            v5 = ((float)AndroidUtilities.dp(v5));
            v6 = ((float)AndroidUtilities.dp(v7));
            if(this.distanceTextView != null) {
                v3 = 48f;
            }

            v1_1.set(v8, v5, v6, ((float)AndroidUtilities.dp(v3)));
        }
        else {
            v1_1 = this.rect;
            v7 = ((float)(this.getMeasuredWidth() - AndroidUtilities.dp(v7)));
            if(this.distanceTextView != null) {
                v5 = 18f;
            }

            v5 = ((float)AndroidUtilities.dp(v5));
            v6 = ((float)(this.getMeasuredWidth() - AndroidUtilities.dp(v8)));
            if(this.distanceTextView != null) {
                v3 = 48f;
            }

            v1_1.set(v7, v5, v6, ((float)AndroidUtilities.dp(v3)));
        }

        String v1_2 = this.distanceTextView == null ? "dialog_liveLocationProgress" : "location_liveLocationProgress";
        v1 = Theme.getColor(v1_2);
        Theme.chat_radialProgress2Paint.setColor(v1);
        Theme.chat_livePaint.setColor(v1);
        arg11.drawArc(this.rect, -90f, v2_1 * -360f, false, Theme.chat_radialProgress2Paint);
        String v0_1 = LocaleController.formatLocationLeftTime(v0);
        v2_1 = this.rect.centerX() - Theme.chat_livePaint.measureText(v0_1) / 2f;
        float v1_3 = this.distanceTextView != null ? 37f : 31f;
        arg11.drawText(v0_1, v2_1, ((float)AndroidUtilities.dp(v1_3)), Theme.chat_livePaint);
    }

    protected void onMeasure(int arg2, int arg3) {
        arg3 = 1073741824;
        arg2 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), arg3);
        float v0 = this.distanceTextView != null ? 66f : 54f;
        super.onMeasure(arg2, View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(v0), arg3));
    }

    public void setDialog(SharingLocationInfo arg5) {
        TLObject v5_4;
        FileLocation v5_2;
        this.currentInfo = arg5;
        int v5 = ((int)arg5.did);
        String v0 = null;
        if(v5 > 0) {
            User v5_1 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(v5));
            if(v5_1 != null) {
                this.avatarDrawable.setInfo(v5_1);
                this.nameTextView.setText(ContactsController.formatName(v5_1.first_name, v5_1.last_name));
                if(v5_1.photo == null) {
                    goto label_44;
                }
                else if(v5_1.photo.photo_small != null) {
                    v5_2 = v5_1.photo.photo_small;
                }
                else {
                    goto label_44;
                }
            }
            else {
                goto label_44;
            }
        }
        else {
            Chat v5_3 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-v5));
            if(v5_3 != null) {
                this.avatarDrawable.setInfo(v5_3);
                this.nameTextView.setText(v5_3.title);
                if(v5_3.photo == null) {
                    goto label_44;
                }
                else if(v5_3.photo.photo_small != null) {
                    v5_2 = v5_3.photo.photo_small;
                }
                else {
                    goto label_44;
                }
            }
            else {
            label_44:
                v5_4 = ((TLObject)v0);
            }
        }

        this.avatarImageView.setImage(v5_4, v0, this.avatarDrawable);
    }

    public void setDialog(MessageObject arg9, Location arg10) {
        SimpleTextView v0_4;
        String v9_1;
        SimpleTextView v10_1;
        TLObject v3_3;
        FileLocation v3_2;
        String v0_1;
        int v0 = arg9.messageOwner.from_id;
        if(arg9.isForwarded()) {
            v0 = arg9.messageOwner.fwd_from.channel_id != 0 ? -arg9.messageOwner.fwd_from.channel_id : arg9.messageOwner.fwd_from.from_id;
        }

        this.currentAccount = arg9.currentAccount;
        AvatarDrawable v2 = null;
        String v1 = !TextUtils.isEmpty(arg9.messageOwner.media.address) ? arg9.messageOwner.media.address : ((String)v2);
        if(!TextUtils.isEmpty(arg9.messageOwner.media.title)) {
            v0_1 = arg9.messageOwner.media.title;
            Drawable v2_1 = this.getResources().getDrawable(2131231478);
            v2_1.setColorFilter(new PorterDuffColorFilter(Theme.getColor("location_sendLocationIcon"), PorterDuff$Mode.MULTIPLY));
            int v3 = Theme.getColor("location_placeLocationBackground");
            CombinedDrawable v5 = new CombinedDrawable(Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(40f), v3, v3), v2_1);
            v5.setCustomSize(AndroidUtilities.dp(40f), AndroidUtilities.dp(40f));
            v5.setIconSize(AndroidUtilities.dp(24f), AndroidUtilities.dp(24f));
            this.avatarImageView.setImageDrawable(((Drawable)v5));
        }
        else {
            String v3_1 = "";
            this.avatarDrawable = v2;
            if(v0 > 0) {
                User v0_2 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(v0));
                if(v0_2 != null) {
                    if(v0_2.photo != null) {
                        v3_2 = v0_2.photo.photo_small;
                    }
                    else {
                        v3_3 = ((TLObject)v2);
                    }

                    this.avatarDrawable = new AvatarDrawable(v0_2);
                    v0_1 = UserObject.getUserName(v0_2);
                }
                else {
                    goto label_99;
                }
            }
            else {
                Chat v0_3 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-v0));
                if(v0_3 != null) {
                    if(v0_3.photo != null) {
                        v3_2 = v0_3.photo.photo_small;
                    }
                    else {
                        v3_3 = ((TLObject)v2);
                    }

                    this.avatarDrawable = new AvatarDrawable(v0_3);
                    v0_1 = v0_3.title;
                    goto label_101;
                }

            label_99:
                v0_1 = v3_1;
                v3_3 = ((TLObject)v2);
            }

        label_101:
            this.avatarImageView.setImage(((TLObject)v3_2), ((String)v2), this.avatarDrawable);
        }

        this.nameTextView.setText(((CharSequence)v0_1));
        this.location.setLatitude(arg9.messageOwner.media.geo.lat);
        this.location.setLongitude(arg9.messageOwner.media.geo._long);
        if(arg10 != null) {
            float v9 = this.location.distanceTo(arg10);
            int v10 = 2131625029;
            v0 = 2131625190;
            int v2_2 = 2;
            float v5_1 = 1000f;
            if(v1 != null) {
                int v7 = 3;
                if(Float.compare(v9, v5_1) < 0) {
                    v10_1 = this.distanceTextView;
                    Object[] v6 = new Object[v7];
                    v6[0] = v1;
                    v6[1] = Integer.valueOf(((int)v9));
                    v6[v2_2] = LocaleController.getString("MetersAway", v0);
                    v9_1 = String.format("%s - %d %s", v6);
                    goto label_142;
                }
                else {
                    v0_4 = this.distanceTextView;
                    Object[] v7_1 = new Object[v7];
                    v7_1[0] = v1;
                    v7_1[1] = Float.valueOf(v9 / v5_1);
                    v7_1[v2_2] = LocaleController.getString("KMetersAway", v10);
                    v9_1 = String.format("%s - %.2f %s", v7_1);
                }
            }
            else if(v9 < v5_1) {
                v10_1 = this.distanceTextView;
                v9_1 = String.format("%d %s", Integer.valueOf(((int)v9)), LocaleController.getString("MetersAway", v0));
            label_142:
                v10_1.setText(((CharSequence)v9_1));
                return;
            }
            else {
                v0_4 = this.distanceTextView;
                v9_1 = String.format("%.2f %s", Float.valueOf(v9 / v5_1), LocaleController.getString("KMetersAway", v10));
            }

            v0_4.setText(((CharSequence)v9_1));
        }
        else {
            if(v1 != null) {
                this.distanceTextView.setText(((CharSequence)v1));
                return;
            }

            this.distanceTextView.setText(LocaleController.getString("Loading", 2131625103));
        }
    }

    public void setDialog(LiveLocation arg10, Location arg11) {
        Object[] v7_1;
        FileLocation v0_2;
        this.liveLocation = arg10;
        int v0 = arg10.id;
        String v1 = null;
        if(v0 > 0) {
            User v0_1 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(v0));
            this.avatarDrawable.setInfo(v0_1);
            if(v0_1 != null) {
                this.nameTextView.setText(ContactsController.formatName(v0_1.first_name, v0_1.last_name));
                if(v0_1.photo == null) {
                    goto label_43;
                }
                else if(v0_1.photo.photo_small != null) {
                    v0_2 = v0_1.photo.photo_small;
                }
                else {
                    goto label_43;
                }
            }
            else {
                goto label_43;
            }
        }
        else {
            Chat v0_3 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-v0));
            if(v0_3 != null) {
                this.avatarDrawable.setInfo(v0_3);
                this.nameTextView.setText(v0_3.title);
                if(v0_3.photo == null) {
                    goto label_43;
                }
                else if(v0_3.photo.photo_small != null) {
                    v0_2 = v0_3.photo.photo_small;
                }
                else {
                    goto label_43;
                }
            }
            else {
            label_43:
                TLObject v0_4 = ((TLObject)v1);
            }
        }

        LatLng v2 = arg10.marker.getPosition();
        this.location.setLatitude(v2.latitude);
        this.location.setLongitude(v2.longitude);
        int v10 = arg10.object.edit_date != 0 ? arg10.object.edit_date : arg10.object.date;
        long v2_1 = ((long)v10);
        String v10_1 = LocaleController.formatLocationUpdateDate(v2_1);
        if(arg11 != null) {
            float v11 = this.location.distanceTo(arg11);
            float v2_2 = 1000f;
            int v4 = 2;
            int v7 = 3;
            if(Float.compare(v11, v2_2) < 0) {
                SimpleTextView v2_3 = this.distanceTextView;
                v7_1 = new Object[v7];
                v7_1[0] = v10_1;
                v7_1[1] = Integer.valueOf(((int)v11));
                v7_1[v4] = LocaleController.getString("MetersAway", 2131625190);
                v2_3.setText(String.format("%s - %d %s", v7_1));
            }
            else {
                SimpleTextView v3 = this.distanceTextView;
                v7_1 = new Object[v7];
                v7_1[0] = v10_1;
                v7_1[1] = Float.valueOf(v11 / v2_2);
                v7_1[v4] = LocaleController.getString("KMetersAway", 2131625029);
                v3.setText(String.format("%s - %.2f %s", v7_1));
            }
        }
        else {
            this.distanceTextView.setText(((CharSequence)v10_1));
        }

        this.avatarImageView.setImage(((TLObject)v0_2), v1, this.avatarDrawable);
    }
}

