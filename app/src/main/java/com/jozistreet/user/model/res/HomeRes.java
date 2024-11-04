package com.jozistreet.user.model.res;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.jozistreet.user.model.common.ProductOneModel;
import com.jozistreet.user.model.common.DiscoverModel;
import com.jozistreet.user.model.common.FeedModel;
import com.jozistreet.user.model.common.PromotionModel;
import com.jozistreet.user.model.common.StoreCategoryModel;
import com.jozistreet.user.model.common.TrendingBrandModel;

public class HomeRes {
    @SerializedName("storyList")
    @Expose
    private ArrayList<DiscoverModel> storyList;

    @SerializedName("feedList")
    @Expose
    private ArrayList<FeedModel> feedList;

    @SerializedName("poupularList")
    @Expose
    private ArrayList<FeedModel> poupularList;

    @SerializedName("trendingBrandList")
    @Expose
    private ArrayList<TrendingBrandModel> brandList;

    @SerializedName("featuredStoreList")
    @Expose
    private ArrayList<FeedModel> storeList;

    @SerializedName("bestSellingList")
    @Expose
    private ArrayList<PromotionModel> bestSellingList;

    private ArrayList<ProductOneModel> sellingList = new ArrayList<>();

    @SerializedName("promotionList")
    @Expose
    private ArrayList<FeedModel> specialDealList;

    @SerializedName("exclusiveDealList")
    @Expose
    private ArrayList<PromotionModel> exclusiveDealList;

    @SerializedName("cantMissDealList")
    @Expose
    private ArrayList<FeedModel> missDealList;

    @SerializedName("featuredBrandList")
    @Expose
    private ArrayList<FeedModel> featuredBrandList;

    @SerializedName("homeAdvertList")
    @Expose
    private ArrayList<FeedModel> homeAdvertList;

    @SerializedName("storeCategoryList")
    @Expose
    private ArrayList<StoreCategoryModel> storeCategoryList;

    @SerializedName("totalCount")
    @Expose
    private int totalCount;

    private boolean status;
    private String message;
    public HomeRes(){
        storyList = new ArrayList<>();
        feedList = new ArrayList<>();
        poupularList = new ArrayList<>();
        brandList = new ArrayList<>();
        storeList = new ArrayList<>();
        bestSellingList = new ArrayList<>();
        storeCategoryList = new ArrayList<>();
        storeCategoryList.clear();
        sellingList.clear();
        missDealList = new ArrayList<>();
        missDealList.clear();
        featuredBrandList = new ArrayList<>();
        featuredBrandList.clear();
        homeAdvertList = new ArrayList<>();
        homeAdvertList.clear();
        exclusiveDealList = new ArrayList<>();
        exclusiveDealList.clear();
        specialDealList = new ArrayList<>();
        specialDealList.clear();
        this.status = false;
        this.message = "";
    }

    public ArrayList<FeedModel> getFeedList() {
        return feedList;
    }

    public void setFeedList(ArrayList<FeedModel> feedList) {
        this.feedList = feedList;
    }

    public ArrayList<DiscoverModel> getStoryList() {
        return storyList;
    }

    public void setStoryList(ArrayList<DiscoverModel> storyList) {
        this.storyList = storyList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<FeedModel> getPoupularList() {
        //TODO: To hide
        poupularList.clear();
        return poupularList;
    }

    public void setPoupularList(ArrayList<FeedModel> poupularList) {
        this.poupularList = poupularList;
    }

    public ArrayList<TrendingBrandModel> getBrandList() {
        //TODO: To hide
        brandList.clear();
        return brandList;
    }

    public void setBrandList(ArrayList<TrendingBrandModel> brandList) {
        this.brandList = brandList;
    }

    public ArrayList<FeedModel> getStoreList() {
        // Featured Stores
        //TODO: To hide
        storeList.clear();
        return storeList;
    }

    public void setStoreList(ArrayList<FeedModel> storeList) {
        this.storeList = storeList;
    }

    public ArrayList<ProductOneModel> getSellingList() {
        //TODO: To hide
        sellingList.clear();
        return sellingList;
    }

    public void setSellingList(ArrayList<ProductOneModel> sellingList) {
        this.sellingList = sellingList;
    }

    public ArrayList<PromotionModel> getBestSellingList() {
        //TODO: To hide
        bestSellingList.clear();
        return bestSellingList;
    }

    public void setBestSellingList(ArrayList<PromotionModel> bestSellingList) {
        this.bestSellingList = bestSellingList;
    }

    public ArrayList<FeedModel> getSpecialDealList() {
        // Promotion
        //TODO: To hide
        specialDealList.clear();
        return specialDealList;
    }

    public void setSpecialDealList(ArrayList<FeedModel> specialDealList) {
        this.specialDealList = specialDealList;
    }

    public ArrayList<PromotionModel> getExclusiveDealList() {
        //TODO: To hide
        exclusiveDealList.clear();
        return exclusiveDealList;
    }

    public void setExclusiveDealList(ArrayList<PromotionModel> exclusiveDealList) {
        this.exclusiveDealList = exclusiveDealList;
    }

    public ArrayList<FeedModel> getMissDealList() {
        // My Deals
        return missDealList;
    }

    public void setMissDealList(ArrayList<FeedModel> missDealList) {
        this.missDealList = missDealList;
    }

    public ArrayList<FeedModel> getFeaturedBrandList() {
        //TODO: To hide
        featuredBrandList.clear();
        return featuredBrandList;
    }

    public void setFeaturedBrandList(ArrayList<FeedModel> featuredBrandList) {
        this.featuredBrandList = featuredBrandList;
    }

    public ArrayList<FeedModel> getHomeAdvertList() {
        //TODO: To hide
        homeAdvertList.clear();
        return homeAdvertList;
    }

    public void setHomeAdvertList(ArrayList<FeedModel> homeAdvertList) {
        this.homeAdvertList = homeAdvertList;
    }

    public ArrayList<StoreCategoryModel> getStoreCategoryList() {
        return storeCategoryList;
    }

    public void setStoreCategoryList(ArrayList<StoreCategoryModel> storeCategoryList) {
        this.storeCategoryList = storeCategoryList;
    }
}
