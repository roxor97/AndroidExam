package com.talataa.androidexam.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Users {
    private Integer id;
    private String email;
    private String encrypted_password;
    private String first_name;
    private String last_name;
    private String identification;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Users(Integer id, String email, String encrypted_password, String first_name, String last_name, String identification, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.email = email;
        this.encrypted_password = encrypted_password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.identification = identification;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public Users() {

    }

    public Users(Integer id, String email, String encrypted_password, String first_name, String last_name, String identification) {
        this.id = id;
        this.email = email;
        this.encrypted_password = encrypted_password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.identification = identification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(email, users.email) && Objects.equals(encrypted_password, users.encrypted_password) && Objects.equals(first_name, users.first_name) && Objects.equals(last_name, users.last_name) && Objects.equals(identification, users.identification) && Objects.equals(created_at, users.created_at) && Objects.equals(updated_at, users.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, encrypted_password, first_name, last_name, identification, created_at, updated_at);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", encrypted_password='" + encrypted_password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", identification='" + identification + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
