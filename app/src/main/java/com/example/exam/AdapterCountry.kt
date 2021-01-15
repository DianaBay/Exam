package com.example.exam

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterCountry(val countries: ArrayList<Country>, val context: Context): RecyclerView.Adapter<AdapterCountry.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_country, parent, false)
        return MyViewHolder (view as RecyclerView)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = countries.get(position)
        holder.countryText.text = country.country
    }

    class MyViewHolder(view: RecyclerView): RecyclerView.ViewHolder(view){
        var countryText: TextView = view.findViewById(R.id.countryText)
    }
}