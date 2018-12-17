package org.telegram.messenger;

import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.webkit.MimeTypeMap;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Photo;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_documentAttributeFilename;
import org.telegram.tgnet.TLRPC$TL_fileLocationUnavailable;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_messageMediaInvoice;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_messageService;
import org.telegram.tgnet.TLRPC$TL_photoCachedSize;
import org.telegram.tgnet.TLRPC$TL_secureFile;
import org.telegram.tgnet.TLRPC$TL_webDocument;
import org.telegram.tgnet.TLRPC$WebDocument;

public class FileLoader {
    public interface FileLoaderDelegate {
        void fileDidFailedLoad(String arg1, int arg2);

        void fileDidFailedUpload(String arg1, boolean arg2);

        void fileDidLoaded(String arg1, File arg2, int arg3);

        void fileDidUploaded(String arg1, InputFile arg2, InputEncryptedFile arg3, byte[] arg4, byte[] arg5, long arg6);

        void fileLoadProgressChanged(String arg1, float arg2);

        void fileUploadProgressChanged(String arg1, float arg2, boolean arg3);
    }

    public interface FileLoaderDelegateCustomForDownloader {
        void fileDidFailedLoad(Document arg1, FileLocation arg2);

        void fileDidLoaded(Document arg1, FileLocation arg2);

        void fileLoadProgressChanged(Document arg1, FileLocation arg2);
    }

    private static volatile FileLoader[] Instance = null;
    public static final int MEDIA_DIR_AUDIO = 1;
    public static final int MEDIA_DIR_CACHE = 4;
    public static final int MEDIA_DIR_DOCUMENT = 3;
    public static final int MEDIA_DIR_IMAGE = 0;
    public static final int MEDIA_DIR_VIDEO = 2;
    private ArrayList activeFileLoadOperation;
    private SparseArray audioLoadOperationQueues;
    private int currentAccount;
    private SparseIntArray currentAudioLoadOperationsCount;
    private SparseIntArray currentLoadOperationsCount;
    private SparseIntArray currentPhotoLoadOperationsCount;
    private int currentUploadOperationsCount;
    private int currentUploadSmallOperationsCount;
    private ArrayList customDelegates;
    private FileLoaderDelegate delegate;
    private ArrayList delegates;
    private static volatile DispatchQueue fileLoaderQueue;
    private ConcurrentHashMap loadOperationPaths;
    private ConcurrentHashMap loadOperationPathsUI;
    private SparseArray loadOperationQueues;
    private static SparseArray mediaDirs;
    private SparseArray photoLoadOperationQueues;
    private ConcurrentHashMap uploadOperationPaths;
    private ConcurrentHashMap uploadOperationPathsEnc;
    private LinkedList uploadOperationQueue;
    private HashMap uploadSizes;
    private LinkedList uploadSmallOperationQueue;

    static {
        FileLoader.fileLoaderQueue = new DispatchQueue("fileUploadQueue");
        FileLoader.mediaDirs = null;
        FileLoader.Instance = new FileLoader[3];
    }

    public FileLoader(int arg5) {
        super();
        this.uploadOperationQueue = new LinkedList();
        this.uploadSmallOperationQueue = new LinkedList();
        this.uploadOperationPaths = new ConcurrentHashMap();
        this.uploadOperationPathsEnc = new ConcurrentHashMap();
        this.currentUploadOperationsCount = 0;
        this.currentUploadSmallOperationsCount = 0;
        this.loadOperationQueues = new SparseArray();
        this.audioLoadOperationQueues = new SparseArray();
        this.photoLoadOperationQueues = new SparseArray();
        this.currentLoadOperationsCount = new SparseIntArray();
        this.currentAudioLoadOperationsCount = new SparseIntArray();
        this.currentPhotoLoadOperationsCount = new SparseIntArray();
        this.loadOperationPaths = new ConcurrentHashMap();
        this.activeFileLoadOperation = new ArrayList();
        this.loadOperationPathsUI = new ConcurrentHashMap(10, 1f, 2);
        this.uploadSizes = new HashMap();
        this.delegate = null;
        this.delegates = null;
        this.customDelegates = null;
        this.currentAccount = arg5;
    }

    static ConcurrentHashMap access$000(FileLoader arg0) {
        return arg0.uploadOperationPaths;
    }

    static ConcurrentHashMap access$100(FileLoader arg0) {
        return arg0.uploadOperationPathsEnc;
    }

    static ConcurrentHashMap access$1000(FileLoader arg0) {
        return arg0.loadOperationPaths;
    }

    static LinkedList access$1100(FileLoader arg0, int arg1) {
        return arg0.getAudioLoadOperationQueue(arg1);
    }

    static SparseIntArray access$1200(FileLoader arg0) {
        return arg0.currentAudioLoadOperationsCount;
    }

    static LinkedList access$1300(FileLoader arg0, int arg1) {
        return arg0.getPhotoLoadOperationQueue(arg1);
    }

    static SparseIntArray access$1400(FileLoader arg0) {
        return arg0.currentPhotoLoadOperationsCount;
    }

    static LinkedList access$1500(FileLoader arg0, int arg1) {
        return arg0.getLoadOperationQueue(arg1);
    }

    static SparseIntArray access$1600(FileLoader arg0) {
        return arg0.currentLoadOperationsCount;
    }

    static ArrayList access$1700(FileLoader arg0) {
        return arg0.activeFileLoadOperation;
    }

    static ConcurrentHashMap access$1800(FileLoader arg0) {
        return arg0.loadOperationPathsUI;
    }

    static void access$1900(FileLoader arg0, int arg1, Document arg2, WebFile arg3, FileLocation arg4, String arg5) {
        arg0.checkDownloadQueue(arg1, arg2, arg3, arg4, arg5);
    }

    static HashMap access$200(FileLoader arg0) {
        return arg0.uploadSizes;
    }

    static ArrayList access$2000(FileLoader arg0) {
        return arg0.delegates;
    }

    static ArrayList access$2100(FileLoader arg0) {
        return arg0.customDelegates;
    }

    static FileLoadOperation access$2200(FileLoader arg0, Document arg1, SecureDocument arg2, WebFile arg3, FileLocation arg4, String arg5, int arg6, boolean arg7, FileStreamLoadOperation arg8, int arg9, int arg10) {
        return arg0.loadFileInternal(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
    }

    static LinkedList access$300(FileLoader arg0) {
        return arg0.uploadOperationQueue;
    }

    static LinkedList access$400(FileLoader arg0) {
        return arg0.uploadSmallOperationQueue;
    }

    static int access$500(FileLoader arg0) {
        return arg0.currentAccount;
    }

    static int access$600(FileLoader arg0) {
        return arg0.currentUploadSmallOperationsCount;
    }

    static int access$608(FileLoader arg2) {
        int v0 = arg2.currentUploadSmallOperationsCount;
        arg2.currentUploadSmallOperationsCount = v0 + 1;
        return v0;
    }

    static int access$610(FileLoader arg2) {
        int v0 = arg2.currentUploadSmallOperationsCount;
        arg2.currentUploadSmallOperationsCount = v0 - 1;
        return v0;
    }

    static int access$700(FileLoader arg0) {
        return arg0.currentUploadOperationsCount;
    }

    static int access$708(FileLoader arg2) {
        int v0 = arg2.currentUploadOperationsCount;
        arg2.currentUploadOperationsCount = v0 + 1;
        return v0;
    }

    static int access$710(FileLoader arg2) {
        int v0 = arg2.currentUploadOperationsCount;
        arg2.currentUploadOperationsCount = v0 - 1;
        return v0;
    }

    static FileLoaderDelegate access$800(FileLoader arg0) {
        return arg0.delegate;
    }

    static DispatchQueue access$900() {
        return FileLoader.fileLoaderQueue;
    }

    public void addCustomDelegate(FileLoaderDelegateCustomForDownloader arg2) {
        __monitor_enter(this);
        try {
            if(this.customDelegates == null) {
                this.customDelegates = new ArrayList();
            }

            this.customDelegates.add(arg2);
            __monitor_exit(this);
            return;
        label_11:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_11;
        }

        throw v2;
    }

    public void addDelegate(FileLoaderDelegate arg2) {
        __monitor_enter(this);
        try {
            if(this.delegates == null) {
                this.delegates = new ArrayList();
            }

            this.delegates.add(arg2);
            __monitor_exit(this);
            return;
        label_11:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_11;
        }

        throw v2;
    }

    public void cancelLoadFile(PhotoSize arg7) {
        this.cancelLoadFile(null, null, null, arg7.location, null);
    }

    public void cancelLoadFile(Document arg7) {
        this.cancelLoadFile(arg7, null, null, null, null);
    }

    private void cancelLoadFile(Document arg9, SecureDocument arg10, WebFile arg11, FileLocation arg12, String arg13) {
        if(arg12 == null && arg9 == null && arg11 == null && arg10 == null) {
            return;
        }

        if(arg12 != null) {
            arg13 = FileLoader.getAttachFileName(((TLObject)arg12), arg13);
        }
        else if(arg9 != null) {
            arg13 = FileLoader.getAttachFileName(((TLObject)arg9));
        }
        else if(arg10 != null) {
            arg13 = FileLoader.getAttachFileName(((TLObject)arg10));
        }
        else if(arg11 != null) {
            arg13 = FileLoader.getAttachFileName(((TLObject)arg11));
        }
        else {
            arg13 = null;
        }

        String v2 = arg13;
        if(v2 == null) {
            return;
        }

        this.loadOperationPathsUI.remove(v2);
        FileLoader.fileLoaderQueue.postRunnable(new Runnable(v2, arg9, arg11, arg10, arg12) {
            public void run() {
                SparseIntArray v3;
                SparseIntArray v2;
                Object v0 = FileLoader.this.loadOperationPaths.remove(this.val$fileName);
                if(v0 != null) {
                    int v1 = ((FileLoadOperation)v0).getDatacenterId();
                    if((MessageObject.isVoiceDocument(this.val$document)) || (MessageObject.isVoiceWebDocument(this.val$webDocument))) {
                        if(FileLoader.this.getAudioLoadOperationQueue(v1).remove(v0)) {
                            goto label_57;
                        }

                        v2 = FileLoader.this.currentAudioLoadOperationsCount;
                        v3 = FileLoader.this.currentAudioLoadOperationsCount;
                    label_44:
                        v2.put(v1, v3.get(v1) - 1);
                    }
                    else {
                        if(this.val$secureDocument == null && this.val$location == null) {
                            if(MessageObject.isImageWebDocument(this.val$webDocument)) {
                            }
                            else {
                                if(!FileLoader.this.getLoadOperationQueue(v1).remove(v0)) {
                                    FileLoader.this.currentLoadOperationsCount.put(v1, FileLoader.this.currentLoadOperationsCount.get(v1) - 1);
                                }

                                FileLoader.this.activeFileLoadOperation.remove(v0);
                                goto label_57;
                            }
                        }

                        if(FileLoader.this.getPhotoLoadOperationQueue(v1).remove(v0)) {
                            goto label_57;
                        }

                        v2 = FileLoader.this.currentPhotoLoadOperationsCount;
                        v3 = FileLoader.this.currentPhotoLoadOperationsCount;
                        goto label_44;
                    }

                label_57:
                    ((FileLoadOperation)v0).cancel();
                }
            }
        });
    }

    public void cancelLoadFile(SecureDocument arg7) {
        this.cancelLoadFile(null, arg7, null, null, null);
    }

    public void cancelLoadFile(WebFile arg7) {
        this.cancelLoadFile(null, null, arg7, null, null);
    }

    public void cancelLoadFile(FileLocation arg7, String arg8) {
        this.cancelLoadFile(null, null, null, arg7, arg8);
    }

    public void cancelUploadFile(String arg3, boolean arg4) {
        FileLoader.fileLoaderQueue.postRunnable(new Runnable(arg4, arg3) {
            public void run() {
                ConcurrentHashMap v0 = !this.val$enc ? FileLoader.this.uploadOperationPaths : FileLoader.this.uploadOperationPathsEnc;
                Object v0_1 = v0.get(this.val$location);
                FileLoader.this.uploadSizes.remove(this.val$location);
                if(v0_1 != null) {
                    FileLoader.this.uploadOperationPathsEnc.remove(this.val$location);
                    FileLoader.this.uploadOperationQueue.remove(v0_1);
                    FileLoader.this.uploadSmallOperationQueue.remove(v0_1);
                    ((FileUploadOperation)v0_1).cancel();
                }
            }
        });
    }

    public static File checkDirectory(int arg1) {
        return FileLoader.mediaDirs.get(arg1);
    }

    private void checkDownloadQueue(int arg10, Document arg11, WebFile arg12, FileLocation arg13, String arg14) {
        FileLoader.fileLoaderQueue.postRunnable(new Runnable(arg10, arg14, arg11, arg12, arg13) {
            public void run() {
                int v0_1;
                Object v2_2;
                int v2_1;
                int v1_1;
                LinkedList v0 = FileLoader.this.getAudioLoadOperationQueue(this.val$datacenterId);
                LinkedList v1 = FileLoader.this.getPhotoLoadOperationQueue(this.val$datacenterId);
                LinkedList v2 = FileLoader.this.getLoadOperationQueue(this.val$datacenterId);
                Object v3 = FileLoader.this.loadOperationPaths.remove(this.val$arg1);
                if((MessageObject.isVoiceDocument(this.val$document)) || (MessageObject.isVoiceWebDocument(this.val$webDocument))) {
                    v1_1 = FileLoader.this.currentAudioLoadOperationsCount.get(this.val$datacenterId);
                    if(v3 != null) {
                        if(((FileLoadOperation)v3).wasStarted()) {
                            --v1_1;
                            goto label_109;
                        }
                        else {
                            v0.remove(v3);
                            goto label_115;
                            while(true) {
                            label_46:
                                if(v2.isEmpty()) {
                                    return;
                                }

                                v1_1 = v2.get(0).isForceRequest() ? 3 : 1;
                                if(v0_1 >= v1_1) {
                                    return;
                                }

                                Object v1_2 = v2.poll();
                                if(v1_2 == null) {
                                    continue;
                                }

                                if(!((FileLoadOperation)v1_2).start()) {
                                    continue;
                                }

                                ++v0_1;
                                FileLoader.this.currentLoadOperationsCount.put(this.val$datacenterId, v0_1);
                                if(FileLoader.this.activeFileLoadOperation.contains(v1_2)) {
                                    continue;
                                }

                                FileLoader.this.activeFileLoadOperation.add(v1_2);
                            }

                        label_80:
                            FileLoader.this.currentPhotoLoadOperationsCount.put(this.val$datacenterId, v0_1);
                            do {
                            label_86:
                                if(v1.isEmpty()) {
                                    return;
                                }

                                v2_1 = v1.get(0).isForceRequest() ? 3 : 1;
                                if(v0_1 >= v2_1) {
                                    return;
                                }

                                v2_2 = v1.poll();
                                if(v2_2 == null) {
                                    goto label_86;
                                }
                            }
                            while(!((FileLoadOperation)v2_2).start());

                            ++v0_1;
                            goto label_80;
                        label_109:
                            FileLoader.this.currentAudioLoadOperationsCount.put(this.val$datacenterId, v1_1);
                        }
                    }

                    goto label_115;
                }
                else {
                    if(this.val$location == null) {
                        if(MessageObject.isImageWebDocument(this.val$webDocument)) {
                        }
                        else {
                            v0_1 = FileLoader.this.currentLoadOperationsCount.get(this.val$datacenterId);
                            if(v3 != null) {
                                if(((FileLoadOperation)v3).wasStarted()) {
                                    --v0_1;
                                    FileLoader.this.currentLoadOperationsCount.put(this.val$datacenterId, v0_1);
                                }
                                else {
                                    v2.remove(v3);
                                }

                                FileLoader.this.activeFileLoadOperation.remove(v3);
                            }

                            goto label_46;
                        }
                    }

                    v0_1 = FileLoader.this.currentPhotoLoadOperationsCount.get(this.val$datacenterId);
                    if(v3 != null) {
                        if(((FileLoadOperation)v3).wasStarted()) {
                            --v0_1;
                            goto label_80;
                        }
                        else {
                            v1.remove(v3);
                        }
                    }

                    goto label_86;
                }

                return;
                do {
                label_115:
                    if(v0.isEmpty()) {
                        return;
                    }

                    v2_1 = v0.get(0).isForceRequest() ? 3 : 1;
                    if(v1_1 >= v2_1) {
                        return;
                    }

                    v2_2 = v0.poll();
                    if(v2_2 == null) {
                        goto label_115;
                    }
                }
                while(!((FileLoadOperation)v2_2).start());

                ++v1_1;
                goto label_109;
            }
        });
    }

    public void checkUploadNewDataAvailable(String arg11, boolean arg12, long arg13, long arg15) {
        FileLoader.fileLoaderQueue.postRunnable(new Runnable(arg12, arg11, arg13, arg15) {
            public void run() {
                ConcurrentHashMap v0 = this.val$encrypted ? FileLoader.this.uploadOperationPathsEnc : FileLoader.this.uploadOperationPaths;
                Object v0_1 = v0.get(this.val$location);
                if(v0_1 != null) {
                    ((FileUploadOperation)v0_1).checkNewDataAvailable(this.val$newAvailableSize, this.val$finalSize);
                }
                else if(this.val$finalSize != 0) {
                    FileLoader.this.uploadSizes.put(this.val$location, Long.valueOf(this.val$finalSize));
                }
            }
        });
    }

    public void deleteFiles(ArrayList arg3, int arg4) {
        if(arg3 != null) {
            if(arg3.isEmpty()) {
            }
            else {
                FileLoader.fileLoaderQueue.postRunnable(new Runnable(arg3, arg4) {
                    public void run() {
                        StringBuilder v4;
                        File v3_1;
                        int v0;
                        for(v0 = 0; v0 < this.val$files.size(); ++v0) {
                            Object v1 = this.val$files.get(v0);
                            StringBuilder v3 = new StringBuilder();
                            v3.append(((File)v1).getAbsolutePath());
                            v3.append(".enc");
                            File v2 = new File(v3.toString());
                            if(v2.exists()) {
                                try {
                                    if(v2.delete()) {
                                        goto label_23;
                                    }

                                    v2.deleteOnExit();
                                }
                                catch(Exception v2_1) {
                                    FileLog.e(((Throwable)v2_1));
                                }

                                try {
                                label_23:
                                    v3_1 = FileLoader.getInternalCacheDir();
                                    v4 = new StringBuilder();
                                    v4.append(((File)v1).getName());
                                    v4.append(".enc.key");
                                    v2 = new File(v3_1, v4.toString());
                                    if(v2.delete()) {
                                        goto label_45;
                                    }

                                    v2.deleteOnExit();
                                    goto label_45;
                                }
                                catch(Exception v2_1) {
                                    goto label_44;
                                }
                            }

                            if(!((File)v1).exists()) {
                                goto label_45;
                            }

                            try {
                                if(((File)v1).delete()) {
                                    goto label_45;
                                }

                                ((File)v1).deleteOnExit();
                            }
                            catch(Exception v2_1) {
                            label_44:
                                FileLog.e(((Throwable)v2_1));
                            }

                            try {
                            label_45:
                                v3_1 = ((File)v1).getParentFile();
                                v4 = new StringBuilder();
                                v4.append("q_");
                                v4.append(((File)v1).getName());
                                v2 = new File(v3_1, v4.toString());
                                if(!v2.exists()) {
                                    goto label_63;
                                }

                                if(v2.delete()) {
                                    goto label_63;
                                }

                                v2.deleteOnExit();
                            }
                            catch(Exception v1_1) {
                                FileLog.e(((Throwable)v1_1));
                            }

                        label_63:
                        }

                        if(this.val$type == 2) {
                            ImageLoader.getInstance().clearMemory();
                        }
                    }
                });
            }
        }
    }

    public static String fixFileName(String arg2) {
        if(arg2 != null) {
            arg2 = arg2.replaceAll("[\u0001-\u001F<>:\"/\\\\|?*\u007F]+", "").trim();
        }

        return arg2;
    }

    public static String getAttachFileName(TLObject arg1) {
        return FileLoader.getAttachFileName(arg1, null);
    }

    public static String getAttachFileName(TLObject arg4, String arg5) {
        int v1;
        if((arg4 instanceof Document)) {
            arg5 = FileLoader.getDocumentFileName(((Document)arg4));
            int v0 = -1;
            if(arg5 != null) {
                v1 = arg5.lastIndexOf(46);
                if(v1 == v0) {
                    goto label_11;
                }
                else {
                    arg5 = arg5.substring(v1);
                }
            }
            else {
            label_11:
                arg5 = "";
            }

            if(arg5.length() <= 1) {
                if(((Document)arg4).mime_type != null) {
                    arg5 = ((Document)arg4).mime_type;
                    v1 = arg5.hashCode();
                    if(v1 != 187091926) {
                        if(v1 != 1331848029) {
                        }
                        else if(arg5.equals("video/mp4")) {
                            v0 = 0;
                        }
                    }
                    else if(arg5.equals("audio/ogg")) {
                        v0 = 1;
                    }

                    switch(v0) {
                        case 0: {
                            goto label_37;
                        }
                        case 1: {
                            goto label_35;
                        }
                    }

                    goto label_39;
                label_35:
                    arg5 = ".ogg";
                    goto label_40;
                label_37:
                    arg5 = ".mp4";
                    goto label_40;
                }

            label_39:
                arg5 = "";
            }

        label_40:
            if(((Document)arg4).version == 0) {
                if(arg5.length() > 1) {
                    return ((Document)arg4).dc_id + "_" + ((Document)arg4).id + arg5;
                }

                return ((Document)arg4).dc_id + "_" + ((Document)arg4).id;
            }

            if(arg5.length() > 1) {
                return ((Document)arg4).dc_id + "_" + ((Document)arg4).id + "_" + ((Document)arg4).version + arg5;
            }

            return ((Document)arg4).dc_id + "_" + ((Document)arg4).id + "_" + ((Document)arg4).version;
        }

        if((arg4 instanceof SecureDocument)) {
            return ((SecureDocument)arg4).secureFile.dc_id + "_" + ((SecureDocument)arg4).secureFile.id + ".jpg";
        }

        if((arg4 instanceof TL_secureFile)) {
            return ((TL_secureFile)arg4).dc_id + "_" + ((TL_secureFile)arg4).id + ".jpg";
        }

        if((arg4 instanceof WebFile)) {
            return Utilities.MD5(((WebFile)arg4).url) + "." + ImageLoader.getHttpUrlExtension(((WebFile)arg4).url, FileLoader.getExtensionByMime(((WebFile)arg4).mime_type));
        }

        if((arg4 instanceof PhotoSize)) {
            if(((PhotoSize)arg4).location != null) {
                if((((PhotoSize)arg4).location instanceof TL_fileLocationUnavailable)) {
                }
                else {
                    v0_1 = new StringBuilder();
                    v0_1.append(((PhotoSize)arg4).location.volume_id);
                    v0_1.append("_");
                    v0_1.append(((PhotoSize)arg4).location.local_id);
                    v0_1.append(".");
                    if(arg5 != null) {
                    }
                    else {
                        arg5 = "jpg";
                    }

                    v0_1.append(arg5);
                    return v0_1.toString();
                }
            }

            return "";
        }

        if((arg4 instanceof FileLocation)) {
            if((arg4 instanceof TL_fileLocationUnavailable)) {
                return "";
            }

            v0_1 = new StringBuilder();
            v0_1.append(((FileLocation)arg4).volume_id);
            v0_1.append("_");
            v0_1.append(((FileLocation)arg4).local_id);
            v0_1.append(".");
            if(arg5 != null) {
            }
            else {
                arg5 = "jpg";
            }

            v0_1.append(arg5);
            return v0_1.toString();
        }

        if((arg4 instanceof Photo)) {
            v0_1 = new StringBuilder();
            v0_1.append(((FileLocation)arg4).volume_id);
            v0_1.append("_");
            v0_1.append(((FileLocation)arg4).local_id);
            v0_1.append(".");
            if(arg5 != null) {
            }
            else {
                arg5 = "jpg";
            }

            v0_1.append(arg5);
            return v0_1.toString();
        }

        return "";
    }

    private LinkedList getAudioLoadOperationQueue(int arg3) {
        LinkedList v0_1;
        Object v0 = this.audioLoadOperationQueues.get(arg3);
        if(v0 == null) {
            v0_1 = new LinkedList();
            this.audioLoadOperationQueues.put(arg3, v0_1);
        }

        return v0_1;
    }

    public float getBufferedProgressFromPosition(float arg3, String arg4) {
        if(TextUtils.isEmpty(((CharSequence)arg4))) {
            return 0;
        }

        Object v4 = this.loadOperationPaths.get(arg4);
        if(v4 != null) {
            return ((FileLoadOperation)v4).getDownloadedLengthFromOffset(arg3);
        }

        return 0;
    }

    public static PhotoSize getClosestPhotoSizeWithSize(ArrayList arg1, int arg2) {
        return FileLoader.getClosestPhotoSizeWithSize(arg1, arg2, false);
    }

    public static PhotoSize getClosestPhotoSizeWithSize(ArrayList arg8, int arg9, boolean arg10) {
        int v6;
        Object v0 = null;
        if(arg8 != null) {
            if(arg8.isEmpty()) {
            }
            else {
                int v1 = 0;
                int v2 = 0;
                while(v1 < arg8.size()) {
                    Object v3 = arg8.get(v1);
                    if(v3 == null) {
                    }
                    else {
                        int v4 = -2147483648;
                        int v5 = 100;
                        if(arg10) {
                            v6 = ((PhotoSize)v3).h >= ((PhotoSize)v3).w ? ((PhotoSize)v3).w : ((PhotoSize)v3).h;
                            if(v0 == null) {
                                goto label_50;
                            }

                            if(arg9 > v5 && ((PhotoSize)v0).location != null && ((PhotoSize)v0).location.dc_id == v4) {
                                goto label_50;
                            }

                            if((v3 instanceof TL_photoCachedSize)) {
                                goto label_50;
                            }

                            if(arg9 <= v2) {
                                goto label_52;
                            }

                            if(v2 >= v6) {
                                goto label_52;
                            }
                        }
                        else {
                            v6 = ((PhotoSize)v3).w >= ((PhotoSize)v3).h ? ((PhotoSize)v3).w : ((PhotoSize)v3).h;
                            if(v0 == null) {
                                goto label_50;
                            }

                            if(arg9 > v5 && ((PhotoSize)v0).location != null && ((PhotoSize)v0).location.dc_id == v4) {
                                goto label_50;
                            }

                            if((v3 instanceof TL_photoCachedSize)) {
                                goto label_50;
                            }

                            if(v6 > arg9) {
                                goto label_52;
                            }

                            if(v2 >= v6) {
                                goto label_52;
                            }
                        }

                    label_50:
                        v0 = v3;
                        v2 = v6;
                    }

                label_52:
                    ++v1;
                }
            }
        }

        return ((PhotoSize)v0);
    }

    public static File getDirectory(int arg2) {
        Object v0 = FileLoader.mediaDirs.get(arg2);
        if(v0 == null) {
            int v1 = 4;
            if(arg2 != v1) {
                v0 = FileLoader.mediaDirs.get(v1);
            }
        }

        try {
            if(!((File)v0).isDirectory()) {
                ((File)v0).mkdirs();
            }

            goto label_11;
        }
        catch(Exception ) {
        label_11:
            return ((File)v0);
        }
    }

    public static String getDocumentExtension(Document arg3) {
        String v0 = FileLoader.getDocumentFileName(arg3);
        int v1 = v0.lastIndexOf(46);
        v0 = v1 != -1 ? v0.substring(v1 + 1) : null;
        if(v0 == null || v0.length() == 0) {
            v0 = arg3.mime_type;
        }

        if(v0 == null) {
            v0 = "";
        }

        return v0.toUpperCase();
    }

    public static String getDocumentFileName(Document arg4) {
        String v0 = null;
        if(arg4 != null) {
            if(arg4.file_name != null) {
                v0 = arg4.file_name;
            }
            else {
                int v1;
                for(v1 = 0; v1 < arg4.attributes.size(); ++v1) {
                    Object v2 = arg4.attributes.get(v1);
                    if((v2 instanceof TL_documentAttributeFilename)) {
                        v0 = ((DocumentAttribute)v2).file_name;
                    }
                }
            }
        }

        String v4 = FileLoader.fixFileName(v0);
        if(v4 != null) {
        }
        else {
            v4 = "";
        }

        return v4;
    }

    public static String getExtensionByMime(String arg2) {
        int v0 = arg2.lastIndexOf(47);
        if(v0 != -1) {
            return arg2.substring(v0 + 1);
        }

        return "";
    }

    public static String getFileExtension(File arg1) {
        String v1 = arg1.getName();
        int v0 = 46;
        try {
            return v1.substring(v1.lastIndexOf(v0) + 1);
        }
        catch(Exception ) {
            return "";
        }
    }

    public static FileLoader getInstance(int arg3) {
        FileLoader v0 = FileLoader.Instance[arg3];
        if(v0 == null) {
            Class v1 = FileLoader.class;
            __monitor_enter(v1);
            try {
                v0 = FileLoader.Instance[arg3];
                if(v0 == null) {
                    FileLoader[] v0_1 = FileLoader.Instance;
                    FileLoader v2 = new FileLoader(arg3);
                    v0_1[arg3] = v2;
                    v0 = v2;
                }

                __monitor_exit(v1);
                return v0;
            label_16:
                __monitor_exit(v1);
            }
            catch(Throwable v3) {
                goto label_16;
            }

            throw v3;
        }

        return v0;
    }

    public static File getInternalCacheDir() {
        return ApplicationLoader.applicationContext.getCacheDir();
    }

    private LinkedList getLoadOperationQueue(int arg3) {
        LinkedList v0_1;
        Object v0 = this.loadOperationQueues.get(arg3);
        if(v0 == null) {
            v0_1 = new LinkedList();
            this.loadOperationQueues.put(arg3, v0_1);
        }

        return v0_1;
    }

    public static String getMessageFileName(Message arg2) {
        PhotoSize v2_1;
        ArrayList v2;
        if(arg2 == null) {
            return "";
        }

        if((arg2 instanceof TL_messageService)) {
            if(arg2.action.photo != null) {
                v2 = arg2.action.photo.sizes;
                if(v2.size() > 0) {
                    v2_1 = FileLoader.getClosestPhotoSizeWithSize(v2, AndroidUtilities.getPhotoSize());
                    if(v2_1 != null) {
                        return FileLoader.getAttachFileName(((TLObject)v2_1));
                    }
                }
            }
        }
        else if((arg2.media instanceof TL_messageMediaDocument)) {
            return FileLoader.getAttachFileName(arg2.media.document);
        }
        else if((arg2.media instanceof TL_messageMediaPhoto)) {
            v2 = arg2.media.photo.sizes;
            if(v2.size() > 0) {
                v2_1 = FileLoader.getClosestPhotoSizeWithSize(v2, AndroidUtilities.getPhotoSize());
                if(v2_1 != null) {
                    return FileLoader.getAttachFileName(((TLObject)v2_1));
                }
            }
        }
        else if((arg2.media instanceof TL_messageMediaWebPage)) {
            if(arg2.media.webpage.document != null) {
                return FileLoader.getAttachFileName(arg2.media.webpage.document);
            }
            else if(arg2.media.webpage.photo != null) {
                v2 = arg2.media.webpage.photo.sizes;
                if(v2.size() > 0) {
                    v2_1 = FileLoader.getClosestPhotoSizeWithSize(v2, AndroidUtilities.getPhotoSize());
                    if(v2_1 != null) {
                        return FileLoader.getAttachFileName(((TLObject)v2_1));
                    }
                }
            }
            else if((arg2.media instanceof TL_messageMediaInvoice)) {
                return FileLoader.getAttachFileName(arg2.media.photo);
            }
        }
        else if((arg2.media instanceof TL_messageMediaInvoice)) {
            WebDocument v2_2 = arg2.media.photo;
            if(v2_2 != null) {
                return Utilities.MD5(v2_2.url) + "." + ImageLoader.getHttpUrlExtension(v2_2.url, FileLoader.getExtensionByMime(v2_2.mime_type));
            }
        }

        return "";
    }

    public static String getMimeType(String arg1) {
        if(MimeTypeMap.getFileExtensionFromUrl(arg1).contentEquals("mp3")) {
            return "audio/mp3";
        }

        return "audio/ogg";
    }

    public static File getPathToAttach(TLObject arg1, boolean arg2) {
        return FileLoader.getPathToAttach(arg1, null, arg2);
    }

    public static File getPathToAttach(TLObject arg2) {
        return FileLoader.getPathToAttach(arg2, null, false);
    }

    public static File getPathToAttach(TLObject arg7, String arg8, boolean arg9) {
        File v9_1;
        TLObject v9;
        int v0 = 4;
        if(!arg9) {
            int v1 = 3;
            int v2 = 2;
            if((arg7 instanceof Document)) {
                v9 = arg7;
                if(((Document)v9).key != null) {
                    goto label_2;
                }
                else if(!MessageObject.isVoiceDocument(((Document)v9))) {
                    if(!MessageObject.isVideoDocument(((Document)v9))) {
                        goto label_21;
                    }

                    goto label_19;
                }
                else {
                    goto label_15;
                }
            }
            else {
                long v4 = -2147483648;
                if((arg7 instanceof PhotoSize)) {
                    v9 = arg7;
                    if(((PhotoSize)v9).location == null) {
                    }
                    else if(((PhotoSize)v9).location.key == null) {
                        if(((PhotoSize)v9).location.volume_id == v4 && ((PhotoSize)v9).location.local_id < 0) {
                            goto label_2;
                        }

                        if(((PhotoSize)v9).size >= 0) {
                            goto label_42;
                        }
                    }
                    else {
                    }

                    goto label_2;
                }
                else {
                    if((arg7 instanceof FileLocation)) {
                        v9 = arg7;
                        if(((FileLocation)v9).key != null) {
                            goto label_2;
                        }

                        if(((FileLocation)v9).volume_id != v4) {
                            goto label_42;
                        }

                        if(((FileLocation)v9).local_id >= 0) {
                            goto label_42;
                        }

                        goto label_2;
                    }

                    if(!(arg7 instanceof WebFile)) {
                        goto label_72;
                    }

                    v9 = arg7;
                    if(!((WebFile)v9).mime_type.startsWith("image/")) {
                        goto label_62;
                    }
                }

            label_42:
                v9_1 = FileLoader.getDirectory(0);
                goto label_78;
            label_62:
                if(((WebFile)v9).mime_type.startsWith("audio/")) {
                label_15:
                    v9_1 = FileLoader.getDirectory(1);
                    goto label_78;
                }

                if(!((WebFile)v9).mime_type.startsWith("video/")) {
                    goto label_21;
                }

            label_19:
                v9_1 = FileLoader.getDirectory(v2);
                goto label_78;
            }

        label_21:
            v9_1 = FileLoader.getDirectory(v1);
            goto label_78;
        label_72:
            if((arg7 instanceof TL_secureFile)) {
                goto label_2;
            }

            if((arg7 instanceof SecureDocument)) {
                goto label_2;
            }

            v9_1 = null;
        }
        else {
        label_2:
            v9_1 = FileLoader.getDirectory(v0);
        }

    label_78:
        if(v9_1 == null) {
            return new File("");
        }

        return new File(v9_1, FileLoader.getAttachFileName(arg7, arg8));
    }

    public static File getPathToMessage(Message arg4) {
        PhotoSize v4_1;
        ArrayList v4;
        if(arg4 == null) {
            return new File("");
        }

        if(!(arg4 instanceof TL_messageService)) {
            boolean v1 = false;
            if((arg4.media instanceof TL_messageMediaDocument)) {
                Document v0 = arg4.media.document;
                if(arg4.media.ttl_seconds != 0) {
                    v1 = true;
                }

                return FileLoader.getPathToAttach(((TLObject)v0), v1);
            }
            else {
                if((arg4.media instanceof TL_messageMediaPhoto)) {
                    ArrayList v0_1 = arg4.media.photo.sizes;
                    if(v0_1.size() <= 0) {
                        goto label_84;
                    }

                    PhotoSize v0_2 = FileLoader.getClosestPhotoSizeWithSize(v0_1, AndroidUtilities.getPhotoSize());
                    if(v0_2 == null) {
                        goto label_84;
                    }

                    if(arg4.media.ttl_seconds != 0) {
                        v1 = true;
                    }

                    return FileLoader.getPathToAttach(((TLObject)v0_2), v1);
                }

                if((arg4.media instanceof TL_messageMediaWebPage)) {
                    if(arg4.media.webpage.document != null) {
                        return FileLoader.getPathToAttach(arg4.media.webpage.document);
                    }

                    if(arg4.media.webpage.photo == null) {
                        goto label_84;
                    }

                    v4 = arg4.media.webpage.photo.sizes;
                    if(v4.size() <= 0) {
                        goto label_84;
                    }

                    v4_1 = FileLoader.getClosestPhotoSizeWithSize(v4, AndroidUtilities.getPhotoSize());
                    if(v4_1 == null) {
                        goto label_84;
                    }

                    return FileLoader.getPathToAttach(((TLObject)v4_1));
                }

                if(!(arg4.media instanceof TL_messageMediaInvoice)) {
                    goto label_84;
                }

                return FileLoader.getPathToAttach(arg4.media.photo, true);
            }
        }
        else if(arg4.action.photo != null) {
            v4 = arg4.action.photo.sizes;
            if(v4.size() > 0) {
                v4_1 = FileLoader.getClosestPhotoSizeWithSize(v4, AndroidUtilities.getPhotoSize());
                if(v4_1 != null) {
                    return FileLoader.getPathToAttach(((TLObject)v4_1));
                }
            }
        }

    label_84:
        return new File("");
    }

    private LinkedList getPhotoLoadOperationQueue(int arg3) {
        Object v0 = this.photoLoadOperationQueues.get(arg3);
        if(v0 == null) {
            LinkedList v0_1 = new LinkedList();
            this.photoLoadOperationQueues.put(arg3, v0_1);
        }

        return ((LinkedList)v0);
    }

    public static FileStreamLoadOperation getStreamLoadOperation(TransferListener arg1) {
        return new FileStreamLoadOperation(arg1);
    }

    public boolean isLoadingFile(String arg2) {
        return this.loadOperationPathsUI.containsKey(arg2);
    }

    public void loadFile(PhotoSize arg10, String arg11, int arg12) {
        int v8;
        if(arg10 == null) {
            return;
        }

        if(arg12 != 0 || arg10 == null) {
        label_12:
            v8 = arg12;
        }
        else {
            if(arg10.size != 0 && arg10.location.key == null) {
                goto label_12;
            }

            v8 = 1;
        }

        this.loadFile(null, null, null, arg10.location, arg11, arg10.size, false, v8);
    }

    public void loadFile(Document arg10, boolean arg11, int arg12) {
        if(arg10 == null) {
            return;
        }

        int v8 = arg12 != 0 || arg10 == null || arg10.key == null ? arg12 : 1;
        this.loadFile(arg10, null, null, null, null, 0, arg11, v8);
    }

    public void loadFile(FileLocation arg10, String arg11, int arg12, int arg13) {
        int v8;
        if(arg10 == null) {
            return;
        }

        if(arg13 == 0) {
            if(arg12 != 0) {
                if(arg10 == null) {
                }
                else if(arg10.key != null) {
                    goto label_7;
                }

                goto label_10;
            }

        label_7:
            v8 = 1;
        }
        else {
        label_10:
            v8 = arg13;
        }

        this.loadFile(null, null, null, arg10, arg11, arg12, true, v8);
    }

    private void loadFile(Document arg14, SecureDocument arg15, WebFile arg16, FileLocation arg17, String arg18, int arg19, boolean arg20, int arg21) {
        CharSequence v0_1;
        String v0;
        if(arg17 != null) {
            v0 = FileLoader.getAttachFileName(((TLObject)arg17), arg18);
        }
        else if(arg14 != null) {
            v0 = FileLoader.getAttachFileName(((TLObject)arg14));
        }
        else if(arg16 != null) {
            v0 = FileLoader.getAttachFileName(((TLObject)arg16));
        }
        else {
            v0_1 = null;
        }

        if(!TextUtils.isEmpty(v0_1) && !((String)v0_1).contains("-2147483648")) {
            this.loadOperationPathsUI.put(v0_1, Boolean.valueOf(true));
        }

        FileLoader.fileLoaderQueue.postRunnable(new Runnable(arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21) {
            public void run() {
                FileLoader.this.loadFileInternal(this.val$document, this.val$secureDocument, this.val$webDocument, this.val$location, this.val$locationExt, this.val$locationSize, this.val$force, null, 0, this.val$cacheType);
            }
        });
    }

    public void loadFile(SecureDocument arg10, boolean arg11) {
        if(arg10 == null) {
            return;
        }

        this.loadFile(null, arg10, null, null, null, 0, arg11, 1);
    }

    public void loadFile(WebFile arg10, boolean arg11, int arg12) {
        this.loadFile(null, null, arg10, null, null, 0, arg11, arg12);
    }

    private FileLoadOperation loadFileInternal(Document arg18, SecureDocument arg19, WebFile arg20, FileLocation arg21, String arg22, int arg23, boolean arg24, FileStreamLoadOperation arg25, int arg26, int arg27) {
        int v4_2;
        File v0_3;
        SparseIntArray v3_2;
        SparseIntArray v0_2;
        LinkedList v0_1;
        LinkedList v3_1;
        String v13;
        String v3;
        FileLoader v7 = this;
        Document v8 = arg18;
        SecureDocument v0 = arg19;
        WebFile v9 = arg20;
        FileLocation v10 = arg21;
        FileStreamLoadOperation v11 = arg25;
        int v12 = arg26;
        int v1 = arg27;
        FileLoadOperation v2 = null;
        if(v10 != null) {
            v3 = FileLoader.getAttachFileName(((TLObject)arg21), arg22);
            goto label_11;
        }
        else if(v0 != null) {
            v3 = FileLoader.getAttachFileName(((TLObject)arg19));
            goto label_11;
        }
        else if(v8 != null) {
            v3 = FileLoader.getAttachFileName(((TLObject)arg18));
            goto label_11;
        }
        else if(v9 != null) {
            v3 = FileLoader.getAttachFileName(((TLObject)arg20));
        label_11:
            v13 = v3;
        }
        else {
            v13 = ((String)v2);
        }

        if(v13 != null) {
            if(v13.contains("-2147483648")) {
            }
            else {
                if(!TextUtils.isEmpty(((CharSequence)v13)) && !v13.contains("-2147483648")) {
                    v7.loadOperationPathsUI.put(v13, Boolean.valueOf(true));
                }

                Object v2_1 = v7.loadOperationPaths.get(v13);
                if(v2_1 != null) {
                    if(v12 != 0 || (arg24)) {
                        v1 = ((FileLoadOperation)v2_1).getDatacenterId();
                        v3_1 = v7.getAudioLoadOperationQueue(v1);
                        LinkedList v4 = v7.getPhotoLoadOperationQueue(v1);
                        LinkedList v5 = v7.getLoadOperationQueue(v1);
                        ((FileLoadOperation)v2_1).setForceRequest(true);
                        if((MessageObject.isVoiceDocument(arg18)) || (MessageObject.isVoiceWebDocument(arg20))) {
                            v0_1 = v3_1;
                        }
                        else {
                            if(v0 == null && v10 == null) {
                                if(MessageObject.isImageWebDocument(arg20)) {
                                }
                                else {
                                    v0_1 = v5;
                                    goto label_63;
                                }
                            }

                            v0_1 = v4;
                        }

                    label_63:
                        if(v0_1 == null) {
                            goto label_109;
                        }

                        int v8_1 = v0_1.indexOf(v2_1);
                        if(v8_1 > 0) {
                            v0_1.remove(v8_1);
                            if(v12 != 0) {
                                if(v0_1 == v3_1) {
                                    if(((FileLoadOperation)v2_1).start(v11, v12)) {
                                        v0_2 = v7.currentAudioLoadOperationsCount;
                                        v3_2 = v7.currentAudioLoadOperationsCount;
                                    }
                                    else {
                                        goto label_109;
                                    }
                                }
                                else if(v0_1 != v4) {
                                    goto label_83;
                                }
                                else if(((FileLoadOperation)v2_1).start(v11, v12)) {
                                    v0_2 = v7.currentPhotoLoadOperationsCount;
                                    v3_2 = v7.currentPhotoLoadOperationsCount;
                                }
                                else {
                                    goto label_109;
                                }

                                v0_2.put(v1, v3_2.get(v1) + 1);
                                goto label_109;
                            label_83:
                                if(((FileLoadOperation)v2_1).start(v11, v12)) {
                                    v7.currentLoadOperationsCount.put(v1, v7.currentLoadOperationsCount.get(v1) + 1);
                                }

                                if(!((FileLoadOperation)v2_1).wasStarted()) {
                                    goto label_109;
                                }

                                if(v7.activeFileLoadOperation.contains(v2_1)) {
                                    goto label_109;
                                }

                                if(v11 == null) {
                                    goto label_107;
                                }

                                v7.pauseCurrentFileLoadOperations(((FileLoadOperation)v2_1));
                            }
                            else {
                                v0_1.add(0, v2_1);
                                goto label_109;
                            }
                        }
                        else {
                            if(v11 != null) {
                                v7.pauseCurrentFileLoadOperations(((FileLoadOperation)v2_1));
                            }

                            ((FileLoadOperation)v2_1).start(v11, v12);
                            if(v0_1 != v5) {
                                goto label_109;
                            }

                            if(v7.activeFileLoadOperation.contains(v2_1)) {
                                goto label_109;
                            }
                        }

                    label_107:
                        v7.activeFileLoadOperation.add(v2_1);
                    }

                label_109:
                    return ((FileLoadOperation)v2_1);
                }

                int v3_3 = 4;
                File v4_1 = FileLoader.getDirectory(v3_3);
                int v5_1 = 2;
                if(v0 != null) {
                    v2 = new FileLoadOperation(v0);
                    goto label_117;
                }
                else {
                    if(v10 != null) {
                        v2 = new FileLoadOperation(v10, arg22, arg23);
                    }
                    else {
                        if(v8 != null) {
                            v2 = new FileLoadOperation(v8);
                            if(MessageObject.isVoiceDocument(arg18)) {
                                goto label_131;
                            }
                            else if(!MessageObject.isVideoDocument(arg18)) {
                                goto label_117;
                            }
                        }
                        else if(v9 != null) {
                            v2 = new FileLoadOperation(v7.currentAccount, v9);
                            if(MessageObject.isVoiceWebDocument(arg20)) {
                            label_131:
                                v3_3 = 1;
                                goto label_150;
                            }
                            else if(MessageObject.isVideoWebDocument(arg20)) {
                            }
                            else {
                                goto label_147;
                            }
                        }
                        else {
                            goto label_150;
                        }

                        v3_3 = 2;
                        goto label_150;
                    label_147:
                        if(!MessageObject.isImageWebDocument(arg20)) {
                            goto label_117;
                        }
                    }

                    v3_3 = 0;
                    goto label_150;
                label_117:
                    v3_3 = 3;
                }

            label_150:
                if(v1 == 0) {
                    v0_3 = FileLoader.getDirectory(v3_3);
                }
                else {
                    if(v1 == v5_1) {
                        v2.setEncryptFile(true);
                    }

                    v0_3 = v4_1;
                }

                v2.setPaths(v7.currentAccount, v0_3, v4_1);
                int v16 = v3_3;
                Object v14 = v2;
                ((FileLoadOperation)v14).setDelegate(new FileLoadOperationDelegate(v13, v3_3, arg18, arg20, arg21) {
                    public void didChangedLoadProgress(FileLoadOperation arg2, float arg3) {
                        if(FileLoader.this.delegate != null) {
                            FileLoader.this.delegate.fileLoadProgressChanged(this.val$finalFileName, arg3);
                        }
                    }

                    public void didFailedLoadingFile(FileLoadOperation arg9, int arg10) {
                        FileLoader.this.loadOperationPathsUI.remove(this.val$finalFileName);
                        FileLoader.this.checkDownloadQueue(arg9.getDatacenterId(), this.val$document, this.val$webDocument, this.val$location, this.val$finalFileName);
                        if(FileLoader.this.delegate != null) {
                            FileLoader.this.delegate.fileDidFailedLoad(this.val$finalFileName, arg10);
                        }
                    }

                    public void didFinishLoadingFile(FileLoadOperation arg10, File arg11) {
                        FileLoader.this.loadOperationPathsUI.remove(this.val$finalFileName);
                        if(FileLoader.this.delegate != null) {
                            FileLoader.this.delegate.fileDidLoaded(this.val$finalFileName, arg11, this.val$finalType);
                        }

                        FileLoader.this.checkDownloadQueue(arg10.getDatacenterId(), this.val$document, this.val$webDocument, this.val$location, this.val$finalFileName);
                    }
                });
                ((FileLoadOperation)v14).setDelegate(new FileLoadOperationDelegate(v13, v3_3, arg18, arg21, arg20) {
                    public void didChangedLoadProgress(FileLoadOperation arg3, float arg4) {
                        String v1;
                        Object v0;
                        Iterator v3;
                        if(FileLoader.this.delegate != null) {
                            FileLoader.this.delegate.fileLoadProgressChanged(this.val$finalFileName, arg4);
                        }

                        if(FileLoader.this.delegates != null) {
                            try {
                                v3 = FileLoader.this.delegates.iterator();
                                while(true) {
                                label_13:
                                    if(v3.hasNext()) {
                                        v0 = v3.next();
                                        break;
                                    }

                                    goto label_19;
                                }
                            }
                            catch(Exception ) {
                                goto label_19;
                            }

                            try {
                                v1 = this.val$finalFileName;
                            }
                            catch(Exception ) {
                                goto label_19;
                            }
                            catch(Exception ) {
                                goto label_13;
                            }

                            try {
                                ((FileLoaderDelegate)v0).fileLoadProgressChanged(v1, arg4);
                            }
                            catch(Exception ) {
                            }

                            goto label_13;
                        }

                    label_19:
                        __monitor_enter(this);
                        try {
                            if(FileLoader.this.customDelegates != null) {
                                v3 = FileLoader.this.customDelegates.iterator();
                                while(v3.hasNext()) {
                                    v3.next().fileLoadProgressChanged(this.val$document, this.val$location);
                                }
                            }

                            __monitor_exit(this);
                            return;
                        label_36:
                            __monitor_exit(this);
                        }
                        catch(Throwable v3_1) {
                            goto label_36;
                        }

                        throw v3_1;
                    }

                    public void didFailedLoadingFile(FileLoadOperation arg7, int arg8) {
                        String v1;
                        Object v0;
                        Iterator v7;
                        FileLoader.this.checkDownloadQueue(arg7.getDatacenterId(), this.val$document, this.val$webDocument, this.val$location, this.val$finalFileName);
                        if(FileLoader.this.delegate != null) {
                            FileLoader.this.delegate.fileDidFailedLoad(this.val$finalFileName, arg8);
                        }

                        if(FileLoader.this.delegates != null) {
                            try {
                                v7 = FileLoader.this.delegates.iterator();
                                while(true) {
                                label_20:
                                    if(v7.hasNext()) {
                                        v0 = v7.next();
                                        break;
                                    }

                                    goto label_26;
                                }
                            }
                            catch(Exception ) {
                                goto label_26;
                            }

                            try {
                                v1 = this.val$finalFileName;
                            }
                            catch(Exception ) {
                                goto label_26;
                            }
                            catch(Exception ) {
                                goto label_20;
                            }

                            try {
                                ((FileLoaderDelegate)v0).fileDidFailedLoad(v1, arg8);
                            }
                            catch(Exception ) {
                            }

                            goto label_20;
                        }

                    label_26:
                        __monitor_enter(this);
                        try {
                            if(FileLoader.this.customDelegates != null) {
                                v7 = FileLoader.this.customDelegates.iterator();
                                while(v7.hasNext()) {
                                    v7.next().fileDidFailedLoad(this.val$document, this.val$location);
                                }
                            }

                            __monitor_exit(this);
                            return;
                        label_43:
                            __monitor_exit(this);
                        }
                        catch(Throwable v7_1) {
                            goto label_43;
                        }

                        throw v7_1;
                    }

                    public void didFinishLoadingFile(FileLoadOperation arg7, File arg8) {
                        String v2;
                        Object v1;
                        if(FileLoader.this.delegate != null) {
                            FileLoader.this.delegate.fileDidLoaded(this.val$finalFileName, arg8, this.val$finalType);
                        }

                        if(FileLoader.this.delegates != null) {
                            try {
                                Iterator v0 = FileLoader.this.delegates.iterator();
                                while(true) {
                                label_14:
                                    if(v0.hasNext()) {
                                        v1 = v0.next();
                                        break;
                                    }

                                    goto label_21;
                                }
                            }
                            catch(Exception ) {
                                goto label_21;
                            }

                            try {
                                v2 = this.val$finalFileName;
                            }
                            catch(Exception ) {
                                goto label_21;
                            }
                            catch(Exception ) {
                                goto label_14;
                            }

                            try {
                                ((FileLoaderDelegate)v1).fileDidLoaded(v2, arg8, this.val$finalType);
                            }
                            catch(Exception ) {
                            }

                            goto label_14;
                        }

                    label_21:
                        __monitor_enter(this);
                        try {
                            if(FileLoader.this.customDelegates != null) {
                                Iterator v8 = FileLoader.this.customDelegates.iterator();
                                while(v8.hasNext()) {
                                    v8.next().fileDidLoaded(this.val$document, this.val$location);
                                }
                            }

                            __monitor_exit(this);
                        }
                        catch(Throwable v7) {
                            try {
                            label_45:
                                __monitor_exit(this);
                            }
                            catch(Throwable v7) {
                                goto label_45;
                            }

                            throw v7;
                        }

                        FileLoader.this.checkDownloadQueue(arg7.getDatacenterId(), this.val$document, this.val$webDocument, this.val$location, this.val$finalFileName);
                    }
                });
                int v0_4 = ((FileLoadOperation)v14).getDatacenterId();
                LinkedList v1_1 = v7.getAudioLoadOperationQueue(v0_4);
                LinkedList v2_2 = v7.getPhotoLoadOperationQueue(v0_4);
                v3_1 = v7.getLoadOperationQueue(v0_4);
                v7.loadOperationPaths.put(v13, v14);
                if(arg24) {
                    v5_1 = v16;
                    v4_2 = 3;
                }
                else {
                    v5_1 = v16;
                    v4_2 = 1;
                }

                if(v5_1 == 1) {
                    int v2_3 = v7.currentAudioLoadOperationsCount.get(v0_4);
                    if(v12 == 0) {
                        if(v2_3 < v4_2) {
                        }
                        else {
                            if(arg24) {
                                v1_1.add(0, v14);
                            }
                            else {
                                v1_1.add(v14);
                            }

                            goto label_256;
                        }
                    }

                    if(!((FileLoadOperation)v14).start(v11, v12)) {
                        goto label_256;
                    }

                    v7.currentAudioLoadOperationsCount.put(v0_4, v2_3 + 1);
                }
                else {
                    if(v10 == null) {
                        if(MessageObject.isImageWebDocument(arg20)) {
                        }
                        else {
                            v1 = v7.currentLoadOperationsCount.get(v0_4);
                            if(v12 == 0) {
                                if(v1 < v4_2) {
                                }
                                else {
                                    if(arg24) {
                                        v3_1.add(0, v14);
                                    }
                                    else {
                                        v3_1.add(v14);
                                    }

                                    goto label_256;
                                }
                            }

                            if(((FileLoadOperation)v14).start(v11, v12)) {
                                v7.currentLoadOperationsCount.put(v0_4, v1 + 1);
                                v7.activeFileLoadOperation.add(v14);
                            }

                            if(!((FileLoadOperation)v14).wasStarted()) {
                                goto label_256;
                            }

                            if(v11 == null) {
                                goto label_256;
                            }

                            v7.pauseCurrentFileLoadOperations(((FileLoadOperation)v14));
                            goto label_256;
                        }
                    }

                    v1 = v7.currentPhotoLoadOperationsCount.get(v0_4);
                    if(v12 == 0) {
                        if(v1 < v4_2) {
                        }
                        else {
                            if(arg24) {
                                v2_2.add(0, v14);
                            }
                            else {
                                v2_2.add(v14);
                            }

                            goto label_256;
                        }
                    }

                    if(!((FileLoadOperation)v14).start(v11, v12)) {
                        goto label_256;
                    }

                    v7.currentPhotoLoadOperationsCount.put(v0_4, v1 + 1);
                }

            label_256:
                return ((FileLoadOperation)v14);
            }
        }

        return v2;
    }

    protected FileLoadOperation loadStreamFile(FileStreamLoadOperation arg12, Document arg13, int arg14) {
        CountDownLatch v7 = new CountDownLatch(1);
        FileLoadOperation[] v8 = new FileLoadOperation[1];
        FileLoader.fileLoaderQueue.postRunnable(new Runnable(v8, arg13, arg12, arg14, v7) {
            public void run() {
                this.val$result[0] = FileLoader.this.loadFileInternal(this.val$document, null, null, null, null, 0, true, this.val$stream, this.val$offset, 0);
                this.val$semaphore.countDown();
            }
        });
        try {
            v7.await();
        }
        catch(Exception v12) {
            FileLog.e(((Throwable)v12));
        }

        return v8[0];
    }

    public void onNetworkChanged(boolean arg3) {
        FileLoader.fileLoaderQueue.postRunnable(new Runnable(arg3) {
            public void run() {
                Iterator v0 = FileLoader.this.uploadOperationPaths.entrySet().iterator();
                while(v0.hasNext()) {
                    v0.next().getValue().onNetworkChanged(this.val$slow);
                }

                v0 = FileLoader.this.uploadOperationPathsEnc.entrySet().iterator();
                while(v0.hasNext()) {
                    v0.next().getValue().onNetworkChanged(this.val$slow);
                }
            }
        });
    }

    private void pauseCurrentFileLoadOperations(FileLoadOperation arg6) {
        int v1;
        for(v1 = 0; v1 < this.activeFileLoadOperation.size(); ++v1) {
            Object v2 = this.activeFileLoadOperation.get(v1);
            if((((FileLoadOperation)v2)) == arg6) {
            }
            else {
                this.activeFileLoadOperation.remove(v2);
                --v1;
                ((FileLoadOperation)v2).pause();
                int v3 = ((FileLoadOperation)v2).getDatacenterId();
                this.getLoadOperationQueue(v3).add(0, v2);
                if(((FileLoadOperation)v2).wasStarted()) {
                    this.currentLoadOperationsCount.put(v3, this.currentLoadOperationsCount.get(v3) - 1);
                }
            }
        }
    }

    public void setDelegate(FileLoaderDelegate arg1) {
        this.delegate = arg1;
    }

    public static void setMediaDirs(SparseArray arg0) {
        FileLoader.mediaDirs = arg0;
    }

    public static String slsGetAttachFileName(TLObject arg4, String arg5) {
        int v1;
        if((arg4 instanceof Document)) {
            arg5 = FileLoader.getDocumentFileName(((Document)arg4));
            int v0 = -1;
            if(arg5 != null) {
                v1 = arg5.lastIndexOf(46);
                if(v1 == v0) {
                    goto label_11;
                }
                else {
                    arg5 = arg5.substring(v1);
                }
            }
            else {
            label_11:
                arg5 = "";
            }

            if(arg5.length() <= 1) {
                if(((Document)arg4).mime_type != null) {
                    arg5 = ((Document)arg4).mime_type;
                    v1 = arg5.hashCode();
                    if(v1 != 187090231) {
                        if(v1 != 187091926) {
                            if(v1 != 1331848029) {
                                if(v1 != 1504831518) {
                                }
                                else if(arg5.equals("audio/mpeg")) {
                                    v0 = 3;
                                }
                            }
                            else if(arg5.equals("video/mp4")) {
                                v0 = 0;
                            }
                        }
                        else if(arg5.equals("audio/ogg")) {
                            v0 = 1;
                        }
                    }
                    else if(arg5.equals("audio/mp3")) {
                        v0 = 2;
                    }

                    switch(v0) {
                        case 0: {
                            goto label_53;
                        }
                        case 1: {
                            goto label_51;
                        }
                        case 2: 
                        case 3: {
                            goto label_49;
                        }
                    }

                    goto label_55;
                label_49:
                    arg5 = ".mp3";
                    goto label_56;
                label_51:
                    arg5 = ".ogg";
                    goto label_56;
                label_53:
                    arg5 = ".mp4";
                    goto label_56;
                }

            label_55:
                arg5 = "";
            }

        label_56:
            if(((Document)arg4).version == 0) {
                if(arg5.length() > 1) {
                    return ((Document)arg4).dc_id + "_" + ((Document)arg4).id + arg5;
                }

                return ((Document)arg4).dc_id + "_" + ((Document)arg4).id;
            }

            if(arg5.length() > 1) {
                return ((Document)arg4).dc_id + "_" + ((Document)arg4).id + "_" + ((Document)arg4).version + arg5;
            }

            return ((Document)arg4).dc_id + "_" + ((Document)arg4).id + "_" + ((Document)arg4).version;
        }

        if((arg4 instanceof TL_webDocument)) {
            return Utilities.MD5(((TL_webDocument)arg4).url) + "." + ImageLoader.getHttpUrlExtension(((TL_webDocument)arg4).url, FileLoader.getExtensionByMime(((TL_webDocument)arg4).mime_type));
        }

        if((arg4 instanceof PhotoSize)) {
            if(((PhotoSize)arg4).location != null) {
                if((((PhotoSize)arg4).location instanceof TL_fileLocationUnavailable)) {
                }
                else {
                    v0_1 = new StringBuilder();
                    v0_1.append(((PhotoSize)arg4).location.volume_id);
                    v0_1.append("_");
                    v0_1.append(((PhotoSize)arg4).location.local_id);
                    v0_1.append(".");
                    if(arg5 != null) {
                    }
                    else {
                        arg5 = "jpg";
                    }

                    v0_1.append(arg5);
                    return v0_1.toString();
                }
            }

            return "";
        }

        if((arg4 instanceof FileLocation)) {
            if((arg4 instanceof TL_fileLocationUnavailable)) {
                return "";
            }

            v0_1 = new StringBuilder();
            v0_1.append(((FileLocation)arg4).volume_id);
            v0_1.append("_");
            v0_1.append(((FileLocation)arg4).local_id);
            v0_1.append(".");
            if(arg5 != null) {
            }
            else {
                arg5 = "jpg";
            }

            v0_1.append(arg5);
            return v0_1.toString();
        }

        return "";
    }

    public void uploadFile(String arg7, boolean arg8, boolean arg9, int arg10) {
        this.uploadFile(arg7, arg8, arg9, 0, arg10);
    }

    public void uploadFile(String arg10, boolean arg11, boolean arg12, int arg13, int arg14) {
        if(arg10 == null) {
            return;
        }

        FileLoader.fileLoaderQueue.postRunnable(new Runnable(arg11, arg10, arg13, arg14, arg12) {
            public void run() {
                LinkedList v1_1;
                int v7;
                if(this.val$encrypted) {
                    if(FileLoader.this.uploadOperationPathsEnc.containsKey(this.val$location)) {
                        return;
                    }
                }
                else if(FileLoader.this.uploadOperationPaths.containsKey(this.val$location)) {
                    return;
                }

                int v0 = this.val$estimatedSize;
                if(v0 == 0 || FileLoader.this.uploadSizes.get(this.val$location) == null) {
                    v7 = v0;
                }
                else {
                    FileLoader.this.uploadSizes.remove(this.val$location);
                    v7 = 0;
                }

                FileUploadOperation v0_1 = new FileUploadOperation(FileLoader.this.currentAccount, this.val$location, this.val$encrypted, v7, this.val$type);
                ConcurrentHashMap v1 = this.val$encrypted ? FileLoader.this.uploadOperationPathsEnc : FileLoader.this.uploadOperationPaths;
                v1.put(this.val$location, v0_1);
                v0_1.setDelegate(new FileUploadOperationDelegate() {
                    public void didChangedUploadProgress(FileUploadOperation arg3, float arg4) {
                        if(this.this$1.this$0.delegate != null) {
                            this.this$1.this$0.delegate.fileUploadProgressChanged(this.this$1.val$location, arg4, this.this$1.val$encrypted);
                        }
                    }

                    public void didFailedUploadingFile(FileUploadOperation arg2) {
                        FileLoader.fileLoaderQueue.postRunnable(new Runnable() {
                            public void run() {
                                Object v0_1;
                                ConcurrentHashMap v0 = this.this$2.this$1.val$encrypted ? this.this$2.this$1.this$0.uploadOperationPathsEnc : this.this$2.this$1.this$0.uploadOperationPaths;
                                v0.remove(this.this$2.this$1.val$location);
                                if(this.this$2.this$1.this$0.delegate != null) {
                                    this.this$2.this$1.this$0.delegate.fileDidFailedUpload(this.this$2.this$1.val$location, this.this$2.this$1.val$encrypted);
                                }

                                if(this.this$2.this$1.val$small) {
                                    FileLoader.access$610(this.this$2.this$1.this$0);
                                    if(this.this$2.this$1.this$0.currentUploadSmallOperationsCount < 1) {
                                        v0_1 = this.this$2.this$1.this$0.uploadSmallOperationQueue.poll();
                                        if(v0_1 != null) {
                                            FileLoader.access$608(this.this$2.this$1.this$0);
                                            goto label_78;
                                        }
                                    }
                                }
                                else {
                                    FileLoader.access$710(this.this$2.this$1.this$0);
                                    if(this.this$2.this$1.this$0.currentUploadOperationsCount < 1) {
                                        v0_1 = this.this$2.this$1.this$0.uploadOperationQueue.poll();
                                        if(v0_1 != null) {
                                            FileLoader.access$708(this.this$2.this$1.this$0);
                                        label_78:
                                            ((FileUploadOperation)v0_1).start();
                                        }
                                    }
                                }
                            }
                        });
                    }

                    public void didFinishUploadingFile(FileUploadOperation arg10, InputFile arg11, InputEncryptedFile arg12, byte[] arg13, byte[] arg14) {
                        FileLoader.fileLoaderQueue.postRunnable(new Runnable(arg11, arg12, arg13, arg14, arg10) {
                            public void run() {
                                Object v0_1;
                                ConcurrentHashMap v0 = this.this$2.this$1.val$encrypted ? this.this$2.this$1.this$0.uploadOperationPathsEnc : this.this$2.this$1.this$0.uploadOperationPaths;
                                v0.remove(this.this$2.this$1.val$location);
                                if(this.this$2.this$1.val$small) {
                                    FileLoader.access$610(this.this$2.this$1.this$0);
                                    if(this.this$2.this$1.this$0.currentUploadSmallOperationsCount < 1) {
                                        v0_1 = this.this$2.this$1.this$0.uploadSmallOperationQueue.poll();
                                        if(v0_1 != null) {
                                            FileLoader.access$608(this.this$2.this$1.this$0);
                                            goto label_62;
                                        }
                                    }
                                }
                                else {
                                    FileLoader.access$710(this.this$2.this$1.this$0);
                                    if(this.this$2.this$1.this$0.currentUploadOperationsCount < 1) {
                                        v0_1 = this.this$2.this$1.this$0.uploadOperationQueue.poll();
                                        if(v0_1 != null) {
                                            FileLoader.access$708(this.this$2.this$1.this$0);
                                        label_62:
                                            ((FileUploadOperation)v0_1).start();
                                        }
                                    }
                                }

                                if(this.this$2.this$1.this$0.delegate != null) {
                                    this.this$2.this$1.this$0.delegate.fileDidUploaded(this.this$2.this$1.val$location, this.val$inputFile, this.val$inputEncryptedFile, this.val$key, this.val$iv, this.val$operation.getTotalFileSize());
                                }
                            }
                        });
                    }
                });
                if(this.val$small) {
                    if(FileLoader.this.currentUploadSmallOperationsCount < 1) {
                        FileLoader.access$608(FileLoader.this);
                        goto label_59;
                    }
                    else {
                        v1_1 = FileLoader.this.uploadSmallOperationQueue;
                        goto label_63;
                    }
                }
                else if(FileLoader.this.currentUploadOperationsCount < 1) {
                    FileLoader.access$708(FileLoader.this);
                label_59:
                    v0_1.start();
                }
                else {
                    v1_1 = FileLoader.this.uploadOperationQueue;
                label_63:
                    v1_1.add(v0_1);
                }
            }
        });
    }
}

