package org.telegram.messenger;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.LaunchActivity;

@TargetApi(value=23) public class TgChooserTargetService extends ChooserTargetService {
    private RectF bitmapRect;
    private Paint roundPaint;

    public TgChooserTargetService() {
        super();
    }

    static Icon access$000(TgChooserTargetService arg0, File arg1) {
        return arg0.createRoundBitmap(arg1);
    }

    private Icon createRoundBitmap(File arg7) {
        try {
            Bitmap v7_1 = BitmapFactory.decodeFile(arg7.toString());
            if(v7_1 == null) {
                return null;
            }

            Bitmap v0 = Bitmap.createBitmap(v7_1.getWidth(), v7_1.getHeight(), Bitmap$Config.ARGB_8888);
            v0.eraseColor(0);
            Canvas v1 = new Canvas(v0);
            BitmapShader v2 = new BitmapShader(v7_1, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
            if(this.roundPaint == null) {
                this.roundPaint = new Paint(1);
                this.bitmapRect = new RectF();
            }

            this.roundPaint.setShader(((Shader)v2));
            this.bitmapRect.set(0f, 0f, ((float)v7_1.getWidth()), ((float)v7_1.getHeight()));
            v1.drawRoundRect(this.bitmapRect, ((float)v7_1.getWidth()), ((float)v7_1.getHeight()), this.roundPaint);
            return Icon.createWithBitmap(v0);
        }
        catch(Throwable v7) {
            FileLog.e(v7);
        }

        return null;
    }

    public List onGetChooserTargets(ComponentName arg9, IntentFilter arg10) {
        int v2 = UserConfig.selectedAccount;
        ArrayList v9 = new ArrayList();
        if(!UserConfig.getInstance(v2).isClientActivated()) {
            return ((List)v9);
        }

        if(!MessagesController.getGlobalMainSettings().getBoolean("direct_share", true)) {
            return ((List)v9);
        }

        ImageLoader.getInstance();
        CountDownLatch v10 = new CountDownLatch(1);
        MessagesStorage.getInstance(v2).getStorageQueue().postRunnable(new Runnable(v2, v9, new ComponentName(this.getPackageName(), LaunchActivity.class.getCanonicalName()), v10) {
            public void run() {
                String v8_2;
                String v6_2;
                Object v9;
                int v8_1;
                ArrayList v0 = new ArrayList();
                ArrayList v1 = new ArrayList();
                ArrayList v2 = new ArrayList();
                try {
                    ArrayList v5_1 = new ArrayList();
                    v5_1.add(Integer.valueOf(UserConfig.getInstance(this.val$currentAccount).getClientUserId()));
                    ArrayList v6 = new ArrayList();
                    SQLiteCursor v7 = MessagesStorage.getInstance(this.val$currentAccount).getDatabase().b(String.format(Locale.US, "SELECT did FROM dialogs ORDER BY date DESC LIMIT %d,%d", Integer.valueOf(0), Integer.valueOf(30)), new Object[0]);
                    do {
                        if(!v7.a()) {
                            break;
                        }

                        long v8 = v7.d(0);
                        int v10 = ((int)v8);
                        v8_1 = ((int)(v8 >> 32));
                        if(v10 == 0) {
                            continue;
                        }

                        if(v8_1 == 1) {
                            continue;
                        }
                        else {
                            if(v10 <= 0) {
                                v8_1 = -v10;
                                if(!v6.contains(Integer.valueOf(v8_1))) {
                                    v6.add(Integer.valueOf(v8_1));
                                }
                            }
                            else if(!v5_1.contains(Integer.valueOf(v10))) {
                                v5_1.add(Integer.valueOf(v10));
                            }

                            v0.add(Integer.valueOf(v10));
                            if(v0.size() != 8) {
                                continue;
                            }
                        }

                        break;
                    }
                    while(true);

                    v7.b();
                    if(!v6.isEmpty()) {
                        MessagesStorage.getInstance(this.val$currentAccount).getChatsInternal(TextUtils.join(",", ((Iterable)v6)), v1);
                    }

                    if(v5_1.isEmpty()) {
                        goto label_78;
                    }

                    MessagesStorage.getInstance(this.val$currentAccount).getUsersInternal(TextUtils.join(",", ((Iterable)v5_1)), v2);
                }
                catch(Exception v5) {
                    FileLog.e(((Throwable)v5));
                }

            label_78:
                int v5_2;
                for(v5_2 = 0; v5_2 < v0.size(); ++v5_2) {
                    Bundle v12 = new Bundle();
                    int v6_1 = v0.get(v5_2).intValue();
                    Icon v7_1 = null;
                    if(v6_1 > 0) {
                        v8_1 = 0;
                        while(v8_1 < v2.size()) {
                            v9 = v2.get(v8_1);
                            if(((User)v9).id != v6_1) {
                                ++v8_1;
                                continue;
                            }
                            else if(!((User)v9).bot) {
                                v12.putLong("dialogId", ((long)v6_1));
                                if(((User)v9).photo != null && ((User)v9).photo.photo_small != null) {
                                    v7_1 = TgChooserTargetService.this.createRoundBitmap(FileLoader.getPathToAttach(((User)v9).photo.photo_small, true));
                                }

                                v6_2 = ContactsController.formatName(((User)v9).first_name, ((User)v9).last_name);
                                goto label_116;
                            }

                            break;
                        }

                        v6_2 = ((String)v7_1);
                        goto label_116;
                    }
                    else {
                        v8_1 = 0;
                        while(v8_1 < v1.size()) {
                            v9 = v1.get(v8_1);
                            if(((Chat)v9).id != -v6_1) {
                                ++v8_1;
                                continue;
                            }
                            else if(!ChatObject.isNotInChat(((Chat)v9))) {
                                if((ChatObject.isChannel(((Chat)v9))) && !((Chat)v9).megagroup) {
                                    break;
                                }

                                v12.putLong("dialogId", ((long)v6_1));
                                if(((Chat)v9).photo != null && ((Chat)v9).photo.photo_small != null) {
                                    v7_1 = TgChooserTargetService.this.createRoundBitmap(FileLoader.getPathToAttach(((Chat)v9).photo.photo_small, true));
                                }

                                v6_2 = ((Chat)v9).title;
                                goto label_116;
                            }

                            break;
                        }

                        v8_2 = ((String)v7_1);
                        goto label_150;
                    label_116:
                        v8_2 = v6_2;
                    }

                label_150:
                    if(v8_2 != null) {
                        Icon v9_1 = v7_1 == null ? Icon.createWithResource(ApplicationLoader.applicationContext, 2131231333) : v7_1;
                        this.val$targets.add(new ChooserTarget(((CharSequence)v8_2), v9_1, 1f, this.val$componentName, v12));
                    }
                }

                this.val$countDownLatch.countDown();
            }
        });
        try {
            v10.await();
        }
        catch(Exception v10_1) {
            FileLog.e(((Throwable)v10_1));
        }

        return ((List)v9);
    }
}

