package com.tutorial.myapplication.data.local

import  android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tutorial.myapplication.Crime


private const val DATABASE_NAME = "crime-database"

@Database(entities = [Crime::class], version = 1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {

    abstract fun crimeDao(): CrimeDao

    companion object {
        private var INSTANCE: CrimeDatabase? = null

        fun initialize(context: Context) {
            if (INSTANCE == null)
                INSTANCE = buildDatabase(context)
        }

        fun getInstance() = INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")

        private fun buildDatabase(context: Context): CrimeDatabase = Room
            .databaseBuilder(
                context.applicationContext,
                CrimeDatabase::class.java,
                DATABASE_NAME
            )
            .createFromAsset(DATABASE_NAME)
            .build()
    }
}