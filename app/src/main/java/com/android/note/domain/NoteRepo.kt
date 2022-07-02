package com.android.note.domain

/**
 * Repository
 * CRUD хранилище - это такое хранилище которое предлогает четыре способа обработки данных
 * - Create Создавать
 * - Read Читать
 * - Update Обновлять
 * - Delete Удалять
 * Репозиторий не ограничивается только этими параметрами
 *
 *
 * Взаимодействия всегда осуществляется через интерфейсы
 */
interface NoteRepo {
    //Интерфейсы взаимодействия
    fun addNote(noteEntity: NoteEntity?) // добавить заметку

    //получить список всех заметок (чтение)
    val notes: List<NoteEntity?>?
    fun deleteNoteById(id: Int) //удалить заметку по ее id
    fun deleteAll() //удалить все заметки
    fun createRandomId(): Int
    fun update(changedNote: NoteEntity?)
}