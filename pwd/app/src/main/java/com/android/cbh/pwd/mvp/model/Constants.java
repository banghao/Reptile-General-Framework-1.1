package com.android.cbh.pwd.mvp.model;



public class Constants {
//    GESTURE手势
//    constant就是常量的意思，简单的说就是比如有的时候你写一组分支，当a=0;a=1.....但
//    是时间久了你就忘记0,和1分别代表什么，所以就定义静态常量，然后就可以便于代码的维护以及查看使用

        public static int REALM_VERSION = 1;

        public static int VIEW_MODE = 0;
        public static int CREATE_MODE = 1;

        public static int CREATE_GESTURE = 1;
        public static int UPDATE_GESTURE = 2;

        public final static class EVEN_BUS {
            public static int CHANGE_THEME = 2;
            public static int CHANGE_PASS_WORD_SHOW = 3;
        }

        public final static class SETTING {
            public static String OPEN_GESTURE = "OPEN_GESTURE";
            public static String OPEN_PASS_WORD_SHOW = "OPEN_PASS_WORD_SHOW";
        }


}
