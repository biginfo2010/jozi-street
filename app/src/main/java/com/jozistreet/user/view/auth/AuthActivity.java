package com.jozistreet.user.view.auth;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.jozistreet.user.R;
import com.jozistreet.user.base.BaseActivity;
import com.jozistreet.user.view_model.auth.AuthViewModel;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthActivity extends BaseActivity {
    private AuthViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(this);
        mViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.btnLogin, R.id.btnSignup})
    public void clickButtons(View view){
        switch (view.getId()){
            case R.id.btnLogin:
                mViewModel.gotoLogin(this);
                break;
            case R.id.btnSignup:
                mViewModel.gotoSignup(this);
                break;
        }
    }

}