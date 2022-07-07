package com.android.note.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.note.R
import com.android.note.domain.NoteEntity

class NoteViewHolder  //конструктор на входе у которого View
    (itemView: View) : RecyclerView.ViewHolder(itemView) {
    var titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    var contentTextView = itemView.findViewById<TextView>(R.id.content_text_view)
    fun bind(noteEntity: NoteEntity, listener: InteractionListener) {
        titleTextView.text = noteEntity.title
        contentTextView.text = noteEntity.content
        itemView.setOnClickListener { v: View -> listener.onItemClickListener(noteEntity) }
    }
}