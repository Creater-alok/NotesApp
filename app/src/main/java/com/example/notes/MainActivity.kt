package com.example.notes

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    private lateinit var viewModel: NoteViewModel
    private lateinit var input:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this)
        recyclerView.adapter=adapter


        //provider gives ViewModel
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                adapter.allNotes = it
            }
        }

    }
    override fun onItemCLicked(note: Note) {
    viewModel.delete(note)
    }

    override fun onNoteCLicked(note: Note) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitData(view: View) {
        input = findViewById(R.id.insert)
        val dateinput = LocalDateTime.now().toString()
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()){
            //to make noteText as Note we pass Note(noteText)
            viewModel.insertNote(Note(noteText,dateinput))
        }
        input.text.clear()
    }
}