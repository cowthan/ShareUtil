package com.iwomedia.zhigu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import me.shaohui.shareutil.LoginUtil;
import me.shaohui.shareutil.login.LoginListener;
import me.shaohui.shareutil.login.LoginPlatform;
import me.shaohui.shareutil.login.LoginResult;
import me.shaohui.shareutil.login.result.QQToken;
import me.shaohui.shareutil.login.result.QQUser;
import me.shaohui.shareutil.login.result.WeiboToken;
import me.shaohui.shareutil.login.result.WeiboUser;
import me.shaohui.shareutil.login.result.WxToken;
import me.shaohui.shareutil.login.result.WxUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginListener mLoginListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_qq).setOnClickListener(this);
        findViewById(R.id.login_weibo).setOnClickListener(this);
        findViewById(R.id.login_wx).setOnClickListener(this);

        mLoginListener = new LoginListener() {
            @Override
            public void loginSuccess(LoginResult result) {
                Toast.makeText(LoginActivity.this,
                        result.getUserInfo() != null ? result.getUserInfo().getNickname()
                                : "" + "登录成功", Toast.LENGTH_SHORT).show();

                // 处理result
                switch (result.getPlatform()) {
                    case LoginPlatform.QQ:
                        QQUser user = (QQUser) result.getUserInfo();
                        QQToken token = (QQToken) result.getToken();
                        break;
                    case LoginPlatform.WEIBO:
                        WeiboUser user2 = (WeiboUser) result.getUserInfo();
                        WeiboToken token2 = (WeiboToken) result.getToken();
                        // 处理信息
                        break;
                    case LoginPlatform.WX:
                        WxUser user3 = (WxUser) result.getUserInfo();
                        WxToken token3 = (WxToken) result.getToken();
                        break;
                }
            }

            @Override
            public void loginFailure(Exception e) {
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginCancel() {
                Toast.makeText(LoginActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_qq:
                LoginUtil.login(this, LoginPlatform.QQ, mLoginListener);
                break;
            case R.id.login_weibo:
                LoginUtil.login(this, LoginPlatform.WEIBO, mLoginListener, false);
                break;
            case R.id.login_wx:
                LoginUtil.login(this, LoginPlatform.WX, mLoginListener);
                break;
        }
    }
}
