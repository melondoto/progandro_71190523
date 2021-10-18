package com.example.pertemuan7_71190523

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (var listContact: ArrayList<Contact>, val onContactClickListener: OnContactClickListener, var context: Context): RecyclerView.Adapter<ContactAdapter.ContactHolder>() {
    class ContactHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(contact: Contact, context: Context){
            view.findViewById<ImageView>(R.id.imgContact).setImageResource(contact.image)
            view.findViewById<TextView>(R.id.txtName).setText(contact.name)
            view.findViewById<TextView>(R.id.txtNo).setText(contact.no)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactHolder(v)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(listContact[position], context)
        holder.itemView.setOnClickListener{
            onContactClickListener.onContactItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return listContact.size
    }
}