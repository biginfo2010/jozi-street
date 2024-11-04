package com.jozistreet.user.view.seeall;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.jozistreet.user.R;
import com.jozistreet.user.adapter.AllStoreCategoryAdapter;
import com.jozistreet.user.adapter.NotificationAdapter;
import com.jozistreet.user.adapter.StoreCategoryAdapter;
import com.jozistreet.user.base.BaseActivity;
import com.jozistreet.user.listener.ClickListener;
import com.jozistreet.user.model.common.NotificationModel;
import com.jozistreet.user.model.common.StoreCategoryModel;
import com.jozistreet.user.sqlite.DatabaseQueryClass;
import com.jozistreet.user.utils.G;
import com.jozistreet.user.view.cart.DeliverCartActivity;
import com.jozistreet.user.view.cart.ShoppingCartActivity;
import com.jozistreet.user.view.detail.BrandDetailActivity;
import com.jozistreet.user.view.detail.ProductDetailActivity;
import com.jozistreet.user.view.detail.PromotionDetailActivity;
import com.jozistreet.user.view.detail.StoreCategoryDetailActivity;
import com.jozistreet.user.view.detail.StoreDetailActivity;
import com.jozistreet.user.view.detail.VideoPlayerActivity;
import com.jozistreet.user.view.profile.FriendActivity;
import com.jozistreet.user.view_model.menu.NotificationViewModel;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllStoreCategoryActivity extends BaseActivity {

    private AllStoreCategoryActivity activity;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.li_empty)
    LinearLayout li_empty;

    private AllStoreCategoryAdapter storeCategoryAdapter;
    ArrayList<StoreCategoryModel> storeCategoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_store_category);
        ButterKnife.bind(this);
        activity = this;
        setStoreCategoryRecycler();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    private void setStoreCategoryRecycler() {
        storeCategoryAdapter = new AllStoreCategoryAdapter(this, G.storeCategoryList, new AllStoreCategoryAdapter.StoreCategoryAdapterRecyclerListener() {
            @Override
            public void onItemClicked(int pos, StoreCategoryModel model) {
                Intent intent = new Intent(AllStoreCategoryActivity.this, StoreCategoryDetailActivity.class);
                intent.putExtra("category_id", model.getId());
                intent.putExtra("category_name", model.getName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(storeCategoryAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }



    @OnClick({R.id.imgBack})
    public void onClickButtons(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}