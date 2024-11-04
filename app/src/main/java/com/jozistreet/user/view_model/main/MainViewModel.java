package com.jozistreet.user.view_model.main;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.jozistreet.user.view.cart.CartActivity;
import com.jozistreet.user.view.main.MainActivity;
import com.jozistreet.user.view.menu.MenuActivity;
import com.jozistreet.user.view.menu.NotificationActivity;
import com.jozistreet.user.view.search.SearchActivity;

public class MainViewModel extends ViewModel {
    public MainViewModel(){

    }
    public void goMenu(MainActivity activity) {
        Intent intent = new Intent(activity, MenuActivity.class);
        activity.startActivity(intent);
    }

    public void goCart(MainActivity activity) {
        Intent intent = new Intent(activity, CartActivity.class);
        activity.startActivity(intent);
    }

    public void goNotification(MainActivity activity) {
        Intent intent = new Intent(activity, NotificationActivity.class);
        activity.startActivity(intent);
    }

    public void goSearch(MainActivity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }
}
