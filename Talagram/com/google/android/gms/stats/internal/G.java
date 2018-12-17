package com.google.android.gms.stats.internal;

import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.stats.netstats.NetstatsParserPatterns;
import java.util.concurrent.TimeUnit;

public final class G {
    public final class netStats {
        public final class patterns {
            public static final GservicesValue BUCKET;
            public static final GservicesValue HISTORY;
            public static final GservicesValue IDENT;
            public static final GservicesValue IDENTS;
            public static final GservicesValue TAG_RADIX;
            public static final GservicesValue TS_TO_MILLIS;
            public static final GservicesValue TYPE_BACKGROUND;
            public static final GservicesValue TYPE_BOTH;
            public static final GservicesValue TYPE_DEBUG_VPN_IN_PATTERN;
            public static final GservicesValue TYPE_DEBUG_VPN_OUT_PATTERN;
            public static final GservicesValue TYPE_FOREGROUND;
            public static final GservicesValue UID_STATS_START;
            public static final GservicesValue UID_TAG_STATS_START;

            static {
                patterns.IDENTS = GservicesValue.value("gms:stats:netstats:pattern:idents", " *ident=\\[(?<idents>.*)\\](?: uid=(?<uid>-?[0-9]+))?(?: set=(?<set>\\w+))?(?: tag=0x(?<tag>[0-9a-f]+))?.*");
                patterns.IDENT = GservicesValue.value("gms:stats:netstats:pattern:ident", NetstatsParserPatterns.IDENT_PATTERN);
                patterns.HISTORY = GservicesValue.value("gms:stats:netstats:pattern:history", ".*bucketDuration=(?<duration>[0-9]+).*");
                patterns.BUCKET = GservicesValue.value("gms:stats:netstats:pattern:bucket", NetstatsParserPatterns.BUCKET_PATTERN);
                patterns.UID_STATS_START = GservicesValue.value("gms:stats:netstats:pattern:uid_start", "UID stats:|Detailed UID stats:");
                patterns.UID_TAG_STATS_START = GservicesValue.value("gms:stats:netstats:pattern:uid_tag_start", "UID tag stats:");
                patterns.TYPE_BOTH = GservicesValue.value("gms:stats:netstats:pattern:type_both", "ALL");
                patterns.TYPE_BACKGROUND = GservicesValue.value("gms:stats:netstats:pattern:type_background", "DEFAULT");
                patterns.TYPE_FOREGROUND = GservicesValue.value("gms:stats:netstats:pattern:type_foreground", "FOREGROUND");
                patterns.TYPE_DEBUG_VPN_IN_PATTERN = GservicesValue.value("gms:stats:netstats:pattern:type_debug_vpn_in_pattern", "DBG_VPN_IN");
                patterns.TYPE_DEBUG_VPN_OUT_PATTERN = GservicesValue.value("gms:stats:netstats:pattern:type_debug_vpn_out_pattern", "DBG_VPN_OUT");
                patterns.TAG_RADIX = GservicesValue.value("gms:stats:netstats:pattern:tag_radix", Integer.valueOf(16));
                patterns.TS_TO_MILLIS = GservicesValue.value("gms:stats:netstats:pattern:ts_to_millis", Integer.valueOf(NetstatsParserPatterns.TS_TO_MILLIS));
            }

            private patterns() {
                super();
            }
        }

        public static final GservicesValue dataSourcePollIntervalMillis;
        public static final GservicesValue enabled;
        public static final GservicesValue recordIntervalSecs;

        static {
            netStats.enabled = GservicesValue.value("gms:stats:netstats:enabled", true);
            netStats.recordIntervalSecs = GservicesValue.value("gms:stats:netstats:record_interval_secs", Long.valueOf(TimeUnit.DAYS.toSeconds(1)));
            netStats.dataSourcePollIntervalMillis = GservicesValue.value("gms:stats:netstats:data_source_poll_millis", Long.valueOf(TimeUnit.MINUTES.toMillis(30)));
        }

        private netStats() {
            super();
        }
    }

    public G() {
        super();
    }
}

