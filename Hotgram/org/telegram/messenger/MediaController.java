package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog$Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager$OnAudioFocusChangeListener;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaCodec;
import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager$WakeLock;
import android.provider.MediaStore$Images$Media;
import android.provider.MediaStore$Video$Media;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.video.InputSurface;
import org.telegram.messenger.video.MP4Builder;
import org.telegram.messenger.video.Mp4Movie;
import org.telegram.messenger.video.OutputSurface;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Photo;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAnimated;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAudio;
import org.telegram.tgnet.TLRPC$TL_encryptedChat;
import org.telegram.tgnet.TLRPC$TL_messages_messages;
import org.telegram.tgnet.TLRPC$TL_photoSizeEmpty;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.tgnet.TLRPC$messages_Messages;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.EmbedBottomSheet;
import org.telegram.ui.Components.PhotoFilterView$CurvesToolValue;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.Point;
import org.telegram.ui.Components.VideoPlayer$VideoPlayerDelegate;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.PhotoViewer;

public class MediaController implements SensorEventListener, AudioManager$OnAudioFocusChangeListener, NotificationCenterDelegate {
    class org.telegram.messenger.MediaController$1 implements Runnable {
        org.telegram.messenger.MediaController$1(MediaController arg1) {
            MediaController.this = arg1;
            super();
        }

        public void run() {
            short v9_3;
            double v16;
            float v10;
            int v9_2;
            int v0_3;
            long v9;
            long v7;
            ByteBuffer v0_1;
            org.telegram.messenger.MediaController$1 v1 = this;
            if(v1.this$0.audioRecorder == null) {
                return;
            }

            boolean v2 = false;
            if(!v1.this$0.recordBuffers.isEmpty()) {
                Object v0 = v1.this$0.recordBuffers.get(0);
                v1.this$0.recordBuffers.remove(0);
            }
            else {
                v0_1 = ByteBuffer.allocateDirect(v1.this$0.recordBufferSize);
                v0_1.order(ByteOrder.nativeOrder());
            }

            Object v3 = v0_1;
            ((ByteBuffer)v3).rewind();
            int v4 = v1.this$0.audioRecorder.read(((ByteBuffer)v3), ((ByteBuffer)v3).capacity());
            if(v4 <= 0) {
                goto label_137;
            }

            ((ByteBuffer)v3).limit(v4);
            try {
                v7 = v1.this$0.samplesCount + (((long)(v4 / 2)));
                v9 = v1.this$0.samplesCount;
            }
            catch(Exception v0_2) {
                goto label_110;
            }

            double v9_1 = ((double)v9);
            double v11 = ((double)v7);
            Double.isNaN(v9_1);
            Double.isNaN(v11);
            v9_1 /= v11;
            try {
                v0_3 = v1.this$0.recordSamples.length;
            }
            catch(Exception v0_2) {
                goto label_110;
            }

            v11 = ((double)v0_3);
            Double.isNaN(v11);
            v0_3 = ((int)(v9_1 * v11));
            try {
                v9_2 = v1.this$0.recordSamples.length - v0_3;
                v10 = 0f;
                if(v0_3 != 0) {
                    float v11_1 = (((float)v1.this$0.recordSamples.length)) / (((float)v0_3));
                    int v12 = 0;
                    float v13 = 0f;
                    while(v12 < v0_3) {
                        v1.this$0.recordSamples[v12] = v1.this$0.recordSamples[((int)v13)];
                        v13 += v11_1;
                        ++v12;
                    }
                }
            }
            catch(Exception v0_2) {
            label_110:
                v16 = 0;
                goto label_111;
            }

            float v5 = (((float)v4)) / 2f / (((float)v9_2));
            int v6 = v0_3;
            v0_3 = 0;
            v16 = 0;
            try {
                while(true) {
                label_82:
                    if(v0_3 >= v4 / 2) {
                        goto label_104;
                    }

                    v9_3 = ((ByteBuffer)v3).getShort();
                    if(v9_3 > 2500) {
                        break;
                    }

                    goto label_91;
                }
            }
            catch(Exception v0_2) {
                goto label_108;
            }

            v11 = ((double)(v9_3 * v9_3));
            Double.isNaN(v11);
            v16 += v11;
        label_91:
            if(v0_3 == (((int)v10))) {
                try {
                    if(v6 < v1.this$0.recordSamples.length) {
                        v1.this$0.recordSamples[v6] = v9_3;
                        v10 += v5;
                        ++v6;
                    }

                label_102:
                    ++v0_3;
                    goto label_82;
                label_104:
                    v1.this$0.samplesCount = v7;
                    goto label_112;
                }
                catch(Exception v0_2) {
                label_108:
                    goto label_111;
                }
            }

            goto label_102;
        label_111:
            FileLog.e(((Throwable)v0_2));
        label_112:
            ((ByteBuffer)v3).position(0);
            double v5_1 = ((double)v4);
            Double.isNaN(v5_1);
            v5_1 = Math.sqrt(v16 / v5_1 / 2);
            if(v4 != ((ByteBuffer)v3).capacity()) {
                v2 = true;
            }

            if(v4 != 0) {
                v1.this$0.fileEncodingQueue.postRunnable(new Runnable(((ByteBuffer)v3), v2) {
                    public void run() {
                        int v0;
                        while(this.val$finalBuffer.hasRemaining()) {
                            int v2 = -1;
                            if(this.val$finalBuffer.remaining() > this.this$1.this$0.fileBuffer.remaining()) {
                                v0 = this.val$finalBuffer.limit();
                                this.val$finalBuffer.limit(this.this$1.this$0.fileBuffer.remaining() + this.val$finalBuffer.position());
                            }
                            else {
                                v0 = -1;
                            }

                            this.this$1.this$0.fileBuffer.put(this.val$finalBuffer);
                            if(this.this$1.this$0.fileBuffer.position() == this.this$1.this$0.fileBuffer.limit() || (this.val$flush)) {
                                MediaController v1 = this.this$1.this$0;
                                ByteBuffer v3 = this.this$1.this$0.fileBuffer;
                                int v4 = !this.val$flush ? this.this$1.this$0.fileBuffer.limit() : this.val$finalBuffer.position();
                                if(v1.writeFrame(v3, v4) == 0) {
                                    goto label_74;
                                }

                                this.this$1.this$0.fileBuffer.rewind();
                                this.this$1.this$0.recordTimeCount += ((long)(this.this$1.this$0.fileBuffer.limit() / 2 / 16));
                            }

                        label_74:
                            if(v0 == v2) {
                                continue;
                            }

                            this.val$finalBuffer.limit(v0);
                        }

                        this.this$1.this$0.recordQueue.postRunnable(new Runnable() {
                            public void run() {
                                this.this$2.this$1.this$0.recordBuffers.add(this.this$2.val$finalBuffer);
                            }
                        });
                    }
                });
            }

            v1.this$0.recordQueue.postRunnable(v1.this$0.recordRunnable);
            AndroidUtilities.runOnUIThread(new Runnable(v5_1) {
                public void run() {
                    NotificationCenter.getInstance(this.this$1.this$0.recordingCurrentAccount).postNotificationName(NotificationCenter.recordProgressChanged, new Object[]{Long.valueOf(System.currentTimeMillis() - this.this$1.this$0.recordStartTime), Double.valueOf(this.val$amplitude)});
                }
            });
            return;
        label_137:
            v1.this$0.recordBuffers.add(v3);
            v1.this$0.stopRecordingInternal(v1.this$0.sendAfterDone);
        }
    }

    public class AlbumEntry {
        public int bucketId;
        public String bucketName;
        public PhotoEntry coverPhoto;
        public ArrayList photos;
        public SparseArray photosByIds;

        public AlbumEntry(int arg2, String arg3, PhotoEntry arg4) {
            super();
            this.photos = new ArrayList();
            this.photosByIds = new SparseArray();
            this.bucketId = arg2;
            this.bucketName = arg3;
            this.coverPhoto = arg4;
        }

        public void addPhoto(PhotoEntry arg3) {
            this.photos.add(arg3);
            this.photosByIds.put(arg3.imageId, arg3);
        }
    }

    class AudioBuffer {
        ByteBuffer buffer;
        byte[] bufferBytes;
        int finished;
        long pcmOffset;
        int size;

        public AudioBuffer(MediaController arg1, int arg2) {
            MediaController.this = arg1;
            super();
            this.buffer = ByteBuffer.allocateDirect(arg2);
            this.bufferBytes = new byte[arg2];
        }
    }

    public class AudioEntry {
        public String author;
        public int duration;
        public String genre;
        public long id;
        public MessageObject messageObject;
        public String path;
        public String title;

        public AudioEntry() {
            super();
        }
    }

    class ExternalObserver extends ContentObserver {
        public ExternalObserver(MediaController arg1) {
            MediaController.this = arg1;
            super(null);
        }

        public void onChange(boolean arg2) {
            super.onChange(arg2);
            MediaController.this.processMediaObserver(MediaStore$Images$Media.EXTERNAL_CONTENT_URI);
        }
    }

    class GalleryObserverExternal extends ContentObserver {
        public GalleryObserverExternal(MediaController arg1) {
            MediaController.this = arg1;
            super(null);
        }

        public void onChange(boolean arg3) {
            super.onChange(arg3);
            if(MediaController.refreshGalleryRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(MediaController.refreshGalleryRunnable);
            }

            AndroidUtilities.runOnUIThread(MediaController.access$1702(new Runnable() {
                public void run() {
                    MediaController.refreshGalleryRunnable = null;
                    MediaController.loadGalleryPhotosAlbums(0);
                }
            }), 2000);
        }
    }

    class GalleryObserverInternal extends ContentObserver {
        public GalleryObserverInternal(MediaController arg1) {
            MediaController.this = arg1;
            super(null);
        }

        static void access$1800(GalleryObserverInternal arg0) {
            arg0.scheduleReloadRunnable();
        }

        public void onChange(boolean arg1) {
            super.onChange(arg1);
            if(MediaController.refreshGalleryRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(MediaController.refreshGalleryRunnable);
            }

            this.scheduleReloadRunnable();
        }

        private void scheduleReloadRunnable() {
            AndroidUtilities.runOnUIThread(MediaController.access$1702(new Runnable() {
                public void run() {
                    if(PhotoViewer.getInstance().isVisible()) {
                        this.this$1.scheduleReloadRunnable();
                        return;
                    }

                    MediaController.refreshGalleryRunnable = null;
                    MediaController.loadGalleryPhotosAlbums(0);
                }
            }), 2000);
        }
    }

    class InternalObserver extends ContentObserver {
        public InternalObserver(MediaController arg1) {
            MediaController.this = arg1;
            super(null);
        }

        public void onChange(boolean arg2) {
            super.onChange(arg2);
            MediaController.this.processMediaObserver(MediaStore$Images$Media.INTERNAL_CONTENT_URI);
        }
    }

    public class PhotoEntry {
        public int bucketId;
        public CharSequence caption;
        public long dateTaken;
        public int duration;
        public VideoEditedInfo editedInfo;
        public ArrayList entities;
        public int imageId;
        public String imagePath;
        public boolean isCropped;
        public boolean isFiltered;
        public boolean isMuted;
        public boolean isPainted;
        public boolean isVideo;
        public int orientation;
        public String path;
        public SavedFilterState savedFilterState;
        public ArrayList stickers;
        public String thumbPath;
        public int ttl;

        public PhotoEntry(int arg2, int arg3, long arg4, String arg6, int arg7, boolean arg8) {
            super();
            this.stickers = new ArrayList();
            this.bucketId = arg2;
            this.imageId = arg3;
            this.dateTaken = arg4;
            this.path = arg6;
            if(arg8) {
                this.duration = arg7;
            }
            else {
                this.orientation = arg7;
            }

            this.isVideo = arg8;
        }

        public void reset() {
            this.isFiltered = false;
            this.isPainted = false;
            this.isCropped = false;
            this.ttl = 0;
            String v0 = null;
            this.imagePath = v0;
            if(!this.isVideo) {
                this.thumbPath = v0;
            }

            this.editedInfo = ((VideoEditedInfo)v0);
            this.caption = ((CharSequence)v0);
            this.entities = ((ArrayList)v0);
            this.savedFilterState = ((SavedFilterState)v0);
            this.stickers.clear();
        }
    }

    public class SavedFilterState {
        public float blurAngle;
        public float blurExcludeBlurSize;
        public Point blurExcludePoint;
        public float blurExcludeSize;
        public int blurType;
        public float contrastValue;
        public CurvesToolValue curvesToolValue;
        public float enhanceValue;
        public float exposureValue;
        public float fadeValue;
        public float grainValue;
        public float highlightsValue;
        public float saturationValue;
        public float shadowsValue;
        public float sharpenValue;
        public int tintHighlightsColor;
        public int tintShadowsColor;
        public float vignetteValue;
        public float warmthValue;

        public SavedFilterState() {
            super();
            this.curvesToolValue = new CurvesToolValue();
        }
    }

    public class SearchImage {
        public CharSequence caption;
        public int date;
        public Document document;
        public ArrayList entities;
        public int height;
        public String id;
        public String imagePath;
        public String imageUrl;
        public boolean isCropped;
        public boolean isFiltered;
        public boolean isPainted;
        public String localUrl;
        public Photo photo;
        public PhotoSize photoSize;
        public SavedFilterState savedFilterState;
        public int size;
        public ArrayList stickers;
        public String thumbPath;
        public PhotoSize thumbPhotoSize;
        public String thumbUrl;
        public int ttl;
        public int type;
        public int width;

        public SearchImage() {
            super();
            this.stickers = new ArrayList();
        }

        public String getAttachName() {
            if(this.photoSize != null) {
                return FileLoader.getAttachFileName(this.photoSize);
            }

            if(this.document != null) {
                return FileLoader.getAttachFileName(this.document);
            }

            if(this.type != 1 && this.localUrl != null && this.localUrl.length() > 0) {
                File v0 = new File(this.localUrl);
                if(v0.exists()) {
                    return v0.getName();
                }
                else {
                    this.localUrl = "";
                }
            }

            return Utilities.MD5(this.imageUrl) + "." + ImageLoader.getHttpUrlExtension(this.imageUrl, "jpg");
        }

        public String getPathToAttach() {
            PhotoSize v0;
            if(this.photoSize != null) {
                v0 = this.photoSize;
            }
            else if(this.document != null) {
                Document v0_1 = this.document;
            }
            else {
                goto label_11;
            }

            return FileLoader.getPathToAttach(((TLObject)v0), true).getAbsolutePath();
        label_11:
            return this.imageUrl;
        }

        public void reset() {
            this.isFiltered = false;
            this.isPainted = false;
            this.isCropped = false;
            this.ttl = 0;
            this.imagePath = null;
            this.thumbPath = null;
            this.caption = null;
            this.entities = null;
            this.savedFilterState = null;
            this.stickers.clear();
        }
    }

    class SmsObserver extends ContentObserver {
        public SmsObserver(MediaController arg1) {
            MediaController.this = arg1;
            super(null);
        }

        public void onChange(boolean arg1) {
            MediaController.this.readSms();
        }
    }

    final class StopMediaObserverRunnable implements Runnable {
        public int currentObserverToken;

        StopMediaObserverRunnable(MediaController arg1, org.telegram.messenger.MediaController$1 arg2) {
            this(arg1);
        }

        private StopMediaObserverRunnable(MediaController arg1) {
            MediaController.this = arg1;
            super();
            this.currentObserverToken = 0;
        }

        public void run() {
            if(this.currentObserverToken == MediaController.this.startObserverToken) {
                InternalObserver v0 = null;
                try {
                    if(MediaController.this.internalObserver == null) {
                        goto label_18;
                    }

                    ApplicationLoader.applicationContext.getContentResolver().unregisterContentObserver(MediaController.this.internalObserver);
                    MediaController.this.internalObserver = v0;
                }
                catch(Exception v1) {
                    FileLog.e(((Throwable)v1));
                }

                try {
                label_18:
                    if(MediaController.this.externalObserver == null) {
                        return;
                    }

                    ApplicationLoader.applicationContext.getContentResolver().unregisterContentObserver(MediaController.this.externalObserver);
                    MediaController.this.externalObserver = ((ExternalObserver)v0);
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }
        }
    }

    class VideoConvertRunnable implements Runnable {
        private MessageObject messageObject;

        private VideoConvertRunnable(MessageObject arg1) {
            super();
            this.messageObject = arg1;
        }

        VideoConvertRunnable(MessageObject arg1, org.telegram.messenger.MediaController$1 arg2) {
            this(arg1);
        }

        public void run() {
            MediaController.getInstance().convertVideo(this.messageObject);
        }

        public static void runConversion(MessageObject arg2) {
            new Thread(new Runnable(arg2) {
                public void run() {
                    try {
                        Thread v1 = new Thread(new VideoConvertRunnable(this.val$obj, null), "VideoConvertRunnable");
                        v1.start();
                        v1.join();
                    }
                    catch(Exception v0) {
                        FileLog.e(((Throwable)v0));
                    }
                }
            }).start();
        }
    }

    private static final int AUDIO_FOCUSED = 2;
    private static final int AUDIO_NO_FOCUS_CAN_DUCK = 1;
    private static final int AUDIO_NO_FOCUS_NO_DUCK = 0;
    private static volatile MediaController Instance = null;
    public static final String MIME_TYPE = "video/avc";
    private static final int PROCESSOR_TYPE_INTEL = 2;
    private static final int PROCESSOR_TYPE_MTK = 3;
    private static final int PROCESSOR_TYPE_OTHER = 0;
    private static final int PROCESSOR_TYPE_QCOM = 1;
    private static final int PROCESSOR_TYPE_SEC = 4;
    private static final int PROCESSOR_TYPE_TI = 5;
    private static final float VOLUME_DUCK = 0.2f;
    private static final float VOLUME_NORMAL = 0f;
    private Sensor accelerometerSensor;
    private boolean accelerometerVertical;
    public static AlbumEntry allMediaAlbumEntry;
    public static AlbumEntry allPhotosAlbumEntry;
    private boolean allowStartRecord;
    private int audioFocus;
    private AudioInfo audioInfo;
    private VideoPlayer audioPlayer;
    private AudioRecord audioRecorder;
    private Activity baseActivity;
    private static Runnable broadcastPhotosRunnable;
    private boolean callInProgress;
    private boolean cancelCurrentVideoConversion;
    private int countLess;
    private AspectRatioFrameLayout currentAspectRatioFrameLayout;
    private float currentAspectRatioFrameLayoutRatio;
    private boolean currentAspectRatioFrameLayoutReady;
    private int currentAspectRatioFrameLayoutRotation;
    private float currentPlaybackSpeed;
    private int currentPlaylistNum;
    private TextureView currentTextureView;
    private FrameLayout currentTextureViewContainer;
    private boolean downloadingCurrentMessage;
    private ExternalObserver externalObserver;
    private View feedbackView;
    private ByteBuffer fileBuffer;
    private DispatchQueue fileEncodingQueue;
    private BaseFragment flagSecureFragment;
    private boolean forceLoopCurrentPlaylist;
    private HashMap generatingWaveform;
    private float[] gravity;
    private float[] gravityFast;
    private Sensor gravitySensor;
    private int hasAudioFocus;
    private boolean ignoreOnPause;
    private boolean ignoreProximity;
    private boolean inputFieldHasText;
    private InternalObserver internalObserver;
    private boolean isDrawingWasReady;
    private boolean isPaused;
    private int lastChatAccount;
    private long lastChatEnterTime;
    private long lastChatLeaveTime;
    private ArrayList lastChatVisibleMessages;
    private long lastMediaCheckTime;
    private int lastMessageId;
    private long lastProgress;
    private float lastProximityValue;
    private EncryptedChat lastSecretChat;
    private long lastTimestamp;
    private User lastUser;
    private float[] linearAcceleration;
    private Sensor linearSensor;
    private String[] mediaProjections;
    private PipRoundVideoView pipRoundVideoView;
    private int pipSwitchingState;
    private boolean playMusicAgain;
    private MessageObject playingMessageObject;
    private ArrayList playlist;
    private float previousAccValue;
    private Timer progressTimer;
    private final Object progressTimerSync;
    private static final String[] projectionPhotos;
    private static final String[] projectionVideo;
    private boolean proximityHasDifferentValues;
    private Sensor proximitySensor;
    private boolean proximityTouched;
    private PowerManager$WakeLock proximityWakeLock;
    private ChatActivity raiseChat;
    private boolean raiseToEarRecord;
    private int raisedToBack;
    private int raisedToTop;
    private int raisedToTopSign;
    private int recordBufferSize;
    private ArrayList recordBuffers;
    private long recordDialogId;
    private DispatchQueue recordQueue;
    private MessageObject recordReplyingMessageObject;
    private Runnable recordRunnable;
    private short[] recordSamples;
    private Runnable recordStartRunnable;
    private long recordStartTime;
    private long recordTimeCount;
    private TL_document recordingAudio;
    private File recordingAudioFile;
    private int recordingCurrentAccount;
    private static Runnable refreshGalleryRunnable;
    private boolean resumeAudioOnFocusGain;
    private long samplesCount;
    private float seekToProgressPending;
    private int sendAfterDone;
    private SensorManager sensorManager;
    private boolean sensorsStarted;
    private ArrayList shuffledPlaylist;
    private SmsObserver smsObserver;
    private int startObserverToken;
    private StopMediaObserverRunnable stopMediaObserverRunnable;
    private final Object sync;
    private long timeSinceRaise;
    private boolean useFrontSpeaker;
    private boolean videoConvertFirstWrite;
    private ArrayList videoConvertQueue;
    private final Object videoConvertSync;
    private VideoPlayer videoPlayer;
    private final Object videoQueueSync;
    private ArrayList voiceMessagesPlaylist;
    private SparseArray voiceMessagesPlaylistMap;
    private boolean voiceMessagesPlaylistUnread;

    static {
        MediaController.projectionPhotos = new String[]{"_id", "bucket_id", "bucket_display_name", "_data", "datetaken", "orientation"};
        MediaController.projectionVideo = new String[]{"_id", "bucket_id", "bucket_display_name", "_data", "datetaken", "duration"};
    }

    public MediaController() {
        super();
        this.videoConvertSync = new Object();
        this.lastTimestamp = 0;
        this.lastProximityValue = -100f;
        this.gravity = new float[3];
        this.gravityFast = new float[3];
        this.linearAcceleration = new float[3];
        this.audioFocus = 0;
        this.videoConvertQueue = new ArrayList();
        this.videoQueueSync = new Object();
        this.cancelCurrentVideoConversion = false;
        this.videoConvertFirstWrite = true;
        this.generatingWaveform = new HashMap();
        this.isPaused = false;
        this.audioPlayer = null;
        this.currentPlaybackSpeed = 1f;
        this.lastProgress = 0;
        this.progressTimer = null;
        this.progressTimerSync = new Object();
        this.playlist = new ArrayList();
        this.shuffledPlaylist = new ArrayList();
        this.recordSamples = new short[1024];
        this.sync = new Object();
        this.recordBuffers = new ArrayList();
        this.recordBufferSize = 1280;
        this.recordRunnable = new org.telegram.messenger.MediaController$1(this);
        this.recordQueue = new DispatchQueue("recordQueue");
        this.recordQueue.setPriority(10);
        this.fileEncodingQueue = new DispatchQueue("fileEncodingQueue");
        this.fileEncodingQueue.setPriority(10);
        this.recordQueue.postRunnable(new Runnable() {
            public void run() {
                try {
                    MediaController.access$202(MediaController.this, AudioRecord.getMinBufferSize(16000, 16, 2));
                    if(MediaController.access$200(MediaController.this) <= 0) {
                        MediaController.access$202(MediaController.this, 1280);
                    }

                    int v0_1;
                    for(v0_1 = 0; v0_1 < 5; ++v0_1) {
                        ByteBuffer v1 = ByteBuffer.allocateDirect(4096);
                        v1.order(ByteOrder.nativeOrder());
                        MediaController.access$100(MediaController.this).add(v1);
                    }
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                }
            }
        });
        Utilities.globalQueue.postRunnable(new Runnable() {
            public void run() {
                int v0 = 32;
                try {
                    MediaController.access$2202(MediaController.this, MessagesController.getGlobalMainSettings().getFloat("playbackSpeed", 1f));
                    MediaController.access$2302(MediaController.this, ApplicationLoader.applicationContext.getSystemService("sensor"));
                    MediaController.access$2402(MediaController.this, MediaController.access$2300(MediaController.this).getDefaultSensor(10));
                    MediaController.access$2502(MediaController.this, MediaController.access$2300(MediaController.this).getDefaultSensor(9));
                    if(MediaController.access$2400(MediaController.this) == null || MediaController.access$2500(MediaController.this) == null) {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.d("gravity or linear sensor not found");
                        }

                        MediaController.access$2602(MediaController.this, MediaController.access$2300(MediaController.this).getDefaultSensor(1));
                        MediaController.access$2402(MediaController.this, null);
                        MediaController.access$2502(MediaController.this, null);
                    }

                    MediaController.access$2702(MediaController.this, MediaController.access$2300(MediaController.this).getDefaultSensor(8));
                    MediaController.access$2802(MediaController.this, ApplicationLoader.applicationContext.getSystemService("power").newWakeLock(v0, "proximity"));
                }
                catch(Exception v1) {
                    FileLog.e(((Throwable)v1));
                }

                try {
                    org.telegram.messenger.MediaController$4$1 v1_1 = new PhoneStateListener() {
                        public void onCallStateChanged(int arg1, String arg2) {
                            AndroidUtilities.runOnUIThread(new Runnable(arg1) {
                                public void run() {
                                    EmbedBottomSheet v0;
                                    int v1 = 2;
                                    if(this.val$state == 1) {
                                        if(!this.this$2.this$1.this$0.isPlayingMessage(MediaController.access$2900(this.this$2.this$1.this$0)) || (this.this$2.this$1.this$0.isMessagePaused())) {
                                            if(MediaController.access$3000(this.this$2.this$1.this$0) == null && MediaController.access$3100(this.this$2.this$1.this$0) == null) {
                                                goto label_41;
                                            }

                                            this.this$2.this$1.this$0.stopRecording(v1);
                                        }
                                        else {
                                            this.this$2.this$1.this$0.pauseMessage(MediaController.access$2900(this.this$2.this$1.this$0));
                                        }

                                    label_41:
                                        v0 = EmbedBottomSheet.getInstance();
                                        if(v0 == null) {
                                            goto label_57;
                                        }

                                        goto label_56;
                                    }
                                    else {
                                        if(this.val$state == 0) {
                                            MediaController.access$3202(this.this$2.this$1.this$0, false);
                                            return;
                                        }

                                        if(this.val$state != v1) {
                                            return;
                                        }

                                        v0 = EmbedBottomSheet.getInstance();
                                        if(v0 != null) {
                                        label_56:
                                            v0.pause();
                                        }

                                    label_57:
                                        MediaController.access$3202(this.this$2.this$1.this$0, true);
                                    }
                                }
                            });
                        }
                    };
                    Object v2 = ApplicationLoader.applicationContext.getSystemService("phone");
                    if(v2 == null) {
                        return;
                    }

                    ((TelephonyManager)v2).listen(((PhoneStateListener)v1_1), v0);
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }
        });
        this.fileBuffer = ByteBuffer.allocateDirect(1920);
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                int v0;
                for(v0 = 0; v0 < 3; ++v0) {
                    NotificationCenter.getInstance(v0).addObserver(MediaController.this, NotificationCenter.FileDidLoaded);
                    NotificationCenter.getInstance(v0).addObserver(MediaController.this, NotificationCenter.httpFileDidLoaded);
                    NotificationCenter.getInstance(v0).addObserver(MediaController.this, NotificationCenter.didReceivedNewMessages);
                    NotificationCenter.getInstance(v0).addObserver(MediaController.this, NotificationCenter.messagesDeleted);
                    NotificationCenter.getInstance(v0).addObserver(MediaController.this, NotificationCenter.removeAllMessagesFromDialog);
                    NotificationCenter.getInstance(v0).addObserver(MediaController.this, NotificationCenter.musicDidLoaded);
                    NotificationCenter.getGlobalInstance().addObserver(MediaController.this, NotificationCenter.playerDidStartPlaying);
                }
            }
        });
        this.mediaProjections = new String[]{"_data", "_display_name", "bucket_display_name", "datetaken", "title", "width", "height"};
        ContentResolver v0 = ApplicationLoader.applicationContext.getContentResolver();
        try {
            v0.registerContentObserver(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, true, new GalleryObserverExternal(this));
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

        try {
            v0.registerContentObserver(MediaStore$Images$Media.INTERNAL_CONTENT_URI, true, new GalleryObserverInternal(this));
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

        try {
            v0.registerContentObserver(MediaStore$Video$Media.EXTERNAL_CONTENT_URI, true, new GalleryObserverExternal(this));
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

        try {
            v0.registerContentObserver(MediaStore$Video$Media.INTERNAL_CONTENT_URI, true, new GalleryObserverInternal(this));
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    static AudioRecord access$000(MediaController arg0) {
        return arg0.audioRecorder;
    }

    static AudioRecord access$002(MediaController arg0, AudioRecord arg1) {
        arg0.audioRecorder = arg1;
        return arg1;
    }

    static ArrayList access$100(MediaController arg0) {
        return arg0.recordBuffers;
    }

    static Runnable access$1000(MediaController arg0) {
        return arg0.recordRunnable;
    }

    static long access$1100(MediaController arg2) {
        return arg2.recordStartTime;
    }

    static long access$1102(MediaController arg0, long arg1) {
        arg0.recordStartTime = arg1;
        return arg1;
    }

    static int access$1200(MediaController arg0) {
        return arg0.recordingCurrentAccount;
    }

    static int access$1202(MediaController arg0, int arg1) {
        arg0.recordingCurrentAccount = arg1;
        return arg1;
    }

    static int access$1300(MediaController arg0) {
        return arg0.sendAfterDone;
    }

    static int access$1302(MediaController arg0, int arg1) {
        arg0.sendAfterDone = arg1;
        return arg1;
    }

    static void access$1400(MediaController arg0, int arg1) {
        arg0.stopRecordingInternal(arg1);
    }

    static void access$1500(MediaController arg0) {
        arg0.readSms();
    }

    static void access$1600(MediaController arg0, Uri arg1) {
        arg0.processMediaObserver(arg1);
    }

    static Runnable access$1700() {
        return MediaController.refreshGalleryRunnable;
    }

    static Runnable access$1702(Runnable arg0) {
        MediaController.refreshGalleryRunnable = arg0;
        return arg0;
    }

    static int access$1900(MediaController arg0) {
        return arg0.startObserverToken;
    }

    static int access$200(MediaController arg0) {
        return arg0.recordBufferSize;
    }

    static InternalObserver access$2000(MediaController arg0) {
        return arg0.internalObserver;
    }

    static InternalObserver access$2002(MediaController arg0, InternalObserver arg1) {
        arg0.internalObserver = arg1;
        return arg1;
    }

    static int access$202(MediaController arg0, int arg1) {
        arg0.recordBufferSize = arg1;
        return arg1;
    }

    static ExternalObserver access$2100(MediaController arg0) {
        return arg0.externalObserver;
    }

    static ExternalObserver access$2102(MediaController arg0, ExternalObserver arg1) {
        arg0.externalObserver = arg1;
        return arg1;
    }

    static float access$2202(MediaController arg0, float arg1) {
        arg0.currentPlaybackSpeed = arg1;
        return arg1;
    }

    static SensorManager access$2300(MediaController arg0) {
        return arg0.sensorManager;
    }

    static SensorManager access$2302(MediaController arg0, SensorManager arg1) {
        arg0.sensorManager = arg1;
        return arg1;
    }

    static Sensor access$2400(MediaController arg0) {
        return arg0.linearSensor;
    }

    static Sensor access$2402(MediaController arg0, Sensor arg1) {
        arg0.linearSensor = arg1;
        return arg1;
    }

    static Sensor access$2500(MediaController arg0) {
        return arg0.gravitySensor;
    }

    static Sensor access$2502(MediaController arg0, Sensor arg1) {
        arg0.gravitySensor = arg1;
        return arg1;
    }

    static Sensor access$2600(MediaController arg0) {
        return arg0.accelerometerSensor;
    }

    static Sensor access$2602(MediaController arg0, Sensor arg1) {
        arg0.accelerometerSensor = arg1;
        return arg1;
    }

    static Sensor access$2700(MediaController arg0) {
        return arg0.proximitySensor;
    }

    static Sensor access$2702(MediaController arg0, Sensor arg1) {
        arg0.proximitySensor = arg1;
        return arg1;
    }

    static PowerManager$WakeLock access$2802(MediaController arg0, PowerManager$WakeLock arg1) {
        arg0.proximityWakeLock = arg1;
        return arg1;
    }

    static MessageObject access$2900(MediaController arg0) {
        return arg0.playingMessageObject;
    }

    static long access$300(MediaController arg2) {
        return arg2.samplesCount;
    }

    static Runnable access$3000(MediaController arg0) {
        return arg0.recordStartRunnable;
    }

    static Runnable access$3002(MediaController arg0, Runnable arg1) {
        arg0.recordStartRunnable = arg1;
        return arg1;
    }

    static long access$302(MediaController arg0, long arg1) {
        arg0.samplesCount = arg1;
        return arg1;
    }

    static TL_document access$3100(MediaController arg0) {
        return arg0.recordingAudio;
    }

    static TL_document access$3102(MediaController arg0, TL_document arg1) {
        arg0.recordingAudio = arg1;
        return arg1;
    }

    static boolean access$3202(MediaController arg0, boolean arg1) {
        arg0.callInProgress = arg1;
        return arg1;
    }

    static Object access$3300(MediaController arg0) {
        return arg0.sync;
    }

    static VideoPlayer access$3400(MediaController arg0) {
        return arg0.audioPlayer;
    }

    static VideoPlayer access$3500(MediaController arg0) {
        return arg0.videoPlayer;
    }

    static boolean access$3600(MediaController arg0) {
        return arg0.isPaused;
    }

    static float access$3700(MediaController arg0) {
        return arg0.seekToProgressPending;
    }

    static float access$3702(MediaController arg0, float arg1) {
        arg0.seekToProgressPending = arg1;
        return arg1;
    }

    static long access$3800(MediaController arg2) {
        return arg2.lastProgress;
    }

    static long access$3802(MediaController arg0, long arg1) {
        arg0.lastProgress = arg1;
        return arg1;
    }

    static SmsObserver access$3900(MediaController arg0) {
        return arg0.smsObserver;
    }

    static SmsObserver access$3902(MediaController arg0, SmsObserver arg1) {
        arg0.smsObserver = arg1;
        return arg1;
    }

    static short[] access$400(MediaController arg0) {
        return arg0.recordSamples;
    }

    static int access$4100(MediaController arg0) {
        return arg0.lastChatAccount;
    }

    static void access$4200(MediaController arg0, ArrayList arg1) {
        arg0.checkScreenshots(arg1);
    }

    static Activity access$4300(MediaController arg0) {
        return arg0.baseActivity;
    }

    static boolean access$4402(MediaController arg0, boolean arg1) {
        arg0.currentAspectRatioFrameLayoutReady = arg1;
        return arg1;
    }

    static FrameLayout access$4500(MediaController arg0) {
        return arg0.currentTextureViewContainer;
    }

    static int access$4600(MediaController arg0) {
        return arg0.currentAspectRatioFrameLayoutRotation;
    }

    static int access$4602(MediaController arg0, int arg1) {
        arg0.currentAspectRatioFrameLayoutRotation = arg1;
        return arg1;
    }

    static float access$4700(MediaController arg0) {
        return arg0.currentAspectRatioFrameLayoutRatio;
    }

    static float access$4702(MediaController arg0, float arg1) {
        arg0.currentAspectRatioFrameLayoutRatio = arg1;
        return arg1;
    }

    static AspectRatioFrameLayout access$4800(MediaController arg0) {
        return arg0.currentAspectRatioFrameLayout;
    }

    static boolean access$4900(MediaController arg0) {
        return arg0.isDrawingWasReady;
    }

    static boolean access$4902(MediaController arg0, boolean arg1) {
        arg0.isDrawingWasReady = arg1;
        return arg1;
    }

    static ByteBuffer access$500(MediaController arg0) {
        return arg0.fileBuffer;
    }

    static int access$5000(MediaController arg0) {
        return arg0.pipSwitchingState;
    }

    static int access$5002(MediaController arg0, int arg1) {
        arg0.pipSwitchingState = arg1;
        return arg1;
    }

    static TextureView access$5100(MediaController arg0) {
        return arg0.currentTextureView;
    }

    static PipRoundVideoView access$5200(MediaController arg0) {
        return arg0.pipRoundVideoView;
    }

    static PipRoundVideoView access$5202(MediaController arg0, PipRoundVideoView arg1) {
        arg0.pipRoundVideoView = arg1;
        return arg1;
    }

    static ArrayList access$5300(MediaController arg0) {
        return arg0.playlist;
    }

    static void access$5400(MediaController arg0, boolean arg1) {
        arg0.playNextMessageWithoutOrder(arg1);
    }

    static File access$5500(MediaController arg0) {
        return arg0.recordingAudioFile;
    }

    static File access$5502(MediaController arg0, File arg1) {
        arg0.recordingAudioFile = arg1;
        return arg1;
    }

    static int access$5600(MediaController arg0, String arg1) {
        return arg0.startRecord(arg1);
    }

    static long access$5700(MediaController arg2) {
        return arg2.recordDialogId;
    }

    static long access$5702(MediaController arg0, long arg1) {
        arg0.recordDialogId = arg1;
        return arg1;
    }

    static MessageObject access$5800(MediaController arg0) {
        return arg0.recordReplyingMessageObject;
    }

    static MessageObject access$5802(MediaController arg0, MessageObject arg1) {
        arg0.recordReplyingMessageObject = arg1;
        return arg1;
    }

    static void access$5900(MediaController arg0) {
        arg0.stopRecord();
    }

    static int access$600(MediaController arg0, ByteBuffer arg1, int arg2) {
        return arg0.writeFrame(arg1, arg2);
    }

    static HashMap access$6000(MediaController arg0) {
        return arg0.generatingWaveform;
    }

    static ChatActivity access$6100(MediaController arg0) {
        return arg0.raiseChat;
    }

    static View access$6200(MediaController arg0) {
        return arg0.feedbackView;
    }

    static String[] access$6300() {
        return MediaController.projectionPhotos;
    }

    static String[] access$6400() {
        return MediaController.projectionVideo;
    }

    static void access$6500(int arg0, ArrayList arg1, ArrayList arg2, Integer arg3, AlbumEntry arg4, AlbumEntry arg5, int arg6) {
        MediaController.broadcastNewPhotos(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    static Runnable access$6602(Runnable arg0) {
        MediaController.broadcastPhotosRunnable = arg0;
        return arg0;
    }

    static Object access$6700(MediaController arg0) {
        return arg0.videoConvertSync;
    }

    static boolean access$6802(MediaController arg0, boolean arg1) {
        arg0.cancelCurrentVideoConversion = arg1;
        return arg1;
    }

    static ArrayList access$6900(MediaController arg0) {
        return arg0.videoConvertQueue;
    }

    static long access$700(MediaController arg2) {
        return arg2.recordTimeCount;
    }

    static boolean access$7000(MediaController arg0) {
        return arg0.startVideoConvertFromQueue();
    }

    static long access$702(MediaController arg0, long arg1) {
        arg0.recordTimeCount = arg1;
        return arg1;
    }

    static boolean access$7100(MediaController arg0, MessageObject arg1) {
        return arg0.convertVideo(arg1);
    }

    static DispatchQueue access$800(MediaController arg0) {
        return arg0.recordQueue;
    }

    static DispatchQueue access$900(MediaController arg0) {
        return arg0.fileEncodingQueue;
    }

    private static void broadcastNewPhotos(int arg8, ArrayList arg9, ArrayList arg10, Integer arg11, AlbumEntry arg12, AlbumEntry arg13, int arg14) {
        if(MediaController.broadcastPhotosRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(MediaController.broadcastPhotosRunnable);
        }

        org.telegram.messenger.MediaController$24 v0 = new Runnable(arg8, arg9, arg10, arg11, arg12, arg13) {
            public void run() {
                if(PhotoViewer.getInstance().isVisible()) {
                    MediaController.broadcastNewPhotos(this.val$guid, this.val$mediaAlbumsSorted, this.val$photoAlbumsSorted, this.val$cameraAlbumIdFinal, this.val$allMediaAlbumFinal, this.val$allPhotosAlbumFinal, 1000);
                    return;
                }

                MediaController.broadcastPhotosRunnable = null;
                MediaController.allPhotosAlbumEntry = this.val$allPhotosAlbumFinal;
                MediaController.allMediaAlbumEntry = this.val$allMediaAlbumFinal;
                int v1;
                for(v1 = 0; true; ++v1) {
                    int v2 = 3;
                    if(v1 >= v2) {
                        return;
                    }

                    NotificationCenter v3 = NotificationCenter.getInstance(v1);
                    int v4 = NotificationCenter.albumsDidLoaded;
                    Object[] v5 = new Object[4];
                    v5[0] = Integer.valueOf(this.val$guid);
                    v5[1] = this.val$mediaAlbumsSorted;
                    v5[2] = this.val$photoAlbumsSorted;
                    v5[v2] = this.val$cameraAlbumIdFinal;
                    v3.postNotificationName(v4, v5);
                }
            }
        };
        MediaController.broadcastPhotosRunnable = ((Runnable)v0);
        AndroidUtilities.runOnUIThread(((Runnable)v0), ((long)arg14));
    }

    private void buildShuffledPlayList() {
        if(this.playlist.isEmpty()) {
            return;
        }

        ArrayList v0 = new ArrayList(this.playlist);
        this.shuffledPlaylist.clear();
        Object v1 = this.playlist.get(this.currentPlaylistNum);
        v0.remove(this.currentPlaylistNum);
        this.shuffledPlaylist.add(v1);
        int v1_1 = v0.size();
        int v2;
        for(v2 = 0; v2 < v1_1; ++v2) {
            int v3 = Utilities.random.nextInt(v0.size());
            this.shuffledPlaylist.add(v0.get(v3));
            v0.remove(v3);
        }
    }

    public void cancelVideoConvert(MessageObject arg6) {
        Object v6_1;
        if(arg6 == null) {
            Object v1 = this.videoConvertSync;
            __monitor_enter(v1);
            try {
                this.cancelCurrentVideoConversion = true;
                __monitor_exit(v1);
                return;
            label_8:
                __monitor_exit(v1);
            }
            catch(Throwable v6) {
                goto label_8;
            }

            throw v6;
        }
        else if(!this.videoConvertQueue.isEmpty()) {
            int v1_1 = 0;
            while(true) {
                if(v1_1 < this.videoConvertQueue.size()) {
                    Object v2 = this.videoConvertQueue.get(v1_1);
                    if(((MessageObject)v2).getId() == arg6.getId() && ((MessageObject)v2).currentAccount == arg6.currentAccount) {
                        if(v1_1 == 0) {
                            v6_1 = this.videoConvertSync;
                            __monitor_enter(v6_1);
                            try {
                                this.cancelCurrentVideoConversion = true;
                                __monitor_exit(v6_1);
                                return;
                            }
                            catch(Throwable v0) {
                                break;
                            }
                        }
                        else {
                            this.videoConvertQueue.remove(v1_1);
                            return;
                        }
                    }

                    ++v1_1;
                    continue;
                }

                return;
            }

            try {
            label_32:
                __monitor_exit(v6_1);
            }
            catch(Throwable v0) {
                goto label_32;
            }

            throw v0;
        }
    }

    private void checkAudioFocus(MessageObject arg5) {
        int v5;
        int v1 = 2;
        int v2 = 3;
        if(!arg5.isVoice() && !arg5.isRoundVideo()) {
            v5 = 1;
        }
        else if(this.useFrontSpeaker) {
            v5 = 3;
        }
        else {
            v5 = 2;
        }

        if(this.hasAudioFocus != v5) {
            this.hasAudioFocus = v5;
            if(v5 == v2) {
                v5 = NotificationsController.audioManager.requestAudioFocus(((AudioManager$OnAudioFocusChangeListener)this), 0, 1);
            }
            else {
                AudioManager v0 = NotificationsController.audioManager;
                v5 = v5 == v1 ? 3 : 1;
                v5 = v0.requestAudioFocus(((AudioManager$OnAudioFocusChangeListener)this), v2, v5);
            }

            if(v5 != 1) {
                return;
            }

            this.audioFocus = v1;
        }
    }

    private void checkConversionCanceled() {
        Object v0 = this.videoConvertSync;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            if(this.cancelCurrentVideoConversion) {
                goto label_6;
            }
        }
        catch(Throwable v1) {
            try {
            label_11:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_11;
            }

            throw v1;
        }

        return;
    label_6:
        throw new RuntimeException("canceled conversion");
    }

    public static void checkGallery() {
        if(Build$VERSION.SDK_INT >= 24) {
            if(MediaController.allPhotosAlbumEntry == null) {
            }
            else {
                Utilities.globalQueue.postRunnable(new Runnable(MediaController.allPhotosAlbumEntry.photos.size()) {
                    @SuppressLint(value={"NewApi"}) public void run() {
                        Cursor v0_1;
                        int v4_1;
                        Cursor v3;
                        Runnable v1 = null;
                        try {
                            if(ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                                v3 = MediaStore$Images$Media.query(ApplicationLoader.applicationContext.getContentResolver(), MediaStore$Images$Media.EXTERNAL_CONTENT_URI, new String[]{"COUNT(_id)"}, null, null, null);
                                if(v3 == null) {
                                    goto label_26;
                                }
                            }
                            else {
                                goto label_25;
                            }
                        }
                        catch(Throwable v0) {
                            goto label_90;
                        }
                        catch(Throwable v4) {
                            v3 = ((Cursor)v1);
                            goto label_34;
                        }

                        try {
                            if(v3.moveToNext()) {
                                v4_1 = v3.getInt(0);
                            }
                            else {
                                goto label_26;
                            }

                            goto label_27;
                        }
                        catch(Throwable v0) {
                        label_89:
                            Cursor v1_1 = v3;
                        }
                        catch(Throwable v4) {
                            try {
                            label_34:
                                FileLog.e(v4);
                                if(v3 != null) {
                                    goto label_36;
                                }

                                goto label_37;
                            }
                            catch(Throwable v0) {
                                goto label_89;
                            }

                        label_36:
                            v3.close();
                        label_37:
                            v4_1 = 0;
                            goto label_38;
                        }

                    label_90:
                        if((((Cursor)v1)) != null) {
                            ((Cursor)v1).close();
                        }

                        throw v0;
                    label_25:
                        v3 = ((Cursor)v1);
                    label_26:
                        v4_1 = 0;
                    label_27:
                        if(v3 == null) {
                            goto label_38;
                        }

                        v3.close();
                        try {
                        label_38:
                            if(ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                                v0_1 = MediaStore$Images$Media.query(ApplicationLoader.applicationContext.getContentResolver(), MediaStore$Video$Media.EXTERNAL_CONTENT_URI, new String[]{"COUNT(_id)"}, null, null, null);
                                if(v0_1 == null) {
                                    goto label_67;
                                }

                                goto label_53;
                            }
                            else {
                                goto label_66;
                            }
                        }
                        catch(Throwable v1_2) {
                        }
                        catch(Throwable v0) {
                            goto label_73;
                            try {
                            label_53:
                                if(v0_1.moveToNext()) {
                                    v4_1 += v0_1.getInt(0);
                                }

                                goto label_67;
                            }
                            catch(Throwable v1_2) {
                                v3 = v0_1;
                            }
                            catch(Throwable v3_1) {
                                Throwable v12 = v3_1;
                                v3 = v0_1;
                                v0 = v12;
                                try {
                                label_73:
                                    FileLog.e(v0);
                                    if(v3 == null) {
                                        goto label_76;
                                    }
                                }
                                catch(Throwable v1_2) {
                                    goto label_85;
                                }

                                v3.close();
                                goto label_76;
                            }
                        }

                    label_85:
                        if(v3 != null) {
                            v3.close();
                        }

                        throw v1_2;
                    label_66:
                        v0_1 = v3;
                    label_67:
                        if(v0_1 != null) {
                            v0_1.close();
                        }

                    label_76:
                        if(this.val$prevSize != v4_1) {
                            if(MediaController.refreshGalleryRunnable != null) {
                                AndroidUtilities.cancelRunOnUIThread(MediaController.refreshGalleryRunnable);
                                MediaController.refreshGalleryRunnable = v1;
                            }

                            MediaController.loadGalleryPhotosAlbums(0);
                        }
                    }
                }, 2000);
            }
        }
    }

    protected void checkIsNextMediaFileDownloaded() {
        if(this.playingMessageObject != null) {
            if(!this.playingMessageObject.isMusic()) {
            }
            else {
                this.checkIsNextMusicFileDownloaded(this.playingMessageObject.currentAccount);
            }
        }
    }

    private void checkIsNextMusicFileDownloaded(int arg6) {
        File v1_1;
        int v1;
        if((DownloadController.getInstance(arg6).getCurrentDownloadMask() & 16) == 0) {
            return;
        }

        ArrayList v0 = SharedConfig.shuffleMusic ? this.shuffledPlaylist : this.playlist;
        if(v0 != null) {
            if(v0.size() < 2) {
            }
            else {
                if(SharedConfig.playOrderReversed) {
                    v1 = this.currentPlaylistNum + 1;
                    if(v1 >= v0.size()) {
                        v1 = 0;
                    }
                }
                else {
                    v1 = this.currentPlaylistNum - 1;
                    if(v1 < 0) {
                        v1 = v0.size() - 1;
                    }
                }

                Object v0_1 = v0.get(v1);
                if(!DownloadController.getInstance(arg6).canDownloadMedia(((MessageObject)v0_1))) {
                    return;
                }

                File v3 = null;
                if(!TextUtils.isEmpty(((MessageObject)v0_1).messageOwner.attachPath)) {
                    v1_1 = new File(((MessageObject)v0_1).messageOwner.attachPath);
                    if(!v1_1.exists()) {
                    }
                    else {
                        v3 = v1_1;
                    }
                }

                v1_1 = v3 != null ? v3 : FileLoader.getPathToMessage(((MessageObject)v0_1).messageOwner);
                if(v1_1 != null) {
                    v1_1.exists();
                }

                if(v1_1 == null) {
                    return;
                }

                if(v1_1 == v3) {
                    return;
                }

                if(v1_1.exists()) {
                    return;
                }

                if(!((MessageObject)v0_1).isMusic()) {
                    return;
                }

                FileLoader.getInstance(arg6).loadFile(((MessageObject)v0_1).getDocument(), false, 0);
            }
        }
    }

    private void checkIsNextVoiceFileDownloaded(int arg5) {
        File v1;
        if(this.voiceMessagesPlaylist != null) {
            if(this.voiceMessagesPlaylist.size() < 2) {
            }
            else {
                Object v0 = this.voiceMessagesPlaylist.get(1);
                File v2 = null;
                if(((MessageObject)v0).messageOwner.attachPath != null && ((MessageObject)v0).messageOwner.attachPath.length() > 0) {
                    v1 = new File(((MessageObject)v0).messageOwner.attachPath);
                    if(!v1.exists()) {
                    }
                    else {
                        v2 = v1;
                    }
                }

                v1 = v2 != null ? v2 : FileLoader.getPathToMessage(((MessageObject)v0).messageOwner);
                if(v1 != null) {
                    v1.exists();
                }

                if(v1 == null) {
                    return;
                }

                if(v1 == v2) {
                    return;
                }

                if(v1.exists()) {
                    return;
                }

                FileLoader.getInstance(arg5).loadFile(((MessageObject)v0).getDocument(), false, 0);
            }
        }
    }

    private void checkScreenshots(ArrayList arg13) {
        if(arg13 != null && !arg13.isEmpty()) {
            long v2 = 0;
            if(this.lastChatEnterTime != v2) {
                if(this.lastUser == null && !(this.lastSecretChat instanceof TL_encryptedChat)) {
                    return;
                }

                long v0 = 2000;
                int v4 = 0;
                int v5 = 0;
                while(v4 < arg13.size()) {
                    Object v6 = arg13.get(v4);
                    if(this.lastMediaCheckTime != v2 && ((Long)v6).longValue() <= this.lastMediaCheckTime) {
                    }
                    else if(((Long)v6).longValue() >= this.lastChatEnterTime) {
                        if(this.lastChatLeaveTime != v2 && ((Long)v6).longValue() > this.lastChatLeaveTime + v0) {
                            goto label_38;
                        }

                        this.lastMediaCheckTime = Math.max(this.lastMediaCheckTime, ((Long)v6).longValue());
                        v5 = 1;
                    }

                label_38:
                    ++v4;
                }

                if(v5 == 0) {
                    return;
                }

                Message v0_1 = null;
                if(this.lastSecretChat != null) {
                    SecretChatHelper.getInstance(this.lastChatAccount).sendScreenshotMessage(this.lastSecretChat, this.lastChatVisibleMessages, v0_1);
                    return;
                }

                SendMessagesHelper.getInstance(this.lastChatAccount).sendScreenshotMessage(this.lastUser, this.lastMessageId, v0_1);
            }
        }
    }

    public void cleanup() {
        int v1 = 0;
        this.cleanupPlayer(false, true);
        AudioInfo v0 = null;
        this.audioInfo = v0;
        this.playMusicAgain = false;
        while(v1 < 3) {
            DownloadController.getInstance(v1).cleanup();
            ++v1;
        }

        this.videoConvertQueue.clear();
        this.playlist.clear();
        this.shuffledPlaylist.clear();
        this.generatingWaveform.clear();
        this.voiceMessagesPlaylist = ((ArrayList)v0);
        this.voiceMessagesPlaylistMap = ((SparseArray)v0);
        this.cancelVideoConvert(((MessageObject)v0));
    }

    public void cleanupPlayer(boolean arg2, boolean arg3) {
        this.cleanupPlayer(arg2, arg3, false);
    }

    public void cleanupPlayer(boolean arg10, boolean arg11, boolean arg12) {
        VideoPlayer v2 = null;
        if(this.audioPlayer != null) {
            try {
                this.audioPlayer.releasePlayer();
            }
            catch(Exception v0) {
                FileLog.e(((Throwable)v0));
            }

            this.audioPlayer = v2;
            goto label_27;
        }

        if(this.videoPlayer != null) {
            this.currentAspectRatioFrameLayout = ((AspectRatioFrameLayout)v2);
            this.currentTextureViewContainer = ((FrameLayout)v2);
            this.currentAspectRatioFrameLayoutReady = false;
            this.currentTextureView = ((TextureView)v2);
            this.videoPlayer.releasePlayer();
            this.videoPlayer = v2;
            try {
                this.baseActivity.getWindow().clearFlags(128);
            }
            catch(Exception v0) {
                FileLog.e(((Throwable)v0));
            }
        }

    label_27:
        this.stopProgressTimer();
        this.lastProgress = 0;
        this.isPaused = false;
        if(!this.useFrontSpeaker && !SharedConfig.raiseToSpeak) {
            ChatActivity v0_1 = this.raiseChat;
            this.stopRaiseToEarSensors(this.raiseChat, false);
            this.raiseChat = v0_1;
        }

        if(this.playingMessageObject != null) {
            if(this.downloadingCurrentMessage) {
                FileLoader.getInstance(this.playingMessageObject.currentAccount).cancelLoadFile(this.playingMessageObject.getDocument());
            }

            MessageObject v0_2 = this.playingMessageObject;
            int v3 = 2;
            if(arg10) {
                this.playingMessageObject.resetPlayingProgress();
                NotificationCenter v5 = NotificationCenter.getInstance(v0_2.currentAccount);
                int v6 = NotificationCenter.messagePlayingProgressDidChanged;
                Object[] v7 = new Object[v3];
                v7[0] = Integer.valueOf(this.playingMessageObject.getId());
                v7[1] = Integer.valueOf(0);
                v5.postNotificationName(v6, v7);
            }

            this.playingMessageObject = ((MessageObject)v2);
            this.downloadingCurrentMessage = false;
            if(arg10) {
                NotificationsController.audioManager.abandonAudioFocus(((AudioManager$OnAudioFocusChangeListener)this));
                this.hasAudioFocus = 0;
                int v10 = -1;
                if(this.voiceMessagesPlaylist != null) {
                    if(arg12) {
                        v10 = this.voiceMessagesPlaylist.indexOf(v0_2);
                        if(v10 >= 0) {
                            this.voiceMessagesPlaylist.remove(v10);
                            this.voiceMessagesPlaylistMap.remove(v0_2.getId());
                            if(this.voiceMessagesPlaylist.isEmpty()) {
                                goto label_87;
                            }

                            goto label_89;
                        }
                    }

                label_87:
                    this.voiceMessagesPlaylist = ((ArrayList)v2);
                    this.voiceMessagesPlaylistMap = ((SparseArray)v2);
                }

            label_89:
                if(this.voiceMessagesPlaylist == null || v10 >= this.voiceMessagesPlaylist.size()) {
                    if(((v0_2.isVoice()) || (v0_2.isRoundVideo())) && v0_2.getId() != 0) {
                        this.startRecordingIfFromSpeaker();
                    }

                    NotificationCenter v10_2 = NotificationCenter.getInstance(v0_2.currentAccount);
                    int v12 = NotificationCenter.messagePlayingDidReset;
                    Object[] v3_1 = new Object[v3];
                    v3_1[0] = Integer.valueOf(v0_2.getId());
                    v3_1[1] = Boolean.valueOf(arg11);
                    v10_2.postNotificationName(v12, v3_1);
                    this.pipSwitchingState = 0;
                    if(this.pipRoundVideoView == null) {
                        goto label_125;
                    }
                }
                else {
                    Object v10_1 = this.voiceMessagesPlaylist.get(v10);
                    this.playMessage(((MessageObject)v10_1));
                    if(((MessageObject)v10_1).isRoundVideo()) {
                        goto label_125;
                    }
                    else if(this.pipRoundVideoView != null) {
                    }
                    else {
                        goto label_125;
                    }
                }

                this.pipRoundVideoView.close(true);
                this.pipRoundVideoView = ((PipRoundVideoView)v2);
            }

        label_125:
            if(!arg11) {
                return;
            }

            ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
        }
    }

    private boolean convertVideo(MessageObject arg68) {
        boolean v37;
        StringBuilder v7_2;
        long v9_4;
        int v62;
        boolean v2_8;
        ByteBuffer v13_4;
        ByteBuffer v9_3;
        ByteBuffer v12_5;
        int v58;
        int v59;
        int v60;
        int v24;
        ByteBuffer v56;
        int v57;
        int v51;
        int v52;
        long v38;
        int v30_1;
        int v12_3;
        ByteBuffer[] v31;
        ByteBuffer[] v6_6;
        ByteBuffer[] v12_2;
        ByteBuffer[] v5_8;
        MediaCrypto v1_6;
        OutputSurface v1_5;
        int v48;
        InputSurface v5_7;
        Surface v12_1;
        MediaCodec v4_3;
        MediaFormat v5_6;
        long v46;
        int v45;
        ByteBuffer v10_4;
        int v44;
        int v13_3;
        MediaFormat v6_5;
        File v42;
        int v41;
        int v40;
        int v6_3;
        String v10_3;
        int v9_2;
        int v8_3;
        boolean v9_1;
        boolean v5_2;
        File v13_1;
        int v1_2;
        MediaExtractor v8_2;
        MediaExtractor v6_2;
        Exception v1_1;
        SharedPreferences v36;
        File v5_1;
        Throwable v2_1;
        MP4Builder v14_2;
        MediaCodec$BufferInfo v7_1;
        int v35;
        int v34;
        int v33;
        MediaController v12 = this;
        MessageObject v13 = arg68;
        String v1 = v13.videoEditedInfo.originalPath;
        long v6 = v13.videoEditedInfo.startTime;
        long v8 = v13.videoEditedInfo.endTime;
        int v2 = v13.videoEditedInfo.resultWidth;
        int v3 = v13.videoEditedInfo.resultHeight;
        int v4 = v13.videoEditedInfo.rotationValue;
        int v5 = v13.videoEditedInfo.originalWidth;
        int v10 = v13.videoEditedInfo.originalHeight;
        int v11 = v13.videoEditedInfo.framerate;
        int v14 = v13.videoEditedInfo.bitrate;
        long v15 = v8;
        boolean v8_1 = (((int)arg68.getDialogId())) == 0 ? true : false;
        int v19 = v11;
        File v9 = new File(v13.messageOwner.attachPath);
        if(v1 == null) {
            v1 = "";
        }

        long v20 = v15;
        if(Build$VERSION.SDK_INT >= 18 || v3 <= v2 || v2 == v5 || v3 == v10) {
            if(Build$VERSION.SDK_INT > 20) {
                v11 = 90;
                if(v4 == v11) {
                    v4 = 0;
                label_53:
                    v11 = 270;
                    goto label_69;
                }
                else if(v4 == 180) {
                    v4 = 0;
                    v11 = 180;
                }
                else if(v4 == 270) {
                    v4 = 0;
                    goto label_69;
                }
                else {
                    goto label_65;
                }
            }
            else {
            label_65:
                v11 = 0;
            }

            int v66 = v3;
            v3 = v2;
            v2 = v66;
        }
        else {
            v4 = 90;
            goto label_53;
        }

    label_69:
        long v30 = v6;
        SharedPreferences v15_1 = ApplicationLoader.applicationContext.getSharedPreferences("videoconvert", 0);
        File v6_1 = new File(v1);
        if(arg68.getId() != 0) {
            v33 = v14;
            boolean v7 = v15_1.getBoolean("isPreviousOk", true);
            v34 = v11;
            v35 = v10;
            v15_1.edit().putBoolean("isPreviousOk", false).commit();
            if((v6_1.canRead()) && (v7)) {
                goto label_103;
            }

            v12.didWriteData(v13, v9, true, true);
            v15_1.edit().putBoolean("isPreviousOk", true).commit();
            return 0;
        }
        else {
            v35 = v10;
            v34 = v11;
            v33 = v14;
        }

    label_103:
        v12.videoConvertFirstWrite = true;
        long v16 = System.currentTimeMillis();
        if(v3 != 0 && v2 != 0) {
            try {
                v7_1 = new MediaCodec$BufferInfo();
                Mp4Movie v14_1 = new Mp4Movie();
                v14_1.setCacheFile(v9);
                v14_1.setRotation(v4);
                v14_1.setSize(v3, v2);
                v14_2 = new MP4Builder().createMovie(v14_1, v8_1);
            }
            catch(Throwable v0) {
                v2_1 = v0;
                v14_2 = null;
                goto label_1116;
            }
            catch(Exception v0_1) {
                v5_1 = v9;
                v36 = v15_1;
                v1_1 = v0_1;
                v6_2 = null;
                v14_2 = null;
                goto label_1124;
            }

            try {
                v8_2 = new MediaExtractor();
                goto label_121;
            }
            catch(Throwable v0) {
                v2_1 = v0;
            }
            catch(Exception v0_1) {
                v5_1 = v9;
                v36 = v15_1;
                v1_1 = v0_1;
                v6_2 = null;
                goto label_1124;
            }

        label_1116:
            MediaExtractor v15_2 = null;
            goto label_1158;
            try {
            label_121:
                v8_2.setDataSource(v1);
                this.checkConversionCanceled();
                v1_2 = -1;
                if(v3 != v5) {
                    goto label_172;
                }
            }
            catch(Throwable v0) {
                v15_2 = v8_2;
                goto label_1092;
            }
            catch(Exception v0_1) {
                v5_1 = v9;
                v36 = v15_1;
                v15_2 = v8_2;
                goto label_1099;
            }

            if(v2 != v35 || v34 != 0) {
            label_172:
                v13_1 = v9;
                v36 = v15_1;
                v4 = v33;
                v15_2 = v8_2;
                v5_2 = false;
            }
            else {
                try {
                    if(!v13.videoEditedInfo.roundVideo) {
                        goto label_137;
                    }
                }
                catch(Throwable v0) {
                    v15_2 = v8_2;
                    goto label_163;
                }
                catch(Exception v0_1) {
                    v36 = v15_1;
                    v15_2 = v8_2;
                    v1_1 = v0_1;
                    v5_1 = v9;
                    goto label_1100;
                }

                v13_1 = v9;
                v36 = v15_1;
                v4 = v33;
                v5_2 = false;
                v15_2 = v8_2;
                goto label_177;
            label_137:
                boolean v18 = v33 != v1_2 ? true : false;
                MediaController v1_3 = this;
                MessageObject v2_2 = arg68;
                MediaExtractor v3_1 = v8_2;
                MP4Builder v4_1 = v14_2;
                MediaCodec$BufferInfo v5_3 = v7_1;
                v6 = v30;
                MediaExtractor v10_1 = v8_2;
                File v11_1 = v9;
                v36 = v15_1;
                v8 = v20;
                v15_2 = v10_1;
                File v10_2 = v11_1;
                v13_1 = v11_1;
                boolean v11_2 = v18;
                try {
                    v1_3.readAndWriteTracks(v2_2, v3_1, v4_1, v5_3, v6, v8, v10_2, v11_2);
                    v5_1 = v13_1;
                    v9_1 = false;
                    goto label_1066;
                }
                catch(Exception v0_1) {
                    goto label_185;
                }
                catch(Throwable v0) {
                    goto label_163;
                }
            }

            try {
            label_177:
                v8_3 = v12.findTrack(v15_2, v5_2);
                if(v4 == v1_2) {
                    goto label_188;
                }

                goto label_179;
            }
            catch(Throwable v0) {
            }
            catch(Exception v0_1) {
                v5_1 = v13_1;
                goto label_1099;
            label_179:
                try {
                    v9_2 = v12.findTrack(v15_2, true);
                    goto label_190;
                }
                catch(Throwable v0) {
                }
                catch(Exception v0_1) {
                label_185:
                    v1_1 = v0_1;
                    v5_1 = v13_1;
                    goto label_1100;
                label_188:
                    v9_2 = -1;
                label_190:
                    if(v8_3 < 0) {
                        goto label_1063;
                    }

                    try {
                        v10_3 = Build.MANUFACTURER.toLowerCase();
                        if(Build$VERSION.SDK_INT < 18) {
                        }
                        else {
                            goto label_277;
                        }
                    }
                    catch(Exception v0_1) {
                        goto label_1038;
                    }
                    catch(Throwable v0) {
                        goto label_1086;
                    }

                    try {
                        MediaCodecInfo v1_4 = MediaController.selectCodec("video/avc");
                        v11 = MediaController.selectColorFormat(v1_4, "video/avc");
                        if(v11 != 0) {
                            String v5_4 = v1_4.getName();
                            if(v5_4.contains("OMX.qcom.")) {
                                if(Build$VERSION.SDK_INT == 16 && ((v10_3.equals("lge")) || (v10_3.equals("nokia")))) {
                                    v5 = 1;
                                    goto label_217;
                                }

                                v5 = 1;
                                goto label_220;
                            }
                            else {
                                if(v5_4.contains("OMX.Intel.")) {
                                    v5 = 2;
                                    goto label_220;
                                }

                                if(v5_4.equals("OMX.MTK.VIDEO.ENCODER.AVC")) {
                                    v5 = 3;
                                    goto label_220;
                                }

                                if(v5_4.equals("OMX.SEC.AVC.Encoder")) {
                                    v5 = 4;
                                label_217:
                                    v6_3 = 1;
                                    goto label_244;
                                }

                                v5 = v5_4.equals("OMX.TI.DUCATI1.VIDEO.H264E") ? 5 : 0;
                            label_220:
                                v6_3 = 0;
                            }

                        label_244:
                            if(BuildVars.LOGS_ENABLED) {
                                v40 = v5;
                                StringBuilder v5_5 = new StringBuilder();
                                v41 = v6_3;
                                v5_5.append("codec = ");
                                v5_5.append(v1_4.getName());
                                v5_5.append(" manufacturer = ");
                                v5_5.append(v10_3);
                                v5_5.append("device = ");
                                v5_5.append(Build.MODEL);
                                FileLog.d(v5_5.toString());
                            }
                            else {
                                v40 = v5;
                                v41 = v6_3;
                            }

                            v1_2 = v11;
                            v5 = v40;
                        }
                        else {
                            throw new RuntimeException("no supported color format");
                        }

                        goto label_280;
                    }
                    catch(Exception v0_1) {
                        goto label_274;
                    }
                    catch(Throwable v0) {
                        goto label_163;
                    }

                label_277:
                    v1_2 = 2130708361;
                    v5 = 0;
                    v41 = 0;
                    try {
                    label_280:
                        if(BuildVars.LOGS_ENABLED) {
                            goto label_282;
                        }

                        goto label_289;
                    }
                    catch(Exception v0_1) {
                        goto label_1038;
                    }
                    catch(Throwable v0) {
                        goto label_1086;
                    }

                    try {
                    label_282:
                        FileLog.d("colorFormat = " + v1_2);
                    }
                    catch(Exception v0_1) {
                        goto label_274;
                    }
                    catch(Throwable v0) {
                        goto label_163;
                    }

                label_289:
                    v6_3 = v3 * v2;
                    v11 = v6_3 * 3;
                    try {
                        v11 /= 2;
                        if(v5 != 0) {
                            goto label_308;
                        }

                        goto label_293;
                    }
                    catch(Throwable v0) {
                    }
                    catch(Exception v0_1) {
                    label_1038:
                        v5_1 = v13_1;
                        goto label_1039;
                        try {
                        label_293:
                            if(v2 % 16 != 0) {
                                v5 = v3 * (16 - v2 % 16 + v2 - v2);
                                v11 += v5 * 5 / 4;
                                v42 = v13_1;
                            }
                            else {
                                goto label_306;
                            }

                            goto label_343;
                        }
                        catch(Throwable v0) {
                        }
                        catch(Exception v0_1) {
                        label_274:
                            v1_1 = v0_1;
                            v5_1 = v13_1;
                            goto label_1040;
                        label_306:
                            v42 = v13_1;
                            goto label_342;
                        label_308:
                            v42 = v13_1;
                            if(v5 == 1) {
                                try {
                                    if(!v10_3.toLowerCase().equals("lge")) {
                                        v5 = (v6_3 + 2047 & -2048) - v6_3;
                                        v11 += v5;
                                        goto label_343;
                                    label_324:
                                        if(v5 == 5) {
                                            goto label_342;
                                        }
                                        else if(v5 != 3) {
                                            goto label_342;
                                        }
                                        else if(v10_3.equals("baidu")) {
                                            v5 = v3 * (16 - v2 % 16 + v2 - v2);
                                            v11 += v5 * 5 / 4;
                                        }
                                        else {
                                            goto label_342;
                                        }
                                    }
                                    else {
                                        goto label_342;
                                    }
                                }
                                catch(Exception v0_1) {
                                    goto label_321;
                                }
                                catch(Throwable v0) {
                                    goto label_163;
                                }
                            }
                            else {
                                goto label_324;
                            label_342:
                                v5 = 0;
                            }

                            try {
                            label_343:
                                v15_2.selectTrack(v8_3);
                                v6_5 = v15_2.getTrackFormat(v8_3);
                                if(v9_2 >= 0) {
                                }
                                else {
                                    goto label_357;
                                }
                            }
                            catch(Exception v0_1) {
                                goto label_1035;
                            }
                            catch(Throwable v0) {
                                goto label_1086;
                            }

                            try {
                                v15_2.selectTrack(v9_2);
                                MediaFormat v13_2 = v15_2.getTrackFormat(v9_2);
                                ByteBuffer v43 = ByteBuffer.allocateDirect(v13_2.getInteger("max-input-size"));
                                v13_3 = v14_2.addTrack(v13_2, true);
                                v44 = v11;
                                v10_4 = v43;
                                goto label_360;
                            }
                            catch(Exception v0_1) {
                                goto label_321;
                            }
                            catch(Throwable v0) {
                                goto label_163;
                            }

                        label_357:
                            v44 = v11;
                            v10_4 = null;
                            v13_3 = -5;
                        label_360:
                            long v11_3 = 0;
                            if(v30 > v11_3) {
                                v45 = v5;
                                v11_3 = v30;
                                try {
                                    v15_2.seekTo(v11_3, 0);
                                    v46 = v11_3;
                                    goto label_372;
                                }
                                catch(Exception v0_1) {
                                    goto label_321;
                                }
                                catch(Throwable v0) {
                                    goto label_1086;
                                }
                            }
                            else {
                                v45 = v5;
                                v46 = v30;
                                try {
                                    v15_2.seekTo(v11_3, 0);
                                label_372:
                                    v5_6 = MediaFormat.createVideoFormat("video/avc", v3, v2);
                                    v5_6.setInteger("color-format", v1_2);
                                    String v11_4 = "bitrate";
                                    if(v4 > 0) {
                                    }
                                    else {
                                        v4 = 921600;
                                    }

                                    v5_6.setInteger(v11_4, v4);
                                    String v4_2 = "frame-rate";
                                    v11 = v19 != 0 ? v19 : 25;
                                    v5_6.setInteger(v4_2, v11);
                                    v5_6.setInteger("i-frame-interval", 10);
                                    if(Build$VERSION.SDK_INT < 18) {
                                        goto label_393;
                                    }

                                    goto label_398;
                                }
                                catch(Exception v0_1) {
                                    goto label_1035;
                                }
                                catch(Throwable v0) {
                                    goto label_1086;
                                }
                            }

                            goto label_372;
                            try {
                            label_393:
                                v5_6.setInteger("stride", v3 + 32);
                                v5_6.setInteger("slice-height", v2);
                                goto label_398;
                            }
                            catch(Throwable v0) {
                            }
                            catch(Exception v0_1) {
                            label_321:
                                v1_1 = v0_1;
                                v5_1 = v42;
                                goto label_1040;
                            label_163:
                                v2_1 = v0;
                                goto label_1158;
                                try {
                                label_398:
                                    v4_3 = MediaCodec.createEncoderByType("video/avc");
                                    v12_1 = null;
                                    goto label_402;
                                }
                                catch(Throwable v0) {
                                }
                                catch(Exception v0_1) {
                                label_1035:
                                    v5_1 = v42;
                                label_1039:
                                    v1_1 = v0_1;
                                label_1040:
                                    v4_3 = null;
                                    goto label_1041;
                                    try {
                                    label_402:
                                        v4_3.configure(v5_6, v12_1, ((MediaCrypto)v12_1), 1);
                                        if(Build$VERSION.SDK_INT < 18) {
                                            goto label_420;
                                        }

                                        goto label_406;
                                    }
                                    catch(Throwable v0) {
                                    }
                                    catch(Exception v0_1) {
                                        v5_1 = v42;
                                        v1_1 = v0_1;
                                        goto label_1041;
                                        try {
                                        label_406:
                                            v5_7 = new InputSurface(v4_3.createInputSurface());
                                            goto label_409;
                                        }
                                        catch(Throwable v0) {
                                        }
                                        catch(Exception v0_1) {
                                            v1_1 = v0_1;
                                            v5_1 = v42;
                                        label_1041:
                                            MediaCodec v11_5 = null;
                                            InputSurface v49 = null;
                                            goto label_1043;
                                            try {
                                            label_409:
                                                v5_7.makeCurrent();
                                                goto label_421;
                                            }
                                            catch(Throwable v0) {
                                            }
                                            catch(Exception v0_1) {
                                                v1_1 = v0_1;
                                                v49 = v5_7;
                                                v5_1 = v42;
                                                goto label_1028;
                                            label_420:
                                                v5_7 = null;
                                                try {
                                                label_421:
                                                    v4_3.start();
                                                    v11_5 = MediaCodec.createDecoderByType(v6_5.getString("mime"));
                                                    goto label_425;
                                                }
                                                catch(Throwable v0) {
                                                }
                                                catch(Exception v0_1) {
                                                    InputSurface v3_2 = v5_7;
                                                    v5_1 = v42;
                                                    v1_1 = v0_1;
                                                    v49 = v3_2;
                                                label_1028:
                                                    v11_5 = null;
                                                    goto label_1043;
                                                    try {
                                                    label_425:
                                                        v48 = v1_2;
                                                        if(Build$VERSION.SDK_INT >= 18) {
                                                        }
                                                        else {
                                                            goto label_437;
                                                        }
                                                    }
                                                    catch(Exception v0_1) {
                                                        goto label_1018;
                                                    }
                                                    catch(Throwable v0) {
                                                        goto label_1086;
                                                    }

                                                    try {
                                                        v1_5 = new OutputSurface();
                                                        goto label_440;
                                                    }
                                                    catch(Throwable v0) {
                                                    }
                                                    catch(Exception v0_1) {
                                                        v1_1 = v0_1;
                                                        v49 = v5_7;
                                                        v5_1 = v42;
                                                        goto label_1043;
                                                        try {
                                                        label_437:
                                                            v1_5 = new OutputSurface(v3, v2, v34);
                                                            goto label_440;
                                                        }
                                                        catch(Throwable v0) {
                                                        }
                                                        catch(Exception v0_1) {
                                                        label_1018:
                                                            v3_2 = v5_7;
                                                            v5_1 = v42;
                                                            v1_1 = v0_1;
                                                            v49 = v3_2;
                                                        label_1043:
                                                            OutputSurface v50 = null;
                                                            goto label_1044;
                                                            try {
                                                            label_440:
                                                                v12_1 = v1_5.getSurface();
                                                                v50 = v1_5;
                                                                v49 = v5_7;
                                                                v1_6 = null;
                                                                v5_7 = null;
                                                                goto label_445;
                                                            }
                                                            catch(Throwable v0) {
                                                            }
                                                            catch(Exception v0_1) {
                                                                OutputSurface v2_3 = ((OutputSurface)v1_6);
                                                                v3_2 = v5_7;
                                                                v5_1 = v42;
                                                                goto label_1013;
                                                                try {
                                                                label_445:
                                                                    v11_5.configure(v6_5, v12_1, v1_6, 0);
                                                                    v11_5.start();
                                                                    if(Build$VERSION.SDK_INT < 21) {
                                                                    }
                                                                    else {
                                                                        goto label_463;
                                                                    }
                                                                }
                                                                catch(Exception v0_1) {
                                                                    goto label_1004;
                                                                }
                                                                catch(Throwable v0) {
                                                                    goto label_1086;
                                                                }

                                                                try {
                                                                    v5_8 = v11_5.getInputBuffers();
                                                                    v12_2 = v4_3.getOutputBuffers();
                                                                    if(Build$VERSION.SDK_INT < 18) {
                                                                        v6_6 = v4_3.getInputBuffers();
                                                                    }
                                                                    else {
                                                                        goto label_457;
                                                                    }

                                                                    goto label_466;
                                                                }
                                                                catch(Exception v0_1) {
                                                                    goto label_460;
                                                                }
                                                                catch(Throwable v0) {
                                                                    goto label_1086;
                                                                }

                                                            label_457:
                                                                v6_6 = null;
                                                                goto label_466;
                                                            label_463:
                                                                v5_8 = null;
                                                                v6_6 = null;
                                                                v12_2 = null;
                                                                try {
                                                                label_466:
                                                                    this.checkConversionCanceled();
                                                                    v31 = v12_2;
                                                                    v1_2 = 0;
                                                                    v12_3 = 0;
                                                                    v19 = 0;
                                                                    v30_1 = -5;
                                                                    v38 = -1;
                                                                    while(true) {
                                                                    label_473:
                                                                        if(v1_2 != 0) {
                                                                            goto label_998;
                                                                        }

                                                                        this.checkConversionCanceled();
                                                                        v52 = v1_2;
                                                                        v51 = v2;
                                                                        if(v12_3 == 0) {
                                                                            break;
                                                                        }
                                                                        else {
                                                                            goto label_608;
                                                                        }
                                                                    }
                                                                }
                                                                catch(Exception v0_1) {
                                                                    goto label_1004;
                                                                }
                                                                catch(Throwable v0) {
                                                                    goto label_1086;
                                                                }

                                                                try {
                                                                    v1_2 = v15_2.getSampleTrackIndex();
                                                                    if(v1_2 == v8_3) {
                                                                    }
                                                                    else {
                                                                        goto label_519;
                                                                    }
                                                                }
                                                                catch(Exception v0_1) {
                                                                    goto label_604;
                                                                }
                                                                catch(Throwable v0) {
                                                                    goto label_1086;
                                                                }

                                                                int v53 = v3;
                                                                long v2_4 = 2500;
                                                                try {
                                                                    v1_2 = v11_5.dequeueInputBuffer(v2_4);
                                                                    if(v1_2 >= 0) {
                                                                        ByteBuffer v2_5 = Build$VERSION.SDK_INT < 21 ? v5_8[v1_2] : v11_5.getInputBuffer(v1_2);
                                                                        int v26 = v15_2.readSampleData(v2_5, 0);
                                                                        if(v26 < 0) {
                                                                            v11_5.queueInputBuffer(v1_2, 0, 0, 0, 4);
                                                                            v12_3 = 1;
                                                                            goto label_510;
                                                                        }

                                                                        v11_5.queueInputBuffer(v1_2, 0, v26, v15_2.getSampleTime(), 0);
                                                                        v15_2.advance();
                                                                    }
                                                                }
                                                                catch(Exception v0_1) {
                                                                    goto label_460;
                                                                }
                                                                catch(Throwable v0) {
                                                                    goto label_1086;
                                                                }

                                                            label_510:
                                                                ByteBuffer[] v54 = v5_8;
                                                                int v55 = v9_2;
                                                                v9_2 = v12_3;
                                                                v5_1 = v42;
                                                                v1_2 = 0;
                                                                MediaController v2_6 = this;
                                                                MessageObject v3_3 = arg68;
                                                                long v32 = 0;
                                                                goto label_580;
                                                            label_519:
                                                                v53 = v3;
                                                                if(v9_2 == -1) {
                                                                    goto label_567;
                                                                }
                                                                else if(v1_2 == v9_2) {
                                                                    try {
                                                                        v7_1.size = v15_2.readSampleData(v10_4, 0);
                                                                        if(Build$VERSION.SDK_INT < 21) {
                                                                            goto label_529;
                                                                        }

                                                                        goto label_532;
                                                                    }
                                                                    catch(Exception v0_1) {
                                                                        goto label_604;
                                                                    }
                                                                    catch(Throwable v0) {
                                                                        goto label_1086;
                                                                    }

                                                                    try {
                                                                    label_529:
                                                                        v10_4.position(0);
                                                                        v10_4.limit(v7_1.size);
                                                                    }
                                                                    catch(Exception v0_1) {
                                                                        goto label_460;
                                                                    }
                                                                    catch(Throwable v0) {
                                                                        goto label_1086;
                                                                    }

                                                                    try {
                                                                    label_532:
                                                                        if(v7_1.size >= 0) {
                                                                        }
                                                                        else {
                                                                            goto label_539;
                                                                        }
                                                                    }
                                                                    catch(Exception v0_1) {
                                                                        goto label_604;
                                                                    }
                                                                    catch(Throwable v0) {
                                                                        goto label_1086;
                                                                    }

                                                                    try {
                                                                        v7_1.presentationTimeUs = v15_2.getSampleTime();
                                                                        v15_2.advance();
                                                                        goto label_541;
                                                                    }
                                                                    catch(Exception v0_1) {
                                                                        goto label_460;
                                                                    }
                                                                    catch(Throwable v0) {
                                                                        goto label_1086;
                                                                    }

                                                                    try {
                                                                    label_539:
                                                                        v7_1.size = 0;
                                                                        v12_3 = 1;
                                                                    label_541:
                                                                        if(v7_1.size > 0) {
                                                                        }
                                                                        else {
                                                                            goto label_560;
                                                                        }
                                                                    }
                                                                    catch(Exception v0_1) {
                                                                        goto label_604;
                                                                    }
                                                                    catch(Throwable v0) {
                                                                        goto label_1086;
                                                                    }

                                                                    if(v20 >= 0) {
                                                                        try {
                                                                            if(v7_1.presentationTimeUs < v20) {
                                                                            }
                                                                            else {
                                                                                goto label_560;
                                                                            }
                                                                        }
                                                                        catch(Exception v0_1) {
                                                                            goto label_460;
                                                                        }
                                                                        catch(Throwable v0) {
                                                                            goto label_1086;
                                                                        }
                                                                    }

                                                                    try {
                                                                        v7_1.offset = 0;
                                                                        v7_1.flags = v15_2.getSampleFlags();
                                                                        if(!v14_2.writeSampleData(v13_3, v10_4, v7_1, false)) {
                                                                            goto label_560;
                                                                        }

                                                                        goto label_553;
                                                                    }
                                                                    catch(Throwable v0) {
                                                                    }
                                                                    catch(Exception v0_1) {
                                                                    label_604:
                                                                        v5_1 = v42;
                                                                        goto label_1007;
                                                                    label_553:
                                                                        v54 = v5_8;
                                                                        v5_1 = v42;
                                                                        v2_6 = this;
                                                                        v3_3 = arg68;
                                                                        v32 = 0;
                                                                        try {
                                                                            v2_6.didWriteData(v3_3, v5_1, false, false);
                                                                            goto label_565;
                                                                        label_560:
                                                                            v54 = v5_8;
                                                                            v5_1 = v42;
                                                                            v2_6 = this;
                                                                            v3_3 = arg68;
                                                                            v32 = 0;
                                                                        label_565:
                                                                            v55 = v9_2;
                                                                            goto label_578;
                                                                        label_567:
                                                                            v54 = v5_8;
                                                                            v5_1 = v42;
                                                                            v2_6 = this;
                                                                            v3_3 = arg68;
                                                                            v32 = 0;
                                                                            v55 = v9_2;
                                                                            if(v1_2 == -1) {
                                                                                v9_2 = v12_3;
                                                                                v1_2 = 1;
                                                                            }
                                                                            else {
                                                                            label_578:
                                                                                v9_2 = v12_3;
                                                                                v1_2 = 0;
                                                                            }

                                                                        label_580:
                                                                            if(v1_2 == 0) {
                                                                                goto label_600;
                                                                            }

                                                                            v57 = v9_2;
                                                                            v56 = v10_4;
                                                                            v24 = v11_5.dequeueInputBuffer(2500);
                                                                            if(v24 < 0) {
                                                                                goto label_617;
                                                                            }

                                                                            v11_5.queueInputBuffer(v24, 0, 0, 0, 4);
                                                                            v57 = 1;
                                                                            goto label_617;
                                                                        }
                                                                        catch(Exception v0_1) {
                                                                            goto label_599;
                                                                        }
                                                                        catch(Throwable v0) {
                                                                            goto label_596;
                                                                        }

                                                                    label_600:
                                                                        v57 = v9_2;
                                                                        v56 = v10_4;
                                                                        goto label_617;
                                                                    label_608:
                                                                        v53 = v3;
                                                                        v54 = v5_8;
                                                                        v55 = v9_2;
                                                                        v56 = v10_4;
                                                                        v5_1 = v42;
                                                                        v2_6 = this;
                                                                        v3_3 = arg68;
                                                                        v32 = 0;
                                                                        v57 = v12_3;
                                                                    label_617:
                                                                        v9_2 = v19 ^ 1;
                                                                        v10 = v30_1;
                                                                        v12_3 = v57;
                                                                        v1_2 = 1;
                                                                        while(true) {
                                                                            if(v9_2 == 0) {
                                                                                if(v1_2 != 0) {
                                                                                }
                                                                                else {
                                                                                    v42 = v5_1;
                                                                                    v30_1 = v10;
                                                                                    v2 = v51;
                                                                                    v1_2 = v52;
                                                                                    v3 = v53;
                                                                                    v5_8 = v54;
                                                                                    v9_2 = v55;
                                                                                    v10_4 = v56;
                                                                                    goto label_473;
                                                                                }
                                                                            }

                                                                            try {
                                                                                this.checkConversionCanceled();
                                                                                v60 = v1_2;
                                                                                v59 = v12_3;
                                                                                v58 = v13_3;
                                                                                v1_2 = v4_3.dequeueOutputBuffer(v7_1, 2500);
                                                                                if(v1_2 == -1) {
                                                                                }
                                                                                else {
                                                                                    goto label_648;
                                                                                }
                                                                            }
                                                                            catch(Exception v0_1) {
                                                                                goto label_997;
                                                                            }
                                                                            catch(Throwable v0) {
                                                                                goto label_1086;
                                                                            }

                                                                            int v61 = v9_2;
                                                                            v12_3 = v51;
                                                                            v3 = v53;
                                                                            v2 = -1;
                                                                            v60 = 0;
                                                                            goto label_760;
                                                                        label_648:
                                                                            if(v1_2 == -3) {
                                                                                try {
                                                                                    if(Build$VERSION.SDK_INT < 21) {
                                                                                        v31 = v4_3.getOutputBuffers();
                                                                                        goto label_654;
                                                                                    label_659:
                                                                                        if(v1_2 == -2) {
                                                                                            MediaFormat v12_4 = v4_3.getOutputFormat();
                                                                                            if(v10 == -5) {
                                                                                                v10 = v14_2.addTrack(v12_4, false);
                                                                                            }
                                                                                            else {
                                                                                            }

                                                                                            goto label_654;
                                                                                        }
                                                                                        else {
                                                                                            goto label_667;
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                    label_654:
                                                                                        v61 = v9_2;
                                                                                        v12_3 = v51;
                                                                                        v3 = v53;
                                                                                    }
                                                                                }
                                                                                catch(Exception v0_1) {
                                                                                    goto label_599;
                                                                                }
                                                                                catch(Throwable v0) {
                                                                                    goto label_596;
                                                                                }
                                                                            }
                                                                            else {
                                                                                goto label_659;
                                                                            label_667:
                                                                                if(v1_2 >= 0) {
                                                                                    try {
                                                                                        if(Build$VERSION.SDK_INT < 21) {
                                                                                        }
                                                                                        else {
                                                                                            goto label_673;
                                                                                        }
                                                                                    }
                                                                                    catch(Exception v0_1) {
                                                                                        goto label_997;
                                                                                    }
                                                                                    catch(Throwable v0) {
                                                                                        goto label_1086;
                                                                                    }

                                                                                    try {
                                                                                        v12_5 = v31[v1_2];
                                                                                        goto label_674;
                                                                                    }
                                                                                    catch(Exception v0_1) {
                                                                                        goto label_599;
                                                                                    }
                                                                                    catch(Throwable v0) {
                                                                                        goto label_596;
                                                                                    }

                                                                                    try {
                                                                                    label_673:
                                                                                        v12_5 = v4_3.getOutputBuffer(v1_2);
                                                                                    label_674:
                                                                                        if(v12_5 == null) {
                                                                                            goto label_970;
                                                                                        }

                                                                                        v61 = v9_2;
                                                                                        if(v7_1.size > 1) {
                                                                                        }
                                                                                        else {
                                                                                            goto label_748;
                                                                                        }
                                                                                    }
                                                                                    catch(Exception v0_1) {
                                                                                        goto label_997;
                                                                                    }
                                                                                    catch(Throwable v0) {
                                                                                        goto label_1086;
                                                                                    }

                                                                                    try {
                                                                                        if((v7_1.flags & 2) == 0) {
                                                                                        }
                                                                                        else {
                                                                                            goto label_687;
                                                                                        }
                                                                                    }
                                                                                    catch(Exception v0_1) {
                                                                                        goto label_599;
                                                                                    }
                                                                                    catch(Throwable v0) {
                                                                                        goto label_1086;
                                                                                    }

                                                                                    try {
                                                                                        if(v14_2.writeSampleData(v10, v12_5, v7_1, true)) {
                                                                                            v2_6.didWriteData(v3_3, v5_1, false, false);
                                                                                        }
                                                                                        else {
                                                                                        }

                                                                                        goto label_748;
                                                                                    }
                                                                                    catch(Exception v0_1) {
                                                                                        goto label_599;
                                                                                    }
                                                                                    catch(Throwable v0) {
                                                                                        goto label_596;
                                                                                    }

                                                                                label_687:
                                                                                    if(v10 == -5) {
                                                                                        try {
                                                                                            byte[] v10_5 = new byte[v7_1.size];
                                                                                            v12_5.limit(v7_1.offset + v7_1.size);
                                                                                            v12_5.position(v7_1.offset);
                                                                                            v12_5.get(v10_5);
                                                                                            v12_3 = 1;
                                                                                            v9_2 = v7_1.size - 1;
                                                                                            while(true) {
                                                                                                if(v9_2 < 0) {
                                                                                                    break;
                                                                                                }
                                                                                                else if(v9_2 > 3) {
                                                                                                    if(v10_5[v9_2] == v12_3 && v10_5[v9_2 - 1] == 0 && v10_5[v9_2 - 2] == 0) {
                                                                                                        v12_3 = v9_2 - 3;
                                                                                                        if(v10_5[v12_3] == 0) {
                                                                                                            v9_3 = ByteBuffer.allocate(v12_3);
                                                                                                            v13_4 = ByteBuffer.allocate(v7_1.size - v12_3);
                                                                                                            v9_3.put(v10_5, 0, v12_3).position(0);
                                                                                                            v13_4.put(v10_5, v12_3, v7_1.size - v12_3).position(0);
                                                                                                            goto label_734;
                                                                                                        }
                                                                                                    }

                                                                                                    --v9_2;
                                                                                                    v12_3 = 1;
                                                                                                    continue;
                                                                                                }
                                                                                                else {
                                                                                                    break;
                                                                                                }
                                                                                            }

                                                                                            v9_3 = null;
                                                                                            v13_4 = null;
                                                                                        label_734:
                                                                                            v12_3 = v51;
                                                                                            v3 = v53;
                                                                                            MediaFormat v2_7 = MediaFormat.createVideoFormat("video/avc", v3, v12_3);
                                                                                            if(v9_3 != null && v13_4 != null) {
                                                                                                v2_7.setByteBuffer("csd-0", v9_3);
                                                                                                v2_7.setByteBuffer("csd-1", v13_4);
                                                                                            }

                                                                                            v10 = v14_2.addTrack(v2_7, false);
                                                                                        }
                                                                                        catch(Exception v0_1) {
                                                                                            goto label_599;
                                                                                        }
                                                                                        catch(Throwable v0) {
                                                                                            goto label_1086;
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                    label_748:
                                                                                        v12_3 = v51;
                                                                                        v3 = v53;
                                                                                    }

                                                                                    try {
                                                                                        if((v7_1.flags & 4) != 0) {
                                                                                            v2_8 = false;
                                                                                            v52 = 1;
                                                                                        }
                                                                                        else {
                                                                                            v2_8 = false;
                                                                                            v52 = 0;
                                                                                        }

                                                                                        v4_3.releaseOutputBuffer(v1_2, v2_8);
                                                                                    }
                                                                                    catch(Exception v0_1) {
                                                                                        goto label_997;
                                                                                    }
                                                                                    catch(Throwable v0) {
                                                                                        goto label_1086;
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    goto label_983;
                                                                                }
                                                                            }

                                                                            v2_6 = null;
                                                                            try {
                                                                            label_760:
                                                                                if(v1_2 != (((int)v2_6))) {
                                                                                    v53 = v3;
                                                                                    v51 = v12_3;
                                                                                    v13_3 = v58;
                                                                                    v12_3 = v59;
                                                                                    v1_2 = v60;
                                                                                    v9_2 = v61;
                                                                                    goto label_767;
                                                                                }

                                                                                if(v19 == 0) {
                                                                                    v62 = v3;
                                                                                    v1_2 = v11_5.dequeueOutputBuffer(v7_1, 2500);
                                                                                    if(v1_2 == -1) {
                                                                                        goto label_776;
                                                                                    }
                                                                                    else {
                                                                                        goto label_782;
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    goto label_954;
                                                                                }

                                                                                goto label_955;
                                                                            }
                                                                            catch(Exception v0_1) {
                                                                                goto label_997;
                                                                            }
                                                                            catch(Throwable v0) {
                                                                                goto label_1086;
                                                                            }

                                                                        label_776:
                                                                            int v63 = v10;
                                                                            long v64 = v46;
                                                                            v3_2 = v49;
                                                                            v2_3 = v50;
                                                                            goto label_780;
                                                                        label_782:
                                                                            if(v1_2 == -3) {
                                                                            }
                                                                            else if(v1_2 == -2) {
                                                                                try {
                                                                                    MediaFormat v1_7 = v11_5.getOutputFormat();
                                                                                    if(BuildVars.LOGS_ENABLED) {
                                                                                        FileLog.d("newFormat = " + v1_7);
                                                                                    }

                                                                                    goto label_955;
                                                                                }
                                                                                catch(Exception v0_1) {
                                                                                    goto label_599;
                                                                                }
                                                                                catch(Throwable v0) {
                                                                                    goto label_1086;
                                                                                }
                                                                            }
                                                                            else if(v1_2 >= 0) {
                                                                                try {
                                                                                    if(Build$VERSION.SDK_INT >= 18) {
                                                                                    }
                                                                                    else {
                                                                                        goto label_809;
                                                                                    }
                                                                                }
                                                                                catch(Exception v0_1) {
                                                                                    goto label_997;
                                                                                }
                                                                                catch(Throwable v0) {
                                                                                    goto label_1086;
                                                                                }

                                                                                try {
                                                                                    if(v7_1.size != 0) {
                                                                                    }
                                                                                    else {
                                                                                        goto label_806;
                                                                                    }
                                                                                }
                                                                                catch(Exception v0_1) {
                                                                                    goto label_599;
                                                                                }
                                                                                catch(Throwable v0) {
                                                                                    goto label_1086;
                                                                                }

                                                                                boolean v3_5 = true;
                                                                                goto label_807;
                                                                            label_806:
                                                                                v3_5 = false;
                                                                            label_807:
                                                                                v9_1 = v3_5;
                                                                                goto label_818;
                                                                                try {
                                                                                label_809:
                                                                                    if(v7_1.size == 0) {
                                                                                    }
                                                                                    else {
                                                                                        goto label_816;
                                                                                    }
                                                                                }
                                                                                catch(Exception v0_1) {
                                                                                    goto label_997;
                                                                                }
                                                                                catch(Throwable v0) {
                                                                                    goto label_1086;
                                                                                }

                                                                                try {
                                                                                    if(v7_1.presentationTimeUs != v32) {
                                                                                        goto label_816;
                                                                                    }
                                                                                    else {
                                                                                        v2_8 = false;
                                                                                        goto label_817;
                                                                                    label_816:
                                                                                        v2_8 = true;
                                                                                    }

                                                                                label_817:
                                                                                    v9_1 = v2_8;
                                                                                label_818:
                                                                                    if(v20 > v32 && v7_1.presentationTimeUs >= v20) {
                                                                                        v7_1.flags |= 4;
                                                                                        v9_1 = false;
                                                                                        v19 = 1;
                                                                                        v59 = 1;
                                                                                    }

                                                                                    if(v46 <= v32) {
                                                                                        goto label_858;
                                                                                    }
                                                                                    else if(v38 != -1) {
                                                                                        goto label_858;
                                                                                    }
                                                                                    else if(v7_1.presentationTimeUs < v46) {
                                                                                        if(BuildVars.LOGS_ENABLED) {
                                                                                            StringBuilder v2_9 = new StringBuilder();
                                                                                            v2_9.append("drop frame startTime = ");
                                                                                            v63 = v10;
                                                                                            v2_9.append(v46);
                                                                                            v2_9.append(" present time = ");
                                                                                            v64 = v46;
                                                                                            v2_9.append(v7_1.presentationTimeUs);
                                                                                            FileLog.d(v2_9.toString());
                                                                                        }
                                                                                        else {
                                                                                            v63 = v10;
                                                                                            v64 = v46;
                                                                                        }

                                                                                        v9_1 = false;
                                                                                    }
                                                                                    else {
                                                                                        v63 = v10;
                                                                                        v64 = v46;
                                                                                        v38 = v7_1.presentationTimeUs;
                                                                                    }

                                                                                    goto label_860;
                                                                                }
                                                                                catch(Exception v0_1) {
                                                                                    goto label_599;
                                                                                }
                                                                                catch(Throwable v0) {
                                                                                    goto label_1086;
                                                                                }

                                                                            label_858:
                                                                                v63 = v10;
                                                                                v64 = v46;
                                                                                try {
                                                                                label_860:
                                                                                    v11_5.releaseOutputBuffer(v1_2, v9_1);
                                                                                    if(!v9_1) {
                                                                                        goto label_918;
                                                                                    }
                                                                                }
                                                                                catch(Exception v0_1) {
                                                                                    goto label_997;
                                                                                }
                                                                                catch(Throwable v0) {
                                                                                    goto label_1086;
                                                                                }

                                                                                try {
                                                                                    v50.awaitNewImage();
                                                                                    v1_2 = 0;
                                                                                    goto label_869;
                                                                                }
                                                                                catch(Throwable v0) {
                                                                                }
                                                                                catch(Exception v0_1) {
                                                                                    v1_1 = v0_1;
                                                                                    try {
                                                                                        FileLog.e(((Throwable)v1_1));
                                                                                        v1_2 = 1;
                                                                                    label_869:
                                                                                        if(v1_2 != 0) {
                                                                                            goto label_918;
                                                                                        }

                                                                                        if(Build$VERSION.SDK_INT < 18) {
                                                                                            goto label_888;
                                                                                        }
                                                                                    }
                                                                                    catch(Exception v0_1) {
                                                                                        goto label_997;
                                                                                    }
                                                                                    catch(Throwable v0) {
                                                                                        goto label_1086;
                                                                                    }

                                                                                    v2_3 = v50;
                                                                                    try {
                                                                                        v2_3.drawImage(false);
                                                                                        v9_4 = v7_1.presentationTimeUs * 1000;
                                                                                        v3_2 = v49;
                                                                                        goto label_880;
                                                                                    }
                                                                                    catch(Throwable v0) {
                                                                                    }
                                                                                    catch(Exception v0_1) {
                                                                                        try {
                                                                                            v3_2 = v49;
                                                                                            v1_1 = v0_1;
                                                                                            v50 = v2_3;
                                                                                            goto label_1044;
                                                                                        label_880:
                                                                                            v3_2.setPresentationTime(v9_4);
                                                                                            v3_2.swapBuffers();
                                                                                            goto label_920;
                                                                                        label_888:
                                                                                            v3_2 = v49;
                                                                                            v2_3 = v50;
                                                                                            v1_2 = v4_3.dequeueInputBuffer(2500);
                                                                                            if(v1_2 >= 0) {
                                                                                                v2_3.drawImage(true);
                                                                                                ByteBuffer v23 = v2_3.getFrame();
                                                                                                ByteBuffer v24_1 = v6_6[v1_2];
                                                                                                v24_1.clear();
                                                                                                Utilities.convertVideoFrame(v23, v24_1, v48, v62, v12_3, v45, v41);
                                                                                                v4_3.queueInputBuffer(v1_2, 0, v44, v7_1.presentationTimeUs, 0);
                                                                                                goto label_920;
                                                                                            }

                                                                                            if(!BuildVars.LOGS_ENABLED) {
                                                                                            }
                                                                                            else {
                                                                                                FileLog.d("input buffer not available");
                                                                                                goto label_920;
                                                                                            label_918:
                                                                                                v3_2 = v49;
                                                                                                v2_3 = v50;
                                                                                            }

                                                                                        label_920:
                                                                                            if((v7_1.flags & 4) == 0) {
                                                                                                goto label_959;
                                                                                            }

                                                                                            if(BuildVars.LOGS_ENABLED) {
                                                                                                FileLog.d("decoder stream end");
                                                                                            }

                                                                                            if(Build$VERSION.SDK_INT >= 18) {
                                                                                                v4_3.signalEndOfInputStream();
                                                                                                goto label_780;
                                                                                            }

                                                                                            v24 = v4_3.dequeueInputBuffer(2500);
                                                                                            if(v24 < 0) {
                                                                                                goto label_780;
                                                                                            }

                                                                                            v4_3.queueInputBuffer(v24, 0, 1, v7_1.presentationTimeUs, 4);
                                                                                        }
                                                                                        catch(Exception v0_1) {
                                                                                            goto label_995;
                                                                                        }
                                                                                        catch(Throwable v0) {
                                                                                            goto label_1086;
                                                                                        }

                                                                                    label_780:
                                                                                        v9_2 = 0;
                                                                                        goto label_960;
                                                                                        try {
                                                                                        label_943:
                                                                                            v3_2 = v49;
                                                                                            v2_3 = v50;
                                                                                            v7_2 = new StringBuilder();
                                                                                            v7_2.append("unexpected result from decoder.dequeueOutputBuffer: ");
                                                                                            v7_2.append(v1_2);
                                                                                            throw new RuntimeException(v7_2.toString());
                                                                                        label_954:
                                                                                            v62 = v3;
                                                                                        label_955:
                                                                                            v63 = v10;
                                                                                            v64 = v46;
                                                                                            v3_2 = v49;
                                                                                            v2_3 = v50;
                                                                                        label_959:
                                                                                            v9_2 = v61;
                                                                                        label_960:
                                                                                            v50 = v2_3;
                                                                                            v49 = v3_2;
                                                                                            v51 = v12_3;
                                                                                            v13_3 = v58;
                                                                                            v12_3 = v59;
                                                                                            v1_2 = v60;
                                                                                            v53 = v62;
                                                                                            v10 = v63;
                                                                                            v46 = v64;
                                                                                        }
                                                                                        catch(Exception v0_1) {
                                                                                            goto label_995;
                                                                                        }
                                                                                        catch(Throwable v0) {
                                                                                            goto label_1086;
                                                                                        }

                                                                                    label_767:
                                                                                        v2_6 = this;
                                                                                        v3_3 = arg68;
                                                                                        continue;
                                                                                    label_997:
                                                                                        goto label_1007;
                                                                                        try {
                                                                                        label_970:
                                                                                            v3_2 = v49;
                                                                                            v2_3 = v50;
                                                                                            v7_2 = new StringBuilder();
                                                                                            v7_2.append("encoderOutputBuffer ");
                                                                                            v7_2.append(v1_2);
                                                                                            v7_2.append(" was null");
                                                                                            throw new RuntimeException(v7_2.toString());
                                                                                        label_983:
                                                                                            v3_2 = v49;
                                                                                            v2_3 = v50;
                                                                                            v7_2 = new StringBuilder();
                                                                                            v7_2.append("unexpected result from encoder.dequeueOutputBuffer: ");
                                                                                            v7_2.append(v1_2);
                                                                                            throw new RuntimeException(v7_2.toString());
                                                                                        }
                                                                                        catch(Throwable v0) {
                                                                                        }
                                                                                        catch(Exception v0_1) {
                                                                                        label_995:
                                                                                            goto label_1013;
                                                                                        label_599:
                                                                                            goto label_1007;
                                                                                        label_596:
                                                                                            v2_1 = v0;
                                                                                            goto label_1158;
                                                                                        label_998:
                                                                                            v5_1 = v42;
                                                                                            v3_2 = v49;
                                                                                            v9_1 = false;
                                                                                            goto label_1047;
                                                                                        label_1013:
                                                                                            v1_1 = v0_1;
                                                                                            v50 = ((OutputSurface)v2_6);
                                                                                            v49 = v3_2;
                                                                                            goto label_1044;
                                                                                        label_460:
                                                                                            v1_1 = v0_1;
                                                                                            v5_1 = v42;
                                                                                            goto label_1044;
                                                                                        label_1004:
                                                                                            v5_1 = v42;
                                                                                        label_1007:
                                                                                            v1_1 = v0_1;
                                                                                            try {
                                                                                            label_1044:
                                                                                                FileLog.e(((Throwable)v1_1));
                                                                                                v3_2 = v49;
                                                                                                v9_1 = true;
                                                                                            label_1047:
                                                                                                v15_2.unselectTrack(v8_3);
                                                                                                if(v50 != null) {
                                                                                                    v50.release();
                                                                                                }

                                                                                                if(v3_2 != null) {
                                                                                                    v3_2.release();
                                                                                                }

                                                                                                if(v11_5 != null) {
                                                                                                    v11_5.stop();
                                                                                                    v11_5.release();
                                                                                                }

                                                                                                if(v4_3 != null) {
                                                                                                    v4_3.stop();
                                                                                                    v4_3.release();
                                                                                                }

                                                                                                this.checkConversionCanceled();
                                                                                                v37 = v9_1;
                                                                                                goto label_1065;
                                                                                            }
                                                                                            catch(Throwable v0) {
                                                                                            label_1086:
                                                                                                goto label_1092;
                                                                                            }
                                                                                            catch(Exception v0_1) {
                                                                                            label_1099:
                                                                                                v1_1 = v0_1;
                                                                                            label_1100:
                                                                                                v6_2 = v15_2;
                                                                                                try {
                                                                                                label_1124:
                                                                                                    FileLog.e(((Throwable)v1_1));
                                                                                                    if(v6_2 == null) {
                                                                                                        goto label_1127;
                                                                                                    }
                                                                                                }
                                                                                                catch(Throwable v0) {
                                                                                                    v2_1 = v0;
                                                                                                    v15_2 = v6_2;
                                                                                                    goto label_1158;
                                                                                                }

                                                                                                v6_2.release();
                                                                                            label_1127:
                                                                                                if(v14_2 != null) {
                                                                                                    try {
                                                                                                        v14_2.finishMovie();
                                                                                                    }
                                                                                                    catch(Exception v0_1) {
                                                                                                        FileLog.e(v0_1);
                                                                                                    }
                                                                                                }

                                                                                                if(BuildVars.LOGS_ENABLED) {
                                                                                                    FileLog.d("time = " + (System.currentTimeMillis() - v16));
                                                                                                }

                                                                                                v9_1 = true;
                                                                                                goto label_1145;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            else {
                                                                                goto label_943;
                                                                            }

                                                                            goto label_955;
                                                                        }
                                                                    }
                                                                }
                                                                else {
                                                                    goto label_567;
                                                                }

                                                                goto label_578;
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
                    }
                }
            }

        label_1092:
            v2_1 = v0;
        label_1158:
            if(v15_2 != null) {
                v15_2.release();
            }

            if(v14_2 != null) {
                try {
                    v14_2.finishMovie();
                }
                catch(Exception v0_1) {
                    FileLog.e(v0_1);
                }
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("time = " + (System.currentTimeMillis() - v16));
            }

            throw v2_1;
        label_1063:
            v5_1 = v13_1;
            v37 = false;
        label_1065:
            v9_1 = v37;
        label_1066:
            v15_2.release();
            if(v14_2 != null) {
                try {
                    v14_2.finishMovie();
                }
                catch(Exception v0_1) {
                    FileLog.e(v0_1);
                }
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("time = " + (System.currentTimeMillis() - v16));
            }

        label_1145:
            v36.edit().putBoolean("isPreviousOk", true).commit();
            this.didWriteData(arg68, v5_1, true, v9_1);
            return 1;
        }

        v15_1.edit().putBoolean("isPreviousOk", true).commit();
        v12.didWriteData(v13, v9, true, true);
        return 0;
    }

    public static String copyFileToCache(Uri arg7, String arg8) {
        InputStream v0_1;
        FileOutputStream v1_2;
        InputStream v8_1;
        FileOutputStream v8_2;
        InputStream v7_2;
        File v3;
        int v1_1;
        String v1;
        String v0 = null;
        try {
            v1 = FileLoader.fixFileName(MediaController.getFileName(arg7));
            if(v1 == null) {
                v1_1 = SharedConfig.getLastLocalId();
                SharedConfig.saveConfig();
                v1 = String.format(Locale.US, "%d.%s", Integer.valueOf(v1_1), arg8);
            }

            File v8 = new File(FileLoader.getDirectory(4), "sharing/");
            v8.mkdirs();
            v3 = new File(v8, v1);
            if(AndroidUtilities.isInternalUri(Uri.fromFile(v3))) {
                return v0;
            }

            v7_2 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(arg7);
        }
        catch(Throwable v7) {
            v8_2 = ((FileOutputStream)v0);
            goto label_95;
        }
        catch(Exception v7_1) {
            v8_1 = ((InputStream)v0);
            v1_2 = ((FileOutputStream)v8_1);
            goto label_80;
        }

        try {
            v8_2 = new FileOutputStream(v3);
            v1_1 = 20480;
        }
        catch(Throwable v8_3) {
            String v6_1 = v0;
            v0_1 = v7_2;
            v7 = v8_3;
            v8_2 = ((FileOutputStream)v6_1);
            goto label_95;
        }
        catch(Exception v8_4) {
            v1_2 = ((FileOutputStream)v0);
            Exception v6 = v8_4;
            v8_1 = v7_2;
            v7_1 = v6;
            goto label_80;
        }

        try {
            byte[] v1_4 = new byte[v1_1];
            while(true) {
                int v4 = v7_2.read(v1_4);
                if(v4 == -1) {
                    break;
                }

                v8_2.write(v1_4, 0, v4);
            }

            v1 = v3.getAbsolutePath();
            if(v7_2 == null) {
                goto label_46;
            }
        }
        catch(Throwable v0_2) {
            goto label_52;
        }
        catch(Exception v1_3) {
            goto label_57;
        }

        try {
            v7_2.close();
        }
        catch(Exception v7_1) {
            FileLog.e(((Throwable)v7_1));
        }

        try {
        label_46:
            v8_2.close();
        }
        catch(Exception v7_1) {
            FileLog.e(((Throwable)v7_1));
        }

        return v1;
    label_57:
        FileOutputStream v6_2 = v8_2;
        v8_1 = v7_2;
        v7_1 = v1_3;
        v1_2 = v6_2;
        try {
        label_80:
            FileLog.e(((Throwable)v7_1));
            if(v8_1 == null) {
                goto label_86;
            }
        }
        catch(Throwable v7) {
            v0_1 = v8_1;
            v8_2 = v1_2;
            goto label_95;
        }

        try {
            v8_1.close();
        }
        catch(Exception v7_1) {
            FileLog.e(((Throwable)v7_1));
        }

    label_86:
        if(v1_2 != null) {
            try {
                v1_2.close();
            }
            catch(Exception v7_1) {
                FileLog.e(((Throwable)v7_1));
            }
        }

        return v0;
    label_52:
        Throwable v6_3 = v0_2;
        v0_1 = v7_2;
        v7 = v6_3;
    label_95:
        if(v0_1 != null) {
            try {
                v0_1.close();
            }
            catch(Exception v0_3) {
                FileLog.e(((Throwable)v0_3));
            }
        }

        if(v8_2 != null) {
            try {
                v8_2.close();
            }
            catch(Exception v8_4) {
                FileLog.e(((Throwable)v8_4));
            }
        }

        throw v7;
    }

    public void didReceivedNotification(int arg6, int arg7, Object[] arg8) {
        long v6_1;
        Object v6;
        Object v7;
        int v2 = 0;
        if(arg6 == NotificationCenter.FileDidLoaded || arg6 == NotificationCenter.httpFileDidLoaded) {
            v6 = arg8[0];
            if(!this.downloadingCurrentMessage) {
                return;
            }

            if(this.playingMessageObject == null) {
                return;
            }

            if(this.playingMessageObject.currentAccount != arg7) {
                return;
            }

            if(!FileLoader.getAttachFileName(this.playingMessageObject.getDocument()).equals(v6)) {
                return;
            }

            this.playMusicAgain = true;
            this.playMessage(this.playingMessageObject);
        }
        else if(arg6 == NotificationCenter.messagesDeleted) {
            arg6 = arg8[1].intValue();
            v7 = arg8[0];
            if(this.playingMessageObject != null && arg6 == this.playingMessageObject.messageOwner.to_id.channel_id && (((ArrayList)v7).contains(Integer.valueOf(this.playingMessageObject.getId())))) {
                this.cleanupPlayer(true, true);
            }

            if(this.voiceMessagesPlaylist == null) {
                return;
            }

            if(this.voiceMessagesPlaylist.isEmpty()) {
                return;
            }

            if(arg6 != this.voiceMessagesPlaylist.get(0).messageOwner.to_id.channel_id) {
                return;
            }

            while(v2 < ((ArrayList)v7).size()) {
                v6 = ((ArrayList)v7).get(v2);
                Object v8 = this.voiceMessagesPlaylistMap.get(((Integer)v6).intValue());
                this.voiceMessagesPlaylistMap.remove(((Integer)v6).intValue());
                if(v8 != null) {
                    this.voiceMessagesPlaylist.remove(v8);
                }

                ++v2;
            }
        }
        else {
            if(arg6 == NotificationCenter.removeAllMessagesFromDialog) {
                v6_1 = arg8[0].longValue();
                if(this.playingMessageObject == null) {
                    return;
                }

                if(this.playingMessageObject.getDialogId() != v6_1) {
                    return;
                }

                this.cleanupPlayer(false, true);
                return;
            }

            if(arg6 == NotificationCenter.musicDidLoaded) {
                v6_1 = arg8[0].longValue();
                if(this.playingMessageObject == null) {
                    return;
                }

                if(!this.playingMessageObject.isMusic()) {
                    return;
                }

                if(this.playingMessageObject.getDialogId() != v6_1) {
                    return;
                }

                v6 = arg8[1];
                this.playlist.addAll(0, ((Collection)v6));
                if(SharedConfig.shuffleMusic) {
                    this.buildShuffledPlayList();
                    this.currentPlaylistNum = 0;
                    return;
                }

                this.currentPlaylistNum += ((ArrayList)v6).size();
                return;
            }

            if(arg6 == NotificationCenter.didReceivedNewMessages) {
                if(this.voiceMessagesPlaylist == null) {
                    return;
                }

                if(this.voiceMessagesPlaylist.isEmpty()) {
                    return;
                }

                if(arg8[0].longValue() != this.voiceMessagesPlaylist.get(0).getDialogId()) {
                    return;
                }

                v6 = arg8[1];
                while(true) {
                    if(v2 >= ((ArrayList)v6).size()) {
                        return;
                    }

                    v7 = ((ArrayList)v6).get(v2);
                    if((((MessageObject)v7).isVoice()) || (((MessageObject)v7).isRoundVideo())) {
                        if(this.voiceMessagesPlaylistUnread) {
                            if(!((MessageObject)v7).isContentUnread()) {
                            }
                            else if(!((MessageObject)v7).isOut()) {
                                goto label_113;
                            }

                            goto label_118;
                        }

                    label_113:
                        this.voiceMessagesPlaylist.add(v7);
                        this.voiceMessagesPlaylistMap.put(((MessageObject)v7).getId(), v7);
                    }

                label_118:
                    ++v2;
                }
            }

            if(arg6 != NotificationCenter.playerDidStartPlaying) {
                return;
            }

            if(MediaController.getInstance().isCurrentPlayer(arg8[0])) {
                return;
            }

            MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
        }
    }

    private void didWriteData(MessageObject arg9, File arg10, boolean arg11, boolean arg12) {
        boolean v6 = this.videoConvertFirstWrite;
        if(v6) {
            this.videoConvertFirstWrite = false;
        }

        AndroidUtilities.runOnUIThread(new Runnable(arg12, arg11, arg9, arg10, v6) {
            public void run() {
                Object[] v5;
                int v4;
                NotificationCenter v0_1;
                if((this.val$error) || (this.val$last)) {
                    Object v0 = MediaController.this.videoConvertSync;
                    __monitor_enter(v0);
                    try {
                        MediaController.this.cancelCurrentVideoConversion = false;
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        try {
                        label_73:
                            __monitor_exit(v0);
                        }
                        catch(Throwable v1) {
                            goto label_73;
                        }

                        throw v1;
                    }

                    MediaController.this.videoConvertQueue.remove(this.val$messageObject);
                    MediaController.this.startVideoConvertFromQueue();
                }

                int v3 = 2;
                if(this.val$error) {
                    v0_1 = NotificationCenter.getInstance(this.val$messageObject.currentAccount);
                    v4 = NotificationCenter.FilePreparingFailed;
                    Object[] v3_1 = new Object[v3];
                    v3_1[0] = this.val$messageObject;
                    v3_1[1] = this.val$file.toString();
                    v0_1.postNotificationName(v4, v3_1);
                }
                else {
                    if(this.val$firstWrite) {
                        v0_1 = NotificationCenter.getInstance(this.val$messageObject.currentAccount);
                        v4 = NotificationCenter.FilePreparingStarted;
                        v5 = new Object[v3];
                        v5[0] = this.val$messageObject;
                        v5[1] = this.val$file.toString();
                        v0_1.postNotificationName(v4, v5);
                    }

                    v0_1 = NotificationCenter.getInstance(this.val$messageObject.currentAccount);
                    v4 = NotificationCenter.FileNewChunkAvailable;
                    v5 = new Object[4];
                    v5[0] = this.val$messageObject;
                    v5[1] = this.val$file.toString();
                    v5[v3] = Long.valueOf(this.val$file.length());
                    int v1_1 = 3;
                    long v2 = this.val$last ? this.val$file.length() : 0;
                    v5[v1_1] = Long.valueOf(v2);
                    v0_1.postNotificationName(v4, v5);
                }
            }
        });
    }

    public boolean findMessageInPlaylistAndPlay(MessageObject arg3) {
        int v0 = this.playlist.indexOf(arg3);
        if(v0 == -1) {
            return this.playMessage(arg3);
        }

        this.playMessageAtIndex(v0);
        return 1;
    }

    private int findTrack(MediaExtractor arg5, boolean arg6) {
        int v0 = arg5.getTrackCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            String v2 = arg5.getTrackFormat(v1).getString("mime");
            if(arg6) {
                if(v2.startsWith("audio/")) {
                    return v1;
                }
            }
            else if(v2.startsWith("video/")) {
                return v1;
            }
        }

        return -5;
    }

    public void generateWaveform(MessageObject arg4) {
        String v0_1 = arg4.getId() + "_" + arg4.getDialogId();
        String v1 = FileLoader.getPathToMessage(arg4.messageOwner).getAbsolutePath();
        if(this.generatingWaveform.containsKey(v0_1)) {
            return;
        }

        this.generatingWaveform.put(v0_1, arg4);
        Utilities.globalQueue.postRunnable(new Runnable(v1, v0_1) {
            public void run() {
                AndroidUtilities.runOnUIThread(new Runnable(MediaController.this.getWaveform(this.val$path)) {
                    public void run() {
                        Object v0 = this.this$1.this$0.generatingWaveform.remove(this.this$1.val$id);
                        if(v0 == null) {
                            return;
                        }

                        if(this.val$waveform != null) {
                            int v2 = 0;
                            while(v2 < ((MessageObject)v0).getDocument().attributes.size()) {
                                Object v3 = ((MessageObject)v0).getDocument().attributes.get(v2);
                                if((v3 instanceof TL_documentAttributeAudio)) {
                                    ((DocumentAttribute)v3).waveform = this.val$waveform;
                                    ((DocumentAttribute)v3).flags |= 4;
                                }
                                else {
                                    ++v2;
                                    continue;
                                }

                                break;
                            }

                            TL_messages_messages v4 = new TL_messages_messages();
                            v4.messages.add(((MessageObject)v0).messageOwner);
                            MessagesStorage.getInstance(((MessageObject)v0).currentAccount).putMessages(((messages_Messages)v4), ((MessageObject)v0).getDialogId(), -1, 0, false);
                            ArrayList v2_1 = new ArrayList();
                            v2_1.add(v0);
                            NotificationCenter.getInstance(((MessageObject)v0).currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects, new Object[]{Long.valueOf(((MessageObject)v0).getDialogId()), v2_1});
                        }
                    }
                });
            }
        });
    }

    public AudioInfo getAudioInfo() {
        return this.audioInfo;
    }

    public static String getFileName(Uri arg9) {
        String v2_1;
        Cursor v0;
        Cursor v2 = null;
        if(!arg9.getScheme().equals("content")) {
            goto label_40;
        }

        try {
            v0 = ApplicationLoader.applicationContext.getContentResolver().query(arg9, new String[]{"_display_name"}, null, null, null);
        }
        catch(Throwable v9) {
            goto label_37;
        }
        catch(Exception v3) {
            v0 = v2;
            goto label_32;
        }

        try {
            if(v0.moveToFirst()) {
                v2_1 = v0.getString(v0.getColumnIndex("_display_name"));
            }

            goto label_23;
        }
        catch(Throwable v9) {
        label_36:
            v2 = v0;
        }
        catch(Exception v3) {
            try {
            label_32:
                FileLog.e(((Throwable)v3));
                if(v0 != null) {
                }
                else {
                    goto label_40;
                }
            }
            catch(Throwable v9) {
                goto label_36;
            }

            goto label_24;
        }

    label_37:
        if(v2 != null) {
            v2.close();
        }

        throw v9;
    label_23:
        if(v0 != null) {
        label_24:
            v0.close();
        }

    label_40:
        if(v2_1 == null) {
            v2_1 = arg9.getPath();
            int v9_1 = v2_1.lastIndexOf(47);
            if(v9_1 != -1) {
                v2_1 = v2_1.substring(v9_1 + 1);
            }
        }

        return v2_1;
    }

    public static MediaController getInstance() {
        MediaController v0 = MediaController.Instance;
        if(v0 == null) {
            Class v1 = MediaController.class;
            __monitor_enter(v1);
            try {
                v0 = MediaController.Instance;
                if(v0 == null) {
                    v0 = new MediaController();
                    MediaController.Instance = v0;
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

    public float getPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    public MessageObject getPlayingMessageObject() {
        return this.playingMessageObject;
    }

    public int getPlayingMessageObjectNum() {
        return this.currentPlaylistNum;
    }

    public ArrayList getPlaylist() {
        return this.playlist;
    }

    public native byte[] getWaveform(String arg1) {
    }

    public native byte[] getWaveform2(short[] arg1, int arg2) {
    }

    public boolean isCurrentPlayer(VideoPlayer arg2) {
        boolean v2 = this.videoPlayer == arg2 || this.audioPlayer == arg2 ? true : false;
        return v2;
    }

    public boolean isDownloadingCurrentMessage() {
        return this.downloadingCurrentMessage;
    }

    public static boolean isGif(Uri arg5) {
        Exception v1_1;
        int v1;
        InputStream v5_1;
        try {
            v5_1 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(arg5);
            v1 = 3;
        }
        catch(Throwable v0) {
            v5_1 = ((InputStream)v1);
            goto label_41;
        }
        catch(Exception v5) {
            InputStream v4 = ((InputStream)v1);
            v1_1 = v5;
            v5_1 = v4;
            goto label_33;
        }

        try {
            byte[] v2 = new byte[v1];
            if(v5_1.read(v2, 0, v1) == v1) {
                if(!new String(v2).equalsIgnoreCase("gif")) {
                    goto label_21;
                }

                goto label_14;
            }

            goto label_21;
        }
        catch(Throwable v0) {
        }
        catch(Exception v1_1) {
            try {
            label_33:
                FileLog.e(((Throwable)v1_1));
                if(v5_1 != null) {
                }
                else {
                    return 0;
                }
            }
            catch(Throwable v0) {
                goto label_41;
            }

            try {
                v5_1.close();
                return 0;
            }
            catch(Exception v5) {
                goto label_38;
            }
        }

    label_41:
        if(v5_1 != null) {
            try {
                v5_1.close();
            }
            catch(Exception v5) {
                FileLog.e(((Throwable)v5));
            }

            goto label_46;
        }
        else {
        label_46:
            throw v0;
        label_14:
            if(v5_1 != null) {
                try {
                    v5_1.close();
                }
                catch(Exception v5) {
                    FileLog.e(((Throwable)v5));
                }

                return 1;
            }
            else {
                return 1;
            }
        }

    label_21:
        if(v5_1 != null) {
            try {
                v5_1.close();
            }
            catch(Exception v5) {
            label_38:
                FileLog.e(((Throwable)v5));
            }
        }

        return 0;
    }

    public boolean isMessagePaused() {
        boolean v0 = (this.isPaused) || (this.downloadingCurrentMessage) ? true : false;
        return v0;
    }

    private boolean isNearToSensor(float arg2) {
        boolean v2 = arg2 >= 5f || arg2 == this.proximitySensor.getMaximumRange() ? false : true;
        return v2;
    }

    public static native int isOpusFile(String arg0) {
    }

    public boolean isPlayingMessage(MessageObject arg7) {
        if((this.audioPlayer != null || this.videoPlayer != null) && arg7 != null) {
            if(this.playingMessageObject == null) {
            }
            else {
                if(this.playingMessageObject.eventId != 0 && this.playingMessageObject.eventId == arg7.eventId) {
                    return this.downloadingCurrentMessage ^ 1;
                }

                if(!this.isSamePlayingMessage(arg7)) {
                    return 0;
                }

                return this.downloadingCurrentMessage ^ 1;
            }
        }

        return 0;
    }

    private static boolean isRecognizedFormat(int arg1) {
        if(arg1 != 39 && arg1 != 2130706688) {
            switch(arg1) {
                case 19: 
                case 20: 
                case 21: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }

        return 1;
    }

    protected boolean isRecordingAudio() {
        boolean v0 = this.recordStartRunnable != null || this.recordingAudio != null ? true : false;
        return v0;
    }

    public boolean isRecordingOrListeningByProximity() {
        boolean v0;
        if(this.proximityTouched) {
            if(!this.isRecordingAudio()) {
                if(this.playingMessageObject == null) {
                    goto label_14;
                }
                else if(!this.playingMessageObject.isVoice()) {
                    if(this.playingMessageObject.isRoundVideo()) {
                        goto label_12;
                    }

                    goto label_14;
                }
            }

        label_12:
            v0 = true;
        }
        else {
        label_14:
            v0 = false;
        }

        return v0;
    }

    public boolean isRoundVideoDrawingReady() {
        boolean v0 = this.currentAspectRatioFrameLayout == null || !this.currentAspectRatioFrameLayout.isDrawingReady() ? false : true;
        return v0;
    }

    private boolean isSamePlayingMessage(MessageObject arg8) {
        boolean v1 = true;
        if(this.playingMessageObject == null || this.playingMessageObject.getDialogId() != arg8.getDialogId() || this.playingMessageObject.getId() != arg8.getId()) {
        label_26:
            v1 = false;
        }
        else {
            long v5 = 0;
            int v0 = this.playingMessageObject.eventId == v5 ? 1 : 0;
            int v8 = arg8.eventId == v5 ? 1 : 0;
            if(v0 != v8) {
                goto label_26;
            }
        }

        return v1;
    }

    public static boolean isWebp(Uri arg5) {
        Exception v1_1;
        int v1;
        InputStream v5_1;
        try {
            v5_1 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(arg5);
            v1 = 12;
        }
        catch(Throwable v0) {
            v5_1 = ((InputStream)v1);
            goto label_45;
        }
        catch(Exception v5) {
            InputStream v4 = ((InputStream)v1);
            v1_1 = v5;
            v5_1 = v4;
            goto label_37;
        }

        try {
            byte[] v2 = new byte[v1];
            if(v5_1.read(v2, 0, v1) == v1) {
                String v1_2 = new String(v2).toLowerCase();
                if(v1_2.startsWith("riff")) {
                    if(!v1_2.endsWith("webp")) {
                        goto label_25;
                    }

                    goto label_18;
                }
            }

            goto label_25;
        }
        catch(Throwable v0) {
        }
        catch(Exception v1_1) {
            try {
            label_37:
                FileLog.e(((Throwable)v1_1));
                if(v5_1 != null) {
                }
                else {
                    return 0;
                }
            }
            catch(Throwable v0) {
                goto label_45;
            }

            try {
                v5_1.close();
                return 0;
            }
            catch(Exception v5) {
                goto label_42;
            }
        }

    label_45:
        if(v5_1 != null) {
            try {
                v5_1.close();
            }
            catch(Exception v5) {
                FileLog.e(((Throwable)v5));
            }

            goto label_50;
        }
        else {
        label_50:
            throw v0;
        label_18:
            if(v5_1 != null) {
                try {
                    v5_1.close();
                }
                catch(Exception v5) {
                    FileLog.e(((Throwable)v5));
                }

                return 1;
            }
            else {
                return 1;
            }
        }

    label_25:
        if(v5_1 != null) {
            try {
                v5_1.close();
            }
            catch(Exception v5) {
            label_42:
                FileLog.e(((Throwable)v5));
            }
        }

        return 0;
    }

    public static void loadGalleryPhotosAlbums(int arg2) {
        Thread v0 = new Thread(new Runnable(arg2) {
            public void run() {
                Exception v2_2;
                AlbumEntry v5_3;
                String v6_3;
                int v36;
                int v35;
                PhotoEntry v15_1;
                long v19_1;
                long v22;
                String v14_1;
                String v13_1;
                int v21;
                int v8_1;
                Cursor v5_2;
                Cursor v34;
                AlbumEntry v12_2;
                AlbumEntry v11_2;
                int v33;
                AlbumEntry v6_2;
                int v32;
                int v30;
                PhotoEntry v0_4;
                int v29;
                long v24;
                String v8;
                Throwable v2_1;
                Cursor v6_1;
                Integer v19;
                int v6;
                int v15;
                int v14;
                int v13;
                int v12;
                int v0_3;
                Cursor v11;
                Cursor v31;
                Integer v18;
                AlbumEntry v17;
                AlbumEntry v16;
                String v7;
                org.telegram.messenger.MediaController$23 v1 = this;
                ArrayList v3 = new ArrayList();
                ArrayList v4 = new ArrayList();
                SparseArray v2 = new SparseArray();
                SparseArray v5 = new SparseArray();
                try {
                    v7 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/";
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                    v7 = null;
                }

                int v9 = 23;
                try {
                    if(Build$VERSION.SDK_INT >= v9) {
                        if(Build$VERSION.SDK_INT >= v9 && ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                            goto label_38;
                        }

                        v16 = null;
                        v17 = null;
                        v18 = null;
                        v31 = null;
                    }
                    else {
                    label_38:
                        v11 = MediaStore$Images$Media.query(ApplicationLoader.applicationContext.getContentResolver(), MediaStore$Images$Media.EXTERNAL_CONTENT_URI, MediaController.projectionPhotos, null, null, "datetaken DESC");
                        if(v11 == null) {
                            goto label_187;
                        }

                        goto label_47;
                    }

                    goto label_191;
                }
                catch(Throwable v0_2) {
                    goto label_203;
                }
                catch(Throwable v0_2) {
                    goto label_199;
                }

                try {
                label_47:
                    v0_3 = v11.getColumnIndex("_id");
                    v12 = v11.getColumnIndex("bucket_id");
                    v13 = v11.getColumnIndex("bucket_display_name");
                    v14 = v11.getColumnIndex("_data");
                    v15 = v11.getColumnIndex("datetaken");
                    v6 = v11.getColumnIndex("orientation");
                    v16 = null;
                    v17 = null;
                    v18 = null;
                    v19 = null;
                    goto label_63;
                }
                catch(Throwable v0_2) {
                }
                catch(Throwable v0_2) {
                    v6_1 = v11;
                    goto label_204;
                label_187:
                    v31 = v11;
                    v16 = null;
                    v17 = null;
                    v18 = null;
                    goto label_191;
                label_199:
                    v2_1 = v0_2;
                    v31 = null;
                    goto label_387;
                label_203:
                    v6_1 = null;
                label_204:
                    v16 = null;
                    v17 = null;
                    v18 = null;
                    goto label_207;
                    try {
                        while(true) {
                        label_63:
                            if(!v11.moveToNext()) {
                                goto label_173;
                            }

                            int v23 = v11.getInt(v0_3);
                            v9 = v11.getInt(v12);
                            v8 = v11.getString(v13);
                            String v10 = v11.getString(v14);
                            v24 = v11.getLong(v15);
                            int v27 = v11.getInt(v6);
                            if(v10 == null) {
                                goto label_161;
                            }
                            else if(v10.length() == 0) {
                                goto label_161;
                            }
                            else {
                                v29 = v0_3;
                                v0_4 = new PhotoEntry(v9, v23, v24, v10, v27, false);
                                if(v17 == null) {
                                    v30 = v6;
                                    v31 = v11;
                                    goto label_87;
                                }
                                else {
                                    goto label_99;
                                }
                            }

                            goto label_166;
                        }
                    }
                    catch(Throwable v0_2) {
                    }
                    catch(Throwable v0_2) {
                        v31 = v11;
                        goto label_177;
                        try {
                        label_87:
                            v32 = v12;
                            String v11_1 = LocaleController.getString("AllPhotos", 2131624053);
                            v6_2 = new AlbumEntry(0, v11_1, v0_4);
                            goto label_93;
                        }
                        catch(Throwable v0_2) {
                        label_96:
                            goto label_181;
                        }
                        catch(Throwable v0_2) {
                            try {
                                goto label_177;
                            label_93:
                                v4.add(0, v6_2);
                                goto label_103;
                            label_99:
                                v30 = v6;
                                v31 = v11;
                                v32 = v12;
                                v6_2 = v17;
                            label_103:
                                if(v16 == null) {
                                    v33 = v13;
                                    v11_2 = new AlbumEntry(0, LocaleController.getString("AllMedia", 2131624052), v0_4);
                                }
                                else {
                                    goto label_116;
                                }
                            }
                            catch(Throwable v0_2) {
                                goto label_114;
                            }
                            catch(Throwable v0_2) {
                                goto label_96;
                            }

                            try {
                                v3.add(0, v11_2);
                                goto label_118;
                            label_116:
                                v33 = v13;
                                v11_2 = v16;
                            label_118:
                                v6_2.addPhoto(v0_4);
                                v11_2.addPhoto(v0_4);
                                Object v12_1 = v2.get(v9);
                                if(v12_1 == null) {
                                    v12_2 = new AlbumEntry(v9, v8, v0_4);
                                    v2.put(v9, v12_2);
                                    if(v18 == null && v7 != null && v10 != null && (v10.startsWith(v7))) {
                                        v3.add(0, v12_2);
                                        v18 = Integer.valueOf(v9);
                                        goto label_136;
                                    }

                                    v3.add(v12_2);
                                }

                            label_136:
                                v12_2.addPhoto(v0_4);
                                v12_1 = v5.get(v9);
                                if(v12_1 == null) {
                                    v12_2 = new AlbumEntry(v9, v8, v0_4);
                                    v5.put(v9, v12_2);
                                    if(v19 == null && v7 != null && v10 != null && (v10.startsWith(v7))) {
                                        v4.add(0, v12_2);
                                        v19 = Integer.valueOf(v9);
                                        goto label_153;
                                    }

                                    v4.add(v12_2);
                                }

                            label_153:
                                v12_2.addPhoto(v0_4);
                                v17 = v6_2;
                                v16 = v11_2;
                                goto label_166;
                            }
                            catch(Throwable v0_2) {
                                goto label_158;
                            }
                            catch(Throwable v0_2) {
                                goto label_96;
                            }

                        label_161:
                            v29 = v0_3;
                            v30 = v6;
                            v31 = v11;
                            v32 = v12;
                            v33 = v13;
                        label_166:
                            v0_3 = v29;
                            v6 = v30;
                            v11 = v31;
                            v12 = v32;
                            v13 = v33;
                            goto label_63;
                        label_173:
                            v31 = v11;
                        label_191:
                            if(v31 == null) {
                                goto label_215;
                            }

                            try {
                                v31.close();
                            }
                            catch(Exception v0) {
                                FileLog.e(v0);
                            }

                            goto label_215;
                            v31 = v11;
                            goto label_181;
                        }
                    }
                }

            label_114:
                v17 = v6_2;
                goto label_177;
            label_158:
                v17 = v6_2;
                Cursor v16_1 = v11;
            label_177:
                v6_1 = v31;
                goto label_207;
            label_181:
                v2_1 = v0_2;
                goto label_387;
                try {
                label_215:
                    int v5_1 = 23;
                    if(Build$VERSION.SDK_INT >= v5_1) {
                        if(Build$VERSION.SDK_INT >= v5_1 && ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                            goto label_228;
                        }

                        v34 = v31;
                    }
                    else {
                    label_228:
                        v5_2 = MediaStore$Images$Media.query(ApplicationLoader.applicationContext.getContentResolver(), MediaStore$Video$Media.EXTERNAL_CONTENT_URI, MediaController.projectionVideo, null, null, "datetaken DESC");
                        if(v5_2 != null) {
                            goto label_237;
                        }

                        goto label_340;
                    }

                    goto label_226;
                }
                catch(Throwable v0_2) {
                    goto label_353;
                }
                catch(Throwable v0_2) {
                    goto label_349;
                }

                try {
                label_237:
                    v0_3 = v5_2.getColumnIndex("_id");
                    v6 = v5_2.getColumnIndex("bucket_id");
                    v8_1 = v5_2.getColumnIndex("bucket_display_name");
                    v9 = v5_2.getColumnIndex("_data");
                    int v10_1 = v5_2.getColumnIndex("datetaken");
                    int v11_3 = v5_2.getColumnIndex("duration");
                    while(true) {
                    label_249:
                        if(v5_2.moveToNext()) {
                            v21 = v5_2.getInt(v0_3);
                            v12 = v5_2.getInt(v6);
                            v13_1 = v5_2.getString(v8_1);
                            v14_1 = v5_2.getString(v9);
                            v22 = v5_2.getLong(v10_1);
                            v19_1 = v5_2.getLong(v11_3);
                            if(v14_1 == null) {
                                goto label_323;
                            }
                            else if(v14_1.length() == 0) {
                                goto label_323;
                            }
                            else {
                                v15_1 = null;
                                v24 = 1000;
                                v34 = v5_2;
                                v35 = v6;
                                break;
                            }
                        }

                        goto label_340;
                    }
                }
                catch(Throwable v0_2) {
                    goto label_336;
                }
                catch(Throwable v0_2) {
                    goto label_332;
                }

                try {
                    super(v12, v21, v22, v14_1, ((int)(v19_1 / v24)), true);
                    if(v16 != null) {
                        goto label_290;
                    }

                    goto label_274;
                }
                catch(Throwable v0_2) {
                }
                catch(Throwable v0_2) {
                    goto label_337;
                    try {
                    label_274:
                        v36 = v8_1;
                        v6_3 = LocaleController.getString("AllMedia", 2131624052);
                        v8_1 = 0;
                        goto label_280;
                    }
                    catch(Throwable v0_2) {
                    }
                    catch(Throwable v0_2) {
                        v31 = v34;
                        goto label_353;
                        try {
                        label_280:
                            v5_3 = new AlbumEntry(0, v6_3, v15_1);
                            goto label_281;
                        }
                        catch(Throwable v0_2) {
                        }
                        catch(Throwable v0_2) {
                            goto label_338;
                            try {
                            label_281:
                                v3.add(0, v5_3);
                                goto label_292;
                            }
                            catch(Throwable v0_2) {
                                goto label_317;
                            }
                            catch(Throwable v0_2) {
                                goto label_320;
                            }

                        label_290:
                            v36 = v8_1;
                            v5_3 = v16;
                            try {
                            label_292:
                                v5_3.addPhoto(v15_1);
                                Object v6_4 = v2.get(v12);
                                if(v6_4 == null) {
                                    v6_2 = new AlbumEntry(v12, v13_1, v15_1);
                                    v2.put(v12, v6_2);
                                    if(v18 == null && v7 != null && v14_1 != null) {
                                        if(!v14_1.startsWith(v7)) {
                                            goto label_308;
                                        }

                                        goto label_303;
                                    }

                                    goto label_308;
                                }
                                else {
                                    goto label_311;
                                }
                            }
                            catch(Throwable v0_2) {
                            }
                            catch(Throwable v0_2) {
                                v8_1 = 0;
                                goto label_317;
                            label_303:
                                v8_1 = 0;
                                try {
                                    v3.add(0, v6_2);
                                    v18 = Integer.valueOf(v12);
                                    goto label_312;
                                label_308:
                                    v8_1 = 0;
                                    v3.add(v6_2);
                                    goto label_312;
                                label_311:
                                    v8_1 = 0;
                                label_312:
                                    v6_2.addPhoto(v15_1);
                                    v16 = v5_3;
                                    goto label_327;
                                }
                                catch(Throwable v0_2) {
                                label_320:
                                    goto label_333;
                                }
                                catch(Throwable v0_2) {
                                label_317:
                                    v16 = v5_3;
                                    goto label_338;
                                }
                            }
                        }
                    }
                }

            label_323:
                v34 = v5_2;
                v35 = v6;
                v36 = v8_1;
            label_327:
                v5_2 = v34;
                v6 = v35;
                v8_1 = v36;
                goto label_249;
            label_340:
                v34 = v5_2;
                goto label_226;
            label_336:
                v34 = v5_2;
            label_337:
                v8_1 = 0;
            label_338:
                v31 = v34;
                goto label_354;
            label_332:
                v34 = v5_2;
            label_333:
                v2_1 = v0_2;
                goto label_377;
            label_226:
                v8_1 = 0;
                if(v34 == null) {
                    goto label_361;
                }

                try {
                    v34.close();
                    goto label_361;
                }
                catch(Exception v0) {
                    v2_2 = v0;
                    goto label_360;
                }

            label_353:
                v8_1 = 0;
                try {
                label_354:
                    FileLog.e(v0_2);
                    if(v31 == null) {
                        goto label_361;
                    }
                }
                catch(Throwable v0_2) {
                    goto label_349;
                }

                try {
                    v31.close();
                    goto label_361;
                }
                catch(Exception v0) {
                    v2_2 = v0;
                }

            label_360:
                FileLog.e(((Throwable)v2_2));
                goto label_361;
            label_349:
                v2_1 = v0_2;
                v34 = v31;
            label_377:
                if(v34 != null) {
                    try {
                        v34.close();
                    }
                    catch(Exception v0) {
                        FileLog.e(v0);
                    }
                }

                throw v2_1;
            label_361:
                v6_2 = v16;
                Integer v5_4 = v18;
                while(v8_1 < v3.size()) {
                    Collections.sort(v3.get(v8_1).photos, new Comparator() {
                        public int compare(Object arg1, Object arg2) {
                            return this.compare(((PhotoEntry)arg1), ((PhotoEntry)arg2));
                        }

                        public int compare(PhotoEntry arg6, PhotoEntry arg7) {
                            if(arg6.dateTaken < arg7.dateTaken) {
                                return 1;
                            }

                            if(arg6.dateTaken > arg7.dateTaken) {
                                return -1;
                            }

                            return 0;
                        }
                    });
                    ++v8_1;
                }

                MediaController.broadcastNewPhotos(v1.val$guid, v3, v4, v5_4, v6_2, v17, 0);
                return;
                try {
                label_207:
                    FileLog.e(v0_2);
                    if(v6_1 == null) {
                        goto label_214;
                    }
                }
                catch(Throwable v0_2) {
                    goto label_385;
                }

                try {
                    v6_1.close();
                }
                catch(Exception v0) {
                    FileLog.e(v0);
                }

            label_214:
                v31 = v6_1;
                goto label_215;
            label_385:
                v2_1 = v0_2;
                v31 = v6_1;
            label_387:
                if(v31 != null) {
                    try {
                        v31.close();
                    }
                    catch(Exception v0) {
                        FileLog.e(v0);
                    }
                }

                throw v2_1;
            }
        });
        v0.setPriority(1);
        v0.start();
    }

    public void onAccuracyChanged(Sensor arg1, int arg2) {
    }

    public void onAudioFocusChange(int arg4) {
        if(arg4 == -1) {
            if((this.isPlayingMessage(this.getPlayingMessageObject())) && !this.isMessagePaused()) {
                this.pauseMessage(this.playingMessageObject);
            }

            this.hasAudioFocus = 0;
            this.audioFocus = 0;
        }
        else {
            if(arg4 == 1) {
                this.audioFocus = 2;
                if(!this.resumeAudioOnFocusGain) {
                    goto label_43;
                }

                this.resumeAudioOnFocusGain = false;
                if(!this.isPlayingMessage(this.getPlayingMessageObject())) {
                    goto label_43;
                }

                if(!this.isMessagePaused()) {
                    goto label_43;
                }

                this.playMessage(this.getPlayingMessageObject());
                goto label_43;
            }

            if(arg4 == -3) {
                this.audioFocus = 1;
                goto label_43;
            }

            if(arg4 != -2) {
                goto label_43;
            }

            this.audioFocus = 0;
            if(!this.isPlayingMessage(this.getPlayingMessageObject())) {
                goto label_43;
            }

            if(this.isMessagePaused()) {
                goto label_43;
            }

            this.pauseMessage(this.playingMessageObject);
            this.resumeAudioOnFocusGain = true;
        }

    label_43:
        this.setPlayerVolume();
    }

    public void onSensorChanged(SensorEvent arg15) {
        // Method was not decompiled
    }

    public boolean pauseMessage(MessageObject arg6) {
        VideoPlayer v6_1;
        if((this.audioPlayer != null || this.videoPlayer != null) && arg6 != null && this.playingMessageObject != null && (this.isSamePlayingMessage(arg6))) {
            this.stopProgressTimer();
            try {
                if(this.audioPlayer != null) {
                    v6_1 = this.audioPlayer;
                    goto label_15;
                }
                else if(this.videoPlayer != null) {
                    v6_1 = this.videoPlayer;
                label_15:
                    v6_1.pause();
                }

                this.isPaused = true;
                NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
                return 1;
            }
            catch(Exception v6) {
                FileLog.e(((Throwable)v6));
                this.isPaused = false;
            }
        }

        return 0;
    }

    public boolean playMessage(MessageObject arg15) {
        Object[] v4_3;
        long v9;
        int v3_5;
        NotificationCenter v15_2;
        TextureView v4_1;
        VideoPlayer v3_1;
        Intent v15;
        boolean v5;
        File v3;
        if(arg15 == null) {
            return 0;
        }

        if((this.audioPlayer != null || this.videoPlayer != null) && (this.isSamePlayingMessage(arg15))) {
            if(this.isPaused) {
                this.resumeAudio(arg15);
            }

            if(!SharedConfig.raiseToSpeak) {
                this.startRaiseToEarSensors(this.raiseChat);
            }

            return 1;
        }

        if(!arg15.isOut() && (arg15.isContentUnread())) {
            MessagesController.getInstance(arg15.currentAccount).markMessageContentAsRead(arg15);
        }

        int v1 = this.playMusicAgain ^ 1;
        if(this.playingMessageObject != null) {
            if(!this.playMusicAgain) {
                this.playingMessageObject.resetPlayingProgress();
            }

            boolean v1_1 = false;
        }

        this.cleanupPlayer(((boolean)v1), false);
        this.playMusicAgain = false;
        this.seekToProgressPending = 0f;
        AudioInfo v4 = null;
        if(arg15.messageOwner.attachPath == null || arg15.messageOwner.attachPath.length() <= 0) {
            v3 = ((File)v4);
            v5 = false;
        }
        else {
            v3 = new File(arg15.messageOwner.attachPath);
            v5 = v3.exists();
            if(!v5) {
                v3 = ((File)v4);
            }
        }

        File v6 = v3 != null ? v3 : FileLoader.getPathToMessage(arg15.messageOwner);
        int v7 = !SharedConfig.streamMedia || !arg15.isMusic() || (((int)arg15.getDialogId())) == 0 ? 0 : 1;
        long v8 = 0;
        if(v6 != null && v6 != v3) {
            v5 = v6.exists();
            if(!v5 && v7 == 0) {
                FileLoader.getInstance(arg15.currentAccount).loadFile(arg15.getDocument(), false, 0);
                this.downloadingCurrentMessage = true;
                this.isPaused = false;
                this.lastProgress = v8;
                this.audioInfo = v4;
                this.playingMessageObject = arg15;
                if(this.playingMessageObject.isMusic()) {
                    v15 = new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class);
                    try {
                        ApplicationLoader.applicationContext.startService(v15);
                    }
                    catch(Throwable v15_1) {
                        FileLog.e(v15_1);
                    }

                    goto label_105;
                }
                else {
                    ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
                label_105:
                    NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
                    return 1;
                }
            }
        }

        this.downloadingCurrentMessage = false;
        if(arg15.isMusic()) {
            this.checkIsNextMusicFileDownloaded(arg15.currentAccount);
        }
        else {
            this.checkIsNextVoiceFileDownloaded(arg15.currentAccount);
        }

        if(this.currentAspectRatioFrameLayout != null) {
            this.isDrawingWasReady = false;
            this.currentAspectRatioFrameLayout.setDrawingReady(false);
        }

        float v10 = 1f;
        int v11 = 3;
        if(arg15.isRoundVideo()) {
            this.playlist.clear();
            this.shuffledPlaylist.clear();
            this.videoPlayer = new VideoPlayer();
            this.videoPlayer.setDelegate(new VideoPlayerDelegate() {
                public void onError(Exception arg1) {
                    FileLog.e(((Throwable)arg1));
                }

                public void onRenderedFirstFrame() {
                    if(MediaController.this.currentAspectRatioFrameLayout != null && !MediaController.this.currentAspectRatioFrameLayout.isDrawingReady()) {
                        MediaController.this.isDrawingWasReady = true;
                        MediaController.this.currentAspectRatioFrameLayout.setDrawingReady(true);
                        if(MediaController.this.currentTextureViewContainer != null && MediaController.this.currentTextureViewContainer.getVisibility() != 0) {
                            MediaController.this.currentTextureViewContainer.setVisibility(0);
                        }
                    }
                }

                public void onStateChanged(boolean arg4, int arg5) {
                    if(MediaController.this.videoPlayer == null) {
                        return;
                    }

                    int v4 = 128;
                    int v0 = 4;
                    if(arg5 != v0 && arg5 != 1) {
                        try {
                            MediaController.this.baseActivity.getWindow().addFlags(v4);
                            goto label_21;
                        label_14:
                            MediaController.this.baseActivity.getWindow().clearFlags(v4);
                        }
                        catch(Exception v4_1) {
                            FileLog.e(((Throwable)v4_1));
                        }
                    }
                    else {
                        goto label_14;
                    }

                label_21:
                    if(arg5 == 3) {
                        MediaController.this.currentAspectRatioFrameLayoutReady = true;
                        if(MediaController.this.currentTextureViewContainer != null && MediaController.this.currentTextureViewContainer.getVisibility() != 0) {
                            MediaController.this.currentTextureViewContainer.setVisibility(0);
                        }
                    }
                    else if((MediaController.this.videoPlayer.isPlaying()) && arg5 == v0) {
                        MediaController.this.cleanupPlayer(true, true, true);
                    }
                }

                public boolean onSurfaceDestroyed(SurfaceTexture arg6) {
                    TextureView v0;
                    VideoPlayer v6;
                    if(MediaController.this.videoPlayer == null) {
                        return 0;
                    }

                    if(MediaController.this.pipSwitchingState == 2) {
                        if(MediaController.this.currentAspectRatioFrameLayout == null) {
                            goto label_40;
                        }

                        if(MediaController.this.isDrawingWasReady) {
                            MediaController.this.currentAspectRatioFrameLayout.setDrawingReady(true);
                        }

                        if(MediaController.this.currentAspectRatioFrameLayout.getParent() == null) {
                            MediaController.this.currentTextureViewContainer.addView(MediaController.this.currentAspectRatioFrameLayout);
                        }

                        if(MediaController.this.currentTextureView.getSurfaceTexture() != arg6) {
                            MediaController.this.currentTextureView.setSurfaceTexture(arg6);
                        }

                        v6 = MediaController.this.videoPlayer;
                        v0 = MediaController.this.currentTextureView;
                        goto label_39;
                    }

                    if(MediaController.this.pipSwitchingState == 1) {
                        if(MediaController.this.baseActivity != null) {
                            if(MediaController.this.pipRoundVideoView == null) {
                                try {
                                    MediaController.this.pipRoundVideoView = new PipRoundVideoView();
                                    MediaController.this.pipRoundVideoView.show(MediaController.this.baseActivity, new Runnable() {
                                        public void run() {
                                            this.this$1.this$0.cleanupPlayer(true, true);
                                        }
                                    });
                                }
                                catch(Exception ) {
                                    MediaController.this.pipRoundVideoView = null;
                                }
                            }

                            if(MediaController.this.pipRoundVideoView == null) {
                                goto label_40;
                            }

                            if(MediaController.this.pipRoundVideoView.getTextureView().getSurfaceTexture() != arg6) {
                                MediaController.this.pipRoundVideoView.getTextureView().setSurfaceTexture(arg6);
                            }

                            v6 = MediaController.this.videoPlayer;
                            v0 = MediaController.this.pipRoundVideoView.getTextureView();
                        label_39:
                            v6.setTextureView(v0);
                        }

                    label_40:
                        MediaController.this.pipSwitchingState = 0;
                        return 1;
                    }

                    return 0;
                }

                public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
                }

                public void onVideoSizeChanged(int arg3, int arg4, int arg5, float arg6) {
                    MediaController.this.currentAspectRatioFrameLayoutRotation = arg5;
                    if(arg5 != 90) {
                        if(arg5 == 270) {
                        }
                        else {
                            int v1 = arg4;
                            arg4 = arg3;
                            arg3 = v1;
                        }
                    }

                    MediaController v5 = MediaController.this;
                    float v3 = arg3 == 0 ? 1f : (((float)arg4)) * arg6 / (((float)arg3));
                    v5.currentAspectRatioFrameLayoutRatio = v3;
                    if(MediaController.this.currentAspectRatioFrameLayout != null) {
                        MediaController.this.currentAspectRatioFrameLayout.setAspectRatio(MediaController.this.currentAspectRatioFrameLayoutRatio, MediaController.this.currentAspectRatioFrameLayoutRotation);
                    }
                }
            });
            this.currentAspectRatioFrameLayoutReady = false;
            if(this.pipRoundVideoView == null) {
                if(!MessagesController.getInstance(arg15.currentAccount).isDialogVisible(arg15.getDialogId())) {
                }
                else if(this.currentTextureView != null) {
                    v3_1 = this.videoPlayer;
                    v4_1 = this.currentTextureView;
                    goto label_157;
                }
                else {
                    goto label_177;
                }
            }

            if(this.pipRoundVideoView == null) {
                try {
                    this.pipRoundVideoView = new PipRoundVideoView();
                    this.pipRoundVideoView.show(this.baseActivity, new Runnable() {
                        public void run() {
                            MediaController.this.cleanupPlayer(true, true);
                        }
                    });
                }
                catch(Exception ) {
                    this.pipRoundVideoView = ((PipRoundVideoView)v4);
                }
            }

            if(this.pipRoundVideoView != null) {
                v3_1 = this.videoPlayer;
                v4_1 = this.pipRoundVideoView.getTextureView();
            label_157:
                v3_1.setTextureView(v4_1);
            }

        label_177:
            this.videoPlayer.preparePlayer(Uri.fromFile(v6), "other");
            v3_1 = this.videoPlayer;
            if(this.useFrontSpeaker) {
                v11 = 0;
            }

            v3_1.setStreamType(v11);
            if(this.currentPlaybackSpeed > v10) {
                this.videoPlayer.setPlaybackSpeed(this.currentPlaybackSpeed);
            }

            this.videoPlayer.play();
            goto label_291;
        }

        if(this.pipRoundVideoView != null) {
            this.pipRoundVideoView.close(true);
            this.pipRoundVideoView = ((PipRoundVideoView)v4);
        }

        try {
            this.audioPlayer = new VideoPlayer();
            this.audioPlayer.setDelegate(new VideoPlayerDelegate(arg15) {
                public void onError(Exception arg1) {
                }

                public void onRenderedFirstFrame() {
                }

                public void onStateChanged(boolean arg5, int arg6) {
                    if(arg6 == 4) {
                        if(!MediaController.this.playlist.isEmpty() && MediaController.this.playlist.size() > 1) {
                            MediaController.this.playNextMessageWithoutOrder(true);
                            return;
                        }

                        MediaController v6 = MediaController.this;
                        boolean v0 = this.val$messageObject == null || !this.val$messageObject.isVoice() ? false : true;
                        v6.cleanupPlayer(true, true, v0);
                    }
                    else {
                        if(MediaController.this.seekToProgressPending == 0f) {
                            return;
                        }

                        if(arg6 != 3 && arg6 != 1) {
                            return;
                        }

                        long v2 = ((long)(((int)((((float)MediaController.this.audioPlayer.getDuration())) * MediaController.this.seekToProgressPending))));
                        MediaController.this.audioPlayer.seekTo(v2);
                        MediaController.this.lastProgress = v2;
                        MediaController.this.seekToProgressPending = 0f;
                    }
                }

                public boolean onSurfaceDestroyed(SurfaceTexture arg1) {
                    return 0;
                }

                public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
                }

                public void onVideoSizeChanged(int arg1, int arg2, int arg3, float arg4) {
                }
            });
            if(v5) {
                if(!arg15.mediaExists && v6 != v3) {
                    AndroidUtilities.runOnUIThread(new Runnable(arg15) {
                        public void run() {
                            NotificationCenter.getInstance(this.val$messageObject.currentAccount).postNotificationName(NotificationCenter.FileDidLoaded, new Object[]{FileLoader.getAttachFileName(this.val$messageObject.getDocument())});
                        }
                    });
                }

                this.audioPlayer.preparePlayer(Uri.fromFile(v6), "other");
            }
            else {
                Document v3_2 = arg15.getDocument();
                String v3_3 = "?account=" + arg15.currentAccount + "&id=" + v3_2.id + "&hash=" + v3_2.access_hash + "&dc=" + v3_2.dc_id + "&size=" + v3_2.size + "&mime=" + URLEncoder.encode(v3_2.mime_type, "UTF-8") + "&name=" + URLEncoder.encode(FileLoader.getDocumentFileName(v3_2), "UTF-8");
                v5_1 = new StringBuilder();
                v5_1.append("tg://");
                v5_1.append(arg15.getFileName());
                v5_1.append(v3_3);
                this.audioPlayer.preparePlayer(Uri.parse(v5_1.toString()), "other");
            }

            if(!arg15.isVoice()) {
                goto label_279;
            }

            if(this.currentPlaybackSpeed > v10) {
                this.audioPlayer.setPlaybackSpeed(this.currentPlaybackSpeed);
            }

            this.audioInfo = v4;
            this.playlist.clear();
            this.shuffledPlaylist.clear();
            goto label_284;
        }
        catch(Exception v1_2) {
            goto label_407;
        }

        try {
        label_279:
            this.audioInfo = AudioInfo.getAudioInfo(v6);
            goto label_284;
        }
        catch(Exception v3_4) {
            try {
                FileLog.e(((Throwable)v3_4));
            label_284:
                v3_1 = this.audioPlayer;
                if(this.useFrontSpeaker) {
                    v11 = 0;
                }

                v3_1.setStreamType(v11);
                this.audioPlayer.play();
            }
            catch(Exception v1_2) {
            label_407:
                FileLog.e(((Throwable)v1_2));
                v15_2 = NotificationCenter.getInstance(arg15.currentAccount);
                v1 = NotificationCenter.messagePlayingPlayStateChanged;
                Object[] v2 = new Object[1];
                v3_5 = this.playingMessageObject != null ? this.playingMessageObject.getId() : 0;
                v2[0] = Integer.valueOf(v3_5);
                v15_2.postNotificationName(v1, v2);
                if(this.audioPlayer != null) {
                    this.audioPlayer.releasePlayer();
                    this.audioPlayer = ((VideoPlayer)v4);
                    this.isPaused = false;
                    this.playingMessageObject = ((MessageObject)v4);
                    this.downloadingCurrentMessage = false;
                }

                return 0;
            }
        }

    label_291:
        this.checkAudioFocus(arg15);
        this.setPlayerVolume();
        this.isPaused = false;
        this.lastProgress = v8;
        this.playingMessageObject = arg15;
        if(!SharedConfig.raiseToSpeak) {
            this.startRaiseToEarSensors(this.raiseChat);
        }

        this.startProgressTimer(this.playingMessageObject);
        NotificationCenter.getInstance(arg15.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidStarted, new Object[]{arg15});
        long v4_2 = 1000;
        long v6_1 = -9223372036854775807L;
        int v8_1 = 2;
        if(this.videoPlayer != null) {
            try {
                if(this.playingMessageObject.audioProgress == 0f) {
                    goto label_386;
                }

                v9 = this.audioPlayer.getDuration();
                if(v9 == v6_1) {
                    v9 = (((long)this.playingMessageObject.getDuration())) * v4_2;
                }

                this.videoPlayer.seekTo(((long)(((int)((((float)v9)) * this.playingMessageObject.audioProgress)))));
            }
            catch(Exception v3_4) {
                this.playingMessageObject.audioProgress = 0f;
                this.playingMessageObject.audioProgressSec = 0;
                v15_2 = NotificationCenter.getInstance(arg15.currentAccount);
                v1 = NotificationCenter.messagePlayingProgressDidChanged;
                v4_3 = new Object[v8_1];
                v4_3[0] = Integer.valueOf(this.playingMessageObject.getId());
                v4_3[1] = Integer.valueOf(0);
                v15_2.postNotificationName(v1, v4_3);
                FileLog.e(((Throwable)v3_4));
            }

            goto label_386;
        }

        if(this.audioPlayer != null) {
            try {
                if(this.playingMessageObject.audioProgress == 0f) {
                    goto label_386;
                }

                v9 = this.audioPlayer.getDuration();
                if(v9 == v6_1) {
                    v9 = (((long)this.playingMessageObject.getDuration())) * v4_2;
                }

                this.audioPlayer.seekTo(((long)(((int)((((float)v9)) * this.playingMessageObject.audioProgress)))));
            }
            catch(Exception v1_2) {
                this.playingMessageObject.resetPlayingProgress();
                v15_2 = NotificationCenter.getInstance(arg15.currentAccount);
                v3_5 = NotificationCenter.messagePlayingProgressDidChanged;
                v4_3 = new Object[v8_1];
                v4_3[0] = Integer.valueOf(this.playingMessageObject.getId());
                v4_3[1] = Integer.valueOf(0);
                v15_2.postNotificationName(v3_5, v4_3);
                FileLog.e(((Throwable)v1_2));
            }
        }

    label_386:
        if(this.playingMessageObject.isMusic()) {
            v15 = new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class);
            try {
                ApplicationLoader.applicationContext.startService(v15);
            }
            catch(Throwable v15_1) {
                FileLog.e(v15_1);
            }
        }
        else {
            ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
        }

        return 1;
    }

    public void playMessageAtIndex(int arg3) {
        if(this.currentPlaylistNum >= 0) {
            if(this.currentPlaylistNum >= this.playlist.size()) {
            }
            else {
                this.currentPlaylistNum = arg3;
                this.playMusicAgain = true;
                if(this.playingMessageObject != null) {
                    this.playingMessageObject.resetPlayingProgress();
                }

                this.playMessage(this.playlist.get(this.currentPlaylistNum));
            }
        }
    }

    public void playNextMessage() {
        this.playNextMessageWithoutOrder(false);
    }

    private void playNextMessageWithoutOrder(boolean arg9) {
        Object v9;
        int v4;
        ArrayList v0 = SharedConfig.shuffleMusic ? this.shuffledPlaylist : this.playlist;
        int v2 = 2;
        if(!arg9 || SharedConfig.repeatMode != v2 || (this.forceLoopCurrentPlaylist)) {
            if(SharedConfig.playOrderReversed) {
                ++this.currentPlaylistNum;
                if(this.currentPlaylistNum >= v0.size()) {
                    this.currentPlaylistNum = 0;
                    goto label_30;
                }
                else {
                    goto label_41;
                }
            }
            else {
                --this.currentPlaylistNum;
                if(this.currentPlaylistNum < 0) {
                    this.currentPlaylistNum = v0.size() - 1;
                label_30:
                    v4 = 1;
                }
                else {
                label_41:
                    v4 = 0;
                }
            }

            if(v4 != 0 && (arg9) && SharedConfig.repeatMode == 0 && !this.forceLoopCurrentPlaylist) {
                if(this.audioPlayer != null || this.videoPlayer != null) {
                    VideoPlayer v0_1 = null;
                    if(this.audioPlayer != null) {
                        try {
                            this.audioPlayer.releasePlayer();
                        }
                        catch(Exception v9_1) {
                            FileLog.e(((Throwable)v9_1));
                        }

                        this.audioPlayer = v0_1;
                        goto label_78;
                    }

                    if(this.videoPlayer != null) {
                        this.currentAspectRatioFrameLayout = ((AspectRatioFrameLayout)v0_1);
                        this.currentTextureViewContainer = ((FrameLayout)v0_1);
                        this.currentAspectRatioFrameLayoutReady = false;
                        this.currentTextureView = ((TextureView)v0_1);
                        this.videoPlayer.releasePlayer();
                        this.videoPlayer = v0_1;
                        try {
                            this.baseActivity.getWindow().clearFlags(128);
                        }
                        catch(Exception v9_1) {
                            FileLog.e(((Throwable)v9_1));
                        }
                    }

                label_78:
                    this.stopProgressTimer();
                    this.lastProgress = 0;
                    this.isPaused = true;
                    this.playingMessageObject.audioProgress = 0f;
                    this.playingMessageObject.audioProgressSec = 0;
                    NotificationCenter v9_2 = NotificationCenter.getInstance(this.playingMessageObject.currentAccount);
                    int v0_2 = NotificationCenter.messagePlayingProgressDidChanged;
                    Object[] v1 = new Object[v2];
                    v1[0] = Integer.valueOf(this.playingMessageObject.getId());
                    v1[1] = Integer.valueOf(0);
                    v9_2.postNotificationName(v0_2, v1);
                    NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
                }

                return;
            }

            if(this.currentPlaylistNum >= 0) {
                if(this.currentPlaylistNum >= v0.size()) {
                }
                else {
                    if(this.playingMessageObject != null) {
                        this.playingMessageObject.resetPlayingProgress();
                    }

                    this.playMusicAgain = true;
                    v9 = v0.get(this.currentPlaylistNum);
                    goto label_18;
                }
            }

            return;
        }
        else {
            this.cleanupPlayer(false, false);
            v9 = v0.get(this.currentPlaylistNum);
            ((MessageObject)v9).audioProgress = 0f;
            ((MessageObject)v9).audioProgressSec = 0;
        }

    label_18:
        this.playMessage(((MessageObject)v9));
    }

    public void playPreviousMessage() {
        int v1_1;
        ArrayList v0 = SharedConfig.shuffleMusic ? this.shuffledPlaylist : this.playlist;
        if(!v0.isEmpty() && this.currentPlaylistNum >= 0) {
            if(this.currentPlaylistNum >= v0.size()) {
            }
            else {
                Object v1 = v0.get(this.currentPlaylistNum);
                if(((MessageObject)v1).audioProgressSec > 10) {
                    this.seekToProgress(((MessageObject)v1), 0f);
                    return;
                }
                else {
                    if(SharedConfig.playOrderReversed) {
                        --this.currentPlaylistNum;
                        if(this.currentPlaylistNum < 0) {
                            v1_1 = v0.size() - 1;
                            goto label_31;
                        }
                    }
                    else {
                        ++this.currentPlaylistNum;
                        if(this.currentPlaylistNum >= v0.size()) {
                            v1_1 = 0;
                        label_31:
                            this.currentPlaylistNum = v1_1;
                        }
                    }

                    if(this.currentPlaylistNum < 0) {
                        return;
                    }

                    if(this.currentPlaylistNum >= v0.size()) {
                        return;
                    }

                    this.playMusicAgain = true;
                    this.playMessage(v0.get(this.currentPlaylistNum));
                }
            }
        }
    }

    private void processMediaObserver(Uri arg15) {
        int v10;
        int v9;
        long v6;
        String v2;
        ArrayList v0_3;
        Exception v0_1;
        Cursor v13;
        Throwable v0_2;
        Cursor v15_2;
        Cursor v0 = null;
        try {
            android.graphics.Point v1 = AndroidUtilities.getRealScreenSize();
            v15_2 = ApplicationLoader.applicationContext.getContentResolver().query(arg15, this.mediaProjections, null, null, "date_added DESC LIMIT 1");
        }
        catch(Throwable v15) {
            v13 = v0;
            v0_2 = v15;
            v15_2 = v13;
            goto label_98;
        }
        catch(Exception v15_1) {
            v13 = v0;
            v0_1 = v15_1;
            v15_2 = v13;
            goto label_93;
        }

        try {
            v0_3 = new ArrayList();
            if(v15_2 == null) {
                goto label_74;
            }

            while(true) {
            label_13:
                if(!v15_2.moveToNext()) {
                    goto label_73;
                }

                v2 = v15_2.getString(0);
                String v4 = v15_2.getString(1);
                String v5 = v15_2.getString(2);
                v6 = v15_2.getLong(3);
                String v8 = v15_2.getString(4);
                v9 = v15_2.getInt(5);
                v10 = v15_2.getInt(6);
                if(v2 == null || !v2.toLowerCase().contains("screenshot")) {
                    if(v4 != null && (v4.toLowerCase().contains("screenshot"))) {
                        break;
                    }

                    if(v5 != null && (v5.toLowerCase().contains("screenshot"))) {
                        break;
                    }

                    if(v8 == null) {
                        continue;
                    }

                    if(!v8.toLowerCase().contains("screenshot")) {
                        continue;
                    }
                }

                break;
            }
        }
        catch(Throwable v0_2) {
            goto label_98;
        }
        catch(Exception v0_1) {
            goto label_83;
        }

        if(v9 == 0) {
            try {
            label_51:
                BitmapFactory$Options v4_1 = new BitmapFactory$Options();
                v4_1.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(v2, v4_1);
                v9 = v4_1.outWidth;
                v10 = v4_1.outHeight;
            label_57:
                if(v9 > 0 && v10 > 0 && (v9 != v1.x || v10 != v1.y)) {
                    if(v10 != v1.x) {
                        goto label_13;
                    }

                    if(v9 != v1.y) {
                        goto label_13;
                    }
                }

                v0_3.add(Long.valueOf(v6));
                goto label_13;
            }
            catch(Exception ) {
                try {
                    v0_3.add(Long.valueOf(v6));
                    goto label_13;
                label_73:
                    v15_2.close();
                label_74:
                    if(!v0_3.isEmpty()) {
                        AndroidUtilities.runOnUIThread(new Runnable(v0_3) {
                            public void run() {
                                NotificationCenter.getInstance(MediaController.this.lastChatAccount).postNotificationName(NotificationCenter.screenshotTook, new Object[0]);
                                MediaController.this.checkScreenshots(this.val$screenshotDates);
                            }
                        });
                    }

                    goto label_79;
                }
                catch(Exception v0_1) {
                label_83:
                    goto label_93;
                }
            }
        }
        else if(v10 == 0) {
            goto label_51;
        }

        goto label_57;
        try {
        label_93:
            FileLog.e(((Throwable)v0_1));
            if(v15_2 != null) {
            }
            else {
                return;
            }
        }
        catch(Throwable v0_2) {
            goto label_98;
        }

        goto label_80;
    label_98:
        if(v15_2 != null) {
            try {
                v15_2.close();
                goto label_100;
            }
            catch(Exception ) {
            label_100:
                throw v0_2;
            }
        }

        goto label_100;
    label_79:
        if(v15_2 == null) {
            return;
        }

        try {
        label_80:
            v15_2.close();
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    private long readAndWriteTracks(MessageObject arg27, MediaExtractor arg28, MP4Builder arg29, MediaCodec$BufferInfo arg30, long arg31, long arg33, File arg35, boolean arg36) {
        MediaExtractor v0_2;
        MediaController v6_1;
        long v9_2;
        int v0_1;
        int v24;
        int v21;
        int v11_1;
        int v15;
        int v8;
        int v14;
        MediaFormat v13;
        MediaController v0 = this;
        MediaExtractor v1 = arg28;
        MP4Builder v2 = arg29;
        MediaCodec$BufferInfo v3 = arg30;
        long v4 = arg31;
        int v6 = 0;
        int v7 = v0.findTrack(v1, false);
        int v10 = arg36 ? v0.findTrack(v1, true) : -1;
        long v11 = 0;
        if(v7 >= 0) {
            v1.selectTrack(v7);
            v13 = v1.getTrackFormat(v7);
            v14 = v2.addTrack(v13, false);
            v8 = v13.getInteger("max-input-size");
            if(v4 > v11) {
                v1.seekTo(v4, 0);
            }
            else {
                v1.seekTo(v11, 0);
            }
        }
        else {
            v8 = 0;
            v14 = -1;
        }

        if(v10 >= 0) {
            v1.selectTrack(v10);
            v13 = v1.getTrackFormat(v10);
            v15 = v2.addTrack(v13, true);
            v8 = Math.max(v13.getInteger("max-input-size"), v8);
            if(v4 > v11) {
                v1.seekTo(v4, 0);
            }
            else {
                v1.seekTo(v11, 0);
            }
        }
        else {
            v15 = -1;
        }

        ByteBuffer v8_1 = ByteBuffer.allocateDirect(v8);
        long v17 = -1;
        if(v10 < 0) {
            if(v7 >= 0) {
            }
            else {
                return v17;
            }
        }

        this.checkConversionCanceled();
        long v19 = v17;
        int v9 = 0;
        while(v9 == 0) {
            this.checkConversionCanceled();
            v3.size = v1.readSampleData(v8_1, v6);
            int v13_1 = arg28.getSampleTrackIndex();
            if(v13_1 == v7) {
                v11_1 = v14;
            }
            else if(v13_1 == v10) {
                v11_1 = v15;
            }
            else {
                v11_1 = -1;
            }

            int v12 = -1;
            if(v11_1 != v12) {
                v21 = v9;
                if(Build$VERSION.SDK_INT < 21) {
                    v8_1.position(v6);
                    v8_1.limit(v3.size);
                }

                if(v13_1 != v10) {
                    byte[] v9_1 = v8_1.array();
                    if(v9_1 != null) {
                        v12 = v8_1.arrayOffset();
                        int v22 = v12 + v8_1.limit();
                        v6 = -1;
                        while(true) {
                            int v23 = 4;
                            v24 = v10;
                            v10 = v22 - 4;
                            if(v12 <= v10) {
                                if(v9_1[v12] == 0 && v9_1[v12 + 1] == 0 && v9_1[v12 + 2] == 0 && v9_1[v12 + 3] == 1 || v12 == v10) {
                                    if(v6 != -1) {
                                        int v1_1 = v12 - v6;
                                        if(v12 != v10) {
                                        }
                                        else {
                                            v23 = 0;
                                        }

                                        v1_1 -= v23;
                                        v9_1[v6] = ((byte)(v1_1 >> 24));
                                        v9_1[v6 + 1] = ((byte)(v1_1 >> 16));
                                        v9_1[v6 + 2] = ((byte)(v1_1 >> 8));
                                        v9_1[v6 + 3] = ((byte)v1_1);
                                    }

                                    v6 = v12;
                                }

                                ++v12;
                                v10 = v24;
                                continue;
                            }

                            break;
                        }
                    }
                    else {
                        goto label_123;
                    }
                }
                else {
                label_123:
                    v24 = v10;
                }

                if(v3.size >= 0) {
                    v3.presentationTimeUs = arg28.getSampleTime();
                    v0_1 = 0;
                }
                else {
                    v3.size = 0;
                    v0_1 = 1;
                }

                if(v3.size <= 0 || v0_1 != 0) {
                    v6_1 = this;
                }
                else {
                    if(v13_1 == v7) {
                        v9_2 = 0;
                        if(v4 > v9_2 && v19 == v17) {
                            v19 = v3.presentationTimeUs;
                        }
                    }
                    else {
                        v9_2 = 0;
                    }

                    if(arg33 < v9_2 || v3.presentationTimeUs < arg33) {
                        v3.offset = 0;
                        v3.flags = arg28.getSampleFlags();
                        if(v2.writeSampleData(v11_1, v8_1, v3, false)) {
                            v6_1 = this;
                            v6_1.didWriteData(arg27, arg35, false, false);
                            goto label_176;
                        }
                    }
                    else {
                        v0_1 = 1;
                    }

                    v6_1 = this;
                }

            label_176:
                if(v0_1 == 0) {
                    arg28.advance();
                }

                v13_1 = v0_1;
            }
            else {
                v6_1 = v0;
                v21 = v9;
                v24 = v10;
                if(v13_1 == -1) {
                    v13_1 = 1;
                    goto label_195;
                }

                arg28.advance();
                v13_1 = 0;
            }

        label_195:
            if(v13_1 != 0) {
                v21 = 1;
            }

            v0 = v6_1;
            v9 = v21;
            v10 = v24;
            v1 = arg28;
            v6 = 0;
        }

        v24 = v10;
        if(v7 >= 0) {
            v0_2 = arg28;
            v0_2.unselectTrack(v7);
        }
        else {
            v0_2 = arg28;
        }

        if(v24 >= 0) {
            v0_2.unselectTrack(v24);
        }

        return v19;
    }

    private void readSms() {
    }

    public boolean resumeAudio(MessageObject arg6) {
        VideoPlayer v0;
        if((this.audioPlayer != null || this.videoPlayer != null) && arg6 != null && this.playingMessageObject != null && (this.isSamePlayingMessage(arg6))) {
            try {
                this.startProgressTimer(this.playingMessageObject);
                if(this.audioPlayer != null) {
                    v0 = this.audioPlayer;
                    goto label_16;
                }
                else if(this.videoPlayer != null) {
                    v0 = this.videoPlayer;
                label_16:
                    v0.play();
                }

                this.checkAudioFocus(arg6);
                this.isPaused = false;
                NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
                return 1;
            }
            catch(Exception v6) {
                FileLog.e(((Throwable)v6));
            }
        }

        return 0;
    }

    public static void saveFile(String arg9, Context arg10, int arg11, String arg12, String arg13) {
        // Method was not decompiled
    }

    public void scheduleVideoConvert(MessageObject arg2) {
        this.scheduleVideoConvert(arg2, false);
    }

    public boolean scheduleVideoConvert(MessageObject arg3, boolean arg4) {
        if(arg3 != null) {
            if(arg3.videoEditedInfo == null) {
            }
            else {
                if((arg4) && !this.videoConvertQueue.isEmpty()) {
                    return 0;
                }

                if(arg4) {
                    new File(arg3.messageOwner.attachPath).delete();
                }

                this.videoConvertQueue.add(arg3);
                if(this.videoConvertQueue.size() == 1) {
                    this.startVideoConvertFromQueue();
                }

                return 1;
            }
        }

        return 0;
    }

    public boolean seekToProgress(MessageObject arg7, float arg8) {
        if((this.audioPlayer != null || this.videoPlayer != null) && arg7 != null && this.playingMessageObject != null && (this.isSamePlayingMessage(arg7))) {
            try {
                if(this.audioPlayer != null) {
                    long v2 = this.audioPlayer.getDuration();
                    if(v2 == -9223372036854775807L) {
                        this.seekToProgressPending = arg8;
                    }
                    else {
                        long v3 = ((long)(((int)((((float)v2)) * arg8))));
                        this.audioPlayer.seekTo(v3);
                        this.lastProgress = v3;
                    }
                }
                else if(this.videoPlayer != null) {
                    this.videoPlayer.seekTo(((long)((((float)this.videoPlayer.getDuration())) * arg8)));
                }
            }
            catch(Exception v7) {
                FileLog.e(((Throwable)v7));
                return 0;
            }

            NotificationCenter.getInstance(arg7.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidSeek, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), Float.valueOf(arg8)});
            return 1;
        }

        return 0;
    }

    @SuppressLint(value={"NewApi"}) public static MediaCodecInfo selectCodec(String arg9) {
        int v0 = MediaCodecList.getCodecCount();
        MediaCodecInfo v3 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            MediaCodecInfo v4 = MediaCodecList.getCodecInfoAt(v2);
            if(!v4.isEncoder()) {
            }
            else {
                String[] v5 = v4.getSupportedTypes();
                int v6 = v5.length;
                MediaCodecInfo v7 = v3;
                int v3_1;
                for(v3_1 = 0; v3_1 < v6; ++v3_1) {
                    if(v5[v3_1].equalsIgnoreCase(arg9)) {
                        String v7_1 = v4.getName();
                        if(v7_1 != null) {
                            if(!v7_1.equals("OMX.SEC.avc.enc")) {
                                return v4;
                            }
                            else if(v7_1.equals("OMX.SEC.AVC.Encoder")) {
                                return v4;
                            }
                        }

                        v7 = v4;
                    }
                }

                v3 = v7;
            }
        }

        return v3;
    }

    @SuppressLint(value={"NewApi"}) public static int selectColorFormat(MediaCodecInfo arg4, String arg5) {
        MediaCodecInfo$CodecCapabilities v5 = arg4.getCapabilitiesForType(arg5);
        int v0 = 0;
        int v1 = 0;
        while(v0 < v5.colorFormats.length) {
            int v2 = v5.colorFormats[v0];
            if(MediaController.isRecognizedFormat(v2)) {
                if(arg4.getName().equals("OMX.SEC.AVC.Encoder")) {
                    if(v2 != 19) {
                    }
                    else {
                        v1 = v2;
                        goto label_20;
                    }
                }

                return v2;
            }

        label_20:
            ++v0;
        }

        return v1;
    }

    public void setAllowStartRecord(boolean arg1) {
        this.allowStartRecord = arg1;
    }

    public void setBaseActivity(Activity arg1, boolean arg2) {
        if(arg2) {
        label_1:
            this.baseActivity = arg1;
        }
        else if(this.baseActivity == arg1) {
            arg1 = null;
            goto label_1;
        }
    }

    public void setCurrentRoundVisible(boolean arg4) {
        TextureView v0_1;
        VideoPlayer v4;
        if(this.currentAspectRatioFrameLayout == null) {
            return;
        }

        PipRoundVideoView v0 = null;
        if(arg4) {
            if(this.pipRoundVideoView != null) {
                this.pipSwitchingState = 2;
                this.pipRoundVideoView.close(true);
                this.pipRoundVideoView = v0;
                return;
            }

            if(this.currentAspectRatioFrameLayout == null) {
                return;
            }

            if(this.currentAspectRatioFrameLayout.getParent() == null) {
                this.currentTextureViewContainer.addView(this.currentAspectRatioFrameLayout);
            }

            v4 = this.videoPlayer;
            v0_1 = this.currentTextureView;
            goto label_24;
        }

        if(this.currentAspectRatioFrameLayout.getParent() != null) {
            this.pipSwitchingState = 1;
            this.currentTextureViewContainer.removeView(this.currentAspectRatioFrameLayout);
            return;
        }

        if(this.pipRoundVideoView == null) {
            try {
                this.pipRoundVideoView = new PipRoundVideoView();
                this.pipRoundVideoView.show(this.baseActivity, new Runnable() {
                    public void run() {
                        MediaController.this.cleanupPlayer(true, true);
                    }
                });
            }
            catch(Exception ) {
                this.pipRoundVideoView = v0;
            }
        }

        if(this.pipRoundVideoView != null) {
            v4 = this.videoPlayer;
            v0_1 = this.pipRoundVideoView.getTextureView();
        label_24:
            v4.setTextureView(v0_1);
        }
    }

    public void setFeedbackView(View arg1, boolean arg2) {
        if(arg2) {
        label_1:
            this.feedbackView = arg1;
        }
        else if(this.feedbackView == arg1) {
            arg1 = null;
            goto label_1;
        }
    }

    public void setFlagSecure(BaseFragment arg2, boolean arg3) {
        int v0 = 8192;
        if(arg3) {
            try {
                arg2.getParentActivity().getWindow().setFlags(v0, v0);
            }
            catch(Exception ) {
            }

            goto label_5;
        }
        else if(this.flagSecureFragment == arg2) {
            try {
                arg2.getParentActivity().getWindow().clearFlags(v0);
                goto label_12;
            }
            catch(Exception ) {
            label_12:
                arg2 = null;
            }

        label_5:
            this.flagSecureFragment = arg2;
        }
    }

    public void setInputFieldHasText(boolean arg1) {
        this.inputFieldHasText = arg1;
    }

    public void setLastVisibleMessageIds(int arg1, long arg2, long arg4, User arg6, EncryptedChat arg7, ArrayList arg8, int arg9) {
        this.lastChatEnterTime = arg2;
        this.lastChatLeaveTime = arg4;
        this.lastChatAccount = arg1;
        this.lastSecretChat = arg7;
        this.lastUser = arg6;
        this.lastMessageId = arg9;
        this.lastChatVisibleMessages = arg8;
    }

    public void setPlaybackSpeed(float arg3) {
        VideoPlayer v0;
        this.currentPlaybackSpeed = arg3;
        if(this.audioPlayer != null) {
            v0 = this.audioPlayer;
            goto label_4;
        }
        else if(this.videoPlayer != null) {
            v0 = this.videoPlayer;
        label_4:
            v0.setPlaybackSpeed(this.currentPlaybackSpeed);
        }

        MessagesController.getGlobalMainSettings().edit().putFloat("playbackSpeed", arg3).commit();
    }

    private void setPlayerVolume() {
        VideoPlayer v1;
        try {
            float v0_1 = this.audioFocus != 1 ? 1f : 0.2f;
            if(this.audioPlayer != null) {
                v1 = this.audioPlayer;
            }
            else if(this.videoPlayer != null) {
                v1 = this.videoPlayer;
            }
            else {
                return;
            }

            v1.setVolume(v0_1);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public boolean setPlaylist(ArrayList arg2, MessageObject arg3) {
        return this.setPlaylist(arg2, arg3, true);
    }

    public boolean setPlaylist(ArrayList arg6, MessageObject arg7, boolean arg8) {
        if(this.playingMessageObject == arg7) {
            return this.playMessage(arg7);
        }

        this.forceLoopCurrentPlaylist = (((int)arg8)) ^ 1;
        this.playMusicAgain = this.playlist.isEmpty() ^ 1;
        this.playlist.clear();
        int v0;
        for(v0 = arg6.size() - 1; v0 >= 0; --v0) {
            Object v1 = arg6.get(v0);
            if(((MessageObject)v1).isMusic()) {
                this.playlist.add(v1);
            }
        }

        this.currentPlaylistNum = this.playlist.indexOf(arg7);
        if(this.currentPlaylistNum == -1) {
            this.playlist.clear();
            this.shuffledPlaylist.clear();
            this.currentPlaylistNum = this.playlist.size();
            this.playlist.add(arg7);
        }

        if(arg7.isMusic()) {
            if(SharedConfig.shuffleMusic) {
                this.buildShuffledPlayList();
                this.currentPlaylistNum = 0;
            }

            if(!arg8) {
                goto label_52;
            }

            DataQuery.getInstance(arg7.currentAccount).loadMusic(arg7.getDialogId(), this.playlist.get(0).getIdWithChannel());
        }

    label_52:
        return this.playMessage(arg7);
    }

    public void setReplyingMessage(MessageObject arg1) {
        this.recordReplyingMessageObject = arg1;
    }

    public void setTextureView(TextureView arg3, AspectRatioFrameLayout arg4, FrameLayout arg5, boolean arg6) {
        TextureView v0_1;
        VideoPlayer v3;
        if(arg3 == null) {
            return;
        }

        boolean v0 = true;
        if(!arg6 && this.currentTextureView == arg3) {
            this.pipSwitchingState = 1;
            this.currentTextureView = null;
            this.currentAspectRatioFrameLayout = null;
            this.currentTextureViewContainer = null;
            return;
        }

        if(this.videoPlayer != null) {
            if(arg3 == this.currentTextureView) {
            }
            else {
                if(arg4 == null || !arg4.isDrawingReady()) {
                    v0 = false;
                }
                else {
                }

                this.isDrawingWasReady = v0;
                this.currentTextureView = arg3;
                if(this.pipRoundVideoView != null) {
                    v3 = this.videoPlayer;
                    v0_1 = this.pipRoundVideoView.getTextureView();
                }
                else {
                    v3 = this.videoPlayer;
                    v0_1 = this.currentTextureView;
                }

                v3.setTextureView(v0_1);
                this.currentAspectRatioFrameLayout = arg4;
                this.currentTextureViewContainer = arg5;
                if(!this.currentAspectRatioFrameLayoutReady) {
                    return;
                }

                if(this.currentAspectRatioFrameLayout == null) {
                    return;
                }

                if(this.currentAspectRatioFrameLayout != null) {
                    this.currentAspectRatioFrameLayout.setAspectRatio(this.currentAspectRatioFrameLayoutRatio, this.currentAspectRatioFrameLayoutRotation);
                }

                if(this.currentTextureViewContainer.getVisibility() == 0) {
                    return;
                }

                this.currentTextureViewContainer.setVisibility(0);
            }
        }
    }

    private void setUseFrontSpeaker(boolean arg2) {
        boolean v0;
        this.useFrontSpeaker = arg2;
        AudioManager v2 = NotificationsController.audioManager;
        if(this.useFrontSpeaker) {
            v0 = false;
            v2.setBluetoothScoOn(false);
        }
        else {
            v0 = true;
        }

        v2.setSpeakerphoneOn(v0);
    }

    public void setVoiceMessagesPlaylist(ArrayList arg3, boolean arg4) {
        this.voiceMessagesPlaylist = arg3;
        if(this.voiceMessagesPlaylist != null) {
            this.voiceMessagesPlaylistUnread = arg4;
            this.voiceMessagesPlaylistMap = new SparseArray();
            int v3;
            for(v3 = 0; v3 < this.voiceMessagesPlaylist.size(); ++v3) {
                Object v4 = this.voiceMessagesPlaylist.get(v3);
                this.voiceMessagesPlaylistMap.put(((MessageObject)v4).getId(), v4);
            }
        }
    }

    private void startAudioAgain(boolean arg7) {
        if(this.playingMessageObject == null) {
            return;
        }

        NotificationCenter v0 = NotificationCenter.getInstance(this.playingMessageObject.currentAccount);
        int v1 = NotificationCenter.audioRouteChanged;
        Object[] v3 = new Object[1];
        int v5 = 0;
        v3[0] = Boolean.valueOf(this.useFrontSpeaker);
        v0.postNotificationName(v1, v3);
        if(this.videoPlayer != null) {
            VideoPlayer v0_1 = this.videoPlayer;
            if(this.useFrontSpeaker) {
            }
            else {
                v5 = 3;
            }

            v0_1.setStreamType(v5);
            if(!arg7) {
                this.videoPlayer.play();
                return;
            }

            this.videoPlayer.pause();
        }
        else {
            int v0_2 = this.audioPlayer != null ? 1 : 0;
            MessageObject v1_1 = this.playingMessageObject;
            float v3_1 = this.playingMessageObject.audioProgress;
            this.cleanupPlayer(false, true);
            v1_1.audioProgress = v3_1;
            this.playMessage(v1_1);
            if(!arg7) {
                return;
            }

            if(v0_2 != 0) {
                AndroidUtilities.runOnUIThread(new Runnable(v1_1) {
                    public void run() {
                        MediaController.this.pauseMessage(this.val$currentMessageObject);
                    }
                }, 100);
                return;
            }

            this.pauseMessage(v1_1);
        }
    }

    public void startMediaObserver() {
        Uri v2;
        ContentResolver v1_1;
        ApplicationLoader.applicationHandler.removeCallbacks(this.stopMediaObserverRunnable);
        ++this.startObserverToken;
        try {
            if(this.internalObserver != null) {
                goto label_19;
            }

            v1_1 = ApplicationLoader.applicationContext.getContentResolver();
            v2 = MediaStore$Images$Media.EXTERNAL_CONTENT_URI;
            ExternalObserver v3 = new ExternalObserver(this);
            this.externalObserver = v3;
            v1_1.registerContentObserver(v2, false, ((ContentObserver)v3));
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

        try {
        label_19:
            if(this.externalObserver != null) {
                return;
            }

            v1_1 = ApplicationLoader.applicationContext.getContentResolver();
            v2 = MediaStore$Images$Media.INTERNAL_CONTENT_URI;
            InternalObserver v3_1 = new InternalObserver(this);
            this.internalObserver = v3_1;
            v1_1.registerContentObserver(v2, false, ((ContentObserver)v3_1));
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    private void startProgressTimer(MessageObject arg9) {
        Object v0 = this.progressTimerSync;
        __monitor_enter(v0);
        try {
            if(this.progressTimer == null) {
                goto label_11;
            }

            try {
                this.progressTimer.cancel();
                this.progressTimer = null;
                goto label_11;
            }
            catch(Exception v1) {
                try {
                    FileLog.e(((Throwable)v1));
                label_11:
                    arg9.getFileName();
                    this.progressTimer = new Timer();
                    this.progressTimer.schedule(new TimerTask(arg9) {
                        public void run() {
                            Object v0 = MediaController.this.sync;
                            __monitor_enter(v0);
                            try {
                                AndroidUtilities.runOnUIThread(new Runnable() {
                                    public void run() {
                                        float v10;
                                        float v0_1;
                                        long v6;
                                        long v4;
                                        if(this.this$1.val$currentPlayingMessageObject == null) {
                                            return;
                                        }

                                        if(this.this$1.this$0.audioPlayer == null && this.this$1.this$0.videoPlayer == null) {
                                            return;
                                        }

                                        if(this.this$1.this$0.isPaused) {
                                            return;
                                        }

                                        try {
                                            float v1 = 0f;
                                            long v2 = 0;
                                            if(this.this$1.this$0.videoPlayer != null) {
                                                v4 = this.this$1.this$0.videoPlayer.getDuration();
                                                v6 = this.this$1.this$0.videoPlayer.getCurrentPosition();
                                                float v8 = ((float)v4);
                                                v0_1 = (((float)this.this$1.this$0.videoPlayer.getBufferedPosition())) / v8;
                                                if(v4 >= v2) {
                                                    v1 = (((float)v6)) / v8;
                                                }

                                                if(v6 < v2) {
                                                    return;
                                                }

                                                if(v1 >= 1f) {
                                                    return;
                                                }

                                                v10 = v0_1;
                                                v0_1 = v1;
                                            }
                                            else {
                                                v4 = this.this$1.this$0.audioPlayer.getDuration();
                                                v6 = this.this$1.this$0.audioPlayer.getCurrentPosition();
                                                long v8_1 = -9223372036854775807L;
                                                v0_1 = v4 == v8_1 || v4 < v2 ? 0f : (((float)v6)) / (((float)v4));
                                                v10 = (((float)this.this$1.this$0.audioPlayer.getBufferedPosition())) / (((float)v4));
                                                if(v4 != v8_1 && v6 >= v2) {
                                                    if(this.this$1.this$0.seekToProgressPending != 0f) {
                                                    }
                                                    else {
                                                        goto label_77;
                                                    }
                                                }

                                                return;
                                            }

                                        label_77:
                                            this.this$1.this$0.lastProgress = v6;
                                            this.this$1.val$currentPlayingMessageObject.audioPlayerDuration = ((int)(v4 / 1000));
                                            this.this$1.val$currentPlayingMessageObject.audioProgress = v0_1;
                                            this.this$1.val$currentPlayingMessageObject.audioProgressSec = ((int)(this.this$1.this$0.lastProgress / 1000));
                                            this.this$1.val$currentPlayingMessageObject.bufferedProgress = v10;
                                            NotificationCenter.getInstance(this.this$1.val$currentPlayingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.this$1.val$currentPlayingMessageObject.getId()), Float.valueOf(v0_1)});
                                        }
                                        catch(Exception v0) {
                                            FileLog.e(((Throwable)v0));
                                        }
                                    }
                                });
                                __monitor_exit(v0);
                                return;
                            label_9:
                                __monitor_exit(v0);
                            }
                            catch(Throwable v1) {
                                goto label_9;
                            }

                            throw v1;
                        }
                    }, 0, 17);
                    __monitor_exit(v0);
                    return;
                label_24:
                    __monitor_exit(v0);
                }
                catch(Throwable v9) {
                    goto label_24;
                }
            }
        }
        catch(Throwable v9) {
            goto label_24;
        }

        throw v9;
    }

    public void startRaiseToEarSensors(ChatActivity arg7) {
        if(arg7 != null) {
            if(this.accelerometerSensor == null) {
                if(this.gravitySensor == null) {
                }
                else if(this.linearAcceleration != null) {
                    goto label_7;
                }

                return;
            }

        label_7:
            if(this.proximitySensor == null) {
                return;
            }

            this.raiseChat = arg7;
            if(!SharedConfig.raiseToSpeak) {
                if(this.playingMessageObject != null) {
                    if(this.playingMessageObject.isVoice()) {
                    }
                    else if(!this.playingMessageObject.isRoundVideo()) {
                        return;
                    }

                    goto label_22;
                }

                return;
            }

        label_22:
            if(this.sensorsStarted) {
                return;
            }

            float[] v7 = this.gravity;
            float[] v0 = this.gravity;
            this.gravity[2] = 0f;
            v0[1] = 0f;
            v7[0] = 0f;
            v7 = this.linearAcceleration;
            float[] v4 = this.linearAcceleration;
            this.linearAcceleration[2] = 0f;
            v4[1] = 0f;
            v7[0] = 0f;
            v7 = this.gravityFast;
            v4 = this.gravityFast;
            this.gravityFast[2] = 0f;
            v4[1] = 0f;
            v7[0] = 0f;
            this.lastTimestamp = 0;
            this.previousAccValue = 0f;
            this.raisedToTop = 0;
            this.raisedToTopSign = 0;
            this.countLess = 0;
            this.raisedToBack = 0;
            Utilities.globalQueue.postRunnable(new Runnable() {
                public void run() {
                    int v1 = 30000;
                    if(MediaController.this.gravitySensor != null) {
                        MediaController.this.sensorManager.registerListener(MediaController.this, MediaController.this.gravitySensor, v1);
                    }

                    if(MediaController.this.linearSensor != null) {
                        MediaController.this.sensorManager.registerListener(MediaController.this, MediaController.this.linearSensor, v1);
                    }

                    if(MediaController.this.accelerometerSensor != null) {
                        MediaController.this.sensorManager.registerListener(MediaController.this, MediaController.this.accelerometerSensor, v1);
                    }

                    MediaController.this.sensorManager.registerListener(MediaController.this, MediaController.this.proximitySensor, 3);
                }
            });
            this.sensorsStarted = true;
        }
    }

    private native int startRecord(String arg1) {
    }

    public void startRecording(int arg10, long arg11, MessageObject arg13) {
        int v0;
        if(this.playingMessageObject != null && (this.isPlayingMessage(this.playingMessageObject)) && !this.isMessagePaused()) {
            v0 = 1;
            this.pauseMessage(this.playingMessageObject);
            try {
            label_12:
                this.feedbackView.performHapticFeedback(3, 2);
            }
            catch(Exception ) {
            }
        }
        else {
            v0 = 0;
            goto label_12;
        }

        DispatchQueue v1 = this.recordQueue;
        org.telegram.messenger.MediaController$17 v8 = new Runnable(arg10, arg11, arg13) {
            public void run() {
                org.telegram.messenger.MediaController$17$3 v0_2;
                if(MediaController.this.audioRecorder != null) {
                    org.telegram.messenger.MediaController$17$1 v0 = new Runnable() {
                        public void run() {
                            this.this$1.this$0.recordStartRunnable = null;
                            NotificationCenter.getInstance(this.this$1.val$currentAccount).postNotificationName(NotificationCenter.recordStartError, new Object[0]);
                        }
                    };
                    goto label_5;
                }

                MediaController.this.recordingAudio = new TL_document();
                MediaController.this.recordingAudio.dc_id = -2147483648;
                MediaController.this.recordingAudio.id = ((long)SharedConfig.getLastLocalId());
                MediaController.this.recordingAudio.user_id = UserConfig.getInstance(this.val$currentAccount).getClientUserId();
                MediaController.this.recordingAudio.mime_type = "audio/ogg";
                MediaController.this.recordingAudio.thumb = new TL_photoSizeEmpty();
                MediaController.this.recordingAudio.thumb.type = "s";
                SharedConfig.saveConfig();
                MediaController.this.recordingAudioFile = new File(FileLoader.getDirectory(4), FileLoader.getAttachFileName(MediaController.this.recordingAudio));
                try {
                    if(MediaController.this.startRecord(MediaController.this.recordingAudioFile.getAbsolutePath()) == 0) {
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                this.this$1.this$0.recordStartRunnable = null;
                                NotificationCenter.getInstance(this.this$1.val$currentAccount).postNotificationName(NotificationCenter.recordStartError, new Object[0]);
                            }
                        });
                        return;
                    }

                    MediaController.this.audioRecorder = new AudioRecord(1, 16000, 16, 2, MediaController.this.recordBufferSize * 10);
                    MediaController.this.recordStartTime = System.currentTimeMillis();
                    MediaController.this.recordTimeCount = 0;
                    MediaController.this.samplesCount = 0;
                    MediaController.this.recordDialogId = this.val$dialog_id;
                    MediaController.this.recordingCurrentAccount = this.val$currentAccount;
                    MediaController.this.recordReplyingMessageObject = this.val$reply_to_msg;
                    MediaController.this.fileBuffer.rewind();
                    MediaController.this.audioRecorder.startRecording();
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                    TL_document v1 = null;
                    MediaController.this.recordingAudio = v1;
                    MediaController.this.stopRecord();
                    MediaController.this.recordingAudioFile.delete();
                    MediaController.this.recordingAudioFile = ((File)v1);
                    try {
                        MediaController.this.audioRecorder.release();
                        MediaController.this.audioRecorder = ((AudioRecord)v1);
                    }
                    catch(Exception v0_1) {
                        FileLog.e(((Throwable)v0_1));
                    }

                    v0_2 = new Runnable() {
                        public void run() {
                            this.this$1.this$0.recordStartRunnable = null;
                            NotificationCenter.getInstance(this.this$1.val$currentAccount).postNotificationName(NotificationCenter.recordStartError, new Object[0]);
                        }
                    };
                    goto label_5;
                }

                MediaController.this.recordQueue.postRunnable(MediaController.this.recordRunnable);
                org.telegram.messenger.MediaController$17$4 v0_3 = new Runnable() {
                    public void run() {
                        this.this$1.this$0.recordStartRunnable = null;
                        NotificationCenter.getInstance(this.this$1.val$currentAccount).postNotificationName(NotificationCenter.recordStarted, new Object[0]);
                    }
                };
            label_5:
                AndroidUtilities.runOnUIThread(((Runnable)v0_2));
            }
        };
        this.recordStartRunnable = ((Runnable)v8);
        long v10 = v0 != 0 ? 500 : 50;
        v1.postRunnable(((Runnable)v8), v10);
    }

    public void startRecordingIfFromSpeaker() {
        if((this.useFrontSpeaker) && this.raiseChat != null) {
            if(!this.allowStartRecord) {
            }
            else {
                this.raiseToEarRecord = true;
                this.startRecording(this.raiseChat.getCurrentAccount(), this.raiseChat.getDialogId(), null);
                this.ignoreOnPause = true;
            }
        }
    }

    public void startSmsObserver() {
        try {
            if(this.smsObserver == null) {
                ContentResolver v0_1 = ApplicationLoader.applicationContext.getContentResolver();
                Uri v1 = Uri.parse("content://sms");
                SmsObserver v3 = new SmsObserver(this);
                this.smsObserver = v3;
                v0_1.registerContentObserver(v1, false, ((ContentObserver)v3));
            }

            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    try {
                        if(MediaController.this.smsObserver == null) {
                            return;
                        }

                        ApplicationLoader.applicationContext.getContentResolver().unregisterContentObserver(MediaController.this.smsObserver);
                        MediaController.this.smsObserver = null;
                    }
                    catch(Exception v0) {
                        FileLog.e(((Throwable)v0));
                    }
                }
            }, 300000);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    private boolean startVideoConvertFromQueue() {
        int v1 = 0;
        if(!this.videoConvertQueue.isEmpty()) {
            Object v0 = this.videoConvertSync;
            __monitor_enter(v0);
            try {
                this.cancelCurrentVideoConversion = false;
                __monitor_exit(v0);
            }
            catch(Throwable v1_1) {
                try {
                label_54:
                    __monitor_exit(v0);
                }
                catch(Throwable v1_1) {
                    goto label_54;
                }

                throw v1_1;
            }

            v0 = this.videoConvertQueue.get(0);
            Intent v2 = new Intent(ApplicationLoader.applicationContext, VideoEncodingService.class);
            v2.putExtra("path", ((MessageObject)v0).messageOwner.attachPath);
            v2.putExtra("currentAccount", ((MessageObject)v0).currentAccount);
            if(((MessageObject)v0).messageOwner.media.document != null) {
                while(v1 < ((MessageObject)v0).messageOwner.media.document.attributes.size()) {
                    if((((MessageObject)v0).messageOwner.media.document.attributes.get(v1) instanceof TL_documentAttributeAnimated)) {
                        v2.putExtra("gif", true);
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    break;
                }
            }

            if(((MessageObject)v0).getId() != 0) {
                try {
                    ApplicationLoader.applicationContext.startService(v2);
                }
                catch(Throwable v1_1) {
                    FileLog.e(v1_1);
                }
            }

            VideoConvertRunnable.runConversion(((MessageObject)v0));
            return 1;
        }

        return 0;
    }

    public void stopAudio() {
        VideoPlayer v0_1;
        if(this.audioPlayer == null && this.videoPlayer == null || this.playingMessageObject == null) {
            return;
        }

        try {
            if(this.audioPlayer != null) {
                v0_1 = this.audioPlayer;
            }
            else if(this.videoPlayer != null) {
                v0_1 = this.videoPlayer;
            }
            else {
                goto label_18;
            }

            v0_1.pause();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }

    label_18:
        VideoPlayer v1 = null;
        try {
            if(this.audioPlayer != null) {
                this.audioPlayer.releasePlayer();
                this.audioPlayer = v1;
                goto label_45;
            }

            if(this.videoPlayer == null) {
                goto label_45;
            }

            this.currentAspectRatioFrameLayout = ((AspectRatioFrameLayout)v1);
            this.currentTextureViewContainer = ((FrameLayout)v1);
            this.currentAspectRatioFrameLayoutReady = false;
            this.currentTextureView = ((TextureView)v1);
            this.videoPlayer.releasePlayer();
            this.videoPlayer = v1;
        }
        catch(Exception v2) {
            goto label_44;
        }

        try {
            this.baseActivity.getWindow().clearFlags(128);
        }
        catch(Exception v2) {
            try {
                FileLog.e(((Throwable)v2));
            }
            catch(Exception v2) {
            label_44:
                FileLog.e(((Throwable)v2));
            }
        }

    label_45:
        this.stopProgressTimer();
        this.playingMessageObject = ((MessageObject)v1);
        this.downloadingCurrentMessage = false;
        this.isPaused = false;
        ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
    }

    public void stopMediaObserver() {
        if(this.stopMediaObserverRunnable == null) {
            this.stopMediaObserverRunnable = new StopMediaObserverRunnable(this, null);
        }

        this.stopMediaObserverRunnable.currentObserverToken = this.startObserverToken;
        ApplicationLoader.applicationHandler.postDelayed(this.stopMediaObserverRunnable, 5000);
    }

    private void stopProgressTimer() {
        Object v0 = this.progressTimerSync;
        __monitor_enter(v0);
        try {
            if(this.progressTimer == null) {
                goto label_11;
            }

            try {
                this.progressTimer.cancel();
                this.progressTimer = null;
                goto label_11;
            }
            catch(Exception v1_1) {
                try {
                    FileLog.e(((Throwable)v1_1));
                label_11:
                    __monitor_exit(v0);
                    return;
                label_14:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_14;
                }
            }
        }
        catch(Throwable v1) {
            goto label_14;
        }

        throw v1;
    }

    public void stopRaiseToEarSensors(ChatActivity arg3, boolean arg4) {
        if(this.ignoreOnPause) {
            this.ignoreOnPause = false;
            return;
        }

        int v4 = arg4 ? 2 : 0;
        this.stopRecording(v4);
        if((this.sensorsStarted) && !this.ignoreOnPause) {
            if(this.accelerometerSensor == null) {
                if(this.gravitySensor == null) {
                }
                else if(this.linearAcceleration != null) {
                    goto label_20;
                }

                return;
            }

        label_20:
            if(this.proximitySensor == null) {
                return;
            }

            if(this.raiseChat != arg3) {
                return;
            }

            this.raiseChat = null;
            this.sensorsStarted = false;
            this.accelerometerVertical = false;
            this.proximityTouched = false;
            this.raiseToEarRecord = false;
            this.useFrontSpeaker = false;
            Utilities.globalQueue.postRunnable(new Runnable() {
                public void run() {
                    if(MediaController.this.linearSensor != null) {
                        MediaController.this.sensorManager.unregisterListener(MediaController.this, MediaController.this.linearSensor);
                    }

                    if(MediaController.this.gravitySensor != null) {
                        MediaController.this.sensorManager.unregisterListener(MediaController.this, MediaController.this.gravitySensor);
                    }

                    if(MediaController.this.accelerometerSensor != null) {
                        MediaController.this.sensorManager.unregisterListener(MediaController.this, MediaController.this.accelerometerSensor);
                    }

                    MediaController.this.sensorManager.unregisterListener(MediaController.this, MediaController.this.proximitySensor);
                }
            });
            if(!this.proximityHasDifferentValues) {
                return;
            }

            if(this.proximityWakeLock == null) {
                return;
            }

            if(!this.proximityWakeLock.isHeld()) {
                return;
            }

            this.proximityWakeLock.release();
        }
    }

    private native void stopRecord() {
    }

    public void stopRecording(int arg3) {
        if(this.recordStartRunnable != null) {
            this.recordQueue.cancelRunnable(this.recordStartRunnable);
            this.recordStartRunnable = null;
        }

        this.recordQueue.postRunnable(new Runnable(arg3) {
            public void run() {
                if(MediaController.this.audioRecorder == null) {
                    return;
                }

                try {
                    MediaController.this.sendAfterDone = this.val$send;
                    MediaController.this.audioRecorder.stop();
                }
                catch(Exception v0) {
                    FileLog.e(((Throwable)v0));
                    if(MediaController.this.recordingAudioFile == null) {
                        goto label_19;
                    }

                    MediaController.this.recordingAudioFile.delete();
                }

            label_19:
                if(this.val$send == 0) {
                    MediaController.this.stopRecordingInternal(0);
                }

                try {
                    MediaController.this.feedbackView.performHapticFeedback(3, 2);
                    goto label_29;
                }
                catch(Exception ) {
                label_29:
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            NotificationCenter v0 = NotificationCenter.getInstance(this.this$1.this$0.recordingCurrentAccount);
                            int v1 = NotificationCenter.recordStopped;
                            int v2 = 1;
                            Object[] v3 = new Object[1];
                            if(this.this$1.val$send == 2) {
                            }
                            else {
                                v2 = 0;
                            }

                            v3[0] = Integer.valueOf(v2);
                            v0.postNotificationName(v1, v3);
                        }
                    });
                    return;
                }
            }
        });
    }

    private void stopRecordingInternal(int arg5) {
        if(arg5 != 0) {
            this.fileEncodingQueue.postRunnable(new Runnable(this.recordingAudio, this.recordingAudioFile, arg5) {
                public void run() {
                    MediaController.this.stopRecord();
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            String v7_1;
                            TL_document v6;
                            this.this$1.val$audioToSend.date = ConnectionsManager.getInstance(this.this$1.this$0.recordingCurrentAccount).getCurrentTime();
                            this.this$1.val$audioToSend.size = ((int)this.this$1.val$recordingAudioFileToSend.length());
                            TL_documentAttributeAudio v0 = new TL_documentAttributeAudio();
                            v0.voice = true;
                            v0.waveform = this.this$1.this$0.getWaveform2(this.this$1.this$0.recordSamples, this.this$1.this$0.recordSamples.length);
                            if(v0.waveform != null) {
                                v0.flags |= 4;
                            }

                            long v2 = this.this$1.this$0.recordTimeCount;
                            v0.duration = ((int)(this.this$1.this$0.recordTimeCount / 1000));
                            this.this$1.val$audioToSend.attributes.add(v0);
                            if(v2 > 700) {
                                if(this.this$1.val$send == 1) {
                                    if((ApplicationLoader.applicationContext.getSharedPreferences("plusconfig", 0).getBoolean("confirmForStickers", true)) && this.this$1.this$0.raiseChat != null && this.this$1.this$0.raiseChat.getParentActivity() != null) {
                                        new AlertDialog$Builder(this.this$1.this$0.raiseChat.getParentActivity()).setMessage("برای ارسال صدا مطمین هستید؟").setPositiveButton("بله", new DialogInterface$OnClickListener() {
                                            public void onClick(DialogInterface arg13, int arg14) {
                                                SendMessagesHelper.getInstance(this.this$2.this$1.this$0.recordingCurrentAccount).sendMessage(this.this$2.this$1.val$audioToSend, null, this.this$2.this$1.val$recordingAudioFileToSend.getAbsolutePath(), this.this$2.this$1.this$0.recordDialogId, this.this$2.this$1.this$0.recordReplyingMessageObject, null, null, null, null, 0);
                                            }
                                        }).setNegativeButton("خیر", new DialogInterface$OnClickListener() {
                                            public void onClick(DialogInterface arg1, int arg2) {
                                            }
                                        }).setIcon(17301543).show();
                                        goto label_112;
                                    }

                                    SendMessagesHelper.getInstance(this.this$1.this$0.recordingCurrentAccount).sendMessage(this.this$1.val$audioToSend, null, this.this$1.val$recordingAudioFileToSend.getAbsolutePath(), this.this$1.this$0.recordDialogId, this.this$1.this$0.recordReplyingMessageObject, null, null, null, null, 0);
                                }

                            label_112:
                                NotificationCenter v0_1 = NotificationCenter.getInstance(this.this$1.this$0.recordingCurrentAccount);
                                int v3 = NotificationCenter.audioDidSent;
                                int v4 = 2;
                                Object[] v5 = new Object[v4];
                                Object v7 = null;
                                if(this.this$1.val$send == v4) {
                                    v6 = this.this$1.val$audioToSend;
                                }
                                else {
                                    Object v6_1 = v7;
                                }

                                v5[0] = v6;
                                if(this.this$1.val$send == v4) {
                                    v7_1 = this.this$1.val$recordingAudioFileToSend.getAbsolutePath();
                                }

                                v5[1] = v7_1;
                                v0_1.postNotificationName(v3, v5);
                            }
                            else {
                                this.this$1.val$recordingAudioFileToSend.delete();
                            }
                        }
                    });
                }
            });
        }

        AudioRecord v5 = null;
        try {
            if(this.audioRecorder == null) {
                goto label_16;
            }

            this.audioRecorder.release();
            this.audioRecorder = v5;
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }

    label_16:
        this.recordingAudio = ((TL_document)v5);
        this.recordingAudioFile = ((File)v5);
    }

    public void toggleShuffleMusic(int arg2) {
        boolean v0 = SharedConfig.shuffleMusic;
        SharedConfig.toggleShuffleMusic(arg2);
        if(v0 != SharedConfig.shuffleMusic) {
            if(SharedConfig.shuffleMusic) {
                this.buildShuffledPlayList();
                this.currentPlaylistNum = 0;
            }
            else if(this.playingMessageObject != null) {
                this.currentPlaylistNum = this.playlist.indexOf(this.playingMessageObject);
                if(this.currentPlaylistNum == -1) {
                    this.playlist.clear();
                    this.shuffledPlaylist.clear();
                    this.cleanupPlayer(true, true);
                }
            }
        }
    }

    private native int writeFrame(ByteBuffer arg1, int arg2) {
    }
}

