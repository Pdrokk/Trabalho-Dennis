package com.example.apprestapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.apprestapi.model.Note
import com.example.apprestapi.viewmodel.ListViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddListActivity : AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        val editTextTitle: TextInputEditText = findViewById(R.id.editTextTitle)
        val editTextDescription: TextInputEditText = findViewById(R.id.editTextDescription)
        val buttonSave: MaterialButton = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()

            val note = Note(
                title = title,
                body = description
            )

            viewModel.addNoteToLocal(note)
            finish()
        }
    }
}