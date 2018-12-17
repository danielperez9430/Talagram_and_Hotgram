package org.telegram.ui.ActionBar;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build$VERSION;
import android.text.SpannedString;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatBigEmptyView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.ContextProgressView;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.GroupCreateCheckBox;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.Components.LineProgressView;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.Components.TypefaceSpan;

public class ThemeDescription {
    public interface ThemeDescriptionDelegate {
        void didSetColor();
    }

    public static int FLAG_AB_AM_BACKGROUND = 1048576;
    public static int FLAG_AB_AM_ITEMSCOLOR = 512;
    public static int FLAG_AB_AM_SELECTORCOLOR = 4194304;
    public static int FLAG_AB_AM_TOPBACKGROUND = 2097152;
    public static int FLAG_AB_ITEMSCOLOR = 64;
    public static int FLAG_AB_SEARCH = 134217728;
    public static int FLAG_AB_SEARCHPLACEHOLDER = 67108864;
    public static int FLAG_AB_SELECTORCOLOR = 256;
    public static int FLAG_AB_SUBMENUBACKGROUND = -2147483648;
    public static int FLAG_AB_SUBMENUITEM = 1073741824;
    public static int FLAG_AB_SUBTITLECOLOR = 1024;
    public static int FLAG_AB_TITLECOLOR = 128;
    public static int FLAG_BACKGROUND = 1;
    public static int FLAG_BACKGROUNDFILTER = 32;
    public static int FLAG_CELLBACKGROUNDCOLOR = 16;
    public static int FLAG_CHECKBOX = 8192;
    public static int FLAG_CHECKBOXCHECK = 16384;
    public static int FLAG_CHECKTAG = 262144;
    public static int FLAG_CURSORCOLOR = 16777216;
    public static int FLAG_DRAWABLESELECTEDSTATE = 65536;
    public static int FLAG_FASTSCROLL = 33554432;
    public static int FLAG_HINTTEXTCOLOR = 8388608;
    public static int FLAG_IMAGECOLOR = 8;
    public static int FLAG_LINKCOLOR = 2;
    public static int FLAG_LISTGLOWCOLOR = 32768;
    public static int FLAG_PROGRESSBAR = 2048;
    public static int FLAG_SECTIONS = 524288;
    public static int FLAG_SELECTOR = 4096;
    public static int FLAG_SELECTORWHITE = 268435456;
    public static int FLAG_SERVICEBACKGROUND = 536870912;
    public static int FLAG_TEXTCOLOR = 4;
    public static int FLAG_USEBACKGROUNDDRAWABLE = 131072;
    private HashMap cachedFields;
    private int changeFlags;
    private int currentColor;
    private String currentKey;
    private int defaultColor;
    private ThemeDescriptionDelegate delegate;
    private Drawable[] drawablesToUpdate;
    private Class[] listClasses;
    private String[] listClassesFieldName;
    private HashMap notFoundCachedFields;
    private Paint[] paintToUpdate;
    private int previousColor;
    private boolean[] previousIsDefault;
    private View viewToInvalidate;

    static {
    }

    public ThemeDescription(View arg3, int arg4, Class[] arg5, Paint arg6, Drawable[] arg7, ThemeDescriptionDelegate arg8, String arg9) {
        super();
        this.previousIsDefault = new boolean[1];
        this.currentKey = arg9;
        if(arg6 != null) {
            this.paintToUpdate = new Paint[]{arg6};
        }

        this.drawablesToUpdate = arg7;
        this.viewToInvalidate = arg3;
        this.changeFlags = arg4;
        this.listClasses = arg5;
        this.delegate = arg8;
    }

    public ThemeDescription(View arg1, int arg2, Class[] arg3, Paint[] arg4, Drawable[] arg5, ThemeDescriptionDelegate arg6, String arg7, Object arg8) {
        super();
        this.previousIsDefault = new boolean[1];
        this.currentKey = arg7;
        this.paintToUpdate = arg4;
        this.drawablesToUpdate = arg5;
        this.viewToInvalidate = arg1;
        this.changeFlags = arg2;
        this.listClasses = arg3;
        this.delegate = arg6;
    }

    public ThemeDescription(View arg2, int arg3, Class[] arg4, String[] arg5, Paint[] arg6, Drawable[] arg7, ThemeDescriptionDelegate arg8, String arg9) {
        super();
        this.previousIsDefault = new boolean[1];
        this.currentKey = arg9;
        this.paintToUpdate = arg6;
        this.drawablesToUpdate = arg7;
        this.viewToInvalidate = arg2;
        this.changeFlags = arg3;
        this.listClasses = arg4;
        this.listClassesFieldName = arg5;
        this.delegate = arg8;
        this.cachedFields = new HashMap();
        this.notFoundCachedFields = new HashMap();
    }

    private boolean checkTag(String arg3, View arg4) {
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                Object v4 = arg4.getTag();
                if((v4 instanceof String)) {
                    return ((String)v4).contains(((CharSequence)arg3));
                }
            }
        }

        return 0;
    }

    public int getCurrentColor() {
        return this.currentColor;
    }

    public String getCurrentKey() {
        return this.currentKey;
    }

    public int getSetColor() {
        return Theme.getColor(this.currentKey);
    }

    public String getTitle() {
        return this.currentKey;
    }

    private void processViewColor(View arg10, int arg11) {
        PorterDuffColorFilter v3_1;
        int v2;
        PorterDuffColorFilter v5_1;
        Drawable v2_1;
        int v1;
        for(v1 = 0; v1 < this.listClasses.length; ++v1) {
            if(this.listClasses[v1].isInstance(arg10)) {
                arg10.invalidate();
                int v3 = 21;
                if((this.changeFlags & ThemeDescription.FLAG_CHECKTAG) == 0 || (this.checkTag(this.currentKey, arg10))) {
                    arg10.invalidate();
                    if((this.changeFlags & ThemeDescription.FLAG_BACKGROUNDFILTER) != 0) {
                        v2_1 = arg10.getBackground();
                        if(v2_1 != null) {
                            if((this.changeFlags & ThemeDescription.FLAG_CELLBACKGROUNDCOLOR) == 0) {
                                if((v2_1 instanceof CombinedDrawable)) {
                                    v2_1 = ((CombinedDrawable)v2_1).getIcon();
                                }
                                else {
                                    if(!(v2_1 instanceof StateListDrawable)) {
                                        if(Build$VERSION.SDK_INT < v3) {
                                        }
                                        else if((v2_1 instanceof RippleDrawable)) {
                                            goto label_50;
                                        }

                                        goto label_58;
                                    }

                                label_50:
                                    boolean v5 = (this.changeFlags & ThemeDescription.FLAG_DRAWABLESELECTEDSTATE) != 0 ? true : false;
                                    Theme.setSelectorDrawableColor(v2_1, arg11, v5);
                                }

                            label_58:
                                v5_1 = new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY);
                                goto label_61;
                            }
                            else if((v2_1 instanceof CombinedDrawable)) {
                                v2_1 = ((CombinedDrawable)v2_1).getBackground();
                                if((v2_1 instanceof ColorDrawable)) {
                                    ((ColorDrawable)v2_1).setColor(arg11);
                                }
                            }
                        }
                    }
                    else {
                        if((this.changeFlags & ThemeDescription.FLAG_CELLBACKGROUNDCOLOR) != 0) {
                            arg10.setBackgroundColor(arg11);
                            goto label_99;
                        }

                        if((this.changeFlags & ThemeDescription.FLAG_TEXTCOLOR) != 0) {
                            if(!(arg10 instanceof TextView)) {
                                goto label_99;
                            }

                            arg10.setTextColor(arg11);
                            goto label_99;
                        }

                        if((this.changeFlags & ThemeDescription.FLAG_SERVICEBACKGROUND) != 0) {
                            v2_1 = arg10.getBackground();
                            if(v2_1 == null) {
                                goto label_99;
                            }

                            v5_1 = Theme.colorFilter;
                        label_61:
                            v2_1.setColorFilter(((ColorFilter)v5_1));
                            goto label_99;
                        }

                        if((this.changeFlags & ThemeDescription.FLAG_SELECTOR) != 0) {
                            v2_1 = Theme.getSelectorDrawable(false);
                        }
                        else if((this.changeFlags & ThemeDescription.FLAG_SELECTORWHITE) != 0) {
                            v2_1 = Theme.getSelectorDrawable(true);
                        }
                        else {
                            goto label_99;
                        }

                        arg10.setBackgroundDrawable(v2_1);
                    }

                label_99:
                    v2 = 1;
                }
                else {
                    v2 = 0;
                }

                if(this.listClassesFieldName != null) {
                    String v5_3 = this.listClasses[v1] + "_" + this.listClassesFieldName[v1];
                    if(this.notFoundCachedFields != null && (this.notFoundCachedFields.containsKey(v5_3))) {
                        goto label_404;
                    }

                    try {
                        Object v6 = this.cachedFields.get(v5_3);
                        if(v6 == null) {
                            Field v6_1 = this.listClasses[v1].getDeclaredField(this.listClassesFieldName[v1]);
                            if(v6_1 != null) {
                                v6_1.setAccessible(true);
                                this.cachedFields.put(v5_3, v6_1);
                            }
                        }

                        if(v6 == null) {
                            goto label_404;
                        }

                        Object v7 = ((Field)v6).get(arg10);
                        if(v7 == null) {
                            goto label_404;
                        }

                        if(v2 == 0 && ((v7 instanceof View)) && !this.checkTag(this.currentKey, v7)) {
                            goto label_404;
                        }

                        if((v7 instanceof View)) {
                            v7.invalidate();
                        }

                        if((this.changeFlags & ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE) != 0 && ((v7 instanceof View))) {
                            Drawable v7_1 = ((View)v7).getBackground();
                        }

                        if((this.changeFlags & ThemeDescription.FLAG_BACKGROUND) != 0 && ((v7 instanceof View))) {
                            ((View)v7).setBackgroundColor(arg11);
                            goto label_404;
                        }

                        if((v7 instanceof Switch)) {
                            ((Switch)v7).checkColorFilters();
                            goto label_404;
                        }

                        if((v7 instanceof EditTextCaption)) {
                            if((this.changeFlags & ThemeDescription.FLAG_HINTTEXTCOLOR) != 0) {
                                v7.setHintColor(arg11);
                                ((EditTextCaption)v7).setHintTextColor(arg11);
                                goto label_404;
                            }

                            ((EditTextCaption)v7).setTextColor(arg11);
                            goto label_404;
                        }

                        if((v7 instanceof SimpleTextView)) {
                            if((this.changeFlags & ThemeDescription.FLAG_LINKCOLOR) != 0) {
                                ((SimpleTextView)v7).setLinkTextColor(arg11);
                                goto label_404;
                            }

                            ((SimpleTextView)v7).setTextColor(arg11);
                            goto label_404;
                        }

                        if((v7 instanceof TextView)) {
                            if((this.changeFlags & ThemeDescription.FLAG_IMAGECOLOR) != 0) {
                                Drawable[] v2_3 = ((TextView)v7).getCompoundDrawables();
                                if(v2_3 == null) {
                                    goto label_404;
                                }

                                for(v3 = 0; true; ++v3) {
                                    if(v3 >= v2_3.length) {
                                        goto label_404;
                                    }

                                    if(v2_3[v3] != null) {
                                        v2_3[v3].setColorFilter(new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY));
                                    }
                                }
                            }

                            if((this.changeFlags & ThemeDescription.FLAG_LINKCOLOR) != 0) {
                                ((TextView)v7).getPaint().linkColor = arg11;
                                ((TextView)v7).invalidate();
                                goto label_404;
                            }

                            if((this.changeFlags & ThemeDescription.FLAG_FASTSCROLL) != 0) {
                                CharSequence v2_4 = ((TextView)v7).getText();
                                if(!(v2_4 instanceof SpannedString)) {
                                    goto label_404;
                                }

                                Object[] v2_5 = v2_4.getSpans(0, v2_4.length(), TypefaceSpan.class);
                                if(v2_5 == null) {
                                    goto label_404;
                                }

                                if(v2_5.length <= 0) {
                                    goto label_404;
                                }

                                for(v3 = 0; true; ++v3) {
                                    if(v3 >= v2_5.length) {
                                        goto label_404;
                                    }

                                    v2_5[v3].setColor(arg11);
                                }
                            }

                            ((TextView)v7).setTextColor(arg11);
                            goto label_404;
                        }

                        if((v7 instanceof ImageView)) {
                            ((ImageView)v7).setColorFilter(new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY));
                            goto label_404;
                        }

                        if((v7 instanceof BackupImageView)) {
                            v2_1 = ((BackupImageView)v7).getImageReceiver().getStaticThumb();
                            if((v2_1 instanceof CombinedDrawable)) {
                                if((this.changeFlags & ThemeDescription.FLAG_BACKGROUNDFILTER) != 0) {
                                    v2_1 = ((CombinedDrawable)v2_1).getBackground();
                                    v3_1 = new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY);
                                }
                                else {
                                    v2_1 = ((CombinedDrawable)v2_1).getIcon();
                                    v3_1 = new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY);
                                }
                            }
                            else if(v2_1 != null) {
                                v3_1 = new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY);
                            }
                            else {
                                goto label_404;
                            }
                        }
                        else if(!(v7 instanceof Drawable)) {
                            goto label_320;
                        }
                        else if((v7 instanceof LetterDrawable)) {
                            if((this.changeFlags & ThemeDescription.FLAG_BACKGROUNDFILTER) != 0) {
                                ((LetterDrawable)v7).setBackgroundColor(arg11);
                            }
                            else {
                                ((LetterDrawable)v7).setColor(arg11);
                            }

                            goto label_404;
                        }
                        else if(!(v7 instanceof CombinedDrawable)) {
                            goto label_299;
                        }
                        else if((this.changeFlags & ThemeDescription.FLAG_BACKGROUNDFILTER) != 0) {
                            v2_1 = ((CombinedDrawable)v7).getBackground();
                            v3_1 = new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY);
                        }
                        else {
                            v2_1 = ((CombinedDrawable)v7).getIcon();
                            v3_1 = new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY);
                        }

                        v2_1.setColorFilter(((ColorFilter)v3_1));
                        goto label_404;
                    label_299:
                        if(!(v7 instanceof StateListDrawable) && (Build$VERSION.SDK_INT < v3 || !(v7 instanceof RippleDrawable))) {
                            ((Drawable)v7).setColorFilter(new PorterDuffColorFilter(arg11, PorterDuff$Mode.MULTIPLY));
                            goto label_404;
                        }

                        boolean v2_6 = (this.changeFlags & ThemeDescription.FLAG_DRAWABLESELECTEDSTATE) != 0 ? true : false;
                        Theme.setSelectorDrawableColor(((Drawable)v7), arg11, v2_6);
                        goto label_404;
                    label_320:
                        if((v7 instanceof CheckBox)) {
                            if((this.changeFlags & ThemeDescription.FLAG_CHECKBOX) != 0) {
                                ((CheckBox)v7).setBackgroundColor(arg11);
                                goto label_404;
                            }

                            if((this.changeFlags & ThemeDescription.FLAG_CHECKBOXCHECK) == 0) {
                                goto label_404;
                            }

                            ((CheckBox)v7).setCheckColor(arg11);
                            goto label_404;
                        }

                        if((v7 instanceof GroupCreateCheckBox)) {
                            ((GroupCreateCheckBox)v7).updateColors();
                            goto label_404;
                        }

                        if((v7 instanceof Integer)) {
                            ((Field)v6).set(arg10, Integer.valueOf(arg11));
                            goto label_404;
                        }

                        if((v7 instanceof RadioButton)) {
                            if((this.changeFlags & ThemeDescription.FLAG_CHECKBOX) != 0) {
                                v7.setBackgroundColor(arg11);
                            }
                            else if((this.changeFlags & ThemeDescription.FLAG_CHECKBOXCHECK) != 0) {
                                v7.setCheckedColor(arg11);
                            }
                            else {
                                goto label_404;
                            }

                            ((RadioButton)v7).invalidate();
                            goto label_404;
                        }

                        if((v7 instanceof TextPaint)) {
                            if((this.changeFlags & ThemeDescription.FLAG_LINKCOLOR) != 0) {
                                ((TextPaint)v7).linkColor = arg11;
                                goto label_404;
                            }

                            ((TextPaint)v7).setColor(arg11);
                            goto label_404;
                        }

                        if((v7 instanceof LineProgressView)) {
                            if((this.changeFlags & ThemeDescription.FLAG_PROGRESSBAR) != 0) {
                                ((LineProgressView)v7).setProgressColor(arg11);
                                goto label_404;
                            }

                            ((LineProgressView)v7).setBackColor(arg11);
                            goto label_404;
                        }

                        if((v7 instanceof Paint)) {
                            ((Paint)v7).setColor(arg11);
                            goto label_404;
                        }

                        if(!(v7 instanceof SeekBarView)) {
                            goto label_404;
                        }

                        if((this.changeFlags & ThemeDescription.FLAG_PROGRESSBAR) != 0) {
                            ((SeekBarView)v7).setOuterColor(arg11);
                            goto label_404;
                        }

                        ((SeekBarView)v7).setInnerColor(arg11);
                    }
                    catch(Throwable v2_2) {
                        FileLog.e(v2_2);
                        this.notFoundCachedFields.put(v5_3, Boolean.valueOf(true));
                    }

                    goto label_404;
                }

                if((arg10 instanceof GroupCreateSpan)) {
                    arg10.updateColors();
                }
            }

        label_404:
        }
    }

    public void setColor(int arg5, boolean arg6, boolean arg7) {
        View v6_2;
        Drawable v6_1;
        PorterDuffColorFilter v1;
        Drawable v0;
        if(arg7) {
            Theme.setColor(this.currentKey, arg5, arg6);
        }

        int v7 = 0;
        if(this.paintToUpdate != null) {
            int v6;
            for(v6 = 0; v6 < this.paintToUpdate.length; ++v6) {
                if((this.changeFlags & ThemeDescription.FLAG_LINKCOLOR) == 0 || !(this.paintToUpdate[v6] instanceof TextPaint)) {
                    this.paintToUpdate[v6].setColor(arg5);
                }
                else {
                    this.paintToUpdate[v6].linkColor = arg5;
                }
            }
        }

        if(this.drawablesToUpdate != null) {
            for(v6 = 0; v6 < this.drawablesToUpdate.length; ++v6) {
                if(this.drawablesToUpdate[v6] == null) {
                }
                else {
                    if((this.drawablesToUpdate[v6] instanceof CombinedDrawable)) {
                        if((this.changeFlags & ThemeDescription.FLAG_BACKGROUNDFILTER) != 0) {
                            v0 = this.drawablesToUpdate[v6].getBackground();
                            v1 = new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY);
                        }
                        else {
                            v0 = this.drawablesToUpdate[v6].getIcon();
                            v1 = new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY);
                        }
                    }
                    else if((this.drawablesToUpdate[v6] instanceof AvatarDrawable)) {
                        this.drawablesToUpdate[v6].setColor(arg5);
                        goto label_74;
                    }
                    else {
                        v0 = this.drawablesToUpdate[v6];
                        v1 = new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY);
                    }

                    v0.setColorFilter(((ColorFilter)v1));
                }

            label_74:
            }
        }

        int v0_1 = 21;
        if(this.viewToInvalidate != null && this.listClasses == null && this.listClassesFieldName == null && ((this.changeFlags & ThemeDescription.FLAG_CHECKTAG) == 0 || (this.checkTag(this.currentKey, this.viewToInvalidate)))) {
            if((this.changeFlags & ThemeDescription.FLAG_BACKGROUND) != 0) {
                this.viewToInvalidate.setBackgroundColor(arg5);
            }

            if((this.changeFlags & ThemeDescription.FLAG_BACKGROUNDFILTER) == 0) {
                goto label_149;
            }

            if((this.changeFlags & ThemeDescription.FLAG_PROGRESSBAR) != 0) {
                if(!(this.viewToInvalidate instanceof EditTextBoldCursor)) {
                    goto label_149;
                }

                this.viewToInvalidate.setErrorLineColor(arg5);
                goto label_149;
            }

            v6_1 = this.viewToInvalidate.getBackground();
            if((v6_1 instanceof CombinedDrawable)) {
                v6_1 = (this.changeFlags & ThemeDescription.FLAG_DRAWABLESELECTEDSTATE) != 0 ? ((CombinedDrawable)v6_1).getBackground() : ((CombinedDrawable)v6_1).getIcon();
            }

            if(v6_1 == null) {
                goto label_149;
            }

            if(!(v6_1 instanceof StateListDrawable) && (Build$VERSION.SDK_INT < v0_1 || !(v6_1 instanceof RippleDrawable))) {
                if((v6_1 instanceof ShapeDrawable)) {
                    ((ShapeDrawable)v6_1).getPaint().setColor(arg5);
                    goto label_149;
                }

                v6_1.setColorFilter(new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY));
                goto label_149;
            }

            boolean v2 = (this.changeFlags & ThemeDescription.FLAG_DRAWABLESELECTEDSTATE) != 0 ? true : false;
            Theme.setSelectorDrawableColor(v6_1, arg5, v2);
        }

    label_149:
        if((this.viewToInvalidate instanceof ActionBar)) {
            if((this.changeFlags & ThemeDescription.FLAG_AB_ITEMSCOLOR) != 0) {
                this.viewToInvalidate.setItemsColor(arg5, false);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_TITLECOLOR) != 0) {
                this.viewToInvalidate.setTitleColor(arg5);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_SELECTORCOLOR) != 0) {
                this.viewToInvalidate.setItemsBackgroundColor(arg5, false);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_AM_SELECTORCOLOR) != 0) {
                this.viewToInvalidate.setItemsBackgroundColor(arg5, true);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_AM_ITEMSCOLOR) != 0) {
                this.viewToInvalidate.setItemsColor(arg5, true);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_SUBTITLECOLOR) != 0) {
                this.viewToInvalidate.setSubtitleColor(arg5);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_AM_BACKGROUND) != 0) {
                this.viewToInvalidate.setActionModeColor(arg5);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_AM_TOPBACKGROUND) != 0) {
                this.viewToInvalidate.setActionModeTopColor(arg5);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER) != 0) {
                this.viewToInvalidate.setSearchTextColor(arg5, true);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_SEARCH) != 0) {
                this.viewToInvalidate.setSearchTextColor(arg5, false);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_SUBMENUITEM) != 0) {
                this.viewToInvalidate.setPopupItemsColor(arg5);
            }

            if((this.changeFlags & ThemeDescription.FLAG_AB_SUBMENUBACKGROUND) == 0) {
                goto label_224;
            }

            this.viewToInvalidate.setPopupBackgroundColor(arg5);
        }

    label_224:
        if((this.viewToInvalidate instanceof EmptyTextProgressView)) {
            if((this.changeFlags & ThemeDescription.FLAG_TEXTCOLOR) != 0) {
                this.viewToInvalidate.setTextColor(arg5);
            }
            else if((this.changeFlags & ThemeDescription.FLAG_PROGRESSBAR) != 0) {
                this.viewToInvalidate.setProgressBarColor(arg5);
            }
        }

        if((this.viewToInvalidate instanceof RadialProgressView)) {
            this.viewToInvalidate.setProgressColor(arg5);
        }
        else if((this.viewToInvalidate instanceof LineProgressView)) {
            if((this.changeFlags & ThemeDescription.FLAG_PROGRESSBAR) != 0) {
                this.viewToInvalidate.setProgressColor(arg5);
            }
            else {
                this.viewToInvalidate.setBackColor(arg5);
            }
        }
        else if((this.viewToInvalidate instanceof ContextProgressView)) {
            this.viewToInvalidate.updateColors();
        }

        if((this.changeFlags & ThemeDescription.FLAG_TEXTCOLOR) != 0 && ((this.changeFlags & ThemeDescription.FLAG_CHECKTAG) == 0 || (this.checkTag(this.currentKey, this.viewToInvalidate)))) {
            if((this.viewToInvalidate instanceof TextView)) {
                this.viewToInvalidate.setTextColor(arg5);
                goto label_299;
            }

            if((this.viewToInvalidate instanceof NumberTextView)) {
                this.viewToInvalidate.setTextColor(arg5);
                goto label_299;
            }

            if((this.viewToInvalidate instanceof SimpleTextView)) {
                this.viewToInvalidate.setTextColor(arg5);
                goto label_299;
            }

            if(!(this.viewToInvalidate instanceof ChatBigEmptyView)) {
                goto label_299;
            }

            this.viewToInvalidate.setTextColor(arg5);
        }

    label_299:
        if((this.changeFlags & ThemeDescription.FLAG_CURSORCOLOR) != 0 && ((this.viewToInvalidate instanceof EditTextBoldCursor))) {
            this.viewToInvalidate.setCursorColor(arg5);
        }

        if((this.changeFlags & ThemeDescription.FLAG_HINTTEXTCOLOR) != 0) {
            if((this.viewToInvalidate instanceof EditTextBoldCursor)) {
                if((this.changeFlags & ThemeDescription.FLAG_PROGRESSBAR) != 0) {
                    this.viewToInvalidate.setHeaderHintColor(arg5);
                }
                else {
                    this.viewToInvalidate.setHintColor(arg5);
                }
            }
            else if((this.viewToInvalidate instanceof EditText)) {
                this.viewToInvalidate.setHintTextColor(arg5);
            }
        }

        if(this.viewToInvalidate != null && (this.changeFlags & ThemeDescription.FLAG_SERVICEBACKGROUND) != 0) {
            v6_1 = this.viewToInvalidate.getBackground();
            if(v6_1 != null) {
                v6_1.setColorFilter(Theme.colorFilter);
            }
        }

        if((this.changeFlags & ThemeDescription.FLAG_IMAGECOLOR) != 0 && ((this.changeFlags & ThemeDescription.FLAG_CHECKTAG) == 0 || (this.checkTag(this.currentKey, this.viewToInvalidate))) && ((this.viewToInvalidate instanceof ImageView))) {
            if((this.changeFlags & ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE) != 0) {
                v6_1 = this.viewToInvalidate.getDrawable();
                if(!(v6_1 instanceof StateListDrawable)) {
                    if(Build$VERSION.SDK_INT < v0_1) {
                    }
                    else if((v6_1 instanceof RippleDrawable)) {
                        goto label_368;
                    }

                    goto label_385;
                }

            label_368:
                boolean v0_2 = (this.changeFlags & ThemeDescription.FLAG_DRAWABLESELECTEDSTATE) != 0 ? true : false;
                Theme.setSelectorDrawableColor(v6_1, arg5, v0_2);
                goto label_385;
            }

            this.viewToInvalidate.setColorFilter(new PorterDuffColorFilter(arg5, PorterDuff$Mode.MULTIPLY));
        }

    label_385:
        if(((this.viewToInvalidate instanceof ScrollView)) && (this.changeFlags & ThemeDescription.FLAG_LISTGLOWCOLOR) != 0) {
            AndroidUtilities.setScrollViewEdgeEffectColor(this.viewToInvalidate, arg5);
        }

        if((this.viewToInvalidate instanceof RecyclerListView)) {
            v6_2 = this.viewToInvalidate;
            if((this.changeFlags & ThemeDescription.FLAG_SELECTOR) != 0 && (this.currentKey.equals("listSelectorSDK21"))) {
                ((RecyclerListView)v6_2).setListSelectorColor(arg5);
            }

            if((this.changeFlags & ThemeDescription.FLAG_FASTSCROLL) != 0) {
                ((RecyclerListView)v6_2).updateFastScrollColors();
            }

            if((this.changeFlags & ThemeDescription.FLAG_LISTGLOWCOLOR) != 0) {
                ((RecyclerListView)v6_2).setGlowColor(arg5);
            }

            if((this.changeFlags & ThemeDescription.FLAG_SECTIONS) == 0) {
                goto label_465;
            }

            ArrayList v0_3 = ((RecyclerListView)v6_2).getHeaders();
            if(v0_3 != null) {
                int v1_1;
                for(v1_1 = 0; v1_1 < v0_3.size(); ++v1_1) {
                    this.processViewColor(v0_3.get(v1_1), arg5);
                }
            }

            v0_3 = ((RecyclerListView)v6_2).getHeadersCache();
            if(v0_3 != null) {
                for(v1_1 = 0; v1_1 < v0_3.size(); ++v1_1) {
                    this.processViewColor(v0_3.get(v1_1), arg5);
                }
            }

            v6_2 = ((RecyclerListView)v6_2).getPinnedHeader();
            if(v6_2 == null) {
                goto label_465;
            }

            this.processViewColor(v6_2, arg5);
        }
        else {
            if(this.viewToInvalidate == null) {
                goto label_465;
            }

            if(this.listClasses != null && this.listClasses.length != 0) {
                goto label_465;
            }

            if((this.changeFlags & ThemeDescription.FLAG_SELECTOR) != 0) {
                v6_2 = this.viewToInvalidate;
                v0 = Theme.getSelectorDrawable(false);
            }
            else if((this.changeFlags & ThemeDescription.FLAG_SELECTORWHITE) != 0) {
                v6_2 = this.viewToInvalidate;
                v0 = Theme.getSelectorDrawable(true);
            }
            else {
                goto label_465;
            }

            v6_2.setBackgroundDrawable(v0);
        }

    label_465:
        if(this.listClasses != null) {
            if((this.viewToInvalidate instanceof RecyclerListView)) {
                v6_2 = this.viewToInvalidate;
                ((RecyclerListView)v6_2).getRecycledViewPool().clear();
                v0_1 = ((RecyclerListView)v6_2).getHiddenChildCount();
                for(v1_1 = 0; v1_1 < v0_1; ++v1_1) {
                    this.processViewColor(((RecyclerListView)v6_2).getHiddenChildAt(v1_1), arg5);
                }

                v0_1 = ((RecyclerListView)v6_2).getCachedChildCount();
                for(v1_1 = 0; v1_1 < v0_1; ++v1_1) {
                    this.processViewColor(((RecyclerListView)v6_2).getCachedChildAt(v1_1), arg5);
                }

                v0_1 = ((RecyclerListView)v6_2).getAttachedScrapChildCount();
                for(v1_1 = 0; v1_1 < v0_1; ++v1_1) {
                    this.processViewColor(((RecyclerListView)v6_2).getAttachedScrapChildAt(v1_1), arg5);
                }
            }

            if((this.viewToInvalidate instanceof ViewGroup)) {
                v6_2 = this.viewToInvalidate;
                v0_1 = ((ViewGroup)v6_2).getChildCount();
                while(v7 < v0_1) {
                    this.processViewColor(((ViewGroup)v6_2).getChildAt(v7), arg5);
                    ++v7;
                }
            }

            this.processViewColor(this.viewToInvalidate, arg5);
        }

        this.currentColor = arg5;
        if(this.delegate != null) {
            this.delegate.didSetColor();
        }

        if(this.viewToInvalidate != null) {
            this.viewToInvalidate.invalidate();
        }
    }

    public void setColor(int arg2, boolean arg3) {
        this.setColor(arg2, arg3, true);
    }

    public void setDefaultColor() {
        this.setColor(Theme.getDefaultColor(this.currentKey), true);
    }

    public ThemeDescriptionDelegate setDelegateDisabled() {
        ThemeDescriptionDelegate v0 = this.delegate;
        this.delegate = null;
        return v0;
    }

    public void setPreviousColor() {
        this.setColor(this.previousColor, this.previousIsDefault[0]);
    }

    public void startEditing() {
        int v0 = Theme.getColor(this.currentKey, this.previousIsDefault);
        this.previousColor = v0;
        this.currentColor = v0;
    }
}

