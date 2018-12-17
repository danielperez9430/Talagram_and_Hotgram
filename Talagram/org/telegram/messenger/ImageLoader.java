package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.Environment;
import android.provider.MediaStore$Images$Thumbnails;
import android.provider.MediaStore$Video$Thumbnails;
import android.support.d.a;
import android.text.TextUtils;
import android.util.SparseArray;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel$MapMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import org.telegram.messenger.secretmedia.EncryptedFileInputStream;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Photo;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_documentEncrypted;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_fileEncryptedLocation;
import org.telegram.tgnet.TLRPC$TL_fileLocation;
import org.telegram.tgnet.TLRPC$TL_fileLocationUnavailable;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_photoCachedSize;
import org.telegram.tgnet.TLRPC$TL_photoSize;
import org.telegram.tgnet.TLRPC$TL_upload_getWebFile;
import org.telegram.ui.Components.AnimatedFileDrawable;

public class ImageLoader {
    class CacheImage {
        protected boolean animatedFile;
        protected CacheOutTask cacheTask;
        protected int currentAccount;
        protected File encryptionKeyPath;
        protected String ext;
        protected String filter;
        protected ArrayList filters;
        protected File finalFilePath;
        protected HttpImageTask httpTask;
        protected String httpUrl;
        protected ArrayList imageReceiverArray;
        protected String key;
        protected ArrayList keys;
        protected TLObject location;
        protected SecureDocument secureDocument;
        protected boolean selfThumb;
        protected File tempFilePath;
        protected ArrayList thumbs;
        protected String url;

        CacheImage(ImageLoader arg1, org.telegram.messenger.ImageLoader$1 arg2) {
            this(arg1);
        }

        private CacheImage(ImageLoader arg1) {
            ImageLoader.this = arg1;
            super();
            this.imageReceiverArray = new ArrayList();
            this.keys = new ArrayList();
            this.filters = new ArrayList();
            this.thumbs = new ArrayList();
        }

        public void addImageReceiver(ImageReceiver arg2, String arg3, String arg4, boolean arg5) {
            if(this.imageReceiverArray.contains(arg2)) {
                return;
            }

            this.imageReceiverArray.add(arg2);
            this.keys.add(arg3);
            this.filters.add(arg4);
            this.thumbs.add(Boolean.valueOf(arg5));
            ImageLoader.this.imageLoadingByTag.put(arg2.getTag(arg5), this);
        }

        public static void lambda$setImageAndClear$0(CacheImage arg7, BitmapDrawable arg8, ArrayList arg9) {
            int v0;
            if((arg8 instanceof AnimatedFileDrawable)) {
                v0 = 0;
                int v2 = 0;
                while(v0 < arg9.size()) {
                    Object v3 = arg9.get(v0);
                    if(v0 == 0) {
                        BitmapDrawable v4 = arg8;
                    }
                    else {
                        AnimatedFileDrawable v4_1 = ((AnimatedFileDrawable)arg8).makeCopy();
                    }

                    if(((ImageReceiver)v3).setImageBitmapByKey(v4, arg7.key, arg7.selfThumb, false)) {
                        v2 = 1;
                    }

                    ++v0;
                }

                if(v2 != 0) {
                    return;
                }

                ((AnimatedFileDrawable)arg8).recycle();
            }
            else {
                for(v0 = 0; v0 < arg9.size(); ++v0) {
                    arg9.get(v0).setImageBitmapByKey(arg8, arg7.key, arg7.selfThumb, false);
                }
            }
        }

        public void removeImageReceiver(ImageReceiver arg8) {
            Object v2_1;
            int v1 = 0;
            Boolean v2 = Boolean.valueOf(this.selfThumb);
            int v0;
            for(v0 = 0; v0 < this.imageReceiverArray.size(); ++v0) {
                Object v3 = this.imageReceiverArray.get(v0);
                if(v3 == null || (((ImageReceiver)v3)) == arg8) {
                    this.imageReceiverArray.remove(v0);
                    this.keys.remove(v0);
                    this.filters.remove(v0);
                    v2_1 = this.thumbs.remove(v0);
                    if(v3 != null) {
                        ImageLoader.this.imageLoadingByTag.remove(((ImageReceiver)v3).getTag(((Boolean)v2_1).booleanValue()));
                    }

                    --v0;
                }
            }

            if(this.imageReceiverArray.size() == 0) {
                while(v1 < this.imageReceiverArray.size()) {
                    ImageLoader.this.imageLoadingByTag.remove(this.imageReceiverArray.get(v1).getTag(((Boolean)v2_1).booleanValue()));
                    ++v1;
                }

                this.imageReceiverArray.clear();
                if(this.location != null && !ImageLoader.this.forceLoadingImages.containsKey(this.key)) {
                    if((this.location instanceof FileLocation)) {
                        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.location, this.ext);
                    }
                    else if((this.location instanceof Document)) {
                        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.location);
                    }
                    else if((this.location instanceof SecureDocument)) {
                        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.location);
                    }
                    else if((this.location instanceof WebFile)) {
                        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.location);
                    }
                }

                CacheOutTask v0_1 = null;
                if(this.cacheTask != null) {
                    DispatchQueue v8 = this.selfThumb ? ImageLoader.this.cacheThumbOutQueue : ImageLoader.this.cacheOutQueue;
                    v8.cancelRunnable(this.cacheTask);
                    this.cacheTask.cancel();
                    this.cacheTask = v0_1;
                }

                if(this.httpTask != null) {
                    ImageLoader.this.httpTasks.remove(this.httpTask);
                    this.httpTask.cancel(true);
                    this.httpTask = ((HttpImageTask)v0_1);
                }

                if(this.url != null) {
                    ImageLoader.this.imageLoadingByUrl.remove(this.url);
                }

                if(this.key == null) {
                    return;
                }

                ImageLoader.this.imageLoadingByKeys.remove(this.key);
            }
        }

        public void replaceImageReceiver(ImageReceiver arg4, String arg5, String arg6, boolean arg7) {
            int v0 = this.imageReceiverArray.indexOf(arg4);
            int v1 = -1;
            if(v0 == v1) {
                return;
            }

            if(this.thumbs.get(v0).booleanValue() != arg7) {
                v0 = this.imageReceiverArray.subList(v0 + 1, this.imageReceiverArray.size()).indexOf(arg4);
                if(v0 == v1) {
                    return;
                }
            }

            this.keys.set(v0, arg5);
            this.filters.set(v0, arg6);
        }

        public void setImageAndClear(BitmapDrawable arg4) {
            if(arg4 != null) {
                AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$CacheImage$-4d24Wn72OGrdz-PXIizp3MnMzk(this, arg4, new ArrayList(this.imageReceiverArray)));
            }

            int v4;
            for(v4 = 0; v4 < this.imageReceiverArray.size(); ++v4) {
                ImageLoader.this.imageLoadingByTag.remove(this.imageReceiverArray.get(v4).getTag(this.selfThumb));
            }

            this.imageReceiverArray.clear();
            if(this.url != null) {
                ImageLoader.this.imageLoadingByUrl.remove(this.url);
            }

            if(this.key != null) {
                ImageLoader.this.imageLoadingByKeys.remove(this.key);
            }
        }
    }

    class CacheOutTask implements Runnable {
        private CacheImage cacheImage;
        private boolean isCancelled;
        private Thread runningThread;
        private final Object sync;

        public CacheOutTask(ImageLoader arg1, CacheImage arg2) {
            ImageLoader.this = arg1;
            super();
            this.sync = new Object();
            this.cacheImage = arg2;
        }

        static CacheImage access$3500(CacheOutTask arg0) {
            return arg0.cacheImage;
        }

        public void cancel() {
            Object v0 = this.sync;
            __monitor_enter(v0);
            try {
                this.isCancelled = true;
                if(this.runningThread != null) {
                    this.runningThread.interrupt();
                }

                goto label_11;
            }
            catch(Throwable v1) {
            }
            catch(Exception ) {
                try {
                label_11:
                    __monitor_exit(v0);
                    return;
                }
                catch(Throwable v1) {
                label_10:
                    try {
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        goto label_10;
                    }

                    throw v1;
                }
            }
        }

        public static void lambda$null$0(CacheOutTask arg1, BitmapDrawable arg2) {
            arg1.cacheImage.setImageAndClear(arg2);
        }

        public static void lambda$onPostExecute$1(CacheOutTask arg2, BitmapDrawable arg3) {
            if((arg3 instanceof AnimatedFileDrawable)) {
            }
            else if(arg3 != null) {
                BitmapDrawable v0 = arg2.this$0.memCache.get(arg2.cacheImage.key);
                if(v0 == null) {
                    arg2.this$0.memCache.put(arg2.cacheImage.key, arg3);
                }
                else {
                    arg3.getBitmap().recycle();
                    arg3 = v0;
                }
            }
            else {
                arg3 = null;
            }

            arg2.this$0.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$CacheOutTask$2uCrAh_Iah20q58lGeE9-LJYzHo(arg2, arg3));
        }

        private void onPostExecute(BitmapDrawable arg2) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$CacheOutTask$7L8KR4H1H1vLf5rq1Xq1ityNPvY(this, arg2));
        }

        public void run() {
            Bitmap v4_2;
            EncryptedFileInputStream v0_10;
            BitmapFactory$Options v8_2;
            int v7_4;
            Bitmap v10_1;
            int v8_1;
            BitmapFactory$Options v5_4;
            MappedByteBuffer v3_6;
            RandomAccessFile v0_9;
            Bitmap v0_8;
            BitmapDrawable v11_6;
            Object v3_5;
            Bitmap v9_3;
            boolean v6_4;
            float v11_5;
            boolean v26;
            int v25;
            int v24;
            Bitmap v12_1;
            FileInputStream v3_4;
            Bitmap v3_2;
            int v3_1;
            int v11_4;
            byte[] v11_3;
            boolean v3;
            float v6_3;
            float v9_2;
            BitmapFactory$Options v2_8;
            int v13_4;
            Long v0_7;
            String v13_3;
            int v15;
            Long v13_2;
            int v14_1;
            Bitmap v11_1;
            int v23;
            int v22;
            int v21;
            int v20;
            int v19;
            byte[] v14;
            Bitmap v5_2;
            FileInputStream v2_6;
            Bitmap v2_4;
            Bitmap v7_3;
            MappedByteBuffer v5_1;
            RandomAccessFile v2_3;
            BitmapFactory$Options v13_1;
            Object v13;
            int v0_6;
            int v10;
            String v0_5;
            byte[] v0_4;
            Throwable v2_2;
            RandomAccessFile v9;
            SecureDocumentKey v7_1;
            byte[] v8;
            int v5;
            File v4_1;
            CacheOutTask v1 = this;
            Object v2 = v1.sync;
            __monitor_enter(v2);
            try {
                v1.runningThread = Thread.currentThread();
                Thread.interrupted();
                if(v1.isCancelled) {
                    __monitor_exit(v2);
                    return;
                }

                __monitor_exit(v2);
            }
            catch(Throwable v0) {
                try {
                label_887:
                    __monitor_exit(v2);
                }
                catch(Throwable v0) {
                    goto label_887;
                }

                throw v0;
            }

            boolean v2_1 = false;
            if(v1.cacheImage.animatedFile) {
                Object v4 = v1.sync;
                __monitor_enter(v4);
                try {
                    if(v1.isCancelled) {
                        __monitor_exit(v4);
                        return;
                    }

                    __monitor_exit(v4);
                }
                catch(Throwable v0) {
                    try {
                    label_40:
                        __monitor_exit(v4);
                    }
                    catch(Throwable v0) {
                        goto label_40;
                    }

                    throw v0;
                }

                v4_1 = v1.cacheImage.finalFilePath;
                if(v1.cacheImage.filter != null && (v1.cacheImage.filter.equals("d"))) {
                    v2_1 = true;
                }

                AnimatedFileDrawable v0_1 = new AnimatedFileDrawable(v4_1, v2_1);
                Thread.interrupted();
                v1.onPostExecute(((BitmapDrawable)v0_1));
                return;
            }

            v4_1 = v1.cacheImage.finalFilePath;
            if(v1.cacheImage.secureDocument == null) {
                if(v1.cacheImage.encryptionKeyPath != null && v4_1 != null && (v4_1.getAbsolutePath().endsWith(".enc"))) {
                    goto label_58;
                }

                v5 = 0;
            }
            else {
            label_58:
                v5 = 1;
            }

            Bitmap v6 = null;
            if(v1.cacheImage.secureDocument != null) {
                SecureDocumentKey v0_2 = v1.cacheImage.secureDocument.secureDocumentKey;
                byte[] v7 = v1.cacheImage.secureDocument.secureFile == null || v1.cacheImage.secureDocument.secureFile.file_hash == null ? v1.cacheImage.secureDocument.fileHash : v1.cacheImage.secureDocument.secureFile.file_hash;
                v8 = v7;
                v7_1 = v0_2;
            }
            else {
                v7_1 = ((SecureDocumentKey)v6);
                v8 = ((byte[])v7_1);
            }

            if(Build$VERSION.SDK_INT >= 19) {
                goto label_146;
            }

            try {
                v9 = new RandomAccessFile(v4_1, "r");
            }
            catch(Throwable v0) {
                v2_2 = v0;
                v9 = ((RandomAccessFile)v6);
                goto label_139;
            }
            catch(Exception v0_3) {
                v9 = ((RandomAccessFile)v6);
                goto label_132;
            }

            try {
                v0_4 = v1.cacheImage.selfThumb ? ImageLoader.headerThumb : ImageLoader.header;
                v9.readFully(v0_4, 0, v0_4.length);
                v0_5 = new String(v0_4).toLowerCase().toLowerCase();
                if(v0_5.startsWith("riff")) {
                    if(!v0_5.endsWith("webp")) {
                        goto label_114;
                    }

                    goto label_112;
                }
                else {
                    goto label_114;
                }
            }
            catch(Throwable v0) {
            }
            catch(Exception v0_3) {
            label_132:
                v10 = 0;
                goto label_133;
            label_112:
                v10 = 1;
                goto label_115;
            label_114:
                v10 = 0;
                try {
                label_115:
                    v9.close();
                    goto label_116;
                }
                catch(Throwable v0) {
                label_138:
                    v2_2 = v0;
                }
                catch(Exception v0_3) {
                    try {
                    label_133:
                        FileLog.e(((Throwable)v0_3));
                        if(v9 == null) {
                            goto label_147;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_138;
                    }

                    try {
                        v9.close();
                        goto label_147;
                    }
                    catch(Exception v0_3) {
                        goto label_120;
                    }
                }
            }

        label_139:
            if(v9 != null) {
                try {
                    v9.close();
                }
                catch(Exception v0_3) {
                    FileLog.e(v0_3);
                }
            }

            throw v2_2;
            try {
            label_116:
                v9.close();
            }
            catch(Exception v0_3) {
            label_120:
                FileLog.e(v0_3);
            }

            goto label_147;
        label_146:
            v10 = 0;
        label_147:
            int v9_1 = 21;
            long v11 = 0;
            if(v1.cacheImage.selfThumb) {
                if(v1.cacheImage.filter == null) {
                label_176:
                    v0_6 = 0;
                }
                else if(v1.cacheImage.filter.contains("b2")) {
                    v0_6 = 3;
                }
                else if(v1.cacheImage.filter.contains("b1")) {
                    v0_6 = 2;
                }
                else if(v1.cacheImage.filter.contains("b")) {
                    v0_6 = 1;
                }
                else {
                    goto label_176;
                }

                try {
                    v1.this$0.lastCacheOutTime = System.currentTimeMillis();
                    v13 = v1.sync;
                    __monitor_enter(v13);
                }
                catch(Throwable v0) {
                    goto label_356;
                }

                try {
                    if(v1.isCancelled) {
                        __monitor_exit(v13);
                        return;
                    }

                    __monitor_exit(v13);
                    goto label_187;
                }
                catch(Throwable v0) {
                    try {
                    label_353:
                        __monitor_exit(v13);
                    }
                    catch(Throwable v0) {
                        goto label_353;
                    }

                    try {
                        throw v0;
                    label_187:
                        v13_1 = new BitmapFactory$Options();
                        v13_1.inSampleSize = 1;
                        if(Build$VERSION.SDK_INT < v9_1) {
                            v13_1.inPurgeable = true;
                        }

                        if(v10 != 0) {
                            v2_3 = new RandomAccessFile(v4_1, "r");
                            v5_1 = v2_3.getChannel().map(FileChannel$MapMode.READ_ONLY, 0, v4_1.length());
                            BitmapFactory$Options v7_2 = new BitmapFactory$Options();
                            v7_2.inJustDecodeBounds = true;
                            Utilities.loadWebpImage(v6, ((ByteBuffer)v5_1), ((ByteBuffer)v5_1).limit(), v7_2, true);
                            v7_3 = Bitmaps.createBitmap(v7_2.outWidth, v7_2.outHeight, Bitmap$Config.ARGB_8888);
                        }
                        else {
                            goto label_221;
                        }
                    }
                    catch(Throwable v0) {
                        goto label_356;
                    }
                }

                try {
                    Utilities.loadWebpImage(v7_3, ((ByteBuffer)v5_1), ((ByteBuffer)v5_1).limit(), ((BitmapFactory$Options)v6), v13_1.inPurgeable ^ 1);
                    v2_3.close();
                    v2_4 = v7_3;
                    goto label_284;
                }
                catch(Throwable v0) {
                    v2_4 = v7_3;
                    goto label_357;
                }

                try {
                label_221:
                    if(!v13_1.inPurgeable) {
                        if(v7_1 != null) {
                        }
                        else {
                            if(v5 != 0) {
                                EncryptedFileInputStream v2_5 = new EncryptedFileInputStream(v4_1, v1.cacheImage.encryptionKeyPath);
                            }
                            else {
                                v2_6 = new FileInputStream(v4_1);
                            }

                            v5_2 = BitmapFactory.decodeStream(((InputStream)v2_6), ((Rect)v6), v13_1);
                            goto label_234;
                        }
                    }

                    goto label_240;
                }
                catch(Throwable v0) {
                    goto label_356;
                }

                try {
                label_234:
                    v2_6.close();
                    v2_4 = v5_2;
                    goto label_284;
                }
                catch(Throwable v0) {
                    v2_4 = v5_2;
                    goto label_357;
                }

                try {
                label_240:
                    v9 = new RandomAccessFile(v4_1, "r");
                    v10 = ((int)v9.length());
                    v14 = ImageLoader.bytesThumb == null || ImageLoader.bytesThumb.length < v10 ? ((byte[])v6) : ImageLoader.bytesThumb;
                    if(v14 == null) {
                        v14 = new byte[v10];
                        ImageLoader.bytesThumb = v14;
                    }

                    v9.readFully(v14, 0, v10);
                    v9.close();
                    if(v7_1 != null) {
                        EncryptedFileInputStream.decryptBytesWithKeyFile(v14, 0, v10, v7_1);
                        byte[] v5_3 = Utilities.computeSHA256(v14, 0, v10);
                        v5 = v8 == null || !Arrays.equals(v5_3, v8) ? 1 : 0;
                        int v2_7 = v14[0] & 255;
                        v10 -= v2_7;
                        int v27 = v5;
                        v5 = v2_7;
                        v2_7 = v27;
                    }
                    else {
                        if(v5 != 0) {
                            EncryptedFileInputStream.decryptBytesWithKeyFile(v14, 0, v10, v1.cacheImage.encryptionKeyPath);
                        }

                        v5 = 0;
                    }

                    if((((boolean)v2_7))) {
                        goto label_283;
                    }

                    v2_4 = BitmapFactory.decodeByteArray(v14, v5, v10, v13_1);
                    goto label_284;
                }
                catch(Throwable v0) {
                label_356:
                    v2_4 = v6;
                    goto label_357;
                }

            label_283:
                v2_4 = v6;
            label_284:
                if(v2_4 != null) {
                    goto label_294;
                }

                try {
                    if(v4_1.length() != v11 && v1.cacheImage.filter != null) {
                        goto label_358;
                    }

                    v4_1.delete();
                    goto label_358;
                label_294:
                    if(v0_6 == 1) {
                        if(v2_4.getConfig() == Bitmap$Config.ARGB_8888) {
                            v19 = 3;
                            v20 = v13_1.inPurgeable ^ 1;
                            v21 = v2_4.getWidth();
                            v22 = v2_4.getHeight();
                            v23 = v2_4.getRowBytes();
                        }
                        else {
                            goto label_358;
                        }
                    }
                    else if(v0_6 == 2) {
                        if(v2_4.getConfig() == Bitmap$Config.ARGB_8888) {
                            v19 = 1;
                            v20 = v13_1.inPurgeable ^ 1;
                            v21 = v2_4.getWidth();
                            v22 = v2_4.getHeight();
                            v23 = v2_4.getRowBytes();
                        }
                        else {
                            goto label_358;
                        }
                    }
                    else if(v0_6 != 3) {
                        goto label_347;
                    }
                    else if(v2_4.getConfig() == Bitmap$Config.ARGB_8888) {
                        Utilities.blurBitmap(v2_4, 7, v13_1.inPurgeable ^ 1, v2_4.getWidth(), v2_4.getHeight(), v2_4.getRowBytes());
                        Utilities.blurBitmap(v2_4, 7, v13_1.inPurgeable ^ 1, v2_4.getWidth(), v2_4.getHeight(), v2_4.getRowBytes());
                        v19 = 7;
                        v20 = v13_1.inPurgeable ^ 1;
                        v21 = v2_4.getWidth();
                        v22 = v2_4.getHeight();
                        v23 = v2_4.getRowBytes();
                    }
                    else {
                        goto label_358;
                    }

                    Utilities.blurBitmap(v2_4, v19, v20, v21, v22, v23);
                    goto label_358;
                label_347:
                    if(v0_6 != 0) {
                        goto label_358;
                    }

                    if(!v13_1.inPurgeable) {
                        goto label_358;
                    }

                    Utilities.pinBitmap(v2_4);
                    goto label_358;
                label_293:
                }
                catch(Throwable v0) {
                    goto label_293;
                }

            label_357:
                FileLog.e(v0);
            label_358:
                v11_1 = v6;
                goto label_878;
            }

            try {
                if(v1.cacheImage.httpUrl == null) {
                    goto label_429;
                }
            }
            catch(Throwable ) {
                v11_1 = v6;
                goto label_877;
            }

            try {
                if(v1.cacheImage.httpUrl.startsWith("thumb://")) {
                    v14_1 = 8;
                    v0_6 = v1.cacheImage.httpUrl.indexOf(":", v14_1);
                    if(v0_6 >= 0) {
                        v13_2 = Long.valueOf(Long.parseLong(v1.cacheImage.httpUrl.substring(v14_1, v0_6)));
                        v0_5 = v1.cacheImage.httpUrl.substring(v0_6 + 1);
                    }
                    else {
                        v0_5 = ((String)v6);
                        v13_2 = ((Long)v0_5);
                    }

                    v14_1 = 0;
                    v15 = 0;
                    Long v27_1 = v13_2;
                    v13_3 = v0_5;
                    v0_7 = v27_1;
                }
                else {
                    if(v1.cacheImage.httpUrl.startsWith("vthumb://")) {
                        v14_1 = 9;
                        v0_6 = v1.cacheImage.httpUrl.indexOf(":", v14_1);
                        if(v0_6 >= 0) {
                            v0_7 = Long.valueOf(Long.parseLong(v1.cacheImage.httpUrl.substring(v14_1, v0_6)));
                            v13_4 = 1;
                        }
                        else {
                            v0_7 = ((Long)v6);
                            v13_4 = 0;
                        }

                        v14_1 = v13_4;
                        v15 = 0;
                        v13_3 = ((String)v6);
                        goto label_433;
                    }

                    if(v1.cacheImage.httpUrl.startsWith("http")) {
                        goto label_429;
                    }

                    goto label_421;
                }

                goto label_433;
            }
            catch(Throwable ) {
                v2_4 = v6;
                v11_1 = v2_4;
                goto label_878;
            }

        label_421:
            v0_7 = ((Long)v6);
            v13_3 = ((String)v0_7);
            v14_1 = 0;
            v15 = 0;
            goto label_433;
        label_429:
            v0_7 = ((Long)v6);
            v13_3 = ((String)v0_7);
            v14_1 = 0;
            v15 = 1;
        label_433:
            int v6_1 = v0_7 != null ? 0 : 20;
            if(v6_1 != 0) {
                try {
                    if(v1.this$0.lastCacheOutTime != v11) {
                        v11 = ((long)v6_1);
                        if(v1.this$0.lastCacheOutTime > System.currentTimeMillis() - v11 && Build$VERSION.SDK_INT < v9_1) {
                            Thread.sleep(v11);
                        }
                    }
                }
                catch(Throwable ) {
                    goto label_452;
                }
            }

            try {
                v1.this$0.lastCacheOutTime = System.currentTimeMillis();
                v2 = v1.sync;
                __monitor_enter(v2);
            }
            catch(Throwable ) {
                goto label_874;
            }

            try {
                if(v1.isCancelled) {
                    __monitor_exit(v2);
                    return;
                }

                __monitor_exit(v2);
            }
            catch(Throwable v0) {
                v11_1 = null;
                try {
                    while(true) {
                    label_870:
                        __monitor_exit(v2);
                        break;
                    }
                }
                catch(Throwable v0) {
                    goto label_870;
                }

                try {
                    throw v0;
                }
                catch(Throwable ) {
                    goto label_877;
                }
            }

            try {
                v2_8 = new BitmapFactory$Options();
                v2_8.inSampleSize = 1;
                if(v1.cacheImage.filter == null) {
                    goto label_598;
                }

                goto label_472;
            }
            catch(Throwable ) {
            label_874:
                v11_1 = null;
            }

        label_877:
            v2_4 = v11_1;
            goto label_878;
            try {
            label_472:
                String[] v6_2 = v1.cacheImage.filter.split("_");
                if(v6_2.length >= 2) {
                    v9_2 = Float.parseFloat(v6_2[0]) * AndroidUtilities.density;
                    v6_3 = Float.parseFloat(v6_2[1]) * AndroidUtilities.density;
                }
                else {
                    v6_3 = 0f;
                    v9_2 = 0f;
                }

                v3 = v1.cacheImage.filter.contains("b");
                if(v9_2 != 0f && v6_3 != 0f) {
                    v2_8.inJustDecodeBounds = true;
                    if(v0_7 == null || v13_3 != null) {
                        v24 = v14_1;
                        v25 = v15;
                        if(v7_1 != null) {
                            RandomAccessFile v11_2 = new RandomAccessFile(v4_1, "r");
                            int v12 = ((int)v11_2.length());
                            v14 = ImageLoader.bytes == null || ImageLoader.bytes.length < v12 ? null : ImageLoader.bytes;
                            if(v14 == null) {
                                v14 = new byte[v12];
                                ImageLoader.bytes = v14;
                            }

                            v11_2.readFully(v14, 0, v12);
                            v11_2.close();
                            EncryptedFileInputStream.decryptBytesWithKeyFile(v14, 0, v12, v7_1);
                            v11_3 = Utilities.computeSHA256(v14, 0, v12);
                            if(v8 == null || !Arrays.equals(v11_3, v8)) {
                                v26 = v3;
                                v11_4 = 1;
                            }
                            else {
                                v26 = v3;
                                v11_4 = 0;
                            }

                            v3_1 = v14[0] & 255;
                            v12 -= v3_1;
                            if(v11_4 == 0) {
                                v3_2 = BitmapFactory.decodeByteArray(v14, v3_1, v12, v2_8);
                                goto label_571;
                            }

                        label_517:
                            v3_2 = null;
                            goto label_571;
                        }

                        v26 = v3;
                        if(v5 != 0) {
                            EncryptedFileInputStream v3_3 = new EncryptedFileInputStream(v4_1, v1.cacheImage.encryptionKeyPath);
                        }
                        else {
                            v3_4 = new FileInputStream(v4_1);
                        }

                        v12_1 = BitmapFactory.decodeStream(((InputStream)v3_4), null, v2_8);
                        goto label_569;
                    }
                    else {
                        if(v14_1 != 0) {
                            v24 = v14_1;
                            v25 = v15;
                            MediaStore$Video$Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), v0_7.longValue(), 1, v2_8);
                        }
                        else {
                            v24 = v14_1;
                            v25 = v15;
                            MediaStore$Images$Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), v0_7.longValue(), 1, v2_8);
                        }

                        v26 = v3;
                        goto label_517;
                    }
                }

                goto label_593;
            }
            catch(Throwable ) {
                goto label_452;
            }

            try {
            label_569:
                v3_4.close();
                v3_2 = v12_1;
            }
            catch(Throwable ) {
                v2_4 = v12_1;
                goto label_453;
            }

            try {
            label_571:
                v11_5 = Math.max((((float)v2_8.outWidth)) / v9_2, (((float)v2_8.outHeight)) / v6_3);
                if(v11_5 < 1f) {
                    v6_4 = false;
                    v11_5 = 1f;
                }
                else {
                    v6_4 = false;
                }

                v2_8.inJustDecodeBounds = v6_4;
                v2_8.inSampleSize = ((int)v11_5);
                v6 = v3_2;
                goto label_636;
            }
            catch(Throwable ) {
                v2_4 = v3_2;
                goto label_453;
            }

        label_593:
            v26 = v3;
            v24 = v14_1;
            v25 = v15;
            v6 = null;
            goto label_636;
        label_598:
            v24 = v14_1;
            v25 = v15;
            if(v13_3 != null) {
                try {
                    v2_8.inJustDecodeBounds = true;
                    v2_8.inPreferredConfig = Bitmap$Config.RGB_565;
                    v3_4 = new FileInputStream(v4_1);
                    v9_3 = BitmapFactory.decodeStream(((InputStream)v3_4), null, v2_8);
                }
                catch(Throwable ) {
                label_452:
                    v2_4 = null;
                    goto label_453;
                }

                try {
                    v3_4.close();
                    v3_1 = v2_8.outWidth;
                    v6_1 = v2_8.outHeight;
                    v2_8.inJustDecodeBounds = false;
                    v11_5 = ((float)Math.max(v3_1 / 200, v6_1 / 200));
                    v6_3 = 1f;
                    if(v11_5 < v6_3) {
                    }
                    else {
                        v6_3 = v11_5;
                    }

                    v3_1 = 1;
                    do {
                        v3_1 *= 2;
                    }
                    while((((float)(v3_1 * 2))) < v6_3);

                    v2_8.inSampleSize = v3_1;
                    v6 = v9_3;
                }
                catch(Throwable ) {
                    v2_4 = v9_3;
                    goto label_453;
                }
            }
            else {
                v6 = null;
            }

            v9_2 = 0f;
            v26 = false;
            try {
            label_636:
                v3_5 = v1.sync;
                __monitor_enter(v3_5);
            }
            catch(Throwable ) {
                goto label_866;
            }

            try {
                if(v1.isCancelled) {
                    __monitor_exit(v3_5);
                    return;
                }

                __monitor_exit(v3_5);
            }
            catch(Throwable v0) {
                v11_6 = null;
                try {
                    while(true) {
                    label_862:
                        __monitor_exit(v3_5);
                        break;
                    }
                }
                catch(Throwable v0) {
                    goto label_862;
                }

                try {
                    throw v0;
                }
                catch(Throwable ) {
                    goto label_803;
                }
            }

            try {
                if(v1.cacheImage.filter != null) {
                }
                else {
                    goto label_656;
                }
            }
            catch(Throwable ) {
                goto label_866;
            }

            if(!v26) {
                try {
                    if(v1.cacheImage.httpUrl != null) {
                        goto label_656;
                    }
                    else {
                        v2_8.inPreferredConfig = Bitmap$Config.RGB_565;
                    }

                    goto label_658;
                }
                catch(Throwable ) {
                    goto label_654;
                }
            }
            else {
                try {
                label_656:
                    v2_8.inPreferredConfig = Bitmap$Config.ARGB_8888;
                label_658:
                    if(Build$VERSION.SDK_INT < 21) {
                        goto label_661;
                    }

                    goto label_664;
                }
                catch(Throwable ) {
                    goto label_866;
                }
            }

            goto label_658;
        label_661:
            try {
                v2_8.inPurgeable = true;
            }
            catch(Throwable ) {
                goto label_654;
            }

            try {
            label_664:
                v2_8.inDither = false;
                if(v0_7 != null) {
                    goto label_666;
                }

                goto label_681;
            }
            catch(Throwable ) {
                goto label_866;
            }

        label_666:
            if(v13_3 == null) {
                if(v24 != 0) {
                    try {
                        v0_8 = MediaStore$Video$Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), v0_7.longValue(), 1, v2_8);
                        goto label_673;
                    label_675:
                        v0_8 = MediaStore$Images$Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), v0_7.longValue(), 1, v2_8);
                    label_673:
                        v6 = v0_8;
                    label_681:
                        if(v6 != null) {
                            goto label_792;
                        }
                        else if(v10 != 0) {
                            v0_9 = new RandomAccessFile(v4_1, "r");
                            v3_6 = v0_9.getChannel().map(FileChannel$MapMode.READ_ONLY, 0, v4_1.length());
                            v5_4 = new BitmapFactory$Options();
                            v5_4.inJustDecodeBounds = true;
                            v8_1 = ((ByteBuffer)v3_6).limit();
                            v10_1 = null;
                            goto label_697;
                        }
                        else {
                            goto label_719;
                        }
                    }
                    catch(Throwable ) {
                        goto label_654;
                    }
                }
                else {
                    goto label_675;
                }

                goto label_673;
            }

            goto label_681;
            try {
            label_697:
                Utilities.loadWebpImage(v10_1, ((ByteBuffer)v3_6), v8_1, v5_4, true);
            }
            catch(Throwable ) {
                v2_4 = v6;
                v11_1 = v10_1;
                goto label_878;
            }

            try {
                v5_2 = Bitmaps.createBitmap(v5_4.outWidth, v5_4.outHeight, Bitmap$Config.ARGB_8888);
            }
            catch(Throwable ) {
                goto label_654;
            }

            try {
                v6_1 = ((ByteBuffer)v3_6).limit();
                v7_4 = v2_8.inPurgeable ^ 1;
                v8_2 = null;
            }
            catch(Throwable ) {
                goto label_714;
            }

            try {
                Utilities.loadWebpImage(v5_2, ((ByteBuffer)v3_6), v6_1, v8_2, ((boolean)v7_4));
            }
            catch(Throwable ) {
                v2_4 = v5_2;
                BitmapFactory$Options v11_7 = v8_2;
                goto label_878;
            }

            try {
                v0_9.close();
                v6 = v5_2;
                goto label_792;
            }
            catch(Throwable ) {
            label_714:
                v2_4 = v5_2;
                goto label_453;
            }

            try {
            label_719:
                if(!v2_8.inPurgeable) {
                    goto label_721;
                }

                goto label_739;
            }
            catch(Throwable ) {
                goto label_866;
            }

        label_721:
            if(v7_1 != null) {
            }
            else {
                if(v5 != 0) {
                    try {
                        v0_10 = new EncryptedFileInputStream(v4_1, v1.cacheImage.encryptionKeyPath);
                        goto label_731;
                    }
                    catch(Throwable ) {
                    label_654:
                        v2_4 = v6;
                    }

                label_453:
                    v11_6 = null;
                    goto label_878;
                }
                else {
                    try {
                        FileInputStream v0_11 = new FileInputStream(v4_1);
                    }
                    catch(Throwable ) {
                    label_866:
                        v11_6 = null;
                        goto label_803;
                    }
                }

            label_731:
                Rect v11_8 = null;
                try {
                    v3_2 = BitmapFactory.decodeStream(((InputStream)v0_10), v11_8, v2_8);
                }
                catch(Throwable ) {
                    goto label_803;
                }

                try {
                    ((FileInputStream)v0_10).close();
                    v6 = v3_2;
                    v5 = 0;
                    goto label_794;
                }
                catch(Throwable ) {
                    v2_4 = v3_2;
                    goto label_878;
                }
            }

        label_739:
            v11_3 = null;
            try {
                v0_9 = new RandomAccessFile(v4_1, "r");
                v3_1 = ((int)v0_9.length());
                byte[] v10_2 = ImageLoader.bytes == null || ImageLoader.bytes.length < v3_1 ? v11_3 : ImageLoader.bytes;
                if(v10_2 == null) {
                    v10_2 = new byte[v3_1];
                    ImageLoader.bytes = v10_2;
                }

                v0_9.readFully(v10_2, 0, v3_1);
                v0_9.close();
                if(v7_1 != null) {
                    EncryptedFileInputStream.decryptBytesWithKeyFile(v10_2, 0, v3_1, v7_1);
                    v0_4 = Utilities.computeSHA256(v10_2, 0, v3_1);
                    v0_6 = v8 == null || !Arrays.equals(v0_4, v8) ? 1 : 0;
                    v5 = v10_2[0] & 255;
                    v7_4 = v3_1 - v5;
                    v3_1 = v5;
                    v5 = 0;
                }
                else {
                    if(v5 != 0) {
                        v5 = 0;
                        EncryptedFileInputStream.decryptBytesWithKeyFile(v10_2, 0, v3_1, v1.cacheImage.encryptionKeyPath);
                    }
                    else {
                        v5 = 0;
                    }

                    v7_4 = v3_1;
                    v0_6 = 0;
                    v3_1 = 0;
                }

                v0_8 = v0_6 == 0 ? BitmapFactory.decodeByteArray(v10_2, v3_1, v7_4, v2_8) : v6;
                v6 = v0_8;
                goto label_794;
            label_792:
                v5 = 0;
                v11_6 = null;
            label_794:
                if(v6 == null) {
                    if(v25 == 0) {
                        goto label_803;
                    }

                    if(v4_1.length() != 0 && v1.cacheImage.filter != null) {
                        goto label_803;
                    }

                    v4_1.delete();
                    goto label_803;
                }

                if(v1.cacheImage.filter != null) {
                    float v0_12 = ((float)v6.getWidth());
                    float v3_7 = ((float)v6.getHeight());
                    if(v2_8.inPurgeable) {
                        goto label_829;
                    }
                    else if(v9_2 == 0f) {
                        goto label_829;
                    }
                    else if(v0_12 == v9_2) {
                        goto label_829;
                    }
                    else if(v0_12 > 20f + v9_2) {
                        v4_2 = Bitmaps.createScaledBitmap(v6, ((int)v9_2), ((int)(v3_7 / (v0_12 / v9_2))), true);
                        if(v6 != v4_2) {
                            v6.recycle();
                        }
                        else {
                            goto label_829;
                        }
                    }
                    else {
                        goto label_829;
                    }

                    goto label_830;
                }

                goto label_855;
            }
            catch(Throwable ) {
                goto label_803;
            }

        label_829:
            v4_2 = v6;
        label_830:
            if(v4_2 != null && (v26)) {
                v6_3 = 100f;
                if(v3_7 < v6_3 && v0_12 < v6_3) {
                    try {
                        if(v4_2.getConfig() == Bitmap$Config.ARGB_8888) {
                            Utilities.blurBitmap(v4_2, 3, v2_8.inPurgeable ^ 1, v4_2.getWidth(), v4_2.getHeight(), v4_2.getRowBytes());
                        }
                    }
                    catch(Throwable ) {
                        v2_4 = v4_2;
                        goto label_878;
                    }

                    v6 = v4_2;
                    v5 = 1;
                    goto label_855;
                }
            }

            v6 = v4_2;
        label_855:
            if(v5 != 0) {
                goto label_803;
            }

            try {
                if(v2_8.inPurgeable) {
                    Utilities.pinBitmap(v6);
                }

            label_803:
                v2_4 = v6;
            }
            catch(Throwable ) {
                goto label_803;
            }

        label_878:
            Thread.interrupted();
            BitmapDrawable v6_5 = v2_4 != null ? new BitmapDrawable(v2_4) : v11_6;
            v1.onPostExecute(v6_5);
        }
    }

    class HttpFileTask extends AsyncTask {
        private boolean canRetry;
        private int currentAccount;
        private String ext;
        private RandomAccessFile fileOutputStream;
        private int fileSize;
        private long lastProgressTime;
        private File tempFile;
        private String url;

        public HttpFileTask(ImageLoader arg1, String arg2, File arg3, String arg4, int arg5) {
            ImageLoader.this = arg1;
            super();
            this.fileOutputStream = null;
            this.canRetry = true;
            this.url = arg2;
            this.tempFile = arg3;
            this.ext = arg4;
            this.currentAccount = arg5;
        }

        static boolean access$2700(HttpFileTask arg0) {
            return arg0.canRetry;
        }

        static String access$2800(HttpFileTask arg0) {
            return arg0.url;
        }

        static File access$2900(HttpFileTask arg0) {
            return arg0.tempFile;
        }

        static String access$3000(HttpFileTask arg0) {
            return arg0.ext;
        }

        static int access$3100(HttpFileTask arg0) {
            return arg0.currentAccount;
        }

        protected Boolean doInBackground(Void[] arg9) {
            byte[] v2_7;
            InputStream v3_3;
            String v3_2;
            int v4_1;
            InputStream v2_2;
            Throwable v3;
            URLConnection v4;
            URLConnection v2_1;
            RandomAccessFile v0 = null;
            boolean v1 = false;
            try {
                v2_1 = new URL(this.url).openConnection();
            }
            catch(Throwable v2) {
                v4 = ((URLConnection)v0);
                v3 = v2;
                v2_2 = ((InputStream)v4);
                goto label_63;
            }

            try {
                v2_1.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");
                v2_1.setConnectTimeout(5000);
                v2_1.setReadTimeout(5000);
                if((v2_1 instanceof HttpURLConnection)) {
                    URLConnection v3_1 = v2_1;
                    ((HttpURLConnection)v3_1).setInstanceFollowRedirects(true);
                    v4_1 = ((HttpURLConnection)v3_1).getResponseCode();
                    if(v4_1 != 302 && v4_1 != 301 && v4_1 != 303) {
                        goto label_41;
                    }

                    String v4_2 = ((HttpURLConnection)v3_1).getHeaderField("Location");
                    v3_2 = ((HttpURLConnection)v3_1).getHeaderField("Set-Cookie");
                    v4 = new URL(v4_2).openConnection();
                    goto label_31;
                }

                goto label_41;
            }
            catch(Throwable v3) {
                goto label_56;
            }

            try {
            label_31:
                v4.setRequestProperty("Cookie", v3_2);
                v4.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");
                v2_1 = v4;
            }
            catch(Throwable v2) {
                v3 = v2;
                goto label_57;
            }

            try {
            label_41:
                v2_1.connect();
                v3_3 = v2_1.getInputStream();
                goto label_43;
            }
            catch(Throwable v3) {
            label_56:
                v4 = v2_1;
            }

        label_57:
            v2_2 = ((InputStream)v0);
            goto label_63;
            try {
            label_43:
                this.fileOutputStream = new RandomAccessFile(this.tempFile, "rws");
                goto label_87;
            }
            catch(Throwable v4_3) {
                Throwable v7 = v4_3;
                v4 = v2_1;
                v2_2 = v3_3;
                v3 = v7;
            }

        label_63:
            if((v3 instanceof SocketTimeoutException)) {
                if(!ConnectionsManager.isNetworkOnline()) {
                    goto label_84;
                }

                goto label_67;
            }
            else if(((v3 instanceof UnknownHostException)) || ((v3 instanceof FileNotFoundException)) || ((v3 instanceof SocketException)) && (v3.getMessage() != null && (v3.getMessage().contains("ECONNRESET")))) {
            label_67:
                this.canRetry = false;
            }

        label_84:
            FileLog.e(v3);
            v3_3 = v2_2;
            v2_1 = v4;
        label_87:
            if(this.canRetry) {
                if(v2_1 != null) {
                    try {
                        if(!(v2_1 instanceof HttpURLConnection)) {
                            goto label_104;
                        }

                        v4_1 = v2_1.getResponseCode();
                        if(v4_1 == 200) {
                            goto label_104;
                        }

                        if(v4_1 == 202) {
                            goto label_104;
                        }

                        if(v4_1 == 304) {
                            goto label_104;
                        }

                        this.canRetry = false;
                    }
                    catch(Exception v4_4) {
                        FileLog.e(((Throwable)v4_4));
                    }
                }

            label_104:
                if(v2_1 != null) {
                    try {
                        Map v2_4 = v2_1.getHeaderFields();
                        if(v2_4 == null) {
                            goto label_120;
                        }

                        Object v2_5 = v2_4.get("content-Length");
                        if(v2_5 == null) {
                            goto label_120;
                        }

                        if(((List)v2_5).isEmpty()) {
                            goto label_120;
                        }

                        v2_5 = ((List)v2_5).get(0);
                        if(v2_5 == null) {
                            goto label_120;
                        }

                        this.fileSize = Utilities.parseInt(((String)v2_5)).intValue();
                    }
                    catch(Exception v2_3) {
                        FileLog.e(((Throwable)v2_3));
                    }
                }

            label_120:
                if(v3_3 == null) {
                    goto label_161;
                }

                int v2_6 = 32768;
                try {
                    v2_7 = new byte[v2_6];
                    v4_1 = 0;
                label_124:
                    while(this.isCancelled()) {
                        goto label_126;
                    }
                }
                catch(Throwable v9) {
                    goto label_160;
                }

                try {
                    int v5 = v3_3.read(v2_7);
                    if(v5 <= 0) {
                        goto label_140;
                    }

                    this.fileOutputStream.write(v2_7, 0, v5);
                    v4_1 += v5;
                    if(this.fileSize <= 0) {
                        goto label_124;
                    }

                    this.reportProgress((((float)v4_1)) / (((float)this.fileSize)));
                    goto label_124;
                }
                catch(Throwable v9) {
                }
                catch(Exception v9_1) {
                    goto label_157;
                label_140:
                    if(v5 != -1) {
                        goto label_161;
                    }

                    try {
                        if(this.fileSize != 0) {
                            this.reportProgress(1f);
                        }
                    }
                    catch(Throwable v1_1) {
                        v9 = v1_1;
                        v1 = true;
                        goto label_160;
                    }
                    catch(Exception v1_2) {
                        v9_1 = v1_2;
                        v1 = true;
                        try {
                        label_157:
                            FileLog.e(((Throwable)v9_1));
                            goto label_161;
                        }
                        catch(Throwable v9) {
                            goto label_160;
                        }
                    }

                    v1 = true;
                    goto label_161;
                label_126:
                    goto label_161;
                }

            label_160:
                FileLog.e(v9);
                try {
                label_161:
                    if(this.fileOutputStream == null) {
                        goto label_169;
                    }

                    this.fileOutputStream.close();
                    this.fileOutputStream = v0;
                }
                catch(Throwable v9) {
                    FileLog.e(v9);
                }

            label_169:
                if(v3_3 == null) {
                    goto label_174;
                }

                try {
                    v3_3.close();
                }
                catch(Throwable v9) {
                    FileLog.e(v9);
                }
            }

        label_174:
            return Boolean.valueOf(v1);
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        public static void lambda$null$0(HttpFileTask arg5, float arg6) {
            NotificationCenter.getInstance(arg5.currentAccount).postNotificationName(NotificationCenter.FileLoadProgressChanged, new Object[]{arg5.url, Float.valueOf(arg6)});
        }

        public static void lambda$reportProgress$1(HttpFileTask arg3, float arg4) {
            arg3.this$0.fileProgresses.put(arg3.url, Float.valueOf(arg4));
            AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$HttpFileTask$jxNOungyWTOpPFlVvFvzXDtQEx0(arg3, arg4));
        }

        protected void onCancelled() {
            ImageLoader.this.runHttpFileLoadTasks(this, 2);
        }

        protected void onPostExecute(Boolean arg2) {
            ImageLoader v0 = ImageLoader.this;
            int v2 = arg2.booleanValue() ? 2 : 1;
            v0.runHttpFileLoadTasks(this, v2);
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((Boolean)arg1));
        }

        private void reportProgress(float arg8) {
            long v0 = System.currentTimeMillis();
            if(arg8 == 1f || this.lastProgressTime == 0 || this.lastProgressTime < v0 - 500) {
                this.lastProgressTime = v0;
                Utilities.stageQueue.postRunnable(new -$$Lambda$ImageLoader$HttpFileTask$CbdoQhu0HscXntXREbdZu5bUbuA(this, arg8));
            }
        }
    }

    class HttpImageTask extends AsyncTask {
        private CacheImage cacheImage;
        private boolean canRetry;
        private RandomAccessFile fileOutputStream;
        private HttpURLConnection httpConnection;
        private int imageSize;
        private long lastProgressTime;

        public HttpImageTask(ImageLoader arg1, CacheImage arg2, int arg3) {
            ImageLoader.this = arg1;
            super();
            this.canRetry = true;
            this.cacheImage = arg2;
            this.imageSize = arg3;
        }

        static CacheImage access$3600(HttpImageTask arg0) {
            return arg0.cacheImage;
        }

        static int access$3700(HttpImageTask arg0) {
            return arg0.imageSize;
        }

        protected Boolean doInBackground(Void[] arg9) {
            int v4;
            byte[] v3_7;
            int v3_4;
            InputStream v3_1;
            InputStream v9_3;
            RandomAccessFile v1 = null;
            boolean v2 = false;
            if(!this.isCancelled()) {
                try {
                    if((this.cacheImage.httpUrl.startsWith("https://static-maps")) || (this.cacheImage.httpUrl.startsWith("https://maps.googleapis"))) {
                        int v9_1 = MessagesController.getInstance(this.cacheImage.currentAccount).mapProvider;
                        if(v9_1 != 3 && v9_1 != 4) {
                            goto label_40;
                        }

                        Object v9_2 = ImageLoader.this.testWebFile.get(this.cacheImage.httpUrl);
                        if(v9_2 == null) {
                            goto label_40;
                        }

                        TL_upload_getWebFile v3 = new TL_upload_getWebFile();
                        v3.location = ((WebFile)v9_2).location;
                        v3.offset = 0;
                        v3.limit = 0;
                        ConnectionsManager.getInstance(this.cacheImage.currentAccount).sendRequest(((TLObject)v3), -$$Lambda$ImageLoader$HttpImageTask$T115Ddi3sI3XyS3851ENmLig_I8.INSTANCE);
                    }

                label_40:
                    this.httpConnection = new URL(this.cacheImage.httpUrl).openConnection();
                    this.httpConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");
                    this.httpConnection.setConnectTimeout(5000);
                    this.httpConnection.setReadTimeout(5000);
                    this.httpConnection.setInstanceFollowRedirects(true);
                    if(this.isCancelled()) {
                        goto label_101;
                    }

                    this.httpConnection.connect();
                    v9_3 = this.httpConnection.getInputStream();
                }
                catch(Throwable v9) {
                    v3_1 = ((InputStream)v1);
                    goto label_77;
                }

                try {
                    this.fileOutputStream = new RandomAccessFile(this.cacheImage.tempFilePath, "rws");
                    goto label_102;
                }
                catch(Throwable v3_2) {
                    Throwable v7 = v3_2;
                    v3_1 = v9_3;
                    v9 = v7;
                }

            label_77:
                if((v9 instanceof SocketTimeoutException)) {
                    if(!ConnectionsManager.isNetworkOnline()) {
                        goto label_98;
                    }

                    goto label_81;
                }
                else if(((v9 instanceof UnknownHostException)) || ((v9 instanceof FileNotFoundException)) || ((v9 instanceof SocketException)) && (v9.getMessage() != null && (v9.getMessage().contains("ECONNRESET")))) {
                label_81:
                    this.canRetry = false;
                }

            label_98:
                FileLog.e(v9);
                v9_3 = v3_1;
            }
            else {
            label_101:
                v9_3 = ((InputStream)v1);
            }

        label_102:
            if(this.isCancelled()) {
                goto label_182;
            }

            try {
                if(this.httpConnection == null) {
                    goto label_121;
                }

                if(!(this.httpConnection instanceof HttpURLConnection)) {
                    goto label_121;
                }

                v3_4 = this.httpConnection.getResponseCode();
                if(v3_4 == 200) {
                    goto label_121;
                }

                if(v3_4 == 202) {
                    goto label_121;
                }

                if(v3_4 == 304) {
                    goto label_121;
                }

                this.canRetry = false;
            }
            catch(Exception v3_3) {
                FileLog.e(((Throwable)v3_3));
            }

        label_121:
            if(this.imageSize == 0 && this.httpConnection != null) {
                try {
                    Map v3_5 = this.httpConnection.getHeaderFields();
                    if(v3_5 == null) {
                        goto label_141;
                    }

                    Object v3_6 = v3_5.get("content-Length");
                    if(v3_6 == null) {
                        goto label_141;
                    }

                    if(((List)v3_6).isEmpty()) {
                        goto label_141;
                    }

                    v3_6 = ((List)v3_6).get(0);
                    if(v3_6 == null) {
                        goto label_141;
                    }

                    this.imageSize = Utilities.parseInt(((String)v3_6)).intValue();
                }
                catch(Exception v3_3) {
                    FileLog.e(((Throwable)v3_3));
                }
            }

        label_141:
            if(v9_3 == null) {
                goto label_182;
            }

            v3_4 = 8192;
            try {
                v3_7 = new byte[v3_4];
                v4 = 0;
            label_145:
                while(this.isCancelled()) {
                    goto label_147;
                }
            }
            catch(Throwable v0) {
                goto label_181;
            }

            try {
                int v5 = v9_3.read(v3_7);
                if(v5 <= 0) {
                    goto label_161;
                }

                v4 += v5;
                this.fileOutputStream.write(v3_7, 0, v5);
                if(this.imageSize == 0) {
                    goto label_145;
                }

                this.reportProgress((((float)v4)) / (((float)this.imageSize)));
                goto label_145;
            }
            catch(Throwable v0) {
            }
            catch(Exception v0_1) {
                goto label_178;
            label_161:
                if(v5 != -1) {
                    goto label_182;
                }

                try {
                    if(this.imageSize != 0) {
                        this.reportProgress(1f);
                    }
                }
                catch(Throwable v2_1) {
                    v0 = v2_1;
                    v2 = true;
                    goto label_181;
                }
                catch(Exception v2_2) {
                    v0_1 = v2_2;
                    v2 = true;
                    try {
                    label_178:
                        FileLog.e(((Throwable)v0_1));
                        goto label_182;
                    }
                    catch(Throwable v0) {
                        goto label_181;
                    }
                }

                v2 = true;
                goto label_182;
            label_147:
                goto label_182;
            }

        label_181:
            FileLog.e(v0);
            try {
            label_182:
                if(this.fileOutputStream == null) {
                    goto label_190;
                }

                this.fileOutputStream.close();
                this.fileOutputStream = v1;
            }
            catch(Throwable v0) {
                FileLog.e(v0);
            }

            try {
            label_190:
                if(this.httpConnection != null) {
                    this.httpConnection.disconnect();
                }

                goto label_194;
            }
            catch(Throwable ) {
            label_194:
                if(v9_3 != null) {
                    try {
                        v9_3.close();
                    }
                    catch(Throwable v9) {
                        FileLog.e(v9);
                    }
                }

                if((v2) && this.cacheImage.tempFilePath != null && !this.cacheImage.tempFilePath.renameTo(this.cacheImage.finalFilePath)) {
                    this.cacheImage.finalFilePath = this.cacheImage.tempFilePath;
                }

                return Boolean.valueOf(v2);
            }
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((Void[])arg1));
        }

        static void lambda$doInBackground$2(TLObject arg0, TL_error arg1) {
        }

        public static void lambda$null$0(HttpImageTask arg5, float arg6) {
            NotificationCenter.getInstance(arg5.cacheImage.currentAccount).postNotificationName(NotificationCenter.FileLoadProgressChanged, new Object[]{arg5.cacheImage.url, Float.valueOf(arg6)});
        }

        public static void lambda$null$3(HttpImageTask arg6, Boolean arg7) {
            if(arg7.booleanValue()) {
                NotificationCenter.getInstance(arg6.cacheImage.currentAccount).postNotificationName(NotificationCenter.FileDidLoaded, new Object[]{arg6.cacheImage.url});
            }
            else {
                NotificationCenter.getInstance(arg6.cacheImage.currentAccount).postNotificationName(NotificationCenter.FileDidFailedLoad, new Object[]{arg6.cacheImage.url, Integer.valueOf(2)});
            }
        }

        public static void lambda$null$7(HttpImageTask arg5) {
            NotificationCenter.getInstance(arg5.cacheImage.currentAccount).postNotificationName(NotificationCenter.FileDidFailedLoad, new Object[]{arg5.cacheImage.url, Integer.valueOf(1)});
        }

        public static void lambda$onCancelled$6(HttpImageTask arg2) {
            arg2.this$0.runHttpTasks(true);
        }

        public static void lambda$onCancelled$8(HttpImageTask arg2) {
            arg2.this$0.fileProgresses.remove(arg2.cacheImage.url);
            AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$HttpImageTask$gXa55exgYzWejvB_mF-uS-Y2fOo(arg2));
        }

        public static void lambda$onPostExecute$4(HttpImageTask arg2, Boolean arg3) {
            arg2.this$0.fileProgresses.remove(arg2.cacheImage.url);
            AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$HttpImageTask$pG_0hY9R-vFFkVPjhMdZBfbtL24(arg2, arg3));
        }

        public static void lambda$onPostExecute$5(HttpImageTask arg2) {
            arg2.this$0.runHttpTasks(true);
        }

        public static void lambda$reportProgress$1(HttpImageTask arg3, float arg4) {
            arg3.this$0.fileProgresses.put(arg3.cacheImage.url, Float.valueOf(arg4));
            AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$HttpImageTask$5HGicoisjrTisboRYDJXYCptNjk(arg3, arg4));
        }

        protected void onCancelled() {
            ImageLoader.this.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$HttpImageTask$hp6Qb_emVVm8eUAVBNZgyYyMaoI(this));
            Utilities.stageQueue.postRunnable(new -$$Lambda$ImageLoader$HttpImageTask$a-vnJl4U3DAZgDJvr8vdGGH5i2s(this));
        }

        protected void onPostExecute(Boolean arg5) {
            if((arg5.booleanValue()) || !this.canRetry) {
                ImageLoader.this.fileDidLoaded(this.cacheImage.url, this.cacheImage.finalFilePath, 0);
            }
            else {
                ImageLoader.this.httpFileLoadError(this.cacheImage.url);
            }

            Utilities.stageQueue.postRunnable(new -$$Lambda$ImageLoader$HttpImageTask$SfPPeQgJq15qYgHfn-IXbR-DnQ0(this, arg5));
            ImageLoader.this.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$HttpImageTask$Z2AJLo51Bz13ruMq5jl4ihFrKyc(this));
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((Boolean)arg1));
        }

        private void reportProgress(float arg8) {
            long v0 = System.currentTimeMillis();
            if(arg8 == 1f || this.lastProgressTime == 0 || this.lastProgressTime < v0 - 500) {
                this.lastProgressTime = v0;
                Utilities.stageQueue.postRunnable(new -$$Lambda$ImageLoader$HttpImageTask$WWTxHtUw7-WIiuq5bKLqtQ8BNBI(this, arg8));
            }
        }
    }

    class ThumbGenerateInfo {
        private int count;
        private FileLocation fileLocation;
        private String filter;

        ThumbGenerateInfo(ImageLoader arg1, org.telegram.messenger.ImageLoader$1 arg2) {
            this(arg1);
        }

        private ThumbGenerateInfo(ImageLoader arg1) {
            ImageLoader.this = arg1;
            super();
        }

        static int access$2600(ThumbGenerateInfo arg0) {
            return arg0.count;
        }

        static int access$2608(ThumbGenerateInfo arg2) {
            int v0 = arg2.count;
            arg2.count = v0 + 1;
            return v0;
        }

        static int access$2610(ThumbGenerateInfo arg2) {
            int v0 = arg2.count;
            arg2.count = v0 - 1;
            return v0;
        }

        static FileLocation access$3200(ThumbGenerateInfo arg0) {
            return arg0.fileLocation;
        }

        static FileLocation access$3202(ThumbGenerateInfo arg0, FileLocation arg1) {
            arg0.fileLocation = arg1;
            return arg1;
        }

        static String access$3300(ThumbGenerateInfo arg0) {
            return arg0.filter;
        }

        static String access$3302(ThumbGenerateInfo arg0, String arg1) {
            arg0.filter = arg1;
            return arg1;
        }
    }

    class ThumbGenerateTask implements Runnable {
        private String filter;
        private int mediaType;
        private File originalPath;
        private FileLocation thumbLocation;

        public ThumbGenerateTask(ImageLoader arg1, int arg2, File arg3, FileLocation arg4, String arg5) {
            ImageLoader.this = arg1;
            super();
            this.mediaType = arg2;
            this.originalPath = arg3;
            this.thumbLocation = arg4;
            this.filter = arg5;
        }

        public static void lambda$removeTask$0(ThumbGenerateTask arg1, String arg2) {
            arg1.this$0.thumbGenerateTasks.remove(arg2);
        }

        public static void lambda$run$1(ThumbGenerateTask arg4, String arg5, BitmapDrawable arg6) {
            arg4.removeTask();
            if(arg4.filter != null) {
                arg5 = arg5 + "@" + arg4.filter;
            }

            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.messageThumbGenerated, new Object[]{arg6, arg5});
            arg4.this$0.memCache.put(arg5, arg6);
        }

        private void removeTask() {
            if(this.thumbLocation == null) {
                return;
            }

            ImageLoader.this.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$ThumbGenerateTask$wNtiv0w_w5JLAy5lgehk-MfB6UY(this, FileLoader.getAttachFileName(this.thumbLocation)));
        }

        public void run() {
            Bitmap v6_1;
            String v3_1;
            try {
                if(this.thumbLocation == null) {
                    this.removeTask();
                    return;
                }

                String v0_2 = this.thumbLocation.volume_id + "_" + this.thumbLocation.local_id;
                int v2 = 4;
                File v3 = FileLoader.getDirectory(v2);
                StringBuilder v4 = new StringBuilder();
                v4.append("q_");
                v4.append(v0_2);
                v4.append(".jpg");
                File v1 = new File(v3, v4.toString());
                if(!v1.exists()) {
                    if(!this.originalPath.exists()) {
                    }
                    else {
                        v2 = Math.min(180, Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y) / v2);
                        Uri v6 = null;
                        if(this.mediaType == 0) {
                            v3_1 = this.originalPath.toString();
                            goto label_48;
                        }
                        else if(this.mediaType == 2) {
                            v6_1 = ThumbnailUtils.createVideoThumbnail(this.originalPath.toString(), 1);
                        }
                        else if(this.mediaType == 3) {
                            v3_1 = this.originalPath.toString().toLowerCase();
                            if((v3_1.endsWith(".jpg")) || (v3_1.endsWith(".jpeg")) || (v3_1.endsWith(".png")) || (v3_1.endsWith(".gif"))) {
                            label_48:
                                float v7 = ((float)v2);
                                v6_1 = ImageLoader.loadBitmap(v3_1, v6, v7, v7, false);
                            }
                            else {
                                this.removeTask();
                                return;
                            }
                        }

                        if((((Bitmap)v6)) == null) {
                            this.removeTask();
                            return;
                        }

                        int v3_2 = ((Bitmap)v6).getWidth();
                        int v5 = ((Bitmap)v6).getHeight();
                        if(v3_2 != 0) {
                            if(v5 == 0) {
                            }
                            else {
                                float v3_3 = ((float)v3_2);
                                float v2_1 = ((float)v2);
                                float v5_1 = ((float)v5);
                                v2_1 = Math.min(v3_3 / v2_1, v5_1 / v2_1);
                                Bitmap v2_2 = Bitmaps.createScaledBitmap(((Bitmap)v6), ((int)(v3_3 / v2_1)), ((int)(v5_1 / v2_1)), true);
                                if(v2_2 != (((Bitmap)v6))) {
                                    ((Bitmap)v6).recycle();
                                }
                                else {
                                    v2_2 = ((Bitmap)v6);
                                }

                                FileOutputStream v3_4 = new FileOutputStream(v1);
                                v2_2.compress(Bitmap$CompressFormat.JPEG, 60, ((OutputStream)v3_4));
                                try {
                                    v3_4.close();
                                    goto label_110;
                                }
                                catch(Exception v1_1) {
                                    try {
                                        FileLog.e(((Throwable)v1_1));
                                    label_110:
                                        AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$ThumbGenerateTask$nH1CQPQlMBbwf3QspbAZBS4UHfU(this, v0_2, new BitmapDrawable(v2_2)));
                                        return;
                                    label_116:
                                        this.removeTask();
                                        return;
                                    label_118:
                                        this.removeTask();
                                        return;
                                    }
                                    catch(Throwable v0) {
                                    label_121:
                                        FileLog.e(v0);
                                        this.removeTask();
                                        return;
                                    }
                                }
                            }
                        }

                        goto label_116;
                    }
                }

                goto label_118;
            }
            catch(Throwable v0) {
                goto label_121;
            }
        }
    }

    private static volatile ImageLoader Instance;
    private HashMap bitmapUseCounts;
    private static byte[] bytes;
    private static byte[] bytesThumb;
    private DispatchQueue cacheOutQueue;
    private DispatchQueue cacheThumbOutQueue;
    private int currentHttpFileLoadTasksCount;
    private int currentHttpTasksCount;
    private ConcurrentHashMap fileProgresses;
    private HashMap forceLoadingImages;
    private static byte[] header;
    private static byte[] headerThumb;
    private LinkedList httpFileLoadTasks;
    private HashMap httpFileLoadTasksByKeys;
    private LinkedList httpTasks;
    private String ignoreRemoval;
    private DispatchQueue imageLoadQueue;
    private HashMap imageLoadingByKeys;
    private SparseArray imageLoadingByTag;
    private HashMap imageLoadingByUrl;
    private volatile long lastCacheOutTime;
    private int lastImageNum;
    private long lastProgressUpdateTime;
    private LruCache memCache;
    private HashMap replacedBitmaps;
    private HashMap retryHttpsTasks;
    private File telegramPath;
    private ConcurrentHashMap testWebFile;
    private HashMap thumbGenerateTasks;
    private DispatchQueue thumbGeneratingQueue;
    private HashMap waitingForQualityThumb;
    private SparseArray waitingForQualityThumbByTag;

    static {
        ImageLoader.header = new byte[12];
        ImageLoader.headerThumb = new byte[12];
        ImageLoader.Instance = null;
    }

    public ImageLoader() {
        super();
        this.bitmapUseCounts = new HashMap();
        this.imageLoadingByUrl = new HashMap();
        this.imageLoadingByKeys = new HashMap();
        this.imageLoadingByTag = new SparseArray();
        this.waitingForQualityThumb = new HashMap();
        this.waitingForQualityThumbByTag = new SparseArray();
        this.httpTasks = new LinkedList();
        this.cacheOutQueue = new DispatchQueue("cacheOutQueue");
        this.cacheThumbOutQueue = new DispatchQueue("cacheThumbOutQueue");
        this.thumbGeneratingQueue = new DispatchQueue("thumbGeneratingQueue");
        this.imageLoadQueue = new DispatchQueue("imageLoadQueue");
        this.replacedBitmaps = new HashMap();
        this.fileProgresses = new ConcurrentHashMap();
        this.thumbGenerateTasks = new HashMap();
        this.forceLoadingImages = new HashMap();
        int v0 = 0;
        this.currentHttpTasksCount = 0;
        this.testWebFile = new ConcurrentHashMap();
        this.httpFileLoadTasks = new LinkedList();
        this.httpFileLoadTasksByKeys = new HashMap();
        this.retryHttpsTasks = new HashMap();
        this.currentHttpFileLoadTasksCount = 0;
        this.ignoreRemoval = null;
        this.lastCacheOutTime = 0;
        this.lastImageNum = 0;
        this.lastProgressUpdateTime = 0;
        this.telegramPath = null;
        this.thumbGeneratingQueue.setPriority(1);
        this.memCache = new LruCache(Math.min(15, ApplicationLoader.applicationContext.getSystemService("activity").getMemoryClass() / 7) * 1024 * 1024) {
            protected void entryRemoved(boolean arg1, String arg2, BitmapDrawable arg3, BitmapDrawable arg4) {
                if(ImageLoader.access$2100(ImageLoader.this) != null && arg2 != null && (ImageLoader.access$2100(ImageLoader.this).equals(arg2))) {
                    return;
                }

                Object v1 = ImageLoader.access$2200(ImageLoader.this).get(arg2);
                if(v1 == null || ((Integer)v1).intValue() == 0) {
                    Bitmap v1_1 = arg3.getBitmap();
                    if(!v1_1.isRecycled()) {
                        v1_1.recycle();
                    }
                }
            }

            protected int sizeOf(String arg1, BitmapDrawable arg2) {
                return arg2.getBitmap().getByteCount();
            }
        };
        SparseArray v1 = new SparseArray();
        File v2 = AndroidUtilities.getCacheDir();
        if(v2.isDirectory()) {
            goto label_96;
        }

        try {
            v2.mkdirs();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }

        try {
        label_96:
            new File(v2, ".nomedia").createNewFile();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }

        v1.put(4, v2);
        while(v0 < 3) {
            FileLoader.getInstance(v0).setDelegate(new FileLoaderDelegate(v0) {
                public void fileDidFailedLoad(String arg3, int arg4) {
                    ImageLoader.access$100(ImageLoader.this).remove(arg3);
                    AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$2$vIiMCIJWPinqK2V-AkSP5M1D_h0(this, arg3, arg4, this.val$currentAccount));
                }

                public void fileDidFailedUpload(String arg4, boolean arg5) {
                    Utilities.stageQueue.postRunnable(new -$$Lambda$ImageLoader$2$nZgpS-rMCgvNyxzUKbj_RWA6jQk(this, this.val$currentAccount, arg4, arg5));
                }

                public void fileDidLoaded(String arg8, File arg9, int arg10) {
                    ImageLoader.access$100(ImageLoader.this).remove(arg8);
                    AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$2$XAAto7UYXpjbXY4_NyLw0JfQ0rA(this, arg9, arg8, this.val$currentAccount, arg10));
                }

                public void fileDidUploaded(String arg14, InputFile arg15, InputEncryptedFile arg16, byte[] arg17, byte[] arg18, long arg19) {
                    Utilities.stageQueue.postRunnable(new -$$Lambda$ImageLoader$2$OJOkl6dXzCVsFC1n7bftktzMxsM(this, this.val$currentAccount, arg14, arg15, arg16, arg17, arg18, arg19));
                }

                public void fileLoadProgressChanged(String arg8, float arg9) {
                    ImageLoader.access$100(ImageLoader.this).put(arg8, Float.valueOf(arg9));
                    long v0 = System.currentTimeMillis();
                    if(ImageLoader.access$2300(ImageLoader.this) == 0 || ImageLoader.access$2300(ImageLoader.this) < v0 - 500) {
                        ImageLoader.access$2302(ImageLoader.this, v0);
                        AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$2$Fz7og9tmQ141WKm_AnGeoOkHPJ8(this.val$currentAccount, arg8, arg9));
                    }
                }

                public void fileUploadProgressChanged(String arg8, float arg9, boolean arg10) {
                    ImageLoader.access$100(ImageLoader.this).put(arg8, Float.valueOf(arg9));
                    long v0 = System.currentTimeMillis();
                    if(ImageLoader.access$2300(ImageLoader.this) == 0 || ImageLoader.access$2300(ImageLoader.this) < v0 - 500) {
                        ImageLoader.access$2302(ImageLoader.this, v0);
                        AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$2$kx6bKZLf1Fl9Tfjq8OBUmPKsqGg(this.val$currentAccount, arg8, arg9, arg10));
                    }
                }

                public static void lambda$fileDidFailedLoad$6(org.telegram.messenger.ImageLoader$2 arg3, String arg4, int arg5, int arg6) {
                    ImageLoader.access$2400(arg3.this$0, arg4, arg5);
                    NotificationCenter.getInstance(arg6).postNotificationName(NotificationCenter.FileDidFailedLoad, new Object[]{arg4, Integer.valueOf(arg5)});
                }

                public static void lambda$fileDidFailedUpload$4(org.telegram.messenger.ImageLoader$2 arg1, int arg2, String arg3, boolean arg4) {
                    AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$2$LhAmagMEUXEQMbU-65sqeFfWk-U(arg2, arg3, arg4));
                    ImageLoader.access$100(arg1.this$0).remove(arg3);
                }

                public static void lambda$fileDidLoaded$5(org.telegram.messenger.ImageLoader$2 arg3, File arg4, String arg5, int arg6, int arg7) {
                    if((SharedConfig.saveToGallery) && ImageLoader.access$2500(arg3.this$0) != null && arg4 != null && ((arg5.endsWith(".mp4")) || (arg5.endsWith(".jpg"))) && (arg4.toString().startsWith(ImageLoader.access$2500(arg3.this$0).toString()))) {
                        AndroidUtilities.addMediaToGallery(arg4.toString());
                    }

                    NotificationCenter.getInstance(arg6).postNotificationName(NotificationCenter.FileDidLoaded, new Object[]{arg5});
                    ImageLoader.access$300(arg3.this$0, arg5, arg4, arg7);
                }

                public static void lambda$fileDidUploaded$2(org.telegram.messenger.ImageLoader$2 arg10, int arg11, String arg12, InputFile arg13, InputEncryptedFile arg14, byte[] arg15, byte[] arg16, long arg17) {
                    AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$2$-V5jPiyC2kg0zX7Y5l0pTebxVX4(arg11, arg12, arg13, arg14, arg15, arg16, arg17));
                    ImageLoader.access$100(arg10.this$0).remove(arg12);
                }

                static void lambda$fileLoadProgressChanged$7(int arg3, String arg4, float arg5) {
                    NotificationCenter.getInstance(arg3).postNotificationName(NotificationCenter.FileLoadProgressChanged, new Object[]{arg4, Float.valueOf(arg5)});
                }

                static void lambda$fileUploadProgressChanged$0(int arg3, String arg4, float arg5, boolean arg6) {
                    NotificationCenter.getInstance(arg3).postNotificationName(NotificationCenter.FileUploadProgressChanged, new Object[]{arg4, Float.valueOf(arg5), Boolean.valueOf(arg6)});
                }

                static void lambda$null$1(int arg3, String arg4, InputFile arg5, InputEncryptedFile arg6, byte[] arg7, byte[] arg8, long arg9) {
                    NotificationCenter.getInstance(arg3).postNotificationName(NotificationCenter.FileDidUpload, new Object[]{arg4, arg5, arg6, arg7, arg8, Long.valueOf(arg9)});
                }

                static void lambda$null$3(int arg3, String arg4, boolean arg5) {
                    NotificationCenter.getInstance(arg3).postNotificationName(NotificationCenter.FileDidFailUpload, new Object[]{arg4, Boolean.valueOf(arg5)});
                }
            });
            ++v0;
        }

        FileLoader.setMediaDirs(v1);
        org.telegram.messenger.ImageLoader$3 v0_1 = new BroadcastReceiver() {
            public static void lambda$onReceive$0(org.telegram.messenger.ImageLoader$3 arg1) {
                arg1.this$0.checkMediaPaths();
            }

            public void onReceive(Context arg3, Intent arg4) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("file system changed");
                }

                -$$Lambda$ImageLoader$3$8lqLWipLLeq6gG0puZxyh84L4yc v3 = new -$$Lambda$ImageLoader$3$8lqLWipLLeq6gG0puZxyh84L4yc(this);
                if("android.intent.action.MEDIA_UNMOUNTED".equals(arg4.getAction())) {
                    AndroidUtilities.runOnUIThread(((Runnable)v3), 1000);
                }
                else {
                    ((Runnable)v3).run();
                }
            }
        };
        IntentFilter v1_1 = new IntentFilter();
        v1_1.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        v1_1.addAction("android.intent.action.MEDIA_CHECKING");
        v1_1.addAction("android.intent.action.MEDIA_EJECT");
        v1_1.addAction("android.intent.action.MEDIA_MOUNTED");
        v1_1.addAction("android.intent.action.MEDIA_NOFS");
        v1_1.addAction("android.intent.action.MEDIA_REMOVED");
        v1_1.addAction("android.intent.action.MEDIA_SHARED");
        v1_1.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
        v1_1.addAction("android.intent.action.MEDIA_UNMOUNTED");
        v1_1.addDataScheme("file");
        try {
            ApplicationLoader.applicationContext.registerReceiver(((BroadcastReceiver)v0_1), v1_1);
            goto label_140;
        }
        catch(Throwable ) {
        label_140:
            this.checkMediaPaths();
            return;
        }
    }

    static void access$000(ImageLoader arg0, HttpFileTask arg1, int arg2) {
        arg0.runHttpFileLoadTasks(arg1, arg2);
    }

    static ConcurrentHashMap access$100(ImageLoader arg0) {
        return arg0.fileProgresses;
    }

    static byte[] access$1000() {
        return ImageLoader.header;
    }

    static long access$1100(ImageLoader arg2) {
        return arg2.lastCacheOutTime;
    }

    static long access$1102(ImageLoader arg0, long arg1) {
        arg0.lastCacheOutTime = arg1;
        return arg1;
    }

    static byte[] access$1200() {
        return ImageLoader.bytesThumb;
    }

    static byte[] access$1202(byte[] arg0) {
        ImageLoader.bytesThumb = arg0;
        return arg0;
    }

    static byte[] access$1300() {
        return ImageLoader.bytes;
    }

    static byte[] access$1302(byte[] arg0) {
        ImageLoader.bytes = arg0;
        return arg0;
    }

    static SparseArray access$1400(ImageLoader arg0) {
        return arg0.imageLoadingByTag;
    }

    static HashMap access$1500(ImageLoader arg0) {
        return arg0.forceLoadingImages;
    }

    static DispatchQueue access$1600(ImageLoader arg0) {
        return arg0.cacheThumbOutQueue;
    }

    static DispatchQueue access$1700(ImageLoader arg0) {
        return arg0.cacheOutQueue;
    }

    static LinkedList access$1800(ImageLoader arg0) {
        return arg0.httpTasks;
    }

    static HashMap access$1900(ImageLoader arg0) {
        return arg0.imageLoadingByUrl;
    }

    static ConcurrentHashMap access$200(ImageLoader arg0) {
        return arg0.testWebFile;
    }

    static HashMap access$2000(ImageLoader arg0) {
        return arg0.imageLoadingByKeys;
    }

    static String access$2100(ImageLoader arg0) {
        return arg0.ignoreRemoval;
    }

    static HashMap access$2200(ImageLoader arg0) {
        return arg0.bitmapUseCounts;
    }

    static long access$2300(ImageLoader arg2) {
        return arg2.lastProgressUpdateTime;
    }

    static long access$2302(ImageLoader arg0, long arg1) {
        arg0.lastProgressUpdateTime = arg1;
        return arg1;
    }

    static void access$2400(ImageLoader arg0, String arg1, int arg2) {
        arg0.fileDidFailedLoad(arg1, arg2);
    }

    static File access$2500(ImageLoader arg0) {
        return arg0.telegramPath;
    }

    static void access$300(ImageLoader arg0, String arg1, File arg2, int arg3) {
        arg0.fileDidLoaded(arg1, arg2, arg3);
    }

    static void access$400(ImageLoader arg0, String arg1) {
        arg0.httpFileLoadError(arg1);
    }

    static DispatchQueue access$500(ImageLoader arg0) {
        return arg0.imageLoadQueue;
    }

    static void access$600(ImageLoader arg0, boolean arg1) {
        arg0.runHttpTasks(arg1);
    }

    static LruCache access$700(ImageLoader arg0) {
        return arg0.memCache;
    }

    static HashMap access$800(ImageLoader arg0) {
        return arg0.thumbGenerateTasks;
    }

    static byte[] access$900() {
        return ImageLoader.headerThumb;
    }

    public void addTestWebFile(String arg2, WebFile arg3) {
        if(arg2 != null) {
            if(arg3 == null) {
            }
            else {
                this.testWebFile.put(arg2, arg3);
            }
        }
    }

    private boolean canMoveFiles(File arg5, File arg6, int arg7) {
        RandomAccessFile v2;
        byte[] v6;
        File v7;
        RandomAccessFile v1 = null;
        if(arg7 == 0) {
            try {
                v7 = new File(arg5, "000000000_999999_temp.jpg");
                arg5 = new File(arg6, "000000000_999999.jpg");
                goto label_42;
            label_14:
                if(arg7 == 3) {
                    v7 = new File(arg5, "000000000_999999_temp.doc");
                    arg5 = new File(arg6, "000000000_999999.doc");
                }
                else if(arg7 == 1) {
                    v7 = new File(arg5, "000000000_999999_temp.ogg");
                    arg5 = new File(arg6, "000000000_999999.ogg");
                }
                else if(arg7 == 2) {
                    v7 = new File(arg5, "000000000_999999_temp.mp4");
                    arg5 = new File(arg6, "000000000_999999.mp4");
                }
                else {
                    arg5 = ((File)v1);
                    v7 = arg5;
                }

            label_42:
                v6 = new byte[1024];
                v7.createNewFile();
                v2 = new RandomAccessFile(v7, "rws");
                goto label_48;
            }
            catch(Throwable v5) {
                goto label_11;
            }
            catch(Exception v5_1) {
                goto label_13;
            }
        }
        else {
            goto label_14;
        }

        goto label_42;
        try {
        label_48:
            v2.write(v6);
            v2.close();
        }
        catch(Throwable v5) {
            goto label_56;
        }
        catch(Exception v5_1) {
            goto label_59;
        }

        try {
            boolean v6_1 = v7.renameTo(arg5);
            v7.delete();
            arg5.delete();
            if(!v6_1) {
                return 0;
            }
        }
        catch(Throwable v5) {
            goto label_11;
        }
        catch(Exception v5_1) {
            goto label_13;
        }

        return 1;
    label_56:
        v1 = v2;
        goto label_68;
    label_59:
        v1 = v2;
        goto label_60;
    label_11:
        goto label_68;
    label_13:
        try {
        label_60:
            FileLog.e(((Throwable)v5_1));
            if(v1 == null) {
                return 0;
            }
        }
        catch(Throwable v5) {
            goto label_11;
        }

        try {
            v1.close();
        }
        catch(Exception v5_1) {
            FileLog.e(((Throwable)v5_1));
        }

        return 0;
    label_68:
        if(v1 != null) {
            try {
                v1.close();
            }
            catch(Exception v6_2) {
                FileLog.e(((Throwable)v6_2));
            }
        }

        throw v5;
    }

    public void cancelForceLoadingForImageReceiver(ImageReceiver arg3) {
        if(arg3 == null) {
            return;
        }

        String v3 = arg3.getKey();
        if(v3 == null) {
            return;
        }

        this.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$pOb77-ep1O4qdDkpbIPheznVQgg(this, v3));
    }

    public void cancelLoadHttpFile(String arg3) {
        Object v0 = this.httpFileLoadTasksByKeys.get(arg3);
        if(v0 != null) {
            ((HttpFileTask)v0).cancel(true);
            this.httpFileLoadTasksByKeys.remove(arg3);
            this.httpFileLoadTasks.remove(v0);
        }

        Object v3 = this.retryHttpsTasks.get(arg3);
        if(v3 != null) {
            AndroidUtilities.cancelRunOnUIThread(((Runnable)v3));
        }

        this.runHttpFileLoadTasks(null, 0);
    }

    public void cancelLoadingForImageReceiver(ImageReceiver arg3, int arg4) {
        if(arg3 == null) {
            return;
        }

        this.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$XrXp32CG-3M3L0PNTbZtlsZU8Ds(this, arg4, arg3));
    }

    public void checkMediaPaths() {
        this.cacheOutQueue.postRunnable(new -$$Lambda$ImageLoader$TEcsmbVkFIlJCFa-8B6JxwYMU3A(this));
    }

    public void clearMemory() {
        this.memCache.evictAll();
    }

    private void createLoadOperationForImageReceiver(ImageReceiver arg20, String arg21, String arg22, String arg23, TLObject arg24, String arg25, String arg26, int arg27, int arg28, int arg29) {
        ImageLoader v15 = this;
        ImageReceiver v6 = arg20;
        if(v6 != null && arg22 != null) {
            if(arg21 == null) {
            }
            else {
                boolean v5 = arg29 != 0 ? true : false;
                int v5_1 = v6.getTag(v5);
                if(v5_1 == 0) {
                    v5_1 = v15.lastImageNum;
                    boolean v7 = arg29 != 0 ? true : false;
                    v6.setTag(v5_1, v7);
                    ++v15.lastImageNum;
                    if(v15.lastImageNum != 2147483647) {
                        goto label_27;
                    }

                    v15.lastImageNum = 0;
                }

            label_27:
                v15.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$zpwAHNMBO1mAFXXJyUpihz_n978(this, arg29, arg22, arg21, v5_1, arg20, arg26, arg25, arg20.isNeedsQualityThumb(), arg20.getParentMessageObject(), arg24, arg20.isShouldGenerateQualityThumb(), arg28, arg27, arg23, arg20.getcurrentAccount()));
            }
        }
    }

    public SparseArray createMediaPaths() {
        int v3_1;
        File v2_2;
        SparseArray v0 = new SparseArray();
        File v1 = AndroidUtilities.getCacheDir();
        if(v1.isDirectory()) {
            goto label_9;
        }

        try {
            v1.mkdirs();
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }

        try {
        label_9:
            new File(v1, ".nomedia").createNewFile();
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }

        v0.put(4, v1);
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("cache path = " + v1);
        }

        try {
            if(!"mounted".equals(Environment.getExternalStorageState())) {
                goto label_142;
            }

            this.telegramPath = new File(Environment.getExternalStorageDirectory(), "Telegram");
            this.telegramPath.mkdirs();
            if(!this.telegramPath.isDirectory()) {
                goto label_146;
            }
        }
        catch(Exception v1_1) {
            goto label_149;
        }

        try {
            v2_2 = new File(this.telegramPath, "Telegram Images");
            v2_2.mkdir();
            if(!v2_2.isDirectory()) {
                goto label_64;
            }

            if(!this.canMoveFiles(v1, v2_2, 0)) {
                goto label_64;
            }

            v0.put(0, v2_2);
            if(!BuildVars.LOGS_ENABLED) {
                goto label_64;
            }

            FileLog.d("image path = " + v2_2);
        }
        catch(Exception v2) {
            try {
                FileLog.e(((Throwable)v2));
            }
            catch(Exception v1_1) {
                goto label_149;
            }
        }

        try {
        label_64:
            v2_2 = new File(this.telegramPath, "Telegram Video");
            v2_2.mkdir();
            if(!v2_2.isDirectory()) {
                goto label_87;
            }

            v3_1 = 2;
            if(!this.canMoveFiles(v1, v2_2, v3_1)) {
                goto label_87;
            }

            v0.put(v3_1, v2_2);
            if(!BuildVars.LOGS_ENABLED) {
                goto label_87;
            }

            FileLog.d("video path = " + v2_2);
        }
        catch(Exception v2) {
            try {
                FileLog.e(((Throwable)v2));
            }
            catch(Exception v1_1) {
                goto label_149;
            }
        }

        try {
        label_87:
            v2_2 = new File(this.telegramPath, "Telegram Audio");
            v2_2.mkdir();
            if(!v2_2.isDirectory()) {
                goto label_114;
            }

            if(!this.canMoveFiles(v1, v2_2, 1)) {
                goto label_114;
            }

            new File(v2_2, ".nomedia").createNewFile();
            v0.put(1, v2_2);
            if(!BuildVars.LOGS_ENABLED) {
                goto label_114;
            }

            FileLog.d("audio path = " + v2_2);
        }
        catch(Exception v2) {
            try {
                FileLog.e(((Throwable)v2));
            }
            catch(Exception v1_1) {
                goto label_149;
            }
        }

        try {
        label_114:
            v2_2 = new File(this.telegramPath, "Telegram Documents");
            v2_2.mkdir();
            if(!v2_2.isDirectory()) {
                goto label_146;
            }

            v3_1 = 3;
            if(!this.canMoveFiles(v1, v2_2, v3_1)) {
                goto label_146;
            }

            new File(v2_2, ".nomedia").createNewFile();
            v0.put(v3_1, v2_2);
            if(!BuildVars.LOGS_ENABLED) {
                goto label_146;
            }

            FileLog.d("documents path = " + v2_2);
            goto label_146;
        }
        catch(Exception v1_1) {
            try {
                FileLog.e(((Throwable)v1_1));
                goto label_146;
            label_142:
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("this Android can\'t rename files");
                }

            label_146:
                SharedConfig.checkSaveToGalleryFiles();
            }
            catch(Exception v1_1) {
            label_149:
                FileLog.e(((Throwable)v1_1));
            }
        }

        return v0;
    }

    public boolean decrementUseCount(String arg4) {
        Object v0 = this.bitmapUseCounts.get(arg4);
        if(v0 == null) {
            return 1;
        }

        if(((Integer)v0).intValue() == 1) {
            this.bitmapUseCounts.remove(arg4);
            return 1;
        }

        this.bitmapUseCounts.put(arg4, Integer.valueOf(((Integer)v0).intValue() - 1));
        return 0;
    }

    private void fileDidFailedLoad(String arg2, int arg3) {
        if(arg3 == 1) {
            return;
        }

        this.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$r_VvJ_EGNLJ9DXEdal1IvzbKng8(this, arg2));
    }

    private void fileDidLoaded(String arg3, File arg4, int arg5) {
        this.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$pjTzJkUNuoE9R9ey9fjv_ZLvtjc(this, arg3, arg5, arg4));
    }

    public static void fillPhotoSizeWithBytes(PhotoSize arg4) {
        if(arg4 != null && arg4.bytes == null) {
            File v0 = FileLoader.getPathToAttach(((TLObject)arg4), true);
            try {
                RandomAccessFile v1 = new RandomAccessFile(v0, "r");
                if((((int)v1.length())) >= 20000) {
                    return;
                }

                arg4.bytes = new byte[((int)v1.length())];
                v1.readFully(arg4.bytes, 0, arg4.bytes.length);
            }
            catch(Throwable v4) {
                FileLog.e(v4);
            }
        }
    }

    private void generateThumb(int arg8, File arg9, FileLocation arg10, String arg11) {
        if((arg8 == 0 || arg8 == 2 || arg8 == 3) && arg9 != null) {
            if(arg10 == null) {
            }
            else if(this.thumbGenerateTasks.get(FileLoader.getAttachFileName(((TLObject)arg10))) == null) {
                this.thumbGeneratingQueue.postRunnable(new ThumbGenerateTask(this, arg8, arg9, arg10, arg11));
            }
        }
    }

    public Float getFileProgress(String arg2) {
        if(arg2 == null) {
            return null;
        }

        return this.fileProgresses.get(arg2);
    }

    public static String getHttpUrlExtension(String arg3, String arg4) {
        String v0 = Uri.parse(arg3).getLastPathSegment();
        if(!TextUtils.isEmpty(((CharSequence)v0)) && v0.length() > 1) {
            arg3 = v0;
        }

        int v0_1 = arg3.lastIndexOf(46);
        arg3 = v0_1 != -1 ? arg3.substring(v0_1 + 1) : null;
        if(arg3 == null || arg3.length() == 0 || arg3.length() > 4) {
            arg3 = arg4;
        }

        return arg3;
    }

    public BitmapDrawable getImageFromMemory(TLObject arg3, String arg4, String arg5) {
        StringBuilder v4;
        String v0_1;
        BitmapDrawable v0 = null;
        if(arg3 == null && arg4 == null) {
            return v0;
        }

        if(arg4 != null) {
            v0_1 = Utilities.MD5(arg4);
        }
        else {
            if((arg3 instanceof FileLocation)) {
                v4 = new StringBuilder();
                v4.append(((FileLocation)arg3).volume_id);
                v4.append("_");
                v4.append(((FileLocation)arg3).local_id);
            }
            else if((arg3 instanceof Document)) {
                if(((Document)arg3).version == 0) {
                    v4 = new StringBuilder();
                    v4.append(((Document)arg3).dc_id);
                    v4.append("_");
                    v4.append(((Document)arg3).id);
                }
                else {
                    v4 = new StringBuilder();
                    v4.append(((Document)arg3).dc_id);
                    v4.append("_");
                    v4.append(((Document)arg3).id);
                    v4.append("_");
                    v4.append(((Document)arg3).version);
                }

                v0_1 = v4.toString();
                goto label_64;
            }
            else {
                if(!(arg3 instanceof SecureDocument)) {
                    goto label_60;
                }

                v4 = new StringBuilder();
                v4.append(((SecureDocument)arg3).secureFile.dc_id);
                v4.append("_");
                v4.append(((SecureDocument)arg3).secureFile.id);
            }

            v0_1 = v4.toString();
            goto label_64;
        label_60:
            if(!(arg3 instanceof WebFile)) {
                goto label_64;
            }

            v0_1 = Utilities.MD5(((WebFile)arg3).url);
        }

    label_64:
        if(arg5 != null) {
            v0_1 = (((String)v0)) + "@" + arg5;
        }

        return this.memCache.get(((String)v0));
    }

    public BitmapDrawable getImageFromMemory(String arg2) {
        return this.memCache.get(arg2);
    }

    public static ImageLoader getInstance() {
        ImageLoader v0 = ImageLoader.Instance;
        if(v0 == null) {
            Class v1 = ImageLoader.class;
            __monitor_enter(v1);
            try {
                v0 = ImageLoader.Instance;
                if(v0 == null) {
                    v0 = new ImageLoader();
                    ImageLoader.Instance = v0;
                }

                __monitor_exit(v1);
                return v0;
            label_12:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_12;
            }

            throw v0_1;
        }

        return v0;
    }

    public String getReplacedKey(String arg2) {
        return this.replacedBitmaps.get(arg2);
    }

    private void httpFileLoadError(String arg3) {
        this.imageLoadQueue.postRunnable(new -$$Lambda$ImageLoader$ZaOfz0BNqcCgsH2qEkswBKN9Vb0(this, arg3));
    }

    public void incrementUseCount(String arg4) {
        Object v0 = this.bitmapUseCounts.get(arg4);
        if(v0 == null) {
            this.bitmapUseCounts.put(arg4, Integer.valueOf(1));
        }
        else {
            this.bitmapUseCounts.put(arg4, Integer.valueOf(((Integer)v0).intValue() + 1));
        }
    }

    public boolean isInCache(String arg2) {
        boolean v2 = this.memCache.get(arg2) != null ? true : false;
        return v2;
    }

    public boolean isLoadingHttpFile(String arg2) {
        return this.httpFileLoadTasksByKeys.containsKey(arg2);
    }

    public static void lambda$cancelForceLoadingForImageReceiver$4(ImageLoader arg1, String arg2) {
        arg1.forceLoadingImages.remove(arg2);
    }

    public static void lambda$cancelLoadingForImageReceiver$2(ImageLoader arg5, int arg6, ImageReceiver arg7) {
        int v0 = 2;
        if(arg6 == 1) {
            arg6 = 0;
            v0 = 1;
        }
        else if(arg6 == v0) {
            arg6 = 1;
        }
        else {
            arg6 = 0;
        }

        while(arg6 < v0) {
            boolean v3 = arg6 == 0 ? true : false;
            int v3_1 = arg7.getTag(v3);
            if(arg6 == 0) {
                arg5.removeFromWaitingForThumb(v3_1);
            }

            if(v3_1 != 0) {
                Object v3_2 = arg5.imageLoadingByTag.get(v3_1);
                if(v3_2 != null) {
                    ((CacheImage)v3_2).removeImageReceiver(arg7);
                }
            }

            ++arg6;
        }
    }

    public static void lambda$checkMediaPaths$1(ImageLoader arg2) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$54eJN0C_gHRDy8W6-JUJzMnPHt0(arg2.createMediaPaths()));
    }

    public static void lambda$createLoadOperationForImageReceiver$5(ImageLoader arg17, int arg18, String arg19, String arg20, int arg21, ImageReceiver arg22, String arg23, String arg24, boolean arg25, MessageObject arg26, TLObject arg27, boolean arg28, int arg29, int arg30, String arg31, int arg32) {
        int v3_1;
        int v2_1;
        File v8;
        int v9_1;
        ThumbGenerateInfo v14_2;
        File v12_3;
        File v13_1;
        File v14_1;
        int v15_1;
        int v12_1;
        boolean v12;
        Object v14;
        ImageLoader v0 = arg17;
        int v1 = arg18;
        String v2 = arg19;
        String v3 = arg20;
        int v4 = arg21;
        ImageReceiver v5 = arg22;
        String v6 = arg23;
        String v7 = arg24;
        MessageObject v9 = arg26;
        TLObject v10 = arg27;
        int v11 = arg29;
        if(v1 != 2) {
            v14 = v0.imageLoadingByUrl.get(v2);
            Object v15 = v0.imageLoadingByKeys.get(v3);
            Object v13 = v0.imageLoadingByTag.get(v4);
            if(v13 != null) {
                if(v13 != v15) {
                    if(v13 != v14) {
                        goto label_33;
                    }
                    else if(v15 == null) {
                        v12 = v1 != 0 ? true : false;
                        ((CacheImage)v13).replaceImageReceiver(v5, v3, v6, v12);
                    }
                }

                v12_1 = 1;
                goto label_35;
            label_33:
                ((CacheImage)v13).removeImageReceiver(v5);
                goto label_34;
            }
            else {
            label_34:
                v12_1 = 0;
            }

        label_35:
            if(v12_1 != 0 || v15 == null) {
                v15_1 = v12_1;
            }
            else {
                v12 = v1 != 0 ? true : false;
                ((CacheImage)v15).addImageReceiver(v5, v3, v6, v12);
                v15_1 = 1;
            }

            if(v15_1 != 0) {
                goto label_55;
            }

            if(v14 == null) {
                goto label_55;
            }

            v12 = v1 != 0 ? true : false;
            ((CacheImage)v14).addImageReceiver(v5, v3, v6, v12);
            v15_1 = 1;
        }
        else {
            v15_1 = 0;
        }

    label_55:
        if(v15_1 == 0) {
            v12_1 = 4;
            if(v7 == null) {
                if(v1 != 0) {
                    if(arg25) {
                        File v15_2 = FileLoader.getDirectory(v12_1);
                        StringBuilder v12_2 = new StringBuilder();
                        v12_2.append("q_");
                        v12_2.append(v2);
                        v14_1 = new File(v15_2, v12_2.toString());
                        if(!v14_1.exists()) {
                            goto label_111;
                        }
                        else {
                            v13_1 = v14_1;
                            v15_1 = 1;
                        }
                    }
                    else {
                    label_111:
                        v13_1 = null;
                        v15_1 = 0;
                    }

                    if(v9 != null) {
                        if(v9.messageOwner.attachPath == null || v9.messageOwner.attachPath.length() <= 0) {
                        label_127:
                            v12_3 = null;
                        }
                        else {
                            v12_3 = new File(v9.messageOwner.attachPath);
                            if(!v12_3.exists()) {
                                goto label_127;
                            }
                        }

                        if(v12_3 == null) {
                            v12_3 = FileLoader.getPathToMessage(v9.messageOwner);
                        }

                        if((arg25) && v13_1 == null) {
                            String v8_1 = arg26.getFileName();
                            v14 = v0.waitingForQualityThumb.get(v8_1);
                            if(v14 == null) {
                                v14_2 = new ThumbGenerateInfo(v0, null);
                                ThumbGenerateInfo.access$3202(v14_2, v10);
                                ThumbGenerateInfo.access$3302(v14_2, v6);
                                v0.waitingForQualityThumb.put(v8_1, v14_2);
                            }

                            ThumbGenerateInfo.access$2608(v14_2);
                            v0.waitingForQualityThumbByTag.put(v4, v8_1);
                        }

                        if(!v12_3.exists()) {
                            goto label_154;
                        }

                        if(!arg28) {
                            goto label_154;
                        }

                        v0.generateThumb(arg26.getFileType(), v12_3, v10, v6);
                    }

                label_154:
                    v8 = v13_1;
                    v4 = 0;
                    v9_1 = 2;
                    goto label_162;
                }

            label_158:
                v4 = 0;
                v8 = null;
            label_160:
                v9_1 = 2;
                v15_1 = 0;
            }
            else if(!v7.startsWith("http")) {
                if(v7.startsWith("thumb://")) {
                    v4 = v7.indexOf(":", 8);
                    if(v4 >= 0) {
                        v8 = new File(v7.substring(v4 + 1));
                    }
                    else {
                        goto label_74;
                    }
                }
                else if(v7.startsWith("vthumb://")) {
                    v4 = v7.indexOf(":", 9);
                    if(v4 >= 0) {
                        v8 = new File(v7.substring(v4 + 1));
                    }
                    else {
                    label_74:
                        v8 = null;
                    }
                }
                else {
                    v8 = new File(v7);
                }

                v4 = 1;
                goto label_160;
            }
            else {
                goto label_158;
            }

        label_162:
            if(v1 == v9_1) {
                return;
            }

            v9_1 = ((v10 instanceof TL_documentEncrypted)) || ((v10 instanceof TL_fileEncryptedLocation)) ? 1 : 0;
            CacheImage v12_4 = new CacheImage(v0, null);
            if(v7 == null || (v7.startsWith("vthumb")) || (v7.startsWith("thumb"))) {
                if((v10 instanceof WebFile)) {
                    if(!MessageObject.isGifDocument(v10)) {
                    }
                    else {
                        goto label_206;
                    }
                }

                if(!(v10 instanceof Document)) {
                    goto label_207;
                }

                TLObject v13_3 = v10;
                if(!MessageObject.isGifDocument(((Document)v13_3)) && !MessageObject.isRoundVideoDocument(((Document)v13_3))) {
                    goto label_207;
                }

            label_206:
                v12_4.animatedFile = true;
            }
            else {
                String v13_2 = ImageLoader.getHttpUrlExtension(v7, "jpg");
                if(v13_2.equals("mp4")) {
                    goto label_206;
                }
                else if(v13_2.equals("gif")) {
                    goto label_206;
                }
            }

        label_207:
            if(v8 == null) {
                if((v10 instanceof SecureDocument)) {
                    v12_4.secureDocument = v10;
                    v4 = v12_4.secureDocument.secureFile.dc_id == -2147483648 ? 1 : 0;
                    v8 = new File(FileLoader.getDirectory(4), v2);
                }
                else {
                    if(v11 == 0 && arg30 > 0 && v7 == null) {
                        if(v9_1 != 0) {
                        }
                        else {
                            v9_1 = 3;
                            if((v10 instanceof Document)) {
                                v8 = MessageObject.isVideoDocument(v10) ? new File(FileLoader.getDirectory(2), v2) : new File(FileLoader.getDirectory(v9_1), v2);
                            }
                            else if((v10 instanceof WebFile)) {
                                v8 = new File(FileLoader.getDirectory(v9_1), v2);
                            }
                            else {
                                v8 = new File(FileLoader.getDirectory(0), v2);
                            }

                            goto label_284;
                        }
                    }

                    v9_1 = 4;
                    v8 = new File(FileLoader.getDirectory(v9_1), v2);
                    if(v8.exists()) {
                        v15_1 = 1;
                        goto label_284;
                    }

                    if(v11 != 2) {
                        goto label_284;
                    }

                    v14_1 = FileLoader.getDirectory(v9_1);
                    StringBuilder v9_2 = new StringBuilder();
                    v9_2.append(v2);
                    v9_2.append(".enc");
                    v8 = new File(v14_1, v9_2.toString());
                    v4 = v4;
                }
            }

        label_284:
            boolean v9_3 = v1 != 0 ? true : false;
            v12_4.selfThumb = v9_3;
            v12_4.key = v3;
            v12_4.filter = v6;
            v12_4.httpUrl = v7;
            v12_4.ext = arg31;
            v12_4.currentAccount = arg32;
            if(v11 == 2) {
                File v9_4 = FileLoader.getInternalCacheDir();
                StringBuilder v13_4 = new StringBuilder();
                v13_4.append(v2);
                v13_4.append(".enc.key");
                v12_4.encryptionKeyPath = new File(v9_4, v13_4.toString());
            }

            v9_3 = v1 != 0 ? true : false;
            v12_4.addImageReceiver(v5, v3, v6, v9_3);
            if(v4 == 0 && v15_1 == 0) {
                if(v8.exists()) {
                }
                else {
                    v12_4.url = v2;
                    v12_4.location = v10;
                    v0.imageLoadingByUrl.put(v2, v12_4);
                    if(v7 == null) {
                        if((v10 instanceof FileLocation)) {
                            TLObject v1_1 = v10;
                            v2_1 = arg29;
                            if(v2_1 == 0) {
                                v3_1 = arg30;
                                if(v3_1 > 0 && ((FileLocation)v1_1).key == null) {
                                    goto label_335;
                                }

                                v2_1 = 1;
                            }
                            else {
                                v3_1 = arg30;
                            }

                        label_335:
                            FileLoader.getInstance(arg32).loadFile(((FileLocation)v1_1), arg31, v3_1, v2_1);
                        }
                        else {
                            v2_1 = arg29;
                            if((v10 instanceof Document)) {
                                FileLoader.getInstance(arg32).loadFile(v10, true, v2_1);
                                goto label_359;
                            }

                            if((v10 instanceof SecureDocument)) {
                                FileLoader.getInstance(arg32).loadFile(v10, true);
                                goto label_359;
                            }

                            if(!(v10 instanceof WebFile)) {
                                goto label_359;
                            }

                            FileLoader.getInstance(arg32).loadFile(v10, true, v2_1);
                        }

                    label_359:
                        if(!arg22.isForceLoding()) {
                            return;
                        }

                        v0.forceLoadingImages.put(v12_4.key, Integer.valueOf(0));
                    }
                    else {
                        String v1_2 = Utilities.MD5(arg24);
                        File v2_2 = FileLoader.getDirectory(4);
                        StringBuilder v5_1 = new StringBuilder();
                        v5_1.append(v1_2);
                        v5_1.append("_temp.jpg");
                        v12_4.tempFilePath = new File(v2_2, v5_1.toString());
                        v12_4.finalFilePath = v8;
                        v12_4.httpTask = new HttpImageTask(v0, v12_4, arg30);
                        v0.httpTasks.add(v12_4.httpTask);
                        v0.runHttpTasks(false);
                    }

                    return;
                }
            }

            v12_4.finalFilePath = v8;
            v12_4.cacheTask = new CacheOutTask(v0, v12_4);
            v0.imageLoadingByKeys.put(v3, v12_4);
            DispatchQueue v1_3 = v1 != 0 ? v0.cacheThumbOutQueue : v0.cacheOutQueue;
            v1_3.postRunnable(v12_4.cacheTask);
        }
    }

    public static void lambda$fileDidFailedLoad$8(ImageLoader arg1, String arg2) {
        Object v2 = arg1.imageLoadingByUrl.get(arg2);
        if(v2 != null) {
            ((CacheImage)v2).setImageAndClear(null);
        }
    }

    public static void lambda$fileDidLoaded$7(ImageLoader arg8, String arg9, int arg10, File arg11) {
        Object v0 = arg8.waitingForQualityThumb.get(arg9);
        if(v0 != null) {
            arg8.generateThumb(arg10, arg11, ThumbGenerateInfo.access$3200(((ThumbGenerateInfo)v0)), ThumbGenerateInfo.access$3300(((ThumbGenerateInfo)v0)));
            arg8.waitingForQualityThumb.remove(arg9);
        }

        Object v10 = arg8.imageLoadingByUrl.get(arg9);
        if(v10 == null) {
            return;
        }

        arg8.imageLoadingByUrl.remove(arg9);
        ArrayList v9 = new ArrayList();
        int v0_1 = 0;
        int v1;
        for(v1 = 0; v1 < ((CacheImage)v10).imageReceiverArray.size(); ++v1) {
            Object v2 = ((CacheImage)v10).keys.get(v1);
            Object v3 = ((CacheImage)v10).filters.get(v1);
            Object v4 = ((CacheImage)v10).thumbs.get(v1);
            Object v5 = ((CacheImage)v10).imageReceiverArray.get(v1);
            Object v6 = arg8.imageLoadingByKeys.get(v2);
            if(v6 == null) {
                CacheImage v6_1 = new CacheImage(arg8, null);
                v6_1.secureDocument = ((CacheImage)v10).secureDocument;
                v6_1.currentAccount = ((CacheImage)v10).currentAccount;
                v6_1.finalFilePath = arg11;
                v6_1.key = ((String)v2);
                v6_1.httpUrl = ((CacheImage)v10).httpUrl;
                v6_1.selfThumb = ((Boolean)v4).booleanValue();
                v6_1.ext = ((CacheImage)v10).ext;
                v6_1.encryptionKeyPath = ((CacheImage)v10).encryptionKeyPath;
                ((CacheImage)v6).cacheTask = new CacheOutTask(arg8, ((CacheImage)v6));
                v6_1.filter = ((String)v3);
                v6_1.animatedFile = ((CacheImage)v10).animatedFile;
                arg8.imageLoadingByKeys.put(v2, v6_1);
                v9.add(v6_1.cacheTask);
            }

            ((CacheImage)v6).addImageReceiver(((ImageReceiver)v5), ((String)v2), ((String)v3), ((Boolean)v4).booleanValue());
        }

        while(v0_1 < v9.size()) {
            v10 = v9.get(v0_1);
            DispatchQueue v11 = CacheOutTask.access$3500(((CacheOutTask)v10)).selfThumb ? arg8.cacheThumbOutQueue : arg8.cacheOutQueue;
            v11.postRunnable(((Runnable)v10));
            ++v0_1;
        }
    }

    public static void lambda$httpFileLoadError$6(ImageLoader arg3, String arg4) {
        Object v4 = arg3.imageLoadingByUrl.get(arg4);
        if(v4 == null) {
            return;
        }

        ((CacheImage)v4).httpTask = new HttpImageTask(arg3, HttpImageTask.access$3600(((CacheImage)v4).httpTask), HttpImageTask.access$3700(((CacheImage)v4).httpTask));
        arg3.httpTasks.add(((CacheImage)v4).httpTask);
        arg3.runHttpTasks(false);
    }

    static void lambda$null$0(SparseArray arg0) {
        FileLoader.setMediaDirs(arg0);
    }

    public static void lambda$null$9(ImageLoader arg1, HttpFileTask arg2) {
        arg1.httpFileLoadTasks.add(arg2);
        arg1.runHttpFileLoadTasks(null, 0);
    }

    public static void lambda$replaceImageInCache$3(ImageLoader arg0, String arg1, String arg2, FileLocation arg3) {
        arg0.replaceImageInCacheInternal(arg1, arg2, arg3);
    }

    public static void lambda$runHttpFileLoadTasks$10(ImageLoader arg9, HttpFileTask arg10, int arg11) {
        if(arg10 != null) {
            --arg9.currentHttpFileLoadTasksCount;
        }

        int v2 = 2;
        if(arg10 != null) {
            if(arg11 == 1) {
                if(HttpFileTask.access$2700(arg10)) {
                    -$$Lambda$ImageLoader$WNDo8A3XGEBlPVRgwx5buqc5RSE v3 = new -$$Lambda$ImageLoader$WNDo8A3XGEBlPVRgwx5buqc5RSE(arg9, new HttpFileTask(arg9, HttpFileTask.access$2800(arg10), HttpFileTask.access$2900(arg10), HttpFileTask.access$3000(arg10), HttpFileTask.access$3100(arg10)));
                    arg9.retryHttpsTasks.put(HttpFileTask.access$2800(arg10), v3);
                    AndroidUtilities.runOnUIThread(((Runnable)v3), 1000);
                }
                else {
                    arg9.httpFileLoadTasksByKeys.remove(HttpFileTask.access$2800(arg10));
                    NotificationCenter v11 = NotificationCenter.getInstance(HttpFileTask.access$3100(arg10));
                    int v3_1 = NotificationCenter.httpFileDidFailedLoad;
                    Object[] v4 = new Object[v2];
                    v4[0] = HttpFileTask.access$2800(arg10);
                    v4[1] = Integer.valueOf(0);
                    v11.postNotificationName(v3_1, v4);
                }
            }
            else if(arg11 == v2) {
                arg9.httpFileLoadTasksByKeys.remove(HttpFileTask.access$2800(arg10));
                File v3_2 = FileLoader.getDirectory(4);
                StringBuilder v4_1 = new StringBuilder();
                v4_1.append(Utilities.MD5(HttpFileTask.access$2800(arg10)));
                v4_1.append(".");
                v4_1.append(HttpFileTask.access$3000(arg10));
                File v11_1 = new File(v3_2, v4_1.toString());
                if(!HttpFileTask.access$2900(arg10).renameTo(v11_1)) {
                    v11_1 = HttpFileTask.access$2900(arg10);
                }

                String v11_2 = v11_1.toString();
                NotificationCenter v3_3 = NotificationCenter.getInstance(HttpFileTask.access$3100(arg10));
                int v4_2 = NotificationCenter.httpFileDidLoaded;
                Object[] v5 = new Object[v2];
                v5[0] = HttpFileTask.access$2800(arg10);
                v5[1] = v11_2;
                v3_3.postNotificationName(v4_2, v5);
            }
        }

        while(arg9.currentHttpFileLoadTasksCount < v2) {
            if(arg9.httpFileLoadTasks.isEmpty()) {
                return;
            }

            Object v10 = arg9.httpFileLoadTasks.poll();
            Executor v11_3 = AsyncTask.THREAD_POOL_EXECUTOR;
            Void[] v3_4 = new Void[3];
            v3_4[0] = null;
            v3_4[1] = null;
            v3_4[v2] = null;
            ((HttpFileTask)v10).executeOnExecutor(v11_3, ((Object[])v3_4));
            ++arg9.currentHttpFileLoadTasksCount;
        }
    }

    public static Bitmap loadBitmap(String arg16, Uri arg17, float arg18, float arg19, boolean arg20) {
        Bitmap v7_1;
        Bitmap v3_1;
        Bitmap v0_5;
        Bitmap v1_1;
        Matrix v2_1;
        InputStream v8;
        String v6;
        String v0;
        Uri v1 = arg17;
        BitmapFactory$Options v4 = new BitmapFactory$Options();
        v4.inJustDecodeBounds = true;
        if(arg16 == null) {
            if(v1 == null) {
                goto label_21;
            }

            if(arg17.getScheme() == null) {
                goto label_21;
            }

            if(arg17.getScheme().contains("file")) {
                v0 = arg17.getPath();
            }
            else {
                try {
                    v0 = AndroidUtilities.getPath(arg17);
                }
                catch(Throwable v0_1) {
                    FileLog.e(v0_1);
                    goto label_21;
                }
            }

            v6 = v0;
        }
        else {
        label_21:
            v6 = arg16;
        }

        Rect v7 = null;
        if(v6 != null) {
            BitmapFactory.decodeFile(v6, v4);
            goto label_40;
        }
        else if(v1 != null) {
            try {
                InputStream v0_2 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(v1);
                BitmapFactory.decodeStream(v0_2, v7, v4);
                v0_2.close();
                v8 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(v1);
            }
            catch(Throwable v0_1) {
                FileLog.e(v0_1);
                return ((Bitmap)v7);
            }
        }
        else {
        label_40:
            v8 = ((InputStream)v7);
        }

        float v0_3 = ((float)v4.outWidth);
        float v9 = ((float)v4.outHeight);
        v0_3 = arg20 ? Math.max(v0_3 / arg18, v9 / arg19) : Math.min(v0_3 / arg18, v9 / arg19);
        if(v0_3 < 1f) {
            v0_3 = 1f;
        }

        boolean v2 = false;
        v4.inJustDecodeBounds = false;
        v4.inSampleSize = ((int)v0_3);
        if(v4.inSampleSize % 2 != 0) {
            int v0_4;
            for(v0_4 = 1; true; v0_4 = v3) {
                int v3 = v0_4 * 2;
                if(v3 >= v4.inSampleSize) {
                    break;
                }
            }

            v4.inSampleSize = v0_4;
        }

        if(Build$VERSION.SDK_INT < 21) {
            v2 = true;
        }

        v4.inPurgeable = v2;
        if(v6 != null) {
            v0 = v6;
        }
        else if(v1 != null) {
            v0 = AndroidUtilities.getPath(arg17);
        }
        else {
            v0 = ((String)v7);
        }

        if(v0 != null) {
            try {
                v0_4 = new a(v0).a("Orientation", 1);
                v2_1 = new Matrix();
                if(v0_4 != 3) {
                }
                else {
                    goto label_101;
                }
            }
            catch(Throwable ) {
                goto label_103;
            }

            if(v0_4 == 6) {
                v0_3 = 90f;
                goto label_97;
            label_101:
                v0_3 = 180f;
            }
            else if(v0_4 != 8) {
                goto label_104;
            }
            else {
                v0_3 = 270f;
            }

            try {
            label_97:
                v2_1.postRotate(v0_3);
            }
            catch(Throwable ) {
            }
        }
        else {
        label_103:
            v2_1 = ((Matrix)v7);
        }

    label_104:
        if(v6 != null) {
            try {
                v1_1 = BitmapFactory.decodeFile(v6, v4);
                if(v1_1 == null) {
                    return v1_1;
                }
            }
            catch(Throwable v0_1) {
                v1_1 = ((Bitmap)v7);
                goto label_128;
            }

            try {
                if(v4.inPurgeable) {
                    Utilities.pinBitmap(v1_1);
                }

                v0_5 = Bitmaps.createBitmap(v1_1, 0, 0, v1_1.getWidth(), v1_1.getHeight(), v2_1, true);
                if(v0_5 == v1_1) {
                    return v1_1;
                }

                v1_1.recycle();
                goto label_120;
            }
            catch(Throwable v0_1) {
            }

        label_128:
            FileLog.e(v0_1);
            ImageLoader.getInstance().clearMemory();
            if(v1_1 == null) {
                try {
                    v3_1 = BitmapFactory.decodeFile(v6, v4);
                    if(v3_1 != null) {
                        goto label_134;
                    }

                    goto label_141;
                }
                catch(Throwable v0_1) {
                    goto label_144;
                }

                try {
                label_134:
                    if(v4.inPurgeable) {
                        Utilities.pinBitmap(v3_1);
                    }
                }
                catch(Throwable v0_1) {
                    v1_1 = v3_1;
                    goto label_157;
                }

            label_141:
                v1_1 = v3_1;
            }

            if(v1_1 != null) {
                try {
                    v0_5 = Bitmaps.createBitmap(v1_1, 0, 0, v1_1.getWidth(), v1_1.getHeight(), v2_1, true);
                    if(v0_5 != v1_1) {
                        v1_1.recycle();
                        goto label_120;
                    }

                    goto label_159;
                }
                catch(Throwable v0_1) {
                label_144:
                }

            label_157:
                FileLog.e(v0_1);
            }
            else {
            label_159:
                v0_5 = v1_1;
            label_120:
                v7_1 = v0_5;
                return v7_1;
            }

            return v1_1;
        }

        if(v1 != null) {
            try {
                v1_1 = BitmapFactory.decodeStream(v8, v7, v4);
                if(v1_1 == null) {
                    goto label_182;
                }

                goto label_164;
            }
            catch(Throwable v0_1) {
            }
            catch(Throwable v0_1) {
                goto label_193;
                try {
                label_164:
                    if(v4.inPurgeable) {
                        Utilities.pinBitmap(v1_1);
                    }

                    v0_5 = Bitmaps.createBitmap(v1_1, 0, 0, v1_1.getWidth(), v1_1.getHeight(), v2_1, true);
                    if(v0_5 == v1_1) {
                        goto label_182;
                    }

                    v1_1.recycle();
                    v7_1 = v0_5;
                    goto label_183;
                }
                catch(Throwable v0_1) {
                label_190:
                    Throwable v1_2 = v0_1;
                    try {
                        v8.close();
                    }
                    catch(Throwable v0_1) {
                        FileLog.e(v0_1);
                    }

                    throw v1_2;
                }
                catch(Throwable v0_1) {
                    v7_1 = v1_1;
                    try {
                    label_193:
                        FileLog.e(v0_1);
                    }
                    catch(Throwable v0_1) {
                        goto label_190;
                    }

                    try {
                        v8.close();
                        return v7_1;
                    }
                    catch(Throwable v0_1) {
                        goto label_187;
                    }
                }
            }

        label_182:
            v7_1 = v1_1;
            try {
            label_183:
                v8.close();
            }
            catch(Throwable v0_1) {
            label_187:
                FileLog.e(v0_1);
            }
        }

        return v7_1;
    }

    public void loadHttpFile(String arg8, String arg9, int arg10) {
        if(arg8 != null && arg8.length() != 0) {
            if(this.httpFileLoadTasksByKeys.containsKey(arg8)) {
            }
            else {
                String v5 = ImageLoader.getHttpUrlExtension(arg8, arg9);
                File v9 = FileLoader.getDirectory(4);
                StringBuilder v0 = new StringBuilder();
                v0.append(Utilities.MD5(arg8));
                v0.append("_temp.");
                v0.append(v5);
                File v4 = new File(v9, v0.toString());
                v4.delete();
                HttpFileTask v9_1 = new HttpFileTask(this, arg8, v4, v5, arg10);
                this.httpFileLoadTasks.add(v9_1);
                this.httpFileLoadTasksByKeys.put(arg8, v9_1);
                this.runHttpFileLoadTasks(null, 0);
            }
        }
    }

    public void loadImageForImageReceiver(ImageReceiver arg21) {
        int v10_1;
        int v8_2;
        TLObject v5_1;
        String v19;
        String v2;
        ImageReceiver v1_1;
        ImageLoader v0_2;
        int v18;
        int v9_1;
        String v8_1;
        String v7_1;
        TLObject v6_1;
        String v15;
        TLObject v16;
        StringBuilder v7;
        String v6;
        int v0_1;
        ImageLoader v11 = this;
        ImageReceiver v12 = arg21;
        if(v12 == null) {
            return;
        }

        String v0 = arg21.getKey();
        int v1 = 0;
        if(v0 != null) {
            BitmapDrawable v3 = v11.memCache.get(v0);
            if(v3 != null) {
                v11.cancelLoadingForImageReceiver(v12, 0);
                v12.setImageBitmapByKey(v3, v0, false, true);
                if(!arg21.isForcePreview()) {
                    return;
                }
                else {
                    v0_1 = 1;
                }
            }
            else {
                goto label_18;
            }
        }
        else {
        label_18:
            v0_1 = 0;
        }

        String v3_1 = arg21.getThumbKey();
        if(v3_1 != null) {
            BitmapDrawable v4 = v11.memCache.get(v3_1);
            if(v4 != null) {
                v12.setImageBitmapByKey(v4, v3_1, true, true);
                v11.cancelLoadingForImageReceiver(v12, 1);
                if(v0_1 != 0 && (arg21.isForcePreview())) {
                    return;
                }

                v0_1 = 1;
            }
            else {
                goto label_32;
            }
        }
        else {
        label_32:
            v0_1 = 0;
        }

        FileLocation v5 = arg21.getThumbLocation();
        TLObject v3_2 = arg21.getImageLocation();
        String v13 = arg21.getHttpImageLocation();
        String v4_1 = arg21.getExt();
        if(v4_1 == null) {
            v4_1 = "jpg";
        }

        String v14 = v4_1;
        v4_1 = null;
        if(v13 != null) {
            v6 = Utilities.MD5(v13);
            v7 = new StringBuilder();
            v7.append(v6);
            v7.append(".");
            v7.append(ImageLoader.getHttpUrlExtension(v13, "jpg"));
            v16 = v3_2;
            v15 = v7.toString();
        }
        else if(v3_2 != null) {
            if((v3_2 instanceof FileLocation)) {
                v6_1 = v3_2;
                v7_1 = ((FileLocation)v6_1).volume_id + "_" + ((FileLocation)v6_1).local_id;
                v8_1 = v7_1 + "." + v14;
                if(arg21.getExt() != null) {
                    goto label_84;
                }
                else if(((FileLocation)v6_1).key != null) {
                    goto label_84;
                }
                else if(((FileLocation)v6_1).volume_id == -2147483648) {
                    if(((FileLocation)v6_1).local_id >= 0) {
                        goto label_197;
                    }

                    goto label_84;
                }
            }
            else if((v3_2 instanceof WebFile)) {
                v7_1 = FileLoader.getExtensionByMime(v3_2.mime_type);
                v8_1 = Utilities.MD5(v3_2.url);
                StringBuilder v9 = new StringBuilder();
                v9.append(v8_1);
                v9.append(".");
                v9.append(ImageLoader.getHttpUrlExtension(v3_2.url, v7_1));
                v7_1 = v8_1;
                v8_1 = v9.toString();
            }
            else if((v3_2 instanceof SecureDocument)) {
                v6 = v3_2.secureFile.dc_id + "_" + v3_2.secureFile.id;
                v8_1 = v6 + "." + v14;
                v7_1 = v6;
            }
            else if((v3_2 instanceof Document)) {
                v6_1 = v3_2;
                if(((Document)v6_1).id != 0) {
                    if(((Document)v6_1).dc_id == 0) {
                    }
                    else {
                        if(((Document)v6_1).version == 0) {
                            v7 = new StringBuilder();
                            v7.append(((Document)v6_1).dc_id);
                            v7.append("_");
                            v7.append(((Document)v6_1).id);
                        }
                        else {
                            v7 = new StringBuilder();
                            v7.append(((Document)v6_1).dc_id);
                            v7.append("_");
                            v7.append(((Document)v6_1).id);
                            v7.append("_");
                            v7.append(((Document)v6_1).version);
                        }

                        v7_1 = v7.toString();
                        v8_1 = FileLoader.getDocumentFileName(((Document)v6_1));
                        if(v8_1 != null) {
                            v9_1 = v8_1.lastIndexOf(46);
                            if(v9_1 == -1) {
                                goto label_172;
                            }
                            else {
                                v8_1 = v8_1.substring(v9_1);
                            }
                        }
                        else {
                        label_172:
                            v8_1 = "";
                        }

                        if(v8_1.length() <= 1) {
                            if(((Document)v6_1).mime_type != null && (((Document)v6_1).mime_type.equals("video/mp4"))) {
                                v8_1 = ".mp4";
                                goto label_184;
                            }

                            v8_1 = "";
                        }

                    label_184:
                        v8_1 = v7_1 + v8_1;
                        if(MessageObject.isGifDocument(((Document)v6_1))) {
                            goto label_197;
                        }

                        if(MessageObject.isRoundVideoDocument(((Document)v6_1))) {
                            goto label_197;
                        }

                    label_84:
                        v1 = 1;
                        goto label_197;
                    }
                }

                return;
            }
            else {
                v7_1 = v4_1;
                v8_1 = v7_1;
            }

        label_197:
            if((((FileLocation)v3_2)) == v5) {
                v6 = v4_1;
                v15 = v6;
                v16 = ((TLObject)v15);
                goto label_209;
            }

            v16 = v3_2;
            v6 = v7_1;
            v15 = v8_1;
        }
        else {
            v16 = v3_2;
            v6 = v4_1;
            v15 = v6;
        }

    label_209:
        if(v5 != null) {
            v4_1 = v5.volume_id + "_" + v5.local_id;
            v3_1 = v4_1 + "." + v14;
        }
        else {
            v3_1 = v4_1;
        }

        String v10 = arg21.getFilter();
        v7_1 = arg21.getThumbFilter();
        if(v6 != null && v10 != null) {
            v6 = v6 + "@" + v10;
        }

        String v17 = v6;
        if(v4_1 != null && v7_1 != null) {
            v4_1 = v4_1 + "@" + v7_1;
        }

        if(v13 != null) {
            v8_1 = null;
            v18 = v0_1 != 0 ? 2 : 1;
            v0_2 = this;
            v1_1 = arg21;
            v2 = v4_1;
            v4_1 = v14;
            v19 = v10;
            v0_2.createLoadOperationForImageReceiver(v1_1, v2, v3_1, v4_1, ((TLObject)v5), v8_1, v7_1, 0, 1, v18);
            v5_1 = null;
            v8_2 = 0;
            v9_1 = 1;
            v10_1 = 0;
            v2 = v17;
            v3_1 = v15;
            v6 = v13;
            v7_1 = v19;
        }
        else {
            v19 = v10;
            v8_2 = arg21.getCacheType();
            int v13_1 = v8_2 != 0 || v1 == 0 ? v8_2 : 1;
            v8_1 = null;
            v10_1 = v13_1 == 0 ? 1 : v13_1;
            v18 = v0_1 != 0 ? 2 : 1;
            v0_2 = this;
            v1_1 = arg21;
            v2 = v4_1;
            v4_1 = v14;
            v0_2.createLoadOperationForImageReceiver(v1_1, v2, v3_1, v4_1, ((TLObject)v5), v8_1, v7_1, 0, v10_1, v18);
            v6 = null;
            v8_2 = arg21.getSize();
            v10_1 = 0;
            v2 = v17;
            v3_1 = v15;
            v5_1 = v16;
            v7_1 = v19;
            v9_1 = v13_1;
        }

        v0_2.createLoadOperationForImageReceiver(v1_1, v2, v3_1, v4_1, v5_1, v6, v7_1, v8_2, v9_1, v10_1);
    }

    private void performReplace(String arg7, String arg8) {
        BitmapDrawable v0 = this.memCache.get(arg7);
        this.replacedBitmaps.put(arg7, arg8);
        if(v0 != null) {
            BitmapDrawable v1 = this.memCache.get(arg8);
            int v2 = 0;
            if(v1 != null && v1.getBitmap() != null && v0.getBitmap() != null) {
                Bitmap v1_1 = v1.getBitmap();
                Bitmap v3 = v0.getBitmap();
                if(v1_1.getWidth() <= v3.getWidth() && v1_1.getHeight() <= v3.getHeight()) {
                    goto label_22;
                }

                v2 = 1;
            }

        label_22:
            if(v2 == 0) {
                this.ignoreRemoval = arg7;
                this.memCache.remove(arg7);
                this.memCache.put(arg8, v0);
                this.ignoreRemoval = null;
                goto label_33;
            }

            this.memCache.remove(arg7);
        }

    label_33:
        Object v0_1 = this.bitmapUseCounts.get(arg7);
        if(v0_1 != null) {
            this.bitmapUseCounts.put(arg8, v0_1);
            this.bitmapUseCounts.remove(arg7);
        }
    }

    public void putImageToCache(BitmapDrawable arg2, String arg3) {
        this.memCache.put(arg3, arg2);
    }

    private void removeFromWaitingForThumb(int arg3) {
        Object v0 = this.waitingForQualityThumbByTag.get(arg3);
        if(v0 != null) {
            Object v1 = this.waitingForQualityThumb.get(v0);
            if(v1 != null) {
                ThumbGenerateInfo.access$2610(((ThumbGenerateInfo)v1));
                if(ThumbGenerateInfo.access$2600(((ThumbGenerateInfo)v1)) == 0) {
                    this.waitingForQualityThumb.remove(v0);
                }
            }

            this.waitingForQualityThumbByTag.remove(arg3);
        }
    }

    public void removeImage(String arg2) {
        this.bitmapUseCounts.remove(arg2);
        this.memCache.remove(arg2);
    }

    public void removeTestWebFile(String arg2) {
        if(arg2 == null) {
            return;
        }

        this.testWebFile.remove(arg2);
    }

    public void replaceImageInCache(String arg1, String arg2, FileLocation arg3, boolean arg4) {
        if(arg4) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$4avNq1uhTUFIFINWnkahRwsKjhI(this, arg1, arg2, arg3));
        }
        else {
            this.replaceImageInCacheInternal(arg1, arg2, arg3);
        }
    }

    private void replaceImageInCacheInternal(String arg12, String arg13, FileLocation arg14) {
        ArrayList v0 = this.memCache.getFilterKeys(arg12);
        int v1 = 2;
        int v2 = 3;
        if(v0 != null) {
            int v5;
            for(v5 = 0; v5 < v0.size(); ++v5) {
                Object v6 = v0.get(v5);
                String v7_1 = arg12 + "@" + (((String)v6));
                String v6_1 = arg13 + "@" + (((String)v6));
                this.performReplace(v7_1, v6_1);
                NotificationCenter v8_1 = NotificationCenter.getGlobalInstance();
                int v9 = NotificationCenter.didReplacedPhotoInMemCache;
                Object[] v10 = new Object[v2];
                v10[0] = v7_1;
                v10[1] = v6_1;
                v10[v1] = arg14;
                v8_1.postNotificationName(v9, v10);
            }
        }
        else {
            this.performReplace(arg12, arg13);
            NotificationCenter v0_1 = NotificationCenter.getGlobalInstance();
            v5 = NotificationCenter.didReplacedPhotoInMemCache;
            Object[] v2_1 = new Object[v2];
            v2_1[0] = arg12;
            v2_1[1] = arg13;
            v2_1[v1] = arg14;
            v0_1.postNotificationName(v5, v2_1);
        }
    }

    private void runHttpFileLoadTasks(HttpFileTask arg2, int arg3) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$ImageLoader$O2hTkS634-52KzVPc9xvrMsSbjI(this, arg2, arg3));
    }

    private void runHttpTasks(boolean arg6) {
        int v6;
        if(arg6) {
            v6 = this.currentHttpTasksCount - 1;
        }
        else {
        label_5:
            if(this.currentHttpTasksCount < 4 && !this.httpTasks.isEmpty()) {
                this.httpTasks.poll().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
                v6 = this.currentHttpTasksCount + 1;
                goto label_4;
            }

            return;
        }

    label_4:
        this.currentHttpTasksCount = v6;
        goto label_5;
    }

    public static void saveMessageThumbs(Message arg14) {
        Photo v14;
        int v2_2;
        StringBuilder v5;
        Object v4;
        int v3;
        int v0;
        int v1 = 0;
        PhotoSize v2 = null;
        if((arg14.media instanceof TL_messageMediaPhoto)) {
            v0 = arg14.media.photo.sizes.size();
            v3 = 0;
            while(v3 < v0) {
                v4 = arg14.media.photo.sizes.get(v3);
                if((v4 instanceof TL_photoCachedSize)) {
                    goto label_53;
                }
                else {
                    ++v3;
                    continue;
                }
            }
        }
        else if((arg14.media instanceof TL_messageMediaDocument)) {
            if((arg14.media.document.thumb instanceof TL_photoCachedSize)) {
                v2 = arg14.media.document.thumb;
            }
        }
        else if(((arg14.media instanceof TL_messageMediaWebPage)) && arg14.media.webpage.photo != null) {
            v0 = arg14.media.webpage.photo.sizes.size();
            v3 = 0;
            while(true) {
                if(v3 < v0) {
                    v4 = arg14.media.webpage.photo.sizes.get(v3);
                    if(!(v4 instanceof TL_photoCachedSize)) {
                        ++v3;
                        continue;
                    }
                    else {
                        break;
                    }
                }

                goto label_57;
            }

        label_53:
            Object v2_1 = v4;
        }

    label_57:
        if(v2 == null) {
            return;
        }

        if(v2.bytes == null) {
            return;
        }

        if(v2.bytes.length == 0) {
            return;
        }

        if((v2.location instanceof TL_fileLocationUnavailable)) {
            v2.location = new TL_fileLocation();
            v2.location.volume_id = -2147483648;
            v2.location.dc_id = -2147483648;
            v2.location.local_id = SharedConfig.getLastLocalId();
        }

        v0 = 1;
        File v3_1 = FileLoader.getPathToAttach(((TLObject)v2), true);
        if(MessageObject.shouldEncryptPhotoOrVideo(arg14)) {
            v5 = new StringBuilder();
            v5.append(v3_1.getAbsolutePath());
            v5.append(".enc");
            v3_1 = new File(v5.toString());
        }
        else {
            v0 = 0;
        }

        if(v3_1.exists()) {
            goto label_147;
        }

        if(v0 != 0) {
            try {
                File v4_1 = FileLoader.getInternalCacheDir();
                v5 = new StringBuilder();
                v5.append(v3_1.getName());
                v5.append(".key");
                RandomAccessFile v4_2 = new RandomAccessFile(new File(v4_1, v5.toString()), "rws");
                long v5_1 = v4_2.length();
                v0 = 32;
                byte[] v8 = new byte[v0];
                int v7 = 16;
                byte[] v9 = new byte[v7];
                long v10 = 0;
                if(v5_1 <= v10 || v5_1 % 48 != v10) {
                    Utilities.random.nextBytes(v8);
                    Utilities.random.nextBytes(v9);
                    v4_2.write(v8);
                    v4_2.write(v9);
                }
                else {
                    v4_2.read(v8, 0, v0);
                    v4_2.read(v9, 0, v7);
                }

                v4_2.close();
                Utilities.aesCtrDecryptionByteArray(v2.bytes, v8, v9, 0, v2.bytes.length, 0);
            label_139:
                RandomAccessFile v0_2 = new RandomAccessFile(v3_1, "rws");
                v0_2.write(v2.bytes);
                v0_2.close();
                goto label_147;
            label_138:
                goto label_146;
            }
            catch(Exception v0_1) {
                goto label_138;
            }
        }

        goto label_139;
    label_146:
        FileLog.e(((Throwable)v0_1));
    label_147:
        TL_photoSize v0_3 = new TL_photoSize();
        v0_3.w = v2.w;
        v0_3.h = v2.h;
        v0_3.location = v2.location;
        v0_3.size = v2.size;
        v0_3.type = v2.type;
        if((arg14.media instanceof TL_messageMediaPhoto)) {
            v2_2 = arg14.media.photo.sizes.size();
            while(true) {
                if(v1 >= v2_2) {
                    return;
                }
                else if((arg14.media.photo.sizes.get(v1) instanceof TL_photoCachedSize)) {
                    v14 = arg14.media.photo;
                }
                else {
                    ++v1;
                    continue;
                }

                break;
            }
        }
        else if((arg14.media instanceof TL_messageMediaDocument)) {
            arg14.media.document.thumb = ((PhotoSize)v0_3);
            return;
        }
        else if((arg14.media instanceof TL_messageMediaWebPage)) {
            v2_2 = arg14.media.webpage.photo.sizes.size();
            while(true) {
                if(v1 >= v2_2) {
                    return;
                }
                else if((arg14.media.webpage.photo.sizes.get(v1) instanceof TL_photoCachedSize)) {
                    v14 = arg14.media.webpage.photo;
                }
                else {
                    ++v1;
                    continue;
                }

                break;
            }
        }
        else {
            return;
        }

        v14.sizes.set(v1, v0_3);
    }

    public static void saveMessagesThumbs(ArrayList arg2) {
        if(arg2 != null) {
            if(arg2.isEmpty()) {
            }
            else {
                int v0;
                for(v0 = 0; v0 < arg2.size(); ++v0) {
                    ImageLoader.saveMessageThumbs(arg2.get(v0));
                }
            }
        }
    }

    public static PhotoSize scaleAndSaveImage(Bitmap arg7, float arg8, float arg9, int arg10, boolean arg11) {
        return ImageLoader.scaleAndSaveImage(arg7, arg8, arg9, arg10, arg11, 0, 0);
    }

    public static PhotoSize scaleAndSaveImage(Bitmap arg17, float arg18, float arg19, int arg20, boolean arg21, int arg22, int arg23) {
        boolean v14;
        float v1_1;
        int v0 = arg22;
        int v1 = arg23;
        PhotoSize v2 = null;
        if(arg17 == null) {
            return v2;
        }

        float v12 = ((float)arg17.getWidth());
        float v13 = ((float)arg17.getHeight());
        if(v12 != 0f && v13 != 0f) {
            float v4 = Math.max(v12 / arg18, v13 / arg19);
            if(v0 == 0 || v1 == 0) {
            label_41:
                v1_1 = v4;
                v14 = false;
            }
            else {
                float v0_1 = ((float)v0);
                if(v12 >= v0_1 && v13 >= (((float)v1))) {
                    goto label_41;
                }

                if(v12 >= v0_1 || v13 <= (((float)v1))) {
                    if(v12 > v0_1) {
                        float v3 = ((float)v1);
                        if(v13 < v3) {
                            v0_1 = v13 / v3;
                            goto label_37;
                        }
                    }

                    v0_1 = Math.max(v12 / v0_1, v13 / (((float)v1)));
                }
                else {
                    v0_1 = v12 / v0_1;
                }

            label_37:
                v1_1 = v0_1;
                v14 = true;
            }

            int v11 = ((int)(v12 / v1_1));
            int v10 = ((int)(v13 / v1_1));
            if(v10 == 0) {
                return v2;
            }

            if(v11 == 0) {
                return v2;
            }

            Bitmap v3_1 = arg17;
            int v4_1 = v11;
            int v5 = v10;
            float v6 = v12;
            float v7 = v13;
            float v8 = v1_1;
            int v9 = arg20;
            int v15 = v10;
            boolean v10_1 = arg21;
            int v16 = v11;
            boolean v11_1 = v14;
            try {
                return ImageLoader.scaleAndSaveImageInternal(v3_1, v4_1, v5, v6, v7, v8, v9, v10_1, v11_1);
            }
            catch(Throwable v0_2) {
                FileLog.e(v0_2);
                ImageLoader.getInstance().clearMemory();
                System.gc();
                v3_1 = arg17;
                v4_1 = v16;
                v5 = v15;
                v6 = v12;
                v7 = v13;
                v8 = v1_1;
                v9 = arg20;
                v10_1 = arg21;
                v11_1 = v14;
                try {
                    return ImageLoader.scaleAndSaveImageInternal(v3_1, v4_1, v5, v6, v7, v8, v9, v10_1, v11_1);
                }
                catch(Throwable v0_2) {
                    FileLog.e(v0_2);
                }
            }
        }

        return v2;
    }

    private static PhotoSize scaleAndSaveImageInternal(Bitmap arg2, int arg3, int arg4, float arg5, float arg6, float arg7, int arg8, boolean arg9, boolean arg10) {
        String v6;
        Bitmap v3 = arg7 > 1f || (arg10) ? Bitmaps.createScaledBitmap(arg2, arg3, arg4, true) : arg2;
        TL_fileLocation v4 = new TL_fileLocation();
        v4.volume_id = -2147483648;
        v4.dc_id = -2147483648;
        v4.local_id = SharedConfig.getLastLocalId();
        TL_photoSize v5 = new TL_photoSize();
        ((PhotoSize)v5).location = ((FileLocation)v4);
        ((PhotoSize)v5).w = v3.getWidth();
        ((PhotoSize)v5).h = v3.getHeight();
        int v7 = 100;
        if(((PhotoSize)v5).w > v7 || ((PhotoSize)v5).h > v7) {
            v7 = 320;
            if(((PhotoSize)v5).w <= v7 && ((PhotoSize)v5).h <= v7) {
                v6 = "m";
                goto label_29;
            }

            v7 = 800;
            if(((PhotoSize)v5).w <= v7 && ((PhotoSize)v5).h <= v7) {
                v6 = "x";
                goto label_29;
            }

            v7 = 1280;
            if(((PhotoSize)v5).w <= v7 && ((PhotoSize)v5).h <= v7) {
                v6 = "y";
                goto label_29;
            }

            v6 = "w";
        }
        else {
            v6 = "s";
        }

    label_29:
        ((PhotoSize)v5).type = v6;
        StringBuilder v6_1 = new StringBuilder();
        v6_1.append(v4.volume_id);
        v6_1.append("_");
        v6_1.append(v4.local_id);
        v6_1.append(".jpg");
        FileOutputStream v4_1 = new FileOutputStream(new File(FileLoader.getDirectory(4), v6_1.toString()));
        v3.compress(Bitmap$CompressFormat.JPEG, arg8, ((OutputStream)v4_1));
        if(arg9) {
            ByteArrayOutputStream v6_2 = new ByteArrayOutputStream();
            v3.compress(Bitmap$CompressFormat.JPEG, arg8, ((OutputStream)v6_2));
            ((PhotoSize)v5).bytes = v6_2.toByteArray();
            ((PhotoSize)v5).size = ((PhotoSize)v5).bytes.length;
            v6_2.close();
        }
        else {
            ((PhotoSize)v5).size = ((int)v4_1.getChannel().size());
        }

        v4_1.close();
        if(v3 != arg2) {
            v3.recycle();
        }

        return ((PhotoSize)v5);
    }
}

