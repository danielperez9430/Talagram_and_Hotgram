package org.linphone.tools;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.linphone.core.OpenH264DownloadHelperListener;
import org.linphone.mediastream.Log;

public class OpenH264DownloadHelper {
    private String fileDirection;
    private String licenseMessage;
    private String nameFileDownload;
    private String nameLib;
    private OpenH264DownloadHelperListener openH264DownloadHelperListener;
    private String urlDownload;
    private ArrayList userData;

    public OpenH264DownloadHelper(Context arg2) {
        super();
        this.userData = new ArrayList();
        this.licenseMessage = "OpenH264 Video Codec provided by Cisco Systems, Inc.";
        this.nameLib = "libopenh264.so";
        this.urlDownload = "http://ciscobinary.openh264.org/libopenh264-1.5.0-android19.so.bz2";
        this.nameFileDownload = "libopenh264-1.5.0-android19.so.bz2";
        if(arg2.getFilesDir() != null) {
            this.fileDirection = arg2.getFilesDir().toString();
        }
    }

    static String access$000(OpenH264DownloadHelper arg0) {
        return arg0.fileDirection;
    }

    static String access$100(OpenH264DownloadHelper arg0) {
        return arg0.nameLib;
    }

    static String access$200(OpenH264DownloadHelper arg0) {
        return arg0.urlDownload;
    }

    static String access$300(OpenH264DownloadHelper arg0) {
        return arg0.nameFileDownload;
    }

    static OpenH264DownloadHelperListener access$400(OpenH264DownloadHelper arg0) {
        return arg0.openH264DownloadHelperListener;
    }

    public void downloadCodec() {
        new Thread(new Runnable() {
            public void run() {
                OpenH264DownloadHelperListener v1_4;
                String v0_3;
                try {
                    v0_3 = OpenH264DownloadHelper.this.fileDirection + "/" + OpenH264DownloadHelper.this.nameLib;
                    URLConnection v1 = new URL(OpenH264DownloadHelper.this.urlDownload).openConnection();
                    ((HttpURLConnection)v1).connect();
                    int v2 = 2;
                    Object[] v3 = new Object[v2];
                    v3[0] = "OpenH264Downloader";
                    v3[1] = " ";
                    Log.i(v3);
                    InputStream v3_1 = ((HttpURLConnection)v1).getInputStream();
                    StringBuilder v7 = new StringBuilder();
                    v7.append(OpenH264DownloadHelper.this.fileDirection);
                    v7.append("/");
                    v7.append(OpenH264DownloadHelper.this.nameFileDownload);
                    FileOutputStream v4 = new FileOutputStream(v7.toString());
                    int v1_1 = ((HttpURLConnection)v1).getContentLength();
                    OpenH264DownloadHelper.this.openH264DownloadHelperListener.OnProgress(0, v1_1);
                    Object[] v7_1 = new Object[v2];
                    v7_1[0] = "OpenH264Downloader";
                    v7_1[1] = " Download file:" + OpenH264DownloadHelper.this.nameFileDownload;
                    Log.i(v7_1);
                    byte[] v7_2 = new byte[4096];
                    int v8_1 = 0;
                    while(true) {
                        int v9 = v3_1.read(v7_2);
                        if(v9 <= 0) {
                            break;
                        }

                        v8_1 += v9;
                        v4.write(v7_2, 0, v9);
                        OpenH264DownloadHelper.this.openH264DownloadHelperListener.OnProgress(v8_1, v1_1);
                    }

                    v4.close();
                    v3_1.close();
                    Object[] v1_2 = new Object[v2];
                    v1_2[0] = "OpenH264Downloader";
                    v1_2[1] = " Uncompress file:" + OpenH264DownloadHelper.this.nameFileDownload;
                    Log.i(v1_2);
                    v3_2 = new StringBuilder();
                    v3_2.append(OpenH264DownloadHelper.this.fileDirection);
                    v3_2.append("/");
                    v3_2.append(OpenH264DownloadHelper.this.nameFileDownload);
                    FileInputStream v1_3 = new FileInputStream(v3_2.toString());
                    FileOutputStream v3_3 = new FileOutputStream(v0_3);
                    BZip2CompressorInputStream v4_1 = new BZip2CompressorInputStream(((InputStream)v1_3));
                    while(true) {
                        v8_1 = v4_1.read(v7_2);
                        if(v8_1 <= 0) {
                            break;
                        }

                        v3_3.write(v7_2, 0, v8_1);
                    }

                    v1_3.close();
                    v3_3.close();
                    v4_1.close();
                    v1_2 = new Object[v2];
                    v1_2[0] = "OpenH264Downloader";
                    v1_2[1] = " Remove file:" + OpenH264DownloadHelper.this.nameFileDownload;
                    Log.i(v1_2);
                    v3_2 = new StringBuilder();
                    v3_2.append(OpenH264DownloadHelper.this.fileDirection);
                    v3_2.append("/");
                    v3_2.append(OpenH264DownloadHelper.this.nameFileDownload);
                    new File(v3_2.toString()).delete();
                    v1_2 = new Object[v2];
                    v1_2[0] = "OpenH264Downloader";
                    v1_2[1] = " Loading plugin:" + v0_3;
                    Log.i(v1_2);
                    System.load(v0_3);
                    OpenH264DownloadHelper.this.openH264DownloadHelperListener.OnProgress(v2, 1);
                    return;
                }
                catch(IOException v0) {
                    v1_4 = OpenH264DownloadHelper.this.openH264DownloadHelperListener;
                    v0_3 = v0.getLocalizedMessage();
                }
                catch(FileNotFoundException v0_1) {
                    v1_4 = OpenH264DownloadHelper.this.openH264DownloadHelperListener;
                    v0_3 = v0_1.getLocalizedMessage();
                }

                v1_4.OnError(v0_3);
            }
        }).start();
    }

    public String getFullPathLib() {
        return this.fileDirection + "/" + this.getNameLib();
    }

    public String getLicenseMessage() {
        return this.licenseMessage;
    }

    public String getNameLib() {
        return this.nameLib;
    }

    public OpenH264DownloadHelperListener getOpenH264DownloadHelperListener() {
        return this.openH264DownloadHelperListener;
    }

    public Object getUserData(int arg2) {
        if(arg2 >= 0) {
            if(arg2 >= this.userData.size()) {
            }
            else {
                return this.userData.get(arg2);
            }
        }

        return null;
    }

    public int getUserDataSize() {
        return this.userData.size();
    }

    public boolean isCodecFound() {
        StringBuilder v1 = new StringBuilder();
        v1.append(this.fileDirection);
        v1.append("/");
        v1.append(this.nameLib);
        return new File(v1.toString()).exists();
    }

    public void setNameFileDownload(String arg1) {
        this.nameFileDownload = arg1;
    }

    public void setNameLib(String arg1) {
        this.nameLib = arg1;
    }

    public void setOpenH264HelperListener(OpenH264DownloadHelperListener arg1) {
        this.openH264DownloadHelperListener = arg1;
    }

    public void setUrlDownload(String arg1) {
        this.urlDownload = arg1;
    }

    public int setUserData(Object arg2) {
        this.userData.add(arg2);
        return this.userData.indexOf(arg2);
    }

    public void setUserData(int arg2, Object arg3) {
        if(arg2 >= 0) {
            if(arg2 > this.userData.size()) {
            }
            else {
                this.userData.add(arg2, arg3);
            }
        }
    }
}

