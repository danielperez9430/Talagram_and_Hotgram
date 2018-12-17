package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Xml;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.TimeZone;
import org.telegram.messenger.time.FastDateFormat;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$LangPackString;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_langPackDifference;
import org.telegram.tgnet.TLRPC$TL_langPackLanguage;
import org.telegram.tgnet.TLRPC$TL_langPackString;
import org.telegram.tgnet.TLRPC$TL_langPackStringDeleted;
import org.telegram.tgnet.TLRPC$TL_langPackStringPluralized;
import org.telegram.tgnet.TLRPC$TL_langpack_getDifference;
import org.telegram.tgnet.TLRPC$TL_langpack_getLangPack;
import org.telegram.tgnet.TLRPC$TL_langpack_getLanguages;
import org.telegram.tgnet.TLRPC$TL_userEmpty;
import org.telegram.tgnet.TLRPC$TL_userStatusLastMonth;
import org.telegram.tgnet.TLRPC$TL_userStatusLastWeek;
import org.telegram.tgnet.TLRPC$TL_userStatusRecently;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$Vector;
import org.xmlpull.v1.XmlPullParser;
import utils.b.a;

public class LocaleController {
    class org.telegram.messenger.LocaleController$1 {
    }

    public class LocaleInfo {
        public boolean builtIn;
        public String name;
        public String nameEnglish;
        public String pathToFile;
        public String shortName;
        public int version;

        public LocaleInfo() {
            super();
        }

        public static LocaleInfo createWithString(String arg4) {
            LocaleInfo v0 = null;
            if(arg4 != null) {
                if(arg4.length() == 0) {
                }
                else {
                    String[] v4 = arg4.split("\\|");
                    int v2 = 4;
                    if(v4.length >= v2) {
                        v0 = new LocaleInfo();
                        v0.name = v4[0];
                        v0.nameEnglish = v4[1];
                        v0.shortName = v4[2].toLowerCase();
                        v0.pathToFile = v4[3];
                        if(v4.length >= 5) {
                            v0.version = Utilities.parseInt(v4[v2]).intValue();
                        }
                    }
                }
            }

            return v0;
        }

        public String getKey() {
            if(this.pathToFile != null && !"remote".equals(this.pathToFile)) {
                return "local_" + this.shortName;
            }

            return this.shortName;
        }

        public File getPathToFile() {
            if(this.isRemote()) {
                File v1 = ApplicationLoader.getFilesDirFixed();
                StringBuilder v2 = new StringBuilder();
                v2.append("remote_");
                v2.append(this.shortName);
                v2.append(".xml");
                return new File(v1, v2.toString());
            }

            File v0 = !TextUtils.isEmpty(this.pathToFile) ? new File(this.pathToFile) : null;
            return v0;
        }

        public String getSaveString() {
            return this.name + "|" + this.nameEnglish + "|" + this.shortName + "|" + this.pathToFile + "|" + this.version;
        }

        public boolean isBuiltIn() {
            return this.builtIn;
        }

        public boolean isLocal() {
            boolean v0 = (TextUtils.isEmpty(this.pathToFile)) || (this.isRemote()) ? false : true;
            return v0;
        }

        public boolean isRemote() {
            return "remote".equals(this.pathToFile);
        }
    }

    public abstract class PluralRules {
        public PluralRules() {
            super();
        }

        abstract int quantityForNumber(int arg1);
    }

    public class PluralRules_Arabic extends PluralRules {
        public PluralRules_Arabic() {
            super();
        }

        public int quantityForNumber(int arg4) {
            int v0 = arg4 % 100;
            if(arg4 == 0) {
                return 1;
            }

            int v2 = 2;
            if(arg4 == 1) {
                return v2;
            }

            if(arg4 == v2) {
                return 4;
            }

            if(v0 >= 3 && v0 <= 10) {
                return 8;
            }

            if(v0 >= 11 && v0 <= 99) {
                return 16;
            }

            return 0;
        }
    }

    public class PluralRules_Balkan extends PluralRules {
        public PluralRules_Balkan() {
            super();
        }

        public int quantityForNumber(int arg5) {
            int v0 = arg5 % 100;
            arg5 %= 10;
            int v1 = 2;
            int v2 = 11;
            if(arg5 == 1 && v0 != v2) {
                return v1;
            }

            int v3 = 14;
            if(arg5 >= v1 && arg5 <= 4 && (v0 < 12 || v0 > v3)) {
                return 8;
            }

            if(arg5 != 0 && (arg5 < 5 || arg5 > 9) && (v0 < v2 || v0 > v3)) {
                return 0;
            }

            return 16;
        }
    }

    public class PluralRules_Breton extends PluralRules {
        public PluralRules_Breton() {
            super();
        }

        public int quantityForNumber(int arg3) {
            if(arg3 == 0) {
                return 1;
            }

            int v1 = 2;
            if(arg3 == 1) {
                return v1;
            }

            if(arg3 == v1) {
                return 4;
            }

            if(arg3 == 3) {
                return 8;
            }

            if(arg3 == 6) {
                return 16;
            }

            return 0;
        }
    }

    public class PluralRules_Czech extends PluralRules {
        public PluralRules_Czech() {
            super();
        }

        public int quantityForNumber(int arg3) {
            int v0 = 2;
            if(arg3 == 1) {
                return v0;
            }

            if(arg3 >= v0 && arg3 <= 4) {
                return 8;
            }

            return 0;
        }
    }

    public class PluralRules_French extends PluralRules {
        public PluralRules_French() {
            super();
        }

        public int quantityForNumber(int arg2) {
            if(arg2 >= 0) {
                int v0 = 2;
                if(arg2 < v0) {
                    return v0;
                }
            }

            return 0;
        }
    }

    public class PluralRules_Langi extends PluralRules {
        public PluralRules_Langi() {
            super();
        }

        public int quantityForNumber(int arg2) {
            if(arg2 == 0) {
                return 1;
            }

            if(arg2 > 0) {
                int v0 = 2;
                if(arg2 < v0) {
                    return v0;
                }
            }

            return 0;
        }
    }

    public class PluralRules_Latvian extends PluralRules {
        public PluralRules_Latvian() {
            super();
        }

        public int quantityForNumber(int arg3) {
            if(arg3 == 0) {
                return 1;
            }

            if(arg3 % 10 == 1 && arg3 % 100 != 11) {
                return 2;
            }

            return 0;
        }
    }

    public class PluralRules_Lithuanian extends PluralRules {
        public PluralRules_Lithuanian() {
            super();
        }

        public int quantityForNumber(int arg6) {
            int v0 = arg6 % 100;
            arg6 %= 10;
            int v1 = 19;
            int v2 = 2;
            int v3 = 11;
            if(arg6 == 1 && (v0 < v3 || v0 > v1)) {
                return v2;
            }

            if(arg6 >= v2 && arg6 <= 9 && (v0 < v3 || v0 > v1)) {
                return 8;
            }

            return 0;
        }
    }

    public class PluralRules_Macedonian extends PluralRules {
        public PluralRules_Macedonian() {
            super();
        }

        public int quantityForNumber(int arg3) {
            if(arg3 % 10 == 1 && arg3 != 11) {
                return 2;
            }

            return 0;
        }
    }

    public class PluralRules_Maltese extends PluralRules {
        public PluralRules_Maltese() {
            super();
        }

        public int quantityForNumber(int arg4) {
            int v0 = arg4 % 100;
            int v1 = 2;
            if(arg4 == 1) {
                return v1;
            }

            if(arg4 != 0 && (v0 < v1 || v0 > 10)) {
                if(v0 >= 11 && v0 <= 19) {
                    return 16;
                }

                return 0;
            }

            return 8;
        }
    }

    public class PluralRules_None extends PluralRules {
        public PluralRules_None() {
            super();
        }

        public int quantityForNumber(int arg1) {
            return 0;
        }
    }

    public class PluralRules_One extends PluralRules {
        public PluralRules_One() {
            super();
        }

        public int quantityForNumber(int arg2) {
            return arg2 == 1 ? 2 : 0;
        }
    }

    public class PluralRules_Polish extends PluralRules {
        public PluralRules_Polish() {
            super();
        }

        public int quantityForNumber(int arg5) {
            int v0 = arg5 % 100;
            int v1 = arg5 % 10;
            int v2 = 2;
            if(arg5 == 1) {
                return v2;
            }

            if(v1 >= v2 && v1 <= 4 && (v0 < 12 || v0 > 14) && (v0 < 22 || v0 > 24)) {
                return 8;
            }

            return 0;
        }
    }

    public class PluralRules_Romanian extends PluralRules {
        public PluralRules_Romanian() {
            super();
        }

        public int quantityForNumber(int arg3) {
            int v0 = arg3 % 100;
            if(arg3 == 1) {
                return 2;
            }

            if(arg3 != 0 && (v0 < 1 || v0 > 19)) {
                return 0;
            }

            return 8;
        }
    }

    public class PluralRules_Slovenian extends PluralRules {
        public PluralRules_Slovenian() {
            super();
        }

        public int quantityForNumber(int arg3) {
            arg3 %= 100;
            int v0 = 2;
            if(arg3 == 1) {
                return v0;
            }

            int v1 = 4;
            if(arg3 == v0) {
                return v1;
            }

            if(arg3 >= 3 && arg3 <= v1) {
                return 8;
            }

            return 0;
        }
    }

    public class PluralRules_Tachelhit extends PluralRules {
        public PluralRules_Tachelhit() {
            super();
        }

        public int quantityForNumber(int arg3) {
            int v0 = 2;
            if(arg3 >= 0 && arg3 <= 1) {
                return v0;
            }

            if(arg3 >= v0 && arg3 <= 10) {
                return 8;
            }

            return 0;
        }
    }

    public class PluralRules_Two extends PluralRules {
        public PluralRules_Two() {
            super();
        }

        public int quantityForNumber(int arg3) {
            int v0 = 2;
            if(arg3 == 1) {
                return v0;
            }

            if(arg3 == v0) {
                return 4;
            }

            return 0;
        }
    }

    public class PluralRules_Welsh extends PluralRules {
        public PluralRules_Welsh() {
            super();
        }

        public int quantityForNumber(int arg3) {
            if(arg3 == 0) {
                return 1;
            }

            int v1 = 2;
            if(arg3 == 1) {
                return v1;
            }

            if(arg3 == v1) {
                return 4;
            }

            if(arg3 == 3) {
                return 8;
            }

            if(arg3 == 6) {
                return 16;
            }

            return 0;
        }
    }

    public class PluralRules_Zero extends PluralRules {
        public PluralRules_Zero() {
            super();
        }

        public int quantityForNumber(int arg2) {
            if(arg2 != 0) {
                if(arg2 == 1) {
                }
                else {
                    return 0;
                }
            }

            return 2;
        }
    }

    class TimeZoneChangedReceiver extends BroadcastReceiver {
        TimeZoneChangedReceiver(LocaleController arg1, org.telegram.messenger.LocaleController$1 arg2) {
            this(arg1);
        }

        private TimeZoneChangedReceiver(LocaleController arg1) {
            LocaleController.this = arg1;
            super();
        }

        public static void lambda$onReceive$0(TimeZoneChangedReceiver arg2) {
            if(!arg2.this$0.formatterMonth.getTimeZone().equals(TimeZone.getDefault())) {
                LocaleController.getInstance().recreateFormatters();
            }
        }

        public void onReceive(Context arg1, Intent arg2) {
            ApplicationLoader.applicationHandler.post(new -$$Lambda$LocaleController$TimeZoneChangedReceiver$-tF936vTwTBeR7FivZoJHGJKUvY(this));
        }
    }

    private static volatile LocaleController Instance = null;
    static final int QUANTITY_FEW = 8;
    static final int QUANTITY_MANY = 16;
    static final int QUANTITY_ONE = 2;
    static final int QUANTITY_OTHER = 0;
    static final int QUANTITY_TWO = 4;
    static final int QUANTITY_ZERO = 1;
    private HashMap allRules;
    private boolean changingConfiguration;
    public FastDateFormat chatDate;
    public FastDateFormat chatFullDate;
    private HashMap currencyValues;
    private Locale currentLocale;
    private LocaleInfo currentLocaleInfo;
    private PluralRules currentPluralRules;
    public FastDateFormat formatterBannedUntil;
    public FastDateFormat formatterBannedUntilThisYear;
    public FastDateFormat formatterDay;
    public FastDateFormat formatterMonth;
    public FastDateFormat formatterMonthYear;
    public FastDateFormat formatterStats;
    public FastDateFormat formatterWeek;
    public FastDateFormat formatterYear;
    public FastDateFormat formatterYearMax;
    public static boolean is24HourFormat = false;
    public static boolean isRTL = false;
    private String languageOverride;
    public ArrayList languages;
    public HashMap languagesDict;
    private boolean loadingRemoteLanguages;
    private HashMap localeValues;
    public static int nameDisplayOrder = 1;
    private ArrayList otherLanguages;
    private boolean reloadLastFile;
    public ArrayList remoteLanguages;
    private Locale systemDefaultLocale;
    private HashMap translitChars;

    static {
    }

    public LocaleController() {
        Object v5;
        LocaleController v1 = this;
        super();
        v1.allRules = new HashMap();
        v1.localeValues = new HashMap();
        boolean v0 = false;
        v1.changingConfiguration = false;
        v1.languages = new ArrayList();
        v1.remoteLanguages = new ArrayList();
        v1.languagesDict = new HashMap();
        v1.otherLanguages = new ArrayList();
        v1.addRules(new String[]{"bem", "brx", "da", "de", "el", "en", "eo", "es", "et", "fi", "fo", "gl", "he", "iw", "it", "nb", "nl", "nn", "no", "sv", "af", "bg", "bn", "ca", "eu", "fur", "fy", "gu", "ha", "is", "ku", "lb", "ml", "mr", "nah", "ne", "om", "or", "pa", "pap", "ps", "so", "sq", "sw", "ta", "te", "tk", "ur", "zu", "mn", "gsw", "chr", "rm", "pt", "an", "ast"}, new PluralRules_One());
        v1.addRules(new String[]{"cs", "sk"}, new PluralRules_Czech());
        v1.addRules(new String[]{"ff", "fr", "kab"}, new PluralRules_French());
        v1.addRules(new String[]{"hr", "ru", "sr", "uk", "be", "bs", "sh"}, new PluralRules_Balkan());
        v1.addRules(new String[]{"lv"}, new PluralRules_Latvian());
        v1.addRules(new String[]{"lt"}, new PluralRules_Lithuanian());
        v1.addRules(new String[]{"pl"}, new PluralRules_Polish());
        v1.addRules(new String[]{"ro", "mo"}, new PluralRules_Romanian());
        v1.addRules(new String[]{"sl"}, new PluralRules_Slovenian());
        v1.addRules(new String[]{"ar"}, new PluralRules_Arabic());
        v1.addRules(new String[]{"mk"}, new PluralRules_Macedonian());
        v1.addRules(new String[]{"cy"}, new PluralRules_Welsh());
        v1.addRules(new String[]{"br"}, new PluralRules_Breton());
        v1.addRules(new String[]{"lag"}, new PluralRules_Langi());
        v1.addRules(new String[]{"shi"}, new PluralRules_Tachelhit());
        v1.addRules(new String[]{"mt"}, new PluralRules_Maltese());
        v1.addRules(new String[]{"ga", "se", "sma", "smi", "smj", "smn", "sms"}, new PluralRules_Two());
        v1.addRules(new String[]{"ak", "am", "bh", "fil", "tl", "guw", "hi", "ln", "mg", "nso", "ti", "wa"}, new PluralRules_Zero());
        v1.addRules(new String[]{"az", "bm", "fa", "ig", "hu", "ja", "kde", "kea", "ko", "my", "ses", "sg", "to", "tr", "vi", "wo", "yo", "zh", "bo", "dz", "id", "jv", "jw", "ka", "km", "kn", "ms", "th", "in"}, new PluralRules_None());
        LocaleInfo v2 = new LocaleInfo();
        v2.name = "English";
        v2.nameEnglish = "English";
        v2.shortName = "en";
        String v3 = null;
        v2.pathToFile = v3;
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        v2 = new LocaleInfo();
        v2.name = "پارسی";
        v2.nameEnglish = "Parsi";
        v2.shortName = "fa";
        v2.pathToFile = v3;
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        v2 = new LocaleInfo();
        v2.name = "Italiano";
        v2.nameEnglish = "Italian";
        v2.shortName = "it";
        v2.pathToFile = v3;
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        v2 = new LocaleInfo();
        v2.name = "Español";
        v2.nameEnglish = "Spanish";
        v2.shortName = "es";
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        v2 = new LocaleInfo();
        v2.name = "Deutsch";
        v2.nameEnglish = "German";
        v2.shortName = "de";
        v2.pathToFile = v3;
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        v2 = new LocaleInfo();
        v2.name = "Nederlands";
        v2.nameEnglish = "Dutch";
        v2.shortName = "nl";
        v2.pathToFile = v3;
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        v2 = new LocaleInfo();
        v2.name = "العربية";
        v2.nameEnglish = "Arabic";
        v2.shortName = "ar";
        v2.pathToFile = v3;
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        v2 = new LocaleInfo();
        v2.name = "Português (Brasil)";
        v2.nameEnglish = "Portuguese (Brazil)";
        v2.shortName = "pt_br";
        v2.pathToFile = v3;
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        v2 = new LocaleInfo();
        v2.name = "한국어";
        v2.nameEnglish = "Korean";
        v2.shortName = "ko";
        v2.pathToFile = v3;
        v2.builtIn = true;
        v1.languages.add(v2);
        v1.languagesDict.put(v2.shortName, v2);
        this.loadOtherLanguages();
        if(v1.remoteLanguages.isEmpty()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$LocaleController$NNZIDoHieDUSrd9BgBq36GRonDE(v1));
        }

        int v2_1;
        for(v2_1 = 0; v2_1 < v1.otherLanguages.size(); ++v2_1) {
            v5 = v1.otherLanguages.get(v2_1);
            v1.languages.add(v5);
            v1.languagesDict.put(((LocaleInfo)v5).getKey(), v5);
        }

        for(v2_1 = 0; v2_1 < v1.remoteLanguages.size(); ++v2_1) {
            v5 = v1.remoteLanguages.get(v2_1);
            LocaleInfo v6 = v1.getLanguageFromDict(((LocaleInfo)v5).getKey());
            if(v6 != null) {
                v6.pathToFile = ((LocaleInfo)v5).pathToFile;
                v6.version = ((LocaleInfo)v5).version;
                v1.remoteLanguages.set(v2_1, v6);
            }
            else {
                v1.languages.add(v5);
                v1.languagesDict.put(((LocaleInfo)v5).getKey(), v5);
            }
        }

        v1.systemDefaultLocale = Locale.getDefault();
        LocaleController.is24HourFormat = DateFormat.is24HourFormat(ApplicationLoader.applicationContext);
        try {
            String v2_2 = MessagesController.getGlobalMainSettings().getString("language", v3);
            if(v2_2 != null) {
                if(v2_2.contentEquals("fa_IR")) {
                    v2_2 = "fa";
                }

                v2 = v1.getLanguageFromDict(v2_2);
                if(v2 == null) {
                    goto label_624;
                }

                v0 = true;
            }
            else {
                v2 = ((LocaleInfo)v3);
            }

        label_624:
            if(v2 == null && v1.systemDefaultLocale.getLanguage() != null) {
                v2 = v1.getLanguageFromDict(v1.systemDefaultLocale.getLanguage());
            }

            if(v2 == null) {
                v2 = v1.getLanguageFromDict(v1.getLocaleString(v1.systemDefaultLocale));
                if(v2 == null) {
                    v2 = v1.getLanguageFromDict("en");
                }
            }

            v1.applyLanguage(v2, v0, true, UserConfig.selectedAccount);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }

        try {
            ApplicationLoader.applicationContext.registerReceiver(new TimeZoneChangedReceiver(v1, ((org.telegram.messenger.LocaleController$1)v3)), new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public static String addNbsp(String arg2) {
        return arg2.replace(' ', ' ');
    }

    private void addRules(String[] arg5, PluralRules arg6) {
        int v0 = arg5.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.allRules.put(arg5[v1], arg6);
        }
    }

    public void applyLanguage(LocaleInfo arg8, boolean arg9, boolean arg10, int arg11) {
        this.applyLanguage(arg8, arg9, arg10, false, false, arg11);
    }

    public void applyLanguage(LocaleInfo arg7, boolean arg8, boolean arg9, boolean arg10, boolean arg11, int arg12) {
        if(arg7 != null && !TextUtils.isEmpty(arg7.getKey()) && (arg7.getKey().contentEquals("fa_IR"))) {
            arg7.shortName = "fa";
        }

        if(arg7 == null) {
            return;
        }

        File v0 = arg7.getPathToFile();
        String v1 = arg7.shortName;
        if(!arg9) {
            ConnectionsManager.setLangCode(v1.replace("_", "-"));
        }

        if(arg7.isRemote()) {
            if(!arg11 && (v0.exists())) {
                goto label_41;
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("reload locale because file doesn\'t exist " + v0);
            }

            if(arg9) {
                AndroidUtilities.runOnUIThread(new -$$Lambda$LocaleController$TR9kIcfgx1RrMlui5-1UfRzbfxs(this, arg7, arg12));
                goto label_41;
            }

            this.applyRemoteLanguage(arg7, true, arg12);
        }

        try {
        label_41:
            String[] v1_1 = arg7.shortName.split("_");
            Locale v3 = v1_1.length == 1 ? new Locale(arg7.shortName) : new Locale(v1_1[0], v1_1[1]);
            if(arg8) {
                this.languageOverride = arg7.shortName;
                SharedPreferences$Editor v8 = MessagesController.getGlobalMainSettings().edit();
                v8.putString("language", arg7.getKey());
                v8.commit();
            }

            if(v0 == null) {
                this.localeValues.clear();
            }
            else if(!arg10) {
                this.localeValues = this.getLocaleFileStrings(v0);
            }

            this.currentLocale = v3;
            this.currentLocaleInfo = arg7;
            this.currentPluralRules = this.allRules.get(v1_1[0]);
            if(this.currentPluralRules == null) {
                this.currentPluralRules = this.allRules.get(this.currentLocale.getLanguage());
            }

            if(this.currentPluralRules == null) {
                this.currentPluralRules = new PluralRules_None();
            }

            this.changingConfiguration = true;
            Locale.setDefault(this.currentLocale);
            Configuration v7_1 = new Configuration();
            v7_1.locale = this.currentLocale;
            ApplicationLoader.applicationContext.getResources().updateConfiguration(v7_1, ApplicationLoader.applicationContext.getResources().getDisplayMetrics());
            this.changingConfiguration = false;
            if(!this.reloadLastFile) {
                goto label_115;
            }

            if(arg9) {
                AndroidUtilities.runOnUIThread(new -$$Lambda$LocaleController$pGv1_ZxUq7_k1dcgy6Mauzy_7Gs(this, arg12));
            }
            else {
                this.reloadCurrentRemoteLocale(arg12);
            }

            this.reloadLastFile = false;
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
            this.changingConfiguration = false;
        }

    label_115:
        this.recreateFormatters();
    }

    public boolean applyLanguageFile(File arg10, int arg11) {
        try {
            HashMap v1 = this.getLocaleFileStrings(arg10);
            Object v2 = v1.get("LanguageName");
            Object v3 = v1.get("LanguageNameInEnglish");
            Object v4 = v1.get("LanguageCode");
            if(v2 == null) {
                return 0;
            }

            if(((String)v2).length() <= 0) {
                return 0;
            }

            if(v3 == null) {
                return 0;
            }

            if(((String)v3).length() <= 0) {
                return 0;
            }

            if(v4 == null) {
                return 0;
            }

            if(((String)v4).length() <= 0) {
                return 0;
            }

            if(!((String)v2).contains("&")) {
                if(((String)v2).contains("|")) {
                }
                else if(!((String)v3).contains("&")) {
                    if(((String)v3).contains("|")) {
                    }
                    else if(!((String)v4).contains("&") && !((String)v4).contains("|") && !((String)v4).contains("/")) {
                        if(((String)v4).contains("\\")) {
                        }
                        else {
                            File v6 = ApplicationLoader.getFilesDirFixed();
                            StringBuilder v7 = new StringBuilder();
                            v7.append(((String)v4));
                            v7.append(".xml");
                            File v5 = new File(v6, v7.toString());
                            if(!AndroidUtilities.copyFile(arg10, v5)) {
                                return 0;
                            }
                            else {
                                StringBuilder v10_1 = new StringBuilder();
                                v10_1.append("local_");
                                v10_1.append(((String)v4).toLowerCase());
                                LocaleInfo v10_2 = this.getLanguageFromDict(v10_1.toString());
                                if(v10_2 == null) {
                                    v10_2 = new LocaleInfo();
                                    v10_2.name = ((String)v2);
                                    v10_2.nameEnglish = ((String)v3);
                                    v10_2.shortName = ((String)v4).toLowerCase();
                                    v10_2.pathToFile = v5.getAbsolutePath();
                                    this.languages.add(v10_2);
                                    this.languagesDict.put(v10_2.getKey(), v10_2);
                                    this.otherLanguages.add(v10_2);
                                    this.saveOtherLanguages();
                                }

                                this.localeValues = v1;
                                this.applyLanguage(v10_2, true, false, true, false, arg11);
                                return 1;
                            }
                        }
                    }
                }
            }
        }
        catch(Exception v10) {
            FileLog.e(((Throwable)v10));
            return 0;
        }

        return 0;
    }

    private void applyRemoteLanguage(LocaleInfo arg4, boolean arg5, int arg6) {
        -$$Lambda$LocaleController$-7V4kuFlEsJys4Vw8LeBFzz-7P4 v0;
        ConnectionsManager v4;
        TL_langpack_getDifference v5;
        if(arg4 != null && (arg4 == null || (arg4.isRemote()))) {
            int v1 = 8;
            if(arg4.version == 0 || (arg5)) {
                int v5_1;
                for(v5_1 = 0; v5_1 < 3; ++v5_1) {
                    ConnectionsManager.setLangCode(arg4.shortName);
                }

                TL_langpack_getLangPack v5_2 = new TL_langpack_getLangPack();
                v5_2.lang_code = arg4.shortName.replace("_", "-");
                v4 = ConnectionsManager.getInstance(arg6);
                -$$Lambda$LocaleController$3W8ISUcEDg5fZnaCKk93BQlrzNQ v0_1 = new -$$Lambda$LocaleController$3W8ISUcEDg5fZnaCKk93BQlrzNQ(this, arg6);
            }
            else {
                v5 = new TL_langpack_getDifference();
                v5.from_version = arg4.version;
                v4 = ConnectionsManager.getInstance(arg6);
                v0 = new -$$Lambda$LocaleController$-7V4kuFlEsJys4Vw8LeBFzz-7P4(this, arg6);
            }

            v4.sendRequest(((TLObject)v5), ((RequestDelegate)v0), v1);
        }
    }

    public void checkUpdateForCurrentRemoteLocale(int arg2, int arg3) {
        if(this.currentLocaleInfo != null && (this.currentLocaleInfo == null || (this.currentLocaleInfo.isRemote())) && this.currentLocaleInfo.version < arg3) {
            this.applyRemoteLanguage(this.currentLocaleInfo, false, arg2);
        }
    }

    private FastDateFormat createFormatter(Locale arg2, String arg3, String arg4) {
        FastDateFormat v3;
        if(arg3 == null || arg3.length() == 0) {
            arg3 = arg4;
        }

        try {
            v3 = FastDateFormat.getInstance(arg3, arg2);
        }
        catch(Exception ) {
            v3 = FastDateFormat.getInstance(arg4, arg2);
        }

        return v3;
    }

    public boolean deleteLanguage(LocaleInfo arg5, int arg6) {
        if(arg5.pathToFile != null) {
            if(arg5.isRemote()) {
            }
            else {
                if(this.currentLocaleInfo == arg5) {
                    LocaleInfo v0 = null;
                    if(this.systemDefaultLocale.getLanguage() != null) {
                        v0 = this.getLanguageFromDict(this.systemDefaultLocale.getLanguage());
                    }

                    if(v0 == null) {
                        v0 = this.getLanguageFromDict(this.getLocaleString(this.systemDefaultLocale));
                    }

                    if(v0 == null) {
                        v0 = this.getLanguageFromDict("en");
                    }

                    this.applyLanguage(v0, true, false, arg6);
                }

                this.otherLanguages.remove(arg5);
                this.languages.remove(arg5);
                this.languagesDict.remove(arg5.shortName);
                new File(arg5.pathToFile).delete();
                this.saveOtherLanguages();
                return 1;
            }
        }

        return 0;
    }

    private String escapeString(String arg3) {
        if(arg3.contains("[CDATA")) {
            return arg3;
        }

        return arg3.replace("<", "&lt;").replace(">", "&gt;").replace("& ", "&amp; ");
    }

    public static String formatCallDuration(int arg4) {
        int v0 = 60;
        int v1 = 3600;
        if(arg4 > v1) {
            String v2 = LocaleController.formatPluralString("Hours", arg4 / 3600);
            arg4 = arg4 % v1 / v0;
            if(arg4 > 0) {
                v2 = v2 + ", " + LocaleController.formatPluralString("Minutes", arg4);
            }

            return v2;
        }

        if(arg4 > v0) {
            return LocaleController.formatPluralString("Minutes", arg4 / v0);
        }

        return LocaleController.formatPluralString("Seconds", arg4);
    }

    public String formatCurrencyDecimalString(long arg8, String arg10, boolean arg11) {
        int v0;
        arg10 = arg10.toUpperCase();
        arg8 = Math.abs(arg8);
        switch(arg10.hashCode()) {
            case 65726: {
                if(!arg10.equals("BHD")) {
                    goto label_157;
                }

                v0 = 2;
                break;
            }
            case 65759: {
                if(!arg10.equals("BIF")) {
                    goto label_157;
                }

                v0 = 9;
                break;
            }
            case 66267: {
                if(!arg10.equals("BYR")) {
                    goto label_157;
                }

                v0 = 10;
                break;
            }
            case 66813: {
                if(!arg10.equals("CLF")) {
                    goto label_157;
                }

                v0 = 0;
                break;
            }
            case 66823: {
                if(!arg10.equals("CLP")) {
                    goto label_157;
                }

                v0 = 11;
                break;
            }
            case 67122: {
                if(!arg10.equals("CVE")) {
                    goto label_157;
                }

                v0 = 12;
                break;
            }
            case 67712: {
                if(!arg10.equals("DJF")) {
                    goto label_157;
                }

                v0 = 13;
                break;
            }
            case 70719: {
                if(!arg10.equals("GNF")) {
                    goto label_157;
                }

                v0 = 14;
                break;
            }
            case 72732: {
                if(!arg10.equals("IQD")) {
                    goto label_157;
                }

                v0 = 3;
                break;
            }
            case 72777: {
                if(!arg10.equals("IRR")) {
                    goto label_157;
                }

                v0 = 1;
                break;
            }
            case 72801: {
                if(!arg10.equals("ISK")) {
                    goto label_157;
                }

                v0 = 15;
                break;
            }
            case 73631: {
                if(!arg10.equals("JOD")) {
                    goto label_157;
                }

                v0 = 4;
                break;
            }
            case 73683: {
                if(!arg10.equals("JPY")) {
                    goto label_157;
                }

                v0 = 16;
                break;
            }
            case 74532: {
                if(!arg10.equals("KMF")) {
                    goto label_157;
                }

                v0 = 17;
                break;
            }
            case 74704: {
                if(!arg10.equals("KRW")) {
                    goto label_157;
                }

                v0 = 18;
                break;
            }
            case 74840: {
                if(!arg10.equals("KWD")) {
                    goto label_157;
                }

                v0 = 5;
                break;
            }
            case 75863: {
                if(!arg10.equals("LYD")) {
                    goto label_157;
                }

                v0 = 6;
                break;
            }
            case 76263: {
                if(!arg10.equals("MGA")) {
                    goto label_157;
                }

                v0 = 19;
                break;
            }
            case 76618: {
                if(!arg10.equals("MRO")) {
                    goto label_157;
                }

                v0 = 29;
                break;
            }
            case 78388: {
                if(!arg10.equals("OMR")) {
                    goto label_157;
                }

                v0 = 7;
                break;
            }
            case 79710: {
                if(!arg10.equals("PYG")) {
                    goto label_157;
                }

                v0 = 20;
                break;
            }
            case 81569: {
                if(!arg10.equals("RWF")) {
                    goto label_157;
                }

                v0 = 21;
                break;
            }
            case 83210: {
                if(!arg10.equals("TND")) {
                    goto label_157;
                }

                v0 = 8;
                break;
            }
            case 83974: {
                if(!arg10.equals("UGX")) {
                    goto label_157;
                }

                v0 = 22;
                break;
            }
            case 84517: {
                if(arg10.equals("UYI")) {
                    v0 = 23;
                    goto label_158;
                }

            label_157:
                v0 = -1;
                break;
            }
            case 85132: {
                if(!arg10.equals("VND")) {
                    goto label_157;
                }

                v0 = 24;
                break;
            }
            case 85367: {
                if(!arg10.equals("VUV")) {
                    goto label_157;
                }

                v0 = 25;
                break;
            }
            case 86653: {
                if(!arg10.equals("XAF")) {
                    goto label_157;
                }

                v0 = 26;
                break;
            }
            case 87087: {
                if(!arg10.equals("XOF")) {
                    goto label_157;
                }

                v0 = 27;
                break;
            }
            case 87118: {
                if(!arg10.equals("XPF")) {
                    goto label_157;
                }

                v0 = 28;
                break;
            }
            default: {
                goto label_157;
            }
        }

    label_158:
        switch(v0) {
            case 0: {
                goto label_189;
            }
            case 1: {
                goto label_176;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                goto label_172;
            }
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: {
                goto label_169;
            }
            case 29: {
                goto label_165;
            }
        }

        String v0_1 = " %.2f";
        double v8 = ((double)arg8);
        double v3 = 100;
        goto label_162;
    label_165:
        v0_1 = " %.1f";
        v8 = ((double)arg8);
        v3 = 10;
        goto label_162;
    label_169:
        v0_1 = " %.0f";
        v8 = ((double)arg8);
        goto label_193;
    label_172:
        v0_1 = " %.3f";
        v8 = ((double)arg8);
        v3 = 1000;
        goto label_162;
    label_176:
        v3 = ((double)((((float)arg8)) / 100f));
        v0_1 = arg8 % 100 == 0 ? " %.0f" : " %.2f";
        v8 = v3;
        goto label_193;
    label_189:
        v0_1 = " %.4f";
        v8 = ((double)arg8);
        v3 = 10000;
    label_162:
        Double.isNaN(v8);
        v8 /= v3;
    label_193:
        Locale v3_1 = Locale.US;
        if(arg11) {
        }
        else {
            arg10 = "" + v0_1;
        }

        return String.format(v3_1, arg10, Double.valueOf(v8)).trim();
    }

    public String formatCurrencyString(long arg11, String arg13) {
        arg13 = arg13.toUpperCase();
        long v0 = 0;
        int v2 = Long.compare(arg11, v0) < 0 ? 1 : 0;
        arg11 = Math.abs(arg11);
        Currency v5 = Currency.getInstance(arg13);
        int v6 = -1;
        switch(arg13.hashCode()) {
            case 65726: {
                if(!arg13.equals("BHD")) {
                    goto label_164;
                }

                v6 = 2;
                break;
            }
            case 65759: {
                if(!arg13.equals("BIF")) {
                    goto label_164;
                }

                v6 = 9;
                break;
            }
            case 66267: {
                if(!arg13.equals("BYR")) {
                    goto label_164;
                }

                v6 = 10;
                break;
            }
            case 66813: {
                if(!arg13.equals("CLF")) {
                    goto label_164;
                }

                v6 = 0;
                break;
            }
            case 66823: {
                if(!arg13.equals("CLP")) {
                    goto label_164;
                }

                v6 = 11;
                break;
            }
            case 67122: {
                if(!arg13.equals("CVE")) {
                    goto label_164;
                }

                v6 = 12;
                break;
            }
            case 67712: {
                if(!arg13.equals("DJF")) {
                    goto label_164;
                }

                v6 = 13;
                break;
            }
            case 70719: {
                if(!arg13.equals("GNF")) {
                    goto label_164;
                }

                v6 = 14;
                break;
            }
            case 72732: {
                if(!arg13.equals("IQD")) {
                    goto label_164;
                }

                v6 = 3;
                break;
            }
            case 72777: {
                if(!arg13.equals("IRR")) {
                    goto label_164;
                }

                v6 = 1;
                break;
            }
            case 72801: {
                if(!arg13.equals("ISK")) {
                    goto label_164;
                }

                v6 = 15;
                break;
            }
            case 73631: {
                if(!arg13.equals("JOD")) {
                    goto label_164;
                }

                v6 = 4;
                break;
            }
            case 73683: {
                if(!arg13.equals("JPY")) {
                    goto label_164;
                }

                v6 = 16;
                break;
            }
            case 74532: {
                if(!arg13.equals("KMF")) {
                    goto label_164;
                }

                v6 = 17;
                break;
            }
            case 74704: {
                if(!arg13.equals("KRW")) {
                    goto label_164;
                }

                v6 = 18;
                break;
            }
            case 74840: {
                if(!arg13.equals("KWD")) {
                    goto label_164;
                }

                v6 = 5;
                break;
            }
            case 75863: {
                if(!arg13.equals("LYD")) {
                    goto label_164;
                }

                v6 = 6;
                break;
            }
            case 76263: {
                if(!arg13.equals("MGA")) {
                    goto label_164;
                }

                v6 = 19;
                break;
            }
            case 76618: {
                if(!arg13.equals("MRO")) {
                    goto label_164;
                }

                v6 = 29;
                break;
            }
            case 78388: {
                if(!arg13.equals("OMR")) {
                    goto label_164;
                }

                v6 = 7;
                break;
            }
            case 79710: {
                if(!arg13.equals("PYG")) {
                    goto label_164;
                }

                v6 = 20;
                break;
            }
            case 81569: {
                if(!arg13.equals("RWF")) {
                    goto label_164;
                }

                v6 = 21;
                break;
            }
            case 83210: {
                if(!arg13.equals("TND")) {
                    goto label_164;
                }

                v6 = 8;
                break;
            }
            case 83974: {
                if(!arg13.equals("UGX")) {
                    goto label_164;
                }

                v6 = 22;
                break;
            }
            case 84517: {
                if(!arg13.equals("UYI")) {
                    goto label_164;
                }

                v6 = 23;
                break;
            }
            case 85132: {
                if(!arg13.equals("VND")) {
                    goto label_164;
                }

                v6 = 24;
                break;
            }
            case 85367: {
                if(!arg13.equals("VUV")) {
                    goto label_164;
                }

                v6 = 25;
                break;
            }
            case 86653: {
                if(!arg13.equals("XAF")) {
                    goto label_164;
                }

                v6 = 26;
                break;
            }
            case 87087: {
                if(!arg13.equals("XOF")) {
                    goto label_164;
                }

                v6 = 27;
                break;
            }
            case 87118: {
                if(!arg13.equals("XPF")) {
                    goto label_164;
                }

                v6 = 28;
                break;
            }
            default: {
                break;
            }
        }

    label_164:
        switch(v6) {
            case 0: {
                goto label_194;
            }
            case 1: {
                goto label_182;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                goto label_178;
            }
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: {
                goto label_175;
            }
            case 29: {
                goto label_171;
            }
        }

        String v0_1 = " %.2f";
        double v11 = ((double)arg11);
        double v6_1 = 100;
        goto label_168;
    label_171:
        v0_1 = " %.1f";
        v11 = ((double)arg11);
        v6_1 = 10;
        goto label_168;
    label_175:
        v0_1 = " %.0f";
        v11 = ((double)arg11);
        goto label_198;
    label_178:
        v0_1 = " %.3f";
        v11 = ((double)arg11);
        v6_1 = 1000;
        goto label_168;
    label_182:
        v6_1 = ((double)((((float)arg11)) / 100f));
        v0_1 = arg11 % 100 == v0 ? " %.0f" : " %.2f";
        v11 = v6_1;
        goto label_198;
    label_194:
        v0_1 = " %.4f";
        v11 = ((double)arg11);
        v6_1 = 10000;
    label_168:
        Double.isNaN(v11);
        v11 /= v6_1;
    label_198:
        if(v5 != null) {
            Locale v0_2 = this.currentLocale != null ? this.currentLocale : this.systemDefaultLocale;
            NumberFormat v0_3 = NumberFormat.getCurrencyInstance(v0_2);
            v0_3.setCurrency(v5);
            if(arg13.equals("IRR")) {
                v0_3.setMaximumFractionDigits(0);
            }

            StringBuilder v13 = new StringBuilder();
            String v1 = v2 != 0 ? "-" : "";
            v13.append(v1);
            v13.append(v0_3.format(v11));
            return v13.toString();
        }

        StringBuilder v1_1 = new StringBuilder();
        String v2_1 = v2 != 0 ? "-" : "";
        v1_1.append(v2_1);
        Locale v2_2 = Locale.US;
        StringBuilder v5_1 = new StringBuilder();
        v5_1.append(arg13);
        v5_1.append(v0_1);
        v1_1.append(String.format(v2_2, v5_1.toString(), Double.valueOf(v11)));
        return v1_1.toString();
    }

    public static String formatDate(long arg5) {
        arg5 *= 1000;
        try {
            Calendar v0 = Calendar.getInstance();
            int v2 = v0.get(6);
            int v4 = v0.get(1);
            v0.setTimeInMillis(arg5);
            int v1 = v0.get(6);
            int v0_1 = v0.get(1);
            if(v1 == v2 && v4 == v0_1) {
                return LocaleController.getInstance().formatterDay.format(new Date(arg5));
            }

            if(v1 + 1 == v2 && v4 == v0_1) {
                return LocaleController.getString("Yesterday", 2131626437);
            }

            if(Math.abs(System.currentTimeMillis() - arg5) < 31536000000L) {
                return LocaleController.getInstance().formatterMonth.format(new Date(arg5));
            }

            return LocaleController.getInstance().formatterYear.format(new Date(arg5));
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
            return "LOC_ERR: formatDate";
        }
    }

    public static String formatDateAudio(long arg8) {
        // Method was not decompiled
    }

    public static String formatDateCallLog(long arg8) {
        // Method was not decompiled
    }

    public static String formatDateChat(long arg5) {
        try {
            arg5 *= 1000;
            Calendar.getInstance().setTimeInMillis(arg5);
            if(Math.abs(System.currentTimeMillis() - arg5) < 31536000000L) {
                return LocaleController.getInstance().chatDate.format(arg5);
            }

            return LocaleController.getInstance().chatFullDate.format(arg5);
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
            return "LOC_ERR: formatDateChat";
        }
    }

    public static String formatDateForBan(long arg3) {
        arg3 *= 1000;
        try {
            Calendar v0 = Calendar.getInstance();
            int v2 = v0.get(1);
            v0.setTimeInMillis(arg3);
            if(v2 == v0.get(1)) {
                return LocaleController.getInstance().formatterBannedUntilThisYear.format(new Date(arg3));
            }

            return LocaleController.getInstance().formatterBannedUntil.format(new Date(arg3));
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
            return "LOC_ERR";
        }
    }

    public static String formatDateOnline(long arg9) {
        // Method was not decompiled
    }

    public static String formatLocationLeftTime(int arg6) {
        Object[] v5;
        String v6;
        int v0 = arg6 / 60 / 60;
        arg6 -= v0 * 60 * 60;
        int v1 = arg6 / 60;
        arg6 -= v1 * 60;
        int v2 = 30;
        int v4 = 1;
        if(v0 != 0) {
            v6 = "%dh";
            v5 = new Object[1];
            if(v1 > v2) {
            }
            else {
                v4 = 0;
            }

            v5[0] = Integer.valueOf(v0 + v4);
            v6 = String.format(v6, v5);
        }
        else {
            if(v1 != 0) {
                String v0_1 = "%d";
                v5 = new Object[1];
                if(arg6 > v2) {
                }
                else {
                    v4 = 0;
                }

                v5[0] = Integer.valueOf(v1 + v4);
                return String.format(v0_1, v5);
            }

            v6 = String.format("%d", Integer.valueOf(arg6));
        }

        return v6;
    }

    public static String formatLocationUpdateDate(long arg9) {
        // Method was not decompiled
    }

    public static String formatPluralString(String arg3, int arg4) {
        if(arg3 != null && arg3.length() != 0) {
            if(LocaleController.getInstance().currentPluralRules == null) {
            }
            else {
                String v0 = LocaleController.getInstance().stringForQuantity(LocaleController.getInstance().currentPluralRules.quantityForNumber(arg4));
                arg3 = arg3 + "_" + v0;
                return LocaleController.formatString(arg3, ApplicationLoader.applicationContext.getResources().getIdentifier(arg3, "string", ApplicationLoader.applicationContext.getPackageName()), new Object[]{Integer.valueOf(arg4)});
            }
        }

        return "LOC_ERR:" + arg3;
    }

    public static String formatShortNumber(int arg9, int[] arg10) {
        int v3;
        StringBuilder v0 = new StringBuilder();
        int v2 = 0;
        while(true) {
            v3 = arg9 / 1000;
            if(v3 <= 0) {
                break;
            }

            v0.append("K");
            v2 = arg9 % 1000 / 100;
            arg9 = v3;
        }

        if(arg10 != null) {
            double v3_1 = ((double)arg9);
            double v5 = ((double)v2);
            Double.isNaN(v5);
            Double.isNaN(v3_1);
            double v4 = v3_1 + v5 / 10;
            for(v3 = 0; v3 < v0.length(); ++v3) {
                v4 *= 1000;
            }

            arg10[0] = ((int)v4);
        }

        v3 = 2;
        if(v2 != 0 && v0.length() > 0) {
            if(v0.length() == v3) {
                Locale v0_1 = Locale.US;
                return String.format(v0_1, "%d.%dM", Integer.valueOf(arg9), Integer.valueOf(v2));
            }
            else {
                Locale v4_1 = Locale.US;
                Object[] v6 = new Object[3];
                v6[0] = Integer.valueOf(arg9);
                v6[1] = Integer.valueOf(v2);
                v6[v3] = v0.toString();
                return String.format(v4_1, "%d.%d%s", v6);
            }
        }

        if(v0.length() == v3) {
            return String.format(Locale.US, "%dM", Integer.valueOf(arg9));
        }

        Locale v2_1 = Locale.US;
        return String.format(v2_1, "%d%s", Integer.valueOf(arg9), v0.toString());
    }

    public static String formatString(String arg1, int arg2, Object[] arg3) {
        try {
            Object v0 = LocaleController.getInstance().localeValues.get(arg1);
            if(v0 == null) {
                String v0_1 = ApplicationLoader.applicationContext.getString(arg2);
            }

            if(LocaleController.getInstance().currentLocale != null) {
                return String.format(LocaleController.getInstance().currentLocale, ((String)v0), arg3);
            }

            return String.format(((String)v0), arg3);
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
            return "LOC_ERR: " + arg1;
        }
    }

    public static String formatStringSimple(String arg1, Object[] arg2) {
        try {
            if(LocaleController.getInstance().currentLocale != null) {
                return String.format(LocaleController.getInstance().currentLocale, arg1, arg2);
            }

            return String.format(arg1, arg2);
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
            return "LOC_ERR: " + arg1;
        }
    }

    public static String formatTTLString(int arg5) {
        int v0 = 60;
        if(arg5 < v0) {
            return LocaleController.formatPluralString("Seconds", arg5);
        }

        if(arg5 < 3600) {
            return LocaleController.formatPluralString("Minutes", arg5 / v0);
        }

        if(arg5 < 86400) {
            return LocaleController.formatPluralString("Hours", arg5 / v0 / v0);
        }

        if(arg5 < 604800) {
            return LocaleController.formatPluralString("Days", arg5 / v0 / v0 / 24);
        }

        int v1 = arg5 / 60 / v0 / 24;
        if(arg5 % 7 == 0) {
            return LocaleController.formatPluralString("Weeks", v1 / 7);
        }

        return String.format("%s %s", LocaleController.formatPluralString("Weeks", v1 / 7), LocaleController.formatPluralString("Days", v1 % 7));
    }

    public static String formatUserStatus(int arg6, User arg7) {
        int v0 = -102;
        int v1 = -101;
        int v2 = -100;
        if(arg7 != null && arg7.status != null && arg7.status.expires == 0) {
            if((arg7.status instanceof TL_userStatusRecently)) {
                arg7.status.expires = v2;
            }
            else if((arg7.status instanceof TL_userStatusLastWeek)) {
                arg7.status.expires = v1;
            }
            else if((arg7.status instanceof TL_userStatusLastMonth)) {
                arg7.status.expires = v0;
            }
        }

        int v3 = 2131625428;
        if(arg7 != null && arg7.status != null && arg7.status.expires <= 0 && (MessagesController.getInstance(arg6).onlinePrivacy.containsKey(Integer.valueOf(arg7.id)))) {
            return LocaleController.getString("Online", v3);
        }

        if(arg7 != null && arg7.status != null && arg7.status.expires != 0 && !UserObject.isDeleted(arg7)) {
            if((arg7 instanceof TL_userEmpty)) {
            }
            else if(arg7.status.expires > ConnectionsManager.getInstance(arg6).getCurrentTime()) {
                return LocaleController.getString("Online", v3);
            }
            else if(arg7.status.expires == -1) {
                return LocaleController.getString("Invisible", 2131624995);
            }
            else if(arg7.status.expires == v2) {
                return LocaleController.getString("Lately", 2131625075);
            }
            else if(arg7.status.expires == v1) {
                return LocaleController.getString("WithinAWeek", 2131626427);
            }
            else if(arg7.status.expires == v0) {
                return LocaleController.getString("WithinAMonth", 2131626426);
            }
            else {
                return LocaleController.formatDateOnline(((long)arg7.status.expires));
            }
        }

        return LocaleController.getString("ALongTimeAgo", 2131623936);
    }

    public static String getCurrentLanguageName() {
        return LocaleController.getString("LanguageName", 2131625045);
    }

    public LocaleInfo getCurrentLocaleInfo() {
        return this.currentLocaleInfo;
    }

    public static LocaleController getInstance() {
        LocaleController v0 = LocaleController.Instance;
        if(v0 == null) {
            Class v1 = LocaleController.class;
            __monitor_enter(v1);
            try {
                v0 = LocaleController.Instance;
                if(v0 == null) {
                    v0 = new LocaleController();
                    LocaleController.Instance = v0;
                }

                __monitor_exit(v1);
                return v0;
            label_12:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_12;
            }

            throw v0_1;
        }

        return v0;
    }

    private LocaleInfo getLanguageFromDict(String arg4) {
        if(arg4 == null) {
            return null;
        }

        return this.languagesDict.get(arg4.toLowerCase().replace("-", "_"));
    }

    public static String getLocaleAlias(String arg3) {
        String v0 = null;
        if(arg3 == null) {
            return v0;
        }

        int v1 = -1;
        switch(arg3.hashCode()) {
            case 3325: {
                if(!arg3.equals("he")) {
                    goto label_66;
                }

                v1 = 7;
                break;
            }
            case 3355: {
                if(!arg3.equals("id")) {
                    goto label_66;
                }

                v1 = 6;
                break;
            }
            case 3365: {
                if(!arg3.equals("in")) {
                    goto label_66;
                }

                v1 = 0;
                break;
            }
            case 3374: {
                if(!arg3.equals("iw")) {
                    goto label_66;
                }

                v1 = 1;
                break;
            }
            case 3391: {
                if(!arg3.equals("ji")) {
                    goto label_66;
                }

                v1 = 5;
                break;
            }
            case 3404: {
                if(!arg3.equals("jv")) {
                    goto label_66;
                }

                v1 = 8;
                break;
            }
            case 3405: {
                if(!arg3.equals("jw")) {
                    goto label_66;
                }

                v1 = 2;
                break;
            }
            case 3508: {
                if(!arg3.equals("nb")) {
                    goto label_66;
                }

                v1 = 9;
                break;
            }
            case 3521: {
                if(!arg3.equals("no")) {
                    goto label_66;
                }

                v1 = 3;
                break;
            }
            case 3704: {
                if(!arg3.equals("tl")) {
                    goto label_66;
                }

                v1 = 4;
                break;
            }
            case 3856: {
                if(!arg3.equals("yi")) {
                    goto label_66;
                }

                v1 = 11;
                break;
            }
            case 101385: {
                if(!arg3.equals("fil")) {
                    goto label_66;
                }

                v1 = 10;
                break;
            }
            default: {
                break;
            }
        }

    label_66:
        switch(v1) {
            case 0: {
                return "id";
            }
            case 1: {
                return "he";
            }
            case 2: {
                return "jv";
            }
            case 3: {
                return "nb";
            }
            case 4: {
                return "fil";
            }
            case 5: {
                return "yi";
            }
            case 6: {
                return "in";
            }
            case 7: {
                return "iw";
            }
            case 8: {
                return "jw";
            }
            case 9: {
                return "no";
            }
            case 10: {
                return "tl";
            }
            case 11: {
                return "ji";
            }
        }

        return v0;
    }

    private HashMap getLocaleFileStrings(File arg2) {
        return this.getLocaleFileStrings(arg2, false);
    }

    private HashMap getLocaleFileStrings(File arg11, boolean arg12) {
        FileInputStream v2_1;
        String v11_3;
        FileInputStream v5;
        XmlPullParser v4;
        HashMap v3;
        this.reloadLastFile = false;
        String v2 = null;
        try {
            if(!arg11.exists()) {
                return new HashMap();
            }

            v3 = new HashMap();
            v4 = Xml.newPullParser();
            v5 = new FileInputStream(arg11);
            goto label_14;
        }
        catch(Throwable v11) {
        }
        catch(Exception v11_1) {
            goto label_101;
            try {
            label_14:
                v4.setInput(((InputStream)v5), "UTF-8");
                int v11_2 = v4.getEventType();
                String v6 = v2;
                String v7 = v6;
                String v8 = v7;
                while(v11_2 != 1) {
                    if(v11_2 == 2) {
                        v11_3 = v4.getName();
                        if(v4.getAttributeCount() > 0) {
                            v6 = v4.getAttributeValue(0);
                        }

                        v7 = v11_3;
                    }
                    else {
                        if(v11_2 == 4) {
                            if(v6 == null) {
                                goto label_71;
                            }

                            v11_3 = v4.getText();
                            if(v11_3 != null) {
                                v11_3 = v11_3.trim();
                                if(arg12) {
                                    v11_3 = v11_3.replace("<", "&lt;").replace(">", "&gt;").replace("\'", "\\\'").replace("& ", "&amp; ");
                                }
                                else {
                                    v11_3 = v11_3.replace("\\n", "\n").replace("\\", "");
                                    v8 = v11_3.replace("&lt;", "<");
                                    if(this.reloadLastFile) {
                                    }
                                    else if(!v8.equals(v11_3)) {
                                        this.reloadLastFile = true;
                                    }
                                    else {
                                    }

                                    goto label_71;
                                }
                            }

                            v8 = v11_3;
                            goto label_71;
                        }

                        if(v11_2 != 3) {
                            goto label_71;
                        }

                        v6 = v2;
                        v7 = v6;
                        v8 = v7;
                    }

                label_71:
                    if(v7 != null && (v7.equals("string")) && v8 != null && v6 != null && v8.length() != 0 && v6.length() != 0) {
                        v3.put(v6, v8);
                        v6 = v2;
                        v7 = v6;
                        v8 = v7;
                    }

                    v11_2 = v4.next();
                }
            }
            catch(Throwable v11) {
                goto label_111;
            }
            catch(Exception v11_1) {
                goto label_95;
            }

            try {
                v5.close();
            }
            catch(Exception v11_1) {
                FileLog.e(((Throwable)v11_1));
            }

            return v3;
        label_95:
            v2_1 = v5;
            try {
            label_101:
                FileLog.e(((Throwable)v11_1));
                this.reloadLastFile = true;
                if(v2_1 == null) {
                    goto label_108;
                }
            }
            catch(Throwable v11) {
                v5 = v2_1;
                goto label_111;
            }
        }

        try {
            v2_1.close();
        }
        catch(Exception v11_1) {
            FileLog.e(((Throwable)v11_1));
        }

    label_108:
        return new HashMap();
    label_111:
        if(v5 != null) {
            try {
                v5.close();
            }
            catch(Exception v12) {
                FileLog.e(((Throwable)v12));
            }
        }

        throw v11;
    }

    private String getLocaleString(Locale arg5) {
        if(arg5 == null) {
            return "en";
        }

        String v0 = arg5.getLanguage();
        String v1 = arg5.getCountry();
        String v5 = arg5.getVariant();
        if(v0.length() == 0 && v1.length() == 0) {
            return "en";
        }

        StringBuilder v2 = new StringBuilder(11);
        v2.append(v0);
        char v3 = '_';
        if(v1.length() > 0 || v5.length() > 0) {
            v2.append(v3);
        }

        v2.append(v1);
        if(v5.length() > 0) {
            v2.append(v3);
        }

        v2.append(v5);
        return v2.toString();
    }

    public static String getLocaleStringIso639() {
        Locale v0 = LocaleController.getInstance().currentLocale;
        if(v0 == null) {
            return "en";
        }

        String v1 = v0.getLanguage();
        String v2 = v0.getCountry();
        String v0_1 = v0.getVariant();
        if(v1.length() == 0 && v2.length() == 0) {
            return "en";
        }

        StringBuilder v3 = new StringBuilder(11);
        v3.append(v1);
        if(v2.length() > 0 || v0_1.length() > 0) {
            v3.append('-');
        }

        v3.append(v2);
        if(v0_1.length() > 0) {
            v3.append('_');
        }

        v3.append(v0_1);
        return v3.toString();
    }

    public static String getPluralString(String arg2, int arg3) {
        if(arg2 != null && arg2.length() != 0) {
            if(LocaleController.getInstance().currentPluralRules == null) {
            }
            else {
                String v3 = LocaleController.getInstance().stringForQuantity(LocaleController.getInstance().currentPluralRules.quantityForNumber(arg3));
                arg2 = arg2 + "_" + v3;
                return LocaleController.getString(arg2, ApplicationLoader.applicationContext.getResources().getIdentifier(arg2, "string", ApplicationLoader.applicationContext.getPackageName()));
            }
        }

        return "LOC_ERR:" + arg2;
    }

    public static String getServerString(String arg4) {
        Object v0 = LocaleController.getInstance().localeValues.get(arg4);
        if(v0 == null) {
            int v4 = ApplicationLoader.applicationContext.getResources().getIdentifier(arg4, "string", ApplicationLoader.applicationContext.getPackageName());
            if(v4 != 0) {
                String v0_1 = ApplicationLoader.applicationContext.getString(v4);
            }
        }

        return ((String)v0);
    }

    public static String getString(String arg1, int arg2) {
        return LocaleController.getInstance().getStringInternal(arg1, arg2);
    }

    private String getStringInternal(String arg3, int arg4) {
        String v4_1;
        Object v0 = this.localeValues.get(arg3);
        if(v0 == null) {
            try {
                v4_1 = ApplicationLoader.applicationContext.getString(arg4);
                goto label_9;
            }
            catch(Exception v4) {
                FileLog.e(((Throwable)v4));
            }
        }

        Object v4_2 = v0;
    label_9:
        if((((String)v4_2)) == null) {
            v4_1 = "LOC_ERR:" + arg3;
        }

        return ((String)v4_2);
    }

    public Locale getSystemDefaultLocale() {
        return this.systemDefaultLocale;
    }

    public static String getSystemLocaleStringIso639() {
        Locale v0 = LocaleController.getInstance().getSystemDefaultLocale();
        if(v0 == null) {
            return "en";
        }

        String v1 = v0.getLanguage();
        String v2 = v0.getCountry();
        String v0_1 = v0.getVariant();
        if(v1.length() == 0 && v2.length() == 0) {
            return "en";
        }

        StringBuilder v3 = new StringBuilder(11);
        v3.append(v1);
        if(v2.length() > 0 || v0_1.length() > 0) {
            v3.append('-');
        }

        v3.append(v2);
        if(v0_1.length() > 0) {
            v3.append('_');
        }

        v3.append(v0_1);
        return v3.toString();
    }

    public String getTranslitString(String arg2) {
        return this.getTranslitString(arg2, false);
    }

    public String getTranslitString(String arg12, boolean arg13) {
        String v8_1;
        String v0 = null;
        if(arg12 == null) {
            return v0;
        }

        if(this.translitChars == null) {
            this.translitChars = new HashMap(520);
            this.translitChars.put("ȼ", "c");
            this.translitChars.put("ᶇ", "n");
            this.translitChars.put("ɖ", "d");
            this.translitChars.put("ỿ", "y");
            this.translitChars.put("ᴓ", "o");
            this.translitChars.put("ø", "o");
            this.translitChars.put("ḁ", "a");
            this.translitChars.put("ʯ", "h");
            this.translitChars.put("ŷ", "y");
            this.translitChars.put("ʞ", "k");
            this.translitChars.put("ừ", "u");
            this.translitChars.put("ꜳ", "aa");
            this.translitChars.put("ĳ", "ij");
            this.translitChars.put("ḽ", "l");
            this.translitChars.put("ɪ", "i");
            this.translitChars.put("ḇ", "b");
            this.translitChars.put("ʀ", "r");
            this.translitChars.put("ě", "e");
            this.translitChars.put("ﬃ", "ffi");
            this.translitChars.put("ơ", "o");
            this.translitChars.put("ⱹ", "r");
            this.translitChars.put("ồ", "o");
            this.translitChars.put("ǐ", "i");
            this.translitChars.put("ꝕ", "p");
            this.translitChars.put("ý", "y");
            this.translitChars.put("ḝ", "e");
            this.translitChars.put("ₒ", "o");
            this.translitChars.put("ⱥ", "a");
            this.translitChars.put("ʙ", "b");
            this.translitChars.put("ḛ", "e");
            this.translitChars.put("ƈ", "c");
            this.translitChars.put("ɦ", "h");
            this.translitChars.put("ᵬ", "b");
            this.translitChars.put("ṣ", "s");
            this.translitChars.put("đ", "d");
            this.translitChars.put("ỗ", "o");
            this.translitChars.put("ɟ", "j");
            this.translitChars.put("ẚ", "a");
            this.translitChars.put("ɏ", "y");
            this.translitChars.put("л", "l");
            this.translitChars.put("ʌ", "v");
            this.translitChars.put("ꝓ", "p");
            this.translitChars.put("ﬁ", "fi");
            this.translitChars.put("ᶄ", "k");
            this.translitChars.put("ḏ", "d");
            this.translitChars.put("ᴌ", "l");
            this.translitChars.put("ė", "e");
            this.translitChars.put("ё", "yo");
            this.translitChars.put("ᴋ", "k");
            this.translitChars.put("ċ", "c");
            this.translitChars.put("ʁ", "r");
            this.translitChars.put("ƕ", "hv");
            this.translitChars.put("ƀ", "b");
            this.translitChars.put("ṍ", "o");
            this.translitChars.put("ȣ", "ou");
            this.translitChars.put("ǰ", "j");
            this.translitChars.put("ᶃ", "g");
            this.translitChars.put("ṋ", "n");
            this.translitChars.put("ɉ", "j");
            this.translitChars.put("ǧ", "g");
            this.translitChars.put("ǳ", "dz");
            this.translitChars.put("ź", "z");
            this.translitChars.put("ꜷ", "au");
            this.translitChars.put("ǖ", "u");
            this.translitChars.put("ᵹ", "g");
            this.translitChars.put("ȯ", "o");
            this.translitChars.put("ɐ", "a");
            this.translitChars.put("ą", "a");
            this.translitChars.put("õ", "o");
            this.translitChars.put("ɻ", "r");
            this.translitChars.put("ꝍ", "o");
            this.translitChars.put("ǟ", "a");
            this.translitChars.put("ȴ", "l");
            this.translitChars.put("ʂ", "s");
            this.translitChars.put("ﬂ", "fl");
            this.translitChars.put("ȉ", "i");
            this.translitChars.put("ⱻ", "e");
            this.translitChars.put("ṉ", "n");
            this.translitChars.put("ï", "i");
            this.translitChars.put("ñ", "n");
            this.translitChars.put("ᴉ", "i");
            this.translitChars.put("ʇ", "t");
            this.translitChars.put("ẓ", "z");
            this.translitChars.put("ỷ", "y");
            this.translitChars.put("ȳ", "y");
            this.translitChars.put("ṩ", "s");
            this.translitChars.put("ɽ", "r");
            this.translitChars.put("ĝ", "g");
            this.translitChars.put("в", "v");
            this.translitChars.put("ᴝ", "u");
            this.translitChars.put("ḳ", "k");
            this.translitChars.put("ꝫ", "et");
            this.translitChars.put("ī", "i");
            this.translitChars.put("ť", "t");
            this.translitChars.put("ꜿ", "c");
            this.translitChars.put("ʟ", "l");
            this.translitChars.put("ꜹ", "av");
            this.translitChars.put("û", "u");
            this.translitChars.put("æ", "ae");
            this.translitChars.put("и", "i");
            this.translitChars.put("ă", "a");
            this.translitChars.put("ǘ", "u");
            this.translitChars.put("ꞅ", "s");
            this.translitChars.put("ᵣ", "r");
            this.translitChars.put("ᴀ", "a");
            this.translitChars.put("ƃ", "b");
            this.translitChars.put("ḩ", "h");
            this.translitChars.put("ṧ", "s");
            this.translitChars.put("ₑ", "e");
            this.translitChars.put("ʜ", "h");
            this.translitChars.put("ẋ", "x");
            this.translitChars.put("ꝅ", "k");
            this.translitChars.put("ḋ", "d");
            this.translitChars.put("ƣ", "oi");
            this.translitChars.put("ꝑ", "p");
            this.translitChars.put("ħ", "h");
            this.translitChars.put("ⱴ", "v");
            this.translitChars.put("ẇ", "w");
            this.translitChars.put("ǹ", "n");
            this.translitChars.put("ɯ", "m");
            this.translitChars.put("ɡ", "g");
            this.translitChars.put("ɴ", "n");
            this.translitChars.put("ᴘ", "p");
            this.translitChars.put("ᵥ", "v");
            this.translitChars.put("ū", "u");
            this.translitChars.put("ḃ", "b");
            this.translitChars.put("ṗ", "p");
            this.translitChars.put("ь", "");
            this.translitChars.put("å", "a");
            this.translitChars.put("ɕ", "c");
            this.translitChars.put("ọ", "o");
            this.translitChars.put("ắ", "a");
            this.translitChars.put("ƒ", "f");
            this.translitChars.put("ǣ", "ae");
            this.translitChars.put("ꝡ", "vy");
            this.translitChars.put("ﬀ", "ff");
            this.translitChars.put("ᶉ", "r");
            this.translitChars.put("ô", "o");
            this.translitChars.put("ǿ", "o");
            this.translitChars.put("ṳ", "u");
            this.translitChars.put("ȥ", "z");
            this.translitChars.put("ḟ", "f");
            this.translitChars.put("ḓ", "d");
            this.translitChars.put("ȇ", "e");
            this.translitChars.put("ȕ", "u");
            this.translitChars.put("п", "p");
            this.translitChars.put("ȵ", "n");
            this.translitChars.put("ʠ", "q");
            this.translitChars.put("ấ", "a");
            this.translitChars.put("ǩ", "k");
            this.translitChars.put("ĩ", "i");
            this.translitChars.put("ṵ", "u");
            this.translitChars.put("ŧ", "t");
            this.translitChars.put("ɾ", "r");
            this.translitChars.put("ƙ", "k");
            this.translitChars.put("ṫ", "t");
            this.translitChars.put("ꝗ", "q");
            this.translitChars.put("ậ", "a");
            this.translitChars.put("н", "n");
            this.translitChars.put("ʄ", "j");
            this.translitChars.put("ƚ", "l");
            this.translitChars.put("ᶂ", "f");
            this.translitChars.put("д", "d");
            this.translitChars.put("ᵴ", "s");
            this.translitChars.put("ꞃ", "r");
            this.translitChars.put("ᶌ", "v");
            this.translitChars.put("ɵ", "o");
            this.translitChars.put("ḉ", "c");
            this.translitChars.put("ᵤ", "u");
            this.translitChars.put("ẑ", "z");
            this.translitChars.put("ṹ", "u");
            this.translitChars.put("ň", "n");
            this.translitChars.put("ʍ", "w");
            this.translitChars.put("ầ", "a");
            this.translitChars.put("ǉ", "lj");
            this.translitChars.put("ɓ", "b");
            this.translitChars.put("ɼ", "r");
            this.translitChars.put("ò", "o");
            this.translitChars.put("ẘ", "w");
            this.translitChars.put("ɗ", "d");
            this.translitChars.put("ꜽ", "ay");
            this.translitChars.put("ư", "u");
            this.translitChars.put("ᶀ", "b");
            this.translitChars.put("ǜ", "u");
            this.translitChars.put("ẹ", "e");
            this.translitChars.put("ǡ", "a");
            this.translitChars.put("ɥ", "h");
            this.translitChars.put("ṏ", "o");
            this.translitChars.put("ǔ", "u");
            this.translitChars.put("ʎ", "y");
            this.translitChars.put("ȱ", "o");
            this.translitChars.put("ệ", "e");
            this.translitChars.put("ế", "e");
            this.translitChars.put("ĭ", "i");
            this.translitChars.put("ⱸ", "e");
            this.translitChars.put("ṯ", "t");
            this.translitChars.put("ᶑ", "d");
            this.translitChars.put("ḧ", "h");
            this.translitChars.put("ṥ", "s");
            this.translitChars.put("ë", "e");
            this.translitChars.put("ᴍ", "m");
            this.translitChars.put("ö", "o");
            this.translitChars.put("é", "e");
            this.translitChars.put("ı", "i");
            this.translitChars.put("ď", "d");
            this.translitChars.put("ᵯ", "m");
            this.translitChars.put("ỵ", "y");
            this.translitChars.put("я", "ya");
            this.translitChars.put("ŵ", "w");
            this.translitChars.put("ề", "e");
            this.translitChars.put("ứ", "u");
            this.translitChars.put("ƶ", "z");
            this.translitChars.put("ĵ", "j");
            this.translitChars.put("ḍ", "d");
            this.translitChars.put("ŭ", "u");
            this.translitChars.put("ʝ", "j");
            this.translitChars.put("ж", "zh");
            this.translitChars.put("ê", "e");
            this.translitChars.put("ǚ", "u");
            this.translitChars.put("ġ", "g");
            this.translitChars.put("ṙ", "r");
            this.translitChars.put("ƞ", "n");
            this.translitChars.put("ъ", "");
            this.translitChars.put("ḗ", "e");
            this.translitChars.put("ẝ", "s");
            this.translitChars.put("ᶁ", "d");
            this.translitChars.put("ķ", "k");
            this.translitChars.put("ᴂ", "ae");
            this.translitChars.put("ɘ", "e");
            this.translitChars.put("ợ", "o");
            this.translitChars.put("ḿ", "m");
            this.translitChars.put("ꜰ", "f");
            this.translitChars.put("а", "a");
            this.translitChars.put("ẵ", "a");
            this.translitChars.put("ꝏ", "oo");
            this.translitChars.put("ᶆ", "m");
            this.translitChars.put("ᵽ", "p");
            this.translitChars.put("ц", "ts");
            this.translitChars.put("ữ", "u");
            this.translitChars.put("ⱪ", "k");
            this.translitChars.put("ḥ", "h");
            this.translitChars.put("ţ", "t");
            this.translitChars.put("ᵱ", "p");
            this.translitChars.put("ṁ", "m");
            this.translitChars.put("á", "a");
            this.translitChars.put("ᴎ", "n");
            this.translitChars.put("ꝟ", "v");
            this.translitChars.put("è", "e");
            this.translitChars.put("ᶎ", "z");
            this.translitChars.put("ꝺ", "d");
            this.translitChars.put("ᶈ", "p");
            this.translitChars.put("м", "m");
            this.translitChars.put("ɫ", "l");
            this.translitChars.put("ᴢ", "z");
            this.translitChars.put("ɱ", "m");
            this.translitChars.put("ṝ", "r");
            this.translitChars.put("ṽ", "v");
            this.translitChars.put("ũ", "u");
            this.translitChars.put("ß", "ss");
            this.translitChars.put("т", "t");
            this.translitChars.put("ĥ", "h");
            this.translitChars.put("ᵵ", "t");
            this.translitChars.put("ʐ", "z");
            this.translitChars.put("ṟ", "r");
            this.translitChars.put("ɲ", "n");
            this.translitChars.put("à", "a");
            this.translitChars.put("ẙ", "y");
            this.translitChars.put("ỳ", "y");
            this.translitChars.put("ᴔ", "oe");
            this.translitChars.put("ы", "i");
            this.translitChars.put("ₓ", "x");
            this.translitChars.put("ȗ", "u");
            this.translitChars.put("ⱼ", "j");
            this.translitChars.put("ẫ", "a");
            this.translitChars.put("ʑ", "z");
            this.translitChars.put("ẛ", "s");
            this.translitChars.put("ḭ", "i");
            this.translitChars.put("ꜵ", "ao");
            this.translitChars.put("ɀ", "z");
            this.translitChars.put("ÿ", "y");
            this.translitChars.put("ǝ", "e");
            this.translitChars.put("ǭ", "o");
            this.translitChars.put("ᴅ", "d");
            this.translitChars.put("ᶅ", "l");
            this.translitChars.put("ù", "u");
            this.translitChars.put("ạ", "a");
            this.translitChars.put("ḅ", "b");
            this.translitChars.put("ụ", "u");
            this.translitChars.put("к", "k");
            this.translitChars.put("ằ", "a");
            this.translitChars.put("ᴛ", "t");
            this.translitChars.put("ƴ", "y");
            this.translitChars.put("ⱦ", "t");
            this.translitChars.put("з", "z");
            this.translitChars.put("ⱡ", "l");
            this.translitChars.put("ȷ", "j");
            this.translitChars.put("ᵶ", "z");
            this.translitChars.put("ḫ", "h");
            this.translitChars.put("ⱳ", "w");
            this.translitChars.put("ḵ", "k");
            this.translitChars.put("ờ", "o");
            this.translitChars.put("î", "i");
            this.translitChars.put("ģ", "g");
            this.translitChars.put("ȅ", "e");
            this.translitChars.put("ȧ", "a");
            this.translitChars.put("ẳ", "a");
            this.translitChars.put("щ", "sch");
            this.translitChars.put("ɋ", "q");
            this.translitChars.put("ṭ", "t");
            this.translitChars.put("ꝸ", "um");
            this.translitChars.put("ᴄ", "c");
            this.translitChars.put("ẍ", "x");
            this.translitChars.put("ủ", "u");
            this.translitChars.put("ỉ", "i");
            this.translitChars.put("ᴚ", "r");
            this.translitChars.put("ś", "s");
            this.translitChars.put("ꝋ", "o");
            this.translitChars.put("ỹ", "y");
            this.translitChars.put("ṡ", "s");
            this.translitChars.put("ǌ", "nj");
            this.translitChars.put("ȁ", "a");
            this.translitChars.put("ẗ", "t");
            this.translitChars.put("ĺ", "l");
            this.translitChars.put("ž", "z");
            this.translitChars.put("ᵺ", "th");
            this.translitChars.put("ƌ", "d");
            this.translitChars.put("ș", "s");
            this.translitChars.put("š", "s");
            this.translitChars.put("ᶙ", "u");
            this.translitChars.put("ẽ", "e");
            this.translitChars.put("ẜ", "s");
            this.translitChars.put("ɇ", "e");
            this.translitChars.put("ṷ", "u");
            this.translitChars.put("ố", "o");
            this.translitChars.put("ȿ", "s");
            this.translitChars.put("ᴠ", "v");
            this.translitChars.put("ꝭ", "is");
            this.translitChars.put("ᴏ", "o");
            this.translitChars.put("ɛ", "e");
            this.translitChars.put("ǻ", "a");
            this.translitChars.put("ﬄ", "ffl");
            this.translitChars.put("ⱺ", "o");
            this.translitChars.put("ȋ", "i");
            this.translitChars.put("ᵫ", "ue");
            this.translitChars.put("ȡ", "d");
            this.translitChars.put("ⱬ", "z");
            this.translitChars.put("ẁ", "w");
            this.translitChars.put("ᶏ", "a");
            this.translitChars.put("ꞇ", "t");
            this.translitChars.put("ğ", "g");
            this.translitChars.put("ɳ", "n");
            this.translitChars.put("ʛ", "g");
            this.translitChars.put("ᴜ", "u");
            this.translitChars.put("ф", "f");
            this.translitChars.put("ẩ", "a");
            this.translitChars.put("ṅ", "n");
            this.translitChars.put("ɨ", "i");
            this.translitChars.put("ᴙ", "r");
            this.translitChars.put("ǎ", "a");
            this.translitChars.put("ſ", "s");
            this.translitChars.put("у", "u");
            this.translitChars.put("ȫ", "o");
            this.translitChars.put("ɿ", "r");
            this.translitChars.put("ƭ", "t");
            this.translitChars.put("ḯ", "i");
            this.translitChars.put("ǽ", "ae");
            this.translitChars.put("ⱱ", "v");
            this.translitChars.put("ɶ", "oe");
            this.translitChars.put("ṃ", "m");
            this.translitChars.put("ż", "z");
            this.translitChars.put("ĕ", "e");
            this.translitChars.put("ꜻ", "av");
            this.translitChars.put("ở", "o");
            this.translitChars.put("ễ", "e");
            this.translitChars.put("ɬ", "l");
            this.translitChars.put("ị", "i");
            this.translitChars.put("ᵭ", "d");
            this.translitChars.put("ﬆ", "st");
            this.translitChars.put("ḷ", "l");
            this.translitChars.put("ŕ", "r");
            this.translitChars.put("ᴕ", "ou");
            this.translitChars.put("ʈ", "t");
            this.translitChars.put("ā", "a");
            this.translitChars.put("э", "e");
            this.translitChars.put("ḙ", "e");
            this.translitChars.put("ᴑ", "o");
            this.translitChars.put("ç", "c");
            this.translitChars.put("ᶊ", "s");
            this.translitChars.put("ặ", "a");
            this.translitChars.put("ų", "u");
            this.translitChars.put("ả", "a");
            this.translitChars.put("ǥ", "g");
            this.translitChars.put("р", "r");
            this.translitChars.put("ꝁ", "k");
            this.translitChars.put("ẕ", "z");
            this.translitChars.put("ŝ", "s");
            this.translitChars.put("ḕ", "e");
            this.translitChars.put("ɠ", "g");
            this.translitChars.put("ꝉ", "l");
            this.translitChars.put("ꝼ", "f");
            this.translitChars.put("ᶍ", "x");
            this.translitChars.put("х", "h");
            this.translitChars.put("ǒ", "o");
            this.translitChars.put("ę", "e");
            this.translitChars.put("ổ", "o");
            this.translitChars.put("ƫ", "t");
            this.translitChars.put("ǫ", "o");
            this.translitChars.put("i̇", "i");
            this.translitChars.put("ṇ", "n");
            this.translitChars.put("ć", "c");
            this.translitChars.put("ᵷ", "g");
            this.translitChars.put("ẅ", "w");
            this.translitChars.put("ḑ", "d");
            this.translitChars.put("ḹ", "l");
            this.translitChars.put("ч", "ch");
            this.translitChars.put("œ", "oe");
            this.translitChars.put("ᵳ", "r");
            this.translitChars.put("ļ", "l");
            this.translitChars.put("ȑ", "r");
            this.translitChars.put("ȭ", "o");
            this.translitChars.put("ᵰ", "n");
            this.translitChars.put("ᴁ", "ae");
            this.translitChars.put("ŀ", "l");
            this.translitChars.put("ä", "a");
            this.translitChars.put("ƥ", "p");
            this.translitChars.put("ỏ", "o");
            this.translitChars.put("į", "i");
            this.translitChars.put("ȓ", "r");
            this.translitChars.put("ǆ", "dz");
            this.translitChars.put("ḡ", "g");
            this.translitChars.put("ṻ", "u");
            this.translitChars.put("ō", "o");
            this.translitChars.put("ľ", "l");
            this.translitChars.put("ẃ", "w");
            this.translitChars.put("ț", "t");
            this.translitChars.put("ń", "n");
            this.translitChars.put("ɍ", "r");
            this.translitChars.put("ȃ", "a");
            this.translitChars.put("ü", "u");
            this.translitChars.put("ꞁ", "l");
            this.translitChars.put("ᴐ", "o");
            this.translitChars.put("ớ", "o");
            this.translitChars.put("ᴃ", "b");
            this.translitChars.put("ɹ", "r");
            this.translitChars.put("ᵲ", "r");
            this.translitChars.put("ʏ", "y");
            this.translitChars.put("ᵮ", "f");
            this.translitChars.put("ⱨ", "h");
            this.translitChars.put("ŏ", "o");
            this.translitChars.put("ú", "u");
            this.translitChars.put("ṛ", "r");
            this.translitChars.put("ʮ", "h");
            this.translitChars.put("ó", "o");
            this.translitChars.put("ů", "u");
            this.translitChars.put("ỡ", "o");
            this.translitChars.put("ṕ", "p");
            this.translitChars.put("ᶖ", "i");
            this.translitChars.put("ự", "u");
            this.translitChars.put("ã", "a");
            this.translitChars.put("ᵢ", "i");
            this.translitChars.put("ṱ", "t");
            this.translitChars.put("ể", "e");
            this.translitChars.put("ử", "u");
            this.translitChars.put("í", "i");
            this.translitChars.put("ɔ", "o");
            this.translitChars.put("с", "s");
            this.translitChars.put("й", "i");
            this.translitChars.put("ɺ", "r");
            this.translitChars.put("ɢ", "g");
            this.translitChars.put("ř", "r");
            this.translitChars.put("ẖ", "h");
            this.translitChars.put("ű", "u");
            this.translitChars.put("ȍ", "o");
            this.translitChars.put("ш", "sh");
            this.translitChars.put("ḻ", "l");
            this.translitChars.put("ḣ", "h");
            this.translitChars.put("ȶ", "t");
            this.translitChars.put("ņ", "n");
            this.translitChars.put("ᶒ", "e");
            this.translitChars.put("ì", "i");
            this.translitChars.put("ẉ", "w");
            this.translitChars.put("б", "b");
            this.translitChars.put("ē", "e");
            this.translitChars.put("ᴇ", "e");
            this.translitChars.put("ł", "l");
            this.translitChars.put("ộ", "o");
            this.translitChars.put("ɭ", "l");
            this.translitChars.put("ẏ", "y");
            this.translitChars.put("ᴊ", "j");
            this.translitChars.put("ḱ", "k");
            this.translitChars.put("ṿ", "v");
            this.translitChars.put("ȩ", "e");
            this.translitChars.put("â", "a");
            this.translitChars.put("ş", "s");
            this.translitChars.put("ŗ", "r");
            this.translitChars.put("ʋ", "v");
            this.translitChars.put("ₐ", "a");
            this.translitChars.put("ↄ", "c");
            this.translitChars.put("ᶓ", "e");
            this.translitChars.put("ɰ", "m");
            this.translitChars.put("е", "e");
            this.translitChars.put("ᴡ", "w");
            this.translitChars.put("ȏ", "o");
            this.translitChars.put("č", "c");
            this.translitChars.put("ǵ", "g");
            this.translitChars.put("ĉ", "c");
            this.translitChars.put("ю", "yu");
            this.translitChars.put("ᶗ", "o");
            this.translitChars.put("ꝃ", "k");
            this.translitChars.put("ꝙ", "q");
            this.translitChars.put("г", "g");
            this.translitChars.put("ṑ", "o");
            this.translitChars.put("ꜱ", "s");
            this.translitChars.put("ṓ", "o");
            this.translitChars.put("ȟ", "h");
            this.translitChars.put("ő", "o");
            this.translitChars.put("ꜩ", "tz");
            this.translitChars.put("ẻ", "e");
            this.translitChars.put("о", "o");
        }

        StringBuilder v1 = new StringBuilder(arg12.length());
        int v2 = arg12.length();
        int v4 = 0;
        int v5 = 0;
        while(v4 < v2) {
            int v6 = v4 + 1;
            String v4_1 = arg12.substring(v4, v6);
            if(arg13) {
                String v5_1 = v4_1.toLowerCase();
                String v10 = v5_1;
                v5 = v4_1.equals(v5_1) ^ 1;
                v4_1 = v10;
            }

            Object v8 = this.translitChars.get(v4_1);
            if(v8 != null) {
                if((arg13) && v5 != 0) {
                    v8_1 = ((String)v8).length() > 1 ? ((String)v8).substring(0, 1).toUpperCase() + ((String)v8).substring(1) : ((String)v8).toUpperCase();
                }

                v1.append(v8_1);
            }
            else {
                if(arg13) {
                    int v7 = v4_1.charAt(0);
                    if((v7 < 97 || v7 > 122 || v7 < 48 || v7 > 57) && (v7 != 32 && v7 != 39 && v7 != 44 && v7 != 46 && v7 != 38 && v7 != 45 && v7 != 47)) {
                        return v0;
                    }

                    if(v5 == 0) {
                        goto label_2149;
                    }

                    v4_1 = v4_1.toUpperCase();
                }

            label_2149:
                v1.append(v4_1);
            }

            v4 = v6;
        }

        return v1.toString();
    }

    public boolean isCurrentLocalLocale() {
        return this.currentLocaleInfo.isLocal();
    }

    public static boolean isRTLCharacter(char arg3) {
        boolean v1 = true;
        if(Character.getDirectionality(arg3) != 1 && Character.getDirectionality(arg3) != 2 && Character.getDirectionality(arg3) != 16) {
            if(Character.getDirectionality(arg3) == 17) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    public static void lambda$applyLanguage$1(LocaleController arg1, LocaleInfo arg2, int arg3) {
        arg1.applyRemoteLanguage(arg2, true, arg3);
    }

    public static void lambda$applyLanguage$2(LocaleController arg0, int arg1) {
        arg0.reloadCurrentRemoteLocale(arg1);
    }

    public static void lambda$applyRemoteLanguage$7(LocaleController arg0, int arg1, TLObject arg2, TL_error arg3) {
        if(arg2 != null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$LocaleController$rxmz9t50NYf8Gm9zreDc51qb_Ls(arg0, arg2, arg1));
        }
    }

    public static void lambda$applyRemoteLanguage$9(LocaleController arg0, int arg1, TLObject arg2, TL_error arg3) {
        if(arg2 != null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$LocaleController$qOqU9NthvZa28tX7JkUzY9yE0a4(arg0, arg2, arg1));
        }
    }

    public static void lambda$loadRemoteLanguages$5(LocaleController arg0, int arg1, TLObject arg2, TL_error arg3) {
        if(arg2 != null) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$LocaleController$YBp71kboDDs9Wxsm6D1Urk3iX1U(arg0, arg2, arg1));
        }
    }

    public static void lambda$new$0(LocaleController arg1) {
        arg1.loadRemoteLanguages(UserConfig.selectedAccount);
    }

    public static void lambda$null$4(LocaleController arg7, TLObject arg8, int arg9) {
        LocaleInfo v2_2;
        arg7.loadingRemoteLanguages = false;
        HashMap v1 = new HashMap();
        arg7.remoteLanguages.clear();
        int v2;
        for(v2 = 0; v2 < ((Vector)arg8).objects.size(); ++v2) {
            Object v3 = ((Vector)arg8).objects.get(v2);
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("loaded lang " + ((TL_langPackLanguage)v3).name);
            }

            LocaleInfo v4_1 = new LocaleInfo();
            v4_1.nameEnglish = ((TL_langPackLanguage)v3).name;
            v4_1.name = ((TL_langPackLanguage)v3).native_name;
            v4_1.shortName = ((TL_langPackLanguage)v3).lang_code.replace('-', '_').toLowerCase();
            v4_1.pathToFile = "remote";
            LocaleInfo v3_1 = arg7.getLanguageFromDict(v4_1.getKey());
            if(v3_1 == null) {
                arg7.languages.add(v4_1);
                arg7.languagesDict.put(v4_1.getKey(), v4_1);
                v3_1 = v4_1;
            }
            else {
                v3_1.nameEnglish = v4_1.nameEnglish;
                v3_1.name = v4_1.name;
                v3_1.pathToFile = v4_1.pathToFile;
            }

            arg7.remoteLanguages.add(v3_1);
            v1.put(v3_1.getKey(), v3_1);
        }

        int v8;
        for(v8 = 0; v8 < arg7.languages.size(); ++v8) {
            Object v2_1 = arg7.languages.get(v8);
            if(!((LocaleInfo)v2_1).isBuiltIn()) {
                if(!((LocaleInfo)v2_1).isRemote()) {
                }
                else if(v1.get(((LocaleInfo)v2_1).getKey()) == null) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("remove lang " + ((LocaleInfo)v2_1).getKey());
                    }

                    arg7.languages.remove(v8);
                    arg7.languagesDict.remove(((LocaleInfo)v2_1).getKey());
                    --v8;
                    if(v2_1 != arg7.currentLocaleInfo) {
                        goto label_109;
                    }

                    if(arg7.systemDefaultLocale.getLanguage() != null) {
                        v2_2 = arg7.getLanguageFromDict(arg7.systemDefaultLocale.getLanguage());
                    }

                    if(v2_2 == null) {
                        v2_2 = arg7.getLanguageFromDict(arg7.getLocaleString(arg7.systemDefaultLocale));
                    }

                    if(v2_2 == null) {
                        v2_2 = arg7.getLanguageFromDict("en");
                    }

                    arg7.applyLanguage(v2_2, true, false, arg9);
                    NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reloadInterface, new Object[0]);
                }
            }

        label_109:
        }

        arg7.saveOtherLanguages();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.suggestedLangpack, new Object[0]);
        arg7.applyLanguage(arg7.currentLocaleInfo, true, false, arg9);
    }

    public static void lambda$null$6(LocaleController arg0, TLObject arg1, int arg2) {
        arg0.saveRemoteLocaleStrings(((TL_langPackDifference)arg1), arg2);
    }

    public static void lambda$null$8(LocaleController arg0, TLObject arg1, int arg2) {
        arg0.saveRemoteLocaleStrings(((TL_langPackDifference)arg1), arg2);
    }

    public static void lambda$saveRemoteLocaleStrings$3(LocaleController arg5, String arg6, TL_langPackDifference arg7, HashMap arg8) {
        LocaleInfo v6 = arg5.getLanguageFromDict(arg6);
        if(v6 != null) {
            v6.version = arg7.version;
        }

        arg5.saveOtherLanguages();
        if(arg5.currentLocaleInfo != null && (arg5.currentLocaleInfo.isLocal())) {
            return;
        }

        try {
            String[] v0 = v6.shortName.split("_");
            Locale v0_1 = v0.length == 1 ? new Locale(v6.shortName) : new Locale(v0[0], v0[1]);
            arg5.languageOverride = v6.shortName;
            SharedPreferences$Editor v1 = MessagesController.getGlobalMainSettings().edit();
            v1.putString("language", v6.getKey());
            v1.commit();
            arg5.localeValues = arg8;
            arg5.currentLocale = v0_1;
            arg5.currentLocaleInfo = v6;
            arg5.currentPluralRules = arg5.allRules.get(arg5.currentLocale.getLanguage());
            if(arg5.currentPluralRules == null) {
                arg5.currentPluralRules = arg5.allRules.get("en");
            }

            arg5.changingConfiguration = true;
            Locale.setDefault(arg5.currentLocale);
            Configuration v6_2 = new Configuration();
            v6_2.locale = arg5.currentLocale;
            ApplicationLoader.applicationContext.getResources().updateConfiguration(v6_2, ApplicationLoader.applicationContext.getResources().getDisplayMetrics());
            arg5.changingConfiguration = false;
        }
        catch(Exception v6_1) {
            FileLog.e(((Throwable)v6_1));
            arg5.changingConfiguration = false;
        }

        arg5.recreateFormatters();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reloadInterface, new Object[0]);
    }

    private void loadOtherLanguages() {
        int v2 = 0;
        SharedPreferences v0 = ApplicationLoader.applicationContext.getSharedPreferences("langconfig", 0);
        String v3 = null;
        String v1 = v0.getString("locales", v3);
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            String[] v1_1 = v1.split("&");
            int v4 = v1_1.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                LocaleInfo v6 = LocaleInfo.createWithString(v1_1[v5]);
                if(v6 != null) {
                    this.otherLanguages.add(v6);
                }
            }
        }

        String v0_1 = v0.getString("remote", v3);
        if(!TextUtils.isEmpty(((CharSequence)v0_1))) {
            String[] v0_2 = v0_1.split("&");
            int v1_2 = v0_2.length;
            while(v2 < v1_2) {
                LocaleInfo v3_1 = LocaleInfo.createWithString(v0_2[v2]);
                ((LocaleInfo)v3).shortName = ((LocaleInfo)v3).shortName.replace("-", "_");
                if(v3_1 != null) {
                    this.remoteLanguages.add(v3_1);
                }

                ++v2;
            }
        }
    }

    public void loadRemoteLanguages(int arg4) {
        if(this.loadingRemoteLanguages) {
            return;
        }

        this.loadingRemoteLanguages = true;
        ConnectionsManager.getInstance(arg4).sendRequest(new TL_langpack_getLanguages(), new -$$Lambda$LocaleController$wb1-rMc5tYwIL_0Eu4mT1HDm3pw(this, arg4), 8);
    }

    public void onDeviceConfigurationChange(Configuration arg3) {
        if(this.changingConfiguration) {
            return;
        }

        LocaleController.is24HourFormat = DateFormat.is24HourFormat(ApplicationLoader.applicationContext);
        this.systemDefaultLocale = arg3.locale;
        if(this.languageOverride != null) {
            LocaleInfo v3 = this.currentLocaleInfo;
            this.currentLocaleInfo = null;
            this.applyLanguage(v3, false, false, UserConfig.selectedAccount);
        }
        else {
            Locale v3_1 = arg3.locale;
            if(v3_1 != null) {
                String v0 = v3_1.getDisplayName();
                String v1 = this.currentLocale.getDisplayName();
                if(v0 != null && v1 != null && !v0.equals(v1)) {
                    this.recreateFormatters();
                }

                this.currentLocale = v3_1;
                this.currentPluralRules = this.allRules.get(this.currentLocale.getLanguage());
                if(this.currentPluralRules != null) {
                    return;
                }

                this.currentPluralRules = this.allRules.get("en");
            }
        }
    }

    public void recreateFormatters() {
        int v2_2;
        String v2_1;
        Locale v0 = this.currentLocale;
        if(v0 == null) {
            v0 = Locale.getDefault();
        }

        String v1 = v0.getLanguage();
        if(v1 == null) {
            v1 = "en";
        }

        v1 = v1.toLowerCase();
        int v3 = 1;
        boolean v2 = (v1.startsWith("ar")) || (v1.startsWith("fa")) || (v1.startsWith("he")) || (v1.startsWith("iw")) || (v1.startsWith("fa")) ? true : false;
        LocaleController.isRTL = v2;
        if(v1.equals("ko")) {
            v3 = 2;
        }

        LocaleController.nameDisplayOrder = v3;
        this.formatterMonth = this.createFormatter(v0, this.getStringInternal("formatterMonth", 2131626782), "dd MMM");
        this.formatterYear = this.createFormatter(v0, this.getStringInternal("formatterYear", 2131626787), "dd.MM.yy");
        this.formatterYearMax = this.createFormatter(v0, this.getStringInternal("formatterYearMax", 2131626788), "dd.MM.yyyy");
        this.chatDate = this.createFormatter(v0, this.getStringInternal("chatDate", 2131626701), "d MMMM");
        this.chatFullDate = this.createFormatter(v0, this.getStringInternal("chatFullDate", 2131626702), "d MMMM yyyy");
        this.formatterWeek = this.createFormatter(v0, this.getStringInternal("formatterWeek", 2131626786), "EEE");
        this.formatterMonthYear = this.createFormatter(v0, this.getStringInternal("formatterMonthYear", 2131626783), "MMMM yyyy");
        Locale v1_1 = (v1.toLowerCase().equals("ar")) || (v1.toLowerCase().equals("ko")) ? v0 : Locale.US;
        if(LocaleController.is24HourFormat) {
            v2_1 = "formatterDay24H";
            v3 = 2131626781;
        }
        else {
            v2_1 = "formatterDay12H";
            v3 = 2131626780;
        }

        v2_1 = this.getStringInternal(v2_1, v3);
        String v3_1 = LocaleController.is24HourFormat ? "HH:mm" : "h:mm a";
        this.formatterDay = this.createFormatter(v1_1, v2_1, v3_1);
        if(LocaleController.is24HourFormat) {
            v1 = "formatterStats24H";
            v2_2 = 2131626785;
        }
        else {
            v1 = "formatterStats12H";
            v2_2 = 2131626784;
        }

        v1 = this.getStringInternal(v1, v2_2);
        v2_1 = LocaleController.is24HourFormat ? "MMM dd yyyy, HH:mm" : "MMM dd yyyy, h:mm a";
        this.formatterStats = this.createFormatter(v0, v1, v2_1);
        if(LocaleController.is24HourFormat) {
            v1 = "formatterBannedUntil24H";
            v2_2 = 2131626777;
        }
        else {
            v1 = "formatterBannedUntil12H";
            v2_2 = 2131626776;
        }

        v1 = this.getStringInternal(v1, v2_2);
        v2_1 = LocaleController.is24HourFormat ? "MMM dd yyyy, HH:mm" : "MMM dd yyyy, h:mm a";
        this.formatterBannedUntil = this.createFormatter(v0, v1, v2_1);
        if(LocaleController.is24HourFormat) {
            v1 = "formatterBannedUntilThisYear24H";
            v2_2 = 2131626779;
        }
        else {
            v1 = "formatterBannedUntilThisYear12H";
            v2_2 = 2131626778;
        }

        v1 = this.getStringInternal(v1, v2_2);
        v2_1 = LocaleController.is24HourFormat ? "MMM dd, HH:mm" : "MMM dd, h:mm a";
        this.formatterBannedUntilThisYear = this.createFormatter(v0, v1, v2_1);
    }

    public void reloadCurrentRemoteLocale(int arg3) {
        this.applyRemoteLanguage(this.currentLocaleInfo, true, arg3);
    }

    private void saveOtherLanguages() {
        int v2 = 0;
        SharedPreferences$Editor v0 = ApplicationLoader.applicationContext.getSharedPreferences("langconfig", 0).edit();
        StringBuilder v1 = new StringBuilder();
        int v3;
        for(v3 = 0; v3 < this.otherLanguages.size(); ++v3) {
            String v4 = this.otherLanguages.get(v3).getSaveString();
            if(v4 != null) {
                if(v1.length() != 0) {
                    v1.append("&");
                }

                v1.append(v4);
            }
        }

        v0.putString("locales", v1.toString());
        v1.setLength(0);
        while(v2 < this.remoteLanguages.size()) {
            String v3_1 = this.remoteLanguages.get(v2).getSaveString();
            if(v3_1 != null) {
                if(v1.length() != 0) {
                    v1.append("&");
                }

                v1.append(v3_1);
            }

            ++v2;
        }

        v0.putString("remote", v1.toString());
        v0.commit();
    }

    public void saveRemoteLocaleStrings(TL_langPackDifference arg10, int arg11) {
        String v5_1;
        String v6;
        Object v5;
        if(arg10 != null && !arg10.strings.isEmpty()) {
            if(this.currentLocaleInfo == null) {
            }
            else {
                String v11 = arg10.lang_code.replace('-', '_').toLowerCase();
                if(!v11.equals(this.currentLocaleInfo.shortName)) {
                    return;
                }
                else {
                    File v1 = ApplicationLoader.getFilesDirFixed();
                    StringBuilder v2 = new StringBuilder();
                    v2.append("remote_");
                    v2.append(v11);
                    v2.append(".xml");
                    File v0 = new File(v1, v2.toString());
                    try {
                        HashMap v1_1 = arg10.from_version == 0 ? new HashMap() : this.getLocaleFileStrings(v0, true);
                        int v4;
                        for(v4 = 0; v4 < arg10.strings.size(); ++v4) {
                            v5 = arg10.strings.get(v4);
                            if((v5 instanceof TL_langPackString)) {
                                v6 = ((LangPackString)v5).key;
                                v5_1 = ((LangPackString)v5).value;
                                goto label_46;
                            }
                            else if((v5 instanceof TL_langPackStringPluralized)) {
                                v6 = ((LangPackString)v5).key + "_zero";
                                String v7 = ((LangPackString)v5).zero_value != null ? this.escapeString(((LangPackString)v5).zero_value) : "";
                                v1_1.put(v6, v7);
                                v6 = ((LangPackString)v5).key + "_one";
                                v7 = ((LangPackString)v5).one_value != null ? this.escapeString(((LangPackString)v5).one_value) : "";
                                v1_1.put(v6, v7);
                                v6 = ((LangPackString)v5).key + "_two";
                                v7 = ((LangPackString)v5).two_value != null ? this.escapeString(((LangPackString)v5).two_value) : "";
                                v1_1.put(v6, v7);
                                v6 = ((LangPackString)v5).key + "_few";
                                v7 = ((LangPackString)v5).few_value != null ? this.escapeString(((LangPackString)v5).few_value) : "";
                                v1_1.put(v6, v7);
                                v6 = ((LangPackString)v5).key + "_many";
                                v7 = ((LangPackString)v5).many_value != null ? this.escapeString(((LangPackString)v5).many_value) : "";
                                v1_1.put(v6, v7);
                                v6 = ((LangPackString)v5).key + "_other";
                                if(((LangPackString)v5).other_value != null) {
                                    v5_1 = ((LangPackString)v5).other_value;
                                label_46:
                                    v5_1 = this.escapeString(v5_1);
                                }
                                else {
                                    v5_1 = "";
                                }

                                v1_1.put(v6, v5_1);
                            }
                            else {
                                if(!(v5 instanceof TL_langPackStringDeleted)) {
                                    goto label_138;
                                }

                                v1_1.remove(((LangPackString)v5).key);
                            }

                        label_138:
                        }

                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("save locale file to " + v0);
                        }

                        BufferedWriter v4_2 = new BufferedWriter(new FileWriter(v0));
                        v4_2.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
                        v4_2.write("<resources>\n");
                        Iterator v1_2 = v1_1.entrySet().iterator();
                        while(v1_2.hasNext()) {
                            v5 = v1_2.next();
                            v4_2.write(String.format("<string name=\"%1$s\">%2$s</string>\n", ((Map$Entry)v5).getKey(), ((Map$Entry)v5).getValue()));
                        }

                        v4_2.write("</resources>");
                        v4_2.close();
                        AndroidUtilities.runOnUIThread(new -$$Lambda$LocaleController$6KItKgaUoWf5RCOZQrjCAS7rymM(this, v11, arg10, this.getLocaleFileStrings(v0)));
                        return;
                    }
                    catch(Exception ) {
                        return;
                    }
                }
            }
        }
    }

    public static String stringForMessageListDate(long arg7) {
        arg7 *= 1000;
        try {
            Calendar v0 = Calendar.getInstance();
            int v2 = v0.get(6);
            v0.setTimeInMillis(arg7);
            int v0_1 = v0.get(6);
            if(Math.abs(System.currentTimeMillis() - arg7) >= 31536000000L) {
                return LocaleController.getInstance().formatterYear.format(new Date(arg7));
            }

            v0_1 -= v2;
            if(v0_1 != 0) {
                int v1 = -1;
                if(v0_1 == v1 && System.currentTimeMillis() - arg7 < 28800000) {
                    goto label_60;
                }

                if(v0_1 > -7 && v0_1 <= v1) {
                    return LocaleController.getInstance().formatterWeek.format(new Date(arg7));
                }

                a v0_2 = new a(arg7);
                String v0_3 = v0_2.c() + " " + v0_2.d();
                if(LocaleController.getCurrentLanguageName().contentEquals("فارسی")) {
                    return v0_3;
                }

                return LocaleController.getInstance().formatterMonth.format(new Date(arg7));
            }

        label_60:
            return LocaleController.getInstance().formatterDay.format(new Date(arg7));
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
            return "LOC_ERR";
        }
    }

    private String stringForQuantity(int arg2) {
        if(arg2 == 4) {
            return "two";
        }

        if(arg2 == 8) {
            return "few";
        }

        if(arg2 == 16) {
            return "many";
        }

        switch(arg2) {
            case 1: {
                return "zero";
            }
            case 2: {
                return "one";
            }
        }

        return "other";
    }
}

