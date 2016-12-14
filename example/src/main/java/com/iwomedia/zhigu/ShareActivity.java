package com.iwomedia.zhigu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.SharePlatform;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        ShareConfig config = ShareConfig.instance()
                .qqId("1103754464")
                .weiboId("3065380466")  //app secret:16e2e6729ee197b29fe733ae17356232  appkey:3065380466
                .weiboRedirectUrl("http://www.bjzzcb.com/")
                .wxId("wx10e9bd853cf3c9ad")
                .wxSecret("0bfc0b88d403b326f356bd91a634ebbd");
        ShareManager.init(config);

        //---------------知谷的配置-----------------//
        //包名：com.iwomedia.zhigu
        //签名配置：jk.key  signing2.properties
        //qq  appid: 1103754464   appkey:e5bto0LrPvQDgAUO
        //weibo    AppKey="3065380466"    AppSecret="16e2e6729ee197b29fe733ae17356232" RedirectUrl="http://www.bjzzcb.com/"
        //wx   AppId="wx10e9bd853cf3c9ad"   AppSecret="0bfc0b88d403b326f356bd91a634ebbd"

        //--------------路书的配置-------------------//
        //包名：com.iwomedia.roadbook
        //签名配置：iwolushu.key  signing.properties
        //qq  appid: 1104091687   appkey:zzRlYUKfUMaUQvDU
        //weibo    AppKey="3712494637"    AppSecret="6b2c85766422060df0d088d432c25b97" RedirectUrl="http://www.bjzzcb.com/"
        //wx   AppId="wx5be32f5cc3b95666"   AppSecret="675a5f8ca74d98992c1062cf43d5aa26"



        findViewById(R.id.action_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareBottomDialog dialog = new ShareBottomDialog();
                dialog.show(getSupportFragmentManager());
            }
        });

        findViewById(R.id.action_share_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtil.shareImage(ShareActivity.this, SharePlatform.QQ,
                        "http://shaohui.me/images/avatar.gif", new ShareListener() {
                            @Override
                            public void shareSuccess() {
                                Log.i("TAG", "分享成功");
                            }

                            @Override
                            public void shareFailure(Exception e) {
                                Log.i("TAG", "分享失败");
                            }

                            @Override
                            public void shareCancel() {
                                Log.i("TAG", "分享取消");
                            }
                        });
            }
        });

        findViewById(R.id.to_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShareActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.to_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShareActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
