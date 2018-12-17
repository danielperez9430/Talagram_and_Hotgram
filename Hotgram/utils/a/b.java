package utils.a;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.google.a.c.a;
import com.google.a.f;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.telegram.customization.Model.Ads.Category;
import org.telegram.customization.Model.CAI;
import org.telegram.customization.Model.CUrl;
import org.telegram.customization.Model.ChatTime;
import org.telegram.customization.Model.DialogTab;
import org.telegram.customization.Model.OfficialJoinChannel;
import org.telegram.customization.Model.ProxyServerModel;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserConfig;

public class b {
    public SharedPreferences$Editor a;
    static ArrayList b;
    static Random c;
    public static CUrl d;
    private static b e;
    private SharedPreferences f;
    private static List g;
    private static List h;
    private static Boolean i;
    private static Boolean j;
    private static Integer k;

    static {
        b.c = new Random();
        b.d = null;
    }

    private b(Context arg2, String arg3) {
        super();
        this.f = arg2.getSharedPreferences(arg3, 0);
        this.a = this.f.edit();
    }

    public static long A() {
        return b.ax(ApplicationLoader.applicationContext).c("CIF_PRD").longValue();
    }

    public static String A(Context arg1) {
        return b.ax(arg1).a("SUBTITLE");
    }

    public static void A(Context arg1, String arg2) {
        b.ax(arg1).a("SP_DEFAULT_PROXY", arg2);
    }

    public static String B() {
        String v0 = b.ax(ApplicationLoader.applicationContext).a("SP_WHATS_UP_URL");
        if(v0 != null && !TextUtils.isEmpty(((CharSequence)v0))) {
            return v0;
        }

        return "http://2.188.184.13/";
    }

    public static int B(Context arg1) {
        if(b.k == null) {
            b.k = Integer.valueOf(b.ax(arg1).b("SUBTITLE_TYPE"));
        }

        return b.k.intValue();
    }

    public static ArrayList B(Context arg6, String arg7) {
        ArrayList v7;
        String v2_1;
        ArrayList v6_2;
        ArrayList v0 = new ArrayList();
        try {
            Object v6_1 = new f().a(b.ax(arg6).a(arg7), new a() {
            }.b());
        }
        catch(Exception v6) {
            v6.printStackTrace();
            v6_2 = v0;
        }

        int v0_1 = 9;
        int v1 = 0;
        int v2 = 1;
        if(v6_2 == null || v6_2.size() < 1 || (TextUtils.isEmpty(v6_2.get(0)))) {
            v6_2 = new ArrayList();
            int v4 = arg7.hashCode();
            if(v4 != 1367331750) {
                switch(v4) {
                    case 1367331718: {
                        goto label_76;
                    }
                    case 1367331719: {
                        goto label_71;
                    }
                    case 1367331720: {
                        goto label_66;
                    }
                    case 1367331721: {
                        goto label_61;
                    }
                    case 1367331722: {
                        goto label_56;
                    }
                    case 1367331723: {
                        goto label_52;
                    }
                    case 1367331724: {
                        goto label_47;
                    }
                }

                switch(v4) {
                    case 1367331753: {
                        goto label_42;
                    }
                    case 1367331754: {
                        goto label_37;
                    }
                    case 1367331755: {
                        goto label_32;
                    }
                }

                goto label_86;
            label_37:
                if(arg7.equals("set-1066")) {
                    v2 = 8;
                    goto label_87;
                label_42:
                    if(arg7.equals("set-1065")) {
                        v2 = 10;
                        goto label_87;
                    label_32:
                        if(arg7.equals("set-1067")) {
                            v2 = 9;
                            goto label_87;
                        label_66:
                            if(arg7.equals("set-1053")) {
                                v2 = 5;
                                goto label_87;
                            label_52:
                                if(arg7.equals("set-1056")) {
                                    goto label_87;
                                label_71:
                                    if(arg7.equals("set-1052")) {
                                        v2 = 3;
                                        goto label_87;
                                    label_56:
                                        if(arg7.equals("set-1055")) {
                                            v2 = 4;
                                            goto label_87;
                                        label_76:
                                            if(arg7.equals("set-1051")) {
                                                v2 = 0;
                                                goto label_87;
                                            label_61:
                                                if(arg7.equals("set-1054")) {
                                                    v2 = 2;
                                                    goto label_87;
                                                label_47:
                                                    if(arg7.equals("set-1057")) {
                                                        v2 = 6;
                                                    }
                                                    else {
                                                        goto label_86;
                                                    }
                                                }
                                                else {
                                                    goto label_86;
                                                }
                                            }
                                            else {
                                                goto label_86;
                                            }
                                        }
                                        else {
                                            goto label_86;
                                        }
                                    }
                                    else {
                                        goto label_86;
                                    }
                                }
                                else {
                                    goto label_86;
                                }
                            }
                            else {
                                goto label_86;
                            }
                        }
                        else {
                            goto label_86;
                        }
                    }
                    else {
                        goto label_86;
                    }
                }
                else {
                    goto label_86;
                }
            }
            else if(arg7.equals("set-1062")) {
                v2 = 7;
            }
            else {
            label_86:
                v2 = -1;
            }

        label_87:
            switch(v2) {
                case 0: {
                    goto label_147;
                }
                case 1: {
                    goto label_141;
                }
                case 2: {
                    goto label_135;
                }
                case 3: {
                    goto label_129;
                }
                case 4: {
                    goto label_123;
                }
                case 5: {
                    goto label_117;
                }
                case 6: {
                    goto label_111;
                }
                case 7: {
                    goto label_109;
                }
                case 8: 
                case 9: 
                case 10: {
                    goto label_89;
                }
            }

            goto label_153;
        label_129:
            v6_2.add("http://sapi.hotgram.ir/");
            v6_2.add("http://sapi.harsobh.com/");
            v2_1 = "http://sapi.talagram.ir/";
            goto label_152;
        label_147:
            v6_2.add("http://uapi.hotgram.ir/");
            v6_2.add("http://uapi.harsobh.com/");
            v2_1 = "http://uapi.talagram.ir/";
            goto label_152;
        label_117:
            v6_2.add("http://gsapi.hotgram.ir/");
            v6_2.add("http://gsapi.harsobh.com/");
            v2_1 = "http://gsapi.talagram.ir/";
            goto label_152;
        label_135:
            v6_2.add("http://giapi.hotgram.ir/");
            v6_2.add("http://giapi.harsobh.com/");
            v2_1 = "http://giapi.talagram.ir/";
            goto label_152;
        label_89:
            v6_2.add("http://lh0.hotgram.ir/");
            v6_2.add("http://lh1.harsobh.com/");
            v6_2.add("http://lh2.hotgram.ir/");
            v6_2.add("http://lh3.harsobh.com/");
            v6_2.add("http://lh4.hotgram.ir/");
            v6_2.add("http://lh5.talagram.ir/");
            v6_2.add("http://lh6.hotgram.ir/");
            v6_2.add("http://lh7.harsobh.com/");
            v6_2.add("http://lh8.hotgram.ir/");
            v2_1 = "http://lh9.talagram.ir/";
            goto label_152;
        label_123:
            v6_2.add("http://rgapi.hotgram.ir/");
            v6_2.add("http://rgapi.harsobh.com/");
            v2_1 = "http://rgapi.talagram.ir/";
            goto label_152;
        label_141:
            v6_2.add("http://gpapi.hotgram.ir/");
            v6_2.add("http://gpapi.harsobh.com/");
            v2_1 = "http://gpapi.talagram.ir/";
            goto label_152;
        label_109:
            v2_1 = "https://api.pay.hotgram.ir/";
            goto label_152;
        label_111:
            v6_2.add("http://cfapi.hotgram.ir/");
            v6_2.add("http://cfapi.harsobh.com/");
            v2_1 = "http://cfapi.talagram.ir/";
        label_152:
            v6_2.add(v2_1);
        }

    label_153:
        Collections.shuffle(((List)v6_2));
        if((arg7.equals("set-1066")) || (arg7.equals("set-1065")) || (arg7.equals("set-1067"))) {
            v7 = new ArrayList();
            while(v1 <= v0_1) {
                v2_1 = "http://lh" + v1 + ".hotgram.ir/";
                if(!v6_2.contains(v2_1)) {
                    v7.add(v2_1);
                }

                ++v1;
            }

            Collections.shuffle(((List)v7));
            v6_2.addAll(((Collection)v7));
        }

        v7 = new ArrayList();
        Iterator v6_3 = v6_2.iterator();
        while(v6_3.hasNext()) {
            Object v0_2 = v6_3.next();
            if(v0_2 == null) {
                continue;
            }

            String v0_3 = ((String)v0_2).trim();
            if(!v0_3.endsWith("/")) {
                v0_3 = v0_3 + "/";
            }

            v7.add(v0_3);
        }

        return v7;
    }

    public static long C(Context arg2) {
        b v2 = b.ax(arg2);
        StringBuilder v0 = new StringBuilder();
        v0.append("SP_LAST_SUCCESS_SYNC_CONTACT_");
        v0.append(UserConfig.selectedAccount);
        return v2.c(v0.toString()).longValue();
    }

    public static String C() {
        return b.ax(ApplicationLoader.applicationContext).a("LAST_USED_LH");
    }

    public static void C(Context arg1, String arg2) {
        b.ax(arg1).a("PUSHTOKEN", arg2);
    }

    public static boolean D() {
        return b.ax(ApplicationLoader.applicationContext).a("WU_ENABLE", true);
    }

    public static long D(Context arg2) {
        b v2 = b.ax(arg2);
        StringBuilder v0 = new StringBuilder();
        v0.append("SP_LAST_SUCCESS_SYNC_LOCATION_");
        v0.append(UserConfig.selectedAccount);
        return v2.c(v0.toString()).longValue();
    }

    private static PublicKey D(Context arg2, String arg3) {
        PublicKey v3_1;
        InputStream v2;
        CertificateFactory v1;
        InputStream v0 = null;
        try {
            v1 = CertificateFactory.getInstance("X.509");
            v2 = arg2.getAssets().open(arg3);
        }
        catch(Throwable v3) {
            goto label_14;
        }

        try {
            v3_1 = v1.generateCertificate(v2).getPublicKey();
            if(v2 == null) {
                return v3_1;
            }

            goto label_8;
        }
        catch(Throwable v3) {
            v0 = v2;
        }

    label_14:
        if(v0 != null) {
            try {
                v0.close();
                goto label_16;
            }
            catch(IOException ) {
            label_16:
                throw v3;
            }
        }

        goto label_16;
        try {
        label_8:
            v2.close();
            return v3_1;
        }
        catch(IOException ) {
            return v3_1;
        }
    }

    public static long E(Context arg2) {
        b v2 = b.ax(arg2);
        StringBuilder v0 = new StringBuilder();
        v0.append("SP_LAST_SUCCESS_SYNC_CHANNEL_");
        v0.append(UserConfig.selectedAccount);
        return v2.c(v0.toString()).longValue();
    }

    public static String E() {
        return b.ax(ApplicationLoader.applicationContext).a("SIP_CONFIG");
    }

    public static long F(Context arg5) {
        if(b.ax(arg5).c("SP_CHANNEL_SYNC_PERIOD").longValue() == 0) {
            return 172800000;
        }

        return b.ax(arg5).c("SP_CHANNEL_SYNC_PERIOD").longValue();
    }

    public static String F() {
        return b.ax(ApplicationLoader.applicationContext).a("LHID");
    }

    public static long G(Context arg5) {
        if(b.ax(arg5).c("SP_CONTACT_SYNC_PERIOD").longValue() == 0) {
            return 1209600000;
        }

        return b.ax(arg5).c("SP_CONTACT_SYNC_PERIOD").longValue();
    }

    public static String G() {
        return b.ax(ApplicationLoader.applicationContext).a("APPIDNAME");
    }

    public static String H() {
        String v0 = b.ax(ApplicationLoader.applicationContext).a("VAST");
        if(v0 != null && !TextUtils.isEmpty(((CharSequence)v0))) {
            return v0;
        }

        return "http://ads.advest.ir/z1/";
    }

    public static long H(Context arg5) {
        if(b.ax(arg5).c("SP_LOCATION_SYNC_PERIOD").longValue() == 0) {
            return 14400000;
        }

        return b.ax(arg5).c("SP_LOCATION_SYNC_PERIOD").longValue();
    }

    public static long I(Context arg5) {
        if(b.ax(arg5).c("SP_SUPET_SYNC_PERIOD").longValue() == 0) {
            return 1209600000;
        }

        return b.ax(arg5).c("SP_SUPET_SYNC_PERIOD").longValue();
    }

    public static ArrayList I() {
        Object v0_1;
        ArrayList v0 = new ArrayList();
        if(!TextUtils.isEmpty(b.ax(ApplicationLoader.applicationContext).a("D_CHAT"))) {
            v0_1 = new f().a(b.ax(ApplicationLoader.applicationContext).a("D_CHAT"), new a() {
            }.b());
        }

        return ((ArrayList)v0_1);
    }

    public static long J(Context arg2) {
        b v2 = b.ax(arg2);
        StringBuilder v0 = new StringBuilder();
        v0.append("SP_LAST_SUCCESSFULLY_SYNC_SUPER_");
        v0.append(UserConfig.selectedAccount);
        return v2.c(v0.toString()).longValue();
    }

    public static boolean J() {
        return b.ax(ApplicationLoader.applicationContext).a("VAST_ENABLE", false);
    }

    public static long K(Context arg5) {
        if(b.ax(arg5).c("SP_STATE_SYNC_PERIOD").longValue() == 0) {
            return 86400000;
        }

        return b.ax(arg5).c("SP_STATE_SYNC_PERIOD").longValue();
    }

    public static boolean K() {
        return b.ax(ApplicationLoader.applicationContext).a("RANDOM_ENABLE", false);
    }

    public static long L(Context arg2) {
        b v2 = b.ax(arg2);
        StringBuilder v0 = new StringBuilder();
        v0.append("SP_LAST_SUCCESSFULLY_SYNC_STATE_");
        v0.append(UserConfig.selectedAccount);
        return v2.c(v0.toString()).longValue();
    }

    public static boolean L() {
        return b.ax(ApplicationLoader.applicationContext).a("FMS_ENABLE", true);
    }

    public static long M(Context arg5) {
        if(b.ax(arg5).c("SP_BOT_SYNC_PERIOD").longValue() == 0) {
            return 1209600000;
        }

        return b.ax(arg5).c("SP_BOT_SYNC_PERIOD").longValue();
    }

    public static boolean M() {
        return b.ax(ApplicationLoader.applicationContext).a("USER_STATUS", true);
    }

    public static long N(Context arg2) {
        b v2 = b.ax(arg2);
        StringBuilder v0 = new StringBuilder();
        v0.append("SP_LAST_SUCCESSFULLY_SYNC_BOT_");
        v0.append(UserConfig.selectedAccount);
        return v2.c(v0.toString()).longValue();
    }

    public static boolean O(Context arg2) {
        return b.ax(arg2).a("IS_ENABLE_STREAM", false);
    }

    public static int P(Context arg1) {
        return b.ax(arg1).b("IS_ENABLE_GHOST");
    }

    public static final int Q(Context arg3) {
        int v0 = arg3.getResources().getInteger(2131361793);
        if(b.ax(arg3).b("SP_TEL_APP_ID") == 0) {
            return v0;
        }

        return b.ax(arg3).b("SP_TEL_APP_ID");
    }

    public static final String R(Context arg3) {
        String v0 = arg3.getResources().getString(2131626188);
        if(TextUtils.isEmpty(b.ax(arg3).a("SP_TEL_APP_HASH"))) {
            return v0;
        }

        return b.ax(arg3).a("SP_TEL_APP_HASH");
    }

    public static boolean S(Context arg2) {
        return b.ax(arg2).a("IS_ENABLE_FREE_ICON", true);
    }

    public static OfficialJoinChannel T(Context arg2) {
        try {
            return new f().a(b.ax(arg2).a("JOIN_OFFICIAL_CHANNEL"), OfficialJoinChannel.class);
        }
        catch(Exception v2) {
            v2.printStackTrace();
            return null;
        }
    }

    public static String U(Context arg2) {
        String v0;
        try {
            v0 = b.ax(arg2).a("SP_FILTER_MESSAGE");
            if(TextUtils.isEmpty(((CharSequence)v0))) {
                v0 = arg2.getString(2131626773);
            }
        }
        catch(Exception v2) {
            v2.printStackTrace();
            return null;
        }

        return v0;
    }

    public static boolean V(Context arg2) {
        return b.ax(arg2).a("SHOW_FREE_POPUP", false);
    }

    public static boolean W(Context arg2) {
        return b.ax(arg2).a("SCHEDULED_DOWNLOAD", false);
    }

    public static boolean X(Context arg2) {
        return b.ax(arg2).a("WIFI_ON", false);
    }

    public static boolean Y(Context arg2) {
        return b.ax(arg2).a("WIFI_ON", false);
    }

    public static ArrayList Z(Context arg2) {
        ArrayList v2_2;
        String v2 = b.ax(arg2).a("ADS_CAHNNEL");
        new ArrayList();
        Object v2_1 = new f().a(v2, new a() {
        }.b());
        if(v2_1 == null) {
            v2_2 = new ArrayList();
        }

        return v2_2;
    }

    public static void a(Context arg0, String arg1, String arg2, String arg3, String arg4, boolean arg5, long arg6) {
        b.w(arg0, arg1);
        b.x(arg0, arg2);
        b.y(arg0, arg3);
        b.z(arg0, arg4);
        b.t(arg0, arg5);
        b.b(arg6);
    }

    public String a(String arg3) {
        return this.f.getString(arg3, null);
    }

    public void a(String arg2, String arg3) {
        this.a.putString(arg2, arg3);
        this.a.commit();
    }

    public boolean a(String arg2, boolean arg3) {
        return this.f.getBoolean(arg2, arg3);
    }

    public static int a(Context arg2, int arg3) {
        b v2 = b.ax(arg2);
        StringBuilder v0 = new StringBuilder();
        v0.append("HOME_FRAGMENT_POS");
        v0.append(arg3);
        return v2.b(v0.toString());
    }

    public static long a() {
        return b.ax(ApplicationLoader.applicationContext).f.getLong("SP_PROXY_EXPIRE", 0);
    }

    public static void a(int arg2) {
        b.ax(ApplicationLoader.applicationContext).a("VIDEO_PLAY_COUNT", arg2);
    }

    public void a(String arg2, int arg3) {
        this.a.putInt(arg2, arg3);
        this.a.commit();
    }

    public static void a(long arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_GET_PROXY_PERIOD", Long.valueOf(arg2));
    }

    public void a(String arg4, Long arg5) {
        this.a.putLong(arg4, arg5.longValue());
        this.a.commit();
    }

    public static void a(Context arg2, int arg3, int arg4) {
        b v2 = b.ax(arg2);
        v2.a("HOME_FRAGMENT_POS" + arg3, arg4);
    }

    public static void a(Context arg2, long arg3) {
        String v0 = b.ax(arg2).a("HIDDEN_CHATS");
        String v3 = TextUtils.isEmpty(((CharSequence)v0)) ? String.valueOf(arg3) : v0 + "," + String.valueOf(arg3);
        b.ax(arg2).a("HIDDEN_CHATS", v3);
        b.g = null;
    }

    public static void a(Context arg1, Float arg2) {
        b.ax(arg1).a("TEXT_SIZE", arg2);
    }

    public void a(String arg2, Float arg3) {
        this.a.putFloat(arg2, arg3.floatValue());
        this.a.commit();
    }

    public static void a(Context arg2, String arg3) {
        try {
            b.ax(arg2).a("SP_MIRROR_ADDRESS", new f().a(arg3.split(",")));
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public static void a(Context arg0, String arg1, long arg2) {
        b.ax(arg0).a(arg1, Long.valueOf(arg2));
    }

    public static void a(Context arg0, String arg1, String arg2) {
        b.ax(arg0).a(arg1, arg2);
    }

    public static void a(Context arg2, String arg3, boolean arg4) {
        b v2 = b.ax(arg2);
        v2.b("IS_JOIN_" + arg3, arg4);
    }

    public static void a(Context arg2, ArrayList arg3) {
        b.ax(arg2).a("ADS_CAHNNEL", new f().a(arg3));
    }

    public static void a(Context arg1, boolean arg2) {
        b.ax(arg1).b("USER_REGISTERED", arg2);
    }

    public static void a(ChatTime arg9) {
        ArrayList v0 = new ArrayList();
        if(!TextUtils.isEmpty(b.ax(ApplicationLoader.applicationContext).a("D_CHAT"))) {
            Object v0_1 = new f().a(b.ax(ApplicationLoader.applicationContext).a("D_CHAT"), new a() {
            }.b());
        }

        Iterator v1 = v0.iterator();
        int v2 = 0;
        int v3 = 0;
        while(v1.hasNext()) {
            if(v1.next().getcId() == arg9.getcId()) {
                v2 = 1;
            }
            else {
                ++v3;
                continue;
            }

            break;
        }

        if(v2 != 0) {
            v0.remove(v3);
        }

        v0.add(arg9);
        b.ax(ApplicationLoader.applicationContext).a("D_CHAT", new f().a(v0));
    }

    public static void a(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("CALL_ENABLE", arg2);
    }

    public static boolean a(Context arg2) {
        return b.ax(arg2).a("USER_REGISTERED", false);
    }

    public static boolean a(String arg2, Context arg3) {
        Iterator v3 = b.w(arg3).iterator();
        do {
        label_2:
            if(!v3.hasNext()) {
                return 0;
            }

            Object v0 = v3.next();
            if(!((DialogTab)v0).getTag().contentEquals(((CharSequence)arg2))) {
                goto label_2;
            }
        }
        while(((DialogTab)v0).isHidden());

        return 1;
    }

    public static ArrayList aa(Context arg4) {
        ArrayList v0 = new ArrayList();
        Iterator v4 = b.Z(arg4).iterator();
        while(v4.hasNext()) {
            Object v1 = v4.next();
            if(((Category)v1).getStatus() != 1) {
                continue;
            }

            v0.add(v1);
        }

        return v0;
    }

    public static ArrayList ab(Context arg2) {
        if(b.b == null) {
            b.b = new ArrayList();
            Iterator v2 = b.Z(arg2).iterator();
            while(v2.hasNext()) {
                b.b.add(Integer.valueOf(v2.next().getChannelId()));
            }
        }

        return b.b;
    }

    public static boolean ac(Context arg2) {
        return b.ax(arg2).a("IS_ENABLE_ADS", false);
    }

    public static boolean ad(Context arg2) {
        return b.ax(arg2).a("IS_SHOW_COIN", true);
    }

    public static String ae(Context arg1) {
        return b.ax(arg1).a("SP_ADS_TU");
    }

    public static String af(Context arg1) {
        return b.ax(arg1).a("SP_ADS_TU1");
    }

    public static String ag(Context arg1) {
        return b.ax(arg1).a("SP_ADS_JOIN_MSG");
    }

    public static String ah(Context arg1) {
        return b.ax(arg1).a("SP_ADS_URL");
    }

    public static String ai(Context arg1) {
        return b.ax(arg1).a("SP_ADS_TU_URL");
    }

    public static String aj(Context arg2) {
        return b.ax(arg2).f.getString("SP_PROXY_ADDRESS", "");
    }

    public static String ak(Context arg2) {
        return b.ax(arg2).f.getString("SP_PROXY_PORT", "");
    }

    public static String al(Context arg2) {
        return b.ax(arg2).f.getString("SP_PROXY_USERNAME", "");
    }

    public static String am(Context arg2) {
        return b.ax(arg2).f.getString("SP_PROXY_PASSWORD", "");
    }

    public static int an(Context arg2) {
        return b.ax(arg2).f.getInt("SP_PROXY_ENABLE", 0);
    }

    public static boolean ao(Context arg2) {
        return b.ax(arg2).f.getBoolean("SP_PROXY_HEALTH", true);
    }

    public static ArrayList ap(Context arg7) {
        Object v0_1;
        try {
            ArrayList v0 = new ArrayList();
            String v7_1 = b.ax(arg7).a("SP_PROXY_LIST");
            if(!TextUtils.isEmpty(((CharSequence)v7_1))) {
                v0_1 = new f().a(v7_1, new a() {
                }.b());
            }

            if((((ArrayList)v0_1)) != null) {
                if(((ArrayList)v0_1).size() == 0) {
                }
                else {
                    ArrayList v7_2 = new ArrayList();
                    Iterator v0_2 = ((ArrayList)v0_1).iterator();
                    while(v0_2.hasNext()) {
                        Object v1 = v0_2.next();
                        ((ProxyServerModel)v1).getLocalExpireTime();
                        System.currentTimeMillis();
                        if(((ProxyServerModel)v1).getLocalExpireTime() - System.currentTimeMillis() < 0) {
                            continue;
                        }

                        v7_2.add(v1);
                    }

                    return v7_2;
                }
            }

            return new ArrayList();
        }
        catch(Exception v7) {
            v7.printStackTrace();
            return new ArrayList();
        }
    }

    public static ArrayList aq(Context arg4) {
        ArrayList v0_1;
        ArrayList v4_2;
        Object v4_1;
        String v0 = "set-1067";
        ArrayList v1 = new ArrayList();
        try {
            v4_1 = new f().a(b.ax(arg4).a(v0), new a() {
            }.b());
        }
        catch(Exception v4) {
            v4.printStackTrace();
            v4_2 = v1;
        }

        int v1_1 = 0;
        if(v4_1 == null || ((ArrayList)v4_1).size() < 1 || (TextUtils.isEmpty(((ArrayList)v4_1).get(0)))) {
            v4_2 = new ArrayList();
            v4_2.add("http://lh0.hotgram.ir/");
            v4_2.add("http://lh1.harsobh.com/");
            v4_2.add("http://lh2.hotgram.ir/");
            v4_2.add("http://lh3.harsobh.com/");
            v4_2.add("http://lh4.hotgram.ir/");
            v4_2.add("http://lh5.talagram.ir/");
            v4_2.add("http://lh6.hotgram.ir/");
            v4_2.add("http://lh7.harsobh.com/");
            v4_2.add("http://lh8.hotgram.ir/");
            v4_2.add("http://lh9.talagram.ir/");
        }

        Collections.shuffle(((List)v4_1));
        if((v0.equals("set-1066")) || (v0.equals("set-1065")) || (v0.equals("set-1067"))) {
            v0_1 = new ArrayList();
            while(v1_1 <= 9) {
                String v2_1 = "http://lh" + v1_1 + ".hotgram.ir/";
                if(!((ArrayList)v4_1).contains(v2_1)) {
                    v0_1.add(v2_1);
                }

                ++v1_1;
            }

            Collections.shuffle(((List)v0_1));
            ((ArrayList)v4_1).addAll(((Collection)v0_1));
        }

        v0_1 = new ArrayList();
        Iterator v4_3 = ((ArrayList)v4_1).iterator();
        while(v4_3.hasNext()) {
            Object v1_2 = v4_3.next();
            if(v1_2 == null) {
                continue;
            }

            String v1_3 = ((String)v1_2).trim();
            if(!v1_3.endsWith("/")) {
                v1_3 = v1_3 + "/";
            }

            v0_1.add(v1_3);
        }

        return v0_1;
    }

    public static boolean ar(Context arg2) {
        return b.ax(arg2).a("FILTER_CHANNEL", true);
    }

    public static boolean as(Context arg2) {
        return b.ax(arg2).a("THEME_SHOW", false);
    }

    public static String at(Context arg2) {
        if(TextUtils.isEmpty(b.ax(arg2).a("SECURITY_TOKEN"))) {
            b.au(arg2);
        }

        return b.ax(arg2).a("SECURITY_TOKEN");
    }

    public static void au(Context arg2) {
        b.ax(arg2).a("SECURITY_TOKEN", b.d());
    }

    public static boolean av(Context arg2) {
        return b.ax(arg2).a("CURRENT_USER_ADDED_TO_FAVE", false);
    }

    public static String aw(Context arg1) {
        return b.ax(arg1).a("PUSHTOKEN");
    }

    private static b ax(Context arg2) {
        if(b.e == null) {
            b.e = new b(arg2, "SP_MAIN");
        }

        return b.e;
    }

    public static void b(Context arg2, ArrayList arg3) {
        b.ax(arg2).a("SP_PROXY_LIST", new f().a(arg3));
    }

    public int b(String arg3) {
        return this.f.getInt(arg3, 0);
    }

    public static void b(long arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_PROXY_EXPIRE", Long.valueOf(arg2));
    }

    public void b(String arg2, boolean arg3) {
        this.a.putBoolean(arg2, arg3);
        this.a.commit();
    }

    public static String b() {
        if(!TextUtils.isEmpty(b.C()) && b.z() + 30000 > System.currentTimeMillis()) {
            return b.C();
        }

        ArrayList v0 = b.aq(ApplicationLoader.applicationContext);
        if(v0 != null && v0.size() > 0) {
            String v0_1 = v0.get(0) + "v5/";
            b.p(v0_1);
            b.g(System.currentTimeMillis());
            return v0_1;
        }

        return "";
    }

    public static List b(Context arg4) {
        if(b.g == null) {
            String v4 = b.ax(arg4).a("HIDDEN_CHATS");
            b.g = new ArrayList();
            if(!TextUtils.isEmpty(((CharSequence)v4))) {
                String[] v4_1 = v4.split(",");
                if(v4_1 != null && v4_1.length > 0) {
                    int v0 = 0;
                    while(v0 < v4_1.length) {
                        try {
                            b.g.add(Long.valueOf(Long.parseLong(v4_1[v0])));
                            goto label_23;
                        }
                        catch(Exception ) {
                        label_23:
                            ++v0;
                            continue;
                        }

                        break;
                    }
                }
            }
        }

        return b.g;
    }

    public static void b(Context arg1, int arg2) {
        b.ax(arg1).a("APP_VERSION", arg2);
    }

    public static void b(Context arg6, long arg7) {
        String v3_1;
        String v0 = b.ax(arg6).a("HIDDEN_CHATS");
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            String[] v0_1 = v0.split(",");
            if(v0_1 != null && v0_1.length > 0) {
                String v1 = "";
                int v2;
                for(v2 = 0; v2 < v0_1.length; ++v2) {
                    try {
                        if(Long.parseLong(v0_1[v2]) == arg7) {
                        }
                        else {
                            v3_1 = v1 + v0_1[v2];
                            goto label_24;
                        }
                    }
                    catch(Exception ) {
                    }

                    goto label_35;
                    try {
                    label_24:
                        if(v2 < v0_1.length - 1) {
                            v1 = v3_1 + ",";
                        }
                        else {
                            goto label_34;
                        }
                    }
                    catch(Exception ) {
                    label_34:
                        v1 = v3_1;
                    }

                label_35:
                }

                b.ax(arg6).a("HIDDEN_CHATS", v1);
                b.g = null;
            }
        }
    }

    public static void b(Context arg1, String arg2) {
        b.ax(arg1).a("PFLC", arg2);
    }

    public static void b(Context arg1, String arg2, String arg3) {
        try {
            b.ax(arg1).a(arg3, new f().a(arg2.split(",")));
        }
        catch(Exception v1) {
            v1.printStackTrace();
        }
    }

    public static void b(Context arg1, boolean arg2) {
        b.ax(arg1).b("SORT_DIALOGS_BY_UNREAD", arg2);
    }

    public static void b(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("AP_REGISTERED", arg2);
    }

    public static boolean c() {
        return b.ax(ApplicationLoader.applicationContext).a("CALL_ENABLE", false);
    }

    public Long c(String arg4) {
        return Long.valueOf(this.f.getLong(arg4, 0));
    }

    public static List c(Context arg4) {
        if(b.h == null) {
            String v4 = b.ax(arg4).a("HIDDEN_CONTACTS");
            b.h = new ArrayList();
            if(!TextUtils.isEmpty(((CharSequence)v4))) {
                String[] v4_1 = v4.split(",");
                if(v4_1 != null && v4_1.length > 0) {
                    int v0 = 0;
                    while(v0 < v4_1.length) {
                        try {
                            b.h.add(Long.valueOf(Long.parseLong(v4_1[v0])));
                            goto label_23;
                        }
                        catch(Exception ) {
                        label_23:
                            ++v0;
                            continue;
                        }

                        break;
                    }
                }
            }
        }

        return b.h;
    }

    public static void c(long arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_LAST_CALL_URL", Long.valueOf(arg2));
    }

    public static void c(Context arg1, int arg2) {
        b.ax(arg1).a("SELECTED_FONT", arg2);
    }

    public static void c(Context arg1, String arg2) {
        b.ax(arg1).a("START_DOWNLOAD_TIME", arg2);
    }

    public static void c(Context arg1, boolean arg2) {
        b.i = Boolean.valueOf(arg2);
        b.ax(arg1).b("CHECK_LOCAL_URL", arg2);
    }

    public static void c(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("PAYMENT_ENABLE", arg2);
    }

    public static boolean c(Context arg0, long arg1) {
        try {
            return b.b(arg0).contains(Long.valueOf(arg1));
        }
        catch(Exception ) {
            return 0;
        }
    }

    public static boolean c(Context arg2, String arg3, String arg4) {
        try {
            PublicKey v2 = b.D(arg2, "sign/PaymentPublic.cer");
            String[] v4 = arg4.split("#");
            Integer.parseInt(v4[0]);
            Integer.parseInt(v4[1]);
            byte[] v4_1 = Base64.decode(v4[2], 2);
            Signature v1 = Signature.getInstance("SHA256withRSA");
            v1.initVerify(v2);
            v1.update(arg3.getBytes());
            return v1.verify(v4_1);
        }
        catch(Exception ) {
            return 0;
        }
    }

    public static String d() {
        Random v0 = new Random();
        StringBuilder v1 = new StringBuilder();
        int v2 = v0.nextInt(100);
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v1.append(((char)(v0.nextInt(96) + 32)));
        }

        return v1.toString();
    }

    public static String d(Context arg1) {
        String v1 = b.ax(arg1).a("PFLC");
        if(v1 == null) {
            v1 = "";
        }

        return v1;
    }

    public static void d(long arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_LAST_CALL_URL_JOB", Long.valueOf(arg2));
    }

    public static void d(Context arg1, int arg2) {
        b.k = Integer.valueOf(arg2);
        b.ax(arg1).a("SUBTITLE_TYPE", arg2);
    }

    public static void d(Context arg2, long arg3) {
        String v0 = b.ax(arg2).a("HIDDEN_CONTACTS");
        String v3 = TextUtils.isEmpty(((CharSequence)v0)) ? String.valueOf(arg3) : v0 + "," + String.valueOf(arg3);
        b.ax(arg2).a("HIDDEN_CONTACTS", v3);
        b.h = null;
    }

    public static void d(Context arg1, String arg2) {
        b.ax(arg1).a("END_DOWNLOAD_TIME", arg2);
    }

    public static void d(Context arg1, boolean arg2) {
        b.ax(arg1).b("SHOW_ELECTION_TAB", arg2);
    }

    public static void d(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("SP_TAG_POST_ENABLE", arg2);
    }

    public Float d(String arg3) {
        return Float.valueOf(this.f.getFloat(arg3, 0f));
    }

    public static String e(Context arg0, String arg1) {
        return b.ax(arg0).a(arg1);
    }

    public static String e(String arg1) {
        if(arg1 != null && ((arg1.startsWith("http://")) || (arg1.startsWith("https://")))) {
            return arg1;
        }

        ArrayList v1 = b.B(ApplicationLoader.applicationContext, arg1);
        if(v1 != null && v1.size() > 0) {
            return v1.get(0);
        }

        return "";
    }

    public static void e(long arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_VITRIN_BG", Long.valueOf(arg2));
    }

    public static void e(Context arg1, int arg2) {
        b.ax(arg1).a("IS_ENABLE_GHOST", arg2);
    }

    public static void e(Context arg6, long arg7) {
        String v3_1;
        String v0 = b.ax(arg6).a("HIDDEN_CONTACTS");
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            String[] v0_1 = v0.split(",");
            if(v0_1 != null && v0_1.length > 0) {
                String v1 = "";
                int v2;
                for(v2 = 0; v2 < v0_1.length; ++v2) {
                    try {
                        if(Long.parseLong(v0_1[v2]) == arg7) {
                        }
                        else {
                            v3_1 = v1 + v0_1[v2];
                            goto label_24;
                        }
                    }
                    catch(Exception ) {
                    }

                    goto label_35;
                    try {
                    label_24:
                        if(v2 < v0_1.length - 1) {
                            v1 = v3_1 + ",";
                        }
                        else {
                            goto label_34;
                        }
                    }
                    catch(Exception ) {
                    label_34:
                        v1 = v3_1;
                    }

                label_35:
                }

                b.ax(arg6).a("HIDDEN_CONTACTS", v1);
                b.h = null;
            }
        }
    }

    public static void e(Context arg1, boolean arg2) {
        b.ax(arg1).b("SHOW_HOT_TAB", arg2);
    }

    public static void e(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("SP_SEND_STAT", arg2);
    }

    public static boolean e() {
        return b.ax(ApplicationLoader.applicationContext).a("AP_REGISTERED", false);
    }

    public static boolean e(Context arg2) {
        return b.ax(arg2).a("SORT_DIALOGS_BY_UNREAD", false);
    }

    public static boolean f(Context arg0, long arg1) {
        try {
            return b.c(arg0).contains(Long.valueOf(arg1));
        }
        catch(Exception ) {
            return 0;
        }
    }

    public static String f(Context arg2) {
        String v0 = b.ax(arg2).a("START_DOWNLOAD_TIME");
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            v0 = "2:00";
            b.c(arg2, v0);
        }

        return v0;
    }

    public static void f(long arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_VITRIN_LAST", Long.valueOf(arg2));
    }

    public static void f(Context arg1, int arg2) {
        b.ax(arg1).a("SP_TEL_APP_ID", arg2);
    }

    public static void f(Context arg1, String arg2) {
        b.ax(arg1).a("MAIN_DOMAIN", arg2);
    }

    public static void f(Context arg1, boolean arg2) {
        b.ax(arg1).b("SHOW_SEARCH_TAB", arg2);
    }

    public static void f(String arg2) {
        ArrayList v0 = b.B(ApplicationLoader.applicationContext, arg2);
        Object v1 = v0.get(0);
        v0.remove(v1);
        v0.add(v1);
        b.b(ApplicationLoader.applicationContext, TextUtils.join(",", ((Iterable)v0)), arg2);
    }

    public static void f(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("SHOW_QA", arg2);
    }

    public static boolean f() {
        return b.ax(ApplicationLoader.applicationContext).a("PAYMENT_ENABLE", false);
    }

    public static void g(long arg2) {
        b.ax(ApplicationLoader.applicationContext).a("LAST_LH_T", Long.valueOf(arg2));
    }

    public static String g(Context arg2) {
        String v0 = b.ax(arg2).a("END_DOWNLOAD_TIME");
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            v0 = "8:00";
            b.d(arg2, v0);
        }

        return v0;
    }

    public static void g(Context arg1, int arg2) {
        b.ax(arg1).a("SP_PROXY_ENABLE", arg2);
    }

    public static void g(Context arg1, long arg2) {
        b.ax(arg1).a("NEWS_TAG_ID", Long.valueOf(arg2));
    }

    public static void g(Context arg1, String arg2) {
        b.ax(arg1).a("INVITE_MESSEAGE", arg2);
    }

    public static void g(Context arg1, boolean arg2) {
        b.ax(arg1).b("SHOW_NEWS_LIST", arg2);
    }

    public static void g(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_CHECK_APP", arg2);
    }

    public static void g(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("JCBLOCKED", arg2);
    }

    public static boolean g() {
        return b.ax(ApplicationLoader.applicationContext).a("SP_TAG_POST_ENABLE", false);
    }

    public static int h() {
        return b.ax(ApplicationLoader.applicationContext).b("VIDEO_PLAY_COUNT");
    }

    public static void h(long arg2) {
        b.ax(ApplicationLoader.applicationContext).a("CIF_PRD", Long.valueOf(arg2));
    }

    public static void h(Context arg2, long arg3) {
        b v2 = b.ax(arg2);
        v2.a("SP_LAST_SUCCESS_SYNC_CONTACT_" + UserConfig.selectedAccount, Long.valueOf(arg3));
    }

    public static void h(Context arg1, String arg2) {
        b.ax(arg1).a("TAG_API_BASE_URL", arg2);
    }

    public static void h(Context arg1, boolean arg2) {
        b.ax(arg1).b("SHOW_VITRIN_TAB", arg2);
    }

    public static void h(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_CALL_URL", arg2);
    }

    public static void h(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("SAFE_CHECK", arg2);
    }

    public static boolean h(Context arg2) {
        if(b.i == null) {
            b.i = Boolean.valueOf(b.ax(arg2).a("CHECK_LOCAL_URL", true));
        }

        return b.i.booleanValue();
    }

    public static int i(Context arg1) {
        return b.ax(arg1).b("APP_VERSION");
    }

    public static void i(Context arg2, long arg3) {
        b v2 = b.ax(arg2);
        v2.a("SP_LAST_SUCCESS_SYNC_LOCATION_" + UserConfig.selectedAccount, Long.valueOf(arg3));
    }

    public static void i(Context arg1, String arg2) {
        b.ax(arg1).a("DIALOG_TABS", arg2);
    }

    public static void i(Context arg1, boolean arg2) {
        b.j = Boolean.valueOf(arg2);
        b.ax(arg1).b("SHOW_HIDDEN_DIALOGS", arg2);
    }

    public static void i(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_SUPPORT_TEXT", arg2);
    }

    public static void i(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("SP_TERM_ACC", arg2);
    }

    public static boolean i() {
        return b.ax(ApplicationLoader.applicationContext).a("SP_SEND_STAT", false);
    }

    public static String j(Context arg2) {
        if(TextUtils.isEmpty(b.ax(arg2).a("INVITE_MESSEAGE"))) {
            return LocaleController.getString("inviteMessage", 2131626903);
        }

        return b.ax(arg2).a("INVITE_MESSEAGE");
    }

    public static CAI j() {
        CAI v0 = new CAI();
        String v1 = b.ax(ApplicationLoader.applicationContext).a("SP_CHECK_APP");
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            Object v0_1 = new f().a(v1, CAI.class);
        }

        return v0;
    }

    public static void j(Context arg2, long arg3) {
        b v2 = b.ax(arg2);
        v2.a("SP_LAST_SUCCESS_SYNC_CHANNEL_" + UserConfig.selectedAccount, Long.valueOf(arg3));
    }

    public static void j(Context arg1, String arg2) {
        b.ax(arg1).a("SUBTITLE", arg2);
    }

    public static void j(Context arg1, boolean arg2) {
        b.ax(arg1).b("OFF_MODE", arg2);
    }

    public static void j(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_AGREE", arg2);
    }

    public static void j(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("SP_TEST_ENABLE", arg2);
    }

    public static int k(Context arg1) {
        int v1 = b.ax(arg1).b("TIMEOUT");
        if(v1 == 0) {
            v1 = 30000;
        }

        return v1;
    }

    public static long k() {
        return b.ax(ApplicationLoader.applicationContext).c("SP_LAST_CALL_URL").longValue();
    }

    public static long k(Context arg0, String arg1) {
        return b.ax(arg0).c(arg1).longValue();
    }

    public static void k(Context arg1, long arg2) {
        b.ax(arg1).a("SP_CHANNEL_SYNC_PERIOD", Long.valueOf(arg2));
    }

    public static void k(Context arg1, boolean arg2) {
        b.ax(arg1).b("SP_FIRST_TIME_SYNC", arg2);
    }

    public static void k(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_TC", arg2);
    }

    public static void k(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("SP_FINISHED_WHATS_UP", arg2);
    }

    public static String l() {
        return b.ax(ApplicationLoader.applicationContext).a("SP_CALL_URL");
    }

    public static void l(Context arg4) {
        b.ax(arg4).a("HOT_POST_RANDOM_NUM", Long.valueOf(b.m(arg4) + 1));
    }

    public static void l(Context arg1, long arg2) {
        b.ax(arg1).a("SP_CONTACT_SYNC_PERIOD", Long.valueOf(arg2));
    }

    public static void l(Context arg1, boolean arg2) {
        b.ax(arg1).b("IS_ENABLE_STREAM", arg2);
    }

    public static void l(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_ADS_BOX_URL", arg2);
    }

    public static void l(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("SUPPORT_VIDEO_CALL", arg2);
    }

    public static boolean l(Context arg2, String arg3) {
        b v2 = b.ax(arg2);
        StringBuilder v0 = new StringBuilder();
        v0.append("IS_JOIN_");
        v0.append(arg3);
        return v2.a(v0.toString(), false);
    }

    public static long m(Context arg2) {
        return b.ax(arg2).c("HOT_POST_RANDOM_NUM").longValue();
    }

    public static String m() {
        return b.ax(ApplicationLoader.applicationContext).a("SP_SUPPORT_TEXT");
    }

    public static void m(Context arg1, long arg2) {
        b.ax(arg1).a("SP_LOCATION_SYNC_PERIOD", Long.valueOf(arg2));
    }

    public static void m(Context arg1, String arg2) {
        b.ax(arg1).a("SP_TEL_APP_HASH", arg2);
    }

    public static void m(Context arg1, boolean arg2) {
        b.ax(arg1).b("IS_ENABLE_FREE_ICON", arg2);
    }

    public static void m(String arg3) {
        b v0 = b.ax(ApplicationLoader.applicationContext);
        v0.a("SP_CL_URL_" + UserConfig.selectedAccount, arg3);
    }

    public static void m(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("WU_ENABLE", arg2);
    }

    public static String n() {
        if(!TextUtils.isEmpty(b.ax(ApplicationLoader.applicationContext).a("SP_AGREE"))) {
            if(b.ax(ApplicationLoader.applicationContext).a("SP_AGREE") == null) {
            }
            else {
                return b.ax(ApplicationLoader.applicationContext).a("SP_AGREE");
            }
        }

        return "http://www.hotgram.ir/privacy.htm";
    }

    public static void n(Context arg1, long arg2) {
        b.ax(arg1).a("SP_SUPET_SYNC_PERIOD", Long.valueOf(arg2));
    }

    public static void n(Context arg1, String arg2) {
        b.ax(arg1).a("MAIN_DOMAIN_CHECK_URL", arg2);
    }

    public static void n(Context arg1, boolean arg2) {
        b.ax(arg1).b("SHOW_FREE_POPUP", arg2);
    }

    public static void n(String arg2) {
        b.d = null;
        b.ax(ApplicationLoader.applicationContext).a("SP_CALL_URL_JOB", arg2);
    }

    public static void n(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("VAST_ENABLE", arg2);
    }

    public static boolean n(Context arg2) {
        return b.ax(arg2).a("SHOW_HOT_TAB", true);
    }

    public static String o() {
        if(!TextUtils.isEmpty(b.ax(ApplicationLoader.applicationContext).a("SP_TC"))) {
            return b.ax(ApplicationLoader.applicationContext).a("SP_TC");
        }

        return "Themes_4_Telegram";
    }

    public static void o(Context arg2, long arg3) {
        b v2 = b.ax(arg2);
        v2.a("SP_LAST_SUCCESSFULLY_SYNC_SUPER_" + UserConfig.selectedAccount, Long.valueOf(arg3));
    }

    public static void o(Context arg2, String arg3) {
        try {
            b.ax(arg2).a("SP_MIRROR_ADDRESS_FOR_CHECK_URL", new f().a(arg3.split(",")));
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public static void o(Context arg1, boolean arg2) {
        b.ax(arg1).b("SCHEDULED_DOWNLOAD", arg2);
    }

    public static void o(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SP_WHATS_UP_URL", arg2);
    }

    public static void o(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("RANDOM_ENABLE", arg2);
    }

    public static boolean o(Context arg2) {
        return b.ax(arg2).a("SHOW_SEARCH_TAB", true);
    }

    public static void p(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("LAST_USED_LH", arg2);
    }

    public static void p(Context arg1, long arg2) {
        b.ax(arg1).a("SP_STATE_SYNC_PERIOD", Long.valueOf(arg2));
    }

    public static void p(Context arg1, String arg2) {
        b.ax(arg1).a("JOIN_OFFICIAL_CHANNEL", arg2);
    }

    public static void p(Context arg1, boolean arg2) {
        b.ax(arg1).b("WIFI_ON", arg2);
    }

    public static void p(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("FMS_ENABLE", arg2);
    }

    public static boolean p() {
        return b.ax(ApplicationLoader.applicationContext).a("SHOW_QA", false);
    }

    public static boolean p(Context arg0) {
        return 0;
    }

    public static void q(Context arg2, long arg3) {
        b v2 = b.ax(arg2);
        v2.a("SP_LAST_SUCCESSFULLY_SYNC_STATE_" + UserConfig.selectedAccount, Long.valueOf(arg3));
    }

    public static void q(Context arg1, String arg2) {
        b.ax(arg1).a("SP_FILTER_MESSAGE", arg2);
    }

    public static void q(Context arg1, boolean arg2) {
        b.ax(arg1).b("WIFI_ON", arg2);
    }

    public static void q(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("SIP_CONFIG", arg2);
    }

    public static void q(boolean arg2) {
        b.ax(ApplicationLoader.applicationContext).b("USER_STATUS", arg2);
    }

    public static boolean q() {
        return b.ax(ApplicationLoader.applicationContext).a("JCBLOCKED", true);
    }

    public static boolean q(Context arg2) {
        return b.ax(arg2).a("SHOW_NEWS_LIST", false);
    }

    public static void r(Context arg1, long arg2) {
        b.ax(arg1).a("SP_BOT_SYNC_PERIOD", Long.valueOf(arg2));
    }

    public static void r(Context arg1, String arg2) {
        b.ax(arg1).a("SP_ADS_TU", arg2);
    }

    public static void r(Context arg1, boolean arg2) {
        b.ax(arg1).b("IS_ENABLE_ADS", arg2);
    }

    public static void r(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("LHID", arg2);
    }

    public static boolean r() {
        return b.ax(ApplicationLoader.applicationContext).a("SAFE_CHECK", true);
    }

    public static boolean r(Context arg2) {
        return b.ax(arg2).a("SHOW_VITRIN_TAB", false);
    }

    public static long s(Context arg2) {
        return b.ax(arg2).c("NEWS_TAG_ID").longValue();
    }

    public static String s() {
        return b.ax(ApplicationLoader.applicationContext).a("SP_ADS_BOX_URL") + "?uid=" + UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
    }

    public static void s(Context arg2, long arg3) {
        b v2 = b.ax(arg2);
        v2.a("SP_LAST_SUCCESSFULLY_SYNC_BOT_" + UserConfig.selectedAccount, Long.valueOf(arg3));
    }

    public static void s(Context arg1, String arg2) {
        b.ax(arg1).a("SP_ADS_TU1", arg2);
    }

    public static void s(Context arg1, boolean arg2) {
        b.ax(arg1).b("IS_SHOW_COIN", arg2);
    }

    public static void s(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("THEMENAME", arg2);
    }

    public static void t(Context arg1, boolean arg2) {
        b.ax(arg1).b("SP_PROXY_HEALTH", arg2);
    }

    public static float t(Context arg1) {
        float v1 = b.ax(arg1).d("TEXT_SIZE").floatValue();
        if(v1 < 14f) {
            v1 = 16f;
        }

        return v1;
    }

    public static String t() {
        b v0 = b.ax(ApplicationLoader.applicationContext);
        StringBuilder v1 = new StringBuilder();
        v1.append("SP_CL_URL_");
        v1.append(UserConfig.selectedAccount);
        return v0.a(v1.toString());
    }

    public static void t(Context arg1, long arg2) {
        b.ax(arg1).a("CHANGE_JOIN_TIME", Long.valueOf(arg2));
    }

    public static void t(Context arg1, String arg2) {
        b.ax(arg1).a("SP_ADS_JOIN_MSG", arg2);
    }

    public static void t(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("APPIDNAME", arg2);
    }

    public static long u() {
        return b.ax(ApplicationLoader.applicationContext).c("SP_LAST_CALL_URL_JOB").longValue();
    }

    public static String u(Context arg2) {
        if(TextUtils.isEmpty(b.ax(arg2).a("TAG_API_BASE_URL"))) {
            return "http://betaws.tag.ir/tag/api/mtag/";
        }

        return b.ax(arg2).a("TAG_API_BASE_URL").trim();
    }

    public static void u(Context arg1, String arg2) {
        b.ax(arg1).a("SP_ADS_URL", arg2);
    }

    public static void u(Context arg1, boolean arg2) {
        b.ax(arg1).b("SP_SHOW_FILTER_DIALOG", arg2);
    }

    public static void u(String arg2) {
        b.ax(ApplicationLoader.applicationContext).a("VAST", arg2);
    }

    public static CUrl v() {
        if(b.d == null && !TextUtils.isEmpty(b.ax(ApplicationLoader.applicationContext).a("SP_CALL_URL_JOB"))) {
            b.d = new f().a(b.ax(ApplicationLoader.applicationContext).a("SP_CALL_URL_JOB"), CUrl.class);
        }

        return b.d;
    }

    public static ArrayList v(Context arg25) {
        ArrayList v0 = new ArrayList();
        String v1 = b.ax(arg25).a("DIALOG_TABS");
        if(TextUtils.isEmpty(((CharSequence)v1))) {
            DialogTab v1_1 = new DialogTab(2131493100, 2131231147, "TAB_ADS", false, 12, LocaleController.getString("Advertise", 2131624046));
            DialogTab v2 = new DialogTab(2131493109, 2131231636, "TAB_UNREAD_CHATS", true, 11, LocaleController.getString("unreadChats", 2131627084));
            DialogTab v10 = new DialogTab(2131493107, 2131231640, "TAB_LOCK", true, 10, LocaleController.getString("lockedChats", 2131626918));
            DialogTab v3 = new DialogTab(2131493105, 2131231622, "TAB_FAVES", false, 8, LocaleController.getString("Favorites", 2131624781));
            DialogTab v4 = new DialogTab(2131493102, 2131231612, "TAB_BOTS", true, 6, LocaleController.getString("Bots", 2131624226));
            DialogTab v5 = new DialogTab(2131493103, 2131231616, "TAB_CHANNELS", false, 5, LocaleController.getString("Channels", 2131624377));
            DialogTab v6 = new DialogTab(2131493108, 2131231633, "TAB_SGROUP", false, 7, LocaleController.getString("SuperGroups", 2131626176));
            DialogTab v7 = new DialogTab(2131493106, 2131231626, "TAB_GROUPS", false, 4, LocaleController.getString("Groups", 2131624922));
            DialogTab v8 = new DialogTab(2131493110, 2131231640, "TAB_USERS", false, 3, LocaleController.getString("Users", 2131626347));
            v0.add(new DialogTab(2131493101, 2131231608, "TAB_ALL", false, 0, LocaleController.getString("MyAppName", 2131625224)));
            v0.add(v8);
            v0.add(v7);
            v0.add(v6);
            v0.add(v5);
            v0.add(v4);
            v0.add(v2);
            v0.add(v3);
            v0.add(v10);
            if((b.ac(arg25)) && b.aa(arg25).size() > 0) {
                v0.add(v1_1);
            }

            return v0;
        }

        Object v0_1 = new f().a(v1, new a() {
        }.b());
        ArrayList v1_2 = new ArrayList();
        Iterator v0_2 = ((ArrayList)v0_1).iterator();
        while(v0_2.hasNext()) {
            Object v2_1 = v0_2.next();
            int v4_1 = 2131231640;
            switch(((DialogTab)v2_1).getDialogType()) {
                case 0: {
                    goto label_190;
                }
                case 3: {
                    goto label_186;
                }
                case 4: {
                    goto label_182;
                }
                case 5: {
                    goto label_178;
                }
                case 6: {
                    goto label_174;
                }
                case 7: {
                    goto label_170;
                }
                case 8: {
                    goto label_166;
                }
                case 10: {
                    goto label_164;
                }
                case 11: {
                    goto label_160;
                }
                case 12: {
                    goto label_156;
                }
            }

            goto label_194;
        label_178:
            ((DialogTab)v2_1).setTabLayoutResource(2131493103);
            int v3_1 = 2131231616;
            goto label_193;
        label_164:
            v3_1 = 2131493107;
            goto label_187;
        label_182:
            ((DialogTab)v2_1).setTabLayoutResource(2131493106);
            v3_1 = 2131231626;
            goto label_193;
        label_166:
            ((DialogTab)v2_1).setTabLayoutResource(2131493105);
            v3_1 = 2131231622;
            goto label_193;
        label_186:
            v3_1 = 2131493110;
        label_187:
            ((DialogTab)v2_1).setTabLayoutResource(v3_1);
            ((DialogTab)v2_1).setTabDrawable(v4_1);
            goto label_194;
        label_170:
            ((DialogTab)v2_1).setTabLayoutResource(2131493108);
            v3_1 = 2131231633;
            goto label_193;
        label_156:
            ((DialogTab)v2_1).setTabLayoutResource(2131493100);
            v3_1 = 2131231620;
            goto label_193;
        label_190:
            ((DialogTab)v2_1).setTabLayoutResource(2131493101);
            v3_1 = 2131231608;
            goto label_193;
        label_174:
            ((DialogTab)v2_1).setTabLayoutResource(2131493102);
            v3_1 = 2131231612;
            goto label_193;
        label_160:
            ((DialogTab)v2_1).setTabLayoutResource(2131493109);
            v3_1 = 2131231636;
        label_193:
            ((DialogTab)v2_1).setTabDrawable(v3_1);
        label_194:
            v1_2.add(v2_1);
        }

        return v1_2;
    }

    public static void v(Context arg1, String arg2) {
        b.ax(arg1).a("SP_ADS_TU_URL", arg2);
    }

    public static void v(Context arg1, boolean arg2) {
        b.ax(arg1).b("FILTER_CHANNEL", arg2);
    }

    public static void w(Context arg1, String arg2) {
        b.ax(arg1).a("SP_PROXY_ADDRESS", arg2);
    }

    public static ArrayList w(Context arg3) {
        ArrayList v0 = new ArrayList();
        Iterator v3 = b.v(arg3).iterator();
        while(v3.hasNext()) {
            Object v1 = v3.next();
            if(((DialogTab)v1).isHidden()) {
                continue;
            }

            v0.add(v1);
        }

        return v0;
    }

    public static Long w() {
        return b.ax(ApplicationLoader.applicationContext).c("SP_VITRIN_BG");
    }

    public static void w(Context arg1, boolean arg2) {
        b.ax(arg1).b("CURRENT_USER_ADDED_TO_FAVE", arg2);
    }

    public static void x(Context arg1, String arg2) {
        b.ax(arg1).a("SP_PROXY_PORT", arg2);
    }

    public static int x(Context arg1) {
        return b.ax(arg1).b("SELECTED_FONT");
    }

    public static Long x() {
        return b.ax(ApplicationLoader.applicationContext).c("SP_VITRIN_LAST");
    }

    public static void y(Context arg1, String arg2) {
        b.ax(arg1).a("SP_PROXY_USERNAME", arg2);
    }

    public static boolean y() {
        return b.ax(ApplicationLoader.applicationContext).a("SP_TERM_ACC", false);
    }

    public static boolean y(Context arg2) {
        if(b.j == null) {
            b.j = Boolean.valueOf(b.ax(arg2).a("SHOW_HIDDEN_DIALOGS", false));
        }

        return b.j.booleanValue();
    }

    public static boolean z(Context arg2) {
        return b.ax(arg2).a("OFF_MODE", false);
    }

    public static void z(Context arg1, String arg2) {
        b.ax(arg1).a("SP_PROXY_PASSWORD", arg2);
    }

    public static long z() {
        return b.ax(ApplicationLoader.applicationContext).c("LAST_LH_T").longValue();
    }
}

