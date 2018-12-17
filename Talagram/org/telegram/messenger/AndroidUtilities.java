package org.telegram.messenger;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences$Editor;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix$ScaleToFit;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface$Builder;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Environment;
import android.os.Handler;
import android.provider.CallLog$Calls;
import android.provider.DocumentsContract;
import android.provider.MediaStore$Audio$Media;
import android.provider.MediaStore$Images$Media;
import android.provider.MediaStore$Video$Media;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.i;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.StateSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.a.a.a.a;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.regex.Pattern;
import org.telegram.a.b;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_userContact_old2;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.AlertDialog$Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Components.ForegroundDetector;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PickerBottomLayout;
import org.telegram.ui.Components.TypefaceSpan;
import utils.view.FontUtil;

public class AndroidUtilities {
    public class LinkMovementMethodMy extends LinkMovementMethod {
        public LinkMovementMethodMy() {
            super();
        }

        public boolean onTouchEvent(TextView arg3, Spannable arg4, MotionEvent arg5) {
            boolean v3_1;
            try {
                v3_1 = super.onTouchEvent(arg3, arg4, arg5);
                if(arg5.getAction() == 1 || arg5.getAction() == 3) {
                    Selection.removeSelection(arg4);
                }
            }
            catch(Exception v3) {
                FileLog.e(((Throwable)v3));
                return 0;
            }

            return v3_1;
        }
    }

    class VcardData {
        String name;
        ArrayList phones;
        StringBuilder vcard;

        VcardData(org.telegram.messenger.AndroidUtilities$1 arg1) {
            this();
        }

        private VcardData() {
            super();
            this.phones = new ArrayList();
            this.vcard = new StringBuilder();
        }
    }

    public class VcardItem {
        public boolean checked;
        public String fullData;
        public int type;
        public ArrayList vcardData;

        public VcardItem() {
            super();
            this.vcardData = new ArrayList();
            this.fullData = "";
            this.checked = true;
        }

        public String getRawType(boolean arg5) {
            String v5;
            int v0 = this.fullData.indexOf(58);
            if(v0 < 0) {
                return "";
            }

            int v2 = 0;
            String v0_1 = this.fullData.substring(0, v0);
            if(this.type == 20) {
                String[] v0_2 = v0_1.substring(2).split(";");
                if(arg5) {
                    v5 = v0_2[0];
                }
                else if(v0_2.length > 1) {
                    v5 = v0_2[v0_2.length - 1];
                }
                else {
                    v5 = "";
                }

                return v5;
            }

            String[] v5_1 = v0_1.split(";");
            while(v2 < v5_1.length) {
                if(v5_1[v2].indexOf(61) >= 0) {
                }
                else {
                    v0_1 = v5_1[v2];
                }

                ++v2;
            }

            return v0_1;
        }

        public String[] getRawValue() {
            int v0 = this.fullData.indexOf(58);
            int v1 = 0;
            if(v0 < 0) {
                return new String[0];
            }

            String v2 = this.fullData.substring(0, v0);
            String v0_1 = this.fullData.substring(v0 + 1, this.fullData.length());
            String[] v2_1 = v2.split(";");
            String v6 = "UTF-8";
            String v5 = null;
            int v3;
            for(v3 = 0; v3 < v2_1.length; ++v3) {
                String[] v7 = v2_1[v3].split("=");
                if(v7.length != 2) {
                }
                else if(v7[0].equals("CHARSET")) {
                    v6 = v7[1];
                }
                else if(v7[0].equals("ENCODING")) {
                    v5 = v7[1];
                }
            }

            String[] v0_2 = v0_1.split(";");
            while(v1 < v0_2.length) {
                if(TextUtils.isEmpty(v0_2[v1])) {
                }
                else if(v5 != null && (v5.equalsIgnoreCase("QUOTED-PRINTABLE"))) {
                    byte[] v2_2 = AndroidUtilities.decodeQuotedPrintable(AndroidUtilities.getStringBytes(v0_2[v1]));
                    if(v2_2 != null && v2_2.length != 0) {
                        try {
                            v0_2[v1] = new String(v2_2, v6);
                            goto label_65;
                        }
                        catch(Exception ) {
                        label_65:
                            ++v1;
                            continue;
                        }
                    }
                }

                goto label_65;
            }

            return v0_2;
        }

        public String getType() {
            int v1_1;
            if(this.type == 5) {
                return LocaleController.getString("ContactBirthday", 2131624469);
            }

            if(this.type == 6) {
                if("ORG".equalsIgnoreCase(this.getRawType(true))) {
                    return LocaleController.getString("ContactJob", 2131624470);
                }

                return LocaleController.getString("ContactJobTitle", 2131624471);
            }

            int v0 = this.fullData.indexOf(58);
            if(v0 < 0) {
                return "";
            }

            String v0_1 = this.fullData.substring(0, v0);
            int v5 = 2;
            if(this.type == 20) {
                v0_1 = v0_1.substring(v5).split(";")[0];
            }
            else {
                String[] v1 = v0_1.split(";");
                String v4 = v0_1;
                for(v0 = 0; v0 < v1.length; ++v0) {
                    if(v1[v0].indexOf(61) >= 0) {
                    }
                    else {
                        v4 = v1[v0];
                    }
                }

                v0_1 = v4.startsWith("X-") ? v4.substring(v5) : v4;
                if("PREF".equals(v0_1)) {
                    v0_1 = "PhoneMain";
                    v1_1 = 2131625747;
                }
                else if("HOME".equals(v0_1)) {
                    v0_1 = "PhoneHome";
                    v1_1 = 2131625746;
                }
                else {
                    if(!"MOBILE".equals(v0_1)) {
                        if("CELL".equals(v0_1)) {
                        }
                        else if("OTHER".equals(v0_1)) {
                            v0_1 = "PhoneOther";
                            v1_1 = 2131625753;
                            goto label_66;
                        }
                        else if("WORK".equals(v0_1)) {
                            v0_1 = "PhoneWork";
                            v1_1 = 2131625755;
                            goto label_66;
                        }
                        else {
                            goto label_96;
                        }
                    }

                    v0_1 = "PhoneMobile";
                    v1_1 = 2131625748;
                }

            label_66:
                v0_1 = LocaleController.getString(v0_1, v1_1);
            }

        label_96:
            return v0_1.substring(0, 1).toUpperCase() + v0_1.substring(1, v0_1.length()).toLowerCase();
        }

        public String getValue(boolean arg12) {
            int v9;
            StringBuilder v0 = new StringBuilder();
            int v1 = this.fullData.indexOf(58);
            if(v1 < 0) {
                return "";
            }

            if(v0.length() > 0) {
                v0.append(", ");
            }

            String v2 = this.fullData.substring(0, v1);
            String v1_1 = this.fullData.substring(v1 + 1, this.fullData.length());
            String[] v2_1 = v2.split(";");
            String v7 = "UTF-8";
            String v6 = null;
            int v4;
            for(v4 = 0; true; ++v4) {
                v9 = 2;
                if(v4 >= v2_1.length) {
                    break;
                }

                String[] v8 = v2_1[v4].split("=");
                if(v8.length != v9) {
                }
                else if(v8[0].equals("CHARSET")) {
                    v7 = v8[1];
                }
                else if(v8[0].equals("ENCODING")) {
                    v6 = v8[1];
                }
            }

            String[] v1_2 = v1_1.split(";");
            int v2_2 = 0;
            v4 = 0;
            while(v2_2 < v1_2.length) {
                if(TextUtils.isEmpty(v1_2[v2_2])) {
                }
                else {
                    if(v6 != null && (v6.equalsIgnoreCase("QUOTED-PRINTABLE"))) {
                        byte[] v8_1 = AndroidUtilities.decodeQuotedPrintable(AndroidUtilities.getStringBytes(v1_2[v2_2]));
                        if(v8_1 != null && v8_1.length != 0) {
                            try {
                                v1_2[v2_2] = new String(v8_1, v7);
                                goto label_73;
                            }
                            catch(Exception ) {
                            label_73:
                                if(v4 != 0 && v0.length() > 0) {
                                    v0.append(" ");
                                }

                                v0.append(v1_2[v2_2]);
                                if(v4 != 0) {
                                    goto label_87;
                                }

                                if(v1_2[v2_2].length() > 0) {
                                    v4 = 1;
                                    goto label_87;
                                }

                                v4 = 0;
                                goto label_87;
                            }
                        }
                    }

                    goto label_73;
                }

            label_87:
                ++v2_2;
            }

            if(arg12) {
                if(this.type == 0) {
                    return b.a().e(v0.toString());
                }
                else {
                    v1 = 5;
                    if(this.type == v1) {
                        String[] v12 = v0.toString().split("T");
                        if(v12.length > 0) {
                            v12 = v12[0].split("-");
                            if(v12.length == 3) {
                                Calendar v0_1 = Calendar.getInstance();
                                v0_1.set(1, Utilities.parseInt(v12[0]).intValue());
                                v0_1.set(v9, Utilities.parseInt(v12[1]).intValue() - 1);
                                v0_1.set(v1, Utilities.parseInt(v12[v9]).intValue());
                                return LocaleController.getInstance().formatterYearMax.format(v0_1.getTime());
                            }
                        }
                    }
                }
            }

            return v0.toString();
        }
    }

    public static final int FLAG_TAG_ALL = 11;
    public static final int FLAG_TAG_BOLD = 2;
    public static final int FLAG_TAG_BR = 1;
    public static final int FLAG_TAG_COLOR = 4;
    public static final int FLAG_TAG_URL = 8;
    public static Pattern WEB_URL;
    public static AccelerateInterpolator accelerateInterpolator;
    private static int adjustOwnerClassGuid;
    private static RectF bitmapRect;
    private static final Object callLock;
    private static ContentObserver callLogContentObserver;
    private static CallReceiver callReceiver;
    public static DecelerateInterpolator decelerateInterpolator;
    public static float density;
    public static DisplayMetrics displayMetrics;
    public static Point displaySize;
    private static boolean hasCallPermissions;
    public static boolean incorrectDisplaySizeFix;
    public static boolean isInMultiwindow;
    private static Boolean isTablet;
    public static int leftBaseline;
    private static Field mAttachInfoField;
    private static Field mStableInsetsField;
    public static OvershootInterpolator overshootInterpolator;
    public static Integer photoSize;
    private static int prevOrientation;
    public static int roundMessageSize;
    private static Paint roundPaint;
    private static final Object smsLock;
    public static int statusBarHeight;
    private static final Hashtable typefaceCache;
    private static Runnable unregisterRunnable;
    public static boolean usingHardwareInput;
    private static boolean waitingForCall;
    private static boolean waitingForSms;

    static {
        AndroidUtilities.typefaceCache = new Hashtable();
        AndroidUtilities.prevOrientation = -10;
        boolean v0 = false;
        AndroidUtilities.waitingForSms = false;
        AndroidUtilities.waitingForCall = false;
        AndroidUtilities.smsLock = new Object();
        AndroidUtilities.callLock = new Object();
        AndroidUtilities.statusBarHeight = 0;
        AndroidUtilities.density = 1f;
        AndroidUtilities.displaySize = new Point();
        Integer v1 = null;
        AndroidUtilities.photoSize = v1;
        AndroidUtilities.displayMetrics = new DisplayMetrics();
        AndroidUtilities.decelerateInterpolator = new DecelerateInterpolator();
        AndroidUtilities.accelerateInterpolator = new AccelerateInterpolator();
        AndroidUtilities.overshootInterpolator = new OvershootInterpolator();
        AndroidUtilities.isTablet = ((Boolean)v1);
        AndroidUtilities.adjustOwnerClassGuid = 0;
        AndroidUtilities.WEB_URL = ((Pattern)v1);
        try {
            Pattern v2_1 = Pattern.compile("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))");
            StringBuilder v3 = new StringBuilder();
            v3.append("(([a-zA-Z0-9 -퟿豈-﷏ﷰ-￯]([a-zA-Z0-9 -퟿豈-﷏ﷰ-￯\\-]{0,61}[a-zA-Z0-9 -퟿豈-﷏ﷰ-￯]){0,1}\\.)+[a-zA-Z -퟿豈-﷏ﷰ-￯]{2,63}|");
            v3.append(v2_1);
            v3.append(")");
            v2_1 = Pattern.compile(v3.toString());
            v3 = new StringBuilder();
            v3.append("((?:(http|https|Http|Https):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?(?:");
            v3.append(v2_1);
            v3.append(")(?:\\:\\d{1,5})?)(\\/(?:(?:[");
            v3.append("a-zA-Z0-9 -퟿豈-﷏ﷰ-￯");
            v3.append("\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");
            AndroidUtilities.WEB_URL = Pattern.compile(v3.toString());
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }

        int v2_2 = AndroidUtilities.isTablet() ? 80 : 72;
        AndroidUtilities.leftBaseline = v2_2;
        AndroidUtilities.checkDisplaySize(ApplicationLoader.applicationContext, ((Configuration)v1));
        if(Build$VERSION.SDK_INT >= 23) {
            v0 = true;
        }

        AndroidUtilities.hasCallPermissions = v0;
    }

    public AndroidUtilities() {
        super();
    }

    static void access$100(boolean arg0, String arg1) {
        AndroidUtilities.registerLoginContentObserver(arg0, arg1);
    }

    static Runnable access$202(Runnable arg0) {
        AndroidUtilities.unregisterRunnable = arg0;
        return arg0;
    }

    public static void addMediaToGallery(Uri arg2) {
        if(arg2 == null) {
            return;
        }

        try {
            Intent v0 = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            v0.setData(arg2);
            ApplicationLoader.applicationContext.sendBroadcast(v0);
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    public static void addMediaToGallery(String arg1) {
        if(arg1 == null) {
            return;
        }

        AndroidUtilities.addMediaToGallery(Uri.fromFile(new File(arg1)));
    }

    public static void addToClipboard(CharSequence arg2) {
        try {
            ApplicationLoader.applicationContext.getSystemService("clipboard").setPrimaryClip(ClipData.newPlainText("label", arg2));
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    public static byte[] calcAuthKeyHash(byte[] arg3) {
        arg3 = Utilities.computeSHA1(arg3);
        byte[] v1 = new byte[16];
        System.arraycopy(arg3, 0, v1, 0, 16);
        return v1;
    }

    public static int[] calcDrawableColor(Drawable arg12) {
        int v6;
        Bitmap v5;
        int v0 = 2;
        int[] v1 = new int[v0];
        int v4 = -16777216;
        try {
            if(!(arg12 instanceof BitmapDrawable)) {
                goto label_20;
            }

            Bitmap v12_1 = ((BitmapDrawable)arg12).getBitmap();
            if(v12_1 == null) {
                goto label_27;
            }

            v5 = Bitmaps.createScaledBitmap(v12_1, 1, 1, true);
            if(v5 == null) {
                goto label_27;
            }

            v6 = v5.getPixel(0, 0);
            if(v12_1 != v5) {
                goto label_13;
            }

            goto label_18;
        }
        catch(Exception v12) {
            goto label_26;
        }

        try {
        label_13:
            v5.recycle();
        }
        catch(Exception v12) {
            v4 = v6;
            goto label_26;
        }

    label_18:
        v4 = v6;
        goto label_27;
        try {
        label_20:
            if(!(arg12 instanceof ColorDrawable)) {
                goto label_27;
            }

            v4 = ((ColorDrawable)arg12).getColor();
            goto label_27;
        }
        catch(Exception v12) {
        }

    label_26:
        FileLog.e(((Throwable)v12));
    label_27:
        double[] v12_2 = AndroidUtilities.rgbToHsv(v4 >> 16 & 255, v4 >> 8 & 255, v4 & 255);
        v12_2[1] = Math.min(1, v12_2[1] + 0.05 + (1 - v12_2[1]) * 0.1);
        v12_2[v0] = Math.max(0, v12_2[v0] * 0.65);
        int[] v12_3 = AndroidUtilities.hsvToRgb(v12_2[0], v12_2[1], v12_2[v0]);
        v1[0] = Color.argb(102, v12_3[0], v12_3[1], v12_3[v0]);
        v1[1] = Color.argb(136, v12_3[0], v12_3[1], v12_3[v0]);
        return v1;
    }

    public static void cancelRunOnUIThread(Runnable arg1) {
        ApplicationLoader.applicationHandler.removeCallbacks(arg1);
    }

    public static void checkDisplaySize(Context arg3, Configuration arg4) {
        int v3_3;
        try {
            AndroidUtilities.density = arg3.getResources().getDisplayMetrics().density;
            if(arg4 == null) {
                arg4 = arg3.getResources().getConfiguration();
            }

            boolean v1 = true;
            if(arg4.keyboard == 1 || arg4.hardKeyboardHidden != 1) {
                v1 = false;
            }
            else {
            }

            AndroidUtilities.usingHardwareInput = v1;
            Object v3_1 = arg3.getSystemService("window");
            if(v3_1 != null) {
                Display v3_2 = ((WindowManager)v3_1).getDefaultDisplay();
                if(v3_2 != null) {
                    v3_2.getMetrics(AndroidUtilities.displayMetrics);
                    v3_2.getSize(AndroidUtilities.displaySize);
                }
            }

            int v0 = 3;
            if(arg4.screenWidthDp != 0) {
                v3_3 = ((int)Math.ceil(((double)((((float)arg4.screenWidthDp)) * AndroidUtilities.density))));
                if(Math.abs(AndroidUtilities.displaySize.x - v3_3) > v0) {
                    AndroidUtilities.displaySize.x = v3_3;
                }
            }

            if(arg4.screenHeightDp != 0) {
                v3_3 = ((int)Math.ceil(((double)((((float)arg4.screenHeightDp)) * AndroidUtilities.density))));
                if(Math.abs(AndroidUtilities.displaySize.y - v3_3) > v0) {
                    AndroidUtilities.displaySize.y = v3_3;
                }
            }

            if(AndroidUtilities.roundMessageSize == 0) {
                float v4 = 0.6f;
                v3_3 = AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
                AndroidUtilities.roundMessageSize = ((int)((((float)v3_3)) * v4));
            }

            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("display size = " + AndroidUtilities.displaySize.x + " " + AndroidUtilities.displaySize.y + " " + AndroidUtilities.displayMetrics.xdpi + "x" + AndroidUtilities.displayMetrics.ydpi);
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    public static void checkForCrashes(Activity arg0) {
    }

    public static void checkForUpdates(Activity arg0) {
    }

    public static boolean checkPhonePattern(String arg6, String arg7) {
        if(!TextUtils.isEmpty(((CharSequence)arg6))) {
            if(arg6.equals("*")) {
            }
            else {
                String[] v6 = arg6.split("\\*");
                arg7 = b.b(arg7);
                int v2 = 0;
                int v3 = 0;
                while(v2 < v6.length) {
                    String v4 = v6[v2];
                    if(!TextUtils.isEmpty(((CharSequence)v4))) {
                        v3 = arg7.indexOf(v4, v3);
                        if(v3 == -1) {
                            return 0;
                        }
                        else {
                            v3 += v4.length();
                        }
                    }

                    ++v2;
                }
            }
        }

        return 1;
    }

    public static void clearCursorDrawable(EditText arg2) {
        if(arg2 == null) {
            return;
        }

        try {
            Field v0 = TextView.class.getDeclaredField("mCursorDrawableRes");
            v0.setAccessible(true);
            v0.setInt(arg2, 0);
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    @SuppressLint(value={"NewApi"}) public static void clearDrawableAnimation(View arg2) {
        Drawable v2;
        if(Build$VERSION.SDK_INT >= 21) {
            if(arg2 == null) {
            }
            else if((arg2 instanceof ListView)) {
                v2 = ((ListView)arg2).getSelector();
                if(v2 != null) {
                    v2.setState(StateSet.NOTHING);
                }
            }
            else {
                v2 = arg2.getBackground();
                if(v2 != null) {
                    v2.setState(StateSet.NOTHING);
                    v2.jumpToCurrentState();
                }
            }
        }
    }

    public static int compare(int arg0, int arg1) {
        if(arg0 == arg1) {
            return 0;
        }

        if(arg0 > arg1) {
            return 1;
        }

        return -1;
    }

    public static boolean copyFile(File arg8, File arg9) {
        FileOutputStream v8;
        FileInputStream v1;
        if(!arg9.exists()) {
            arg9.createNewFile();
        }

        FileInputStream v0 = null;
        try {
            v1 = new FileInputStream(arg8);
        }
        catch(Throwable v9) {
            v8 = ((FileOutputStream)v0);
            v1 = ((FileInputStream)v8);
            goto label_44;
        }
        catch(Exception v9_1) {
            v8 = ((FileOutputStream)v0);
            goto label_35;
        }

        try {
            v8 = new FileOutputStream(arg9);
        }
        catch(Throwable v9) {
            v8 = ((FileOutputStream)v0);
            goto label_44;
        }
        catch(Exception v9_1) {
            v8 = ((FileOutputStream)v0);
            goto label_27;
        }

        try {
            v8.getChannel().transferFrom(v1.getChannel(), 0, v1.getChannel().size());
            goto label_14;
        }
        catch(Throwable v9) {
        }
        catch(Exception v9_1) {
        label_27:
            v0 = v1;
            try {
            label_35:
                FileLog.e(((Throwable)v9_1));
                if(v0 == null) {
                    goto label_39;
                }
            }
            catch(Throwable v9) {
                v1 = v0;
                goto label_44;
            }

            v0.close();
        label_39:
            if(v8 != null) {
                v8.close();
            }

            return 0;
        }

    label_44:
        if(v1 != null) {
            v1.close();
        }

        if(v8 != null) {
            v8.close();
        }

        throw v9;
    label_14:
        v1.close();
        v8.close();
        return 1;
    }

    public static boolean copyFile(InputStream arg3, File arg4) {
        FileOutputStream v0 = new FileOutputStream(arg4);
        byte[] v4 = new byte[4096];
        while(true) {
            int v1 = arg3.read(v4);
            if(v1 <= 0) {
                break;
            }

            Thread.yield();
            ((OutputStream)v0).write(v4, 0, v1);
        }

        ((OutputStream)v0).close();
        return 1;
    }

    public static byte[] decodeQuotedPrintable(byte[] arg6) {
        byte[] v0 = null;
        if(arg6 == null) {
            return v0;
        }

        ByteArrayOutputStream v1 = new ByteArrayOutputStream();
        int v2;
        for(v2 = 0; v2 < arg6.length; ++v2) {
            int v3 = arg6[v2];
            if(v3 == 61) {
                ++v2;
                try {
                    v3 = Character.digit(((char)arg6[v2]), 16);
                    ++v2;
                    v1.write(((char)((v3 << 4) + Character.digit(((char)arg6[v2]), 16))));
                }
                catch(Exception v6) {
                    FileLog.e(((Throwable)v6));
                    return v0;
                }
            }
            else {
                v1.write(v3);
            }
        }

        arg6 = v1.toByteArray();
        try {
            v1.close();
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }

        return arg6;
    }

    public static int dp(float arg2) {
        if(arg2 == 0f) {
            return 0;
        }

        return ((int)Math.ceil(((double)(AndroidUtilities.density * arg2))));
    }

    public static int dp2(float arg2) {
        if(arg2 == 0f) {
            return 0;
        }

        return ((int)Math.floor(((double)(AndroidUtilities.density * arg2))));
    }

    public static float dpf2(float arg2) {
        if(arg2 == 0f) {
            return 0;
        }

        return AndroidUtilities.density * arg2;
    }

    public static void endIncomingCall() {
        if(!AndroidUtilities.hasCallPermissions) {
            return;
        }

        try {
            Object v0_1 = ApplicationLoader.applicationContext.getSystemService("phone");
            Method v1 = Class.forName(v0_1.getClass().getName()).getDeclaredMethod("getITelephony");
            v1.setAccessible(true);
            v1.invoke(v0_1);
            v0_1 = v1.invoke(v0_1);
            ((a)v0_1).b();
            ((a)v0_1).a();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static double fixLocationCoord(double arg2) {
        arg2 = ((double)(((long)(arg2 * 1000000))));
        Double.isNaN(arg2);
        return arg2 / 1000000;
    }

    public static String formapMapUrl(int arg18, double arg19, double arg21, int arg23, int arg24, boolean arg25, int arg26) {
        Object[] v6_1;
        Locale v2;
        int v4 = 2;
        int v3 = Math.min(v4, ((int)Math.ceil(((double)AndroidUtilities.density))));
        int v5 = MessagesController.getInstance(arg18).mapProvider;
        int v6 = 9;
        int v7 = 8;
        int v8 = 7;
        int v9 = 6;
        int v10 = 5;
        int v11 = 4;
        int v12 = 3;
        if(v5 != 1) {
            if(v5 == v12) {
            }
            else {
                String v5_1 = MessagesController.getInstance(arg18).mapKey;
                if(!TextUtils.isEmpty(((CharSequence)v5_1))) {
                    if(arg25) {
                        v2 = Locale.US;
                        v6_1 = new Object[v6];
                        v6_1[0] = Double.valueOf(arg19);
                        v6_1[1] = Double.valueOf(arg21);
                        v6_1[v4] = Integer.valueOf(arg26);
                        v6_1[v12] = Integer.valueOf(arg23);
                        v6_1[v11] = Integer.valueOf(arg24);
                        v6_1[v10] = Integer.valueOf(v3);
                        v6_1[v9] = Double.valueOf(arg19);
                        v6_1[v8] = Double.valueOf(arg21);
                        v6_1[v7] = v5_1;
                        return String.format(v2, "https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&markers=color:red%%7Csize:mid%%7C%.6f,%.6f&sensor=false&key=%s", v6_1);
                    }
                    else {
                        v2 = Locale.US;
                        Object[] v7_1 = new Object[v8];
                        v7_1[0] = Double.valueOf(arg19);
                        v7_1[1] = Double.valueOf(arg21);
                        v7_1[v4] = Integer.valueOf(arg26);
                        v7_1[v12] = Integer.valueOf(arg23);
                        v7_1[v11] = Integer.valueOf(arg24);
                        v7_1[v10] = Integer.valueOf(v3);
                        v7_1[v9] = v5_1;
                        return String.format(v2, "https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&key=%s", v7_1);
                    }
                }
                else if(arg25) {
                    v2 = Locale.US;
                    v6_1 = new Object[v7];
                    v6_1[0] = Double.valueOf(arg19);
                    v6_1[1] = Double.valueOf(arg21);
                    v6_1[v4] = Integer.valueOf(arg26);
                    v6_1[v12] = Integer.valueOf(arg23);
                    v6_1[v11] = Integer.valueOf(arg24);
                    v6_1[v10] = Integer.valueOf(v3);
                    v6_1[v9] = Double.valueOf(arg19);
                    v6_1[v8] = Double.valueOf(arg21);
                    return String.format(v2, "https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&markers=color:red%%7Csize:mid%%7C%.6f,%.6f&sensor=false", v6_1);
                }
                else {
                    v2 = Locale.US;
                    v6_1 = new Object[v9];
                    v6_1[0] = Double.valueOf(arg19);
                    v6_1[1] = Double.valueOf(arg21);
                    v6_1[v4] = Integer.valueOf(arg26);
                    v6_1[v12] = Integer.valueOf(arg23);
                    v6_1[v11] = Integer.valueOf(arg24);
                    v6_1[v10] = Integer.valueOf(v3);
                    return String.format(v2, "https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d", v6_1);
                }
            }
        }

        String[] v15 = new String[v4];
        v15[0] = "ru_RU";
        v15[1] = "tr_TR";
        LocaleInfo v5_2 = LocaleController.getInstance().getCurrentLocaleInfo();
        v7 = 0;
        String v17 = null;
        while(v7 < v15.length) {
            if(v15[v7].toLowerCase().contains(v5_2.shortName)) {
                v17 = v15[v7];
            }

            ++v7;
        }

        if(v17 == null) {
            v17 = "en_US";
        }

        if(arg25) {
            v2 = Locale.US;
            v6_1 = new Object[v6];
            v6_1[0] = Double.valueOf(arg21);
            v6_1[1] = Double.valueOf(arg19);
            v6_1[v4] = Integer.valueOf(arg26);
            v6_1[v12] = Integer.valueOf(arg23 * v3);
            v6_1[v11] = Integer.valueOf(arg24 * v3);
            v6_1[v10] = Integer.valueOf(v3);
            v6_1[6] = Double.valueOf(arg21);
            v6_1[7] = Double.valueOf(arg19);
            v6_1[8] = v17;
            return String.format(v2, "https://static-maps.yandex.ru/1.x/?ll=%.6f,%.6f&z=%d&size=%d,%d&l=map&scale=%d&pt=%.6f,%.6f,vkbkm&lang=%s", v6_1);
        }

        v2 = Locale.US;
        v6_1 = new Object[7];
        v6_1[0] = Double.valueOf(arg21);
        v6_1[1] = Double.valueOf(arg19);
        v6_1[v4] = Integer.valueOf(arg26);
        v6_1[v12] = Integer.valueOf(arg23 * v3);
        v6_1[v11] = Integer.valueOf(arg24 * v3);
        v6_1[v10] = Integer.valueOf(v3);
        v6_1[6] = v17;
        return String.format(v2, "https://static-maps.yandex.ru/1.x/?ll=%.6f,%.6f&z=%d&size=%d,%d&l=map&scale=%d&lang=%s", v6_1);
    }

    public static String formatFileSize(long arg6) {
        // Method was not decompiled
    }

    public static File generatePicturePath() {
        try {
            File v0_1 = AndroidUtilities.getAlbumDir();
            Date v1 = new Date();
            v1.setTime(System.currentTimeMillis() + (((long)Utilities.random.nextInt(1000))) + 1);
            String v1_1 = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(v1);
            StringBuilder v3 = new StringBuilder();
            v3.append("IMG_");
            v3.append(v1_1);
            v3.append(".jpg");
            return new File(v0_1, v3.toString());
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            return null;
        }
    }

    public static CharSequence generateSearchName(String arg7, String arg8, String arg9) {
        String v2_1;
        if(arg7 == null && arg8 == null) {
            return "";
        }

        SpannableStringBuilder v0 = new SpannableStringBuilder();
        if(arg7 == null || arg7.length() == 0) {
            arg7 = arg8;
        }
        else if(arg8 != null && arg8.length() != 0) {
            arg7 = arg7 + " " + arg8;
        }

        arg7 = arg7.trim();
        arg8 = " " + arg7.toLowerCase();
        int v2;
        for(v2 = 0; true; v2 = v3_1) {
            StringBuilder v3 = new StringBuilder();
            v3.append(" ");
            v3.append(arg9);
            int v3_1 = arg8.indexOf(v3.toString(), v2);
            int v4 = -1;
            if(v3_1 == v4) {
                break;
            }

            v4 = 1;
            int v5 = v3_1 == 0 ? 0 : 1;
            v5 = v3_1 - v5;
            int v6 = arg9.length();
            if(v3_1 == 0) {
                v4 = 0;
            }

            v3_1 = v6 + v4 + v5;
            if(v2 != 0 && v2 != v5 + 1) {
                v2_1 = arg7.substring(v2, v5);
                goto label_56;
            }
            else if(v2 == 0 && v5 != 0) {
                v2_1 = arg7.substring(0, v5);
            label_56:
                v0.append(((CharSequence)v2_1));
            }

            v2_1 = arg7.substring(v5, Math.min(arg7.length(), v3_1));
            if(v2_1.startsWith(" ")) {
                v0.append(" ");
            }

            v2_1 = v2_1.trim();
            v4 = v0.length();
            v0.append(((CharSequence)v2_1));
            v0.setSpan(new ForegroundColorSpan(Theme.getColor("windowBackgroundWhiteBlueText4")), v4, v2_1.length() + v4, 33);
        }

        if(v2 != v4 && v2 < arg7.length()) {
            v0.append(arg7.substring(v2, arg7.length()));
        }

        return ((CharSequence)v0);
    }

    public static File generateVideoPath() {
        try {
            File v0_1 = AndroidUtilities.getAlbumDir();
            Date v1 = new Date();
            v1.setTime(System.currentTimeMillis() + (((long)Utilities.random.nextInt(1000))) + 1);
            String v1_1 = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(v1);
            StringBuilder v3 = new StringBuilder();
            v3.append("VID_");
            v3.append(v1_1);
            v3.append(".mp4");
            return new File(v0_1, v3.toString());
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            return null;
        }
    }

    private static File getAlbumDir() {
        File v0;
        if(Build$VERSION.SDK_INT >= 23 && ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
            return FileLoader.getDirectory(4);
        }

        File v1 = null;
        if("mounted".equals(Environment.getExternalStorageState())) {
            v0 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Telegram");
            if(!v0.mkdirs() && !v0.exists()) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("failed to create directory");
                }

                return v1;
            }
        }
        else {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("External storage is not mounted READ/WRITE.");
            }

            v0 = v1;
        }

        return v0;
    }

    public static File getCacheDir() {
        File v0_2;
        String v0_1;
        try {
            v0_1 = Environment.getExternalStorageState();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            v0_1 = null;
        }

        if(v0_1 != null) {
            if(v0_1.startsWith("mounted")) {
                goto label_9;
            }

            goto label_15;
        }

        try {
        label_9:
            v0_2 = ApplicationLoader.applicationContext.getExternalCacheDir();
            if(v0_2 == null) {
                goto label_15;
            }
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            goto label_15;
        }

        return v0_2;
        try {
        label_15:
            v0_2 = ApplicationLoader.applicationContext.getCacheDir();
            if(v0_2 == null) {
                goto label_21;
            }
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            goto label_21;
        }

        return v0_2;
    label_21:
        return new File("");
    }

    public static String getDataColumn(Context arg7, Uri arg8, String arg9, String[] arg10) {
        String v8_1;
        Cursor v7;
        String[] v3 = new String[]{"_data"};
        String v0 = null;
        try {
            v7 = arg7.getContentResolver().query(arg8, v3, arg9, arg10, null);
            if(v7 == null) {
                goto label_36;
            }
        }
        catch(Throwable v8) {
            v7 = ((Cursor)v0);
            goto label_40;
        }
        catch(Exception ) {
            v7 = ((Cursor)v0);
            goto label_44;
        }

        try {
            if(v7.moveToFirst()) {
                v8_1 = v7.getString(v7.getColumnIndexOrThrow("_data"));
                if(!v8_1.startsWith("content://")) {
                    if(!v8_1.startsWith("/")) {
                        if(v8_1.startsWith("file://")) {
                            goto label_28;
                        }

                        goto label_27;
                    }

                    goto label_28;
                }

                goto label_31;
            }

            goto label_36;
        }
        catch(Throwable v8) {
        }
        catch(Exception ) {
        label_44:
            if(v7 == null) {
                return v0;
            }

            goto label_45;
        }

    label_40:
        if(v7 != null) {
            v7.close();
        }

        throw v8;
    label_27:
        goto label_31;
    label_28:
        if(v7 != null) {
            v7.close();
        }

        return v8_1;
    label_31:
        if(v7 != null) {
            v7.close();
        }

        return v0;
    label_36:
        if(v7 != null) {
        label_45:
            v7.close();
        }

        return v0;
    }

    public static int getMinTabletSide() {
        int v2;
        int v0;
        float v1 = 320f;
        if(!AndroidUtilities.isSmallTablet()) {
            v0 = Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
            v2 = v0 * 35 / 100;
            if(v2 < AndroidUtilities.dp(v1)) {
                v2 = AndroidUtilities.dp(v1);
            }

            return v0 - v2;
        }

        v0 = Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
        v2 = Math.max(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
        int v3 = v2 * 35 / 100;
        if(v3 < AndroidUtilities.dp(v1)) {
            v3 = AndroidUtilities.dp(v1);
        }

        return Math.min(v0, v2 - v3);
    }

    public static int getMyLayerVersion(int arg1) {
        return arg1 & 65535;
    }

    @SuppressLint(value={"NewApi"}) public static String getPath(Uri arg7) {
        Uri v1_3;
        String[] v7_1;
        String v0 = null;
        try {
            int v1 = Build$VERSION.SDK_INT >= 19 ? 1 : 0;
            if(v1 != 0 && (DocumentsContract.isDocumentUri(ApplicationLoader.applicationContext, arg7))) {
                if(AndroidUtilities.isExternalStorageDocument(arg7)) {
                    v7_1 = DocumentsContract.getDocumentId(arg7).split(":");
                    if("primary".equalsIgnoreCase(v7_1[0])) {
                        return Environment.getExternalStorageDirectory() + "/" + v7_1[1];
                    }
                    else {
                        return v0;
                    }
                }
                else if(AndroidUtilities.isDownloadsDocument(arg7)) {
                    return AndroidUtilities.getDataColumn(ApplicationLoader.applicationContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(arg7)).longValue()), v0, ((String[])v0));
                }
                else if(AndroidUtilities.isMediaDocument(arg7)) {
                    v7_1 = DocumentsContract.getDocumentId(arg7).split(":");
                    String v1_2 = v7_1[0];
                    int v5 = v1_2.hashCode();
                    if(v5 != 93166550) {
                        if(v5 != 100313435) {
                            if(v5 != 112202875) {
                                goto label_73;
                            }
                            else if(v1_2.equals("video")) {
                                v1 = 1;
                            }
                            else {
                                goto label_73;
                            }
                        }
                        else if(v1_2.equals("image")) {
                            v1 = 0;
                        }
                        else {
                            goto label_73;
                        }
                    }
                    else if(v1_2.equals("audio")) {
                        v1 = 2;
                    }
                    else {
                    label_73:
                        v1 = -1;
                    }

                    switch(v1) {
                        case 0: {
                            v1_3 = MediaStore$Images$Media.EXTERNAL_CONTENT_URI;
                            break;
                        }
                        case 1: {
                            v1_3 = MediaStore$Video$Media.EXTERNAL_CONTENT_URI;
                            break;
                        }
                        case 2: {
                            v1_3 = MediaStore$Audio$Media.EXTERNAL_CONTENT_URI;
                            break;
                        }
                        default: {
                            v1_3 = ((Uri)v0);
                            break;
                        }
                    }

                    return AndroidUtilities.getDataColumn(ApplicationLoader.applicationContext, v1_3, "_id=?", new String[]{v7_1[1]});
                }
                else {
                    return v0;
                }
            }

            if("content".equalsIgnoreCase(arg7.getScheme())) {
                return AndroidUtilities.getDataColumn(ApplicationLoader.applicationContext, arg7, v0, ((String[])v0));
            }

            if(!"file".equalsIgnoreCase(arg7.getScheme())) {
                return v0;
            }

            return arg7.getPath();
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
        }

        return v0;
    }

    public static int getPeerLayerVersion(int arg1) {
        return arg1 >> 16 & 65535;
    }

    public static int getPhotoSize() {
        if(AndroidUtilities.photoSize == null) {
            AndroidUtilities.photoSize = Integer.valueOf(1280);
        }

        return AndroidUtilities.photoSize.intValue();
    }

    public static float getPixelsInCM(float arg1, boolean arg2) {
        arg1 /= 2.54f;
        float v2 = arg2 ? AndroidUtilities.displayMetrics.xdpi : AndroidUtilities.displayMetrics.ydpi;
        return arg1 * v2;
    }

    public static Point getRealScreenSize() {
        Object v1_1;
        Point v0 = new Point();
        try {
            v1_1 = ApplicationLoader.applicationContext.getSystemService("window");
            if(Build$VERSION.SDK_INT < 17) {
                goto label_11;
            }

            ((WindowManager)v1_1).getDefaultDisplay().getRealSize(v0);
            return v0;
        }
        catch(Exception v1) {
            goto label_39;
        }

        try {
        label_11:
            v0.set(Display.class.getMethod("getRawWidth").invoke(((WindowManager)v1_1).getDefaultDisplay()).intValue(), Display.class.getMethod("getRawHeight").invoke(((WindowManager)v1_1).getDefaultDisplay()).intValue());
        }
        catch(Exception v2) {
            try {
                v0.set(((WindowManager)v1_1).getDefaultDisplay().getWidth(), ((WindowManager)v1_1).getDefaultDisplay().getHeight());
                FileLog.e(((Throwable)v2));
            }
            catch(Exception v1) {
            label_39:
                FileLog.e(((Throwable)v1));
            }
        }

        return v0;
    }

    public static byte[] getStringBytes(String arg1) {
        try {
            return arg1.getBytes("UTF-8");
        }
        catch(Exception ) {
            return new byte[0];
        }
    }

    public static String getSystemProperty(String arg7) {
        Object v0 = null;
        try {
            return Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(v0, arg7);
        }
        catch(Exception ) {
            return ((String)v0);
        }
    }

    public static CharSequence getTrimmedString(CharSequence arg5) {
        if(arg5 != null && arg5.length() != 0) {
            while(true) {
                int v1 = 32;
                int v2 = 10;
                if(arg5.length() > 0) {
                    if(arg5.charAt(0) != v2 && arg5.charAt(0) != v1) {
                        break;
                    }

                    arg5 = arg5.subSequence(1, arg5.length());
                    continue;
                }

                break;
            }

            while(arg5.length() > 0) {
                if(arg5.charAt(arg5.length() - 1) != v2 && arg5.charAt(arg5.length() - 1) != v1) {
                    return arg5;
                }

                arg5 = arg5.subSequence(0, arg5.length() - 1);
            }
        }

        return arg5;
    }

    public static Typeface getTypeface(String arg5) {
        Typeface v2_2;
        Typeface v0 = null;
        if(ApplicationLoader.USE_DEVICE_FONT) {
            return v0;
        }

        arg5 = FontUtil.a();
        Hashtable v1 = AndroidUtilities.typefaceCache;
        __monitor_enter(v1);
        try {
            if(!AndroidUtilities.typefaceCache.containsKey(arg5)) {
                try {
                    if(Build$VERSION.SDK_INT >= 26) {
                        Typeface$Builder v2_1 = new Typeface$Builder(ApplicationLoader.applicationContext.getAssets(), arg5);
                        if(arg5.contains("medium")) {
                            v2_1.setWeight(700);
                        }

                        if(arg5.contains("italic")) {
                            v2_1.setItalic(true);
                        }

                        v2_2 = v2_1.build();
                    }
                    else {
                        v2_2 = Typeface.createFromAsset(ApplicationLoader.applicationContext.getAssets(), arg5);
                    }

                    AndroidUtilities.typefaceCache.put(arg5, v2_2);
                    goto label_51;
                }
                catch(Exception v2) {
                    try {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.e("Could not get typeface \'" + arg5 + "\' because " + v2.getMessage());
                        }

                        __monitor_exit(v1);
                        return v0;
                    label_51:
                        __monitor_exit(v1);
                        return AndroidUtilities.typefaceCache.get(arg5);
                    label_56:
                        __monitor_exit(v1);
                        goto label_57;
                    }
                    catch(Throwable v5) {
                        goto label_56;
                    }
                }
            }

            goto label_51;
        }
        catch(Throwable v5) {
            goto label_56;
        }

    label_57:
        throw v5;
    }

    public static int getViewInset(View arg4) {
        if(arg4 != null && Build$VERSION.SDK_INT >= 21 && arg4.getHeight() != AndroidUtilities.displaySize.y && arg4.getHeight() != AndroidUtilities.displaySize.y - AndroidUtilities.statusBarHeight) {
            try {
                if(AndroidUtilities.mAttachInfoField == null) {
                    AndroidUtilities.mAttachInfoField = View.class.getDeclaredField("mAttachInfo");
                    AndroidUtilities.mAttachInfoField.setAccessible(true);
                }

                Object v4_1 = AndroidUtilities.mAttachInfoField.get(arg4);
                if(v4_1 == null) {
                    return 0;
                }

                if(AndroidUtilities.mStableInsetsField == null) {
                    AndroidUtilities.mStableInsetsField = v4_1.getClass().getDeclaredField("mStableInsets");
                    AndroidUtilities.mStableInsetsField.setAccessible(true);
                }

                return AndroidUtilities.mStableInsetsField.get(v4_1).bottom;
            }
            catch(Exception v4) {
                FileLog.e(((Throwable)v4));
            }
        }

        return 0;
    }

    public static boolean handleProxyIntent(Activity arg12, Intent arg13) {
        String v6;
        String v7;
        String v4;
        String v3;
        String v13_1;
        if(arg13 == null) {
            return 0;
        }

        try {
            if((arg13.getFlags() & 1048576) != 0) {
                return 0;
            }

            Uri v13 = arg13.getData();
            if(v13 != null) {
                String v1 = v13.getScheme();
                String v2 = null;
                if(v1 != null) {
                    if(!v1.equals("http")) {
                        if(v1.equals("https")) {
                        }
                        else if(v1.equals("tg")) {
                            v13_1 = v13.toString();
                            if(!v13_1.startsWith("tg:proxy") && !v13_1.startsWith("tg://proxy") && !v13_1.startsWith("tg:socks") && !v13_1.startsWith("tg://socks")) {
                                goto label_108;
                            }

                            v13 = Uri.parse(v13_1.replace("tg:proxy", "tg://telegram.org").replace("tg://proxy", "tg://telegram.org").replace("tg://socks", "tg://telegram.org").replace("tg:socks", "tg://telegram.org"));
                            v2 = v13.getQueryParameter("server");
                            v1 = v13.getQueryParameter("port");
                            v3 = v13.getQueryParameter("user");
                            v4 = v13.getQueryParameter("pass");
                            v13_1 = v13.getQueryParameter("secret");
                            v7 = v1;
                            v6 = v2;
                            v2 = v3;
                        label_112:
                            if(TextUtils.isEmpty(((CharSequence)v6))) {
                                return 0;
                            }

                            goto label_114;
                        }
                        else {
                            goto label_108;
                        }
                    }

                    v1 = v13.getHost().toLowerCase();
                    if((v1.equals("telegram.me")) || (v1.equals("t.me")) || (v1.equals("telegram.dog")) || (v1.equals("telesco.pe"))) {
                        v1 = v13.getPath();
                        if(v1 != null) {
                            if(!v1.startsWith("/socks") && !v1.startsWith("/proxy")) {
                                goto label_100;
                            }

                            v2 = v13.getQueryParameter("server");
                            v1 = v13.getQueryParameter("port");
                            v3 = v13.getQueryParameter("user");
                            v4 = v13.getQueryParameter("pass");
                            String v11 = v3;
                            v3 = v13.getQueryParameter("secret");
                            v13_1 = v2;
                            v2 = v11;
                        }
                        else {
                        label_100:
                            v13_1 = v2;
                            v1 = v13_1;
                            v3 = v1;
                            v4 = v3;
                        }
                    }
                    else {
                        goto label_100;
                    }

                    v6 = v13_1;
                    v7 = v1;
                    v13_1 = v3;
                    goto label_112;
                }

            label_108:
                v13_1 = v2;
                v4 = v13_1;
                CharSequence v6_1 = ((CharSequence)v4);
                CharSequence v7_1 = v6_1;
                goto label_112;
            label_114:
                if(TextUtils.isEmpty(v7_1)) {
                    return 0;
                }

                String v8 = v2 == null ? "" : v2;
                String v9 = v4 == null ? "" : v4;
                if(v13_1 == null) {
                    v13_1 = "";
                }

                AndroidUtilities.showProxyAlert(arg12, v6, ((String)v7_1), v8, v9, v13_1);
                return 1;
            }

            return 0;
        }
        catch(Exception ) {
            return 0;
        }
    }

    public static void hideKeyboard(View arg2) {
        if(arg2 == null) {
            return;
        }

        try {
            Object v0 = arg2.getContext().getSystemService("input_method");
            if(!((InputMethodManager)v0).isActive()) {
                return;
            }

            ((InputMethodManager)v0).hideSoftInputFromWindow(arg2.getWindowToken(), 0);
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    private static int[] hsvToRgb(double arg10, double arg12, double arg14) {
        arg10 *= 6;
        double v0 = ((double)(((int)Math.floor(arg10))));
        Double.isNaN(v0);
        arg10 -= v0;
        double v4 = (1 - arg12) * arg14;
        double v6 = (1 - arg10 * arg12) * arg14;
        arg10 = arg14 * (1 - (1 - arg10) * arg12);
        int v12 = (((int)v0)) % 6;
        v0 = 0;
        switch(v12) {
            case 0: {
                goto label_42;
            }
            case 1: {
                goto label_38;
            }
            case 2: {
                goto label_35;
            }
            case 3: {
                goto label_31;
            }
            case 4: {
                goto label_27;
            }
            case 5: {
                goto label_25;
            }
        }

        arg10 = v0;
        arg14 = arg10;
        v4 = arg14;
        goto label_45;
    label_35:
        double v8 = arg14;
        arg14 = v4;
        goto label_44;
    label_38:
        arg10 = v4;
        v4 = arg14;
        arg14 = v6;
        goto label_45;
    label_25:
        arg10 = v6;
        goto label_45;
    label_42:
        v8 = arg10;
        arg10 = v4;
    label_44:
        v4 = v8;
        goto label_45;
    label_27:
        v8 = arg10;
        arg10 = arg14;
        arg14 = v8;
        goto label_45;
    label_31:
        arg10 = arg14;
        arg14 = v4;
        v4 = v6;
    label_45:
        return new int[]{((int)(arg14 * 255)), ((int)(v4 * 255)), ((int)(arg10 * 255))};
    }

    public static boolean isBannedForever(int arg6) {
        boolean v6 = Math.abs((((long)arg6)) - System.currentTimeMillis() / 1000) > 157680000 ? true : false;
        return v6;
    }

    public static boolean isDownloadsDocument(Uri arg1) {
        return "com.android.providers.downloads.documents".equals(arg1.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri arg1) {
        return "com.android.externalstorage.documents".equals(arg1.getAuthority());
    }

    public static boolean isGoogleMapsInstalled(BaseFragment arg4) {
        try {
            ApplicationLoader.applicationContext.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            arg4 = null;
            return 1;
        }
        catch(PackageManager$NameNotFoundException ) {
            if(arg4.getParentActivity() == null) {
                return 0;
            }

            Builder v1 = new Builder(arg4.getParentActivity());
            v1.setMessage("Install Google Maps?");
            v1.setPositiveButton(LocaleController.getString("OK", 2131625420), new DialogInterface$OnClickListener(arg4) {
                public void onClick(DialogInterface arg2, int arg3) {
                    try {
                        this.val$fragment.getParentActivity().startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.apps.maps")), 500);
                    }
                    catch(Exception v2) {
                        FileLog.e(((Throwable)v2));
                    }
                }
            });
            v1.setNegativeButton(LocaleController.getString("Cancel", 2131624257), null);
            arg4.showDialog(v1.create());
            return 0;
        }
    }

    public static boolean isInternalUri(Uri arg3) {
        String v1;
        String v3 = arg3.getPath();
        boolean v0 = false;
        if(v3 == null) {
            return 0;
        }

        while(true) {
            v1 = Utilities.readlink(v3);
            if(v1 != null) {
                if(v1.equals(v3)) {
                }
                else {
                    v3 = v1;
                    continue;
                }
            }

            break;
        }

        if(v3 != null) {
            try {
                v1 = new File(v3).getCanonicalPath();
                if(v1 == null) {
                    goto label_21;
                }
            }
            catch(Exception ) {
                v3.replace("/./", "/");
                goto label_21;
            }

            v3 = v1;
        }

    label_21:
        if(v3 != null) {
            v3 = v3.toLowerCase();
            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("/data/data/");
            v1_1.append(ApplicationLoader.applicationContext.getPackageName());
            v1_1.append("/files");
            if(v3.contains(v1_1.toString())) {
                v0 = true;
            }
        }

        return v0;
    }

    public static boolean isKeyboardShowed(View arg3) {
        if(arg3 == null) {
            return 0;
        }

        try {
            return arg3.getContext().getSystemService("input_method").isActive(arg3);
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
            return 0;
        }
    }

    public static boolean isMediaDocument(Uri arg1) {
        return "com.android.providers.media.documents".equals(arg1.getAuthority());
    }

    public static boolean isSmallTablet() {
        boolean v0 = (((float)Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y))) / AndroidUtilities.density <= 700f ? true : false;
        return v0;
    }

    public static boolean isTablet() {
        if(AndroidUtilities.isTablet == null) {
            AndroidUtilities.isTablet = Boolean.valueOf(ApplicationLoader.applicationContext.getResources().getBoolean(2131034124));
        }

        return AndroidUtilities.isTablet.booleanValue();
    }

    public static boolean isWaitingForCall() {
        Object v0 = AndroidUtilities.callLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return AndroidUtilities.waitingForCall;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public static boolean isWaitingForSms() {
        Object v0 = AndroidUtilities.smsLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return AndroidUtilities.waitingForSms;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public static ArrayList loadVCardFromStream(Uri arg19, int arg20, boolean arg21, ArrayList arg22, String arg23) {
        ArrayList v2_2;
        String v18;
        String v17;
        String[] v2_1;
        String[] v15;
        int v14_1;
        VcardItem v7_2;
        int v13;
        String v11;
        int v7;
        VcardItem v10;
        String v9;
        VcardData v8;
        int v6;
        int v5;
        BufferedReader v4;
        ArrayList v3;
        InputStream v0_3;
        Uri v0 = arg19;
        ArrayList v1 = arg22;
        org.telegram.messenger.AndroidUtilities$1 v2 = null;
        if(arg21) {
            try {
                FileInputStream v0_2 = ApplicationLoader.applicationContext.getContentResolver().openAssetFileDescriptor(v0, "r").createInputStream();
                goto label_15;
            }
            catch(Throwable v0_1) {
                goto label_11;
            }
        }
        else {
            try {
                v0_3 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(v0);
            label_15:
                v3 = new ArrayList();
                v4 = new BufferedReader(new InputStreamReader(v0_3, "UTF-8"));
                v5 = 1;
                v6 = 0;
                v8 = ((VcardData)v2);
                v9 = ((String)v8);
                v10 = ((VcardItem)v9);
                v7 = 0;
                while(true) {
                label_28:
                    v11 = v4.readLine();
                    if(v11 == null) {
                        goto label_288;
                    }

                    if(v11.startsWith("PHOTO")) {
                        v7 = 1;
                        continue;
                    }

                    v13 = 2;
                    if(v11.indexOf(58) >= 0) {
                        goto label_39;
                    }
                    else {
                        goto label_154;
                    }
                }
            }
            catch(Throwable v0_1) {
                goto label_347;
            }
        }

        goto label_15;
        try {
        label_39:
            if(v11.startsWith("BEGIN:VCARD")) {
                VcardData v7_1 = new VcardData(v2);
                v3.add(v7_1);
                v7_1.name = arg23;
                v10 = ((VcardItem)v2);
                v8 = v7_1;
            }
            else {
                if(v11.startsWith("END:VCARD")) {
                }
                else if(v1 != null) {
                    if(v11.startsWith("TEL")) {
                        v7_2 = new VcardItem();
                        v7_2.type = v6;
                    }
                    else if(v11.startsWith("EMAIL")) {
                        v7_2 = new VcardItem();
                        v7_2.type = v5;
                    }
                    else {
                        if(!v11.startsWith("ADR") && !v11.startsWith("LABEL")) {
                            if(v11.startsWith("GEO")) {
                            }
                            else if(v11.startsWith("URL")) {
                                v7_2 = new VcardItem();
                                v7_2.type = 3;
                                goto label_144;
                            }
                            else {
                                if(v11.startsWith("NOTE")) {
                                    v7_2 = new VcardItem();
                                    v7_2.type = 4;
                                }
                                else if(v11.startsWith("BDAY")) {
                                    v7_2 = new VcardItem();
                                    v7_2.type = 5;
                                }
                                else {
                                    if(!v11.startsWith("ORG") && !v11.startsWith("TITLE")) {
                                        if(v11.startsWith("ROLE")) {
                                        }
                                        else {
                                            if(v11.startsWith("X-ANDROID")) {
                                                v7_2 = new VcardItem();
                                                v7_2.type = -1;
                                            }
                                            else {
                                                if(v11.startsWith("X-PHONETIC")) {
                                                }
                                                else if(v11.startsWith("X-")) {
                                                    v7_2 = new VcardItem();
                                                    v7_2.type = 20;
                                                    goto label_144;
                                                }

                                                v7_2 = ((VcardItem)v2);
                                            }

                                            goto label_144;
                                        }
                                    }

                                    v7_2 = new VcardItem();
                                    v7_2.type = 6;
                                }

                                goto label_144;
                            }
                        }

                        v7_2 = new VcardItem();
                        v7_2.type = v13;
                    }

                label_144:
                    if(v7_2 != null && v7_2.type >= 0) {
                        v1.add(v7_2);
                    }

                    v10 = v7_2;
                    goto label_151;
                }

                v10 = ((VcardItem)v2);
            }

        label_151:
            v7 = 0;
        label_154:
            if(v7 == 0 && v8 != null) {
                if(v10 == null) {
                    if(v8.vcard.length() > 0) {
                        v8.vcard.append('\n');
                    }

                    v8.vcard.append(v11);
                }
                else {
                    v10.vcardData.add(v11);
                }
            }

            if(v9 != null) {
                v11 = v9 + v11;
                v9 = ((String)v2);
            }
        }
        catch(Throwable v0_1) {
            goto label_11;
        }

        try {
            if(v11.contains("=QUOTED-PRINTABLE")) {
                goto label_178;
            }

            goto label_185;
        }
        catch(Throwable v0_1) {
            goto label_347;
        }

        try {
        label_178:
            if(v11.endsWith("=")) {
                v9 = v11.substring(v6, v11.length() - v5);
                goto label_28;
            }

        label_185:
            if(v7 == 0 && v8 != null && v10 != null) {
                v10.fullData = v11;
            }
        }
        catch(Throwable v0_1) {
            goto label_11;
        }

        try {
            v14_1 = v11.indexOf(":");
            if(v14_1 >= 0) {
                v15 = new String[v13];
                v15[v6] = v11.substring(v6, v14_1);
                v15[v5] = v11.substring(v14_1 + 1, v11.length()).trim();
            }
            else {
                v15 = new String[v5];
                v15[v6] = v11.trim();
            }

            if(v15.length >= v13) {
                if(v8 == null) {
                }
                else {
                    if(!v15[v6].startsWith("FN") && (!v15[v6].startsWith("ORG") || !TextUtils.isEmpty(v8.name))) {
                        if(!v15[v6].startsWith("TEL")) {
                            goto label_284;
                        }

                        v8.phones.add(v15[v5]);
                        goto label_284;
                    }

                    v2_1 = v15[v6].split(";");
                    int v11_1 = v2_1.length;
                    v14_1 = 0;
                    v17 = null;
                    v18 = null;
                    goto label_235;
                }
            }

            goto label_284;
        }
        catch(Throwable v0_1) {
            goto label_347;
        }

        try {
        label_288:
            v4.close();
            v0_3.close();
            goto label_293;
        }
        catch(Throwable v0_1) {
        label_347:
            v2_2 = null;
        }
        catch(Exception v0_4) {
            try {
                FileLog.e(((Throwable)v0_4));
            }
            catch(Throwable v0_1) {
                goto label_347;
            }

        label_293:
            int v0_5 = 0;
            v2_2 = null;
            goto label_295;
            try {
            label_235:
                while(v14_1 < v11_1) {
                    String[] v5_1 = v2_1[v14_1].split("=");
                    if(v5_1.length != v13) {
                    }
                    else if(v5_1[0].equals("CHARSET")) {
                        v18 = v5_1[1];
                    }
                    else if(v5_1[0].equals("ENCODING")) {
                        v17 = v5_1[1];
                    }

                    ++v14_1;
                    v13 = 2;
                }

                v8.name = v15[1];
                String v5_2 = v17;
                if(v5_2 == null) {
                    goto label_284;
                }

                if(!v5_2.equalsIgnoreCase("QUOTED-PRINTABLE")) {
                    goto label_284;
                }

                byte[] v5_3 = AndroidUtilities.decodeQuotedPrintable(AndroidUtilities.getStringBytes(v8.name));
                if(v5_3 == null) {
                    goto label_284;
                }

                if(v5_3.length == 0) {
                    goto label_284;
                }

                v8.name = new String(v5_3, v18);
            }
            catch(Throwable v0_1) {
                goto label_347;
            }

        label_284:
            v2 = null;
            v5 = 1;
            v6 = 0;
            goto label_28;
            try {
                while(true) {
                label_295:
                    if(v0_5 >= v3.size()) {
                        return v2_2;
                    }

                    Object v1_1 = v3.get(v0_5);
                    if(((VcardData)v1_1).name != null && !((VcardData)v1_1).phones.isEmpty()) {
                        if(v2_2 == null) {
                            v2_2 = new ArrayList();
                        }

                        Object v4_1 = ((VcardData)v1_1).phones.get(0);
                        v5 = 0;
                        while(v5 < ((VcardData)v1_1).phones.size()) {
                            Object v6_1 = ((VcardData)v1_1).phones.get(v5);
                            if(ContactsController.getInstance(arg20).contactsByShortPhone.get(((String)v6_1).substring(Math.max(0, ((String)v6_1).length() - 7))) != null) {
                                v4_1 = v6_1;
                            }
                            else {
                                ++v5;
                                continue;
                            }

                            break;
                        }

                        TL_userContact_old2 v5_4 = new TL_userContact_old2();
                        ((User)v5_4).phone = ((String)v4_1);
                        ((User)v5_4).first_name = ((VcardData)v1_1).name;
                        ((User)v5_4).last_name = "";
                        ((User)v5_4).id = 0;
                        ((User)v5_4).restriction_reason = ((VcardData)v1_1).vcard.toString();
                        v2_2.add(v5_4);
                    }

                    break;
                }
            }
            catch(Throwable v0_1) {
                goto label_11;
            }

            ++v0_5;
            goto label_295;
        label_11:
        }

        FileLog.e(v0_1);
        return v2_2;
    }

    public static void lockOrientation(Activity arg7) {
        int v5;
        int v3;
        if(arg7 != null && AndroidUtilities.prevOrientation == -10) {
            try {
                AndroidUtilities.prevOrientation = arg7.getRequestedOrientation();
                Object v0 = arg7.getSystemService("window");
                if(v0 == null) {
                    return;
                }

                if(((WindowManager)v0).getDefaultDisplay() == null) {
                    return;
                }

                int v0_1 = ((WindowManager)v0).getDefaultDisplay().getRotation();
                int v1 = arg7.getResources().getConfiguration().orientation;
                v3 = 8;
                if(v0_1 == 3) {
                    if(v1 != 1) {
                        goto label_24;
                    }

                    goto label_22;
                }
                else {
                    v5 = 9;
                    if(v0_1 != 1) {
                        goto label_34;
                    }
                    else if(v1 != 1) {
                        goto label_32;
                    }

                    goto label_30;
                }
            }
            catch(Exception v7) {
                goto label_41;
            }

        label_34:
            int v6 = 2;
            if(v0_1 == 0) {
                if(v1 == v6) {
                    try {
                    label_32:
                        arg7.setRequestedOrientation(0);
                        return;
                    label_22:
                        arg7.setRequestedOrientation(1);
                        return;
                    }
                    catch(Exception v7) {
                        goto label_41;
                    }
                }
                else {
                    goto label_22;
                }
            }
            else if(v1 == v6) {
                try {
                label_24:
                    arg7.setRequestedOrientation(v3);
                    return;
                label_30:
                    arg7.setRequestedOrientation(v5);
                }
                catch(Exception v7) {
                label_41:
                    FileLog.e(((Throwable)v7));
                }

                return;
            }

            goto label_30;
        }
    }

    public static long makeBroadcastId(int arg4) {
        return (((long)arg4)) & 4294967295L | 4294967296L;
    }

    public static boolean needShowPasscode(boolean arg1) {
        boolean v0 = ForegroundDetector.getInstance().isWasInBackground(arg1);
        if(arg1) {
            ForegroundDetector.getInstance().resetBackgroundVar();
        }

        if(SharedConfig.passcodeHash.length() <= 0 || !v0) {
        label_32:
            arg1 = false;
        }
        else {
            if(!SharedConfig.appLocked && (SharedConfig.autoLockIn == 0 || SharedConfig.lastPauseTime == 0 || (SharedConfig.appLocked) || SharedConfig.lastPauseTime + SharedConfig.autoLockIn > ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime()) && ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime() + 5 >= SharedConfig.lastPauseTime) {
                goto label_32;
            }

            arg1 = true;
        }

        return arg1;
    }

    public static String obtainLoginPhoneCall(String arg10) {
        String v2;
        Cursor v0;
        String v1 = null;
        if(!AndroidUtilities.hasCallPermissions) {
            return v1;
        }

        try {
            v0 = ApplicationLoader.applicationContext.getContentResolver().query(CallLog$Calls.CONTENT_URI, new String[]{"number", "date"}, "type IN (3,1,5)", null, "date DESC LIMIT 5");
        }
        catch(Throwable v10) {
            v0 = ((Cursor)v1);
            goto label_58;
        }
        catch(Exception v10_1) {
            v0 = ((Cursor)v1);
            goto label_53;
        }

        try {
            do {
                if(!v0.moveToNext()) {
                    goto label_44;
                }

                v2 = v0.getString(0);
                long v3 = v0.getLong(1);
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("number = " + v2);
                }

                if(Math.abs(System.currentTimeMillis() - v3) >= 3600000) {
                    continue;
                }

                if(!AndroidUtilities.checkPhonePattern(arg10, v2)) {
                    continue;
                }

                goto label_41;
            }
            while(true);
        }
        catch(Throwable v10) {
        }
        catch(Exception v10_1) {
            goto label_53;
        label_41:
            if(v0 != null) {
                v0.close();
            }

            return v2;
        label_44:
            if(v0 == null) {
                return v1;
            }

            goto label_55;
            try {
            label_53:
                FileLog.e(((Throwable)v10_1));
                if(v0 == null) {
                    return v1;
                }
            }
            catch(Throwable v10) {
                goto label_58;
            }

        label_55:
            v0.close();
            return v1;
        }

    label_58:
        if(v0 != null) {
            v0.close();
        }

        throw v10;
    }

    public static void openForView(MessageObject arg8, Activity arg9) {
        String v4;
        Uri v0_1;
        String v8;
        String v0 = arg8.getFileName();
        DialogInterface$OnClickListener v2 = null;
        File v1 = arg8.messageOwner.attachPath == null || arg8.messageOwner.attachPath.length() == 0 ? ((File)v2) : new File(arg8.messageOwner.attachPath);
        if(v1 == null || !v1.exists()) {
            v1 = FileLoader.getPathToMessage(arg8.messageOwner);
        }

        if(v1 != null && (v1.exists())) {
            Intent v3 = new Intent("android.intent.action.VIEW");
            v3.setFlags(1);
            MimeTypeMap v5 = MimeTypeMap.getSingleton();
            int v6 = v0.lastIndexOf(46);
            if(v6 != -1) {
                v0 = v5.getMimeTypeFromExtension(v0.substring(v6 + 1).toLowerCase());
                if(v0 == null) {
                    v8 = arg8.type == 9 || arg8.type == 0 ? arg8.getDocument().mime_type : v0;
                    if(v8 == null) {
                        goto label_54;
                    }

                    if(v8.length() != 0) {
                        goto label_55;
                    }

                    goto label_54;
                }
                else {
                    v8 = v0;
                }
            }
            else {
            label_54:
                v8 = ((String)v2);
            }

        label_55:
            if(Build$VERSION.SDK_INT >= 26 && v8 != null && (v8.equals("application/vnd.android.package-archive")) && !ApplicationLoader.applicationContext.getPackageManager().canRequestPackageInstalls()) {
                Builder v8_1 = new Builder(((Context)arg9));
                v8_1.setTitle(LocaleController.getString("AppName", 2131624086));
                v8_1.setMessage(LocaleController.getString("ApkRestricted", 2131624085));
                v8_1.setPositiveButton(LocaleController.getString("PermissionOpenSettings", 2131625741), new DialogInterface$OnClickListener(arg9) {
                    @TargetApi(value=26) public void onClick(DialogInterface arg4, int arg5) {
                        try {
                            Activity v4_1 = this.val$activity;
                            StringBuilder v1 = new StringBuilder();
                            v1.append("package:");
                            v1.append(this.val$activity.getPackageName());
                            v4_1.startActivity(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse(v1.toString())));
                        }
                        catch(Exception v4) {
                            FileLog.e(((Throwable)v4));
                        }
                    }
                });
                v8_1.setNegativeButton(LocaleController.getString("Cancel", 2131624257), v2);
                v8_1.show();
                return;
            }

            int v2_1 = 24;
            if(Build$VERSION.SDK_INT >= v2_1) {
                v0_1 = FileProvider.a(((Context)arg9), "org.ir.talaeii.provider", v1);
                if(v8 == null) {
                    goto label_96;
                }

                goto label_94;
            }
            else {
                v0_1 = Uri.fromFile(v1);
                if(v8 != null) {
                label_94:
                    v4 = v8;
                }
                else {
                label_96:
                    v4 = "text/plain";
                }
            }

            v3.setDataAndType(v0_1, v4);
            int v0_2 = 500;
            if(v8 != null) {
                try {
                    arg9.startActivityForResult(v3, v0_2);
                    return;
                }
                catch(Exception ) {
                    Uri v8_2 = Build$VERSION.SDK_INT >= v2_1 ? FileProvider.a(((Context)arg9), "org.ir.talaeii.provider", v1) : Uri.fromFile(v1);
                    v3.setDataAndType(v8_2, "text/plain");
                }
            }

            arg9.startActivityForResult(v3, v0_2);
        }
    }

    public static void openForView(TLObject arg8, Activity arg9) {
        String v1;
        Uri v8_1;
        if(arg8 != null && arg9 != null) {
            String v0 = FileLoader.getAttachFileName(arg8);
            File v2 = FileLoader.getPathToAttach(arg8, true);
            if(v2 != null && (v2.exists())) {
                Intent v3 = new Intent("android.intent.action.VIEW");
                v3.setFlags(1);
                MimeTypeMap v4 = MimeTypeMap.getSingleton();
                int v5 = v0.lastIndexOf(46);
                String v7 = null;
                if(v5 != -1) {
                    v0 = v4.getMimeTypeFromExtension(v0.substring(v5 + 1).toLowerCase());
                    if(v0 == null) {
                        String v8 = (arg8 instanceof TL_document) ? ((TL_document)arg8).mime_type : v0;
                        if(v8 == null) {
                            goto label_36;
                        }

                        if(v8.length() == 0) {
                            goto label_36;
                        }

                        v7 = v8;
                    }
                    else {
                        v7 = v0;
                    }
                }

            label_36:
                int v0_1 = 24;
                if(Build$VERSION.SDK_INT >= v0_1) {
                    v8_1 = FileProvider.a(((Context)arg9), "org.ir.talaeii.provider", v2);
                    if(v7 == null) {
                        goto label_44;
                    }

                    goto label_42;
                }
                else {
                    v8_1 = Uri.fromFile(v2);
                    if(v7 != null) {
                    label_42:
                        v1 = v7;
                    }
                    else {
                    label_44:
                        v1 = "text/plain";
                    }
                }

                v3.setDataAndType(v8_1, v1);
                int v8_2 = 500;
                if(v7 != null) {
                    try {
                        arg9.startActivityForResult(v3, v8_2);
                        return;
                    }
                    catch(Exception ) {
                        Uri v0_2 = Build$VERSION.SDK_INT >= v0_1 ? FileProvider.a(((Context)arg9), "org.ir.talaeii.provider", v2) : Uri.fromFile(v2);
                        v3.setDataAndType(v0_2, "text/plain");
                    }
                }

                arg9.startActivityForResult(v3, v8_2);
            }
        }
    }

    private static void registerLoginContentObserver(boolean arg4, String arg5) {
        if(arg4) {
            if(AndroidUtilities.callLogContentObserver != null) {
                return;
            }
            else {
                ContentResolver v4 = ApplicationLoader.applicationContext.getContentResolver();
                Uri v0 = CallLog$Calls.CONTENT_URI;
                org.telegram.messenger.AndroidUtilities$2 v2 = new ContentObserver(new Handler(), arg5) {
                    public boolean deliverSelfNotifications() {
                        return 1;
                    }

                    public void onChange(boolean arg2) {
                        AndroidUtilities.registerLoginContentObserver(false, this.val$number);
                        AndroidUtilities.removeLoginPhoneCall(this.val$number, false);
                    }
                };
                AndroidUtilities.callLogContentObserver = ((ContentObserver)v2);
                v4.registerContentObserver(v0, true, ((ContentObserver)v2));
                org.telegram.messenger.AndroidUtilities$3 v4_1 = new Runnable(arg5) {
                    public void run() {
                        AndroidUtilities.unregisterRunnable = null;
                        AndroidUtilities.registerLoginContentObserver(false, this.val$number);
                    }
                };
                AndroidUtilities.unregisterRunnable = ((Runnable)v4_1);
                AndroidUtilities.runOnUIThread(((Runnable)v4_1), 10000);
            }
        }
        else if(AndroidUtilities.callLogContentObserver == null) {
            return;
        }
        else {
            Runnable v5 = null;
            if(AndroidUtilities.unregisterRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(AndroidUtilities.unregisterRunnable);
                AndroidUtilities.unregisterRunnable = v5;
            }

            try {
                ApplicationLoader.applicationContext.getContentResolver().unregisterContentObserver(AndroidUtilities.callLogContentObserver);
                goto label_37;
            }
            catch(Exception ) {
            label_37:
                AndroidUtilities.callLogContentObserver = ((ContentObserver)v5);
            }
            catch(Throwable v4_2) {
                AndroidUtilities.callLogContentObserver = ((ContentObserver)v5);
                throw v4_2;
            }
        }
    }

    public static void removeAdjustResize(Activity arg1, int arg2) {
        if(arg1 != null) {
            if(AndroidUtilities.isTablet()) {
            }
            else if(AndroidUtilities.adjustOwnerClassGuid == arg2) {
                arg1.getWindow().setSoftInputMode(32);
            }
        }
    }

    public static void removeLoginPhoneCall(String arg10, boolean arg11) {
        Cursor v1;
        int v8;
        if(!AndroidUtilities.hasCallPermissions) {
            return;
        }

        Cursor v0 = null;
        try {
            ContentResolver v2 = ApplicationLoader.applicationContext.getContentResolver();
            Uri v3 = CallLog$Calls.CONTENT_URI;
            String[] v4 = new String[2];
            v8 = 0;
            v4[0] = "_id";
            v4[1] = "number";
            v1 = v2.query(v3, v4, "type IN (3,1,5)", null, "date DESC LIMIT 5");
            goto label_19;
        }
        catch(Throwable v10) {
        }
        catch(Exception v10_1) {
            goto label_51;
            try {
                do {
                label_19:
                    if(v1.moveToNext()) {
                        String v0_1 = v1.getString(1);
                        if(!v0_1.contains(((CharSequence)arg10)) && !arg10.contains(((CharSequence)v0_1))) {
                            continue;
                        }

                        break;
                    }

                    goto label_36;
                }
                while(true);

                ApplicationLoader.applicationContext.getContentResolver().delete(CallLog$Calls.CONTENT_URI, "_id = ? ", new String[]{String.valueOf(v1.getInt(0))});
                v8 = 1;
            label_36:
                if(v8 == 0 && (arg11)) {
                    AndroidUtilities.registerLoginContentObserver(true, arg10);
                }
            }
            catch(Throwable v10) {
                goto label_55;
            }
            catch(Exception v10_1) {
                v0 = v1;
                goto label_51;
            }

            if(v1 == null) {
                return;
            }

            v1.close();
            return;
            try {
            label_51:
                FileLog.e(((Throwable)v10_1));
                if(v0 == null) {
                    return;
                }
            }
            catch(Throwable v10) {
                v1 = v0;
                goto label_55;
            }
        }

        v0.close();
        return;
    label_55:
        if(v1 != null) {
            v1.close();
        }

        throw v10;
    }

    public static SpannableStringBuilder replaceTags(String arg2) {
        return AndroidUtilities.replaceTags(arg2, 11, new Object[0]);
    }

    public static SpannableStringBuilder replaceTags(String arg6, int arg7, Object[] arg8) {
        SpannableStringBuilder v7_1;
        int v3;
        int v2;
        int v0;
        try {
            StringBuilder v8 = new StringBuilder(arg6);
            int v1 = -1;
            if((arg7 & 1) != 0) {
                while(true) {
                    v0 = v8.indexOf("<br>");
                    if(v0 != v1) {
                        v8.replace(v0, v0 + 4, "\n");
                        continue;
                    }

                    break;
                }

                while(true) {
                    v0 = v8.indexOf("<br/>");
                    if(v0 == v1) {
                        break;
                    }

                    v8.replace(v0, v0 + 5, "\n");
                }
            }

            ArrayList v0_1 = new ArrayList();
            if((arg7 & 2) != 0) {
                while(true) {
                    v2 = v8.indexOf("<b>");
                    if(v2 != v1) {
                        v8.replace(v2, v2 + 3, "");
                        v3 = v8.indexOf("</b>");
                        if(v3 == v1) {
                            v3 = v8.indexOf("<b>");
                        }

                        v8.replace(v3, v3 + 4, "");
                        v0_1.add(Integer.valueOf(v2));
                        v0_1.add(Integer.valueOf(v3));
                        continue;
                    }

                    break;
                }

                while(true) {
                    v2 = v8.indexOf("**");
                    if(v2 == v1) {
                        break;
                    }

                    v8.replace(v2, v2 + 2, "");
                    v3 = v8.indexOf("**");
                    if(v3 < 0) {
                        continue;
                    }

                    v8.replace(v3, v3 + 2, "");
                    v0_1.add(Integer.valueOf(v2));
                    v0_1.add(Integer.valueOf(v3));
                }
            }

            if((arg7 & 8) != 0) {
                while(true) {
                    arg7 = v8.indexOf("**");
                    if(arg7 != v1) {
                        v8.replace(arg7, arg7 + 2, "");
                        v2 = v8.indexOf("**");
                        if(v2 < 0) {
                            continue;
                        }

                        v8.replace(v2, v2 + 2, "");
                        v0_1.add(Integer.valueOf(arg7));
                        v0_1.add(Integer.valueOf(v2));
                        continue;
                    }

                    break;
                }
            }

            v7_1 = new SpannableStringBuilder(((CharSequence)v8));
            int v8_1;
            for(v8_1 = 0; v8_1 < v0_1.size() / 2; ++v8_1) {
                v2 = v8_1 * 2;
                v7_1.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")), v0_1.get(v2).intValue(), v0_1.get(v2 + 1).intValue(), 33);
            }
        }
        catch(Exception v7) {
            goto label_100;
        }

        return v7_1;
    label_100:
        FileLog.e(((Throwable)v7));
        return new SpannableStringBuilder(((CharSequence)arg6));
    }

    public static void requestAdjustResize(Activity arg1, int arg2) {
        if(arg1 != null) {
            if(AndroidUtilities.isTablet()) {
            }
            else {
                arg1.getWindow().setSoftInputMode(16);
                AndroidUtilities.adjustOwnerClassGuid = arg2;
            }
        }
    }

    private static double[] rgbToHsv(int arg17, int arg18, int arg19) {
        double v8;
        double v0 = ((double)arg17);
        Double.isNaN(v0);
        v0 /= 255;
        double v4 = ((double)arg18);
        Double.isNaN(v4);
        v4 /= 255;
        double v6 = ((double)arg19);
        Double.isNaN(v6);
        double v2 = v6 / 255;
        if(v0 > v4 && v0 > v2) {
            v6 = v0;
        }
        else if(v4 > v2) {
            v6 = v4;
        }
        else {
            v6 = v2;
        }

        if(v0 < v4 && v0 < v2) {
            v8 = v0;
        }
        else if(v4 < v2) {
            v8 = v4;
        }
        else {
            v8 = v2;
        }

        double v10 = v6 - v8;
        double v12 = 0;
        double v14 = v6 == v12 ? v12 : v10 / v6;
        if(Double.compare(v6, v8) == 0) {
        }
        else {
            if(v0 <= v4 || v0 <= v2) {
                if(v4 > v2) {
                    v0 = 2 + (v2 - v0) / v10;
                    goto label_62;
                }

                v0 = (v0 - v4) / v10;
                v2 = 4;
            label_61:
                v0 += v2;
            }
            else {
                v0 = (v4 - v2) / v10;
                int v2_1 = v4 < v2 ? 6 : 0;
                v2 = ((double)v2_1);
                Double.isNaN(v2);
                goto label_61;
            }

        label_62:
            v12 = v0 / 6;
        }

        return new double[]{v12, v14, v6};
    }

    public static void runOnUIThread(Runnable arg3, long arg4) {
        if(arg4 == 0) {
            ApplicationLoader.applicationHandler.post(arg3);
        }
        else {
            ApplicationLoader.applicationHandler.postDelayed(arg3, arg4);
        }
    }

    public static void runOnUIThread(Runnable arg2) {
        AndroidUtilities.runOnUIThread(arg2, 0);
    }

    public static void setEnabled(View arg2, boolean arg3) {
        if(arg2 == null) {
            return;
        }

        arg2.setEnabled(arg3);
        if((arg2 instanceof ViewGroup)) {
            int v0;
            for(v0 = 0; v0 < ((ViewGroup)arg2).getChildCount(); ++v0) {
                AndroidUtilities.setEnabled(((ViewGroup)arg2).getChildAt(v0), arg3);
            }
        }
    }

    public static int setMyLayerVersion(int arg1, int arg2) {
        return arg1 & -65536 | arg2;
    }

    public static int setPeerLayerVersion(int arg1, int arg2) {
        return arg1 & 65535 | arg2 << 16;
    }

    public static void setRectToRect(Matrix arg6, RectF arg7, RectF arg8, int arg9, Matrix$ScaleToFit arg10) {
        float v3;
        float v2;
        int v0 = 270;
        int v1 = 90;
        if(arg9 == v1 || arg9 == v0) {
            v2 = arg8.height() / arg7.width();
            v3 = arg8.width();
        }
        else {
            v2 = arg8.width() / arg7.width();
            v3 = arg8.height();
        }

        v3 /= arg7.height();
        if(arg10 != Matrix$ScaleToFit.FILL) {
            if(v2 > v3) {
                v2 = v3;
            }
            else {
                v3 = v2;
            }
        }

        float v10 = -arg7.left * v2;
        float v7 = -arg7.top * v3;
        arg6.setTranslate(arg8.left, arg8.top);
        if(arg9 == v1) {
            arg6.preRotate(90f);
            arg6.preTranslate(0f, -arg8.width());
        }
        else if(arg9 == 180) {
            arg6.preRotate(180f);
            arg6.preTranslate(-arg8.width(), -arg8.height());
        }
        else if(arg9 == v0) {
            arg6.preRotate(270f);
            arg6.preTranslate(-arg8.height(), 0f);
        }

        arg6.preScale(v2, v3);
        arg6.preTranslate(v10, v7);
    }

    public static void setScrollViewEdgeEffectColor(ScrollView arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            try {
                Field v0 = ScrollView.class.getDeclaredField("mEdgeGlowTop");
                v0.setAccessible(true);
                Object v0_1 = v0.get(arg3);
                if(v0_1 != null) {
                    ((EdgeEffect)v0_1).setColor(arg4);
                }

                v0 = ScrollView.class.getDeclaredField("mEdgeGlowBottom");
                v0.setAccessible(true);
                Object v3_1 = v0.get(arg3);
                if(v3_1 == null) {
                    return;
                }

                ((EdgeEffect)v3_1).setColor(arg4);
            }
            catch(Exception v3) {
                FileLog.e(((Throwable)v3));
            }
        }
    }

    public static void setViewPagerEdgeEffectColor(ViewPager arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            try {
                Field v0 = ViewPager.class.getDeclaredField("mLeftEdge");
                v0.setAccessible(true);
                Object v0_1 = v0.get(arg4);
                if(v0_1 != null) {
                    Field v2 = i.class.getDeclaredField("a");
                    v2.setAccessible(true);
                    v0_1 = v2.get(v0_1);
                    if(v0_1 != null) {
                        ((EdgeEffect)v0_1).setColor(arg5);
                    }
                }

                v0 = ViewPager.class.getDeclaredField("mRightEdge");
                v0.setAccessible(true);
                Object v4_1 = v0.get(arg4);
                if(v4_1 == null) {
                    return;
                }

                v0 = i.class.getDeclaredField("a");
                v0.setAccessible(true);
                v4_1 = v0.get(v4_1);
                if(v4_1 == null) {
                    return;
                }

                ((EdgeEffect)v4_1).setColor(arg5);
            }
            catch(Exception v4) {
                FileLog.e(((Throwable)v4));
            }
        }
    }

    public static void setWaitingForCall(boolean arg4) {
        Object v0 = AndroidUtilities.callLock;
        __monitor_enter(v0);
        if(arg4) {
            try {
                if(AndroidUtilities.callReceiver == null) {
                    IntentFilter v1 = new IntentFilter("android.intent.action.PHONE_STATE");
                    Context v2 = ApplicationLoader.applicationContext;
                    CallReceiver v3 = new CallReceiver();
                    AndroidUtilities.callReceiver = v3;
                    v2.registerReceiver(((BroadcastReceiver)v3), v1);
                    goto label_23;
                label_16:
                    if(AndroidUtilities.callReceiver != null) {
                        ApplicationLoader.applicationContext.unregisterReceiver(AndroidUtilities.callReceiver);
                        AndroidUtilities.callReceiver = null;
                        goto label_23;
                    label_15:
                        goto label_26;
                    }
                }
            }
            catch(Exception ) {
            }
            catch(Throwable v4) {
                goto label_15;
            }
        }
        else {
            goto label_16;
            try {
            label_26:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_15;
            }

            throw v4;
        }

        try {
        label_23:
            AndroidUtilities.waitingForCall = ((boolean)v4);
            __monitor_exit(v0);
            return;
        }
        catch(Throwable v4) {
            goto label_15;
        }
    }

    public static void setWaitingForSms(boolean arg1) {
        Object v0 = AndroidUtilities.smsLock;
        __monitor_enter(v0);
        try {
            AndroidUtilities.waitingForSms = arg1;
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public static void shakeView(View arg6, float arg7, int arg8) {
        if(arg6 == null) {
            return;
        }

        if(arg8 == 6) {
            arg6.setTranslationX(0f);
            return;
        }

        AnimatorSet v0 = new AnimatorSet();
        v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(arg6, "translationX", new float[]{((float)AndroidUtilities.dp(arg7))})});
        v0.setDuration(50);
        v0.addListener(new AnimatorListenerAdapter(arg6, arg8, arg7) {
            public void onAnimationEnd(Animator arg3) {
                View v3 = this.val$view;
                float v0 = this.val$num == 5 ? 0f : -this.val$x;
                AndroidUtilities.shakeView(v3, v0, this.val$num + 1);
            }
        });
        v0.start();
    }

    public static void showKeyboard(View arg2) {
        if(arg2 == null) {
            return;
        }

        try {
            arg2.getContext().getSystemService("input_method").showSoftInput(arg2, 1);
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    public static void showProxyAlert(Activity arg19, String arg20, String arg21, String arg22, String arg23, String arg24) {
        String v12_2;
        String v14;
        int v11;
        Activity v0 = arg19;
        org.telegram.ui.ActionBar.BottomSheet$Builder v1 = new org.telegram.ui.ActionBar.BottomSheet$Builder(((Context)v0));
        Runnable v8 = v1.getDismissRunnable();
        v1.setApplyTopPadding(false);
        v1.setApplyBottomPadding(false);
        LinearLayout v3 = new LinearLayout(((Context)v0));
        v1.setCustomView(((View)v3));
        v3.setOrientation(1);
        int v6 = 48;
        int v7 = 3;
        int v9 = 5;
        int v10 = -1;
        if(!TextUtils.isEmpty(((CharSequence)arg24))) {
            TextView v5 = new TextView(((Context)v0));
            v5.setText(LocaleController.getString("UseProxyTelegramInfo2", 2131626316));
            v5.setTextColor(Theme.getColor("dialogTextGray4"));
            v5.setTextSize(1, 14f);
            v5.setGravity(49);
            int v12 = -2;
            int v13 = -2;
            v11 = LocaleController.isRTL ? 5 : 3;
            v3.addView(((View)v5), LayoutHelper.createLinear(v12, v13, v11 | 48, 17, 8, 17, 8));
            View v5_1 = new View(((Context)v0));
            v5_1.setBackgroundColor(Theme.getColor("divider"));
            v3.addView(v5_1, new LinearLayout$LayoutParams(v10, 1));
        }

        int v5_2;
        for(v5_2 = 0; v5_2 < v9; ++v5_2) {
            v11 = 2;
            CharSequence v12_1 = null;
            if(v5_2 == 0) {
                v14 = LocaleController.getString("UseProxyAddress", 2131626302);
                v12_2 = arg20;
            }
            else if(v5_2 == 1) {
                v12_2 = "" + arg21;
                v14 = LocaleController.getString("UseProxyPort", 2131626307);
            }
            else if(v5_2 == v11) {
                v14 = LocaleController.getString("UseProxySecret", 2131626308);
                v12_2 = arg24;
            }
            else if(v5_2 == v7) {
                v14 = LocaleController.getString("UseProxyUsername", 2131626317);
                v12_2 = arg22;
            }
            else if(v5_2 == 4) {
                v14 = LocaleController.getString("UseProxyPassword", 2131626306);
                v12_2 = arg23;
            }
            else {
                CharSequence v14_1 = v12_1;
            }

            if(TextUtils.isEmpty(((CharSequence)v12_2))) {
            }
            else {
                TextDetailSettingsCell v15 = new TextDetailSettingsCell(((Context)v0));
                v15.setTextAndValue(v12_2, ((CharSequence)v14), true);
                v15.getTextView().setTextColor(Theme.getColor("dialogTextBlack"));
                v15.getValueTextView().setTextColor(Theme.getColor("dialogTextGray3"));
                v3.addView(((View)v15), LayoutHelper.createLinear(v10, -2));
                if(v5_2 == v11) {
                    break;
                }
            }
        }

        PickerBottomLayout v4 = new PickerBottomLayout(((Context)v0), false);
        v4.setBackgroundColor(Theme.getColor("dialogBackground"));
        v3.addView(((View)v4), LayoutHelper.createFrame(v10, v6, 83));
        v4.cancelButton.setPadding(AndroidUtilities.dp(18f), 0, AndroidUtilities.dp(18f), 0);
        v4.cancelButton.setTextColor(Theme.getColor("dialogTextBlue2"));
        v4.cancelButton.setText(LocaleController.getString("Cancel", 2131624257).toUpperCase());
        v4.cancelButton.setOnClickListener(new View$OnClickListener(v8) {
            public void onClick(View arg1) {
                this.val$dismissRunnable.run();
            }
        });
        v4.doneButtonTextView.setTextColor(Theme.getColor("dialogTextBlue2"));
        v4.doneButton.setPadding(AndroidUtilities.dp(18f), 0, AndroidUtilities.dp(18f), 0);
        v4.doneButtonBadgeTextView.setVisibility(8);
        v4.doneButtonTextView.setText(LocaleController.getString("ConnectingConnectProxy", 2131624466).toUpperCase());
        v4.doneButton.setOnClickListener(new View$OnClickListener(arg20, arg21, arg24, arg23, arg22, v8) {
            public void onClick(View arg9) {
                ProxyInfo v7;
                SharedPreferences$Editor v9 = MessagesController.getGlobalMainSettings().edit();
                v9.putBoolean("proxy_enabled", true);
                v9.putString("proxy_ip", this.val$address);
                int v0 = Utilities.parseInt(this.val$port).intValue();
                v9.putInt("proxy_port", v0);
                if(TextUtils.isEmpty(this.val$secret)) {
                    v9.remove("proxy_secret");
                    if(TextUtils.isEmpty(this.val$password)) {
                        v9.remove("proxy_pass");
                    }
                    else {
                        v9.putString("proxy_pass", this.val$password);
                    }

                    if(TextUtils.isEmpty(this.val$user)) {
                        v9.remove("proxy_user");
                    }
                    else {
                        v9.putString("proxy_user", this.val$user);
                    }

                    v7 = new ProxyInfo(this.val$address, v0, this.val$user, this.val$password, "");
                }
                else {
                    v9.remove("proxy_pass");
                    v9.remove("proxy_user");
                    v9.putString("proxy_secret", this.val$secret);
                    v7 = new ProxyInfo(this.val$address, v0, "", "", this.val$secret);
                }

                v9.commit();
                SharedConfig.currentProxy = SharedConfig.addProxy(v7);
                ConnectionsManager.setProxySettings(true, this.val$address, v0, this.val$user, this.val$password, this.val$secret);
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxySettingsChanged, new Object[0]);
                this.val$dismissRunnable.run();
            }
        });
        v1.show();
    }

    public static void unlockOrientation(Activity arg2) {
        if(arg2 == null) {
            return;
        }

        try {
            int v1 = -10;
            if(AndroidUtilities.prevOrientation == v1) {
                return;
            }

            arg2.setRequestedOrientation(AndroidUtilities.prevOrientation);
            AndroidUtilities.prevOrientation = v1;
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    public static void unregisterUpdates() {
        if(BuildVars.DEBUG_VERSION) {
            net.hockeyapp.android.i.a();
        }
    }
}

