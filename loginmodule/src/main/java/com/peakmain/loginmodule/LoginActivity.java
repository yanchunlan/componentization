package com.peakmain.loginmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mzule.activityrouter.annotation.Router;
import com.peakmain.basemodule.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */

@Router("login")
public class LoginActivity extends AppCompatActivity {
    private EditText mPasswordView;
    private EditText mEmailView;

    private String username;
    private String userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = getIntent().getStringExtra("username");
        userpassword = getIntent().getStringExtra("userpassword");
        Log.e("TAG", "username=" + username);
        Log.e("TAG", "userpassword=" + userpassword);
        mEmailView = findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEmailView.getText().toString()) || TextUtils.isEmpty(mPasswordView.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "邮件和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (mEmailView.getText().toString().equals("peakmain@126.com")
                            && mPasswordView.getText().toString().equals("123456")) {
                        //Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        MessageEvent messageEvent = new MessageEvent("登陆成功");
                        messageEvent.setCode(200);
                        messageEvent.setPosition(1);
                        EventBus.getDefault().post(messageEvent);
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                        MessageEvent messageEvent = new MessageEvent("登陆失败");
                        messageEvent.setCode(400);
                        messageEvent.setPosition(1);
                        EventBus.getDefault().post(messageEvent);
                        LoginActivity.this.finish();
                    }
                }
            }
        });


    }

}

