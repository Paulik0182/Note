package com.android.note.ui;
/**
 * У адаптера обязательно есть три метода которые нужно переапределить
 * - onCreateViewHolder
 * - onBindViewHolder
 * - getItemCount
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.note.R;
import com.android.note.domain.NoteEntity;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<NoteEntity> data;

    //создание ViewHolder (создаем view)
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //создаем NoteViewHolder
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_note, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView);
        return viewHolder;
    }

    //к имеющимуся holder подставить данные
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        final NoteEntity noteEntity = data.get(position);//получили данные

        holder.titleTextView.setText(noteEntity.getTitle());
        holder.contentTextView.setText(noteEntity.getContent());
    }

    //вернуть количество данных
    @Override
    public int getItemCount() {
        return data.size();//взяли размер массива
    }

    //завели метод чтобы передать заметки в адаптер
    public void setData(List<NoteEntity> notes){
        data = notes;
    }
}
