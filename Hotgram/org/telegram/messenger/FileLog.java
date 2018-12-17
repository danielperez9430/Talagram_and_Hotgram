package org.telegram.messenger;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import org.telegram.messenger.time.FastDateFormat;

public class FileLog {
    private static volatile FileLog Instance = null;
    private File currentFile;
    private FastDateFormat dateFormat;
    private boolean initied;
    private DispatchQueue logQueue;
    private File networkFile;
    private OutputStreamWriter streamWriter;
    private static final String tag = "tmessages";

    static {
    }

    public FileLog() {
        super();
        this.streamWriter = null;
        this.dateFormat = null;
        this.logQueue = null;
        this.currentFile = null;
        this.networkFile = null;
        if(!BuildVars.LOGS_ENABLED) {
            return;
        }

        this.init();
    }

    static FastDateFormat access$000(FileLog arg0) {
        return arg0.dateFormat;
    }

    static OutputStreamWriter access$100(FileLog arg0) {
        return arg0.streamWriter;
    }

    public static void cleanupLogs() {
        FileLog.ensureInitied();
        File v0 = ApplicationLoader.applicationContext.getExternalFilesDir(null);
        if(v0 == null) {
            return;
        }

        StringBuilder v2 = new StringBuilder();
        v2.append(v0.getAbsolutePath());
        v2.append("/logs");
        File[] v0_1 = new File(v2.toString()).listFiles();
        if(v0_1 != null) {
            int v1;
            for(v1 = 0; v1 < v0_1.length; ++v1) {
                File v2_1 = v0_1[v1];
                if(FileLog.getInstance().currentFile == null || !v2_1.getAbsolutePath().equals(FileLog.getInstance().currentFile.getAbsolutePath())) {
                    if(FileLog.getInstance().networkFile != null && (v2_1.getAbsolutePath().equals(FileLog.getInstance().networkFile.getAbsolutePath()))) {
                        goto label_42;
                    }

                    v2_1.delete();
                }
                else {
                }

            label_42:
            }
        }
    }

    public static void d(String arg2) {
        if(!BuildVars.LOGS_ENABLED) {
            return;
        }

        FileLog.ensureInitied();
        Log.d("tmessages", arg2);
        if(FileLog.getInstance().streamWriter != null) {
            FileLog.getInstance().logQueue.postRunnable(new Runnable(arg2) {
                public void run() {
                    try {
                        OutputStreamWriter v0_1 = FileLog.getInstance().streamWriter;
                        v0_1.write(FileLog.getInstance().dateFormat.format(System.currentTimeMillis()) + " D/tmessages: " + this.val$message + "\n");
                        FileLog.getInstance().streamWriter.flush();
                    }
                    catch(Exception v0) {
                        v0.printStackTrace();
                    }
                }
            });
        }
    }

    public static void e(Throwable arg2) {
        if(!BuildVars.LOGS_ENABLED) {
            return;
        }

        FileLog.ensureInitied();
        arg2.printStackTrace();
        if(FileLog.getInstance().streamWriter != null) {
            FileLog.getInstance().logQueue.postRunnable(new Runnable(arg2) {
                public void run() {
                    try {
                        OutputStreamWriter v0_1 = FileLog.getInstance().streamWriter;
                        v0_1.write(FileLog.getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: " + this.val$e + "\n");
                        StackTraceElement[] v0_2 = this.val$e.getStackTrace();
                        int v1_1;
                        for(v1_1 = 0; v1_1 < v0_2.length; ++v1_1) {
                            OutputStreamWriter v2 = FileLog.getInstance().streamWriter;
                            v2.write(FileLog.getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: " + v0_2[v1_1] + "\n");
                        }

                        FileLog.getInstance().streamWriter.flush();
                    }
                    catch(Exception v0) {
                        v0.printStackTrace();
                    }
                }
            });
        }
        else {
            arg2.printStackTrace();
        }
    }

    public static void e(String arg2) {
        if(!BuildVars.LOGS_ENABLED) {
            return;
        }

        FileLog.ensureInitied();
        Log.e("tmessages", arg2);
        if(FileLog.getInstance().streamWriter != null) {
            FileLog.getInstance().logQueue.postRunnable(new Runnable(arg2) {
                public void run() {
                    try {
                        OutputStreamWriter v0_1 = FileLog.getInstance().streamWriter;
                        v0_1.write(FileLog.getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: " + this.val$message + "\n");
                        FileLog.getInstance().streamWriter.flush();
                    }
                    catch(Exception v0) {
                        v0.printStackTrace();
                    }
                }
            });
        }
    }

    public static void e(String arg2, Throwable arg3) {
        if(!BuildVars.LOGS_ENABLED) {
            return;
        }

        FileLog.ensureInitied();
        Log.e("tmessages", arg2, arg3);
        if(FileLog.getInstance().streamWriter != null) {
            FileLog.getInstance().logQueue.postRunnable(new Runnable(arg2, arg3) {
                public void run() {
                    try {
                        OutputStreamWriter v0_1 = FileLog.getInstance().streamWriter;
                        v0_1.write(FileLog.getInstance().dateFormat.format(System.currentTimeMillis()) + " E/tmessages: " + this.val$message + "\n");
                        FileLog.getInstance().streamWriter.write(this.val$exception.toString());
                        FileLog.getInstance().streamWriter.flush();
                    }
                    catch(Exception v0) {
                        v0.printStackTrace();
                    }
                }
            });
        }
    }

    public static void ensureInitied() {
        FileLog.getInstance().init();
    }

    public static FileLog getInstance() {
        FileLog v0 = FileLog.Instance;
        if(v0 == null) {
            Class v1 = FileLog.class;
            __monitor_enter(v1);
            try {
                v0 = FileLog.Instance;
                if(v0 == null) {
                    v0 = new FileLog();
                    FileLog.Instance = v0;
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

    public static String getNetworkLogPath() {
        if(!BuildVars.LOGS_ENABLED) {
            return "";
        }

        try {
            File v0_1 = ApplicationLoader.applicationContext.getExternalFilesDir(null);
            if(v0_1 == null) {
                return "";
            }

            StringBuilder v2 = new StringBuilder();
            v2.append(v0_1.getAbsolutePath());
            v2.append("/logs");
            File v1 = new File(v2.toString());
            v1.mkdirs();
            FileLog v0_2 = FileLog.getInstance();
            StringBuilder v3 = new StringBuilder();
            v3.append(FileLog.getInstance().dateFormat.format(System.currentTimeMillis()));
            v3.append("_net.txt");
            v0_2.networkFile = new File(v1, v3.toString());
            return FileLog.getInstance().networkFile.getAbsolutePath();
        }
        catch(Throwable v0) {
            v0.printStackTrace();
            return "";
        }
    }

    public void init() {
        if(this.initied) {
            return;
        }

        this.dateFormat = FastDateFormat.getInstance("dd_MM_yyyy_HH_mm_ss", Locale.US);
        try {
            File v0_1 = ApplicationLoader.applicationContext.getExternalFilesDir(null);
            if(v0_1 == null) {
                return;
            }

            StringBuilder v2 = new StringBuilder();
            v2.append(v0_1.getAbsolutePath());
            v2.append("/logs");
            File v1 = new File(v2.toString());
            v1.mkdirs();
            v2 = new StringBuilder();
            v2.append(this.dateFormat.format(System.currentTimeMillis()));
            v2.append(".txt");
            this.currentFile = new File(v1, v2.toString());
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }

        try {
            this.logQueue = new DispatchQueue("logQueue");
            this.currentFile.createNewFile();
            this.streamWriter = new OutputStreamWriter(new FileOutputStream(this.currentFile));
            OutputStreamWriter v0_2 = this.streamWriter;
            v0_2.write("-----start log " + this.dateFormat.format(System.currentTimeMillis()) + "-----\n");
            this.streamWriter.flush();
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }

        this.initied = true;
    }

    public static void w(String arg2) {
        if(!BuildVars.LOGS_ENABLED) {
            return;
        }

        FileLog.ensureInitied();
        Log.w("tmessages", arg2);
        if(FileLog.getInstance().streamWriter != null) {
            FileLog.getInstance().logQueue.postRunnable(new Runnable(arg2) {
                public void run() {
                    try {
                        OutputStreamWriter v0_1 = FileLog.getInstance().streamWriter;
                        v0_1.write(FileLog.getInstance().dateFormat.format(System.currentTimeMillis()) + " W/tmessages: " + this.val$message + "\n");
                        FileLog.getInstance().streamWriter.flush();
                    }
                    catch(Exception v0) {
                        v0.printStackTrace();
                    }
                }
            });
        }
    }
}

