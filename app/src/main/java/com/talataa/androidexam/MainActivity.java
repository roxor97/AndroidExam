package com.talataa.androidexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.talataa.androidexam.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button btnWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWelcome = findViewById(R.id.btnWelcome);

        btnWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null){
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected( MenuItem item){
        if (item.getItemId() == R.id.newMenu) {
            newRegister();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void newRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}