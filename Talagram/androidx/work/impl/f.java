package androidx.work.impl;

import android.arch.b.a.b;
import android.arch.b.b.a.a;
import android.content.Context;
import android.os.Build$VERSION;
import androidx.work.impl.utils.e;

public class f {
    final class androidx.work.impl.f$1 extends a {
        androidx.work.impl.f$1(int arg1, int arg2) {
            super(arg1, arg2);
        }

        public void a(b arg2) {
            arg2.c("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            arg2.c("INSERT INTO SystemIdInfo(work_spec_id, system_id) SELECT work_spec_id, alarm_id AS system_id FROM alarmInfo");
            arg2.c("DROP TABLE IF EXISTS alarmInfo");
            arg2.c("INSERT OR IGNORE INTO worktag(tag, work_spec_id) SELECT worker_class_name AS tag, id AS work_spec_id FROM workspec");
        }
    }

    final class androidx.work.impl.f$2 extends a {
        androidx.work.impl.f$2(int arg1, int arg2) {
            super(arg1, arg2);
        }

        public void a(b arg3) {
            if(Build$VERSION.SDK_INT >= 23) {
                arg3.c("UPDATE workspec SET schedule_requested_at=0 WHERE state NOT IN (2, 3, 5) AND schedule_requested_at=-1 AND interval_duration<>0");
            }
        }
    }

    public class androidx.work.impl.f$a extends a {
        final Context c;

        public androidx.work.impl.f$a(Context arg1, int arg2, int arg3) {
            super(arg2, arg3);
            this.c = arg1;
        }

        public void a(b arg2) {
            new e(this.c).a(true);
        }
    }

    public static a a;
    public static a b;

    static {
        f.a = new androidx.work.impl.f$1(1, 2);
        f.b = new androidx.work.impl.f$2(3, 4);
    }
}

