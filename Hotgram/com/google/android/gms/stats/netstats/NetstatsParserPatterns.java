package com.google.android.gms.stats.netstats;

import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.stats.internal.G$netStats$patterns;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetstatsParserPatterns {
    public class NetstatsMatcher {
        private Matcher zzaff;
        private Map zzafg;

        public NetstatsMatcher(Matcher arg1, Map arg2) {
            super();
            this.zzaff = arg1;
            this.zzafg = arg2;
        }

        public boolean find() {
            return this.zzaff.find();
        }

        public String get(String arg4) {
            if(!this.zzafg.containsKey(arg4)) {
                String v1 = "Unknown group ";
                arg4 = String.valueOf(arg4);
                arg4 = arg4.length() != 0 ? v1.concat(arg4) : new String(v1);
                throw new IllegalArgumentException(arg4);
            }

            return this.zzaff.group(this.zzafg.get(arg4).intValue());
        }

        public boolean matches() {
            return this.zzaff.matches();
        }
    }

    public static final String BUCKET_PATTERN = null;
    public static final String HISTORY_PATTERN = ".*bucketDuration=(?<duration>[0-9]+).*";
    public static final String IDENTS_PATTERN = " *ident=\\[(?<idents>.*)\\](?: uid=(?<uid>-?[0-9]+))?(?: set=(?<set>\\w+))?(?: tag=0x(?<tag>[0-9a-f]+))?.*";
    public static final String IDENT_PATTERN = null;
    public static final String NEW_BUCKET_PATTERN = " *st=(?<start>[0-9]+)(?: rb=(?<rxBytes>[0-9]+))?(?: rp=(?<rxPackets>[0-9]+))?(?: tb=(?<txBytes>[0-9]+))?(?: tp=(?<txPackets>[0-9]+))?(?: op=(?<operations>[0-9]+))?.*";
    public static final String NEW_IDENT_PATTERN = "[\\[{](?:type=(?<type>-1|\\w+))[, ]*(?:subType=(?<subtype>[^,]+))?[, ]*(?:subscriberId=(?<subscriberId>[0-9]+)(?:...)?)?[, ]*(?<roaming>ROAMING)?[^\\]}]*[\\]}]";
    public static final int NEW_TS_TO_MILLIS = 1000;
    public static final String OLD_BUCKET_PATTERN = " *bucketStart=(?<start>[0-9]+)(?: activeTime=(?<active>[0-9]+))?(?: rxBytes=(?<rxBytes>[0-9]+))?(?: rxPackets=(?<rxPackets>[0-9]+))?(?: txBytes=(?<txBytes>[0-9]+))?(?: txPackets=(?<txPackets>[0-9]+))?(?: operations=(?<operations>[0-9]+))?.*";
    public static final String OLD_IDENT_PATTERN = "\\[(?:type=(?<type>-1|\\w+))[, ]*(?:subType=(?<subtype>[^,]+))?[, ]*(?:subscriberId=(?<subscriberId>[0-9]+)(?:...)?)?[, ]*(?<roaming>ROAMING)?[^]]*\\]";
    public static final int OLD_TS_TO_MILLIS = 1;
    public static final int TS_TO_MILLIS = 0;
    public static final String TYPE_BACKGROUND_PATTERN = "DEFAULT";
    public static final String TYPE_BOTH_PATTERN = "ALL";
    public static final String TYPE_DEBUG_VPN_IN_PATTERN = "DBG_VPN_IN";
    public static final String TYPE_DEBUG_VPN_OUT_PATTERN = "DBG_VPN_OUT";
    public static final String TYPE_FOREGROUND_PATTERN = "FOREGROUND";
    public static final String UID_STATS_START_PATTERN = "UID stats:|Detailed UID stats:";
    public static final String UID_TAG_STATS_START_PATTERN = "UID tag stats:";
    private Pattern zzaen;
    private Map zzaeo;
    private Pattern zzaep;
    private Map zzaeq;
    private Pattern zzaer;
    private Map zzaes;
    private Pattern zzaet;
    private Map zzaeu;
    private Pattern zzaev;
    private Pattern zzaew;
    private Pattern zzaex;
    private Pattern zzaey;
    private Pattern zzaez;
    private Pattern zzafa;
    private Pattern zzafb;
    private int zzafc;
    private int zzafd;
    private static final Pattern zzafe;

    static {
        String v0 = PlatformVersion.isAtLeastLollipopMR1() ? "[\\[{](?:type=(?<type>-1|\\w+))[, ]*(?:subType=(?<subtype>[^,]+))?[, ]*(?:subscriberId=(?<subscriberId>[0-9]+)(?:...)?)?[, ]*(?<roaming>ROAMING)?[^\\]}]*[\\]}]" : "\\[(?:type=(?<type>-1|\\w+))[, ]*(?:subType=(?<subtype>[^,]+))?[, ]*(?:subscriberId=(?<subscriberId>[0-9]+)(?:...)?)?[, ]*(?<roaming>ROAMING)?[^]]*\\]";
        NetstatsParserPatterns.IDENT_PATTERN = v0;
        v0 = PlatformVersion.isAtLeastLollipopMR1() ? " *st=(?<start>[0-9]+)(?: rb=(?<rxBytes>[0-9]+))?(?: rp=(?<rxPackets>[0-9]+))?(?: tb=(?<txBytes>[0-9]+))?(?: tp=(?<txPackets>[0-9]+))?(?: op=(?<operations>[0-9]+))?.*" : " *bucketStart=(?<start>[0-9]+)(?: activeTime=(?<active>[0-9]+))?(?: rxBytes=(?<rxBytes>[0-9]+))?(?: rxPackets=(?<rxPackets>[0-9]+))?(?: txBytes=(?<txBytes>[0-9]+))?(?: txPackets=(?<txPackets>[0-9]+))?(?: operations=(?<operations>[0-9]+))?.*";
        NetstatsParserPatterns.BUCKET_PATTERN = v0;
        int v0_1 = PlatformVersion.isAtLeastLollipopMR1() ? 1000 : 1;
        NetstatsParserPatterns.TS_TO_MILLIS = v0_1;
        NetstatsParserPatterns.zzafe = Pattern.compile("\\?<([a-zA-Z0-9]+)>");
    }

    public NetstatsParserPatterns() {
        super();
        Object v0 = patterns.IDENTS.getBinderSafe();
        this.zzaen = NetstatsParserPatterns.zzp(((String)v0));
        this.zzaeo = NetstatsParserPatterns.zzo(((String)v0));
        v0 = patterns.IDENT.getBinderSafe();
        this.zzaep = NetstatsParserPatterns.zzp(((String)v0));
        this.zzaeq = NetstatsParserPatterns.zzo(((String)v0));
        v0 = patterns.HISTORY.getBinderSafe();
        this.zzaer = NetstatsParserPatterns.zzp(((String)v0));
        this.zzaes = NetstatsParserPatterns.zzo(((String)v0));
        v0 = patterns.BUCKET.getBinderSafe();
        this.zzaet = NetstatsParserPatterns.zzp(((String)v0));
        this.zzaeu = NetstatsParserPatterns.zzo(((String)v0));
        this.zzaev = Pattern.compile(patterns.UID_STATS_START.getBinderSafe());
        this.zzaew = Pattern.compile(patterns.UID_TAG_STATS_START.getBinderSafe());
        this.zzaex = Pattern.compile(patterns.TYPE_BOTH.getBinderSafe());
        this.zzaey = Pattern.compile(patterns.TYPE_BACKGROUND.getBinderSafe());
        this.zzaez = Pattern.compile(patterns.TYPE_FOREGROUND.getBinderSafe());
        this.zzafa = Pattern.compile(patterns.TYPE_DEBUG_VPN_IN_PATTERN.getBinderSafe());
        this.zzafb = Pattern.compile(patterns.TYPE_DEBUG_VPN_OUT_PATTERN.getBinderSafe());
        this.zzafc = patterns.TAG_RADIX.getBinderSafe().intValue();
        this.zzafd = patterns.TS_TO_MILLIS.getBinderSafe().intValue();
    }

    public NetstatsMatcher bucket(String arg3) {
        return new NetstatsMatcher(this.zzaet.matcher(((CharSequence)arg3)), this.zzaeu);
    }

    public NetstatsMatcher history(String arg3) {
        return new NetstatsMatcher(this.zzaer.matcher(((CharSequence)arg3)), this.zzaes);
    }

    public NetstatsMatcher ident(String arg3) {
        return new NetstatsMatcher(this.zzaep.matcher(((CharSequence)arg3)), this.zzaeq);
    }

    public NetstatsMatcher idents(String arg3) {
        return new NetstatsMatcher(this.zzaen.matcher(((CharSequence)arg3)), this.zzaeo);
    }

    public boolean isTypeBackground(String arg2) {
        return this.zzaey.matcher(((CharSequence)arg2)).matches();
    }

    public boolean isTypeBoth(String arg2) {
        return this.zzaex.matcher(((CharSequence)arg2)).matches();
    }

    public boolean isTypeDebugVpn(String arg2) {
        if(!this.zzafa.matcher(((CharSequence)arg2)).matches()) {
            if(this.zzafb.matcher(((CharSequence)arg2)).matches()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public boolean isTypeForeground(String arg2) {
        return this.zzaez.matcher(((CharSequence)arg2)).matches();
    }

    public boolean isUidStart(String arg2) {
        return this.zzaev.matcher(((CharSequence)arg2)).matches();
    }

    public boolean isUidTagStart(String arg2) {
        return this.zzaew.matcher(((CharSequence)arg2)).matches();
    }

    public int tagRadix() {
        return this.zzafc;
    }

    public long toMillis(long arg3) {
        return arg3 * (((long)this.zzafd));
    }

    private static Map zzo(String arg5) {
        HashMap v0 = new HashMap();
        Matcher v5 = NetstatsParserPatterns.zzafe.matcher(((CharSequence)arg5));
        int v2;
        for(v2 = 1; v5.find(); ++v2) {
            ((Map)v0).put(v5.group(1), Integer.valueOf(v2));
        }

        return ((Map)v0);
    }

    private static Pattern zzp(String arg1) {
        return Pattern.compile(NetstatsParserPatterns.zzafe.matcher(((CharSequence)arg1)).replaceAll(""));
    }
}

