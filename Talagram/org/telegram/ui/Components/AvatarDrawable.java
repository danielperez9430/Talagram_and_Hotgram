package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;

public class AvatarDrawable extends Drawable {
    private int color;
    private boolean drawBrodcast;
    private boolean drawPhoto;
    private boolean isProfile;
    private TextPaint namePaint;
    private int savedMessages;
    private StringBuilder stringBuilder;
    private float textHeight;
    private StaticLayout textLayout;
    private float textLeft;
    private float textWidth;

    public AvatarDrawable(User arg2) {
        this(arg2, false);
    }

    public AvatarDrawable(Chat arg2) {
        this(arg2, false);
    }

    public AvatarDrawable() {
        super();
        this.stringBuilder = new StringBuilder(5);
        this.namePaint = new TextPaint(1);
        this.namePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.namePaint.setTextSize(((float)AndroidUtilities.dp(18f)));
    }

    public AvatarDrawable(Chat arg7, boolean arg8) {
        this();
        this.isProfile = arg8;
        if(arg7 != null) {
            int v1 = arg7.id;
            String v2 = arg7.title;
            String v3 = null;
            boolean v4 = arg7.id < 0 ? true : false;
            this.setInfo(v1, v2, v3, v4, null);
        }
    }

    public AvatarDrawable(User arg7, boolean arg8) {
        this();
        this.isProfile = arg8;
        if(arg7 != null) {
            this.setInfo(arg7.id, arg7.first_name, arg7.last_name, false, null);
        }
    }

    public void draw(Canvas arg6) {
        Drawable v0_3;
        int v0_2;
        Rect v0 = this.getBounds();
        if(v0 == null) {
            return;
        }

        int v1 = v0.width();
        this.namePaint.setColor(Theme.getColor("avatar_text"));
        Theme.avatar_backgroundPaint.setColor(this.color);
        arg6.save();
        arg6.translate(((float)v0.left), ((float)v0.top));
        float v0_1 = ((float)v1);
        float v2 = 2f;
        float v3 = v0_1 / v2;
        arg6.drawCircle(v3, v3, v3, Theme.avatar_backgroundPaint);
        int v4 = 2;
        if(this.savedMessages == 0 || Theme.avatar_savedDrawable == null) {
            if((this.drawBrodcast) && Theme.avatar_broadcastDrawable != null) {
                v0_2 = (v1 - Theme.avatar_broadcastDrawable.getIntrinsicWidth()) / v4;
                v1 = (v1 - Theme.avatar_broadcastDrawable.getIntrinsicHeight()) / v4;
                Theme.avatar_broadcastDrawable.setBounds(v0_2, v1, Theme.avatar_broadcastDrawable.getIntrinsicWidth() + v0_2, Theme.avatar_broadcastDrawable.getIntrinsicHeight() + v1);
                v0_3 = Theme.avatar_broadcastDrawable;
                goto label_49;
            }

            if(this.textLayout != null) {
                arg6.translate((v0_1 - this.textWidth) / v2 - this.textLeft, (v0_1 - this.textHeight) / v2);
                this.textLayout.draw(arg6);
                goto label_109;
            }

            if(!this.drawPhoto) {
                goto label_109;
            }

            if(Theme.avatar_photoDrawable == null) {
                goto label_109;
            }

            v0_2 = (v1 - Theme.avatar_photoDrawable.getIntrinsicWidth()) / v4;
            v1 = (v1 - Theme.avatar_photoDrawable.getIntrinsicHeight()) / v4;
            Theme.avatar_photoDrawable.setBounds(v0_2, v1, Theme.avatar_photoDrawable.getIntrinsicWidth() + v0_2, Theme.avatar_photoDrawable.getIntrinsicHeight() + v1);
            v0_3 = Theme.avatar_photoDrawable;
        label_49:
            v0_3.draw(arg6);
        }
        else {
            v0_2 = Theme.avatar_savedDrawable.getIntrinsicWidth();
            int v2_1 = Theme.avatar_savedDrawable.getIntrinsicHeight();
            if(this.savedMessages == v4) {
                v0_2 = ((int)((((float)v0_2)) * 0.8f));
                v2_1 = ((int)((((float)v2_1)) * 0.8f));
            }

            int v3_1 = (v1 - v0_2) / v4;
            v1 = (v1 - v2_1) / v4;
            Theme.avatar_savedDrawable.setBounds(v3_1, v1, v0_2 + v3_1, v2_1 + v1);
            v0_3 = Theme.avatar_savedDrawable;
            goto label_49;
        }

    label_109:
        arg6.restore();
    }

    public static int getButtonColorForId(int arg1) {
        return Theme.getColor(Theme.keys_avatar_actionBarSelector[AvatarDrawable.getColorIndex(arg1)]);
    }

    public int getColor() {
        return this.color;
    }

    public static int getColorForId(int arg1) {
        return Theme.getColor(Theme.keys_avatar_background[AvatarDrawable.getColorIndex(arg1)]);
    }

    public static int getColorIndex(int arg1) {
        if(arg1 >= 0 && arg1 < 7) {
            return arg1;
        }

        return Math.abs(arg1 % Theme.keys_avatar_background.length);
    }

    public static int getIconColorForId(int arg1) {
        return Theme.getColor(Theme.keys_avatar_actionBarIcon[AvatarDrawable.getColorIndex(arg1)]);
    }

    public int getIntrinsicHeight() {
        return 0;
    }

    public int getIntrinsicWidth() {
        return 0;
    }

    public static int getNameColorForId(int arg1) {
        return Theme.getColor(Theme.keys_avatar_nameInMessage[AvatarDrawable.getColorIndex(arg1)]);
    }

    public int getOpacity() {
        return -2;
    }

    public static int getProfileBackColorForId(int arg1) {
        return Theme.getColor(Theme.keys_avatar_backgroundActionBar[AvatarDrawable.getColorIndex(arg1)]);
    }

    public static int getProfileColorForId(int arg1) {
        return Theme.getColor(Theme.keys_avatar_backgroundInProfile[AvatarDrawable.getColorIndex(arg1)]);
    }

    public static int getProfileTextColorForId(int arg1) {
        return Theme.getColor(Theme.keys_avatar_subtitleInProfile[AvatarDrawable.getColorIndex(arg1)]);
    }

    public void setAlpha(int arg1) {
    }

    public void setColor(int arg1) {
        this.color = arg1;
    }

    public void setColorFilter(ColorFilter arg1) {
    }

    public void setDrawPhoto(boolean arg1) {
        this.drawPhoto = arg1;
    }

    public void setInfo(User arg7) {
        if(arg7 != null) {
            this.setInfo(arg7.id, arg7.first_name, arg7.last_name, false, null);
        }
    }

    public void setInfo(Chat arg7) {
        if(arg7 != null) {
            int v1 = arg7.id;
            String v2 = arg7.title;
            String v3 = null;
            boolean v4 = arg7.id < 0 ? true : false;
            this.setInfo(v1, v2, v3, v4, null);
        }
    }

    public void setInfo(int arg7, String arg8, String arg9, boolean arg10) {
        this.setInfo(arg7, arg8, arg9, arg10, null);
    }

    public void setInfo(int arg9, String arg10, String arg11, boolean arg12, String arg13) {
        arg9 = this.isProfile ? AvatarDrawable.getProfileColorForId(arg9) : AvatarDrawable.getColorForId(arg9);
        this.color = arg9;
        this.drawBrodcast = arg12;
        this.savedMessages = 0;
        StaticLayout v12 = null;
        if(arg10 == null || arg10.length() == 0) {
            arg10 = arg11;
            arg11 = ((String)v12);
        }

        this.stringBuilder.setLength(0);
        if(arg13 != null) {
            this.stringBuilder.append(arg13);
        }
        else {
            if(arg10 != null && arg10.length() > 0) {
                this.stringBuilder.appendCodePoint(arg10.codePointAt(0));
            }

            int v13 = 17;
            int v0 = 32;
            if(arg11 != null && arg11.length() > 0) {
                int v10 = arg11.length() - 1;
                Integer v1 = ((Integer)v12);
                while(v10 >= 0) {
                    if(v1 != null && arg11.charAt(v10) == v0) {
                        break;
                    }

                    v1 = Integer.valueOf(arg11.codePointAt(v10));
                    --v10;
                }

                if(Build$VERSION.SDK_INT > v13) {
                    this.stringBuilder.append("‌");
                }

                this.stringBuilder.appendCodePoint(v1.intValue());
                goto label_79;
            }

            if(arg10 == null) {
                goto label_79;
            }

            if(arg10.length() <= 0) {
                goto label_79;
            }

            int v11;
            for(v11 = arg10.length() - 1; v11 >= 0; --v11) {
                if(arg10.charAt(v11) == v0 && v11 != arg10.length() - 1) {
                    int v1_1 = v11 + 1;
                    if(arg10.charAt(v1_1) != v0) {
                        if(Build$VERSION.SDK_INT > v13) {
                            this.stringBuilder.append("‌");
                        }

                        this.stringBuilder.appendCodePoint(arg10.codePointAt(v1_1));
                        break;
                    }
                }
            }
        }

    label_79:
        if(this.stringBuilder.length() > 0) {
            String v1_2 = this.stringBuilder.toString().toUpperCase();
            try {
                this.textLayout = new StaticLayout(((CharSequence)v1_2), this.namePaint, AndroidUtilities.dp(100f), Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
                if(this.textLayout.getLineCount() <= 0) {
                    return;
                }

                this.textLeft = this.textLayout.getLineLeft(0);
                this.textWidth = this.textLayout.getLineWidth(0);
                this.textHeight = ((float)this.textLayout.getLineBottom(0));
            }
            catch(Exception v9) {
                FileLog.e(((Throwable)v9));
            }
        }
        else {
            this.textLayout = v12;
        }
    }

    public void setProfile(boolean arg1) {
        this.isProfile = arg1;
    }

    public void setSavedMessages(int arg1) {
        this.savedMessages = arg1;
        this.color = Theme.getColor("avatar_backgroundSaved");
    }

    public void setTextSize(int arg2) {
        this.namePaint.setTextSize(((float)arg2));
    }
}

