package com.example.lovecoupons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class DatesFragment : Fragment() {

    private lateinit var signSpinner: Spinner
    private lateinit var getHoroscopeButton: Button
    private lateinit var horoscopeText: TextView
    private lateinit var progressBar: ProgressBar

    private val signs = arrayOf(
        "aries", "taurus", "gemini", "cancer", "leo", "virgo",
        "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces"
    )

    private val client = OkHttpClient()
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dates, container, false)

        database = AppDatabase.getDatabase(requireContext())

        signSpinner = view.findViewById(R.id.signSpinner)
        getHoroscopeButton = view.findViewById(R.id.getHoroscopeButton)
        horoscopeText = view.findViewById(R.id.horoscopeText)
        progressBar = view.findViewById(R.id.progressBar)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, signs)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        signSpinner.adapter = adapter

        getHoroscopeButton.setOnClickListener {
            val selectedSign = signs[signSpinner.selectedItemPosition]
            fetchHoroscope(selectedSign)
        }

        return view
    }

    private fun fetchHoroscope(sign: String) {
        lifecycleScope.launch {
            val cached = database.horoscopeDao().getHoroscope(sign)
            if (cached != null && System.currentTimeMillis() - cached.timestamp < 24 * 60 * 60 * 1000) { // 24 hours
                displayHoroscope(cached)
                return@launch
            }

            progressBar.visibility = View.VISIBLE
            horoscopeText.text = ""

            val url = "https://aztro.sameerkumar.website/?sign=$sign&day=today"
            val requestBody = "".toRequestBody("application/json".toMediaType())

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    activity?.runOnUiThread {
                        progressBar.visibility = View.GONE
                        horoscopeText.text = getString(R.string.error)
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) {
                            activity?.runOnUiThread {
                                progressBar.visibility = View.GONE
                                horoscopeText.text = getString(R.string.error)
                            }
                            return
                        }

                        val responseBody = response.body?.string()
                        val json = JSONObject(responseBody ?: "{}")
                        val date = json.optString("date", "")
                        val description = json.optString("description", "")
                        val mood = json.optString("mood", "")
                        val color = json.optString("color", "")
                        val luckyNumber = json.optString("lucky_number", "")
                        val luckyTime = json.optString("lucky_time", "")
                        val compatibility = json.optString("compatibility", "")

                        val horoscope = CachedHoroscope(
                            sign = sign,
                            date = date,
                            description = description,
                            mood = mood,
                            color = color,
                            luckyNumber = luckyNumber,
                            luckyTime = luckyTime,
                            compatibility = compatibility,
                            timestamp = System.currentTimeMillis()
                        )

                        lifecycleScope.launch {
                            database.horoscopeDao().insertHoroscope(horoscope)
                        }

                        activity?.runOnUiThread {
                            progressBar.visibility = View.GONE
                            displayHoroscope(horoscope)
                        }
                    }
                }
            })
        }
    }

    private fun displayHoroscope(horoscope: CachedHoroscope) {
        val text = """
            Date: ${horoscope.date}
            Description: ${horoscope.description}
            Mood: ${horoscope.mood}
            Color: ${horoscope.color}
            Lucky Number: ${horoscope.luckyNumber}
            Lucky Time: ${horoscope.luckyTime}
            Compatibility: ${horoscope.compatibility}
        """.trimIndent()
        horoscopeText.text = text
    }
}