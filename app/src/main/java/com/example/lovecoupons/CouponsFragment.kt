package com.example.lovecoupons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class CouponsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewKonfetti: KonfettiView
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
        viewKonfetti = view.findViewById(R.id.viewKonfetti)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CouponAdapter(coupons) { coupon ->
            Toast.makeText(context, getString(R.string.redeemed), Toast.LENGTH_SHORT).show()
            showConfetti()
        }
        return view
    }

    private fun showConfetti() {
        viewKonfetti.build()
            .addColors(resources.getColor(R.color.pink_500), resources.getColor(R.color.red_200))
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.Square, Shape.Circle)
            .addSizes(Size(12))
            .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
            .streamFor(300, 5000L)
    }
}