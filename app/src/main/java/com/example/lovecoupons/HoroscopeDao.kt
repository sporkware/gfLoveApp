package com.example.lovecoupons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HoroscopeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHoroscope(horoscope: CachedHoroscope)

    @Query("SELECT * FROM cached_horoscopes WHERE sign = :sign LIMIT 1")
    suspend fun getHoroscope(sign: String): CachedHoroscope?

    @Query("DELETE FROM cached_horoscopes WHERE timestamp < :oldTimestamp")
    suspend fun deleteOldHoroscopes(oldTimestamp: Long)
}