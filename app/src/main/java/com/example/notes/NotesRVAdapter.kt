package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val diffCallback = object: DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.text==newItem.text
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private val differ= AsyncListDiffer(this,diffCallback)

    var allNotes: List<Note>
    get() = differ.currentList
    set(value) {differ.submitList(value)}

    inner class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.textView)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)
        val date:TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemCLicked(allNotes[viewHolder.adapterPosition])
        }
        viewHolder.textView.setOnClickListener{
            listener.onNoteCLicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    holder.textView.text= allNotes[position].text
        holder.date.text= allNotes[position].date
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

}