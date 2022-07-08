package com.android.note.data

import android.graphics.Color
import com.android.note.domain.NoteEntity
import com.android.note.domain.NoteRepo

/**
 * этот клас для реализации интерфейса (NoteRepo)
 * здесь реализуем методы которые указаны в NoteRepo
 */
class NoteRepoImpl : NoteRepo {
    private var counter = 0
    override fun addNote(noteEntity: NoteEntity) {
        data.add(noteEntity) //добавляем заметку в список
    }

    override fun getNotes(): List<NoteEntity> {
        return ArrayList(data) //отдаем копию списка, копию коллекции (лучше отдавать копию чтобы никто не повредил оригинальные данные)
    }

    override fun deleteNoteById(id: Int) {
        for (i in data.indices) {
            if (data[i].id == id) {
                data.removeAt(i)
                break
            }
        }
    }

    override fun deleteAll() {
        data.clear()
    }

    override fun createRandomId(): Int {
        return counter++
    }

    override fun update(changedNote: NoteEntity) {
        val id = changedNote.id //это id который мы хотим изменить

        //поиск старой заметки
        var oldNote: NoteEntity? = null
        for (i in data.indices) {
            if (data[i].id == id) { //находим нужный id
                oldNote = data[i] //получаем нужный элемент
                break
            }
        }
        if (oldNote == null) { //если не нашли заметку, то добавляем заметку
            addNote(changedNote)
        } else {
            //если заметка существует, то меняем в ней setTitle, setContent, setColor
            oldNote.title = changedNote.title
            oldNote.content = changedNote.content
            oldNote.color = changedNote.color
        }
    }

    companion object {
        //заводим массив сущьности (хранилище заметок на основе массива)
        @JvmField
        var data: MutableList<NoteEntity> = ArrayList()
    }

    //добавляем данные (создаем список)
    init {
        addNote(
            NoteEntity(
                createRandomId(),
                "Заголовок",
                "Привет, Привет",
                Color.RED
            )
        )
        addNote(
            NoteEntity(
                createRandomId(),
                "Название",
                "Дорогой дневник",
                Color.BLUE
            )
        )
        addNote(
            NoteEntity(
                createRandomId(),
                "Hello",
                "Привет, Привет",
                Color.YELLOW
            )
        )
        addNote(
            NoteEntity(
                createRandomId(),
                "Заголовок",
                "Ура",
                Color.BLACK
            )
        )
    }
}