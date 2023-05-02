package com.talataa.androidexam.services;

import com.talataa.androidexam.model.Asteroids;

import java.sql.SQLException;
import java.util.List;

public interface AsteroidServiceInterface {
    void saveAsteroids(int id) throws Exception;
    void deleteAsteroidsByUserId(int userId) throws SQLException;
}
