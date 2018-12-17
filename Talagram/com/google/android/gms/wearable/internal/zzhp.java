package com.google.android.gms.wearable.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class zzhp {
    private static zzhp zzfs;
    private final Context zzft;
    @VisibleForTesting private static final byte[] zzfu;
    @VisibleForTesting private static final byte[] zzfv;

    static {
        zzhp.zzfu = zzhp.zzd("0\u0082\u0005a0\u0082\u0003K\u0002\u0006\u0001D\u009E\u0091\u0096Ó0\u000B\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u00050v1\u000B0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000BGoogle Inc.1\u00100\u000E\u0006\u0003U\u0004\u000B\u0013\u0007Android1\u00120\u0010\u0006\u0003U\u0004\u0003\u0013\tClockWork0\u001E\u0017\r140307220225Z\u0017\r380119031407Z0v1\u000B0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000BGoogle Inc.1\u00100\u000E\u0006\u0003U\u0004\u000B\u0013\u0007Android1\u00120\u0010\u0006\u0003U\u0004\u0003\u0013\tClockWork0\u0082\u0002\"0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0002\u000F\u00000\u0082\u0002\n\u0002\u0082\u0002\u0001\u0000º<\u007F9\u000BþY\u008Ab¼ü\u008B<\u0094Æ\'Z\u0099\u0015íÜÝ7:Uj\u0099\u000BâýC÷\u009F\u0018³\u0001Ò@\'ãr\u007F\tÎýâ\u009C|&°\u008A Þ6}\u001AßãN§\u008F®7ó\u0090õà&rzN\b(;ïvøöC¼\u0015\'6 H?É·\u0091«R<ó½\u0086{f-*\'L\u0000Ø\u0090ç\u009D\u0011è°&_í©uÜÈåB\u0099\u0089\u008E\u0090\u0013jbq\u008C.\u000B/9yQÛ$±W¡¿çÅkÎJ8\u000B%ú¹&c>¨\u0094\u00048à¶\u0094\u000B¹\u009E\u0089~.ú\u0005<2)\u009BÙao¤½\u0096\u0082!{7C\u001FÍØ\u0082í!§òðF\u007Fà\u0095\u001C¼Z\u0098bãJ\u0015káZ\u0017ÿ\u0002\u0017\u0098dDÖ\u0013±\u001E×_\u0083\u0080\u0018î´ý\u0094ä\u008FZã\u001Cä¯¤68¶\u0097,\u0085\\ÒÛ\n\u0001Ä2a(äÅ\u0019z¾¬ÌmÂè­¤B_\u000F\u0090Õ¥¥X$a¿x\u0011á.Î\u000Eê\u0006\u0003?\u0096T9íàqÿÄl òß¾##:\u007FdÁÎ\t­¡ËÎkö¼¢.\u009B\u0098\u009CJÀÉj\u009DluOì\u0018qØ{\u0010\u0098Á Þ`¼}wÞ0ÕN¸GÎk\u0012|\u0019\u001E§\u0093o\nFÁFó6¹4êºZ_\u001C\u0003d·R\u0096UD2Pýcªå{ë«à&?\t\bM\u0019D\u0006\f:Ù»º\u007FyôÞ<+-7º³\rK¹\u0011ÜQià¯\u0095RôÓ\u008E=³òË\u0080\u001CR\u0002Rpa¿\u0001°BÐ~\u0089ä\u008F\u0011©ª \'ðD\u0095\u009EÚ(ÅÝØSW§\u001E9»\u0082Q³Wëor\u0018üÌ\u0017\u0018\u0081¦0gF1àU\u00949\u001Azg\u009AòZ b\u0001Ö\"¸Ð\tÝ\u0011Õ\u0006¢\u0003\u000F$\'®gØ\u001B47yy\u0002\u0003\u0001\u0000\u00010\u000B\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0005\u0003\u0082\u0002\u0001\u0000¤Ä\u0096\u00964aÈ\u00955¥±\nÍ\u0001$7j\u0089Ú\'C\u009D¬0\u0003Hg\u000B +­ã?/º*\u0007d\u0003µ\u000BèqÊ*²\u009B¾½»Ä\u0006Û\t9AÉ\u008C\u0017j\u000EFÿ\u0090ÿ\u0000\u0016\u0016\u0004D\u0080nÜ\u0082á0þ\u0010\u0086\u001Eã\u0005\u009D·~=\u009D©¢­4©Ò´Ú\u001B&ýZ[p\u001CÕlþédzä\u0014;\u0097¦|\u0002\u0080e±\u007F\u009E\u0014ò2¥ï\u0017ád¡I\u0017\u0092\u0096\u0094\u001C0½Z6«øóBÈã¯¼oICs\u0007}j\u009C\u0011×9\"\rZ×µ\u0019/\u009B\u001Cþ\u0096\u008FJr±¸Tuàé\u0088¾hr\u0088fe±+ôîÃ\"VTõáò+\u008BëU\u008E¾fw\u008BÖ_\t\u0091-ù^\u0080\u009Dþï\u000FÇêÊ]\u000E¾\u001DA\u0004\u001Fç Ë2\u009B0~9.\u0013\u0097ñ 9Ti0\u0084\u008B\u007F\u0002\u0017@\u0089-öÇ rçß8ºÃ×\"5oæT\u007Fj|W\u008AßgÉ=+5\u0088\u0093T5ðù¡\u0013Î-ìÍm¡\u009DÃKA\u0082ì®Ö ëR\u00850%Åà\u0004ì´Q¼EáHZÌ6\u007F¶I\u0092¯YLU\u001B\u000BÉ8ËÖ\u001AÕgY\u0090 ÷:eá©È¤\u0088Û¬\u0083\u001Eë\u0091\u008F\f\u0092)\t^ÞA\u0005{<®êN\u0016Å¹EK\u0092âY\u008A\u0011´\u0094¢\u001F?z¿\u0083Àgô\u0018.\u0098A\u009B¤ä\u0093\u008A4\u0080ð\u0086/í¯WrJU3W\u008FÚ_³ÍüùT\u0080\u007FÿØÉQwçu\u0004¦B¾\\Û á\u0000eü|h\u0012\u009Cí\'³¨\u0004×¤ÍÙ\fÓìË\u0005¨È\u008A`\u009AÐV N´\u008A\u001E\u0005\u0095ý9\\\u001F§{³\u001D¥$4^\n½N\u0001µ\u0006\u0082OêºBÓ-Ô\u0092g>ÏÀ\u0017\u009D\u0086\u001D&éÍ\\FïÐ");
        zzhp.zzfv = zzhp.zzd("0\u0082\u0003¿0\u0082\u0002§ \u0003\u0002\u0001\u0002\u0002\t\u0000Ú\u0098ÃÙ\u0015sÓï0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0005\u0005\u00000v1\u000B0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\f\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\f\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\f\u000BGoogle Inc.1\u00100\u000E\u0006\u0003U\u0004\u000B\f\u0007Android1\u00120\u0010\u0006\u0003U\u0004\u0003\f\tClockWork0\u001E\u0017\r140307220118Z\u0017\r410723220118Z0v1\u000B0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\f\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\f\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\f\u000BGoogle Inc.1\u00100\u000E\u0006\u0003U\u0004\u000B\f\u0007Android1\u00120\u0010\u0006\u0003U\u0004\u0003\f\tClockWork0\u0082\u0001\"0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\u000F\u00000\u0082\u0001\n\u0002\u0082\u0001\u0001\u0000Ü\u001DoK(í80\u0014²\u009C\u0082öÚÿÓ\u001DÞ{\u008C\u001Ec\b@e\u000BX±e£j®¶,qS\u0095.\u0004E\t¯\u0082\u001F\u0094º\u009FO\u0018dÃ§µÖSÌ\u0000\u0015\u009D\u0000\u0010áåfú7ªÿ\u00186]®{J\u0085Ý±ó\u0083ÌGp¢>\u0095b\u0091þµrÁi1Z¯Nôê¥®\u0086\u001FÍÖçåêÔ1\u0013tFF\f|(û2,\u0092\u0095\\\\z¨\u0095wÃp?\u0097à\u0098·~¶ n¬krê ­!\n°*\u001FÜüvbttA©?<ê\u008A\u0016ô\u008C\u0097\"Áã2A2~ÂÉ÷01.\u008D\u001Bïî)\u000BE\u001A4\u0089,¬ï[\u0014rÖÙ~ùT(Ì\u008AÕï\u0004¸Äñõ\rÒBÕ]rXf\u0085P[^K\u001B\u001EY­\u008C\u001D\u0085/ \u0082H\u0015g;ÆæC)ìÄêÔÛ\u0084d©k1\u0083\u009F\u009FÛÉ\u0007\u0002\u0003\u0001\u0000\u0001£P0N0\u001D\u0006\u0003U\u001D\u000E\u0004\u0016\u0004\u0014\u0084\u0085G\u0010\u0084¤<³êø?«!b \u0095\u0000Î,z0\u001F\u0006\u0003U\u001D#\u0004\u00180\u0016\u0080\u0014\u0084\u0085G\u0010\u0084¤<³êø?«!b \u0095\u0000Î,z0\f\u0006\u0003U\u001D\u0013\u0004\u00050\u0003\u0001\u0001ÿ0\r\u0006\t*\u0086H\u0086÷\r\u0001\u0001\u0005\u0005\u0000\u0003\u0082\u0001\u0001\u0000\u00079b\u000B¢}*\u000FT\u0088C­\u001B`\u008E\u001C)Ù\u0001(\u0081êü?Ö(__bj\u0097>ðWæ\u0097î²¬\\¢æ\u0005Ê=3õ\u0090\u0099k\u008B\u00002ÄGæP\u000F%½\u0017Êù\u0095\u00039\u0083@ÈîlÜµ;íä±òHçÐ \u0099\u009E\u0081çÊê¥2ÏÚ\u0099þJ¥í@@ND÷[ïÒ\u007FÊÛ5¸²\u001B\u0094xF^\u0017\"òzû+\u000Bn\u0015\u008EDÄ«\fOe{\u0019×}\u008FSÉÏ¹î-OE¶Tà\u0012¼\u008Dé\u0081äÂâÃÓ\u009EQ\u0093\u0003Ø®M,ÁÈb\u008DxW®u?\u001D{\u0002£§\u0005xÆ\u0005ã\u0005\u001Cl\u001D©I\u001AÎ\u0013»\u0088Ð}\u0081}Ô\u0094&Q\u0097\u0084®\u0096\u0095¤G5\r\u0089ë@^\u0090ò«óf®/ÊXÒö¿\u001D¿K\u001CH\u009Eà \u0001TßÏ\u0002%\u0012õ¡Ç\"\u009Es\u001DãðGÖø");
    }

    @VisibleForTesting private zzhp(Context arg1) {
        super();
        this.zzft = arg1.getApplicationContext();
    }

    public static zzhp zza(Context arg2) {
        Preconditions.checkNotNull(arg2);
        Class v0 = zzhp.class;
        __monitor_enter(v0);
        try {
            if(zzhp.zzfs == null) {
                zzhp.zzfs = new zzhp(arg2);
            }

            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            try {
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_12;
            }

            throw v2;
        }

        return zzhp.zzfs;
    }

    @VisibleForTesting private static boolean zza(PackageInfo arg3, boolean arg4) {
        if(arg3.signatures.length != 1) {
            Log.w("WearSignatureVerifier", "Package has more than one signature.");
            return 0;
        }

        arg3.signatures[0].toByteArray();
        byte[][] v4 = arg4 ? new byte[][]{zzhp.zzfu, zzhp.zzfv} : new byte[][]{zzhp.zzfu};
        boolean v3 = zzhp.zza(arg3, v4);
        return v3;
    }

    private static boolean zza(PackageInfo arg4, byte[][] arg5) {
        if(arg4.signatures == null) {
            return 0;
        }

        if(arg4.signatures.length != 1) {
            Log.w("WearSignatureVerifier", "Package has more than one signature.");
            return 0;
        }

        byte[] v4 = arg4.signatures[0].toByteArray();
        int v0;
        for(v0 = 0; v0 < arg5.length; ++v0) {
            if(Arrays.equals(arg5[v0], v4)) {
                return 1;
            }
        }

        return 0;
    }

    private static byte[] zzd(String arg1) {
        try {
            return arg1.getBytes("ISO-8859-1");
        }
        catch(UnsupportedEncodingException v1) {
            throw new AssertionError(v1);
        }
    }

    public final boolean zze(String arg4) {
        PackageInfo v4 = this.zzf(arg4);
        if(v4 == null) {
            return 0;
        }

        if(GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzft)) {
            return zzhp.zza(v4, true);
        }

        boolean v0 = zzhp.zza(v4, false);
        if(!v0 && (zzhp.zza(v4, true))) {
            Log.w("WearSignatureVerifier", "Test-keys aren\'t accepted on this build.");
        }

        return v0;
    }

    private final PackageInfo zzf(String arg3) {
        try {
            return Wrappers.packageManager(this.zzft).getPackageInfo(arg3, 64);
        }
        catch(PackageManager$NameNotFoundException ) {
            return null;
        }
    }
}

