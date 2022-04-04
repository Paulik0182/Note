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
import com.android.note.domain.NoteRepoImpl;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<NoteEntity> data;
    private InteractionListener listener;

    public NoteAdapter(List<NoteEntity> data, InteractionListener listener) {
        this.data = new ArrayList<>(data);
        this.listener = listener;
    }

    //создание ViewHolder (создаем view)
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //создаем NoteViewHolder
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_note, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView, listener);
        return viewHolder;
    }

    //к имеющемуся holder подставить данные
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
        notifyDataSetChanged();//обновляет данные
    }

    public interface InteractionListener{
        void  onItemClickListener(NoteRepoImpl noteRepoImpl);
    }
}
