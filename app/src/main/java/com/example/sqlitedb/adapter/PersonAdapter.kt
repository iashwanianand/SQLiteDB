package com.example.sqlitedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedb.databinding.LayoutDetailsBinding
import com.example.sqlitedb.model.PersonData

class PersonAdapter(private val context: Context, private val person: ArrayList<PersonData>) :
    RecyclerView.Adapter<PersonAdapter.MyViewHolder>() {
    private lateinit var binding: LayoutDetailsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = LayoutDetailsBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val personData = person[position]
        binding.name.text = personData.name
        binding.email.text = personData.email
        binding.phone.text = personData.phone
    }

    override fun getItemCount(): Int {
        return person.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}