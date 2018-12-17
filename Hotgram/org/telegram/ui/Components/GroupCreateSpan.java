package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController$Contact;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;

public class GroupCreateSpan extends View {
    private AvatarDrawable avatarDrawable;
    private static Paint backPaint;
    private int[] colors;
    private Contact currentContact;
    private Drawable deleteDrawable;
    private boolean deleting;
    private ImageReceiver imageReceiver;
    private String key;
    private long lastUpdateTime;
    private StaticLayout nameLayout;
    private float progress;
    private RectF rect;
    private static TextPaint textPaint;
    private int textWidth;
    private float textX;
    private int uid;

    static {
        GroupCreateSpan.textPaint = new TextPaint(1);
        GroupCreateSpan.backPaint = new Paint(1);
    }

    public GroupCreateSpan(Context arg2, Contact arg3) {
        this(arg2, null, arg3);
    }

    public GroupCreateSpan(Context arg12, User arg13, Contact arg14) {
        String v14;
        super(arg12);
        this.rect = new RectF();
        this.colors = new int[8];
        this.currentContact = arg14;
        this.deleteDrawable = this.getResources().getDrawable(2131231034);
        GroupCreateSpan.textPaint.setTextSize(((float)AndroidUtilities.dp(14f)));
        this.avatarDrawable = new AvatarDrawable();
        this.avatarDrawable.setTextSize(AndroidUtilities.dp(12f));
        if(arg13 != null) {
            this.avatarDrawable.setInfo(arg13);
            this.uid = arg13.id;
        }
        else {
            this.avatarDrawable.setInfo(0, arg14.first_name, arg14.last_name, false);
            this.uid = arg14.contact_id;
            this.key = arg14.key;
        }

        this.imageReceiver = new ImageReceiver();
        this.imageReceiver.setRoundRadius(AndroidUtilities.dp(16f));
        this.imageReceiver.setParentView(((View)this));
        this.imageReceiver.setImageCoords(0, 0, AndroidUtilities.dp(32f), AndroidUtilities.dp(32f));
        int v0 = AndroidUtilities.isTablet() ? AndroidUtilities.dp(366f) : Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y) - AndroidUtilities.dp(164f);
        v0 /= 2;
        if(arg13 != null) {
            v14 = UserObject.getFirstName(arg13);
        }
        else if(!TextUtils.isEmpty(arg14.first_name)) {
            v14 = arg14.first_name;
        }
        else {
            v14 = arg14.last_name;
        }

        this.nameLayout = new StaticLayout(TextUtils.ellipsize(v14.replace('\n', ' '), GroupCreateSpan.textPaint, ((float)v0), TextUtils$TruncateAt.END), GroupCreateSpan.textPaint, 1000, Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
        if(this.nameLayout.getLineCount() > 0) {
            this.textWidth = ((int)Math.ceil(((double)this.nameLayout.getLineWidth(0))));
            this.textX = -this.nameLayout.getLineLeft(0);
        }

        FileLocation v12 = null;
        if(arg13 != null && arg13.photo != null) {
            v12 = arg13.photo.photo_small;
        }

        this.imageReceiver.setImage(v12, null, "50_50", this.avatarDrawable, null, null, 0, null, 1);
        this.updateColors();
    }

    public GroupCreateSpan(Context arg2, User arg3) {
        this(arg2, arg3, null);
    }

    public void cancelDeleteAnimation() {
        if(!this.deleting) {
            return;
        }

        this.deleting = false;
        this.lastUpdateTime = System.currentTimeMillis();
        this.invalidate();
    }

    public Contact getContact() {
        return this.currentContact;
    }

    public String getKey() {
        return this.key;
    }

    public int getUid() {
        return this.uid;
    }

    public boolean isDeleting() {
        return this.deleting;
    }

    protected void onDraw(Canvas arg11) {
        // Method was not decompiled
    }

    protected void onMeasure(int arg1, int arg2) {
        this.setMeasuredDimension(AndroidUtilities.dp(57f) + this.textWidth, AndroidUtilities.dp(32f));
    }

    public void startDeleteAnimation() {
        if(this.deleting) {
            return;
        }

        this.deleting = true;
        this.lastUpdateTime = System.currentTimeMillis();
        this.invalidate();
    }

    public void updateColors() {
        int v0 = Theme.getColor("avatar_backgroundGroupCreateSpanBlue");
        int v1 = Theme.getColor("groupcreate_spanBackground");
        int v2 = Theme.getColor("groupcreate_spanText");
        this.colors[0] = Color.red(v1);
        this.colors[1] = Color.red(v0);
        this.colors[2] = Color.green(v1);
        this.colors[3] = Color.green(v0);
        this.colors[4] = Color.blue(v1);
        this.colors[5] = Color.blue(v0);
        this.colors[6] = Color.alpha(v1);
        this.colors[7] = Color.alpha(v0);
        GroupCreateSpan.textPaint.setColor(v2);
        this.deleteDrawable.setColorFilter(new PorterDuffColorFilter(v2, PorterDuff$Mode.MULTIPLY));
        GroupCreateSpan.backPaint.setColor(v1);
        this.avatarDrawable.setColor(AvatarDrawable.getColorForId(5));
    }
}

