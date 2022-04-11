package com.android.note.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.note.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTextView = itemView.findViewById(R.id.title_text_view);
    public TextView contentTextView = itemView.findViewById(R.id.content_text_view);

    //конструктор на входе у которого View
    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);

    }
}
