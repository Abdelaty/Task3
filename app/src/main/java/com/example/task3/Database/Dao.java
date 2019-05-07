package com.example.task3.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@android.arch.persistence.room.Dao
public interface Dao {
    @Query("select id and name and gender and college and gpa from Model")
    LiveData<List<Model>> getAllStudents();

    @Query("select college from Model")
    LiveData<List<Model>> getAllColleges();

    @Insert(onConflict = REPLACE)
    void addStudent(Model borrowModel);

    @Delete
    void deleteStudent(Model borrowModel);
}
