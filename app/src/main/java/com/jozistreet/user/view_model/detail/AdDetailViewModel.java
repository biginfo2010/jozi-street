package com.jozistreet.user.view_model.detail;

import static com.jozistreet.user.utils.ParseUtil.parseSingleProduct;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jozistreet.user.api.newsfeed.NewsFeedApi;
import com.jozistreet.user.api.promotion.PromotionApi;
import com.jozistreet.user.model.common.BrandModel;
import com.jozistreet.user.model.common.FeedModel;
import com.jozistreet.user.model.common.PostModel;
import com.jozistreet.user.model.common.ProductOneModel;
import com.jozistreet.user.model.common.PromotionModel;
import com.jozistreet.user.model.common.PromotionOneModel;
import com.jozistreet.user.model.common.TrendingBrandModel;
import com.jozistreet.user.model.res.AdRes;
import com.jozistreet.user.model.res.ProductSingleRes;
import com.jozistreet.user.model.res.PromotionRes;
import com.jozistreet.user.sqlite.DatabaseQueryClass;
import com.jozistreet.user.utils.G;
import com.jozistreet.user.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.util.ArrayList;

public class AdDetailViewModel extends ViewModel {
    private MutableLiveData<Boolean> isBusy;
    private MutableLiveData<String> feedType;
    private MutableLiveData<FeedModel> feedInfo;
    private MutableLiveData<ArrayList<TrendingBrandModel>> brandList;

    public AdDetailViewModel() {
        isBusy = new MutableLiveData<>();
        isBusy.setValue(false);
        feedType = new MutableLiveData<>();
        feedType.setValue("");
        feedInfo = new MutableLiveData<>();
        feedInfo.setValue(new FeedModel());
        brandList = new MutableLiveData<>();
        brandList.setValue(new ArrayList<>());

        EventBus.getDefault().register(this);
    }
    public void loadLocalData() {
        try {
            String data = DatabaseQueryClass.getInstance().getData(G.getUserID(), "HomeAdvert", this.feedType.getValue());
            if (!TextUtils.isEmpty(data)) {
                AdRes localRes = GsonUtils.getInstance().fromJson(data, AdRes.class);
                setFeedInfo(localRes.getFeedInfo());
                setBrandList(localRes.getBrandList());
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }

    public MutableLiveData<Boolean> getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy.setValue(isBusy);
    }



    public MutableLiveData<String> getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType.setValue(feedType);
    }

    public MutableLiveData<FeedModel> getFeedInfo() {
        return feedInfo;
    }

    public void setFeedInfo(FeedModel feedInfo) {
        this.feedInfo.setValue(feedInfo);
    }

    public MutableLiveData<ArrayList<TrendingBrandModel>> getBrandList() {
        return brandList;
    }

    public void setBrandList(ArrayList<TrendingBrandModel> brandList) {
        this.brandList.setValue(brandList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessResult(AdRes res) {
        setIsBusy(false);
        if (res.isStatus()) {
            setBrandList(res.getBrandList());
            setFeedInfo(res.getFeedInfo());
            String data = new Gson().toJson(res, new TypeToken<AdRes>() {
            }.getType());
            DatabaseQueryClass.getInstance().insertData(
                    G.getUserID(),
                    "HomeAdvert",
                    data,
                    this.feedType.getValue(),
                    ""
            );
        }
    }

    public void loadData(int id) {
        NewsFeedApi.getAdDetail(id);
    }

}
