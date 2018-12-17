package org.telegram.messenger;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.io.File;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.ArrayList;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$TL_boolTrue;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_inputEncryptedFileBigUploaded;
import org.telegram.tgnet.TLRPC$TL_inputEncryptedFileUploaded;
import org.telegram.tgnet.TLRPC$TL_inputFile;
import org.telegram.tgnet.TLRPC$TL_inputFileBig;
import org.telegram.tgnet.TLRPC$TL_upload_saveBigFilePart;
import org.telegram.tgnet.TLRPC$TL_upload_saveFilePart;
import org.telegram.tgnet.WriteToSocketDelegate;

public class FileUploadOperation {
    public interface FileUploadOperationDelegate {
        void didChangedUploadProgress(FileUploadOperation arg1, float arg2);

        void didFailedUploadingFile(FileUploadOperation arg1);

        void didFinishUploadingFile(FileUploadOperation arg1, InputFile arg2, InputEncryptedFile arg3, byte[] arg4, byte[] arg5);
    }

    class UploadCachedResult {
        private long bytesOffset;
        private byte[] iv;

        UploadCachedResult(FileUploadOperation arg1, org.telegram.messenger.FileUploadOperation$1 arg2) {
            this(arg1);
        }

        private UploadCachedResult(FileUploadOperation arg1) {
            FileUploadOperation.this = arg1;
            super();
        }

        static long access$3600(UploadCachedResult arg2) {
            return arg2.bytesOffset;
        }

        static long access$3602(UploadCachedResult arg0, long arg1) {
            arg0.bytesOffset = arg1;
            return arg1;
        }

        static byte[] access$3700(UploadCachedResult arg0) {
            return arg0.iv;
        }

        static byte[] access$3702(UploadCachedResult arg0, byte[] arg1) {
            arg0.iv = arg1;
            return arg1;
        }
    }

    private long availableSize;
    private SparseArray cachedResults;
    private int currentAccount;
    private long currentFileId;
    private int currentPartNum;
    private int currentType;
    private int currentUploadRequetsCount;
    private FileUploadOperationDelegate delegate;
    private int estimatedSize;
    private String fileKey;
    private int fingerprint;
    private ArrayList freeRequestIvs;
    private static final int initialRequestsCount = 8;
    private static final int initialRequestsSlowNetworkCount = 1;
    private boolean isBigFile;
    private boolean isEncrypted;
    private boolean isLastPart;
    private byte[] iv;
    private byte[] ivChange;
    private byte[] key;
    private int lastSavedPartNum;
    private int maxRequestsCount;
    private static final int maxUploadingKBytes = 2048;
    private static final int maxUploadingSlowNetworkKBytes = 32;
    private static final int minUploadChunkSize = 128;
    private static final int minUploadChunkSlowNetworkSize = 32;
    private boolean nextPartFirst;
    private int operationGuid;
    private SharedPreferences preferences;
    private byte[] readBuffer;
    private long readBytesCount;
    private int requestNum;
    private SparseIntArray requestTokens;
    private int saveInfoTimes;
    private boolean slowNetwork;
    private boolean started;
    private int state;
    private RandomAccessFile stream;
    private long totalFileSize;
    private int totalPartsCount;
    private int uploadChunkSize;
    private boolean uploadFirstPartLater;
    private int uploadStartTime;
    private long uploadedBytesCount;
    private String uploadingFilePath;

    public FileUploadOperation(int arg2, String arg3, boolean arg4, int arg5, int arg6) {
        super();
        this.uploadChunkSize = 65536;
        this.requestTokens = new SparseIntArray();
        this.cachedResults = new SparseArray();
        this.currentAccount = arg2;
        this.uploadingFilePath = arg3;
        this.isEncrypted = arg4;
        this.estimatedSize = arg5;
        this.currentType = arg6;
        boolean v2 = arg5 == 0 || (this.isEncrypted) ? false : true;
        this.uploadFirstPartLater = v2;
    }

    static SharedPreferences access$000(FileUploadOperation arg0) {
        return arg0.preferences;
    }

    static SharedPreferences access$002(FileUploadOperation arg0, SharedPreferences arg1) {
        arg0.preferences = arg1;
        return arg1;
    }

    static boolean access$100(FileUploadOperation arg0) {
        return arg0.slowNetwork;
    }

    static long access$1002(FileUploadOperation arg0, long arg1) {
        arg0.readBytesCount = arg1;
        return arg1;
    }

    static boolean access$102(FileUploadOperation arg0, boolean arg1) {
        arg0.slowNetwork = arg1;
        return arg1;
    }

    static long access$1100(FileUploadOperation arg2) {
        return arg2.uploadedBytesCount;
    }

    static long access$1102(FileUploadOperation arg0, long arg1) {
        arg0.uploadedBytesCount = arg1;
        return arg1;
    }

    static int access$1200(FileUploadOperation arg0) {
        return arg0.saveInfoTimes;
    }

    static int access$1202(FileUploadOperation arg0, int arg1) {
        arg0.saveInfoTimes = arg1;
        return arg1;
    }

    static int access$1208(FileUploadOperation arg2) {
        int v0 = arg2.saveInfoTimes;
        arg2.saveInfoTimes = v0 + 1;
        return v0;
    }

    static byte[] access$1300(FileUploadOperation arg0) {
        return arg0.key;
    }

    static byte[] access$1302(FileUploadOperation arg0, byte[] arg1) {
        arg0.key = arg1;
        return arg1;
    }

    static byte[] access$1400(FileUploadOperation arg0) {
        return arg0.iv;
    }

    static byte[] access$1402(FileUploadOperation arg0, byte[] arg1) {
        arg0.iv = arg1;
        return arg1;
    }

    static byte[] access$1502(FileUploadOperation arg0, byte[] arg1) {
        arg0.ivChange = arg1;
        return arg1;
    }

    static int access$1600(FileUploadOperation arg0) {
        return arg0.currentUploadRequetsCount;
    }

    static int access$1602(FileUploadOperation arg0, int arg1) {
        arg0.currentUploadRequetsCount = arg1;
        return arg1;
    }

    static int access$1610(FileUploadOperation arg2) {
        int v0 = arg2.currentUploadRequetsCount;
        arg2.currentUploadRequetsCount = v0 - 1;
        return v0;
    }

    static int access$1700(FileUploadOperation arg0) {
        return arg0.lastSavedPartNum;
    }

    static int access$1702(FileUploadOperation arg0, int arg1) {
        arg0.lastSavedPartNum = arg1;
        return arg1;
    }

    static int access$1708(FileUploadOperation arg2) {
        int v0 = arg2.lastSavedPartNum;
        arg2.lastSavedPartNum = v0 + 1;
        return v0;
    }

    static boolean access$1800(FileUploadOperation arg0) {
        return arg0.uploadFirstPartLater;
    }

    static boolean access$1802(FileUploadOperation arg0, boolean arg1) {
        arg0.uploadFirstPartLater = arg1;
        return arg1;
    }

    static SparseArray access$1900(FileUploadOperation arg0) {
        return arg0.cachedResults;
    }

    static void access$200(FileUploadOperation arg0) {
        arg0.startUploadRequest();
    }

    static int access$2000(FileUploadOperation arg0) {
        return arg0.operationGuid;
    }

    static int access$2008(FileUploadOperation arg2) {
        int v0 = arg2.operationGuid;
        arg2.operationGuid = v0 + 1;
        return v0;
    }

    static int access$2100(FileUploadOperation arg0) {
        return arg0.estimatedSize;
    }

    static int access$2102(FileUploadOperation arg0, int arg1) {
        arg0.estimatedSize = arg1;
        return arg1;
    }

    static long access$2200(FileUploadOperation arg2) {
        return arg2.totalFileSize;
    }

    static long access$2202(FileUploadOperation arg0, long arg1) {
        arg0.totalFileSize = arg1;
        return arg1;
    }

    static void access$2300(FileUploadOperation arg0) {
        arg0.calcTotalPartsCount();
    }

    static boolean access$2400(FileUploadOperation arg0) {
        return arg0.started;
    }

    static void access$2500(FileUploadOperation arg0) {
        arg0.storeFileUploadInfo();
    }

    static long access$2600(FileUploadOperation arg2) {
        return arg2.availableSize;
    }

    static long access$2602(FileUploadOperation arg0, long arg1) {
        arg0.availableSize = arg1;
        return arg1;
    }

    static int access$2700(FileUploadOperation arg0) {
        return arg0.maxRequestsCount;
    }

    static int access$2800(FileUploadOperation arg0) {
        return arg0.currentType;
    }

    static ArrayList access$2900(FileUploadOperation arg0) {
        return arg0.freeRequestIvs;
    }

    static SparseIntArray access$300(FileUploadOperation arg0) {
        return arg0.requestTokens;
    }

    static int access$3000(FileUploadOperation arg0) {
        return arg0.state;
    }

    static int access$3002(FileUploadOperation arg0, int arg1) {
        arg0.state = arg1;
        return arg1;
    }

    static FileUploadOperationDelegate access$3100(FileUploadOperation arg0) {
        return arg0.delegate;
    }

    static boolean access$3200(FileUploadOperation arg0) {
        return arg0.isBigFile;
    }

    static long access$3300(FileUploadOperation arg2) {
        return arg2.currentFileId;
    }

    static String access$3400(FileUploadOperation arg0) {
        return arg0.uploadingFilePath;
    }

    static int access$3500(FileUploadOperation arg0) {
        return arg0.fingerprint;
    }

    static String access$3800(FileUploadOperation arg0) {
        return arg0.fileKey;
    }

    static boolean access$3900(FileUploadOperation arg0) {
        return arg0.isEncrypted;
    }

    static int access$400(FileUploadOperation arg0) {
        return arg0.currentAccount;
    }

    static void access$500(FileUploadOperation arg0) {
        arg0.cleanup();
    }

    static boolean access$600(FileUploadOperation arg0) {
        return arg0.isLastPart;
    }

    static boolean access$602(FileUploadOperation arg0, boolean arg1) {
        arg0.isLastPart = arg1;
        return arg1;
    }

    static boolean access$700(FileUploadOperation arg0) {
        return arg0.nextPartFirst;
    }

    static boolean access$702(FileUploadOperation arg0, boolean arg1) {
        arg0.nextPartFirst = arg1;
        return arg1;
    }

    static int access$802(FileUploadOperation arg0, int arg1) {
        arg0.requestNum = arg1;
        return arg1;
    }

    static int access$900(FileUploadOperation arg0) {
        return arg0.currentPartNum;
    }

    static int access$902(FileUploadOperation arg0, int arg1) {
        arg0.currentPartNum = arg1;
        return arg1;
    }

    private void calcTotalPartsCount() {
        int v0;
        long v5;
        long v3;
        long v1 = 1;
        if(this.uploadFirstPartLater) {
            if(this.isBigFile) {
                v3 = this.totalFileSize;
                v5 = ((long)this.uploadChunkSize);
            }
            else {
                v3 = this.totalFileSize;
                v5 = 1024;
            }

            v0 = (((int)(v3 - v5 + (((long)this.uploadChunkSize)) - v1))) / this.uploadChunkSize + 1;
        }
        else {
            v0 = (((int)(this.totalFileSize + (((long)this.uploadChunkSize)) - v1))) / this.uploadChunkSize;
        }

        this.totalPartsCount = v0;
    }

    public void cancel() {
        if(this.state == 3) {
            return;
        }

        this.state = 2;
        Utilities.stageQueue.postRunnable(new Runnable() {
            public void run() {
                int v0;
                for(v0 = 0; v0 < FileUploadOperation.this.requestTokens.size(); ++v0) {
                    ConnectionsManager.getInstance(FileUploadOperation.this.currentAccount).cancelRequest(FileUploadOperation.this.requestTokens.valueAt(v0), true);
                }
            }
        });
        this.delegate.didFailedUploadingFile(this);
        this.cleanup();
    }

    protected void checkNewDataAvailable(long arg9, long arg11) {
        Utilities.stageQueue.postRunnable(new Runnable(arg11, arg9) {
            public void run() {
                if(FileUploadOperation.this.estimatedSize != 0 && this.val$finalSize != 0) {
                    FileUploadOperation.this.estimatedSize = 0;
                    FileUploadOperation.this.totalFileSize = this.val$finalSize;
                    FileUploadOperation.this.calcTotalPartsCount();
                    if(!FileUploadOperation.this.uploadFirstPartLater && (FileUploadOperation.this.started)) {
                        FileUploadOperation.this.storeFileUploadInfo();
                    }
                }

                FileUploadOperation.this.availableSize = this.val$newAvailableSize;
                if(FileUploadOperation.this.currentUploadRequetsCount < FileUploadOperation.this.maxRequestsCount) {
                    FileUploadOperation.this.startUploadRequest();
                }
            }
        });
    }

    private void cleanup() {
        if(this.preferences == null) {
            this.preferences = ApplicationLoader.applicationContext.getSharedPreferences("uploadinfo", 0);
        }

        SharedPreferences$Editor v0 = this.preferences.edit();
        StringBuilder v1 = new StringBuilder();
        v1.append(this.fileKey);
        v1.append("_time");
        v0 = v0.remove(v1.toString());
        v1 = new StringBuilder();
        v1.append(this.fileKey);
        v1.append("_size");
        v0 = v0.remove(v1.toString());
        v1 = new StringBuilder();
        v1.append(this.fileKey);
        v1.append("_uploaded");
        v0 = v0.remove(v1.toString());
        v1 = new StringBuilder();
        v1.append(this.fileKey);
        v1.append("_id");
        v0 = v0.remove(v1.toString());
        v1 = new StringBuilder();
        v1.append(this.fileKey);
        v1.append("_iv");
        v0 = v0.remove(v1.toString());
        v1 = new StringBuilder();
        v1.append(this.fileKey);
        v1.append("_key");
        v0 = v0.remove(v1.toString());
        v1 = new StringBuilder();
        v1.append(this.fileKey);
        v1.append("_ivc");
        v0.remove(v1.toString()).commit();
        try {
            if(this.stream == null) {
                return;
            }

            this.stream.close();
            this.stream = null;
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public long getTotalFileSize() {
        return this.totalFileSize;
    }

    protected void onNetworkChanged(boolean arg3) {
        if(this.state != 1) {
            return;
        }

        Utilities.stageQueue.postRunnable(new Runnable(arg3) {
            public void run() {
                int v3;
                if(FileUploadOperation.this.slowNetwork != this.val$slow) {
                    FileUploadOperation.this.slowNetwork = this.val$slow;
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("network changed to slow = " + FileUploadOperation.this.slowNetwork);
                    }

                    int v0_1 = 0;
                    int v1;
                    for(v1 = 0; true; ++v1) {
                        v3 = 1;
                        if(v1 >= FileUploadOperation.this.requestTokens.size()) {
                            break;
                        }

                        ConnectionsManager.getInstance(FileUploadOperation.this.currentAccount).cancelRequest(FileUploadOperation.this.requestTokens.valueAt(v1), true);
                    }

                    FileUploadOperation.this.requestTokens.clear();
                    FileUploadOperation.this.cleanup();
                    FileUploadOperation.this.isLastPart = false;
                    FileUploadOperation.this.nextPartFirst = false;
                    FileUploadOperation.this.requestNum = 0;
                    FileUploadOperation.this.currentPartNum = 0;
                    FileUploadOperation.this.readBytesCount = 0;
                    FileUploadOperation.this.uploadedBytesCount = 0;
                    FileUploadOperation.this.saveInfoTimes = 0;
                    FileUploadOperation.this.key = null;
                    FileUploadOperation.this.iv = null;
                    FileUploadOperation.this.ivChange = null;
                    FileUploadOperation.this.currentUploadRequetsCount = 0;
                    FileUploadOperation.this.lastSavedPartNum = 0;
                    FileUploadOperation.this.uploadFirstPartLater = false;
                    FileUploadOperation.this.cachedResults.clear();
                    FileUploadOperation.access$2008(FileUploadOperation.this);
                    if(FileUploadOperation.this.slowNetwork) {
                    }
                    else {
                        v3 = 8;
                    }

                    while(v0_1 < v3) {
                        FileUploadOperation.this.startUploadRequest();
                        ++v0_1;
                    }
                }
            }
        });
    }

    public void setDelegate(FileUploadOperationDelegate arg1) {
        this.delegate = arg1;
    }

    public void start() {
        if(this.state != 0) {
            return;
        }

        this.state = 1;
        Utilities.stageQueue.postRunnable(new Runnable() {
            public void run() {
                int v3 = 0;
                FileUploadOperation.this.preferences = ApplicationLoader.applicationContext.getSharedPreferences("uploadinfo", 0);
                FileUploadOperation.this.slowNetwork = ConnectionsManager.isConnectionSlow();
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("start upload on slow network = " + FileUploadOperation.this.slowNetwork);
                }

                int v0_1 = FileUploadOperation.this.slowNetwork ? 1 : 8;
                while(v3 < v0_1) {
                    FileUploadOperation.this.startUploadRequest();
                    ++v3;
                }
            }
        });
    }

    private void startUploadRequest() {
        TL_upload_saveFilePart v22_1;
        Object v6_1;
        int v2;
        int v21;
        long v10_2;
        int v9_1;
        int v7;
        long v5;
        String v4;
        int v3;
        FileUploadOperation v12 = this;
        if(v12.state != 1) {
            return;
        }

        try {
            v12.started = true;
            v3 = 1024;
            v4 = null;
            v5 = 0;
            v7 = 32;
            if(v12.stream != null) {
                goto label_391;
            }

            File v0_1 = new File(v12.uploadingFilePath);
            if(AndroidUtilities.isInternalUri(Uri.fromFile(v0_1))) {
                goto label_387;
            }

            v12.stream = new RandomAccessFile(v0_1, "r");
            v12.totalFileSize = v12.estimatedSize != 0 ? ((long)v12.estimatedSize) : v0_1.length();
            if(v12.totalFileSize > 10485760) {
                v12.isBigFile = true;
            }

            long v9 = v12.slowNetwork ? 32 : 128;
            v12.uploadChunkSize = ((int)Math.max(v9, (v12.totalFileSize + 3072000 - 1) / 3072000));
            v9_1 = 64;
            if(v3 % v12.uploadChunkSize != 0) {
                int v0_2;
                for(v0_2 = 64; v12.uploadChunkSize > v0_2; v0_2 *= 2) {
                }

                v12.uploadChunkSize = v0_2;
            }

            v0_2 = v12.slowNetwork ? 32 : 2048;
            v12.maxRequestsCount = Math.max(1, v0_2 / v12.uploadChunkSize);
            if(v12.isEncrypted) {
                v12.freeRequestIvs = new ArrayList(v12.maxRequestsCount);
                for(v0_2 = 0; v0_2 < v12.maxRequestsCount; ++v0_2) {
                    v12.freeRequestIvs.add(new byte[v7]);
                }
            }

            v12.uploadChunkSize *= 1024;
            this.calcTotalPartsCount();
            v12.readBuffer = new byte[v12.uploadChunkSize];
            StringBuilder v0_3 = new StringBuilder();
            v0_3.append(v12.uploadingFilePath);
            String v10 = v12.isEncrypted ? "enc" : "";
            v0_3.append(v10);
            v12.fileKey = Utilities.MD5(v0_3.toString());
            SharedPreferences v0_4 = v12.preferences;
            StringBuilder v10_1 = new StringBuilder();
            v10_1.append(v12.fileKey);
            v10_1.append("_size");
            v10_2 = v0_4.getLong(v10_1.toString(), v5);
            v12.uploadStartTime = ((int)(System.currentTimeMillis() / 1000));
            if((v12.uploadFirstPartLater) || (v12.nextPartFirst) || v12.estimatedSize != 0 || v10_2 != v12.totalFileSize) {
            label_309:
                v21 = 1;
            }
            else {
                v0_4 = v12.preferences;
                v10_1 = new StringBuilder();
                v10_1.append(v12.fileKey);
                v10_1.append("_id");
                v12.currentFileId = v0_4.getLong(v10_1.toString(), v5);
                v0_4 = v12.preferences;
                v10_1 = new StringBuilder();
                v10_1.append(v12.fileKey);
                v10_1.append("_time");
                v0_2 = v0_4.getInt(v10_1.toString(), 0);
                SharedPreferences v10_3 = v12.preferences;
                StringBuilder v11 = new StringBuilder();
                v11.append(v12.fileKey);
                v11.append("_uploaded");
                v10_2 = v10_3.getLong(v11.toString(), v5);
                if(v12.isEncrypted) {
                    SharedPreferences v13 = v12.preferences;
                    StringBuilder v14 = new StringBuilder();
                    v14.append(v12.fileKey);
                    v14.append("_iv");
                    String v13_1 = v13.getString(v14.toString(), v4);
                    SharedPreferences v14_1 = v12.preferences;
                    StringBuilder v15 = new StringBuilder();
                    v15.append(v12.fileKey);
                    v15.append("_key");
                    String v3_1 = v14_1.getString(v15.toString(), v4);
                    if(v13_1 != null && v3_1 != null) {
                        v12.key = Utilities.hexToBytes(v3_1);
                        v12.iv = Utilities.hexToBytes(v13_1);
                        if(v12.key != null && v12.iv != null && v12.key.length == v7 && v12.iv.length == v7) {
                            v12.ivChange = new byte[v7];
                            System.arraycopy(v12.iv, 0, v12.ivChange, 0, v7);
                            goto label_196;
                        }
                    }

                    v3 = 1;
                }
                else {
                label_196:
                    v3 = 0;
                }

                if(v3 != 0) {
                    goto label_309;
                }

                if(v0_2 == 0) {
                    goto label_309;
                }

                if((v12.isBigFile) && v0_2 < v12.uploadStartTime - 86400 || !v12.isBigFile && (((float)v0_2)) < (((float)v12.uploadStartTime)) - 5400f) {
                    v0_2 = 0;
                }

                if(v0_2 != 0) {
                    if(v10_2 <= v5) {
                        goto label_309;
                    }

                    v12.readBytesCount = v10_2;
                    v12.currentPartNum = ((int)(v10_2 / (((long)v12.uploadChunkSize))));
                    if(!v12.isBigFile) {
                        v0_2 = 0;
                        while(true) {
                            v21 = v3;
                            if((((long)v0_2)) >= v12.readBytesCount / (((long)v12.uploadChunkSize))) {
                                goto label_310;
                            }

                            v2 = v12.stream.read(v12.readBuffer);
                            v3 = !v12.isEncrypted || v2 % 16 == 0 ? 0 : 16 - v2 % 16;
                            int v11_1 = v2 + v3;
                            NativeByteBuffer v10_4 = new NativeByteBuffer(v11_1);
                            if(v2 != v12.uploadChunkSize || v12.totalPartsCount == v12.currentPartNum + 1) {
                                v12.isLastPart = true;
                            }

                            v10_4.writeBytes(v12.readBuffer, 0, v2);
                            if(v12.isEncrypted) {
                                for(v2 = 0; v2 < v3; ++v2) {
                                    v10_4.writeByte(0);
                                }

                                Utilities.aesIgeEncryption(v10_4.buffer, v12.key, v12.ivChange, true, true, 0, v11_1);
                            }

                            v10_4.reuse();
                            ++v0_2;
                            v3 = v21;
                        }
                    }

                    v21 = v3;
                    v12.stream.seek(v10_2);
                    if(!v12.isEncrypted) {
                        goto label_310;
                    }

                    v0_4 = v12.preferences;
                    StringBuilder v2_1 = new StringBuilder();
                    v2_1.append(v12.fileKey);
                    v2_1.append("_ivc");
                    String v0_5 = v0_4.getString(v2_1.toString(), v4);
                    if(v0_5 != null) {
                        v12.ivChange = Utilities.hexToBytes(v0_5);
                        if(v12.ivChange != null && v12.ivChange.length == v7) {
                            goto label_310;
                        }

                        v12.readBytesCount = v5;
                    }
                    else {
                        v12.readBytesCount = v5;
                    }

                    v12.currentPartNum = 0;
                    goto label_309;
                }

                v21 = v3;
            }

        label_310:
            if(v21 != 0) {
                if(v12.isEncrypted) {
                    v12.iv = new byte[v7];
                    v12.key = new byte[v7];
                    v12.ivChange = new byte[v7];
                    Utilities.random.nextBytes(v12.iv);
                    Utilities.random.nextBytes(v12.key);
                    System.arraycopy(v12.iv, 0, v12.ivChange, 0, v7);
                }

                v12.currentFileId = Utilities.random.nextLong();
                if(v12.nextPartFirst) {
                    goto label_338;
                }

                if(v12.uploadFirstPartLater) {
                    goto label_338;
                }

                if(v12.estimatedSize != 0) {
                    goto label_338;
                }

                this.storeFileUploadInfo();
            }

        label_338:
            if(!v12.isEncrypted) {
                goto label_365;
            }
        }
        catch(Exception v0) {
            goto label_566;
        }

        try {
            MessageDigest v0_6 = MessageDigest.getInstance("MD5");
            byte[] v2_2 = new byte[v9_1];
            System.arraycopy(v12.key, 0, v2_2, 0, v7);
            System.arraycopy(v12.iv, 0, v2_2, v7, v7);
            byte[] v0_7 = v0_6.digest(v2_2);
            for(v2 = 0; true; ++v2) {
                if(v2 >= 4) {
                    goto label_365;
                }

                v12.fingerprint |= ((v0_7[v2] ^ v0_7[v2 + 4]) & 255) << v2 * 8;
            }
        }
        catch(Exception v0) {
            try {
                FileLog.e(((Throwable)v0));
            label_365:
                v12.uploadedBytesCount = v12.readBytesCount;
                v12.lastSavedPartNum = v12.currentPartNum;
                if(!v12.uploadFirstPartLater) {
                    goto label_391;
                }

                if(v12.isBigFile) {
                    v12.stream.seek(((long)v12.uploadChunkSize));
                    v12.readBytesCount = ((long)v12.uploadChunkSize);
                }
                else {
                    v12.stream.seek(1024);
                    v12.readBytesCount = 1024;
                }

                v12.currentPartNum = 1;
                goto label_391;
            label_387:
                throw new Exception("trying to upload internal file");
            label_391:
                if(v12.estimatedSize != 0 && v12.readBytesCount + (((long)v12.uploadChunkSize)) > v12.availableSize) {
                    return;
                }

                if(v12.nextPartFirst) {
                    v12.stream.seek(v5);
                    v0_2 = v12.isBigFile ? v12.stream.read(v12.readBuffer) : v12.stream.read(v12.readBuffer, 0, 1024);
                    v12.currentPartNum = 0;
                }
                else {
                    v0_2 = v12.stream.read(v12.readBuffer);
                }

                v2 = -1;
                if(v0_2 == v2) {
                    return;
                }

                v3 = !v12.isEncrypted || v0_2 % 16 == 0 ? 0 : 16 - v0_2 % 16;
                int v6 = v0_2 + v3;
                NativeByteBuffer v5_1 = new NativeByteBuffer(v6);
                if((v12.nextPartFirst) || v0_2 != v12.uploadChunkSize || v12.estimatedSize == 0 && v12.totalPartsCount == v12.currentPartNum + 1) {
                    if(v12.uploadFirstPartLater) {
                        v12.nextPartFirst = true;
                        v12.uploadFirstPartLater = false;
                    }
                    else {
                        v12.isLastPart = true;
                    }
                }

                v5_1.writeBytes(v12.readBuffer, 0, v0_2);
                if(v12.isEncrypted) {
                    int v4_1;
                    for(v4_1 = 0; v4_1 < v3; ++v4_1) {
                        v5_1.writeByte(0);
                    }

                    Utilities.aesIgeEncryption(v5_1.buffer, v12.key, v12.ivChange, true, true, 0, v6);
                    Object v3_2 = v12.freeRequestIvs.get(0);
                    System.arraycopy(v12.ivChange, 0, v3_2, 0, v7);
                    v12.freeRequestIvs.remove(0);
                    v6_1 = v3_2;
                }
                else {
                    v6_1 = v4;
                }

                if(v12.isBigFile) {
                    TL_upload_saveBigFilePart v3_3 = new TL_upload_saveBigFilePart();
                    v4_1 = v12.currentPartNum;
                    v3_3.file_part = v4_1;
                    v3_3.file_id = v12.currentFileId;
                    if(v12.estimatedSize == 0) {
                        v2 = v12.totalPartsCount;
                    }

                    v3_3.file_total_parts = v2;
                    v3_3.bytes = v5_1;
                    TL_upload_saveBigFilePart v22 = v3_3;
                    v9_1 = v4_1;
                }
                else {
                    TL_upload_saveFilePart v2_3 = new TL_upload_saveFilePart();
                    v3 = v12.currentPartNum;
                    v2_3.file_part = v3;
                    v2_3.file_id = v12.currentFileId;
                    v2_3.bytes = v5_1;
                    v22_1 = v2_3;
                    v9_1 = v3;
                }

                if((v12.isLastPart) && (v12.nextPartFirst)) {
                    v12.nextPartFirst = false;
                    v12.currentPartNum = v12.totalPartsCount - 1;
                    v12.stream.seek(v12.totalFileSize);
                }

                v12.readBytesCount += ((long)v0_2);
            }
            catch(Exception v0) {
            label_566:
                FileLog.e(((Throwable)v0));
                v12.state = 4;
                v12.delegate.didFailedUploadingFile(v12);
                this.cleanup();
                return;
            }
        }

        ++v12.currentPartNum;
        ++v12.currentUploadRequetsCount;
        int v13_2 = v12.requestNum;
        v12.requestNum = v13_2 + 1;
        v10_2 = ((long)(v9_1 + v0_2));
        v4_1 = ((TLObject)v22_1).getObjectSize() + 4;
        v3 = v12.operationGuid;
        int v28 = v12.slowNetwork ? 4 : v13_2 % 4 << 16 | 4;
        v12.requestTokens.put(v13_2, ConnectionsManager.getInstance(v12.currentAccount).sendRequest(((TLObject)v22_1), new RequestDelegate(v3, v4_1, v6_1, v13_2, v0_2, v9_1, v10_2, ((TL_upload_saveBigFilePart)v22_1)) {
            public void run(TLObject arg19, TL_error arg20) {
                TL_inputEncryptedFileBigUploaded v15_1;
                TL_inputEncryptedFileUploaded v1_4;
                byte[] v17;
                byte[] v16;
                FileUploadOperation v13;
                FileUploadOperationDelegate v12_1;
                TL_inputFileBig v14;
                TL_inputFileBig v1_1;
                org.telegram.messenger.FileUploadOperation$5 v0 = this;
                TLObject v1 = arg19;
                if(v0.val$currentOperationGuid != v0.this$0.operationGuid) {
                    return;
                }

                int v2 = v1 != null ? v1.networkType : ConnectionsManager.getCurrentNetworkType();
                int v4 = 5;
                int v5 = 67108864;
                int v6 = 16777216;
                int v7 = 2;
                int v8 = 33554432;
                int v9 = 50331648;
                int v10 = 3;
                int v11 = 4;
                if(v0.this$0.currentType == v9) {
                    StatsController.getInstance(v0.this$0.currentAccount).incrementSentBytesCount(v2, v10, ((long)v0.val$requestSize));
                }
                else if(v0.this$0.currentType == v8) {
                    StatsController.getInstance(v0.this$0.currentAccount).incrementSentBytesCount(v2, v7, ((long)v0.val$requestSize));
                }
                else if(v0.this$0.currentType == v6) {
                    StatsController.getInstance(v0.this$0.currentAccount).incrementSentBytesCount(v2, v11, ((long)v0.val$requestSize));
                }
                else if(v0.this$0.currentType == v5) {
                    StatsController.getInstance(v0.this$0.currentAccount).incrementSentBytesCount(v2, v4, ((long)v0.val$requestSize));
                }

                if(v0.val$currentRequestIv != null) {
                    v0.this$0.freeRequestIvs.add(v0.val$currentRequestIv);
                }

                v0.this$0.requestTokens.delete(v0.val$requestNumFinal);
                if(!(v1 instanceof TL_boolTrue)) {
                    if(v0.val$finalRequest != null) {
                        FileLog.e("23123");
                    }

                    v0.this$0.state = v11;
                    v0.this$0.delegate.didFailedUploadingFile(v0.this$0);
                    v0.this$0.cleanup();
                }
                else if(v0.this$0.state != 1) {
                    return;
                }
                else {
                    v0.this$0.uploadedBytesCount += ((long)v0.val$currentRequestBytes);
                    long v12 = v0.this$0.estimatedSize != 0 ? Math.max(v0.this$0.availableSize, ((long)v0.this$0.estimatedSize)) : v0.this$0.totalFileSize;
                    v0.this$0.delegate.didChangedUploadProgress(v0.this$0, (((float)v0.this$0.uploadedBytesCount)) / (((float)v12)));
                    FileUploadOperation.access$1610(v0.this$0);
                    if((v0.this$0.isLastPart) && v0.this$0.currentUploadRequetsCount == 0 && v0.this$0.state == 1) {
                        v0.this$0.state = v10;
                        if(v0.this$0.key == null) {
                            if(v0.this$0.isBigFile) {
                                v1_1 = new TL_inputFileBig();
                            }
                            else {
                                TL_inputFile v1_2 = new TL_inputFile();
                                ((InputFile)v1_2).md5_checksum = "";
                            }

                            v14 = v1_1;
                            ((InputFile)v14).parts = v0.this$0.currentPartNum;
                            ((InputFile)v14).id = v0.this$0.currentFileId;
                            ((InputFile)v14).name = v0.this$0.uploadingFilePath.substring(v0.this$0.uploadingFilePath.lastIndexOf("/") + 1);
                            v12_1 = v0.this$0.delegate;
                            v13 = v0.this$0;
                            InputEncryptedFile v15 = null;
                            v16 = null;
                            v17 = null;
                        }
                        else {
                            if(v0.this$0.isBigFile) {
                                TL_inputEncryptedFileBigUploaded v1_3 = new TL_inputEncryptedFileBigUploaded();
                            }
                            else {
                                v1_4 = new TL_inputEncryptedFileUploaded();
                                ((InputEncryptedFile)v1_4).md5_checksum = "";
                            }

                            v15_1 = ((TL_inputEncryptedFileBigUploaded)v1_4);
                            ((InputEncryptedFile)v15_1).parts = v0.this$0.currentPartNum;
                            ((InputEncryptedFile)v15_1).id = v0.this$0.currentFileId;
                            ((InputEncryptedFile)v15_1).key_fingerprint = v0.this$0.fingerprint;
                            v12_1 = v0.this$0.delegate;
                            v13 = v0.this$0;
                            InputFile v14_1 = null;
                            v16 = v0.this$0.key;
                            v17 = v0.this$0.iv;
                        }

                        v12_1.didFinishUploadingFile(v13, ((InputFile)v14), ((InputEncryptedFile)v15_1), v16, v17);
                        v0.this$0.cleanup();
                        if(v0.this$0.currentType == v9) {
                            StatsController.getInstance(v0.this$0.currentAccount).incrementSentItemsCount(ConnectionsManager.getCurrentNetworkType(), v10, 1);
                            return;
                        }

                        if(v0.this$0.currentType == v8) {
                            StatsController.getInstance(v0.this$0.currentAccount).incrementSentItemsCount(ConnectionsManager.getCurrentNetworkType(), v7, 1);
                            return;
                        }

                        if(v0.this$0.currentType == v6) {
                            StatsController.getInstance(v0.this$0.currentAccount).incrementSentItemsCount(ConnectionsManager.getCurrentNetworkType(), v11, 1);
                            return;
                        }

                        if(v0.this$0.currentType != v5) {
                            return;
                        }

                        StatsController.getInstance(v0.this$0.currentAccount).incrementSentItemsCount(ConnectionsManager.getCurrentNetworkType(), v4, 1);
                        return;
                    }

                    if(v0.this$0.currentUploadRequetsCount >= v0.this$0.maxRequestsCount) {
                        return;
                    }

                    if(v0.this$0.estimatedSize == 0 && !v0.this$0.uploadFirstPartLater && !v0.this$0.nextPartFirst) {
                        if(v0.this$0.saveInfoTimes >= v11) {
                            v0.this$0.saveInfoTimes = 0;
                        }

                        if(v0.val$currentRequestPartNum == v0.this$0.lastSavedPartNum) {
                            FileUploadOperation.access$1708(v0.this$0);
                            long v1_5 = v0.val$currentRequestBytesOffset;
                            byte[] v3 = v0.val$currentRequestIv;
                            while(true) {
                                Object v4_1 = v0.this$0.cachedResults.get(v0.this$0.lastSavedPartNum);
                                if(v4_1 == null) {
                                    break;
                                }

                                v1_5 = UploadCachedResult.access$3600(((UploadCachedResult)v4_1));
                                v3 = UploadCachedResult.access$3700(((UploadCachedResult)v4_1));
                                v0.this$0.cachedResults.remove(v0.this$0.lastSavedPartNum);
                                FileUploadOperation.access$1708(v0.this$0);
                            }

                            if(!v0.this$0.isBigFile || v1_5 % 1048576 != 0) {
                                if(v0.this$0.isBigFile) {
                                }
                                else if(v0.this$0.saveInfoTimes == 0) {
                                    goto label_279;
                                }

                                goto label_324;
                            }

                        label_279:
                            SharedPreferences$Editor v4_2 = v0.this$0.preferences.edit();
                            v4_2.putLong(v0.this$0.fileKey + "_uploaded", v1_5);
                            if(v0.this$0.isEncrypted) {
                                v4_2.putString(v0.this$0.fileKey + "_ivc", Utilities.bytesToHex(v3));
                            }

                            v4_2.commit();
                        }
                        else {
                            UploadCachedResult v1_7 = new UploadCachedResult(v0.this$0, null);
                            UploadCachedResult.access$3602(v1_7, v0.val$currentRequestBytesOffset);
                            if(v0.val$currentRequestIv != null) {
                                UploadCachedResult.access$3702(v1_7, new byte[32]);
                                System.arraycopy(v0.val$currentRequestIv, 0, UploadCachedResult.access$3700(v1_7), 0, 32);
                            }

                            v0.this$0.cachedResults.put(v0.val$currentRequestPartNum, v1_7);
                        }

                    label_324:
                        FileUploadOperation.access$1208(v0.this$0);
                    }

                    v0.this$0.startUploadRequest();
                }
            }
        }, null, new WriteToSocketDelegate() {
            public void run() {
                Utilities.stageQueue.postRunnable(new Runnable() {
                    public void run() {
                        if(this.this$1.this$0.currentUploadRequetsCount < this.this$1.this$0.maxRequestsCount) {
                            this.this$1.this$0.startUploadRequest();
                        }
                    }
                });
            }
        }, 0, 2147483647, v28, true));
    }

    private void storeFileUploadInfo() {
        SharedPreferences$Editor v0 = this.preferences.edit();
        v0.putInt(this.fileKey + "_time", this.uploadStartTime);
        v0.putLong(this.fileKey + "_size", this.totalFileSize);
        v0.putLong(this.fileKey + "_id", this.currentFileId);
        v0.remove(this.fileKey + "_uploaded");
        if(this.isEncrypted) {
            v0.putString(this.fileKey + "_iv", Utilities.bytesToHex(this.iv));
            v0.putString(this.fileKey + "_ivc", Utilities.bytesToHex(this.ivChange));
            v0.putString(this.fileKey + "_key", Utilities.bytesToHex(this.key));
        }

        v0.commit();
    }
}

