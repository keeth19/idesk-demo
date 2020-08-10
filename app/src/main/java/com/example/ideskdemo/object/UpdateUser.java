package com.example.ideskdemo.object;

public class UpdateUser {
    public String proName;
    public String proEmail;
    public String proPhone;
    private String proType;
    private String proLocation;
    private String proIntro;
    public String deviceModel;
    public String os;
    private String usersID;
    private String proImage;


    public UpdateUser() { }


    public UpdateUser(String proName, String proEmail, String proPhone, String proType, String proLocation, String proIntro, String proImage) {
        this.proName  = proName;
        this.proEmail = proEmail;
        this.proPhone = proPhone;
        this.proType  = proType;
        this.proLocation = proLocation;
        this.proIntro = proIntro;
        this.proImage = proImage;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getUsersID() {
        return usersID;
    }

    public void setUsersID(String usersID) {
        this.usersID = usersID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProEmail() {
        return proEmail;
    }

    public void setProEmail(String proEmail) {
        this.proEmail = proEmail;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProLocation() {
        return proLocation;
    }

    public void setProLocation(String proLocation) {
        this.proLocation = proLocation;
    }

    public String getProIntro() {
        return proIntro;
    }

    public void setProIntro(String proIntro) {
        this.proIntro = proIntro;
    }
}
