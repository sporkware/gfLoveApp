package com.example.lovecoupons

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HoroscopeDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var horoscopeDao: HoroscopeDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        horoscopeDao = database.horoscopeDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetHoroscope() = runBlocking {
        val horoscope = CachedHoroscope(
            sign = "aries",
            date = "2023-12-25",
            description = "Today is a great day",
            mood = "Happy",
            color = "Red",
            luckyNumber = "7",
            luckyTime = "10 AM",
            compatibility = "Leo",
            timestamp = System.currentTimeMillis()
        )

        horoscopeDao.insertHoroscope(horoscope)
        val retrieved = horoscopeDao.getHoroscope("aries")

        assertEquals(horoscope, retrieved)
    }

    @Test
    fun getNonExistentHoroscope() = runBlocking {
        val retrieved = horoscopeDao.getHoroscope("nonexistent")
        assertNull(retrieved)
    }
}