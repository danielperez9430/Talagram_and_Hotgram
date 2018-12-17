package android.support.g.b.a;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

public final class b {
    public interface a {
        boolean onCommitContent(c arg1, int arg2, Bundle arg3);
    }

    public static InputConnection a(InputConnection arg3, EditorInfo arg4, a arg5) {
        if(arg3 != null) {
            if(arg4 != null) {
                if(arg5 != null) {
                    if(Build$VERSION.SDK_INT >= 25) {
                        return new InputConnectionWrapper(arg3, false, arg5) {
                            public boolean commitContent(InputContentInfo arg3, int arg4, Bundle arg5) {
                                if(this.a.onCommitContent(c.a(arg3), arg4, arg5)) {
                                    return 1;
                                }

                                return super.commitContent(arg3, arg4, arg5);
                            }
                        };
                    }

                    if(android.support.g.b.a.a.a(arg4).length == 0) {
                        return arg3;
                    }

                    return new InputConnectionWrapper(arg3, false, arg5) {
                        public boolean performPrivateCommand(String arg2, Bundle arg3) {
                            if(b.a(arg2, arg3, this.a)) {
                                return 1;
                            }

                            return super.performPrivateCommand(arg2, arg3);
                        }
                    };
                }

                throw new IllegalArgumentException("onCommitContentListener must be non-null");
            }

            throw new IllegalArgumentException("editorInfo must be non-null");
        }

        throw new IllegalArgumentException("inputConnection must be non-null");
    }

    static boolean a(String arg7, Bundle arg8, a arg9) {
        boolean v8_1;
        String v2;
        Parcelable v1;
        if(!TextUtils.equals("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", ((CharSequence)arg7))) {
            return 0;
        }

        if(arg8 == null) {
            return 0;
        }

        Bundle v7 = null;
        try {
            v1 = arg8.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER");
        }
        catch(Throwable v8) {
            goto label_29;
        }

        try {
            v2 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
            goto label_11;
        }
        catch(Throwable v8) {
        }
        catch(Throwable v8) {
        label_29:
            v1 = ((Parcelable)v7);
            goto label_30;
            try {
            label_11:
                v8_1 = arg9.onCommitContent(new c(arg8.getParcelable(v2), arg8.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION"), arg8.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI")), arg8.getInt("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS"), arg8.getParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS"));
                if(v1 == null) {
                    return v8_1;
                }

                goto label_24;
            }
            catch(Throwable v8) {
            }
        }

    label_30:
        if(v1 != null) {
            ((ResultReceiver)v1).send(0, v7);
        }

        throw v8;
    label_24:
        ((ResultReceiver)v1).send(((int)v8_1), v7);
        return v8_1;
    }
}

