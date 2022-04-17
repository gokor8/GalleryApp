package com.example.galleryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.galleryapp.R
import com.example.galleryapp.ui.activities.addons.RedirectStates

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Imitation some validates
        var state = RedirectStates.UnAuthorizate
        goToValidActivity(state)
        finish()
    }

    private fun goToValidActivity(state: RedirectStates){
        val activityClass = when(state){
            RedirectStates.UnAuthorizate ->
                SignActivity::class.java
            RedirectStates.Authorizate ->
                UserActivity::class.java
        }

        startActivity(Intent(this, activityClass))
    }

}