package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.support.v4.view.t;
import android.support.v7.a.a$j;
import android.support.v7.view.d;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.bh;
import android.support.v7.widget.f;
import android.support.v7.widget.h;
import android.support.v7.widget.i;
import android.support.v7.widget.l;
import android.support.v7.widget.o;
import android.support.v7.widget.r;
import android.support.v7.widget.s;
import android.support.v7.widget.v;
import android.support.v7.widget.x;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View$OnClickListener;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class AppCompatViewInflater {
    class a implements View$OnClickListener {
        private final View a;
        private final String b;
        private Method c;
        private Context d;

        public a(View arg1, String arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        private void a(Context arg5, String arg6) {
            while(arg5 != null) {
                try {
                    if(!arg5.isRestricted()) {
                        Method v6 = arg5.getClass().getMethod(this.b, View.class);
                        if(v6 != null) {
                            this.c = v6;
                            this.d = arg5;
                            return;
                        }
                    }

                    goto label_15;
                }
                catch(NoSuchMethodException ) {
                label_15:
                    if((arg5 instanceof ContextWrapper)) {
                        arg5 = ((ContextWrapper)arg5).getBaseContext();
                        continue;
                    }

                    arg5 = null;
                    continue;
                }
            }

            int v5 = this.a.getId();
            String v5_1 = v5 == -1 ? "" : " with id \'" + this.a.getContext().getResources().getResourceEntryName(v5) + "\'";
            StringBuilder v0 = new StringBuilder();
            v0.append("Could not find method ");
            v0.append(this.b);
            v0.append("(View) in a parent or ancestor Context for android:onClick ");
            v0.append("attribute defined on view ");
            v0.append(this.a.getClass());
            v0.append(v5_1);
            throw new IllegalStateException(v0.toString());
        }

        public void onClick(View arg5) {
            if(this.c == null) {
                this.a(this.a.getContext(), this.b);
            }

            try {
                this.c.invoke(this.d, arg5);
                return;
            }
            catch(InvocationTargetException v5) {
                throw new IllegalStateException("Could not execute method for android:onClick", ((Throwable)v5));
            }
            catch(IllegalAccessException v5_1) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", ((Throwable)v5_1));
            }
        }
    }

    private static final String LOG_TAG = "AppCompatViewInflater";
    private final Object[] mConstructorArgs;
    private static final String[] sClassPrefixList;
    private static final Map sConstructorMap;
    private static final Class[] sConstructorSignature;
    private static final int[] sOnClickAttrs;

    static {
        AppCompatViewInflater.sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
        AppCompatViewInflater.sOnClickAttrs = new int[]{16843375};
        AppCompatViewInflater.sClassPrefixList = new String[]{"android.widget.", "android.view.", "android.webkit."};
        AppCompatViewInflater.sConstructorMap = new android.support.v4.f.a();
    }

    public AppCompatViewInflater() {
        super();
        this.mConstructorArgs = new Object[2];
    }

    private void checkOnClickListener(View arg4, AttributeSet arg5) {
        Context v0 = arg4.getContext();
        if(((v0 instanceof ContextWrapper)) && (Build$VERSION.SDK_INT < 15 || (t.E(arg4)))) {
            TypedArray v5 = v0.obtainStyledAttributes(arg5, AppCompatViewInflater.sOnClickAttrs);
            String v0_1 = v5.getString(0);
            if(v0_1 != null) {
                arg4.setOnClickListener(new a(arg4, v0_1));
            }

            v5.recycle();
        }
    }

    protected f createAutoCompleteTextView(Context arg2, AttributeSet arg3) {
        return new f(arg2, arg3);
    }

    protected AppCompatButton createButton(Context arg2, AttributeSet arg3) {
        return new AppCompatButton(arg2, arg3);
    }

    protected h createCheckBox(Context arg2, AttributeSet arg3) {
        return new h(arg2, arg3);
    }

    protected i createCheckedTextView(Context arg2, AttributeSet arg3) {
        return new i(arg2, arg3);
    }

    protected l createEditText(Context arg2, AttributeSet arg3) {
        return new l(arg2, arg3);
    }

    protected AppCompatImageButton createImageButton(Context arg2, AttributeSet arg3) {
        return new AppCompatImageButton(arg2, arg3);
    }

    protected AppCompatImageView createImageView(Context arg2, AttributeSet arg3) {
        return new AppCompatImageView(arg2, arg3);
    }

    protected o createMultiAutoCompleteTextView(Context arg2, AttributeSet arg3) {
        return new o(arg2, arg3);
    }

    protected r createRadioButton(Context arg2, AttributeSet arg3) {
        return new r(arg2, arg3);
    }

    protected s createRatingBar(Context arg2, AttributeSet arg3) {
        return new s(arg2, arg3);
    }

    protected android.support.v7.widget.t createSeekBar(Context arg2, AttributeSet arg3) {
        return new android.support.v7.widget.t(arg2, arg3);
    }

    protected v createSpinner(Context arg2, AttributeSet arg3) {
        return new v(arg2, arg3);
    }

    protected x createTextView(Context arg2, AttributeSet arg3) {
        return new x(arg2, arg3);
    }

    protected View createView(Context arg1, String arg2, AttributeSet arg3) {
        return null;
    }

    final View createView(View arg1, String arg2, Context arg3, AttributeSet arg4, boolean arg5, boolean arg6, boolean arg7, boolean arg8) {
        Context v1 = !arg5 || arg1 == null ? arg3 : arg1.getContext();
        if((arg6) || (arg7)) {
            v1 = AppCompatViewInflater.themifyContext(v1, arg4, arg6, arg7);
        }

        if(arg8) {
            v1 = bh.a(v1);
        }

        int v5 = -1;
        switch(arg2.hashCode()) {
            case 776382189: {
                if(!arg2.equals("RadioButton")) {
                    goto label_78;
                }

                v5 = 7;
                break;
            }
            case 1125864064: {
                if(!arg2.equals("ImageView")) {
                    goto label_78;
                }

                v5 = 1;
                break;
            }
            case 1413872058: {
                if(!arg2.equals("AutoCompleteTextView")) {
                    goto label_78;
                }

                v5 = 9;
                break;
            }
            case 1601505219: {
                if(!arg2.equals("CheckBox")) {
                    goto label_78;
                }

                v5 = 6;
                break;
            }
            case 1666676343: {
                if(!arg2.equals("EditText")) {
                    goto label_78;
                }

                v5 = 3;
                break;
            }
            case 2001146706: {
                if(!arg2.equals("Button")) {
                    goto label_78;
                }

                v5 = 2;
                break;
            }
            case -1946472170: {
                if(!arg2.equals("RatingBar")) {
                    goto label_78;
                }

                v5 = 11;
                break;
            }
            case -1455429095: {
                if(!arg2.equals("CheckedTextView")) {
                    goto label_78;
                }

                v5 = 8;
                break;
            }
            case -1346021293: {
                if(!arg2.equals("MultiAutoCompleteTextView")) {
                    goto label_78;
                }

                v5 = 10;
                break;
            }
            case -938935918: {
                if(!arg2.equals("TextView")) {
                    goto label_78;
                }

                v5 = 0;
                break;
            }
            case -937446323: {
                if(!arg2.equals("ImageButton")) {
                    goto label_78;
                }

                v5 = 5;
                break;
            }
            case -658531749: {
                if(!arg2.equals("SeekBar")) {
                    goto label_78;
                }

                v5 = 12;
                break;
            }
            case -339785223: {
                if(!arg2.equals("Spinner")) {
                    goto label_78;
                }

                v5 = 4;
                break;
            }
            default: {
                break;
            }
        }

    label_78:
        switch(v5) {
            case 0: {
                goto label_105;
            }
            case 1: {
                goto label_103;
            }
            case 2: {
                goto label_101;
            }
            case 3: {
                goto label_99;
            }
            case 4: {
                goto label_97;
            }
            case 5: {
                goto label_95;
            }
            case 6: {
                goto label_93;
            }
            case 7: {
                goto label_91;
            }
            case 8: {
                goto label_89;
            }
            case 9: {
                goto label_87;
            }
            case 10: {
                goto label_85;
            }
            case 11: {
                goto label_83;
            }
            case 12: {
                goto label_81;
            }
        }

        View v5_1 = this.createView(v1, arg2, arg4);
        goto label_107;
    label_97:
        v v5_2 = this.createSpinner(v1, arg4);
        goto label_106;
    label_99:
        l v5_3 = this.createEditText(v1, arg4);
        goto label_106;
    label_101:
        AppCompatButton v5_4 = this.createButton(v1, arg4);
        goto label_106;
    label_103:
        AppCompatImageView v5_5 = this.createImageView(v1, arg4);
        goto label_106;
    label_105:
        x v5_6 = this.createTextView(v1, arg4);
        goto label_106;
    label_81:
        android.support.v7.widget.t v5_7 = this.createSeekBar(v1, arg4);
        goto label_106;
    label_83:
        s v5_8 = this.createRatingBar(v1, arg4);
        goto label_106;
    label_85:
        o v5_9 = this.createMultiAutoCompleteTextView(v1, arg4);
        goto label_106;
    label_87:
        f v5_10 = this.createAutoCompleteTextView(v1, arg4);
        goto label_106;
    label_89:
        i v5_11 = this.createCheckedTextView(v1, arg4);
        goto label_106;
    label_91:
        r v5_12 = this.createRadioButton(v1, arg4);
        goto label_106;
    label_93:
        h v5_13 = this.createCheckBox(v1, arg4);
        goto label_106;
    label_95:
        AppCompatImageButton v5_14 = this.createImageButton(v1, arg4);
    label_106:
        this.verifyNotNull(((View)v5_3), arg2);
    label_107:
        if((((View)v5_3)) == null && arg3 != v1) {
            v5_1 = this.createViewFromTag(v1, arg2, arg4);
        }

        if((((View)v5_3)) != null) {
            this.checkOnClickListener(((View)v5_3), arg4);
        }

        return ((View)v5_3);
    }

    private View createViewByPrefix(Context arg2, String arg3, String arg4) {
        Object v0 = AppCompatViewInflater.sConstructorMap.get(arg3);
        if(v0 == null) {
            try {
                ClassLoader v2 = arg2.getClassLoader();
                arg4 = arg4 != null ? arg4 + arg3 : arg3;
                Constructor v0_2 = v2.loadClass(arg4).asSubclass(View.class).getConstructor(AppCompatViewInflater.sConstructorSignature);
                AppCompatViewInflater.sConstructorMap.put(arg3, v0_2);
            label_19:
                v0_2.setAccessible(true);
                return v0_2.newInstance(this.mConstructorArgs);
            }
            catch(Exception ) {
                return null;
            }
        }

        goto label_19;
    }

    private View createViewFromTag(Context arg5, String arg6, AttributeSet arg7) {
        View v5_1;
        View v3;
        int v7;
        String v1 = null;
        if(arg6.equals("view")) {
            arg6 = arg7.getAttributeValue(v1, "class");
        }

        try {
            this.mConstructorArgs[0] = arg5;
            this.mConstructorArgs[1] = arg7;
            if(-1 != arg6.indexOf(46)) {
                goto label_36;
            }

            v7 = 0;
            while(true) {
            label_17:
                if(v7 >= AppCompatViewInflater.sClassPrefixList.length) {
                    goto label_31;
                }

                v3 = this.createViewByPrefix(arg5, arg6, AppCompatViewInflater.sClassPrefixList[v7]);
                if(v3 == null) {
                    goto label_29;
                }

                break;
            }
        }
        catch(Exception ) {
            goto label_48;
        }
        catch(Throwable v5) {
            goto label_44;
        }

        this.mConstructorArgs[0] = v1;
        this.mConstructorArgs[1] = v1;
        return v3;
    label_29:
        ++v7;
        goto label_17;
    label_31:
        this.mConstructorArgs[0] = v1;
        this.mConstructorArgs[1] = v1;
        return ((View)v1);
        try {
        label_36:
            v5_1 = this.createViewByPrefix(arg5, arg6, v1);
        }
        catch(Throwable v5) {
        label_44:
            this.mConstructorArgs[0] = v1;
            this.mConstructorArgs[1] = v1;
            throw v5;
        }
        catch(Exception ) {
        label_48:
            this.mConstructorArgs[0] = v1;
            this.mConstructorArgs[1] = v1;
            return ((View)v1);
        }

        this.mConstructorArgs[0] = v1;
        this.mConstructorArgs[1] = v1;
        return v5_1;
    }

    private static Context themifyContext(Context arg2, AttributeSet arg3, boolean arg4, boolean arg5) {
        d v2;
        TypedArray v3 = arg2.obtainStyledAttributes(arg3, j.View, 0, 0);
        int v4 = arg4 ? v3.getResourceId(j.View_android_theme, 0) : 0;
        if((arg5) && v4 == 0) {
            v4 = v3.getResourceId(j.View_theme, 0);
            if(v4 != 0) {
                Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }

        v3.recycle();
        if(v4 != 0 && (!(arg2 instanceof d) || arg2.a() != v4)) {
            v2 = new d(arg2, v4);
        }

        return ((Context)v2);
    }

    private void verifyNotNull(View arg3, String arg4) {
        if(arg3 != null) {
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append(this.getClass().getName());
        v0.append(" asked to inflate view for <");
        v0.append(arg4);
        v0.append(">, but returned null");
        throw new IllegalStateException(v0.toString());
    }
}

