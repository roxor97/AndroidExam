package com.talataa.androidexam.services.impl;


import com.talataa.androidexam.model.Asteroids;
import com.talataa.androidexam.repositories.impl.AsteroidRepositoryImp;
import com.talataa.androidexam.services.AsteroidServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class AsteroidServiceImp implements AsteroidServiceInterface {

    private final AsteroidRepositoryImp asteroidRepository;

    public AsteroidServiceImp(AsteroidRepositoryImp asteroidRepository) {
        this.asteroidRepository = asteroidRepository;
    }


    @Override
    public void saveAsteroids(int id ) throws Exception {
        try {
            asteroidRepository.saveAsteroids(id );
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAsteroidsByUserId(int userId) throws SQLException{
        asteroidRepository.deleteAsteroidsByUserId(userId);
    }

}
