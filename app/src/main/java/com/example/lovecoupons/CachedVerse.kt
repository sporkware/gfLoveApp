package com.example.lovecoupons

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_verses")
data class CachedVerse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val reference: String,
    val timestamp: Long
)