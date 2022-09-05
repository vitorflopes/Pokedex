package com.example.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.dao.CampeaoDao
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.Campeao
import com.example.pokedex.model.Pokemon

/*
@Database (
    entities = arrayOf(Campeao::class),
    version = 1
    )

abstract class AppDatabase : RoomDatabase() {
    abstract fun campeaoDao() : CampeaoDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, AppDatabase::class.java,
                    "database.db").build()
            }
            return INSTANCE as AppDatabase
        }
    }
}
*/