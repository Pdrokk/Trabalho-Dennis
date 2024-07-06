package com.example.apprestapi

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprestapi.adapter.ListAdapter
import com.example.apprestapi.model.Note
import com.example.apprestapi.viewmodel.ListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val noteList = mutableListOf<Note>()
    private lateinit var listAdapter: ListAdapter
    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val buttonAdd: FloatingActionButton = findViewById(R.id.buttonAdd)
        val buttonDelete: FloatingActionButton = findViewById(R.id.buttonDelete)

        listAdapter = ListAdapter(noteList)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.notes.observe(this, Observer { notes ->
            noteList.clear()
            noteList.addAll(notes)
            listAdapter.notifyDataSetChanged()
        })

        buttonAdd.setOnClickListener {
            startActivity(Intent(this, AddListActivity::class.java))
        }

        buttonDelete.setOnClickListener {
            if (noteList.isNotEmpty()) {
                val noteToRemove = noteList[noteList.size - 1]
                viewModel.deleteItem(noteToRemove)
            }
        }
    }
}