package com.example.lovecoupons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VerseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVerse(verse: CachedVerse)

    @Query("SELECT * FROM cached_verses ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestVerse(): CachedVerse?

    @Query("DELETE FROM cached_verses WHERE timestamp < :oldTimestamp")
    suspend fun deleteOldVerses(oldTimestamp: Long)
}