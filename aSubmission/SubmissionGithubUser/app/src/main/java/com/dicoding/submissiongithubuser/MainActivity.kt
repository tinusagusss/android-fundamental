package com.dicoding.submissiongithubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvUser: ImageView = findViewById(R.id.imageView)
        Glide.with(this@MainActivity)
            .load("https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/small/avatar/dos:119e4522e05268a50db09adce0d10ebd20230309183553.png")
            .into(tvUser)


    }
}