package com.example.barunyoba.ui.skill

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.barunyoba.R

class SkillDetail : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        // Mendapatkan data yang dikirim dari RecyclerView (contoh: title dan logo)
        val title = intent.getStringExtra("title")
        val logoResId = intent.getIntExtra("logoResId", 0)

        // Mengatur data ke tampilan detail
        val titleTextView = findViewById<TextView>(R.id.titleTv)
        val logoImageView = findViewById<ImageView>(R.id.logoIv)

        // Mengatur judul aktivitas sesuai dengan title yang dikirim
        title?.let {
            setTitle(it)
        }

        titleTextView.text = title
        logoImageView.setImageResource(logoResId)
    }
}
