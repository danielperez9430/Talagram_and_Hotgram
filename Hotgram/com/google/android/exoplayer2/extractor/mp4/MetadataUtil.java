package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class MetadataUtil {
    private static final String LANGUAGE_UNDEFINED = "und";
    private static final int PICTURE_TYPE_FRONT_COVER = 3;
    private static final int SHORT_TYPE_ALBUM = 0;
    private static final int SHORT_TYPE_ARTIST = 0;
    private static final int SHORT_TYPE_COMMENT = 0;
    private static final int SHORT_TYPE_COMPOSER_1 = 0;
    private static final int SHORT_TYPE_COMPOSER_2 = 0;
    private static final int SHORT_TYPE_ENCODER = 0;
    private static final int SHORT_TYPE_GENRE = 0;
    private static final int SHORT_TYPE_LYRICS = 0;
    private static final int SHORT_TYPE_NAME_1 = 0;
    private static final int SHORT_TYPE_NAME_2 = 0;
    private static final int SHORT_TYPE_YEAR = 0;
    private static final String[] STANDARD_GENRES = null;
    private static final String TAG = "MetadataUtil";
    private static final int TYPE_ALBUM_ARTIST;
    private static final int TYPE_COMPILATION;
    private static final int TYPE_COVER_ART;
    private static final int TYPE_DISK_NUMBER;
    private static final int TYPE_GAPLESS_ALBUM;
    private static final int TYPE_GENRE;
    private static final int TYPE_GROUPING;
    private static final int TYPE_INTERNAL;
    private static final int TYPE_RATING;
    private static final int TYPE_SORT_ALBUM;
    private static final int TYPE_SORT_ALBUM_ARTIST;
    private static final int TYPE_SORT_ARTIST;
    private static final int TYPE_SORT_COMPOSER;
    private static final int TYPE_SORT_TRACK_NAME;
    private static final int TYPE_TEMPO;
    private static final int TYPE_TRACK_NUMBER;
    private static final int TYPE_TV_SHOW;
    private static final int TYPE_TV_SORT_SHOW;

    static {
        MetadataUtil.SHORT_TYPE_NAME_1 = Util.getIntegerCodeForString("nam");
        MetadataUtil.SHORT_TYPE_NAME_2 = Util.getIntegerCodeForString("trk");
        MetadataUtil.SHORT_TYPE_COMMENT = Util.getIntegerCodeForString("cmt");
        MetadataUtil.SHORT_TYPE_YEAR = Util.getIntegerCodeForString("day");
        MetadataUtil.SHORT_TYPE_ARTIST = Util.getIntegerCodeForString("ART");
        MetadataUtil.SHORT_TYPE_ENCODER = Util.getIntegerCodeForString("too");
        MetadataUtil.SHORT_TYPE_ALBUM = Util.getIntegerCodeForString("alb");
        MetadataUtil.SHORT_TYPE_COMPOSER_1 = Util.getIntegerCodeForString("com");
        MetadataUtil.SHORT_TYPE_COMPOSER_2 = Util.getIntegerCodeForString("wrt");
        MetadataUtil.SHORT_TYPE_LYRICS = Util.getIntegerCodeForString("lyr");
        MetadataUtil.SHORT_TYPE_GENRE = Util.getIntegerCodeForString("gen");
        MetadataUtil.TYPE_COVER_ART = Util.getIntegerCodeForString("covr");
        MetadataUtil.TYPE_GENRE = Util.getIntegerCodeForString("gnre");
        MetadataUtil.TYPE_GROUPING = Util.getIntegerCodeForString("grp");
        MetadataUtil.TYPE_DISK_NUMBER = Util.getIntegerCodeForString("disk");
        MetadataUtil.TYPE_TRACK_NUMBER = Util.getIntegerCodeForString("trkn");
        MetadataUtil.TYPE_TEMPO = Util.getIntegerCodeForString("tmpo");
        MetadataUtil.TYPE_COMPILATION = Util.getIntegerCodeForString("cpil");
        MetadataUtil.TYPE_ALBUM_ARTIST = Util.getIntegerCodeForString("aART");
        MetadataUtil.TYPE_SORT_TRACK_NAME = Util.getIntegerCodeForString("sonm");
        MetadataUtil.TYPE_SORT_ALBUM = Util.getIntegerCodeForString("soal");
        MetadataUtil.TYPE_SORT_ARTIST = Util.getIntegerCodeForString("soar");
        MetadataUtil.TYPE_SORT_ALBUM_ARTIST = Util.getIntegerCodeForString("soaa");
        MetadataUtil.TYPE_SORT_COMPOSER = Util.getIntegerCodeForString("soco");
        MetadataUtil.TYPE_RATING = Util.getIntegerCodeForString("rtng");
        MetadataUtil.TYPE_GAPLESS_ALBUM = Util.getIntegerCodeForString("pgap");
        MetadataUtil.TYPE_TV_SORT_SHOW = Util.getIntegerCodeForString("sosn");
        MetadataUtil.TYPE_TV_SHOW = Util.getIntegerCodeForString("tvsh");
        MetadataUtil.TYPE_INTERNAL = Util.getIntegerCodeForString("----");
        MetadataUtil.STANDARD_GENRES = new String[]{"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};
    }

    private MetadataUtil() {
        super();
    }

    private static CommentFrame parseCommentAttribute(int arg3, ParsableByteArray arg4) {
        int v0 = arg4.readInt();
        if(arg4.readInt() == Atom.TYPE_data) {
            arg4.skipBytes(8);
            String v3 = arg4.readNullTerminatedString(v0 - 16);
            return new CommentFrame("und", v3, v3);
        }

        Log.w("MetadataUtil", "Failed to parse comment attribute: " + Atom.getAtomTypeString(arg3));
        return null;
    }

    private static ApicFrame parseCoverArt(ParsableByteArray arg5) {
        String v0_2;
        String v5;
        String v2;
        int v0 = arg5.readInt();
        ApicFrame v3 = null;
        if(arg5.readInt() == Atom.TYPE_data) {
            int v1 = Atom.parseFullAtomFlags(arg5.readInt());
            if(v1 == 13) {
                v2 = "image/jpeg";
            }
            else if(v1 == 14) {
                v2 = "image/png";
            }
            else {
                v2 = ((String)v3);
            }

            if(v2 == null) {
                v5 = "MetadataUtil";
                v0_2 = "Unrecognized cover art flags: " + v1;
                goto label_24;
            }

            arg5.skipBytes(4);
            byte[] v0_3 = new byte[v0 - 16];
            arg5.readBytes(v0_3, 0, v0_3.length);
            return new ApicFrame(v2, ((String)v3), 3, v0_3);
        }
        else {
            v5 = "MetadataUtil";
            v0_2 = "Failed to parse cover art attribute";
        }

    label_24:
        Log.w(v5, v0_2);
        return v3;
    }

    public static Entry parseIlstElement(ParsableByteArray arg5) {
        ApicFrame v1_4;
        Id3Frame v1_3;
        TextInformationFrame v1_2;
        CommentFrame v1_5;
        int v0 = arg5.getPosition() + arg5.readInt();
        int v1 = arg5.readInt();
        int v2 = v1 >> 24 & 255;
        if(v2 == 169 || v2 == 65533) {
            v2 = 16777215 & v1;
            try {
                if(v2 == MetadataUtil.SHORT_TYPE_COMMENT) {
                    v1_5 = MetadataUtil.parseCommentAttribute(v1, arg5);
                }
                else {
                    goto label_119;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_5);
            try {
            label_119:
                if(v2 != MetadataUtil.SHORT_TYPE_NAME_1) {
                    if(v2 == MetadataUtil.SHORT_TYPE_NAME_2) {
                    }
                    else {
                        if(v2 != MetadataUtil.SHORT_TYPE_COMPOSER_1) {
                            if(v2 == MetadataUtil.SHORT_TYPE_COMPOSER_2) {
                            }
                            else if(v2 == MetadataUtil.SHORT_TYPE_YEAR) {
                                v1_2 = MetadataUtil.parseTextAttribute(v1, "TDRC", arg5);
                                goto label_133;
                            }
                            else {
                                goto label_135;
                            }
                        }

                        goto label_183;
                    }
                }

                goto label_187;
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

        label_133:
            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_135:
                if(v2 == MetadataUtil.SHORT_TYPE_ARTIST) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TPE1", arg5);
                }
                else {
                    goto label_141;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_141:
                if(v2 == MetadataUtil.SHORT_TYPE_ENCODER) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TSSE", arg5);
                }
                else {
                    goto label_147;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_147:
                if(v2 == MetadataUtil.SHORT_TYPE_ALBUM) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TALB", arg5);
                }
                else {
                    goto label_153;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_153:
                if(v2 == MetadataUtil.SHORT_TYPE_LYRICS) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "USLT", arg5);
                }
                else {
                    goto label_159;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_159:
                if(v2 == MetadataUtil.SHORT_TYPE_GENRE) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TCON", arg5);
                }
                else {
                    goto label_165;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_165:
                if(v2 == MetadataUtil.TYPE_GROUPING) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TIT1", arg5);
                }
                else {
                    goto label_171;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
        }
        else {
            try {
                if(v1 == MetadataUtil.TYPE_GENRE) {
                    v1_2 = MetadataUtil.parseStandardGenreAttribute(arg5);
                }
                else {
                    goto label_16;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_16:
                if(v1 == MetadataUtil.TYPE_DISK_NUMBER) {
                    v1_2 = MetadataUtil.parseIndexAndCountAttribute(v1, "TPOS", arg5);
                }
                else {
                    goto label_22;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_22:
                if(v1 == MetadataUtil.TYPE_TRACK_NUMBER) {
                    v1_2 = MetadataUtil.parseIndexAndCountAttribute(v1, "TRCK", arg5);
                }
                else {
                    goto label_28;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_28:
                if(v1 == MetadataUtil.TYPE_TEMPO) {
                    v1_3 = MetadataUtil.parseUint8Attribute(v1, "TBPM", arg5, true, false);
                }
                else {
                    goto label_36;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_3);
            try {
            label_36:
                if(v1 == MetadataUtil.TYPE_COMPILATION) {
                    v1_3 = MetadataUtil.parseUint8Attribute(v1, "TCMP", arg5, true, true);
                }
                else {
                    goto label_42;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_3);
            try {
            label_42:
                if(v1 == MetadataUtil.TYPE_COVER_ART) {
                    v1_4 = MetadataUtil.parseCoverArt(arg5);
                }
                else {
                    goto label_47;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_4);
            try {
            label_47:
                if(v1 == MetadataUtil.TYPE_ALBUM_ARTIST) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TPE2", arg5);
                }
                else {
                    goto label_53;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_53:
                if(v1 == MetadataUtil.TYPE_SORT_TRACK_NAME) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TSOT", arg5);
                }
                else {
                    goto label_59;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_59:
                if(v1 == MetadataUtil.TYPE_SORT_ALBUM) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TSO2", arg5);
                }
                else {
                    goto label_65;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_65:
                if(v1 == MetadataUtil.TYPE_SORT_ARTIST) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TSOA", arg5);
                }
                else {
                    goto label_71;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_71:
                if(v1 == MetadataUtil.TYPE_SORT_ALBUM_ARTIST) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TSOP", arg5);
                }
                else {
                    goto label_77;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_77:
                if(v1 == MetadataUtil.TYPE_SORT_COMPOSER) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TSOC", arg5);
                }
                else {
                    goto label_83;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_83:
                if(v1 == MetadataUtil.TYPE_RATING) {
                    v1_3 = MetadataUtil.parseUint8Attribute(v1, "ITUNESADVISORY", arg5, false, false);
                }
                else {
                    goto label_89;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_3);
            try {
            label_89:
                if(v1 == MetadataUtil.TYPE_GAPLESS_ALBUM) {
                    v1_3 = MetadataUtil.parseUint8Attribute(v1, "ITUNESGAPLESS", arg5, false, true);
                }
                else {
                    goto label_95;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_3);
            try {
            label_95:
                if(v1 == MetadataUtil.TYPE_TV_SORT_SHOW) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TVSHOWSORT", arg5);
                }
                else {
                    goto label_101;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_101:
                if(v1 == MetadataUtil.TYPE_TV_SHOW) {
                    v1_2 = MetadataUtil.parseTextAttribute(v1, "TVSHOW", arg5);
                }
                else {
                    goto label_107;
                }
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

            arg5.setPosition(v0);
            return ((Entry)v1_2);
            try {
            label_107:
                if(v1 == MetadataUtil.TYPE_INTERNAL) {
                    v1_3 = MetadataUtil.parseInternalAttribute(arg5, v0);
                    goto label_110;
                }

                goto label_171;
            }
            catch(Throwable v1_1) {
                goto label_192;
            }

        label_110:
            arg5.setPosition(v0);
            return ((Entry)v1_3);
        }

        try {
        label_171:
            Log.d("MetadataUtil", "Skipped unknown metadata entry: " + Atom.getAtomTypeString(v1));
        }
        catch(Throwable v1_1) {
            goto label_192;
        }

        arg5.setPosition(v0);
        return null;
        try {
        label_183:
            v1_2 = MetadataUtil.parseTextAttribute(v1, "TCOM", arg5);
        }
        catch(Throwable v1_1) {
            goto label_192;
        }

        arg5.setPosition(v0);
        return ((Entry)v1_2);
        try {
        label_187:
            v1_2 = MetadataUtil.parseTextAttribute(v1, "TIT2", arg5);
        }
        catch(Throwable v1_1) {
        label_192:
            arg5.setPosition(v0);
            throw v1_1;
        }

        arg5.setPosition(v0);
        return ((Entry)v1_2);
    }

    private static TextInformationFrame parseIndexAndCountAttribute(int arg4, String arg5, ParsableByteArray arg6) {
        int v0 = arg6.readInt();
        String v3 = null;
        if(arg6.readInt() == Atom.TYPE_data && v0 >= 22) {
            arg6.skipBytes(10);
            v0 = arg6.readUnsignedShort();
            if(v0 > 0) {
                String v4_1 = "" + v0;
                int v6 = arg6.readUnsignedShort();
                if(v6 > 0) {
                    v4_1 = v4_1 + "/" + v6;
                }

                return new TextInformationFrame(arg5, v3, v4_1);
            }
        }

        Log.w("MetadataUtil", "Failed to parse index/count attribute: " + Atom.getAtomTypeString(arg4));
        return ((TextInformationFrame)v3);
    }

    private static Id3Frame parseInternalAttribute(ParsableByteArray arg10, int arg11) {
        int v0 = -1;
        Id3Frame v1 = null;
        String v2 = ((String)v1);
        String v3 = v2;
        int v4 = -1;
        int v5 = -1;
        while(arg10.getPosition() < arg11) {
            int v6 = arg10.getPosition();
            int v7 = arg10.readInt();
            int v8 = arg10.readInt();
            arg10.skipBytes(4);
            if(v8 == Atom.TYPE_mean) {
                v2 = arg10.readNullTerminatedString(v7 - 12);
                continue;
            }

            if(v8 == Atom.TYPE_name) {
                v3 = arg10.readNullTerminatedString(v7 - 12);
                continue;
            }

            if(v8 == Atom.TYPE_data) {
                v4 = v6;
                v5 = v7;
            }

            arg10.skipBytes(v7 - 12);
        }

        if(v2 != null && v3 != null) {
            if(v4 == v0) {
            }
            else {
                arg10.setPosition(v4);
                arg10.skipBytes(16);
                return new InternalFrame(v2, v3, arg10.readNullTerminatedString(v5 - 16));
            }
        }

        return v1;
    }

    private static TextInformationFrame parseStandardGenreAttribute(ParsableByteArray arg3) {
        int v3 = MetadataUtil.parseUint8AttributeValue(arg3);
        String v0 = null;
        String v3_1 = v3 <= 0 || v3 > MetadataUtil.STANDARD_GENRES.length ? v0 : MetadataUtil.STANDARD_GENRES[v3 - 1];
        if(v3_1 != null) {
            return new TextInformationFrame("TCON", v0, v3_1);
        }

        Log.w("MetadataUtil", "Failed to parse standard genre code");
        return ((TextInformationFrame)v0);
    }

    private static TextInformationFrame parseTextAttribute(int arg4, String arg5, ParsableByteArray arg6) {
        int v0 = arg6.readInt();
        String v3 = null;
        if(arg6.readInt() == Atom.TYPE_data) {
            arg6.skipBytes(8);
            return new TextInformationFrame(arg5, v3, arg6.readNullTerminatedString(v0 - 16));
        }

        Log.w("MetadataUtil", "Failed to parse text attribute: " + Atom.getAtomTypeString(arg4));
        return ((TextInformationFrame)v3);
    }

    private static Id3Frame parseUint8Attribute(int arg0, String arg1, ParsableByteArray arg2, boolean arg3, boolean arg4) {
        TextInformationFrame v0;
        int v2 = MetadataUtil.parseUint8AttributeValue(arg2);
        if(arg4) {
            v2 = Math.min(1, v2);
        }

        String v4 = null;
        if(v2 >= 0) {
            if(arg3) {
                v0 = new TextInformationFrame(arg1, v4, Integer.toString(v2));
            }
            else {
                CommentFrame v0_1 = new CommentFrame("und", arg1, Integer.toString(v2));
            }

            return ((Id3Frame)v0);
        }

        Log.w("MetadataUtil", "Failed to parse uint8 attribute: " + Atom.getAtomTypeString(arg0));
        return ((Id3Frame)v4);
    }

    private static int parseUint8AttributeValue(ParsableByteArray arg2) {
        arg2.skipBytes(4);
        if(arg2.readInt() == Atom.TYPE_data) {
            arg2.skipBytes(8);
            return arg2.readUnsignedByte();
        }

        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}

