package com.example.ideskdemo.database;

class db_tables {

    //    District table
    static final String TABLE_NAME_DISTRICT     = "DISTRICT";

    private static final String DISTRICT_ID     = "_id";
            static final String DISTRICT_NAME   = "district_name";

    //    category table
    static final String TABLE_NAME_CATEGORY     = "CATEGORY";

    static final String ID     = "id";
    static final String CATEGORY_ID     = "category_id";
    static final String CATEGORY_NAME   = "category_name";
    static final String CATEGORY_IMAGE  = "category_image";

    //    category table
    static final String TABLE_NAME_SUB_CATEGORY     = "SUB_CATEGORY";

    static final String SUB_ID                          = "_id";
    static final String SUB_CATEGORY_ID                 = "sub_id";
    static final String SUB_CATEGORY_MAIN_CATEGORY_ID   = "sub_category_main_category_id";
    static final String SUB_CATEGORY_NAME               = "sub_category_name";
    static final String SUB_CATEGORY_IMAGE              = "sub_category_image";

   // user table
    static final String TABLE_NAME_USER     = "USER";

    static final String USER_ID             = "_id";
    static final String USER_UNIQUE_ID      = "unique_id";
    static final String USER_NAME           = "user_name";
    static final String USER_EMAIL          = "user_email";
    static final String USER_PASSWORD       = "user_password";
    static final String USER_PHONE          = "user_phone";
    static final String USER_DEVICE_MODEL   = "user_device_model";
    static final String USER_OS             = "user_os";
    static final String USER_TYPE           = "user_type";
    static final String USER_LOCATION       = "user_location";
    static final String USER_INTRO          = "user_intro";
    static final String USER_PACKAGE        = "user_package";
    static final String USER_IMAGE          = "user_image";
    static final String USER_EXPIRE_DATE    = "user_expire_date";
    static final String USER_REVIEW         = "user_review";
    static final String USER_FEEDBACK       = "user_feedback";
    static final String USER_SCHOOL         = "user_school";
    static final String USER_GRADE          = "user_grade";
    static final String USER_CLASS          = "user_class";
    static final String USER_ATTENDS          = "user_attends";


    // advertisement table
    static final String TABLE_NAME_ADVERTISEMENT    = "ADVERTISEMENT";

    static final String AD_ID                           = "_id";
    static final String ADVERTISEMENT_ID                = "advertisement_id";
    static final String ADVERTISEMENT_TITTLE            = "advertisement_tittle";
    static final String ADVERTISEMENT_CATEGORY_ID       = "advertisement_category_id";
    static final String ADVERTISEMENT_SUB_CATEGORY_ID   = "advertisement_sub_category_id";
    static final String ADVERTISEMENT_TYPE              = "advertisement_type";
    static final String ADVERTISEMENT_PRICE             = "advertisement_price";
    static final String ADVERTISEMENT_UNIT_QTY          = "advertisement_unit_qty";
    static final String ADVERTISEMENT_TOTAL_QTY         = "advertisement_total_qty";
    static final String ADVERTISEMENT_DESCRIPTION       = "advertisement_description";
    static final String ADVERTISEMENT_USER_ID           = "advertisement_user_id";
    static final String ADVERTISEMENT_USER_NAME         = "advertisement_user_name";
    static final String ADVERTISEMENT_USER_TYPE         = "advertisement_user_type";
    static final String ADVERTISEMENT_TARGET_PLACE      = "advertisement_target_place";
    static final String ADVERTISEMENT_USER_ADDRESS      = "advertisement_user_address";
    static final String ADVERTISEMENT_DATE              = "advertisement_date";
    static final String ADVERTISEMENT_REVIEW            = "advertisement_review";
    static final String ADVERTISEMENT_COMMENTS          = "advertisement_comments";
    static final String ADVERTISEMENT_TERMS_AGREED      = "advertisement_terms_agreed";
    static final String ADVERTISEMENT_IMAGE             = "advertisement_image";


    // rating table
    static final String TABLE_NAME_RATING               = "RATING";

    static final String R_ID                            = "r_id";
    static final String RATING_ID                       = "rating_id";
    static final String RATING_ADVERTISEMENT_ID         = "rating_advertisement_id";
    static final String RATING_USER_ID                  = "rating_userId";
    static final String RATING                          = "rating";
    static final String RATING_COMMENT                  = "rating_comment";


    // favourite table
    static final String TABLE_NAME_FAVOURITE               = "FAVOURITE";

    static final String F_ID                               = "f_id";
    static final String FAVOURITE_ID                       = "favourite_id";
    static final String FAVOURITE_ADVERTISEMENT_ID         = "favourite_advertisement_id";
    static final String FAVOURITE_USER_ID                  = "favourite_userId";


    //    favourite  table
    static final String CREATE_TABLE_FAVOURITE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_FAVOURITE
            + " ( " +
            F_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FAVOURITE_ID + " TEXT," +
            FAVOURITE_ADVERTISEMENT_ID + " TEXT," +
            FAVOURITE_USER_ID + " TEXT" +
            ");";

    //    rating  table
    static final String CREATE_TABLE_RATING = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_RATING
            + " ( " +
            R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            RATING_ID + " TEXT," +
            RATING_ADVERTISEMENT_ID + " TEXT," +
            RATING_USER_ID + " TEXT," +
            RATING + " TEXT," +
            RATING_COMMENT + " TEXT" +
            ");";

    //    district  table
    static final String CREATE_TABLE_DISTRICT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_DISTRICT
            + " ( " +
            DISTRICT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DISTRICT_NAME + " TEXT" +
            ");";

    //    category  table
    static final String CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_CATEGORY
            + " ( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CATEGORY_ID + " TEXT," +
            CATEGORY_NAME + " TEXT," +
            CATEGORY_IMAGE + " TEXT" +
            ");";

    static final String CREATE_TABLE_SUB_CATEGORY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_SUB_CATEGORY
            + " ( " +
            SUB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SUB_CATEGORY_ID + " TEXT," +
            SUB_CATEGORY_MAIN_CATEGORY_ID + " TEXT," +
            SUB_CATEGORY_NAME + " TEXT," +
            SUB_CATEGORY_IMAGE + " TEXT" +
            ");";

    static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USER
            + " ( " +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            USER_UNIQUE_ID + " TEXT," +
            USER_NAME + " TEXT," +
            USER_EMAIL + " TEXT," +
            USER_PASSWORD + " TEXT," +
            USER_PHONE + " TEXT," +
            USER_DEVICE_MODEL + " TEXT," +
            USER_OS + " TEXT," +
            USER_TYPE + " TEXT," +
            USER_LOCATION + " TEXT," +
            USER_INTRO + " TEXT," +
            USER_PACKAGE + " TEXT," +
            USER_IMAGE + " TEXT," +
            USER_EXPIRE_DATE + " TEXT," +
            USER_REVIEW + " TEXT," +
            USER_FEEDBACK + " TEXT," +
            USER_SCHOOL + " TEXT," +
            USER_GRADE + " TEXT," +
            USER_CLASS + " TEXT," +
            USER_ATTENDS + " TEXT" +
            ");";

    static final String CREATE_TABLE_ADVERTISEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ADVERTISEMENT
            + " ( " +
            AD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ADVERTISEMENT_ID + " TEXT," +
            ADVERTISEMENT_TITTLE + " TEXT," +
            ADVERTISEMENT_CATEGORY_ID + " TEXT," +
            ADVERTISEMENT_SUB_CATEGORY_ID + " TEXT," +
            ADVERTISEMENT_TYPE + " TEXT," +
            ADVERTISEMENT_PRICE + " TEXT," +
            ADVERTISEMENT_UNIT_QTY + " TEXT," +
            ADVERTISEMENT_TOTAL_QTY + " TEXT," +
            ADVERTISEMENT_DESCRIPTION + " TEXT," +
            ADVERTISEMENT_USER_ID + " TEXT," +
            ADVERTISEMENT_USER_NAME + " TEXT," +
            ADVERTISEMENT_USER_TYPE + " TEXT," +
            ADVERTISEMENT_TARGET_PLACE + " TEXT," +
            ADVERTISEMENT_USER_ADDRESS + " TEXT," +
            ADVERTISEMENT_DATE + " TEXT," +
            ADVERTISEMENT_REVIEW + " TEXT," +
            ADVERTISEMENT_TERMS_AGREED + " TEXT," +
            ADVERTISEMENT_IMAGE + " TEXT," +
            ADVERTISEMENT_COMMENTS + " TEXT" +
            ");";

}
