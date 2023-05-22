package com.example.holidays.presentation.holidays

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.holidays.R
import com.example.holidays.data.model.Holidays

class HolidaysAdapter(private val holidays: Holidays) : RecyclerView.Adapter<HolidaysAdapter.HolidaysAdapter>() {

    class HolidaysAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val holidayTextView: TextView = itemView.findViewById(R.id.holidayTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidaysAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holiday_item, parent, false)
        return HolidaysAdapter(view)
    }


    override fun onBindViewHolder(holder: HolidaysAdapter, position: Int) {
        val holiday = holidays[position]

        holder.holidayTextView.text = holiday.name
        holder.descriptionTextView.text = holiday.description
    }

    override fun getItemCount(): Int {
        return holidays.size
    }
}