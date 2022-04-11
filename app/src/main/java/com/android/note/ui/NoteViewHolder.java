package com.android.note.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.note.R;
import com.android.note.domain.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private NoteEntity noteEntity;

    public TextView titleTextView = itemView.findViewById(R.id.title_text_view);
    public TextView contentTextView = itemView.findViewById(R.id.content_text_view);

    //достаем переменную через аргумент, создали метод.
    public void setNoteEntity(NoteEntity noteEntity) {
        this.noteEntity = noteEntity;
    }

    //конструктор на входе у которого View
    public NoteViewHolder(@NonNull View itemView, NoteAdapter.InteractionListener listener) {
        super(itemView);

//        noteEntity = new NoteRepoImpl().getNotes();
        //обработка нажатия на item
        itemView.setOnClickListener(v -> {
            listener.onItemClickListener(noteEntity);
        });
    }

}
