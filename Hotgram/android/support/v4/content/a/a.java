package android.support.v4.content.a;

import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.a.a$d;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class a {
    private static int a(int arg1, float arg2) {
        return arg1 & 16777215 | Math.round((((float)Color.alpha(arg1))) * arg2) << 24;
    }

    public static ColorStateList a(Resources arg4, XmlPullParser arg5, Resources$Theme arg6) {
        AttributeSet v0 = Xml.asAttributeSet(arg5);
        while(true) {
            int v1 = arg5.next();
            int v2 = 2;
            if(v1 != v2 && v1 != 1) {
                continue;
            }

            break;
        }

        if(v1 == v2) {
            return a.a(arg4, arg5, v0, arg6);
        }

        throw new XmlPullParserException("No start tag found");
    }

    public static ColorStateList a(Resources arg2, XmlPullParser arg3, AttributeSet arg4, Resources$Theme arg5) {
        String v0 = arg3.getName();
        if(v0.equals("selector")) {
            return a.b(arg2, arg3, arg4, arg5);
        }

        StringBuilder v4 = new StringBuilder();
        v4.append(arg3.getPositionDescription());
        v4.append(": invalid color state list tag ");
        v4.append(v0);
        throw new XmlPullParserException(v4.toString());
    }

    private static TypedArray a(Resources arg0, Resources$Theme arg1, AttributeSet arg2, int[] arg3) {
        TypedArray v0 = arg1 == null ? arg0.obtainAttributes(arg2, arg3) : arg1.obtainStyledAttributes(arg2, arg3, 0, 0);
        return v0;
    }

    private static ColorStateList b(Resources arg17, XmlPullParser arg18, AttributeSet arg19, Resources$Theme arg20) {
        int v12;
        AttributeSet v0 = arg19;
        int v2 = 1;
        int v1 = arg18.getDepth() + 1;
        int[][] v3 = new int[20][];
        int[] v4 = new int[v3.length];
        int v6 = 0;
        while(true) {
            int v7 = arg18.next();
            if(v7 != v2) {
                int v8 = arg18.getDepth();
                if(v8 < v1 && v7 == 3) {
                    break;
                }

                if(v7 == 2 && v8 <= v1) {
                    if(!arg18.getName().equals("item")) {
                    }
                    else {
                        TypedArray v7_1 = a.a(arg17, arg20, v0, d.ColorStateListItem);
                        int v10 = v7_1.getColor(d.ColorStateListItem_android_color, -65281);
                        float v11 = 1f;
                        if(v7_1.hasValue(d.ColorStateListItem_android_alpha)) {
                            v12 = d.ColorStateListItem_android_alpha;
                            goto label_36;
                        }
                        else if(v7_1.hasValue(d.ColorStateListItem_alpha)) {
                            v12 = d.ColorStateListItem_alpha;
                        label_36:
                            v11 = v7_1.getFloat(v12, v11);
                        }

                        v7_1.recycle();
                        v7 = arg19.getAttributeCount();
                        int[] v12_1 = new int[v7];
                        int v13 = 0;
                        int v14 = 0;
                        while(v13 < v7) {
                            int v15 = v0.getAttributeNameResource(v13);
                            if(v15 != 16843173 && v15 != 16843551 && v15 != android.support.a.a$a.alpha) {
                                v2 = v14 + 1;
                                if(v0.getAttributeBooleanValue(v13, false)) {
                                }
                                else {
                                    v15 = -v15;
                                }

                                v12_1[v14] = v15;
                                v14 = v2;
                            }

                            ++v13;
                        }

                        int[] v2_1 = StateSet.trimStateSet(v12_1, v14);
                        v4 = e.a(v4, v6, a.a(v10, v11));
                        Object[] v3_1 = e.a(((Object[])v3), v6, v2_1);
                        ++v6;
                    }
                }

                v2 = 1;
                continue;
            }

            break;
        }

        int[] v0_1 = new int[v6];
        int[][] v1_1 = new int[v6][];
        System.arraycopy(v4, 0, v0_1, 0, v6);
        System.arraycopy(v3, 0, v1_1, 0, v6);
        return new ColorStateList(v1_1, v0_1);
    }
}

