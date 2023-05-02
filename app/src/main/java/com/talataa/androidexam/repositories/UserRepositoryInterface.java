package com.talataa.androidexam.repositories;

import com.talataa.androidexam.model.Users;

import java.util.List;
import java.sql.SQLException;


public interface UserRepositoryInterface {

    public int save(Users user) throws SQLException;

    public Users findById(int id) throws SQLException;

    public List<Users> findAll() throws SQLException;

    public void update(int id, Users updatedUser) throws SQLException;

    public void deleteById(int id) throws SQLException;


}
