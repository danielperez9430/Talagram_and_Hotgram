package org.telegram.ui.ActionBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build$VERSION;
import android.os.SystemClock;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.StateSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.time.SunDate;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.ThemeEditorView;

public class Theme {
    final class org.telegram.ui.ActionBar.Theme$1 implements Runnable {
        org.telegram.ui.ActionBar.Theme$1() {
            super();
        }

        public void run() {
            Theme.switchDayRunnableScheduled = false;
            Theme.applyDayNightThemeMaybe(false);
        }
    }

    final class org.telegram.ui.ActionBar.Theme$2 implements Runnable {
        org.telegram.ui.ActionBar.Theme$2() {
            super();
        }

        public void run() {
            Theme.switchNightRunnableScheduled = false;
            Theme.applyDayNightThemeMaybe(true);
        }
    }

    final class org.telegram.ui.ActionBar.Theme$7 implements SensorEventListener {
        org.telegram.ui.ActionBar.Theme$7() {
            super();
        }

        public void onAccuracyChanged(Sensor arg1, int arg2) {
        }

        public void onSensorChanged(SensorEvent arg6) {
            // Method was not decompiled
        }
    }

    class AttachCameraDrawable extends Drawable {
        private Paint paint;
        private Path segment;

        public AttachCameraDrawable() {
            super();
            this.paint = new Paint(1);
            float v0 = ((float)AndroidUtilities.dp(54f));
            RectF v1 = new RectF(0f, 0f, v0, v0);
            this.segment = new Path();
            this.segment.moveTo(((float)AndroidUtilities.dp(23f)), ((float)AndroidUtilities.dp(20f)));
            this.segment.lineTo(((float)AndroidUtilities.dp(23f)), 0f);
            this.segment.arcTo(v1, -98f, 50f, false);
            this.segment.close();
        }

        public void draw(Canvas arg5) {
            arg5.save();
            float v0 = ((float)AndroidUtilities.dp(27f));
            arg5.rotate(-90f, v0, v0);
            int v1;
            for(v1 = 0; v1 < 6; ++v1) {
                switch(v1) {
                    case 0: {
                        goto label_26;
                    }
                    case 1: {
                        goto label_23;
                    }
                    case 2: {
                        goto label_20;
                    }
                    case 3: {
                        goto label_17;
                    }
                    case 4: {
                        goto label_14;
                    }
                    case 5: {
                        goto label_11;
                    }
                }

                goto label_30;
            label_17:
                Paint v2 = this.paint;
                String v3 = "chat_attachCameraIcon4";
                goto label_28;
            label_20:
                v2 = this.paint;
                v3 = "chat_attachCameraIcon3";
                goto label_28;
            label_23:
                v2 = this.paint;
                v3 = "chat_attachCameraIcon2";
                goto label_28;
            label_26:
                v2 = this.paint;
                v3 = "chat_attachCameraIcon1";
                goto label_28;
            label_11:
                v2 = this.paint;
                v3 = "chat_attachCameraIcon6";
                goto label_28;
            label_14:
                v2 = this.paint;
                v3 = "chat_attachCameraIcon5";
            label_28:
                v2.setColor(Theme.getColor(v3));
            label_30:
                arg5.rotate(60f, v0, v0);
                arg5.drawPath(this.segment, this.paint);
            }

            arg5.restore();
        }

        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(54f);
        }

        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(54f);
        }

        public int getMinimumHeight() {
            return AndroidUtilities.dp(54f);
        }

        public int getMinimumWidth() {
            return AndroidUtilities.dp(54f);
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int arg1) {
        }

        public void setColorFilter(ColorFilter arg1) {
            this.invalidateSelf();
        }
    }

    public class ThemeInfo {
        public String assetName;
        public String name;
        public String pathToFile;

        public ThemeInfo() {
            super();
        }

        public static ThemeInfo createWithJson(JSONObject arg3) {
            ThemeInfo v0 = null;
            if(arg3 == null) {
                return v0;
            }

            try {
                ThemeInfo v1 = new ThemeInfo();
                v1.name = arg3.getString("name");
                v1.pathToFile = arg3.getString("path");
                return v1;
            }
            catch(Exception v3) {
                FileLog.e(((Throwable)v3));
                return v0;
            }
        }

        public static ThemeInfo createWithString(String arg3) {
            ThemeInfo v1 = null;
            if(TextUtils.isEmpty(((CharSequence)arg3))) {
                return v1;
            }

            String[] v3 = arg3.split("\\|");
            if(v3.length != 2) {
                return v1;
            }

            ThemeInfo v0 = new ThemeInfo();
            v0.name = v3[0];
            v0.pathToFile = v3[1];
            return v0;
        }

        public String getName() {
            int v1;
            String v0;
            if("Default".equals(this.name)) {
                v0 = "Default";
                v1 = 2131624559;
            }
            else if("Blue".equals(this.name)) {
                v0 = "ThemeBlue";
                v1 = 2131626222;
            }
            else if("Dark".equals(this.name)) {
                v0 = "ThemeDark";
                v1 = 2131626223;
            }
            else {
                goto label_22;
            }

            return LocaleController.getString(v0, v1);
        label_22:
            return this.name;
        }

        public JSONObject getSaveJson() {
            try {
                JSONObject v0_1 = new JSONObject();
                v0_1.put("name", this.name);
                v0_1.put("path", this.pathToFile);
                return v0_1;
            }
            catch(Exception v0) {
                FileLog.e(((Throwable)v0));
                return null;
            }
        }
    }

    public static final int ACTION_BAR_AUDIO_SELECTOR_COLOR = 788529152;
    public static final int ACTION_BAR_MEDIA_PICKER_COLOR = -13421773;
    public static final int ACTION_BAR_PHOTO_VIEWER_COLOR = 2130706432;
    public static final int ACTION_BAR_PICKER_SELECTOR_COLOR = -12763843;
    public static final int ACTION_BAR_PLAYER_COLOR = -1;
    public static final int ACTION_BAR_VIDEO_EDIT_COLOR = -16777216;
    public static final int ACTION_BAR_WHITE_SELECTOR_COLOR = 1090519039;
    public static final int ARTICLE_VIEWER_MEDIA_PROGRESS_COLOR = -1;
    public static final int AUTO_NIGHT_TYPE_AUTOMATIC = 2;
    public static final int AUTO_NIGHT_TYPE_NONE = 0;
    public static final int AUTO_NIGHT_TYPE_SCHEDULED = 1;
    private static Field BitmapDrawable_mColorFilter = null;
    private static final int LIGHT_SENSOR_THEME_SWITCH_DELAY = 1800;
    private static final int LIGHT_SENSOR_THEME_SWITCH_NEAR_DELAY = 12000;
    private static final int LIGHT_SENSOR_THEME_SWITCH_NEAR_THRESHOLD = 12000;
    private static final float MAXIMUM_LUX_BREAKPOINT = 0f;
    private static Method StateListDrawable_getStateDrawableMethod = null;
    private static SensorEventListener ambientSensorListener = null;
    public static float autoNightBrighnessThreshold = 0f;
    public static String autoNightCityName = null;
    public static int autoNightDayEndTime = 0;
    public static int autoNightDayStartTime = 0;
    public static int autoNightLastSunCheckDay = 0;
    public static double autoNightLocationLatitude = 0;
    public static double autoNightLocationLongitude = 0;
    public static boolean autoNightScheduleByLocation = false;
    public static int autoNightSunriseTime = 0;
    public static int autoNightSunsetTime = 0;
    public static Paint avatar_backgroundPaint = null;
    public static Drawable avatar_broadcastDrawable = null;
    public static Drawable avatar_photoDrawable = null;
    public static Drawable avatar_savedDrawable = null;
    private static boolean canStartHolidayAnimation = false;
    public static Paint chat_actionBackgroundPaint = null;
    public static TextPaint chat_actionTextPaint = null;
    public static TextPaint chat_adminPaint = null;
    public static Drawable[] chat_attachButtonDrawables = null;
    public static TextPaint chat_audioPerformerPaint = null;
    public static TextPaint chat_audioTimePaint = null;
    public static TextPaint chat_audioTitlePaint = null;
    public static TextPaint chat_botButtonPaint = null;
    public static Drawable chat_botInlineDrawable = null;
    public static Drawable chat_botLinkDrawalbe = null;
    public static Paint chat_botProgressPaint = null;
    public static Paint chat_composeBackgroundPaint = null;
    public static Drawable chat_composeShadowDrawable = null;
    public static Drawable[] chat_contactDrawable = null;
    public static TextPaint chat_contactNamePaint = null;
    public static TextPaint chat_contactPhonePaint = null;
    public static TextPaint chat_contextResult_descriptionTextPaint = null;
    public static Drawable chat_contextResult_shadowUnderSwitchDrawable = null;
    public static TextPaint chat_contextResult_titleTextPaint = null;
    public static Drawable[] chat_cornerInner = null;
    public static Drawable[] chat_cornerOuter = null;
    public static Paint chat_deleteProgressPaint = null;
    public static Paint chat_docBackPaint = null;
    public static TextPaint chat_docNamePaint = null;
    public static TextPaint chat_durationPaint = null;
    public static CombinedDrawable[][] chat_fileMiniStatesDrawable = null;
    public static Drawable[][] chat_fileStatesDrawable = null;
    public static TextPaint chat_forwardNamePaint = null;
    public static TextPaint chat_gamePaint = null;
    public static Drawable chat_goIconDrawable = null;
    public static TextPaint chat_infoPaint = null;
    public static Drawable chat_inlineResultAudio = null;
    public static Drawable chat_inlineResultFile = null;
    public static Drawable chat_inlineResultLocation = null;
    public static TextPaint chat_instantViewPaint = null;
    public static Paint chat_instantViewRectPaint = null;
    public static Drawable[][] chat_ivStatesDrawable = null;
    public static TextPaint chat_livePaint = null;
    public static TextPaint chat_locationAddressPaint = null;
    public static Drawable[] chat_locationDrawable = null;
    public static TextPaint chat_locationTitlePaint = null;
    public static Drawable chat_lockIconDrawable = null;
    public static Drawable chat_msgAvatarLiveLocationDrawable = null;
    public static TextPaint chat_msgBotButtonPaint = null;
    public static Drawable chat_msgBroadcastDrawable = null;
    public static Drawable chat_msgBroadcastMediaDrawable = null;
    public static Drawable chat_msgCallDownGreenDrawable = null;
    public static Drawable chat_msgCallDownRedDrawable = null;
    public static Drawable chat_msgCallUpGreenDrawable = null;
    public static Drawable chat_msgCallUpRedDrawable = null;
    public static Drawable chat_msgErrorDrawable = null;
    public static Paint chat_msgErrorPaint = null;
    public static TextPaint chat_msgGameTextPaint = null;
    public static Drawable chat_msgInCallDrawable = null;
    public static Drawable chat_msgInCallSelectedDrawable = null;
    public static Drawable chat_msgInClockDrawable = null;
    public static Drawable chat_msgInDrawable = null;
    public static Drawable chat_msgInInstantDrawable = null;
    public static Drawable chat_msgInMediaDrawable = null;
    public static Drawable chat_msgInMediaSelectedDrawable = null;
    public static Drawable chat_msgInMediaShadowDrawable = null;
    public static Drawable chat_msgInMenuDrawable = null;
    public static Drawable chat_msgInMenuSelectedDrawable = null;
    public static Drawable chat_msgInSelectedClockDrawable = null;
    public static Drawable chat_msgInSelectedDrawable = null;
    public static Drawable chat_msgInShadowDrawable = null;
    public static Drawable chat_msgInViewsDrawable = null;
    public static Drawable chat_msgInViewsSelectedDrawable = null;
    public static Drawable chat_msgMediaBroadcastDrawable = null;
    public static Drawable chat_msgMediaCheckDrawable = null;
    public static Drawable chat_msgMediaClockDrawable = null;
    public static Drawable chat_msgMediaHalfCheckDrawable = null;
    public static Drawable chat_msgMediaMenuDrawable = null;
    public static Drawable chat_msgMediaViewsDrawable = null;
    public static Drawable chat_msgOutBroadcastDrawable = null;
    public static Drawable chat_msgOutCallDrawable = null;
    public static Drawable chat_msgOutCallSelectedDrawable = null;
    public static Drawable chat_msgOutCheckDrawable = null;
    public static Drawable chat_msgOutCheckSelectedDrawable = null;
    public static Drawable chat_msgOutClockDrawable = null;
    public static Drawable chat_msgOutDrawable = null;
    public static Drawable chat_msgOutHalfCheckDrawable = null;
    public static Drawable chat_msgOutHalfCheckSelectedDrawable = null;
    public static Drawable chat_msgOutInstantDrawable = null;
    public static Drawable chat_msgOutLocationDrawable = null;
    public static Drawable chat_msgOutMediaDrawable = null;
    public static Drawable chat_msgOutMediaSelectedDrawable = null;
    public static Drawable chat_msgOutMediaShadowDrawable = null;
    public static Drawable chat_msgOutMenuDrawable = null;
    public static Drawable chat_msgOutMenuSelectedDrawable = null;
    public static Drawable chat_msgOutSelectedClockDrawable = null;
    public static Drawable chat_msgOutSelectedDrawable = null;
    public static Drawable chat_msgOutShadowDrawable = null;
    public static Drawable chat_msgOutViewsDrawable = null;
    public static Drawable chat_msgOutViewsSelectedDrawable = null;
    public static Drawable chat_msgStickerCheckDrawable = null;
    public static Drawable chat_msgStickerClockDrawable = null;
    public static Drawable chat_msgStickerHalfCheckDrawable = null;
    public static Drawable chat_msgStickerViewsDrawable = null;
    public static TextPaint chat_msgTextPaint = null;
    public static TextPaint chat_msgTextPaintOneEmoji = null;
    public static TextPaint chat_msgTextPaintThreeEmoji = null;
    public static TextPaint chat_msgTextPaintTwoEmoji = null;
    public static Drawable chat_muteIconDrawable = null;
    public static TextPaint chat_namePaint = null;
    public static Drawable[][] chat_photoStatesDrawables = null;
    public static Paint chat_radialProgress2Paint = null;
    public static Paint chat_radialProgressPaint = null;
    public static Drawable chat_redLocationIcon = null;
    public static Drawable chat_replyIconDrawable = null;
    public static Paint chat_replyLinePaint = null;
    public static TextPaint chat_replyNamePaint = null;
    public static TextPaint chat_replyTextPaint = null;
    public static Drawable chat_roundVideoShadow = null;
    public static Drawable chat_shareDrawable = null;
    public static Drawable chat_shareIconDrawable = null;
    public static TextPaint chat_shipmentPaint = null;
    public static Paint chat_statusPaint = null;
    public static Paint chat_statusRecordPaint = null;
    public static Drawable chat_systemDrawable = null;
    public static Paint chat_textSearchSelectionPaint = null;
    public static Paint chat_timeBackgroundPaint = null;
    public static TextPaint chat_timePaint = null;
    public static Paint chat_urlPaint = null;
    public static Paint checkboxSquare_backgroundPaint = null;
    public static Paint checkboxSquare_checkPaint = null;
    public static Paint checkboxSquare_eraserPaint = null;
    public static PorterDuffColorFilter colorFilter = null;
    public static PorterDuffColorFilter colorPressedFilter = null;
    private static int currentColor = 0;
    private static HashMap currentColors = null;
    private static ThemeInfo currentDayTheme = null;
    private static ThemeInfo currentNightTheme = null;
    private static int currentSelectedColor = 0;
    private static ThemeInfo currentTheme = null;
    private static HashMap defaultColors = null;
    private static ThemeInfo defaultTheme = null;
    public static Drawable dialogs_botDrawable = null;
    public static Drawable dialogs_broadcastDrawable = null;
    public static Drawable dialogs_checkDrawable = null;
    public static Drawable dialogs_clockDrawable = null;
    public static Paint dialogs_countGrayPaint = null;
    public static Paint dialogs_countPaint = null;
    public static TextPaint dialogs_countTextPaint = null;
    public static Drawable dialogs_errorDrawable = null;
    public static Paint dialogs_errorPaint = null;
    public static Drawable dialogs_groupDrawable = null;
    public static Drawable dialogs_halfCheckDrawable = null;
    private static Drawable dialogs_holidayDrawable = null;
    private static int dialogs_holidayDrawableOffsetX = 0;
    private static int dialogs_holidayDrawableOffsetY = 0;
    public static Drawable dialogs_lockDrawable = null;
    public static Drawable dialogs_mentionDrawable = null;
    public static TextPaint dialogs_messagePaint = null;
    public static TextPaint dialogs_messagePrintingPaint = null;
    public static Drawable dialogs_muteDrawable = null;
    public static TextPaint dialogs_nameEncryptedPaint = null;
    public static TextPaint dialogs_namePaint = null;
    public static TextPaint dialogs_offlinePaint = null;
    public static TextPaint dialogs_onlinePaint = null;
    public static Drawable dialogs_pinnedDrawable = null;
    public static Paint dialogs_pinnedPaint = null;
    public static Paint dialogs_tabletSeletedPaint = null;
    public static TextPaint dialogs_timePaint = null;
    public static Drawable dialogs_verifiedCheckDrawable = null;
    public static Drawable dialogs_verifiedDrawable = null;
    public static Paint dividerPaint = null;
    private static HashMap fallbackKeys = null;
    public static Drawable favoriteDrawable = null;
    public static Drawable favoriteOffIconDrawable = null;
    public static Drawable favoriteOnIconDrawable = null;
    private static boolean isCustomTheme = false;
    public static final String key_actionBarActionModeDefault = "actionBarActionModeDefault";
    public static final String key_actionBarActionModeDefaultIcon = "actionBarActionModeDefaultIcon";
    public static final String key_actionBarActionModeDefaultSelector = "actionBarActionModeDefaultSelector";
    public static final String key_actionBarActionModeDefaultTop = "actionBarActionModeDefaultTop";
    public static final String key_actionBarDefault = "actionBarDefault";
    public static final String key_actionBarDefaultIcon = "actionBarDefaultIcon";
    public static final String key_actionBarDefaultSearch = "actionBarDefaultSearch";
    public static final String key_actionBarDefaultSearchPlaceholder = "actionBarDefaultSearchPlaceholder";
    public static final String key_actionBarDefaultSelector = "actionBarDefaultSelector";
    public static final String key_actionBarDefaultSubmenuBackground = "actionBarDefaultSubmenuBackground";
    public static final String key_actionBarDefaultSubmenuItem = "actionBarDefaultSubmenuItem";
    public static final String key_actionBarDefaultSubtitle = "actionBarDefaultSubtitle";
    public static final String key_actionBarDefaultTitle = "actionBarDefaultTitle";
    public static final String key_actionBarWhiteSelector = "actionBarWhiteSelector";
    public static final String key_avatar_actionBarIconBlue = "avatar_actionBarIconBlue";
    public static final String key_avatar_actionBarIconCyan = "avatar_actionBarIconCyan";
    public static final String key_avatar_actionBarIconGreen = "avatar_actionBarIconGreen";
    public static final String key_avatar_actionBarIconOrange = "avatar_actionBarIconOrange";
    public static final String key_avatar_actionBarIconPink = "avatar_actionBarIconPink";
    public static final String key_avatar_actionBarIconRed = "avatar_actionBarIconRed";
    public static final String key_avatar_actionBarIconViolet = "avatar_actionBarIconViolet";
    public static final String key_avatar_actionBarSelectorBlue = "avatar_actionBarSelectorBlue";
    public static final String key_avatar_actionBarSelectorCyan = "avatar_actionBarSelectorCyan";
    public static final String key_avatar_actionBarSelectorGreen = "avatar_actionBarSelectorGreen";
    public static final String key_avatar_actionBarSelectorOrange = "avatar_actionBarSelectorOrange";
    public static final String key_avatar_actionBarSelectorPink = "avatar_actionBarSelectorPink";
    public static final String key_avatar_actionBarSelectorRed = "avatar_actionBarSelectorRed";
    public static final String key_avatar_actionBarSelectorViolet = "avatar_actionBarSelectorViolet";
    public static final String key_avatar_backgroundActionBarBlue = "avatar_backgroundActionBarBlue";
    public static final String key_avatar_backgroundActionBarCyan = "avatar_backgroundActionBarCyan";
    public static final String key_avatar_backgroundActionBarGreen = "avatar_backgroundActionBarGreen";
    public static final String key_avatar_backgroundActionBarOrange = "avatar_backgroundActionBarOrange";
    public static final String key_avatar_backgroundActionBarPink = "avatar_backgroundActionBarPink";
    public static final String key_avatar_backgroundActionBarRed = "avatar_backgroundActionBarRed";
    public static final String key_avatar_backgroundActionBarViolet = "avatar_backgroundActionBarViolet";
    public static final String key_avatar_backgroundBlue = "avatar_backgroundBlue";
    public static final String key_avatar_backgroundCyan = "avatar_backgroundCyan";
    public static final String key_avatar_backgroundGreen = "avatar_backgroundGreen";
    public static final String key_avatar_backgroundGroupCreateSpanBlue = "avatar_backgroundGroupCreateSpanBlue";
    public static final String key_avatar_backgroundInProfileBlue = "avatar_backgroundInProfileBlue";
    public static final String key_avatar_backgroundInProfileCyan = "avatar_backgroundInProfileCyan";
    public static final String key_avatar_backgroundInProfileGreen = "avatar_backgroundInProfileGreen";
    public static final String key_avatar_backgroundInProfileOrange = "avatar_backgroundInProfileOrange";
    public static final String key_avatar_backgroundInProfilePink = "avatar_backgroundInProfilePink";
    public static final String key_avatar_backgroundInProfileRed = "avatar_backgroundInProfileRed";
    public static final String key_avatar_backgroundInProfileViolet = "avatar_backgroundInProfileViolet";
    public static final String key_avatar_backgroundOrange = "avatar_backgroundOrange";
    public static final String key_avatar_backgroundPink = "avatar_backgroundPink";
    public static final String key_avatar_backgroundRed = "avatar_backgroundRed";
    public static final String key_avatar_backgroundSaved = "avatar_backgroundSaved";
    public static final String key_avatar_backgroundViolet = "avatar_backgroundViolet";
    public static final String key_avatar_nameInMessageBlue = "avatar_nameInMessageBlue";
    public static final String key_avatar_nameInMessageCyan = "avatar_nameInMessageCyan";
    public static final String key_avatar_nameInMessageGreen = "avatar_nameInMessageGreen";
    public static final String key_avatar_nameInMessageOrange = "avatar_nameInMessageOrange";
    public static final String key_avatar_nameInMessagePink = "avatar_nameInMessagePink";
    public static final String key_avatar_nameInMessageRed = "avatar_nameInMessageRed";
    public static final String key_avatar_nameInMessageViolet = "avatar_nameInMessageViolet";
    public static final String key_avatar_subtitleInProfileBlue = "avatar_subtitleInProfileBlue";
    public static final String key_avatar_subtitleInProfileCyan = "avatar_subtitleInProfileCyan";
    public static final String key_avatar_subtitleInProfileGreen = "avatar_subtitleInProfileGreen";
    public static final String key_avatar_subtitleInProfileOrange = "avatar_subtitleInProfileOrange";
    public static final String key_avatar_subtitleInProfilePink = "avatar_subtitleInProfilePink";
    public static final String key_avatar_subtitleInProfileRed = "avatar_subtitleInProfileRed";
    public static final String key_avatar_subtitleInProfileViolet = "avatar_subtitleInProfileViolet";
    public static final String key_avatar_text = "avatar_text";
    public static final String key_calls_callReceivedGreenIcon = "calls_callReceivedGreenIcon";
    public static final String key_calls_callReceivedRedIcon = "calls_callReceivedRedIcon";
    public static final String key_calls_ratingStar = "calls_ratingStar";
    public static final String key_calls_ratingStarSelected = "calls_ratingStarSelected";
    public static final String key_changephoneinfo_changeText = "key_changephoneinfo_changeText";
    public static final String key_changephoneinfo_image = "changephoneinfo_image";
    public static final String key_chat_addContact = "chat_addContact";
    public static final String key_chat_adminSelectedText = "chat_adminSelectedText";
    public static final String key_chat_adminText = "chat_adminText";
    public static final String key_chat_attachAudioBackground = "chat_attachAudioBackground";
    public static final String key_chat_attachAudioIcon = "chat_attachAudioIcon";
    public static final String key_chat_attachCameraIcon1 = "chat_attachCameraIcon1";
    public static final String key_chat_attachCameraIcon2 = "chat_attachCameraIcon2";
    public static final String key_chat_attachCameraIcon3 = "chat_attachCameraIcon3";
    public static final String key_chat_attachCameraIcon4 = "chat_attachCameraIcon4";
    public static final String key_chat_attachCameraIcon5 = "chat_attachCameraIcon5";
    public static final String key_chat_attachCameraIcon6 = "chat_attachCameraIcon6";
    public static final String key_chat_attachContactBackground = "chat_attachContactBackground";
    public static final String key_chat_attachContactIcon = "chat_attachContactIcon";
    public static final String key_chat_attachFileBackground = "chat_attachFileBackground";
    public static final String key_chat_attachFileIcon = "chat_attachFileIcon";
    public static final String key_chat_attachGalleryBackground = "chat_attachGalleryBackground";
    public static final String key_chat_attachGalleryIcon = "chat_attachGalleryIcon";
    public static final String key_chat_attachHideBackground = "chat_attachHideBackground";
    public static final String key_chat_attachHideIcon = "chat_attachHideIcon";
    public static final String key_chat_attachLocationBackground = "chat_attachLocationBackground";
    public static final String key_chat_attachLocationIcon = "chat_attachLocationIcon";
    public static final String key_chat_attachSendBackground = "chat_attachSendBackground";
    public static final String key_chat_attachSendIcon = "chat_attachSendIcon";
    public static final String key_chat_attachVideoBackground = "chat_attachVideoBackground";
    public static final String key_chat_attachVideoIcon = "chat_attachVideoIcon";
    public static final String key_chat_botButtonText = "chat_botButtonText";
    public static final String key_chat_botKeyboardButtonBackground = "chat_botKeyboardButtonBackground";
    public static final String key_chat_botKeyboardButtonBackgroundPressed = "chat_botKeyboardButtonBackgroundPressed";
    public static final String key_chat_botKeyboardButtonText = "chat_botKeyboardButtonText";
    public static final String key_chat_botProgress = "chat_botProgress";
    public static final String key_chat_botSwitchToInlineText = "chat_botSwitchToInlineText";
    public static final String key_chat_editDoneIcon = "chat_editDoneIcon";
    public static final String key_chat_emojiPanelBackground = "chat_emojiPanelBackground";
    public static final String key_chat_emojiPanelBackspace = "chat_emojiPanelBackspace";
    public static final String key_chat_emojiPanelBadgeBackground = "chat_emojiPanelBadgeBackground";
    public static final String key_chat_emojiPanelBadgeText = "chat_emojiPanelBadgeText";
    public static final String key_chat_emojiPanelEmptyText = "chat_emojiPanelEmptyText";
    public static final String key_chat_emojiPanelIcon = "chat_emojiPanelIcon";
    public static final String key_chat_emojiPanelIconSelected = "chat_emojiPanelIconSelected";
    public static final String key_chat_emojiPanelIconSelector = "chat_emojiPanelIconSelector";
    public static final String key_chat_emojiPanelMasksIcon = "chat_emojiPanelMasksIcon";
    public static final String key_chat_emojiPanelMasksIconSelected = "chat_emojiPanelMasksIconSelected";
    public static final String key_chat_emojiPanelNewTrending = "chat_emojiPanelNewTrending";
    public static final String key_chat_emojiPanelShadowLine = "chat_emojiPanelShadowLine";
    public static final String key_chat_emojiPanelStickerPackSelector = "chat_emojiPanelStickerPackSelector";
    public static final String key_chat_emojiPanelStickerSetName = "chat_emojiPanelStickerSetName";
    public static final String key_chat_emojiPanelStickerSetNameIcon = "chat_emojiPanelStickerSetNameIcon";
    public static final String key_chat_emojiPanelTrendingDescription = "chat_emojiPanelTrendingDescription";
    public static final String key_chat_emojiPanelTrendingTitle = "chat_emojiPanelTrendingTitle";
    public static final String key_chat_emojiSearchBackground = "chat_emojiSearchBackground";
    public static final String key_chat_fieldOverlayText = "chat_fieldOverlayText";
    public static final String key_chat_gifSaveHintBackground = "chat_gifSaveHintBackground";
    public static final String key_chat_gifSaveHintText = "chat_gifSaveHintText";
    public static final String key_chat_goDownButton = "chat_goDownButton";
    public static final String key_chat_goDownButtonCounter = "chat_goDownButtonCounter";
    public static final String key_chat_goDownButtonCounterBackground = "chat_goDownButtonCounterBackground";
    public static final String key_chat_goDownButtonIcon = "chat_goDownButtonIcon";
    public static final String key_chat_goDownButtonShadow = "chat_goDownButtonShadow";
    public static final String key_chat_inAudioCacheSeekbar = "chat_inAudioCacheSeekbar";
    public static final String key_chat_inAudioDurationSelectedText = "chat_inAudioDurationSelectedText";
    public static final String key_chat_inAudioDurationText = "chat_inAudioDurationText";
    public static final String key_chat_inAudioPerfomerSelectedText = "chat_inAudioPerfomerSelectedText";
    public static final String key_chat_inAudioPerfomerText = "chat_inAudioPerfomerText";
    public static final String key_chat_inAudioProgress = "chat_inAudioProgress";
    public static final String key_chat_inAudioSeekbar = "chat_inAudioSeekbar";
    public static final String key_chat_inAudioSeekbarFill = "chat_inAudioSeekbarFill";
    public static final String key_chat_inAudioSeekbarSelected = "chat_inAudioSeekbarSelected";
    public static final String key_chat_inAudioSelectedProgress = "chat_inAudioSelectedProgress";
    public static final String key_chat_inAudioTitleText = "chat_inAudioTitleText";
    public static final String key_chat_inBubble = "chat_inBubble";
    public static final String key_chat_inBubbleSelected = "chat_inBubbleSelected";
    public static final String key_chat_inBubbleShadow = "chat_inBubbleShadow";
    public static final String key_chat_inContactBackground = "chat_inContactBackground";
    public static final String key_chat_inContactIcon = "chat_inContactIcon";
    public static final String key_chat_inContactNameText = "chat_inContactNameText";
    public static final String key_chat_inContactPhoneSelectedText = "chat_inContactPhoneSelectedText";
    public static final String key_chat_inContactPhoneText = "chat_inContactPhoneText";
    public static final String key_chat_inFileBackground = "chat_inFileBackground";
    public static final String key_chat_inFileBackgroundSelected = "chat_inFileBackgroundSelected";
    public static final String key_chat_inFileIcon = "chat_inFileIcon";
    public static final String key_chat_inFileInfoSelectedText = "chat_inFileInfoSelectedText";
    public static final String key_chat_inFileInfoText = "chat_inFileInfoText";
    public static final String key_chat_inFileNameText = "chat_inFileNameText";
    public static final String key_chat_inFileProgress = "chat_inFileProgress";
    public static final String key_chat_inFileProgressSelected = "chat_inFileProgressSelected";
    public static final String key_chat_inFileSelectedIcon = "chat_inFileSelectedIcon";
    public static final String key_chat_inForwardedNameText = "chat_inForwardedNameText";
    public static final String key_chat_inInstant = "chat_inInstant";
    public static final String key_chat_inInstantSelected = "chat_inInstantSelected";
    public static final String key_chat_inLoader = "chat_inLoader";
    public static final String key_chat_inLoaderPhoto = "chat_inLoaderPhoto";
    public static final String key_chat_inLoaderPhotoIcon = "chat_inLoaderPhotoIcon";
    public static final String key_chat_inLoaderPhotoIconSelected = "chat_inLoaderPhotoIconSelected";
    public static final String key_chat_inLoaderPhotoSelected = "chat_inLoaderPhotoSelected";
    public static final String key_chat_inLoaderSelected = "chat_inLoaderSelected";
    public static final String key_chat_inLocationBackground = "chat_inLocationBackground";
    public static final String key_chat_inLocationIcon = "chat_inLocationIcon";
    public static final String key_chat_inMediaIcon = "chat_inMediaIcon";
    public static final String key_chat_inMediaIconSelected = "chat_inMediaIconSelected";
    public static final String key_chat_inMenu = "chat_inMenu";
    public static final String key_chat_inMenuSelected = "chat_inMenuSelected";
    public static final String key_chat_inPreviewInstantSelectedText = "chat_inPreviewInstantSelectedText";
    public static final String key_chat_inPreviewInstantText = "chat_inPreviewInstantText";
    public static final String key_chat_inPreviewLine = "chat_inPreviewLine";
    public static final String key_chat_inReplyLine = "chat_inReplyLine";
    public static final String key_chat_inReplyMediaMessageSelectedText = "chat_inReplyMediaMessageSelectedText";
    public static final String key_chat_inReplyMediaMessageText = "chat_inReplyMediaMessageText";
    public static final String key_chat_inReplyMessageText = "chat_inReplyMessageText";
    public static final String key_chat_inReplyNameText = "chat_inReplyNameText";
    public static final String key_chat_inSentClock = "chat_inSentClock";
    public static final String key_chat_inSentClockSelected = "chat_inSentClockSelected";
    public static final String key_chat_inSiteNameText = "chat_inSiteNameText";
    public static final String key_chat_inTimeSelectedText = "chat_inTimeSelectedText";
    public static final String key_chat_inTimeText = "chat_inTimeText";
    public static final String key_chat_inVenueInfoSelectedText = "chat_inVenueInfoSelectedText";
    public static final String key_chat_inVenueInfoText = "chat_inVenueInfoText";
    public static final String key_chat_inViaBotNameText = "chat_inViaBotNameText";
    public static final String key_chat_inViews = "chat_inViews";
    public static final String key_chat_inViewsSelected = "chat_inViewsSelected";
    public static final String key_chat_inVoiceSeekbar = "chat_inVoiceSeekbar";
    public static final String key_chat_inVoiceSeekbarFill = "chat_inVoiceSeekbarFill";
    public static final String key_chat_inVoiceSeekbarSelected = "chat_inVoiceSeekbarSelected";
    public static final String key_chat_inlineResultIcon = "chat_inlineResultIcon";
    public static final String key_chat_linkSelectBackground = "chat_linkSelectBackground";
    public static final String key_chat_lockIcon = "chat_lockIcon";
    public static final String key_chat_mediaBroadcast = "chat_mediaBroadcast";
    public static final String key_chat_mediaInfoText = "chat_mediaInfoText";
    public static final String key_chat_mediaLoaderPhoto = "chat_mediaLoaderPhoto";
    public static final String key_chat_mediaLoaderPhotoIcon = "chat_mediaLoaderPhotoIcon";
    public static final String key_chat_mediaLoaderPhotoIconSelected = "chat_mediaLoaderPhotoIconSelected";
    public static final String key_chat_mediaLoaderPhotoSelected = "chat_mediaLoaderPhotoSelected";
    public static final String key_chat_mediaMenu = "chat_mediaMenu";
    public static final String key_chat_mediaProgress = "chat_mediaProgress";
    public static final String key_chat_mediaSentCheck = "chat_mediaSentCheck";
    public static final String key_chat_mediaSentClock = "chat_mediaSentClock";
    public static final String key_chat_mediaTimeBackground = "chat_mediaTimeBackground";
    public static final String key_chat_mediaTimeText = "chat_mediaTimeText";
    public static final String key_chat_mediaViews = "chat_mediaViews";
    public static final String key_chat_messageLinkIn = "chat_messageLinkIn";
    public static final String key_chat_messageLinkOut = "chat_messageLinkOut";
    public static final String key_chat_messagePanelBackground = "chat_messagePanelBackground";
    public static final String key_chat_messagePanelCancelInlineBot = "chat_messagePanelCancelInlineBot";
    public static final String key_chat_messagePanelHint = "chat_messagePanelHint";
    public static final String key_chat_messagePanelIcons = "chat_messagePanelIcons";
    public static final String key_chat_messagePanelSend = "chat_messagePanelSend";
    public static final String key_chat_messagePanelShadow = "chat_messagePanelShadow";
    public static final String key_chat_messagePanelText = "chat_messagePanelText";
    public static final String key_chat_messagePanelVoiceBackground = "chat_messagePanelVoiceBackground";
    public static final String key_chat_messagePanelVoiceDelete = "chat_messagePanelVoiceDelete";
    public static final String key_chat_messagePanelVoiceDuration = "chat_messagePanelVoiceDuration";
    public static final String key_chat_messagePanelVoiceLock = "key_chat_messagePanelVoiceLock";
    public static final String key_chat_messagePanelVoiceLockBackground = "key_chat_messagePanelVoiceLockBackground";
    public static final String key_chat_messagePanelVoiceLockShadow = "key_chat_messagePanelVoiceLockShadow";
    public static final String key_chat_messagePanelVoicePressed = "chat_messagePanelVoicePressed";
    public static final String key_chat_messagePanelVoiceShadow = "chat_messagePanelVoiceShadow";
    public static final String key_chat_messageTextIn = "chat_messageTextIn";
    public static final String key_chat_messageTextOut = "chat_messageTextOut";
    public static final String key_chat_muteIcon = "chat_muteIcon";
    public static final String key_chat_outAudioCacheSeekbar = "chat_outAudioCacheSeekbar";
    public static final String key_chat_outAudioDurationSelectedText = "chat_outAudioDurationSelectedText";
    public static final String key_chat_outAudioDurationText = "chat_outAudioDurationText";
    public static final String key_chat_outAudioPerfomerSelectedText = "chat_outAudioPerfomerSelectedText";
    public static final String key_chat_outAudioPerfomerText = "chat_outAudioPerfomerText";
    public static final String key_chat_outAudioProgress = "chat_outAudioProgress";
    public static final String key_chat_outAudioSeekbar = "chat_outAudioSeekbar";
    public static final String key_chat_outAudioSeekbarFill = "chat_outAudioSeekbarFill";
    public static final String key_chat_outAudioSeekbarSelected = "chat_outAudioSeekbarSelected";
    public static final String key_chat_outAudioSelectedProgress = "chat_outAudioSelectedProgress";
    public static final String key_chat_outAudioTitleText = "chat_outAudioTitleText";
    public static final String key_chat_outBroadcast = "chat_outBroadcast";
    public static final String key_chat_outBubble = "chat_outBubble";
    public static final String key_chat_outBubbleSelected = "chat_outBubbleSelected";
    public static final String key_chat_outBubbleShadow = "chat_outBubbleShadow";
    public static final String key_chat_outContactBackground = "chat_outContactBackground";
    public static final String key_chat_outContactIcon = "chat_outContactIcon";
    public static final String key_chat_outContactNameText = "chat_outContactNameText";
    public static final String key_chat_outContactPhoneSelectedText = "chat_outContactPhoneText";
    public static final String key_chat_outContactPhoneText = "chat_outContactPhoneText";
    public static final String key_chat_outFileBackground = "chat_outFileBackground";
    public static final String key_chat_outFileBackgroundSelected = "chat_outFileBackgroundSelected";
    public static final String key_chat_outFileIcon = "chat_outFileIcon";
    public static final String key_chat_outFileInfoSelectedText = "chat_outFileInfoSelectedText";
    public static final String key_chat_outFileInfoText = "chat_outFileInfoText";
    public static final String key_chat_outFileNameText = "chat_outFileNameText";
    public static final String key_chat_outFileProgress = "chat_outFileProgress";
    public static final String key_chat_outFileProgressSelected = "chat_outFileProgressSelected";
    public static final String key_chat_outFileSelectedIcon = "chat_outFileSelectedIcon";
    public static final String key_chat_outForwardedNameText = "chat_outForwardedNameText";
    public static final String key_chat_outInstant = "chat_outInstant";
    public static final String key_chat_outInstantSelected = "chat_outInstantSelected";
    public static final String key_chat_outLoader = "chat_outLoader";
    public static final String key_chat_outLoaderPhoto = "chat_outLoaderPhoto";
    public static final String key_chat_outLoaderPhotoIcon = "chat_outLoaderPhotoIcon";
    public static final String key_chat_outLoaderPhotoIconSelected = "chat_outLoaderPhotoIconSelected";
    public static final String key_chat_outLoaderPhotoSelected = "chat_outLoaderPhotoSelected";
    public static final String key_chat_outLoaderSelected = "chat_outLoaderSelected";
    public static final String key_chat_outLocationBackground = "chat_outLocationBackground";
    public static final String key_chat_outLocationIcon = "chat_outLocationIcon";
    public static final String key_chat_outMediaIcon = "chat_outMediaIcon";
    public static final String key_chat_outMediaIconSelected = "chat_outMediaIconSelected";
    public static final String key_chat_outMenu = "chat_outMenu";
    public static final String key_chat_outMenuSelected = "chat_outMenuSelected";
    public static final String key_chat_outPreviewInstantSelectedText = "chat_outPreviewInstantSelectedText";
    public static final String key_chat_outPreviewInstantText = "chat_outPreviewInstantText";
    public static final String key_chat_outPreviewLine = "chat_outPreviewLine";
    public static final String key_chat_outReplyLine = "chat_outReplyLine";
    public static final String key_chat_outReplyMediaMessageSelectedText = "chat_outReplyMediaMessageSelectedText";
    public static final String key_chat_outReplyMediaMessageText = "chat_outReplyMediaMessageText";
    public static final String key_chat_outReplyMessageText = "chat_outReplyMessageText";
    public static final String key_chat_outReplyNameText = "chat_outReplyNameText";
    public static final String key_chat_outSentCheck = "chat_outSentCheck";
    public static final String key_chat_outSentCheckSelected = "chat_outSentCheckSelected";
    public static final String key_chat_outSentClock = "chat_outSentClock";
    public static final String key_chat_outSentClockSelected = "chat_outSentClockSelected";
    public static final String key_chat_outSiteNameText = "chat_outSiteNameText";
    public static final String key_chat_outTimeSelectedText = "chat_outTimeSelectedText";
    public static final String key_chat_outTimeText = "chat_outTimeText";
    public static final String key_chat_outVenueInfoSelectedText = "chat_outVenueInfoSelectedText";
    public static final String key_chat_outVenueInfoText = "chat_outVenueInfoText";
    public static final String key_chat_outViaBotNameText = "chat_outViaBotNameText";
    public static final String key_chat_outViews = "chat_outViews";
    public static final String key_chat_outViewsSelected = "chat_outViewsSelected";
    public static final String key_chat_outVoiceSeekbar = "chat_outVoiceSeekbar";
    public static final String key_chat_outVoiceSeekbarFill = "chat_outVoiceSeekbarFill";
    public static final String key_chat_outVoiceSeekbarSelected = "chat_outVoiceSeekbarSelected";
    public static final String key_chat_previewDurationText = "chat_previewDurationText";
    public static final String key_chat_previewGameText = "chat_previewGameText";
    public static final String key_chat_recordTime = "chat_recordTime";
    public static final String key_chat_recordVoiceCancel = "chat_recordVoiceCancel";
    public static final String key_chat_recordedVoiceBackground = "chat_recordedVoiceBackground";
    public static final String key_chat_recordedVoiceDot = "chat_recordedVoiceDot";
    public static final String key_chat_recordedVoicePlayPause = "chat_recordedVoicePlayPause";
    public static final String key_chat_recordedVoicePlayPausePressed = "chat_recordedVoicePlayPausePressed";
    public static final String key_chat_recordedVoiceProgress = "chat_recordedVoiceProgress";
    public static final String key_chat_recordedVoiceProgressInner = "chat_recordedVoiceProgressInner";
    public static final String key_chat_replyPanelClose = "chat_replyPanelClose";
    public static final String key_chat_replyPanelIcons = "chat_replyPanelIcons";
    public static final String key_chat_replyPanelLine = "chat_replyPanelLine";
    public static final String key_chat_replyPanelMessage = "chat_replyPanelMessage";
    public static final String key_chat_replyPanelName = "chat_replyPanelName";
    public static final String key_chat_reportSpam = "chat_reportSpam";
    public static final String key_chat_searchPanelIcons = "chat_searchPanelIcons";
    public static final String key_chat_searchPanelText = "chat_searchPanelText";
    public static final String key_chat_secretChatStatusText = "chat_secretChatStatusText";
    public static final String key_chat_secretTimeText = "chat_secretTimeText";
    public static final String key_chat_secretTimerBackground = "chat_secretTimerBackground";
    public static final String key_chat_secretTimerText = "chat_secretTimerText";
    public static final String key_chat_selectedBackground = "chat_selectedBackground";
    public static final String key_chat_sentError = "chat_sentError";
    public static final String key_chat_sentErrorIcon = "chat_sentErrorIcon";
    public static final String key_chat_serviceBackground = "chat_serviceBackground";
    public static final String key_chat_serviceBackgroundSelected = "chat_serviceBackgroundSelected";
    public static final String key_chat_serviceIcon = "chat_serviceIcon";
    public static final String key_chat_serviceLink = "chat_serviceLink";
    public static final String key_chat_serviceText = "chat_serviceText";
    public static final String key_chat_stickerNameText = "chat_stickerNameText";
    public static final String key_chat_stickerReplyLine = "chat_stickerReplyLine";
    public static final String key_chat_stickerReplyMessageText = "chat_stickerReplyMessageText";
    public static final String key_chat_stickerReplyNameText = "chat_stickerReplyNameText";
    public static final String key_chat_stickerViaBotNameText = "chat_stickerViaBotNameText";
    public static final String key_chat_stickersHintPanel = "chat_stickersHintPanel";
    public static final String key_chat_textSelectBackground = "chat_textSelectBackground";
    public static final String key_chat_topPanelBackground = "chat_topPanelBackground";
    public static final String key_chat_topPanelClose = "chat_topPanelClose";
    public static final String key_chat_topPanelLine = "chat_topPanelLine";
    public static final String key_chat_topPanelMessage = "chat_topPanelMessage";
    public static final String key_chat_topPanelTitle = "chat_topPanelTitle";
    public static final String key_chat_unreadMessagesStartArrowIcon = "chat_unreadMessagesStartArrowIcon";
    public static final String key_chat_unreadMessagesStartBackground = "chat_unreadMessagesStartBackground";
    public static final String key_chat_unreadMessagesStartText = "chat_unreadMessagesStartText";
    public static final String key_chat_wallpaper = "chat_wallpaper";
    public static final String key_chats_actionBackground = "chats_actionBackground";
    public static final String key_chats_actionIcon = "chats_actionIcon";
    public static final String key_chats_actionMessage = "chats_actionMessage";
    public static final String key_chats_actionPressedBackground = "chats_actionPressedBackground";
    public static final String key_chats_actionUnreadBackground = "chats_actionUnreadBackground";
    public static final String key_chats_actionUnreadIcon = "chats_actionUnreadIcon";
    public static final String key_chats_actionUnreadPressedBackground = "chats_actionUnreadPressedBackground";
    public static final String key_chats_attachMessage = "chats_attachMessage";
    public static final String key_chats_date = "chats_date";
    public static final String key_chats_draft = "chats_draft";
    public static final String key_chats_mentionIcon = "chats_mentionIcon";
    public static final String key_chats_menuBackground = "chats_menuBackground";
    public static final String key_chats_menuCloud = "chats_menuCloud";
    public static final String key_chats_menuCloudBackgroundCats = "chats_menuCloudBackgroundCats";
    public static final String key_chats_menuItemCheck = "chats_menuItemCheck";
    public static final String key_chats_menuItemIcon = "chats_menuItemIcon";
    public static final String key_chats_menuItemText = "chats_menuItemText";
    public static final String key_chats_menuName = "chats_menuName";
    public static final String key_chats_menuPhone = "chats_menuPhone";
    public static final String key_chats_menuPhoneCats = "chats_menuPhoneCats";
    public static final String key_chats_menuTopShadow = "chats_menuTopShadow";
    public static final String key_chats_message = "chats_message";
    public static final String key_chats_muteIcon = "chats_muteIcon";
    public static final String key_chats_name = "chats_name";
    public static final String key_chats_nameIcon = "chats_nameIcon";
    public static final String key_chats_nameMessage = "chats_nameMessage";
    public static final String key_chats_pinnedIcon = "chats_pinnedIcon";
    public static final String key_chats_pinnedOverlay = "chats_pinnedOverlay";
    public static final String key_chats_secretIcon = "chats_secretIcon";
    public static final String key_chats_secretName = "chats_secretName";
    public static final String key_chats_sentCheck = "chats_sentCheck";
    public static final String key_chats_sentClock = "chats_sentClock";
    public static final String key_chats_sentError = "chats_sentError";
    public static final String key_chats_sentErrorIcon = "chats_sentErrorIcon";
    public static final String key_chats_tabletSelectedOverlay = "chats_tabletSelectedOverlay";
    public static final String key_chats_unreadCounter = "chats_unreadCounter";
    public static final String key_chats_unreadCounterMuted = "chats_unreadCounterMuted";
    public static final String key_chats_unreadCounterText = "chats_unreadCounterText";
    public static final String key_chats_verifiedBackground = "chats_verifiedBackground";
    public static final String key_chats_verifiedCheck = "chats_verifiedCheck";
    public static final String key_checkbox = "checkbox";
    public static final String key_checkboxCheck = "checkboxCheck";
    public static final String key_checkboxSquareBackground = "checkboxSquareBackground";
    public static final String key_checkboxSquareCheck = "checkboxSquareCheck";
    public static final String key_checkboxSquareDisabled = "checkboxSquareDisabled";
    public static final String key_checkboxSquareUnchecked = "checkboxSquareUnchecked";
    public static final String key_contacts_inviteBackground = "contacts_inviteBackground";
    public static final String key_contacts_inviteText = "contacts_inviteText";
    public static final String key_contextProgressInner1 = "contextProgressInner1";
    public static final String key_contextProgressInner2 = "contextProgressInner2";
    public static final String key_contextProgressInner3 = "contextProgressInner3";
    public static final String key_contextProgressOuter1 = "contextProgressOuter1";
    public static final String key_contextProgressOuter2 = "contextProgressOuter2";
    public static final String key_contextProgressOuter3 = "contextProgressOuter3";
    public static final String key_dialogBackground = "dialogBackground";
    public static final String key_dialogBackgroundGray = "dialogBackgroundGray";
    public static final String key_dialogBadgeBackground = "dialogBadgeBackground";
    public static final String key_dialogBadgeText = "dialogBadgeText";
    public static final String key_dialogButton = "dialogButton";
    public static final String key_dialogButtonSelector = "dialogButtonSelector";
    public static final String key_dialogCameraIcon = "dialogCameraIcon";
    public static final String key_dialogCheckboxSquareBackground = "dialogCheckboxSquareBackground";
    public static final String key_dialogCheckboxSquareCheck = "dialogCheckboxSquareCheck";
    public static final String key_dialogCheckboxSquareDisabled = "dialogCheckboxSquareDisabled";
    public static final String key_dialogCheckboxSquareUnchecked = "dialogCheckboxSquareUnchecked";
    public static final String key_dialogGrayLine = "dialogGrayLine";
    public static final String key_dialogIcon = "dialogIcon";
    public static final String key_dialogInputField = "dialogInputField";
    public static final String key_dialogInputFieldActivated = "dialogInputFieldActivated";
    public static final String key_dialogLineProgress = "dialogLineProgress";
    public static final String key_dialogLineProgressBackground = "dialogLineProgressBackground";
    public static final String key_dialogLinkSelection = "dialogLinkSelection";
    public static final String key_dialogProgressCircle = "dialogProgressCircle";
    public static final String key_dialogRadioBackground = "dialogRadioBackground";
    public static final String key_dialogRadioBackgroundChecked = "dialogRadioBackgroundChecked";
    public static final String key_dialogRoundCheckBox = "dialogRoundCheckBox";
    public static final String key_dialogRoundCheckBoxCheck = "dialogRoundCheckBoxCheck";
    public static final String key_dialogScrollGlow = "dialogScrollGlow";
    public static final String key_dialogTextBlack = "dialogTextBlack";
    public static final String key_dialogTextBlue = "dialogTextBlue";
    public static final String key_dialogTextBlue2 = "dialogTextBlue2";
    public static final String key_dialogTextBlue3 = "dialogTextBlue3";
    public static final String key_dialogTextBlue4 = "dialogTextBlue4";
    public static final String key_dialogTextGray = "dialogTextGray";
    public static final String key_dialogTextGray2 = "dialogTextGray2";
    public static final String key_dialogTextGray3 = "dialogTextGray3";
    public static final String key_dialogTextGray4 = "dialogTextGray4";
    public static final String key_dialogTextHint = "dialogTextHint";
    public static final String key_dialogTextLink = "dialogTextLink";
    public static final String key_dialogTextRed = "dialogTextRed";
    public static final String key_dialogTopBackground = "dialogTopBackground";
    public static final String key_dialog_liveLocationProgress = "dialog_liveLocationProgress";
    public static final String key_divider = "divider";
    public static final String key_emptyListPlaceholder = "emptyListPlaceholder";
    public static final String key_fastScrollActive = "fastScrollActive";
    public static final String key_fastScrollInactive = "fastScrollInactive";
    public static final String key_fastScrollText = "fastScrollText";
    public static final String key_featuredStickers_addButton = "featuredStickers_addButton";
    public static final String key_featuredStickers_addButtonPressed = "featuredStickers_addButtonPressed";
    public static final String key_featuredStickers_addedIcon = "featuredStickers_addedIcon";
    public static final String key_featuredStickers_buttonProgress = "featuredStickers_buttonProgress";
    public static final String key_featuredStickers_buttonText = "featuredStickers_buttonText";
    public static final String key_featuredStickers_delButton = "featuredStickers_delButton";
    public static final String key_featuredStickers_delButtonPressed = "featuredStickers_delButtonPressed";
    public static final String key_featuredStickers_unread = "featuredStickers_unread";
    public static final String key_files_folderIcon = "files_folderIcon";
    public static final String key_files_folderIconBackground = "files_folderIconBackground";
    public static final String key_files_iconText = "files_iconText";
    public static final String key_graySection = "graySection";
    public static final String key_graySectionText = "key_graySectionText";
    public static final String key_groupcreate_checkbox = "groupcreate_checkbox";
    public static final String key_groupcreate_checkboxCheck = "groupcreate_checkboxCheck";
    public static final String key_groupcreate_cursor = "groupcreate_cursor";
    public static final String key_groupcreate_hintText = "groupcreate_hintText";
    public static final String key_groupcreate_offlineText = "groupcreate_offlineText";
    public static final String key_groupcreate_onlineText = "groupcreate_onlineText";
    public static final String key_groupcreate_sectionShadow = "groupcreate_sectionShadow";
    public static final String key_groupcreate_sectionText = "groupcreate_sectionText";
    public static final String key_groupcreate_spanBackground = "groupcreate_spanBackground";
    public static final String key_groupcreate_spanText = "groupcreate_spanText";
    public static final String key_inappPlayerBackground = "inappPlayerBackground";
    public static final String key_inappPlayerClose = "inappPlayerClose";
    public static final String key_inappPlayerPerformer = "inappPlayerPerformer";
    public static final String key_inappPlayerPlayPause = "inappPlayerPlayPause";
    public static final String key_inappPlayerTitle = "inappPlayerTitle";
    public static final String key_listSelector = "listSelectorSDK21";
    public static final String key_location_liveLocationProgress = "location_liveLocationProgress";
    public static final String key_location_markerX = "location_markerX";
    public static final String key_location_placeLocationBackground = "location_placeLocationBackground";
    public static final String key_location_sendLiveLocationBackground = "location_sendLiveLocationBackground";
    public static final String key_location_sendLiveLocationIcon = "location_sendLiveLocationIcon";
    public static final String key_location_sendLocationBackground = "location_sendLocationBackground";
    public static final String key_location_sendLocationIcon = "location_sendLocationIcon";
    public static final String key_login_progressInner = "login_progressInner";
    public static final String key_login_progressOuter = "login_progressOuter";
    public static final String key_musicPicker_buttonBackground = "musicPicker_buttonBackground";
    public static final String key_musicPicker_buttonIcon = "musicPicker_buttonIcon";
    public static final String key_musicPicker_checkbox = "musicPicker_checkbox";
    public static final String key_musicPicker_checkboxCheck = "musicPicker_checkboxCheck";
    public static final String key_passport_authorizeBackground = "passport_authorizeBackground";
    public static final String key_passport_authorizeBackgroundSelected = "passport_authorizeBackgroundSelected";
    public static final String key_passport_authorizeText = "passport_authorizeText";
    public static final String key_picker_badge = "picker_badge";
    public static final String key_picker_badgeText = "picker_badgeText";
    public static final String key_picker_disabledButton = "picker_disabledButton";
    public static final String key_picker_enabledButton = "picker_enabledButton";
    public static final String key_player_actionBar = "player_actionBar";
    public static final String key_player_actionBarItems = "player_actionBarItems";
    public static final String key_player_actionBarSelector = "player_actionBarSelector";
    public static final String key_player_actionBarSubtitle = "player_actionBarSubtitle";
    public static final String key_player_actionBarTitle = "player_actionBarTitle";
    public static final String key_player_actionBarTop = "player_actionBarTop";
    public static final String key_player_background = "player_background";
    public static final String key_player_button = "player_button";
    public static final String key_player_buttonActive = "player_buttonActive";
    public static final String key_player_placeholder = "player_placeholder";
    public static final String key_player_placeholderBackground = "player_placeholderBackground";
    public static final String key_player_progress = "player_progress";
    public static final String key_player_progressBackground = "player_progressBackground";
    public static final String key_player_progressCachedBackground = "key_player_progressCachedBackground";
    public static final String key_player_time = "player_time";
    public static final String key_profile_actionBackground = "profile_actionBackground";
    public static final String key_profile_actionIcon = "profile_actionIcon";
    public static final String key_profile_actionPressedBackground = "profile_actionPressedBackground";
    public static final String key_profile_adminIcon = "profile_adminIcon";
    public static final String key_profile_creatorIcon = "profile_creatorIcon";
    public static final String key_profile_title = "profile_title";
    public static final String key_profile_verifiedBackground = "profile_verifiedBackground";
    public static final String key_profile_verifiedCheck = "profile_verifiedCheck";
    public static final String key_progressCircle = "progressCircle";
    public static final String key_radioBackground = "radioBackground";
    public static final String key_radioBackgroundChecked = "radioBackgroundChecked";
    public static final String key_returnToCallBackground = "returnToCallBackground";
    public static final String key_returnToCallText = "returnToCallText";
    public static final String key_sessions_devicesImage = "sessions_devicesImage";
    public static final String key_sharedMedia_linkPlaceholder = "sharedMedia_linkPlaceholder";
    public static final String key_sharedMedia_linkPlaceholderText = "sharedMedia_linkPlaceholderText";
    public static final String key_sharedMedia_startStopLoadIcon = "sharedMedia_startStopLoadIcon";
    public static final String key_stickers_menu = "stickers_menu";
    public static final String key_stickers_menuSelector = "stickers_menuSelector";
    public static final String key_switch2Check = "switch2Check";
    public static final String key_switch2Thumb = "switch2Thumb";
    public static final String key_switch2ThumbChecked = "switch2ThumbChecked";
    public static final String key_switch2Track = "switch2Track";
    public static final String key_switch2TrackChecked = "switch2TrackChecked";
    public static final String key_switchThumb = "switchThumb";
    public static final String key_switchThumbChecked = "switchThumbChecked";
    public static final String key_switchTrack = "switchTrack";
    public static final String key_switchTrackChecked = "switchTrackChecked";
    public static final String key_windowBackgroundGray = "windowBackgroundGray";
    public static final String key_windowBackgroundGrayShadow = "windowBackgroundGrayShadow";
    public static final String key_windowBackgroundWhite = "windowBackgroundWhite";
    public static final String key_windowBackgroundWhiteBlackText = "windowBackgroundWhiteBlackText";
    public static final String key_windowBackgroundWhiteBlueHeader = "windowBackgroundWhiteBlueHeader";
    public static final String key_windowBackgroundWhiteBlueText = "windowBackgroundWhiteBlueText";
    public static final String key_windowBackgroundWhiteBlueText2 = "windowBackgroundWhiteBlueText2";
    public static final String key_windowBackgroundWhiteBlueText3 = "windowBackgroundWhiteBlueText3";
    public static final String key_windowBackgroundWhiteBlueText4 = "windowBackgroundWhiteBlueText4";
    public static final String key_windowBackgroundWhiteBlueText5 = "windowBackgroundWhiteBlueText5";
    public static final String key_windowBackgroundWhiteBlueText6 = "windowBackgroundWhiteBlueText6";
    public static final String key_windowBackgroundWhiteBlueText7 = "windowBackgroundWhiteBlueText7";
    public static final String key_windowBackgroundWhiteGrayIcon = "windowBackgroundWhiteGrayIcon";
    public static final String key_windowBackgroundWhiteGrayLine = "windowBackgroundWhiteGrayLine";
    public static final String key_windowBackgroundWhiteGrayText = "windowBackgroundWhiteGrayText";
    public static final String key_windowBackgroundWhiteGrayText2 = "windowBackgroundWhiteGrayText2";
    public static final String key_windowBackgroundWhiteGrayText3 = "windowBackgroundWhiteGrayText3";
    public static final String key_windowBackgroundWhiteGrayText4 = "windowBackgroundWhiteGrayText4";
    public static final String key_windowBackgroundWhiteGrayText5 = "windowBackgroundWhiteGrayText5";
    public static final String key_windowBackgroundWhiteGrayText6 = "windowBackgroundWhiteGrayText6";
    public static final String key_windowBackgroundWhiteGrayText7 = "windowBackgroundWhiteGrayText7";
    public static final String key_windowBackgroundWhiteGrayText8 = "windowBackgroundWhiteGrayText8";
    public static final String key_windowBackgroundWhiteGreenText = "windowBackgroundWhiteGreenText";
    public static final String key_windowBackgroundWhiteGreenText2 = "windowBackgroundWhiteGreenText2";
    public static final String key_windowBackgroundWhiteHintText = "windowBackgroundWhiteHintText";
    public static final String key_windowBackgroundWhiteInputField = "windowBackgroundWhiteInputField";
    public static final String key_windowBackgroundWhiteInputFieldActivated = "windowBackgroundWhiteInputFieldActivated";
    public static final String key_windowBackgroundWhiteLinkSelection = "windowBackgroundWhiteLinkSelection";
    public static final String key_windowBackgroundWhiteLinkText = "windowBackgroundWhiteLinkText";
    public static final String key_windowBackgroundWhiteRedText = "windowBackgroundWhiteRedText";
    public static final String key_windowBackgroundWhiteRedText2 = "windowBackgroundWhiteRedText2";
    public static final String key_windowBackgroundWhiteRedText3 = "windowBackgroundWhiteRedText3";
    public static final String key_windowBackgroundWhiteRedText4 = "windowBackgroundWhiteRedText4";
    public static final String key_windowBackgroundWhiteRedText5 = "windowBackgroundWhiteRedText5";
    public static final String key_windowBackgroundWhiteRedText6 = "windowBackgroundWhiteRedText6";
    public static final String key_windowBackgroundWhiteValueText = "windowBackgroundWhiteValueText";
    public static String[] keys_avatar_actionBarIcon;
    public static String[] keys_avatar_actionBarSelector;
    public static String[] keys_avatar_background;
    public static String[] keys_avatar_backgroundActionBar;
    public static String[] keys_avatar_backgroundInProfile;
    public static String[] keys_avatar_nameInMessage;
    public static String[] keys_avatar_subtitleInProfile;
    private static float lastBrightnessValue;
    private static long lastHolidayCheckTime;
    private static long lastThemeSwitchTime;
    private static Sensor lightSensor;
    private static boolean lightSensorRegistered;
    public static Paint linkSelectionPaint;
    public static Drawable listSelector;
    private static Paint maskPaint;
    public static Drawable moveUpDrawable;
    private static ArrayList otherThemes;
    private static ThemeInfo previousTheme;
    public static TextPaint profile_aboutTextPaint;
    public static Drawable profile_verifiedCheckDrawable;
    public static Drawable profile_verifiedDrawable;
    public static int selectedAutoNightType;
    private static int selectedColor;
    private static SensorManager sensorManager;
    private static int serviceMessageColor;
    private static int serviceSelectedMessageColor;
    private static Runnable switchDayBrightnessRunnable;
    private static boolean switchDayRunnableScheduled;
    private static Runnable switchNightBrightnessRunnable;
    private static boolean switchNightRunnableScheduled;
    private static final Object sync;
    private static Drawable themedWallpaper;
    private static int themedWallpaperFileOffset;
    public static ArrayList themes;
    private static HashMap themesDict;
    private static Drawable wallpaper;
    private static final Object wallpaperSync;

    static {
        int v3_2;
        Theme.sync = new Object();
        Theme.wallpaperSync = new Object();
        Theme.lastBrightnessValue = 1f;
        Theme.switchDayBrightnessRunnable = new org.telegram.ui.ActionBar.Theme$1();
        Theme.switchNightBrightnessRunnable = new org.telegram.ui.ActionBar.Theme$2();
        Theme.selectedAutoNightType = 0;
        Theme.autoNightBrighnessThreshold = 0.25f;
        Theme.autoNightDayStartTime = 1320;
        Theme.autoNightDayEndTime = 480;
        Theme.autoNightSunsetTime = 1320;
        int v1 = -1;
        Theme.autoNightLastSunCheckDay = v1;
        Theme.autoNightSunriseTime = 480;
        Theme.autoNightCityName = "";
        Theme.autoNightLocationLatitude = 10000;
        Theme.autoNightLocationLongitude = 10000;
        Theme.maskPaint = new Paint(1);
        Theme.chat_attachButtonDrawables = new Drawable[9];
        Theme.chat_locationDrawable = new Drawable[2];
        Theme.chat_contactDrawable = new Drawable[2];
        Theme.chat_cornerOuter = new Drawable[4];
        Theme.chat_cornerInner = new Drawable[4];
        Theme.chat_fileStatesDrawable = new Drawable[10][2];
        Theme.chat_fileMiniStatesDrawable = new CombinedDrawable[6][2];
        Theme.chat_ivStatesDrawable = new Drawable[4][2];
        Theme.chat_photoStatesDrawables = new Drawable[13][2];
        Theme.keys_avatar_background = new String[]{"avatar_backgroundRed", "avatar_backgroundOrange", "avatar_backgroundViolet", "avatar_backgroundGreen", "avatar_backgroundCyan", "avatar_backgroundBlue", "avatar_backgroundPink"};
        Theme.keys_avatar_backgroundInProfile = new String[]{"avatar_backgroundInProfileRed", "avatar_backgroundInProfileOrange", "avatar_backgroundInProfileViolet", "avatar_backgroundInProfileGreen", "avatar_backgroundInProfileCyan", "avatar_backgroundInProfileBlue", "avatar_backgroundInProfilePink"};
        Theme.keys_avatar_backgroundActionBar = new String[]{"avatar_backgroundActionBarRed", "avatar_backgroundActionBarOrange", "avatar_backgroundActionBarViolet", "avatar_backgroundActionBarGreen", "avatar_backgroundActionBarCyan", "avatar_backgroundActionBarBlue", "avatar_backgroundActionBarPink"};
        Theme.keys_avatar_subtitleInProfile = new String[]{"avatar_subtitleInProfileRed", "avatar_subtitleInProfileOrange", "avatar_subtitleInProfileViolet", "avatar_subtitleInProfileGreen", "avatar_subtitleInProfileCyan", "avatar_subtitleInProfileBlue", "avatar_subtitleInProfilePink"};
        Theme.keys_avatar_nameInMessage = new String[]{"avatar_nameInMessageRed", "avatar_nameInMessageOrange", "avatar_nameInMessageViolet", "avatar_nameInMessageGreen", "avatar_nameInMessageCyan", "avatar_nameInMessageBlue", "avatar_nameInMessagePink"};
        Theme.keys_avatar_actionBarSelector = new String[]{"avatar_actionBarSelectorRed", "avatar_actionBarSelectorOrange", "avatar_actionBarSelectorViolet", "avatar_actionBarSelectorGreen", "avatar_actionBarSelectorCyan", "avatar_actionBarSelectorBlue", "avatar_actionBarSelectorPink"};
        Theme.keys_avatar_actionBarIcon = new String[]{"avatar_actionBarIconRed", "avatar_actionBarIconOrange", "avatar_actionBarIconViolet", "avatar_actionBarIconGreen", "avatar_actionBarIconCyan", "avatar_actionBarIconBlue", "avatar_actionBarIconPink"};
        Theme.defaultColors = new HashMap();
        Theme.fallbackKeys = new HashMap();
        Theme.defaultColors.put("dialogBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("dialogBackgroundGray", Integer.valueOf(-986896));
        Theme.defaultColors.put("dialogTextBlack", Integer.valueOf(-14606047));
        Theme.defaultColors.put("dialogTextLink", Integer.valueOf(-14255946));
        Theme.defaultColors.put("dialogLinkSelection", Integer.valueOf(862104035));
        Theme.defaultColors.put("dialogTextRed", Integer.valueOf(-3319206));
        Theme.defaultColors.put("dialogTextBlue", Integer.valueOf(-13660983));
        Theme.defaultColors.put("dialogTextBlue2", Integer.valueOf(-12940081));
        Theme.defaultColors.put("dialogTextBlue3", Integer.valueOf(-12664327));
        Theme.defaultColors.put("dialogTextBlue4", Integer.valueOf(-15095832));
        Theme.defaultColors.put("dialogTextGray", Integer.valueOf(-13333567));
        Theme.defaultColors.put("dialogTextGray2", Integer.valueOf(-9079435));
        Theme.defaultColors.put("dialogTextGray3", Integer.valueOf(-6710887));
        Theme.defaultColors.put("dialogTextGray4", Integer.valueOf(-5000269));
        Theme.defaultColors.put("dialogTextHint", Integer.valueOf(-6842473));
        Theme.defaultColors.put("dialogIcon", Integer.valueOf(-7697782));
        Theme.defaultColors.put("dialogGrayLine", Integer.valueOf(-2960686));
        Theme.defaultColors.put("dialogTopBackground", Integer.valueOf(-9456923));
        Theme.defaultColors.put("dialogInputField", Integer.valueOf(-2368549));
        Theme.defaultColors.put("dialogInputFieldActivated", Integer.valueOf(-13129232));
        Theme.defaultColors.put("dialogCheckboxSquareBackground", Integer.valueOf(-12345121));
        Theme.defaultColors.put("dialogCheckboxSquareCheck", Integer.valueOf(v1));
        Theme.defaultColors.put("dialogCheckboxSquareUnchecked", Integer.valueOf(-9211021));
        Theme.defaultColors.put("dialogCheckboxSquareDisabled", Integer.valueOf(-5197648));
        Theme.defaultColors.put("dialogRadioBackground", Integer.valueOf(-5000269));
        Theme.defaultColors.put("dialogRadioBackgroundChecked", Integer.valueOf(-13129232));
        Theme.defaultColors.put("dialogProgressCircle", Integer.valueOf(-11371101));
        Theme.defaultColors.put("dialogLineProgress", Integer.valueOf(-11371101));
        Theme.defaultColors.put("dialogLineProgressBackground", Integer.valueOf(-2368549));
        Theme.defaultColors.put("dialogButton", Integer.valueOf(-11955764));
        Theme.defaultColors.put("dialogButtonSelector", Integer.valueOf(251658240));
        Theme.defaultColors.put("dialogScrollGlow", Integer.valueOf(-657673));
        Theme.defaultColors.put("dialogRoundCheckBox", Integer.valueOf(-12664327));
        Theme.defaultColors.put("dialogRoundCheckBoxCheck", Integer.valueOf(v1));
        Theme.defaultColors.put("dialogBadgeBackground", Integer.valueOf(-12664327));
        Theme.defaultColors.put("dialogBadgeText", Integer.valueOf(v1));
        Theme.defaultColors.put("dialogCameraIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("windowBackgroundWhite", Integer.valueOf(v1));
        Theme.defaultColors.put("progressCircle", Integer.valueOf(-11371101));
        Theme.defaultColors.put("windowBackgroundWhiteGrayIcon", Integer.valueOf(-9211021));
        Theme.defaultColors.put("windowBackgroundWhiteBlueText", Integer.valueOf(-12876608));
        Theme.defaultColors.put("windowBackgroundWhiteBlueText2", Integer.valueOf(-13333567));
        Theme.defaultColors.put("windowBackgroundWhiteBlueText3", Integer.valueOf(-14255946));
        Theme.defaultColors.put("windowBackgroundWhiteBlueText4", Integer.valueOf(-11697229));
        Theme.defaultColors.put("windowBackgroundWhiteBlueText5", Integer.valueOf(-11759926));
        Theme.defaultColors.put("windowBackgroundWhiteBlueText6", Integer.valueOf(-12940081));
        Theme.defaultColors.put("windowBackgroundWhiteBlueText7", Integer.valueOf(-13141330));
        Theme.defaultColors.put("windowBackgroundWhiteGreenText", Integer.valueOf(-14248148));
        Theme.defaultColors.put("windowBackgroundWhiteGreenText2", Integer.valueOf(-13129447));
        Theme.defaultColors.put("windowBackgroundWhiteRedText", Integer.valueOf(-3319206));
        Theme.defaultColors.put("windowBackgroundWhiteRedText2", Integer.valueOf(-2404015));
        Theme.defaultColors.put("windowBackgroundWhiteRedText3", Integer.valueOf(-2995895));
        Theme.defaultColors.put("windowBackgroundWhiteRedText4", Integer.valueOf(-3198928));
        Theme.defaultColors.put("windowBackgroundWhiteRedText5", Integer.valueOf(-1229511));
        Theme.defaultColors.put("windowBackgroundWhiteRedText6", Integer.valueOf(-39322));
        Theme.defaultColors.put("windowBackgroundWhiteGrayText", Integer.valueOf(-5723992));
        Theme.defaultColors.put("windowBackgroundWhiteGrayText2", Integer.valueOf(-7697782));
        Theme.defaultColors.put("windowBackgroundWhiteGrayText3", Integer.valueOf(-6710887));
        Theme.defaultColors.put("windowBackgroundWhiteGrayText4", Integer.valueOf(-8355712));
        Theme.defaultColors.put("windowBackgroundWhiteGrayText5", Integer.valueOf(-6052957));
        Theme.defaultColors.put("windowBackgroundWhiteGrayText6", Integer.valueOf(-9079435));
        Theme.defaultColors.put("windowBackgroundWhiteGrayText7", Integer.valueOf(-3750202));
        Theme.defaultColors.put("windowBackgroundWhiteGrayText8", Integer.valueOf(-9605774));
        Theme.defaultColors.put("windowBackgroundWhiteGrayLine", Integer.valueOf(-2368549));
        Theme.defaultColors.put("windowBackgroundWhiteBlackText", Integer.valueOf(-14606047));
        Theme.defaultColors.put("windowBackgroundWhiteHintText", Integer.valueOf(-6842473));
        Theme.defaultColors.put("windowBackgroundWhiteValueText", Integer.valueOf(-13660983));
        Theme.defaultColors.put("windowBackgroundWhiteLinkText", Integer.valueOf(-14255946));
        Theme.defaultColors.put("windowBackgroundWhiteLinkSelection", Integer.valueOf(862104035));
        Theme.defaultColors.put("windowBackgroundWhiteBlueHeader", Integer.valueOf(-12676913));
        Theme.defaultColors.put("windowBackgroundWhiteInputField", Integer.valueOf(-2368549));
        Theme.defaultColors.put("windowBackgroundWhiteInputFieldActivated", Integer.valueOf(-13129232));
        Theme.defaultColors.put("switchThumb", Integer.valueOf(-1184275));
        Theme.defaultColors.put("switchTrack", Integer.valueOf(-3684409));
        Theme.defaultColors.put("switchThumbChecked", Integer.valueOf(-12211217));
        Theme.defaultColors.put("switchTrackChecked", Integer.valueOf(-6236422));
        Theme.defaultColors.put("switch2Thumb", Integer.valueOf(-2402212));
        Theme.defaultColors.put("switch2Track", Integer.valueOf(-20307));
        Theme.defaultColors.put("switch2ThumbChecked", Integer.valueOf(-12277526));
        Theme.defaultColors.put("switch2TrackChecked", Integer.valueOf(-6236422));
        Theme.defaultColors.put("switch2Check", Integer.valueOf(v1));
        Theme.defaultColors.put("checkboxSquareBackground", Integer.valueOf(-12345121));
        Theme.defaultColors.put("checkboxSquareCheck", Integer.valueOf(v1));
        Theme.defaultColors.put("checkboxSquareUnchecked", Integer.valueOf(-9211021));
        Theme.defaultColors.put("checkboxSquareDisabled", Integer.valueOf(-5197648));
        Theme.defaultColors.put("listSelectorSDK21", Integer.valueOf(251658240));
        Theme.defaultColors.put("radioBackground", Integer.valueOf(-5000269));
        Theme.defaultColors.put("radioBackgroundChecked", Integer.valueOf(-13129232));
        Theme.defaultColors.put("windowBackgroundGray", Integer.valueOf(-986896));
        Theme.defaultColors.put("windowBackgroundGrayShadow", Integer.valueOf(-16777216));
        Theme.defaultColors.put("emptyListPlaceholder", Integer.valueOf(-6974059));
        Theme.defaultColors.put("divider", Integer.valueOf(-2500135));
        Theme.defaultColors.put("graySection", Integer.valueOf(-855310));
        Theme.defaultColors.put("key_graySectionText", Integer.valueOf(-7697782));
        Theme.defaultColors.put("contextProgressInner1", Integer.valueOf(-4202506));
        Theme.defaultColors.put("contextProgressOuter1", Integer.valueOf(-13920542));
        Theme.defaultColors.put("contextProgressInner2", Integer.valueOf(-4202506));
        Theme.defaultColors.put("contextProgressOuter2", Integer.valueOf(v1));
        Theme.defaultColors.put("contextProgressInner3", Integer.valueOf(-5000269));
        Theme.defaultColors.put("contextProgressOuter3", Integer.valueOf(v1));
        Theme.defaultColors.put("fastScrollActive", Integer.valueOf(-11361317));
        Theme.defaultColors.put("fastScrollInactive", Integer.valueOf(-10263709));
        Theme.defaultColors.put("fastScrollText", Integer.valueOf(v1));
        Theme.defaultColors.put("avatar_text", Integer.valueOf(v1));
        Theme.defaultColors.put("avatar_backgroundSaved", Integer.valueOf(-10043398));
        Theme.defaultColors.put("avatar_backgroundRed", Integer.valueOf(-1743531));
        Theme.defaultColors.put("avatar_backgroundOrange", Integer.valueOf(-881592));
        Theme.defaultColors.put("avatar_backgroundViolet", Integer.valueOf(-7436818));
        Theme.defaultColors.put("avatar_backgroundGreen", Integer.valueOf(-8992691));
        Theme.defaultColors.put("avatar_backgroundCyan", Integer.valueOf(-10502443));
        Theme.defaultColors.put("avatar_backgroundBlue", Integer.valueOf(-11232035));
        Theme.defaultColors.put("avatar_backgroundPink", Integer.valueOf(-887654));
        Theme.defaultColors.put("avatar_backgroundGroupCreateSpanBlue", Integer.valueOf(-4204822));
        Theme.defaultColors.put("avatar_backgroundInProfileRed", Integer.valueOf(-2592923));
        Theme.defaultColors.put("avatar_backgroundInProfileOrange", Integer.valueOf(-615071));
        Theme.defaultColors.put("avatar_backgroundInProfileViolet", Integer.valueOf(-7570990));
        Theme.defaultColors.put("avatar_backgroundInProfileGreen", Integer.valueOf(-9981091));
        Theme.defaultColors.put("avatar_backgroundInProfileCyan", Integer.valueOf(-11099461));
        Theme.defaultColors.put("avatar_backgroundInProfileBlue", Integer.valueOf(-11500111));
        Theme.defaultColors.put("avatar_backgroundInProfilePink", Integer.valueOf(-819290));
        Theme.defaultColors.put("avatar_backgroundActionBarRed", Integer.valueOf(-3514282));
        Theme.defaultColors.put("avatar_backgroundActionBarOrange", Integer.valueOf(-947900));
        Theme.defaultColors.put("avatar_backgroundActionBarViolet", Integer.valueOf(-8557884));
        Theme.defaultColors.put("avatar_backgroundActionBarGreen", Integer.valueOf(-11099828));
        Theme.defaultColors.put("avatar_backgroundActionBarCyan", Integer.valueOf(-12283220));
        Theme.defaultColors.put("avatar_backgroundActionBarBlue", Integer.valueOf(-10907718));
        Theme.defaultColors.put("avatar_backgroundActionBarPink", Integer.valueOf(-10907718));
        Theme.defaultColors.put("avatar_subtitleInProfileRed", Integer.valueOf(-406587));
        Theme.defaultColors.put("avatar_subtitleInProfileOrange", Integer.valueOf(-139832));
        Theme.defaultColors.put("avatar_subtitleInProfileViolet", Integer.valueOf(-3291923));
        Theme.defaultColors.put("avatar_subtitleInProfileGreen", Integer.valueOf(-4133446));
        Theme.defaultColors.put("avatar_subtitleInProfileCyan", Integer.valueOf(-4660496));
        Theme.defaultColors.put("avatar_subtitleInProfileBlue", Integer.valueOf(-2626822));
        Theme.defaultColors.put("avatar_subtitleInProfilePink", Integer.valueOf(-2626822));
        Theme.defaultColors.put("avatar_nameInMessageRed", Integer.valueOf(-3516848));
        Theme.defaultColors.put("avatar_nameInMessageOrange", Integer.valueOf(-2589911));
        Theme.defaultColors.put("avatar_nameInMessageViolet", Integer.valueOf(-11627828));
        Theme.defaultColors.put("avatar_nameInMessageGreen", Integer.valueOf(-11488718));
        Theme.defaultColors.put("avatar_nameInMessageCyan", Integer.valueOf(-12406360));
        Theme.defaultColors.put("avatar_nameInMessageBlue", Integer.valueOf(-11627828));
        Theme.defaultColors.put("avatar_nameInMessagePink", Integer.valueOf(-11627828));
        Theme.defaultColors.put("avatar_actionBarSelectorRed", Integer.valueOf(-4437183));
        Theme.defaultColors.put("avatar_actionBarSelectorOrange", Integer.valueOf(-1674199));
        Theme.defaultColors.put("avatar_actionBarSelectorViolet", Integer.valueOf(-9216066));
        Theme.defaultColors.put("avatar_actionBarSelectorGreen", Integer.valueOf(-12020419));
        Theme.defaultColors.put("avatar_actionBarSelectorCyan", Integer.valueOf(-13007715));
        Theme.defaultColors.put("avatar_actionBarSelectorBlue", Integer.valueOf(-11959891));
        Theme.defaultColors.put("avatar_actionBarSelectorPink", Integer.valueOf(-11959891));
        Theme.defaultColors.put("avatar_actionBarIconRed", Integer.valueOf(v1));
        Theme.defaultColors.put("avatar_actionBarIconOrange", Integer.valueOf(v1));
        Theme.defaultColors.put("avatar_actionBarIconViolet", Integer.valueOf(v1));
        Theme.defaultColors.put("avatar_actionBarIconGreen", Integer.valueOf(v1));
        Theme.defaultColors.put("avatar_actionBarIconCyan", Integer.valueOf(v1));
        Theme.defaultColors.put("avatar_actionBarIconBlue", Integer.valueOf(v1));
        Theme.defaultColors.put("avatar_actionBarIconPink", Integer.valueOf(v1));
        Theme.defaultColors.put("actionBarDefault", Integer.valueOf(-11371101));
        Theme.defaultColors.put("actionBarDefaultIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("actionBarActionModeDefault", Integer.valueOf(v1));
        Theme.defaultColors.put("actionBarActionModeDefaultTop", Integer.valueOf(-1728053248));
        Theme.defaultColors.put("actionBarActionModeDefaultIcon", Integer.valueOf(-9211021));
        Theme.defaultColors.put("actionBarDefaultTitle", Integer.valueOf(v1));
        Theme.defaultColors.put("actionBarDefaultSubtitle", Integer.valueOf(-2758409));
        Theme.defaultColors.put("actionBarDefaultSelector", Integer.valueOf(-12554860));
        Theme.defaultColors.put("actionBarWhiteSelector", Integer.valueOf(788529152));
        Theme.defaultColors.put("actionBarDefaultSearch", Integer.valueOf(v1));
        Theme.defaultColors.put("actionBarDefaultSearchPlaceholder", Integer.valueOf(-1996488705));
        Theme.defaultColors.put("actionBarDefaultSubmenuItem", Integer.valueOf(-14606047));
        Theme.defaultColors.put("actionBarDefaultSubmenuBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("actionBarActionModeDefaultSelector", Integer.valueOf(-986896));
        Theme.defaultColors.put("chats_unreadCounter", Integer.valueOf(-11613090));
        Theme.defaultColors.put("chats_unreadCounterMuted", Integer.valueOf(-3684409));
        Theme.defaultColors.put("chats_unreadCounterText", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_name", Integer.valueOf(-14606047));
        Theme.defaultColors.put("chats_secretName", Integer.valueOf(-16734706));
        Theme.defaultColors.put("chats_secretIcon", Integer.valueOf(-15093466));
        Theme.defaultColors.put("chats_nameIcon", Integer.valueOf(-14408668));
        Theme.defaultColors.put("chats_pinnedIcon", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chats_message", Integer.valueOf(-7368817));
        Theme.defaultColors.put("chats_draft", Integer.valueOf(-2274503));
        Theme.defaultColors.put("chats_nameMessage", Integer.valueOf(-11697229));
        Theme.defaultColors.put("chats_attachMessage", Integer.valueOf(-11697229));
        Theme.defaultColors.put("chats_actionMessage", Integer.valueOf(-11697229));
        Theme.defaultColors.put("chats_date", Integer.valueOf(-6710887));
        Theme.defaultColors.put("chats_pinnedOverlay", Integer.valueOf(134217728));
        Theme.defaultColors.put("chats_tabletSelectedOverlay", Integer.valueOf(251658240));
        Theme.defaultColors.put("chats_sentCheck", Integer.valueOf(-12146122));
        Theme.defaultColors.put("chats_sentClock", Integer.valueOf(-9061026));
        Theme.defaultColors.put("chats_sentError", Integer.valueOf(-2796974));
        Theme.defaultColors.put("chats_sentErrorIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_verifiedBackground", Integer.valueOf(-13391642));
        Theme.defaultColors.put("chats_verifiedCheck", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_muteIcon", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chats_mentionIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_menuBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_menuItemText", Integer.valueOf(-12303292));
        Theme.defaultColors.put("chats_menuItemCheck", Integer.valueOf(-10907718));
        Theme.defaultColors.put("chats_menuItemIcon", Integer.valueOf(-9211021));
        Theme.defaultColors.put("chats_menuName", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_menuPhone", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_menuPhoneCats", Integer.valueOf(-4004353));
        Theme.defaultColors.put("chats_menuCloud", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_menuCloudBackgroundCats", Integer.valueOf(-12420183));
        Theme.defaultColors.put("chats_actionIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_actionBackground", Integer.valueOf(-9788978));
        Theme.defaultColors.put("chats_actionPressedBackground", Integer.valueOf(-11038014));
        Theme.defaultColors.put("chats_actionUnreadIcon", Integer.valueOf(-9211021));
        Theme.defaultColors.put("chats_actionUnreadBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("chats_actionUnreadPressedBackground", Integer.valueOf(-855310));
        Theme.defaultColors.put("chat_attachCameraIcon1", Integer.valueOf(-33488));
        Theme.defaultColors.put("chat_attachCameraIcon2", Integer.valueOf(-1353648));
        Theme.defaultColors.put("chat_attachCameraIcon3", Integer.valueOf(-12342798));
        Theme.defaultColors.put("chat_attachCameraIcon4", Integer.valueOf(-4958752));
        Theme.defaultColors.put("chat_attachCameraIcon5", Integer.valueOf(-10366879));
        Theme.defaultColors.put("chat_attachCameraIcon6", Integer.valueOf(-81627));
        Theme.defaultColors.put("chat_attachGalleryBackground", Integer.valueOf(-5997863));
        Theme.defaultColors.put("chat_attachGalleryIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_attachVideoBackground", Integer.valueOf(-1871495));
        Theme.defaultColors.put("chat_attachVideoIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_attachAudioBackground", Integer.valueOf(-620719));
        Theme.defaultColors.put("chat_attachAudioIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_attachFileBackground", Integer.valueOf(-13328140));
        Theme.defaultColors.put("chat_attachFileIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_attachContactBackground", Integer.valueOf(-12664838));
        Theme.defaultColors.put("chat_attachContactIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_attachLocationBackground", Integer.valueOf(-12597126));
        Theme.defaultColors.put("chat_attachLocationIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_attachHideBackground", Integer.valueOf(-5330248));
        Theme.defaultColors.put("chat_attachHideIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_attachSendBackground", Integer.valueOf(-12664838));
        Theme.defaultColors.put("chat_attachSendIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_lockIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_muteIcon", Integer.valueOf(-5124893));
        Theme.defaultColors.put("chat_inBubble", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inBubbleSelected", Integer.valueOf(-1902337));
        Theme.defaultColors.put("chat_inBubbleShadow", Integer.valueOf(-14862509));
        Theme.defaultColors.put("chat_outBubble", Integer.valueOf(-1048610));
        Theme.defaultColors.put("chat_outBubbleSelected", Integer.valueOf(-2820676));
        Theme.defaultColors.put("chat_outBubbleShadow", Integer.valueOf(-14781172));
        Theme.defaultColors.put("chat_inMediaIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inMediaIconSelected", Integer.valueOf(-1902337));
        Theme.defaultColors.put("chat_outMediaIcon", Integer.valueOf(-1048610));
        Theme.defaultColors.put("chat_outMediaIconSelected", Integer.valueOf(-2820676));
        Theme.defaultColors.put("chat_messageTextIn", Integer.valueOf(-16777216));
        Theme.defaultColors.put("chat_messageTextOut", Integer.valueOf(-16777216));
        Theme.defaultColors.put("chat_messageLinkIn", Integer.valueOf(-14255946));
        Theme.defaultColors.put("chat_messageLinkOut", Integer.valueOf(-14255946));
        Theme.defaultColors.put("chat_serviceText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_serviceLink", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_serviceIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_mediaTimeBackground", Integer.valueOf(1711276032));
        Theme.defaultColors.put("chat_outSentCheck", Integer.valueOf(-10637232));
        Theme.defaultColors.put("chat_outSentCheckSelected", Integer.valueOf(-10637232));
        Theme.defaultColors.put("chat_outSentClock", Integer.valueOf(-9061026));
        Theme.defaultColors.put("chat_outSentClockSelected", Integer.valueOf(-9061026));
        Theme.defaultColors.put("chat_inSentClock", Integer.valueOf(-6182221));
        Theme.defaultColors.put("chat_inSentClockSelected", Integer.valueOf(-7094838));
        Theme.defaultColors.put("chat_mediaSentCheck", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_mediaSentClock", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inViews", Integer.valueOf(-6182221));
        Theme.defaultColors.put("chat_inViewsSelected", Integer.valueOf(-7094838));
        Theme.defaultColors.put("chat_outViews", Integer.valueOf(-9522601));
        Theme.defaultColors.put("chat_outViewsSelected", Integer.valueOf(-9522601));
        Theme.defaultColors.put("chat_mediaViews", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inMenu", Integer.valueOf(-4801083));
        Theme.defaultColors.put("chat_inMenuSelected", Integer.valueOf(-6766130));
        Theme.defaultColors.put("chat_outMenu", Integer.valueOf(-7221634));
        Theme.defaultColors.put("chat_outMenuSelected", Integer.valueOf(-7221634));
        Theme.defaultColors.put("chat_mediaMenu", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_outInstant", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_outInstantSelected", Integer.valueOf(-12019389));
        Theme.defaultColors.put("chat_inInstant", Integer.valueOf(-12940081));
        Theme.defaultColors.put("chat_inInstantSelected", Integer.valueOf(-13600331));
        Theme.defaultColors.put("chat_sentError", Integer.valueOf(-2411211));
        Theme.defaultColors.put("chat_sentErrorIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_selectedBackground", Integer.valueOf(1714664933));
        Theme.defaultColors.put("chat_previewDurationText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_previewGameText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inPreviewInstantText", Integer.valueOf(-12940081));
        Theme.defaultColors.put("chat_outPreviewInstantText", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_inPreviewInstantSelectedText", Integer.valueOf(-13600331));
        Theme.defaultColors.put("chat_outPreviewInstantSelectedText", Integer.valueOf(-12019389));
        Theme.defaultColors.put("chat_secretTimeText", Integer.valueOf(-1776928));
        Theme.defaultColors.put("chat_stickerNameText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_botButtonText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_botProgress", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inForwardedNameText", Integer.valueOf(-13072697));
        Theme.defaultColors.put("chat_outForwardedNameText", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_inViaBotNameText", Integer.valueOf(-12940081));
        Theme.defaultColors.put("chat_outViaBotNameText", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_stickerViaBotNameText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inReplyLine", Integer.valueOf(-10903592));
        Theme.defaultColors.put("chat_outReplyLine", Integer.valueOf(-9520791));
        Theme.defaultColors.put("chat_stickerReplyLine", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inReplyNameText", Integer.valueOf(-12940081));
        Theme.defaultColors.put("chat_outReplyNameText", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_stickerReplyNameText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inReplyMessageText", Integer.valueOf(-16777216));
        Theme.defaultColors.put("chat_outReplyMessageText", Integer.valueOf(-16777216));
        Theme.defaultColors.put("chat_inReplyMediaMessageText", Integer.valueOf(-6182221));
        Theme.defaultColors.put("chat_outReplyMediaMessageText", Integer.valueOf(-10112933));
        Theme.defaultColors.put("chat_inReplyMediaMessageSelectedText", Integer.valueOf(-7752511));
        Theme.defaultColors.put("chat_outReplyMediaMessageSelectedText", Integer.valueOf(-10112933));
        Theme.defaultColors.put("chat_stickerReplyMessageText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inPreviewLine", Integer.valueOf(-9390872));
        Theme.defaultColors.put("chat_outPreviewLine", Integer.valueOf(-7812741));
        Theme.defaultColors.put("chat_inSiteNameText", Integer.valueOf(-12940081));
        Theme.defaultColors.put("chat_outSiteNameText", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_inContactNameText", Integer.valueOf(-11625772));
        Theme.defaultColors.put("chat_outContactNameText", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_inContactPhoneText", Integer.valueOf(-13683656));
        Theme.defaultColors.put("chat_inContactPhoneSelectedText", Integer.valueOf(-13683656));
        Theme.defaultColors.put("chat_outContactPhoneText", Integer.valueOf(-13286860));
        Theme.defaultColors.put("chat_outContactPhoneText", Integer.valueOf(-13286860));
        Theme.defaultColors.put("chat_mediaProgress", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inAudioProgress", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_outAudioProgress", Integer.valueOf(-1048610));
        Theme.defaultColors.put("chat_inAudioSelectedProgress", Integer.valueOf(-1902337));
        Theme.defaultColors.put("chat_outAudioSelectedProgress", Integer.valueOf(-2820676));
        Theme.defaultColors.put("chat_mediaTimeText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inTimeText", Integer.valueOf(-6182221));
        Theme.defaultColors.put("chat_outTimeText", Integer.valueOf(-9391780));
        Theme.defaultColors.put("chat_adminText", Integer.valueOf(-4143413));
        Theme.defaultColors.put("chat_adminSelectedText", Integer.valueOf(-7752511));
        Theme.defaultColors.put("chat_inTimeSelectedText", Integer.valueOf(-7752511));
        Theme.defaultColors.put("chat_outTimeSelectedText", Integer.valueOf(-9391780));
        Theme.defaultColors.put("chat_inAudioPerfomerText", Integer.valueOf(-13683656));
        Theme.defaultColors.put("chat_inAudioPerfomerSelectedText", Integer.valueOf(-13683656));
        Theme.defaultColors.put("chat_outAudioPerfomerText", Integer.valueOf(-13286860));
        Theme.defaultColors.put("chat_outAudioPerfomerSelectedText", Integer.valueOf(-13286860));
        Theme.defaultColors.put("chat_inAudioTitleText", Integer.valueOf(-11625772));
        Theme.defaultColors.put("chat_outAudioTitleText", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_inAudioDurationText", Integer.valueOf(-6182221));
        Theme.defaultColors.put("chat_outAudioDurationText", Integer.valueOf(-10112933));
        Theme.defaultColors.put("chat_inAudioDurationSelectedText", Integer.valueOf(-7752511));
        Theme.defaultColors.put("chat_outAudioDurationSelectedText", Integer.valueOf(-10112933));
        Theme.defaultColors.put("chat_inAudioSeekbar", Integer.valueOf(-1774864));
        Theme.defaultColors.put("chat_inAudioCacheSeekbar", Integer.valueOf(1071966960));
        Theme.defaultColors.put("chat_outAudioSeekbar", Integer.valueOf(-4463700));
        Theme.defaultColors.put("chat_outAudioCacheSeekbar", Integer.valueOf(1069278124));
        Theme.defaultColors.put("chat_inAudioSeekbarSelected", Integer.valueOf(-4399384));
        Theme.defaultColors.put("chat_outAudioSeekbarSelected", Integer.valueOf(-5644906));
        Theme.defaultColors.put("chat_inAudioSeekbarFill", Integer.valueOf(-9259544));
        Theme.defaultColors.put("chat_outAudioSeekbarFill", Integer.valueOf(-8863118));
        Theme.defaultColors.put("chat_inVoiceSeekbar", Integer.valueOf(-2169365));
        Theme.defaultColors.put("chat_outVoiceSeekbar", Integer.valueOf(-4463700));
        Theme.defaultColors.put("chat_inVoiceSeekbarSelected", Integer.valueOf(-4399384));
        Theme.defaultColors.put("chat_outVoiceSeekbarSelected", Integer.valueOf(-5644906));
        Theme.defaultColors.put("chat_inVoiceSeekbarFill", Integer.valueOf(-9259544));
        Theme.defaultColors.put("chat_outVoiceSeekbarFill", Integer.valueOf(-8863118));
        Theme.defaultColors.put("chat_inFileProgress", Integer.valueOf(-1314571));
        Theme.defaultColors.put("chat_outFileProgress", Integer.valueOf(-2427453));
        Theme.defaultColors.put("chat_inFileProgressSelected", Integer.valueOf(-3413258));
        Theme.defaultColors.put("chat_outFileProgressSelected", Integer.valueOf(-3806041));
        Theme.defaultColors.put("chat_inFileNameText", Integer.valueOf(-11625772));
        Theme.defaultColors.put("chat_outFileNameText", Integer.valueOf(-11162801));
        Theme.defaultColors.put("chat_inFileInfoText", Integer.valueOf(-6182221));
        Theme.defaultColors.put("chat_outFileInfoText", Integer.valueOf(-10112933));
        Theme.defaultColors.put("chat_inFileInfoSelectedText", Integer.valueOf(-7752511));
        Theme.defaultColors.put("chat_outFileInfoSelectedText", Integer.valueOf(-10112933));
        Theme.defaultColors.put("chat_inFileBackground", Integer.valueOf(-1314571));
        Theme.defaultColors.put("chat_outFileBackground", Integer.valueOf(-2427453));
        Theme.defaultColors.put("chat_inFileBackgroundSelected", Integer.valueOf(-3413258));
        Theme.defaultColors.put("chat_outFileBackgroundSelected", Integer.valueOf(-3806041));
        Theme.defaultColors.put("chat_inVenueInfoText", Integer.valueOf(-6182221));
        Theme.defaultColors.put("chat_outVenueInfoText", Integer.valueOf(-10112933));
        Theme.defaultColors.put("chat_inVenueInfoSelectedText", Integer.valueOf(-7752511));
        Theme.defaultColors.put("chat_outVenueInfoSelectedText", Integer.valueOf(-10112933));
        Theme.defaultColors.put("chat_mediaInfoText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_linkSelectBackground", Integer.valueOf(862104035));
        Theme.defaultColors.put("chat_textSelectBackground", Integer.valueOf(1717742051));
        Theme.defaultColors.put("chat_emojiPanelBackground", Integer.valueOf(-657673));
        Theme.defaultColors.put("chat_emojiPanelBadgeBackground", Integer.valueOf(-11688214));
        Theme.defaultColors.put("chat_emojiPanelBadgeText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_emojiSearchBackground", Integer.valueOf(-1578003));
        Theme.defaultColors.put("chat_emojiPanelShadowLine", Integer.valueOf(-1907225));
        Theme.defaultColors.put("chat_emojiPanelEmptyText", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chat_emojiPanelIcon", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chat_emojiPanelIconSelected", Integer.valueOf(-13920542));
        Theme.defaultColors.put("chat_emojiPanelStickerPackSelector", Integer.valueOf(-1907225));
        Theme.defaultColors.put("chat_emojiPanelIconSelector", Integer.valueOf(-13920542));
        Theme.defaultColors.put("chat_emojiPanelBackspace", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chat_emojiPanelMasksIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_emojiPanelMasksIconSelected", Integer.valueOf(-10305560));
        Theme.defaultColors.put("chat_emojiPanelTrendingTitle", Integer.valueOf(-14606047));
        Theme.defaultColors.put("chat_emojiPanelStickerSetName", Integer.valueOf(-8156010));
        Theme.defaultColors.put("chat_emojiPanelStickerSetNameIcon", Integer.valueOf(-5130564));
        Theme.defaultColors.put("chat_emojiPanelTrendingDescription", Integer.valueOf(-7697782));
        Theme.defaultColors.put("chat_botKeyboardButtonText", Integer.valueOf(-13220017));
        Theme.defaultColors.put("chat_botKeyboardButtonBackground", Integer.valueOf(-1775639));
        Theme.defaultColors.put("chat_botKeyboardButtonBackgroundPressed", Integer.valueOf(-3354156));
        Theme.defaultColors.put("chat_unreadMessagesStartArrowIcon", Integer.valueOf(-6113849));
        Theme.defaultColors.put("chat_unreadMessagesStartText", Integer.valueOf(-11102772));
        Theme.defaultColors.put("chat_unreadMessagesStartBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_editDoneIcon", Integer.valueOf(-11420173));
        Theme.defaultColors.put("chat_inFileIcon", Integer.valueOf(-6113849));
        Theme.defaultColors.put("chat_inFileSelectedIcon", Integer.valueOf(-7883067));
        Theme.defaultColors.put("chat_outFileIcon", Integer.valueOf(-8011912));
        Theme.defaultColors.put("chat_outFileSelectedIcon", Integer.valueOf(-8011912));
        Theme.defaultColors.put("chat_inLocationBackground", Integer.valueOf(-1314571));
        Theme.defaultColors.put("chat_inLocationIcon", Integer.valueOf(-6113849));
        Theme.defaultColors.put("chat_outLocationBackground", Integer.valueOf(-2427453));
        Theme.defaultColors.put("chat_outLocationIcon", Integer.valueOf(-7880840));
        Theme.defaultColors.put("chat_inContactBackground", Integer.valueOf(-9259544));
        Theme.defaultColors.put("chat_inContactIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_outContactBackground", Integer.valueOf(-8863118));
        Theme.defaultColors.put("chat_outContactIcon", Integer.valueOf(-1048610));
        Theme.defaultColors.put("chat_outBroadcast", Integer.valueOf(-12146122));
        Theme.defaultColors.put("chat_mediaBroadcast", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_searchPanelIcons", Integer.valueOf(-10639908));
        Theme.defaultColors.put("chat_searchPanelText", Integer.valueOf(-11625772));
        Theme.defaultColors.put("chat_secretChatStatusText", Integer.valueOf(-8421505));
        Theme.defaultColors.put("chat_fieldOverlayText", Integer.valueOf(-12940081));
        Theme.defaultColors.put("chat_stickersHintPanel", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_replyPanelIcons", Integer.valueOf(-11032346));
        Theme.defaultColors.put("chat_replyPanelClose", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chat_replyPanelName", Integer.valueOf(-12940081));
        Theme.defaultColors.put("chat_replyPanelMessage", Integer.valueOf(-14540254));
        Theme.defaultColors.put("chat_replyPanelLine", Integer.valueOf(-1513240));
        Theme.defaultColors.put("chat_messagePanelBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_messagePanelText", Integer.valueOf(-16777216));
        Theme.defaultColors.put("chat_messagePanelHint", Integer.valueOf(-5066062));
        Theme.defaultColors.put("chat_messagePanelShadow", Integer.valueOf(-16777216));
        Theme.defaultColors.put("chat_messagePanelIcons", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chat_recordedVoicePlayPause", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_recordedVoicePlayPausePressed", Integer.valueOf(-2495749));
        Theme.defaultColors.put("chat_recordedVoiceDot", Integer.valueOf(-2468275));
        Theme.defaultColors.put("chat_recordedVoiceBackground", Integer.valueOf(-11165981));
        Theme.defaultColors.put("chat_recordedVoiceProgress", Integer.valueOf(-6107400));
        Theme.defaultColors.put("chat_recordedVoiceProgressInner", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_recordVoiceCancel", Integer.valueOf(-6710887));
        Theme.defaultColors.put("chat_messagePanelSend", Integer.valueOf(-10309397));
        Theme.defaultColors.put("key_chat_messagePanelVoiceLock", Integer.valueOf(-5987164));
        Theme.defaultColors.put("key_chat_messagePanelVoiceLockBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("key_chat_messagePanelVoiceLockShadow", Integer.valueOf(-16777216));
        Theme.defaultColors.put("chat_recordTime", Integer.valueOf(-11711413));
        Theme.defaultColors.put("chat_emojiPanelNewTrending", Integer.valueOf(-11688214));
        Theme.defaultColors.put("chat_gifSaveHintText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_gifSaveHintBackground", Integer.valueOf(-871296751));
        Theme.defaultColors.put("chat_goDownButton", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_goDownButtonShadow", Integer.valueOf(-16777216));
        Theme.defaultColors.put("chat_goDownButtonIcon", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chat_goDownButtonCounter", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_goDownButtonCounterBackground", Integer.valueOf(-11689240));
        Theme.defaultColors.put("chat_messagePanelCancelInlineBot", Integer.valueOf(-5395027));
        Theme.defaultColors.put("chat_messagePanelVoicePressed", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_messagePanelVoiceBackground", Integer.valueOf(-11037236));
        Theme.defaultColors.put("chat_messagePanelVoiceShadow", Integer.valueOf(218103808));
        Theme.defaultColors.put("chat_messagePanelVoiceDelete", Integer.valueOf(-9211021));
        Theme.defaultColors.put("chat_messagePanelVoiceDuration", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_inlineResultIcon", Integer.valueOf(-11037236));
        Theme.defaultColors.put("chat_topPanelBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_topPanelClose", Integer.valueOf(-5723992));
        Theme.defaultColors.put("chat_topPanelLine", Integer.valueOf(-9658414));
        Theme.defaultColors.put("chat_topPanelTitle", Integer.valueOf(-12940081));
        Theme.defaultColors.put("chat_topPanelMessage", Integer.valueOf(-6710887));
        Theme.defaultColors.put("chat_reportSpam", Integer.valueOf(-3188393));
        Theme.defaultColors.put("chat_addContact", Integer.valueOf(-11894091));
        Theme.defaultColors.put("chat_inLoader", Integer.valueOf(-9259544));
        Theme.defaultColors.put("chat_inLoaderSelected", Integer.valueOf(-10114080));
        Theme.defaultColors.put("chat_outLoader", Integer.valueOf(-8863118));
        Theme.defaultColors.put("chat_outLoaderSelected", Integer.valueOf(-9783964));
        Theme.defaultColors.put("chat_inLoaderPhoto", Integer.valueOf(-6113080));
        Theme.defaultColors.put("chat_inLoaderPhotoSelected", Integer.valueOf(-6113849));
        Theme.defaultColors.put("chat_inLoaderPhotoIcon", Integer.valueOf(-197380));
        Theme.defaultColors.put("chat_inLoaderPhotoIconSelected", Integer.valueOf(-1314571));
        Theme.defaultColors.put("chat_outLoaderPhoto", Integer.valueOf(-8011912));
        Theme.defaultColors.put("chat_outLoaderPhotoSelected", Integer.valueOf(-8538000));
        Theme.defaultColors.put("chat_outLoaderPhotoIcon", Integer.valueOf(-2427453));
        Theme.defaultColors.put("chat_outLoaderPhotoIconSelected", Integer.valueOf(-4134748));
        Theme.defaultColors.put("chat_mediaLoaderPhoto", Integer.valueOf(1711276032));
        Theme.defaultColors.put("chat_mediaLoaderPhotoSelected", Integer.valueOf(2130706432));
        Theme.defaultColors.put("chat_mediaLoaderPhotoIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_mediaLoaderPhotoIconSelected", Integer.valueOf(-2500135));
        Theme.defaultColors.put("chat_secretTimerBackground", Integer.valueOf(-868326258));
        Theme.defaultColors.put("chat_secretTimerText", Integer.valueOf(v1));
        Theme.defaultColors.put("profile_creatorIcon", Integer.valueOf(-11888682));
        Theme.defaultColors.put("profile_adminIcon", Integer.valueOf(-8026747));
        Theme.defaultColors.put("profile_actionIcon", Integer.valueOf(-9211021));
        Theme.defaultColors.put("profile_actionBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("profile_actionPressedBackground", Integer.valueOf(-855310));
        Theme.defaultColors.put("profile_verifiedBackground", Integer.valueOf(-5056776));
        Theme.defaultColors.put("profile_verifiedCheck", Integer.valueOf(-11959368));
        Theme.defaultColors.put("profile_title", Integer.valueOf(v1));
        Theme.defaultColors.put("player_actionBar", Integer.valueOf(v1));
        Theme.defaultColors.put("player_actionBarSelector", Integer.valueOf(788529152));
        Theme.defaultColors.put("player_actionBarTitle", Integer.valueOf(-13683656));
        Theme.defaultColors.put("player_actionBarTop", Integer.valueOf(-1728053248));
        Theme.defaultColors.put("player_actionBarSubtitle", Integer.valueOf(-7697782));
        Theme.defaultColors.put("player_actionBarItems", Integer.valueOf(-7697782));
        Theme.defaultColors.put("player_background", Integer.valueOf(v1));
        Theme.defaultColors.put("player_time", Integer.valueOf(-7564650));
        Theme.defaultColors.put("player_progressBackground", Integer.valueOf(419430400));
        Theme.defaultColors.put("key_player_progressCachedBackground", Integer.valueOf(419430400));
        Theme.defaultColors.put("player_progress", Integer.valueOf(-14438417));
        Theme.defaultColors.put("player_placeholder", Integer.valueOf(-5723992));
        Theme.defaultColors.put("player_placeholderBackground", Integer.valueOf(-986896));
        Theme.defaultColors.put("player_button", Integer.valueOf(-13421773));
        Theme.defaultColors.put("player_buttonActive", Integer.valueOf(-11753238));
        Theme.defaultColors.put("files_folderIcon", Integer.valueOf(-6710887));
        Theme.defaultColors.put("files_folderIconBackground", Integer.valueOf(-986896));
        Theme.defaultColors.put("files_iconText", Integer.valueOf(v1));
        Theme.defaultColors.put("sessions_devicesImage", Integer.valueOf(-6908266));
        Theme.defaultColors.put("passport_authorizeBackground", Integer.valueOf(-12211217));
        Theme.defaultColors.put("passport_authorizeBackgroundSelected", Integer.valueOf(-12542501));
        Theme.defaultColors.put("passport_authorizeText", Integer.valueOf(v1));
        Theme.defaultColors.put("location_markerX", Integer.valueOf(-8355712));
        Theme.defaultColors.put("location_sendLocationBackground", Integer.valueOf(-9592620));
        Theme.defaultColors.put("location_sendLiveLocationBackground", Integer.valueOf(-39836));
        Theme.defaultColors.put("location_sendLocationIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("location_sendLiveLocationIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("location_liveLocationProgress", Integer.valueOf(-13262875));
        Theme.defaultColors.put("location_placeLocationBackground", Integer.valueOf(-11753238));
        Theme.defaultColors.put("dialog_liveLocationProgress", Integer.valueOf(-13262875));
        Theme.defaultColors.put("calls_callReceivedGreenIcon", Integer.valueOf(-16725933));
        Theme.defaultColors.put("calls_callReceivedRedIcon", Integer.valueOf(-47032));
        Theme.defaultColors.put("featuredStickers_addedIcon", Integer.valueOf(-11491093));
        Theme.defaultColors.put("featuredStickers_buttonProgress", Integer.valueOf(v1));
        Theme.defaultColors.put("featuredStickers_addButton", Integer.valueOf(-11491093));
        Theme.defaultColors.put("featuredStickers_addButtonPressed", Integer.valueOf(-12346402));
        Theme.defaultColors.put("featuredStickers_delButton", Integer.valueOf(-2533545));
        Theme.defaultColors.put("featuredStickers_delButtonPressed", Integer.valueOf(-3782327));
        Theme.defaultColors.put("featuredStickers_buttonText", Integer.valueOf(v1));
        Theme.defaultColors.put("featuredStickers_unread", Integer.valueOf(-11688214));
        Theme.defaultColors.put("inappPlayerPerformer", Integer.valueOf(-13683656));
        Theme.defaultColors.put("inappPlayerTitle", Integer.valueOf(-13683656));
        Theme.defaultColors.put("inappPlayerBackground", Integer.valueOf(v1));
        Theme.defaultColors.put("inappPlayerPlayPause", Integer.valueOf(-10309397));
        Theme.defaultColors.put("inappPlayerClose", Integer.valueOf(-5723992));
        Theme.defaultColors.put("returnToCallBackground", Integer.valueOf(-12279325));
        Theme.defaultColors.put("returnToCallText", Integer.valueOf(v1));
        Theme.defaultColors.put("sharedMedia_startStopLoadIcon", Integer.valueOf(-13196562));
        Theme.defaultColors.put("sharedMedia_linkPlaceholder", Integer.valueOf(-986896));
        Theme.defaultColors.put("sharedMedia_linkPlaceholderText", Integer.valueOf(v1));
        Theme.defaultColors.put("checkbox", Integer.valueOf(-10567099));
        Theme.defaultColors.put("checkboxCheck", Integer.valueOf(v1));
        Theme.defaultColors.put("stickers_menu", Integer.valueOf(-4801083));
        Theme.defaultColors.put("stickers_menuSelector", Integer.valueOf(788529152));
        Theme.defaultColors.put("changephoneinfo_image", Integer.valueOf(-5723992));
        Theme.defaultColors.put("key_changephoneinfo_changeText", Integer.valueOf(-11697229));
        Theme.defaultColors.put("groupcreate_hintText", Integer.valueOf(-6182221));
        Theme.defaultColors.put("groupcreate_cursor", Integer.valueOf(-11361317));
        Theme.defaultColors.put("groupcreate_sectionShadow", Integer.valueOf(-16777216));
        Theme.defaultColors.put("groupcreate_sectionText", Integer.valueOf(-8617336));
        Theme.defaultColors.put("groupcreate_onlineText", Integer.valueOf(-12545331));
        Theme.defaultColors.put("groupcreate_offlineText", Integer.valueOf(-8156010));
        Theme.defaultColors.put("groupcreate_checkbox", Integer.valueOf(-10567099));
        Theme.defaultColors.put("groupcreate_checkboxCheck", Integer.valueOf(v1));
        Theme.defaultColors.put("groupcreate_spanText", Integer.valueOf(-14606047));
        Theme.defaultColors.put("groupcreate_spanBackground", Integer.valueOf(-855310));
        Theme.defaultColors.put("contacts_inviteBackground", Integer.valueOf(-11157919));
        Theme.defaultColors.put("contacts_inviteText", Integer.valueOf(v1));
        Theme.defaultColors.put("login_progressInner", Integer.valueOf(-1971470));
        Theme.defaultColors.put("login_progressOuter", Integer.valueOf(-10313520));
        Theme.defaultColors.put("musicPicker_checkbox", Integer.valueOf(-14043401));
        Theme.defaultColors.put("musicPicker_checkboxCheck", Integer.valueOf(v1));
        Theme.defaultColors.put("musicPicker_buttonBackground", Integer.valueOf(-10702870));
        Theme.defaultColors.put("musicPicker_buttonIcon", Integer.valueOf(v1));
        Theme.defaultColors.put("picker_enabledButton", Integer.valueOf(-15095832));
        Theme.defaultColors.put("picker_disabledButton", Integer.valueOf(-6710887));
        Theme.defaultColors.put("picker_badge", Integer.valueOf(-14043401));
        Theme.defaultColors.put("picker_badgeText", Integer.valueOf(v1));
        Theme.defaultColors.put("chat_botSwitchToInlineText", Integer.valueOf(-12348980));
        Theme.defaultColors.put("calls_ratingStar", Integer.valueOf(-2147483648));
        Theme.defaultColors.put("calls_ratingStarSelected", Integer.valueOf(-11888682));
        Theme.fallbackKeys.put("chat_adminText", "chat_inTimeText");
        Theme.fallbackKeys.put("chat_adminSelectedText", "chat_inTimeSelectedText");
        Theme.fallbackKeys.put("key_player_progressCachedBackground", "player_progressBackground");
        Theme.fallbackKeys.put("chat_inAudioCacheSeekbar", "chat_inAudioSeekbar");
        Theme.fallbackKeys.put("chat_outAudioCacheSeekbar", "chat_outAudioSeekbar");
        Theme.fallbackKeys.put("chat_emojiSearchBackground", "chat_emojiPanelStickerPackSelector");
        Theme.fallbackKeys.put("location_sendLiveLocationIcon", "location_sendLocationIcon");
        Theme.fallbackKeys.put("key_changephoneinfo_changeText", "windowBackgroundWhiteBlueText4");
        Theme.fallbackKeys.put("key_graySectionText", "windowBackgroundWhiteGrayText2");
        Theme.fallbackKeys.put("chat_inMediaIcon", "chat_inBubble");
        Theme.fallbackKeys.put("chat_outMediaIcon", "chat_outBubble");
        Theme.fallbackKeys.put("chat_inMediaIconSelected", "chat_inBubbleSelected");
        Theme.fallbackKeys.put("chat_outMediaIconSelected", "chat_outBubbleSelected");
        Theme.fallbackKeys.put("chats_actionUnreadIcon", "profile_actionIcon");
        Theme.fallbackKeys.put("chats_actionUnreadBackground", "profile_actionBackground");
        Theme.fallbackKeys.put("chats_actionUnreadPressedBackground", "profile_actionPressedBackground");
        Theme.themes = new ArrayList();
        Theme.otherThemes = new ArrayList();
        Theme.themesDict = new HashMap();
        Theme.currentColors = new HashMap();
        ThemeInfo v2 = new ThemeInfo();
        v2.name = "Default";
        ArrayList v3 = Theme.themes;
        Theme.defaultTheme = v2;
        Theme.currentTheme = v2;
        Theme.currentDayTheme = v2;
        v3.add(v2);
        Theme.themesDict.put("Default", Theme.defaultTheme);
        v2 = new ThemeInfo();
        v2.name = "Dark";
        v2.assetName = "dark.attheme";
        v3 = Theme.themes;
        Theme.currentNightTheme = v2;
        v3.add(v2);
        Theme.themesDict.put("Dark", v2);
        v2 = new ThemeInfo();
        v2.name = "hotgram";
        v2.assetName = "hotgram.attheme";
        Theme.themes.add(v2);
        Theme.themesDict.put("hotgram", v2);
        v2 = new ThemeInfo();
        v2.name = "talagram";
        v2.assetName = "talagram.attheme";
        Theme.themes.add(v2);
        Theme.themesDict.put("talagram", v2);
        v2 = new ThemeInfo();
        v2.name = LocaleController.getString("more_theme", 2131626958);
        v2.assetName = "";
        Theme.themes.add(v2);
        Theme.themesDict.put(v2.name, v2);
        v2 = new ThemeInfo();
        v2.name = "Blue";
        v2.assetName = "bluebubbles.attheme";
        Theme.themes.add(v2);
        Theme.themesDict.put("Blue", v2);
        SharedPreferences v2_1 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String v3_1 = v2_1.getString("themes2", null);
        if(TextUtils.isEmpty(((CharSequence)v3_1))) {
            goto label_3024;
        }

        try {
            JSONArray v2_3 = new JSONArray(v3_1);
            v3_2 = 0;
            while(true) {
            label_3007:
                if(v3_2 >= v2_3.length()) {
                    goto label_3051;
                }

                ThemeInfo v4 = ThemeInfo.createWithJson(v2_3.getJSONObject(v3_2));
                if(v4 != null) {
                    Theme.otherThemes.add(v4);
                    Theme.themes.add(v4);
                    Theme.themesDict.put(v4.name, v4);
                }

                break;
            }
        }
        catch(Exception v2_2) {
            goto label_3022;
        }

        ++v3_2;
        goto label_3007;
    label_3022:
        FileLog.e(((Throwable)v2_2));
        goto label_3051;
    label_3024:
        v3_1 = v2_1.getString("themes", null);
        if(!TextUtils.isEmpty(((CharSequence)v3_1))) {
            String[] v3_3 = v3_1.split("&");
            int v4_1;
            for(v4_1 = 0; v4_1 < v3_3.length; ++v4_1) {
                ThemeInfo v5 = ThemeInfo.createWithString(v3_3[v4_1]);
                if(v5 != null) {
                    Theme.otherThemes.add(v5);
                    Theme.themes.add(v5);
                    Theme.themesDict.put(v5.name, v5);
                }
            }
        }

        Theme.saveOtherThemes();
        v2_1.edit().remove("themes").commit();
    label_3051:
        Theme.sortThemes();
        Object v2_4 = null;
        try {
            SharedPreferences v3_4 = MessagesController.getGlobalMainSettings();
            String v4_2 = v3_4.getString("theme", null);
            if(v4_2 != null) {
                v2_4 = Theme.themesDict.get(v4_2);
            }

            v4_2 = v3_4.getString("nighttheme", null);
            if(v4_2 != null) {
                Object v4_3 = Theme.themesDict.get(v4_2);
                if(v4_3 != null) {
                    Theme.currentNightTheme = ((ThemeInfo)v4_3);
                }
            }

            Theme.selectedAutoNightType = v3_4.getInt("selectedAutoNightType", 0);
            Theme.autoNightScheduleByLocation = v3_4.getBoolean("autoNightScheduleByLocation", false);
            Theme.autoNightBrighnessThreshold = v3_4.getFloat("autoNightBrighnessThreshold", 0.25f);
            Theme.autoNightDayStartTime = v3_4.getInt("autoNightDayStartTime", 1320);
            Theme.autoNightDayEndTime = v3_4.getInt("autoNightDayEndTime", 480);
            Theme.autoNightSunsetTime = v3_4.getInt("autoNightSunsetTime", 1320);
            Theme.autoNightSunriseTime = v3_4.getInt("autoNightSunriseTime", 480);
            Theme.autoNightCityName = v3_4.getString("autoNightCityName", "");
            long v4_4 = v3_4.getLong("autoNightLocationLatitude3", 10000);
            Theme.autoNightLocationLatitude = v4_4 != 10000 ? Double.longBitsToDouble(v4_4) : 10000;
            v4_4 = v3_4.getLong("autoNightLocationLongitude3", 10000);
            Theme.autoNightLocationLongitude = v4_4 != 10000 ? Double.longBitsToDouble(v4_4) : 10000;
            Theme.autoNightLastSunCheckDay = v3_4.getInt("autoNightLastSunCheckDay", v1);
        }
        catch(Exception v1_1) {
            FileLog.e(((Throwable)v1_1));
        }

        if(v2_4 == null) {
            v2 = Theme.defaultTheme;
        }
        else {
            Theme.currentDayTheme = ((ThemeInfo)v2_4);
        }

        Theme.applyTheme(((ThemeInfo)v2_4), false, false, false);
        AndroidUtilities.runOnUIThread(-$$Lambda$RQB0Jwr1FTqp6hrbGUHuOs-9k1I.INSTANCE);
        Theme.ambientSensorListener = new org.telegram.ui.ActionBar.Theme$7();
    }

    public Theme() {
        super();
    }

    static boolean access$000() {
        return Theme.switchDayRunnableScheduled;
    }

    static boolean access$002(boolean arg0) {
        Theme.switchDayRunnableScheduled = arg0;
        return arg0;
    }

    static void access$100(boolean arg0) {
        Theme.applyDayNightThemeMaybe(arg0);
    }

    static boolean access$200() {
        return Theme.switchNightRunnableScheduled;
    }

    static boolean access$202(boolean arg0) {
        Theme.switchNightRunnableScheduled = arg0;
        return arg0;
    }

    static Drawable access$300(Drawable arg0, int arg1) {
        return Theme.getStateDrawable(arg0, arg1);
    }

    static Paint access$400() {
        return Theme.maskPaint;
    }

    static float access$500() {
        return Theme.lastBrightnessValue;
    }

    static float access$502(float arg0) {
        Theme.lastBrightnessValue = arg0;
        return arg0;
    }

    static Runnable access$600() {
        return Theme.switchDayBrightnessRunnable;
    }

    static Runnable access$700() {
        return Theme.switchNightBrightnessRunnable;
    }

    static long access$800() {
        return Theme.getAutoNightSwitchThemeDelay();
    }

    public static void applyChatServiceMessageColor() {
        Integer v1_1;
        if(Theme.chat_actionBackgroundPaint == null) {
            return;
        }

        Object v0 = Theme.currentColors.get("chat_serviceBackground");
        Object v1 = Theme.currentColors.get("chat_serviceBackgroundSelected");
        if(v0 == null) {
            Integer v0_1 = Integer.valueOf(Theme.serviceMessageColor);
        }

        if(v1 == null) {
            v1_1 = Integer.valueOf(Theme.serviceSelectedMessageColor);
        }

        if(Theme.currentColor != ((Integer)v0).intValue()) {
            Theme.chat_actionBackgroundPaint.setColor(((Integer)v0).intValue());
            Theme.colorFilter = new PorterDuffColorFilter(((Integer)v0).intValue(), PorterDuff$Mode.MULTIPLY);
            Theme.currentColor = ((Integer)v0).intValue();
            int v2 = 0;
            if(Theme.chat_cornerOuter[0] == null) {
                goto label_44;
            }

            while(v2 < 4) {
                Theme.chat_cornerOuter[v2].setColorFilter(Theme.colorFilter);
                Theme.chat_cornerInner[v2].setColorFilter(Theme.colorFilter);
                ++v2;
            }
        }

    label_44:
        if(Theme.currentSelectedColor != v1_1.intValue()) {
            Theme.currentSelectedColor = v1_1.intValue();
            Theme.colorPressedFilter = new PorterDuffColorFilter(v1_1.intValue(), PorterDuff$Mode.MULTIPLY);
        }
    }

    public static void applyChatTheme(boolean arg8) {
        int v4;
        int v3;
        int v1;
        if(Theme.chat_msgTextPaint == null) {
            return;
        }

        if(Theme.chat_msgInDrawable != null && !arg8) {
            Theme.chat_gamePaint.setColor(Theme.getColor("chat_previewGameText"));
            Theme.chat_durationPaint.setColor(Theme.getColor("chat_previewDurationText"));
            Theme.chat_botButtonPaint.setColor(Theme.getColor("chat_botButtonText"));
            Theme.chat_urlPaint.setColor(Theme.getColor("chat_linkSelectBackground"));
            Theme.chat_botProgressPaint.setColor(Theme.getColor("chat_botProgress"));
            Theme.chat_deleteProgressPaint.setColor(Theme.getColor("chat_secretTimeText"));
            Theme.chat_textSearchSelectionPaint.setColor(Theme.getColor("chat_textSelectBackground"));
            Theme.chat_msgErrorPaint.setColor(Theme.getColor("chat_sentError"));
            Theme.chat_statusPaint.setColor(Theme.getColor("actionBarDefaultSubtitle"));
            Theme.chat_statusRecordPaint.setColor(Theme.getColor("actionBarDefaultSubtitle"));
            Theme.chat_actionTextPaint.setColor(Theme.getColor("chat_serviceText"));
            Theme.chat_actionTextPaint.linkColor = Theme.getColor("chat_serviceLink");
            Theme.chat_contextResult_titleTextPaint.setColor(Theme.getColor("windowBackgroundWhiteBlackText"));
            Theme.chat_composeBackgroundPaint.setColor(Theme.getColor("chat_messagePanelBackground"));
            Theme.chat_timeBackgroundPaint.setColor(Theme.getColor("chat_mediaTimeBackground"));
            Theme.setDrawableColorByKey(Theme.chat_msgInDrawable, "chat_inBubble");
            Theme.setDrawableColorByKey(Theme.chat_msgInSelectedDrawable, "chat_inBubbleSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgInShadowDrawable, "chat_inBubbleShadow");
            Theme.setDrawableColorByKey(Theme.chat_msgOutDrawable, "chat_outBubble");
            Theme.setDrawableColorByKey(Theme.chat_msgOutSelectedDrawable, "chat_outBubbleSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgOutShadowDrawable, "chat_outBubbleShadow");
            Theme.setDrawableColorByKey(Theme.chat_msgInMediaDrawable, "chat_inBubble");
            Theme.setDrawableColorByKey(Theme.chat_msgInMediaSelectedDrawable, "chat_inBubbleSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgInMediaShadowDrawable, "chat_inBubbleShadow");
            Theme.setDrawableColorByKey(Theme.chat_msgOutMediaDrawable, "chat_outBubble");
            Theme.setDrawableColorByKey(Theme.chat_msgOutMediaSelectedDrawable, "chat_outBubbleSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgOutMediaShadowDrawable, "chat_outBubbleShadow");
            Theme.setDrawableColorByKey(Theme.chat_msgOutCheckDrawable, "chat_outSentCheck");
            Theme.setDrawableColorByKey(Theme.chat_msgOutCheckSelectedDrawable, "chat_outSentCheckSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgOutHalfCheckDrawable, "chat_outSentCheck");
            Theme.setDrawableColorByKey(Theme.chat_msgOutHalfCheckSelectedDrawable, "chat_outSentCheckSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgOutClockDrawable, "chat_outSentClock");
            Theme.setDrawableColorByKey(Theme.chat_msgOutSelectedClockDrawable, "chat_outSentClockSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgInClockDrawable, "chat_inSentClock");
            Theme.setDrawableColorByKey(Theme.chat_msgInSelectedClockDrawable, "chat_inSentClockSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgMediaCheckDrawable, "chat_mediaSentCheck");
            Theme.setDrawableColorByKey(Theme.chat_msgMediaHalfCheckDrawable, "chat_mediaSentCheck");
            Theme.setDrawableColorByKey(Theme.chat_msgMediaClockDrawable, "chat_mediaSentClock");
            Theme.setDrawableColorByKey(Theme.chat_msgStickerCheckDrawable, "chat_serviceText");
            Theme.setDrawableColorByKey(Theme.chat_msgStickerHalfCheckDrawable, "chat_serviceText");
            Theme.setDrawableColorByKey(Theme.chat_msgStickerClockDrawable, "chat_serviceText");
            Theme.setDrawableColorByKey(Theme.chat_msgStickerViewsDrawable, "chat_serviceText");
            Theme.setDrawableColorByKey(Theme.chat_shareIconDrawable, "chat_serviceIcon");
            Theme.setDrawableColorByKey(Theme.chat_replyIconDrawable, "chat_serviceIcon");
            Theme.setDrawableColorByKey(Theme.chat_goIconDrawable, "chat_serviceIcon");
            Theme.setDrawableColorByKey(Theme.chat_botInlineDrawable, "chat_serviceIcon");
            Theme.setDrawableColorByKey(Theme.chat_botLinkDrawalbe, "chat_serviceIcon");
            Theme.setDrawableColorByKey(Theme.chat_msgInViewsDrawable, "chat_inViews");
            Theme.setDrawableColorByKey(Theme.chat_msgInViewsSelectedDrawable, "chat_inViewsSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgOutViewsDrawable, "chat_outViews");
            Theme.setDrawableColorByKey(Theme.chat_msgOutViewsSelectedDrawable, "chat_outViewsSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgMediaViewsDrawable, "chat_mediaViews");
            Theme.setDrawableColorByKey(Theme.chat_msgInMenuDrawable, "chat_inMenu");
            Theme.setDrawableColorByKey(Theme.chat_msgInMenuSelectedDrawable, "chat_inMenuSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgOutMenuDrawable, "chat_outMenu");
            Theme.setDrawableColorByKey(Theme.chat_msgOutMenuSelectedDrawable, "chat_outMenuSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgMediaMenuDrawable, "chat_mediaMenu");
            Theme.setDrawableColorByKey(Theme.chat_msgOutInstantDrawable, "chat_outInstant");
            Theme.setDrawableColorByKey(Theme.chat_msgInInstantDrawable, "chat_inInstant");
            Theme.setDrawableColorByKey(Theme.chat_msgErrorDrawable, "chat_sentErrorIcon");
            Theme.setDrawableColorByKey(Theme.chat_muteIconDrawable, "chat_muteIcon");
            Theme.setDrawableColorByKey(Theme.chat_lockIconDrawable, "chat_lockIcon");
            Theme.setDrawableColorByKey(Theme.chat_msgBroadcastDrawable, "chat_outBroadcast");
            Theme.setDrawableColorByKey(Theme.chat_msgBroadcastMediaDrawable, "chat_mediaBroadcast");
            Theme.setDrawableColorByKey(Theme.chat_inlineResultFile, "chat_inlineResultIcon");
            Theme.setDrawableColorByKey(Theme.chat_inlineResultAudio, "chat_inlineResultIcon");
            Theme.setDrawableColorByKey(Theme.chat_inlineResultLocation, "chat_inlineResultIcon");
            Theme.setDrawableColorByKey(Theme.chat_msgInCallDrawable, "chat_inInstant");
            Theme.setDrawableColorByKey(Theme.chat_msgInCallSelectedDrawable, "chat_inInstantSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgOutCallDrawable, "chat_outInstant");
            Theme.setDrawableColorByKey(Theme.chat_msgOutCallSelectedDrawable, "chat_outInstantSelected");
            Theme.setDrawableColorByKey(Theme.chat_msgCallUpRedDrawable, "calls_callReceivedRedIcon");
            Theme.setDrawableColorByKey(Theme.chat_msgCallUpGreenDrawable, "calls_callReceivedGreenIcon");
            Theme.setDrawableColorByKey(Theme.chat_msgCallDownRedDrawable, "calls_callReceivedRedIcon");
            Theme.setDrawableColorByKey(Theme.chat_msgCallDownGreenDrawable, "calls_callReceivedGreenIcon");
            int v0;
            for(v0 = 0; true; ++v0) {
                v1 = 2;
                if(v0 >= v1) {
                    break;
                }

                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v0][0], Theme.getColor("chat_outLoader"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v0][0], Theme.getColor("chat_outMediaIcon"), true);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v0][1], Theme.getColor("chat_outLoaderSelected"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v0][1], Theme.getColor("chat_outMediaIconSelected"), true);
                v3 = v0 + 2;
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v3][0], Theme.getColor("chat_inLoader"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v3][0], Theme.getColor("chat_inMediaIcon"), true);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v3][1], Theme.getColor("chat_inLoaderSelected"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v3][1], Theme.getColor("chat_inMediaIconSelected"), true);
                v3 = v0 + 4;
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v3][0], Theme.getColor("chat_mediaLoaderPhoto"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v3][0], Theme.getColor("chat_mediaLoaderPhotoIcon"), true);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v3][1], Theme.getColor("chat_mediaLoaderPhotoSelected"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileMiniStatesDrawable[v3][1], Theme.getColor("chat_mediaLoaderPhotoIconSelected"), true);
            }

            for(v0 = 0; true; ++v0) {
                v3 = 5;
                if(v0 >= v3) {
                    break;
                }

                Theme.setCombinedDrawableColor(Theme.chat_fileStatesDrawable[v0][0], Theme.getColor("chat_outLoader"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileStatesDrawable[v0][0], Theme.getColor("chat_outMediaIcon"), true);
                Theme.setCombinedDrawableColor(Theme.chat_fileStatesDrawable[v0][1], Theme.getColor("chat_outLoaderSelected"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileStatesDrawable[v0][1], Theme.getColor("chat_outMediaIconSelected"), true);
                v4 = v0 + 5;
                Theme.setCombinedDrawableColor(Theme.chat_fileStatesDrawable[v4][0], Theme.getColor("chat_inLoader"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileStatesDrawable[v4][0], Theme.getColor("chat_inMediaIcon"), true);
                Theme.setCombinedDrawableColor(Theme.chat_fileStatesDrawable[v4][1], Theme.getColor("chat_inLoaderSelected"), false);
                Theme.setCombinedDrawableColor(Theme.chat_fileStatesDrawable[v4][1], Theme.getColor("chat_inMediaIconSelected"), true);
            }

            for(v0 = 0; true; ++v0) {
                v4 = 4;
                if(v0 >= v4) {
                    break;
                }

                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v0][0], Theme.getColor("chat_mediaLoaderPhoto"), false);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v0][0], Theme.getColor("chat_mediaLoaderPhotoIcon"), true);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v0][1], Theme.getColor("chat_mediaLoaderPhotoSelected"), false);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v0][1], Theme.getColor("chat_mediaLoaderPhotoIconSelected"), true);
            }

            for(v0 = 0; v0 < v1; ++v0) {
                int v6 = v0 + 7;
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v6][0], Theme.getColor("chat_outLoaderPhoto"), false);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v6][0], Theme.getColor("chat_outLoaderPhotoIcon"), true);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v6][1], Theme.getColor("chat_outLoaderPhotoSelected"), false);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v6][1], Theme.getColor("chat_outLoaderPhotoIconSelected"), true);
                v6 = v0 + 10;
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v6][0], Theme.getColor("chat_inLoaderPhoto"), false);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v6][0], Theme.getColor("chat_inLoaderPhotoIcon"), true);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v6][1], Theme.getColor("chat_inLoaderPhotoSelected"), false);
                Theme.setCombinedDrawableColor(Theme.chat_photoStatesDrawables[v6][1], Theme.getColor("chat_inLoaderPhotoIconSelected"), true);
            }

            Theme.setDrawableColorByKey(Theme.chat_photoStatesDrawables[9][0], "chat_outFileIcon");
            Theme.setDrawableColorByKey(Theme.chat_photoStatesDrawables[9][1], "chat_outFileSelectedIcon");
            Theme.setDrawableColorByKey(Theme.chat_photoStatesDrawables[12][0], "chat_inFileIcon");
            Theme.setDrawableColorByKey(Theme.chat_photoStatesDrawables[12][1], "chat_inFileSelectedIcon");
            Theme.setCombinedDrawableColor(Theme.chat_contactDrawable[0], Theme.getColor("chat_inContactBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_contactDrawable[0], Theme.getColor("chat_inContactIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_contactDrawable[1], Theme.getColor("chat_outContactBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_contactDrawable[1], Theme.getColor("chat_outContactIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_locationDrawable[0], Theme.getColor("chat_inLocationBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_locationDrawable[0], Theme.getColor("chat_inLocationIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_locationDrawable[1], Theme.getColor("chat_outLocationBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_locationDrawable[1], Theme.getColor("chat_outLocationIcon"), true);
            Theme.setDrawableColorByKey(Theme.chat_composeShadowDrawable, "chat_messagePanelShadow");
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[1], Theme.getColor("chat_attachGalleryBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[1], Theme.getColor("chat_attachGalleryIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[v1], Theme.getColor("chat_attachVideoBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[v1], Theme.getColor("chat_attachVideoIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[3], Theme.getColor("chat_attachAudioBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[3], Theme.getColor("chat_attachAudioIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[v4], Theme.getColor("chat_attachFileBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[v4], Theme.getColor("chat_attachFileIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[v3], Theme.getColor("chat_attachContactBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[v3], Theme.getColor("chat_attachContactIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[6], Theme.getColor("chat_attachLocationBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[6], Theme.getColor("chat_attachLocationIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[7], Theme.getColor("chat_attachHideBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[7], Theme.getColor("chat_attachHideIcon"), true);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[8], Theme.getColor("chat_attachSendBackground"), false);
            Theme.setCombinedDrawableColor(Theme.chat_attachButtonDrawables[8], Theme.getColor("chat_attachSendIcon"), true);
            Theme.applyChatServiceMessageColor();
        }
    }

    public static void applyCommonTheme() {
        if(Theme.dividerPaint == null) {
            return;
        }

        Theme.dividerPaint.setColor(Theme.getColor("divider"));
        Theme.linkSelectionPaint.setColor(Theme.getColor("windowBackgroundWhiteLinkSelection"));
        Theme.setDrawableColorByKey(Theme.avatar_broadcastDrawable, "avatar_text");
        Theme.setDrawableColorByKey(Theme.avatar_savedDrawable, "avatar_text");
        Theme.setDrawableColorByKey(Theme.avatar_photoDrawable, "avatar_text");
    }

    private static void applyDayNightThemeMaybe(boolean arg4) {
        Object[] v1;
        int v2;
        NotificationCenter v4;
        if(arg4) {
            if(Theme.currentTheme != Theme.currentNightTheme) {
                Theme.lastThemeSwitchTime = SystemClock.elapsedRealtime();
                v4 = NotificationCenter.getGlobalInstance();
                v2 = NotificationCenter.needSetDayNightTheme;
                v1 = new Object[]{Theme.currentNightTheme};
                goto label_13;
            }
        }
        else if(Theme.currentTheme != Theme.currentDayTheme) {
            Theme.lastThemeSwitchTime = SystemClock.elapsedRealtime();
            v4 = NotificationCenter.getGlobalInstance();
            v2 = NotificationCenter.needSetDayNightTheme;
            v1 = new Object[]{Theme.currentDayTheme};
        label_13:
            v4.postNotificationName(v2, v1);
        }
    }

    public static void applyDialogsTheme() {
        if(Theme.dialogs_namePaint == null) {
            return;
        }

        Theme.dialogs_namePaint.setColor(Theme.getColor("chats_name"));
        Theme.dialogs_nameEncryptedPaint.setColor(Theme.getColor("chats_secretName"));
        TextPaint v0 = Theme.dialogs_messagePaint;
        TextPaint v1 = Theme.dialogs_messagePaint;
        int v2 = Theme.getColor("chats_message");
        v1.linkColor = v2;
        v0.setColor(v2);
        Theme.dialogs_tabletSeletedPaint.setColor(Theme.getColor("chats_tabletSelectedOverlay"));
        Theme.dialogs_pinnedPaint.setColor(Theme.getColor("chats_pinnedOverlay"));
        Theme.dialogs_timePaint.setColor(Theme.getColor("chats_date"));
        Theme.dialogs_countTextPaint.setColor(Theme.getColor("chats_unreadCounterText"));
        Theme.dialogs_messagePrintingPaint.setColor(Theme.getColor("chats_actionMessage"));
        Theme.dialogs_countPaint.setColor(Theme.getColor("chats_unreadCounter"));
        Theme.dialogs_countGrayPaint.setColor(Theme.getColor("chats_unreadCounterMuted"));
        Theme.dialogs_errorPaint.setColor(Theme.getColor("chats_sentError"));
        Theme.dialogs_onlinePaint.setColor(Theme.getColor("windowBackgroundWhiteBlueText3"));
        Theme.dialogs_offlinePaint.setColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        Theme.setDrawableColorByKey(Theme.dialogs_lockDrawable, "chats_secretIcon");
        Theme.setDrawableColorByKey(Theme.dialogs_checkDrawable, "chats_sentCheck");
        Theme.setDrawableColorByKey(Theme.dialogs_halfCheckDrawable, "chats_sentCheck");
        Theme.setDrawableColorByKey(Theme.dialogs_clockDrawable, "chats_sentClock");
        Theme.setDrawableColorByKey(Theme.dialogs_errorDrawable, "chats_sentErrorIcon");
        Theme.setDrawableColorByKey(Theme.dialogs_groupDrawable, "chats_nameIcon");
        Theme.setDrawableColorByKey(Theme.dialogs_broadcastDrawable, "chats_nameIcon");
        Theme.setDrawableColorByKey(Theme.dialogs_botDrawable, "chats_nameIcon");
        Theme.setDrawableColorByKey(Theme.dialogs_pinnedDrawable, "chats_pinnedIcon");
        Theme.setDrawableColorByKey(Theme.dialogs_muteDrawable, "chats_muteIcon");
        Theme.setDrawableColorByKey(Theme.dialogs_mentionDrawable, "chats_mentionIcon");
        Theme.setDrawableColorByKey(Theme.dialogs_verifiedDrawable, "chats_verifiedBackground");
        Theme.setDrawableColorByKey(Theme.dialogs_verifiedCheckDrawable, "chats_verifiedCheck");
    }

    public static void applyPreviousTheme() {
        if(Theme.previousTheme == null) {
            return;
        }

        Theme.applyTheme(Theme.previousTheme, true, false, false);
        Theme.previousTheme = null;
        Theme.checkAutoNightThemeConditions();
    }

    public static void applyProfileTheme() {
        if(Theme.profile_verifiedDrawable == null) {
            return;
        }

        Theme.profile_aboutTextPaint.setColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        Theme.profile_aboutTextPaint.linkColor = Theme.getColor("windowBackgroundWhiteLinkText");
        Theme.setDrawableColorByKey(Theme.profile_verifiedDrawable, "profile_verifiedBackground");
        Theme.setDrawableColorByKey(Theme.profile_verifiedCheckDrawable, "profile_verifiedCheck");
    }

    public static void applyTheme(ThemeInfo arg3, boolean arg4, boolean arg5, boolean arg6) {
        SharedPreferences$Editor v4;
        if(arg3 == null) {
            return;
        }

        ThemeEditorView v0 = ThemeEditorView.getInstance();
        if(v0 != null) {
            v0.destroy();
        }

        try {
            Drawable v1 = null;
            if(arg3.pathToFile != null || arg3.assetName != null) {
                if(!arg6 && (arg4)) {
                    v4 = MessagesController.getGlobalMainSettings().edit();
                    v4.putString("theme", arg3.name);
                    if(arg5) {
                        v4.remove("overrideThemeWallpaper");
                    }

                    v4.commit();
                }

                HashMap v4_1 = arg3.assetName != null ? Theme.getThemeFileValues(((File)v1), arg3.assetName) : Theme.getThemeFileValues(new File(arg3.pathToFile), ((String)v1));
                Theme.currentColors = v4_1;
            }
            else {
                if(!arg6 && (arg4)) {
                    v4 = MessagesController.getGlobalMainSettings().edit();
                    v4.remove("theme");
                    if(arg5) {
                        v4.remove("overrideThemeWallpaper");
                    }

                    v4.commit();
                }

                Theme.currentColors.clear();
                Theme.wallpaper = v1;
                Theme.themedWallpaper = v1;
            }

            Theme.currentTheme = arg3;
            if(!arg6) {
                Theme.currentDayTheme = Theme.currentTheme;
            }

            Theme.reloadWallpaper();
            Theme.applyCommonTheme();
            Theme.applyDialogsTheme();
            Theme.applyProfileTheme();
            Theme.applyChatTheme(false);
            AndroidUtilities.runOnUIThread(new -$$Lambda$Theme$U5dmA2RnRuUehj9EUY9kmkkhUlE(arg6));
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public static void applyTheme(ThemeInfo arg2) {
        Theme.applyTheme(arg2, true, true, false);
    }

    public static void applyTheme(ThemeInfo arg1, boolean arg2) {
        Theme.applyTheme(arg1, true, true, arg2);
    }

    public static ThemeInfo applyThemeFile(File arg4, String arg5, boolean arg6) {
        int v4_3;
        ThemeInfo v5;
        ThemeInfo v0 = null;
        try {
            if(!arg5.equals("Default") && !arg5.equals("Dark")) {
                if(arg5.equals("Blue")) {
                }
                else {
                    File v1 = new File(ApplicationLoader.getFilesDirFixed(), arg5);
                    if(!AndroidUtilities.copyFile(arg4, v1)) {
                        return v0;
                    }
                    else {
                        Object v4_1 = Theme.themesDict.get(arg5);
                        if(v4_1 == null) {
                            ThemeInfo v4_2 = new ThemeInfo();
                            v4_2.name = arg5;
                            v4_2.pathToFile = v1.getAbsolutePath();
                            v5 = v4_2;
                            v4_3 = 1;
                        }
                        else {
                            Object v5_1 = v4_1;
                            v4_3 = 0;
                        }

                        if(!arg6) {
                            Theme.previousTheme = v0;
                            if(v4_3 != 0) {
                                Theme.themes.add(v5);
                                Theme.themesDict.put(v5.name, v5);
                                Theme.otherThemes.add(v5);
                                Theme.sortThemes();
                                Theme.saveOtherThemes();
                            }
                        }
                        else {
                            Theme.previousTheme = Theme.currentTheme;
                        }

                        Theme.applyTheme(v5, (((int)arg6)) ^ 1, true, false);
                        return v5;
                    }
                }
            }
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
            return v0;
        }

        return v0;
    }

    private static void calcBackgroundColor(Drawable arg1, int arg2) {
        if(arg2 != 2) {
            int[] v1 = AndroidUtilities.calcDrawableColor(arg1);
            Theme.serviceMessageColor = v1[0];
            Theme.serviceSelectedMessageColor = v1[1];
        }
    }

    public static boolean canStartHolidayAnimation() {
        return Theme.canStartHolidayAnimation;
    }

    public static void checkAutoNightThemeConditions() {
        Theme.checkAutoNightThemeConditions(false);
    }

    public static void checkAutoNightThemeConditions(boolean arg10) {
        int v1_1;
        if(Theme.previousTheme != null) {
            return;
        }

        boolean v0 = false;
        if(arg10) {
            if(Theme.switchNightRunnableScheduled) {
                Theme.switchNightRunnableScheduled = false;
                AndroidUtilities.cancelRunOnUIThread(Theme.switchNightBrightnessRunnable);
            }

            if(!Theme.switchDayRunnableScheduled) {
                goto label_15;
            }

            Theme.switchDayRunnableScheduled = false;
            AndroidUtilities.cancelRunOnUIThread(Theme.switchDayBrightnessRunnable);
        }

    label_15:
        int v2 = 2;
        if(Theme.selectedAutoNightType != v2) {
            if(Theme.switchNightRunnableScheduled) {
                Theme.switchNightRunnableScheduled = false;
                AndroidUtilities.cancelRunOnUIThread(Theme.switchNightBrightnessRunnable);
            }

            if(Theme.switchDayRunnableScheduled) {
                Theme.switchDayRunnableScheduled = false;
                AndroidUtilities.cancelRunOnUIThread(Theme.switchDayBrightnessRunnable);
            }

            if(!Theme.lightSensorRegistered) {
                goto label_41;
            }

            Theme.lastBrightnessValue = 1f;
            Theme.sensorManager.unregisterListener(Theme.ambientSensorListener, Theme.lightSensor);
            Theme.lightSensorRegistered = false;
            if(!BuildVars.LOGS_ENABLED) {
                goto label_41;
            }

            FileLog.d("light sensor unregistered");
        }

    label_41:
        int v3 = 5;
        if(Theme.selectedAutoNightType == 1) {
            Calendar v1 = Calendar.getInstance();
            v1.setTimeInMillis(System.currentTimeMillis());
            int v5 = v1.get(11) * 60 + v1.get(12);
            if(Theme.autoNightScheduleByLocation) {
                v1_1 = v1.get(v3);
                if(Theme.autoNightLastSunCheckDay != v1_1) {
                    double v8 = 10000;
                    if(Theme.autoNightLocationLatitude != v8 && Theme.autoNightLocationLongitude != v8) {
                        int[] v3_1 = SunDate.calculateSunriseSunset(Theme.autoNightLocationLatitude, Theme.autoNightLocationLongitude);
                        Theme.autoNightSunriseTime = v3_1[0];
                        Theme.autoNightSunsetTime = v3_1[1];
                        Theme.autoNightLastSunCheckDay = v1_1;
                        Theme.saveAutoNightThemeConfig();
                    }
                }

                v1_1 = Theme.autoNightSunsetTime;
                v3 = Theme.autoNightSunriseTime;
            }
            else {
                v1_1 = Theme.autoNightDayStartTime;
                v3 = Theme.autoNightDayEndTime;
            }

            if(v1_1 < v3) {
                if(v1_1 > v5) {
                    goto label_125;
                }

                if(v5 > v3) {
                    goto label_125;
                }

                goto label_118;
            }

            if(v1_1 <= v5 && v5 <= 1440) {
                goto label_118;
            }

            if(v5 < 0) {
                goto label_125;
            }

            if(v5 > v3) {
                goto label_125;
            }

            goto label_118;
        }
        else {
            if(Theme.selectedAutoNightType == v2) {
                if(Theme.lightSensor == null) {
                    Theme.sensorManager = ApplicationLoader.applicationContext.getSystemService("sensor");
                    Theme.lightSensor = Theme.sensorManager.getDefaultSensor(v3);
                }

                if(!Theme.lightSensorRegistered && Theme.lightSensor != null) {
                    Theme.sensorManager.registerListener(Theme.ambientSensorListener, Theme.lightSensor, 500000);
                    Theme.lightSensorRegistered = true;
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("light sensor registered");
                    }
                }

                if(Theme.lastBrightnessValue <= Theme.autoNightBrighnessThreshold) {
                    if(Theme.switchNightRunnableScheduled) {
                        goto label_127;
                    }

                label_118:
                    v1_1 = 2;
                    goto label_128;
                }

                if(Theme.switchDayRunnableScheduled) {
                    goto label_127;
                }

                goto label_125;
            }
            else {
                if(Theme.selectedAutoNightType != 0) {
                    goto label_127;
                }

            label_125:
                v1_1 = 1;
                goto label_128;
            }

        label_127:
            v1_1 = 0;
        }

    label_128:
        if(v1_1 != 0) {
            if(v1_1 == v2) {
                v0 = true;
            }

            Theme.applyDayNightThemeMaybe(v0);
        }

        if(arg10) {
            Theme.lastThemeSwitchTime = 0;
        }
    }

    public static void createChatResources(Context arg16, boolean arg17) {
        int v1_1;
        Object v1 = Theme.sync;
        __monitor_enter(v1);
        try {
            if(Theme.chat_msgTextPaint == null) {
                Theme.chat_msgTextPaint = new TextPaint(1);
                Theme.chat_msgTextPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                Theme.chat_msgGameTextPaint = new TextPaint(1);
                Theme.chat_msgGameTextPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                Theme.chat_msgTextPaintOneEmoji = new TextPaint(1);
                Theme.chat_msgTextPaintOneEmoji.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                Theme.chat_msgTextPaintTwoEmoji = new TextPaint(1);
                Theme.chat_msgTextPaintTwoEmoji.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                Theme.chat_msgTextPaintThreeEmoji = new TextPaint(1);
                Theme.chat_msgTextPaintThreeEmoji.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                Theme.chat_msgBotButtonPaint = new TextPaint(1);
                Theme.chat_msgBotButtonPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            }

            __monitor_exit(v1);
            v1_1 = 2;
            if(arg17) {
                goto label_1069;
            }
        }
        catch(Throwable v0) {
            try {
            label_1248:
                __monitor_exit(v1);
            }
            catch(Throwable v0) {
                goto label_1248;
            }

            throw v0;
        }

        if(Theme.chat_msgInDrawable == null) {
            Theme.chat_infoPaint = new TextPaint(1);
            Theme.chat_infoPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_docNamePaint = new TextPaint(1);
            Theme.chat_docNamePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_docBackPaint = new Paint(1);
            Theme.chat_docBackPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_deleteProgressPaint = new Paint(1);
            Theme.chat_deleteProgressPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_botProgressPaint = new Paint(1);
            Theme.chat_botProgressPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_botProgressPaint.setStrokeCap(Paint$Cap.ROUND);
            Theme.chat_botProgressPaint.setStyle(Paint$Style.STROKE);
            Theme.chat_locationTitlePaint = new TextPaint(1);
            Theme.chat_locationTitlePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_locationAddressPaint = new TextPaint(1);
            Theme.chat_locationAddressPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_urlPaint = new Paint();
            Theme.chat_textSearchSelectionPaint = new Paint();
            Theme.chat_radialProgressPaint = new Paint(1);
            Theme.chat_radialProgressPaint.setStrokeCap(Paint$Cap.ROUND);
            Theme.chat_radialProgressPaint.setStyle(Paint$Style.STROKE);
            Theme.chat_radialProgressPaint.setColor(-1610612737);
            Theme.chat_radialProgress2Paint = new Paint(1);
            Theme.chat_radialProgress2Paint.setStrokeCap(Paint$Cap.ROUND);
            Theme.chat_radialProgress2Paint.setStyle(Paint$Style.STROKE);
            Theme.chat_audioTimePaint = new TextPaint(1);
            Theme.chat_livePaint = new TextPaint(1);
            Theme.chat_livePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_audioTitlePaint = new TextPaint(1);
            Theme.chat_audioTitlePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_audioPerformerPaint = new TextPaint(1);
            Theme.chat_audioPerformerPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_botButtonPaint = new TextPaint(1);
            Theme.chat_botButtonPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_contactNamePaint = new TextPaint(1);
            Theme.chat_contactNamePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_contactPhonePaint = new TextPaint(1);
            Theme.chat_contactPhonePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_durationPaint = new TextPaint(1);
            Theme.chat_durationPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_gamePaint = new TextPaint(1);
            Theme.chat_gamePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_shipmentPaint = new TextPaint(1);
            Theme.chat_shipmentPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_timePaint = new TextPaint(1);
            Theme.chat_timePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_adminPaint = new TextPaint(1);
            Theme.chat_adminPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_namePaint = new TextPaint(1);
            Theme.chat_namePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_forwardNamePaint = new TextPaint(1);
            Theme.chat_forwardNamePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_replyNamePaint = new TextPaint(1);
            Theme.chat_replyNamePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_replyTextPaint = new TextPaint(1);
            Theme.chat_replyTextPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_instantViewPaint = new TextPaint(1);
            Theme.chat_instantViewPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_instantViewRectPaint = new Paint(1);
            Theme.chat_instantViewRectPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_instantViewRectPaint.setStyle(Paint$Style.STROKE);
            Theme.chat_replyLinePaint = new Paint();
            Theme.chat_msgErrorPaint = new Paint(1);
            Theme.chat_statusPaint = new Paint(1);
            Theme.chat_statusRecordPaint = new Paint(1);
            Theme.chat_statusRecordPaint.setStyle(Paint$Style.STROKE);
            Theme.chat_statusRecordPaint.setStrokeCap(Paint$Cap.ROUND);
            Theme.chat_actionTextPaint = new TextPaint(1);
            Theme.chat_actionTextPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_actionBackgroundPaint = new Paint(1);
            Theme.chat_timeBackgroundPaint = new Paint(1);
            Theme.chat_contextResult_titleTextPaint = new TextPaint(1);
            Theme.chat_contextResult_titleTextPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.chat_contextResult_descriptionTextPaint = new TextPaint(1);
            Theme.chat_composeBackgroundPaint = new Paint();
            Resources v2 = arg16.getResources();
            Theme.chat_msgInDrawable = v2.getDrawable(2131231378).mutate();
            Theme.chat_msgInSelectedDrawable = v2.getDrawable(2131231378).mutate();
            Theme.chat_msgOutDrawable = v2.getDrawable(2131231382).mutate();
            Theme.chat_msgOutSelectedDrawable = v2.getDrawable(2131231382).mutate();
            Theme.chat_msgInMediaDrawable = v2.getDrawable(2131231388).mutate();
            Theme.chat_msgInMediaSelectedDrawable = v2.getDrawable(2131231388).mutate();
            Theme.chat_msgOutMediaDrawable = v2.getDrawable(2131231388).mutate();
            Theme.chat_msgOutMediaSelectedDrawable = v2.getDrawable(2131231388).mutate();
            Theme.chat_msgOutCheckDrawable = v2.getDrawable(2131231373).mutate();
            Theme.chat_msgOutCheckSelectedDrawable = v2.getDrawable(2131231373).mutate();
            Theme.chat_msgMediaCheckDrawable = v2.getDrawable(2131231373).mutate();
            Theme.chat_msgStickerCheckDrawable = v2.getDrawable(2131231373).mutate();
            Theme.chat_msgOutHalfCheckDrawable = v2.getDrawable(2131231377).mutate();
            Theme.chat_msgOutHalfCheckSelectedDrawable = v2.getDrawable(2131231377).mutate();
            Theme.chat_msgMediaHalfCheckDrawable = v2.getDrawable(2131231377).mutate();
            Theme.chat_msgStickerHalfCheckDrawable = v2.getDrawable(2131231377).mutate();
            Theme.chat_msgOutClockDrawable = v2.getDrawable(2131231374).mutate();
            Theme.chat_msgOutSelectedClockDrawable = v2.getDrawable(2131231374).mutate();
            Theme.chat_msgInClockDrawable = v2.getDrawable(2131231374).mutate();
            Theme.chat_msgInSelectedClockDrawable = v2.getDrawable(2131231374).mutate();
            Theme.chat_msgMediaClockDrawable = v2.getDrawable(2131231374).mutate();
            Theme.chat_msgStickerClockDrawable = v2.getDrawable(2131231374).mutate();
            Theme.chat_msgInViewsDrawable = v2.getDrawable(2131231396).mutate();
            Theme.chat_msgInViewsSelectedDrawable = v2.getDrawable(2131231396).mutate();
            Theme.chat_msgOutViewsDrawable = v2.getDrawable(2131231396).mutate();
            Theme.chat_msgOutViewsSelectedDrawable = v2.getDrawable(2131231396).mutate();
            Theme.chat_msgMediaViewsDrawable = v2.getDrawable(2131231396).mutate();
            Theme.chat_msgStickerViewsDrawable = v2.getDrawable(2131231396).mutate();
            Theme.chat_msgInMenuDrawable = v2.getDrawable(2131231372).mutate();
            Theme.chat_msgInMenuSelectedDrawable = v2.getDrawable(2131231372).mutate();
            Theme.chat_msgOutMenuDrawable = v2.getDrawable(2131231372).mutate();
            Theme.chat_msgOutMenuSelectedDrawable = v2.getDrawable(2131231372).mutate();
            Theme.chat_msgMediaMenuDrawable = v2.getDrawable(2131231697);
            Theme.chat_msgInInstantDrawable = v2.getDrawable(2131231380).mutate();
            Theme.chat_msgOutInstantDrawable = v2.getDrawable(2131231380).mutate();
            Theme.chat_msgErrorDrawable = v2.getDrawable(2131231397);
            Theme.chat_muteIconDrawable = v2.getDrawable(2131231311).mutate();
            Theme.chat_lockIconDrawable = v2.getDrawable(2131231184);
            Theme.chat_msgBroadcastDrawable = v2.getDrawable(2131230959).mutate();
            Theme.chat_msgBroadcastMediaDrawable = v2.getDrawable(2131230959).mutate();
            Theme.chat_msgInCallDrawable = v2.getDrawable(2131231144).mutate();
            Theme.chat_msgInCallSelectedDrawable = v2.getDrawable(2131231144).mutate();
            Theme.chat_msgOutCallDrawable = v2.getDrawable(2131231144).mutate();
            Theme.chat_msgOutCallSelectedDrawable = v2.getDrawable(2131231144).mutate();
            Theme.chat_msgCallUpRedDrawable = v2.getDrawable(2131231138).mutate();
            Theme.chat_msgCallUpGreenDrawable = v2.getDrawable(2131231138).mutate();
            Theme.chat_msgCallDownRedDrawable = v2.getDrawable(2131231142).mutate();
            Theme.chat_msgCallDownGreenDrawable = v2.getDrawable(2131231142).mutate();
            Theme.chat_msgAvatarLiveLocationDrawable = v2.getDrawable(2131231319).mutate();
            Theme.chat_inlineResultFile = v2.getDrawable(2131230948);
            Theme.chat_inlineResultAudio = v2.getDrawable(2131230954);
            Theme.chat_inlineResultLocation = v2.getDrawable(2131230953);
            Theme.chat_redLocationIcon = v2.getDrawable(2131231335).mutate();
            Theme.chat_msgInShadowDrawable = v2.getDrawable(2131231379);
            Theme.chat_msgOutShadowDrawable = v2.getDrawable(2131231383);
            Theme.chat_msgInMediaShadowDrawable = v2.getDrawable(2131231389);
            Theme.chat_msgOutMediaShadowDrawable = v2.getDrawable(2131231389);
            Theme.chat_botLinkDrawalbe = v2.getDrawable(2131230952);
            Theme.chat_botInlineDrawable = v2.getDrawable(2131230951);
            Theme.chat_systemDrawable = v2.getDrawable(2131231591);
            Theme.chat_contextResult_shadowUnderSwitchDrawable = v2.getDrawable(2131231098).mutate();
            Theme.chat_attachButtonDrawables[0] = new AttachCameraDrawable();
            Theme.chat_attachButtonDrawables[1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(54f), 2131230901);
            Theme.chat_attachButtonDrawables[v1_1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(54f), 2131230904);
            Theme.chat_attachButtonDrawables[3] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(54f), 2131230897);
            Theme.chat_attachButtonDrawables[4] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(54f), 2131230900);
            Theme.chat_attachButtonDrawables[5] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(54f), 2131230899);
            Theme.chat_attachButtonDrawables[6] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(54f), 2131230902);
            Theme.chat_attachButtonDrawables[7] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(54f), 2131230898);
            Theme.chat_attachButtonDrawables[8] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(54f), 2131230903);
            Theme.chat_cornerOuter[0] = v2.getDrawable(2131231031);
            Theme.chat_cornerOuter[1] = v2.getDrawable(2131231032);
            Theme.chat_cornerOuter[v1_1] = v2.getDrawable(2131231030);
            Theme.chat_cornerOuter[3] = v2.getDrawable(2131231029);
            Theme.chat_cornerInner[0] = v2.getDrawable(2131231028);
            Theme.chat_cornerInner[1] = v2.getDrawable(2131231027);
            Theme.chat_cornerInner[v1_1] = v2.getDrawable(2131231026);
            Theme.chat_cornerInner[3] = v2.getDrawable(2131231025);
            Theme.chat_shareDrawable = v2.getDrawable(2131231558);
            Theme.chat_shareIconDrawable = v2.getDrawable(2131231556);
            Theme.chat_replyIconDrawable = v2.getDrawable(2131231060);
            Theme.chat_goIconDrawable = v2.getDrawable(2131231364);
            Theme.favoriteDrawable = v2.getDrawable(2131231558);
            Theme.favoriteOnIconDrawable = v2.getDrawable(2131230995);
            Theme.favoriteOffIconDrawable = v2.getDrawable(2131230995);
            Theme.chat_ivStatesDrawable[0][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40f), 2131231395, 1);
            Theme.chat_ivStatesDrawable[0][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40f), 2131231395, 1);
            Theme.chat_ivStatesDrawable[1][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40f), 2131231394, 1);
            Theme.chat_ivStatesDrawable[1][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40f), 2131231394, 1);
            Theme.chat_ivStatesDrawable[v1_1][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40f), 2131231393, 1);
            Theme.chat_ivStatesDrawable[v1_1][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40f), 2131231393, 1);
            Theme.chat_ivStatesDrawable[3][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40f), 2131231390, v1_1);
            Theme.chat_ivStatesDrawable[3][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(40f), 2131231390, v1_1);
            Theme.chat_fileMiniStatesDrawable[0][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131230905);
            Theme.chat_fileMiniStatesDrawable[0][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131230905);
            Theme.chat_fileMiniStatesDrawable[1][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131230906);
            Theme.chat_fileMiniStatesDrawable[1][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131230906);
            Theme.chat_fileMiniStatesDrawable[v1_1][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131230905);
            Theme.chat_fileMiniStatesDrawable[v1_1][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131230905);
            Theme.chat_fileMiniStatesDrawable[3][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131230906);
            Theme.chat_fileMiniStatesDrawable[3][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131230906);
            Theme.chat_fileMiniStatesDrawable[4][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131231704);
            Theme.chat_fileMiniStatesDrawable[4][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131231704);
            Theme.chat_fileMiniStatesDrawable[5][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131231705);
            Theme.chat_fileMiniStatesDrawable[5][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(22f), 2131231705);
            Theme.chat_fileStatesDrawable[0][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231395);
            Theme.chat_fileStatesDrawable[0][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231395);
            Theme.chat_fileStatesDrawable[1][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231394);
            Theme.chat_fileStatesDrawable[1][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231394);
            Theme.chat_fileStatesDrawable[v1_1][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231393);
            Theme.chat_fileStatesDrawable[v1_1][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231393);
            Theme.chat_fileStatesDrawable[3][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231391);
            Theme.chat_fileStatesDrawable[3][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231391);
            Theme.chat_fileStatesDrawable[4][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231390);
            Theme.chat_fileStatesDrawable[4][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231390);
            Theme.chat_fileStatesDrawable[5][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231395);
            Theme.chat_fileStatesDrawable[5][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231395);
            Theme.chat_fileStatesDrawable[6][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231394);
            Theme.chat_fileStatesDrawable[6][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231394);
            Theme.chat_fileStatesDrawable[7][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231393);
            Theme.chat_fileStatesDrawable[7][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231393);
            Theme.chat_fileStatesDrawable[8][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231391);
            Theme.chat_fileStatesDrawable[8][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231391);
            Theme.chat_fileStatesDrawable[9][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231390);
            Theme.chat_fileStatesDrawable[9][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231390);
            Theme.chat_photoStatesDrawables[0][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231393);
            Theme.chat_photoStatesDrawables[0][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231393);
            Theme.chat_photoStatesDrawables[1][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231390);
            Theme.chat_photoStatesDrawables[1][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231390);
            Theme.chat_photoStatesDrawables[v1_1][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231392);
            Theme.chat_photoStatesDrawables[v1_1][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231392);
            Theme.chat_photoStatesDrawables[3][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231395);
            Theme.chat_photoStatesDrawables[3][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231395);
            Drawable[] v4 = Theme.chat_photoStatesDrawables[4];
            Drawable[] v8 = Theme.chat_photoStatesDrawables[4];
            Drawable v9 = v2.getDrawable(2131230964);
            v8[1] = v9;
            v4[0] = v9;
            v4 = Theme.chat_photoStatesDrawables[5];
            v8 = Theme.chat_photoStatesDrawables[5];
            v9 = v2.getDrawable(2131230991);
            v8[1] = v9;
            v4[0] = v9;
            v4 = Theme.chat_photoStatesDrawables[6];
            v8 = Theme.chat_photoStatesDrawables[6];
            v9 = v2.getDrawable(2131231472);
            v8[1] = v9;
            v4[0] = v9;
            Theme.chat_photoStatesDrawables[7][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231393);
            Theme.chat_photoStatesDrawables[7][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231393);
            Theme.chat_photoStatesDrawables[8][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231390);
            Theme.chat_photoStatesDrawables[8][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231390);
            Theme.chat_photoStatesDrawables[9][0] = v2.getDrawable(2131231047).mutate();
            Theme.chat_photoStatesDrawables[9][1] = v2.getDrawable(2131231047).mutate();
            Theme.chat_photoStatesDrawables[10][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231393);
            Theme.chat_photoStatesDrawables[10][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231393);
            Theme.chat_photoStatesDrawables[11][0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231390);
            Theme.chat_photoStatesDrawables[11][1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), 2131231390);
            Theme.chat_photoStatesDrawables[12][0] = v2.getDrawable(2131231047).mutate();
            Theme.chat_photoStatesDrawables[12][1] = v2.getDrawable(2131231047).mutate();
            Theme.chat_contactDrawable[0] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231375);
            Theme.chat_contactDrawable[1] = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(44f), 2131231375);
            Theme.chat_locationDrawable[0] = Theme.createRoundRectDrawableWithIcon(AndroidUtilities.dp(2f), 2131231381);
            Theme.chat_locationDrawable[1] = Theme.createRoundRectDrawableWithIcon(AndroidUtilities.dp(2f), 2131231381);
            Theme.chat_composeShadowDrawable = arg16.getResources().getDrawable(2131231023);
            try {
                int v2_1 = AndroidUtilities.roundMessageSize + AndroidUtilities.dp(6f);
                Bitmap v4_1 = Bitmap.createBitmap(v2_1, v2_1, Bitmap$Config.ARGB_8888);
                Canvas v5 = new Canvas(v4_1);
                Paint v6 = new Paint(1);
                v6.setShadowLayer(((float)AndroidUtilities.dp(4f)), 0f, 0f, 1593835520);
                v5.drawCircle(((float)(v2_1 / 2)), ((float)(v2_1 / v1_1)), ((float)(AndroidUtilities.roundMessageSize / v1_1 - AndroidUtilities.dp(1f))), v6);
                Bitmap v2_2 = null;
                try {
                    v5.setBitmap(v2_2);
                    goto label_1065;
                }
                catch(Exception ) {
                label_1065:
                    Theme.chat_roundVideoShadow = new BitmapDrawable(v4_1);
                    goto label_1068;
                }
            }
            catch(Throwable ) {
            label_1068:
                Theme.applyChatTheme(arg17);
            }
        }

    label_1069:
        Theme.chat_msgTextPaintOneEmoji.setTextSize(((float)AndroidUtilities.dp(28f)));
        Theme.chat_msgTextPaintTwoEmoji.setTextSize(((float)AndroidUtilities.dp(24f)));
        Theme.chat_msgTextPaintThreeEmoji.setTextSize(((float)AndroidUtilities.dp(20f)));
        Theme.chat_msgTextPaint.setTextSize(((float)AndroidUtilities.dp(((float)SharedConfig.fontSize))));
        Theme.chat_msgGameTextPaint.setTextSize(((float)AndroidUtilities.dp(14f)));
        float v3 = 15f;
        Theme.chat_msgBotButtonPaint.setTextSize(((float)AndroidUtilities.dp(v3)));
        if(!arg17 && Theme.chat_botProgressPaint != null) {
            Theme.chat_botProgressPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
            Theme.chat_infoPaint.setTextSize(((float)AndroidUtilities.dp(12f)));
            Theme.chat_docNamePaint.setTextSize(((float)AndroidUtilities.dp(v3)));
            Theme.chat_locationTitlePaint.setTextSize(((float)AndroidUtilities.dp(v3)));
            Theme.chat_locationAddressPaint.setTextSize(((float)AndroidUtilities.dp(13f)));
            Theme.chat_audioTimePaint.setTextSize(((float)AndroidUtilities.dp(12f)));
            Theme.chat_livePaint.setTextSize(((float)AndroidUtilities.dp(12f)));
            Theme.chat_audioTitlePaint.setTextSize(((float)AndroidUtilities.dp(16f)));
            Theme.chat_audioPerformerPaint.setTextSize(((float)AndroidUtilities.dp(v3)));
            Theme.chat_botButtonPaint.setTextSize(((float)AndroidUtilities.dp(v3)));
            Theme.chat_contactNamePaint.setTextSize(((float)AndroidUtilities.dp(v3)));
            Theme.chat_contactPhonePaint.setTextSize(((float)AndroidUtilities.dp(13f)));
            Theme.chat_durationPaint.setTextSize(((float)AndroidUtilities.dp(12f)));
            Theme.chat_timePaint.setTextSize(((float)AndroidUtilities.dp(12f)));
            Theme.chat_adminPaint.setTextSize(((float)AndroidUtilities.dp(13f)));
            Theme.chat_namePaint.setTextSize(((float)AndroidUtilities.dp(14f)));
            Theme.chat_forwardNamePaint.setTextSize(((float)AndroidUtilities.dp(14f)));
            Theme.chat_replyNamePaint.setTextSize(((float)AndroidUtilities.dp(14f)));
            Theme.chat_replyTextPaint.setTextSize(((float)AndroidUtilities.dp(14f)));
            Theme.chat_gamePaint.setTextSize(((float)AndroidUtilities.dp(13f)));
            Theme.chat_shipmentPaint.setTextSize(((float)AndroidUtilities.dp(13f)));
            Theme.chat_instantViewPaint.setTextSize(((float)AndroidUtilities.dp(13f)));
            Theme.chat_instantViewRectPaint.setStrokeWidth(((float)AndroidUtilities.dp(1f)));
            Theme.chat_statusRecordPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
            Theme.chat_actionTextPaint.setTextSize(((float)AndroidUtilities.dp(((float)(Math.max(16, SharedConfig.fontSize) - v1_1)))));
            Theme.chat_contextResult_titleTextPaint.setTextSize(((float)AndroidUtilities.dp(v3)));
            Theme.chat_contextResult_descriptionTextPaint.setTextSize(((float)AndroidUtilities.dp(13f)));
            Theme.chat_radialProgressPaint.setStrokeWidth(((float)AndroidUtilities.dp(3f)));
            Theme.chat_radialProgress2Paint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        }
    }

    public static Drawable createCircleDrawable(int arg1, int arg2) {
        OvalShape v0 = new OvalShape();
        float v1 = ((float)arg1);
        v0.resize(v1, v1);
        ShapeDrawable v1_1 = new ShapeDrawable(((Shape)v0));
        v1_1.getPaint().setColor(arg2);
        return ((Drawable)v1_1);
    }

    public static CombinedDrawable createCircleDrawableWithIcon(int arg1, int arg2) {
        return Theme.createCircleDrawableWithIcon(arg1, arg2, 0);
    }

    public static CombinedDrawable createCircleDrawableWithIcon(int arg1, int arg2, int arg3) {
        Drawable v2 = arg2 != 0 ? ApplicationLoader.applicationContext.getResources().getDrawable(arg2).mutate() : null;
        return Theme.createCircleDrawableWithIcon(arg1, v2, arg3);
    }

    public static CombinedDrawable createCircleDrawableWithIcon(int arg3, Drawable arg4, int arg5) {
        OvalShape v0 = new OvalShape();
        float v1 = ((float)arg3);
        v0.resize(v1, v1);
        ShapeDrawable v1_1 = new ShapeDrawable(((Shape)v0));
        Paint v0_1 = v1_1.getPaint();
        v0_1.setColor(-1);
        if(arg5 == 1) {
            v0_1.setStyle(Paint$Style.STROKE);
            v0_1.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        }
        else if(arg5 == 2) {
            v0_1.setAlpha(0);
        }

        CombinedDrawable v5 = new CombinedDrawable(((Drawable)v1_1), arg4);
        v5.setCustomSize(arg3, arg3);
        return v5;
    }

    public static void createCommonResources(Context arg4) {
        if(Theme.dividerPaint == null) {
            Theme.dividerPaint = new Paint();
            Theme.dividerPaint.setStrokeWidth(1f);
            Theme.avatar_backgroundPaint = new Paint(1);
            Theme.checkboxSquare_checkPaint = new Paint(1);
            Theme.checkboxSquare_checkPaint.setStyle(Paint$Style.STROKE);
            Theme.checkboxSquare_checkPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
            Theme.checkboxSquare_eraserPaint = new Paint(1);
            Theme.checkboxSquare_eraserPaint.setColor(0);
            Theme.checkboxSquare_eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
            Theme.checkboxSquare_backgroundPaint = new Paint(1);
            Theme.linkSelectionPaint = new Paint();
            Resources v4 = arg4.getResources();
            Theme.avatar_broadcastDrawable = v4.getDrawable(2131230960);
            Theme.avatar_savedDrawable = v4.getDrawable(2131230940);
            Theme.avatar_photoDrawable = v4.getDrawable(2131231470);
            Theme.applyCommonTheme();
        }
    }

    public static void createDialogsResources(Context arg3) {
        Theme.createCommonResources(arg3);
        if(Theme.dialogs_namePaint == null) {
            Resources v3 = arg3.getResources();
            Theme.dialogs_namePaint = new TextPaint(1);
            Theme.dialogs_namePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.dialogs_nameEncryptedPaint = new TextPaint(1);
            Theme.dialogs_nameEncryptedPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.dialogs_messagePaint = new TextPaint(1);
            Theme.dialogs_messagePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.dialogs_messagePrintingPaint = new TextPaint(1);
            Theme.dialogs_messagePrintingPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.dialogs_timePaint = new TextPaint(1);
            Theme.dialogs_timePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.dialogs_countTextPaint = new TextPaint(1);
            Theme.dialogs_countTextPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.dialogs_onlinePaint = new TextPaint(1);
            Theme.dialogs_onlinePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.dialogs_offlinePaint = new TextPaint(1);
            Theme.dialogs_offlinePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            Theme.dialogs_tabletSeletedPaint = new Paint();
            Theme.dialogs_pinnedPaint = new Paint();
            Theme.dialogs_countPaint = new Paint(1);
            Theme.dialogs_countGrayPaint = new Paint(1);
            Theme.dialogs_errorPaint = new Paint(1);
            Theme.dialogs_lockDrawable = v3.getDrawable(2131231313);
            Theme.dialogs_checkDrawable = v3.getDrawable(2131231307);
            Theme.dialogs_halfCheckDrawable = v3.getDrawable(2131231310);
            Theme.dialogs_clockDrawable = v3.getDrawable(2131231374).mutate();
            Theme.dialogs_errorDrawable = v3.getDrawable(2131231316);
            Theme.dialogs_groupDrawable = v3.getDrawable(2131231309);
            Theme.dialogs_broadcastDrawable = v3.getDrawable(2131231306);
            Theme.dialogs_muteDrawable = v3.getDrawable(2131231311).mutate();
            Theme.dialogs_verifiedDrawable = v3.getDrawable(2131231690);
            Theme.dialogs_verifiedCheckDrawable = v3.getDrawable(2131231691);
            Theme.dialogs_mentionDrawable = v3.getDrawable(2131231347);
            Theme.dialogs_botDrawable = v3.getDrawable(2131231305);
            Theme.dialogs_pinnedDrawable = v3.getDrawable(2131231312);
            Theme.moveUpDrawable = v3.getDrawable(2131231507);
            Theme.applyDialogsTheme();
        }

        Theme.dialogs_namePaint.setTextSize(((float)AndroidUtilities.dp(17f)));
        Theme.dialogs_nameEncryptedPaint.setTextSize(((float)AndroidUtilities.dp(17f)));
        Theme.dialogs_messagePaint.setTextSize(((float)AndroidUtilities.dp(16f)));
        Theme.dialogs_messagePrintingPaint.setTextSize(((float)AndroidUtilities.dp(16f)));
        Theme.dialogs_timePaint.setTextSize(((float)AndroidUtilities.dp(13f)));
        Theme.dialogs_countTextPaint.setTextSize(((float)AndroidUtilities.dp(13f)));
        Theme.dialogs_onlinePaint.setTextSize(((float)AndroidUtilities.dp(16f)));
        Theme.dialogs_offlinePaint.setTextSize(((float)AndroidUtilities.dp(16f)));
    }

    public static Drawable createEditTextDrawable(Context arg4, boolean arg5) {
        Resources v4 = arg4.getResources();
        Drawable v0 = v4.getDrawable(2131231538).mutate();
        String v2 = arg5 ? "dialogInputField" : "windowBackgroundWhiteInputField";
        v0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(v2), PorterDuff$Mode.MULTIPLY));
        Drawable v4_1 = v4.getDrawable(2131231539).mutate();
        String v5 = arg5 ? "dialogInputFieldActivated" : "windowBackgroundWhiteInputFieldActivated";
        v4_1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(v5), PorterDuff$Mode.MULTIPLY));
        org.telegram.ui.ActionBar.Theme$4 v5_1 = new StateListDrawable() {
            public boolean selectDrawable(int arg4) {
                Paint v1_1;
                if(Build$VERSION.SDK_INT < 21) {
                    Drawable v0 = Theme.getStateDrawable(((Drawable)this), arg4);
                    ColorFilter v1 = null;
                    if((v0 instanceof BitmapDrawable)) {
                        v1_1 = v0.getPaint();
                        goto label_9;
                    }
                    else if((v0 instanceof NinePatchDrawable)) {
                        v1_1 = v0.getPaint();
                    label_9:
                        v1 = v1_1.getColorFilter();
                    }

                    boolean v4 = super.selectDrawable(arg4);
                    if(v1 != null) {
                        v0.setColorFilter(v1);
                    }

                    return v4;
                }

                return super.selectDrawable(arg4);
            }
        };
        ((StateListDrawable)v5_1).addState(new int[]{16842910, 16842908}, v4_1);
        ((StateListDrawable)v5_1).addState(new int[]{16842908}, v4_1);
        ((StateListDrawable)v5_1).addState(StateSet.WILD_CARD, v0);
        return ((Drawable)v5_1);
    }

    public static Drawable createEmojiIconSelectorDrawable(Context arg3, int arg4, int arg5, int arg6) {
        Resources v3 = arg3.getResources();
        Drawable v0 = v3.getDrawable(arg4).mutate();
        if(arg5 != 0) {
            v0.setColorFilter(new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY));
        }

        Drawable v3_1 = v3.getDrawable(arg4).mutate();
        if(arg6 != 0) {
            v3_1.setColorFilter(new PorterDuffColorFilter(arg6, PorterDuff$Mode.MULTIPLY));
        }

        org.telegram.ui.ActionBar.Theme$3 v4 = new StateListDrawable() {
            public boolean selectDrawable(int arg4) {
                Paint v1_1;
                if(Build$VERSION.SDK_INT < 21) {
                    Drawable v0 = Theme.getStateDrawable(((Drawable)this), arg4);
                    ColorFilter v1 = null;
                    if((v0 instanceof BitmapDrawable)) {
                        v1_1 = v0.getPaint();
                        goto label_9;
                    }
                    else if((v0 instanceof NinePatchDrawable)) {
                        v1_1 = v0.getPaint();
                    label_9:
                        v1 = v1_1.getColorFilter();
                    }

                    boolean v4 = super.selectDrawable(arg4);
                    if(v1 != null) {
                        v0.setColorFilter(v1);
                    }

                    return v4;
                }

                return super.selectDrawable(arg4);
            }
        };
        ((StateListDrawable)v4).setEnterFadeDuration(1);
        ((StateListDrawable)v4).setExitFadeDuration(200);
        ((StateListDrawable)v4).addState(new int[]{16842913}, v3_1);
        ((StateListDrawable)v4).addState(new int[0], v0);
        return ((Drawable)v4);
    }

    public static void createProfileResources(Context arg2) {
        if(Theme.profile_verifiedDrawable == null) {
            Theme.profile_aboutTextPaint = new TextPaint(1);
            Resources v2 = arg2.getResources();
            Theme.profile_verifiedDrawable = v2.getDrawable(2131231690).mutate();
            Theme.profile_verifiedCheckDrawable = v2.getDrawable(2131231691).mutate();
            Theme.applyProfileTheme();
        }

        Theme.profile_aboutTextPaint.setTextSize(((float)AndroidUtilities.dp(16f)));
    }

    public static Drawable createRoundRectDrawable(int arg4, int arg5) {
        float[] v2 = new float[8];
        float v4 = ((float)arg4);
        v2[0] = v4;
        v2[1] = v4;
        v2[2] = v4;
        v2[3] = v4;
        v2[4] = v4;
        v2[5] = v4;
        v2[6] = v4;
        v2[7] = v4;
        ShapeDrawable v0 = new ShapeDrawable(new RoundRectShape(v2, null, null));
        v0.getPaint().setColor(arg5);
        return ((Drawable)v0);
    }

    public static Drawable createRoundRectDrawableWithIcon(int arg4, int arg5) {
        float[] v2 = new float[8];
        float v4 = ((float)arg4);
        v2[0] = v4;
        v2[1] = v4;
        v2[2] = v4;
        v2[3] = v4;
        v2[4] = v4;
        v2[5] = v4;
        v2[6] = v4;
        v2[7] = v4;
        ShapeDrawable v0 = new ShapeDrawable(new RoundRectShape(v2, null, null));
        v0.getPaint().setColor(-1);
        return new CombinedDrawable(((Drawable)v0), ApplicationLoader.applicationContext.getResources().getDrawable(arg5).mutate());
    }

    public static Drawable createSelectorDrawable(int arg1) {
        return Theme.createSelectorDrawable(arg1, 1);
    }

    public static Drawable createSelectorDrawable(int arg6, int arg7) {
        org.telegram.ui.ActionBar.Theme$6 v7;
        if(Build$VERSION.SDK_INT >= 21) {
            int v0 = -1;
            Drawable v3 = null;
            if(arg7 == 1) {
                Theme.maskPaint.setColor(v0);
                v7 = new Drawable() {
                    public void draw(Canvas arg5) {
                        Rect v0 = this.getBounds();
                        arg5.drawCircle(((float)v0.centerX()), ((float)v0.centerY()), ((float)AndroidUtilities.dp(18f)), Theme.maskPaint);
                    }

                    public int getOpacity() {
                        return 0;
                    }

                    public void setAlpha(int arg1) {
                    }

                    public void setColorFilter(ColorFilter arg1) {
                    }
                };
            }
            else if(arg7 == 2) {
                ColorDrawable v7_1 = new ColorDrawable(v0);
            }
            else {
                Drawable v7_2 = v3;
            }

            return new RippleDrawable(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{arg6}), v3, ((Drawable)v7));
        }

        StateListDrawable v7_3 = new StateListDrawable();
        v7_3.addState(new int[]{16842919}, new ColorDrawable(arg6));
        v7_3.addState(new int[]{16842913}, new ColorDrawable(arg6));
        v7_3.addState(StateSet.WILD_CARD, new ColorDrawable(0));
        return ((Drawable)v7_3);
    }

    public static Drawable createSelectorWithBackgroundDrawable(int arg6, int arg7) {
        if(Build$VERSION.SDK_INT >= 21) {
            return new RippleDrawable(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{arg7}), new ColorDrawable(arg6), new ColorDrawable(arg6));
        }

        StateListDrawable v0 = new StateListDrawable();
        v0.addState(new int[]{16842919}, new ColorDrawable(arg7));
        v0.addState(new int[]{16842913}, new ColorDrawable(arg7));
        v0.addState(StateSet.WILD_CARD, new ColorDrawable(arg6));
        return ((Drawable)v0);
    }

    public static Drawable createSimpleSelectorCircleDrawable(int arg5, int arg6, int arg7) {
        OvalShape v0 = new OvalShape();
        float v5 = ((float)arg5);
        v0.resize(v5, v5);
        ShapeDrawable v5_1 = new ShapeDrawable(((Shape)v0));
        v5_1.getPaint().setColor(arg6);
        ShapeDrawable v6 = new ShapeDrawable(((Shape)v0));
        if(Build$VERSION.SDK_INT >= 21) {
            v6.getPaint().setColor(-1);
            return new RippleDrawable(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{arg7}), ((Drawable)v5_1), ((Drawable)v6));
        }

        v6.getPaint().setColor(arg7);
        StateListDrawable v7 = new StateListDrawable();
        v7.addState(new int[]{16842919}, ((Drawable)v6));
        v7.addState(new int[]{16842908}, ((Drawable)v6));
        v7.addState(StateSet.WILD_CARD, ((Drawable)v5_1));
        return ((Drawable)v7);
    }

    public static Drawable createSimpleSelectorDrawable(Context arg3, int arg4, int arg5, int arg6) {
        Resources v3 = arg3.getResources();
        Drawable v0 = v3.getDrawable(arg4).mutate();
        if(arg5 != 0) {
            v0.setColorFilter(new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY));
        }

        Drawable v3_1 = v3.getDrawable(arg4).mutate();
        if(arg6 != 0) {
            v3_1.setColorFilter(new PorterDuffColorFilter(arg6, PorterDuff$Mode.MULTIPLY));
        }

        org.telegram.ui.ActionBar.Theme$5 v4 = new StateListDrawable() {
            public boolean selectDrawable(int arg4) {
                Paint v1_1;
                if(Build$VERSION.SDK_INT < 21) {
                    Drawable v0 = Theme.getStateDrawable(((Drawable)this), arg4);
                    ColorFilter v1 = null;
                    if((v0 instanceof BitmapDrawable)) {
                        v1_1 = v0.getPaint();
                        goto label_9;
                    }
                    else if((v0 instanceof NinePatchDrawable)) {
                        v1_1 = v0.getPaint();
                    label_9:
                        v1 = v1_1.getColorFilter();
                    }

                    boolean v4 = super.selectDrawable(arg4);
                    if(v1 != null) {
                        v0.setColorFilter(v1);
                    }

                    return v4;
                }

                return super.selectDrawable(arg4);
            }
        };
        ((StateListDrawable)v4).addState(new int[]{16842919}, v3_1);
        ((StateListDrawable)v4).addState(new int[]{16842913}, v3_1);
        ((StateListDrawable)v4).addState(StateSet.WILD_CARD, v0);
        return ((Drawable)v4);
    }

    public static Drawable createSimpleSelectorRoundRectDrawable(int arg13, int arg14, int arg15) {
        float[] v3 = new float[8];
        float v13 = ((float)arg13);
        v3[0] = v13;
        v3[1] = v13;
        v3[2] = v13;
        v3[3] = v13;
        v3[4] = v13;
        v3[5] = v13;
        v3[6] = v13;
        v3[7] = v13;
        ShapeDrawable v0 = new ShapeDrawable(new RoundRectShape(v3, null, null));
        v0.getPaint().setColor(arg14);
        ShapeDrawable v14 = new ShapeDrawable(new RoundRectShape(new float[]{v13, v13, v13, v13, v13, v13, v13, v13}, null, null));
        v14.getPaint().setColor(arg15);
        StateListDrawable v13_1 = new StateListDrawable();
        v13_1.addState(new int[]{16842919}, ((Drawable)v14));
        v13_1.addState(new int[]{16842913}, ((Drawable)v14));
        v13_1.addState(StateSet.WILD_CARD, ((Drawable)v0));
        return ((Drawable)v13_1);
    }

    public static boolean deleteTheme(ThemeInfo arg3) {
        boolean v1 = false;
        if(arg3.pathToFile == null) {
            return 0;
        }

        if(Theme.currentTheme == arg3) {
            Theme.applyTheme(Theme.defaultTheme, true, false, false);
            v1 = true;
        }

        Theme.otherThemes.remove(arg3);
        Theme.themesDict.remove(arg3.name);
        Theme.themes.remove(arg3);
        new File(arg3.pathToFile).delete();
        Theme.saveOtherThemes();
        return v1;
    }

    public static void destroyResources() {
        int v0;
        for(v0 = 0; v0 < Theme.chat_attachButtonDrawables.length; ++v0) {
            if(Theme.chat_attachButtonDrawables[v0] != null) {
                Theme.chat_attachButtonDrawables[v0].setCallback(null);
            }
        }
    }

    public static File getAssetFile(String arg7) {
        InputStream v7_1;
        long v4;
        File v0 = new File(ApplicationLoader.getFilesDirFixed(), arg7);
        long v1 = 0;
        try {
            InputStream v3_1 = ApplicationLoader.applicationContext.getAssets().open(arg7);
            v4 = ((long)v3_1.available());
            v3_1.close();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
            v4 = v1;
        }

        if(v0.exists()) {
            if(v4 == v1) {
            }
            else if(v0.length() != v4) {
                goto label_19;
            }

            return v0;
        }

    label_19:
        InputStream v1_1 = null;
        try {
            v7_1 = ApplicationLoader.applicationContext.getAssets().open(arg7);
            goto label_23;
        }
        catch(Throwable v0_1) {
        }
        catch(Exception v7) {
            goto label_38;
            try {
            label_23:
                AndroidUtilities.copyFile(v7_1, v0);
                if(v7_1 == null) {
                    return v0;
                }

                goto label_25;
            }
            catch(Throwable v0_1) {
                v1_1 = v7_1;
            }
            catch(Exception v1_2) {
                Exception v6 = v1_2;
                v1_1 = v7_1;
                v7 = v6;
                try {
                label_38:
                    FileLog.e(((Throwable)v7));
                    if(v1_1 == null) {
                        return v0;
                    }
                }
                catch(Throwable v0_1) {
                    goto label_42;
                }

                try {
                    v1_1.close();
                }
                catch(Exception ) {
                }

                return v0;
            }
        }

    label_42:
        if(v1_1 != null) {
            try {
                v1_1.close();
                goto label_44;
            }
            catch(Exception ) {
            label_44:
                throw v0_1;
            }
        }

        goto label_44;
        try {
        label_25:
            v7_1.close();
            return v0;
        }
        catch(Exception ) {
            return v0;
        }
    }

    private static long getAutoNightSwitchThemeDelay() {
        long v2 = 12000;
        if(Math.abs(Theme.lastThemeSwitchTime - SystemClock.elapsedRealtime()) >= v2) {
            return 1800;
        }

        return v2;
    }

    public static Drawable getCachedWallpaper() {
        Object v0 = Theme.wallpaperSync;
        __monitor_enter(v0);
        try {
            if(Theme.themedWallpaper != null) {
                __monitor_exit(v0);
                return Theme.themedWallpaper;
            }

            __monitor_exit(v0);
            return Theme.wallpaper;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    public static int getColor(String arg1) {
        return Theme.getColor(arg1, null);
    }

    public static int getColor(String arg2, boolean[] arg3) {
        Object v0 = Theme.currentColors.get(arg2);
        if(v0 == null) {
            Object v1 = Theme.fallbackKeys.get(arg2);
            if(v1 != null) {
                v0 = Theme.currentColors.get(v1);
            }

            if(v0 != null) {
                goto label_25;
            }

            if(arg3 != null) {
                arg3[0] = true;
            }

            if(arg2.equals("chat_serviceBackground")) {
                return Theme.serviceMessageColor;
            }

            if(arg2.equals("chat_serviceBackgroundSelected")) {
                return Theme.serviceSelectedMessageColor;
            }

            return Theme.getDefaultColor(arg2);
        }

    label_25:
        return ((Integer)v0).intValue();
    }

    public static Integer getColorOrNull(String arg2) {
        Object v0 = Theme.currentColors.get(arg2);
        if(v0 == null) {
            if(Theme.fallbackKeys.get(arg2) != null) {
                v0 = Theme.currentColors.get(arg2);
            }

            if(v0 != null) {
                goto label_12;
            }

            v0 = Theme.defaultColors.get(arg2);
        }

    label_12:
        return ((Integer)v0);
    }

    public static Drawable getCurrentHolidayDrawable() {
        if(System.currentTimeMillis() - Theme.lastHolidayCheckTime >= 60000) {
            Theme.lastHolidayCheckTime = System.currentTimeMillis();
            Calendar v0 = Calendar.getInstance();
            v0.setTimeInMillis(System.currentTimeMillis());
            int v1 = v0.get(2);
            int v2 = v0.get(5);
            int v3 = v0.get(12);
            int v4 = 11;
            int v0_1 = v0.get(v4);
            Theme.canStartHolidayAnimation = v1 != 0 || v2 != 1 || v3 > 10 || v0_1 != 0 ? false : true;
            if(Theme.dialogs_holidayDrawable != null) {
                goto label_51;
            }

            if(v1 == v4) {
                v3 = 31;
                v0_1 = BuildVars.DEBUG_PRIVATE_VERSION ? 29 : 31;
                if(v2 < v0_1) {
                    goto label_39;
                }

                if(v2 <= v3) {
                    goto label_41;
                }

                goto label_39;
            }
            else {
            label_39:
                if(v1 != 0) {
                    goto label_51;
                }

                if(v2 != 1) {
                    goto label_51;
                }
            }

        label_41:
            Theme.dialogs_holidayDrawable = ApplicationLoader.applicationContext.getResources().getDrawable(2131231407);
            Theme.dialogs_holidayDrawableOffsetX = -AndroidUtilities.dp(3f);
            Theme.dialogs_holidayDrawableOffsetY = 0;
        }

    label_51:
        return Theme.dialogs_holidayDrawable;
    }

    public static int getCurrentHolidayDrawableXOffset() {
        return Theme.dialogs_holidayDrawableOffsetX;
    }

    public static int getCurrentHolidayDrawableYOffset() {
        return Theme.dialogs_holidayDrawableOffsetY;
    }

    public static ThemeInfo getCurrentNightTheme() {
        return Theme.currentNightTheme;
    }

    public static String getCurrentNightThemeName() {
        if(Theme.currentNightTheme == null) {
            return "";
        }

        String v0 = Theme.currentNightTheme.getName();
        if(v0.toLowerCase().endsWith(".attheme")) {
            v0 = v0.substring(0, v0.lastIndexOf(46));
        }

        return v0;
    }

    public static ThemeInfo getCurrentTheme() {
        ThemeInfo v0 = Theme.currentDayTheme != null ? Theme.currentDayTheme : Theme.defaultTheme;
        return v0;
    }

    public static String getCurrentThemeName() {
        String v0 = Theme.currentDayTheme.getName();
        if(v0.toLowerCase().endsWith(".attheme")) {
            v0 = v0.substring(0, v0.lastIndexOf(46));
        }

        return v0;
    }

    public static int getDefaultColor(String arg1) {
        Object v0 = Theme.defaultColors.get(arg1);
        if(v0 == null) {
            if(arg1.equals("chats_menuTopShadow")) {
                return 0;
            }

            return -65536;
        }

        return ((Integer)v0).intValue();
    }

    public static HashMap getDefaultColors() {
        return Theme.defaultColors;
    }

    public static Drawable getRoundRectSelectorDrawable() {
        float v1 = 3f;
        if(Build$VERSION.SDK_INT >= 21) {
            return new RippleDrawable(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{Theme.getColor("dialogButtonSelector")}), null, Theme.createRoundRectDrawable(AndroidUtilities.dp(v1), -1));
        }

        StateListDrawable v0 = new StateListDrawable();
        v0.addState(new int[]{16842919}, Theme.createRoundRectDrawable(AndroidUtilities.dp(v1), Theme.getColor("dialogButtonSelector")));
        v0.addState(new int[]{16842913}, Theme.createRoundRectDrawable(AndroidUtilities.dp(v1), Theme.getColor("dialogButtonSelector")));
        v0.addState(StateSet.WILD_CARD, new ColorDrawable(0));
        return ((Drawable)v0);
    }

    public static int getSelectedColor() {
        return Theme.selectedColor;
    }

    public static Drawable getSelectorDrawable(boolean arg5) {
        if(arg5) {
            if(Build$VERSION.SDK_INT >= 21) {
                return new RippleDrawable(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{Theme.getColor("listSelectorSDK21")}), new ColorDrawable(Theme.getColor("windowBackgroundWhite")), new ColorDrawable(-1));
            }

            int v5 = Theme.getColor("listSelectorSDK21");
            StateListDrawable v0 = new StateListDrawable();
            v0.addState(new int[]{16842919}, new ColorDrawable(v5));
            v0.addState(new int[]{16842913}, new ColorDrawable(v5));
            v0.addState(StateSet.WILD_CARD, new ColorDrawable(Theme.getColor("windowBackgroundWhite")));
            return ((Drawable)v0);
        }

        return Theme.createSelectorDrawable(Theme.getColor("listSelectorSDK21"), 2);
    }

    public static int getServiceMessageColor() {
        Object v0 = Theme.currentColors.get("chat_serviceBackground");
        int v0_1 = v0 == null ? Theme.serviceMessageColor : ((Integer)v0).intValue();
        return v0_1;
    }

    @SuppressLint(value={"PrivateApi"}) private static Drawable getStateDrawable(Drawable arg6, int arg7) {
        if(Theme.StateListDrawable_getStateDrawableMethod == null) {
            try {
                Theme.StateListDrawable_getStateDrawableMethod = StateListDrawable.class.getDeclaredMethod("getStateDrawable", Integer.TYPE);
                goto label_11;
            }
            catch(Throwable ) {
            label_11:
                Drawable v3 = null;
                if(Theme.StateListDrawable_getStateDrawableMethod == null) {
                    return v3;
                }

                try {
                    return Theme.StateListDrawable_getStateDrawableMethod.invoke(arg6, Integer.valueOf(arg7));
                }
                catch(Exception ) {
                    return v3;
                }
            }
        }

        goto label_11;
    }

    private static HashMap getThemeFileValues(File arg14, String arg15) {
        Integer v9_2;
        int v11_1;
        int v8;
        int v3;
        int v14_1;
        FileInputStream v15;
        byte[] v1_1;
        HashMap v0 = new HashMap();
        int v1 = 1024;
        FileInputStream v2 = null;
        try {
            v1_1 = new byte[v1];
            if(arg15 != null) {
                arg14 = Theme.getAssetFile(arg15);
            }

            v15 = new FileInputStream(arg14);
            v14_1 = -1;
            goto label_10;
        }
        catch(Throwable v14) {
        }
        catch(Throwable v14) {
            goto label_81;
            try {
            label_10:
                Theme.themedWallpaperFileOffset = v14_1;
                v3 = 0;
                int v4 = 0;
                while(true) {
                label_14:
                    int v5 = v15.read(v1_1);
                    if(v5 == v14_1) {
                        goto label_70;
                    }

                    v8 = v3;
                    int v6 = 0;
                    int v7 = 0;
                    while(v6 < v5) {
                        if(v1_1[v6] == 10) {
                            int v10 = v6 - v7 + 1;
                            String v11 = new String(v1_1, v7, v10 - 1, "UTF-8");
                            if(v11.startsWith("WPS")) {
                                Theme.themedWallpaperFileOffset = v10 + v8;
                                v4 = 1;
                            }
                            else {
                                int v9 = v11.indexOf(61);
                                if(v9 != v14_1) {
                                    String v12 = v11.substring(0, v9);
                                    String v9_1 = v11.substring(v9 + 1);
                                    if(v9_1.length() > 0 && v9_1.charAt(0) == 35) {
                                        try {
                                            v11_1 = Color.parseColor(v9_1);
                                            goto label_55;
                                        }
                                        catch(Exception ) {
                                            v9_2 = Utilities.parseInt(v9_1);
                                            goto label_51;
                                        }
                                    }

                                    v9_2 = Utilities.parseInt(v9_1);
                                label_51:
                                    v11_1 = v9_2.intValue();
                                label_55:
                                    v0.put(v12, Integer.valueOf(v11_1));
                                    goto label_57;
                                }
                                else {
                                label_57:
                                    v7 += v10;
                                    v8 += v10;
                                    goto label_59;
                                }
                            }
                        }
                        else {
                        label_59:
                            ++v6;
                            continue;
                        }

                        break;
                    }

                    if(v3 == v8) {
                        goto label_70;
                    }

                    v15.getChannel().position(((long)v8));
                    if(v4 == 0) {
                        goto label_68;
                    }

                    break;
                }
            }
            catch(Throwable v14) {
                goto label_88;
            }
            catch(Throwable v14) {
                goto label_75;
            }

            goto label_70;
        label_68:
            v3 = v8;
            goto label_14;
            try {
            label_70:
                v15.close();
                return v0;
            }
            catch(Exception v14_2) {
                goto label_86;
            }

        label_75:
            v2 = v15;
            try {
            label_81:
                FileLog.e(v14);
                if(v2 == null) {
                    return v0;
                }
            }
            catch(Throwable v14) {
                v15 = v2;
                goto label_88;
            }
        }

        try {
            v2.close();
        }
        catch(Exception v14_2) {
        label_86:
            FileLog.e(((Throwable)v14_2));
        }

        return v0;
    label_88:
        if(v15 != null) {
            try {
                v15.close();
            }
            catch(Exception v15_1) {
                FileLog.e(((Throwable)v15_1));
            }
        }

        throw v14;
    }

    public static Drawable getThemedDrawable(Context arg1, int arg2, String arg3) {
        if(arg1 == null) {
            return null;
        }

        Drawable v1 = arg1.getResources().getDrawable(arg2).mutate();
        v1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(arg3), PorterDuff$Mode.MULTIPLY));
        return v1;
    }

    public static Drawable getThemedWallpaper(boolean arg8) {
        BitmapDrawable v0_3;
        FileInputStream v2;
        Object v0 = Theme.currentColors.get("chat_wallpaper");
        if(v0 != null) {
            return new ColorDrawable(((Integer)v0).intValue());
        }

        Rect v1 = null;
        if(Theme.themedWallpaperFileOffset <= 0) {
            goto label_88;
        }

        if(Theme.currentTheme.pathToFile == null) {
            if(Theme.currentTheme.assetName != null) {
                goto label_17;
            }

            goto label_88;
        }

        try {
        label_17:
            File v0_1 = Theme.currentTheme.assetName != null ? Theme.getAssetFile(Theme.currentTheme.assetName) : new File(Theme.currentTheme.pathToFile);
            v2 = new FileInputStream(v0_1);
        }
        catch(Throwable v8) {
            v2 = ((FileInputStream)v1);
            goto label_82;
        }
        catch(Throwable v8) {
            v2 = ((FileInputStream)v1);
            goto label_74;
        }

        try {
            v2.getChannel().position(((long)Theme.themedWallpaperFileOffset));
            BitmapFactory$Options v0_2 = new BitmapFactory$Options();
            int v3 = 1;
            if(arg8) {
                v0_2.inJustDecodeBounds = true;
                float v8_1 = ((float)v0_2.outWidth);
                float v4 = ((float)v0_2.outHeight);
                int v5 = AndroidUtilities.dp(100f);
                while(true) {
                    float v6 = ((float)v5);
                    if(v8_1 <= v6 && v4 <= v6) {
                        break;
                    }

                    v3 *= 2;
                    v8_1 /= 2f;
                    v4 /= 2f;
                }
            }

            v0_2.inJustDecodeBounds = false;
            v0_2.inSampleSize = v3;
            Bitmap v8_2 = BitmapFactory.decodeStream(((InputStream)v2), v1, v0_2);
            if(v8_2 == null) {
                goto label_65;
            }

            v0_3 = new BitmapDrawable(v8_2);
            goto label_60;
        }
        catch(Throwable v8) {
        }
        catch(Throwable v8) {
            try {
            label_74:
                FileLog.e(v8);
                if(v2 == null) {
                    goto label_88;
                }
            }
            catch(Throwable v8) {
                goto label_82;
            }

            try {
                v2.close();
                goto label_88;
            }
            catch(Exception v8_3) {
                goto label_79;
            }
        }

    label_82:
        if(v2 != null) {
            try {
                v2.close();
            }
            catch(Exception v0_4) {
                FileLog.e(((Throwable)v0_4));
            }
        }

        throw v8;
        try {
        label_60:
            v2.close();
        }
        catch(Exception v8_3) {
            FileLog.e(((Throwable)v8_3));
        }

        return ((Drawable)v0_3);
        try {
        label_65:
            v2.close();
        }
        catch(Exception v8_3) {
        label_79:
            FileLog.e(((Throwable)v8_3));
        }

    label_88:
        return ((Drawable)v1);
    }

    public static boolean hasThemeKey(String arg1) {
        return Theme.currentColors.containsKey(arg1);
    }

    public static boolean hasWallpaperFromTheme() {
        boolean v0 = (Theme.currentColors.containsKey("chat_wallpaper")) || Theme.themedWallpaperFileOffset > 0 ? true : false;
        return v0;
    }

    public static boolean isCurrentThemeNight() {
        boolean v0 = Theme.currentTheme == Theme.currentNightTheme ? true : false;
        return v0;
    }

    public static boolean isCustomTheme() {
        return Theme.isCustomTheme;
    }

    static void lambda$applyTheme$1(boolean arg4) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewTheme, new Object[]{Boolean.valueOf(arg4)});
    }

    static void lambda$loadWallpaper$3() {
        int v1_6;
        FileInputStream v5;
        File v4_1;
        Object v0 = Theme.wallpaperSync;
        __monitor_enter(v0);
        try {
            if(MessagesController.getGlobalMainSettings().getBoolean("overrideThemeWallpaper", false)) {
                goto label_77;
            }

            Object v1_1 = Theme.currentColors.get("chat_wallpaper");
            if(v1_1 != null) {
                Theme.wallpaper = new ColorDrawable(((Integer)v1_1).intValue());
                Theme.isCustomTheme = true;
                goto label_77;
            }

            if(Theme.themedWallpaperFileOffset <= 0) {
                goto label_77;
            }

            if(Theme.currentTheme.pathToFile == null) {
                if(Theme.currentTheme.assetName != null) {
                    goto label_26;
                }

                goto label_77;
            }
        }
        catch(Throwable v1) {
            goto label_125;
        }

    label_26:
        FileInputStream v1_2 = null;
        try {
            v4_1 = Theme.currentTheme.assetName != null ? Theme.getAssetFile(Theme.currentTheme.assetName) : new File(Theme.currentTheme.pathToFile);
            v5 = new FileInputStream(v4_1);
            goto label_40;
        }
        catch(Throwable v2) {
        }
        catch(Throwable v4) {
            goto label_65;
            try {
            label_40:
                v5.getChannel().position(((long)Theme.themedWallpaperFileOffset));
                Bitmap v1_3 = BitmapFactory.decodeStream(((InputStream)v5));
                if(v1_3 != null) {
                    BitmapDrawable v4_2 = new BitmapDrawable(v1_3);
                    Theme.wallpaper = ((Drawable)v4_2);
                    Theme.themedWallpaper = ((Drawable)v4_2);
                    Theme.isCustomTheme = true;
                }

                goto label_51;
            }
            catch(Throwable v2) {
            }
            catch(Throwable v4) {
                v1_2 = v5;
                try {
                label_65:
                    FileLog.e(v4);
                    if(v1_2 == null) {
                        goto label_77;
                    }
                }
                catch(Throwable v2) {
                    goto label_71;
                }

                try {
                    v1_2.close();
                    goto label_77;
                }
                catch(Throwable v1) {
                }
                catch(Exception v1_4) {
                    goto label_54;
                    v1_2 = v5;
                label_71:
                    if(v1_2 != null) {
                        try {
                            v1_2.close();
                            goto label_76;
                        }
                        catch(Throwable v1) {
                        }
                        catch(Exception v1_4) {
                            try {
                                FileLog.e(((Throwable)v1_4));
                            label_76:
                                throw v2;
                            }
                            catch(Throwable v1) {
                                goto label_125;
                            }

                            try {
                            label_51:
                                v5.close();
                                goto label_77;
                            }
                            catch(Throwable v1) {
                            }
                            catch(Exception v1_4) {
                                try {
                                label_54:
                                    FileLog.e(((Throwable)v1_4));
                                label_77:
                                    if(Theme.wallpaper != null) {
                                        goto label_118;
                                    }

                                    try {
                                        SharedPreferences v1_5 = MessagesController.getGlobalMainSettings();
                                        int v5_1 = 1000001;
                                        int v4_3 = v1_5.getInt("selectedBackground", v5_1);
                                        v1_6 = v1_5.getInt("selectedColor", 0);
                                        if(v1_6 != 0) {
                                            goto label_111;
                                        }
                                    }
                                    catch(Throwable ) {
                                        v1_6 = 0;
                                        goto label_111;
                                    }
                                }
                                catch(Throwable v1) {
                                    goto label_125;
                                }

                                int v6 = 2131230913;
                                if(v4_3 == v5_1) {
                                    try {
                                        Theme.wallpaper = ApplicationLoader.applicationContext.getResources().getDrawable(v6);
                                        goto label_92;
                                    label_94:
                                        v4_1 = new File(ApplicationLoader.getFilesDirFixed(), "wallpaper.jpg");
                                        if(v4_1.exists()) {
                                            Theme.wallpaper = Drawable.createFromPath(v4_1.getAbsolutePath());
                                            Theme.isCustomTheme = true;
                                            goto label_111;
                                        }
                                        else {
                                            Theme.wallpaper = ApplicationLoader.applicationContext.getResources().getDrawable(v6);
                                        }

                                    label_92:
                                        Theme.isCustomTheme = false;
                                        goto label_111;
                                    }
                                    catch(Throwable v1) {
                                    label_126:
                                        throw v1;
                                    }
                                    catch(Throwable ) {
                                        try {
                                        label_111:
                                            if(Theme.wallpaper == null) {
                                                if(v1_6 == 0) {
                                                    v1_6 = -2693905;
                                                }

                                                Theme.wallpaper = new ColorDrawable(v1_6);
                                            }

                                        label_118:
                                            Theme.calcBackgroundColor(Theme.wallpaper, 1);
                                            AndroidUtilities.runOnUIThread(-$$Lambda$Theme$TMhHMPfcji-vj3thFJwRUoKkxRA.INSTANCE);
                                            __monitor_exit(v0);
                                            return;
                                        label_125:
                                            __monitor_exit(v0);
                                            goto label_126;
                                        }
                                        catch(Throwable v1) {
                                            goto label_125;
                                        }
                                    }
                                }
                                else {
                                    goto label_94;
                                }

                                goto label_92;
                            }
                        }
                    }
                    else {
                        goto label_76;
                    }

                    goto label_51;
                }
            }
        }
    }

    static void lambda$null$2() {
        Theme.applyChatServiceMessageColor();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
    }

    static int lambda$sortThemes$0(ThemeInfo arg1, ThemeInfo arg2) {
        if(arg1.pathToFile == null && arg1.assetName == null) {
            return -1;
        }

        if(arg2.pathToFile == null && arg2.assetName == null) {
            return 1;
        }

        return arg1.name.compareTo(arg2.name);
    }

    public static void loadWallpaper() {
        if(Theme.wallpaper != null) {
            return;
        }

        Utilities.searchQueue.postRunnable(-$$Lambda$Theme$g9IkSg8DwYzdCeZKlfMvOXD2f9I.INSTANCE);
    }

    public static void reloadWallpaper() {
        Theme.wallpaper = null;
        Theme.themedWallpaper = null;
        Theme.loadWallpaper();
    }

    public static void saveAutoNightThemeConfig() {
        SharedPreferences$Editor v0 = MessagesController.getGlobalMainSettings().edit();
        v0.putInt("selectedAutoNightType", Theme.selectedAutoNightType);
        v0.putBoolean("autoNightScheduleByLocation", Theme.autoNightScheduleByLocation);
        v0.putFloat("autoNightBrighnessThreshold", Theme.autoNightBrighnessThreshold);
        v0.putInt("autoNightDayStartTime", Theme.autoNightDayStartTime);
        v0.putInt("autoNightDayEndTime", Theme.autoNightDayEndTime);
        v0.putInt("autoNightSunriseTime", Theme.autoNightSunriseTime);
        v0.putString("autoNightCityName", Theme.autoNightCityName);
        v0.putInt("autoNightSunsetTime", Theme.autoNightSunsetTime);
        v0.putLong("autoNightLocationLatitude3", Double.doubleToRawLongBits(Theme.autoNightLocationLatitude));
        v0.putLong("autoNightLocationLongitude3", Double.doubleToRawLongBits(Theme.autoNightLocationLongitude));
        v0.putInt("autoNightLastSunCheckDay", Theme.autoNightLastSunCheckDay);
        if(Theme.currentNightTheme != null) {
            v0.putString("nighttheme", Theme.currentNightTheme.name);
        }
        else {
            v0.remove("nighttheme");
        }

        v0.commit();
    }

    public static void saveCurrentTheme(String arg5, boolean arg6) {
        FileOutputStream v3;
        StringBuilder v0 = new StringBuilder();
        Iterator v1 = Theme.currentColors.entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.append(((Map$Entry)v2).getKey());
            v0.append("=");
            v0.append(((Map$Entry)v2).getValue());
            v0.append("\n");
        }

        File v1_1 = new File(ApplicationLoader.getFilesDirFixed(), arg5);
        FileOutputStream v2_1 = null;
        try {
            v3 = new FileOutputStream(v1_1);
            goto label_23;
        }
        catch(Throwable v5) {
        }
        catch(Exception v5_1) {
            goto label_88;
            try {
            label_23:
                v3.write(AndroidUtilities.getStringBytes(v0.toString()));
                if((Theme.themedWallpaper instanceof BitmapDrawable)) {
                    Bitmap v0_1 = Theme.themedWallpaper.getBitmap();
                    if(v0_1 != null) {
                        v3.write(new byte[]{87, 80, 83, 10});
                        v0_1.compress(Bitmap$CompressFormat.JPEG, 87, ((OutputStream)v3));
                        v3.write(new byte[]{10, 87, 80, 69, 10});
                    }

                    if(!arg6) {
                        goto label_47;
                    }

                    Theme.wallpaper = Theme.themedWallpaper;
                    Theme.calcBackgroundColor(Theme.wallpaper, 2);
                }

            label_47:
                Object v6 = Theme.themesDict.get(arg5);
                if(v6 == null) {
                    ThemeInfo v6_1 = new ThemeInfo();
                    v6_1.pathToFile = v1_1.getAbsolutePath();
                    v6_1.name = arg5;
                    Theme.themes.add(v6_1);
                    Theme.themesDict.put(v6_1.name, v6_1);
                    Theme.otherThemes.add(v6_1);
                    Theme.saveOtherThemes();
                    Theme.sortThemes();
                }

                Theme.currentTheme = ((ThemeInfo)v6);
                if(Theme.currentTheme != Theme.currentNightTheme) {
                    Theme.currentDayTheme = Theme.currentTheme;
                }

                SharedPreferences$Editor v5_2 = MessagesController.getGlobalMainSettings().edit();
                v5_2.putString("theme", Theme.currentDayTheme.name);
                v5_2.commit();
                goto label_77;
            }
            catch(Throwable v5) {
            }
            catch(Exception v5_1) {
                v2_1 = v3;
                try {
                label_88:
                    FileLog.e(((Throwable)v5_1));
                    if(v2_1 == null) {
                        return;
                    }
                }
                catch(Throwable v5) {
                    v3 = v2_1;
                    goto label_95;
                }

                try {
                    v2_1.close();
                    return;
                }
                catch(Exception v5_1) {
                    goto label_93;
                }
            }
        }

    label_95:
        if(v3 != null) {
            try {
                v3.close();
            }
            catch(Exception v6_2) {
                FileLog.e(((Throwable)v6_2));
            }
        }

        throw v5;
        try {
        label_77:
            v3.close();
        }
        catch(Exception v5_1) {
        label_93:
            FileLog.e(((Throwable)v5_1));
        }
    }

    private static void saveOtherThemes() {
        int v2 = 0;
        SharedPreferences$Editor v0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit();
        JSONArray v1 = new JSONArray();
        while(v2 < Theme.otherThemes.size()) {
            JSONObject v3 = Theme.otherThemes.get(v2).getSaveJson();
            if(v3 != null) {
                v1.put(v3);
            }

            ++v2;
        }

        v0.putString("themes2", v1.toString());
        v0.commit();
    }

    public static void setColor(String arg1, int arg2, boolean arg3) {
        if(arg1.equals("chat_wallpaper")) {
            arg2 |= -16777216;
        }

        if(arg3) {
            Theme.currentColors.remove(arg1);
        }
        else {
            Theme.currentColors.put(arg1, Integer.valueOf(arg2));
        }

        if((arg1.equals("chat_serviceBackground")) || (arg1.equals("chat_serviceBackgroundSelected"))) {
            Theme.applyChatServiceMessageColor();
        }
        else if(arg1.equals("chat_wallpaper")) {
            Theme.reloadWallpaper();
        }
    }

    public static void setCombinedDrawableColor(Drawable arg1, int arg2, boolean arg3) {
        if(!(arg1 instanceof CombinedDrawable)) {
            return;
        }

        arg1 = arg3 ? ((CombinedDrawable)arg1).getIcon() : ((CombinedDrawable)arg1).getBackground();
        arg1.setColorFilter(new PorterDuffColorFilter(arg2, PorterDuff$Mode.MULTIPLY));
    }

    public static void setCurrentNightTheme(ThemeInfo arg3) {
        int v0 = Theme.currentTheme == Theme.currentNightTheme ? 1 : 0;
        Theme.currentNightTheme = arg3;
        if(v0 != 0) {
            Theme.applyDayNightThemeMaybe(true);
        }
    }

    public static void setDrawableColor(Drawable arg2, int arg3) {
        if(arg2 == null) {
            return;
        }

        if((arg2 instanceof ShapeDrawable)) {
            ((ShapeDrawable)arg2).getPaint().setColor(arg3);
        }
        else {
            arg2.setColorFilter(new PorterDuffColorFilter(arg3, PorterDuff$Mode.MULTIPLY));
        }
    }

    public static void setDrawableColorByKey(Drawable arg0, String arg1) {
        if(arg1 == null) {
            return;
        }

        Theme.setDrawableColor(arg0, Theme.getColor(arg1));
    }

    public static void setEmojiDrawableColor(Drawable arg1, int arg2, boolean arg3) {
        PorterDuffColorFilter v3;
        if((arg1 instanceof StateListDrawable)) {
            if(arg3) {
                try {
                    arg1 = Theme.getStateDrawable(arg1, 0);
                    if(!(arg1 instanceof ShapeDrawable)) {
                        v3 = new PorterDuffColorFilter(arg2, PorterDuff$Mode.MULTIPLY);
                    label_13:
                        arg1.setColorFilter(((ColorFilter)v3));
                        return;
                    label_15:
                        arg1 = Theme.getStateDrawable(arg1, 1);
                        if((arg1 instanceof ShapeDrawable)) {
                        }
                        else {
                            goto label_20;
                        }
                    }

                label_7:
                    Paint v1 = ((ShapeDrawable)arg1).getPaint();
                    v1.setColor(arg2);
                    return;
                label_20:
                    v3 = new PorterDuffColorFilter(arg2, PorterDuff$Mode.MULTIPLY);
                    goto label_13;
                }
                catch(Throwable ) {
                    return;
                }
            }
            else {
                goto label_15;
            }

            goto label_7;
        }
    }

    public static void setSelectorDrawableColor(Drawable arg4, int arg5, boolean arg6) {
        if((arg4 instanceof StateListDrawable)) {
            if(arg6) {
                try {
                    Drawable v6 = Theme.getStateDrawable(arg4, 0);
                    if((v6 instanceof ShapeDrawable)) {
                        ((ShapeDrawable)v6).getPaint().setColor(arg5);
                    }
                    else {
                        v6.setColorFilter(new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY));
                    }

                    arg4 = Theme.getStateDrawable(arg4, 1);
                    if((arg4 instanceof ShapeDrawable)) {
                        goto label_18;
                    }

                    PorterDuffColorFilter v6_1 = new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY);
                label_24:
                    arg4.setColorFilter(((ColorFilter)v6_1));
                    return;
                label_26:
                    arg4 = Theme.getStateDrawable(arg4, 2);
                    if((arg4 instanceof ShapeDrawable)) {
                    label_18:
                        Paint v4 = ((ShapeDrawable)arg4).getPaint();
                        v4.setColor(arg5);
                        return;
                    }

                    v6_1 = new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY);
                    goto label_24;
                }
                catch(Throwable ) {
                    return;
                }
            }
            else {
                goto label_26;
            }

            goto label_18;
        }
        else {
            if(Build$VERSION.SDK_INT < 21) {
                return;
            }

            if(!(arg4 instanceof RippleDrawable)) {
                return;
            }

            if(arg6) {
                ((RippleDrawable)arg4).setColor(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{arg5}));
                return;
            }

            if(((RippleDrawable)arg4).getNumberOfLayers() <= 0) {
                return;
            }

            arg4 = ((RippleDrawable)arg4).getDrawable(0);
            if((arg4 instanceof ShapeDrawable)) {
                ((ShapeDrawable)arg4).getPaint().setColor(arg5);
                return;
            }

            arg4.setColorFilter(new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY));
        }
    }

    public static void setThemeWallpaper(String arg1, Bitmap arg2, File arg3) {
        Theme.currentColors.remove("chat_wallpaper");
        MessagesController.getGlobalMainSettings().edit().remove("overrideThemeWallpaper").commit();
        if(arg2 != null) {
            Theme.themedWallpaper = new BitmapDrawable(arg2);
            Theme.saveCurrentTheme(arg1, false);
            Theme.calcBackgroundColor(Theme.themedWallpaper, 0);
            Theme.applyChatServiceMessageColor();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper, new Object[0]);
        }
        else {
            Theme.themedWallpaper = null;
            Theme.wallpaper = null;
            Theme.saveCurrentTheme(arg1, false);
            Theme.reloadWallpaper();
        }
    }

    private static void sortThemes() {
        Collections.sort(Theme.themes, -$$Lambda$Theme$CDAxGNnEyNa6tkvecQwKSXq77-I.INSTANCE);
    }
}

