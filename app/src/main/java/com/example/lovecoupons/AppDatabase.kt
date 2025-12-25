package com.example.lovecoupons

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [CachedHoroscope::class, CachedVerse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun horoscopeDao(): HoroscopeDao
    abstract fun verseDao(): VerseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "love_coupons_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}