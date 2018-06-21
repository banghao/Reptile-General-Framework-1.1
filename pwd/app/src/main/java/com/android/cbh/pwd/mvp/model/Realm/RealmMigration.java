package com.android.cbh.pwd.mvp.model.Realm;

import io.realm.DynamicRealm;
import io.realm.RealmSchema;

/**
 * Created by cbh on 2018/6/2.
 *
 当数据库结构发生改变的时候，我们需要配置Migration来实现数据迁移的操作。
 当Realm发现新旧版本号不一致时，会自动使用该迁移类完成迁移操作。
 */
public class RealmMigration implements io.realm.RealmMigration {

    @Override
    public void migrate(DynamicRealm dynamicRealm, long oldVersion, long newVersion) {
        RealmSchema schema = dynamicRealm.getSchema();

        if (oldVersion == 0) {
            schema.get("God")
                    .addField("memoInfo", String.class);
            oldVersion++;
        }
    }
}
