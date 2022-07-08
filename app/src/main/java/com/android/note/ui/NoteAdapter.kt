package com.android.note.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.note.R
import com.android.note.domain.NoteEntity

/**
 * У адаптера обязательно есть три метода которые нужно переапределить
 * - onCreateViewHolder
 * - onBindViewHolder
 * - getItemCount
 */
class NoteAdapter(
    private var data: List<NoteEntity>,
    private val listener: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteViewHolder>() {

    //создание ViewHolder (создаем view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        //создаем NoteViewHolder
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    //Связываем два поля указанные в конструкторе
    //на вход функции принемается сущность (модель) и привязываем эту модель к разметке
    //возможна любая комбинация, количество полей.
    //для каждой разметки может быть свая комбинация, набор полей.
    //к имеющемуся holder подставить данные
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteEntity =
            data[position] //получили данные. Счетчик - position, счетчик необходим для класса RecyclerView
        holder.bind(noteEntity, listener)
    }

    //вернуть количество данных
    override fun getItemCount(): Int =
        data.size //взяли размер массива. Возвращаем количество элементов в списк (size)

    //завели метод чтобы передать заметки в адаптер
    @SuppressLint("NotifyDataSetChanged")
    fun setData(notes: List<NoteEntity>) {
        data = notes
        notifyDataSetChanged() //обновляет данные
    }
}