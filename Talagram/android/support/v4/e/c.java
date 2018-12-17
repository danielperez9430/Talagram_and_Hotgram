package android.support.v4.e;

import android.os.Build$VERSION;
import android.os.LocaleList;
import android.support.v4.f.i;
import android.text.PrecomputedText$Params$Builder;
import android.text.PrecomputedText$Params;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.Locale;
import java.util.concurrent.Executor;

public class c implements Spannable {
    public final class a {
        public class android.support.v4.e.c$a$a {
            private final TextPaint a;
            private TextDirectionHeuristic b;
            private int c;
            private int d;

            public android.support.v4.e.c$a$a(TextPaint arg2) {
                super();
                this.a = arg2;
                if(Build$VERSION.SDK_INT >= 23) {
                    this.c = 1;
                    this.d = 1;
                }
                else {
                    this.d = 0;
                    this.c = 0;
                }

                TextDirectionHeuristic v2 = Build$VERSION.SDK_INT >= 18 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : null;
                this.b = v2;
            }

            public android.support.v4.e.c$a$a a(int arg1) {
                this.c = arg1;
                return this;
            }

            public android.support.v4.e.c$a$a a(TextDirectionHeuristic arg1) {
                this.b = arg1;
                return this;
            }

            public a a() {
                return new a(this.a, this.b, this.c, this.d);
            }

            public android.support.v4.e.c$a$a b(int arg1) {
                this.d = arg1;
                return this;
            }
        }

        final PrecomputedText$Params a;
        private final TextPaint b;
        private final TextDirectionHeuristic c;
        private final int d;
        private final int e;

        public a(PrecomputedText$Params arg2) {
            super();
            this.b = arg2.getTextPaint();
            this.c = arg2.getTextDirection();
            this.d = arg2.getBreakStrategy();
            this.e = arg2.getHyphenationFrequency();
            this.a = arg2;
        }

        a(TextPaint arg3, TextDirectionHeuristic arg4, int arg5, int arg6) {
            super();
            PrecomputedText$Params v0 = Build$VERSION.SDK_INT >= 28 ? new PrecomputedText$Params$Builder(arg3).setBreakStrategy(arg5).setHyphenationFrequency(arg6).setTextDirection(arg4).build() : null;
            this.a = v0;
            this.b = arg3;
            this.c = arg4;
            this.d = arg5;
            this.e = arg6;
        }

        public TextPaint a() {
            return this.b;
        }

        public TextDirectionHeuristic b() {
            return this.c;
        }

        public int c() {
            return this.d;
        }

        public int d() {
            return this.e;
        }

        public boolean equals(Object arg5) {
            if((((a)arg5)) == this) {
                return 1;
            }

            if(arg5 != null) {
                if(!(arg5 instanceof a)) {
                }
                else if(this.a != null) {
                    return this.a.equals(((a)arg5).a);
                }
                else {
                    if(Build$VERSION.SDK_INT >= 23) {
                        if(this.d != ((a)arg5).c()) {
                            return 0;
                        }
                        else if(this.e != ((a)arg5).d()) {
                            return 0;
                        }
                    }

                    if(Build$VERSION.SDK_INT >= 18 && this.c != ((a)arg5).b()) {
                        return 0;
                    }

                    if(this.b.getTextSize() != ((a)arg5).a().getTextSize()) {
                        return 0;
                    }

                    if(this.b.getTextScaleX() != ((a)arg5).a().getTextScaleX()) {
                        return 0;
                    }

                    if(this.b.getTextSkewX() != ((a)arg5).a().getTextSkewX()) {
                        return 0;
                    }

                    if(Build$VERSION.SDK_INT >= 21) {
                        if(this.b.getLetterSpacing() != ((a)arg5).a().getLetterSpacing()) {
                            return 0;
                        }
                        else if(!TextUtils.equals(this.b.getFontFeatureSettings(), ((a)arg5).a().getFontFeatureSettings())) {
                            return 0;
                        }
                    }

                    if(this.b.getFlags() != ((a)arg5).a().getFlags()) {
                        return 0;
                    }

                    if(Build$VERSION.SDK_INT >= 24) {
                        if(!this.b.getTextLocales().equals(((a)arg5).a().getTextLocales())) {
                            return 0;
                        }
                    }
                    else if(Build$VERSION.SDK_INT >= 17 && !this.b.getTextLocale().equals(((a)arg5).a().getTextLocale())) {
                        return 0;
                    }

                    if(this.b.getTypeface() == null) {
                        if(((a)arg5).a().getTypeface() != null) {
                            return 0;
                        }
                    }
                    else if(!this.b.getTypeface().equals(((a)arg5).a().getTypeface())) {
                        return 0;
                    }

                    return 1;
                }
            }

            return 0;
        }

        public int hashCode() {
            Object[] v0;
            int v1 = 10;
            int v2 = 11;
            int v3 = 9;
            int v4 = 8;
            int v5 = 7;
            int v6 = 6;
            int v7 = 5;
            int v8 = 4;
            int v9 = 3;
            int v10 = 2;
            if(Build$VERSION.SDK_INT >= 24) {
                v0 = new Object[v2];
                v0[0] = Float.valueOf(this.b.getTextSize());
                v0[1] = Float.valueOf(this.b.getTextScaleX());
                v0[v10] = Float.valueOf(this.b.getTextSkewX());
                v0[v9] = Float.valueOf(this.b.getLetterSpacing());
                v0[v8] = Integer.valueOf(this.b.getFlags());
                v0[v7] = this.b.getTextLocales();
                v0[v6] = this.b.getTypeface();
                v0[v5] = Boolean.valueOf(this.b.isElegantTextHeight());
                v0[v4] = this.c;
                v0[v3] = Integer.valueOf(this.d);
                v0[v1] = Integer.valueOf(this.e);
            }
            else if(Build$VERSION.SDK_INT >= 21) {
                v0 = new Object[v2];
                v0[0] = Float.valueOf(this.b.getTextSize());
                v0[1] = Float.valueOf(this.b.getTextScaleX());
                v0[v10] = Float.valueOf(this.b.getTextSkewX());
                v0[v9] = Float.valueOf(this.b.getLetterSpacing());
                v0[v8] = Integer.valueOf(this.b.getFlags());
                v0[v7] = this.b.getTextLocale();
                v0[v6] = this.b.getTypeface();
                v0[v5] = Boolean.valueOf(this.b.isElegantTextHeight());
                v0[v4] = this.c;
                v0[v3] = Integer.valueOf(this.d);
                v0[v1] = Integer.valueOf(this.e);
            }
            else if(Build$VERSION.SDK_INT >= 18) {
                v0 = new Object[v3];
                v0[0] = Float.valueOf(this.b.getTextSize());
                v0[1] = Float.valueOf(this.b.getTextScaleX());
                v0[v10] = Float.valueOf(this.b.getTextSkewX());
                v0[v9] = Integer.valueOf(this.b.getFlags());
                v0[v8] = this.b.getTextLocale();
                v0[v7] = this.b.getTypeface();
                v0[v6] = this.c;
                v0[v5] = Integer.valueOf(this.d);
                v0[v4] = Integer.valueOf(this.e);
            }
            else if(Build$VERSION.SDK_INT >= 17) {
                v0 = new Object[v3];
                v0[0] = Float.valueOf(this.b.getTextSize());
                v0[1] = Float.valueOf(this.b.getTextScaleX());
                v0[v10] = Float.valueOf(this.b.getTextSkewX());
                v0[v9] = Integer.valueOf(this.b.getFlags());
                v0[v8] = this.b.getTextLocale();
                v0[v7] = this.b.getTypeface();
                v0[v6] = this.c;
                v0[v5] = Integer.valueOf(this.d);
                v0[v4] = Integer.valueOf(this.e);
            }
            else {
                v0 = new Object[v4];
                v0[0] = Float.valueOf(this.b.getTextSize());
                v0[1] = Float.valueOf(this.b.getTextScaleX());
                v0[v10] = Float.valueOf(this.b.getTextSkewX());
                v0[v9] = Integer.valueOf(this.b.getFlags());
                v0[v8] = this.b.getTypeface();
                v0[v7] = this.c;
                v0[v6] = Integer.valueOf(this.d);
                v0[v5] = Integer.valueOf(this.e);
            }

            return i.a(v0);
        }

        public String toString() {
            LocaleList v2;
            StringBuilder v0 = new StringBuilder("{");
            v0.append("textSize=" + this.b.getTextSize());
            v0.append(", textScaleX=" + this.b.getTextScaleX());
            v0.append(", textSkewX=" + this.b.getTextSkewX());
            if(Build$VERSION.SDK_INT >= 21) {
                v0.append(", letterSpacing=" + this.b.getLetterSpacing());
                v0.append(", elegantTextHeight=" + this.b.isElegantTextHeight());
            }

            if(Build$VERSION.SDK_INT >= 24) {
                v1 = new StringBuilder();
                v1.append(", textLocale=");
                v2 = this.b.getTextLocales();
                goto label_60;
            }
            else if(Build$VERSION.SDK_INT >= 17) {
                v1 = new StringBuilder();
                v1.append(", textLocale=");
                Locale v2_1 = this.b.getTextLocale();
            label_60:
                v1.append(v2);
                v0.append(v1.toString());
            }

            v0.append(", typeface=" + this.b.getTypeface());
            if(Build$VERSION.SDK_INT >= 26) {
                v0.append(", variationSettings=" + this.b.getFontVariationSettings());
            }

            v0.append(", textDir=" + this.c);
            v0.append(", breakStrategy=" + this.d);
            v0.append(", hyphenationFrequency=" + this.e);
            v0.append("}");
            return v0.toString();
        }
    }

    private static final Object a;
    private static Executor b;
    private final Spannable c;
    private final a d;
    private final PrecomputedText e;

    static {
        c.a = new Object();
        c.b = null;
    }

    public PrecomputedText a() {
        if((this.c instanceof PrecomputedText)) {
            return this.c;
        }

        return null;
    }

    public a b() {
        return this.d;
    }

    public char charAt(int arg2) {
        return this.c.charAt(arg2);
    }

    public int getSpanEnd(Object arg2) {
        return this.c.getSpanEnd(arg2);
    }

    public int getSpanFlags(Object arg2) {
        return this.c.getSpanFlags(arg2);
    }

    public int getSpanStart(Object arg2) {
        return this.c.getSpanStart(arg2);
    }

    public Object[] getSpans(int arg3, int arg4, Class arg5) {
        if(Build$VERSION.SDK_INT >= 28) {
            return this.e.getSpans(arg3, arg4, arg5);
        }

        return this.c.getSpans(arg3, arg4, arg5);
    }

    public int length() {
        return this.c.length();
    }

    public int nextSpanTransition(int arg2, int arg3, Class arg4) {
        return this.c.nextSpanTransition(arg2, arg3, arg4);
    }

    public void removeSpan(Object arg3) {
        if(!(arg3 instanceof MetricAffectingSpan)) {
            if(Build$VERSION.SDK_INT >= 28) {
                this.e.removeSpan(arg3);
            }
            else {
                this.c.removeSpan(arg3);
            }

            return;
        }

        throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
    }

    public void setSpan(Object arg3, int arg4, int arg5, int arg6) {
        if(!(arg3 instanceof MetricAffectingSpan)) {
            if(Build$VERSION.SDK_INT >= 28) {
                this.e.setSpan(arg3, arg4, arg5, arg6);
            }
            else {
                this.c.setSpan(arg3, arg4, arg5, arg6);
            }

            return;
        }

        throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
    }

    public CharSequence subSequence(int arg2, int arg3) {
        return this.c.subSequence(arg2, arg3);
    }

    public String toString() {
        return this.c.toString();
    }
}

