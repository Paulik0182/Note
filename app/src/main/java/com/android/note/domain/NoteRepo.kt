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
    fun addNote(noteEntity: NoteEntity) // добавить заметку

    //получить список всех заметок (чтение)
    fun getNotes(): List<NoteEntity?>//это именно функция которая взвращает список всех сущьностей.
    fun deleteNoteById(id: Int) //удалить заметку по ее id
    fun deleteAll() //удалить все заметки
    fun createRandomId(): Int
    fun update(changedNote: NoteEntity?)
}