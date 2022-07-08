package com.android.note.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.note.R
import com.android.note.domain.NoteEntity

class NoteViewHolder  //конструктор на входе у которого View
    (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
    private val contentTextView = itemView.findViewById<TextView>(R.id.content_text_view)
    fun bind(noteEntity: NoteEntity, listener: (NoteEntity) -> Unit) {
        titleTextView.text = noteEntity.title
        contentTextView.text = noteEntity.content
        itemView.setOnClickListener { _ -> listener.invoke(noteEntity) }
        // когда аргумент не нужен пишут вот так  _ -> (подчеркивание) или ничего не пишут
    }
}