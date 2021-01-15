package com.example.exam

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterStatistics(val statistics: ArrayList<Statistics>, val context: Context): RecyclerView.Adapter<AdapterStatistics.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_statistics, parent, false)
        return MyViewHolder (view as RecyclerView)
    }

    override fun getItemCount(): Int {
        return statistics.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val statistics = statistics.get(position)
        holder.idText.text = statistics.ID
        holder.countryText.text = statistics.country
        holder.countryCodeText.text = statistics.countryCode
        holder.confirmedText.text = statistics.confirmed.toString()
        holder.deathsText.text = statistics.deaths.toString()
        holder.activeText.text = statistics.active.toString()
        holder.dateText.text = statistics.date
    }

    class MyViewHolder(view: RecyclerView): RecyclerView.ViewHolder(view){
        var idText: TextView = view.findViewById(R.id.idTextView)
        var countryText: TextView = view.findViewById(R.id.countryTextView)
        var countryCodeText: TextView = view.findViewById(R.id.countryCodeTextView)
        var confirmedText: TextView = view.findViewById(R.id.confirmedTextView)
        var deathsText: TextView = view.findViewById(R.id.deathsTextView)
        var activeText: TextView = view.findViewById(R.id.activeTextView)
        var dateText: TextView = view.findViewById(R.id.dateTextView)
    }
}