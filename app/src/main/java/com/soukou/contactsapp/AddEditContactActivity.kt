package com.soukou.contactsapp

class AddEditContactActivity : AppContactActivity() {
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button

    private lateinit var contactViewModel: ContactViewModel
    private var contactId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_contact)

        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        val intent = intent
        if (intent.hasExtra("contact_id")) {
            title = "Edit Contact"
            contactId = intent.getIntExtra("contact_id", -1)
            editTextFirstName.setText(intent.getStringExtra("contact_first_name"))
            editTextLastName.setText(intent.getStringExtra("contact_last_name"))
            editTextPhoneNumber.setText(intent.getStringExtra("contact_phone_number"))
            editTextEmail.setText(intent.getStringExtra("contact_email"))
        } else {
            title = "Add Contact"
        }

        buttonSave.setOnClickListener {
            saveContact()
        }

        buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveContact() {
        val firstName = editTextFirstName.text.toString()
        val lastName = editTextLastName.text.toString()
        val phoneNumber = editTextPhoneNumber.text.toString()
        val email = editTextEmail.text.toString()

        if (firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val contact = Contact(
            id = contactId ?: 0,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            email = email
        )

        if (contactId != null) {
            contactViewModel.update(contact)
        } else {
            contactViewModel.insert(contact)
        }

        finish()
    }
}