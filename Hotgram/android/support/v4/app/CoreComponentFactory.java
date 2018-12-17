package android.support.v4.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;

public class CoreComponentFactory extends AppComponentFactory {
    public interface a {
        Object a();
    }

    public CoreComponentFactory() {
        super();
    }

    static Object a(Object arg1) {
        if((arg1 instanceof a)) {
            Object v0 = arg1.a();
            if(v0 != null) {
                return v0;
            }
        }

        return arg1;
    }

    public Activity instantiateActivity(ClassLoader arg1, String arg2, Intent arg3) {
        return CoreComponentFactory.a(super.instantiateActivity(arg1, arg2, arg3));
    }

    public Application instantiateApplication(ClassLoader arg1, String arg2) {
        return CoreComponentFactory.a(super.instantiateApplication(arg1, arg2));
    }

    public ContentProvider instantiateProvider(ClassLoader arg1, String arg2) {
        return CoreComponentFactory.a(super.instantiateProvider(arg1, arg2));
    }

    public BroadcastReceiver instantiateReceiver(ClassLoader arg1, String arg2, Intent arg3) {
        return CoreComponentFactory.a(super.instantiateReceiver(arg1, arg2, arg3));
    }

    public Service instantiateService(ClassLoader arg1, String arg2, Intent arg3) {
        return CoreComponentFactory.a(super.instantiateService(arg1, arg2, arg3));
    }
}

