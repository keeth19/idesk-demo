package com.example.ideskdemo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import com.example.ideskdemo.object.AdvertisementRating;
import com.example.ideskdemo.object.Advertisment;
import com.example.ideskdemo.object.Category;
import com.example.ideskdemo.object.District;
import com.example.ideskdemo.object.FavouriteAdvertisement;
import com.example.ideskdemo.object.SubCategory;
import com.example.ideskdemo.object.User;

import java.util.ArrayList;
import java.util.Objects;

public class DBUtils {

    private static db_manager db_manager;
    private static final String TAG = "DBUtils";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void insert_district_to_table(District district, Context context) {
        boolean isSuccess = false;
        db_manager = new db_manager(context);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.DISTRICT_NAME, district.getDistrictName());

            try {
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_DISTRICT, null, cv);
                if (rowCount != -1) {
                    Log.d(TAG, "District table " + "District inserted");
                    isSuccess = true;
                } else {
                    Log.e(TAG, "District table " + "District insert failed");
                    isSuccess = false;
                }
            } catch (Exception e) {
                try {
                    long rowCount = db.replace(db_tables.TABLE_NAME_DISTRICT, null, cv);
                    if (rowCount != -1) {
                        Log.d(TAG, "District table " + "District replaced");
                        isSuccess = true;
                    } else {
                        Log.e(TAG, "District table " + "District replaced failed");
                        isSuccess = false;
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "insert_district_to_table " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void insert_category_to_table(Category category, Context context) {
        boolean isSuccess = false;
        db_manager = new db_manager(context);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.CATEGORY_ID, category.getCategoryID());
            cv.put(db_tables.CATEGORY_NAME, category.getCategoryName());
            cv.put(db_tables.CATEGORY_IMAGE, category.getCategoryImage());

            try {
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_CATEGORY, null, cv);
                if (rowCount != -1) {
                    Log.d(TAG, "Category table " + "Category inserted");
                    isSuccess = true;
                } else {
                    Log.e(TAG, "Category table " + "category insert failed");
                    isSuccess = false;
                }
            } catch (Exception e) {
                try {
                    long rowCount = db.replace(db_tables.TABLE_NAME_CATEGORY, null, cv);
                    if (rowCount != -1) {
                        Log.d(TAG, "Category table " + "Category replaced");
                        isSuccess = true;
                    } else {
                        Log.e(TAG, "Category table " + "category replaced failed");
                        isSuccess = false;
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void insert_sub_category_to_table(SubCategory subCategory, Context context) {
        boolean isSuccess = false;
        db_manager = new db_manager(context);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.SUB_CATEGORY_ID, subCategory.getSubCategoryID());
            cv.put(db_tables.SUB_CATEGORY_MAIN_CATEGORY_ID, subCategory.getCategoryID());
            cv.put(db_tables.SUB_CATEGORY_NAME, subCategory.getSubCategoryName());
            cv.put(db_tables.SUB_CATEGORY_IMAGE, subCategory.getCategoryImage());

            try {
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_SUB_CATEGORY, null, cv);
                if (rowCount != -1) {
                    Log.d(TAG, "SubCategory table " + "CategoryID " + subCategory.getSubCategoryID() + " inserted");
                    Log.d(TAG, "SubCategory table " + "CategorySUBID " + subCategory.getCategoryID() + " inserted");
                    Log.d(TAG, "SubCategory table " + "CategorySUBNAME " + subCategory.getSubCategoryName() + " inserted");
                    isSuccess = true;
                } else {
                    Log.e(TAG, "SubCategory table " + "CategoryID " + subCategory.getCategoryID() + " insert failed");
                    isSuccess = false;
                }
            } catch (Exception e) {
                try {
                    long rowCount = db.replace(db_tables.TABLE_NAME_SUB_CATEGORY, null, cv);
                    if (rowCount != -1) {
                        Log.d(TAG, "SubCategory table " + "CategoryID " + subCategory.getCategoryID() + "replaced");
                        isSuccess = true;
                    } else {
                        Log.e(TAG, "SubCategory table " + "CategoryID " + subCategory.getCategoryID() + " replaced failed");
                        isSuccess = false;
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<District> getAllDistrict(Context context) {
        ArrayList<District> districtArrayList = new ArrayList<>();
        db_manager = new db_manager(context);
        Cursor c;

        StringBuilder query;
        query = new StringBuilder("SELECT " + db_tables.DISTRICT_NAME + " from " + db_tables.TABLE_NAME_DISTRICT
                + " ORDER BY " + db_tables.DISTRICT_NAME + " ASC");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    District district = new District();

                    district.setDistrictName(c.getString(c.getColumnIndex(db_tables.DISTRICT_NAME)));

                    districtArrayList.add(district);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAllDistrict " + e.getMessage());
        }
        return districtArrayList;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<Category> getAllCategory(Context context) {
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        db_manager = new db_manager(context);
        Cursor c;

        StringBuilder query;
        query = new StringBuilder("SELECT * from " + db_tables.TABLE_NAME_CATEGORY
                + " ORDER BY " + db_tables.CATEGORY_NAME + " ASC");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    Category category = new Category();

                    category.setCategoryID(c.getString(c.getColumnIndex(db_tables.CATEGORY_ID)));
                    category.setCategoryName(c.getString(c.getColumnIndex(db_tables.CATEGORY_NAME)));

                    categoryArrayList.add(category);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAllCategory " + e.getMessage());
        }
        return categoryArrayList;
    }


    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<SubCategory> getAllSubCategoryByCategoryID(Context mContext, String categoryID) {
        db_manager = new db_manager(mContext);
        ArrayList<SubCategory> subCategoryArrayList = new ArrayList<>();

        Cursor c;

        StringBuilder query /*= new StringBuilder("SELECT * from " + db_tables.TABLE_NAME_SUB_CATEGORY
                + " where " + db_tables.SUB_CATEGORY_MAIN_CATEGORY_ID + "=" + categoryID + ""
        )*/;

        query = new StringBuilder("SELECT " +
                db_tables.SUB_CATEGORY_NAME + ", " +
                db_tables.SUB_CATEGORY_MAIN_CATEGORY_ID + ", " +
                db_tables.SUB_CATEGORY_ID +
                " FROM " +
                db_tables.TABLE_NAME_SUB_CATEGORY +
                " WHERE " +
                db_tables.SUB_CATEGORY_MAIN_CATEGORY_ID + " = " + "\"" + categoryID + "\"");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    SubCategory subCategory = new SubCategory();
                    subCategory.setCategoryID(c.getString(c.getColumnIndex(db_tables.SUB_CATEGORY_MAIN_CATEGORY_ID)));
                    subCategory.setSubCategoryID(c.getString(c.getColumnIndex(db_tables.SUB_CATEGORY_ID)));
                    subCategory.setSubCategoryName(c.getString(c.getColumnIndex(db_tables.SUB_CATEGORY_NAME)));

                    subCategoryArrayList.add(subCategory);
                } while (c.moveToNext());
            }

            c.close();
        } catch (Exception e) {
            Log.e("getAllStorePropertyByClintId", Objects.requireNonNull(e.getMessage()));
        }
        return subCategoryArrayList;
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getCategoryIdByCategoryName(Context mContext, String categoryName) {
        db_manager = new db_manager(mContext);
        String category = null;

        Cursor c;

        StringBuilder query = new StringBuilder("SELECT " +
                db_tables.CATEGORY_ID +
                " FROM " +
                db_tables.TABLE_NAME_CATEGORY +
                " WHERE " +
                db_tables.CATEGORY_NAME + " = " + categoryName);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    category = c.getString(c.getColumnIndex(db_tables.CATEGORY_ID));

                } while (c.moveToNext());
            }

            c.close();
        } catch (Exception e) {
            Log.e("getCategoryNAmeByCategoryID", Objects.requireNonNull(e.getMessage()));
        }
        return category;
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getsubCategoryIDBysubCategoryName(Context mContext, String subCategoryName) {
        db_manager = new db_manager(mContext);
        String subCategoryId = null;

        Cursor c;

        StringBuilder query = new StringBuilder("SELECT " +
                db_tables.SUB_CATEGORY_ID +
                " FROM " +
                db_tables.TABLE_NAME_SUB_CATEGORY +
                " WHERE " +
                db_tables.SUB_CATEGORY_NAME + " = "  + subCategoryName );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    subCategoryId = c.getString(c.getColumnIndex(db_tables.SUB_CATEGORY_ID));

                } while (c.moveToNext());
            }

            c.close();
        } catch (Exception e) {
            Log.e("getsubCategoryIDBysubCategoryName", Objects.requireNonNull(e.getMessage()));
        }
        return subCategoryId;
    }


    // delete district from table
    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void deleteAllDistrict(Context mContext) {

        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            long rowCount = db.delete(db_tables.TABLE_NAME_DISTRICT, null, null);
            if (rowCount != -1) {
                Log.e("district", "deleted.");
            } else {
                Log.i("district", "clear");
            }

        } catch (Exception e) {
            Log.e("delete all from district", Objects.requireNonNull(e.getMessage()));
        }
    }

    // delete category from table
    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void deleteAllCategory(Context mContext) {

        db_manager = new db_manager(mContext);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            long rowCount = db.delete(db_tables.TABLE_NAME_CATEGORY, null, null);
            if (rowCount != -1) {
                Log.e("category", "deleted.");
            } else {
                Log.i("category", "clear");
            }

        } catch (Exception e) {
            Log.e("delete all from category", Objects.requireNonNull(e.getMessage()));
        }
    }

    // delete SubCategory from table
    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void deleteAllSubCategory(Context mContext) {

        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            long rowCount = db.delete(db_tables.TABLE_NAME_SUB_CATEGORY, null, null);
            if (rowCount != -1) {
                Log.e("SubCategory", "deleted.");
            } else {
                Log.i("SubCategory", "clear");
            }

        } catch (Exception e) {
            Log.e("delete all from SubCategory", Objects.requireNonNull(e.getMessage()));
        }
    }


    //2020.07.08
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean insert_RegisterUser_to_table(User regUser, Context context) {
        boolean isSuccess = false;
        db_manager = new db_manager(context);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.USER_UNIQUE_ID, regUser.getUsersID());
            cv.put(db_tables.USER_NAME, regUser.getName());
            cv.put(db_tables.USER_EMAIL, regUser.getEmail());
            cv.put(db_tables.USER_PASSWORD, regUser.getPassword());
            cv.put(db_tables.USER_PHONE, regUser.getPhone());
            cv.put(db_tables.USER_DEVICE_MODEL, regUser.getDeviceModel());
            cv.put(db_tables.USER_OS, regUser.getOs());
            cv.put(db_tables.USER_TYPE, regUser.getType());
            cv.put(db_tables.USER_LOCATION, regUser.getLocation());
            cv.put(db_tables.USER_PACKAGE, regUser.getPackage1());
            cv.put(db_tables.USER_IMAGE, regUser.getImage());
            cv.put(db_tables.USER_EXPIRE_DATE, regUser.getEXDate());
            cv.put(db_tables.USER_REVIEW, regUser.getReview());
            cv.put(db_tables.USER_FEEDBACK, regUser.getFeedback());
            cv.put(db_tables.USER_SCHOOL, regUser.getSchoolName());
            cv.put(db_tables.USER_GRADE, regUser.getGradeName());
            cv.put(db_tables.USER_CLASS, regUser.getClassName());
            cv.put(db_tables.USER_ATTENDS, regUser.getAttendantStatus());

            try {
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_USER, null, cv);
                if (rowCount != -1) {
                    Log.d(TAG, "Registered User table " + "Registered User inserted");
                    isSuccess = true;
                } else {
                    Log.e(TAG, "Registered User table " + "registered User failed");
                    isSuccess = false;
                }
            } catch (Exception e) {
                try {
                    long rowCount = db.replace(db_tables.TABLE_NAME_USER, null, cv);
                    if (rowCount != -1) {
                        Log.d(TAG, "Registered User table " + "Registered User replaced");
                        isSuccess = true;
                    } else {
                        Log.e(TAG, "Registered User table " + "registered User replaced failed");
                        isSuccess = false;
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<User> getAllUsers(Context context) {
        ArrayList<User> usersArrayList = new ArrayList<>();
        db_manager = new db_manager(context);
        Cursor c;

        StringBuilder query;
        query = new StringBuilder("SELECT * from " + db_tables.TABLE_NAME_USER
                + " ORDER BY " + db_tables.USER_NAME + " ASC");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    User user = new User();

                    user.setUsersID(c.getString(c.getColumnIndex(db_tables.USER_UNIQUE_ID)));
                    user.setName(c.getString(c.getColumnIndex(db_tables.USER_NAME)));
                    user.setEmail(c.getString(c.getColumnIndex(db_tables.USER_EMAIL)));
                    user.setPhone(c.getString(c.getColumnIndex(db_tables.USER_PHONE)));
                    user.setPassword(c.getString(c.getColumnIndex(db_tables.USER_PASSWORD)));
                    user.setType(c.getString(c.getColumnIndex(db_tables.USER_TYPE)));
                    user.setSchoolName(c.getString(c.getColumnIndex(db_tables.USER_SCHOOL)));
                    user.setGradeName(c.getString(c.getColumnIndex(db_tables.USER_GRADE)));
                    user.setClassName(c.getString(c.getColumnIndex(db_tables.USER_CLASS)));

                    usersArrayList.add(user);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAllUsers " + e.getMessage());
        }
        return usersArrayList;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<User> getAllUsersByStatus(Context context, String status) {
        ArrayList<User> usersArrayList = new ArrayList<>();
        db_manager = new db_manager(context);
        Cursor c;

        StringBuilder query;
        query = new StringBuilder("SELECT " +
                db_tables.USER_NAME + ", " +
                db_tables.USER_UNIQUE_ID + ", " +
                " FROM " +
                db_tables.TABLE_NAME_USER +
                " WHERE " +
                db_tables.USER_TYPE + " = "  + status );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    User user = new User();

                    user.setUsersID(c.getString(c.getColumnIndex(db_tables.USER_ID)));
                    user.setName(c.getString(c.getColumnIndex(db_tables.USER_NAME)));

                    usersArrayList.add(user);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAllUsers " + e.getMessage());
        }
        return usersArrayList;
    }

    //update user
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean insert_UpdateUser_to_table(User updateUser, Context context) {
        boolean isSuccess = false;
        db_manager = new db_manager(context);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.USER_NAME, updateUser.getName());
            cv.put(db_tables.USER_EMAIL, updateUser.getEmail());
            cv.put(db_tables.USER_PHONE, updateUser.getPhone());
            cv.put(db_tables.USER_TYPE, updateUser.getProType());
            cv.put(db_tables.USER_LOCATION, updateUser.getLocation());
            cv.put(db_tables.USER_INTRO, updateUser.getProIntro());
            cv.put(db_tables.USER_DEVICE_MODEL, updateUser.getDeviceModel());
            cv.put(db_tables.USER_OS, updateUser.getOs());
            cv.put(db_tables.USER_IMAGE, updateUser.getImage());


            try {
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_USER, null, cv);
                if (rowCount != -1) {
                    Log.d(TAG, "Registered User table " + "Registered User inserted");
                    isSuccess = true;
                } else {
                    Log.e(TAG, "Registered User table " + "registered User failed");
                    isSuccess = false;
                }
            } catch (Exception e) {
                try {
                    long rowCount = db.replace(db_tables.TABLE_NAME_USER, null, cv);
                    if (rowCount != -1) {
                        Log.d(TAG, "Registered User table " + "Registered User replaced");
                        isSuccess = true;
                    } else {
                        Log.e(TAG, "Registered User table " + "registered User replaced failed");
                        isSuccess = false;
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }


    static String nextId;
    static String nextIdSub;
    static String lastId;
    static String lastIdSub;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String genarateCatId() {

        Cursor c;

        StringBuilder query;
        query = new StringBuilder("SELECT * from " + db_tables.TABLE_NAME_CATEGORY);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {

                do {
                    lastId = c.getString(1);
                } while (c.moveToNext());

                Log.d("addCat_activity", "genarateCatId: " + lastId);

            } else {
                Log.d("addCat_activity", "genarateCatId: else");
            }

            c.close();
            String prefix = "CA";
            if (lastId == null) {
                nextId = prefix + "001";
                Log.d("addCat_activity", "genarateCatId: last id null");
            } else {
                int number = Integer.parseInt(lastId.substring(3));
                Log.d("addCat_activity", "genarateCatId: " + number);
                if (number >= 1 && number < 9) {
                    number = number + 1;
                    nextId = prefix + "00" + number;
                } else if (number >= 9 && number < 99) {
                    number = number + 1;
                    nextId = prefix + "0" + number;
                } else if (number >= 99) {
                    number = number + 1;
                    nextId = prefix + number;
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "addCat_activity " + e.getMessage());
        }
        Log.d("addCat_activity", "genarateItemId: " + nextId);

        return nextId;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String genarateSubCatId(String s) {

        Cursor c;

        StringBuilder query;
        query = new StringBuilder("SELECT * from " + db_tables.TABLE_NAME_SUB_CATEGORY);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {

                do {
                    lastIdSub = c.getString(1);
                } while (c.moveToNext());

                Log.d("addCat_activity", "genarateCatId: " + lastIdSub);

            } else {
                Log.d("addCat_activity", "genarateCatId: else");
            }

            c.close();
            String prefix = s;
            if (lastIdSub == null) {
                nextIdSub = prefix + "001";
                Log.d("addCat_activity", "genarateCatId: last id null");
            } else {
                int number = Integer.parseInt(lastIdSub.substring(3));
                Log.d("addCat_activity", "genarateCatId: " + number);
                if (number >= 1 && number < 9) {
                    number = number + 1;
                    nextIdSub = prefix + "00" + number;
                } else if (number >= 9 && number < 99) {
                    number = number + 1;
                    nextIdSub = prefix + "0" + number;
                } else if (number >= 99) {
                    number = number + 1;
                    nextIdSub = prefix + number;
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "addCat_activity " + e.getMessage());
        }
        Log.d("addCat_activity", "genarateItemId: " + nextIdSub);

        return nextIdSub;
    }

    //get all advertisement
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<Advertisment> getAllAdvertisement(Context context) {
        ArrayList<Advertisment> advertisementArrayList = new ArrayList<>();
        db_manager = new db_manager(context);
        Cursor c;

        StringBuilder query;
        query = new StringBuilder("SELECT * from " + db_tables.TABLE_NAME_ADVERTISEMENT
                /*  + " ORDER BY " + db_tables.USER_NAME + " ASC"*/);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    Advertisment advertisment = new Advertisment();

                    advertisment.setAdvertisementId(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_ID)));
                    advertisment.setAdTopic(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TITTLE)));
                    advertisment.setCategory(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_CATEGORY_ID)));
                    advertisment.setSubCategory(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_SUB_CATEGORY_ID)));
                    advertisment.setAdType(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TYPE)));
                    advertisment.setPrice(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_PRICE)));
                    advertisment.setPerQuantity(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_UNIT_QTY)));
                    advertisment.setTotalQuantity(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TOTAL_QTY)));
                    advertisment.setDescription(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_DESCRIPTION)));
                    advertisment.setUserID(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_ID)));
                    advertisment.setUsername(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_NAME)));
                    advertisment.setUserType(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_TYPE)));
                    advertisment.setLocation(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TARGET_PLACE)));
                    advertisment.setAddress(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_ADDRESS)));
                    advertisment.setImage(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_IMAGE)));
                    advertisment.setTermsAgreed(Boolean.parseBoolean(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TERMS_AGREED))));

                    advertisementArrayList.add(advertisment);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAllAdvertisement " + e.getMessage());
        }
        return advertisementArrayList;
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<Advertisment> getAdvertisementByUserId(Context mContext, String userId) {
        db_manager = new db_manager(mContext);
        ArrayList<Advertisment> advertisementArrayList = new ArrayList<>();

        Cursor c;

        StringBuilder query = new StringBuilder("SELECT * FROM " +
                db_tables.TABLE_NAME_ADVERTISEMENT +
                " WHERE " +
                db_tables.ADVERTISEMENT_USER_ID + " = " + "\"" + userId + "\"");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    Advertisment advertisment = new Advertisment();

                    advertisment.setAdvertisementId(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_ID)));
                    advertisment.setAdTopic(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TITTLE)));
                    advertisment.setCategory(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_CATEGORY_ID)));
                    advertisment.setSubCategory(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_SUB_CATEGORY_ID)));
                    advertisment.setAdType(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TYPE)));
                    advertisment.setPrice(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_PRICE)));
                    advertisment.setPerQuantity(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_UNIT_QTY)));
                    advertisment.setTotalQuantity(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TOTAL_QTY)));
                    advertisment.setDescription(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_DESCRIPTION)));
                    advertisment.setUserID(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_ID)));
                    advertisment.setUsername(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_NAME)));
                    advertisment.setUserType(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_TYPE)));
                    advertisment.setLocation(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TARGET_PLACE)));
                    advertisment.setAddress(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_ADDRESS)));
                    advertisment.setImage(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_IMAGE)));
                    advertisment.setTermsAgreed(Boolean.parseBoolean(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TERMS_AGREED))));

                    advertisementArrayList.add(advertisment);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            Log.e("getAdvertisementByUserId", Objects.requireNonNull(e.getMessage()));
        }
        return advertisementArrayList;
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Advertisment getAdvertisementByAdvertisementId(Context mContext, String advertisementId) {
        db_manager = new db_manager(mContext);
        Advertisment advertisment = new Advertisment();

        Cursor c;

        StringBuilder query = new StringBuilder("SELECT * FROM " +
                db_tables.TABLE_NAME_ADVERTISEMENT +
                " WHERE " +
                db_tables.ADVERTISEMENT_ID + " = " + "\"" + advertisementId + "\"");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {

                    advertisment.setAdvertisementId(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_ID)));
                    advertisment.setAdTopic(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TITTLE)));
                    advertisment.setCategory(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_CATEGORY_ID)));
                    advertisment.setSubCategory(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_SUB_CATEGORY_ID)));
                    advertisment.setAdType(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TYPE)));
                    advertisment.setPrice(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_PRICE)));
                    advertisment.setPerQuantity(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_UNIT_QTY)));
                    advertisment.setTotalQuantity(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TOTAL_QTY)));
                    advertisment.setDescription(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_DESCRIPTION)));
                    advertisment.setUserID(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_ID)));
                    advertisment.setUsername(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_NAME)));
                    advertisment.setUserType(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_TYPE)));
                    advertisment.setLocation(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TARGET_PLACE)));
                    advertisment.setAddress(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_USER_ADDRESS)));
                    advertisment.setImage(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_IMAGE)));
                    advertisment.setTermsAgreed(Boolean.parseBoolean(c.getString(c.getColumnIndex(db_tables.ADVERTISEMENT_TERMS_AGREED))));


                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            Log.e("getAdvertisementByUserId", Objects.requireNonNull(e.getMessage()));
        }
        return advertisment;
    }


    //insert advertisement
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean insert_advertisement_table(Advertisment advertisment, Context context) {
        boolean isSuccess = false;
        db_manager = new db_manager(context);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.ADVERTISEMENT_ID, advertisment.getAdvertisementId());
            cv.put(db_tables.ADVERTISEMENT_TITTLE, advertisment.getAdTopic());
            cv.put(db_tables.ADVERTISEMENT_CATEGORY_ID, advertisment.getCategory());
            cv.put(db_tables.ADVERTISEMENT_SUB_CATEGORY_ID, advertisment.getSubCategory());
            cv.put(db_tables.ADVERTISEMENT_TYPE, advertisment.getAdType());
            cv.put(db_tables.ADVERTISEMENT_PRICE, advertisment.getPrice());
            cv.put(db_tables.ADVERTISEMENT_UNIT_QTY, advertisment.getPerQuantity());
            cv.put(db_tables.ADVERTISEMENT_TOTAL_QTY, advertisment.getTotalQuantity());
            cv.put(db_tables.ADVERTISEMENT_DESCRIPTION, advertisment.getDescription());
            cv.put(db_tables.ADVERTISEMENT_USER_ID, advertisment.getUserID());
            cv.put(db_tables.ADVERTISEMENT_USER_NAME, advertisment.getUsername());
            cv.put(db_tables.ADVERTISEMENT_TARGET_PLACE, advertisment.getLocation());
            cv.put(db_tables.ADVERTISEMENT_USER_ADDRESS, advertisment.getAddress());
            cv.put(db_tables.ADVERTISEMENT_IMAGE, advertisment.getImage());
            cv.put(db_tables.ADVERTISEMENT_TERMS_AGREED, advertisment.isTermsAgreed());


            try {
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_ADVERTISEMENT, null, cv);
                if (rowCount != -1) {
                    Log.d(TAG, "advertisement table " + " inserted");

                    isSuccess = true;
                } else {
                    Log.e(TAG, "advertisement table " + " insert failed");
                    isSuccess = false;
                }
            } catch (Exception e) {
                try {
                    long rowCount = db.replace(db_tables.TABLE_NAME_ADVERTISEMENT, null, cv);
                    if (rowCount != -1) {
                        Log.d(TAG, "advertisement table " + "replaced");
                        isSuccess = true;
                    } else {
                        Log.e(TAG, "advertisement table " + " replaced failed");
                        isSuccess = false;
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // delete advertisement from table
    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void deleteAllAdvertisement(Context mContext) {

        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            long rowCount = db.delete(db_tables.TABLE_NAME_ADVERTISEMENT, null, null);
            if (rowCount != -1) {
                Log.e("advertisement", "deleted.");
            } else {
                Log.i("advertisement", "clear");
            }

        } catch (Exception e) {
            Log.e("delete all from advertisement", Objects.requireNonNull(e.getMessage()));
        }
    }


    //insert advertisement
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean insert_raring_table(AdvertisementRating advertisementRating, Context context) {
        boolean isSuccess = false;
        db_manager = new db_manager(context);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.RATING_ID, advertisementRating.getRatingId());
            cv.put(db_tables.RATING_ADVERTISEMENT_ID, advertisementRating.getAdvertisementId());
            cv.put(db_tables.RATING_USER_ID, advertisementRating.getUserId());
            cv.put(db_tables.RATING, advertisementRating.getRating());
            cv.put(db_tables.RATING_COMMENT, advertisementRating.getComment());


            try {
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_RATING, null, cv);
                if (rowCount != -1) {
                    Log.d(TAG, "advertisementRating table " + " inserted");

                    isSuccess = true;
                } else {
                    Log.e(TAG, "advertisementRating table " + " insert failed");
                    isSuccess = false;
                }
            } catch (Exception e) {
                try {
                    long rowCount = db.replace(db_tables.TABLE_NAME_RATING, null, cv);
                    if (rowCount != -1) {
                        Log.d(TAG, "advertisementRating table " + "replaced");
                        isSuccess = true;
                    } else {
                        Log.e(TAG, "advertisementRating table " + " replaced failed");
                        isSuccess = false;
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }


    // delete advertisement rating from table
    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void deleteAdvertisementRatings(Context mContext) {

        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            long rowCount = db.delete(db_tables.TABLE_NAME_RATING, null, null);
            if (rowCount != -1) {
                Log.e("advertisementRating", "deleted.");
            } else {
                Log.i("advertisementRating", "clear");
            }

        } catch (Exception e) {
            Log.e("delete all from advertisementRating", Objects.requireNonNull(e.getMessage()));
        }
    }

    // delete advertisement rating from table
    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void deleteUser(Context mContext) {

        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            long rowCount = db.delete(db_tables.TABLE_NAME_USER, null, null);
            if (rowCount != -1) {
                Log.e("deleteUser", "deleted.");
            } else {
                Log.i("deleteUser", "clear");
            }

        } catch (Exception e) {
            Log.e("delete all from user", Objects.requireNonNull(e.getMessage()));
        }
    }


    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getUserNameByUserId(Context mContext, String userId) {
        db_manager = new db_manager(mContext);
        String category = null;

        Cursor c;

        StringBuilder query = new StringBuilder("SELECT " +
                db_tables.USER_NAME +
                " FROM " +
                db_tables.TABLE_NAME_USER +
                " WHERE " +
                db_tables.USER_UNIQUE_ID + " = " + "\"" + userId + "\"");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    category = c.getString(c.getColumnIndex(db_tables.USER_NAME));

                } while (c.moveToNext());
            }

            c.close();
        } catch (Exception e) {
            Log.e("getCategoryNAmeByCategoryID", Objects.requireNonNull(e.getMessage()));
        }
        return category;
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getUserGradeUserId(Context mContext, String userId) {
        db_manager = new db_manager(mContext);
        String category = null;

        Cursor c;

        StringBuilder query = new StringBuilder("SELECT " +
                db_tables.USER_GRADE +
                " FROM " +
                db_tables.TABLE_NAME_USER +
                " WHERE " +
                db_tables.USER_UNIQUE_ID + " = " + "\"" + userId + "\"");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    category = c.getString(c.getColumnIndex(db_tables.USER_GRADE));

                } while (c.moveToNext());
            }

            c.close();
        } catch (Exception e) {
            Log.e("getCategoryNAmeByCategoryID", Objects.requireNonNull(e.getMessage()));
        }
        return category;
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getUserStatusUserId(Context mContext, String userId) {
        db_manager = new db_manager(mContext);
        String category = null;

        Cursor c;

        StringBuilder query = new StringBuilder("SELECT " +
                db_tables.USER_TYPE +
                " FROM " +
                db_tables.TABLE_NAME_USER +
                " WHERE " +
                db_tables.USER_UNIQUE_ID + " = " + "\"" + userId + "\"");

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    category = c.getString(c.getColumnIndex(db_tables.USER_TYPE));

                } while (c.moveToNext());
            }

            c.close();
        } catch (Exception e) {
            Log.e("getCategoryNAmeByCategoryID", Objects.requireNonNull(e.getMessage()));
        }
        return category;
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<AdvertisementRating> getRatingByLimit(Context mContext) {
        db_manager = new db_manager(mContext);
        ArrayList<AdvertisementRating> advertisementArrayList = new ArrayList<>();

        Cursor c;

        StringBuilder query = new StringBuilder("SELECT * FROM " +
                db_tables.TABLE_NAME_RATING +
                " ORDER BY " +
                db_tables.RATING +
                " DESC " +
                " LIMIT " + '3');


        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    AdvertisementRating advertisementRating = new AdvertisementRating();

                    advertisementRating.setRatingId(c.getString(c.getColumnIndex(db_tables.RATING_ID)));
                    advertisementRating.setAdvertisementId(c.getString(c.getColumnIndex(db_tables.RATING_ADVERTISEMENT_ID)));
                    advertisementRating.setUserId(c.getString(c.getColumnIndex(db_tables.RATING_USER_ID)));
                    advertisementRating.setRating(Float.parseFloat(c.getString(c.getColumnIndex(db_tables.RATING))));
                    advertisementRating.setComment(c.getString(c.getColumnIndex(db_tables.RATING_COMMENT)));

                    advertisementArrayList.add(advertisementRating);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            Log.e("getAdvertisementByUserId", Objects.requireNonNull(e.getMessage()));
        }
        return advertisementArrayList;
    }

    //insert favourite advertisement
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean insert_favourite_table(FavouriteAdvertisement favouriteAdvertisement, Context context) {
        boolean isSuccess = false;
        db_manager = new db_manager(context);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(db_tables.FAVOURITE_ID, favouriteAdvertisement.getFavoriteId());
            cv.put(db_tables.FAVOURITE_ADVERTISEMENT_ID, favouriteAdvertisement.getAdvertisementID());
            cv.put(db_tables.FAVOURITE_USER_ID, favouriteAdvertisement.getUserId());

            try {
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_FAVOURITE, null, cv);
                if (rowCount != -1) {
                    Log.d(TAG, "advertisementFavourite table " + " inserted");

                    isSuccess = true;
                } else {
                    Log.e(TAG, "advertisementFavourite table " + " insert failed");
                    isSuccess = false;
                }
            } catch (Exception e) {
                try {
                    long rowCount = db.replace(db_tables.TABLE_NAME_FAVOURITE, null, cv);
                    if (rowCount != -1) {
                        Log.d(TAG, "advertisementFavourite table " + "replaced");
                        isSuccess = true;
                    } else {
                        Log.e(TAG, "advertisementFavourite table " + " replaced failed");
                        isSuccess = false;
                    }
                } catch (Exception ex) {
                    Log.e(TAG, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }


    // delete advertisement from table
    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void deleteAllFavouriteAds(Context mContext) {

        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            long rowCount = db.delete(db_tables.TABLE_NAME_FAVOURITE, null, null);
            if (rowCount != -1) {
                Log.e("Favourite advertisement", "deleted.");
            } else {
                Log.i("Favourite advertisement", "clear");
            }

        } catch (Exception e) {
            Log.e("delete all from Favourite advertisement", Objects.requireNonNull(e.getMessage()));
        }
    }


    //get all advertisement
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<FavouriteAdvertisement> getAllFavouriteAdvertisement(Context context) {
        ArrayList<FavouriteAdvertisement> FavouriteAdvertisementArrayList = new ArrayList<>();
        db_manager = new db_manager(context);
        Cursor c;

        StringBuilder query;
        query = new StringBuilder("SELECT * from " + db_tables.TABLE_NAME_FAVOURITE);

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);

            if (c.moveToFirst()) {
                do {
                    FavouriteAdvertisement favouriteAdvertisement = new FavouriteAdvertisement();

                    favouriteAdvertisement.setFavoriteId(c.getString(c.getColumnIndex(db_tables.FAVOURITE_ID)));
                    favouriteAdvertisement.setAdvertisementID(c.getString(c.getColumnIndex(db_tables.FAVOURITE_ADVERTISEMENT_ID)));
                    favouriteAdvertisement.setUserId(c.getString(c.getColumnIndex(db_tables.FAVOURITE_USER_ID)));

                    FavouriteAdvertisementArrayList.add(favouriteAdvertisement);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAllFavouriteAdvertisement" + e.getMessage());
        }
        return FavouriteAdvertisementArrayList;
    }

}