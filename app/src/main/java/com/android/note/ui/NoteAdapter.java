package com.android.note.ui;
/**
 * У адаптера обязательно есть три метода которые нужно переапределить
 * - onCreateViewHolder
 * - onBindViewHolder
 * - getItemCount
 */

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.note.domain.NoteEntity;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NoteEntity> data;

    //создание ViewHolder (создаем view)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }


    //к имеющимуся holder подставить данные
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final NoteEntity noteEntity = data.get(position);//получили данные
        //todo положить в holder
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
