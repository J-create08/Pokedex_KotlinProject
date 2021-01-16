package com.project.pokedex.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
public abstract class PokemonDatabase : RoomDatabase() {
   abstract fun pokemonDao(): PokemonDAO

   companion object {
       @Volatile
       private var INSTANCE: PokemonDatabase? = null
       fun getDatabase(context: Context): PokemonDatabase {
           return INSTANCE?: synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   PokemonDatabase::class.java,
                   "pokemon_database"
               ).build()
               INSTANCE = instance
               instance
           }
       }
   }

}