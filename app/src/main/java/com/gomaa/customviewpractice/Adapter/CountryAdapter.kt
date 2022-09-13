package com.gomaa.customviewpractice.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gomaa.customviewpractice.R
import com.gomaa.customviewpractice.model.Country
import com.gomaa.customviewpractice.ui.CustomViews.CountryView

class CountryAdapter(val countryList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflate = layoutInflater.inflate(R.layout.coountry_items, parent,false)
        return CountryViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.countryView.setFlag(country.image)
        holder.countryView.setText(country.name)
    }

    override fun getItemCount(): Int = countryList.size

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryView = itemView.findViewById<CountryView>(R.id.country)
    }
}