package com.talataa.androidexam.repositories;

import com.talataa.androidexam.model.Asteroids;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AsteroidRepositoryInterface {

    void saveAsteroids(int user_id) throws IOException, SQLException;

    void deleteAsteroidsByUserId(int userId) throws SQLException;
}
