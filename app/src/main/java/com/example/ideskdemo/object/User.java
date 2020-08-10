package com.example.ideskdemo.object;

public class User {
    public String name;
    public String email;
    public String phone;
    public String password;
    public String deviceModel;

    public String os;
    private String usersID;
    private String online;

    private String simpleAdd ="";
    private String featAdd ="";

    private String type;
    private String location;
    private String package1 = "";
    private String image;
    private String EXDate = "";
    private String review = "";
    private String feedback = "";


    private String proType;
    private String proIntro;

    private String schoolName;
    private String gradeName;
    private String className;
    private String attendantStatus;

    public User() {
    }

    public User(String name, String email, String phone, String password, String deviceModel, String os) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.deviceModel = deviceModel;
        this.os = os;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPackage1() {
        return package1;
    }

    public void setPackage1(String package1) {
        this.package1 = package1;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEXDate() {
        return EXDate;
    }

    public void setEXDate(String EXDate) {
        this.EXDate = EXDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProIntro() {
        return proIntro;
    }

    public void setProIntro(String proIntro) {
        this.proIntro = proIntro;
    }

    public String getSimpleAdd() {
        return simpleAdd;
    }

    public void setSimpleAdd(String simpleAdd) {
        this.simpleAdd = simpleAdd;
    }

    public String getFeatAdd() {
        return featAdd;
    }

    public void setFeatAdd(String featAdd) {
        this.featAdd = featAdd;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAttendantStatus() {
        return attendantStatus;
    }

    public void setAttendantStatus(String attendantStatus) {
        this.attendantStatus = attendantStatus;
    }
}
