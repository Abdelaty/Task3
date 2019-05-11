package com.example.task3.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@android.arch.persistence.room.Dao
public interface Dao {
    @Query("select * from Model")
    LiveData<List<Model>> getAllStudents();
//
//    @Query("select college from Model")
//    List<Model> getAllColleges();

    @Query("select id and password from Model where id like :studentId and password like:studentPassword")
    Boolean checkAuth(int studentId, String studentPassword);

    @Query("select name from Model where id like :studentId")
    String getName(int studentId);

    @Insert
    void addStudent(Model... studentModel);

    @Delete
    void deleteStudent(Model... studentModel);

    @Update
    int updateStudent(Model... studentModel);

}

