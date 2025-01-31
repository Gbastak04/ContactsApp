package com.soukou.contactsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() ,
ContactAdapter.OnItemClickListener {
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager  = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.allContacts.observe(this, Observer {
            contacts ->
                contacts?.let {
                    adapter = ContactAdapter(it, this)
                    recyclerView.adapter = adapter
                }
        })

    }
    override fun onEditClick(contact: Contact) {
        //ενέργειες gia thn επεξεργασία της επαφής
    }

    override fun onDeleteClick(contact: Contact) {
        //ενέργειες για την διαγραφή της επαφής
    }
}