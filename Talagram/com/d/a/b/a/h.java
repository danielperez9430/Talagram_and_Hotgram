package com.d.a.b.a;

import android.widget.ImageView$ScaleType;
import android.widget.ImageView;

public enum h {
    class com.d.a.b.a.h$1 {
        static {
            com.d.a.b.a.h$1.a = new int[ImageView$ScaleType.values().length];
            try {
                com.d.a.b.a.h$1.a[ImageView$ScaleType.FIT_CENTER.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.d.a.b.a.h$1.a[ImageView$ScaleType.FIT_XY.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.d.a.b.a.h$1.a[ImageView$ScaleType.FIT_START.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.d.a.b.a.h$1.a[ImageView$ScaleType.FIT_END.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.d.a.b.a.h$1.a[ImageView$ScaleType.CENTER_INSIDE.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.d.a.b.a.h$1.a[ImageView$ScaleType.MATRIX.ordinal()] = 6;
                                    goto label_34;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_34:
                                        com.d.a.b.a.h$1.a[ImageView$ScaleType.CENTER.ordinal()] = 7;
                                        goto label_39;
                                    }
                                    catch(NoSuchFieldError ) {
                                        try {
                                        label_39:
                                            com.d.a.b.a.h$1.a[ImageView$ScaleType.CENTER_CROP.ordinal()] = 8;
                                            return;
                                        }
                                        catch(NoSuchFieldError ) {
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static final enum h a;
    public static final enum h b;

    static {
        h.a = new h("FIT_INSIDE", 0);
        h.b = new h("CROP", 1);
        h.c = new h[]{h.a, h.b};
    }

    private h(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static h a(ImageView arg1) {
        switch(com.d.a.b.a.h$1.a[arg1.getScaleType().ordinal()]) {
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                goto label_7;
            }
        }

        return h.b;
    label_7:
        return h.a;
    }

    public static h valueOf(String arg1) {
        return Enum.valueOf(h.class, arg1);
    }

    public static h[] values() {
        // Method was not decompiled
    }
}

