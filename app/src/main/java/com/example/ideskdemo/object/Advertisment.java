package com.example.ideskdemo.object;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Advertisment implements Parcelable {
    String advertisementId;
    String adTopic;
    String category;
    String subCategory;
    String image;
    String adType;
    String price;
    String perQuantity;
    String totalQuantity;
    String description;
    String userID;
    String username;
    String userType;
    String location;
    String address;
    boolean isTermsAgreed;

    public Advertisment() {

    }

    public Advertisment(String advertisementId, String adTopic, String category, String subCategory, String image, String adType, String price,
                        String perQuantity, String totalQuantity, String description, String username, String userID, String userType, String location, String address, boolean isTermsAgreed) {
        this.advertisementId = advertisementId;
        this.adTopic = adTopic;
        this.category = category;
        this.subCategory = subCategory;
        this.image = image;
        this.adType = adType;
        this.price = price;
        this.perQuantity = perQuantity;
        this.totalQuantity = totalQuantity;
        this.description = description;
        this.userID = userID;
        this.username = username;
        this.userType = userType;
        this.location = location;
        this.address = address;
        this.isTermsAgreed = isTermsAgreed;
    }

    protected Advertisment(Parcel in) {
        advertisementId = in.readString();
        adTopic = in.readString();
        category = in.readString();
        subCategory = in.readString();
        image = in.readString();
        adType = in.readString();
        price = in.readString();
        perQuantity = in.readString();
        totalQuantity = in.readString();
        description = in.readString();
        userID = in.readString();
        username = in.readString();
        userType = in.readString();
        location = in.readString();
        address = in.readString();
        isTermsAgreed = in.readByte() != 0;
    }

    public static final Creator<Advertisment> CREATOR = new Creator<Advertisment>() {
        @Override
        public Advertisment createFromParcel(Parcel in) {
            return new Advertisment(in);
        }

        @Override
        public Advertisment[] newArray(int size) {
            return new Advertisment[size];
        }
    };

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getAdTopic() {
        return adTopic;
    }

    public void setAdTopic(String adTopic) {
        this.adTopic = adTopic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPerQuantity() {
        return perQuantity;
    }

    public void setPerQuantity(String perQuantity) {
        this.perQuantity = perQuantity;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isTermsAgreed() {
        return isTermsAgreed;
    }

    public void setTermsAgreed(boolean termsAgreed) {
        isTermsAgreed = termsAgreed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(advertisementId);
            parcel.writeString(adTopic);
            parcel.writeString(category);
            parcel.writeString(subCategory);
            parcel.writeString(image);
            parcel.writeString(adType);
            parcel.writeString(price);
            parcel.writeString(perQuantity);
            parcel.writeString(totalQuantity);
            parcel.writeString(description);
            parcel.writeString(userID);
            parcel.writeString(username);
            parcel.writeString(userType);
            parcel.writeString(location);
            parcel.writeString(address);
            parcel.writeBoolean(isTermsAgreed);

    }
}