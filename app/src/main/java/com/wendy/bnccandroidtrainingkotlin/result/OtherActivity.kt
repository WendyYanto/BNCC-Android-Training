package com.wendy.bnccandroidtrainingkotlin.result

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.wendy.bnccandroidtrainingkotlin.R

class OtherActivity : AppCompatActivity() {

    companion object {
        const val CONTENT = "content"
    }

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val word = intent.getStringExtra("content")

        word?.let {
            Toast.makeText(this, word, Toast.LENGTH_LONG).show()
        } ?: run {
            Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()
        }

        imageView = findViewById(R.id.iv_image)
        Glide.with(this).load("https://bncc.net/assets/img/events/Alvion%202012.png").into(imageView)
    }
}
