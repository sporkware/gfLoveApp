package com.example.lovecoupons

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val heartIcon = findViewById<ImageView>(R.id.heartIcon)

        // Play heartbeat sound
        val mediaPlayer = MediaPlayer.create(this, R.raw.heartbeat)
        mediaPlayer.start()

        // Animate heart
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        fadeIn.duration = 1000
        heartIcon.startAnimation(fadeIn)
        heartIcon.alpha = 1.0f

        // Scale animation
        val scaleAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        scaleAnim.duration = 1500
        heartIcon.startAnimation(scaleAnim)

        // Go to main after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            mediaPlayer.stop()
            mediaPlayer.release()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}