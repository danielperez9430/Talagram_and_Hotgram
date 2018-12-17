package org.linphone.mediastream;

public class Factory {
    private long mNativePtr;

    private Factory(long arg1) {
        super();
        this.mNativePtr = arg1;
    }

    private native void enableFilterFromName(long arg1, String arg2, boolean arg3) {
    }

    public void enableFilterFromName(String arg3, boolean arg4) {
        this.enableFilterFromName(this.mNativePtr, arg3, arg4);
    }

    private native boolean filterFromNameEnabled(long arg1, String arg2) {
    }

    public boolean filterFromNameEnabled(String arg3) {
        return this.filterFromNameEnabled(this.mNativePtr, arg3);
    }

    private native void setDeviceInfo(long arg1, String arg2, String arg3, String arg4, int arg5, int arg6, int arg7) {
    }

    public void setDeviceInfo(String arg10, String arg11, String arg12, int arg13, int arg14, int arg15) {
        this.setDeviceInfo(this.mNativePtr, arg10, arg11, arg12, arg13, arg14, arg15);
    }
}

