package com.example.lovecoupons

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_horoscopes")
data class CachedHoroscope(
    @PrimaryKey val sign: String,
    val date: String,
    val description: String,
    val mood: String,
    val color: String,
    val luckyNumber: String,
    val luckyTime: String,
    val compatibility: String,
    val timestamp: Long
)