package com.talataa.androidexam.services.impl;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.talataa.androidexam.model.Users;
import com.talataa.androidexam.repositories.impl.AsteroidRepositoryImp;
import com.talataa.androidexam.repositories.impl.UsersRepositoryImp;
import com.talataa.androidexam.services.UserServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImp implements UserServiceInterface {

    private UsersRepositoryImp usersRepositoryImp;


    public UserServiceImp(UsersRepositoryImp usersRepositoryImp) {
        this.usersRepositoryImp = usersRepositoryImp;

    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int save(Users user) throws SQLException {
        int id;
        try {
            id = usersRepositoryImp.save(user);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Users findById(int id) throws SQLException {
        try {
            return usersRepositoryImp.findById(id);
        }catch (SQLException e){
            throw new SQLException("Error al intentar recuperar un usuario.", e);
        }
    }

    @Override
    public List<Users> findAll() throws SQLException {
        try {
            return usersRepositoryImp.findAll();
        } catch (SQLException e) {
            throw new SQLException("Error al intentar recuperar todos los usuarios.", e);
        }
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void update(int id, Users updatedUser) throws SQLException {
        try {
            usersRepositoryImp.update(id,updatedUser);
        }catch (SQLException e){
            throw new SQLException("Error al intentar recuperar todos los usuarios.", e);
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        try {
            usersRepositoryImp.deleteById(id);
        }catch (SQLException e){
            throw new SQLException("Error al intentar recuperar todos los usuarios.", e);
        }
    }

}
