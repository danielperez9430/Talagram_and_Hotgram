package com.google.android.exoplayer2.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Build;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display$Mode;
import android.view.Display;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.upstream.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class Util {
    private static final int[] CRC32_BYTES_MSBF = null;
    public static final String DEVICE = null;
    public static final String DEVICE_DEBUG_INFO = null;
    private static final Pattern ESCAPED_CHARACTER_PATTERN = null;
    public static final String MANUFACTURER = null;
    public static final String MODEL = null;
    public static final int SDK_INT = 0;
    private static final String TAG = "Util";
    private static final Pattern XS_DATE_TIME_PATTERN;
    private static final Pattern XS_DURATION_PATTERN;

    static {
        int v0 = Build$VERSION.SDK_INT != 25 || Build$VERSION.CODENAME.charAt(0) != 79 ? Build$VERSION.SDK_INT : 26;
        Util.SDK_INT = v0;
        Util.DEVICE = Build.DEVICE;
        Util.MANUFACTURER = Build.MANUFACTURER;
        Util.MODEL = Build.MODEL;
        Util.DEVICE_DEBUG_INFO = Util.DEVICE + ", " + Util.MODEL + ", " + Util.MANUFACTURER + ", " + Util.SDK_INT;
        Util.XS_DATE_TIME_PATTERN = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
        Util.XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        Util.ESCAPED_CHARACTER_PATTERN = Pattern.compile("%([A-Fa-f0-9]{2})");
        Util.CRC32_BYTES_MSBF = new int[]{0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};
    }

    private Util() {
        super();
    }

    public static long addWithOverflowDefault(long arg3, long arg5, long arg7) {
        long v0 = arg3 + arg5;
        if(((arg3 ^ v0) & (arg5 ^ v0)) < 0) {
            return arg7;
        }

        return v0;
    }

    public static boolean areEqual(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != null) {
            v0 = arg0.equals(arg1);
        }
        else if(arg1 == null) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public static int binarySearchCeil(long[] arg4, long arg5, boolean arg7, boolean arg8) {
        int v5;
        int v0 = Arrays.binarySearch(arg4, arg5);
        if(v0 < 0) {
            v5 = v0 ^ -1;
        }
        else {
            while(true) {
                ++v0;
                if(v0 < arg4.length && arg4[v0] == arg5) {
                    continue;
                }

                break;
            }

            if(arg7) {
                v5 = v0 - 1;
                goto label_14;
            }

            v5 = v0;
        }

    label_14:
        if(arg8) {
            v5 = Math.min(arg4.length - 1, v5);
        }

        return v5;
    }

    public static int binarySearchCeil(List arg3, Comparable arg4, boolean arg5, boolean arg6) {
        int v4;
        int v0 = Collections.binarySearch(arg3, arg4);
        if(v0 < 0) {
            v4 = v0 ^ -1;
        }
        else {
            int v1 = arg3.size();
            while(true) {
                ++v0;
                if(v0 < v1 && arg3.get(v0).compareTo(arg4) == 0) {
                    continue;
                }

                break;
            }

            if(arg5) {
                v4 = v0 - 1;
                goto label_15;
            }

            v4 = v0;
        }

    label_15:
        if(arg6) {
            v4 = Math.min(arg3.size() - 1, v4);
        }

        return v4;
    }

    public static int binarySearchFloor(long[] arg4, long arg5, boolean arg7, boolean arg8) {
        int v4;
        int v0 = Arrays.binarySearch(arg4, arg5);
        if(v0 < 0) {
            v4 = -(v0 + 2);
        }
        else {
            while(true) {
                --v0;
                if(v0 >= 0 && arg4[v0] == arg5) {
                    continue;
                }

                break;
            }

            if(arg7) {
                v4 = v0 + 1;
                goto label_14;
            }

            v4 = v0;
        }

    label_14:
        if(arg8) {
            v4 = Math.max(0, v4);
        }

        return v4;
    }

    public static int binarySearchFloor(List arg2, Comparable arg3, boolean arg4, boolean arg5) {
        int v2;
        int v0 = Collections.binarySearch(arg2, arg3);
        if(v0 < 0) {
            v2 = -(v0 + 2);
        }
        else {
            while(true) {
                --v0;
                if(v0 >= 0 && arg2.get(v0).compareTo(arg3) == 0) {
                    continue;
                }

                break;
            }

            if(arg4) {
                v2 = v0 + 1;
                goto label_15;
            }

            v2 = v0;
        }

    label_15:
        if(arg5) {
            v2 = Math.max(0, v2);
        }

        return v2;
    }

    public static int binarySearchFloor(int[] arg2, int arg3, boolean arg4, boolean arg5) {
        int v2;
        int v0 = Arrays.binarySearch(arg2, arg3);
        if(v0 < 0) {
            v2 = -(v0 + 2);
        }
        else {
            while(true) {
                --v0;
                if(v0 >= 0 && arg2[v0] == arg3) {
                    continue;
                }

                break;
            }

            if(arg4) {
                v2 = v0 + 1;
                goto label_14;
            }

            v2 = v0;
        }

    label_14:
        if(arg5) {
            v2 = Math.max(0, v2);
        }

        return v2;
    }

    @EnsuresNonNull(value={"#1"}) public static Object castNonNull(Object arg0) {
        return arg0;
    }

    public static int ceilDivide(int arg0, int arg1) {
        return (arg0 + arg1 - 1) / arg1;
    }

    public static long ceilDivide(long arg2, long arg4) {
        return (arg2 + arg4 - 1) / arg4;
    }

    public static void closeQuietly(Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    public static void closeQuietly(DataSource arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    public static int compareLong(long arg1, long arg3) {
        int v1;
        if(arg1 < arg3) {
            v1 = -1;
        }
        else if(arg1 == arg3) {
            v1 = 0;
        }
        else {
            v1 = 1;
        }

        return v1;
    }

    public static long constrainValue(long arg0, long arg2, long arg4) {
        return Math.max(arg2, Math.min(arg0, arg4));
    }

    public static int constrainValue(int arg0, int arg1, int arg2) {
        return Math.max(arg1, Math.min(arg0, arg2));
    }

    public static float constrainValue(float arg0, float arg1, float arg2) {
        return Math.max(arg1, Math.min(arg0, arg2));
    }

    public static boolean contains(Object[] arg4, Object arg5) {
        int v0 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(Util.areEqual(arg4[v2], arg5)) {
                return 1;
            }
        }

        return 0;
    }

    public static int crc(byte[] arg3, int arg4, int arg5, int arg6) {
        while(arg4 < arg5) {
            arg6 = Util.CRC32_BYTES_MSBF[(arg6 >>> 24 ^ arg3[arg4] & 255) & 255] ^ arg6 << 8;
            ++arg4;
        }

        return arg6;
    }

    public static Handler createHandler(Handler$Callback arg1) {
        return Util.createHandler(Util.getLooper(), arg1);
    }

    public static Handler createHandler(Looper arg1, Handler$Callback arg2) {
        return new Handler(arg1, arg2);
    }

    public static File createTempDirectory(Context arg0, String arg1) {
        File v0 = Util.createTempFile(arg0, arg1);
        v0.delete();
        v0.mkdir();
        return v0;
    }

    public static File createTempFile(Context arg1, String arg2) {
        return File.createTempFile(arg2, null, arg1.getCacheDir());
    }

    public static String escapeFileName(String arg6) {
        int v0 = arg6.length();
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            if(Util.shouldEscapeCharacter(arg6.charAt(v2))) {
                ++v3;
            }

            ++v2;
        }

        if(v3 == 0) {
            return arg6;
        }

        StringBuilder v2_1 = new StringBuilder(v3 * 2 + v0);
        while(v3 > 0) {
            int v4 = v1 + 1;
            char v1_1 = arg6.charAt(v1);
            if(Util.shouldEscapeCharacter(v1_1)) {
                v2_1.append('%');
                v2_1.append(Integer.toHexString(v1_1));
                --v3;
            }
            else {
                v2_1.append(v1_1);
            }

            v1 = v4;
        }

        if(v1 < v0) {
            v2_1.append(((CharSequence)arg6), v1, v0);
        }

        return v2_1.toString();
    }

    public static String formatInvariant(String arg1, Object[] arg2) {
        return String.format(Locale.US, arg1, arg2);
    }

    public static String fromUtf8Bytes(byte[] arg2) {
        return new String(arg2, Charset.forName("UTF-8"));
    }

    public static String fromUtf8Bytes(byte[] arg2, int arg3, int arg4) {
        return new String(arg2, arg3, arg4, Charset.forName("UTF-8"));
    }

    public static int getAudioContentTypeForStreamType(int arg0) {
        switch(arg0) {
            case 0: {
                return 1;
            }
            case 1: 
            case 2: 
            case 4: 
            case 5: 
            case 8: {
                return 4;
            }
        }

        return 2;
    }

    public static int getAudioUsageForStreamType(int arg0) {
        switch(arg0) {
            case 0: {
                return 2;
            }
            case 1: {
                return 13;
            }
            case 2: {
                return 6;
            }
            case 4: {
                return 4;
            }
            case 5: {
                return 5;
            }
            case 8: {
                return 3;
            }
        }

        return 1;
    }

    public static byte[] getBytesFromHexString(String arg5) {
        byte[] v0 = new byte[arg5.length() / 2];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            int v2 = v1 * 2;
            v0[v1] = ((byte)((Character.digit(arg5.charAt(v2), 16) << 4) + Character.digit(arg5.charAt(v2 + 1), 16)));
        }

        return v0;
    }

    public static String getCodecsOfType(String arg6, int arg7) {
        String[] v6 = Util.splitCodecs(arg6);
        String v1 = null;
        if(v6.length == 0) {
            return v1;
        }

        StringBuilder v0 = new StringBuilder();
        int v2 = v6.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            String v4 = v6[v3];
            if(arg7 == MimeTypes.getTrackTypeOfCodec(v4)) {
                if(v0.length() > 0) {
                    v0.append(",");
                }

                v0.append(v4);
            }
        }

        if(v0.length() > 0) {
            v1 = v0.toString();
        }

        return v1;
    }

    public static String getCommaDelimitedSimpleClassNames(Object[] arg3) {
        StringBuilder v0 = new StringBuilder();
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0.append(arg3[v1].getClass().getSimpleName());
            if(v1 < arg3.length - 1) {
                v0.append(", ");
            }
        }

        return v0.toString();
    }

    public static String getCountryCode(Context arg1) {
        if(arg1 != null) {
            Object v1 = arg1.getSystemService("phone");
            if(v1 != null) {
                String v1_1 = ((TelephonyManager)v1).getNetworkCountryIso();
                if(!TextUtils.isEmpty(((CharSequence)v1_1))) {
                    return Util.toUpperInvariant(v1_1);
                }
            }
        }

        return Util.toUpperInvariant(Locale.getDefault().getCountry());
    }

    public static int getDefaultBufferSize(int arg1) {
        int v0 = 131072;
        switch(arg1) {
            case 0: {
                return 16777216;
            }
            case 1: {
                return 3538944;
            }
            case 2: {
                return 13107200;
            }
            case 3: 
            case 4: {
                return v0;
            }
        }

        throw new IllegalStateException();
        return v0;
    }

    @TargetApi(value=16) private static void getDisplaySizeV16(Display arg0, Point arg1) {
        arg0.getSize(arg1);
    }

    @TargetApi(value=17) private static void getDisplaySizeV17(Display arg0, Point arg1) {
        arg0.getRealSize(arg1);
    }

    @TargetApi(value=23) private static void getDisplaySizeV23(Display arg1, Point arg2) {
        Display$Mode v1 = arg1.getMode();
        arg2.x = v1.getPhysicalWidth();
        arg2.y = v1.getPhysicalHeight();
    }

    private static void getDisplaySizeV9(Display arg1, Point arg2) {
        arg2.x = arg1.getWidth();
        arg2.y = arg1.getHeight();
    }

    public static UUID getDrmUuid(String arg3) {
        int v0_1;
        String v0 = Util.toLowerInvariant(arg3);
        int v1 = v0.hashCode();
        if(v1 != -1860423953) {
            if(v1 != -1400551171) {
                if(v1 != 790309106) {
                    goto label_24;
                }
                else if(v0.equals("clearkey")) {
                    v0_1 = 2;
                }
                else {
                    goto label_24;
                }
            }
            else if(v0.equals("widevine")) {
                v0_1 = 0;
            }
            else {
                goto label_24;
            }
        }
        else if(v0.equals("playready")) {
            v0_1 = 1;
        }
        else {
        label_24:
            v0_1 = -1;
        }

        switch(v0_1) {
            case 0: {
                goto label_32;
            }
            case 1: {
                goto label_30;
            }
            case 2: {
                goto label_28;
            }
        }

        try {
            UUID v3 = UUID.fromString(arg3);
            return v3;
        }
        catch(RuntimeException ) {
            return null;
        }

    label_28:
        return C.CLEARKEY_UUID;
    label_30:
        return C.PLAYREADY_UUID;
    label_32:
        return C.WIDEVINE_UUID;
    }

    public static int getIntegerCodeForString(String arg4) {
        int v0 = arg4.length();
        int v1 = 0;
        boolean v2 = v0 <= 4 ? true : false;
        Assertions.checkArgument(v2);
        int v2_1 = 0;
        while(v1 < v0) {
            v2_1 = v2_1 << 8 | arg4.charAt(v1);
            ++v1;
        }

        return v2_1;
    }

    public static Looper getLooper() {
        Looper v0 = Looper.myLooper();
        if(v0 != null) {
        }
        else {
            v0 = Looper.getMainLooper();
        }

        return v0;
    }

    public static long getMediaDurationForPlayoutDuration(long arg2, float arg4) {
        if(arg4 == 1f) {
            return arg2;
        }

        double v2 = ((double)arg2);
        double v0 = ((double)arg4);
        Double.isNaN(v2);
        Double.isNaN(v0);
        return Math.round(v2 * v0);
    }

    private static int getMobileNetworkType(NetworkInfo arg0) {
        switch(arg0.getSubtype()) {
            case 1: 
            case 2: {
                return 3;
            }
            case 13: {
                return 5;
            }
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 14: 
            case 15: 
            case 17: {
                return 4;
            }
            case 18: {
                return 2;
            }
        }

        return 6;
    }

    public static int getNetworkType(Context arg2) {
        NetworkInfo v2_1;
        if(arg2 == null) {
            return 0;
        }

        try {
            Object v2 = arg2.getSystemService("connectivity");
            if(v2 == null) {
                return 0;
            }

            v2_1 = ((ConnectivityManager)v2).getActiveNetworkInfo();
            if(v2_1 == null) {
                return 1;
            }
        }
        catch(SecurityException ) {
            return 0;
        }

        if(!v2_1.isConnected()) {
        }
        else {
            switch(v2_1.getType()) {
                case 1: {
                    return 2;
                }
                case 0: 
                case 4: 
                case 5: {
                    goto label_22;
                }
                case 6: {
                    return 5;
                }
                case 9: {
                    return 7;
                }
            }

            return 8;
        label_22:
            return Util.getMobileNetworkType(v2_1);
        }

        return 1;
    }

    public static int getPcmEncoding(int arg1) {
        if(arg1 != 8) {
            if(arg1 != 16) {
                if(arg1 != 24) {
                    if(arg1 != 32) {
                        return 0;
                    }

                    return 1073741824;
                }

                return -2147483648;
            }

            return 2;
        }

        return 3;
    }

    public static int getPcmFrameSize(int arg1, int arg2) {
        if(arg1 != -2147483648) {
            if(arg1 != 1073741824) {
                switch(arg1) {
                    case 2: {
                        goto label_9;
                    }
                    case 3: {
                        return arg2;
                    }
                    case 4: {
                        goto label_11;
                    }
                }

                throw new IllegalArgumentException();
                return arg2;
            label_9:
                return arg2 * 2;
            }

        label_11:
            return arg2 * 4;
        }

        return arg2 * 3;
    }

    public static Point getPhysicalDisplaySize(Context arg1) {
        return Util.getPhysicalDisplaySize(arg1, arg1.getSystemService("window").getDefaultDisplay());
    }

    public static Point getPhysicalDisplaySize(Context arg6, Display arg7) {
        Object v6;
        if(Util.SDK_INT < 25 && arg7.getDisplayId() == 0) {
            if(("Sony".equals(Util.MANUFACTURER)) && (Util.MODEL.startsWith("BRAVIA")) && (arg6.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd"))) {
                return new Point(3840, 2160);
            }

            if(!"NVIDIA".equals(Util.MANUFACTURER) || !Util.MODEL.contains("SHIELD")) {
                if(!"philips".equals(Util.toLowerInvariant(Util.MANUFACTURER))) {
                    goto label_92;
                }
                else if(!Util.MODEL.startsWith("QM1") && !Util.MODEL.equals("QV151E")) {
                    if(Util.MODEL.equals("TPM171E")) {
                    }
                    else {
                        goto label_92;
                    }
                }
            }

            try {
                Class v2_1 = Class.forName("android.os.SystemProperties");
                v6 = v2_1.getMethod("get", String.class).invoke(v2_1, "sys.display-size");
            }
            catch(Exception v2) {
                Log.e("Util", "Failed to read sys.display-size", ((Throwable)v2));
            }

            if(TextUtils.isEmpty(((CharSequence)v6))) {
                goto label_92;
            }

            try {
                String[] v2_2 = Util.split(((String)v6).trim(), "x");
                if(v2_2.length == 2) {
                    int v0 = Integer.parseInt(v2_2[0]);
                    int v1 = Integer.parseInt(v2_2[1]);
                    if(v0 > 0 && v1 > 0) {
                        return new Point(v0, v1);
                    }
                }

                goto label_84;
            }
            catch(NumberFormatException ) {
            label_84:
                Log.e("Util", "Invalid sys.display-size: " + (((String)v6)));
            }
        }

    label_92:
        Point v6_1 = new Point();
        if(Util.SDK_INT >= 23) {
            Util.getDisplaySizeV23(arg7, v6_1);
        }
        else if(Util.SDK_INT >= 17) {
            Util.getDisplaySizeV17(arg7, v6_1);
        }
        else if(Util.SDK_INT >= 16) {
            Util.getDisplaySizeV16(arg7, v6_1);
        }
        else {
            Util.getDisplaySizeV9(arg7, v6_1);
        }

        return v6_1;
    }

    public static long getPlayoutDurationForMediaDuration(long arg2, float arg4) {
        if(arg4 == 1f) {
            return arg2;
        }

        double v2 = ((double)arg2);
        double v0 = ((double)arg4);
        Double.isNaN(v2);
        Double.isNaN(v0);
        return Math.round(v2 / v0);
    }

    public static int getStreamTypeForAudioUsage(int arg1) {
        int v0 = 3;
        switch(arg1) {
            case 2: {
                return 0;
            }
            case 3: {
                return 8;
            }
            case 4: {
                return 4;
            }
            case 6: {
                return 2;
            }
            case 5: 
            case 7: 
            case 8: 
            case 9: 
            case 10: {
                return 5;
            }
            case 13: {
                return 1;
            }
            case 1: 
            case 12: 
            case 14: {
                return v0;
            }
        }

        return v0;
    }

    public static String getStringForTime(StringBuilder arg8, Formatter arg9, long arg10) {
        // Method was not decompiled
    }

    public static String getUserAgent(Context arg2, String arg3) {
        String v2;
        try {
            v2 = arg2.getPackageManager().getPackageInfo(arg2.getPackageName(), 0).versionName;
        }
        catch(PackageManager$NameNotFoundException ) {
            v2 = "?";
        }

        return arg3 + "/" + v2 + " (Linux;Android " + Build$VERSION.RELEASE + ") " + "ExoPlayerLib/2.8.3";
    }

    public static byte[] getUtf8Bytes(String arg1) {
        return arg1.getBytes(Charset.forName("UTF-8"));
    }

    public static int inferContentType(Uri arg0) {
        String v0 = arg0.getPath();
        int v0_1 = v0 == null ? 3 : Util.inferContentType(v0);
        return v0_1;
    }

    public static int inferContentType(String arg1) {
        arg1 = Util.toLowerInvariant(arg1);
        if(arg1.endsWith(".mpd")) {
            return 0;
        }

        if(arg1.endsWith(".m3u8")) {
            return 2;
        }

        if(arg1.matches(".*\\.ism(l)?(/manifest(\\(.+\\))?)?")) {
            return 1;
        }

        return 3;
    }

    public static int inferContentType(Uri arg1, String arg2) {
        int v1;
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            v1 = Util.inferContentType(arg1);
        }
        else {
            StringBuilder v1_1 = new StringBuilder();
            v1_1.append(".");
            v1_1.append(arg2);
            v1 = Util.inferContentType(v1_1.toString());
        }

        return v1;
    }

    public static boolean isEncodingHighResolutionIntegerPcm(int arg1) {
        boolean v1 = arg1 == -2147483648 || arg1 == 1073741824 ? true : false;
        return v1;
    }

    public static boolean isEncodingLinearPcm(int arg1) {
        boolean v1 = arg1 == 3 || arg1 == 2 || arg1 == -2147483648 || arg1 == 1073741824 || arg1 == 4 ? true : false;
        return v1;
    }

    public static boolean isLinebreak(int arg1) {
        boolean v1 = arg1 == 10 || arg1 == 13 ? true : false;
        return v1;
    }

    public static boolean isLocalFileUri(Uri arg1) {
        String v1 = arg1.getScheme();
        boolean v1_1 = (TextUtils.isEmpty(((CharSequence)v1))) || ("file".equals(v1)) ? true : false;
        return v1_1;
    }

    @TargetApi(value=23) public static boolean maybeRequestReadExternalStoragePermission(Activity arg4, Uri[] arg5) {
        if(Util.SDK_INT < 23) {
            return 0;
        }

        int v0 = arg5.length;
        int v2 = 0;
        while(v2 < v0) {
            if(!Util.isLocalFileUri(arg5[v2])) {
                ++v2;
                continue;
            }
            else if(arg4.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                arg4.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
                return 1;
            }

            return 0;
        }

        return 0;
    }

    public static ExecutorService newSingleThreadExecutor(String arg1) {
        return Executors.newSingleThreadExecutor(new ThreadFactory(arg1) {
            public Thread newThread(Runnable arg3) {
                return new Thread(arg3, this.val$threadName);
            }
        });
    }

    public static String normalizeLanguageCode(String arg1) {
        if(arg1 == null) {
            arg1 = null;
        }
        else {
            try {
                arg1 = new Locale(arg1).getISO3Language();
            }
            catch(MissingResourceException ) {
                return Util.toLowerInvariant(arg1);
            }
        }

        return arg1;
    }

    public static Object[] nullSafeArrayCopy(Object[] arg1, int arg2) {
        boolean v0 = arg2 <= arg1.length ? true : false;
        Assertions.checkArgument(v0);
        return Arrays.copyOf(arg1, arg2);
    }

    public static long parseXsDateTime(String arg10) {
        Matcher v0 = Util.XS_DATE_TIME_PATTERN.matcher(((CharSequence)arg10));
        if(v0.matches()) {
            int v10 = 9;
            int v2 = 0;
            if(v0.group(v10) == null) {
            }
            else if(v0.group(v10).equalsIgnoreCase("Z")) {
            }
            else {
                v2 = Integer.parseInt(v0.group(12)) * 60 + Integer.parseInt(v0.group(13));
                if("-".equals(v0.group(11))) {
                    v2 *= -1;
                }
            }

            GregorianCalendar v10_1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            ((Calendar)v10_1).clear();
            int v1 = 3;
            v10_1.set(Integer.parseInt(v0.group(1)), Integer.parseInt(v0.group(2)) - 1, Integer.parseInt(v0.group(v1)), Integer.parseInt(v0.group(4)), Integer.parseInt(v0.group(5)), Integer.parseInt(v0.group(6)));
            int v3 = 8;
            if(!TextUtils.isEmpty(v0.group(v3))) {
                StringBuilder v5 = new StringBuilder();
                v5.append("0.");
                v5.append(v0.group(v3));
                ((Calendar)v10_1).set(14, new BigDecimal(v5.toString()).movePointRight(v1).intValue());
            }

            long v0_1 = ((Calendar)v10_1).getTimeInMillis();
            if(v2 != 0) {
                v0_1 -= ((long)(v2 * 60000));
            }

            return v0_1;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Invalid date/time format: ");
        v1_1.append(arg10);
        throw new ParserException(v1_1.toString());
    }

    public static long parseXsDuration(String arg14) {
        Matcher v0 = Util.XS_DURATION_PATTERN.matcher(((CharSequence)arg14));
        double v2 = 1000;
        double v4 = 3600;
        if(v0.matches()) {
            int v14 = 1 ^ TextUtils.isEmpty(v0.group(1));
            String v1 = v0.group(3);
            double v6 = 0;
            double v8 = v1 != null ? Double.parseDouble(v1) * 31556908 : v6;
            v1 = v0.group(5);
            double v10 = v1 != null ? Double.parseDouble(v1) * 2629739 : v6;
            v8 += v10;
            v1 = v0.group(7);
            v10 = v1 != null ? Double.parseDouble(v1) * 86400 : v6;
            v8 += v10;
            v1 = v0.group(10);
            if(v1 != null) {
                v4 *= Double.parseDouble(v1);
            }
            else {
                v4 = v6;
            }

            v8 += v4;
            v1 = v0.group(12);
            v4 = v1 != null ? Double.parseDouble(v1) * 60 : v6;
            v8 += v4;
            String v0_1 = v0.group(14);
            if(v0_1 != null) {
                v6 = Double.parseDouble(v0_1);
            }

            long v0_2 = ((long)((v8 + v6) * v2));
            if(v14 != 0) {
                v0_2 = -v0_2;
            }

            return v0_2;
        }

        return ((long)(Double.parseDouble(arg14) * v4 * v2));
    }

    public static boolean readBoolean(Parcel arg0) {
        boolean v0 = arg0.readInt() != 0 ? true : false;
        return v0;
    }

    public static void recursiveDelete(File arg4) {
        File[] v0 = arg4.listFiles();
        if(v0 != null) {
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                Util.recursiveDelete(v0[v2]);
            }
        }

        arg4.delete();
    }

    public static void removeRange(List arg0, int arg1, int arg2) {
        arg0.subList(arg1, arg2).clear();
    }

    public static long resolveSeekPositionUs(long arg8, SeekParameters arg10, long arg11, long arg13) {
        if(SeekParameters.EXACT.equals(arg10)) {
            return arg8;
        }

        long v0 = Util.subtractWithOverflowDefault(arg8, arg10.toleranceBeforeUs, -9223372036854775808L);
        long v2 = Util.addWithOverflowDefault(arg8, arg10.toleranceAfterUs, 9223372036854775807L);
        int v4 = 1;
        int v10 = Long.compare(v0, arg11) > 0 || arg11 > v2 ? 0 : 1;
        if(v0 > arg13 || arg13 > v2) {
            v4 = 0;
        }
        else {
        }

        if(v10 != 0 && v4 != 0) {
            if(Math.abs(arg11 - arg8) <= Math.abs(arg13 - arg8)) {
                return arg11;
            }
            else {
                return arg13;
            }
        }

        if(v10 != 0) {
            return arg11;
        }

        if(v4 != 0) {
            return arg13;
        }

        return v0;
    }

    public static long scaleLargeTimestamp(long arg5, long arg7, long arg9) {
        long v1 = 0;
        if(Long.compare(arg9, arg7) >= 0 && arg9 % arg7 == v1) {
            return arg5 / (arg9 / arg7);
        }

        if(arg9 < arg7 && arg7 % arg9 == v1) {
            return arg5 * (arg7 / arg9);
        }

        double v7 = ((double)arg7);
        double v9 = ((double)arg9);
        Double.isNaN(v7);
        Double.isNaN(v9);
        double v5 = ((double)arg5);
        Double.isNaN(v5);
        return ((long)(v5 * (v7 / v9)));
    }

    public static long[] scaleLargeTimestamps(List arg7, long arg8, long arg10) {
        long[] v0 = new long[arg7.size()];
        long v2 = 0;
        int v4 = 0;
        if(Long.compare(arg10, arg8) < 0 || arg10 % arg8 != v2) {
            if(arg10 < arg8 && arg8 % arg10 == v2) {
                arg8 /= arg10;
                while(true) {
                    if(v4 < v0.length) {
                        v0[v4] = arg7.get(v4).longValue() * arg8;
                        ++v4;
                        continue;
                    }
                    else {
                        return v0;
                    }
                }
            }

            double v8 = ((double)arg8);
            double v10 = ((double)arg10);
            Double.isNaN(v8);
            Double.isNaN(v10);
            v8 /= v10;
            while(v4 < v0.length) {
                v10 = ((double)arg7.get(v4).longValue());
                Double.isNaN(v10);
                v0[v4] = ((long)(v10 * v8));
                ++v4;
            }
        }
        else {
            arg10 /= arg8;
            while(v4 < v0.length) {
                v0[v4] = arg7.get(v4).longValue() / arg10;
                ++v4;
            }
        }

        return v0;
    }

    public static void scaleLargeTimestampsInPlace(long[] arg6, long arg7, long arg9) {
        long v1 = 0;
        int v3 = 0;
        if(Long.compare(arg9, arg7) < 0 || arg9 % arg7 != v1) {
            if(arg9 < arg7 && arg7 % arg9 == v1) {
                arg7 /= arg9;
                while(true) {
                    if(v3 < arg6.length) {
                        arg6[v3] *= arg7;
                        ++v3;
                        continue;
                    }
                    else {
                        return;
                    }
                }
            }

            double v7 = ((double)arg7);
            double v9 = ((double)arg9);
            Double.isNaN(v7);
            Double.isNaN(v9);
            v7 /= v9;
            while(v3 < arg6.length) {
                v9 = ((double)arg6[v3]);
                Double.isNaN(v9);
                arg6[v3] = ((long)(v9 * v7));
                ++v3;
            }
        }
        else {
            arg9 /= arg7;
            while(v3 < arg6.length) {
                arg6[v3] /= arg9;
                ++v3;
            }
        }
    }

    private static boolean shouldEscapeCharacter(char arg1) {
        if(arg1 != 34 && arg1 != 37 && arg1 != 42 && arg1 != 47 && arg1 != 58 && arg1 != 60 && arg1 != 92 && arg1 != 124) {
            switch(arg1) {
                case 62: 
                case 63: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }

        return 1;
    }

    public static void sneakyThrow(Throwable arg0) {
        Util.sneakyThrowInternal(arg0);
    }

    private static void sneakyThrowInternal(Throwable arg0) {
        throw arg0;
    }

    public static String[] split(String arg1, String arg2) {
        return arg1.split(arg2, -1);
    }

    public static String[] splitAtFirst(String arg1, String arg2) {
        return arg1.split(arg2, 2);
    }

    public static String[] splitCodecs(String arg1) {
        if(TextUtils.isEmpty(((CharSequence)arg1))) {
            return new String[0];
        }

        return Util.split(arg1.trim(), "(\\s*,\\s*)");
    }

    public static ComponentName startForegroundService(Context arg2, Intent arg3) {
        if(Util.SDK_INT >= 26) {
            return arg2.startForegroundService(arg3);
        }

        return arg2.startService(arg3);
    }

    public static long subtractWithOverflowDefault(long arg3, long arg5, long arg7) {
        long v0 = arg3 - arg5;
        if(((arg3 ^ v0) & (arg5 ^ arg3)) < 0) {
            return arg7;
        }

        return v0;
    }

    public static int[] toArray(List arg4) {
        if(arg4 == null) {
            return null;
        }

        int v0 = arg4.size();
        int[] v1 = new int[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = arg4.get(v2).intValue();
        }

        return v1;
    }

    public static byte[] toByteArray(InputStream arg4) {
        byte[] v0 = new byte[4096];
        ByteArrayOutputStream v1 = new ByteArrayOutputStream();
        while(true) {
            int v2 = arg4.read(v0);
            if(v2 == -1) {
                break;
            }

            v1.write(v0, 0, v2);
        }

        return v1.toByteArray();
    }

    public static String toLowerInvariant(String arg1) {
        if(arg1 == null) {
        }
        else {
            arg1 = arg1.toLowerCase(Locale.US);
        }

        return arg1;
    }

    public static String toUpperInvariant(String arg1) {
        if(arg1 == null) {
        }
        else {
            arg1 = arg1.toUpperCase(Locale.US);
        }

        return arg1;
    }

    public static String unescapeFileName(String arg8) {
        int v0 = arg8.length();
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            if(arg8.charAt(v2) == 37) {
                ++v3;
            }

            ++v2;
        }

        if(v3 == 0) {
            return arg8;
        }

        v2 = v0 - v3 * 2;
        StringBuilder v4 = new StringBuilder(v2);
        Matcher v5 = Util.ESCAPED_CHARACTER_PATTERN.matcher(((CharSequence)arg8));
        while(v3 > 0) {
            if(!v5.find()) {
                break;
            }

            char v6 = ((char)Integer.parseInt(v5.group(1), 16));
            v4.append(((CharSequence)arg8), v1, v5.start());
            v4.append(v6);
            v1 = v5.end();
            --v3;
        }

        if(v1 < v0) {
            v4.append(((CharSequence)arg8), v1, v0);
        }

        if(v4.length() != v2) {
            return null;
        }

        return v4.toString();
    }

    public static void writeBoolean(Parcel arg0, boolean arg1) {
        arg0.writeInt(((int)arg1));
    }
}

