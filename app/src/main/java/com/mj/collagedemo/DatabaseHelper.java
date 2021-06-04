package com.mj.collagedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION = 1;
    private static final  String DATABASE_NAME = "UserManager.db";
    private static final  String TABLE_NAME = "user";

    private static final  String COULUMN_USER_ID = "user_id";
    private static final  String COULUMN_USER_NAME = "user_name";
    private static final  String COULUMN_USER_EMAIL = "user_email";
    private static final  String COULUMN_USER_PASSWORD = "user_password";
    private static final  String COULUMN_USER_PHONE= "user_phone";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COULUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COULUMN_USER_NAME + " TEXT,"
            + COULUMN_USER_EMAIL + " TEXT,"
            + COULUMN_USER_PASSWORD + " TEXT,"
            + COULUMN_USER_PHONE + " TEXT" +")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_USER_TABLE);

        onCreate(db);
    }

    public void addUser(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COULUMN_USER_NAME , user.getName());
        values.put(COULUMN_USER_EMAIL, user.getEmail());
        values.put(COULUMN_USER_PASSWORD, user.getPassword());
        values.put(COULUMN_USER_PHONE, user.getPhone());
        db.insert(TABLE_NAME, null,values);
        db.close();
    }

    //to check email is exist or not
    public boolean checkUser(String email){
        String[] colums ={
                COULUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COULUMN_USER_EMAIL +" = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_NAME,
                colums,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursocount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursocount > 0){
            return  true;
        }
        return false;
    }

    //Login check
    public boolean loginUser(String email, String password){

        String[] colums = {
                COULUMN_USER_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COULUMN_USER_EMAIL + " = ?" + " AND " + COULUMN_USER_PASSWORD
                + " = ?";
        String[] selectonArgs = {email,password};

        Cursor cursor = db.query(TABLE_NAME,
                colums,
                selection,
                selectonArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount> 0){
            return  true;
        }
        return false;
    }
}
