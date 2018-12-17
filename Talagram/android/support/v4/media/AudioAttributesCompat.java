package android.support.v4.media;

import android.util.SparseIntArray;
import androidx.versionedparcelable.c;

public class AudioAttributesCompat implements c {
    a a;
    private static final SparseIntArray b;
    private static final int[] c;

    static {
        AudioAttributesCompat.b = new SparseIntArray();
        AudioAttributesCompat.b.put(5, 1);
        AudioAttributesCompat.b.put(6, 2);
        AudioAttributesCompat.b.put(7, 2);
        AudioAttributesCompat.b.put(8, 1);
        AudioAttributesCompat.b.put(9, 1);
        AudioAttributesCompat.b.put(10, 1);
        AudioAttributesCompat.c = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    }

    AudioAttributesCompat() {
        super();
    }

    static int a(boolean arg3, int arg4, int arg5) {
        int v1 = 1;
        if((arg4 & 1) == 1) {
            if(arg3) {
            }
            else {
                v1 = 7;
            }

            return v1;
        }

        int v0 = 4;
        int v2 = 0;
        if((arg4 & v0) == v0) {
            if(arg3) {
            }
            else {
                v2 = 6;
            }

            return v2;
        }

        arg4 = 3;
        switch(arg5) {
            case 0: {
                goto label_32;
            }
            case 2: {
                return v2;
            }
            case 3: {
                goto label_27;
            }
            case 4: {
                return v0;
            }
            case 6: {
                return 2;
            }
            case 5: 
            case 7: 
            case 8: 
            case 9: 
            case 10: {
                return 5;
            }
            case 11: {
                return 10;
            }
            case 13: {
                return 1;
            }
            case 1: 
            case 12: 
            case 14: 
            case 16: {
                return arg4;
            }
        }

        if(!arg3) {
            return arg4;
        }

        StringBuilder v4 = new StringBuilder();
        v4.append("Unknown usage value ");
        v4.append(arg5);
        v4.append(" in audio attributes");
        throw new IllegalArgumentException(v4.toString());
        return 1;
    label_27:
        if(arg3) {
        }
        else {
            v2 = 8;
        }

        return v2;
    label_32:
        if(arg3) {
            arg4 = -2147483648;
        }

        return arg4;
    }

    static String a(int arg2) {
        switch(arg2) {
            case 0: {
                return "USAGE_UNKNOWN";
            }
            case 1: {
                return "USAGE_MEDIA";
            }
            case 2: {
                return "USAGE_VOICE_COMMUNICATION";
            }
            case 3: {
                return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
            }
            case 4: {
                return "USAGE_ALARM";
            }
            case 5: {
                return "USAGE_NOTIFICATION";
            }
            case 6: {
                return "USAGE_NOTIFICATION_RINGTONE";
            }
            case 7: {
                return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
            }
            case 8: {
                return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
            }
            case 9: {
                return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
            }
            case 10: {
                return "USAGE_NOTIFICATION_EVENT";
            }
            case 11: {
                return "USAGE_ASSISTANCE_ACCESSIBILITY";
            }
            case 12: {
                return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
            }
            case 13: {
                return "USAGE_ASSISTANCE_SONIFICATION";
            }
            case 14: {
                return "USAGE_GAME";
            }
            case 16: {
                return "USAGE_ASSISTANT";
            }
        }

        return "unknown usage " + arg2;
    }

    public boolean equals(Object arg3) {
        boolean v1 = false;
        if(!(arg3 instanceof AudioAttributesCompat)) {
            return 0;
        }

        if(this.a == null) {
            if(((AudioAttributesCompat)arg3).a == null) {
                v1 = true;
            }

            return v1;
        }

        return this.a.equals(((AudioAttributesCompat)arg3).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }
}

