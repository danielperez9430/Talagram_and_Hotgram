package com.google.android.gms.common.internal;

import android.content.ContentValues;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.util.ThreadUtils;

public final class Preconditions {
    private Preconditions() {
        super();
        throw new AssertionError("Uninstantiable");
    }

    public static void checkArgument(boolean arg0, Object arg1) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException(String.valueOf(arg1));
    }

    public static void checkArgument(boolean arg0) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void checkArgument(boolean arg0, String arg1, Object[] arg2) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException(String.format(arg1, arg2));
    }

    public static int checkElementIndex(int arg1, int arg2) {
        return Preconditions.checkElementIndex(arg1, arg2, "index");
    }

    public static int checkElementIndex(int arg5, int arg6, String arg7) {
        String v5;
        if(arg5 >= 0) {
            if(arg5 >= arg6) {
            }
            else {
                return arg5;
            }
        }

        int v3 = 2;
        if(arg5 < 0) {
            Object[] v6 = new Object[v3];
            v6[0] = arg7;
            v6[1] = Integer.valueOf(arg5);
            v5 = Preconditions.format("%s (%s) must not be negative", v6);
        }
        else if(arg6 < 0) {
            StringBuilder v0 = new StringBuilder(26);
            v0.append("negative size: ");
            v0.append(arg6);
            throw new IllegalArgumentException(v0.toString());
        }
        else {
            Object[] v4 = new Object[3];
            v4[0] = arg7;
            v4[1] = Integer.valueOf(arg5);
            v4[v3] = Integer.valueOf(arg6);
            v5 = Preconditions.format("%s (%s) must be less than size (%s)", v4);
        }

        throw new IndexOutOfBoundsException(v5);
    }

    public static void checkHandlerThread(Handler arg1) {
        if(Looper.myLooper() == arg1.getLooper()) {
            return;
        }

        throw new IllegalStateException("Must be called on the handler thread");
    }

    public static void checkMainThread(String arg1) {
        if(ThreadUtils.isMainThread()) {
            return;
        }

        throw new IllegalStateException(arg1);
    }

    public static String checkNotEmpty(String arg1, Object arg2) {
        if(!TextUtils.isEmpty(((CharSequence)arg1))) {
            return arg1;
        }

        throw new IllegalArgumentException(String.valueOf(arg2));
    }

    public static String checkNotEmpty(String arg1) {
        if(!TextUtils.isEmpty(((CharSequence)arg1))) {
            return arg1;
        }

        throw new IllegalArgumentException("Given String is empty or null");
    }

    public static void checkNotMainThread(String arg1) {
        if(!ThreadUtils.isMainThread()) {
            return;
        }

        throw new IllegalStateException(arg1);
    }

    public static void checkNotMainThread() {
        Preconditions.checkNotMainThread("Must not be called on the main application thread");
    }

    public static Object checkNotNull(Object arg1) {
        if(arg1 != null) {
            return arg1;
        }

        throw new NullPointerException("null reference");
    }

    public static Object checkNotNull(Object arg0, Object arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(String.valueOf(arg1));
    }

    public static void checkNotNullIfPresent(String arg1, ContentValues arg2) {
        if(arg2.containsKey(arg1)) {
            if(arg2.get(arg1) != null) {
            }
            else {
                throw new IllegalArgumentException(String.valueOf(arg1).concat(" cannot be set to null"));
            }
        }
    }

    public static int checkNotZero(int arg1) {
        if(arg1 != 0) {
            return arg1;
        }

        throw new IllegalArgumentException("Given Integer is zero");
    }

    public static int checkNotZero(int arg0, Object arg1) {
        if(arg0 != 0) {
            return arg0;
        }

        throw new IllegalArgumentException(String.valueOf(arg1));
    }

    public static long checkNotZero(long arg3) {
        if(arg3 != 0) {
            return arg3;
        }

        throw new IllegalArgumentException("Given Long is zero");
    }

    public static long checkNotZero(long arg3, Object arg5) {
        if(arg3 != 0) {
            return arg3;
        }

        throw new IllegalArgumentException(String.valueOf(arg5));
    }

    public static int checkPositionIndex(int arg1, int arg2) {
        return Preconditions.checkPositionIndex(arg1, arg2, "index");
    }

    public static int checkPositionIndex(int arg1, int arg2, String arg3) {
        if(arg1 >= 0 && arg1 <= arg2) {
            return arg1;
        }

        throw new IndexOutOfBoundsException(Preconditions.zza(arg1, arg2, arg3));
    }

    public static void checkPositionIndexes(int arg2, int arg3, int arg4) {
        String v2;
        if(arg2 >= 0 && arg3 >= arg2) {
            if(arg3 > arg4) {
            }
            else {
                return;
            }
        }

        if(arg2 < 0 || arg2 > arg4) {
            v2 = Preconditions.zza(arg2, arg4, "start index");
        }
        else {
            if(arg3 >= 0) {
                if(arg3 > arg4) {
                }
                else {
                    v2 = Preconditions.format("end index (%s) must not be less than start index (%s)", new Object[]{Integer.valueOf(arg3), Integer.valueOf(arg2)});
                    goto label_27;
                }
            }

            v2 = Preconditions.zza(arg3, arg4, "end index");
        }

    label_27:
        throw new IndexOutOfBoundsException(v2);
    }

    public static void checkState(boolean arg0) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException();
    }

    public static void checkState(boolean arg0, Object arg1) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException(String.valueOf(arg1));
    }

    public static void checkState(boolean arg0, String arg1, Object[] arg2) {
        if(arg0) {
            return;
        }

        throw new IllegalStateException(String.format(arg1, arg2));
    }

    public static String checkTag(String arg2) {
        if(!TextUtils.isEmpty(((CharSequence)arg2))) {
            if(arg2.length() <= 23) {
                return arg2;
            }

            throw new IllegalArgumentException("Tag must not be greater than 23 chars.");
        }

        throw new IllegalArgumentException("Tag must not be empty or null");
    }

    private static String format(String arg6, Object[] arg7) {
        StringBuilder v0 = new StringBuilder(arg6.length() + arg7.length * 16);
        int v1 = 0;
        int v2 = 0;
        while(v1 < arg7.length) {
            int v3 = arg6.indexOf("%s", v2);
            if(v3 == -1) {
                break;
            }

            v0.append(arg6.substring(v2, v3));
            v0.append(arg7[v1]);
            v2 = v3 + 2;
            ++v1;
        }

        v0.append(arg6.substring(v2));
        if(v1 < arg7.length) {
            v0.append(" [");
            int v6 = v1 + 1;
            v0.append(arg7[v1]);
            while(v6 < arg7.length) {
                v0.append(", ");
                v0.append(arg7[v6]);
                ++v6;
            }

            v0.append("]");
        }

        return v0.toString();
    }

    private static String zza(int arg5, int arg6, String arg7) {
        int v2 = 2;
        if(arg5 < 0) {
            Object[] v2_1 = new Object[v2];
            v2_1[0] = arg7;
            v2_1[1] = Integer.valueOf(arg5);
            return Preconditions.format("%s (%s) must not be negative", v2_1);
        }

        if(arg6 >= 0) {
            Object[] v4 = new Object[3];
            v4[0] = arg7;
            v4[1] = Integer.valueOf(arg5);
            v4[v2] = Integer.valueOf(arg6);
            return Preconditions.format("%s (%s) must not be greater than size (%s)", v4);
        }

        StringBuilder v0 = new StringBuilder(26);
        v0.append("negative size: ");
        v0.append(arg6);
        throw new IllegalArgumentException(v0.toString());
    }
}

