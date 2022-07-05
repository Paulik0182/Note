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

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<NoteEntity> data; //список сущьностей
    private final InteractionListener listener; //слушатель

    //вместе со списком data, передаем объект listener
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
        NoteViewHolder viewHolder = new NoteViewHolder(itemView);
        return viewHolder;
    }

    //Связываем два поля указанные в конструкторе
    //на вход функции принемается сущность (модель) и привязываем эту модель к разметке
    //возможна любая комбинация, количество полей.
    //для каждой разметки может быть свая комбинация, набор полей.
    //к имеющемуся holder подставить данные
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        final NoteEntity noteEntity = data.get(position);//получили данные. Счетчик - position, счетчик необходим для класса RecyclerView

        holder.bind(noteEntity, listener);
    }

    //вернуть количество данных
    @Override
    public int getItemCount() {
        return data.size();//взяли размер массива. Возвращаем количество элементов в списк (size)
    }

    //завели метод чтобы передать заметки в адаптер
    public void setData(List<NoteEntity> notes) {
        data = notes;
        notifyDataSetChanged();//обновляет данные
    }

    /**
     * Данный интерфейс описывает контракт, а именно нажатие на кнопку.
     * Далее это нажатие необходимо реализовать в class NoteAdapter.
     * <p>
     * Интерфейсом нельзя создать объект, но передать можно. Можно передавать элементы которые сделаны поразному
     * (выполняют одно и тоже действие), но у них может быть разная реализация.
     * Обеспечивается единообразная работа с разными источниками данных.
     */

    public interface InteractionListener {
        void onItemClickListener(NoteEntity noteEntity);
    }
}
