package com.tutorial.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tutorial.myapplication.Crime


private const val DATABASE_NAME = "crime-database"

@Database(entities = [Crime::class], version = 2)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {

    abstract fun crimeDao(): CrimeDao

    companion object {
        private var instance: CrimeDatabase? = null

        fun initialize(context: Context) {
            if (instance == null) {
                synchronized(CrimeDatabase::class.java) {
                    if (instance == null) {
                        instance = buildDatabase(context)
                    }
                }
            }
        }

        fun getInstance() = instance ?: throw IllegalStateException("CrimeDatabase must be initialized")

        private fun buildDatabase(context: Context): CrimeDatabase = Room
            .databaseBuilder(
                context.applicationContext,
                CrimeDatabase::class.java,
                DATABASE_NAME
            )
            .createFromAsset(DATABASE_NAME)
            .addMigrations(MIGRATION_1_2,)
            .build()


        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE crime ADD COLUMN requiresPolice INTEGER DEFAULT null"
                )
            }
        }
    }
}