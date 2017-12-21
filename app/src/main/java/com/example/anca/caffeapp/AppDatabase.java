package com.example.anca.caffeapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by anca on 12/12/2017.
 */

@Database(entities = {Caffe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CaffeDAO caffeDAO();
}
