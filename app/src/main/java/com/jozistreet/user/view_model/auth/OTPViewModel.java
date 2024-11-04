package com.jozistreet.user.view_model.auth;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.jozistreet.user.view.auth.OTPActivity;
import com.jozistreet.user.view.auth.ResetPasswordActivity;

public class OTPViewModel extends ViewModel {

    public OTPViewModel(){

    }

    public void gotoLogin(OTPActivity context) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        context.finish();

    }

}
