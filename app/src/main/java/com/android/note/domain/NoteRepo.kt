package com.android.note.domain;

import java.util.List;

/**
 * Repository
 * CRUD хранилище - это такое хранилище которое предлогает четыре способа обработки данных
 * - Create Создавать
 * - Read Читать
 * - Update Обновлять
 * - Delete Удалять
 * Репозиторий не ограничивается только этими параметрами
 * <p>
 * Взаимодействия всегда осуществляется через интерфейсы
 */

public interface NoteRepo {

    //Интерфейсы взаимодействия
    void addNote(NoteEntity noteEntity);// добавить заметку

    List<NoteEntity> getNotes();//получить список всех заметок (чтение)

    void deleteNoteById(int id);//удалить заметку по ее id

    void deleteAll();//удалить все заметки

    int createRandomId();

    void update(NoteEntity changedNote);
}
