package com.example.anynote.models;

import com.orm.SugarApp;
import com.orm.SugarContext;

public class App extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();
//        初始化数据库
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
