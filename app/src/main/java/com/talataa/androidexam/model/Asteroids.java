package com.talataa.androidexam.model;


import java.util.Objects;

public class Asteroids {
    private Integer id;
    private String name;
    private Float estimated_diameter_min;
    private Float estimated_diameter_max;
    private Boolean is_potentially_hazardous_asteroid;
    private Double absolute_magnitude;
    private Integer user_id;

    public Asteroids(Integer id, String name, Float estimated_diameter_min, Float estimated_diameter_max, Boolean is_potentially_hazardous_asteroid, Double orbital_period, Double absolute_magnitude, Integer user_id) {
        this.id = id;
        this.name = name;
        this.estimated_diameter_min = estimated_diameter_min;
        this.estimated_diameter_max = estimated_diameter_max;
        this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
        this.absolute_magnitude = absolute_magnitude;
        this.user_id = user_id;
    }

    public Asteroids() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getEstimated_diameter_min() {
        return estimated_diameter_min;
    }

    public void setEstimatedDiameterMin(Float estimated_diameter_min) {
        this.estimated_diameter_min = estimated_diameter_min;
    }

    public Float getEstimated_diameter_max() {
        return estimated_diameter_max;
    }

    public void setEstimatedDiameterMax(Float estimated_diameter_max) {
        this.estimated_diameter_max = estimated_diameter_max;
    }

    public Boolean getIs_potentially_hazardous_asteroid() {
        return is_potentially_hazardous_asteroid;
    }

    public void setIsPotentiallyHazardousAsteroid(Boolean is_potentially_hazardous_asteroid) {
        this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
    }

    public Double getAbsolute_magnitude() {
        return absolute_magnitude;
    }

    public void setAbsoluteMagnitude(Double absolute_magnitude) {
        this.absolute_magnitude = absolute_magnitude;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asteroids asteroids = (Asteroids) o;
        return Objects.equals(id, asteroids.id) && Objects.equals(name, asteroids.name) && Objects.equals(estimated_diameter_min, asteroids.estimated_diameter_min) && Objects.equals(estimated_diameter_max, asteroids.estimated_diameter_max) && Objects.equals(is_potentially_hazardous_asteroid, asteroids.is_potentially_hazardous_asteroid) && Objects.equals(absolute_magnitude, asteroids.absolute_magnitude) && Objects.equals(user_id, asteroids.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, estimated_diameter_min, estimated_diameter_max, is_potentially_hazardous_asteroid, absolute_magnitude, user_id);
    }

    @Override
    public String toString() {
        return "Asteroids{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", estimated_diameter_min=" + estimated_diameter_min +
                ", estimated_diameter_max=" + estimated_diameter_max +
                ", is_potentially_hazardous_asteroid=" + is_potentially_hazardous_asteroid +
                ", absolute_magnitude=" + absolute_magnitude +
                ", user_id=" + user_id +
                '}';
    }
}
