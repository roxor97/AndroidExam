package com.talataa.androidexam.repositories.impl;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.talataa.androidexam.db.DbHelper;
import com.talataa.androidexam.model.Asteroids;
import com.talataa.androidexam.repositories.AsteroidRepositoryInterface;


public class AsteroidRepositoryImp extends DbHelper implements AsteroidRepositoryInterface {

    Context context;
    private static final String NASA_API_URL = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2023-03-26&end_date=2023-03-19&api_key=787Kx0yeoCLx6f5FUR17nH1X0l8HrNYh5gaQsC9r";


    public AsteroidRepositoryImp(Context context) {
        super(context);
        this.context=context;
    }


    @Override
    public void saveAsteroids(int user_id) {
        List<Asteroids> asteroids = getAsteroidsFromApi(user_id);

        if (asteroids != null && asteroids.size() > 0) {
            try {
                DbHelper dbHelper = new DbHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                for (Asteroids asteroid : asteroids) {
                    ContentValues values = new ContentValues();
                    values.put("id", asteroid.getId());
                    values.put("user_id", asteroid.getUser_id());
                    values.put("name", asteroid.getName());
                    values.put("estimated_diameter_min", asteroid.getEstimated_diameter_min());
                    values.put("estimated_diameter_max", asteroid.getEstimated_diameter_max());
                    values.put("is_potentially_hazardous_asteroid", asteroid.getIs_potentially_hazardous_asteroid());
                    values.put("absolute_magnitude", asteroid.getAbsolute_magnitude());

                    db.insert("asteroids", null, values);
                }

                db.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private List<Asteroids> getAsteroidsFromApi(Integer user_id) {
        List<Asteroids> asteroids = new ArrayList<>();

        try {
            URL url = new URL(NASA_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(response.toString()).getAsJsonObject();
            JsonObject nearEarthObjects = jsonObject.getAsJsonObject("near_earth_objects");

            Set<Map.Entry<String, JsonElement>> entries = nearEarthObjects.entrySet();
            for (Map.Entry<String, JsonElement> entry : entries) {
                JsonArray asteroidsArray = entry.getValue().getAsJsonArray();
                for (JsonElement asteroidElement : asteroidsArray) {
                    JsonObject asteroidObject = asteroidElement.getAsJsonObject();
                    int id = asteroidObject.get("neo_reference_id").getAsInt();
                    String name = asteroidObject.get("name").getAsString();
                    JsonObject estimatedDiameterObject = asteroidObject.get("estimated_diameter").getAsJsonObject();
                    double estimatedDiameterMin = estimatedDiameterObject.get("kilometers").getAsJsonObject().get("estimated_diameter_min").getAsDouble();
                    double estimatedDiameterMax = estimatedDiameterObject.get("kilometers").getAsJsonObject().get("estimated_diameter_max").getAsDouble();
                    boolean potentiallyHazardous = asteroidObject.get("is_potentially_hazardous_asteroid").getAsBoolean();
                    double absoluteMagnitude = asteroidObject.get("absolute_magnitude_h").getAsDouble();

                    Asteroids asteroid = new Asteroids();
                    asteroid.setId(id);
                    asteroid.setUserId(user_id);
                    asteroid.setName(name);
                    asteroid.setEstimatedDiameterMin((float) estimatedDiameterMin);
                    asteroid.setEstimatedDiameterMax((float) estimatedDiameterMax);
                    asteroid.setIsPotentiallyHazardousAsteroid(potentiallyHazardous);
                    asteroid.setAbsoluteMagnitude(absoluteMagnitude);

                    asteroids.add(asteroid);
                }
            }
        } catch (IOException e) {
            // Handle the exception here, e.g. log the error or throw a custom exception
        }

        return asteroids;
    }
    @Override
    public void deleteAsteroidsByUserId(int user_id) {
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.delete("asteroids", "user_id = ?", new String[]{String.valueOf(user_id)});
        } catch (Exception e) {
            System.out.println("Error deleting asteroids: " + e.getMessage());
        }
    }

}


