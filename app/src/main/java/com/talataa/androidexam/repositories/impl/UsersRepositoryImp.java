package com.talataa.androidexam.repositories.impl;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.talataa.androidexam.db.DbHelper;
import com.talataa.androidexam.model.Users;
import com.talataa.androidexam.repositories.UserRepositoryInterface;
import com.talataa.androidexam.utils.PasswordEncrypter;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryImp extends DbHelper implements UserRepositoryInterface {

    Context context;

    public UsersRepositoryImp(@Nullable Context context) {
        super(context);
        this.context = context;
    }




    @Override
    public int save(@NonNull Users user) {
        String sql = "INSERT INTO users(id, email, encrypted_password, first_name, last_name, identification, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PasswordEncrypter encryption = new PasswordEncrypter();

        int id ;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("email", user.getEmail());
            values.put("encrypted_password", encryption.encryptPassword(user.getEncrypted_password()));
            values.put("first_name", user.getFirst_name());
            values.put("last_name", user.getLast_name());
            values.put("identification", user.getIdentification());


            id = (int) db.insertOrThrow("users", null, values);
            return id;
        } catch (SQLiteConstraintException e) {
            Log.e(TAG, "Error inserting user: " + e.getMessage());
            return 0;
        }
    }

    @SuppressLint("Range")
    @Override
    public Users findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
            if (cursor != null && cursor.moveToFirst()) {
                Timestamp createdAt;
                Timestamp updatedAt;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    createdAt = new Timestamp(sdf.parse(cursor.getString(cursor.getColumnIndex("created_at"))).getTime());
                    updatedAt = new Timestamp(sdf.parse(cursor.getString(cursor.getColumnIndex("updated_at"))).getTime());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                @SuppressLint("Range") Users user = new Users(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("encrypted_password")),
                        cursor.getString(cursor.getColumnIndex("first_name")),
                        cursor.getString(cursor.getColumnIndex("last_name")),
                        cursor.getString(cursor.getColumnIndex("identification")),
                        createdAt,
                        updatedAt
                );
                cursor.close();
                return user;
            }
        } catch (SQLiteException e) {
            Log.e(TAG, "Error retrieving user: " + e.getMessage());
        }
        return null;
    }


    @SuppressLint("Range")
    @Override
    public List<Users> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            List<Users> userList = new ArrayList<>();
            while (cursor.moveToNext()) {
                Timestamp createdAt;
                Timestamp updatedAt;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    createdAt = new Timestamp(sdf.parse(cursor.getString(cursor.getColumnIndex("created_at"))).getTime());
                    updatedAt = new Timestamp(sdf.parse(cursor.getString(cursor.getColumnIndex("updated_at"))).getTime());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Users user = new Users(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("encrypted_password")),
                        cursor.getString(cursor.getColumnIndex("first_name")),
                        cursor.getString(cursor.getColumnIndex("last_name")),
                        cursor.getString(cursor.getColumnIndex("identification")),
                        createdAt,
                        updatedAt
                );
                userList.add(user);
            }
            cursor.close();
            return userList;
        } catch (SQLiteException e) {
            Log.e(TAG, "Error retrieving users: " + e.getMessage());
            return null;
        }
    }





    @Override
    public void update(int id, Users updatedUser) throws SQLException {
        String sql = "UPDATE users SET email = ?, encrypted_password = ?, first_name = ?, last_name = ?, identification = ?, updated_at = ? WHERE id = ?";
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Bogota"));
        @SuppressLint({"NewApi", "LocalSuppress"}) String formattedDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now);
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            try (Cursor cursor = db.rawQuery("SELECT * FROM users WHERE id = ?", new String[]{String.valueOf(id)})) {
                if (cursor.moveToFirst()) {
                    ContentValues values = new ContentValues();
                    values.put("email", updatedUser.getEmail());
                    values.put("encrypted_password", updatedUser.getEncrypted_password());
                    values.put("first_name", updatedUser.getFirst_name());
                    values.put("last_name", updatedUser.getLast_name());
                    values.put("identification", updatedUser.getIdentification());
                    values.put("updated_at", formattedDateTime);
                    db.update("users", values, "id = ?", new String[]{String.valueOf(id)});
                }
            }
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e(TAG, "Error updating user: " + e.getMessage());
        }
    }


    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL(sql, new Object[]{id});
        } catch (SQLiteException e) {
            Log.e(TAG, "Error deleting user: " + e.getMessage());
        }
    }


}
