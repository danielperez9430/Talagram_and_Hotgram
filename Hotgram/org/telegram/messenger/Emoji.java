package org.telegram.messenger;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$FontMetricsInt;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.text.Spannable$Factory;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;

public class Emoji {
    class DrawableInfo {
        public int emojiIndex;
        public byte page;
        public byte page2;
        public Rect rect;

        public DrawableInfo(Rect arg1, byte arg2, byte arg3, int arg4) {
            super();
            this.rect = arg1;
            this.page = arg2;
            this.page2 = arg3;
            this.emojiIndex = arg4;
        }
    }

    public class EmojiDrawable extends Drawable {
        private boolean fullSize;
        private DrawableInfo info;
        private static Paint paint;
        private static Rect rect;
        private static TextPaint textPaint;

        static {
            EmojiDrawable.paint = new Paint(2);
            EmojiDrawable.rect = new Rect();
            EmojiDrawable.textPaint = new TextPaint(1);
        }

        public EmojiDrawable(DrawableInfo arg2) {
            super();
            this.fullSize = false;
            this.info = arg2;
        }

        static boolean access$002(EmojiDrawable arg0, boolean arg1) {
            arg0.fullSize = arg1;
            return arg1;
        }

        public void draw(Canvas arg5) {
            if(Emoji.emojiBmp[this.info.page][this.info.page2] == null) {
                if(Emoji.loadingEmoji[this.info.page][this.info.page2]) {
                    return;
                }

                Emoji.loadingEmoji[this.info.page][this.info.page2] = true;
                Utilities.globalQueue.postRunnable(new -$$Lambda$Emoji$EmojiDrawable$tIn098DVTEVbhZUc7ywBHxfGQOU(this));
                arg5.drawRect(this.getBounds(), Emoji.placeholderPaint);
                return;
            }

            Rect v0 = this.fullSize ? this.getDrawRect() : this.getBounds();
            arg5.drawBitmap(Emoji.emojiBmp[this.info.page][this.info.page2], this.info.rect, v0, EmojiDrawable.paint);
        }

        public Rect getDrawRect() {
            Rect v0 = this.getBounds();
            int v1 = v0.centerX();
            int v0_1 = v0.centerY();
            Rect v2 = EmojiDrawable.rect;
            int v3 = this.fullSize ? Emoji.bigImgSize : Emoji.drawImgSize;
            v2.left = v1 - v3 / 2;
            v2 = EmojiDrawable.rect;
            v3 = this.fullSize ? Emoji.bigImgSize : Emoji.drawImgSize;
            v2.right = v1 + v3 / 2;
            Rect v1_1 = EmojiDrawable.rect;
            int v2_1 = this.fullSize ? Emoji.bigImgSize : Emoji.drawImgSize;
            v1_1.top = v0_1 - v2_1 / 2;
            v1_1 = EmojiDrawable.rect;
            v2_1 = this.fullSize ? Emoji.bigImgSize : Emoji.drawImgSize;
            v1_1.bottom = v0_1 + v2_1 / 2;
            return EmojiDrawable.rect;
        }

        public DrawableInfo getDrawableInfo() {
            return this.info;
        }

        public int getOpacity() {
            return -2;
        }

        public static void lambda$draw$0(EmojiDrawable arg3) {
            Emoji.loadEmoji(arg3.info.page, arg3.info.page2);
            Emoji.loadingEmoji[arg3.info.page][arg3.info.page2] = false;
        }

        public void setAlpha(int arg1) {
        }

        public void setColorFilter(ColorFilter arg1) {
        }
    }

    public class EmojiSpan extends ImageSpan {
        private Paint$FontMetricsInt fontMetrics;
        private int size;

        public EmojiSpan(EmojiDrawable arg1, int arg2, int arg3, Paint$FontMetricsInt arg4) {
            super(((Drawable)arg1), arg2);
            float v1 = 20f;
            this.size = AndroidUtilities.dp(v1);
            this.fontMetrics = arg4;
            if(arg4 != null) {
                this.size = Math.abs(this.fontMetrics.descent) + Math.abs(this.fontMetrics.ascent);
                if(this.size == 0) {
                    this.size = AndroidUtilities.dp(v1);
                }
            }
        }

        public int getSize(Paint arg8, CharSequence arg9, int arg10, int arg11, Paint$FontMetricsInt arg12) {
            if(arg12 == null) {
                arg12 = new Paint$FontMetricsInt();
            }

            if(this.fontMetrics == null) {
                int v8 = super.getSize(arg8, arg9, arg10, arg11, arg12);
                int v9 = AndroidUtilities.dp(8f);
                arg10 = AndroidUtilities.dp(10f);
                arg11 = -arg10 - v9;
                arg12.top = arg11;
                arg10 -= v9;
                arg12.bottom = arg10;
                arg12.ascent = arg11;
                arg12.leading = 0;
                arg12.descent = arg10;
                return v8;
            }

            if(arg12 != null) {
                arg12.ascent = this.fontMetrics.ascent;
                arg12.descent = this.fontMetrics.descent;
                arg12.top = this.fontMetrics.top;
                arg12.bottom = this.fontMetrics.bottom;
            }

            if(this.getDrawable() != null) {
                this.getDrawable().setBounds(0, 0, this.size, this.size);
            }

            return this.size;
        }

        public void replaceFontMetrics(Paint$FontMetricsInt arg1, int arg2) {
            this.fontMetrics = arg1;
            this.size = arg2;
        }
    }

    private static int bigImgSize = 0;
    private static final int[][] cols = null;
    private static int drawImgSize = 0;
    private static Bitmap[][] emojiBmp = null;
    public static HashMap emojiColor = null;
    public static HashMap emojiUseHistory = null;
    private static boolean inited = false;
    private static boolean[][] loadingEmoji = null;
    private static Paint placeholderPaint = null;
    public static ArrayList recentEmoji = null;
    private static boolean recentEmojiLoaded = false;
    private static HashMap rects = null;
    private static final int splitCount = 4;

    static {
        // Method was not decompiled
    }

    public Emoji() {
        super();
    }

    static int access$100() {
        return Emoji.bigImgSize;
    }

    static int access$200() {
        return Emoji.drawImgSize;
    }

    static Bitmap[][] access$300() {
        return Emoji.emojiBmp;
    }

    static boolean[][] access$400() {
        return Emoji.loadingEmoji;
    }

    static Paint access$500() {
        return Emoji.placeholderPaint;
    }

    static void access$600(int arg0, int arg1) {
        Emoji.loadEmoji(arg0, arg1);
    }

    public static void addRecentEmoji(String arg5) {
        Object v0 = Emoji.emojiUseHistory.get(arg5);
        if(v0 == null) {
            Integer v0_1 = Integer.valueOf(0);
        }

        if(((Integer)v0).intValue() == 0) {
            int v2 = 50;
            if(Emoji.emojiUseHistory.size() > v2) {
                int v1 = Emoji.recentEmoji.size() - 1;
                while(v1 >= 0) {
                    Emoji.emojiUseHistory.remove(Emoji.recentEmoji.get(v1));
                    Emoji.recentEmoji.remove(v1);
                    if(Emoji.emojiUseHistory.size() <= v2) {
                    }
                    else {
                        --v1;
                        continue;
                    }

                    break;
                }
            }
        }

        Emoji.emojiUseHistory.put(arg5, Integer.valueOf(((Integer)v0).intValue() + 1));
    }

    public static void clearRecentEmoji() {
        MessagesController.getGlobalEmojiSettings().edit().putBoolean("filled_default", true).commit();
        Emoji.emojiUseHistory.clear();
        Emoji.recentEmoji.clear();
        Emoji.saveRecentEmoji();
    }

    public static String fixEmoji(String arg6) {
        StringBuilder v3_2;
        int v0 = arg6.length();
        String v2 = arg6;
        int v6;
        for(v6 = 0; v6 < v0; ++v6) {
            char v3 = v2.charAt(v6);
            int v4 = 55356;
            if(v3 < v4 || v3 > 55358) {
                if(v3 == 8419) {
                    return v2;
                }

                if(v3 < 8252) {
                    goto label_55;
                }

                if(v3 > 12953) {
                    goto label_55;
                }

                if(!EmojiData.emojiToFE0FMap.containsKey(Character.valueOf(v3))) {
                    goto label_55;
                }

                v3_2 = new StringBuilder();
                ++v6;
            label_29:
                v3_2.append(v2.substring(0, v6));
                v3_2.append("️");
                v3_2.append(v2.substring(v6));
                v2 = v3_2.toString();
                ++v0;
            }
            else {
                if(v3 == v4 && v6 < v0 - 1) {
                    int v3_1 = v6 + 1;
                    v4 = v2.charAt(v3_1);
                    if(v4 != 56879 && v4 != 56324 && v4 != 56858) {
                        if(v4 == 56703) {
                        }
                        else {
                            v6 = v3_1;
                            goto label_55;
                        }
                    }

                    v3_2 = new StringBuilder();
                    v6 += 2;
                    goto label_29;
                }

                ++v6;
            }

        label_55:
        }

        return v2;
    }

    public static Drawable getEmojiBigDrawable(String arg3) {
        EmojiDrawable v0 = Emoji.getEmojiDrawable(((CharSequence)arg3));
        if(v0 == null) {
            Object v3 = EmojiData.emojiAliasMap.get(arg3);
            if(v3 != null) {
                v0 = Emoji.getEmojiDrawable(((CharSequence)v3));
            }
        }

        if(v0 == null) {
            return null;
        }

        v0.setBounds(0, 0, Emoji.bigImgSize, Emoji.bigImgSize);
        EmojiDrawable.access$002(v0, true);
        return ((Drawable)v0);
    }

    public static EmojiDrawable getEmojiDrawable(CharSequence arg3) {
        Object v0 = Emoji.rects.get(arg3);
        if(v0 == null) {
            Object v1 = EmojiData.emojiAliasMap.get(arg3);
            if(v1 != null) {
                v0 = Emoji.rects.get(v1);
            }
        }

        if(v0 == null) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("No drawable for emoji " + arg3);
            }

            return null;
        }

        EmojiDrawable v3 = new EmojiDrawable(((DrawableInfo)v0));
        v3.setBounds(0, 0, Emoji.drawImgSize, Emoji.drawImgSize);
        return v3;
    }

    public static native Object[] getSuggestion(String arg0) {
    }

    private static boolean inArray(char arg4, char[] arg5) {
        int v0 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg5[v2] == arg4) {
                return 1;
            }
        }

        return 0;
    }

    public static void invalidateAll(View arg2) {
        if((arg2 instanceof ViewGroup)) {
            int v0;
            for(v0 = 0; v0 < ((ViewGroup)arg2).getChildCount(); ++v0) {
                Emoji.invalidateAll(((ViewGroup)arg2).getChildAt(v0));
            }
        }
        else if((arg2 instanceof TextView)) {
            arg2.invalidate();
        }
    }

    public static boolean isValidEmoji(String arg2) {
        Object v0 = Emoji.rects.get(arg2);
        if(v0 == null) {
            Object v2 = EmojiData.emojiAliasMap.get(arg2);
            if(v2 != null) {
                v0 = Emoji.rects.get(v2);
            }
        }

        boolean v2_1 = v0 != null ? true : false;
        return v2_1;
    }

    static void lambda$loadEmoji$0(int arg1, int arg2, Bitmap arg3) {
        Emoji.emojiBmp[arg1][arg2] = arg3;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.emojiDidLoaded, new Object[0]);
    }

    static int lambda$sortEmoji$1(String arg3, String arg4) {
        Object v3 = Emoji.emojiUseHistory.get(arg3);
        Object v4 = Emoji.emojiUseHistory.get(arg4);
        if(v3 == null) {
            Integer v3_1 = Integer.valueOf(0);
        }

        if(v4 == null) {
            Integer v4_1 = Integer.valueOf(0);
        }

        if(((Integer)v3).intValue() > ((Integer)v4).intValue()) {
            return -1;
        }

        if(((Integer)v3).intValue() < ((Integer)v4).intValue()) {
            return 1;
        }

        return 0;
    }

    private static void loadEmoji(int arg11, int arg12) {
        // Method was not decompiled
    }

    public static void loadRecentEmoji() {
        String[] v8;
        int v6;
        String[] v3_2;
        String v3_1;
        if(Emoji.recentEmojiLoaded) {
            return;
        }

        Emoji.recentEmojiLoaded = true;
        SharedPreferences v1 = MessagesController.getGlobalEmojiSettings();
        try {
            Emoji.emojiUseHistory.clear();
            int v4 = 16;
            int v5 = 4;
            if(v1.contains("emojis")) {
                v3_1 = v1.getString("emojis", "");
                if(v3_1 != null && v3_1.length() > 0) {
                    v3_2 = v3_1.split(",");
                    v6 = v3_2.length;
                    int v7;
                    for(v7 = 0; v7 < v6; ++v7) {
                        v8 = v3_2[v7].split("=");
                        long v9 = Utilities.parseLong(v8[0]).longValue();
                        StringBuilder v11 = new StringBuilder();
                        long v12 = v9;
                        int v9_1 = 0;
                        while(v9_1 < v5) {
                            v11.insert(0, String.valueOf(((char)(((int)v12)))));
                            v12 >>= v4;
                            if(v12 == 0) {
                            }
                            else {
                                ++v9_1;
                                continue;
                            }

                            break;
                        }

                        if(v11.length() > 0) {
                            Emoji.emojiUseHistory.put(v11.toString(), Utilities.parseInt(v8[1]));
                        }
                    }
                }

                v1.edit().remove("emojis").commit();
                Emoji.saveRecentEmoji();
            }
            else {
                v3_1 = v1.getString("emojis2", "");
                if(v3_1 == null) {
                    goto label_82;
                }

                if(v3_1.length() <= 0) {
                    goto label_82;
                }

                v3_2 = v3_1.split(",");
                v6 = v3_2.length;
                for(v7 = 0; v7 < v6; ++v7) {
                    v8 = v3_2[v7].split("=");
                    Emoji.emojiUseHistory.put(v8[0], Utilities.parseInt(v8[1]));
                }
            }

        label_82:
            if((Emoji.emojiUseHistory.isEmpty()) && !v1.getBoolean("filled_default", false)) {
                v3_2 = new String[34];
                v3_2[0] = "😂";
                v3_2[1] = "😘";
                v3_2[2] = "❤";
                v3_2[3] = "😍";
                v3_2[v5] = "😊";
                v3_2[5] = "😁";
                v3_2[6] = "👍";
                v3_2[7] = "☺";
                v3_2[8] = "😔";
                v3_2[9] = "😄";
                v3_2[10] = "😭";
                v3_2[11] = "💋";
                v3_2[12] = "😒";
                v3_2[13] = "😳";
                v3_2[14] = "😜";
                v3_2[15] = "🙈";
                v3_2[v4] = "😉";
                v3_2[17] = "😃";
                v3_2[18] = "😢";
                v3_2[19] = "😝";
                v3_2[20] = "😱";
                v3_2[21] = "😡";
                v3_2[22] = "😏";
                v3_2[23] = "😞";
                v3_2[24] = "😅";
                v3_2[25] = "😚";
                v3_2[26] = "🙊";
                v3_2[27] = "😌";
                v3_2[28] = "😀";
                v3_2[29] = "😋";
                v3_2[30] = "😆";
                v3_2[31] = "👌";
                v3_2[32] = "😐";
                v3_2[33] = "😕";
                for(v4 = 0; v4 < v3_2.length; ++v4) {
                    Emoji.emojiUseHistory.put(v3_2[v4], Integer.valueOf(v3_2.length - v4));
                }

                v1.edit().putBoolean("filled_default", true).commit();
                Emoji.saveRecentEmoji();
            }

            Emoji.sortEmoji();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }

        try {
            String v1_1 = v1.getString("color", "");
            if(v1_1 == null) {
                return;
            }

            if(v1_1.length() <= 0) {
                return;
            }

            String[] v1_2 = v1_1.split(",");
            int v3_3;
            for(v3_3 = 0; v3_3 < v1_2.length; ++v3_3) {
                String[] v4_1 = v1_2[v3_3].split("=");
                Emoji.emojiColor.put(v4_1[0], v4_1[1]);
            }
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static CharSequence replaceEmoji(CharSequence arg1, Paint$FontMetricsInt arg2, int arg3, boolean arg4) {
        return Emoji.replaceEmoji(arg1, arg2, arg3, arg4, null);
    }

    public static CharSequence replaceEmoji(CharSequence arg24, Paint$FontMetricsInt arg25, int arg26, boolean arg27, int[] arg28) {
        int v5_1;
        int v3;
        int v6_2;
        char v6_1;
        int v9;
        Spannable v0_1;
        CharSequence v1 = arg24;
        if(!SharedConfig.useSystemEmoji && v1 != null) {
            if(arg24.length() == 0) {
            }
            else {
                if((arg27) || !(v1 instanceof Spannable)) {
                    v0_1 = Spannable$Factory.getInstance().newSpannable(arg24.toString());
                }
                else {
                    CharSequence v0 = v1;
                }

                if(Build$VERSION.SDK_INT >= 19 && (ApplicationLoader.SHOW_ANDROID_EMOJI)) {
                    return ((CharSequence)v0_1);
                }

                StringBuilder v2 = new StringBuilder(16);
                new StringBuilder(2);
                int v4 = arg24.length();
                long v6 = 0;
                int v8 = -1;
                int[] v16 = arg28;
                long v11 = v6;
                int v10 = 0;
                int v13 = -1;
                int v14 = 0;
                int v15 = 0;
                int v17 = 0;
                int v18 = 0;
                while(true) {
                    if(v10 < v4) {
                        try {
                            char v5 = v1.charAt(v10);
                            if(v5 < 55356 || v5 > 55358) {
                                if(v11 != v6 && (v11 & -4294967296L) == v6 && (v11 & 65535) == 55356 && v5 >= 56806 && v5 <= 56831) {
                                label_57:
                                    if(v13 == v8) {
                                        v13 = v10;
                                    }

                                    v2.append(v5);
                                    v9 = v14 + 1;
                                    v11 = v11 << 16 | (((long)v5));
                                    goto label_145;
                                }

                                if(v2.length() > 0) {
                                    if(v5 != 9792 && v5 != 9794 && v5 != 9877) {
                                        goto label_80;
                                    }

                                    v2.append(v5);
                                    v9 = v14 + 1;
                                    v11 = v6;
                                }
                                else {
                                label_80:
                                    if(v11 > v6 && (61440 & v5) == 53248) {
                                        v2.append(v5);
                                        v9 = v14 + 1;
                                        v11 = 0;
                                        goto label_78;
                                    }

                                    if(v5 != 8419) {
                                        if((v5 == 169 || v5 == 174 || v5 >= 8252 && v5 <= 12953) && (EmojiData.dataCharsMap.containsKey(Character.valueOf(v5)))) {
                                            if(v13 == v8) {
                                                v13 = v10;
                                            }

                                            v9 = v14 + 1;
                                            v2.append(v5);
                                            goto label_78;
                                        }

                                        goto label_130;
                                    }
                                    else if(v10 > 0) {
                                        v6_1 = v1.charAt(v15);
                                        if(v6_1 >= 48 && v6_1 <= 57 || (v6_1 == 35 || v6_1 == 42)) {
                                            v2.append(v6_1);
                                            v2.append(v5);
                                            v14 = v10 - v15 + 1;
                                            v13 = v15;
                                            v9 = 1;
                                        }
                                        else {
                                            v9 = v17;
                                        }

                                        v17 = v9;
                                    }

                                    goto label_144;
                                }

                            label_78:
                                v17 = 1;
                                goto label_145;
                            label_130:
                                if(v13 != v8) {
                                    v2.setLength(0);
                                    v9 = 0;
                                    v13 = -1;
                                    v17 = 0;
                                    goto label_145;
                                }

                                if(v5 != 65039 && v16 != null) {
                                    v16[0] = 0;
                                    v16 = null;
                                }

                            label_144:
                                v9 = v14;
                            }
                            else {
                                goto label_57;
                            }

                        label_145:
                            v6_2 = 57343;
                            int v7 = 57339;
                            if(v17 != 0) {
                                v14 = v10 + 2;
                                if(v14 < v4) {
                                    v15 = v10 + 1;
                                    v8 = v1.charAt(v15);
                                    if(v8 == 55356) {
                                        v3 = v1.charAt(v14);
                                        if(v3 < v7) {
                                            goto label_193;
                                        }
                                        else if(v3 <= v6_2) {
                                            v2.append(v1.subSequence(v15, v10 + 3));
                                            v9 += 2;
                                            v15 = v14;
                                        }
                                        else {
                                            goto label_193;
                                        }
                                    }
                                    else if(v2.length() < 2) {
                                        goto label_193;
                                    }
                                    else if(v2.charAt(0) != 55356) {
                                        goto label_193;
                                    }
                                    else if(v2.charAt(1) == 57332) {
                                        v3 = 56128;
                                        if(v8 != v3) {
                                            goto label_193;
                                        }

                                        while(true) {
                                            v8 = v15 + 2;
                                            v2.append(v1.subSequence(v15, v8));
                                            v9 += 2;
                                            if(v8 < arg24.length()) {
                                                if(v1.charAt(v8) != v3) {
                                                }
                                                else {
                                                    v15 = v8;
                                                    continue;
                                                }
                                            }

                                            break;
                                        }

                                        v15 = v8 - 1;
                                    }
                                    else {
                                        goto label_193;
                                    }
                                }
                                else {
                                    goto label_193;
                                }
                            }
                            else {
                            label_193:
                                v15 = v10;
                            }

                            v10 = v9;
                            v8 = v15;
                            v9 = v17;
                            for(v3 = 0; v3 < 3; ++v3) {
                                v14 = v8 + 1;
                                if(v14 < v4) {
                                    v6_1 = v1.charAt(v14);
                                    if(v3 != 1) {
                                        if(v13 == -1 && v5 != 42) {
                                            if(v5 < 49) {
                                            }
                                            else if(v5 <= 57) {
                                                goto label_223;
                                            }

                                            goto label_230;
                                        }

                                    label_223:
                                        if(v6_1 < 65024) {
                                            goto label_230;
                                        }

                                        if(v6_1 > 65039) {
                                            goto label_231;
                                        }

                                        ++v10;
                                        v8 = v14;
                                    }
                                    else if(v6_1 != 8205) {
                                        goto label_230;
                                    }
                                    else if(v2.length() > 0) {
                                        v2.append(v6_1);
                                        ++v10;
                                        v8 = v14;
                                        v9 = 0;
                                    }
                                    else {
                                        goto label_230;
                                    }
                                }
                                else {
                                label_230:
                                }

                            label_231:
                            }

                            if(v9 != 0) {
                                v3 = v8 + 2;
                                if(v3 < v4) {
                                    v5_1 = v8 + 1;
                                    if(v1.charAt(v5_1) == 55356) {
                                        v6_2 = v1.charAt(v3);
                                        if(v6_2 < 57339) {
                                            goto label_252;
                                        }
                                        else if(v6_2 <= 57343) {
                                            v2.append(v1.subSequence(v5_1, v8 + 3));
                                            v10 += 2;
                                        }
                                        else {
                                            goto label_252;
                                        }
                                    }
                                    else {
                                        goto label_252;
                                    }
                                }
                                else {
                                    goto label_252;
                                }
                            }
                            else {
                            label_252:
                                v3 = v8;
                            }

                            if(v9 != 0) {
                                if(v16 != null) {
                                    v5_1 = 0;
                                    ++v16[0];
                                }
                                else {
                                    v5_1 = 0;
                                }

                                EmojiDrawable v6_3 = Emoji.getEmojiDrawable(v2.subSequence(v5_1, v2.length()));
                                if(v6_3 != null) {
                                    v0_1.setSpan(new EmojiSpan(v6_3, v5_1, arg26, arg25), v13, v10 + v13, 33);
                                    ++v18;
                                    v5_1 = 0;
                                }
                                else {
                                }

                                v2.setLength(v5_1);
                                v6_2 = v18;
                                v10 = 0;
                                v13 = -1;
                                v17 = 0;
                            }
                            else {
                                v17 = v9;
                                v6_2 = v18;
                            }

                            if(Build$VERSION.SDK_INT < 23) {
                                goto label_292;
                            }

                            goto label_295;
                        }
                        catch(Exception v0_2) {
                            break;
                        }

                    label_292:
                        if(v6_2 >= 50) {
                            goto label_308;
                        }

                    label_295:
                        v18 = v6_2;
                        v14 = v10;
                        v6 = 0;
                        v8 = -1;
                        v10 = v3 + 1;
                        continue;
                    }

                    goto label_308;
                }

                FileLog.e(((Throwable)v0_2));
                return v1;
            label_308:
                return ((CharSequence)v0_1);
            }
        }

        return v1;
    }

    public static void saveEmojiColors() {
        SharedPreferences v0 = MessagesController.getGlobalEmojiSettings();
        StringBuilder v1 = new StringBuilder();
        Iterator v2 = Emoji.emojiColor.entrySet().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            if(v1.length() != 0) {
                v1.append(",");
            }

            v1.append(((Map$Entry)v3).getKey());
            v1.append("=");
            v1.append(((Map$Entry)v3).getValue());
        }

        v0.edit().putString("color", v1.toString()).commit();
    }

    public static void saveRecentEmoji() {
        SharedPreferences v0 = MessagesController.getGlobalEmojiSettings();
        StringBuilder v1 = new StringBuilder();
        Iterator v2 = Emoji.emojiUseHistory.entrySet().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            if(v1.length() != 0) {
                v1.append(",");
            }

            v1.append(((Map$Entry)v3).getKey());
            v1.append("=");
            v1.append(((Map$Entry)v3).getValue());
        }

        v0.edit().putString("emojis2", v1.toString()).commit();
    }

    public static void sortEmoji() {
        Emoji.recentEmoji.clear();
        Iterator v0 = Emoji.emojiUseHistory.entrySet().iterator();
        while(v0.hasNext()) {
            Emoji.recentEmoji.add(v0.next().getKey());
        }

        Collections.sort(Emoji.recentEmoji, -$$Lambda$Emoji$IRtAaHh32-YY7tgie8_WycuV8i0.INSTANCE);
        while(Emoji.recentEmoji.size() > 50) {
            Emoji.recentEmoji.remove(Emoji.recentEmoji.size() - 1);
        }
    }
}

