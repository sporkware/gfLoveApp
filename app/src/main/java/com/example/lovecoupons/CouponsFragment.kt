package com.example.lovecoupons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CouponsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val coupons = listOf(
        "1/2 Hour Back Massage",
        "Unlimited Cuddling Session",
        "Breakfast in Bed",
        "Movie Night - You Choose the Film",
        "Foot Rub Anytime",
        "Hug Attack Whenever You Want",
        "No Dishes Night – I Handle Cleanup",
        "Personal Playlist – Songs That Remind Me of You 🎶",
        "Adventure Date – You Pick, I Plan",
        "Compliment Shower – 10 Minutes of Why You're Amazing 😘",
        "Cook Your Favorite Meal from Scratch 🍲",
        "Phone-Free Hour – Just Us Talking/Cuddling 📵",
        "Wild Card Wish – Anything Reasonable You Want ✨",
        "Memory Lane – Look at Old Pics & Share Stories 📸",
        "Dance Party in the Living Room 💃🕺",
        "Guilt-Free Nap – I Handle Everything 😴",
        "Handwritten Love Letter 💌",
        "Stargazing Night Under Blanket ⭐"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coupons, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CouponAdapter(coupons) { coupon ->
            Toast.makeText(context, getString(R.string.redeemed), Toast.LENGTH_SHORT).show()
        }
        return view
    }
}