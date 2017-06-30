package com.miracle.demo_6_29_hotpatch;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.showToast(this, "load patch successful !");
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText("更新成功");
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.load_patch:
                TinkerInstaller.onReceiveUpgradePatch(this,
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                Log.i("!!!", "click: " + Environment.getExternalStorageDirectory().getAbsolutePath());
                break;
            case R.id.clear_patch:
                Util.showToast(this, "clean patch !");
                Tinker.with(this).cleanPatch();
                break;
            case R.id.kill:
                ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }
}
