package androidx.work.impl;

import android.arch.b.a.c;
import android.arch.b.b.a;
import android.arch.b.b.b.b$d;
import android.arch.b.b.g;
import androidx.work.impl.b.b;
import androidx.work.impl.b.e;
import androidx.work.impl.b.f;
import androidx.work.impl.b.h;
import androidx.work.impl.b.i;
import androidx.work.impl.b.k;
import androidx.work.impl.b.l;
import androidx.work.impl.b.n;
import androidx.work.impl.b.o;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkDatabase_Impl extends WorkDatabase {
    private volatile k d;
    private volatile b e;
    private volatile n f;
    private volatile e g;
    private volatile h h;

    public WorkDatabase_Impl() {
        super();
    }

    static android.arch.b.a.b a(WorkDatabase_Impl arg0, android.arch.b.a.b arg1) {
        arg0.a = arg1;
        return arg1;
    }

    static List a(WorkDatabase_Impl arg0) {
        return arg0.c;
    }

    static List b(WorkDatabase_Impl arg0) {
        return arg0.c;
    }

    static void b(WorkDatabase_Impl arg0, android.arch.b.a.b arg1) {
        arg0.a(arg1);
    }

    protected c b(a arg5) {
        return arg5.a.a(android.arch.b.a.c$b.a(arg5.b).a(arg5.c).a(new g(arg5, new android.arch.b.b.g$a(4) {
            public void a(android.arch.b.a.b arg2) {
                arg2.c("DROP TABLE IF EXISTS `Dependency`");
                arg2.c("DROP TABLE IF EXISTS `WorkSpec`");
                arg2.c("DROP TABLE IF EXISTS `WorkTag`");
                arg2.c("DROP TABLE IF EXISTS `SystemIdInfo`");
                arg2.c("DROP TABLE IF EXISTS `WorkName`");
            }

            public void b(android.arch.b.a.b arg2) {
                arg2.c("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                arg2.c("CREATE  INDEX `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
                arg2.c("CREATE  INDEX `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
                arg2.c("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `period_start_time` INTEGER NOT NULL, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `required_network_type` INTEGER, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `content_uri_triggers` BLOB, PRIMARY KEY(`id`))");
                arg2.c("CREATE  INDEX `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
                arg2.c("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                arg2.c("CREATE  INDEX `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
                arg2.c("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                arg2.c("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                arg2.c("CREATE  INDEX `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
                arg2.c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                arg2.c("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"c45e5fcbdf3824dead9778f19e2fd8af\")");
            }

            public void c(android.arch.b.a.b arg4) {
                WorkDatabase_Impl.a(this.b, arg4);
                arg4.c("PRAGMA foreign_keys = ON");
                WorkDatabase_Impl.b(this.b, arg4);
                if(WorkDatabase_Impl.d(this.b) != null) {
                    int v0 = 0;
                    int v1 = WorkDatabase_Impl.e(this.b).size();
                    while(v0 < v1) {
                        WorkDatabase_Impl.f(this.b).get(v0).b(arg4);
                        ++v0;
                    }
                }
            }

            protected void d(android.arch.b.a.b arg4) {
                if(WorkDatabase_Impl.a(this.b) != null) {
                    int v0 = 0;
                    int v1 = WorkDatabase_Impl.b(this.b).size();
                    while(v0 < v1) {
                        WorkDatabase_Impl.c(this.b).get(v0).a(arg4);
                        ++v0;
                    }
                }
            }

            protected void e(android.arch.b.a.b arg22) {
                StringBuilder v2_2;
                android.arch.b.a.b v0 = arg22;
                int v2 = 2;
                HashMap v1 = new HashMap(v2);
                v1.put("work_spec_id", new android.arch.b.b.b.b$a("work_spec_id", "TEXT", true, 1));
                v1.put("prerequisite_id", new android.arch.b.b.b.b$a("prerequisite_id", "TEXT", true, v2));
                HashSet v3 = new HashSet(v2);
                v3.add(new android.arch.b.b.b.b$b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                v3.add(new android.arch.b.b.b.b$b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"prerequisite_id"}), Arrays.asList(new String[]{"id"})));
                HashSet v4 = new HashSet(v2);
                v4.add(new d("index_Dependency_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                v4.add(new d("index_Dependency_prerequisite_id", false, Arrays.asList(new String[]{"prerequisite_id"})));
                android.arch.b.b.b.b v5 = new android.arch.b.b.b.b("Dependency", ((Map)v1), ((Set)v3), ((Set)v4));
                android.arch.b.b.b.b v1_1 = android.arch.b.b.b.b.a(v0, "Dependency");
                if(v5.equals(v1_1)) {
                    v1 = new HashMap(21);
                    v1.put("id", new android.arch.b.b.b.b$a("id", "TEXT", true, 1));
                    v1.put("state", new android.arch.b.b.b.b$a("state", "INTEGER", true, 0));
                    v1.put("worker_class_name", new android.arch.b.b.b.b$a("worker_class_name", "TEXT", true, 0));
                    v1.put("input_merger_class_name", new android.arch.b.b.b.b$a("input_merger_class_name", "TEXT", false, 0));
                    v1.put("input", new android.arch.b.b.b.b$a("input", "BLOB", true, 0));
                    v1.put("output", new android.arch.b.b.b.b$a("output", "BLOB", true, 0));
                    v1.put("initial_delay", new android.arch.b.b.b.b$a("initial_delay", "INTEGER", true, 0));
                    v1.put("interval_duration", new android.arch.b.b.b.b$a("interval_duration", "INTEGER", true, 0));
                    v1.put("flex_duration", new android.arch.b.b.b.b$a("flex_duration", "INTEGER", true, 0));
                    v1.put("run_attempt_count", new android.arch.b.b.b.b$a("run_attempt_count", "INTEGER", true, 0));
                    v1.put("backoff_policy", new android.arch.b.b.b.b$a("backoff_policy", "INTEGER", true, 0));
                    v1.put("backoff_delay_duration", new android.arch.b.b.b.b$a("backoff_delay_duration", "INTEGER", true, 0));
                    v1.put("period_start_time", new android.arch.b.b.b.b$a("period_start_time", "INTEGER", true, 0));
                    v1.put("minimum_retention_duration", new android.arch.b.b.b.b$a("minimum_retention_duration", "INTEGER", true, 0));
                    v1.put("schedule_requested_at", new android.arch.b.b.b.b$a("schedule_requested_at", "INTEGER", true, 0));
                    v1.put("required_network_type", new android.arch.b.b.b.b$a("required_network_type", "INTEGER", false, 0));
                    v1.put("requires_charging", new android.arch.b.b.b.b$a("requires_charging", "INTEGER", true, 0));
                    v1.put("requires_device_idle", new android.arch.b.b.b.b$a("requires_device_idle", "INTEGER", true, 0));
                    v1.put("requires_battery_not_low", new android.arch.b.b.b.b$a("requires_battery_not_low", "INTEGER", true, 0));
                    v1.put("requires_storage_not_low", new android.arch.b.b.b.b$a("requires_storage_not_low", "INTEGER", true, 0));
                    v1.put("content_uri_triggers", new android.arch.b.b.b.b$a("content_uri_triggers", "BLOB", false, 0));
                    v3 = new HashSet(0);
                    v4 = new HashSet(1);
                    v4.add(new d("index_WorkSpec_schedule_requested_at", false, Arrays.asList(new String[]{"schedule_requested_at"})));
                    v5 = new android.arch.b.b.b.b("WorkSpec", ((Map)v1), ((Set)v3), ((Set)v4));
                    v1_1 = android.arch.b.b.b.b.a(v0, "WorkSpec");
                    if(v5.equals(v1_1)) {
                        v1 = new HashMap(v2);
                        v1.put("tag", new android.arch.b.b.b.b$a("tag", "TEXT", true, 1));
                        v1.put("work_spec_id", new android.arch.b.b.b.b$a("work_spec_id", "TEXT", true, v2));
                        v3 = new HashSet(1);
                        v3.add(new android.arch.b.b.b.b$b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                        v4 = new HashSet(1);
                        v4.add(new d("index_WorkTag_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                        v5 = new android.arch.b.b.b.b("WorkTag", ((Map)v1), ((Set)v3), ((Set)v4));
                        v1_1 = android.arch.b.b.b.b.a(v0, "WorkTag");
                        if(v5.equals(v1_1)) {
                            v1 = new HashMap(v2);
                            v1.put("work_spec_id", new android.arch.b.b.b.b$a("work_spec_id", "TEXT", true, 1));
                            v1.put("system_id", new android.arch.b.b.b.b$a("system_id", "INTEGER", true, 0));
                            v3 = new HashSet(1);
                            v3.add(new android.arch.b.b.b.b$b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                            v5 = new android.arch.b.b.b.b("SystemIdInfo", ((Map)v1), ((Set)v3), new HashSet(0));
                            v1_1 = android.arch.b.b.b.b.a(v0, "SystemIdInfo");
                            if(v5.equals(v1_1)) {
                                v1 = new HashMap(v2);
                                v1.put("name", new android.arch.b.b.b.b$a("name", "TEXT", true, 1));
                                v1.put("work_spec_id", new android.arch.b.b.b.b$a("work_spec_id", "TEXT", true, v2));
                                HashSet v2_1 = new HashSet(1);
                                v2_1.add(new android.arch.b.b.b.b$b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                                v3 = new HashSet(1);
                                v3.add(new d("index_WorkName_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                                android.arch.b.b.b.b v4_1 = new android.arch.b.b.b.b("WorkName", ((Map)v1), ((Set)v2_1), ((Set)v3));
                                android.arch.b.b.b.b v0_1 = android.arch.b.b.b.b.a(v0, "WorkName");
                                if(v4_1.equals(v0_1)) {
                                    return;
                                }

                                v2_2 = new StringBuilder();
                                v2_2.append("Migration didn\'t properly handle WorkName(androidx.work.impl.model.WorkName).\n Expected:\n");
                                v2_2.append(v4_1);
                                v2_2.append("\n");
                                v2_2.append(" Found:\n");
                                v2_2.append(v0_1);
                                throw new IllegalStateException(v2_2.toString());
                            }

                            v2_2 = new StringBuilder();
                            v2_2.append("Migration didn\'t properly handle SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n");
                            v2_2.append(v5);
                            v2_2.append("\n");
                            v2_2.append(" Found:\n");
                            v2_2.append(v1_1);
                            throw new IllegalStateException(v2_2.toString());
                        }

                        v2_2 = new StringBuilder();
                        v2_2.append("Migration didn\'t properly handle WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n");
                        v2_2.append(v5);
                        v2_2.append("\n");
                        v2_2.append(" Found:\n");
                        v2_2.append(v1_1);
                        throw new IllegalStateException(v2_2.toString());
                    }

                    v2_2 = new StringBuilder();
                    v2_2.append("Migration didn\'t properly handle WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n");
                    v2_2.append(v5);
                    v2_2.append("\n");
                    v2_2.append(" Found:\n");
                    v2_2.append(v1_1);
                    throw new IllegalStateException(v2_2.toString());
                }

                v2_2 = new StringBuilder();
                v2_2.append("Migration didn\'t properly handle Dependency(androidx.work.impl.model.Dependency).\n Expected:\n");
                v2_2.append(v5);
                v2_2.append("\n");
                v2_2.append(" Found:\n");
                v2_2.append(v1_1);
                throw new IllegalStateException(v2_2.toString());
            }
        }, "c45e5fcbdf3824dead9778f19e2fd8af", "433431a854c108416da77d9b397eaeec")).a());
    }

    static List c(WorkDatabase_Impl arg0) {
        return arg0.c;
    }

    protected android.arch.b.b.c c() {
        return new android.arch.b.b.c(((android.arch.b.b.e)this), new String[]{"Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName"});
    }

    static List d(WorkDatabase_Impl arg0) {
        return arg0.c;
    }

    static List e(WorkDatabase_Impl arg0) {
        return arg0.c;
    }

    static List f(WorkDatabase_Impl arg0) {
        return arg0.c;
    }

    public k m() {
        if(this.d != null) {
            return this.d;
        }

        __monitor_enter(this);
        try {
            if(this.d == null) {
                this.d = new l(((android.arch.b.b.e)this));
            }

            __monitor_exit(this);
            return this.d;
        label_14:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_14;
        }

        throw v0;
    }

    public b n() {
        if(this.e != null) {
            return this.e;
        }

        __monitor_enter(this);
        try {
            if(this.e == null) {
                this.e = new androidx.work.impl.b.c(((android.arch.b.b.e)this));
            }

            __monitor_exit(this);
            return this.e;
        label_14:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_14;
        }

        throw v0;
    }

    public n o() {
        if(this.f != null) {
            return this.f;
        }

        __monitor_enter(this);
        try {
            if(this.f == null) {
                this.f = new o(((android.arch.b.b.e)this));
            }

            __monitor_exit(this);
            return this.f;
        label_14:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_14;
        }

        throw v0;
    }

    public e p() {
        if(this.g != null) {
            return this.g;
        }

        __monitor_enter(this);
        try {
            if(this.g == null) {
                this.g = new f(((android.arch.b.b.e)this));
            }

            __monitor_exit(this);
            return this.g;
        label_14:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_14;
        }

        throw v0;
    }

    public h q() {
        if(this.h != null) {
            return this.h;
        }

        __monitor_enter(this);
        try {
            if(this.h == null) {
                this.h = new i(((android.arch.b.b.e)this));
            }

            __monitor_exit(this);
            return this.h;
        label_14:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_14;
        }

        throw v0;
    }
}

