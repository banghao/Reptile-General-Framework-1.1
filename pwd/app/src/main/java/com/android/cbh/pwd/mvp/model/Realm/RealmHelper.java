package com.android.cbh.pwd.mvp.model.Realm;

import android.content.Context;

import com.android.cbh.pwd.mvp.model.bean.God;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 *
 * chrome://inspect/#devices，即可看到当前数据库内容
 */
//虽然从一个Realm读取数据非常简单（下一节有讲），但是向它写入数据就稍微复杂一点。Realm遵循 ACID
//（数据库事务正确执行的四个基本要素的缩写）规范，为了确保原子性和一致性，它强制所有的写入操作都在一个事务中执行。
//条件
//        目前不支持Android以外的Java
//        Android Studio >= 1.5.1
//        较新的Android SDK版本
//        JDK version >=7.
//        支持API 9(Android 2.3)以及之后的版本

//2014年7月发布一款支持运行在手机、平板和可穿戴设备上的嵌入式数据库，目标是取代SQLite。
//相比sqlite，Realm具有别人不可比拟的神奇速度，哦，这不是重点，它还有强大先进的特性等着你，
//        比如更重要数据加密支持，对Json的支持，流畅的API，数据观察者变化，跨平台
// 如他的名字一样：王国，领域，所有的一切都是为了让我们程序猿更加潇洒！还有一个很强大的特性，那就是它可以总能获取到最新的数
public class RealmHelper {

    private static RealmHelper instances;
    private Context mContext;

    private RealmHelper(Context context){
        mContext = context;
    }
    public static RealmHelper getInstances(Context context){
        synchronized (RealmHelper.class) {
            if (instances == null) {
                instances = new RealmHelper(context);
            }
        }
        return instances;
    }
   //关闭数据库链接
    private static void closeConnect(Realm realm) {
        if (null != realm) {
            try {
                realm.close();
                realm = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  //secure安全（官方原文）
//  Realm 文件可以通过传递一个512位（64字节）的密钥参数给 Realm.getInstance().encryptionKey() 来加
//    密存储在磁盘上。保证了所有永久性存储在磁盘上的数据都是通过标准 AES-256 加密的。每次创建新的 Realm 实例的时候，都需要提供相同的密钥。
//    参考 examples/encryptionExample。这个例子演示了如何通过 Android KeyStore 来安全地存储密钥。
    private static RealmConfiguration secure(Context context) {
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                .encryptionKey(key)
                .build();
        //Builder.encryptionKey : 指定数据库的密钥。
          //自定义创建数据库
        // Start with a clean slate every time
        Realm.deleteRealm(realmConfiguration);
        return realmConfiguration;
    }
    //
    public static ArrayList<God> selector(Context context, int godType){
        Realm realm = Realm.getInstance(context);
        RealmQuery<God> realmQuery = realm.where(God.class);
        RealmQuery<God> godRealmQuery = realmQuery.equalTo("godType", godType);
        RealmResults<God> realmResults = godRealmQuery.findAll();
//        在使用where()方法时，能得到一个RealmQuery对象，
//        Tip：查询的时候你不用当心得到的RealmResults为null。如果查询的结果为空，那么RealmResults的size为0
        if (realmResults != null && realmResults.size() > 0) {
            ArrayList<God> godList = new ArrayList<>();
            for (God god : realmResults) {
                godList.add(god);
            }
            Collections.reverse(godList);
            return godList;
        }
        return null;
    }
    //新增，如果有主键且主键重复会报错新增或修改，如果主键不存在则为新增，主键存在则修改
    public static boolean save(Context context, God god) {
        if (check(context, god)) {
            return true;
        }
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();//插入数据时开启事务
        realm.copyToRealm(god);//使用copyToRealmOrUpdate或copyToRealm方法插入数据
        realm.commitTransaction();//提交事务
        return false;
    }

    private static boolean check(Context context, God god) {
        int godType = god.getGodType();
        Realm realm = Realm.getInstance(context);
        RealmQuery<God> realmQuery = realm.where(God.class);
        RealmQuery<God> godRealmQuery = realmQuery.equalTo("godType", godType);
        RealmResults<God> title = godRealmQuery.contains("title", god.getTitle()).findAll();//.contains()包含xxx
        if (title!=null&&title.size()>0) {
            return true;
        }
        return false;
    }

    /**
     * 更新数据库
     * @param context 上下文
     * @param god bean
     * @return 成功返回true
     * @param参数
     */
//    要创建一个新的Realm，你可以在任意Activity中调用静态方法Realm.getInstance
//    Realm myRealm = Realm.getInstance(context);
//1
//    注意，调用Realm.getInstance，而没有传入RealmConfiguration，会创建一个叫做 default.realm的Realm文件。
//    如果你想向app中添加另一个Realm，必须使用一个RealmConfiguration.Builder对象，并为 Realm file 指定一个独有的名字。
    public static boolean update(Context context, God god) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(god);
        realm.commitTransaction();
        return true;
    }

    public static void delete(Context context, God god, int position) {
        Realm realm = Realm.getInstance(context);
        RealmQuery<God> realmQuery = realm.where(God.class);
        RealmQuery<God> godRealmQuery = realmQuery.equalTo("godType", god.getGodType());
        RealmResults<God> realmResults = godRealmQuery.findAll();
        if (realmResults != null ) {
            int size = realmResults.size() - 1;
            int i = size - position;
            realm.beginTransaction();
            realmResults.remove(i);
            realm.commitTransaction();
        }
    }
}

//sqlite因为代码量太多了！作为一个要为全世界做贡献的程序猿，怎么可以忍受这样多的代码实现一点小东西呢？No,No，我们绝对不能接受！！！
