package com.jozistreet.user.view_model.detail;

import android.content.Intent;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jozistreet.user.api.product.ProductApi;
import com.jozistreet.user.model.common.ProductDetailModel;
import com.jozistreet.user.model.res.ProductRes;
import com.jozistreet.user.sqlite.DatabaseQueryClass;
import com.jozistreet.user.utils.G;
import com.jozistreet.user.utils.GsonUtils;
import com.jozistreet.user.view.detail.ProductDetailActivity;
import com.jozistreet.user.view.detail.SingleProductDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

public class SingleProductDetailViewModel extends ViewModel {
    private MutableLiveData<Boolean> isBusy;
    private MutableLiveData<String> barcode;
    private MutableLiveData<ProductDetailModel> productInfo;

    public SingleProductDetailViewModel() {
        isBusy = new MutableLiveData<>();
        isBusy.setValue(false);
        barcode = new MutableLiveData<>();
        barcode.setValue("");
        productInfo = new MutableLiveData<>();
        productInfo.setValue(new ProductDetailModel());
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

    public MutableLiveData<ProductDetailModel> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductDetailModel productInfo) {
        this.productInfo.setValue(productInfo);;
    }

    public MutableLiveData<String> getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode.setValue(barcode);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessResult(ProductRes res) {
        setIsBusy(false);
        if (res.isStatus()) {
            setProductInfo(res.getData());
            String data = new Gson().toJson(res, new TypeToken<ProductRes>() {
            }.getType());
            DatabaseQueryClass.getInstance().insertData(
                    G.getUserID(),
                    "SingleProductDetail",
                    data,
                    barcode.getValue(),
                    ""
            );
        }
    }
    public void loadLocalData() {
        try {
            String data = DatabaseQueryClass.getInstance().getData(G.getUserID(), "SingleProductDetail", barcode.getValue());
            if (!TextUtils.isEmpty(data)) {
                ProductRes localRes = GsonUtils.getInstance().fromJson(data, ProductRes.class);
                setProductInfo(localRes.getData());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void loadData() {
        ProductApi.getProductDetail(barcode.getValue());
    }

    public void goPromotionDetail(SingleProductDetailActivity activity, String barcode) {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra("barcode", barcode);
        activity.startActivity(intent);
    }
}
