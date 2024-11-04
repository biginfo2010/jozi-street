package com.jozistreet.user.view_model.main;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jozistreet.user.api.store.StoreApi;
import com.jozistreet.user.model.common.StoreModel;
import com.jozistreet.user.model.res.FeedRes;
import com.jozistreet.user.model.res.StoreRes;
import com.jozistreet.user.sqlite.DatabaseQueryClass;
import com.jozistreet.user.utils.G;
import com.jozistreet.user.utils.GsonUtils;

public class StoreFragViewModel extends ViewModel {
    private MutableLiveData<Boolean> isBusy;
    private MutableLiveData<Integer> offset;
    private MutableLiveData<String> keyword;
    private MutableLiveData<ArrayList<StoreModel>> mStoreList;


    public StoreFragViewModel() {
        isBusy = new MutableLiveData<>();
        isBusy.setValue(false);
        offset = new MutableLiveData<>();
        offset.setValue(0);
        keyword = new MutableLiveData<>();
        keyword.setValue("");
        mStoreList = new MutableLiveData<>();
        mStoreList.setValue(new ArrayList<>());

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

    public MutableLiveData<ArrayList<StoreModel>> getStoreList() {
        return mStoreList;
    }

    public void setStoreList(ArrayList<StoreModel> mStoreList) {
        this.mStoreList.setValue(mStoreList);
    }

    public MutableLiveData<Integer> getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset.setValue(offset);
    }

    public MutableLiveData<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword.setValue(keyword);
    }

    public void loadData() {
        StoreApi.getStoreList(offset.getValue(), 20, keyword.getValue());
    }
    public void loadLocalData() {
        try {
            String data = DatabaseQueryClass.getInstance().getData(G.getUserID(), "StoreFragment", "");
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
    public void onSuccessStoreList(StoreRes res) {
        setIsBusy(false);
        if (res.isStatus()) {
            ArrayList<StoreModel> list = res.getData();
            setStoreList(list);
            if (offset.getValue() == 0) {
                String data = new Gson().toJson(res, new TypeToken<StoreRes>() {
                }.getType());
                DatabaseQueryClass.getInstance().insertData(
                        G.getUserID(),
                        "StoreFragment",
                        data,
                        "",
                        ""
                );
            }
        }
    }
}
