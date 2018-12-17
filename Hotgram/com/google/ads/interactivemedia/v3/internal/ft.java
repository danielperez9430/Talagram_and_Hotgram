package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Pattern;

public final class ft {
    public static final int a;
    public static final String b;
    public static final String c;
    public static final String d;
    private static final Pattern e;
    private static final Pattern f;
    private static final Pattern g;
    private static final int[] h;

    static {
        int v0 = Build$VERSION.SDK_INT != 25 || Build$VERSION.CODENAME.charAt(0) != 79 ? Build$VERSION.SDK_INT : 26;
        ft.a = v0;
        ft.b = Build.DEVICE;
        ft.c = Build.MANUFACTURER;
        ft.d = Build.MODEL;
        ft.e = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)(\\.(\\d+))?([Zz]|((\\+|\\-)(\\d\\d):?(\\d\\d)))?");
        ft.f = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        ft.g = Pattern.compile("%([A-Fa-f0-9]{2})");
        ft.h = new int[]{0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};
    }

    public static int a(long arg1) {
        return ((int)(arg1 >>> 32));
    }

    public static boolean a(Object arg0, Object arg1) {
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

    public static long a(long arg5, long arg7, long arg9) {
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

    public static void a(long[] arg6, long arg7, long arg9) {
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

    public static int a(int arg0, int arg1) {
        return (arg0 + arg1 - 1) / arg1;
    }

    public static int a(long[] arg0, long arg1, boolean arg3, boolean arg4) {
        int v0 = Arrays.binarySearch(arg0, arg1);
        if(v0 < 0) {
            v0 = -(v0 + 2);
        }
        else if(arg3) {
        }
        else {
            --v0;
        }

        if(arg4) {
            v0 = Math.max(0, v0);
        }

        return v0;
    }

    public static ExecutorService a(String arg1) {
        return Executors.newSingleThreadExecutor(new ThreadFactory(arg1) {
            public Thread newThread(Runnable arg3) {
                return new Thread(arg3, this.a);
            }
        });
    }

    public static int a(int arg1) {
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

    public static int a(byte[] arg3, int arg4, int arg5, int arg6) {
        while(arg4 < arg5) {
            arg6 = ft.h[(arg6 >>> 24 ^ arg3[arg4] & 255) & 255] ^ arg6 << 8;
            ++arg4;
        }

        return arg6;
    }

    public static String a(Context arg3, String arg4) {
        String v3;
        try {
            v3 = arg3.getPackageManager().getPackageInfo(arg3.getPackageName(), 0).versionName;
        }
        catch(PackageManager$NameNotFoundException ) {
            v3 = "?";
        }

        String v0 = Build$VERSION.RELEASE;
        StringBuilder v2 = new StringBuilder(String.valueOf(arg4).length() + 38 + String.valueOf(v3).length() + String.valueOf(v0).length());
        v2.append(arg4);
        v2.append("/");
        v2.append(v3);
        v2.append(" (Linux;Android ");
        v2.append(v0);
        v2.append(") ");
        v2.append("ExoPlayerLib/1.5.16");
        return v2.toString();
    }

    public static String a(Object[] arg3) {
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

    public static void a(et arg0) {
        if(arg0 != null) {
            try {
                arg0.a();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    public static void a(HttpURLConnection arg3, long arg4) {
        if(ft.a != 19 && ft.a != 20) {
            return;
        }

        try {
            InputStream v3 = arg3.getInputStream();
            if(arg4 == -1) {
                if(v3.read() == -1) {
                    return;
                }
            }
            else if(arg4 <= 2048) {
                return;
            }

            String v4 = v3.getClass().getName();
            if((v4.equals("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream")) || (v4.equals("com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream"))) {
                Method v4_1 = v3.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput");
                v4_1.setAccessible(true);
                v4_1.invoke(v3);
            }

            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public static boolean a(Uri arg1) {
        String v1 = arg1.getScheme();
        boolean v1_1 = (TextUtils.isEmpty(((CharSequence)v1))) || (v1.equals("file")) ? true : false;
        return v1_1;
    }

    public static int b(long arg0) {
        return ((int)arg0);
    }

    public static long b(int arg4, int arg5) {
        return (((long)arg5)) & 4294967295L | (((long)arg4)) << 32;
    }

    public static int b(long[] arg0, long arg1, boolean arg3, boolean arg4) {
        int v1 = Arrays.binarySearch(arg0, arg1);
        if(v1 < 0) {
            v1 ^= -1;
        }
        else if(arg3) {
        }
        else {
            ++v1;
        }

        if(arg4) {
            v1 = Math.min(arg0.length - 1, v1);
        }

        return v1;
    }

    public static String b(String arg1) {
        return arg1 == null ? null : arg1.toLowerCase(Locale.US);
    }

    public static int c(String arg4) {
        int v0 = arg4.length();
        int v1 = 0;
        boolean v2 = v0 <= 4 ? true : false;
        fe.a(v2);
        int v2_1 = 0;
        while(v1 < v0) {
            v2_1 = v2_1 << 8 | arg4.charAt(v1);
            ++v1;
        }

        return v2_1;
    }

    public static byte[] d(String arg5) {
        byte[] v0 = new byte[arg5.length() / 2];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            int v2 = v1 * 2;
            v0[v1] = ((byte)((Character.digit(arg5.charAt(v2), 16) << 4) + Character.digit(arg5.charAt(v2 + 1), 16)));
        }

        return v0;
    }
}

