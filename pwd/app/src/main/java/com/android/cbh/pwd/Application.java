package com.android.cbh.pwd;

import android.os.Bundle;

import com.android.cbh.pwd.mvp.model.Constants;
import com.android.cbh.pwd.mvp.model.Realm.RealmMigration;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Application extends android.app.Application {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate();
//        initRealm();
////        Stetho.initialize(
////                Stetho.newInitializerBuilder(this)
////                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
////                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
////                        .build()
////        );
//    }
    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("pwd.realm")
                .migration(new RealmMigration())
                .schemaVersion(Constants.REALM_VERSION)
                .build();
//        指定迁移操作的迁移类。
        Realm.setDefaultConfiguration(realmConfiguration);


    }
}
