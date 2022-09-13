package com.gomaa.customviewpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import androidx.recyclerview.widget.RecyclerView
import com.gomaa.customviewpractice.Adapter.CountryAdapter
import com.gomaa.customviewpractice.model.Country

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        val countryList: List<Country> = listOf(
            Country("مصر", R.drawable.egypt),
            Country("الجزائر", R.drawable.algzair),
            Country("البانيا", R.drawable.elbania),
            Country("البرازيل", R.drawable.brazil),
            Country("الأرجنتين", R.drawable.argantin),
            Country("أستراليا", R.drawable.australia),
            Country("الكويت", R.drawable.kwat),
            Country("ماليزيا", R.drawable.malysia),
            Country("لبنان", R.drawable.lebanon),
            Country("ليبيا", R.drawable.libya)
        )

        val adapter = CountryAdapter(countryList)
        val countryRecycler = findViewById<RecyclerView>(R.id.countryRecycler)
        countryRecycler.adapter = adapter
    }
}