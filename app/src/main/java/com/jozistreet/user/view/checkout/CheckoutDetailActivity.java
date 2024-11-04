package com.jozistreet.user.view.checkout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;

import com.jozistreet.user.R;
import com.jozistreet.user.base.BaseActivity;
import com.jozistreet.user.view_model.checkout.CheckoutDetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckoutDetailActivity extends BaseActivity {

    private CheckoutDetailViewModel mViewModel;
    private CheckoutDetailActivity activity;

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CheckoutDetailViewModel.class);
        setContentView(R.layout.activity_checkout_detail);
        ButterKnife.bind(this);
        activity = this;
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initView() {

    }


    @OnClick({R.id.imgBack, R.id.btnNext})
    public void onClickButtons(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnNext:
                mViewModel.goNext(activity);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}