package org.telegram.tgnet;

import java.io.File;

public class FileLoadOperation {
    private long address;
    private FileLoadOperationDelegate delegate;
    private boolean isForceRequest;
    private boolean started;

    public FileLoadOperation(int arg18, long arg19, long arg21, long arg23, int arg25, byte[] arg26, byte[] arg27, String arg28, int arg29, int arg30, File arg31, File arg32, FileLoadOperationDelegate arg33) {
        super();
        this.address = FileLoadOperation.native_createLoadOpetation(arg18, arg19, arg21, arg23, arg25, arg26, arg27, arg28, arg29, arg30, arg31.getAbsolutePath(), arg32.getAbsolutePath(), arg33);
        this.delegate = arg33;
    }

    public void cancel() {
        if(this.started) {
            if(this.address == 0) {
            }
            else {
                FileLoadOperation.native_cancelLoadOperation(this.address);
            }
        }
    }

    public boolean isForceRequest() {
        return this.isForceRequest;
    }

    public static native void native_cancelLoadOperation(long arg0) {
    }

    public static native long native_createLoadOpetation(int arg0, long arg1, long arg2, long arg3, int arg4, byte[] arg5, byte[] arg6, String arg7, int arg8, int arg9, String arg10, String arg11, Object arg12) {
    }

    public static native void native_startLoadOperation(long arg0) {
    }

    public void setForceRequest(boolean arg1) {
        this.isForceRequest = arg1;
    }

    public void start() {
        if(this.started) {
            return;
        }

        if(this.address == 0) {
            this.delegate.onFailed(0);
            return;
        }

        this.started = true;
        FileLoadOperation.native_startLoadOperation(this.address);
    }

    public boolean wasStarted() {
        return this.started;
    }
}

