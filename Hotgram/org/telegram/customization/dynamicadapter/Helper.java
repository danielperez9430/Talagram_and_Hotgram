package org.telegram.customization.dynamicadapter;

import android.app.Activity;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.telegram.customization.dynamicadapter.annotations.ViewHolderType;
import org.telegram.customization.dynamicadapter.data.ExtraData;
import org.telegram.customization.dynamicadapter.data.ObjBase;
import org.telegram.customization.dynamicadapter.viewholder.ExceptionHolder;
import org.telegram.customization.dynamicadapter.viewholder.HolderBase;
import org.telegram.customization.dynamicadapter.viewholder.MoreTagHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsImportantTagsHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsLoaderHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsMediaTypeHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsMessageHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsNoResultHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsSearchBoxHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsSearchImgClickableHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsStatisticsHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsSuggestionHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsTagHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsTagsCarouselHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsThreeTileDefHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsThreeTileSameHolder;
import org.telegram.customization.dynamicadapter.viewholder.SlsTitleHolder;

public class Helper {
    public static final int HOLDER_TYPE_CATEGORY = 101;
    public static final int HOLDER_TYPE_EXCEPTION = 111;
    public static final int HOLDER_TYPE_IMPORTANT_TAGS = 103;
    public static final int HOLDER_TYPE_LOADING = 107;
    public static final int HOLDER_TYPE_MEDIA_TYPE = 109;
    public static final int HOLDER_TYPE_MESSAGE = 100;
    public static final int HOLDER_TYPE_MORE = 108;
    public static final int HOLDER_TYPE_NO_RESULT = 110;
    public static final int HOLDER_TYPE_SEARCH = 105;
    public static final int HOLDER_TYPE_SEARCH_IMG_CLICKABLE = 112;
    public static final int HOLDER_TYPE_STATISTICS = 104;
    public static final int HOLDER_TYPE_SUGGESTION = 113;
    public static final int HOLDER_TYPE_TAG_COLLECTION = 102;
    public static final int HOLDER_TYPE_TITLE = 106;
    private static HashMap viewHolders;

    public Helper() {
        super();
    }

    private static void addViewHolder(Class arg2) {
        try {
            Helper.viewHolders.remove(Integer.valueOf(arg2.getAnnotation(ViewHolderType.class).type()));
            goto label_6;
        }
        catch(Exception ) {
            try {
            label_6:
                Helper.viewHolders.put(Integer.valueOf(arg2.getAnnotation(ViewHolderType.class).type()), arg2);
                return;
            }
            catch(Exception ) {
                return;
            }
        }
    }

    public static HolderBase createViewHolder(Activity arg7, ViewGroup arg8, int arg9, DynamicAdapter arg10, ExtraData arg11) {
        Constructor v9_2;
        Class[] v5;
        Object v9_1;
        int v0 = 3;
        int v1 = 2;
        int v4 = 4;
        try {
            v9_1 = Helper.getViewHolders().get(Integer.valueOf(arg9));
            v5 = new Class[v4];
            v5[0] = Activity.class;
            v5[1] = ViewGroup.class;
            v5[v1] = DynamicAdapter.class;
            v5[v0] = ExtraData.class;
            v9_2 = ((Class)v9_1).getDeclaredConstructor(v5);
            Object[] v5_1 = new Object[v4];
            v5_1[0] = arg7;
            v5_1[1] = arg8;
            v5_1[v1] = arg10;
            v5_1[v0] = arg11;
            return v9_2.newInstance(v5_1);
        }
        catch(Exception v9) {
            v9.printStackTrace();
            try {
                v9_1 = Helper.getViewHolders().get(Integer.valueOf(111));
                v5 = new Class[v4];
                v5[0] = Activity.class;
                v5[1] = ViewGroup.class;
                v5[v1] = DynamicAdapter.class;
                v5[v0] = ExtraData.class;
                v9_2 = ((Class)v9_1).getDeclaredConstructor(v5);
                Object[] v4_1 = new Object[v4];
                v4_1[0] = arg7;
                v4_1[1] = arg8;
                v4_1[v1] = arg10;
                v4_1[v0] = arg11;
                return v9_2.newInstance(v4_1);
            }
            catch(Exception v7) {
                v7.printStackTrace();
                return null;
            }
        }
    }

    public static ArrayList getChannelItemsAfterIndex(ArrayList arg5, int arg6) {
        ArrayList v0 = new ArrayList();
        Iterator v5 = arg5.iterator();
        int v1 = 0;
        while(v5.hasNext()) {
            Object v2 = v5.next();
            if(((ObjBase)v2).getType() != 101) {
                continue;
            }

            ++v1;
            if(v1 < arg6) {
                continue;
            }

            v0.add(v2);
        }

        return v0;
    }

    public static ArrayList getFullChannelItems(ArrayList arg4) {
        ArrayList v0 = new ArrayList();
        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            Object v1 = v4.next();
            if(((ObjBase)v1).getType() != 101) {
                continue;
            }

            v0.add(v1);
        }

        return v0;
    }

    public static ObjBase getImportantItem(ArrayList arg3) {
        Object v0;
        Iterator v3 = arg3.iterator();
        do {
            if(!v3.hasNext()) {
                return null;
            }

            v0 = v3.next();
        }
        while(((ObjBase)v0).getType() != 103);

        return ((ObjBase)v0);
    }

    public static ArrayList getLimitedChannelItems(ArrayList arg3, int arg4) {
        ArrayList v0 = new ArrayList();
        Iterator v3 = arg3.iterator();
        int v1 = 0;
        do {
            if(!v3.hasNext()) {
                return v0;
            }

            v0.add(v3.next());
            ++v1;
        }
        while(v1 != arg4);

        return v0;
    }

    public static ArrayList getMessageItems(ArrayList arg4) {
        ArrayList v0 = new ArrayList();
        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            Object v1 = v4.next();
            if(((ObjBase)v1).getType() != 100) {
                continue;
            }

            v0.add(v1);
        }

        return v0;
    }

    public static Class getModel(int arg1) {
        try {
            return Helper.getViewHolders().get(Integer.valueOf(arg1)).getAnnotation(ViewHolderType.class).model();
        }
        catch(Exception v1) {
            v1.printStackTrace();
            return null;
        }
    }

    public static ObjBase getSearchItem(ArrayList arg3) {
        Object v0;
        Iterator v3 = arg3.iterator();
        do {
            if(!v3.hasNext()) {
                return null;
            }

            v0 = v3.next();
        }
        while(((ObjBase)v0).getType() != 105);

        return ((ObjBase)v0);
    }

    public static ObjBase getStatisticsItem(ArrayList arg3) {
        Object v0;
        Iterator v3 = arg3.iterator();
        do {
            if(!v3.hasNext()) {
                return null;
            }

            v0 = v3.next();
        }
        while(((ObjBase)v0).getType() != 104);

        return ((ObjBase)v0);
    }

    public static ObjBase getSuggestedSearchItem(ArrayList arg3) {
        Object v0;
        Iterator v3 = arg3.iterator();
        do {
            if(!v3.hasNext()) {
                return null;
            }

            v0 = v3.next();
        }
        while(((ObjBase)v0).getType() != 112);

        return ((ObjBase)v0);
    }

    private static HashMap getViewHolders() {
        if(Helper.viewHolders == null || Helper.viewHolders.size() == 0) {
            Helper.viewHolders = new HashMap();
            Helper.addViewHolder(SlsTagHolder.class);
            Helper.addViewHolder(SlsMessageHolder.class);
            Helper.addViewHolder(SlsThreeTileSameHolder.class);
            Helper.addViewHolder(SlsThreeTileDefHolder.class);
            Helper.addViewHolder(SlsTitleHolder.class);
            Helper.addViewHolder(SlsStatisticsHolder.class);
            Helper.addViewHolder(SlsSearchBoxHolder.class);
            Helper.addViewHolder(SlsImportantTagsHolder.class);
            Helper.addViewHolder(SlsLoaderHolder.class);
            Helper.addViewHolder(SlsTagsCarouselHolder.class);
            Helper.addViewHolder(MoreTagHolder.class);
            Helper.addViewHolder(SlsMediaTypeHolder.class);
            Helper.addViewHolder(SlsNoResultHolder.class);
            Helper.addViewHolder(ExceptionHolder.class);
            Helper.addViewHolder(SlsSearchImgClickableHolder.class);
            Helper.addViewHolder(SlsSuggestionHolder.class);
        }

        return Helper.viewHolders;
    }
}

