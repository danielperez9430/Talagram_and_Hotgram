package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import org.telegram.customization.Model.MediaUsageStatistics;
import org.telegram.customization.Model.NetworkUsage;

public class StatsController {
    final class org.telegram.messenger.StatsController$1 extends ThreadLocal {
        org.telegram.messenger.StatsController$1() {
            super();
        }

        protected Long initialValue() {
            return Long.valueOf(System.currentTimeMillis() - 1000);
        }

        protected Object initialValue() {
            return this.initialValue();
        }
    }

    class org.telegram.messenger.StatsController$2 implements Runnable {
        org.telegram.messenger.StatsController$2(StatsController arg1) {
            StatsController.this = arg1;
            super();
        }

        public void run() {
            int v5;
            int v4;
            long v0 = System.currentTimeMillis();
            if(Math.abs(v0 - StatsController.this.lastInternalStatsSaveTime) < 2000) {
                return;
            }

            StatsController.this.lastInternalStatsSaveTime = v0;
            try {
                StatsController.this.statsFile.seek(0);
                int v1;
                for(v1 = 0; v1 < 3; ++v1) {
                    int v2;
                    for(v2 = 0; true; ++v2) {
                        v4 = 8;
                        v5 = 4;
                        if(v2 >= 7) {
                            break;
                        }

                        StatsController.this.statsFile.write(StatsController.this.longToBytes(StatsController.this.sentBytes[v1][v2]), 0, v4);
                        StatsController.this.statsFile.write(StatsController.this.longToBytes(StatsController.this.receivedBytes[v1][v2]), 0, v4);
                        StatsController.this.statsFile.write(StatsController.this.intToBytes(StatsController.this.sentItems[v1][v2]), 0, v5);
                        StatsController.this.statsFile.write(StatsController.this.intToBytes(StatsController.this.receivedItems[v1][v2]), 0, v5);
                    }

                    StatsController.this.statsFile.write(StatsController.this.intToBytes(StatsController.this.callsTotalTime[v1]), 0, v5);
                    StatsController.this.statsFile.write(StatsController.this.longToBytes(StatsController.this.resetStatsDate[v1]), 0, v4);
                }

                StatsController.this.statsFile.getFD().sync();
                return;
            }
            catch(Exception ) {
                return;
            }
        }
    }

    private static volatile StatsController[] Instance = null;
    private static final int TYPES_COUNT = 7;
    public static final int TYPE_AUDIOS = 3;
    public static final int TYPE_CALLS = 0;
    public static final int TYPE_FILES = 5;
    public static final int TYPE_MESSAGES = 1;
    public static final int TYPE_MOBILE = 0;
    public static final int TYPE_PHOTOS = 4;
    public static final int TYPE_ROAMING = 2;
    public static final int TYPE_TOTAL = 6;
    public static final int TYPE_VIDEOS = 2;
    public static final int TYPE_WIFI = 1;
    private byte[] buffer;
    private int[] callsTotalTime;
    private long lastInternalStatsSaveTime;
    private static final ThreadLocal lastStatsSaveTime;
    private long[][] receivedBytes;
    private int[][] receivedItems;
    private long[] resetStatsDate;
    private Runnable saveRunnable;
    private long[][] sentBytes;
    private int[][] sentItems;
    private RandomAccessFile statsFile;
    private static DispatchQueue statsSaveQueue;

    static {
        StatsController.statsSaveQueue = new DispatchQueue("statsSaveQueue");
        StatsController.lastStatsSaveTime = new org.telegram.messenger.StatsController$1();
        StatsController.Instance = new StatsController[3];
    }

    private StatsController(int arg15) {
        SharedPreferences v15;
        int v10;
        int v8;
        int v3_1;
        super();
        int v0 = 8;
        this.buffer = new byte[v0];
        int v1 = 7;
        int v2 = 3;
        this.sentBytes = new long[v2][v1];
        this.receivedBytes = new long[v2][v1];
        this.sentItems = new int[v2][v1];
        this.receivedItems = new int[v2][v1];
        this.resetStatsDate = new long[v2];
        this.callsTotalTime = new int[v2];
        this.saveRunnable = new org.telegram.messenger.StatsController$2(this);
        File v3 = ApplicationLoader.getFilesDirFixed();
        if(arg15 != 0) {
            File v4 = ApplicationLoader.getFilesDirFixed();
            StringBuilder v5 = new StringBuilder();
            v5.append("account");
            v5.append(arg15);
            v5.append("/");
            v3 = new File(v4, v5.toString());
            v3.mkdirs();
        }

        long v4_1 = 0;
        try {
            this.statsFile = new RandomAccessFile(new File(v3, "stats2.dat"), "rw");
            if(this.statsFile.length() > v4_1) {
                v3_1 = 0;
                v8 = 0;
                while(v3_1 < v2) {
                    int v9;
                    for(v9 = 0; true; ++v9) {
                        v10 = 4;
                        if(v9 >= v1) {
                            break;
                        }

                        this.statsFile.readFully(this.buffer, 0, v0);
                        this.sentBytes[v3_1][v9] = this.bytesToLong(this.buffer);
                        this.statsFile.readFully(this.buffer, 0, v0);
                        this.receivedBytes[v3_1][v9] = this.bytesToLong(this.buffer);
                        this.statsFile.readFully(this.buffer, 0, v10);
                        this.sentItems[v3_1][v9] = this.bytesToInt(this.buffer);
                        this.statsFile.readFully(this.buffer, 0, v10);
                        this.receivedItems[v3_1][v9] = this.bytesToInt(this.buffer);
                    }

                    this.statsFile.readFully(this.buffer, 0, v10);
                    this.callsTotalTime[v3_1] = this.bytesToInt(this.buffer);
                    this.statsFile.readFully(this.buffer, 0, v0);
                    this.resetStatsDate[v3_1] = this.bytesToLong(this.buffer);
                    if(this.resetStatsDate[v3_1] == v4_1) {
                        this.resetStatsDate[v3_1] = System.currentTimeMillis();
                        v8 = 1;
                    }

                    ++v3_1;
                }

                if(v8 != 0) {
                    this.saveStats();
                }
            }
            else {
                goto label_123;
            }
        }
        catch(Exception ) {
            goto label_123;
        }

        v0 = 0;
        goto label_124;
    label_123:
        v0 = 1;
    label_124:
        if(v0 != 0) {
            if(arg15 == 0) {
                v15 = ApplicationLoader.applicationContext.getSharedPreferences("stats", 0);
            }
            else {
                Context v0_1 = ApplicationLoader.applicationContext;
                StringBuilder v3_2 = new StringBuilder();
                v3_2.append("stats");
                v3_2.append(arg15);
                v15 = v0_1.getSharedPreferences(v3_2.toString(), 0);
            }

            v0 = 0;
            v3_1 = 0;
            while(v0 < v2) {
                int[] v8_1 = this.callsTotalTime;
                StringBuilder v9_1 = new StringBuilder();
                v9_1.append("callsTotalTime");
                v9_1.append(v0);
                v8_1[v0] = v15.getInt(v9_1.toString(), 0);
                long[] v8_2 = this.resetStatsDate;
                v9_1 = new StringBuilder();
                v9_1.append("resetStatsDate");
                v9_1.append(v0);
                v8_2[v0] = v15.getLong(v9_1.toString(), v4_1);
                for(v8 = 0; v8 < v1; ++v8) {
                    long[] v9_2 = this.sentBytes[v0];
                    StringBuilder v10_1 = new StringBuilder();
                    v10_1.append("sentBytes");
                    v10_1.append(v0);
                    v10_1.append("_");
                    v10_1.append(v8);
                    v9_2[v8] = v15.getLong(v10_1.toString(), v4_1);
                    v9_2 = this.receivedBytes[v0];
                    v10_1 = new StringBuilder();
                    v10_1.append("receivedBytes");
                    v10_1.append(v0);
                    v10_1.append("_");
                    v10_1.append(v8);
                    v9_2[v8] = v15.getLong(v10_1.toString(), v4_1);
                    int[] v9_3 = this.sentItems[v0];
                    v10_1 = new StringBuilder();
                    v10_1.append("sentItems");
                    v10_1.append(v0);
                    v10_1.append("_");
                    v10_1.append(v8);
                    v9_3[v8] = v15.getInt(v10_1.toString(), 0);
                    v9_3 = this.receivedItems[v0];
                    v10_1 = new StringBuilder();
                    v10_1.append("receivedItems");
                    v10_1.append(v0);
                    v10_1.append("_");
                    v10_1.append(v8);
                    v9_3[v8] = v15.getInt(v10_1.toString(), 0);
                }

                if(this.resetStatsDate[v0] == v4_1) {
                    this.resetStatsDate[v0] = System.currentTimeMillis();
                    v3_1 = 1;
                }

                ++v0;
            }

            if(v3_1 == 0) {
                return;
            }

            this.saveStats();
        }
    }

    static long access$000(StatsController arg2) {
        return arg2.lastInternalStatsSaveTime;
    }

    static long access$002(StatsController arg0, long arg1) {
        arg0.lastInternalStatsSaveTime = arg1;
        return arg1;
    }

    static RandomAccessFile access$100(StatsController arg0) {
        return arg0.statsFile;
    }

    static long[][] access$200(StatsController arg0) {
        return arg0.sentBytes;
    }

    static byte[] access$300(StatsController arg0, long arg1) {
        return arg0.longToBytes(arg1);
    }

    static long[][] access$400(StatsController arg0) {
        return arg0.receivedBytes;
    }

    static int[][] access$500(StatsController arg0) {
        return arg0.sentItems;
    }

    static byte[] access$600(StatsController arg0, int arg1) {
        return arg0.intToBytes(arg1);
    }

    static int[][] access$700(StatsController arg0) {
        return arg0.receivedItems;
    }

    static int[] access$800(StatsController arg0) {
        return arg0.callsTotalTime;
    }

    static long[] access$900(StatsController arg0) {
        return arg0.resetStatsDate;
    }

    private int bytesToInt(byte[] arg3) {
        return arg3[3] & 255 | (arg3[0] << 24 | (arg3[1] & 255) << 16 | (arg3[2] & 255) << 8);
    }

    private long bytesToLong(byte[] arg8) {
        return ((((long)arg8[0])) & 255) << 56 | ((((long)arg8[1])) & 255) << 48 | ((((long)arg8[2])) & 255) << 40 | ((((long)arg8[3])) & 255) << 32 | ((((long)arg8[4])) & 255) << 24 | ((((long)arg8[5])) & 255) << 16 | ((((long)arg8[6])) & 255) << 8 | 255 & (((long)arg8[7]));
    }

    public int getCallsTotalTime(int arg2) {
        return this.callsTotalTime[arg2];
    }

    public static StatsController getInstance(int arg3) {
        StatsController v0 = StatsController.Instance[arg3];
        if(v0 == null) {
            Class v1 = StatsController.class;
            __monitor_enter(v1);
            try {
                v0 = StatsController.Instance[arg3];
                if(v0 == null) {
                    StatsController[] v0_1 = StatsController.Instance;
                    StatsController v2 = new StatsController(arg3);
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

    public ArrayList getNetworkUsageStatistics() {
        ArrayList v0 = new ArrayList();
        int v2;
        for(v2 = 0; v2 < 3; ++v2) {
            ArrayList v3 = new ArrayList();
            NetworkUsage v4 = new NetworkUsage();
            v4.setType(v2);
            v4.setCarrierName(v4.getCarrierName());
            v4.setTime(StatsController.getInstance(UserConfig.selectedAccount).getResetStatsDate(v2));
            int v5;
            for(v5 = 0; v5 < 7; ++v5) {
                MediaUsageStatistics v6 = new MediaUsageStatistics();
                v6.setSent(this.getSentItemsCount(v2, v5));
                v6.setReceived(this.getRecivedItemsCount(v2, v5));
                v6.setBytesSent(this.getSentBytesCount(v2, v5));
                v6.setBytesReceived(this.getReceivedBytesCount(v2, v5));
                v6.setMediaType(v5);
                v3.add(v6);
            }

            v4.setMediaUsageStatistics(v3);
            v0.add(v4);
        }

        return v0;
    }

    public long getReceivedBytesCount(int arg5, int arg6) {
        if(arg6 == 1) {
            return this.receivedBytes[arg5][6] - this.receivedBytes[arg5][5] - this.receivedBytes[arg5][3] - this.receivedBytes[arg5][2] - this.receivedBytes[arg5][4];
        }

        return this.receivedBytes[arg5][arg6];
    }

    public int getRecivedItemsCount(int arg2, int arg3) {
        return this.receivedItems[arg2][arg3];
    }

    public long getResetStatsDate(int arg4) {
        return this.resetStatsDate[arg4];
    }

    public long getSentBytesCount(int arg5, int arg6) {
        if(arg6 == 1) {
            return this.sentBytes[arg5][6] - this.sentBytes[arg5][5] - this.sentBytes[arg5][3] - this.sentBytes[arg5][2] - this.sentBytes[arg5][4];
        }

        return this.sentBytes[arg5][arg6];
    }

    public int getSentItemsCount(int arg2, int arg3) {
        return this.sentItems[arg2][arg3];
    }

    public void incrementReceivedBytesCount(int arg3, int arg4, long arg5) {
        this.receivedBytes[arg3][arg4] += arg5;
        this.saveStats();
    }

    public void incrementReceivedItemsCount(int arg2, int arg3, int arg4) {
        this.receivedItems[arg2][arg3] += arg4;
        this.saveStats();
    }

    public void incrementSentBytesCount(int arg3, int arg4, long arg5) {
        this.sentBytes[arg3][arg4] += arg5;
        this.saveStats();
    }

    public void incrementSentItemsCount(int arg2, int arg3, int arg4) {
        this.sentItems[arg2][arg3] += arg4;
        this.saveStats();
    }

    public void incrementTotalCallsTime(int arg3, int arg4) {
        this.callsTotalTime[arg3] += arg4;
        this.saveStats();
    }

    private byte[] intToBytes(int arg4) {
        this.buffer[0] = ((byte)(arg4 >>> 24));
        this.buffer[1] = ((byte)(arg4 >>> 16));
        this.buffer[2] = ((byte)(arg4 >>> 8));
        this.buffer[3] = ((byte)arg4);
        return this.buffer;
    }

    private byte[] longToBytes(long arg4) {
        this.buffer[0] = ((byte)(((int)(arg4 >>> 56))));
        this.buffer[1] = ((byte)(((int)(arg4 >>> 48))));
        this.buffer[2] = ((byte)(((int)(arg4 >>> 40))));
        this.buffer[3] = ((byte)(((int)(arg4 >>> 32))));
        this.buffer[4] = ((byte)(((int)(arg4 >>> 24))));
        this.buffer[5] = ((byte)(((int)(arg4 >>> 16))));
        this.buffer[6] = ((byte)(((int)(arg4 >>> 8))));
        this.buffer[7] = ((byte)(((int)arg4)));
        return this.buffer;
    }

    public void resetStats(int arg6) {
        this.resetStatsDate[arg6] = System.currentTimeMillis();
        int v1;
        for(v1 = 0; v1 < 7; ++v1) {
            this.sentBytes[arg6][v1] = 0;
            this.receivedBytes[arg6][v1] = 0;
            this.sentItems[arg6][v1] = 0;
            this.receivedItems[arg6][v1] = 0;
        }

        this.callsTotalTime[arg6] = 0;
        this.saveStats();
    }

    private void saveStats() {
        long v0 = System.currentTimeMillis();
        if(Math.abs(v0 - StatsController.lastStatsSaveTime.get().longValue()) >= 2000) {
            StatsController.lastStatsSaveTime.set(Long.valueOf(v0));
            StatsController.statsSaveQueue.postRunnable(this.saveRunnable);
        }
    }
}

