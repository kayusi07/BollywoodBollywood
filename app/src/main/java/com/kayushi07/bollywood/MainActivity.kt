package com.kayushi07.bollywood

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load an ad into the AdMob banner view.
        val adView = findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build()
        adView.loadAd(adRequest)

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
//        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()



        val b_easy = findViewById(R.id.b_easy) as Button
//        val b_medium = findViewById(R.id.b_medium) as Button
//        val b_diffi = findViewById(R.id.b_diffi) as Button
//        val b_very_diffi = findViewById(R.id.b_veryDiffi) as Button

        b_easy.setOnClickListener{
            //            Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()

            val i = Intent(this, GameActivity::class.java);
            startActivity(i);
        }
    }

    override fun onDestroy() {

        val editor = getSharedPreferences("Bollywood Score", Context.MODE_PRIVATE).edit()
        editor.putInt("gameScore", 0)

        editor.apply();

        super.onDestroy()

    }
}
