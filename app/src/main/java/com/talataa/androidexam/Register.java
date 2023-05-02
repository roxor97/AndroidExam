package com.talataa.androidexam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.talataa.androidexam.model.Asteroids;
import com.talataa.androidexam.model.Users;
import com.talataa.androidexam.repositories.impl.AsteroidRepositoryImp;
import com.talataa.androidexam.repositories.impl.UsersRepositoryImp;
import com.talataa.androidexam.services.impl.AsteroidServiceImp;
import com.talataa.androidexam.services.impl.UserServiceImp;

import java.sql.SQLException;
import java.util.List;

public class Register extends AppCompatActivity {

    EditText txtName, txtEmail, txtLastName,txtPassword,txtIdentification;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtLastName = findViewById(R.id.txtLastName);
        txtPassword = findViewById(R.id.txtPassword);
        txtIdentification = findViewById(R.id.txtIdentification);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int response;
                try {
                    UserServiceImp userServiceImp = new UserServiceImp(new UsersRepositoryImp(Register.this));


                    Users user = new Users();
                    user.setEmail(txtEmail.getText().toString());
                    user.setEncrypted_password(txtPassword.getText().toString());
                    user.setLast_name(txtLastName.getText().toString());
                    user.setFirst_name(txtName.getText().toString());
                    user.setIdentification(txtIdentification.getText().toString());

                    response = userServiceImp.save(user);
                    System.out.println(userServiceImp.findById(6).toString());
                    System.out.println(userServiceImp.findAll().size());

                    if (response > 0) {
                        Toast.makeText(Register.this, "CREATED ACCOUNT", Toast.LENGTH_LONG).show();
                        clean();
                    } else {
                        Toast.makeText(Register.this, "ACCOUNT WAS NOT CREATED", Toast.LENGTH_LONG).show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void clean(){
        if (txtEmail != null) txtEmail.setText("");
        if (txtPassword != null) txtPassword.setText("");
        if (txtName != null) txtName.setText("");
        if (txtLastName != null) txtLastName.setText("");
        if (txtIdentification != null) txtIdentification.setText("");
    }

}


