package com.talataa.androidexam.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "app.db";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ASTEROIDS = "asteroids";


    public DbHelper(@Nullable Context context){
        super(context,DATABASE_NAME,null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email VARCHAR NOT NULL," +
                "encrypted_password VARCHAR," +
                "first_name VARCHAR," +
                "last_name VARCHAR," +
                "identification VARCHAR," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");"
        );


        db.execSQL("CREATE TABLE IF NOT EXISTS asteroids (" +
                "id INTEGER NOT NULL," +
                "user_id INTEGER NOT NULL," +
                "name VARCHAR NOT NULL," +
                "estimated_diameter_min FLOAT NOT NULL," +
                "estimated_diameter_max FLOAT NOT NULL," +
                "is_potentially_hazardous_asteroid BIT NOT NULL," +
                "absolute_magnitude DOUBLE NOT NULL," +
                "FOREIGN KEY(user_id) REFERENCES users(id)" +
                ");"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS asteroids");

        onCreate(db);
    }
}
