package com.jozistreet.user.api.cart;

import static com.jozistreet.user.utils.ParseUtil.parseDeliverCartProduct;
import static com.jozistreet.user.utils.ParseUtil.parseProduct;
import static com.jozistreet.user.utils.ParseUtil.parseShoppingCartProduct;

import android.text.TextUtils;

import com.jozistreet.user.api.ApiConstants;
import com.jozistreet.user.api.RetrofitClient;
import com.jozistreet.user.model.common.ApiErrorModel;
import com.jozistreet.user.model.common.ProductOneModel;
import com.jozistreet.user.model.req.CartReq;
import com.jozistreet.user.model.req.CheckoutDeliverReq;
import com.jozistreet.user.model.req.CheckoutShoppingReq;
import com.jozistreet.user.model.res.CartRes;
import com.jozistreet.user.model.res.CheckoutRes;
import com.jozistreet.user.model.res.DeliverCartBaseRes;
import com.jozistreet.user.model.res.DeliverCartInfoRes;
import com.jozistreet.user.model.res.DeliverCartOrderRes;
import com.jozistreet.user.model.res.DeliverHistoryRes;
import com.jozistreet.user.model.res.ExclusiveDealRes;
import com.jozistreet.user.model.res.ShoppingCartBaseRes;
import com.jozistreet.user.model.res.ShoppingCartInfoRes;
import com.jozistreet.user.model.res.ShoppingCartOrderRes;
import com.jozistreet.user.model.res.CommonRes;
import com.jozistreet.user.model.res.ShoppingHistoryRes;
import com.jozistreet.user.utils.G;
import com.jozistreet.user.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartApi {
    public static void addShoppingCart(CartReq req) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .addShoppingCart(G.getToken(), req)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        CartRes res = new CartRes();
                        res.setType("collect");
                        if (result.isSuccessful()) {
                            res.setStatus(result.body().isStatus());
                            res.setMessage(result.body().getMessage());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new CartRes());
                    }
                });
    }
    public static void addDeliverCart(CartReq req) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .addDeliverCart(G.getToken(), req)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        CartRes res = new CartRes();
                        res.setType("deliver");
                        if (result.isSuccessful()) {
                            res.setStatus(result.body().isStatus());
                            res.setMessage(result.body().getMessage());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new CartRes());
                    }
                });
    }
    public static void getShoppingCartAll() {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getShoppingCartAll(G.getToken())
                .enqueue(new Callback<ShoppingCartOrderRes>() {
                    @Override
                    public void onResponse(Call<ShoppingCartOrderRes> call, Response<ShoppingCartOrderRes> result) {
                        ShoppingCartOrderRes res = new ShoppingCartOrderRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                res.setData(result.body().getData());
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<ShoppingCartOrderRes> call, Throwable t) {
                        EventBus.getDefault().post(new ShoppingCartOrderRes());
                    }
                });
    }
    public static void getDeliverCartAll() {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getDeliverCartAll(G.getToken())
                .enqueue(new Callback<DeliverCartOrderRes>() {
                    @Override
                    public void onResponse(Call<DeliverCartOrderRes> call, Response<DeliverCartOrderRes> result) {
                        DeliverCartOrderRes res = new DeliverCartOrderRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                res.setData(result.body().getData());
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<DeliverCartOrderRes> call, Throwable t) {
                        EventBus.getDefault().post(new DeliverCartOrderRes());
                    }
                });
    }
    public static void getDeliverBaseInfo(int cart_id) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getDeliverCartBaseInfo(G.getToken(), cart_id)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        DeliverCartBaseRes res = new DeliverCartBaseRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                String dataStr = GsonUtils.getInstance().toJson(result.body().getData());
                                res = GsonUtils.getInstance().fromJson(dataStr, DeliverCartBaseRes.class);
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new DeliverCartBaseRes());
                    }
                });

    }
    public static void getDeliverCartById(int cart_id) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getDeliverCartById(G.getToken(), cart_id)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        DeliverCartInfoRes res = new DeliverCartInfoRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                String dataStr = GsonUtils.getInstance().toJson(result.body().getData());
                                res = GsonUtils.getInstance().fromJson(dataStr, DeliverCartInfoRes.class);
                                ArrayList<ProductOneModel> productList = parseDeliverCartProduct(res);
                                res.setProductList(productList);
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new DeliverCartInfoRes());
                    }
                });

    }
    public static void getShoppingCartById(int cart_id) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getShoppingCartById(G.getToken(), cart_id)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        ShoppingCartInfoRes res = new ShoppingCartInfoRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                String dataStr = GsonUtils.getInstance().toJson(result.body().getData());
                                res = GsonUtils.getInstance().fromJson(dataStr, ShoppingCartInfoRes.class);
                                ArrayList<ProductOneModel> productList = parseShoppingCartProduct(res);
                                res.setProductList(productList);
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new DeliverCartInfoRes());
                    }
                });

    }
    public static void getExcusiveDealsFromShoppingCart(int cart_id) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getExclusiveDealsFromShoppingCart(G.getToken(), cart_id)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        ExclusiveDealRes res = new ExclusiveDealRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                String dataStr = GsonUtils.getInstance().toJson(result.body().getData());
                                res = GsonUtils.getInstance().fromJson(dataStr, ExclusiveDealRes.class);
                                ArrayList<ProductOneModel> productList = new ArrayList<>();
                                productList.clear();
//                                for (int i = 0; i < res.getData().size(); i++) {
//                                    PromotionModel model = res.getData().get(i);
//                                    productList.addAll(parseOnePromotion(model).getProductList());
//                                }
                                try {
                                    JSONObject jsonObject = new JSONObject(dataStr);
                                    JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));

                                    if (jsonArray.length() != 0) {
                                        productList = parseProduct(jsonArray);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                res.setProductList(productList);
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new ExclusiveDealRes());
                    }
                });

    }
    public static void checkoutDeliver(CheckoutDeliverReq req) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .checkoutDeliver(G.getToken(), req)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        CheckoutRes res = new CheckoutRes();
                        res.setType("deliver");
                        if (result.isSuccessful()) {
                            res.setStatus(result.body().isStatus());
                            res.setMessage(result.body().getMessage());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new CheckoutRes());
                    }
                });
    }
    public static void checkoutShopping(CheckoutShoppingReq req) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .checkoutShopping(G.getToken(), req)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        CheckoutRes res = new CheckoutRes();
                        res.setType("deliver");
                        if (result.isSuccessful()) {
                            res.setStatus(result.body().isStatus());
                            res.setMessage(result.body().getMessage());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new CheckoutRes());
                    }
                });
    }
    public static void getAvailableTime(int cart_id) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getAvailableTime(G.getToken(), cart_id)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        ShoppingCartBaseRes res = new ShoppingCartBaseRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                String dataStr = GsonUtils.getInstance().toJson(result.body().getData());
                                res = GsonUtils.getInstance().fromJson(dataStr, ShoppingCartBaseRes.class);
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new ShoppingCartBaseRes());
                    }
                });

    }
    public static void getShoppingHistory(int offset, int limit) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getShoppingHistory(G.getToken(), offset, limit)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        ShoppingHistoryRes res = new ShoppingHistoryRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                String dataStr = GsonUtils.getInstance().toJson(result.body().getData());
                                res = GsonUtils.getInstance().fromJson(dataStr, ShoppingHistoryRes.class);
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new ShoppingHistoryRes());
                    }
                });

    }
    public static void getDeliverHistory(int offset, int limit) {
        RetrofitClient.getClient(ApiConstants.BASE_URL)
                .create(Cart.class)
                .getDeliverHistory(G.getToken(), offset, limit)
                .enqueue(new Callback<CommonRes>() {
                    @Override
                    public void onResponse(Call<CommonRes> call, Response<CommonRes> result) {
                        DeliverHistoryRes res = new DeliverHistoryRes();
                        if (result.isSuccessful()) {
                            if (result.body().isStatus()) {
                                String dataStr = GsonUtils.getInstance().toJson(result.body().getData());
                                res = GsonUtils.getInstance().fromJson(dataStr, DeliverHistoryRes.class);
                            } else {
                                res.setMessage(result.body().getMessage());
                            }
                            res.setStatus(result.body().isStatus());
                        } else if (result.errorBody() != null) {
                            ResponseBody errorBody = result.errorBody();
                            if (result.code() == 200) {
                                ApiErrorModel errorModel = null;
                                try {
                                    if (TextUtils.isEmpty(errorBody.string()) || errorBody.string().equalsIgnoreCase("Internal Server Error")) {
                                        res.setMessage("Something went wrong");
                                    } else {
                                        try {
                                            errorModel = GsonUtils.getInstance().fromJson(errorBody.string(), ApiErrorModel.class);
                                            res.setMessage(errorModel.getMessage());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                res.setMessage("Something went wrong");
                            }
                        }
                        EventBus.getDefault().post(res);
                    }

                    @Override
                    public void onFailure(Call<CommonRes> call, Throwable t) {
                        EventBus.getDefault().post(new DeliverHistoryRes());
                    }
                });

    }
    public static void deleteDeliverCart(int cart_id){

    }
}
