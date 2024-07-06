package com.example.apprestapi.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.apprestapi.model.Note
import com.example.apprestapi.model.NoteDatabase
import com.example.apprestapi.network.RetrofitClient
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    val notes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun addNoteToLocal(note: Note) {
        viewModelScope.launch {
            noteDao.insert(note)
            addNoteToRemote(note)
        }
    }

    private fun addNoteToRemote(note: Note) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.addNote(note)
                if (response.isSuccessful) {
                    // Handle successful response
                } else {
                    // Handle error response
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    fun deleteItem(note: Note) {
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }
}
