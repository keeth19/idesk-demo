package com.example.ideskdemo.object;

public class FavouriteAdvertisement {
    String FavoriteId;
    String AdvertisementID;
    String userId;

    public String getFavoriteId() {
        return FavoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        FavoriteId = favoriteId;
    }

    public String getAdvertisementID() {
        return AdvertisementID;
    }

    public void setAdvertisementID(String advertisementID) {
        AdvertisementID = advertisementID;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
