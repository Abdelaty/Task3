package com.example.task3.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity
public class CollegeModel {
//    @NonNull
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    private int id;

    public CollegeModel(@NonNull String collegeName) {
//        this.id = id;
        this.collegeName = collegeName;
    }
//
//    @NonNull
//    public int getId() {
//        return id;
//    }
//
//    public void setId(@NonNull int id) {
//        this.id = id;
//    }

    @NonNull
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(@NonNull String collegeName) {
        this.collegeName = collegeName;
    }
@PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String collegeName;
}
