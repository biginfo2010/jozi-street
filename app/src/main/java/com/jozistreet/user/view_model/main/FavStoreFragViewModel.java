package com.jozistreet.user.view_model.main;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jozistreet.user.api.favourite.FavouriteApi;
import com.jozistreet.user.model.common.StoreModel;
import com.jozistreet.user.model.common.TrendingBrandModel;
import com.jozistreet.user.model.res.BrandRes;
import com.jozistreet.user.model.res.StoreRes;
import com.jozistreet.user.sqlite.DatabaseQueryClass;
import com.jozistreet.user.utils.G;
import com.jozistreet.user.utils.GsonUtils;

import java.util.ArrayList;

public class FavStoreFragViewModel extends ViewModel {
    private MutableLiveData<Boolean> isBusy;
    private MutableLiveData<Integer> offset;
    private MutableLiveData<ArrayList<StoreModel>> storeList;
    public FavStoreFragViewModel(){
        isBusy = new MutableLiveData<>();
        isBusy.setValue(false);
        offset = new MutableLiveData<>();
        offset.setValue(0);
        storeList = new MutableLiveData<>();
        storeList.setValue(new ArrayList<>());

        EventBus.getDefault().register(this);
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
    public MutableLiveData<Integer> getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset.setValue(offset);
    }
    public MutableLiveData<ArrayList<StoreModel>> getStoreList() {
        return storeList;
    }
    public void setStoreList(ArrayList<StoreModel> storeList) {
        this.storeList.setValue(storeList);
    }
    public void loadLocalData() {
        try {
            String data = DatabaseQueryClass.getInstance().getData(G.getUserID(), "FavStore", "");
            if (!TextUtils.isEmpty(data)) {
                StoreRes localRes = GsonUtils.getInstance().fromJson(data, StoreRes.class);
                ArrayList<StoreModel> list = localRes.getData();
                setStoreList(list);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessResult(StoreRes res) {
        setIsBusy(false);
        if (res.isStatus()) {
            ArrayList<StoreModel> list = res.getData();
            setStoreList(list);
            if (offset.getValue() == 0) {
                String data = new Gson().toJson(res, new TypeToken<StoreRes>() {
                }.getType());
                DatabaseQueryClass.getInstance().insertData(
                        G.getUserID(),
                        "FavStore",
                        data,
                        "",
                        ""
                );
            }
        }
    }
    public void loadData() {
        FavouriteApi.getFavouriteStore(offset.getValue(), 20);
    }
    public void loadFriendData(int user_id) {
        setIsBusy(true);
        FavouriteApi.getFollowingStore(user_id, offset.getValue(), 20);
    }
    public void loadDataSearch(String key) {
        setIsBusy(true);
        FavouriteApi.getFavouriteStoreSearch(key, 0, 100);
    }
}
