package com.android.cbh.pwd.mvp.model.evenbus;
//定义事件
public class EventCenter<T> {

    /**
     * 指定 这个类里放的类型是，如果不写的话就自动转成 object 了类型了
     * 泛型是Java SE 1.5的新特性，泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。这
     * 种参数类型可以用在类、接口和方法的创建中，分别称为泛型类、泛型接口、泛型方法。 Java语言引入泛型的好处是安全简单。
     * reserved data保留数据
     */
    private T data;
    private int position;

    /**
     * this code distinguish between different events此代码区分不同的事件。
     */
    private int eventCode = -1;

    public EventCenter(int eventCode) {
        this(eventCode, null);
    }

    public EventCenter(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    /**
     * get event code获取事件代码
     *
     * @return
     */
    public int getEventCode() {
        return this.eventCode;
    }

    /**
     * 保留数据的事件
     *
     * @return
     */
    public T getData() {
        return this.data;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
//EventBus是一种用于Android的发布/订阅事件总线。它有很多优点：简化应用组件间的通信；
//        解耦事件的发送者和接收者；避免复杂和容易出错的依赖和生命周期的问题；很快，专门为高性能优化过等等。
//主要分为3个步骤：
//        定义事件。
//        订阅事件。
//        发布事件。
//evenbus实现界面通讯的更新