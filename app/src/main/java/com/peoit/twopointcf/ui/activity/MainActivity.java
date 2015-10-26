package com.peoit.twopointcf.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.view.FragmentIndicator;
import com.peoit.twopointcf.utils.DialogTool;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;


public class MainActivity extends AppCompatActivity {
    public static Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragmentIndicator(0);

        PgyUpdateManager.register(this);//蒲公英检查更新
        PgyUpdateManager.register(MainActivity.this,
                new UpdateManagerListener() {
                    @Override
                    public void onUpdateAvailable(final String result) {
                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        DialogTool.createCommonDialog(MainActivity.this, R.mipmap.ic_launcher, "更新", "确认更新？", "确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startDownloadTask(MainActivity.this, appBean.getDownloadURL());
                            }
                        }, "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                        myToast("取消");
                            }
                        }).show();
                    }
                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });
    }

    /**
     * 初始化fragment
     */
    private void setFragmentIndicator(int whichIsDefault) {
        mFragments = new Fragment[4];
        mFragments[0] = getSupportFragmentManager().findFragmentById(R.id.fragment_InvestFind);//投资发现
        mFragments[1] = getSupportFragmentManager().findFragmentById(R.id.fragment_BusinessDynamics);//资讯中心
        mFragments[2] = getSupportFragmentManager().findFragmentById(R.id.fragment_MyProject);//我的项目
        mFragments[3] = getSupportFragmentManager().findFragmentById(R.id.fragment_MyCenter);//个人中心
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                .hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).show(mFragments[whichIsDefault]).commit();

        FragmentIndicator mIndicator = (FragmentIndicator) findViewById(R.id.indicator);
        //FragmentIndicator.setIndicator(whichIsDefault);
        mIndicator.setOnIndicateListener(new FragmentIndicator.OnIndicateListener() {
            @Override
            public void onIndicate(View v, int which) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]).hide(mFragments[3]).show(mFragments[which]).commit();
            }
        });
    }

}
