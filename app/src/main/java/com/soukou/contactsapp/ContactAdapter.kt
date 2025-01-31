package com.soukou.contactsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contacts: List<Contact>, private val
listener: OnItemClickListener):
RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    inner class ContactViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
                val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
                val phoneTextView: TextView =
                    itemView.findViewById(R.id.textViewPhone)
                        val editImageView: ImageView =
                            itemView.findViewById(R.id.imageViewEdit)
                            val deleteImageView: ImageView =
                                itemView.findViewById(R.id.imageViewDelete)

                                        init {
                                            editImageView.setOnClickListener {
                                                val position = adapterPosition
                                                if (position != RecyclerView.NO_POSITION) {
                                                    listener.onEditClick(contacts[position])
                                                }

                                            }
                                            deleteImageView.setOnClickListener {
                                                val position = adapterPosition
                                                if (position != RecyclerView.NO_POSITION)
                                                    listener.onDeleteClick(contacts[position])
                                            }
                                        }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        ContactViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
            return ContactViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: ContactViewHolder, position:Int) {
            val currentContact = contacts[position]
            holder.nameTextView.text = "${currentContact.firstName}${currentContact.lastName}"
            holder.phoneTextView.text = currentContact.phoneNumber
        }

        override fun getItemCount() = contacts.size

        interface OnItemClickListener {
            fun onEditClick(contact: Contact)
            fun onDeleteClick(contact: Contact)

        }
    }
}