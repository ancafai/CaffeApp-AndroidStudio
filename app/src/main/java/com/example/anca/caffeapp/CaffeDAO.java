package com.example.anca.caffeapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by ancaon 12/12/2017.
 */

@Dao
public interface CaffeDAO {

    @Query("SELECT * FROM caffe")
    List<Caffe> getAllCaffes();

    @Insert
    void insertAll(Caffe... caffes);

    @Delete
    void delete(Caffe caffe);

    @Query("SELECT * FROM caffe where name LIKE  :name")
    Caffe findByName(String name);

    @Update
    void update(Caffe caffe);
}
