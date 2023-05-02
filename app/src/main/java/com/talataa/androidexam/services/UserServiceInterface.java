package com.talataa.androidexam.services;

import com.talataa.androidexam.model.Users;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface {

    int save(Users user) throws SQLException;

    Users findById(int id) throws SQLException;

    List<Users> findAll() throws SQLException;

    void update(int id, Users updatedUser) throws SQLException;

    void deleteById(int id) throws SQLException;

}