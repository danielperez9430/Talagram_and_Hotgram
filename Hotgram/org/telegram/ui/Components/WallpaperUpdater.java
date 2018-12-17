package org.telegram.ui.Components;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build$VERSION;
import android.support.v4.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog$Builder;

public class WallpaperUpdater {
    public interface WallpaperUpdaterDelegate {
        void didSelectWallpaper(File arg1, Bitmap arg2);

        void needOpenColorPicker();
    }

    private String currentPicturePath;
    private File currentWallpaperPath;
    private WallpaperUpdaterDelegate delegate;
    private Activity parentActivity;
    private File picturePath;

    public WallpaperUpdater(Activity arg3, WallpaperUpdaterDelegate arg4) {
        super();
        this.picturePath = null;
        this.parentActivity = arg3;
        this.delegate = arg4;
        File v4 = FileLoader.getDirectory(4);
        StringBuilder v0 = new StringBuilder();
        v0.append(Utilities.random.nextInt());
        v0.append(".jpg");
        this.currentWallpaperPath = new File(v4, v0.toString());
    }

    public void cleanup() {
        this.currentWallpaperPath.delete();
    }

    public String getCurrentPicturePath() {
        return this.currentPicturePath;
    }

    public File getCurrentWallpaperPath() {
        return this.currentWallpaperPath;
    }

    public static void lambda$showAlert$0(WallpaperUpdater arg4, boolean arg5, DialogInterface arg6, int arg7) {
        Intent v5_1;
        int v6 = 2;
        if(arg7 != 0) {
            goto label_31;
        }

        try {
            v5_1 = new Intent("android.media.action.IMAGE_CAPTURE");
            File v7 = AndroidUtilities.generatePicturePath();
            if(v7 != null) {
                if(Build$VERSION.SDK_INT >= 24) {
                    v5_1.putExtra("output", FileProvider.a(arg4.parentActivity, "ir.hotgram.mobile.android.provider", v7));
                    v5_1.addFlags(v6);
                    v5_1.addFlags(1);
                }
                else {
                    v5_1.putExtra("output", Uri.fromFile(v7));
                }

                arg4.currentPicturePath = v7.getAbsolutePath();
            }

            arg4.parentActivity.startActivityForResult(v5_1, 10);
            return;
        }
        catch(Exception v5) {
            try {
                FileLog.e(((Throwable)v5));
                return;
            label_31:
                if(arg7 == 1) {
                    v5_1 = new Intent("android.intent.action.PICK");
                    v5_1.setType("image/*");
                    arg4.parentActivity.startActivityForResult(v5_1, 11);
                    return;
                }

                if(!arg5) {
                    return;
                }

                if(arg7 == v6) {
                    arg4.delegate.needOpenColorPicker();
                    return;
                }

                if(arg7 != 3) {
                    return;
                }

                arg4.delegate.didSelectWallpaper(null, null);
                return;
            label_42:
            }
            catch(Exception v5) {
                goto label_42;
            }
        }

        FileLog.e(((Throwable)v5));
    }

    public void onActivityResult(int arg4, int arg5, Intent arg6) {
        FileOutputStream v2_1;
        FileOutputStream v5;
        Bitmap v4_3;
        Point v4_2;
        if(arg5 != -1) {
            return;
        }

        int v0 = 87;
        Uri v2 = null;
        if(arg4 != 10) {
            goto label_48;
        }

        AndroidUtilities.addMediaToGallery(this.currentPicturePath);
        try {
            v4_2 = AndroidUtilities.getRealScreenSize();
            v4_3 = ImageLoader.loadBitmap(this.currentPicturePath, v2, ((float)v4_2.x), ((float)v4_2.y), true);
            v5 = new FileOutputStream(this.currentWallpaperPath);
        }
        catch(Throwable v4) {
            goto label_42;
        }
        catch(Exception v4_1) {
            v5 = ((FileOutputStream)v2);
            goto label_32;
        }

        try {
            v4_3.compress(Bitmap$CompressFormat.JPEG, v0, ((OutputStream)v5));
            this.delegate.didSelectWallpaper(this.currentWallpaperPath, v4_3);
            goto label_24;
        }
        catch(Throwable v4) {
        label_41:
            v2_1 = v5;
        }
        catch(Exception v4_1) {
            try {
            label_32:
                FileLog.e(((Throwable)v4_1));
                if(v5 == null) {
                    goto label_38;
                }
            }
            catch(Throwable v4) {
                goto label_41;
            }

            try {
                v5.close();
                goto label_38;
            }
            catch(Exception v4_1) {
                goto label_37;
            }
        }

    label_42:
        if(v2_1 != null) {
            try {
                v2_1.close();
            }
            catch(Exception v5_1) {
                FileLog.e(((Throwable)v5_1));
            }
        }

        throw v4;
        try {
        label_24:
            v5.close();
        }
        catch(Exception v4_1) {
        label_37:
            FileLog.e(((Throwable)v4_1));
        }

    label_38:
        this.currentPicturePath = ((String)v2);
        return;
    label_48:
        if(arg4 == 11 && arg6 != null && arg6.getData() != null) {
            try {
                v4_2 = AndroidUtilities.getRealScreenSize();
                v4_3 = ImageLoader.loadBitmap(((String)v2), arg6.getData(), ((float)v4_2.x), ((float)v4_2.y), true);
                v4_3.compress(Bitmap$CompressFormat.JPEG, v0, new FileOutputStream(this.currentWallpaperPath));
                this.delegate.didSelectWallpaper(this.currentWallpaperPath, v4_3);
            }
            catch(Exception v4_1) {
                FileLog.e(((Throwable)v4_1));
            }
        }
    }

    public void setCurrentPicturePath(String arg1) {
        this.currentPicturePath = arg1;
    }

    public void showAlert(boolean arg11) {
        CharSequence[] v8;
        Builder v0 = new Builder(this.parentActivity);
        int v1 = 2131624257;
        int v2 = 3;
        int v3 = 2;
        int v4 = 2131624890;
        int v6 = 2131624882;
        if(arg11) {
            v8 = new CharSequence[5];
            v8[0] = LocaleController.getString("FromCamera", v6);
            v8[1] = LocaleController.getString("FromGalley", v4);
            v8[v3] = LocaleController.getString("SelectColor", 2131625993);
            v8[v2] = LocaleController.getString("Default", 2131624559);
            v8[4] = LocaleController.getString("Cancel", v1);
        }
        else {
            v8 = new CharSequence[v2];
            v8[0] = LocaleController.getString("FromCamera", v6);
            v8[1] = LocaleController.getString("FromGalley", v4);
            v8[v3] = LocaleController.getString("Cancel", v1);
        }

        v0.setItems(v8, new -$$Lambda$WallpaperUpdater$vK87huFk9jAjs7SXqPz2knBtx88(this, arg11));
        v0.show();
    }
}

