package com.example.task3.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Model {
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull

    @ColumnInfo(name = "college")
    private String college;
    @NonNull

    @ColumnInfo(name = "gender")
    private int gender;
    @NonNull

    @ColumnInfo(name = "password")
    private String password;

    @NonNull
    public int getGender() {
        return gender;
    }

    @NonNull
    @PrimaryKey
    public String id;

    public Model(String id, String name, String college, int gender, String password, double gpa) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.gender = gender;
        this.password = password;
        this.gpa = gpa;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int isGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull

    @ColumnInfo(name = "gpa")
    private double gpa;

    public void setId(String id) {
        this.id = id;
    }

}
