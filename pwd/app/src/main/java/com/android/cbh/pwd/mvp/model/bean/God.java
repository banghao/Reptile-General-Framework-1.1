package com.android.cbh.pwd.mvp.model.bean;

import com.android.cbh.pwd.utils.encrypt.Base64Util;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by cbh on 2018/6/2.
 * toString方法都不可以有,有严格要求，
 * 实体类
 * 创建实体，继承RealmObject
 */
public class God extends RealmObject{

    /**
     * 密码所属的分组
     */
    private int godType;

    /**
     * 标题，注明该账号为哪个网站的账号，主键@PrimaryKey
     * 使用了该注解之后可以使用copyToRealmOrUpdate()方法，通过主键查询它的对象，如果查询到了，则更新它，否则新建一个对象来代替
     * 标识主键，不支持自增主键（变相解决了多线程下主键自增可能会重复的问题）
     */
    @PrimaryKey
    private String title;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 创建的时间,或者修改时间
     */
    private long time;

    /**
     * 备忘信息
     */
    private String memoInfo;

    public God() {}

    public God(int godType, String title, String userName, String passWord, long time, String memoInfo) {
        this.godType = godType;
        this.title = Base64Util.encryptBASE64(title);
        this.userName = Base64Util.encryptBASE64(userName);
        this.passWord = Base64Util.encryptBASE64(passWord);
        this.time = time;
        this.memoInfo = Base64Util.encryptBASE64(memoInfo);
    }

    public int getGodType() {
        return godType;
    }

    public void setGodType(int godType) {
        this.godType = godType;
    }

    public String getUserName() {
        return Base64Util.decryptBASE64(userName);
    }

    public void setUserName(String userName) {
        this.userName = Base64Util.encryptBASE64(userName);
    }

    public String getPassWord() {
        return Base64Util.decryptBASE64(passWord);
    }

    public void setPassWord(String passWord) {
        this.passWord = Base64Util.encryptBASE64(passWord);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return Base64Util.decryptBASE64(title);
    }

    public void setTitle(String title) {
        this.title = Base64Util.encryptBASE64(title);
    }

    public String getMemoInfo() {
        return Base64Util.decryptBASE64(memoInfo);
    }

    public void setMemoInfo(String memoInfo) {
        this.memoInfo = Base64Util.encryptBASE64(memoInfo);
    }

}
//用处就是将一些不适合传输的数据内容进行编码来适合传输

