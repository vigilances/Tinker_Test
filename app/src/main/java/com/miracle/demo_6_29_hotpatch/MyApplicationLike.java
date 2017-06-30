package com.miracle.demo_6_29_hotpatch;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.miracle.demo_6_29_hotpatch.Log.MyLogImp;
import com.miracle.demo_6_29_hotpatch.util.TinkerManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by Miracle on 2017/6/29.
 */
@DefaultLifeCycle(application = "com.miracle.demo_6_29_hotpatch.MyApplication",//通过注解，由tinker自动生成MyApplication
        flags = ShareConstants.TINKER_ENABLE_ALL,                 //tinkerFlags
        loaderClass = "com.tencent.tinker.loader.TinkerLoader",   //loaderClassName, 我们这里使用默认
        loadVerifyFlag = false)

public class MyApplicationLike extends DefaultApplicationLike {
    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        MyApplicationContext.application = (MyApplication) getApplication();
        MyApplicationContext.context = getApplication();
        TinkerManager.setTinkerApplicationLike(this);
        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);
        //optional set logIml, or you can use default debug log
        TinkerInstaller.setLogIml(new MyLogImp());
        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);
    }
}
